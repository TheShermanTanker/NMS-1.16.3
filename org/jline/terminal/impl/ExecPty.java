/*     */ package org.jline.terminal.impl;
/*     */ 
/*     */ import java.io.FileDescriptor;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
/*     */ import org.jline.terminal.Attributes;
/*     */ import org.jline.terminal.Size;
/*     */ import org.jline.terminal.spi.Pty;
/*     */ import org.jline.utils.ExecHelper;
/*     */ import org.jline.utils.OSUtils;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ExecPty
/*     */   extends AbstractPty
/*     */   implements Pty
/*     */ {
/*     */   private final String name;
/*     */   private final boolean system;
/*     */   
/*     */   public static Pty current() throws IOException {
/*     */     try {
/*  41 */       String result = ExecHelper.exec(true, new String[] { OSUtils.TTY_COMMAND });
/*  42 */       return new ExecPty(result.trim(), true);
/*  43 */     } catch (IOException e) {
/*  44 */       throw new IOException("Not a tty", e);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected ExecPty(String name, boolean system) {
/*  49 */     this.name = name;
/*  50 */     this.system = system;
/*     */   }
/*     */ 
/*     */   
/*     */   public void close() throws IOException {}
/*     */ 
/*     */   
/*     */   public String getName() {
/*  58 */     return this.name;
/*     */   }
/*     */ 
/*     */   
/*     */   public InputStream getMasterInput() {
/*  63 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */   
/*     */   public OutputStream getMasterOutput() {
/*  68 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */   
/*     */   protected InputStream doGetSlaveInput() throws IOException {
/*  73 */     return this.system ? new FileInputStream(FileDescriptor.in) : new FileInputStream(
/*     */         
/*  75 */         getName());
/*     */   }
/*     */ 
/*     */   
/*     */   public OutputStream getSlaveOutput() throws IOException {
/*  80 */     return this.system ? new FileOutputStream(FileDescriptor.out) : new FileOutputStream(
/*     */         
/*  82 */         getName());
/*     */   }
/*     */ 
/*     */   
/*     */   public Attributes getAttr() throws IOException {
/*  87 */     String cfg = doGetConfig();
/*  88 */     return doGetAttr(cfg);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void doSetAttr(Attributes attr) throws IOException {
/*  93 */     List<String> commands = getFlagsToSet(attr, getAttr());
/*  94 */     if (!commands.isEmpty()) {
/*  95 */       commands.add(0, OSUtils.STTY_COMMAND);
/*  96 */       if (!this.system) {
/*  97 */         commands.add(1, OSUtils.STTY_F_OPTION);
/*  98 */         commands.add(2, getName());
/*     */       } 
/*     */       try {
/* 101 */         ExecHelper.exec(this.system, commands.<String>toArray(new String[commands.size()]));
/* 102 */       } catch (IOException e) {
/*     */         
/* 104 */         if (e.toString().contains("unable to perform all requested operations")) {
/* 105 */           commands = getFlagsToSet(attr, getAttr());
/* 106 */           if (!commands.isEmpty()) {
/* 107 */             throw new IOException("Could not set the following flags: " + String.join(", ", commands), e);
/*     */           }
/*     */         } else {
/* 110 */           throw e;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected List<String> getFlagsToSet(Attributes attr, Attributes current) {
/* 117 */     List<String> commands = new ArrayList<>();
/* 118 */     for (Attributes.InputFlag flag : Attributes.InputFlag.values()) {
/* 119 */       if (attr.getInputFlag(flag) != current.getInputFlag(flag)) {
/* 120 */         commands.add((attr.getInputFlag(flag) ? flag.name() : ("-" + flag.name())).toLowerCase());
/*     */       }
/*     */     } 
/* 123 */     for (Attributes.OutputFlag flag : Attributes.OutputFlag.values()) {
/* 124 */       if (attr.getOutputFlag(flag) != current.getOutputFlag(flag)) {
/* 125 */         commands.add((attr.getOutputFlag(flag) ? flag.name() : ("-" + flag.name())).toLowerCase());
/*     */       }
/*     */     } 
/* 128 */     for (Attributes.ControlFlag flag : Attributes.ControlFlag.values()) {
/* 129 */       if (attr.getControlFlag(flag) != current.getControlFlag(flag)) {
/* 130 */         commands.add((attr.getControlFlag(flag) ? flag.name() : ("-" + flag.name())).toLowerCase());
/*     */       }
/*     */     } 
/* 133 */     for (Attributes.LocalFlag flag : Attributes.LocalFlag.values()) {
/* 134 */       if (attr.getLocalFlag(flag) != current.getLocalFlag(flag)) {
/* 135 */         commands.add((attr.getLocalFlag(flag) ? flag.name() : ("-" + flag.name())).toLowerCase());
/*     */       }
/*     */     } 
/* 138 */     String undef = System.getProperty("os.name").toLowerCase().startsWith("hp") ? "^-" : "undef";
/* 139 */     for (Attributes.ControlChar cchar : Attributes.ControlChar.values()) {
/* 140 */       int v = attr.getControlChar(cchar);
/* 141 */       if (v >= 0 && v != current.getControlChar(cchar)) {
/* 142 */         String str = "";
/* 143 */         commands.add(cchar.name().toLowerCase().substring(1));
/* 144 */         if (cchar == Attributes.ControlChar.VMIN || cchar == Attributes.ControlChar.VTIME) {
/* 145 */           commands.add(Integer.toString(v));
/*     */         }
/* 147 */         else if (v == 0) {
/* 148 */           commands.add(undef);
/*     */         } else {
/*     */           
/* 151 */           if (v >= 128) {
/* 152 */             v -= 128;
/* 153 */             str = str + "M-";
/*     */           } 
/* 155 */           if (v < 32 || v == 127) {
/* 156 */             v ^= 0x40;
/* 157 */             str = str + "^";
/*     */           } 
/* 159 */           str = str + (char)v;
/* 160 */           commands.add(str);
/*     */         } 
/*     */       } 
/*     */     } 
/* 164 */     return commands;
/*     */   }
/*     */ 
/*     */   
/*     */   public Size getSize() throws IOException {
/* 169 */     String cfg = doGetConfig();
/* 170 */     return doGetSize(cfg);
/*     */   }
/*     */   
/*     */   protected String doGetConfig() throws IOException {
/* 174 */     return this.system ? 
/* 175 */       ExecHelper.exec(true, new String[] { OSUtils.STTY_COMMAND, "-a"
/* 176 */         }) : ExecHelper.exec(false, new String[] { OSUtils.STTY_COMMAND, OSUtils.STTY_F_OPTION, getName(), "-a" });
/*     */   }
/*     */   
/*     */   static Attributes doGetAttr(String cfg) throws IOException {
/* 180 */     Attributes attributes = new Attributes();
/* 181 */     for (Attributes.InputFlag flag : Attributes.InputFlag.values()) {
/* 182 */       Boolean value = doGetFlag(cfg, (Enum<?>)flag);
/* 183 */       if (value != null) {
/* 184 */         attributes.setInputFlag(flag, value.booleanValue());
/*     */       }
/*     */     } 
/* 187 */     for (Attributes.OutputFlag flag : Attributes.OutputFlag.values()) {
/* 188 */       Boolean value = doGetFlag(cfg, (Enum<?>)flag);
/* 189 */       if (value != null) {
/* 190 */         attributes.setOutputFlag(flag, value.booleanValue());
/*     */       }
/*     */     } 
/* 193 */     for (Attributes.ControlFlag flag : Attributes.ControlFlag.values()) {
/* 194 */       Boolean value = doGetFlag(cfg, (Enum<?>)flag);
/* 195 */       if (value != null) {
/* 196 */         attributes.setControlFlag(flag, value.booleanValue());
/*     */       }
/*     */     } 
/* 199 */     for (Attributes.LocalFlag flag : Attributes.LocalFlag.values()) {
/* 200 */       Boolean value = doGetFlag(cfg, (Enum<?>)flag);
/* 201 */       if (value != null) {
/* 202 */         attributes.setLocalFlag(flag, value.booleanValue());
/*     */       }
/*     */     } 
/* 205 */     for (Attributes.ControlChar cchar : Attributes.ControlChar.values()) {
/* 206 */       String name = cchar.name().toLowerCase().substring(1);
/* 207 */       if ("reprint".endsWith(name)) {
/* 208 */         name = "(?:reprint|rprnt)";
/*     */       }
/* 210 */       Matcher matcher = Pattern.compile("[\\s;]" + name + "\\s*=\\s*(.+?)[\\s;]").matcher(cfg);
/* 211 */       if (matcher.find()) {
/* 212 */         attributes.setControlChar(cchar, parseControlChar(matcher.group(1).toUpperCase()));
/*     */       }
/*     */     } 
/* 215 */     return attributes;
/*     */   }
/*     */   
/*     */   private static Boolean doGetFlag(String cfg, Enum<?> flag) {
/* 219 */     Matcher matcher = Pattern.compile("(?:^|[\\s;])(\\-?" + flag.name().toLowerCase() + ")(?:[\\s;]|$)").matcher(cfg);
/* 220 */     return matcher.find() ? Boolean.valueOf(!matcher.group(1).startsWith("-")) : null;
/*     */   }
/*     */ 
/*     */   
/*     */   static int parseControlChar(String str) {
/* 225 */     if ("<UNDEF>".equals(str)) {
/* 226 */       return -1;
/*     */     }
/*     */     
/* 229 */     if ("DEL".equalsIgnoreCase(str)) {
/* 230 */       return 127;
/*     */     }
/*     */     
/* 233 */     if (str.charAt(0) == '0') {
/* 234 */       return Integer.parseInt(str, 8);
/*     */     }
/*     */     
/* 237 */     if (str.charAt(0) >= '1' && str.charAt(0) <= '9') {
/* 238 */       return Integer.parseInt(str, 10);
/*     */     }
/*     */     
/* 241 */     if (str.charAt(0) == '^') {
/* 242 */       if (str.charAt(1) == '?') {
/* 243 */         return 127;
/*     */       }
/* 245 */       return str.charAt(1) - 64;
/*     */     } 
/* 247 */     if (str.charAt(0) == 'M' && str.charAt(1) == '-') {
/* 248 */       if (str.charAt(2) == '^') {
/* 249 */         if (str.charAt(3) == '?') {
/* 250 */           return 255;
/*     */         }
/* 252 */         return str.charAt(3) - 64 + 128;
/*     */       } 
/*     */       
/* 255 */       return str.charAt(2) + 128;
/*     */     } 
/*     */     
/* 258 */     return str.charAt(0);
/*     */   }
/*     */ 
/*     */   
/*     */   static Size doGetSize(String cfg) throws IOException {
/* 263 */     return new Size(doGetInt("columns", cfg), doGetInt("rows", cfg));
/*     */   }
/*     */   
/*     */   static int doGetInt(String name, String cfg) throws IOException {
/* 267 */     String[] patterns = { "\\b([0-9]+)\\s+" + name + "\\b", "\\b" + name + "\\s+([0-9]+)\\b", "\\b" + name + "\\s*=\\s*([0-9]+)\\b" };
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 272 */     for (String pattern : patterns) {
/* 273 */       Matcher matcher = Pattern.compile(pattern).matcher(cfg);
/* 274 */       if (matcher.find()) {
/* 275 */         return Integer.parseInt(matcher.group(1));
/*     */       }
/*     */     } 
/* 278 */     throw new IOException("Unable to parse " + name);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSize(Size size) throws IOException {
/* 283 */     if (this.system) {
/* 284 */       ExecHelper.exec(true, new String[] { OSUtils.STTY_COMMAND, "columns", 
/*     */             
/* 286 */             Integer.toString(size.getColumns()), "rows", 
/* 287 */             Integer.toString(size.getRows()) });
/*     */     } else {
/* 289 */       ExecHelper.exec(false, new String[] { OSUtils.STTY_COMMAND, OSUtils.STTY_F_OPTION, 
/*     */             
/* 291 */             getName(), "columns", 
/* 292 */             Integer.toString(size.getColumns()), "rows", 
/* 293 */             Integer.toString(size.getRows()) });
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 299 */     return "ExecPty[" + getName() + (this.system ? ", system]" : "]");
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\jline\terminal\impl\ExecPty.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
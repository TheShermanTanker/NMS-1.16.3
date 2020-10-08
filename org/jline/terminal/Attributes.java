/*     */ package org.jline.terminal;
/*     */ 
/*     */ import java.util.EnumMap;
/*     */ import java.util.EnumSet;
/*     */ import java.util.function.Function;
/*     */ import java.util.stream.Collectors;
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
/*     */ public class Attributes
/*     */ {
/*     */   public enum ControlChar
/*     */   {
/*  22 */     VEOF,
/*  23 */     VEOL,
/*  24 */     VEOL2,
/*  25 */     VERASE,
/*  26 */     VWERASE,
/*  27 */     VKILL,
/*  28 */     VREPRINT,
/*  29 */     VINTR,
/*  30 */     VQUIT,
/*  31 */     VSUSP,
/*  32 */     VDSUSP,
/*  33 */     VSTART,
/*  34 */     VSTOP,
/*  35 */     VLNEXT,
/*  36 */     VDISCARD,
/*  37 */     VMIN,
/*  38 */     VTIME,
/*  39 */     VSTATUS;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public enum InputFlag
/*     */   {
/*  46 */     IGNBRK,
/*  47 */     BRKINT,
/*  48 */     IGNPAR,
/*  49 */     PARMRK,
/*  50 */     INPCK,
/*  51 */     ISTRIP,
/*  52 */     INLCR,
/*  53 */     IGNCR,
/*  54 */     ICRNL,
/*  55 */     IXON,
/*  56 */     IXOFF,
/*  57 */     IXANY,
/*  58 */     IMAXBEL,
/*  59 */     IUTF8;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public enum OutputFlag
/*     */   {
/*  66 */     OPOST,
/*  67 */     ONLCR,
/*  68 */     OXTABS,
/*  69 */     ONOEOT,
/*  70 */     OCRNL,
/*  71 */     ONOCR,
/*  72 */     ONLRET,
/*  73 */     OFILL,
/*  74 */     NLDLY,
/*  75 */     TABDLY,
/*  76 */     CRDLY,
/*  77 */     FFDLY,
/*  78 */     BSDLY,
/*  79 */     VTDLY,
/*  80 */     OFDEL;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public enum ControlFlag
/*     */   {
/*  87 */     CIGNORE,
/*  88 */     CS5,
/*  89 */     CS6,
/*  90 */     CS7,
/*  91 */     CS8,
/*  92 */     CSTOPB,
/*  93 */     CREAD,
/*  94 */     PARENB,
/*  95 */     PARODD,
/*  96 */     HUPCL,
/*  97 */     CLOCAL,
/*  98 */     CCTS_OFLOW,
/*  99 */     CRTS_IFLOW,
/* 100 */     CDTR_IFLOW,
/* 101 */     CDSR_OFLOW,
/* 102 */     CCAR_OFLOW;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public enum LocalFlag
/*     */   {
/* 113 */     ECHOKE,
/* 114 */     ECHOE,
/* 115 */     ECHOK,
/* 116 */     ECHO,
/* 117 */     ECHONL,
/* 118 */     ECHOPRT,
/* 119 */     ECHOCTL,
/* 120 */     ISIG,
/* 121 */     ICANON,
/* 122 */     ALTWERASE,
/* 123 */     IEXTEN,
/* 124 */     EXTPROC,
/* 125 */     TOSTOP,
/* 126 */     FLUSHO,
/* 127 */     NOKERNINFO,
/* 128 */     PENDIN,
/* 129 */     NOFLSH;
/*     */   }
/*     */   
/* 132 */   final EnumSet<InputFlag> iflag = EnumSet.noneOf(InputFlag.class);
/* 133 */   final EnumSet<OutputFlag> oflag = EnumSet.noneOf(OutputFlag.class);
/* 134 */   final EnumSet<ControlFlag> cflag = EnumSet.noneOf(ControlFlag.class);
/* 135 */   final EnumSet<LocalFlag> lflag = EnumSet.noneOf(LocalFlag.class);
/* 136 */   final EnumMap<ControlChar, Integer> cchars = new EnumMap<>(ControlChar.class);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Attributes(Attributes attr) {
/* 142 */     copy(attr);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumSet<InputFlag> getInputFlags() {
/* 150 */     return this.iflag;
/*     */   }
/*     */   
/*     */   public void setInputFlags(EnumSet<InputFlag> flags) {
/* 154 */     this.iflag.clear();
/* 155 */     this.iflag.addAll(flags);
/*     */   }
/*     */   
/*     */   public boolean getInputFlag(InputFlag flag) {
/* 159 */     return this.iflag.contains(flag);
/*     */   }
/*     */   
/*     */   public void setInputFlags(EnumSet<InputFlag> flags, boolean value) {
/* 163 */     if (value) {
/* 164 */       this.iflag.addAll(flags);
/*     */     } else {
/* 166 */       this.iflag.removeAll(flags);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setInputFlag(InputFlag flag, boolean value) {
/* 171 */     if (value) {
/* 172 */       this.iflag.add(flag);
/*     */     } else {
/* 174 */       this.iflag.remove(flag);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumSet<OutputFlag> getOutputFlags() {
/* 183 */     return this.oflag;
/*     */   }
/*     */   
/*     */   public void setOutputFlags(EnumSet<OutputFlag> flags) {
/* 187 */     this.oflag.clear();
/* 188 */     this.oflag.addAll(flags);
/*     */   }
/*     */   
/*     */   public boolean getOutputFlag(OutputFlag flag) {
/* 192 */     return this.oflag.contains(flag);
/*     */   }
/*     */   
/*     */   public void setOutputFlags(EnumSet<OutputFlag> flags, boolean value) {
/* 196 */     if (value) {
/* 197 */       this.oflag.addAll(flags);
/*     */     } else {
/* 199 */       this.oflag.removeAll(flags);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setOutputFlag(OutputFlag flag, boolean value) {
/* 204 */     if (value) {
/* 205 */       this.oflag.add(flag);
/*     */     } else {
/* 207 */       this.oflag.remove(flag);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumSet<ControlFlag> getControlFlags() {
/* 216 */     return this.cflag;
/*     */   }
/*     */   
/*     */   public void setControlFlags(EnumSet<ControlFlag> flags) {
/* 220 */     this.cflag.clear();
/* 221 */     this.cflag.addAll(flags);
/*     */   }
/*     */   
/*     */   public boolean getControlFlag(ControlFlag flag) {
/* 225 */     return this.cflag.contains(flag);
/*     */   }
/*     */   
/*     */   public void setControlFlags(EnumSet<ControlFlag> flags, boolean value) {
/* 229 */     if (value) {
/* 230 */       this.cflag.addAll(flags);
/*     */     } else {
/* 232 */       this.cflag.removeAll(flags);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setControlFlag(ControlFlag flag, boolean value) {
/* 237 */     if (value) {
/* 238 */       this.cflag.add(flag);
/*     */     } else {
/* 240 */       this.cflag.remove(flag);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumSet<LocalFlag> getLocalFlags() {
/* 249 */     return this.lflag;
/*     */   }
/*     */   
/*     */   public void setLocalFlags(EnumSet<LocalFlag> flags) {
/* 253 */     this.lflag.clear();
/* 254 */     this.lflag.addAll(flags);
/*     */   }
/*     */   
/*     */   public boolean getLocalFlag(LocalFlag flag) {
/* 258 */     return this.lflag.contains(flag);
/*     */   }
/*     */   
/*     */   public void setLocalFlags(EnumSet<LocalFlag> flags, boolean value) {
/* 262 */     if (value) {
/* 263 */       this.lflag.addAll(flags);
/*     */     } else {
/* 265 */       this.lflag.removeAll(flags);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setLocalFlag(LocalFlag flag, boolean value) {
/* 270 */     if (value) {
/* 271 */       this.lflag.add(flag);
/*     */     } else {
/* 273 */       this.lflag.remove(flag);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumMap<ControlChar, Integer> getControlChars() {
/* 282 */     return this.cchars;
/*     */   }
/*     */   
/*     */   public void setControlChars(EnumMap<ControlChar, Integer> chars) {
/* 286 */     this.cchars.clear();
/* 287 */     this.cchars.putAll(chars);
/*     */   }
/*     */   
/*     */   public int getControlChar(ControlChar c) {
/* 291 */     Integer v = this.cchars.get(c);
/* 292 */     return (v != null) ? v.intValue() : -1;
/*     */   }
/*     */   
/*     */   public void setControlChar(ControlChar c, int value) {
/* 296 */     this.cchars.put(c, Integer.valueOf(value));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void copy(Attributes attributes) {
/* 304 */     setControlFlags(attributes.getControlFlags());
/* 305 */     setInputFlags(attributes.getInputFlags());
/* 306 */     setLocalFlags(attributes.getLocalFlags());
/* 307 */     setOutputFlags(attributes.getOutputFlags());
/* 308 */     setControlChars(attributes.getControlChars());
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 313 */     return "Attributes[lflags: " + 
/* 314 */       append(this.lflag) + ", iflags: " + 
/* 315 */       append(this.iflag) + ", oflags: " + 
/* 316 */       append(this.oflag) + ", cflags: " + 
/* 317 */       append(this.cflag) + ", cchars: " + 
/* 318 */       append(EnumSet.allOf((Class)ControlChar.class), this::display) + "]";
/*     */   }
/*     */ 
/*     */   
/*     */   private String display(ControlChar c) {
/*     */     String value;
/* 324 */     int ch = getControlChar(c);
/* 325 */     if (c == ControlChar.VMIN || c == ControlChar.VTIME) {
/* 326 */       value = Integer.toString(ch);
/* 327 */     } else if (ch < 0) {
/* 328 */       value = "<undef>";
/* 329 */     } else if (ch < 32) {
/* 330 */       value = "^" + (char)(ch + 65 - 1);
/* 331 */     } else if (ch == 127) {
/* 332 */       value = "^?";
/* 333 */     } else if (ch >= 128) {
/* 334 */       value = String.format("\\u%04x", new Object[] { Integer.valueOf(ch) });
/*     */     } else {
/* 336 */       value = String.valueOf((char)ch);
/*     */     } 
/* 338 */     return c.name().toLowerCase().substring(1) + "=" + value;
/*     */   }
/*     */   
/*     */   private <T extends Enum<T>> String append(EnumSet<T> set) {
/* 342 */     return append(set, e -> e.name().toLowerCase());
/*     */   }
/*     */   
/*     */   private <T extends Enum<T>> String append(EnumSet<T> set, Function<T, String> toString) {
/* 346 */     return set.stream().<CharSequence>map((Function)toString).collect(Collectors.joining(" "));
/*     */   }
/*     */   
/*     */   public Attributes() {}
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\jline\terminal\Attributes.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
/*     */ package org.fusesource.jansi;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.Locale;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AnsiRenderer
/*     */ {
/*     */   public static final String BEGIN_TOKEN = "@|";
/*     */   public static final String END_TOKEN = "|@";
/*     */   public static final String CODE_TEXT_SEPARATOR = " ";
/*     */   public static final String CODE_LIST_SEPARATOR = ",";
/*     */   private static final int BEGIN_TOKEN_LEN = 2;
/*     */   private static final int END_TOKEN_LEN = 2;
/*     */   
/*     */   public static String render(String input) throws IllegalArgumentException {
/*     */     try {
/*  63 */       return render(input, new StringBuilder()).toString();
/*  64 */     } catch (IOException e) {
/*     */       
/*  66 */       throw new IllegalArgumentException(e);
/*     */     } 
/*     */   }
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
/*     */   public static Appendable render(String input, Appendable target) throws IOException {
/*  83 */     int i = 0;
/*     */ 
/*     */     
/*     */     while (true) {
/*  87 */       int j = input.indexOf("@|", i);
/*  88 */       if (j == -1) {
/*  89 */         if (i == 0) {
/*  90 */           target.append(input);
/*  91 */           return target;
/*     */         } 
/*  93 */         target.append(input.substring(i, input.length()));
/*  94 */         return target;
/*     */       } 
/*  96 */       target.append(input.substring(i, j));
/*  97 */       int k = input.indexOf("|@", j);
/*     */       
/*  99 */       if (k == -1) {
/* 100 */         target.append(input);
/* 101 */         return target;
/*     */       } 
/* 103 */       j += 2;
/* 104 */       String spec = input.substring(j, k);
/*     */       
/* 106 */       String[] items = spec.split(" ", 2);
/* 107 */       if (items.length == 1) {
/* 108 */         target.append(input);
/* 109 */         return target;
/*     */       } 
/* 111 */       String replacement = render(items[1], items[0].split(","));
/*     */       
/* 113 */       target.append(replacement);
/*     */       
/* 115 */       i = k + 2;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static String render(String text, String... codes) {
/* 120 */     return render(Ansi.ansi(), codes)
/* 121 */       .a(text).reset().toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String renderCodes(String... codes) {
/* 130 */     return render(Ansi.ansi(), codes).toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String renderCodes(String codes) {
/* 139 */     return renderCodes(codes.split("\\s"));
/*     */   }
/*     */   
/*     */   private static Ansi render(Ansi ansi, String... names) {
/* 143 */     for (String name : names) {
/* 144 */       render(ansi, name);
/*     */     }
/* 146 */     return ansi;
/*     */   }
/*     */   
/*     */   private static Ansi render(Ansi ansi, String name) {
/* 150 */     Code code = Code.valueOf(name.toUpperCase(Locale.ENGLISH));
/* 151 */     if (code.isColor()) {
/* 152 */       if (code.isBackground()) {
/* 153 */         ansi.bg(code.getColor());
/*     */       } else {
/* 155 */         ansi.fg(code.getColor());
/*     */       } 
/* 157 */     } else if (code.isAttribute()) {
/* 158 */       ansi.a(code.getAttribute());
/*     */     } 
/* 160 */     return ansi;
/*     */   }
/*     */   
/*     */   public static boolean test(String text) {
/* 164 */     return (text != null && text.contains("@|"));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public enum Code
/*     */   {
/* 173 */     BLACK((String)Ansi.Color.BLACK),
/* 174 */     RED((String)Ansi.Color.RED),
/* 175 */     GREEN((String)Ansi.Color.GREEN),
/* 176 */     YELLOW((String)Ansi.Color.YELLOW),
/* 177 */     BLUE((String)Ansi.Color.BLUE),
/* 178 */     MAGENTA((String)Ansi.Color.MAGENTA),
/* 179 */     CYAN((String)Ansi.Color.CYAN),
/* 180 */     WHITE((String)Ansi.Color.WHITE),
/*     */ 
/*     */     
/* 183 */     FG_BLACK((String)Ansi.Color.BLACK, false),
/* 184 */     FG_RED((String)Ansi.Color.RED, false),
/* 185 */     FG_GREEN((String)Ansi.Color.GREEN, false),
/* 186 */     FG_YELLOW((String)Ansi.Color.YELLOW, false),
/* 187 */     FG_BLUE((String)Ansi.Color.BLUE, false),
/* 188 */     FG_MAGENTA((String)Ansi.Color.MAGENTA, false),
/* 189 */     FG_CYAN((String)Ansi.Color.CYAN, false),
/* 190 */     FG_WHITE((String)Ansi.Color.WHITE, false),
/*     */ 
/*     */     
/* 193 */     BG_BLACK((String)Ansi.Color.BLACK, true),
/* 194 */     BG_RED((String)Ansi.Color.RED, true),
/* 195 */     BG_GREEN((String)Ansi.Color.GREEN, true),
/* 196 */     BG_YELLOW((String)Ansi.Color.YELLOW, true),
/* 197 */     BG_BLUE((String)Ansi.Color.BLUE, true),
/* 198 */     BG_MAGENTA((String)Ansi.Color.MAGENTA, true),
/* 199 */     BG_CYAN((String)Ansi.Color.CYAN, true),
/* 200 */     BG_WHITE((String)Ansi.Color.WHITE, true),
/*     */ 
/*     */     
/* 203 */     RESET((String)Ansi.Attribute.RESET),
/* 204 */     INTENSITY_BOLD((String)Ansi.Attribute.INTENSITY_BOLD),
/* 205 */     INTENSITY_FAINT((String)Ansi.Attribute.INTENSITY_FAINT),
/* 206 */     ITALIC((String)Ansi.Attribute.ITALIC),
/* 207 */     UNDERLINE((String)Ansi.Attribute.UNDERLINE),
/* 208 */     BLINK_SLOW((String)Ansi.Attribute.BLINK_SLOW),
/* 209 */     BLINK_FAST((String)Ansi.Attribute.BLINK_FAST),
/* 210 */     BLINK_OFF((String)Ansi.Attribute.BLINK_OFF),
/* 211 */     NEGATIVE_ON((String)Ansi.Attribute.NEGATIVE_ON),
/* 212 */     NEGATIVE_OFF((String)Ansi.Attribute.NEGATIVE_OFF),
/* 213 */     CONCEAL_ON((String)Ansi.Attribute.CONCEAL_ON),
/* 214 */     CONCEAL_OFF((String)Ansi.Attribute.CONCEAL_OFF),
/* 215 */     UNDERLINE_DOUBLE((String)Ansi.Attribute.UNDERLINE_DOUBLE),
/* 216 */     UNDERLINE_OFF((String)Ansi.Attribute.UNDERLINE_OFF),
/*     */ 
/*     */     
/* 219 */     BOLD((String)Ansi.Attribute.INTENSITY_BOLD),
/* 220 */     FAINT((String)Ansi.Attribute.INTENSITY_FAINT);
/*     */     
/*     */     private final Enum<?> n;
/*     */     
/*     */     private final boolean background;
/*     */     
/*     */     Code(Enum<?> n, boolean background) {
/* 227 */       this.n = n;
/* 228 */       this.background = background;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean isColor() {
/* 236 */       return this.n instanceof Ansi.Color;
/*     */     }
/*     */     
/*     */     public Ansi.Color getColor() {
/* 240 */       return (Ansi.Color)this.n;
/*     */     }
/*     */     
/*     */     public boolean isAttribute() {
/* 244 */       return this.n instanceof Ansi.Attribute;
/*     */     }
/*     */     
/*     */     public Ansi.Attribute getAttribute() {
/* 248 */       return (Ansi.Attribute)this.n;
/*     */     }
/*     */     
/*     */     public boolean isBackground() {
/* 252 */       return this.background;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\fusesource\jansi\AnsiRenderer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
/*     */ package net.minecraft.server.v1_16_R2;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import java.util.regex.Pattern;
/*     */ import java.util.stream.Collectors;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ public enum EnumChatFormat {
/*     */   private static final Map<String, EnumChatFormat> w;
/*     */   private static final Pattern x;
/*     */   private final String y;
/*     */   public final char character;
/*  15 */   BLACK("BLACK", '0', 0, Integer.valueOf(0)),
/*  16 */   DARK_BLUE("DARK_BLUE", '1', 1, Integer.valueOf(170)),
/*  17 */   DARK_GREEN("DARK_GREEN", '2', 2, Integer.valueOf(43520)),
/*  18 */   DARK_AQUA("DARK_AQUA", '3', 3, Integer.valueOf(43690)),
/*  19 */   DARK_RED("DARK_RED", '4', 4, Integer.valueOf(11141120)),
/*  20 */   DARK_PURPLE("DARK_PURPLE", '5', 5, Integer.valueOf(11141290)),
/*  21 */   GOLD("GOLD", '6', 6, Integer.valueOf(16755200)),
/*  22 */   GRAY("GRAY", '7', 7, Integer.valueOf(11184810)),
/*  23 */   DARK_GRAY("DARK_GRAY", '8', 8, Integer.valueOf(5592405)),
/*  24 */   BLUE("BLUE", '9', 9, Integer.valueOf(5592575)),
/*  25 */   GREEN("GREEN", 'a', 10, Integer.valueOf(5635925)),
/*  26 */   AQUA("AQUA", 'b', 11, Integer.valueOf(5636095)),
/*  27 */   RED("RED", 'c', 12, Integer.valueOf(16733525)),
/*  28 */   LIGHT_PURPLE("LIGHT_PURPLE", 'd', 13, Integer.valueOf(16733695)),
/*  29 */   YELLOW("YELLOW", 'e', 14, Integer.valueOf(16777045)),
/*  30 */   WHITE("WHITE", 'f', 15, Integer.valueOf(16777215)),
/*  31 */   OBFUSCATED("OBFUSCATED", 'k', true),
/*  32 */   BOLD("BOLD", 'l', true),
/*  33 */   STRIKETHROUGH("STRIKETHROUGH", 'm', true),
/*  34 */   UNDERLINE("UNDERLINE", 'n', true),
/*  35 */   ITALIC("ITALIC", 'o', true),
/*  36 */   RESET("RESET", 'r', -1, null); private final boolean A; private final String B;
/*     */   
/*     */   static {
/*  39 */     w = (Map<String, EnumChatFormat>)Arrays.<EnumChatFormat>stream(values()).collect(Collectors.toMap(var0 -> c(var0.y), var0 -> var0));
/*  40 */     x = Pattern.compile("(?i)§[0-9A-FK-OR]");
/*     */   } private final int C; @Nullable
/*     */   private final Integer D; private static String c(String var0) {
/*  43 */     return var0.toLowerCase(Locale.ROOT).replaceAll("[^a-z]", "");
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   EnumChatFormat(String var2, char var3, boolean var4, int var5, @Nullable Integer var6) {
/*  63 */     this.y = var2;
/*  64 */     this.character = var3;
/*  65 */     this.A = var4;
/*  66 */     this.C = var5;
/*  67 */     this.D = var6;
/*     */     
/*  69 */     this.B = "§" + var3;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int b() {
/*  77 */     return this.C;
/*     */   }
/*     */   
/*     */   public boolean isFormat() {
/*  81 */     return this.A;
/*     */   }
/*     */   
/*     */   public boolean d() {
/*  85 */     return (!this.A && this != RESET);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public Integer e() {
/*  90 */     return this.D;
/*     */   }
/*     */   
/*     */   public String f() {
/*  94 */     return name().toLowerCase(Locale.ROOT);
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/*  99 */     return this.B;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public static String a(@Nullable String var0) {
/* 104 */     return (var0 == null) ? null : x.matcher(var0).replaceAll("");
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public static EnumChatFormat b(@Nullable String var0) {
/* 109 */     if (var0 == null) {
/* 110 */       return null;
/*     */     }
/* 112 */     return w.get(c(var0));
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public static EnumChatFormat a(int var0) {
/* 117 */     if (var0 < 0) {
/* 118 */       return RESET;
/*     */     }
/* 120 */     for (EnumChatFormat var4 : values()) {
/* 121 */       if (var4.b() == var0) {
/* 122 */         return var4;
/*     */       }
/*     */     } 
/* 125 */     return null;
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
/*     */   public static Collection<String> a(boolean var0, boolean var1) {
/* 140 */     List<String> var2 = Lists.newArrayList();
/*     */     
/* 142 */     for (EnumChatFormat var6 : values()) {
/* 143 */       if (!var6.d() || var0)
/*     */       {
/*     */         
/* 146 */         if (!var6.isFormat() || var1)
/*     */         {
/*     */           
/* 149 */           var2.add(var6.f()); } 
/*     */       }
/*     */     } 
/* 152 */     return var2;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EnumChatFormat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
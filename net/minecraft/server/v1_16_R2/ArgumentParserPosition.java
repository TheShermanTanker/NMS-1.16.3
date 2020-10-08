/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.mojang.brigadier.StringReader;
/*     */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*     */ import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ArgumentParserPosition
/*     */ {
/*  11 */   public static final SimpleCommandExceptionType a = new SimpleCommandExceptionType(new ChatMessage("argument.pos.missing.double"));
/*  12 */   public static final SimpleCommandExceptionType b = new SimpleCommandExceptionType(new ChatMessage("argument.pos.missing.int"));
/*     */   
/*     */   private final boolean c;
/*     */   private final double d;
/*     */   
/*     */   public ArgumentParserPosition(boolean var0, double var1) {
/*  18 */     this.c = var0;
/*  19 */     this.d = var1;
/*     */   }
/*     */   
/*     */   public double a(double var0) {
/*  23 */     if (this.c) {
/*  24 */       return this.d + var0;
/*     */     }
/*  26 */     return this.d;
/*     */   }
/*     */ 
/*     */   
/*     */   public static ArgumentParserPosition a(StringReader var0, boolean var1) throws CommandSyntaxException {
/*  31 */     if (var0.canRead() && var0.peek() == '^') {
/*  32 */       throw ArgumentVec3.b.createWithContext(var0);
/*     */     }
/*     */     
/*  35 */     if (!var0.canRead()) {
/*  36 */       throw a.createWithContext(var0);
/*     */     }
/*     */     
/*  39 */     boolean var2 = b(var0);
/*  40 */     int var3 = var0.getCursor();
/*  41 */     double var4 = (var0.canRead() && var0.peek() != ' ') ? var0.readDouble() : 0.0D;
/*  42 */     String var6 = var0.getString().substring(var3, var0.getCursor());
/*     */     
/*  44 */     if (var2 && var6.isEmpty()) {
/*  45 */       return new ArgumentParserPosition(true, 0.0D);
/*     */     }
/*     */     
/*  48 */     if (!var6.contains(".") && !var2 && var1) {
/*  49 */       var4 += 0.5D;
/*     */     }
/*     */     
/*  52 */     return new ArgumentParserPosition(var2, var4);
/*     */   }
/*     */   public static ArgumentParserPosition a(StringReader var0) throws CommandSyntaxException {
/*     */     double var2;
/*  56 */     if (var0.canRead() && var0.peek() == '^') {
/*  57 */       throw ArgumentVec3.b.createWithContext(var0);
/*     */     }
/*     */     
/*  60 */     if (!var0.canRead()) {
/*  61 */       throw b.createWithContext(var0);
/*     */     }
/*     */     
/*  64 */     boolean var1 = b(var0);
/*     */     
/*  66 */     if (var0.canRead() && var0.peek() != ' ') {
/*  67 */       var2 = var1 ? var0.readDouble() : var0.readInt();
/*     */     } else {
/*  69 */       var2 = 0.0D;
/*     */     } 
/*  71 */     return new ArgumentParserPosition(var1, var2);
/*     */   }
/*     */   
/*     */   public static boolean b(StringReader var0) {
/*     */     boolean var1;
/*  76 */     if (var0.peek() == '~') {
/*  77 */       var1 = true;
/*  78 */       var0.skip();
/*     */     } else {
/*  80 */       var1 = false;
/*     */     } 
/*  82 */     return var1;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object var0) {
/*  87 */     if (this == var0) {
/*  88 */       return true;
/*     */     }
/*  90 */     if (!(var0 instanceof ArgumentParserPosition)) {
/*  91 */       return false;
/*     */     }
/*     */     
/*  94 */     ArgumentParserPosition var1 = (ArgumentParserPosition)var0;
/*     */     
/*  96 */     if (this.c != var1.c) {
/*  97 */       return false;
/*     */     }
/*  99 */     return (Double.compare(var1.d, this.d) == 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 106 */     int var0 = this.c ? 1 : 0;
/* 107 */     long var1 = Double.doubleToLongBits(this.d);
/* 108 */     var0 = 31 * var0 + (int)(var1 ^ var1 >>> 32L);
/* 109 */     return var0;
/*     */   }
/*     */   
/*     */   public boolean a() {
/* 113 */     return this.c;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ArgumentParserPosition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
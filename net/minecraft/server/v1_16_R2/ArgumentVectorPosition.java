/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.mojang.brigadier.StringReader;
/*     */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*     */ import java.util.Objects;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ArgumentVectorPosition
/*     */   implements IVectorPosition
/*     */ {
/*     */   private final double a;
/*     */   private final double b;
/*     */   private final double c;
/*     */   
/*     */   public ArgumentVectorPosition(double var0, double var2, double var4) {
/*  20 */     this.a = var0;
/*  21 */     this.b = var2;
/*  22 */     this.c = var4;
/*     */   }
/*     */ 
/*     */   
/*     */   public Vec3D a(CommandListenerWrapper var0) {
/*  27 */     Vec2F var1 = var0.i();
/*  28 */     Vec3D var2 = var0.k().a(var0);
/*  29 */     float var3 = MathHelper.cos((var1.j + 90.0F) * 0.017453292F);
/*  30 */     float var4 = MathHelper.sin((var1.j + 90.0F) * 0.017453292F);
/*  31 */     float var5 = MathHelper.cos(-var1.i * 0.017453292F);
/*  32 */     float var6 = MathHelper.sin(-var1.i * 0.017453292F);
/*  33 */     float var7 = MathHelper.cos((-var1.i + 90.0F) * 0.017453292F);
/*  34 */     float var8 = MathHelper.sin((-var1.i + 90.0F) * 0.017453292F);
/*  35 */     Vec3D var9 = new Vec3D((var3 * var5), var6, (var4 * var5));
/*  36 */     Vec3D var10 = new Vec3D((var3 * var7), var8, (var4 * var7));
/*  37 */     Vec3D var11 = var9.c(var10).a(-1.0D);
/*  38 */     double var12 = var9.x * this.c + var10.x * this.b + var11.x * this.a;
/*  39 */     double var14 = var9.y * this.c + var10.y * this.b + var11.y * this.a;
/*  40 */     double var16 = var9.z * this.c + var10.z * this.b + var11.z * this.a;
/*  41 */     return new Vec3D(var2.x + var12, var2.y + var14, var2.z + var16);
/*     */   }
/*     */ 
/*     */   
/*     */   public Vec2F b(CommandListenerWrapper var0) {
/*  46 */     return Vec2F.a;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a() {
/*  51 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean b() {
/*  56 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean c() {
/*  61 */     return true;
/*     */   }
/*     */   
/*     */   public static ArgumentVectorPosition a(StringReader var0) throws CommandSyntaxException {
/*  65 */     int var1 = var0.getCursor();
/*  66 */     double var2 = a(var0, var1);
/*  67 */     if (!var0.canRead() || var0.peek() != ' ') {
/*  68 */       var0.setCursor(var1);
/*  69 */       throw ArgumentVec3.a.createWithContext(var0);
/*     */     } 
/*  71 */     var0.skip();
/*  72 */     double var4 = a(var0, var1);
/*  73 */     if (!var0.canRead() || var0.peek() != ' ') {
/*  74 */       var0.setCursor(var1);
/*  75 */       throw ArgumentVec3.a.createWithContext(var0);
/*     */     } 
/*  77 */     var0.skip();
/*  78 */     double var6 = a(var0, var1);
/*  79 */     return new ArgumentVectorPosition(var2, var4, var6);
/*     */   }
/*     */   
/*     */   private static double a(StringReader var0, int var1) throws CommandSyntaxException {
/*  83 */     if (!var0.canRead()) {
/*  84 */       throw ArgumentParserPosition.a.createWithContext(var0);
/*     */     }
/*     */     
/*  87 */     if (var0.peek() != '^') {
/*  88 */       var0.setCursor(var1);
/*  89 */       throw ArgumentVec3.b.createWithContext(var0);
/*     */     } 
/*  91 */     var0.skip();
/*     */     
/*  93 */     return (var0.canRead() && var0.peek() != ' ') ? var0.readDouble() : 0.0D;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object var0) {
/*  98 */     if (this == var0) {
/*  99 */       return true;
/*     */     }
/* 101 */     if (!(var0 instanceof ArgumentVectorPosition)) {
/* 102 */       return false;
/*     */     }
/*     */     
/* 105 */     ArgumentVectorPosition var1 = (ArgumentVectorPosition)var0;
/*     */     
/* 107 */     return (this.a == var1.a && this.b == var1.b && this.c == var1.c);
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 112 */     return Objects.hash(new Object[] { Double.valueOf(this.a), Double.valueOf(this.b), Double.valueOf(this.c) });
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ArgumentVectorPosition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
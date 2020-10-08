/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.mojang.brigadier.StringReader;
/*     */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*     */ 
/*     */ 
/*     */ public class VectorPosition
/*     */   implements IVectorPosition
/*     */ {
/*     */   private final ArgumentParserPosition a;
/*     */   private final ArgumentParserPosition b;
/*     */   private final ArgumentParserPosition c;
/*     */   
/*     */   public VectorPosition(ArgumentParserPosition var0, ArgumentParserPosition var1, ArgumentParserPosition var2) {
/*  15 */     this.a = var0;
/*  16 */     this.b = var1;
/*  17 */     this.c = var2;
/*     */   }
/*     */ 
/*     */   
/*     */   public Vec3D a(CommandListenerWrapper var0) {
/*  22 */     Vec3D var1 = var0.getPosition();
/*  23 */     return new Vec3D(this.a.a(var1.x), this.b.a(var1.y), this.c.a(var1.z));
/*     */   }
/*     */ 
/*     */   
/*     */   public Vec2F b(CommandListenerWrapper var0) {
/*  28 */     Vec2F var1 = var0.i();
/*  29 */     return new Vec2F((float)this.a.a(var1.i), (float)this.b.a(var1.j));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a() {
/*  34 */     return this.a.a();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean b() {
/*  39 */     return this.b.a();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean c() {
/*  44 */     return this.c.a();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object var0) {
/*  49 */     if (this == var0) {
/*  50 */       return true;
/*     */     }
/*  52 */     if (!(var0 instanceof VectorPosition)) {
/*  53 */       return false;
/*     */     }
/*     */     
/*  56 */     VectorPosition var1 = (VectorPosition)var0;
/*     */     
/*  58 */     if (!this.a.equals(var1.a)) {
/*  59 */       return false;
/*     */     }
/*  61 */     if (!this.b.equals(var1.b)) {
/*  62 */       return false;
/*     */     }
/*  64 */     return this.c.equals(var1.c);
/*     */   }
/*     */   
/*     */   public static VectorPosition a(StringReader var0) throws CommandSyntaxException {
/*  68 */     int var1 = var0.getCursor();
/*  69 */     ArgumentParserPosition var2 = ArgumentParserPosition.a(var0);
/*  70 */     if (!var0.canRead() || var0.peek() != ' ') {
/*  71 */       var0.setCursor(var1);
/*  72 */       throw ArgumentVec3.a.createWithContext(var0);
/*     */     } 
/*  74 */     var0.skip();
/*  75 */     ArgumentParserPosition var3 = ArgumentParserPosition.a(var0);
/*  76 */     if (!var0.canRead() || var0.peek() != ' ') {
/*  77 */       var0.setCursor(var1);
/*  78 */       throw ArgumentVec3.a.createWithContext(var0);
/*     */     } 
/*  80 */     var0.skip();
/*  81 */     ArgumentParserPosition var4 = ArgumentParserPosition.a(var0);
/*  82 */     return new VectorPosition(var2, var3, var4);
/*     */   }
/*     */   
/*     */   public static VectorPosition a(StringReader var0, boolean var1) throws CommandSyntaxException {
/*  86 */     int var2 = var0.getCursor();
/*  87 */     ArgumentParserPosition var3 = ArgumentParserPosition.a(var0, var1);
/*  88 */     if (!var0.canRead() || var0.peek() != ' ') {
/*  89 */       var0.setCursor(var2);
/*  90 */       throw ArgumentVec3.a.createWithContext(var0);
/*     */     } 
/*  92 */     var0.skip();
/*  93 */     ArgumentParserPosition var4 = ArgumentParserPosition.a(var0, false);
/*  94 */     if (!var0.canRead() || var0.peek() != ' ') {
/*  95 */       var0.setCursor(var2);
/*  96 */       throw ArgumentVec3.a.createWithContext(var0);
/*     */     } 
/*  98 */     var0.skip();
/*  99 */     ArgumentParserPosition var5 = ArgumentParserPosition.a(var0, var1);
/* 100 */     return new VectorPosition(var3, var4, var5);
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
/*     */   public static VectorPosition d() {
/* 112 */     return new VectorPosition(new ArgumentParserPosition(true, 0.0D), new ArgumentParserPosition(true, 0.0D), new ArgumentParserPosition(true, 0.0D));
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 117 */     int var0 = this.a.hashCode();
/* 118 */     var0 = 31 * var0 + this.b.hashCode();
/* 119 */     var0 = 31 * var0 + this.c.hashCode();
/* 120 */     return var0;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\VectorPosition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
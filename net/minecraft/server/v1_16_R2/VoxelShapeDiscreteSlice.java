/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ public final class VoxelShapeDiscreteSlice
/*    */   extends VoxelShapeDiscrete
/*    */ {
/*    */   private final VoxelShapeDiscrete d;
/*    */   private final int e;
/*    */   private final int f;
/*    */   private final int g;
/*    */   private final int h;
/*    */   private final int i;
/*    */   private final int j;
/*    */   
/*    */   protected VoxelShapeDiscreteSlice(VoxelShapeDiscrete var0, int var1, int var2, int var3, int var4, int var5, int var6) {
/* 15 */     super(var4 - var1, var5 - var2, var6 - var3);
/* 16 */     this.d = var0;
/* 17 */     this.e = var1;
/* 18 */     this.f = var2;
/* 19 */     this.g = var3;
/* 20 */     this.h = var4;
/* 21 */     this.i = var5;
/* 22 */     this.j = var6;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean b(int var0, int var1, int var2) {
/* 27 */     return this.d.b(this.e + var0, this.f + var1, this.g + var2);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(int var0, int var1, int var2, boolean var3, boolean var4) {
/* 32 */     this.d.a(this.e + var0, this.f + var1, this.g + var2, var3, var4);
/*    */   }
/*    */ 
/*    */   
/*    */   public int a(EnumDirection.EnumAxis var0) {
/* 37 */     return Math.max(0, this.d.a(var0) - var0.a(this.e, this.f, this.g));
/*    */   }
/*    */ 
/*    */   
/*    */   public int b(EnumDirection.EnumAxis var0) {
/* 42 */     return Math.min(var0.a(this.h, this.i, this.j), this.d.b(var0) - var0.a(this.e, this.f, this.g));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\VoxelShapeDiscreteSlice.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
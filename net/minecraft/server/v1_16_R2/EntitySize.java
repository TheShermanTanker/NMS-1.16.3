/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EntitySize
/*    */ {
/*    */   public final float width;
/*    */   public final float height;
/*    */   public final boolean c;
/*    */   
/*    */   public EntitySize(float var0, float var1, boolean var2) {
/* 12 */     this.width = var0;
/* 13 */     this.height = var1;
/* 14 */     this.c = var2;
/*    */   }
/*    */   
/*    */   public AxisAlignedBB a(Vec3D var0) {
/* 18 */     return a(var0.x, var0.y, var0.z);
/*    */   }
/*    */   
/*    */   public AxisAlignedBB a(double var0, double var2, double var4) {
/* 22 */     float var6 = this.width / 2.0F;
/* 23 */     float var7 = this.height;
/* 24 */     return new AxisAlignedBB(var0 - var6, var2, var4 - var6, var0 + var6, var2 + var7, var4 + var6);
/*    */   }
/*    */   
/*    */   public EntitySize a(float var0) {
/* 28 */     return a(var0, var0);
/*    */   }
/*    */   
/*    */   public EntitySize a(float var0, float var1) {
/* 32 */     if (this.c || (var0 == 1.0F && var1 == 1.0F)) {
/* 33 */       return this;
/*    */     }
/* 35 */     return b(this.width * var0, this.height * var1);
/*    */   }
/*    */ 
/*    */   
/*    */   public static EntitySize b(float var0, float var1) {
/* 40 */     return new EntitySize(var0, var1, false);
/*    */   }
/*    */   
/*    */   public static EntitySize c(float var0, float var1) {
/* 44 */     return new EntitySize(var0, var1, true);
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 49 */     return "EntityDimensions w=" + this.width + ", h=" + this.height + ", fixed=" + this.c;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntitySize.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
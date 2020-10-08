/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ public class MovingObjectPositionBlock
/*    */   extends MovingObjectPosition
/*    */ {
/*    */   private final EnumDirection b;
/*    */   private final BlockPosition c;
/*    */   private final boolean d;
/*    */   private final boolean e;
/*    */   
/*    */   public static MovingObjectPositionBlock a(Vec3D var0, EnumDirection var1, BlockPosition var2) {
/* 13 */     return new MovingObjectPositionBlock(true, var0, var1, var2, false);
/*    */   }
/*    */   
/*    */   public MovingObjectPositionBlock(Vec3D var0, EnumDirection var1, BlockPosition var2, boolean var3) {
/* 17 */     this(false, var0, var1, var2, var3);
/*    */   }
/*    */   
/*    */   private MovingObjectPositionBlock(boolean var0, Vec3D var1, EnumDirection var2, BlockPosition var3, boolean var4) {
/* 21 */     super(var1);
/*    */     
/* 23 */     this.d = var0;
/* 24 */     this.b = var2;
/* 25 */     this.c = var3;
/* 26 */     this.e = var4;
/*    */   }
/*    */   
/*    */   public MovingObjectPositionBlock a(EnumDirection var0) {
/* 30 */     return new MovingObjectPositionBlock(this.d, this.pos, var0, this.c, this.e);
/*    */   }
/*    */   
/*    */   public MovingObjectPositionBlock a(BlockPosition var0) {
/* 34 */     return new MovingObjectPositionBlock(this.d, this.pos, this.b, var0, this.e);
/*    */   }
/*    */   
/*    */   public BlockPosition getBlockPosition() {
/* 38 */     return this.c;
/*    */   }
/*    */   
/*    */   public EnumDirection getDirection() {
/* 42 */     return this.b;
/*    */   }
/*    */ 
/*    */   
/*    */   public MovingObjectPosition.EnumMovingObjectType getType() {
/* 47 */     return this.d ? MovingObjectPosition.EnumMovingObjectType.MISS : MovingObjectPosition.EnumMovingObjectType.BLOCK;
/*    */   }
/*    */   
/*    */   public boolean d() {
/* 51 */     return this.e;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\MovingObjectPositionBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
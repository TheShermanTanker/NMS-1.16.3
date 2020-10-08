/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockActionContextDirectional
/*    */   extends BlockActionContext
/*    */ {
/*    */   private final EnumDirection b;
/*    */   
/*    */   public BlockActionContextDirectional(World var0, BlockPosition var1, EnumDirection var2, ItemStack var3, EnumDirection var4) {
/* 15 */     super(var0, (EntityHuman)null, EnumHand.MAIN_HAND, var3, new MovingObjectPositionBlock(Vec3D.c(var1), var4, var1, false));
/*    */     
/* 17 */     this.b = var2;
/*    */   }
/*    */ 
/*    */   
/*    */   public BlockPosition getClickPosition() {
/* 22 */     return i().getBlockPosition();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean b() {
/* 27 */     return getWorld().getType(i().getBlockPosition()).a(this);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean c() {
/* 32 */     return b();
/*    */   }
/*    */ 
/*    */   
/*    */   public EnumDirection d() {
/* 37 */     return EnumDirection.DOWN;
/*    */   }
/*    */ 
/*    */   
/*    */   public EnumDirection[] e() {
/* 42 */     switch (null.a[this.b.ordinal()])
/*    */     
/*    */     { default:
/* 45 */         return new EnumDirection[] { EnumDirection.DOWN, EnumDirection.NORTH, EnumDirection.EAST, EnumDirection.SOUTH, EnumDirection.WEST, EnumDirection.UP };
/*    */       case 2:
/* 47 */         return new EnumDirection[] { EnumDirection.DOWN, EnumDirection.UP, EnumDirection.NORTH, EnumDirection.EAST, EnumDirection.SOUTH, EnumDirection.WEST };
/*    */       case 3:
/* 49 */         return new EnumDirection[] { EnumDirection.DOWN, EnumDirection.NORTH, EnumDirection.EAST, EnumDirection.WEST, EnumDirection.UP, EnumDirection.SOUTH };
/*    */       case 4:
/* 51 */         return new EnumDirection[] { EnumDirection.DOWN, EnumDirection.SOUTH, EnumDirection.EAST, EnumDirection.WEST, EnumDirection.UP, EnumDirection.NORTH };
/*    */       case 5:
/* 53 */         return new EnumDirection[] { EnumDirection.DOWN, EnumDirection.WEST, EnumDirection.SOUTH, EnumDirection.UP, EnumDirection.NORTH, EnumDirection.EAST };
/*    */       case 6:
/* 55 */         break; }  return new EnumDirection[] { EnumDirection.DOWN, EnumDirection.EAST, EnumDirection.SOUTH, EnumDirection.UP, EnumDirection.NORTH, EnumDirection.WEST };
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public EnumDirection f() {
/* 61 */     return (this.b.n() == EnumDirection.EnumAxis.Y) ? EnumDirection.NORTH : this.b;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isSneaking() {
/* 66 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public float h() {
/* 71 */     return (this.b.get2DRotationValue() * 90);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockActionContextDirectional.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
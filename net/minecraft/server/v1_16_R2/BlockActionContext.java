/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockActionContext
/*    */   extends ItemActionContext
/*    */ {
/*    */   private final BlockPosition b;
/*    */   protected boolean a = true;
/*    */   
/*    */   public BlockActionContext(EntityHuman var0, EnumHand var1, ItemStack var2, MovingObjectPositionBlock var3) {
/* 21 */     this(var0.world, var0, var1, var2, var3);
/*    */   }
/*    */   
/*    */   public BlockActionContext(ItemActionContext var0) {
/* 25 */     this(var0.getWorld(), var0.getEntity(), var0.getHand(), var0.getItemStack(), var0.i());
/*    */   }
/*    */   
/*    */   protected BlockActionContext(World var0, @Nullable EntityHuman var1, EnumHand var2, ItemStack var3, MovingObjectPositionBlock var4) {
/* 29 */     super(var0, var1, var2, var3, var4);
/*    */     
/* 31 */     this.b = var4.getBlockPosition().shift(var4.getDirection());
/* 32 */     this.a = var0.getType(var4.getBlockPosition()).a(this);
/*    */   }
/*    */   
/*    */   public static BlockActionContext a(BlockActionContext var0, BlockPosition var1, EnumDirection var2) {
/* 36 */     return new BlockActionContext(var0
/* 37 */         .getWorld(), var0
/* 38 */         .getEntity(), var0
/* 39 */         .getHand(), var0
/* 40 */         .getItemStack(), new MovingObjectPositionBlock(new Vec3D(var1
/*    */ 
/*    */             
/* 43 */             .getX() + 0.5D + var2.getAdjacentX() * 0.5D, var1
/* 44 */             .getY() + 0.5D + var2.getAdjacentY() * 0.5D, var1
/* 45 */             .getZ() + 0.5D + var2.getAdjacentZ() * 0.5D), var2, var1, false));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public BlockPosition getClickPosition() {
/* 56 */     return this.a ? super.getClickPosition() : this.b;
/*    */   }
/*    */   
/*    */   public boolean b() {
/* 60 */     return (this.a || getWorld().getType(getClickPosition()).a(this));
/*    */   }
/*    */   
/*    */   public boolean c() {
/* 64 */     return this.a;
/*    */   }
/*    */   
/*    */   public EnumDirection d() {
/* 68 */     return EnumDirection.a(getEntity())[0];
/*    */   }
/*    */   
/*    */   public EnumDirection[] e() {
/* 72 */     EnumDirection[] var0 = EnumDirection.a(getEntity());
/*    */     
/* 74 */     if (this.a) {
/* 75 */       return var0;
/*    */     }
/*    */     
/* 78 */     EnumDirection var1 = getClickedFace();
/*    */ 
/*    */     
/* 81 */     int var2 = 0;
/* 82 */     for (; var2 < var0.length && 
/* 83 */       var0[var2] != var1.opposite(); var2++);
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 88 */     if (var2 > 0) {
/* 89 */       System.arraycopy(var0, 0, var0, 1, var2);
/* 90 */       var0[0] = var1.opposite();
/*    */     } 
/* 92 */     return var0;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockActionContext.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
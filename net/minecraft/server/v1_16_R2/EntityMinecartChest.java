/*    */ package net.minecraft.server.v1_16_R2;
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
/*    */ 
/*    */ public class EntityMinecartChest
/*    */   extends EntityMinecartContainer
/*    */ {
/*    */   public EntityMinecartChest(EntityTypes<? extends EntityMinecartChest> var0, World var1) {
/* 17 */     super(var0, var1);
/*    */   }
/*    */   
/*    */   public EntityMinecartChest(World var0, double var1, double var3, double var5) {
/* 21 */     super(EntityTypes.CHEST_MINECART, var1, var3, var5, var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(DamageSource var0) {
/* 26 */     super.a(var0);
/*    */     
/* 28 */     if (this.world.getGameRules().getBoolean(GameRules.DO_ENTITY_DROPS)) {
/* 29 */       a(Blocks.CHEST);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public int getSize() {
/* 35 */     return 27;
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityMinecartAbstract.EnumMinecartType getMinecartType() {
/* 40 */     return EntityMinecartAbstract.EnumMinecartType.CHEST;
/*    */   }
/*    */ 
/*    */   
/*    */   public IBlockData q() {
/* 45 */     return Blocks.CHEST.getBlockData().set(BlockChest.FACING, EnumDirection.NORTH);
/*    */   }
/*    */ 
/*    */   
/*    */   public int s() {
/* 50 */     return 8;
/*    */   }
/*    */ 
/*    */   
/*    */   public Container a(int var0, PlayerInventory var1) {
/* 55 */     return ContainerChest.a(var0, var1, this);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntityMinecartChest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ public abstract class EntityFireballFireball
/*    */   extends EntityFireball {
/*  5 */   private static final DataWatcherObject<ItemStack> e = DataWatcher.a((Class)EntityFireballFireball.class, DataWatcherRegistry.g);
/*    */   
/*    */   public EntityFireballFireball(EntityTypes<? extends EntityFireballFireball> entitytypes, World world) {
/*  8 */     super((EntityTypes)entitytypes, world);
/*    */   }
/*    */   
/*    */   public EntityFireballFireball(EntityTypes<? extends EntityFireballFireball> entitytypes, double d0, double d1, double d2, double d3, double d4, double d5, World world) {
/* 12 */     super((EntityTypes)entitytypes, d0, d1, d2, d3, d4, d5, world);
/*    */   }
/*    */   
/*    */   public EntityFireballFireball(EntityTypes<? extends EntityFireballFireball> entitytypes, EntityLiving entityliving, double d0, double d1, double d2, World world) {
/* 16 */     super((EntityTypes)entitytypes, entityliving, d0, d1, d2, world);
/*    */   }
/*    */   
/*    */   public void setItem(ItemStack itemstack) {
/* 20 */     if (itemstack.getItem() != Items.FIRE_CHARGE || itemstack.hasTag()) {
/* 21 */       getDataWatcher().set(e, SystemUtils.<ItemStack>a(itemstack.cloneItemStack(), itemstack1 -> itemstack1.setCount(1)));
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ItemStack getItem() {
/* 29 */     return getDataWatcher().<ItemStack>get(e);
/*    */   }
/*    */ 
/*    */   
/*    */   protected void initDatawatcher() {
/* 34 */     getDataWatcher().register(e, ItemStack.b);
/*    */   }
/*    */ 
/*    */   
/*    */   public void saveData(NBTTagCompound nbttagcompound) {
/* 39 */     super.saveData(nbttagcompound);
/* 40 */     ItemStack itemstack = getItem();
/*    */     
/* 42 */     if (!itemstack.isEmpty()) {
/* 43 */       nbttagcompound.set("Item", itemstack.save(new NBTTagCompound()));
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void loadData(NBTTagCompound nbttagcompound) {
/* 50 */     super.loadData(nbttagcompound);
/* 51 */     ItemStack itemstack = ItemStack.a(nbttagcompound.getCompound("Item"));
/*    */     
/* 53 */     if (!itemstack.isEmpty()) setItem(itemstack); 
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntityFireballFireball.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
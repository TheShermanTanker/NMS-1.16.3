/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ public abstract class EntityProjectileThrowable
/*    */   extends EntityProjectile {
/*  5 */   private static final DataWatcherObject<ItemStack> b = DataWatcher.a((Class)EntityProjectileThrowable.class, DataWatcherRegistry.g);
/*    */   
/*    */   public EntityProjectileThrowable(EntityTypes<? extends EntityProjectileThrowable> entitytypes, World world) {
/*  8 */     super((EntityTypes)entitytypes, world);
/*    */   }
/*    */   
/*    */   public EntityProjectileThrowable(EntityTypes<? extends EntityProjectileThrowable> entitytypes, double d0, double d1, double d2, World world) {
/* 12 */     super((EntityTypes)entitytypes, d0, d1, d2, world);
/*    */   }
/*    */   
/*    */   public EntityProjectileThrowable(EntityTypes<? extends EntityProjectileThrowable> entitytypes, EntityLiving entityliving, World world) {
/* 16 */     super((EntityTypes)entitytypes, entityliving, world);
/*    */   }
/*    */   
/*    */   public void setItem(ItemStack itemstack) {
/* 20 */     if (itemstack.getItem() != getDefaultItem() || itemstack.hasTag()) {
/* 21 */       getDataWatcher().set(b, SystemUtils.<ItemStack>a(itemstack.cloneItemStack(), itemstack1 -> {
/*    */               if (!itemstack1.isEmpty()) {
/*    */                 itemstack1.setCount(1);
/*    */               }
/*    */             }));
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Item getDefaultItemPublic() {
/* 32 */     return getDefaultItem();
/*    */   }
/*    */ 
/*    */   
/*    */   public ItemStack getItem() {
/* 37 */     return getDataWatcher().<ItemStack>get(b);
/*    */   }
/*    */   
/*    */   public ItemStack g() {
/* 41 */     ItemStack itemstack = getItem();
/*    */     
/* 43 */     return itemstack.isEmpty() ? new ItemStack(getDefaultItem()) : itemstack;
/*    */   }
/*    */ 
/*    */   
/*    */   protected void initDatawatcher() {
/* 48 */     getDataWatcher().register(b, ItemStack.b);
/*    */   }
/*    */ 
/*    */   
/*    */   public void saveData(NBTTagCompound nbttagcompound) {
/* 53 */     super.saveData(nbttagcompound);
/* 54 */     ItemStack itemstack = getItem();
/*    */     
/* 56 */     if (!itemstack.isEmpty()) {
/* 57 */       nbttagcompound.set("Item", itemstack.save(new NBTTagCompound()));
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void loadData(NBTTagCompound nbttagcompound) {
/* 64 */     super.loadData(nbttagcompound);
/* 65 */     ItemStack itemstack = ItemStack.a(nbttagcompound.getCompound("Item"));
/*    */     
/* 67 */     setItem(itemstack);
/*    */   }
/*    */   
/*    */   protected abstract Item getDefaultItem();
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntityProjectileThrowable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
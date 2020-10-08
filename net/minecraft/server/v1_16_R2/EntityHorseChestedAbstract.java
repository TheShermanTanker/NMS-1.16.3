/*     */ package net.minecraft.server.v1_16_R2;
/*     */ import org.bukkit.event.entity.EntityDeathEvent;
/*     */ 
/*     */ public abstract class EntityHorseChestedAbstract extends EntityHorseAbstract {
/*   5 */   private static final DataWatcherObject<Boolean> bw = DataWatcher.a((Class)EntityHorseChestedAbstract.class, DataWatcherRegistry.i);
/*     */   
/*     */   protected EntityHorseChestedAbstract(EntityTypes<? extends EntityHorseChestedAbstract> entitytypes, World world) {
/*   8 */     super((EntityTypes)entitytypes, world);
/*   9 */     this.bu = false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void eK() {
/*  14 */     getAttributeInstance(GenericAttributes.MAX_HEALTH).setValue(fp());
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initDatawatcher() {
/*  19 */     super.initDatawatcher();
/*  20 */     this.datawatcher.register(bw, Boolean.valueOf(false));
/*     */   }
/*     */   
/*     */   public static AttributeProvider.Builder eL() {
/*  24 */     return fi().a(GenericAttributes.MOVEMENT_SPEED, 0.17499999701976776D).a(GenericAttributes.JUMP_STRENGTH, 0.5D);
/*     */   }
/*     */   
/*     */   public boolean isCarryingChest() {
/*  28 */     return ((Boolean)this.datawatcher.<Boolean>get(bw)).booleanValue();
/*     */   }
/*     */   
/*     */   public void setCarryingChest(boolean flag) {
/*  32 */     this.datawatcher.set(bw, Boolean.valueOf(flag));
/*     */   }
/*     */ 
/*     */   
/*     */   protected int getChestSlots() {
/*  37 */     return isCarryingChest() ? 17 : super.getChestSlots();
/*     */   }
/*     */ 
/*     */   
/*     */   public double bb() {
/*  42 */     return super.bb() - 0.25D;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void dropInventory() {
/*  47 */     super.dropInventory();
/*  48 */     if (isCarryingChest() && 
/*  49 */       !this.world.isClientSide) {
/*  50 */       a(Blocks.CHEST);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void postDeathDropItems(EntityDeathEvent event) {
/*  60 */     if (isCarryingChest() && (event == null || !event.isCancelled())) {
/*  61 */       setCarryingChest(false);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void saveData(NBTTagCompound nbttagcompound) {
/*  68 */     super.saveData(nbttagcompound);
/*  69 */     nbttagcompound.setBoolean("ChestedHorse", isCarryingChest());
/*  70 */     if (isCarryingChest()) {
/*  71 */       NBTTagList nbttaglist = new NBTTagList();
/*     */       
/*  73 */       for (int i = 2; i < this.inventoryChest.getSize(); i++) {
/*  74 */         ItemStack itemstack = this.inventoryChest.getItem(i);
/*     */         
/*  76 */         if (!itemstack.isEmpty()) {
/*  77 */           NBTTagCompound nbttagcompound1 = new NBTTagCompound();
/*     */           
/*  79 */           nbttagcompound1.setByte("Slot", (byte)i);
/*  80 */           itemstack.save(nbttagcompound1);
/*  81 */           nbttaglist.add(nbttagcompound1);
/*     */         } 
/*     */       } 
/*     */       
/*  85 */       nbttagcompound.set("Items", nbttaglist);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void loadData(NBTTagCompound nbttagcompound) {
/*  92 */     super.loadData(nbttagcompound);
/*  93 */     setCarryingChest(nbttagcompound.getBoolean("ChestedHorse"));
/*  94 */     if (isCarryingChest()) {
/*  95 */       NBTTagList nbttaglist = nbttagcompound.getList("Items", 10);
/*     */       
/*  97 */       loadChest();
/*     */       
/*  99 */       for (int i = 0; i < nbttaglist.size(); i++) {
/* 100 */         NBTTagCompound nbttagcompound1 = nbttaglist.getCompound(i);
/* 101 */         int j = nbttagcompound1.getByte("Slot") & 0xFF;
/*     */         
/* 103 */         if (j >= 2 && j < this.inventoryChest.getSize()) {
/* 104 */           this.inventoryChest.setItem(j, ItemStack.a(nbttagcompound1));
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 109 */     fe();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a_(int i, ItemStack itemstack) {
/* 114 */     if (i == 499) {
/* 115 */       if (isCarryingChest() && itemstack.isEmpty()) {
/* 116 */         setCarryingChest(false);
/* 117 */         loadChest();
/* 118 */         return true;
/*     */       } 
/*     */       
/* 121 */       if (!isCarryingChest() && itemstack.getItem() == Blocks.CHEST.getItem()) {
/* 122 */         setCarryingChest(true);
/* 123 */         loadChest();
/* 124 */         return true;
/*     */       } 
/*     */     } 
/*     */     
/* 128 */     return super.a_(i, itemstack);
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumInteractionResult b(EntityHuman entityhuman, EnumHand enumhand) {
/* 133 */     ItemStack itemstack = entityhuman.b(enumhand);
/*     */     
/* 135 */     if (!isBaby()) {
/* 136 */       if (isTamed() && entityhuman.ep()) {
/* 137 */         f(entityhuman);
/* 138 */         return EnumInteractionResult.a(this.world.isClientSide);
/*     */       } 
/*     */       
/* 141 */       if (isVehicle()) {
/* 142 */         return super.b(entityhuman, enumhand);
/*     */       }
/*     */     } 
/*     */     
/* 146 */     if (!itemstack.isEmpty()) {
/* 147 */       if (k(itemstack)) {
/* 148 */         return b(entityhuman, itemstack);
/*     */       }
/*     */       
/* 151 */       if (!isTamed()) {
/* 152 */         fm();
/* 153 */         return EnumInteractionResult.a(this.world.isClientSide);
/*     */       } 
/*     */       
/* 156 */       if (!isCarryingChest() && itemstack.getItem() == Blocks.CHEST.getItem()) {
/* 157 */         setCarryingChest(true);
/* 158 */         eO();
/* 159 */         if (!entityhuman.abilities.canInstantlyBuild) {
/* 160 */           itemstack.subtract(1);
/*     */         }
/*     */         
/* 163 */         loadChest();
/* 164 */         return EnumInteractionResult.a(this.world.isClientSide);
/*     */       } 
/*     */       
/* 167 */       if (!isBaby() && !hasSaddle() && itemstack.getItem() == Items.SADDLE) {
/* 168 */         f(entityhuman);
/* 169 */         return EnumInteractionResult.a(this.world.isClientSide);
/*     */       } 
/*     */     } 
/*     */     
/* 173 */     if (isBaby()) {
/* 174 */       return super.b(entityhuman, enumhand);
/*     */     }
/* 176 */     h(entityhuman);
/* 177 */     return EnumInteractionResult.a(this.world.isClientSide);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void eO() {
/* 182 */     playSound(SoundEffects.ENTITY_DONKEY_CHEST, 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
/*     */   }
/*     */   
/*     */   public int eU() {
/* 186 */     return 5;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntityHorseChestedAbstract.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
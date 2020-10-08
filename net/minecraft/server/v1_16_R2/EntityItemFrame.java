/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import javax.annotation.Nullable;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import org.bukkit.craftbukkit.libs.org.apache.commons.lang3.Validate;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.event.CraftEventFactory;
/*     */ 
/*     */ public class EntityItemFrame extends EntityHanging {
/*  10 */   private static final Logger LOGGER = LogManager.getLogger();
/*  11 */   private static final DataWatcherObject<ItemStack> ITEM = DataWatcher.a((Class)EntityItemFrame.class, DataWatcherRegistry.g);
/*  12 */   private static final DataWatcherObject<Integer> g = DataWatcher.a((Class)EntityItemFrame.class, DataWatcherRegistry.b);
/*  13 */   private float ag = 1.0F;
/*     */   public boolean fixed;
/*     */   
/*     */   public EntityItemFrame(EntityTypes<? extends EntityItemFrame> entitytypes, World world) {
/*  17 */     super((EntityTypes)entitytypes, world);
/*     */   }
/*     */   
/*     */   public EntityItemFrame(World world, BlockPosition blockposition, EnumDirection enumdirection) {
/*  21 */     super((EntityTypes)EntityTypes.ITEM_FRAME, world, blockposition);
/*  22 */     setDirection(enumdirection);
/*     */   }
/*     */ 
/*     */   
/*     */   protected float getHeadHeight(EntityPose entitypose, EntitySize entitysize) {
/*  27 */     return 0.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initDatawatcher() {
/*  32 */     getDataWatcher().register(ITEM, ItemStack.b);
/*  33 */     getDataWatcher().register(g, Integer.valueOf(0));
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDirection(EnumDirection enumdirection) {
/*  38 */     Validate.notNull(enumdirection);
/*  39 */     this.direction = enumdirection;
/*  40 */     if (enumdirection.n().d()) {
/*  41 */       this.pitch = 0.0F;
/*  42 */       this.yaw = (this.direction.get2DRotationValue() * 90);
/*     */     } else {
/*  44 */       this.pitch = (-90 * enumdirection.e().a());
/*  45 */       this.yaw = 0.0F;
/*     */     } 
/*     */     
/*  48 */     this.lastPitch = this.pitch;
/*  49 */     this.lastYaw = this.yaw;
/*  50 */     updateBoundingBox();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void updateBoundingBox() {
/*  55 */     if (this.direction != null)
/*     */     {
/*  57 */       a(calculateBoundingBox(this, this.blockPosition, this.direction, getHangingWidth(), getHangingHeight()));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static AxisAlignedBB calculateBoundingBox(@Nullable Entity entity, BlockPosition blockPosition, EnumDirection direction, int width, int height) {
/*  65 */     double d0 = 0.46875D;
/*  66 */     double d1 = blockPosition.getX() + 0.5D - direction.getAdjacentX() * 0.46875D;
/*  67 */     double d2 = blockPosition.getY() + 0.5D - direction.getAdjacentY() * 0.46875D;
/*  68 */     double d3 = blockPosition.getZ() + 0.5D - direction.getAdjacentZ() * 0.46875D;
/*     */     
/*  70 */     if (entity != null) {
/*  71 */       entity.setPositionRaw(d1, d2, d3);
/*     */     }
/*  73 */     double d4 = width;
/*  74 */     double d5 = height;
/*  75 */     double d6 = width;
/*  76 */     EnumDirection.EnumAxis enumdirection_enumaxis = direction.n();
/*     */     
/*  78 */     switch (enumdirection_enumaxis) {
/*     */       case X:
/*  80 */         d4 = 1.0D;
/*     */         break;
/*     */       case Y:
/*  83 */         d5 = 1.0D;
/*     */         break;
/*     */       case Z:
/*  86 */         d6 = 1.0D;
/*     */         break;
/*     */     } 
/*  89 */     d4 /= 32.0D;
/*  90 */     d5 /= 32.0D;
/*  91 */     d6 /= 32.0D;
/*  92 */     return new AxisAlignedBB(d1 - d4, d2 - d5, d3 - d6, d1 + d4, d2 + d5, d3 + d6);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean survives() {
/*  99 */     if (this.fixed)
/* 100 */       return true; 
/* 101 */     if (!this.world.getCubes(this)) {
/* 102 */       return false;
/*     */     }
/* 104 */     IBlockData iblockdata = this.world.getType(this.blockPosition.shift(this.direction.opposite()));
/*     */     
/* 106 */     return (!iblockdata.getMaterial().isBuildable() && (!this.direction.n().d() || !BlockDiodeAbstract.isDiode(iblockdata))) ? false : this.world.getEntities(this, getBoundingBox(), b).isEmpty();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void move(EnumMoveType enummovetype, Vec3D vec3d) {
/* 112 */     if (!this.fixed) {
/* 113 */       super.move(enummovetype, vec3d);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void i(double d0, double d1, double d2) {
/* 120 */     if (!this.fixed) {
/* 121 */       super.i(d0, d1, d2);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float bf() {
/* 128 */     return 0.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public void killEntity() {
/* 133 */     c(getItem());
/* 134 */     super.killEntity();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean damageEntity(DamageSource damagesource, float f) {
/* 139 */     if (this.fixed)
/* 140 */       return (damagesource != DamageSource.OUT_OF_WORLD && !damagesource.v()) ? false : super.damageEntity(damagesource, f); 
/* 141 */     if (isInvulnerable(damagesource))
/* 142 */       return false; 
/* 143 */     if (!damagesource.isExplosion() && !getItem().isEmpty()) {
/* 144 */       if (!this.world.isClientSide) {
/*     */         
/* 146 */         if (CraftEventFactory.handleNonLivingEntityDamageEvent(this, damagesource, f, false) || this.dead) {
/* 147 */           return true;
/*     */         }
/*     */         
/* 150 */         b(damagesource.getEntity(), false);
/* 151 */         playSound(SoundEffects.ENTITY_ITEM_FRAME_REMOVE_ITEM, 1.0F, 1.0F);
/*     */       } 
/*     */       
/* 154 */       return true;
/*     */     } 
/* 156 */     return super.damageEntity(damagesource, f);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getHangingWidth() {
/* 162 */     return 12;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getHangingHeight() {
/* 167 */     return 12;
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(@Nullable Entity entity) {
/* 172 */     playSound(SoundEffects.ENTITY_ITEM_FRAME_BREAK, 1.0F, 1.0F);
/* 173 */     b(entity, true);
/*     */   }
/*     */ 
/*     */   
/*     */   public void playPlaceSound() {
/* 178 */     playSound(SoundEffects.ENTITY_ITEM_FRAME_PLACE, 1.0F, 1.0F);
/*     */   }
/*     */   
/*     */   private void b(@Nullable Entity entity, boolean flag) {
/* 182 */     if (!this.fixed) {
/* 183 */       ItemStack itemstack = getItem();
/*     */       
/* 185 */       setItem(ItemStack.b);
/* 186 */       if (!this.world.getGameRules().getBoolean(GameRules.DO_ENTITY_DROPS)) {
/* 187 */         if (entity == null) {
/* 188 */           c(itemstack);
/*     */         }
/*     */       } else {
/*     */         
/* 192 */         if (entity instanceof EntityHuman) {
/* 193 */           EntityHuman entityhuman = (EntityHuman)entity;
/*     */           
/* 195 */           if (entityhuman.abilities.canInstantlyBuild) {
/* 196 */             c(itemstack);
/*     */             
/*     */             return;
/*     */           } 
/*     */         } 
/* 201 */         if (flag) {
/* 202 */           a(Items.ITEM_FRAME);
/*     */         }
/*     */         
/* 205 */         if (!itemstack.isEmpty()) {
/* 206 */           itemstack = itemstack.cloneItemStack();
/* 207 */           c(itemstack);
/* 208 */           if (this.random.nextFloat() < this.ag) {
/* 209 */             a(itemstack);
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void c(ItemStack itemstack) {
/* 218 */     if (itemstack.getItem() == Items.FILLED_MAP) {
/* 219 */       WorldMap worldmap = ItemWorldMap.getSavedMap(itemstack, this.world);
/*     */       
/* 221 */       worldmap.a(this.blockPosition, getId());
/* 222 */       worldmap.a(true);
/*     */     } 
/*     */     
/* 225 */     itemstack.a((Entity)null);
/*     */   }
/*     */   
/*     */   public ItemStack getItem() {
/* 229 */     return getDataWatcher().<ItemStack>get(ITEM);
/*     */   }
/*     */   
/*     */   public void setItem(ItemStack itemstack) {
/* 233 */     setItem(itemstack, true);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setItem(ItemStack itemstack, boolean flag) {
/* 238 */     setItem(itemstack, flag, true);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setItem(ItemStack itemstack, boolean flag, boolean playSound) {
/* 243 */     if (!itemstack.isEmpty()) {
/* 244 */       itemstack = itemstack.cloneItemStack();
/* 245 */       itemstack.setCount(1);
/* 246 */       itemstack.a(this);
/*     */     } 
/*     */     
/* 249 */     getDataWatcher().set(ITEM, itemstack);
/* 250 */     if (!itemstack.isEmpty() && flag && playSound) {
/* 251 */       playSound(SoundEffects.ENTITY_ITEM_FRAME_ADD_ITEM, 1.0F, 1.0F);
/*     */     }
/*     */     
/* 254 */     if (flag && this.blockPosition != null) {
/* 255 */       this.world.updateAdjacentComparators(this.blockPosition, Blocks.AIR);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean a_(int i, ItemStack itemstack) {
/* 262 */     if (i == 0) {
/* 263 */       setItem(itemstack);
/* 264 */       return true;
/*     */     } 
/* 266 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(DataWatcherObject<?> datawatcherobject) {
/* 272 */     if (datawatcherobject.equals(ITEM)) {
/* 273 */       ItemStack itemstack = getItem();
/*     */       
/* 275 */       if (!itemstack.isEmpty() && itemstack.z() != this) {
/* 276 */         itemstack.a(this);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public int getRotation() {
/* 283 */     return ((Integer)getDataWatcher().<Integer>get(g)).intValue();
/*     */   }
/*     */   
/*     */   public void setRotation(int i) {
/* 287 */     setRotation(i, true);
/*     */   }
/*     */   
/*     */   private void setRotation(int i, boolean flag) {
/* 291 */     getDataWatcher().set(g, Integer.valueOf(i % 8));
/* 292 */     if (flag && this.blockPosition != null) {
/* 293 */       this.world.updateAdjacentComparators(this.blockPosition, Blocks.AIR);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void saveData(NBTTagCompound nbttagcompound) {
/* 300 */     super.saveData(nbttagcompound);
/* 301 */     if (!getItem().isEmpty()) {
/* 302 */       nbttagcompound.set("Item", getItem().save(new NBTTagCompound()));
/* 303 */       nbttagcompound.setByte("ItemRotation", (byte)getRotation());
/* 304 */       nbttagcompound.setFloat("ItemDropChance", this.ag);
/*     */     } 
/*     */     
/* 307 */     nbttagcompound.setByte("Facing", (byte)this.direction.c());
/* 308 */     nbttagcompound.setBoolean("Invisible", isInvisible());
/* 309 */     nbttagcompound.setBoolean("Fixed", this.fixed);
/*     */   }
/*     */ 
/*     */   
/*     */   public void loadData(NBTTagCompound nbttagcompound) {
/* 314 */     super.loadData(nbttagcompound);
/* 315 */     NBTTagCompound nbttagcompound1 = nbttagcompound.getCompound("Item");
/*     */     
/* 317 */     if (nbttagcompound1 != null && !nbttagcompound1.isEmpty()) {
/* 318 */       ItemStack itemstack = ItemStack.a(nbttagcompound1);
/*     */       
/* 320 */       if (itemstack.isEmpty()) {
/* 321 */         LOGGER.warn("Unable to load item from: {}", nbttagcompound1);
/*     */       }
/*     */       
/* 324 */       ItemStack itemstack1 = getItem();
/*     */       
/* 326 */       if (!itemstack1.isEmpty() && !ItemStack.matches(itemstack, itemstack1)) {
/* 327 */         c(itemstack1);
/*     */       }
/*     */       
/* 330 */       setItem(itemstack, false);
/* 331 */       setRotation(nbttagcompound.getByte("ItemRotation"), false);
/* 332 */       if (nbttagcompound.hasKeyOfType("ItemDropChance", 99)) {
/* 333 */         this.ag = nbttagcompound.getFloat("ItemDropChance");
/*     */       }
/*     */     } 
/*     */     
/* 337 */     setDirection(EnumDirection.fromType1(nbttagcompound.getByte("Facing")));
/* 338 */     setInvisible(nbttagcompound.getBoolean("Invisible"));
/* 339 */     this.fixed = nbttagcompound.getBoolean("Fixed");
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumInteractionResult a(EntityHuman entityhuman, EnumHand enumhand) {
/* 344 */     ItemStack itemstack = entityhuman.b(enumhand);
/* 345 */     boolean flag = !getItem().isEmpty();
/* 346 */     boolean flag1 = !itemstack.isEmpty();
/*     */     
/* 348 */     if (this.fixed)
/* 349 */       return EnumInteractionResult.PASS; 
/* 350 */     if (!this.world.isClientSide) {
/* 351 */       if (!flag) {
/* 352 */         if (flag1 && !this.dead) {
/* 353 */           setItem(itemstack);
/* 354 */           if (!entityhuman.abilities.canInstantlyBuild) {
/* 355 */             itemstack.subtract(1);
/*     */           }
/*     */         } 
/*     */       } else {
/* 359 */         playSound(SoundEffects.ENTITY_ITEM_FRAME_ROTATE_ITEM, 1.0F, 1.0F);
/* 360 */         setRotation(getRotation() + 1);
/*     */       } 
/*     */       
/* 363 */       return EnumInteractionResult.CONSUME;
/*     */     } 
/* 365 */     return (!flag && !flag1) ? EnumInteractionResult.PASS : EnumInteractionResult.SUCCESS;
/*     */   }
/*     */ 
/*     */   
/*     */   public int q() {
/* 370 */     return getItem().isEmpty() ? 0 : (getRotation() % 8 + 1);
/*     */   }
/*     */ 
/*     */   
/*     */   public Packet<?> P() {
/* 375 */     return new PacketPlayOutSpawnEntity(this, getEntityType(), this.direction.c(), getBlockPosition());
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntityItemFrame.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ public class EntityEnderSignal
/*     */   extends Entity {
/*   5 */   private static final DataWatcherObject<ItemStack> b = DataWatcher.a((Class)EntityEnderSignal.class, DataWatcherRegistry.g);
/*     */   public double targetX;
/*     */   public double targetY;
/*     */   public double targetZ;
/*     */   public int despawnTimer;
/*     */   public boolean shouldDropItem;
/*     */   
/*     */   public EntityEnderSignal(EntityTypes<? extends EntityEnderSignal> entitytypes, World world) {
/*  13 */     super(entitytypes, world);
/*     */   }
/*     */   
/*     */   public EntityEnderSignal(World world, double d0, double d1, double d2) {
/*  17 */     this(EntityTypes.EYE_OF_ENDER, world);
/*  18 */     this.despawnTimer = 0;
/*  19 */     setPosition(d0, d1, d2);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setItem(ItemStack itemstack) {
/*  24 */     getDataWatcher().set(b, SystemUtils.<ItemStack>a(itemstack.cloneItemStack(), itemstack1 -> itemstack1.setCount(1)));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private ItemStack h() {
/*  32 */     return getDataWatcher().<ItemStack>get(b);
/*     */   }
/*     */   
/*     */   public ItemStack getItem() {
/*  36 */     ItemStack itemstack = h();
/*     */     
/*  38 */     return itemstack.isEmpty() ? new ItemStack(Items.ENDER_EYE) : itemstack;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initDatawatcher() {
/*  43 */     getDataWatcher().register(b, ItemStack.b);
/*     */   }
/*     */   
/*     */   public void a(BlockPosition blockposition) {
/*  47 */     double d0 = blockposition.getX();
/*  48 */     int i = blockposition.getY();
/*  49 */     double d1 = blockposition.getZ();
/*  50 */     double d2 = d0 - locX();
/*  51 */     double d3 = d1 - locZ();
/*  52 */     float f = MathHelper.sqrt(d2 * d2 + d3 * d3);
/*     */     
/*  54 */     if (f > 12.0F) {
/*  55 */       this.targetX = locX() + d2 / f * 12.0D;
/*  56 */       this.targetZ = locZ() + d3 / f * 12.0D;
/*  57 */       this.targetY = locY() + 8.0D;
/*     */     } else {
/*  59 */       this.targetX = d0;
/*  60 */       this.targetY = i;
/*  61 */       this.targetZ = d1;
/*     */     } 
/*     */     
/*  64 */     this.despawnTimer = 0;
/*  65 */     this.shouldDropItem = (this.random.nextInt(5) > 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void tick() {
/*  70 */     super.tick();
/*  71 */     Vec3D vec3d = getMot();
/*  72 */     double d0 = locX() + vec3d.x;
/*  73 */     double d1 = locY() + vec3d.y;
/*  74 */     double d2 = locZ() + vec3d.z;
/*  75 */     float f = MathHelper.sqrt(c(vec3d));
/*     */     
/*  77 */     this.pitch = IProjectile.e(this.lastPitch, (float)(MathHelper.d(vec3d.y, f) * 57.2957763671875D));
/*  78 */     this.yaw = IProjectile.e(this.lastYaw, (float)(MathHelper.d(vec3d.x, vec3d.z) * 57.2957763671875D));
/*  79 */     if (!this.world.isClientSide) {
/*  80 */       double d3 = this.targetX - d0;
/*  81 */       double d4 = this.targetZ - d2;
/*  82 */       float f1 = (float)Math.sqrt(d3 * d3 + d4 * d4);
/*  83 */       float f2 = (float)MathHelper.d(d4, d3);
/*  84 */       double d5 = MathHelper.d(0.0025D, f, f1);
/*  85 */       double d6 = vec3d.y;
/*     */       
/*  87 */       if (f1 < 1.0F) {
/*  88 */         d5 *= 0.8D;
/*  89 */         d6 *= 0.8D;
/*     */       } 
/*     */       
/*  92 */       int i = (locY() < this.targetY) ? 1 : -1;
/*     */       
/*  94 */       vec3d = new Vec3D(Math.cos(f2) * d5, d6 + (i - d6) * 0.014999999664723873D, Math.sin(f2) * d5);
/*  95 */       setMot(vec3d);
/*     */     } 
/*     */     
/*  98 */     float f3 = 0.25F;
/*     */     
/* 100 */     if (isInWater()) {
/* 101 */       for (int j = 0; j < 4; j++) {
/* 102 */         this.world.addParticle(Particles.BUBBLE, d0 - vec3d.x * 0.25D, d1 - vec3d.y * 0.25D, d2 - vec3d.z * 0.25D, vec3d.x, vec3d.y, vec3d.z);
/*     */       }
/*     */     } else {
/* 105 */       this.world.addParticle(Particles.PORTAL, d0 - vec3d.x * 0.25D + this.random.nextDouble() * 0.6D - 0.3D, d1 - vec3d.y * 0.25D - 0.5D, d2 - vec3d.z * 0.25D + this.random.nextDouble() * 0.6D - 0.3D, vec3d.x, vec3d.y, vec3d.z);
/*     */     } 
/*     */     
/* 108 */     if (!this.world.isClientSide) {
/* 109 */       setPosition(d0, d1, d2);
/* 110 */       this.despawnTimer++;
/* 111 */       if (this.despawnTimer > 80 && !this.world.isClientSide) {
/* 112 */         playSound(SoundEffects.ENTITY_ENDER_EYE_DEATH, 1.0F, 1.0F);
/* 113 */         die();
/* 114 */         if (this.shouldDropItem) {
/* 115 */           this.world.addEntity(new EntityItem(this.world, locX(), locY(), locZ(), getItem()));
/*     */         } else {
/* 117 */           this.world.triggerEffect(2003, getChunkCoordinates(), 0);
/*     */         } 
/*     */       } 
/*     */     } else {
/* 121 */       setPositionRaw(d0, d1, d2);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void saveData(NBTTagCompound nbttagcompound) {
/* 128 */     ItemStack itemstack = h();
/*     */     
/* 130 */     if (!itemstack.isEmpty()) {
/* 131 */       nbttagcompound.set("Item", itemstack.save(new NBTTagCompound()));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void loadData(NBTTagCompound nbttagcompound) {
/* 138 */     ItemStack itemstack = ItemStack.a(nbttagcompound.getCompound("Item"));
/*     */     
/* 140 */     if (!itemstack.isEmpty()) setItem(itemstack);
/*     */   
/*     */   }
/*     */   
/*     */   public float aQ() {
/* 145 */     return 1.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean bK() {
/* 150 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public Packet<?> P() {
/* 155 */     return new PacketPlayOutSpawnEntity(this);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntityEnderSignal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
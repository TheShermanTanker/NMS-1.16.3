/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.event.weather.LightningStrikeEvent;
/*     */ 
/*     */ public class EntityThrownTrident extends EntityArrow {
/*   7 */   private static final DataWatcherObject<Byte> g = DataWatcher.a((Class)EntityThrownTrident.class, DataWatcherRegistry.a);
/*   8 */   private static final DataWatcherObject<Boolean> ag = DataWatcher.a((Class)EntityThrownTrident.class, DataWatcherRegistry.i);
/*     */   public ItemStack trident;
/*     */   private boolean ai;
/*     */   public int f;
/*     */   
/*     */   public EntityThrownTrident(EntityTypes<? extends EntityThrownTrident> entitytypes, World world) {
/*  14 */     super((EntityTypes)entitytypes, world);
/*  15 */     this.trident = new ItemStack(Items.TRIDENT);
/*     */   }
/*     */   
/*     */   public EntityThrownTrident(World world, EntityLiving entityliving, ItemStack itemstack) {
/*  19 */     super((EntityTypes)EntityTypes.TRIDENT, entityliving, world);
/*  20 */     this.trident = new ItemStack(Items.TRIDENT);
/*  21 */     this.trident = itemstack.cloneItemStack();
/*  22 */     this.datawatcher.set(g, Byte.valueOf((byte)EnchantmentManager.f(itemstack)));
/*  23 */     this.datawatcher.set(ag, Boolean.valueOf(itemstack.u()));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initDatawatcher() {
/*  28 */     super.initDatawatcher();
/*  29 */     this.datawatcher.register(g, Byte.valueOf((byte)0));
/*  30 */     this.datawatcher.register(ag, Boolean.valueOf(false));
/*     */   }
/*     */ 
/*     */   
/*     */   public void tick() {
/*  35 */     if (this.c > 4) {
/*  36 */       this.ai = true;
/*     */     }
/*     */     
/*  39 */     Entity entity = getShooter();
/*     */     
/*  41 */     if ((this.ai || t()) && entity != null) {
/*  42 */       byte b0 = ((Byte)this.datawatcher.<Byte>get(g)).byteValue();
/*     */       
/*  44 */       if (b0 > 0 && !z()) {
/*  45 */         if (!this.world.isClientSide && this.fromPlayer == EntityArrow.PickupStatus.ALLOWED) {
/*  46 */           a(getItemStack(), 0.1F);
/*     */         }
/*     */         
/*  49 */         die();
/*  50 */       } else if (b0 > 0) {
/*  51 */         o(true);
/*  52 */         Vec3D vec3d = new Vec3D(entity.locX() - locX(), entity.getHeadY() - locY(), entity.locZ() - locZ());
/*     */         
/*  54 */         setPositionRaw(locX(), locY() + vec3d.y * 0.015D * b0, locZ());
/*  55 */         if (this.world.isClientSide) {
/*  56 */           this.E = locY();
/*     */         }
/*     */         
/*  59 */         double d0 = 0.05D * b0;
/*     */         
/*  61 */         setMot(getMot().a(0.95D).e(vec3d.d().a(d0)));
/*  62 */         if (this.f == 0) {
/*  63 */           playSound(SoundEffects.ITEM_TRIDENT_RETURN, 10.0F, 1.0F);
/*     */         }
/*     */         
/*  66 */         this.f++;
/*     */       } 
/*     */     } 
/*     */     
/*  70 */     super.tick();
/*     */   }
/*     */   
/*     */   private boolean z() {
/*  74 */     Entity entity = getShooter();
/*     */     
/*  76 */     return (entity != null && entity.isAlive()) ? ((!(entity instanceof EntityPlayer) || !entity.isSpectator())) : false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected ItemStack getItemStack() {
/*  81 */     return this.trident.cloneItemStack();
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   protected MovingObjectPositionEntity a(Vec3D vec3d, Vec3D vec3d1) {
/*  87 */     return this.ai ? null : super.a(vec3d, vec3d1);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(MovingObjectPositionEntity movingobjectpositionentity) {
/*  92 */     Entity entity = movingobjectpositionentity.getEntity();
/*  93 */     float f = 8.0F;
/*     */     
/*  95 */     if (entity instanceof EntityLiving) {
/*  96 */       EntityLiving entityliving = (EntityLiving)entity;
/*     */       
/*  98 */       f += EnchantmentManager.a(this.trident, entityliving.getMonsterType());
/*     */     } 
/*     */     
/* 101 */     Entity entity1 = getShooter();
/* 102 */     DamageSource damagesource = DamageSource.a(this, (entity1 == null) ? this : entity1);
/*     */     
/* 104 */     this.ai = true;
/* 105 */     SoundEffect soundeffect = SoundEffects.ITEM_TRIDENT_HIT;
/*     */     
/* 107 */     if (entity.damageEntity(damagesource, f)) {
/* 108 */       if (entity.getEntityType() == EntityTypes.ENDERMAN) {
/*     */         return;
/*     */       }
/*     */       
/* 112 */       if (entity instanceof EntityLiving) {
/* 113 */         EntityLiving entityliving1 = (EntityLiving)entity;
/*     */         
/* 115 */         if (entity1 instanceof EntityLiving) {
/* 116 */           EnchantmentManager.a(entityliving1, entity1);
/* 117 */           EnchantmentManager.b((EntityLiving)entity1, entityliving1);
/*     */         } 
/*     */         
/* 120 */         a(entityliving1);
/*     */       } 
/*     */     } 
/*     */     
/* 124 */     setMot(getMot().d(-0.01D, -0.1D, -0.01D));
/* 125 */     float f1 = 1.0F;
/*     */     
/* 127 */     if (this.world instanceof WorldServer && this.world.V() && EnchantmentManager.h(this.trident)) {
/* 128 */       BlockPosition blockposition = entity.getChunkCoordinates();
/*     */       
/* 130 */       if (this.world.e(blockposition)) {
/* 131 */         EntityLightning entitylightning = EntityTypes.LIGHTNING_BOLT.a(this.world);
/*     */         
/* 133 */         entitylightning.d(Vec3D.c(blockposition));
/* 134 */         entitylightning.d((entity1 instanceof EntityPlayer) ? (EntityPlayer)entity1 : null);
/* 135 */         ((WorldServer)this.world).strikeLightning(entitylightning, LightningStrikeEvent.Cause.TRIDENT);
/* 136 */         soundeffect = SoundEffects.ITEM_TRIDENT_THUNDER;
/* 137 */         f1 = 5.0F;
/*     */       } 
/*     */     } 
/*     */     
/* 141 */     playSound(soundeffect, f1, 1.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect i() {
/* 146 */     return SoundEffects.ITEM_TRIDENT_HIT_GROUND;
/*     */   }
/*     */ 
/*     */   
/*     */   public void pickup(EntityHuman entityhuman) {
/* 151 */     Entity entity = getShooter();
/*     */     
/* 153 */     if (entity == null || entity.getUniqueID() == entityhuman.getUniqueID()) {
/* 154 */       super.pickup(entityhuman);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void loadData(NBTTagCompound nbttagcompound) {
/* 160 */     super.loadData(nbttagcompound);
/* 161 */     if (nbttagcompound.hasKeyOfType("Trident", 10)) {
/* 162 */       this.trident = ItemStack.a(nbttagcompound.getCompound("Trident"));
/*     */     }
/*     */     
/* 165 */     this.ai = nbttagcompound.getBoolean("DealtDamage");
/* 166 */     this.datawatcher.set(g, Byte.valueOf((byte)EnchantmentManager.f(this.trident)));
/*     */   }
/*     */ 
/*     */   
/*     */   public void saveData(NBTTagCompound nbttagcompound) {
/* 171 */     super.saveData(nbttagcompound);
/* 172 */     nbttagcompound.set("Trident", this.trident.save(new NBTTagCompound()));
/* 173 */     nbttagcompound.setBoolean("DealtDamage", this.ai);
/*     */   }
/*     */ 
/*     */   
/*     */   public void h() {
/* 178 */     byte b0 = ((Byte)this.datawatcher.<Byte>get(g)).byteValue();
/*     */     
/* 180 */     if (this.fromPlayer != EntityArrow.PickupStatus.ALLOWED || b0 <= 0) {
/* 181 */       super.h();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected float s() {
/* 188 */     return 0.99F;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntityThrownTrident.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
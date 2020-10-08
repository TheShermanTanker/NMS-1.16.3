/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.function.Predicate;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.entity.CraftLivingEntity;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.event.CraftEventFactory;
/*     */ import org.bukkit.entity.LivingEntity;
/*     */ import org.bukkit.event.entity.EntityPotionEffectEvent;
/*     */ import org.bukkit.event.entity.LingeringPotionSplashEvent;
/*     */ import org.bukkit.event.entity.PotionSplashEvent;
/*     */ 
/*     */ public class EntityPotion extends EntityProjectileThrowable {
/*  17 */   public static final Predicate<EntityLiving> b = EntityLiving::dN;
/*     */   
/*     */   public EntityPotion(EntityTypes<? extends EntityPotion> entitytypes, World world) {
/*  20 */     super((EntityTypes)entitytypes, world);
/*     */   }
/*     */   
/*     */   public EntityPotion(World world, EntityLiving entityliving) {
/*  24 */     super((EntityTypes)EntityTypes.POTION, entityliving, world);
/*     */   }
/*     */   
/*     */   public EntityPotion(World world, double d0, double d1, double d2) {
/*  28 */     super((EntityTypes)EntityTypes.POTION, d0, d1, d2, world);
/*     */   }
/*     */ 
/*     */   
/*     */   protected Item getDefaultItem() {
/*  33 */     return Items.SPLASH_POTION;
/*     */   }
/*     */ 
/*     */   
/*     */   protected float k() {
/*  38 */     return 0.05F;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(MovingObjectPositionBlock movingobjectpositionblock) {
/*  43 */     super.a(movingobjectpositionblock);
/*  44 */     if (!this.world.isClientSide) {
/*  45 */       ItemStack itemstack = g();
/*  46 */       PotionRegistry potionregistry = PotionUtil.d(itemstack);
/*  47 */       List<MobEffect> list = PotionUtil.getEffects(itemstack);
/*  48 */       boolean flag = (potionregistry == Potions.WATER && list.isEmpty());
/*  49 */       EnumDirection enumdirection = movingobjectpositionblock.getDirection();
/*  50 */       BlockPosition blockposition = movingobjectpositionblock.getBlockPosition();
/*  51 */       BlockPosition blockposition1 = blockposition.shift(enumdirection);
/*     */       
/*  53 */       if (flag) {
/*  54 */         a(blockposition1, enumdirection);
/*  55 */         a(blockposition1.shift(enumdirection.opposite()), enumdirection);
/*  56 */         Iterator<EnumDirection> iterator = EnumDirection.EnumDirectionLimit.HORIZONTAL.iterator();
/*     */         
/*  58 */         while (iterator.hasNext()) {
/*  59 */           EnumDirection enumdirection1 = iterator.next();
/*     */           
/*  61 */           a(blockposition1.shift(enumdirection1), enumdirection1);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void a(MovingObjectPosition movingobjectposition) {
/*  70 */     super.a(movingobjectposition);
/*  71 */     if (!this.world.isClientSide) {
/*  72 */       ItemStack itemstack = g();
/*  73 */       PotionRegistry potionregistry = PotionUtil.d(itemstack);
/*  74 */       List<MobEffect> list = PotionUtil.getEffects(itemstack);
/*  75 */       boolean flag = (potionregistry == Potions.WATER && list.isEmpty());
/*     */       
/*  77 */       if (flag) {
/*  78 */         splash();
/*     */       }
/*  80 */       else if (isLingering()) {
/*  81 */         a(itemstack, potionregistry);
/*     */       } else {
/*  83 */         a(list, (movingobjectposition.getType() == MovingObjectPosition.EnumMovingObjectType.ENTITY) ? ((MovingObjectPositionEntity)movingobjectposition).getEntity() : null);
/*     */       } 
/*     */ 
/*     */       
/*  87 */       int i = potionregistry.b() ? 2007 : 2002;
/*     */       
/*  89 */       this.world.triggerEffect(i, getChunkCoordinates(), PotionUtil.c(itemstack));
/*  90 */       die();
/*     */     } 
/*     */   }
/*     */   
/*     */   private void splash() {
/*  95 */     AxisAlignedBB axisalignedbb = getBoundingBox().grow(4.0D, 2.0D, 4.0D);
/*  96 */     List<EntityLiving> list = this.world.a(EntityLiving.class, axisalignedbb, b);
/*     */     
/*  98 */     if (!list.isEmpty()) {
/*  99 */       Iterator<EntityLiving> iterator = list.iterator();
/*     */       
/* 101 */       while (iterator.hasNext()) {
/* 102 */         EntityLiving entityliving = iterator.next();
/* 103 */         double d0 = h(entityliving);
/*     */         
/* 105 */         if (d0 < 16.0D && entityliving.dN()) {
/* 106 */           entityliving.damageEntity(DamageSource.c(entityliving, getShooter()), 1.0F);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void a(List<MobEffect> list, @Nullable Entity entity) {
/* 114 */     AxisAlignedBB axisalignedbb = getBoundingBox().grow(4.0D, 2.0D, 4.0D);
/* 115 */     List<EntityLiving> list1 = this.world.a(EntityLiving.class, axisalignedbb);
/* 116 */     Map<LivingEntity, Double> affected = new HashMap<>();
/*     */     
/* 118 */     if (!list1.isEmpty()) {
/* 119 */       Iterator<EntityLiving> iterator = list1.iterator();
/*     */       
/* 121 */       while (iterator.hasNext()) {
/* 122 */         EntityLiving entityliving = iterator.next();
/*     */         
/* 124 */         if (entityliving.eg()) {
/* 125 */           double d0 = h(entityliving);
/*     */           
/* 127 */           if (d0 < 16.0D) {
/* 128 */             double d1 = 1.0D - Math.sqrt(d0) / 4.0D;
/*     */             
/* 130 */             if (entityliving == entity) {
/* 131 */               d1 = 1.0D;
/*     */             }
/*     */ 
/*     */             
/* 135 */             affected.put((LivingEntity)entityliving.getBukkitEntity(), Double.valueOf(d1));
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 141 */     PotionSplashEvent event = CraftEventFactory.callPotionSplashEvent(this, affected);
/* 142 */     if (!event.isCancelled() && list != null && !list.isEmpty()) {
/* 143 */       for (LivingEntity victim : event.getAffectedEntities()) {
/* 144 */         if (!(victim instanceof CraftLivingEntity)) {
/*     */           continue;
/*     */         }
/*     */         
/* 148 */         EntityLiving entityliving = ((CraftLivingEntity)victim).getHandle();
/* 149 */         double d1 = event.getIntensity(victim);
/*     */ 
/*     */         
/* 152 */         Iterator<MobEffect> iterator1 = list.iterator();
/*     */         
/* 154 */         while (iterator1.hasNext()) {
/* 155 */           MobEffect mobeffect = iterator1.next();
/* 156 */           MobEffectList mobeffectlist = mobeffect.getMobEffect();
/*     */           
/* 158 */           if (!this.world.pvpMode && getShooter() instanceof EntityPlayer && entityliving instanceof EntityPlayer && entityliving != getShooter()) {
/* 159 */             int j = MobEffectList.getId(mobeffectlist);
/*     */             
/* 161 */             if (j == 2 || j == 4 || j == 7 || j == 15 || j == 17 || j == 18 || j == 19) {
/*     */               continue;
/*     */             }
/*     */           } 
/*     */ 
/*     */           
/* 167 */           if (mobeffectlist.isInstant()) {
/* 168 */             mobeffectlist.applyInstantEffect(this, getShooter(), entityliving, mobeffect.getAmplifier(), d1); continue;
/*     */           } 
/* 170 */           int i = (int)(d1 * mobeffect.getDuration() + 0.5D);
/*     */           
/* 172 */           if (i > 20) {
/* 173 */             entityliving.addEffect(new MobEffect(mobeffectlist, i, mobeffect.getAmplifier(), mobeffect.isAmbient(), mobeffect.isShowParticles()), EntityPotionEffectEvent.Cause.POTION_SPLASH);
/*     */           }
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void a(ItemStack itemstack, PotionRegistry potionregistry) {
/* 183 */     EntityAreaEffectCloud entityareaeffectcloud = new EntityAreaEffectCloud(this.world, locX(), locY(), locZ());
/* 184 */     Entity entity = getShooter();
/*     */     
/* 186 */     if (entity instanceof EntityLiving) {
/* 187 */       entityareaeffectcloud.setSource((EntityLiving)entity);
/*     */     }
/*     */     
/* 190 */     entityareaeffectcloud.setRadius(3.0F);
/* 191 */     entityareaeffectcloud.setRadiusOnUse(-0.5F);
/* 192 */     entityareaeffectcloud.setWaitTime(10);
/* 193 */     entityareaeffectcloud.setRadiusPerTick(-entityareaeffectcloud.getRadius() / entityareaeffectcloud.getDuration());
/* 194 */     entityareaeffectcloud.a(potionregistry);
/* 195 */     Iterator<MobEffect> iterator = PotionUtil.b(itemstack).iterator();
/*     */     
/* 197 */     while (iterator.hasNext()) {
/* 198 */       MobEffect mobeffect = iterator.next();
/*     */       
/* 200 */       entityareaeffectcloud.addEffect(new MobEffect(mobeffect));
/*     */     } 
/*     */     
/* 203 */     NBTTagCompound nbttagcompound = itemstack.getTag();
/*     */     
/* 205 */     if (nbttagcompound != null && nbttagcompound.hasKeyOfType("CustomPotionColor", 99)) {
/* 206 */       entityareaeffectcloud.setColor(nbttagcompound.getInt("CustomPotionColor"));
/*     */     }
/*     */ 
/*     */     
/* 210 */     LingeringPotionSplashEvent event = CraftEventFactory.callLingeringPotionSplashEvent(this, entityareaeffectcloud);
/* 211 */     if (!event.isCancelled() && !entityareaeffectcloud.dead) {
/* 212 */       this.world.addEntity(entityareaeffectcloud);
/*     */     } else {
/* 214 */       entityareaeffectcloud.dead = true;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isLingering() {
/* 220 */     return (g().getItem() == Items.LINGERING_POTION);
/*     */   }
/*     */   
/*     */   private void a(BlockPosition blockposition, EnumDirection enumdirection) {
/* 224 */     IBlockData iblockdata = this.world.getType(blockposition);
/*     */     
/* 226 */     if (iblockdata.a(TagsBlock.FIRE)) {
/*     */       
/* 228 */       if (!CraftEventFactory.callEntityChangeBlockEvent(this, blockposition, Blocks.AIR.getBlockData()).isCancelled()) {
/* 229 */         this.world.a(blockposition, false);
/*     */       }
/*     */     }
/* 232 */     else if (BlockCampfire.g(iblockdata)) {
/*     */       
/* 234 */       if (!CraftEventFactory.callEntityChangeBlockEvent(this, blockposition, iblockdata.set(BlockCampfire.b, Boolean.valueOf(false))).isCancelled()) {
/* 235 */         this.world.a((EntityHuman)null, 1009, blockposition, 0);
/* 236 */         BlockCampfire.c(this.world, blockposition, iblockdata);
/* 237 */         this.world.setTypeUpdate(blockposition, iblockdata.set(BlockCampfire.b, Boolean.valueOf(false)));
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntityPotion.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
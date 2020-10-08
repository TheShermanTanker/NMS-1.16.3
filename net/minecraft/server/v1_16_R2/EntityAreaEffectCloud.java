/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Maps;
/*     */ import com.mojang.brigadier.StringReader;
/*     */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.UUID;
/*     */ import javax.annotation.Nullable;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.entity.CraftLivingEntity;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.event.CraftEventFactory;
/*     */ import org.bukkit.entity.LivingEntity;
/*     */ import org.bukkit.event.entity.AreaEffectCloudApplyEvent;
/*     */ import org.bukkit.event.entity.EntityPotionEffectEvent;
/*     */ 
/*     */ public class EntityAreaEffectCloud
/*     */   extends Entity {
/*  23 */   private static final Logger LOGGER = LogManager.getLogger();
/*  24 */   private static final DataWatcherObject<Float> c = DataWatcher.a((Class)EntityAreaEffectCloud.class, DataWatcherRegistry.c);
/*  25 */   private static final DataWatcherObject<Integer> COLOR = DataWatcher.a((Class)EntityAreaEffectCloud.class, DataWatcherRegistry.b);
/*  26 */   private static final DataWatcherObject<Boolean> e = DataWatcher.a((Class)EntityAreaEffectCloud.class, DataWatcherRegistry.i);
/*  27 */   private static final DataWatcherObject<ParticleParam> f = DataWatcher.a((Class)EntityAreaEffectCloud.class, DataWatcherRegistry.j);
/*     */   private PotionRegistry potionRegistry;
/*     */   public List<MobEffect> effects;
/*     */   private final Map<Entity, Integer> affectedEntities;
/*     */   private int duration;
/*     */   public int waitTime;
/*     */   public int reapplicationDelay;
/*     */   private boolean hasColor;
/*     */   public int durationOnUse;
/*     */   public float radiusOnUse;
/*     */   public float radiusPerTick;
/*     */   private EntityLiving ap;
/*     */   private UUID aq;
/*     */   
/*     */   public EntityAreaEffectCloud(EntityTypes<? extends EntityAreaEffectCloud> entitytypes, World world) {
/*  42 */     super(entitytypes, world);
/*  43 */     this.potionRegistry = Potions.EMPTY;
/*  44 */     this.effects = Lists.newArrayList();
/*  45 */     this.affectedEntities = Maps.newHashMap();
/*  46 */     this.duration = 600;
/*  47 */     this.waitTime = 20;
/*  48 */     this.reapplicationDelay = 20;
/*  49 */     this.noclip = true;
/*  50 */     setRadius(3.0F);
/*     */   }
/*     */   
/*     */   public EntityAreaEffectCloud(World world, double d0, double d1, double d2) {
/*  54 */     this(EntityTypes.AREA_EFFECT_CLOUD, world);
/*  55 */     setPosition(d0, d1, d2);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initDatawatcher() {
/*  60 */     getDataWatcher().register(COLOR, Integer.valueOf(0));
/*  61 */     getDataWatcher().register(c, Float.valueOf(0.5F));
/*  62 */     getDataWatcher().register(e, Boolean.valueOf(false));
/*  63 */     getDataWatcher().register(f, Particles.ENTITY_EFFECT);
/*     */   }
/*     */   
/*     */   public void setRadius(float f) {
/*  67 */     if (!this.world.isClientSide) {
/*  68 */       getDataWatcher().set(c, Float.valueOf(f));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateSize() {
/*  75 */     double d0 = locX();
/*  76 */     double d1 = locY();
/*  77 */     double d2 = locZ();
/*     */     
/*  79 */     super.updateSize();
/*  80 */     setPosition(d0, d1, d2);
/*     */   }
/*     */   
/*     */   public float getRadius() {
/*  84 */     return ((Float)getDataWatcher().<Float>get(c)).floatValue();
/*     */   }
/*     */   
/*     */   public void a(PotionRegistry potionregistry) {
/*  88 */     this.potionRegistry = potionregistry;
/*  89 */     if (!this.hasColor) {
/*  90 */       x();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private void x() {
/*  96 */     if (this.potionRegistry == Potions.EMPTY && this.effects.isEmpty()) {
/*  97 */       getDataWatcher().set(COLOR, Integer.valueOf(0));
/*     */     } else {
/*  99 */       getDataWatcher().set(COLOR, Integer.valueOf(PotionUtil.a(PotionUtil.a(this.potionRegistry, this.effects))));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void addEffect(MobEffect mobeffect) {
/* 105 */     this.effects.add(mobeffect);
/* 106 */     if (!this.hasColor) {
/* 107 */       x();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void refreshEffects() {
/* 114 */     if (!this.hasColor) {
/* 115 */       getDataWatcher().set(COLOR, Integer.valueOf(PotionUtil.a(PotionUtil.a(this.potionRegistry, this.effects))));
/*     */     }
/*     */   }
/*     */   
/*     */   public String getType() {
/* 120 */     return IRegistry.POTION.getKey(this.potionRegistry).toString();
/*     */   }
/*     */   
/*     */   public void setType(String string) {
/* 124 */     a(IRegistry.POTION.get(new MinecraftKey(string)));
/*     */   }
/*     */ 
/*     */   
/*     */   public int getColor() {
/* 129 */     return ((Integer)getDataWatcher().<Integer>get(COLOR)).intValue();
/*     */   }
/*     */   
/*     */   public void setColor(int i) {
/* 133 */     this.hasColor = true;
/* 134 */     getDataWatcher().set(COLOR, Integer.valueOf(i));
/*     */   }
/*     */   
/*     */   public ParticleParam getParticle() {
/* 138 */     return getDataWatcher().<ParticleParam>get(f);
/*     */   }
/*     */   
/*     */   public void setParticle(ParticleParam particleparam) {
/* 142 */     getDataWatcher().set(f, particleparam);
/*     */   }
/*     */   
/*     */   protected void a(boolean flag) {
/* 146 */     getDataWatcher().set(e, Boolean.valueOf(flag));
/*     */   }
/*     */   
/*     */   public boolean k() {
/* 150 */     return ((Boolean)getDataWatcher().<Boolean>get(e)).booleanValue();
/*     */   }
/*     */   
/*     */   public int getDuration() {
/* 154 */     return this.duration;
/*     */   }
/*     */   
/*     */   public void setDuration(int i) {
/* 158 */     this.duration = i;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void inactiveTick() {
/* 164 */     super.inactiveTick();
/*     */     
/* 166 */     if (this.ticksLived >= this.waitTime + this.duration) {
/* 167 */       die();
/*     */       return;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void tick() {
/* 175 */     super.tick();
/* 176 */     boolean flag = k();
/* 177 */     float f = getRadius();
/*     */     
/* 179 */     if (f < 0.0F) {
/* 180 */       die();
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 185 */     if (this.world.isClientSide) {
/* 186 */       ParticleParam particleparam = getParticle();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 194 */       if (flag) {
/* 195 */         if (this.random.nextBoolean()) {
/* 196 */           for (int l = 0; l < 2; l++) {
/* 197 */             float f4 = this.random.nextFloat() * 6.2831855F;
/*     */             
/* 199 */             float f1 = MathHelper.c(this.random.nextFloat()) * 0.2F;
/* 200 */             float f2 = MathHelper.cos(f4) * f1;
/* 201 */             float f3 = MathHelper.sin(f4) * f1;
/* 202 */             if (particleparam.getParticle() == Particles.ENTITY_EFFECT) {
/* 203 */               int i1 = this.random.nextBoolean() ? 16777215 : getColor();
/*     */               
/* 205 */               int i = i1 >> 16 & 0xFF;
/* 206 */               int j = i1 >> 8 & 0xFF;
/* 207 */               int k = i1 & 0xFF;
/* 208 */               this.world.b(particleparam, locX() + f2, locY(), locZ() + f3, (i / 255.0F), (j / 255.0F), (k / 255.0F));
/*     */             } else {
/* 210 */               this.world.b(particleparam, locX() + f2, locY(), locZ() + f3, 0.0D, 0.0D, 0.0D);
/*     */             } 
/*     */           } 
/*     */         }
/*     */       } else {
/* 215 */         float f5 = 3.1415927F * f * f;
/*     */         
/* 217 */         for (int j1 = 0; j1 < f5; j1++) {
/* 218 */           float f1 = this.random.nextFloat() * 6.2831855F;
/* 219 */           float f2 = MathHelper.c(this.random.nextFloat()) * f;
/* 220 */           float f3 = MathHelper.cos(f1) * f2;
/* 221 */           float f6 = MathHelper.sin(f1) * f2;
/*     */           
/* 223 */           if (particleparam.getParticle() == Particles.ENTITY_EFFECT) {
/* 224 */             int i = getColor();
/* 225 */             int j = i >> 16 & 0xFF;
/* 226 */             int k = i >> 8 & 0xFF;
/* 227 */             int k1 = i & 0xFF;
/*     */             
/* 229 */             this.world.b(particleparam, locX() + f3, locY(), locZ() + f6, (j / 255.0F), (k / 255.0F), (k1 / 255.0F));
/*     */           } else {
/* 231 */             this.world.b(particleparam, locX() + f3, locY(), locZ() + f6, (0.5D - this.random.nextDouble()) * 0.15D, 0.009999999776482582D, (0.5D - this.random.nextDouble()) * 0.15D);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } else {
/* 236 */       if (this.ticksLived >= this.waitTime + this.duration) {
/* 237 */         die();
/*     */         
/*     */         return;
/*     */       } 
/* 241 */       boolean flag1 = (this.ticksLived < this.waitTime);
/*     */       
/* 243 */       if (flag != flag1) {
/* 244 */         a(flag1);
/*     */       }
/*     */       
/* 247 */       if (flag1) {
/*     */         return;
/*     */       }
/*     */       
/* 251 */       if (this.radiusPerTick != 0.0F) {
/* 252 */         f += this.radiusPerTick;
/* 253 */         if (f < 0.5F) {
/* 254 */           die();
/*     */           
/*     */           return;
/*     */         } 
/* 258 */         setRadius(f);
/*     */       } 
/*     */       
/* 261 */       if (this.ticksLived % 5 == 0) {
/* 262 */         Iterator<Map.Entry<Entity, Integer>> iterator = this.affectedEntities.entrySet().iterator();
/*     */         
/* 264 */         while (iterator.hasNext()) {
/* 265 */           Map.Entry<Entity, Integer> entry = iterator.next();
/*     */           
/* 267 */           if (this.ticksLived >= ((Integer)entry.getValue()).intValue()) {
/* 268 */             iterator.remove();
/*     */           }
/*     */         } 
/*     */         
/* 272 */         List<MobEffect> list = Lists.newArrayList();
/* 273 */         Iterator<MobEffect> iterator1 = this.potionRegistry.a().iterator();
/*     */         
/* 275 */         while (iterator1.hasNext()) {
/* 276 */           MobEffect mobeffect = iterator1.next();
/*     */           
/* 278 */           list.add(new MobEffect(mobeffect.getMobEffect(), mobeffect.getDuration() / 4, mobeffect.getAmplifier(), mobeffect.isAmbient(), mobeffect.isShowParticles()));
/*     */         } 
/*     */         
/* 281 */         list.addAll(this.effects);
/* 282 */         if (list.isEmpty()) {
/* 283 */           this.affectedEntities.clear();
/*     */         } else {
/* 285 */           List<EntityLiving> list1 = this.world.a(EntityLiving.class, getBoundingBox());
/*     */           
/* 287 */           if (!list1.isEmpty()) {
/* 288 */             Iterator<EntityLiving> iterator2 = list1.iterator();
/*     */             
/* 290 */             List<LivingEntity> entities = new ArrayList<>();
/* 291 */             while (iterator2.hasNext()) {
/* 292 */               EntityLiving entityliving = iterator2.next();
/*     */               
/* 294 */               if (!this.affectedEntities.containsKey(entityliving) && entityliving.eg()) {
/* 295 */                 double d0 = entityliving.locX() - locX();
/* 296 */                 double d1 = entityliving.locZ() - locZ();
/* 297 */                 double d2 = d0 * d0 + d1 * d1;
/*     */                 
/* 299 */                 if (d2 <= (f * f))
/*     */                 {
/* 301 */                   entities.add((LivingEntity)entityliving.getBukkitEntity());
/*     */                 }
/*     */               } 
/*     */             } 
/* 305 */             AreaEffectCloudApplyEvent event = CraftEventFactory.callAreaEffectCloudApplyEvent(this, entities);
/* 306 */             if (!event.isCancelled()) {
/* 307 */               for (LivingEntity entity : event.getAffectedEntities()) {
/* 308 */                 if (entity instanceof CraftLivingEntity) {
/* 309 */                   EntityLiving entityliving = ((CraftLivingEntity)entity).getHandle();
/*     */                   
/* 311 */                   this.affectedEntities.put(entityliving, Integer.valueOf(this.ticksLived + this.reapplicationDelay));
/* 312 */                   Iterator<MobEffect> iterator3 = list.iterator();
/*     */                   
/* 314 */                   while (iterator3.hasNext()) {
/* 315 */                     MobEffect mobeffect1 = iterator3.next();
/*     */                     
/* 317 */                     if (mobeffect1.getMobEffect().isInstant()) {
/* 318 */                       mobeffect1.getMobEffect().applyInstantEffect(this, getSource(), entityliving, mobeffect1.getAmplifier(), 0.5D); continue;
/*     */                     } 
/* 320 */                     entityliving.addEffect(new MobEffect(mobeffect1), EntityPotionEffectEvent.Cause.AREA_EFFECT_CLOUD);
/*     */                   } 
/*     */ 
/*     */                   
/* 324 */                   if (this.radiusOnUse != 0.0F) {
/* 325 */                     f += this.radiusOnUse;
/* 326 */                     if (f < 0.5F) {
/* 327 */                       die();
/*     */                       
/*     */                       return;
/*     */                     } 
/* 331 */                     setRadius(f);
/*     */                   } 
/*     */                   
/* 334 */                   if (this.durationOnUse != 0) {
/* 335 */                     this.duration += this.durationOnUse;
/* 336 */                     if (this.duration <= 0) {
/* 337 */                       die();
/*     */                       return;
/*     */                     } 
/*     */                   } 
/*     */                 } 
/*     */               } 
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRadiusOnUse(float f) {
/* 352 */     this.radiusOnUse = f;
/*     */   }
/*     */   
/*     */   public void setRadiusPerTick(float f) {
/* 356 */     this.radiusPerTick = f;
/*     */   }
/*     */   
/*     */   public void setWaitTime(int i) {
/* 360 */     this.waitTime = i;
/*     */   }
/*     */   
/*     */   public void setSource(@Nullable EntityLiving entityliving) {
/* 364 */     this.ap = entityliving;
/* 365 */     this.aq = (entityliving == null) ? null : entityliving.getUniqueID();
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public EntityLiving getSource() {
/* 370 */     if (this.ap == null && this.aq != null && this.world instanceof WorldServer) {
/* 371 */       Entity entity = ((WorldServer)this.world).getEntity(this.aq);
/*     */       
/* 373 */       if (entity instanceof EntityLiving) {
/* 374 */         this.ap = (EntityLiving)entity;
/*     */       }
/*     */     } 
/*     */     
/* 378 */     return this.ap;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void loadData(NBTTagCompound nbttagcompound) {
/* 383 */     this.ticksLived = nbttagcompound.getInt("Age");
/* 384 */     this.duration = nbttagcompound.getInt("Duration");
/* 385 */     this.waitTime = nbttagcompound.getInt("WaitTime");
/* 386 */     this.reapplicationDelay = nbttagcompound.getInt("ReapplicationDelay");
/* 387 */     this.durationOnUse = nbttagcompound.getInt("DurationOnUse");
/* 388 */     this.radiusOnUse = nbttagcompound.getFloat("RadiusOnUse");
/* 389 */     this.radiusPerTick = nbttagcompound.getFloat("RadiusPerTick");
/* 390 */     setRadius(nbttagcompound.getFloat("Radius"));
/* 391 */     if (nbttagcompound.b("Owner")) {
/* 392 */       this.aq = nbttagcompound.a("Owner");
/*     */     }
/*     */     
/* 395 */     if (nbttagcompound.hasKeyOfType("Particle", 8)) {
/*     */       try {
/* 397 */         setParticle(ArgumentParticle.b(new StringReader(nbttagcompound.getString("Particle"))));
/* 398 */       } catch (CommandSyntaxException commandsyntaxexception) {
/* 399 */         LOGGER.warn("Couldn't load custom particle {}", nbttagcompound.getString("Particle"), commandsyntaxexception);
/*     */       } 
/*     */     }
/*     */     
/* 403 */     if (nbttagcompound.hasKeyOfType("Color", 99)) {
/* 404 */       setColor(nbttagcompound.getInt("Color"));
/*     */     }
/*     */     
/* 407 */     if (nbttagcompound.hasKeyOfType("Potion", 8)) {
/* 408 */       a(PotionUtil.c(nbttagcompound));
/*     */     }
/*     */     
/* 411 */     if (nbttagcompound.hasKeyOfType("Effects", 9)) {
/* 412 */       NBTTagList nbttaglist = nbttagcompound.getList("Effects", 10);
/*     */       
/* 414 */       this.effects.clear();
/*     */       
/* 416 */       for (int i = 0; i < nbttaglist.size(); i++) {
/* 417 */         MobEffect mobeffect = MobEffect.b(nbttaglist.getCompound(i));
/*     */         
/* 419 */         if (mobeffect != null) {
/* 420 */           addEffect(mobeffect);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void saveData(NBTTagCompound nbttagcompound) {
/* 429 */     nbttagcompound.setInt("Age", this.ticksLived);
/* 430 */     nbttagcompound.setInt("Duration", this.duration);
/* 431 */     nbttagcompound.setInt("WaitTime", this.waitTime);
/* 432 */     nbttagcompound.setInt("ReapplicationDelay", this.reapplicationDelay);
/* 433 */     nbttagcompound.setInt("DurationOnUse", this.durationOnUse);
/* 434 */     nbttagcompound.setFloat("RadiusOnUse", this.radiusOnUse);
/* 435 */     nbttagcompound.setFloat("RadiusPerTick", this.radiusPerTick);
/* 436 */     nbttagcompound.setFloat("Radius", getRadius());
/* 437 */     nbttagcompound.setString("Particle", getParticle().a());
/* 438 */     if (this.aq != null) {
/* 439 */       nbttagcompound.a("Owner", this.aq);
/*     */     }
/*     */     
/* 442 */     if (this.hasColor) {
/* 443 */       nbttagcompound.setInt("Color", getColor());
/*     */     }
/*     */     
/* 446 */     if (this.potionRegistry != Potions.EMPTY && this.potionRegistry != null) {
/* 447 */       nbttagcompound.setString("Potion", IRegistry.POTION.getKey(this.potionRegistry).toString());
/*     */     }
/*     */     
/* 450 */     if (!this.effects.isEmpty()) {
/* 451 */       NBTTagList nbttaglist = new NBTTagList();
/* 452 */       Iterator<MobEffect> iterator = this.effects.iterator();
/*     */       
/* 454 */       while (iterator.hasNext()) {
/* 455 */         MobEffect mobeffect = iterator.next();
/*     */         
/* 457 */         nbttaglist.add(mobeffect.a(new NBTTagCompound()));
/*     */       } 
/*     */       
/* 460 */       nbttagcompound.set("Effects", nbttaglist);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(DataWatcherObject<?> datawatcherobject) {
/* 467 */     if (c.equals(datawatcherobject)) {
/* 468 */       updateSize();
/*     */     }
/*     */     
/* 471 */     super.a(datawatcherobject);
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumPistonReaction getPushReaction() {
/* 476 */     return EnumPistonReaction.IGNORE;
/*     */   }
/*     */ 
/*     */   
/*     */   public Packet<?> P() {
/* 481 */     return new PacketPlayOutSpawnEntity(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public EntitySize a(EntityPose entitypose) {
/* 486 */     return EntitySize.b(getRadius() * 2.0F, 0.5F);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntityAreaEffectCloud.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
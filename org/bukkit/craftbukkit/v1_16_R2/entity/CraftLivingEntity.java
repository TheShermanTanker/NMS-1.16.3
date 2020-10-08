/*     */ package org.bukkit.craftbukkit.v1_16_R2.entity;
/*     */ import com.destroystokyo.paper.block.TargetBlockInfo;
/*     */ import com.destroystokyo.paper.entity.TargetEntityInfo;
/*     */ import com.google.common.base.Preconditions;
/*     */ import com.google.common.collect.Sets;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import net.minecraft.server.v1_16_R2.DamageSource;
/*     */ import net.minecraft.server.v1_16_R2.Entity;
/*     */ import net.minecraft.server.v1_16_R2.EntityArrow;
/*     */ import net.minecraft.server.v1_16_R2.EntityDragonFireball;
/*     */ import net.minecraft.server.v1_16_R2.EntityEgg;
/*     */ import net.minecraft.server.v1_16_R2.EntityEnderPearl;
/*     */ import net.minecraft.server.v1_16_R2.EntityFireball;
/*     */ import net.minecraft.server.v1_16_R2.EntityFireworks;
/*     */ import net.minecraft.server.v1_16_R2.EntityFishingHook;
/*     */ import net.minecraft.server.v1_16_R2.EntityHuman;
/*     */ import net.minecraft.server.v1_16_R2.EntityInsentient;
/*     */ import net.minecraft.server.v1_16_R2.EntityLargeFireball;
/*     */ import net.minecraft.server.v1_16_R2.EntityLiving;
/*     */ import net.minecraft.server.v1_16_R2.EntityLlamaSpit;
/*     */ import net.minecraft.server.v1_16_R2.EntityPlayer;
/*     */ import net.minecraft.server.v1_16_R2.EntityPotion;
/*     */ import net.minecraft.server.v1_16_R2.EntityProjectile;
/*     */ import net.minecraft.server.v1_16_R2.EntityShulkerBullet;
/*     */ import net.minecraft.server.v1_16_R2.EntitySmallFireball;
/*     */ import net.minecraft.server.v1_16_R2.EntitySnowball;
/*     */ import net.minecraft.server.v1_16_R2.EntitySpectralArrow;
/*     */ import net.minecraft.server.v1_16_R2.EntityThrownExpBottle;
/*     */ import net.minecraft.server.v1_16_R2.EntityThrownTrident;
/*     */ import net.minecraft.server.v1_16_R2.EntityTippedArrow;
/*     */ import net.minecraft.server.v1_16_R2.EntityWitherSkull;
/*     */ import net.minecraft.server.v1_16_R2.EnumHand;
/*     */ import net.minecraft.server.v1_16_R2.EnumMonsterType;
/*     */ import net.minecraft.server.v1_16_R2.GeneratorAccess;
/*     */ import net.minecraft.server.v1_16_R2.GenericAttributes;
/*     */ import net.minecraft.server.v1_16_R2.IMaterial;
/*     */ import net.minecraft.server.v1_16_R2.ItemStack;
/*     */ import net.minecraft.server.v1_16_R2.Items;
/*     */ import net.minecraft.server.v1_16_R2.MCUtil;
/*     */ import net.minecraft.server.v1_16_R2.MobEffect;
/*     */ import net.minecraft.server.v1_16_R2.MobEffectList;
/*     */ import net.minecraft.server.v1_16_R2.MovingObjectPosition;
/*     */ import net.minecraft.server.v1_16_R2.MovingObjectPositionBlock;
/*     */ import net.minecraft.server.v1_16_R2.MovingObjectPositionEntity;
/*     */ import net.minecraft.server.v1_16_R2.Vec3D;
/*     */ import net.minecraft.server.v1_16_R2.World;
/*     */ import net.minecraft.server.v1_16_R2.WorldServer;
/*     */ import org.apache.commons.lang.Validate;
/*     */ import org.bukkit.FluidCollisionMode;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.attribute.Attribute;
/*     */ import org.bukkit.attribute.AttributeInstance;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.CraftServer;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.CraftWorld;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.block.CraftBlock;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.entity.memory.CraftMemoryKey;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.entity.memory.CraftMemoryMapper;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftEntityEquipment;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftItemStack;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.potion.CraftPotionUtil;
/*     */ import org.bukkit.entity.DragonFireball;
/*     */ import org.bukkit.entity.Egg;
/*     */ import org.bukkit.entity.EnderPearl;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.entity.EntityCategory;
/*     */ import org.bukkit.entity.EntityType;
/*     */ import org.bukkit.entity.Fireball;
/*     */ import org.bukkit.entity.FishHook;
/*     */ import org.bukkit.entity.Item;
/*     */ import org.bukkit.entity.LingeringPotion;
/*     */ import org.bukkit.entity.LivingEntity;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.entity.Projectile;
/*     */ import org.bukkit.entity.ShulkerBullet;
/*     */ import org.bukkit.entity.SpectralArrow;
/*     */ import org.bukkit.entity.ThrownPotion;
/*     */ import org.bukkit.entity.TippedArrow;
/*     */ import org.bukkit.entity.WitherSkull;
/*     */ import org.bukkit.entity.memory.MemoryKey;
/*     */ import org.bukkit.event.entity.EntityPotionEffectEvent;
/*     */ import org.bukkit.event.player.PlayerTeleportEvent;
/*     */ import org.bukkit.inventory.EntityEquipment;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.potion.PotionData;
/*     */ import org.bukkit.potion.PotionEffect;
/*     */ import org.bukkit.potion.PotionEffectType;
/*     */ import org.bukkit.util.BlockIterator;
/*     */ import org.bukkit.util.RayTraceResult;
/*     */ import org.bukkit.util.Vector;
/*     */ 
/*     */ public class CraftLivingEntity extends CraftEntity implements LivingEntity {
/*     */   public CraftLivingEntity(CraftServer server, EntityLiving entity) {
/*  98 */     super(server, (Entity)entity);
/*     */     
/* 100 */     if (entity instanceof EntityInsentient || entity instanceof net.minecraft.server.v1_16_R2.EntityArmorStand)
/* 101 */       this.equipment = new CraftEntityEquipment(this); 
/*     */   }
/*     */   
/*     */   private CraftEntityEquipment equipment;
/*     */   
/*     */   public double getHealth() {
/* 107 */     return Math.min(Math.max(0.0F, getHandle().getHealth()), getMaxHealth());
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHealth(double health) {
/* 112 */     health = (float)health;
/* 113 */     if (health < 0.0D || health > getMaxHealth())
/*     */     {
/* 115 */       throw new IllegalArgumentException("Health must be between 0 and " + getMaxHealth() + ", but was " + health + ". (attribute base value: " + 
/* 116 */           getHandle().getAttributeInstance(GenericAttributes.MAX_HEALTH).getBaseValue() + (
/* 117 */           (this instanceof CraftPlayer) ? (", player: " + getName() + ')') : Character.valueOf(')')));
/*     */     }
/*     */     
/* 120 */     getHandle().setHealth((float)health);
/*     */     
/* 122 */     if (health == 0.0D) {
/* 123 */       getHandle().die(DamageSource.GENERIC);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public double getAbsorptionAmount() {
/* 129 */     return getHandle().getAbsorptionHearts();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAbsorptionAmount(double amount) {
/* 134 */     Preconditions.checkArgument((amount >= 0.0D && Double.isFinite(amount)), "amount < 0 or non-finite");
/*     */     
/* 136 */     getHandle().setAbsorptionHearts((float)amount);
/*     */   }
/*     */ 
/*     */   
/*     */   public double getMaxHealth() {
/* 141 */     return getHandle().getMaxHealth();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMaxHealth(double amount) {
/* 146 */     Validate.isTrue((amount > 0.0D), "Max health must be greater than 0");
/*     */     
/* 148 */     getHandle().getAttributeInstance(GenericAttributes.MAX_HEALTH).setValue(amount);
/*     */     
/* 150 */     if (getHealth() > amount) {
/* 151 */       setHealth(amount);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void resetMaxHealth() {
/* 157 */     setMaxHealth(getHandle().getAttributeInstance(GenericAttributes.MAX_HEALTH).getAttribute().getDefault());
/*     */   }
/*     */ 
/*     */   
/*     */   public double getEyeHeight() {
/* 162 */     return getHandle().getHeadHeight();
/*     */   }
/*     */ 
/*     */   
/*     */   public double getEyeHeight(boolean ignorePose) {
/* 167 */     return getEyeHeight();
/*     */   }
/*     */   
/*     */   private List<Block> getLineOfSight(Set<Material> transparent, int maxDistance, int maxLength) {
/* 171 */     if (transparent == null) {
/* 172 */       transparent = Sets.newHashSet((Object[])new Material[] { Material.AIR, Material.CAVE_AIR, Material.VOID_AIR });
/*     */     }
/* 174 */     if (maxDistance > 120) {
/* 175 */       maxDistance = 120;
/*     */     }
/* 177 */     ArrayList<Block> blocks = new ArrayList<>();
/* 178 */     BlockIterator<Block> blockIterator = new BlockIterator(this, maxDistance);
/* 179 */     while (blockIterator.hasNext()) {
/* 180 */       Block block = blockIterator.next();
/* 181 */       blocks.add(block);
/* 182 */       if (maxLength != 0 && blocks.size() > maxLength) {
/* 183 */         blocks.remove(0);
/*     */       }
/* 185 */       Material material = block.getType();
/* 186 */       if (!transparent.contains(material)) {
/*     */         break;
/*     */       }
/*     */     } 
/* 190 */     return blocks;
/*     */   }
/*     */ 
/*     */   
/*     */   public List<Block> getLineOfSight(Set<Material> transparent, int maxDistance) {
/* 195 */     return getLineOfSight(transparent, maxDistance, 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public Block getTargetBlock(Set<Material> transparent, int maxDistance) {
/* 200 */     List<Block> blocks = getLineOfSight(transparent, maxDistance, 1);
/* 201 */     return blocks.get(0);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Block getTargetBlock(int maxDistance, TargetBlockInfo.FluidMode fluidMode) {
/* 207 */     MovingObjectPosition rayTrace = getHandle().getRayTrace(maxDistance, MCUtil.getNMSFluidCollisionOption(fluidMode));
/* 208 */     return !(rayTrace instanceof MovingObjectPositionBlock) ? null : (Block)CraftBlock.at((GeneratorAccess)(getHandle()).world, ((MovingObjectPositionBlock)rayTrace).getBlockPosition());
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockFace getTargetBlockFace(int maxDistance, TargetBlockInfo.FluidMode fluidMode) {
/* 213 */     MovingObjectPosition rayTrace = getHandle().getRayTrace(maxDistance, MCUtil.getNMSFluidCollisionOption(fluidMode));
/* 214 */     return !(rayTrace instanceof MovingObjectPositionBlock) ? null : MCUtil.toBukkitBlockFace(((MovingObjectPositionBlock)rayTrace).getDirection());
/*     */   }
/*     */ 
/*     */   
/*     */   public TargetBlockInfo getTargetBlockInfo(int maxDistance, TargetBlockInfo.FluidMode fluidMode) {
/* 219 */     MovingObjectPosition rayTrace = getHandle().getRayTrace(maxDistance, MCUtil.getNMSFluidCollisionOption(fluidMode));
/* 220 */     return !(rayTrace instanceof MovingObjectPositionBlock) ? null : 
/* 221 */       new TargetBlockInfo((Block)CraftBlock.at((GeneratorAccess)(getHandle()).world, ((MovingObjectPositionBlock)rayTrace).getBlockPosition()), 
/* 222 */         MCUtil.toBukkitBlockFace(((MovingObjectPositionBlock)rayTrace).getDirection()));
/*     */   }
/*     */   
/*     */   public Entity getTargetEntity(int maxDistance, boolean ignoreBlocks) {
/* 226 */     MovingObjectPositionEntity rayTrace = rayTraceEntity(maxDistance, ignoreBlocks);
/* 227 */     return (rayTrace == null) ? null : rayTrace.getEntity().getBukkitEntity();
/*     */   }
/*     */   
/*     */   public TargetEntityInfo getTargetEntityInfo(int maxDistance, boolean ignoreBlocks) {
/* 231 */     MovingObjectPositionEntity rayTrace = rayTraceEntity(maxDistance, ignoreBlocks);
/* 232 */     return (rayTrace == null) ? null : new TargetEntityInfo(rayTrace.getEntity().getBukkitEntity(), new Vector((rayTrace.getPos()).x, (rayTrace.getPos()).y, (rayTrace.getPos()).z));
/*     */   }
/*     */   
/*     */   public MovingObjectPositionEntity rayTraceEntity(int maxDistance, boolean ignoreBlocks) {
/* 236 */     MovingObjectPositionEntity rayTrace = getHandle().getTargetEntity(maxDistance);
/* 237 */     if (rayTrace == null) {
/* 238 */       return null;
/*     */     }
/* 240 */     if (!ignoreBlocks) {
/* 241 */       MovingObjectPosition rayTraceBlocks = getHandle().getRayTrace(maxDistance, RayTrace.FluidCollisionOption.NONE);
/* 242 */       if (rayTraceBlocks != null) {
/* 243 */         Vec3D eye = getHandle().getEyePosition(1.0F);
/* 244 */         if (eye.distanceSquared(rayTraceBlocks.getPos()) <= eye.distanceSquared(rayTrace.getPos())) {
/* 245 */           return null;
/*     */         }
/*     */       } 
/*     */     } 
/* 249 */     return rayTrace;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public List<Block> getLastTwoTargetBlocks(Set<Material> transparent, int maxDistance) {
/* 255 */     return getLineOfSight(transparent, maxDistance, 2);
/*     */   }
/*     */ 
/*     */   
/*     */   public Block getTargetBlockExact(int maxDistance) {
/* 260 */     return getTargetBlockExact(maxDistance, FluidCollisionMode.NEVER);
/*     */   }
/*     */ 
/*     */   
/*     */   public Block getTargetBlockExact(int maxDistance, FluidCollisionMode fluidCollisionMode) {
/* 265 */     RayTraceResult hitResult = rayTraceBlocks(maxDistance, fluidCollisionMode);
/* 266 */     return (hitResult != null) ? hitResult.getHitBlock() : null;
/*     */   }
/*     */ 
/*     */   
/*     */   public RayTraceResult rayTraceBlocks(double maxDistance) {
/* 271 */     return rayTraceBlocks(maxDistance, FluidCollisionMode.NEVER);
/*     */   }
/*     */ 
/*     */   
/*     */   public RayTraceResult rayTraceBlocks(double maxDistance, FluidCollisionMode fluidCollisionMode) {
/* 276 */     Location eyeLocation = getEyeLocation();
/* 277 */     Vector direction = eyeLocation.getDirection();
/* 278 */     return getWorld().rayTraceBlocks(eyeLocation, direction, maxDistance, fluidCollisionMode, false);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getRemainingAir() {
/* 283 */     return getHandle().getAirTicks();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRemainingAir(int ticks) {
/* 288 */     getHandle().setAirTicks(ticks);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMaximumAir() {
/* 293 */     return (getHandle()).maxAirTicks;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMaximumAir(int ticks) {
/* 298 */     (getHandle()).maxAirTicks = ticks;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getArrowCooldown() {
/* 303 */     return (getHandle()).arrowCooldown;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setArrowCooldown(int ticks) {
/* 308 */     (getHandle()).arrowCooldown = ticks;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getArrowsInBody() {
/* 313 */     return getHandle().getArrowCount();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setArrowsInBody(int count) {
/* 318 */     Preconditions.checkArgument((count >= 0), "New arrow amount must be >= 0");
/* 319 */     getHandle().getDataWatcher().set(EntityLiving.ARROWS_IN_BODY, Integer.valueOf(count));
/*     */   }
/*     */ 
/*     */   
/*     */   public void damage(double amount) {
/* 324 */     damage(amount, (Entity)null);
/*     */   }
/*     */ 
/*     */   
/*     */   public void damage(double amount, Entity source) {
/* 329 */     DamageSource reason = DamageSource.GENERIC;
/*     */     
/* 331 */     if (source instanceof org.bukkit.entity.HumanEntity) {
/* 332 */       reason = DamageSource.playerAttack(((CraftHumanEntity)source).getHandle());
/* 333 */     } else if (source instanceof LivingEntity) {
/* 334 */       reason = DamageSource.mobAttack(((CraftLivingEntity)source).getHandle());
/*     */     } 
/*     */     
/* 337 */     this.entity.damageEntity(reason, (float)amount);
/*     */   }
/*     */ 
/*     */   
/*     */   public Location getEyeLocation() {
/* 342 */     Location loc = getLocation();
/* 343 */     loc.setY(loc.getY() + getEyeHeight());
/* 344 */     return loc;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMaximumNoDamageTicks() {
/* 349 */     return (getHandle()).maxNoDamageTicks;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMaximumNoDamageTicks(int ticks) {
/* 354 */     (getHandle()).maxNoDamageTicks = ticks;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getLastDamage() {
/* 359 */     return (getHandle()).lastDamage;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setLastDamage(double damage) {
/* 364 */     (getHandle()).lastDamage = (float)damage;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getNoDamageTicks() {
/* 369 */     return (getHandle()).noDamageTicks;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setNoDamageTicks(int ticks) {
/* 374 */     (getHandle()).noDamageTicks = ticks;
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityLiving getHandle() {
/* 379 */     return (EntityLiving)this.entity;
/*     */   }
/*     */   
/*     */   public void setHandle(EntityLiving entity) {
/* 383 */     setHandle((Entity)entity);
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 388 */     return "CraftLivingEntity{id=" + getEntityId() + '}';
/*     */   }
/*     */ 
/*     */   
/*     */   public Player getKiller() {
/* 393 */     return ((getHandle()).killer == null) ? null : (Player)(getHandle()).killer.getBukkitEntity();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setKiller(Player killer) {
/* 399 */     EntityPlayer entityPlayer = (killer == null) ? null : ((CraftPlayer)killer).getHandle();
/* 400 */     (getHandle()).killer = (EntityHuman)entityPlayer;
/* 401 */     (getHandle()).lastDamager = (EntityLiving)entityPlayer;
/* 402 */     (getHandle()).lastDamageByPlayerTime = (entityPlayer == null) ? 0 : 100;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean addPotionEffect(PotionEffect effect) {
/* 408 */     return addPotionEffect(effect, false);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean addPotionEffect(PotionEffect effect, boolean force) {
/* 413 */     getHandle().addEffect(new MobEffect(MobEffectList.fromId(effect.getType().getId()), effect.getDuration(), effect.getAmplifier(), effect.isAmbient(), effect.hasParticles(), effect.hasIcon()), EntityPotionEffectEvent.Cause.PLUGIN);
/* 414 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean addPotionEffects(Collection<PotionEffect> effects) {
/* 419 */     boolean success = true;
/* 420 */     for (PotionEffect effect : effects) {
/* 421 */       success &= addPotionEffect(effect);
/*     */     }
/* 423 */     return success;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasPotionEffect(PotionEffectType type) {
/* 428 */     return getHandle().hasEffect(MobEffectList.fromId(type.getId()));
/*     */   }
/*     */ 
/*     */   
/*     */   public PotionEffect getPotionEffect(PotionEffectType type) {
/* 433 */     MobEffect handle = getHandle().getEffect(MobEffectList.fromId(type.getId()));
/* 434 */     return (handle == null) ? null : new PotionEffect(PotionEffectType.getById(MobEffectList.getId(handle.getMobEffect())), handle.getDuration(), handle.getAmplifier(), handle.isAmbient(), handle.isShowParticles());
/*     */   }
/*     */ 
/*     */   
/*     */   public void removePotionEffect(PotionEffectType type) {
/* 439 */     getHandle().removeEffect(MobEffectList.fromId(type.getId()), EntityPotionEffectEvent.Cause.PLUGIN);
/*     */   }
/*     */ 
/*     */   
/*     */   public Collection<PotionEffect> getActivePotionEffects() {
/* 444 */     List<PotionEffect> effects = new ArrayList<>();
/* 445 */     for (MobEffect handle : (getHandle()).effects.values()) {
/* 446 */       effects.add(new PotionEffect(PotionEffectType.getById(MobEffectList.getId(handle.getMobEffect())), handle.getDuration(), handle.getAmplifier(), handle.isAmbient(), handle.isShowParticles()));
/*     */     }
/* 448 */     return effects;
/*     */   }
/*     */ 
/*     */   
/*     */   public <T extends Projectile> T launchProjectile(Class<? extends T> projectile) {
/* 453 */     return launchProjectile(projectile, (Vector)null);
/*     */   }
/*     */ 
/*     */   
/*     */   public <T extends Projectile> T launchProjectile(Class<? extends T> projectile, Vector velocity) {
/*     */     EntityFireworks entityFireworks;
/* 459 */     WorldServer worldServer = ((CraftWorld)getWorld()).getHandle();
/* 460 */     Entity launch = null;
/*     */     
/* 462 */     if (Snowball.class.isAssignableFrom(projectile)) {
/* 463 */       EntitySnowball entitySnowball = new EntitySnowball((World)worldServer, getHandle());
/* 464 */       ((EntityProjectile)entitySnowball).a((Entity)getHandle(), (getHandle()).pitch, (getHandle()).yaw, 0.0F, 1.5F, 1.0F);
/* 465 */     } else if (Egg.class.isAssignableFrom(projectile)) {
/* 466 */       EntityEgg entityEgg = new EntityEgg((World)worldServer, getHandle());
/* 467 */       ((EntityProjectile)entityEgg).a((Entity)getHandle(), (getHandle()).pitch, (getHandle()).yaw, 0.0F, 1.5F, 1.0F);
/* 468 */     } else if (EnderPearl.class.isAssignableFrom(projectile)) {
/* 469 */       EntityEnderPearl entityEnderPearl = new EntityEnderPearl((World)worldServer, getHandle());
/* 470 */       ((EntityProjectile)entityEnderPearl).a((Entity)getHandle(), (getHandle()).pitch, (getHandle()).yaw, 0.0F, 1.5F, 1.0F);
/* 471 */     } else if (AbstractArrow.class.isAssignableFrom(projectile)) {
/* 472 */       EntityTippedArrow entityTippedArrow; if (TippedArrow.class.isAssignableFrom(projectile)) {
/* 473 */         entityTippedArrow = new EntityTippedArrow((World)worldServer, getHandle());
/* 474 */         entityTippedArrow.setType(CraftPotionUtil.fromBukkit(new PotionData(PotionType.WATER, false, false)));
/* 475 */       } else if (SpectralArrow.class.isAssignableFrom(projectile)) {
/* 476 */         EntitySpectralArrow entitySpectralArrow = new EntitySpectralArrow((World)worldServer, getHandle());
/* 477 */       } else if (Trident.class.isAssignableFrom(projectile)) {
/* 478 */         EntityThrownTrident entityThrownTrident = new EntityThrownTrident((World)worldServer, getHandle(), new ItemStack((IMaterial)Items.TRIDENT));
/*     */       } else {
/* 480 */         entityTippedArrow = new EntityTippedArrow((World)worldServer, getHandle());
/*     */       } 
/* 482 */       ((EntityArrow)entityTippedArrow).a((Entity)getHandle(), (getHandle()).pitch, (getHandle()).yaw, 0.0F, 3.0F, 1.0F);
/* 483 */     } else if (ThrownPotion.class.isAssignableFrom(projectile)) {
/* 484 */       EntityPotion entityPotion; if (LingeringPotion.class.isAssignableFrom(projectile)) {
/* 485 */         entityPotion = new EntityPotion((World)worldServer, getHandle());
/* 486 */         entityPotion.setItem(CraftItemStack.asNMSCopy(new ItemStack(Material.LINGERING_POTION, 1)));
/*     */       } else {
/* 488 */         entityPotion = new EntityPotion((World)worldServer, getHandle());
/* 489 */         entityPotion.setItem(CraftItemStack.asNMSCopy(new ItemStack(Material.SPLASH_POTION, 1)));
/*     */       } 
/* 491 */       ((EntityProjectile)entityPotion).a((Entity)getHandle(), (getHandle()).pitch, (getHandle()).yaw, -20.0F, 0.5F, 1.0F);
/* 492 */     } else if (ThrownExpBottle.class.isAssignableFrom(projectile)) {
/* 493 */       EntityThrownExpBottle entityThrownExpBottle = new EntityThrownExpBottle((World)worldServer, getHandle());
/* 494 */       ((EntityProjectile)entityThrownExpBottle).a((Entity)getHandle(), (getHandle()).pitch, (getHandle()).yaw, -20.0F, 0.7F, 1.0F);
/* 495 */     } else if (FishHook.class.isAssignableFrom(projectile) && getHandle() instanceof EntityHuman) {
/* 496 */       EntityFishingHook entityFishingHook = new EntityFishingHook((EntityHuman)getHandle(), (World)worldServer, 0, 0);
/* 497 */     } else if (Fireball.class.isAssignableFrom(projectile)) {
/* 498 */       EntityLargeFireball entityLargeFireball; Location location = getEyeLocation();
/* 499 */       Vector direction = location.getDirection().multiply(10);
/*     */       
/* 501 */       if (SmallFireball.class.isAssignableFrom(projectile)) {
/* 502 */         EntitySmallFireball entitySmallFireball = new EntitySmallFireball((World)worldServer, getHandle(), direction.getX(), direction.getY(), direction.getZ());
/* 503 */       } else if (WitherSkull.class.isAssignableFrom(projectile)) {
/* 504 */         EntityWitherSkull entityWitherSkull = new EntityWitherSkull((World)worldServer, getHandle(), direction.getX(), direction.getY(), direction.getZ());
/* 505 */       } else if (DragonFireball.class.isAssignableFrom(projectile)) {
/* 506 */         EntityDragonFireball entityDragonFireball = new EntityDragonFireball((World)worldServer, getHandle(), direction.getX(), direction.getY(), direction.getZ());
/*     */       } else {
/* 508 */         entityLargeFireball = new EntityLargeFireball((World)worldServer, getHandle(), direction.getX(), direction.getY(), direction.getZ());
/*     */       } 
/*     */       
/* 511 */       ((EntityFireball)entityLargeFireball).projectileSource = (ProjectileSource)this;
/* 512 */       entityLargeFireball.setPositionRotation(location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
/* 513 */     } else if (LlamaSpit.class.isAssignableFrom(projectile)) {
/* 514 */       Location location = getEyeLocation();
/* 515 */       Vector direction = location.getDirection();
/*     */       
/* 517 */       launch = EntityTypes.LLAMA_SPIT.a((World)worldServer);
/*     */       
/* 519 */       ((EntityLlamaSpit)launch).setShooter((Entity)getHandle());
/* 520 */       ((EntityLlamaSpit)launch).shoot(direction.getX(), direction.getY(), direction.getZ(), 1.5F, 10.0F);
/* 521 */       launch.setPositionRotation(location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
/* 522 */     } else if (ShulkerBullet.class.isAssignableFrom(projectile)) {
/* 523 */       Location location = getEyeLocation();
/*     */       
/* 525 */       EntityShulkerBullet entityShulkerBullet = new EntityShulkerBullet((World)worldServer, getHandle(), null, null);
/* 526 */       entityShulkerBullet.setPositionRotation(location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
/* 527 */     } else if (Firework.class.isAssignableFrom(projectile)) {
/* 528 */       Location location = getEyeLocation();
/*     */       
/* 530 */       entityFireworks = new EntityFireworks((World)worldServer, ItemStack.b, getHandle());
/* 531 */       entityFireworks.setPositionRotation(location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
/*     */     } 
/*     */     
/* 534 */     Validate.notNull(entityFireworks, "Projectile not supported");
/*     */     
/* 536 */     if (velocity != null) {
/* 537 */       ((Projectile)entityFireworks.getBukkitEntity()).setVelocity(velocity);
/*     */     }
/*     */     
/* 540 */     worldServer.addEntity((Entity)entityFireworks);
/* 541 */     return (T)entityFireworks.getBukkitEntity();
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityType getType() {
/* 546 */     return EntityType.UNKNOWN;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasLineOfSight(Entity other) {
/* 551 */     return getHandle().hasLineOfSight(((CraftEntity)other).getHandle());
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getRemoveWhenFarAway() {
/* 556 */     return (getHandle() instanceof EntityInsentient && !((EntityInsentient)getHandle()).persistent);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRemoveWhenFarAway(boolean remove) {
/* 561 */     if (getHandle() instanceof EntityInsentient) {
/* 562 */       ((EntityInsentient)getHandle()).persistent = !remove;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityEquipment getEquipment() {
/* 568 */     return (EntityEquipment)this.equipment;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCanPickupItems(boolean pickup) {
/* 573 */     (getHandle()).canPickUpLoot = pickup;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getCanPickupItems() {
/* 578 */     return (getHandle()).canPickUpLoot;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean teleport(Location location, PlayerTeleportEvent.TeleportCause cause) {
/* 583 */     if (getHealth() == 0.0D) {
/* 584 */       return false;
/*     */     }
/*     */     
/* 587 */     return super.teleport(location, cause);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isLeashed() {
/* 592 */     if (!(getHandle() instanceof EntityInsentient)) {
/* 593 */       return false;
/*     */     }
/* 595 */     return (((EntityInsentient)getHandle()).getLeashHolder() != null);
/*     */   }
/*     */ 
/*     */   
/*     */   public Entity getLeashHolder() throws IllegalStateException {
/* 600 */     if (!isLeashed()) {
/* 601 */       throw new IllegalStateException("Entity not leashed");
/*     */     }
/* 603 */     return ((EntityInsentient)getHandle()).getLeashHolder().getBukkitEntity();
/*     */   }
/*     */   
/*     */   private boolean unleash() {
/* 607 */     if (!isLeashed()) {
/* 608 */       return false;
/*     */     }
/* 610 */     ((EntityInsentient)getHandle()).unleash(true, false);
/* 611 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean setLeashHolder(Entity holder) {
/* 616 */     if (getHandle() instanceof net.minecraft.server.v1_16_R2.EntityWither || !(getHandle() instanceof EntityInsentient)) {
/* 617 */       return false;
/*     */     }
/*     */     
/* 620 */     if (holder == null) {
/* 621 */       return unleash();
/*     */     }
/*     */     
/* 624 */     if (holder.isDead()) {
/* 625 */       return false;
/*     */     }
/*     */     
/* 628 */     unleash();
/* 629 */     ((EntityInsentient)getHandle()).setLeashHolder(((CraftEntity)holder).getHandle(), true);
/* 630 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isGliding() {
/* 635 */     return getHandle().getFlag(7);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setGliding(boolean gliding) {
/* 640 */     getHandle().setFlag(7, gliding);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isSwimming() {
/* 645 */     return getHandle().isSwimming();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSwimming(boolean swimming) {
/* 650 */     getHandle().setSwimming(swimming);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isRiptiding() {
/* 655 */     return getHandle().isRiptiding();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isSleeping() {
/* 660 */     return getHandle().isSleeping();
/*     */   }
/*     */ 
/*     */   
/*     */   public AttributeInstance getAttribute(Attribute attribute) {
/* 665 */     return (getHandle()).craftAttributes.getAttribute(attribute);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAI(boolean ai) {
/* 670 */     if (getHandle() instanceof EntityInsentient) {
/* 671 */       ((EntityInsentient)getHandle()).setNoAI(!ai);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasAI() {
/* 677 */     return (getHandle() instanceof EntityInsentient) ? (!((EntityInsentient)getHandle()).isNoAI()) : false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void attack(Entity target) {
/* 682 */     Preconditions.checkArgument((target != null), "target == null");
/*     */     
/* 684 */     getHandle().attackEntity(((CraftEntity)target).getHandle());
/*     */   }
/*     */ 
/*     */   
/*     */   public void swingMainHand() {
/* 689 */     getHandle().swingHand(EnumHand.MAIN_HAND, true);
/*     */   }
/*     */ 
/*     */   
/*     */   public void swingOffHand() {
/* 694 */     getHandle().swingHand(EnumHand.OFF_HAND, true);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCollidable(boolean collidable) {
/* 699 */     (getHandle()).collides = collidable;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isCollidable() {
/* 704 */     return (getHandle()).collides;
/*     */   }
/*     */ 
/*     */   
/*     */   public Set<UUID> getCollidableExemptions() {
/* 709 */     return (getHandle()).collidableExemptions;
/*     */   }
/*     */ 
/*     */   
/*     */   public <T> T getMemory(MemoryKey<T> memoryKey) {
/* 714 */     return getHandle().getBehaviorController().getMemory(CraftMemoryKey.fromMemoryKey(memoryKey)).map(CraftMemoryMapper::fromNms).orElse(null);
/*     */   }
/*     */ 
/*     */   
/*     */   public <T> void setMemory(MemoryKey<T> memoryKey, T t) {
/* 719 */     getHandle().getBehaviorController().setMemory(CraftMemoryKey.fromMemoryKey(memoryKey), CraftMemoryMapper.toNms(t));
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityCategory getCategory() {
/* 724 */     EnumMonsterType type = getHandle().getMonsterType();
/*     */     
/* 726 */     if (type == EnumMonsterType.UNDEFINED)
/* 727 */       return EntityCategory.NONE; 
/* 728 */     if (type == EnumMonsterType.UNDEAD)
/* 729 */       return EntityCategory.UNDEAD; 
/* 730 */     if (type == EnumMonsterType.ARTHROPOD)
/* 731 */       return EntityCategory.ARTHROPOD; 
/* 732 */     if (type == EnumMonsterType.ILLAGER)
/* 733 */       return EntityCategory.ILLAGER; 
/* 734 */     if (type == EnumMonsterType.WATER_MOB) {
/* 735 */       return EntityCategory.WATER;
/*     */     }
/*     */     
/* 738 */     throw new UnsupportedOperationException("Unsupported monster type: " + type + ". This is a bug, report this to Spigot.");
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isInvisible() {
/* 743 */     return getHandle().isInvisible();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setInvisible(boolean invisible) {
/* 748 */     (getHandle()).persistentInvisibility = invisible;
/* 749 */     getHandle().setFlag(5, invisible);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getArrowsStuck() {
/* 755 */     return getHandle().getArrowCount();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setArrowsStuck(int arrows) {
/* 760 */     getHandle().setArrowCount(arrows);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getShieldBlockingDelay() {
/* 765 */     return getHandle().getShieldBlockingDelay();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setShieldBlockingDelay(int delay) {
/* 770 */     getHandle().setShieldBlockingDelay(delay);
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack getActiveItem() {
/* 775 */     return (getHandle()).activeItem.asBukkitMirror();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getItemUseRemainingTime() {
/* 780 */     return getHandle().getItemUseRemainingTime();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getHandRaisedTime() {
/* 785 */     return getHandle().getHandRaisedTime();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isHandRaised() {
/* 790 */     return getHandle().isHandRaised();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isJumping() {
/* 795 */     return (getHandle()).jumping;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setJumping(boolean jumping) {
/* 800 */     getHandle().setJumping(jumping);
/* 801 */     if (jumping && getHandle() instanceof EntityInsentient)
/*     */     {
/* 803 */       ((EntityInsentient)getHandle()).getControllerJump().jump();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void playPickupItemAnimation(Item item, int quantity) {
/* 809 */     getHandle().receive(((CraftItem)item).getHandle(), quantity);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\entity\CraftLivingEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
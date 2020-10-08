/*     */ package net.minecraft.server.v1_16_R2;
/*     */ import com.destroystokyo.paper.event.entity.WitchConsumePotionEvent;
/*     */ import com.destroystokyo.paper.event.entity.WitchReadyPotionEvent;
/*     */ import com.destroystokyo.paper.event.entity.WitchThrowPotionEvent;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.UUID;
/*     */ import java.util.function.Predicate;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftItemStack;
/*     */ import org.bukkit.entity.LivingEntity;
/*     */ import org.bukkit.entity.Witch;
/*     */ import org.bukkit.event.entity.EntityPotionEffectEvent;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ 
/*     */ public class EntityWitch extends EntityRaider implements IRangedEntity {
/*  16 */   private static final UUID b = UUID.fromString("5CD17E52-A79A-43D3-A529-90FDE04B181E");
/*  17 */   private static final AttributeModifier bo = new AttributeModifier(b, "Drinking speed penalty", -0.25D, AttributeModifier.Operation.ADDITION); private int bq; private PathfinderGoalNearestHealableRaider<EntityRaider> br;
/*  18 */   private static final DataWatcherObject<Boolean> bp = DataWatcher.a((Class)EntityWitch.class, DataWatcherRegistry.i); private PathfinderGoalNearestAttackableTargetWitch<EntityHuman> bs; public int getPotionUseTimeLeft() {
/*  19 */     return this.bq; } public void setPotionUseTimeLeft(int timeLeft) { this.bq = timeLeft; }
/*     */ 
/*     */ 
/*     */   
/*     */   public EntityWitch(EntityTypes<? extends EntityWitch> entitytypes, World world) {
/*  24 */     super((EntityTypes)entitytypes, world);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initPathfinder() {
/*  29 */     super.initPathfinder();
/*  30 */     this.br = new PathfinderGoalNearestHealableRaider<>(this, EntityRaider.class, true, entityliving -> 
/*  31 */         (entityliving != null && fb() && entityliving.getEntityType() != EntityTypes.WITCH));
/*     */     
/*  33 */     this.bs = new PathfinderGoalNearestAttackableTargetWitch<>(this, EntityHuman.class, 10, true, false, (Predicate<EntityLiving>)null);
/*  34 */     this.goalSelector.a(1, new PathfinderGoalFloat(this));
/*  35 */     this.goalSelector.a(2, new PathfinderGoalArrowAttack(this, 1.0D, 60, 10.0F));
/*  36 */     this.goalSelector.a(2, new PathfinderGoalRandomStrollLand(this, 1.0D));
/*  37 */     this.goalSelector.a(3, new PathfinderGoalLookAtPlayer(this, (Class)EntityHuman.class, 8.0F));
/*  38 */     this.goalSelector.a(3, new PathfinderGoalRandomLookaround(this));
/*  39 */     this.targetSelector.a(1, new PathfinderGoalHurtByTarget(this, new Class[] { EntityRaider.class }));
/*  40 */     this.targetSelector.a(2, this.br);
/*  41 */     this.targetSelector.a(3, this.bs);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initDatawatcher() {
/*  46 */     super.initDatawatcher();
/*  47 */     getDataWatcher().register(bp, Boolean.valueOf(false));
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundAmbient() {
/*  52 */     return SoundEffects.ENTITY_WITCH_AMBIENT;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundHurt(DamageSource damagesource) {
/*  57 */     return SoundEffects.ENTITY_WITCH_HURT;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundDeath() {
/*  62 */     return SoundEffects.ENTITY_WITCH_DEATH;
/*     */   }
/*     */   public void setDrinkingPotion(boolean drinkingPotion) {
/*  65 */     v(drinkingPotion);
/*     */   } public void v(boolean flag) {
/*  67 */     getDataWatcher().set(bp, Boolean.valueOf(flag));
/*     */   }
/*     */   public boolean isDrinkingPotion() {
/*  70 */     return m();
/*     */   } public boolean m() {
/*  72 */     return ((Boolean)getDataWatcher().<Boolean>get(bp)).booleanValue();
/*     */   }
/*     */   
/*     */   public static AttributeProvider.Builder eK() {
/*  76 */     return EntityMonster.eR().a(GenericAttributes.MAX_HEALTH, 26.0D).a(GenericAttributes.MOVEMENT_SPEED, 0.25D);
/*     */   }
/*     */ 
/*     */   
/*     */   public void movementTick() {
/*  81 */     if (!this.world.isClientSide && isAlive()) {
/*  82 */       this.br.j();
/*  83 */       if (this.br.h() <= 0) {
/*  84 */         this.bs.a(true);
/*     */       } else {
/*  86 */         this.bs.a(false);
/*     */       } 
/*     */       
/*  89 */       if (m()) {
/*  90 */         if (this.bq-- <= 0) {
/*  91 */           v(false);
/*  92 */           ItemStack itemstack = getItemInMainHand();
/*     */           
/*  94 */           setSlot(EnumItemSlot.MAINHAND, ItemStack.b);
/*  95 */           if (itemstack.getItem() == Items.POTION) {
/*     */             
/*  97 */             WitchConsumePotionEvent event = new WitchConsumePotionEvent((Witch)getBukkitEntity(), (ItemStack)CraftItemStack.asCraftMirror(itemstack));
/*     */             
/*  99 */             List<MobEffect> list = event.callEvent() ? PotionUtil.getEffects(CraftItemStack.asNMSCopy(event.getPotion())) : null;
/*     */ 
/*     */             
/* 102 */             if (list != null) {
/* 103 */               Iterator<MobEffect> iterator = list.iterator();
/*     */               
/* 105 */               while (iterator.hasNext()) {
/* 106 */                 MobEffect mobeffect = iterator.next();
/*     */                 
/* 108 */                 addEffect(new MobEffect(mobeffect), EntityPotionEffectEvent.Cause.ATTACK);
/*     */               } 
/*     */             } 
/*     */           } 
/*     */           
/* 113 */           getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).removeModifier(bo);
/*     */         } 
/*     */       } else {
/* 116 */         PotionRegistry potionregistry = null;
/*     */         
/* 118 */         if (this.random.nextFloat() < 0.15F && a(TagsFluid.WATER) && !hasEffect(MobEffects.WATER_BREATHING)) {
/* 119 */           potionregistry = Potions.WATER_BREATHING;
/* 120 */         } else if (this.random.nextFloat() < 0.15F && (isBurning() || (dl() != null && dl().isFire())) && !hasEffect(MobEffects.FIRE_RESISTANCE)) {
/* 121 */           potionregistry = Potions.FIRE_RESISTANCE;
/* 122 */         } else if (this.random.nextFloat() < 0.05F && getHealth() < getMaxHealth()) {
/* 123 */           potionregistry = Potions.HEALING;
/* 124 */         } else if (this.random.nextFloat() < 0.5F && getGoalTarget() != null && !hasEffect(MobEffects.FASTER_MOVEMENT) && getGoalTarget().h(this) > 121.0D) {
/* 125 */           potionregistry = Potions.SWIFTNESS;
/*     */         } 
/*     */         
/* 128 */         if (potionregistry != null) {
/* 129 */           ItemStack potion = PotionUtil.a(new ItemStack(Items.POTION), potionregistry);
/*     */           
/* 131 */           setDrinkingPotion(potion);
/*     */         } 
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 150 */       if (this.random.nextFloat() < 7.5E-4F) {
/* 151 */         this.world.broadcastEntityEffect(this, (byte)15);
/*     */       }
/*     */     } 
/*     */     
/* 155 */     super.movementTick();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDrinkingPotion(ItemStack potion) {
/* 160 */     ItemStack bukkitStack = WitchReadyPotionEvent.process((Witch)getBukkitEntity(), (ItemStack)CraftItemStack.asCraftMirror(potion));
/* 161 */     setSlot(EnumItemSlot.MAINHAND, CraftItemStack.asNMSCopy(bukkitStack));
/*     */     
/* 163 */     this.bq = getItemInMainHand().k();
/* 164 */     v(true);
/* 165 */     if (!isSilent()) {
/* 166 */       this.world.playSound((EntityHuman)null, locX(), locY(), locZ(), SoundEffects.ENTITY_WITCH_DRINK, getSoundCategory(), 1.0F, 0.8F + this.random.nextFloat() * 0.4F);
/*     */     }
/*     */     
/* 169 */     AttributeModifiable attributemodifiable = getAttributeInstance(GenericAttributes.MOVEMENT_SPEED);
/*     */     
/* 171 */     attributemodifiable.removeModifier(bo);
/* 172 */     attributemodifiable.b(bo);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public SoundEffect eL() {
/* 178 */     return SoundEffects.ENTITY_WITCH_CELEBRATE;
/*     */   }
/*     */ 
/*     */   
/*     */   protected float applyMagicModifier(DamageSource damagesource, float f) {
/* 183 */     f = super.applyMagicModifier(damagesource, f);
/* 184 */     if (damagesource.getEntity() == this) {
/* 185 */       f = 0.0F;
/*     */     }
/*     */     
/* 188 */     if (damagesource.isMagic()) {
/* 189 */       f = (float)(f * 0.15D);
/*     */     }
/*     */     
/* 192 */     return f;
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(EntityLiving entityliving, float f) {
/* 197 */     if (!m()) {
/* 198 */       Vec3D vec3d = entityliving.getMot();
/* 199 */       double d0 = entityliving.locX() + vec3d.x - locX();
/* 200 */       double d1 = entityliving.getHeadY() - 1.100000023841858D - locY();
/* 201 */       double d2 = entityliving.locZ() + vec3d.z - locZ();
/* 202 */       float f1 = MathHelper.sqrt(d0 * d0 + d2 * d2);
/* 203 */       PotionRegistry potionregistry = Potions.HARMING;
/*     */       
/* 205 */       if (entityliving instanceof EntityRaider) {
/* 206 */         if (entityliving.getHealth() <= 4.0F) {
/* 207 */           potionregistry = Potions.HEALING;
/*     */         } else {
/* 209 */           potionregistry = Potions.REGENERATION;
/*     */         } 
/*     */         
/* 212 */         setGoalTarget((EntityLiving)null);
/* 213 */       } else if (f1 >= 8.0F && !entityliving.hasEffect(MobEffects.SLOWER_MOVEMENT)) {
/* 214 */         potionregistry = Potions.SLOWNESS;
/* 215 */       } else if (entityliving.getHealth() >= 8.0F && !entityliving.hasEffect(MobEffects.POISON)) {
/* 216 */         potionregistry = Potions.POISON;
/* 217 */       } else if (f1 <= 3.0F && !entityliving.hasEffect(MobEffects.WEAKNESS) && this.random.nextFloat() < 0.25F) {
/* 218 */         potionregistry = Potions.WEAKNESS;
/*     */       } 
/*     */ 
/*     */       
/* 222 */       ItemStack potion = PotionUtil.a(new ItemStack(Items.SPLASH_POTION), potionregistry);
/* 223 */       WitchThrowPotionEvent event = new WitchThrowPotionEvent((Witch)getBukkitEntity(), (LivingEntity)entityliving.getBukkitEntity(), (ItemStack)CraftItemStack.asCraftMirror(potion));
/* 224 */       if (!event.callEvent()) {
/*     */         return;
/*     */       }
/* 227 */       potion = CraftItemStack.asNMSCopy(event.getPotion());
/* 228 */       EntityPotion entitypotion = new EntityPotion(this.world, this);
/* 229 */       entitypotion.setItem(potion);
/*     */       
/* 231 */       entitypotion.pitch -= -20.0F;
/* 232 */       entitypotion.shoot(d0, d1 + (f1 * 0.2F), d2, 0.75F, 8.0F);
/* 233 */       if (!isSilent()) {
/* 234 */         this.world.playSound((EntityHuman)null, locX(), locY(), locZ(), SoundEffects.ENTITY_WITCH_THROW, getSoundCategory(), 1.0F, 0.8F + this.random.nextFloat() * 0.4F);
/*     */       }
/*     */       
/* 237 */       this.world.addEntity(entitypotion);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected float b(EntityPose entitypose, EntitySize entitysize) {
/* 243 */     return 1.62F;
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(int i, boolean flag) {}
/*     */ 
/*     */   
/*     */   public boolean eN() {
/* 251 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntityWitch.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
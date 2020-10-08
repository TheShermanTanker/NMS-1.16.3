/*      */ package net.minecraft.server.v1_16_R2;
/*      */ import com.destroystokyo.paper.event.entity.EntityJumpEvent;
/*      */ import java.util.Arrays;
/*      */ import java.util.Comparator;
/*      */ import java.util.EnumSet;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Random;
/*      */ import java.util.function.Predicate;
/*      */ import javax.annotation.Nullable;
/*      */ import org.bukkit.craftbukkit.v1_16_R2.event.CraftEventFactory;
/*      */ import org.bukkit.entity.LivingEntity;
/*      */ import org.bukkit.event.entity.EntityTargetEvent;
/*      */ 
/*      */ public class EntityPanda extends EntityAnimal {
/*   16 */   private static final DataWatcherObject<Integer> bp = DataWatcher.a((Class)EntityPanda.class, DataWatcherRegistry.b);
/*   17 */   private static final DataWatcherObject<Integer> bq = DataWatcher.a((Class)EntityPanda.class, DataWatcherRegistry.b);
/*   18 */   private static final DataWatcherObject<Integer> br = DataWatcher.a((Class)EntityPanda.class, DataWatcherRegistry.b);
/*   19 */   private static final DataWatcherObject<Byte> bs = DataWatcher.a((Class)EntityPanda.class, DataWatcherRegistry.a);
/*   20 */   private static final DataWatcherObject<Byte> bt = DataWatcher.a((Class)EntityPanda.class, DataWatcherRegistry.a);
/*   21 */   private static final DataWatcherObject<Byte> bu = DataWatcher.a((Class)EntityPanda.class, DataWatcherRegistry.a); private boolean bw; private boolean bx; public int bo; private Vec3D by; private float bz; private float bA;
/*   22 */   private static final PathfinderTargetCondition bv = (new PathfinderTargetCondition()).a(8.0D).b().a();
/*      */   
/*      */   private float bB;
/*      */   
/*      */   private float bC;
/*      */   
/*      */   private float bD;
/*      */   private float bE;
/*      */   private g bF;
/*      */   private static final Predicate<EntityItem> PICKUP_PREDICATE;
/*      */   
/*      */   static {
/*   34 */     PICKUP_PREDICATE = (entityitem -> {
/*      */         Item item = entityitem.getItemStack().getItem();
/*      */         
/*   37 */         return ((item == Blocks.BAMBOO.getItem() || item == Blocks.CAKE.getItem()) && entityitem.isAlive() && !entityitem.p());
/*      */       });
/*      */   }
/*      */   public EntityPanda(EntityTypes<? extends EntityPanda> entitytypes, World world) {
/*   41 */     super((EntityTypes)entitytypes, world);
/*   42 */     this.moveController = new h(this);
/*   43 */     if (!isBaby()) {
/*   44 */       setCanPickupLoot(true);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean e(ItemStack itemstack) {
/*   51 */     EnumItemSlot enumitemslot = EntityInsentient.j(itemstack);
/*      */     
/*   53 */     return !getEquipment(enumitemslot).isEmpty() ? false : ((enumitemslot == EnumItemSlot.MAINHAND && super.e(itemstack)));
/*      */   }
/*      */   
/*      */   public int eK() {
/*   57 */     return ((Integer)this.datawatcher.<Integer>get(bp)).intValue();
/*      */   }
/*      */   
/*      */   public void t(int i) {
/*   61 */     this.datawatcher.set(bp, Integer.valueOf(i));
/*      */   }
/*      */   
/*      */   public boolean eL() {
/*   65 */     return w(2);
/*      */   }
/*      */   
/*      */   public boolean eM() {
/*   69 */     return w(8);
/*      */   }
/*      */   
/*      */   public void t(boolean flag) {
/*   73 */     d(8, flag);
/*      */   }
/*      */   
/*      */   public boolean eN() {
/*   77 */     return w(16);
/*      */   }
/*      */   
/*      */   public void u(boolean flag) {
/*   81 */     d(16, flag);
/*      */   }
/*      */   
/*      */   public boolean eO() {
/*   85 */     return (((Integer)this.datawatcher.<Integer>get(br)).intValue() > 0);
/*      */   }
/*      */   
/*      */   public void v(boolean flag) {
/*   89 */     this.datawatcher.set(br, Integer.valueOf(flag ? 1 : 0));
/*      */   }
/*      */   
/*      */   private int fk() {
/*   93 */     return ((Integer)this.datawatcher.<Integer>get(br)).intValue();
/*      */   }
/*      */   
/*      */   private void v(int i) {
/*   97 */     this.datawatcher.set(br, Integer.valueOf(i));
/*      */   }
/*      */   
/*      */   public void w(boolean flag) {
/*  101 */     d(2, flag);
/*  102 */     if (!flag) {
/*  103 */       u(0);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public int eU() {
/*  109 */     return ((Integer)this.datawatcher.<Integer>get(bq)).intValue();
/*      */   }
/*      */   
/*      */   public void u(int i) {
/*  113 */     this.datawatcher.set(bq, Integer.valueOf(i));
/*      */   }
/*      */   
/*      */   public Gene getMainGene() {
/*  117 */     return Gene.a(((Byte)this.datawatcher.<Byte>get(bs)).byteValue());
/*      */   }
/*      */   
/*      */   public void setMainGene(Gene entitypanda_gene) {
/*  121 */     if (entitypanda_gene.a() > 6) {
/*  122 */       entitypanda_gene = Gene.a(this.random);
/*      */     }
/*      */     
/*  125 */     this.datawatcher.set(bs, Byte.valueOf((byte)entitypanda_gene.a()));
/*      */   }
/*      */   
/*      */   public Gene getHiddenGene() {
/*  129 */     return Gene.a(((Byte)this.datawatcher.<Byte>get(bt)).byteValue());
/*      */   }
/*      */   
/*      */   public void setHiddenGene(Gene entitypanda_gene) {
/*  133 */     if (entitypanda_gene.a() > 6) {
/*  134 */       entitypanda_gene = Gene.a(this.random);
/*      */     }
/*      */     
/*  137 */     this.datawatcher.set(bt, Byte.valueOf((byte)entitypanda_gene.a()));
/*      */   }
/*      */   
/*      */   public boolean eX() {
/*  141 */     return w(4);
/*      */   }
/*      */   
/*      */   public void x(boolean flag) {
/*  145 */     d(4, flag);
/*      */   }
/*      */ 
/*      */   
/*      */   protected void initDatawatcher() {
/*  150 */     super.initDatawatcher();
/*  151 */     this.datawatcher.register(bp, Integer.valueOf(0));
/*  152 */     this.datawatcher.register(bq, Integer.valueOf(0));
/*  153 */     this.datawatcher.register(bs, Byte.valueOf((byte)0));
/*  154 */     this.datawatcher.register(bt, Byte.valueOf((byte)0));
/*  155 */     this.datawatcher.register(bu, Byte.valueOf((byte)0));
/*  156 */     this.datawatcher.register(br, Integer.valueOf(0));
/*      */   }
/*      */   
/*      */   private boolean w(int i) {
/*  160 */     return ((((Byte)this.datawatcher.<Byte>get(bu)).byteValue() & i) != 0);
/*      */   }
/*      */   
/*      */   private void d(int i, boolean flag) {
/*  164 */     byte b0 = ((Byte)this.datawatcher.<Byte>get(bu)).byteValue();
/*      */     
/*  166 */     if (flag) {
/*  167 */       this.datawatcher.set(bu, Byte.valueOf((byte)(b0 | i)));
/*      */     } else {
/*  169 */       this.datawatcher.set(bu, Byte.valueOf((byte)(b0 & (i ^ 0xFFFFFFFF))));
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void saveData(NBTTagCompound nbttagcompound) {
/*  176 */     super.saveData(nbttagcompound);
/*  177 */     nbttagcompound.setString("MainGene", getMainGene().b());
/*  178 */     nbttagcompound.setString("HiddenGene", getHiddenGene().b());
/*      */   }
/*      */ 
/*      */   
/*      */   public void loadData(NBTTagCompound nbttagcompound) {
/*  183 */     super.loadData(nbttagcompound);
/*  184 */     setMainGene(Gene.a(nbttagcompound.getString("MainGene")));
/*  185 */     setHiddenGene(Gene.a(nbttagcompound.getString("HiddenGene")));
/*      */   }
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   public EntityAgeable createChild(WorldServer worldserver, EntityAgeable entityageable) {
/*  191 */     EntityPanda entitypanda = EntityTypes.PANDA.a(worldserver);
/*      */     
/*  193 */     if (entityageable instanceof EntityPanda) {
/*  194 */       entitypanda.a(this, (EntityPanda)entityageable);
/*      */     }
/*      */     
/*  197 */     entitypanda.fg();
/*  198 */     return entitypanda;
/*      */   }
/*      */ 
/*      */   
/*      */   protected void initPathfinder() {
/*  203 */     this.goalSelector.a(0, new PathfinderGoalFloat(this));
/*  204 */     this.goalSelector.a(2, new i(this, 2.0D));
/*  205 */     this.goalSelector.a(2, new d(this, 1.0D));
/*  206 */     this.goalSelector.a(3, new b(this, 1.2000000476837158D, true));
/*  207 */     this.goalSelector.a(4, new PathfinderGoalTempt(this, 1.0D, RecipeItemStack.a(new IMaterial[] { Blocks.BAMBOO.getItem() }, ), false));
/*  208 */     this.goalSelector.a(6, new c<>(this, EntityHuman.class, 8.0F, 2.0D, 2.0D));
/*  209 */     this.goalSelector.a(6, new c<>(this, EntityMonster.class, 4.0F, 2.0D, 2.0D));
/*  210 */     this.goalSelector.a(7, new k());
/*  211 */     this.goalSelector.a(8, new f(this));
/*  212 */     this.goalSelector.a(8, new l(this));
/*  213 */     this.bF = new g(this, (Class)EntityHuman.class, 6.0F);
/*  214 */     this.goalSelector.a(9, this.bF);
/*  215 */     this.goalSelector.a(10, new PathfinderGoalRandomLookaround(this));
/*  216 */     this.goalSelector.a(12, new j(this));
/*  217 */     this.goalSelector.a(13, new PathfinderGoalFollowParent(this, 1.25D));
/*  218 */     this.goalSelector.a(14, new PathfinderGoalRandomStrollLand(this, 1.0D));
/*  219 */     this.targetSelector.a(1, (new e(this, new Class[0])).a(new Class[0]));
/*      */   }
/*      */   
/*      */   public static AttributeProvider.Builder eY() {
/*  223 */     return EntityInsentient.p().a(GenericAttributes.MOVEMENT_SPEED, 0.15000000596046448D).a(GenericAttributes.ATTACK_DAMAGE, 6.0D);
/*      */   }
/*      */   
/*      */   public Gene getActiveGene() {
/*  227 */     return Gene.b(getMainGene(), getHiddenGene());
/*      */   }
/*      */   
/*      */   public boolean isLazy() {
/*  231 */     return (getActiveGene() == Gene.LAZY);
/*      */   }
/*      */   
/*      */   public boolean isWorried() {
/*  235 */     return (getActiveGene() == Gene.WORRIED);
/*      */   }
/*      */   
/*      */   public boolean isPlayful() {
/*  239 */     return (getActiveGene() == Gene.PLAYFUL);
/*      */   }
/*      */   
/*      */   public boolean isWeak() {
/*  243 */     return (getActiveGene() == Gene.WEAK);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isAggressive() {
/*  248 */     return (getActiveGene() == Gene.AGGRESSIVE);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean a(EntityHuman entityhuman) {
/*  253 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean attackEntity(Entity entity) {
/*  258 */     playSound(SoundEffects.ENTITY_PANDA_BITE, 1.0F, 1.0F);
/*  259 */     if (!isAggressive()) {
/*  260 */       this.bx = true;
/*      */     }
/*      */     
/*  263 */     return super.attackEntity(entity);
/*      */   }
/*      */ 
/*      */   
/*      */   public void tick() {
/*  268 */     super.tick();
/*  269 */     if (isWorried()) {
/*  270 */       if (this.world.V() && !isInWater()) {
/*  271 */         t(true);
/*  272 */         v(false);
/*  273 */       } else if (!eO()) {
/*  274 */         t(false);
/*      */       } 
/*      */     }
/*      */     
/*  278 */     if (getGoalTarget() == null) {
/*  279 */       this.bw = false;
/*  280 */       this.bx = false;
/*      */     } 
/*      */     
/*  283 */     if (eK() > 0) {
/*  284 */       if (getGoalTarget() != null) {
/*  285 */         a(getGoalTarget(), 90.0F, 90.0F);
/*      */       }
/*      */       
/*  288 */       if (eK() == 29 || eK() == 14) {
/*  289 */         playSound(SoundEffects.ENTITY_PANDA_CANT_BREED, 1.0F, 1.0F);
/*      */       }
/*      */       
/*  292 */       t(eK() - 1);
/*      */     } 
/*      */     
/*  295 */     if (eL()) {
/*  296 */       u(eU() + 1);
/*  297 */       if (eU() > 20) {
/*  298 */         w(false);
/*  299 */         fr();
/*  300 */       } else if (eU() == 1) {
/*  301 */         playSound(SoundEffects.ENTITY_PANDA_PRE_SNEEZE, 1.0F, 1.0F);
/*      */       } 
/*      */     } 
/*      */     
/*  305 */     if (eX()) {
/*  306 */       fq();
/*      */     } else {
/*  308 */       this.bo = 0;
/*      */     } 
/*      */     
/*  311 */     if (eM()) {
/*  312 */       this.pitch = 0.0F;
/*      */     }
/*      */     
/*  315 */     fn();
/*  316 */     fl();
/*  317 */     fo();
/*  318 */     fp();
/*      */   }
/*      */   
/*      */   public boolean ff() {
/*  322 */     return (isWorried() && this.world.V());
/*      */   }
/*      */   
/*      */   private void fl() {
/*  326 */     if (!eO() && eM() && !ff() && !getEquipment(EnumItemSlot.MAINHAND).isEmpty() && this.random.nextInt(80) == 1) {
/*  327 */       v(true);
/*  328 */     } else if (getEquipment(EnumItemSlot.MAINHAND).isEmpty() || !eM()) {
/*  329 */       v(false);
/*      */     } 
/*      */     
/*  332 */     if (eO()) {
/*  333 */       fm();
/*  334 */       if (!this.world.isClientSide && fk() > 80 && this.random.nextInt(20) == 1) {
/*  335 */         if (fk() > 100 && l(getEquipment(EnumItemSlot.MAINHAND))) {
/*  336 */           if (!this.world.isClientSide) {
/*  337 */             setSlot(EnumItemSlot.MAINHAND, ItemStack.b);
/*      */           }
/*      */           
/*  340 */           t(false);
/*      */         } 
/*      */         
/*  343 */         v(false);
/*      */         
/*      */         return;
/*      */       } 
/*  347 */       v(fk() + 1);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void fm() {
/*  353 */     if (fk() % 5 == 0) {
/*  354 */       playSound(SoundEffects.ENTITY_PANDA_EAT, 0.5F + 0.5F * this.random.nextInt(2), (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
/*      */       
/*  356 */       for (int i = 0; i < 6; i++) {
/*  357 */         Vec3D vec3d = new Vec3D((this.random.nextFloat() - 0.5D) * 0.1D, Math.random() * 0.1D + 0.1D, (this.random.nextFloat() - 0.5D) * 0.1D);
/*      */         
/*  359 */         vec3d = vec3d.a(-this.pitch * 0.017453292F);
/*  360 */         vec3d = vec3d.b(-this.yaw * 0.017453292F);
/*  361 */         double d0 = -this.random.nextFloat() * 0.6D - 0.3D;
/*  362 */         Vec3D vec3d1 = new Vec3D((this.random.nextFloat() - 0.5D) * 0.8D, d0, 1.0D + (this.random.nextFloat() - 0.5D) * 0.4D);
/*      */         
/*  364 */         vec3d1 = vec3d1.b(-this.aA * 0.017453292F);
/*  365 */         vec3d1 = vec3d1.add(locX(), getHeadY() + 1.0D, locZ());
/*  366 */         this.world.addParticle(new ParticleParamItem(Particles.ITEM, getEquipment(EnumItemSlot.MAINHAND)), vec3d1.x, vec3d1.y, vec3d1.z, vec3d.x, vec3d.y + 0.05D, vec3d.z);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void fn() {
/*  373 */     this.bA = this.bz;
/*  374 */     if (eM()) {
/*  375 */       this.bz = Math.min(1.0F, this.bz + 0.15F);
/*      */     } else {
/*  377 */       this.bz = Math.max(0.0F, this.bz - 0.19F);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void fo() {
/*  383 */     this.bC = this.bB;
/*  384 */     if (eN()) {
/*  385 */       this.bB = Math.min(1.0F, this.bB + 0.15F);
/*      */     } else {
/*  387 */       this.bB = Math.max(0.0F, this.bB - 0.19F);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void fp() {
/*  393 */     this.bE = this.bD;
/*  394 */     if (eX()) {
/*  395 */       this.bD = Math.min(1.0F, this.bD + 0.15F);
/*      */     } else {
/*  397 */       this.bD = Math.max(0.0F, this.bD - 0.19F);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void fq() {
/*  403 */     this.bo++;
/*  404 */     if (this.bo > 32) {
/*  405 */       x(false);
/*      */     }
/*  407 */     else if (!this.world.isClientSide) {
/*  408 */       Vec3D vec3d = getMot();
/*      */       
/*  410 */       if (this.bo == 1) {
/*  411 */         float f = this.yaw * 0.017453292F;
/*  412 */         float f1 = isBaby() ? 0.1F : 0.2F;
/*      */         
/*  414 */         this.by = new Vec3D(vec3d.x + (-MathHelper.sin(f) * f1), 0.0D, vec3d.z + (MathHelper.cos(f) * f1));
/*  415 */         setMot(this.by.add(0.0D, 0.27D, 0.0D));
/*  416 */       } else if (this.bo != 7.0F && this.bo != 15.0F && this.bo != 23.0F) {
/*  417 */         setMot(this.by.x, vec3d.y, this.by.z);
/*      */       } else {
/*  419 */         setMot(0.0D, this.onGround ? 0.27D : vec3d.y, 0.0D);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void fr() {
/*  427 */     Vec3D vec3d = getMot();
/*      */     
/*  429 */     this.world.addParticle(Particles.SNEEZE, locX() - (getWidth() + 1.0F) * 0.5D * MathHelper.sin(this.aA * 0.017453292F), getHeadY() - 0.10000000149011612D, locZ() + (getWidth() + 1.0F) * 0.5D * MathHelper.cos(this.aA * 0.017453292F), vec3d.x, 0.0D, vec3d.z);
/*  430 */     playSound(SoundEffects.ENTITY_PANDA_SNEEZE, 1.0F, 1.0F);
/*  431 */     List<EntityPanda> list = this.world.a(EntityPanda.class, getBoundingBox().g(10.0D));
/*  432 */     Iterator<EntityPanda> iterator = list.iterator();
/*      */     
/*  434 */     while (iterator.hasNext()) {
/*  435 */       EntityPanda entitypanda = iterator.next();
/*      */       
/*  437 */       if (!entitypanda.isBaby() && entitypanda.onGround && !entitypanda.isInWater() && entitypanda.fh()) {
/*  438 */         if ((new EntityJumpEvent((LivingEntity)getBukkitLivingEntity())).callEvent()) {
/*  439 */           entitypanda.jump(); continue;
/*  440 */         }  setJumping(false);
/*      */       } 
/*      */     } 
/*      */     
/*  444 */     if (!this.world.s_() && this.random.nextInt(700) == 0 && this.world.getGameRules().getBoolean(GameRules.DO_MOB_LOOT)) {
/*  445 */       a(Items.SLIME_BALL);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected void b(EntityItem entityitem) {
/*  452 */     if (!CraftEventFactory.callEntityPickupItemEvent(this, entityitem, 0, (!getEquipment(EnumItemSlot.MAINHAND).isEmpty() || !PICKUP_PREDICATE.test(entityitem))).isCancelled()) {
/*  453 */       a(entityitem);
/*  454 */       ItemStack itemstack = entityitem.getItemStack();
/*      */       
/*  456 */       setSlot(EnumItemSlot.MAINHAND, itemstack);
/*  457 */       this.dropChanceHand[EnumItemSlot.MAINHAND.b()] = 2.0F;
/*  458 */       receive(entityitem, itemstack.getCount());
/*  459 */       entityitem.die();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean damageEntity(DamageSource damagesource, float f) {
/*  466 */     t(false);
/*  467 */     return super.damageEntity(damagesource, f);
/*      */   }
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   public GroupDataEntity prepare(WorldAccess worldaccess, DifficultyDamageScaler difficultydamagescaler, EnumMobSpawn enummobspawn, @Nullable GroupDataEntity groupdataentity, @Nullable NBTTagCompound nbttagcompound) {
/*  473 */     setMainGene(Gene.a(this.random));
/*  474 */     setHiddenGene(Gene.a(this.random));
/*  475 */     fg();
/*  476 */     if (groupdataentity == null) {
/*  477 */       groupdataentity = new EntityAgeable.a(0.2F);
/*      */     }
/*      */     
/*  480 */     return super.prepare(worldaccess, difficultydamagescaler, enummobspawn, groupdataentity, nbttagcompound);
/*      */   }
/*      */   
/*      */   public void a(EntityPanda entitypanda, @Nullable EntityPanda entitypanda1) {
/*  484 */     if (entitypanda1 == null) {
/*  485 */       if (this.random.nextBoolean()) {
/*  486 */         setMainGene(entitypanda.fs());
/*  487 */         setHiddenGene(Gene.a(this.random));
/*      */       } else {
/*  489 */         setMainGene(Gene.a(this.random));
/*  490 */         setHiddenGene(entitypanda.fs());
/*      */       } 
/*  492 */     } else if (this.random.nextBoolean()) {
/*  493 */       setMainGene(entitypanda.fs());
/*  494 */       setHiddenGene(entitypanda1.fs());
/*      */     } else {
/*  496 */       setMainGene(entitypanda1.fs());
/*  497 */       setHiddenGene(entitypanda.fs());
/*      */     } 
/*      */     
/*  500 */     if (this.random.nextInt(32) == 0) {
/*  501 */       setMainGene(Gene.a(this.random));
/*      */     }
/*      */     
/*  504 */     if (this.random.nextInt(32) == 0) {
/*  505 */       setHiddenGene(Gene.a(this.random));
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   private Gene fs() {
/*  511 */     return this.random.nextBoolean() ? getMainGene() : getHiddenGene();
/*      */   }
/*      */   
/*      */   public void fg() {
/*  515 */     if (isWeak()) {
/*  516 */       getAttributeInstance(GenericAttributes.MAX_HEALTH).setValue(10.0D);
/*      */     }
/*      */     
/*  519 */     if (isLazy()) {
/*  520 */       getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).setValue(0.07000000029802322D);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   private void ft() {
/*  526 */     if (!isInWater()) {
/*  527 */       t(0.0F);
/*  528 */       getNavigation().o();
/*  529 */       t(true);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public EnumInteractionResult b(EntityHuman entityhuman, EnumHand enumhand) {
/*  536 */     ItemStack itemstack = entityhuman.b(enumhand);
/*      */     
/*  538 */     if (ff())
/*  539 */       return EnumInteractionResult.PASS; 
/*  540 */     if (eN()) {
/*  541 */       u(false);
/*  542 */       return EnumInteractionResult.a(this.world.isClientSide);
/*  543 */     }  if (k(itemstack)) {
/*  544 */       if (getGoalTarget() != null) {
/*  545 */         this.bw = true;
/*      */       }
/*      */       
/*  548 */       if (isBaby()) {
/*  549 */         a(entityhuman, itemstack);
/*  550 */         setAge((int)((-getAge() / 20) * 0.1F), true);
/*  551 */       } else if (!this.world.isClientSide && getAge() == 0 && eP()) {
/*  552 */         a(entityhuman, itemstack);
/*  553 */         g(entityhuman);
/*      */       } else {
/*  555 */         if (this.world.isClientSide || eM() || isInWater()) {
/*  556 */           return EnumInteractionResult.PASS;
/*      */         }
/*      */         
/*  559 */         ft();
/*  560 */         v(true);
/*  561 */         ItemStack itemstack1 = getEquipment(EnumItemSlot.MAINHAND);
/*      */         
/*  563 */         if (!itemstack1.isEmpty() && !entityhuman.abilities.canInstantlyBuild) {
/*  564 */           a(itemstack1);
/*      */         }
/*      */         
/*  567 */         setSlot(EnumItemSlot.MAINHAND, new ItemStack(itemstack.getItem(), 1));
/*  568 */         a(entityhuman, itemstack);
/*      */       } 
/*      */       
/*  571 */       return EnumInteractionResult.SUCCESS;
/*      */     } 
/*  573 */     return EnumInteractionResult.PASS;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   protected SoundEffect getSoundAmbient() {
/*  580 */     return isAggressive() ? SoundEffects.ENTITY_PANDA_AGGRESSIVE_AMBIENT : (isWorried() ? SoundEffects.ENTITY_PANDA_WORRIED_AMBIENT : SoundEffects.ENTITY_PANDA_AMBIENT);
/*      */   }
/*      */ 
/*      */   
/*      */   protected void b(BlockPosition blockposition, IBlockData iblockdata) {
/*  585 */     playSound(SoundEffects.ENTITY_PANDA_STEP, 0.15F, 1.0F);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean k(ItemStack itemstack) {
/*  590 */     return (itemstack.getItem() == Blocks.BAMBOO.getItem());
/*      */   }
/*      */   
/*      */   private boolean l(ItemStack itemstack) {
/*  594 */     return (k(itemstack) || itemstack.getItem() == Blocks.CAKE.getItem());
/*      */   }
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   protected SoundEffect getSoundDeath() {
/*  600 */     return SoundEffects.ENTITY_PANDA_DEATH;
/*      */   }
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   protected SoundEffect getSoundHurt(DamageSource damagesource) {
/*  606 */     return SoundEffects.ENTITY_PANDA_HURT;
/*      */   }
/*      */   
/*      */   public boolean fh() {
/*  610 */     return (!eN() && !ff() && !eO() && !eX() && !eM());
/*      */   }
/*      */   
/*      */   static class i
/*      */     extends PathfinderGoalPanic {
/*      */     private final EntityPanda g;
/*      */     
/*      */     public i(EntityPanda entitypanda, double d0) {
/*  618 */       super(entitypanda, d0);
/*  619 */       this.g = entitypanda;
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean a() {
/*  624 */       if (!this.g.isBurning()) {
/*  625 */         return false;
/*      */       }
/*  627 */       BlockPosition blockposition = a(this.a.world, this.a, 5, 4);
/*      */       
/*  629 */       if (blockposition != null) {
/*  630 */         this.c = blockposition.getX();
/*  631 */         this.d = blockposition.getY();
/*  632 */         this.e = blockposition.getZ();
/*  633 */         return true;
/*      */       } 
/*  635 */       return g();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean b() {
/*  642 */       if (this.g.eM()) {
/*  643 */         this.g.getNavigation().o();
/*  644 */         return false;
/*      */       } 
/*  646 */       return super.b();
/*      */     }
/*      */   }
/*      */   
/*      */   static class e
/*      */     extends PathfinderGoalHurtByTarget
/*      */   {
/*      */     private final EntityPanda a;
/*      */     
/*      */     public e(EntityPanda entitypanda, Class<?>... aclass) {
/*  656 */       super(entitypanda, aclass);
/*  657 */       this.a = entitypanda;
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean b() {
/*  662 */       if (!this.a.bw && !this.a.bx) {
/*  663 */         return super.b();
/*      */       }
/*  665 */       this.a.setGoalTarget((EntityLiving)null);
/*  666 */       return false;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     protected void a(EntityInsentient entityinsentient, EntityLiving entityliving) {
/*  672 */       if (entityinsentient instanceof EntityPanda && ((EntityPanda)entityinsentient).isAggressive()) {
/*  673 */         entityinsentient.setGoalTarget(entityliving, EntityTargetEvent.TargetReason.TARGET_ATTACKED_ENTITY, true);
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   static class f
/*      */     extends PathfinderGoal
/*      */   {
/*      */     private final EntityPanda a;
/*      */     private int b;
/*      */     
/*      */     public f(EntityPanda entitypanda) {
/*  685 */       this.a = entitypanda;
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean a() {
/*  690 */       return (this.b < this.a.ticksLived && this.a.isLazy() && this.a.fh() && this.a.random.nextInt(400) == 1);
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean b() {
/*  695 */       return (!this.a.isInWater() && (this.a.isLazy() || this.a.random.nextInt(600) != 1)) ? ((this.a.random.nextInt(2000) != 1)) : false;
/*      */     }
/*      */ 
/*      */     
/*      */     public void c() {
/*  700 */       this.a.u(true);
/*  701 */       this.b = 0;
/*      */     }
/*      */ 
/*      */     
/*      */     public void d() {
/*  706 */       this.a.u(false);
/*  707 */       this.b = this.a.ticksLived + 200;
/*      */     }
/*      */   }
/*      */   
/*      */   class k
/*      */     extends PathfinderGoal {
/*      */     private int b;
/*      */     
/*      */     public k() {
/*  716 */       a(EnumSet.of(PathfinderGoal.Type.MOVE));
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean a() {
/*  721 */       if (this.b <= EntityPanda.this.ticksLived && !EntityPanda.this.isBaby() && !EntityPanda.this.isInWater() && EntityPanda.this.fh() && EntityPanda.this.eK() <= 0) {
/*  722 */         List<EntityItem> list = EntityPanda.this.world.a(EntityItem.class, EntityPanda.this.getBoundingBox().grow(6.0D, 6.0D, 6.0D), EntityPanda.PICKUP_PREDICATE);
/*      */         
/*  724 */         return (!list.isEmpty() || !EntityPanda.this.getEquipment(EnumItemSlot.MAINHAND).isEmpty());
/*      */       } 
/*  726 */       return false;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean b() {
/*  732 */       return (!EntityPanda.this.isInWater() && (EntityPanda.this.isLazy() || EntityPanda.this.random.nextInt(600) != 1)) ? ((EntityPanda.this.random.nextInt(2000) != 1)) : false;
/*      */     }
/*      */ 
/*      */     
/*      */     public void e() {
/*  737 */       if (!EntityPanda.this.eM() && !EntityPanda.this.getEquipment(EnumItemSlot.MAINHAND).isEmpty()) {
/*  738 */         EntityPanda.this.ft();
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public void c() {
/*  745 */       List<EntityItem> list = EntityPanda.this.world.a(EntityItem.class, EntityPanda.this.getBoundingBox().grow(8.0D, 8.0D, 8.0D), EntityPanda.PICKUP_PREDICATE);
/*      */       
/*  747 */       if (!list.isEmpty() && EntityPanda.this.getEquipment(EnumItemSlot.MAINHAND).isEmpty()) {
/*  748 */         EntityPanda.this.getNavigation().a(list.get(0), 1.2000000476837158D);
/*  749 */       } else if (!EntityPanda.this.getEquipment(EnumItemSlot.MAINHAND).isEmpty()) {
/*  750 */         EntityPanda.this.ft();
/*      */       } 
/*      */       
/*  753 */       this.b = 0;
/*      */     }
/*      */ 
/*      */     
/*      */     public void d() {
/*  758 */       ItemStack itemstack = EntityPanda.this.getEquipment(EnumItemSlot.MAINHAND);
/*      */       
/*  760 */       if (!itemstack.isEmpty()) {
/*  761 */         EntityPanda.this.a(itemstack);
/*  762 */         EntityPanda.this.setSlot(EnumItemSlot.MAINHAND, ItemStack.b);
/*  763 */         int i = EntityPanda.this.isLazy() ? (EntityPanda.this.random.nextInt(50) + 10) : (EntityPanda.this.random.nextInt(150) + 10);
/*      */         
/*  765 */         this.b = EntityPanda.this.ticksLived + i * 20;
/*      */       } 
/*      */       
/*  768 */       EntityPanda.this.t(false);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   static class c<T extends EntityLiving>
/*      */     extends PathfinderGoalAvoidTarget<T>
/*      */   {
/*      */     private final EntityPanda i;
/*      */     
/*      */     public c(EntityPanda entitypanda, Class<T> oclass, float f, double d0, double d1) {
/*  779 */       super(entitypanda, oclass, f, d0, d1, IEntitySelector.g::test);
/*  780 */       this.i = entitypanda;
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean a() {
/*  785 */       return (this.i.isWorried() && this.i.fh() && super.a());
/*      */     }
/*      */   }
/*      */   
/*      */   class d
/*      */     extends PathfinderGoalBreed {
/*      */     private final EntityPanda e;
/*      */     private int f;
/*      */     
/*      */     public d(EntityPanda entitypanda, double d0) {
/*  795 */       super(entitypanda, d0);
/*  796 */       this.e = entitypanda;
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean a() {
/*  801 */       if (super.a() && this.e.eK() == 0) {
/*  802 */         if (!h()) {
/*  803 */           if (this.f <= this.e.ticksLived) {
/*  804 */             this.e.t(32);
/*  805 */             this.f = this.e.ticksLived + 600;
/*  806 */             if (this.e.doAITick()) {
/*  807 */               EntityHuman entityhuman = this.b.a(EntityPanda.bv, this.e);
/*      */               
/*  809 */               this.e.bF.a(entityhuman);
/*      */             } 
/*      */           } 
/*      */           
/*  813 */           return false;
/*      */         } 
/*  815 */         return true;
/*      */       } 
/*      */       
/*  818 */       return false;
/*      */     }
/*      */ 
/*      */     
/*      */     private boolean h() {
/*  823 */       BlockPosition blockposition = this.e.getChunkCoordinates();
/*  824 */       BlockPosition.MutableBlockPosition blockposition_mutableblockposition = new BlockPosition.MutableBlockPosition();
/*      */       
/*  826 */       for (int i = 0; i < 3; i++) {
/*  827 */         for (int j = 0; j < 8; j++) {
/*  828 */           int k; for (k = 0; k <= j; k = (k > 0) ? -k : (1 - k)) {
/*  829 */             int l; for (l = (k < j && k > -j) ? j : 0; l <= j; l = (l > 0) ? -l : (1 - l)) {
/*  830 */               blockposition_mutableblockposition.a(blockposition, k, i, l);
/*  831 */               if (this.b.getType(blockposition_mutableblockposition).a(Blocks.BAMBOO)) {
/*  832 */                 return true;
/*      */               }
/*      */             } 
/*      */           } 
/*      */         } 
/*      */       } 
/*      */       
/*  839 */       return false;
/*      */     }
/*      */   }
/*      */   
/*      */   static class l
/*      */     extends PathfinderGoal {
/*      */     private final EntityPanda a;
/*      */     
/*      */     public l(EntityPanda entitypanda) {
/*  848 */       this.a = entitypanda;
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean a() {
/*  853 */       return (this.a.isBaby() && this.a.fh()) ? ((this.a.isWeak() && this.a.random.nextInt(500) == 1) ? true : ((this.a.random.nextInt(6000) == 1))) : false;
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean b() {
/*  858 */       return false;
/*      */     }
/*      */ 
/*      */     
/*      */     public void c() {
/*  863 */       this.a.w(true);
/*      */     }
/*      */   }
/*      */   
/*      */   static class j
/*      */     extends PathfinderGoal {
/*      */     private final EntityPanda a;
/*      */     
/*      */     public j(EntityPanda entitypanda) {
/*  872 */       this.a = entitypanda;
/*  873 */       a(EnumSet.of(PathfinderGoal.Type.MOVE, PathfinderGoal.Type.LOOK, PathfinderGoal.Type.JUMP));
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean a() {
/*  878 */       if ((this.a.isBaby() || this.a.isPlayful()) && this.a.onGround) {
/*  879 */         if (!this.a.fh()) {
/*  880 */           return false;
/*      */         }
/*  882 */         float f = this.a.yaw * 0.017453292F;
/*  883 */         int i = 0;
/*  884 */         int k = 0;
/*  885 */         float f1 = -MathHelper.sin(f);
/*  886 */         float f2 = MathHelper.cos(f);
/*      */         
/*  888 */         if (Math.abs(f1) > 0.5D) {
/*  889 */           i = (int)(i + f1 / Math.abs(f1));
/*      */         }
/*      */         
/*  892 */         if (Math.abs(f2) > 0.5D) {
/*  893 */           k = (int)(k + f2 / Math.abs(f2));
/*      */         }
/*      */         
/*  896 */         return this.a.world.getType(this.a.getChunkCoordinates().b(i, -1, k)).isAir() ? true : ((this.a.isPlayful() && this.a.random.nextInt(60) == 1) ? true : ((this.a.random.nextInt(500) == 1)));
/*      */       } 
/*      */       
/*  899 */       return false;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean b() {
/*  905 */       return false;
/*      */     }
/*      */ 
/*      */     
/*      */     public void c() {
/*  910 */       this.a.x(true);
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean C_() {
/*  915 */       return false;
/*      */     }
/*      */   }
/*      */   
/*      */   static class g
/*      */     extends PathfinderGoalLookAtPlayer {
/*      */     private final EntityPanda g;
/*      */     
/*      */     public g(EntityPanda entitypanda, Class<? extends EntityLiving> oclass, float f) {
/*  924 */       super(entitypanda, oclass, f);
/*  925 */       this.g = entitypanda;
/*      */     }
/*      */     
/*      */     public void a(EntityLiving entityliving) {
/*  929 */       this.b = entityliving;
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean b() {
/*  934 */       return (this.b != null && super.b());
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean a() {
/*  939 */       if (this.a.getRandom().nextFloat() >= this.d) {
/*  940 */         return false;
/*      */       }
/*  942 */       if (this.b == null) {
/*  943 */         if (this.e == EntityHuman.class) {
/*  944 */           this.b = this.a.world.a(this.f, this.a, this.a.locX(), this.a.getHeadY(), this.a.locZ());
/*      */         } else {
/*  946 */           this.b = this.a.world.b((Class)this.e, this.f, this.a, this.a.locX(), this.a.getHeadY(), this.a.locZ(), this.a.getBoundingBox().grow(this.c, 3.0D, this.c));
/*      */         } 
/*      */       }
/*      */       
/*  950 */       return (this.g.fh() && this.b != null);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public void e() {
/*  956 */       if (this.b != null) {
/*  957 */         super.e();
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   static class b
/*      */     extends PathfinderGoalMeleeAttack
/*      */   {
/*      */     private final EntityPanda b;
/*      */     
/*      */     public b(EntityPanda entitypanda, double d0, boolean flag) {
/*  968 */       super(entitypanda, d0, flag);
/*  969 */       this.b = entitypanda;
/*      */     }
/*      */ 
/*      */     
/*      */     public boolean a() {
/*  974 */       return (this.b.fh() && super.a());
/*      */     }
/*      */   }
/*      */   
/*      */   static class h
/*      */     extends ControllerMove {
/*      */     private final EntityPanda i;
/*      */     
/*      */     public h(EntityPanda entitypanda) {
/*  983 */       super(entitypanda);
/*  984 */       this.i = entitypanda;
/*      */     }
/*      */ 
/*      */     
/*      */     public void a() {
/*  989 */       if (this.i.fh()) {
/*  990 */         super.a();
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */   public enum Gene
/*      */   {
/*  997 */     NORMAL(0, "normal", false), LAZY(1, "lazy", false), WORRIED(2, "worried", false), PLAYFUL(3, "playful", false), BROWN(4, "brown", true), WEAK(5, "weak", true), AGGRESSIVE(6, "aggressive", false); private final boolean k; private final String j;
/*      */     static {
/*  999 */       h = (Gene[])Arrays.<Gene>stream(values()).sorted(Comparator.comparingInt(Gene::a)).toArray(i -> new Gene[i]);
/*      */     }
/*      */ 
/*      */     
/*      */     private final int i;
/*      */     private static final Gene[] h;
/*      */     
/*      */     Gene(int i, String s, boolean flag) {
/* 1007 */       this.i = i;
/* 1008 */       this.j = s;
/* 1009 */       this.k = flag;
/*      */     }
/*      */     
/*      */     public int a() {
/* 1013 */       return this.i;
/*      */     }
/*      */     
/*      */     public String b() {
/* 1017 */       return this.j;
/*      */     }
/*      */     
/*      */     public boolean isRecessive() {
/* 1021 */       return this.k;
/*      */     }
/*      */     
/*      */     private static Gene b(Gene entitypanda_gene, Gene entitypanda_gene1) {
/* 1025 */       return entitypanda_gene.isRecessive() ? ((entitypanda_gene == entitypanda_gene1) ? entitypanda_gene : NORMAL) : entitypanda_gene;
/*      */     }
/*      */     
/*      */     public static Gene a(int i) {
/* 1029 */       if (i < 0 || i >= h.length) {
/* 1030 */         i = 0;
/*      */       }
/*      */       
/* 1033 */       return h[i];
/*      */     }
/*      */     
/*      */     public static Gene a(String s) {
/* 1037 */       Gene[] aentitypanda_gene = values();
/* 1038 */       int i = aentitypanda_gene.length;
/*      */       
/* 1040 */       for (int j = 0; j < i; j++) {
/* 1041 */         Gene entitypanda_gene = aentitypanda_gene[j];
/*      */         
/* 1043 */         if (entitypanda_gene.j.equals(s)) {
/* 1044 */           return entitypanda_gene;
/*      */         }
/*      */       } 
/*      */       
/* 1048 */       return NORMAL;
/*      */     }
/*      */     
/*      */     public static Gene a(Random random) {
/* 1052 */       int i = random.nextInt(16);
/*      */       
/* 1054 */       return (i == 0) ? LAZY : ((i == 1) ? WORRIED : ((i == 2) ? PLAYFUL : ((i == 4) ? AGGRESSIVE : ((i < 9) ? WEAK : ((i < 11) ? BROWN : NORMAL)))));
/*      */     }
/*      */   }
/*      */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntityPanda.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
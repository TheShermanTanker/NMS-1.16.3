/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.Maps;
/*     */ import java.util.Map;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ public class EntityPillager
/*     */   extends EntityIllagerAbstract implements ICrossbow {
/*   9 */   private static final DataWatcherObject<Boolean> b = DataWatcher.a((Class)EntityPillager.class, DataWatcherRegistry.i);
/*  10 */   public final InventorySubcontainer inventory = new InventorySubcontainer(5);
/*     */   
/*     */   public EntityPillager(EntityTypes<? extends EntityPillager> entitytypes, World world) {
/*  13 */     super((EntityTypes)entitytypes, world);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initPathfinder() {
/*  18 */     super.initPathfinder();
/*  19 */     this.goalSelector.a(0, new PathfinderGoalFloat(this));
/*  20 */     this.goalSelector.a(2, new EntityRaider.a(this, this, 10.0F));
/*  21 */     this.goalSelector.a(3, new PathfinderGoalCrossbowAttack<>(this, 1.0D, 8.0F));
/*  22 */     this.goalSelector.a(8, new PathfinderGoalRandomStroll(this, 0.6D));
/*  23 */     this.goalSelector.a(9, new PathfinderGoalLookAtPlayer(this, (Class)EntityHuman.class, 15.0F, 1.0F));
/*  24 */     this.goalSelector.a(10, new PathfinderGoalLookAtPlayer(this, (Class)EntityInsentient.class, 15.0F));
/*  25 */     this.targetSelector.a(1, (new PathfinderGoalHurtByTarget(this, new Class[] { EntityRaider.class })).a(new Class[0]));
/*  26 */     this.targetSelector.a(2, new PathfinderGoalNearestAttackableTarget<>(this, EntityHuman.class, true));
/*  27 */     this.targetSelector.a(3, new PathfinderGoalNearestAttackableTarget<>(this, EntityVillagerAbstract.class, false));
/*  28 */     this.targetSelector.a(3, new PathfinderGoalNearestAttackableTarget<>(this, EntityIronGolem.class, true));
/*     */   }
/*     */   
/*     */   public static AttributeProvider.Builder eK() {
/*  32 */     return EntityMonster.eR().a(GenericAttributes.MOVEMENT_SPEED, 0.3499999940395355D).a(GenericAttributes.MAX_HEALTH, 24.0D).a(GenericAttributes.ATTACK_DAMAGE, 5.0D).a(GenericAttributes.FOLLOW_RANGE, 32.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initDatawatcher() {
/*  37 */     super.initDatawatcher();
/*  38 */     this.datawatcher.register(b, Boolean.valueOf(false));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(ItemProjectileWeapon itemprojectileweapon) {
/*  43 */     return (itemprojectileweapon == Items.CROSSBOW);
/*     */   }
/*     */ 
/*     */   
/*     */   public void b(boolean flag) {
/*  48 */     this.datawatcher.set(b, Boolean.valueOf(flag));
/*     */   }
/*     */ 
/*     */   
/*     */   public void U_() {
/*  53 */     this.ticksFarFromPlayer = 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public void saveData(NBTTagCompound nbttagcompound) {
/*  58 */     super.saveData(nbttagcompound);
/*  59 */     NBTTagList nbttaglist = new NBTTagList();
/*     */     
/*  61 */     for (int i = 0; i < this.inventory.getSize(); i++) {
/*  62 */       ItemStack itemstack = this.inventory.getItem(i);
/*     */       
/*  64 */       if (!itemstack.isEmpty()) {
/*  65 */         nbttaglist.add(itemstack.save(new NBTTagCompound()));
/*     */       }
/*     */     } 
/*     */     
/*  69 */     nbttagcompound.set("Inventory", nbttaglist);
/*     */   }
/*     */ 
/*     */   
/*     */   public void loadData(NBTTagCompound nbttagcompound) {
/*  74 */     super.loadData(nbttagcompound);
/*  75 */     NBTTagList nbttaglist = nbttagcompound.getList("Inventory", 10);
/*     */     
/*  77 */     for (int i = 0; i < nbttaglist.size(); i++) {
/*  78 */       ItemStack itemstack = ItemStack.a(nbttaglist.getCompound(i));
/*     */       
/*  80 */       if (!itemstack.isEmpty()) {
/*  81 */         this.inventory.a(itemstack);
/*     */       }
/*     */     } 
/*     */     
/*  85 */     setCanPickupLoot(true);
/*     */   }
/*     */ 
/*     */   
/*     */   public float a(BlockPosition blockposition, IWorldReader iworldreader) {
/*  90 */     IBlockData iblockdata = iworldreader.getType(blockposition.down());
/*     */     
/*  92 */     return (!iblockdata.a(Blocks.GRASS_BLOCK) && !iblockdata.a(Blocks.SAND)) ? (0.5F - iworldreader.y(blockposition)) : 10.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMaxSpawnGroup() {
/*  97 */     return 1;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public GroupDataEntity prepare(WorldAccess worldaccess, DifficultyDamageScaler difficultydamagescaler, EnumMobSpawn enummobspawn, @Nullable GroupDataEntity groupdataentity, @Nullable NBTTagCompound nbttagcompound) {
/* 103 */     a(difficultydamagescaler);
/* 104 */     b(difficultydamagescaler);
/* 105 */     return super.prepare(worldaccess, difficultydamagescaler, enummobspawn, groupdataentity, nbttagcompound);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(DifficultyDamageScaler difficultydamagescaler) {
/* 110 */     setSlot(EnumItemSlot.MAINHAND, new ItemStack(Items.CROSSBOW));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void w(float f) {
/* 115 */     super.w(f);
/* 116 */     if (this.random.nextInt(300) == 0) {
/* 117 */       ItemStack itemstack = getItemInMainHand();
/*     */       
/* 119 */       if (itemstack.getItem() == Items.CROSSBOW) {
/* 120 */         Map<Enchantment, Integer> map = EnchantmentManager.a(itemstack);
/*     */         
/* 122 */         map.putIfAbsent(Enchantments.PIERCING, Integer.valueOf(1));
/* 123 */         EnchantmentManager.a(map, itemstack);
/* 124 */         setSlot(EnumItemSlot.MAINHAND, itemstack);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean r(Entity entity) {
/* 132 */     return super.r(entity) ? true : ((entity instanceof EntityLiving && ((EntityLiving)entity).getMonsterType() == EnumMonsterType.ILLAGER) ? ((getScoreboardTeam() == null && entity.getScoreboardTeam() == null)) : false);
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundAmbient() {
/* 137 */     return SoundEffects.ENTITY_PILLAGER_AMBIENT;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundDeath() {
/* 142 */     return SoundEffects.ENTITY_PILLAGER_DEATH;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundHurt(DamageSource damagesource) {
/* 147 */     return SoundEffects.ENTITY_PILLAGER_HURT;
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(EntityLiving entityliving, float f) {
/* 152 */     b(this, 1.6F);
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(EntityLiving entityliving, ItemStack itemstack, IProjectile iprojectile, float f) {
/* 157 */     a(this, entityliving, iprojectile, f, 1.6F);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void b(EntityItem entityitem) {
/* 162 */     ItemStack itemstack = entityitem.getItemStack();
/*     */     
/* 164 */     if (itemstack.getItem() instanceof ItemBanner) {
/* 165 */       super.b(entityitem);
/*     */     } else {
/* 167 */       Item item = itemstack.getItem();
/*     */       
/* 169 */       if (b(item)) {
/* 170 */         a(entityitem);
/* 171 */         ItemStack itemstack1 = this.inventory.a(itemstack);
/*     */         
/* 173 */         if (itemstack1.isEmpty()) {
/* 174 */           entityitem.die();
/*     */         } else {
/* 176 */           itemstack.setCount(itemstack1.getCount());
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean b(Item item) {
/* 184 */     return (fb() && item == Items.WHITE_BANNER);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a_(int i, ItemStack itemstack) {
/* 189 */     if (super.a_(i, itemstack)) {
/* 190 */       return true;
/*     */     }
/* 192 */     int j = i - 300;
/*     */     
/* 194 */     if (j >= 0 && j < this.inventory.getSize()) {
/* 195 */       this.inventory.setItem(j, itemstack);
/* 196 */       return true;
/*     */     } 
/* 198 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(int i, boolean flag) {
/* 205 */     Raid raid = fa();
/* 206 */     boolean flag1 = (this.random.nextFloat() <= raid.w());
/*     */     
/* 208 */     if (flag1) {
/* 209 */       ItemStack itemstack = new ItemStack(Items.CROSSBOW);
/* 210 */       Map<Enchantment, Integer> map = Maps.newHashMap();
/*     */       
/* 212 */       if (i > raid.a(EnumDifficulty.NORMAL)) {
/* 213 */         map.put(Enchantments.QUICK_CHARGE, Integer.valueOf(2));
/* 214 */       } else if (i > raid.a(EnumDifficulty.EASY)) {
/* 215 */         map.put(Enchantments.QUICK_CHARGE, Integer.valueOf(1));
/*     */       } 
/*     */       
/* 218 */       map.put(Enchantments.MULTISHOT, Integer.valueOf(1));
/* 219 */       EnchantmentManager.a(map, itemstack);
/* 220 */       setSlot(EnumItemSlot.MAINHAND, itemstack);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public SoundEffect eL() {
/* 227 */     return SoundEffects.ENTITY_PILLAGER_CELEBRATE;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntityPillager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
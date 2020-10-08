/*     */ package net.minecraft.server.v1_16_R2;
/*     */ import java.util.Random;
/*     */ import java.util.function.Predicate;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.entity.CraftMonster;
/*     */ 
/*     */ public abstract class EntityMonster extends EntityCreature implements IMonster {
/*     */   public CraftMonster getBukkitMonster() {
/*   8 */     return (CraftMonster)getBukkitEntity();
/*     */   } protected EntityMonster(EntityTypes<? extends EntityMonster> entitytypes, World world) {
/*  10 */     super((EntityTypes)entitytypes, world);
/*  11 */     this.f = 5;
/*     */   }
/*     */ 
/*     */   
/*     */   public SoundCategory getSoundCategory() {
/*  16 */     return SoundCategory.HOSTILE;
/*     */   }
/*     */ 
/*     */   
/*     */   public void movementTick() {
/*  21 */     dz();
/*  22 */     eQ();
/*  23 */     super.movementTick();
/*     */   }
/*     */   
/*     */   protected void eQ() {
/*  27 */     float f = aQ();
/*     */     
/*  29 */     if (f > 0.5F) {
/*  30 */       this.ticksFarFromPlayer += 2;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean L() {
/*  37 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundSwim() {
/*  42 */     return SoundEffects.ENTITY_HOSTILE_SWIM;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundSplash() {
/*  47 */     return SoundEffects.ENTITY_HOSTILE_SPLASH;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean damageEntity(DamageSource damagesource, float f) {
/*  52 */     return isInvulnerable(damagesource) ? false : super.damageEntity(damagesource, f);
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundHurt(DamageSource damagesource) {
/*  57 */     return SoundEffects.ENTITY_HOSTILE_HURT;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundDeath() {
/*  62 */     return SoundEffects.ENTITY_HOSTILE_DEATH;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundFall(int i) {
/*  67 */     return (i > 4) ? SoundEffects.ENTITY_HOSTILE_BIG_FALL : SoundEffects.ENTITY_HOSTILE_SMALL_FALL;
/*     */   }
/*     */ 
/*     */   
/*     */   public float a(BlockPosition blockposition, IWorldReader iworldreader) {
/*  72 */     return 0.5F - iworldreader.y(blockposition);
/*     */   }
/*     */   
/*     */   public static boolean a(WorldAccess worldaccess, BlockPosition blockposition, Random random) {
/*  76 */     if (worldaccess.getBrightness(EnumSkyBlock.SKY, blockposition) > random.nextInt(32)) {
/*  77 */       return false;
/*     */     }
/*  79 */     int i = worldaccess.getMinecraftWorld().V() ? worldaccess.c(blockposition, 10) : worldaccess.getLightLevel(blockposition);
/*     */     
/*  81 */     return (i <= random.nextInt(8));
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean b(EntityTypes<? extends EntityMonster> entitytypes, WorldAccess worldaccess, EnumMobSpawn enummobspawn, BlockPosition blockposition, Random random) {
/*  86 */     return (worldaccess.getDifficulty() != EnumDifficulty.PEACEFUL && a(worldaccess, blockposition, random) && a((EntityTypes)entitytypes, worldaccess, enummobspawn, blockposition, random));
/*     */   }
/*     */   
/*     */   public static boolean c(EntityTypes<? extends EntityMonster> entitytypes, GeneratorAccess generatoraccess, EnumMobSpawn enummobspawn, BlockPosition blockposition, Random random) {
/*  90 */     return (generatoraccess.getDifficulty() != EnumDifficulty.PEACEFUL && a((EntityTypes)entitytypes, generatoraccess, enummobspawn, blockposition, random));
/*     */   }
/*     */   
/*     */   public static AttributeProvider.Builder eR() {
/*  94 */     return EntityInsentient.p().a(GenericAttributes.ATTACK_DAMAGE);
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean isDropExperience() {
/*  99 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean cV() {
/* 104 */     return true;
/*     */   }
/*     */   
/*     */   public boolean f(EntityHuman entityhuman) {
/* 108 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack f(ItemStack itemstack) {
/* 113 */     if (itemstack.getItem() instanceof ItemProjectileWeapon) {
/* 114 */       Predicate<ItemStack> predicate = ((ItemProjectileWeapon)itemstack.getItem()).e();
/* 115 */       ItemStack itemstack1 = ItemProjectileWeapon.a(this, predicate);
/*     */       
/* 117 */       return itemstack1.isEmpty() ? new ItemStack(Items.ARROW) : itemstack1;
/*     */     } 
/* 119 */     return ItemStack.b;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntityMonster.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.util.EnumSet;
/*     */ import java.util.Random;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.event.CraftEventFactory;
/*     */ 
/*     */ public class EntitySilverfish extends EntityMonster {
/*     */   private PathfinderGoalSilverfishWakeOthers b;
/*     */   
/*     */   public EntitySilverfish(EntityTypes<? extends EntitySilverfish> entitytypes, World world) {
/*  11 */     super((EntityTypes)entitytypes, world);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initPathfinder() {
/*  16 */     this.b = new PathfinderGoalSilverfishWakeOthers(this);
/*  17 */     this.goalSelector.a(1, new PathfinderGoalFloat(this));
/*  18 */     this.goalSelector.a(3, this.b);
/*  19 */     this.goalSelector.a(4, new PathfinderGoalMeleeAttack(this, 1.0D, false));
/*  20 */     this.goalSelector.a(5, new PathfinderGoalSilverfishHideInBlock(this));
/*  21 */     this.targetSelector.a(1, (new PathfinderGoalHurtByTarget(this, new Class[0])).a(new Class[0]));
/*  22 */     this.targetSelector.a(2, new PathfinderGoalNearestAttackableTarget<>(this, EntityHuman.class, true));
/*     */   }
/*     */ 
/*     */   
/*     */   public double ba() {
/*  27 */     return 0.1D;
/*     */   }
/*     */ 
/*     */   
/*     */   protected float b(EntityPose entitypose, EntitySize entitysize) {
/*  32 */     return 0.13F;
/*     */   }
/*     */   
/*     */   public static AttributeProvider.Builder m() {
/*  36 */     return EntityMonster.eR().a(GenericAttributes.MAX_HEALTH, 8.0D).a(GenericAttributes.MOVEMENT_SPEED, 0.25D).a(GenericAttributes.ATTACK_DAMAGE, 1.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean playStepSound() {
/*  41 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundAmbient() {
/*  46 */     return SoundEffects.ENTITY_SILVERFISH_AMBIENT;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundHurt(DamageSource damagesource) {
/*  51 */     return SoundEffects.ENTITY_SILVERFISH_HURT;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundDeath() {
/*  56 */     return SoundEffects.ENTITY_SILVERFISH_DEATH;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void b(BlockPosition blockposition, IBlockData iblockdata) {
/*  61 */     playSound(SoundEffects.ENTITY_SILVERFISH_STEP, 0.15F, 1.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean damageEntity(DamageSource damagesource, float f) {
/*  66 */     if (isInvulnerable(damagesource)) {
/*  67 */       return false;
/*     */     }
/*  69 */     if ((damagesource instanceof EntityDamageSource || damagesource == DamageSource.MAGIC) && this.b != null) {
/*  70 */       this.b.g();
/*     */     }
/*     */     
/*  73 */     return super.damageEntity(damagesource, f);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void tick() {
/*  79 */     this.aA = this.yaw;
/*  80 */     super.tick();
/*     */   }
/*     */ 
/*     */   
/*     */   public void n(float f) {
/*  85 */     this.yaw = f;
/*  86 */     super.n(f);
/*     */   }
/*     */ 
/*     */   
/*     */   public float a(BlockPosition blockposition, IWorldReader iworldreader) {
/*  91 */     return BlockMonsterEggs.h(iworldreader.getType(blockposition.down())) ? 10.0F : super.a(blockposition, iworldreader);
/*     */   }
/*     */   
/*     */   public static boolean b(EntityTypes<EntitySilverfish> entitytypes, GeneratorAccess generatoraccess, EnumMobSpawn enummobspawn, BlockPosition blockposition, Random random) {
/*  95 */     if (c((EntityTypes)entitytypes, generatoraccess, enummobspawn, blockposition, random)) {
/*  96 */       EntityHuman entityhuman = generatoraccess.a(blockposition.getX() + 0.5D, blockposition.getY() + 0.5D, blockposition.getZ() + 0.5D, 5.0D, true);
/*     */       
/*  98 */       return ((entityhuman == null || entityhuman.affectsSpawning) && entityhuman == null);
/*     */     } 
/* 100 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumMonsterType getMonsterType() {
/* 106 */     return EnumMonsterType.ARTHROPOD;
/*     */   }
/*     */   
/*     */   static class PathfinderGoalSilverfishHideInBlock
/*     */     extends PathfinderGoalRandomStroll {
/*     */     private EnumDirection h;
/*     */     private boolean i;
/*     */     
/*     */     public PathfinderGoalSilverfishHideInBlock(EntitySilverfish entitysilverfish) {
/* 115 */       super(entitysilverfish, 1.0D, 10);
/* 116 */       a(EnumSet.of(PathfinderGoal.Type.MOVE));
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean a() {
/* 121 */       if (this.a.getGoalTarget() != null)
/* 122 */         return false; 
/* 123 */       if (!this.a.getNavigation().m()) {
/* 124 */         return false;
/*     */       }
/* 126 */       Random random = this.a.getRandom();
/*     */       
/* 128 */       if (this.a.world.getGameRules().getBoolean(GameRules.MOB_GRIEFING) && random.nextInt(10) == 0) {
/* 129 */         this.h = EnumDirection.a(random);
/* 130 */         BlockPosition blockposition = (new BlockPosition(this.a.locX(), this.a.locY() + 0.5D, this.a.locZ())).shift(this.h);
/* 131 */         IBlockData iblockdata = this.a.world.getType(blockposition);
/*     */         
/* 133 */         if (BlockMonsterEggs.h(iblockdata)) {
/* 134 */           this.i = true;
/* 135 */           return true;
/*     */         } 
/*     */       } 
/*     */       
/* 139 */       this.i = false;
/* 140 */       return super.a();
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean b() {
/* 146 */       return this.i ? false : super.b();
/*     */     }
/*     */ 
/*     */     
/*     */     public void c() {
/* 151 */       if (!this.i) {
/* 152 */         super.c();
/*     */       } else {
/* 154 */         World world = this.a.world;
/* 155 */         BlockPosition blockposition = (new BlockPosition(this.a.locX(), this.a.locY() + 0.5D, this.a.locZ())).shift(this.h);
/* 156 */         IBlockData iblockdata = world.getType(blockposition);
/*     */         
/* 158 */         if (BlockMonsterEggs.h(iblockdata)) {
/*     */           
/* 160 */           if (CraftEventFactory.callEntityChangeBlockEvent(this.a, blockposition, BlockMonsterEggs.c(iblockdata.getBlock())).isCancelled()) {
/*     */             return;
/*     */           }
/*     */           
/* 164 */           world.setTypeAndData(blockposition, BlockMonsterEggs.c(iblockdata.getBlock()), 3);
/* 165 */           this.a.doSpawnEffect();
/* 166 */           this.a.die();
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   static class PathfinderGoalSilverfishWakeOthers
/*     */     extends PathfinderGoal
/*     */   {
/*     */     private final EntitySilverfish silverfish;
/*     */     private int b;
/*     */     
/*     */     public PathfinderGoalSilverfishWakeOthers(EntitySilverfish entitysilverfish) {
/* 179 */       this.silverfish = entitysilverfish;
/*     */     }
/*     */     
/*     */     public void g() {
/* 183 */       if (this.b == 0) {
/* 184 */         this.b = 20;
/*     */       }
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean a() {
/* 191 */       return (this.b > 0);
/*     */     }
/*     */ 
/*     */     
/*     */     public void e() {
/* 196 */       this.b--;
/* 197 */       if (this.b <= 0) {
/* 198 */         World world = this.silverfish.world;
/* 199 */         Random random = this.silverfish.getRandom();
/* 200 */         BlockPosition blockposition = this.silverfish.getChunkCoordinates();
/*     */         int i;
/* 202 */         for (i = 0; i <= 5 && i >= -5; i = ((i <= 0) ? 1 : 0) - i) {
/* 203 */           int j; for (j = 0; j <= 10 && j >= -10; j = ((j <= 0) ? 1 : 0) - j) {
/* 204 */             int k; for (k = 0; k <= 10 && k >= -10; k = ((k <= 0) ? 1 : 0) - k) {
/* 205 */               BlockPosition blockposition1 = blockposition.b(j, i, k);
/* 206 */               IBlockData iblockdata = world.getType(blockposition1);
/* 207 */               Block block = iblockdata.getBlock();
/*     */               
/* 209 */               if (block instanceof BlockMonsterEggs)
/*     */               {
/* 211 */                 if (!CraftEventFactory.callEntityChangeBlockEvent(this.silverfish, blockposition1, Blocks.AIR.getBlockData()).isCancelled()) {
/*     */ 
/*     */ 
/*     */                   
/* 215 */                   if (world.getGameRules().getBoolean(GameRules.MOB_GRIEFING)) {
/* 216 */                     world.a(blockposition1, true, this.silverfish);
/*     */                   } else {
/* 218 */                     world.setTypeAndData(blockposition1, ((BlockMonsterEggs)block).c().getBlockData(), 3);
/*     */                   } 
/*     */                   
/* 221 */                   if (random.nextBoolean())
/*     */                     return; 
/*     */                 } 
/*     */               }
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntitySilverfish.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
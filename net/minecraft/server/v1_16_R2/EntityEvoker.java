/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.util.List;
/*     */ 
/*     */ public class EntityEvoker extends EntityIllagerWizard {
/*     */   private EntitySheep bo;
/*     */   
/*   8 */   public final EntitySheep getWololoTarget() { return this.bo; } public final void setWololoTarget(EntitySheep sheep) { this.bo = sheep; }
/*     */   
/*     */   public EntityEvoker(EntityTypes<? extends EntityEvoker> entitytypes, World world) {
/*  11 */     super((EntityTypes)entitytypes, world);
/*  12 */     this.f = 10;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initPathfinder() {
/*  17 */     super.initPathfinder();
/*  18 */     this.goalSelector.a(0, new PathfinderGoalFloat(this));
/*  19 */     this.goalSelector.a(1, new b());
/*  20 */     this.goalSelector.a(2, new PathfinderGoalAvoidTarget<>(this, EntityHuman.class, 8.0F, 0.6D, 1.0D));
/*  21 */     this.goalSelector.a(4, new c());
/*  22 */     this.goalSelector.a(5, new a());
/*  23 */     this.goalSelector.a(6, new d());
/*  24 */     this.goalSelector.a(8, new PathfinderGoalRandomStroll(this, 0.6D));
/*  25 */     this.goalSelector.a(9, new PathfinderGoalLookAtPlayer(this, (Class)EntityHuman.class, 3.0F, 1.0F));
/*  26 */     this.goalSelector.a(10, new PathfinderGoalLookAtPlayer(this, (Class)EntityInsentient.class, 8.0F));
/*  27 */     this.targetSelector.a(1, (new PathfinderGoalHurtByTarget(this, new Class[] { EntityRaider.class })).a(new Class[0]));
/*  28 */     this.targetSelector.a(2, (new PathfinderGoalNearestAttackableTarget<>(this, (Class)EntityHuman.class, true)).a(300));
/*  29 */     this.targetSelector.a(3, (new PathfinderGoalNearestAttackableTarget<>(this, (Class)EntityVillagerAbstract.class, false)).a(300));
/*  30 */     this.targetSelector.a(3, new PathfinderGoalNearestAttackableTarget<>(this, EntityIronGolem.class, false));
/*     */   }
/*     */   
/*     */   public static AttributeProvider.Builder eK() {
/*  34 */     return EntityMonster.eR().a(GenericAttributes.MOVEMENT_SPEED, 0.5D).a(GenericAttributes.FOLLOW_RANGE, 12.0D).a(GenericAttributes.MAX_HEALTH, 24.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initDatawatcher() {
/*  39 */     super.initDatawatcher();
/*     */   }
/*     */ 
/*     */   
/*     */   public void loadData(NBTTagCompound nbttagcompound) {
/*  44 */     super.loadData(nbttagcompound);
/*     */   }
/*     */ 
/*     */   
/*     */   public SoundEffect eL() {
/*  49 */     return SoundEffects.ENTITY_EVOKER_CELEBRATE;
/*     */   }
/*     */ 
/*     */   
/*     */   public void saveData(NBTTagCompound nbttagcompound) {
/*  54 */     super.saveData(nbttagcompound);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void mobTick() {
/*  59 */     super.mobTick();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean r(Entity entity) {
/*  64 */     return (entity == null) ? false : ((entity == this) ? true : (super.r(entity) ? true : ((entity instanceof EntityVex) ? r(((EntityVex)entity).eK()) : ((entity instanceof EntityLiving && ((EntityLiving)entity).getMonsterType() == EnumMonsterType.ILLAGER) ? ((getScoreboardTeam() == null && entity.getScoreboardTeam() == null)) : false))));
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundAmbient() {
/*  69 */     return SoundEffects.ENTITY_EVOKER_AMBIENT;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundDeath() {
/*  74 */     return SoundEffects.ENTITY_EVOKER_DEATH;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundHurt(DamageSource damagesource) {
/*  79 */     return SoundEffects.ENTITY_EVOKER_HURT;
/*     */   }
/*     */   
/*     */   private void a(@Nullable EntitySheep entitysheep) {
/*  83 */     this.bo = entitysheep;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   private EntitySheep fg() {
/*  88 */     return this.bo;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundCastSpell() {
/*  93 */     return SoundEffects.ENTITY_EVOKER_CAST_SPELL;
/*     */   }
/*     */   
/*     */   public void a(int i, boolean flag) {}
/*     */   
/*     */   public class d extends EntityIllagerWizard.PathfinderGoalCastSpell { private final PathfinderTargetCondition e;
/*     */     
/*     */     public d() {
/* 101 */       this.e = (new PathfinderTargetCondition()).a(16.0D).a().a(entityliving -> (((EntitySheep)entityliving).getColor() == EnumColor.BLUE));
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean a() {
/* 111 */       if (EntityEvoker.this.getGoalTarget() != null)
/* 112 */         return false; 
/* 113 */       if (EntityEvoker.this.eW())
/* 114 */         return false; 
/* 115 */       if (EntityEvoker.this.ticksLived < this.c)
/* 116 */         return false; 
/* 117 */       if (!EntityEvoker.this.world.getGameRules().getBoolean(GameRules.MOB_GRIEFING)) {
/* 118 */         return false;
/*     */       }
/* 120 */       List<EntitySheep> list = EntityEvoker.this.world.a(EntitySheep.class, this.e, EntityEvoker.this, EntityEvoker.this.getBoundingBox().grow(16.0D, 4.0D, 16.0D));
/*     */       
/* 122 */       if (list.isEmpty()) {
/* 123 */         return false;
/*     */       }
/* 125 */       EntityEvoker.this.a(list.get(EntityEvoker.this.random.nextInt(list.size())));
/* 126 */       return true;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean b() {
/* 133 */       return (EntityEvoker.this.fg() != null && this.b > 0);
/*     */     }
/*     */ 
/*     */     
/*     */     public void d() {
/* 138 */       super.d();
/* 139 */       EntityEvoker.this.a((EntitySheep)null);
/*     */     }
/*     */ 
/*     */     
/*     */     protected void j() {
/* 144 */       EntitySheep entitysheep = EntityEvoker.this.fg();
/*     */       
/* 146 */       if (entitysheep != null && entitysheep.isAlive()) {
/* 147 */         entitysheep.setColor(EnumColor.RED);
/*     */       }
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected int m() {
/* 154 */       return 40;
/*     */     }
/*     */ 
/*     */     
/*     */     protected int g() {
/* 159 */       return 60;
/*     */     }
/*     */ 
/*     */     
/*     */     protected int h() {
/* 164 */       return 140;
/*     */     }
/*     */ 
/*     */     
/*     */     protected SoundEffect k() {
/* 169 */       return SoundEffects.ENTITY_EVOKER_PREPARE_WOLOLO;
/*     */     }
/*     */ 
/*     */     
/*     */     protected EntityIllagerWizard.Spell getCastSpell() {
/* 174 */       return EntityIllagerWizard.Spell.WOLOLO;
/*     */     } }
/*     */ 
/*     */   
/*     */   class c
/*     */     extends EntityIllagerWizard.PathfinderGoalCastSpell
/*     */   {
/*     */     private final PathfinderTargetCondition e;
/*     */     
/*     */     private c() {
/* 184 */       this.e = (new PathfinderTargetCondition()).a(16.0D).c().e().a().b();
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean a() {
/* 189 */       if (!super.a()) {
/* 190 */         return false;
/*     */       }
/* 192 */       int i = EntityEvoker.this.world.<EntityVex>a(EntityVex.class, this.e, EntityEvoker.this, EntityEvoker.this.getBoundingBox().g(16.0D)).size();
/*     */       
/* 194 */       return (EntityEvoker.this.random.nextInt(8) + 1 > i);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected int g() {
/* 200 */       return 100;
/*     */     }
/*     */ 
/*     */     
/*     */     protected int h() {
/* 205 */       return 340;
/*     */     }
/*     */ 
/*     */     
/*     */     protected void j() {
/* 210 */       WorldServer worldserver = (WorldServer)EntityEvoker.this.world;
/*     */       
/* 212 */       for (int i = 0; i < 3; i++) {
/* 213 */         BlockPosition blockposition = EntityEvoker.this.getChunkCoordinates().b(-2 + EntityEvoker.this.random.nextInt(5), 1, -2 + EntityEvoker.this.random.nextInt(5));
/* 214 */         EntityVex entityvex = EntityTypes.VEX.a(EntityEvoker.this.world);
/*     */         
/* 216 */         entityvex.setPositionRotation(blockposition, 0.0F, 0.0F);
/* 217 */         entityvex.prepare(worldserver, EntityEvoker.this.world.getDamageScaler(blockposition), EnumMobSpawn.MOB_SUMMONED, (GroupDataEntity)null, (NBTTagCompound)null);
/* 218 */         entityvex.a(EntityEvoker.this);
/* 219 */         entityvex.g(blockposition);
/* 220 */         entityvex.a(20 * (30 + EntityEvoker.this.random.nextInt(90)));
/* 221 */         worldserver.addAllEntities(entityvex);
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected SoundEffect k() {
/* 228 */       return SoundEffects.ENTITY_EVOKER_PREPARE_SUMMON;
/*     */     }
/*     */ 
/*     */     
/*     */     protected EntityIllagerWizard.Spell getCastSpell() {
/* 233 */       return EntityIllagerWizard.Spell.SUMMON_VEX;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   class a
/*     */     extends EntityIllagerWizard.PathfinderGoalCastSpell
/*     */   {
/*     */     private a() {}
/*     */ 
/*     */     
/*     */     protected int g() {
/* 245 */       return 40;
/*     */     }
/*     */ 
/*     */     
/*     */     protected int h() {
/* 250 */       return 100;
/*     */     }
/*     */ 
/*     */     
/*     */     protected void j() {
/* 255 */       EntityLiving entityliving = EntityEvoker.this.getGoalTarget();
/* 256 */       double d0 = Math.min(entityliving.locY(), EntityEvoker.this.locY());
/* 257 */       double d1 = Math.max(entityliving.locY(), EntityEvoker.this.locY()) + 1.0D;
/* 258 */       float f = (float)MathHelper.d(entityliving.locZ() - EntityEvoker.this.locZ(), entityliving.locX() - EntityEvoker.this.locX());
/*     */ 
/*     */       
/* 261 */       if (EntityEvoker.this.h(entityliving) < 9.0D) {
/*     */         int i;
/*     */         
/* 264 */         for (i = 0; i < 5; i++) {
/* 265 */           float f1 = f + i * 3.1415927F * 0.4F;
/* 266 */           a(EntityEvoker.this.locX() + MathHelper.cos(f1) * 1.5D, EntityEvoker.this.locZ() + MathHelper.sin(f1) * 1.5D, d0, d1, f1, 0);
/*     */         } 
/*     */         
/* 269 */         for (i = 0; i < 8; i++) {
/* 270 */           float f1 = f + i * 3.1415927F * 2.0F / 8.0F + 1.2566371F;
/* 271 */           a(EntityEvoker.this.locX() + MathHelper.cos(f1) * 2.5D, EntityEvoker.this.locZ() + MathHelper.sin(f1) * 2.5D, d0, d1, f1, 3);
/*     */         } 
/*     */       } else {
/* 274 */         for (int i = 0; i < 16; i++) {
/* 275 */           double d2 = 1.25D * (i + 1);
/* 276 */           int j = 1 * i;
/*     */           
/* 278 */           a(EntityEvoker.this.locX() + MathHelper.cos(f) * d2, EntityEvoker.this.locZ() + MathHelper.sin(f) * d2, d0, d1, f, j);
/*     */         } 
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     private void a(double d0, double d1, double d2, double d3, float f, int i) {
/* 285 */       BlockPosition blockposition = new BlockPosition(d0, d3, d1);
/* 286 */       boolean flag = false;
/* 287 */       double d4 = 0.0D;
/*     */       
/*     */       do {
/* 290 */         BlockPosition blockposition1 = blockposition.down();
/* 291 */         IBlockData iblockdata = EntityEvoker.this.world.getType(blockposition1);
/*     */         
/* 293 */         if (iblockdata.d(EntityEvoker.this.world, blockposition1, EnumDirection.UP)) {
/* 294 */           if (!EntityEvoker.this.world.isEmpty(blockposition)) {
/* 295 */             IBlockData iblockdata1 = EntityEvoker.this.world.getType(blockposition);
/* 296 */             VoxelShape voxelshape = iblockdata1.getCollisionShape(EntityEvoker.this.world, blockposition);
/*     */             
/* 298 */             if (!voxelshape.isEmpty()) {
/* 299 */               d4 = voxelshape.c(EnumDirection.EnumAxis.Y);
/*     */             }
/*     */           } 
/*     */           
/* 303 */           flag = true;
/*     */           
/*     */           break;
/*     */         } 
/* 307 */         blockposition = blockposition.down();
/* 308 */       } while (blockposition.getY() >= MathHelper.floor(d2) - 1);
/*     */       
/* 310 */       if (flag) {
/* 311 */         EntityEvoker.this.world.addEntity(new EntityEvokerFangs(EntityEvoker.this.world, d0, blockposition.getY() + d4, d1, f, i, EntityEvoker.this));
/*     */       }
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected SoundEffect k() {
/* 318 */       return SoundEffects.ENTITY_EVOKER_PREPARE_ATTACK;
/*     */     }
/*     */ 
/*     */     
/*     */     protected EntityIllagerWizard.Spell getCastSpell() {
/* 323 */       return EntityIllagerWizard.Spell.FANGS;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   class b
/*     */     extends EntityIllagerWizard.b
/*     */   {
/*     */     private b() {}
/*     */ 
/*     */     
/*     */     public void e() {
/* 335 */       if (EntityEvoker.this.getGoalTarget() != null) {
/* 336 */         EntityEvoker.this.getControllerLook().a(EntityEvoker.this.getGoalTarget(), EntityEvoker.this.eo(), EntityEvoker.this.O());
/* 337 */       } else if (EntityEvoker.this.fg() != null) {
/* 338 */         EntityEvoker.this.getControllerLook().a(EntityEvoker.this.fg(), EntityEvoker.this.eo(), EntityEvoker.this.O());
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntityEvoker.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
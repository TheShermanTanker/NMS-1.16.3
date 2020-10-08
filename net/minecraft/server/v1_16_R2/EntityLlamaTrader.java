/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.util.EnumSet;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.event.entity.EntityTargetEvent;
/*     */ 
/*     */ public class EntityLlamaTrader extends EntityLlama {
/*   8 */   private int bw = 47999;
/*     */   
/*     */   public EntityLlamaTrader(EntityTypes<? extends EntityLlamaTrader> entitytypes, World world) {
/*  11 */     super((EntityTypes)entitytypes, world);
/*     */   }
/*     */ 
/*     */   
/*     */   protected EntityLlama fz() {
/*  16 */     return EntityTypes.TRADER_LLAMA.a(this.world);
/*     */   }
/*     */ 
/*     */   
/*     */   public void saveData(NBTTagCompound nbttagcompound) {
/*  21 */     super.saveData(nbttagcompound);
/*  22 */     nbttagcompound.setInt("DespawnDelay", this.bw);
/*     */   }
/*     */ 
/*     */   
/*     */   public void loadData(NBTTagCompound nbttagcompound) {
/*  27 */     super.loadData(nbttagcompound);
/*  28 */     if (nbttagcompound.hasKeyOfType("DespawnDelay", 99)) {
/*  29 */       this.bw = nbttagcompound.getInt("DespawnDelay");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void initPathfinder() {
/*  36 */     super.initPathfinder();
/*  37 */     this.goalSelector.a(1, new PathfinderGoalPanic(this, 2.0D));
/*  38 */     this.targetSelector.a(1, new a(this));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void h(EntityHuman entityhuman) {
/*  43 */     Entity entity = getLeashHolder();
/*     */     
/*  45 */     if (!(entity instanceof EntityVillagerTrader)) {
/*  46 */       super.h(entityhuman);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void movementTick() {
/*  52 */     super.movementTick();
/*  53 */     if (!this.world.isClientSide) {
/*  54 */       fE();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private void fE() {
/*  60 */     if (fF()) {
/*  61 */       this.bw = fG() ? (((EntityVillagerTrader)getLeashHolder()).eX() - 1) : (this.bw - 1);
/*  62 */       if (this.bw <= 0) {
/*  63 */         unleash(true, false);
/*  64 */         die();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean fF() {
/*  71 */     return (!isTamed() && !fH() && !hasSinglePlayerPassenger());
/*     */   }
/*     */   
/*     */   private boolean fG() {
/*  75 */     return getLeashHolder() instanceof EntityVillagerTrader;
/*     */   }
/*     */   
/*     */   private boolean fH() {
/*  79 */     return (isLeashed() && !fG());
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public GroupDataEntity prepare(WorldAccess worldaccess, DifficultyDamageScaler difficultydamagescaler, EnumMobSpawn enummobspawn, @Nullable GroupDataEntity groupdataentity, @Nullable NBTTagCompound nbttagcompound) {
/*  85 */     if (enummobspawn == EnumMobSpawn.EVENT) {
/*  86 */       setAgeRaw(0);
/*     */     }
/*     */     
/*  89 */     if (groupdataentity == null) {
/*  90 */       groupdataentity = new EntityAgeable.a(false);
/*     */     }
/*     */     
/*  93 */     return super.prepare(worldaccess, difficultydamagescaler, enummobspawn, groupdataentity, nbttagcompound);
/*     */   }
/*     */   
/*     */   public class a
/*     */     extends PathfinderGoalTarget {
/*     */     private final EntityLlama b;
/*     */     private EntityLiving c;
/*     */     private int d;
/*     */     
/*     */     public a(EntityLlama entityllama) {
/* 103 */       super(entityllama, false);
/* 104 */       this.b = entityllama;
/* 105 */       a(EnumSet.of(PathfinderGoal.Type.TARGET));
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean a() {
/* 110 */       if (!this.b.isLeashed()) {
/* 111 */         return false;
/*     */       }
/* 113 */       Entity entity = this.b.getLeashHolder();
/*     */       
/* 115 */       if (!(entity instanceof EntityVillagerTrader)) {
/* 116 */         return false;
/*     */       }
/* 118 */       EntityVillagerTrader entityvillagertrader = (EntityVillagerTrader)entity;
/*     */       
/* 120 */       this.c = entityvillagertrader.getLastDamager();
/* 121 */       int i = entityvillagertrader.cZ();
/*     */       
/* 123 */       return (i != this.d && a(this.c, PathfinderTargetCondition.a));
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void c() {
/* 130 */       this.e.setGoalTarget(this.c, EntityTargetEvent.TargetReason.TARGET_ATTACKED_OWNER, true);
/* 131 */       Entity entity = this.b.getLeashHolder();
/*     */       
/* 133 */       if (entity instanceof EntityVillagerTrader) {
/* 134 */         this.d = ((EntityVillagerTrader)entity).cZ();
/*     */       }
/*     */       
/* 137 */       super.c();
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntityLlamaTrader.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.util.Optional;
/*     */ import java.util.UUID;
/*     */ import javax.annotation.Nullable;
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
/*     */ public abstract class EntityTameableAnimal
/*     */   extends EntityAnimal
/*     */ {
/*  25 */   protected static final DataWatcherObject<Byte> bo = DataWatcher.a((Class)EntityTameableAnimal.class, DataWatcherRegistry.a);
/*  26 */   protected static final DataWatcherObject<Optional<UUID>> bp = DataWatcher.a((Class)EntityTameableAnimal.class, DataWatcherRegistry.o);
/*     */   
/*     */   private boolean willSit;
/*     */   
/*     */   protected EntityTameableAnimal(EntityTypes<? extends EntityTameableAnimal> var0, World var1) {
/*  31 */     super((EntityTypes)var0, var1);
/*  32 */     eL();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initDatawatcher() {
/*  37 */     super.initDatawatcher();
/*  38 */     this.datawatcher.register(bo, Byte.valueOf((byte)0));
/*  39 */     this.datawatcher.register(bp, Optional.empty());
/*     */   }
/*     */ 
/*     */   
/*     */   public void saveData(NBTTagCompound var0) {
/*  44 */     super.saveData(var0);
/*  45 */     if (getOwnerUUID() != null) {
/*  46 */       var0.a("Owner", getOwnerUUID());
/*     */     }
/*  48 */     var0.setBoolean("Sitting", this.willSit);
/*     */   }
/*     */   
/*     */   public void loadData(NBTTagCompound var0) {
/*     */     UUID var1;
/*  53 */     super.loadData(var0);
/*     */     
/*  55 */     if (var0.b("Owner")) {
/*  56 */       var1 = var0.a("Owner");
/*     */     } else {
/*  58 */       String var2 = var0.getString("Owner");
/*  59 */       var1 = NameReferencingFileConverter.a(getMinecraftServer(), var2);
/*     */     } 
/*  61 */     if (var1 != null) {
/*     */       try {
/*  63 */         setOwnerUUID(var1);
/*  64 */         setTamed(true);
/*  65 */       } catch (Throwable var2) {
/*  66 */         setTamed(false);
/*     */       } 
/*     */     }
/*  69 */     this.willSit = var0.getBoolean("Sitting");
/*  70 */     setSitting(this.willSit);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(EntityHuman var0) {
/*  75 */     return !isLeashed();
/*     */   }
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isTamed() {
/* 103 */     return ((((Byte)this.datawatcher.<Byte>get(bo)).byteValue() & 0x4) != 0);
/*     */   }
/*     */   
/*     */   public void setTamed(boolean var0) {
/* 107 */     byte var1 = ((Byte)this.datawatcher.<Byte>get(bo)).byteValue();
/* 108 */     if (var0) {
/* 109 */       this.datawatcher.set(bo, Byte.valueOf((byte)(var1 | 0x4)));
/*     */     } else {
/* 111 */       this.datawatcher.set(bo, Byte.valueOf((byte)(var1 & 0xFFFFFFFB)));
/*     */     } 
/*     */     
/* 114 */     eL();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void eL() {}
/*     */   
/*     */   public boolean isSitting() {
/* 121 */     return ((((Byte)this.datawatcher.<Byte>get(bo)).byteValue() & 0x1) != 0);
/*     */   }
/*     */   
/*     */   public void setSitting(boolean var0) {
/* 125 */     byte var1 = ((Byte)this.datawatcher.<Byte>get(bo)).byteValue();
/* 126 */     if (var0) {
/* 127 */       this.datawatcher.set(bo, Byte.valueOf((byte)(var1 | 0x1)));
/*     */     } else {
/* 129 */       this.datawatcher.set(bo, Byte.valueOf((byte)(var1 & 0xFFFFFFFE)));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public UUID getOwnerUUID() {
/* 136 */     return ((Optional<UUID>)this.datawatcher.<Optional<UUID>>get(bp)).orElse(null);
/*     */   }
/*     */   
/*     */   public void setOwnerUUID(@Nullable UUID var0) {
/* 140 */     this.datawatcher.set(bp, Optional.ofNullable(var0));
/*     */   }
/*     */   
/*     */   public void tame(EntityHuman var0) {
/* 144 */     setTamed(true);
/* 145 */     setOwnerUUID(var0.getUniqueID());
/* 146 */     if (var0 instanceof EntityPlayer) {
/* 147 */       CriterionTriggers.x.a((EntityPlayer)var0, this);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public EntityLiving getOwner() {
/*     */     try {
/* 155 */       UUID var0 = getOwnerUUID();
/* 156 */       if (var0 == null) {
/* 157 */         return null;
/*     */       }
/* 159 */       return this.world.b(var0);
/* 160 */     } catch (IllegalArgumentException var0) {
/* 161 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean c(EntityLiving var0) {
/* 167 */     if (i(var0)) {
/* 168 */       return false;
/*     */     }
/* 170 */     return super.c(var0);
/*     */   }
/*     */   
/*     */   public boolean i(EntityLiving var0) {
/* 174 */     return (var0 == getOwner());
/*     */   }
/*     */   
/*     */   public boolean a(EntityLiving var0, EntityLiving var1) {
/* 178 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public ScoreboardTeamBase getScoreboardTeam() {
/* 183 */     if (isTamed()) {
/* 184 */       EntityLiving var0 = getOwner();
/* 185 */       if (var0 != null) {
/* 186 */         return var0.getScoreboardTeam();
/*     */       }
/*     */     } 
/* 189 */     return super.getScoreboardTeam();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean r(Entity var0) {
/* 194 */     if (isTamed()) {
/* 195 */       EntityLiving var1 = getOwner();
/* 196 */       if (var0 == var1) {
/* 197 */         return true;
/*     */       }
/* 199 */       if (var1 != null) {
/* 200 */         return var1.r(var0);
/*     */       }
/*     */     } 
/* 203 */     return super.r(var0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void die(DamageSource var0) {
/* 208 */     if (!this.world.isClientSide && this.world.getGameRules().getBoolean(GameRules.SHOW_DEATH_MESSAGES) && 
/* 209 */       getOwner() instanceof EntityPlayer) {
/* 210 */       getOwner().sendMessage(getCombatTracker().getDeathMessage(), SystemUtils.b);
/*     */     }
/*     */     
/* 213 */     super.die(var0);
/*     */   }
/*     */   
/*     */   public boolean isWillSit() {
/* 217 */     return this.willSit;
/*     */   }
/*     */   
/*     */   public void setWillSit(boolean var0) {
/* 221 */     this.willSit = var0;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntityTameableAnimal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
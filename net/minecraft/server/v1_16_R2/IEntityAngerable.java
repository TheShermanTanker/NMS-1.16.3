/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.util.Objects;
/*     */ import java.util.UUID;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.event.entity.EntityTargetEvent;
/*     */ 
/*     */ public interface IEntityAngerable {
/*     */   int getAnger();
/*     */   
/*     */   void setAnger(int paramInt);
/*     */   
/*     */   @Nullable
/*     */   UUID getAngerTarget();
/*     */   
/*     */   void setAngerTarget(@Nullable UUID paramUUID);
/*     */   
/*     */   void anger();
/*     */   
/*     */   default void c(NBTTagCompound nbttagcompound) {
/*  21 */     nbttagcompound.setInt("AngerTime", getAnger());
/*  22 */     if (getAngerTarget() != null) {
/*  23 */       nbttagcompound.a("AngryAt", getAngerTarget());
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   default void a(WorldServer worldserver, NBTTagCompound nbttagcompound) {
/*  29 */     setAnger(nbttagcompound.getInt("AngerTime"));
/*  30 */     if (!nbttagcompound.b("AngryAt")) {
/*  31 */       setAngerTarget((UUID)null);
/*     */     } else {
/*  33 */       UUID uuid = nbttagcompound.a("AngryAt");
/*     */       
/*  35 */       setAngerTarget(uuid);
/*  36 */       Entity entity = worldserver.getEntity(uuid);
/*     */       
/*  38 */       if (entity != null) {
/*  39 */         if (entity instanceof EntityInsentient) {
/*  40 */           setLastDamager((EntityInsentient)entity);
/*     */         }
/*     */         
/*  43 */         if (entity.getEntityType() == EntityTypes.PLAYER) {
/*  44 */           e((EntityHuman)entity);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   default void a(WorldServer worldserver, boolean flag) {
/*  52 */     EntityLiving entityliving = getGoalTarget();
/*  53 */     UUID uuid = getAngerTarget();
/*     */     
/*  55 */     if ((entityliving == null || entityliving.dk()) && uuid != null && worldserver.getEntity(uuid) instanceof EntityInsentient) {
/*  56 */       pacify();
/*     */     } else {
/*  58 */       if (entityliving != null && !Objects.equals(uuid, entityliving.getUniqueID())) {
/*  59 */         setAngerTarget(entityliving.getUniqueID());
/*  60 */         anger();
/*     */       } 
/*     */       
/*  63 */       if (getAnger() > 0 && (entityliving == null || entityliving.getEntityType() != EntityTypes.PLAYER || !flag)) {
/*  64 */         setAnger(getAnger() - 1);
/*  65 */         if (getAnger() == 0) {
/*  66 */           pacify();
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   default boolean a_(EntityLiving entityliving) {
/*  74 */     return !IEntitySelector.f.test(entityliving) ? false : ((entityliving.getEntityType() == EntityTypes.PLAYER && a(entityliving.world)) ? true : entityliving.getUniqueID().equals(getAngerTarget()));
/*     */   }
/*     */   
/*     */   default boolean a(World world) {
/*  78 */     return (world.getGameRules().getBoolean(GameRules.UNIVERSAL_ANGER) && isAngry() && getAngerTarget() == null);
/*     */   }
/*     */   
/*     */   default boolean isAngry() {
/*  82 */     return (getAnger() > 0);
/*     */   }
/*     */   
/*     */   default void b(EntityHuman entityhuman) {
/*  86 */     if (entityhuman.world.getGameRules().getBoolean(GameRules.FORGIVE_DEAD_PLAYERS) && 
/*  87 */       entityhuman.getUniqueID().equals(getAngerTarget())) {
/*  88 */       pacify();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   default void I_() {
/*  94 */     pacify();
/*  95 */     anger();
/*     */   }
/*     */   
/*     */   default void pacify() {
/*  99 */     setLastDamager((EntityLiving)null);
/* 100 */     setAngerTarget((UUID)null);
/* 101 */     setGoalTarget((EntityLiving)null, EntityTargetEvent.TargetReason.FORGOT_TARGET, true);
/* 102 */     setAnger(0);
/*     */   }
/*     */   
/*     */   void setLastDamager(@Nullable EntityLiving paramEntityLiving);
/*     */   
/*     */   void e(@Nullable EntityHuman paramEntityHuman);
/*     */   
/*     */   void setGoalTarget(@Nullable EntityLiving paramEntityLiving);
/*     */   
/*     */   boolean setGoalTarget(EntityLiving paramEntityLiving, EntityTargetEvent.TargetReason paramTargetReason, boolean paramBoolean);
/*     */   
/*     */   @Nullable
/*     */   EntityLiving getGoalTarget();
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\IEntityAngerable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
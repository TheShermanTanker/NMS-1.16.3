/*     */ package org.bukkit.event.entity;
/*     */ 
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.entity.LivingEntity;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CreatureSpawnEvent
/*     */   extends EntitySpawnEvent
/*     */ {
/*     */   private final SpawnReason spawnReason;
/*     */   
/*     */   public CreatureSpawnEvent(@NotNull LivingEntity spawnee, @NotNull SpawnReason spawnReason) {
/*  17 */     super((Entity)spawnee);
/*  18 */     this.spawnReason = spawnReason;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public LivingEntity getEntity() {
/*  24 */     return (LivingEntity)this.entity;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public SpawnReason getSpawnReason() {
/*  35 */     return this.spawnReason;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public enum SpawnReason
/*     */   {
/*  46 */     NATURAL,
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  51 */     JOCKEY,
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  60 */     CHUNK_GEN,
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  65 */     SPAWNER,
/*     */ 
/*     */ 
/*     */     
/*  69 */     EGG,
/*     */ 
/*     */ 
/*     */     
/*  73 */     SPAWNER_EGG,
/*     */ 
/*     */ 
/*     */     
/*  77 */     LIGHTNING,
/*     */ 
/*     */ 
/*     */     
/*  81 */     BUILD_SNOWMAN,
/*     */ 
/*     */ 
/*     */     
/*  85 */     BUILD_IRONGOLEM,
/*     */ 
/*     */ 
/*     */     
/*  89 */     BUILD_WITHER,
/*     */ 
/*     */ 
/*     */     
/*  93 */     VILLAGE_DEFENSE,
/*     */ 
/*     */ 
/*     */     
/*  97 */     VILLAGE_INVASION,
/*     */ 
/*     */ 
/*     */     
/* 101 */     BREEDING,
/*     */ 
/*     */ 
/*     */     
/* 105 */     SLIME_SPLIT,
/*     */ 
/*     */ 
/*     */     
/* 109 */     REINFORCEMENTS,
/*     */ 
/*     */ 
/*     */     
/* 113 */     NETHER_PORTAL,
/*     */ 
/*     */ 
/*     */     
/* 117 */     DISPENSE_EGG,
/*     */ 
/*     */ 
/*     */     
/* 121 */     INFECTION,
/*     */ 
/*     */ 
/*     */     
/* 125 */     CURED,
/*     */ 
/*     */ 
/*     */     
/* 129 */     OCELOT_BABY,
/*     */ 
/*     */ 
/*     */     
/* 133 */     SILVERFISH_BLOCK,
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 138 */     MOUNT,
/*     */ 
/*     */ 
/*     */     
/* 142 */     TRAP,
/*     */ 
/*     */ 
/*     */     
/* 146 */     ENDER_PEARL,
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 151 */     SHOULDER_ENTITY,
/*     */ 
/*     */ 
/*     */     
/* 155 */     DROWNED,
/*     */ 
/*     */ 
/*     */     
/* 159 */     SHEARED,
/*     */ 
/*     */ 
/*     */     
/* 163 */     EXPLOSION,
/*     */ 
/*     */ 
/*     */     
/* 167 */     RAID,
/*     */ 
/*     */ 
/*     */     
/* 171 */     PATROL,
/*     */ 
/*     */ 
/*     */     
/* 175 */     BEEHIVE,
/*     */ 
/*     */ 
/*     */     
/* 179 */     CUSTOM,
/*     */ 
/*     */ 
/*     */     
/* 183 */     DEFAULT;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\entity\CreatureSpawnEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
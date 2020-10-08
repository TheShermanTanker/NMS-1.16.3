/*     */ package org.bukkit.craftbukkit.v1_16_R2.block;
/*     */ 
/*     */ import com.google.common.base.Preconditions;
/*     */ import net.minecraft.server.v1_16_R2.EntityTypes;
/*     */ import net.minecraft.server.v1_16_R2.MinecraftKey;
/*     */ import net.minecraft.server.v1_16_R2.TileEntityMobSpawner;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.block.CreatureSpawner;
/*     */ import org.bukkit.entity.EntityType;
/*     */ 
/*     */ public class CraftCreatureSpawner
/*     */   extends CraftBlockEntityState<TileEntityMobSpawner> implements CreatureSpawner {
/*     */   public CraftCreatureSpawner(Block block) {
/*  15 */     super(block, TileEntityMobSpawner.class);
/*     */   }
/*     */   
/*     */   public CraftCreatureSpawner(Material material, TileEntityMobSpawner te) {
/*  19 */     super(material, te);
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityType getSpawnedType() {
/*  24 */     MinecraftKey key = getSnapshot().getSpawner().getMobName();
/*  25 */     return (key == null) ? EntityType.PIG : EntityType.fromName(key.getKey());
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSpawnedType(EntityType entityType) {
/*  30 */     if (entityType == null || entityType.getName() == null) {
/*  31 */       throw new IllegalArgumentException("Can't spawn EntityType " + entityType + " from mobspawners!");
/*     */     }
/*     */     
/*  34 */     getSnapshot().getSpawner().setMobName(EntityTypes.a(entityType.getName()).get());
/*     */   }
/*     */ 
/*     */   
/*     */   public String getCreatureTypeName() {
/*  39 */     return getSnapshot().getSpawner().getMobName().getKey();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCreatureTypeByName(String creatureType) {
/*  45 */     EntityType type = EntityType.fromName(creatureType);
/*  46 */     if (type == null) {
/*     */       return;
/*     */     }
/*  49 */     setSpawnedType(type);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getDelay() {
/*  54 */     return (getSnapshot().getSpawner()).spawnDelay;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDelay(int delay) {
/*  59 */     (getSnapshot().getSpawner()).spawnDelay = delay;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMinSpawnDelay() {
/*  64 */     return (getSnapshot().getSpawner()).minSpawnDelay;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMinSpawnDelay(int spawnDelay) {
/*  69 */     Preconditions.checkArgument((spawnDelay <= getMaxSpawnDelay()), "Minimum Spawn Delay must be less than or equal to Maximum Spawn Delay");
/*  70 */     (getSnapshot().getSpawner()).minSpawnDelay = spawnDelay;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMaxSpawnDelay() {
/*  75 */     return (getSnapshot().getSpawner()).maxSpawnDelay;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMaxSpawnDelay(int spawnDelay) {
/*  80 */     Preconditions.checkArgument((spawnDelay > 0), "Maximum Spawn Delay must be greater than 0.");
/*  81 */     Preconditions.checkArgument((spawnDelay >= getMinSpawnDelay()), "Maximum Spawn Delay must be greater than or equal to Minimum Spawn Delay");
/*  82 */     (getSnapshot().getSpawner()).maxSpawnDelay = spawnDelay;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMaxNearbyEntities() {
/*  87 */     return (getSnapshot().getSpawner()).maxNearbyEntities;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMaxNearbyEntities(int maxNearbyEntities) {
/*  92 */     (getSnapshot().getSpawner()).maxNearbyEntities = maxNearbyEntities;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getSpawnCount() {
/*  97 */     return (getSnapshot().getSpawner()).spawnCount;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSpawnCount(int count) {
/* 102 */     (getSnapshot().getSpawner()).spawnCount = count;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getRequiredPlayerRange() {
/* 107 */     return (getSnapshot().getSpawner()).requiredPlayerRange;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRequiredPlayerRange(int requiredPlayerRange) {
/* 112 */     (getSnapshot().getSpawner()).requiredPlayerRange = requiredPlayerRange;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getSpawnRange() {
/* 117 */     return (getSnapshot().getSpawner()).spawnRange;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSpawnRange(int spawnRange) {
/* 122 */     (getSnapshot().getSpawner()).spawnRange = spawnRange;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isActivated() {
/* 128 */     return getSnapshot().getSpawner().isActivated();
/*     */   }
/*     */ 
/*     */   
/*     */   public void resetTimer() {
/* 133 */     getSnapshot().getSpawner().resetTimer();
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\CraftCreatureSpawner.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
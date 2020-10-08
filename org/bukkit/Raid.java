/*     */ package org.bukkit;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import java.util.UUID;
/*     */ import org.bukkit.entity.Raider;
/*     */ import org.jetbrains.annotations.NotNull;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public interface Raid
/*     */ {
/*     */   boolean isStarted();
/*     */   
/*     */   long getActiveTicks();
/*     */   
/*     */   int getBadOmenLevel();
/*     */   
/*     */   void setBadOmenLevel(int paramInt);
/*     */   
/*     */   @NotNull
/*     */   Location getLocation();
/*     */   
/*     */   @NotNull
/*     */   RaidStatus getStatus();
/*     */   
/*     */   int getSpawnedGroups();
/*     */   
/*     */   int getTotalGroups();
/*     */   
/*     */   int getTotalWaves();
/*     */   
/*     */   float getTotalHealth();
/*     */   
/*     */   @NotNull
/*     */   Set<UUID> getHeroes();
/*     */   
/*     */   @NotNull
/*     */   List<Raider> getRaiders();
/*     */   
/*     */   public enum RaidStatus
/*     */   {
/* 120 */     ONGOING,
/*     */ 
/*     */ 
/*     */     
/* 124 */     VICTORY,
/*     */ 
/*     */ 
/*     */     
/* 128 */     LOSS,
/*     */ 
/*     */ 
/*     */     
/* 132 */     STOPPED;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\Raid.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
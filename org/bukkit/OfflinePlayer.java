/*     */ package org.bukkit;
/*     */ 
/*     */ import java.util.Date;
/*     */ import java.util.UUID;
/*     */ import org.bukkit.configuration.serialization.ConfigurationSerializable;
/*     */ import org.bukkit.entity.AnimalTamer;
/*     */ import org.bukkit.entity.EntityType;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.permissions.ServerOperator;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
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
/*     */ public interface OfflinePlayer
/*     */   extends ServerOperator, AnimalTamer, ConfigurationSerializable
/*     */ {
/*     */   boolean isOnline();
/*     */   
/*     */   @Nullable
/*     */   String getName();
/*     */   
/*     */   @NotNull
/*     */   UUID getUniqueId();
/*     */   
/*     */   boolean isBanned();
/*     */   
/*     */   @NotNull
/*     */   default BanEntry banPlayer(@Nullable String reason) {
/*  58 */     return banPlayer(reason, null, null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   default BanEntry banPlayer(@Nullable String reason, @Nullable String source) {
/*  69 */     return banPlayer(reason, null, source);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   default BanEntry banPlayer(@Nullable String reason, @Nullable Date expires) {
/*  80 */     return banPlayer(reason, expires, null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   default BanEntry banPlayer(@Nullable String reason, @Nullable Date expires, @Nullable String source) {
/*  92 */     return banPlayer(reason, expires, source, true);
/*     */   }
/*     */   @NotNull
/*     */   default BanEntry banPlayer(@Nullable String reason, @Nullable Date expires, @Nullable String source, boolean kickIfOnline) {
/*  96 */     BanEntry banEntry = Bukkit.getServer().getBanList(BanList.Type.NAME).addBan(getName(), reason, expires, source);
/*  97 */     if (kickIfOnline && isOnline()) {
/*  98 */       getPlayer().kickPlayer(reason);
/*     */     }
/* 100 */     return banEntry;
/*     */   }
/*     */   
/*     */   boolean isWhitelisted();
/*     */   
/*     */   void setWhitelisted(boolean paramBoolean);
/*     */   
/*     */   @Nullable
/*     */   Player getPlayer();
/*     */   
/*     */   long getFirstPlayed();
/*     */   
/*     */   @Deprecated
/*     */   long getLastPlayed();
/*     */   
/*     */   boolean hasPlayedBefore();
/*     */   
/*     */   @Nullable
/*     */   Location getBedSpawnLocation();
/*     */   
/*     */   long getLastLogin();
/*     */   
/*     */   long getLastSeen();
/*     */   
/*     */   void incrementStatistic(@NotNull Statistic paramStatistic) throws IllegalArgumentException;
/*     */   
/*     */   void decrementStatistic(@NotNull Statistic paramStatistic) throws IllegalArgumentException;
/*     */   
/*     */   void incrementStatistic(@NotNull Statistic paramStatistic, int paramInt) throws IllegalArgumentException;
/*     */   
/*     */   void decrementStatistic(@NotNull Statistic paramStatistic, int paramInt) throws IllegalArgumentException;
/*     */   
/*     */   void setStatistic(@NotNull Statistic paramStatistic, int paramInt) throws IllegalArgumentException;
/*     */   
/*     */   int getStatistic(@NotNull Statistic paramStatistic) throws IllegalArgumentException;
/*     */   
/*     */   void incrementStatistic(@NotNull Statistic paramStatistic, @NotNull Material paramMaterial) throws IllegalArgumentException;
/*     */   
/*     */   void decrementStatistic(@NotNull Statistic paramStatistic, @NotNull Material paramMaterial) throws IllegalArgumentException;
/*     */   
/*     */   int getStatistic(@NotNull Statistic paramStatistic, @NotNull Material paramMaterial) throws IllegalArgumentException;
/*     */   
/*     */   void incrementStatistic(@NotNull Statistic paramStatistic, @NotNull Material paramMaterial, int paramInt) throws IllegalArgumentException;
/*     */   
/*     */   void decrementStatistic(@NotNull Statistic paramStatistic, @NotNull Material paramMaterial, int paramInt) throws IllegalArgumentException;
/*     */   
/*     */   void setStatistic(@NotNull Statistic paramStatistic, @NotNull Material paramMaterial, int paramInt) throws IllegalArgumentException;
/*     */   
/*     */   void incrementStatistic(@NotNull Statistic paramStatistic, @NotNull EntityType paramEntityType) throws IllegalArgumentException;
/*     */   
/*     */   void decrementStatistic(@NotNull Statistic paramStatistic, @NotNull EntityType paramEntityType) throws IllegalArgumentException;
/*     */   
/*     */   int getStatistic(@NotNull Statistic paramStatistic, @NotNull EntityType paramEntityType) throws IllegalArgumentException;
/*     */   
/*     */   void incrementStatistic(@NotNull Statistic paramStatistic, @NotNull EntityType paramEntityType, int paramInt) throws IllegalArgumentException;
/*     */   
/*     */   void decrementStatistic(@NotNull Statistic paramStatistic, @NotNull EntityType paramEntityType, int paramInt);
/*     */   
/*     */   void setStatistic(@NotNull Statistic paramStatistic, @NotNull EntityType paramEntityType, int paramInt);
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\OfflinePlayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
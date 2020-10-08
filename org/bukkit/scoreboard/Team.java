/*     */ package org.bukkit.scoreboard;
/*     */ 
/*     */ import java.util.Set;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.OfflinePlayer;
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
/*     */ public interface Team
/*     */ {
/*     */   @NotNull
/*     */   String getName() throws IllegalStateException;
/*     */   
/*     */   @NotNull
/*     */   String getDisplayName() throws IllegalStateException;
/*     */   
/*     */   void setDisplayName(@NotNull String paramString) throws IllegalStateException, IllegalArgumentException;
/*     */   
/*     */   @NotNull
/*     */   String getPrefix() throws IllegalStateException;
/*     */   
/*     */   void setPrefix(@NotNull String paramString) throws IllegalStateException, IllegalArgumentException;
/*     */   
/*     */   @NotNull
/*     */   String getSuffix() throws IllegalStateException;
/*     */   
/*     */   void setSuffix(@NotNull String paramString) throws IllegalStateException, IllegalArgumentException;
/*     */   
/*     */   @NotNull
/*     */   ChatColor getColor() throws IllegalStateException;
/*     */   
/*     */   void setColor(@NotNull ChatColor paramChatColor);
/*     */   
/*     */   boolean allowFriendlyFire() throws IllegalStateException;
/*     */   
/*     */   void setAllowFriendlyFire(boolean paramBoolean) throws IllegalStateException;
/*     */   
/*     */   boolean canSeeFriendlyInvisibles() throws IllegalStateException;
/*     */   
/*     */   void setCanSeeFriendlyInvisibles(boolean paramBoolean) throws IllegalStateException;
/*     */   
/*     */   @Deprecated
/*     */   @NotNull
/*     */   NameTagVisibility getNameTagVisibility() throws IllegalArgumentException;
/*     */   
/*     */   @Deprecated
/*     */   void setNameTagVisibility(@NotNull NameTagVisibility paramNameTagVisibility) throws IllegalArgumentException;
/*     */   
/*     */   @Deprecated
/*     */   @NotNull
/*     */   Set<OfflinePlayer> getPlayers() throws IllegalStateException;
/*     */   
/*     */   @NotNull
/*     */   Set<String> getEntries() throws IllegalStateException;
/*     */   
/*     */   int getSize() throws IllegalStateException;
/*     */   
/*     */   @Nullable
/*     */   Scoreboard getScoreboard();
/*     */   
/*     */   @Deprecated
/*     */   void addPlayer(@NotNull OfflinePlayer paramOfflinePlayer) throws IllegalStateException, IllegalArgumentException;
/*     */   
/*     */   void addEntry(@NotNull String paramString) throws IllegalStateException, IllegalArgumentException;
/*     */   
/*     */   @Deprecated
/*     */   boolean removePlayer(@NotNull OfflinePlayer paramOfflinePlayer) throws IllegalStateException, IllegalArgumentException;
/*     */   
/*     */   boolean removeEntry(@NotNull String paramString) throws IllegalStateException, IllegalArgumentException;
/*     */   
/*     */   void unregister() throws IllegalStateException;
/*     */   
/*     */   @Deprecated
/*     */   boolean hasPlayer(@NotNull OfflinePlayer paramOfflinePlayer) throws IllegalArgumentException, IllegalStateException;
/*     */   
/*     */   boolean hasEntry(@NotNull String paramString) throws IllegalArgumentException, IllegalStateException;
/*     */   
/*     */   @NotNull
/*     */   OptionStatus getOption(@NotNull Option paramOption) throws IllegalStateException;
/*     */   
/*     */   void setOption(@NotNull Option paramOption, @NotNull OptionStatus paramOptionStatus) throws IllegalStateException;
/*     */   
/*     */   public enum Option
/*     */   {
/* 306 */     NAME_TAG_VISIBILITY,
/*     */ 
/*     */ 
/*     */     
/* 310 */     DEATH_MESSAGE_VISIBILITY,
/*     */ 
/*     */ 
/*     */     
/* 314 */     COLLISION_RULE;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public enum OptionStatus
/*     */   {
/* 325 */     ALWAYS,
/*     */ 
/*     */ 
/*     */     
/* 329 */     NEVER,
/*     */ 
/*     */ 
/*     */     
/* 333 */     FOR_OTHER_TEAMS,
/*     */ 
/*     */ 
/*     */     
/* 337 */     FOR_OWN_TEAM;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\scoreboard\Team.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
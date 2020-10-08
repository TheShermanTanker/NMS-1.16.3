/*     */ package org.bukkit.craftbukkit.v1_16_R2.scoreboard;
/*     */ 
/*     */ import com.google.common.collect.ImmutableSet;
/*     */ import java.util.Set;
/*     */ import net.minecraft.server.v1_16_R2.ScoreboardTeam;
/*     */ import net.minecraft.server.v1_16_R2.ScoreboardTeamBase;
/*     */ import org.apache.commons.lang.Validate;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.OfflinePlayer;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.util.CraftChatMessage;
/*     */ import org.bukkit.scoreboard.NameTagVisibility;
/*     */ import org.bukkit.scoreboard.Scoreboard;
/*     */ import org.bukkit.scoreboard.Team;
/*     */ 
/*     */ final class CraftTeam extends CraftScoreboardComponent implements Team {
/*     */   private final ScoreboardTeam team;
/*     */   
/*     */   CraftTeam(CraftScoreboard scoreboard, ScoreboardTeam team) {
/*  20 */     super(scoreboard);
/*  21 */     this.team = team;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getName() throws IllegalStateException {
/*  26 */     CraftScoreboard scoreboard = checkState();
/*     */     
/*  28 */     return this.team.getName();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDisplayName() throws IllegalStateException {
/*  33 */     CraftScoreboard scoreboard = checkState();
/*     */     
/*  35 */     return CraftChatMessage.fromComponent(this.team.getDisplayName());
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDisplayName(String displayName) throws IllegalStateException {
/*  40 */     Validate.notNull(displayName, "Display name cannot be null");
/*  41 */     Validate.isTrue((ChatColor.stripColor(displayName).length() <= 128), "Display name '" + displayName + "' is longer than the limit of 128 characters");
/*  42 */     CraftScoreboard scoreboard = checkState();
/*     */     
/*  44 */     this.team.setDisplayName(CraftChatMessage.fromString(displayName)[0]);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getPrefix() throws IllegalStateException {
/*  49 */     CraftScoreboard scoreboard = checkState();
/*     */     
/*  51 */     return CraftChatMessage.fromComponent(this.team.getPrefix());
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPrefix(String prefix) throws IllegalStateException, IllegalArgumentException {
/*  56 */     Validate.notNull(prefix, "Prefix cannot be null");
/*  57 */     Validate.isTrue((ChatColor.stripColor(prefix).length() <= 64), "Prefix '" + prefix + "' is longer than the limit of 64 characters");
/*  58 */     CraftScoreboard scoreboard = checkState();
/*     */     
/*  60 */     this.team.setPrefix(CraftChatMessage.fromStringOrNull(prefix));
/*     */   }
/*     */ 
/*     */   
/*     */   public String getSuffix() throws IllegalStateException {
/*  65 */     CraftScoreboard scoreboard = checkState();
/*     */     
/*  67 */     return CraftChatMessage.fromComponent(this.team.getSuffix());
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSuffix(String suffix) throws IllegalStateException, IllegalArgumentException {
/*  72 */     Validate.notNull(suffix, "Suffix cannot be null");
/*  73 */     Validate.isTrue((ChatColor.stripColor(suffix).length() <= 64), "Suffix '" + suffix + "' is longer than the limit of 64 characters");
/*  74 */     CraftScoreboard scoreboard = checkState();
/*     */     
/*  76 */     this.team.setSuffix(CraftChatMessage.fromStringOrNull(suffix));
/*     */   }
/*     */ 
/*     */   
/*     */   public ChatColor getColor() throws IllegalStateException {
/*  81 */     CraftScoreboard scoreboard = checkState();
/*     */     
/*  83 */     return CraftChatMessage.getColor(this.team.getColor());
/*     */   }
/*     */ 
/*     */   
/*     */   public void setColor(ChatColor color) {
/*  88 */     Validate.notNull(color, "Color cannot be null");
/*  89 */     CraftScoreboard scoreboard = checkState();
/*     */     
/*  91 */     this.team.setColor(CraftChatMessage.getColor(color));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean allowFriendlyFire() throws IllegalStateException {
/*  96 */     CraftScoreboard scoreboard = checkState();
/*     */     
/*  98 */     return this.team.allowFriendlyFire();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAllowFriendlyFire(boolean enabled) throws IllegalStateException {
/* 103 */     CraftScoreboard scoreboard = checkState();
/*     */     
/* 105 */     this.team.setAllowFriendlyFire(enabled);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canSeeFriendlyInvisibles() throws IllegalStateException {
/* 110 */     CraftScoreboard scoreboard = checkState();
/*     */     
/* 112 */     return this.team.canSeeFriendlyInvisibles();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCanSeeFriendlyInvisibles(boolean enabled) throws IllegalStateException {
/* 117 */     CraftScoreboard scoreboard = checkState();
/*     */     
/* 119 */     this.team.setCanSeeFriendlyInvisibles(enabled);
/*     */   }
/*     */ 
/*     */   
/*     */   public NameTagVisibility getNameTagVisibility() throws IllegalArgumentException {
/* 124 */     CraftScoreboard scoreboard = checkState();
/*     */     
/* 126 */     return notchToBukkit(this.team.getNameTagVisibility());
/*     */   }
/*     */ 
/*     */   
/*     */   public void setNameTagVisibility(NameTagVisibility visibility) throws IllegalArgumentException {
/* 131 */     CraftScoreboard scoreboard = checkState();
/*     */     
/* 133 */     this.team.setNameTagVisibility(bukkitToNotch(visibility));
/*     */   }
/*     */ 
/*     */   
/*     */   public Set<OfflinePlayer> getPlayers() throws IllegalStateException {
/* 138 */     CraftScoreboard scoreboard = checkState();
/*     */     
/* 140 */     ImmutableSet.Builder<OfflinePlayer> players = ImmutableSet.builder();
/* 141 */     for (String playerName : this.team.getPlayerNameSet()) {
/* 142 */       players.add(Bukkit.getOfflinePlayer(playerName));
/*     */     }
/* 144 */     return (Set<OfflinePlayer>)players.build();
/*     */   }
/*     */ 
/*     */   
/*     */   public Set<String> getEntries() throws IllegalStateException {
/* 149 */     CraftScoreboard scoreboard = checkState();
/*     */     
/* 151 */     ImmutableSet.Builder<String> entries = ImmutableSet.builder();
/* 152 */     for (String playerName : this.team.getPlayerNameSet()) {
/* 153 */       entries.add(playerName);
/*     */     }
/* 155 */     return (Set<String>)entries.build();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getSize() throws IllegalStateException {
/* 160 */     CraftScoreboard scoreboard = checkState();
/*     */     
/* 162 */     return this.team.getPlayerNameSet().size();
/*     */   }
/*     */ 
/*     */   
/*     */   public void addPlayer(OfflinePlayer player) throws IllegalStateException, IllegalArgumentException {
/* 167 */     Validate.notNull(player, "OfflinePlayer cannot be null");
/* 168 */     addEntry(player.getName());
/*     */   }
/*     */ 
/*     */   
/*     */   public void addEntry(String entry) throws IllegalStateException, IllegalArgumentException {
/* 173 */     Validate.notNull(entry, "Entry cannot be null");
/* 174 */     CraftScoreboard scoreboard = checkState();
/*     */     
/* 176 */     scoreboard.board.addPlayerToTeam(entry, this.team);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean removePlayer(OfflinePlayer player) throws IllegalStateException, IllegalArgumentException {
/* 181 */     Validate.notNull(player, "OfflinePlayer cannot be null");
/* 182 */     return removeEntry(player.getName());
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean removeEntry(String entry) throws IllegalStateException, IllegalArgumentException {
/* 187 */     Validate.notNull(entry, "Entry cannot be null");
/* 188 */     CraftScoreboard scoreboard = checkState();
/*     */     
/* 190 */     if (!this.team.getPlayerNameSet().contains(entry)) {
/* 191 */       return false;
/*     */     }
/*     */     
/* 194 */     scoreboard.board.removePlayerFromTeam(entry, this.team);
/* 195 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasPlayer(OfflinePlayer player) throws IllegalArgumentException, IllegalStateException {
/* 200 */     Validate.notNull(player, "OfflinePlayer cannot be null");
/* 201 */     return hasEntry(player.getName());
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasEntry(String entry) throws IllegalArgumentException, IllegalStateException {
/* 206 */     Validate.notNull(entry, "Entry cannot be null");
/*     */     
/* 208 */     CraftScoreboard scoreboard = checkState();
/*     */     
/* 210 */     return this.team.getPlayerNameSet().contains(entry);
/*     */   }
/*     */ 
/*     */   
/*     */   public void unregister() throws IllegalStateException {
/* 215 */     CraftScoreboard scoreboard = checkState();
/*     */     
/* 217 */     scoreboard.board.removeTeam(this.team);
/*     */   }
/*     */ 
/*     */   
/*     */   public Team.OptionStatus getOption(Team.Option option) throws IllegalStateException {
/* 222 */     checkState();
/*     */     
/* 224 */     switch (option) {
/*     */       case ALWAYS:
/* 226 */         return Team.OptionStatus.values()[this.team.getNameTagVisibility().ordinal()];
/*     */       case NEVER:
/* 228 */         return Team.OptionStatus.values()[this.team.getDeathMessageVisibility().ordinal()];
/*     */       case HIDE_FOR_OTHER_TEAMS:
/* 230 */         return Team.OptionStatus.values()[this.team.getCollisionRule().ordinal()];
/*     */     } 
/* 232 */     throw new IllegalArgumentException("Unrecognised option " + option);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setOption(Team.Option option, Team.OptionStatus status) throws IllegalStateException {
/* 238 */     checkState();
/*     */     
/* 240 */     switch (option) {
/*     */       case ALWAYS:
/* 242 */         this.team.setNameTagVisibility(ScoreboardTeamBase.EnumNameTagVisibility.values()[status.ordinal()]);
/*     */         return;
/*     */       case NEVER:
/* 245 */         this.team.setDeathMessageVisibility(ScoreboardTeamBase.EnumNameTagVisibility.values()[status.ordinal()]);
/*     */         return;
/*     */       case HIDE_FOR_OTHER_TEAMS:
/* 248 */         this.team.setCollisionRule(ScoreboardTeamBase.EnumTeamPush.values()[status.ordinal()]);
/*     */         return;
/*     */     } 
/* 251 */     throw new IllegalArgumentException("Unrecognised option " + option);
/*     */   }
/*     */ 
/*     */   
/*     */   public static ScoreboardTeamBase.EnumNameTagVisibility bukkitToNotch(NameTagVisibility visibility) {
/* 256 */     switch (visibility) {
/*     */       case ALWAYS:
/* 258 */         return ScoreboardTeamBase.EnumNameTagVisibility.ALWAYS;
/*     */       case NEVER:
/* 260 */         return ScoreboardTeamBase.EnumNameTagVisibility.NEVER;
/*     */       case HIDE_FOR_OTHER_TEAMS:
/* 262 */         return ScoreboardTeamBase.EnumNameTagVisibility.HIDE_FOR_OTHER_TEAMS;
/*     */       case HIDE_FOR_OWN_TEAM:
/* 264 */         return ScoreboardTeamBase.EnumNameTagVisibility.HIDE_FOR_OWN_TEAM;
/*     */     } 
/* 266 */     throw new IllegalArgumentException("Unknown visibility level " + visibility);
/*     */   }
/*     */ 
/*     */   
/*     */   public static NameTagVisibility notchToBukkit(ScoreboardTeamBase.EnumNameTagVisibility visibility) {
/* 271 */     switch (visibility) {
/*     */       case ALWAYS:
/* 273 */         return NameTagVisibility.ALWAYS;
/*     */       case NEVER:
/* 275 */         return NameTagVisibility.NEVER;
/*     */       case HIDE_FOR_OTHER_TEAMS:
/* 277 */         return NameTagVisibility.HIDE_FOR_OTHER_TEAMS;
/*     */       case HIDE_FOR_OWN_TEAM:
/* 279 */         return NameTagVisibility.HIDE_FOR_OWN_TEAM;
/*     */     } 
/* 281 */     throw new IllegalArgumentException("Unknown visibility level " + visibility);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   CraftScoreboard checkState() throws IllegalStateException {
/* 287 */     if ((getScoreboard()).board.getTeam(this.team.getName()) == null) {
/* 288 */       throw new IllegalStateException("Unregistered scoreboard component");
/*     */     }
/*     */     
/* 291 */     return getScoreboard();
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 296 */     int hash = 7;
/* 297 */     hash = 43 * hash + ((this.team != null) ? this.team.hashCode() : 0);
/* 298 */     return hash;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/* 303 */     if (obj == null) {
/* 304 */       return false;
/*     */     }
/* 306 */     if (getClass() != obj.getClass()) {
/* 307 */       return false;
/*     */     }
/* 309 */     CraftTeam other = (CraftTeam)obj;
/* 310 */     return (this.team == other.team || (this.team != null && this.team.equals(other.team)));
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\scoreboard\CraftTeam.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
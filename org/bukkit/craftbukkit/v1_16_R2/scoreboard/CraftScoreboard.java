/*     */ package org.bukkit.craftbukkit.v1_16_R2.scoreboard;
/*     */ 
/*     */ import com.google.common.base.Function;
/*     */ import com.google.common.collect.ImmutableSet;
/*     */ import com.google.common.collect.Iterables;
/*     */ import java.util.Set;
/*     */ import net.minecraft.server.v1_16_R2.Scoreboard;
/*     */ import net.minecraft.server.v1_16_R2.ScoreboardObjective;
/*     */ import net.minecraft.server.v1_16_R2.ScoreboardTeam;
/*     */ import org.apache.commons.lang.Validate;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.OfflinePlayer;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.util.CraftChatMessage;
/*     */ import org.bukkit.scoreboard.DisplaySlot;
/*     */ import org.bukkit.scoreboard.Objective;
/*     */ import org.bukkit.scoreboard.RenderType;
/*     */ import org.bukkit.scoreboard.Score;
/*     */ import org.bukkit.scoreboard.Scoreboard;
/*     */ import org.bukkit.scoreboard.Team;
/*     */ 
/*     */ public final class CraftScoreboard
/*     */   implements Scoreboard {
/*     */   CraftScoreboard(Scoreboard board) {
/*  24 */     this.board = board;
/*     */   }
/*     */   final Scoreboard board;
/*     */   
/*     */   public CraftObjective registerNewObjective(String name, String criteria) throws IllegalArgumentException {
/*  29 */     return registerNewObjective(name, criteria, name);
/*     */   }
/*     */ 
/*     */   
/*     */   public CraftObjective registerNewObjective(String name, String criteria, String displayName) throws IllegalArgumentException {
/*  34 */     return registerNewObjective(name, criteria, displayName, RenderType.INTEGER);
/*     */   }
/*     */ 
/*     */   
/*     */   public CraftObjective registerNewObjective(String name, String criteria, String displayName, RenderType renderType) throws IllegalArgumentException {
/*  39 */     Validate.notNull(name, "Objective name cannot be null");
/*  40 */     Validate.notNull(criteria, "Criteria cannot be null");
/*  41 */     Validate.notNull(displayName, "Display name cannot be null");
/*  42 */     Validate.notNull(renderType, "RenderType cannot be null");
/*  43 */     Validate.isTrue((name.length() <= 16), "The name '" + name + "' is longer than the limit of 16 characters");
/*  44 */     Validate.isTrue((displayName.length() <= 128), "The display name '" + displayName + "' is longer than the limit of 128 characters");
/*  45 */     Validate.isTrue((this.board.getObjective(name) == null), "An objective of name '" + name + "' already exists");
/*     */     
/*  47 */     CraftCriteria craftCriteria = CraftCriteria.getFromBukkit(criteria);
/*  48 */     ScoreboardObjective objective = this.board.registerObjective(name, craftCriteria.criteria, CraftChatMessage.fromStringOrNull(displayName), CraftScoreboardTranslations.fromBukkitRender(renderType));
/*  49 */     return new CraftObjective(this, objective);
/*     */   }
/*     */ 
/*     */   
/*     */   public Objective getObjective(String name) throws IllegalArgumentException {
/*  54 */     Validate.notNull(name, "Name cannot be null");
/*  55 */     ScoreboardObjective nms = this.board.getObjective(name);
/*  56 */     return (nms == null) ? null : new CraftObjective(this, nms);
/*     */   }
/*     */ 
/*     */   
/*     */   public ImmutableSet<Objective> getObjectivesByCriteria(String criteria) throws IllegalArgumentException {
/*  61 */     Validate.notNull(criteria, "Criteria cannot be null");
/*     */     
/*  63 */     ImmutableSet.Builder<Objective> objectives = ImmutableSet.builder();
/*  64 */     for (ScoreboardObjective netObjective : this.board.getObjectives()) {
/*  65 */       CraftObjective objective = new CraftObjective(this, netObjective);
/*  66 */       if (objective.getCriteria().equals(criteria)) {
/*  67 */         objectives.add(objective);
/*     */       }
/*     */     } 
/*  70 */     return objectives.build();
/*     */   }
/*     */ 
/*     */   
/*     */   public ImmutableSet<Objective> getObjectives() {
/*  75 */     return ImmutableSet.copyOf(Iterables.transform(this.board.getObjectives(), new Function<ScoreboardObjective, Objective>()
/*     */           {
/*     */             public Objective apply(ScoreboardObjective input)
/*     */             {
/*  79 */               return new CraftObjective(CraftScoreboard.this, input);
/*     */             }
/*     */           }));
/*     */   }
/*     */ 
/*     */   
/*     */   public Objective getObjective(DisplaySlot slot) throws IllegalArgumentException {
/*  86 */     Validate.notNull(slot, "Display slot cannot be null");
/*  87 */     ScoreboardObjective objective = this.board.getObjectiveForSlot(CraftScoreboardTranslations.fromBukkitSlot(slot));
/*  88 */     if (objective == null) {
/*  89 */       return null;
/*     */     }
/*  91 */     return new CraftObjective(this, objective);
/*     */   }
/*     */ 
/*     */   
/*     */   public ImmutableSet<Score> getScores(OfflinePlayer player) throws IllegalArgumentException {
/*  96 */     Validate.notNull(player, "OfflinePlayer cannot be null");
/*     */     
/*  98 */     return getScores(player.getName());
/*     */   }
/*     */ 
/*     */   
/*     */   public ImmutableSet<Score> getScores(String entry) throws IllegalArgumentException {
/* 103 */     Validate.notNull(entry, "Entry cannot be null");
/*     */     
/* 105 */     ImmutableSet.Builder<Score> scores = ImmutableSet.builder();
/* 106 */     for (ScoreboardObjective objective : this.board.getObjectives()) {
/* 107 */       scores.add(new CraftScore(new CraftObjective(this, objective), entry));
/*     */     }
/* 109 */     return scores.build();
/*     */   }
/*     */ 
/*     */   
/*     */   public void resetScores(OfflinePlayer player) throws IllegalArgumentException {
/* 114 */     Validate.notNull(player, "OfflinePlayer cannot be null");
/*     */     
/* 116 */     resetScores(player.getName());
/*     */   }
/*     */ 
/*     */   
/*     */   public void resetScores(String entry) throws IllegalArgumentException {
/* 121 */     Validate.notNull(entry, "Entry cannot be null");
/*     */     
/* 123 */     for (ScoreboardObjective objective : this.board.getObjectives()) {
/* 124 */       this.board.resetPlayerScores(entry, objective);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public Team getPlayerTeam(OfflinePlayer player) throws IllegalArgumentException {
/* 130 */     Validate.notNull(player, "OfflinePlayer cannot be null");
/*     */     
/* 132 */     ScoreboardTeam team = this.board.getPlayerTeam(player.getName());
/* 133 */     return (team == null) ? null : new CraftTeam(this, team);
/*     */   }
/*     */ 
/*     */   
/*     */   public Team getEntryTeam(String entry) throws IllegalArgumentException {
/* 138 */     Validate.notNull(entry, "Entry cannot be null");
/*     */     
/* 140 */     ScoreboardTeam team = this.board.getPlayerTeam(entry);
/* 141 */     return (team == null) ? null : new CraftTeam(this, team);
/*     */   }
/*     */ 
/*     */   
/*     */   public Team getTeam(String teamName) throws IllegalArgumentException {
/* 146 */     Validate.notNull(teamName, "Team name cannot be null");
/*     */     
/* 148 */     ScoreboardTeam team = this.board.getTeam(teamName);
/* 149 */     return (team == null) ? null : new CraftTeam(this, team);
/*     */   }
/*     */ 
/*     */   
/*     */   public ImmutableSet<Team> getTeams() {
/* 154 */     return ImmutableSet.copyOf(Iterables.transform(this.board.getTeams(), new Function<ScoreboardTeam, Team>()
/*     */           {
/*     */             public Team apply(ScoreboardTeam input)
/*     */             {
/* 158 */               return new CraftTeam(CraftScoreboard.this, input);
/*     */             }
/*     */           }));
/*     */   }
/*     */ 
/*     */   
/*     */   public Team registerNewTeam(String name) throws IllegalArgumentException {
/* 165 */     Validate.notNull(name, "Team name cannot be null");
/* 166 */     Validate.isTrue((name.length() <= 16), "Team name '" + name + "' is longer than the limit of 16 characters");
/* 167 */     Validate.isTrue((this.board.getTeam(name) == null), "Team name '" + name + "' is already in use");
/*     */     
/* 169 */     return new CraftTeam(this, this.board.createTeam(name));
/*     */   }
/*     */ 
/*     */   
/*     */   public ImmutableSet<OfflinePlayer> getPlayers() {
/* 174 */     ImmutableSet.Builder<OfflinePlayer> players = ImmutableSet.builder();
/* 175 */     for (Object playerName : this.board.getPlayers()) {
/* 176 */       players.add(Bukkit.getOfflinePlayer(playerName.toString()));
/*     */     }
/* 178 */     return players.build();
/*     */   }
/*     */ 
/*     */   
/*     */   public ImmutableSet<String> getEntries() {
/* 183 */     ImmutableSet.Builder<String> entries = ImmutableSet.builder();
/* 184 */     for (Object entry : this.board.getPlayers()) {
/* 185 */       entries.add(entry.toString());
/*     */     }
/* 187 */     return entries.build();
/*     */   }
/*     */ 
/*     */   
/*     */   public void clearSlot(DisplaySlot slot) throws IllegalArgumentException {
/* 192 */     Validate.notNull(slot, "Slot cannot be null");
/* 193 */     this.board.setDisplaySlot(CraftScoreboardTranslations.fromBukkitSlot(slot), null);
/*     */   }
/*     */ 
/*     */   
/*     */   public Scoreboard getHandle() {
/* 198 */     return this.board;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\scoreboard\CraftScoreboard.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
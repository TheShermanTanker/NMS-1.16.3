/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Maps;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.function.Consumer;
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
/*     */ public class Scoreboard
/*     */ {
/*  29 */   private final Map<String, ScoreboardObjective> objectivesByName = Maps.newHashMap();
/*  30 */   private final Map<IScoreboardCriteria, List<ScoreboardObjective>> objectivesByCriteria = Maps.newHashMap();
/*  31 */   private final Map<String, Map<ScoreboardObjective, ScoreboardScore>> playerScores = Maps.newHashMap();
/*  32 */   private final ScoreboardObjective[] displaySlots = new ScoreboardObjective[19];
/*  33 */   private final Map<String, ScoreboardTeam> teamsByName = Maps.newHashMap();
/*  34 */   private final Map<String, ScoreboardTeam> teamsByPlayer = Maps.newHashMap();
/*     */ 
/*     */   
/*     */   private static String[] g;
/*     */ 
/*     */   
/*     */   public ScoreboardObjective c(String var0) {
/*  41 */     return this.objectivesByName.get(var0);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public ScoreboardObjective getObjective(@Nullable String var0) {
/*  46 */     return this.objectivesByName.get(var0);
/*     */   }
/*     */   
/*     */   public ScoreboardObjective registerObjective(String var0, IScoreboardCriteria var1, IChatBaseComponent var2, IScoreboardCriteria.EnumScoreboardHealthDisplay var3) {
/*  50 */     if (var0.length() > 16) {
/*  51 */       throw new IllegalArgumentException("The objective name '" + var0 + "' is too long!");
/*     */     }
/*  53 */     if (this.objectivesByName.containsKey(var0)) {
/*  54 */       throw new IllegalArgumentException("An objective with the name '" + var0 + "' already exists!");
/*     */     }
/*     */     
/*  57 */     ScoreboardObjective var4 = new ScoreboardObjective(this, var0, var1, var2, var3);
/*     */     
/*  59 */     ((List<ScoreboardObjective>)this.objectivesByCriteria.computeIfAbsent(var1, var0 -> Lists.newArrayList())).add(var4);
/*  60 */     this.objectivesByName.put(var0, var4);
/*  61 */     handleObjectiveAdded(var4);
/*  62 */     return var4;
/*     */   }
/*     */   
/*     */   public final void getObjectivesForCriteria(IScoreboardCriteria var0, String var1, Consumer<ScoreboardScore> var2) {
/*  66 */     ((List)this.objectivesByCriteria.getOrDefault(var0, Collections.emptyList())).forEach(var2 -> var0.accept(getPlayerScoreForObjective(var1, var2)));
/*     */   }
/*     */   
/*     */   public boolean b(String var0, ScoreboardObjective var1) {
/*  70 */     Map<ScoreboardObjective, ScoreboardScore> var2 = this.playerScores.get(var0);
/*  71 */     if (var2 == null) {
/*  72 */       return false;
/*     */     }
/*  74 */     ScoreboardScore var3 = var2.get(var1);
/*  75 */     return (var3 != null);
/*     */   }
/*     */   
/*     */   public ScoreboardScore getPlayerScoreForObjective(String var0, ScoreboardObjective var1) {
/*  79 */     if (var0.length() > 40) {
/*  80 */       throw new IllegalArgumentException("The player name '" + var0 + "' is too long!");
/*     */     }
/*  82 */     Map<ScoreboardObjective, ScoreboardScore> var2 = this.playerScores.computeIfAbsent(var0, var0 -> Maps.newHashMap());
/*     */     
/*  84 */     return var2.computeIfAbsent(var1, var1 -> {
/*     */           ScoreboardScore var2 = new ScoreboardScore(this, var1, var0);
/*     */           var2.setScore(0);
/*     */           return var2;
/*     */         });
/*     */   }
/*     */   
/*     */   public Collection<ScoreboardScore> getScoresForObjective(ScoreboardObjective var0) {
/*  92 */     List<ScoreboardScore> var1 = Lists.newArrayList();
/*     */     
/*  94 */     for (Map<ScoreboardObjective, ScoreboardScore> var3 : this.playerScores.values()) {
/*  95 */       ScoreboardScore var4 = var3.get(var0);
/*  96 */       if (var4 != null) {
/*  97 */         var1.add(var4);
/*     */       }
/*     */     } 
/*     */     
/* 101 */     var1.sort(ScoreboardScore.a);
/*     */     
/* 103 */     return var1;
/*     */   }
/*     */   
/*     */   public Collection<ScoreboardObjective> getObjectives() {
/* 107 */     return this.objectivesByName.values();
/*     */   }
/*     */   
/*     */   public Collection<String> d() {
/* 111 */     return this.objectivesByName.keySet();
/*     */   }
/*     */   
/*     */   public Collection<String> getPlayers() {
/* 115 */     return Lists.newArrayList(this.playerScores.keySet());
/*     */   }
/*     */   
/*     */   public void resetPlayerScores(String var0, @Nullable ScoreboardObjective var1) {
/* 119 */     if (var1 == null) {
/* 120 */       Map<ScoreboardObjective, ScoreboardScore> var2 = this.playerScores.remove(var0);
/* 121 */       if (var2 != null) {
/* 122 */         handlePlayerRemoved(var0);
/*     */       }
/*     */     } else {
/* 125 */       Map<ScoreboardObjective, ScoreboardScore> var2 = this.playerScores.get(var0);
/* 126 */       if (var2 != null) {
/* 127 */         ScoreboardScore var3 = var2.remove(var1);
/* 128 */         if (var2.size() < 1) {
/* 129 */           Map<ScoreboardObjective, ScoreboardScore> var4 = this.playerScores.remove(var0);
/* 130 */           if (var4 != null) {
/* 131 */             handlePlayerRemoved(var0);
/*     */           }
/* 133 */         } else if (var3 != null) {
/* 134 */           a(var0, var1);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public Map<ScoreboardObjective, ScoreboardScore> getPlayerObjectives(String var0) {
/* 141 */     Map<ScoreboardObjective, ScoreboardScore> var1 = this.playerScores.get(var0);
/* 142 */     if (var1 == null) {
/* 143 */       var1 = Maps.newHashMap();
/*     */     }
/* 145 */     return var1;
/*     */   }
/*     */   
/*     */   public void unregisterObjective(ScoreboardObjective var0) {
/* 149 */     this.objectivesByName.remove(var0.getName());
/*     */     
/* 151 */     for (int i = 0; i < 19; i++) {
/* 152 */       if (getObjectiveForSlot(i) == var0) {
/* 153 */         setDisplaySlot(i, null);
/*     */       }
/*     */     } 
/*     */     
/* 157 */     List<ScoreboardObjective> var1 = this.objectivesByCriteria.get(var0.getCriteria());
/* 158 */     if (var1 != null) {
/* 159 */       var1.remove(var0);
/*     */     }
/*     */     
/* 162 */     for (Map<ScoreboardObjective, ScoreboardScore> var3 : this.playerScores.values()) {
/* 163 */       var3.remove(var0);
/*     */     }
/*     */     
/* 166 */     handleObjectiveRemoved(var0);
/*     */   }
/*     */   
/*     */   public void setDisplaySlot(int var0, @Nullable ScoreboardObjective var1) {
/* 170 */     this.displaySlots[var0] = var1;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public ScoreboardObjective getObjectiveForSlot(int var0) {
/* 175 */     return this.displaySlots[var0];
/*     */   }
/*     */   
/*     */   public ScoreboardTeam getTeam(String var0) {
/* 179 */     return this.teamsByName.get(var0);
/*     */   }
/*     */   
/*     */   public ScoreboardTeam createTeam(String var0) {
/* 183 */     if (var0.length() > 16) {
/* 184 */       throw new IllegalArgumentException("The team name '" + var0 + "' is too long!");
/*     */     }
/* 186 */     ScoreboardTeam var1 = getTeam(var0);
/* 187 */     if (var1 != null) {
/* 188 */       throw new IllegalArgumentException("A team with the name '" + var0 + "' already exists!");
/*     */     }
/*     */     
/* 191 */     var1 = new ScoreboardTeam(this, var0);
/* 192 */     this.teamsByName.put(var0, var1);
/* 193 */     handleTeamAdded(var1);
/*     */     
/* 195 */     return var1;
/*     */   }
/*     */   
/*     */   public void removeTeam(ScoreboardTeam var0) {
/* 199 */     this.teamsByName.remove(var0.getName());
/*     */ 
/*     */ 
/*     */     
/* 203 */     for (String var2 : var0.getPlayerNameSet()) {
/* 204 */       this.teamsByPlayer.remove(var2);
/*     */     }
/*     */     
/* 207 */     handleTeamRemoved(var0);
/*     */   }
/*     */   
/*     */   public boolean addPlayerToTeam(String var0, ScoreboardTeam var1) {
/* 211 */     if (var0.length() > 40) {
/* 212 */       throw new IllegalArgumentException("The player name '" + var0 + "' is too long!");
/*     */     }
/*     */     
/* 215 */     if (getPlayerTeam(var0) != null) {
/* 216 */       removePlayerFromTeam(var0);
/*     */     }
/*     */     
/* 219 */     this.teamsByPlayer.put(var0, var1);
/* 220 */     return var1.getPlayerNameSet().add(var0);
/*     */   }
/*     */   
/*     */   public boolean removePlayerFromTeam(String var0) {
/* 224 */     ScoreboardTeam var1 = getPlayerTeam(var0);
/*     */     
/* 226 */     if (var1 != null) {
/* 227 */       removePlayerFromTeam(var0, var1);
/* 228 */       return true;
/*     */     } 
/* 230 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void removePlayerFromTeam(String var0, ScoreboardTeam var1) {
/* 235 */     if (getPlayerTeam(var0) != var1) {
/* 236 */       throw new IllegalStateException("Player is either on another team or not on any team. Cannot remove from team '" + var1.getName() + "'.");
/*     */     }
/*     */     
/* 239 */     this.teamsByPlayer.remove(var0);
/* 240 */     var1.getPlayerNameSet().remove(var0);
/*     */   }
/*     */   
/*     */   public Collection<String> f() {
/* 244 */     return this.teamsByName.keySet();
/*     */   }
/*     */   
/*     */   public Collection<ScoreboardTeam> getTeams() {
/* 248 */     return this.teamsByName.values();
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public ScoreboardTeam getPlayerTeam(String var0) {
/* 253 */     return this.teamsByPlayer.get(var0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void handleObjectiveAdded(ScoreboardObjective var0) {}
/*     */ 
/*     */   
/*     */   public void handleObjectiveChanged(ScoreboardObjective var0) {}
/*     */ 
/*     */   
/*     */   public void handleObjectiveRemoved(ScoreboardObjective var0) {}
/*     */ 
/*     */   
/*     */   public void handleScoreChanged(ScoreboardScore var0) {}
/*     */ 
/*     */   
/*     */   public void handlePlayerRemoved(String var0) {}
/*     */ 
/*     */   
/*     */   public void a(String var0, ScoreboardObjective var1) {}
/*     */ 
/*     */   
/*     */   public void handleTeamAdded(ScoreboardTeam var0) {}
/*     */ 
/*     */   
/*     */   public void handleTeamChanged(ScoreboardTeam var0) {}
/*     */ 
/*     */   
/*     */   public void handleTeamRemoved(ScoreboardTeam var0) {}
/*     */   
/*     */   public static String getSlotName(int var0) {
/* 284 */     switch (var0) {
/*     */       case 0:
/* 286 */         return "list";
/*     */       case 1:
/* 288 */         return "sidebar";
/*     */       case 2:
/* 290 */         return "belowName";
/*     */     } 
/* 292 */     if (var0 >= 3 && var0 <= 18) {
/* 293 */       EnumChatFormat var1 = EnumChatFormat.a(var0 - 3);
/* 294 */       if (var1 != null && var1 != EnumChatFormat.RESET) {
/* 295 */         return "sidebar.team." + var1.f();
/*     */       }
/*     */     } 
/* 298 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getSlotForName(String var0) {
/* 303 */     if ("list".equalsIgnoreCase(var0))
/* 304 */       return 0; 
/* 305 */     if ("sidebar".equalsIgnoreCase(var0))
/* 306 */       return 1; 
/* 307 */     if ("belowName".equalsIgnoreCase(var0)) {
/* 308 */       return 2;
/*     */     }
/* 310 */     if (var0.startsWith("sidebar.team.")) {
/* 311 */       String var1 = var0.substring("sidebar.team.".length());
/* 312 */       EnumChatFormat var2 = EnumChatFormat.b(var1);
/* 313 */       if (var2 != null && var2.b() >= 0) {
/* 314 */         return var2.b() + 3;
/*     */       }
/*     */     } 
/* 317 */     return -1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String[] h() {
/* 324 */     if (g == null) {
/* 325 */       g = new String[19];
/* 326 */       for (int var0 = 0; var0 < 19; var0++) {
/* 327 */         g[var0] = getSlotName(var0);
/*     */       }
/*     */     } 
/* 330 */     return g;
/*     */   }
/*     */   
/*     */   public void a(Entity var0) {
/* 334 */     if (var0 == null || var0 instanceof EntityHuman || var0.isAlive()) {
/*     */       return;
/*     */     }
/* 337 */     String var1 = var0.getUniqueIDString();
/* 338 */     resetPlayerScores(var1, null);
/* 339 */     removePlayerFromTeam(var1);
/*     */   }
/*     */   
/*     */   protected NBTTagList i() {
/* 343 */     NBTTagList var0 = new NBTTagList();
/*     */     
/* 345 */     this.playerScores.values().stream().map(Map::values).forEach(var1 -> var1.stream().filter(()).forEach(()));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 356 */     return var0;
/*     */   }
/*     */   
/*     */   protected void a(NBTTagList var0) {
/* 360 */     for (int var1 = 0; var1 < var0.size(); var1++) {
/* 361 */       NBTTagCompound var2 = var0.getCompound(var1);
/*     */       
/* 363 */       ScoreboardObjective var3 = c(var2.getString("Objective"));
/* 364 */       String var4 = var2.getString("Name");
/* 365 */       if (var4.length() > 40)
/*     */       {
/* 367 */         var4 = var4.substring(0, 40);
/*     */       }
/* 369 */       ScoreboardScore var5 = getPlayerScoreForObjective(var4, var3);
/* 370 */       var5.setScore(var2.getInt("Score"));
/* 371 */       if (var2.hasKey("Locked"))
/* 372 */         var5.a(var2.getBoolean("Locked")); 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\Scoreboard.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
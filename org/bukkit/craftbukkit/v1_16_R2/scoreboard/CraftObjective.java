/*     */ package org.bukkit.craftbukkit.v1_16_R2.scoreboard;
/*     */ 
/*     */ import net.minecraft.server.v1_16_R2.Scoreboard;
/*     */ import net.minecraft.server.v1_16_R2.ScoreboardObjective;
/*     */ import org.apache.commons.lang.Validate;
/*     */ import org.bukkit.OfflinePlayer;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.util.CraftChatMessage;
/*     */ import org.bukkit.scoreboard.DisplaySlot;
/*     */ import org.bukkit.scoreboard.Objective;
/*     */ import org.bukkit.scoreboard.RenderType;
/*     */ import org.bukkit.scoreboard.Score;
/*     */ import org.bukkit.scoreboard.Scoreboard;
/*     */ 
/*     */ final class CraftObjective extends CraftScoreboardComponent implements Objective {
/*     */   private final ScoreboardObjective objective;
/*     */   
/*     */   CraftObjective(CraftScoreboard scoreboard, ScoreboardObjective objective) {
/*  18 */     super(scoreboard);
/*  19 */     this.objective = objective;
/*  20 */     this.criteria = CraftCriteria.getFromNMS(objective);
/*     */   }
/*     */   private final CraftCriteria criteria;
/*     */   ScoreboardObjective getHandle() {
/*  24 */     return this.objective;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getName() throws IllegalStateException {
/*  29 */     CraftScoreboard scoreboard = checkState();
/*     */     
/*  31 */     return this.objective.getName();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDisplayName() throws IllegalStateException {
/*  36 */     CraftScoreboard scoreboard = checkState();
/*     */     
/*  38 */     return CraftChatMessage.fromComponent(this.objective.getDisplayName());
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDisplayName(String displayName) throws IllegalStateException, IllegalArgumentException {
/*  43 */     Validate.notNull(displayName, "Display name cannot be null");
/*  44 */     Validate.isTrue((displayName.length() <= 128), "Display name '" + displayName + "' is longer than the limit of 128 characters");
/*  45 */     CraftScoreboard scoreboard = checkState();
/*     */     
/*  47 */     this.objective.setDisplayName(CraftChatMessage.fromString(displayName)[0]);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getCriteria() throws IllegalStateException {
/*  52 */     CraftScoreboard scoreboard = checkState();
/*     */     
/*  54 */     return this.criteria.bukkitName;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isModifiable() throws IllegalStateException {
/*  59 */     CraftScoreboard scoreboard = checkState();
/*     */     
/*  61 */     return !this.criteria.criteria.isReadOnly();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDisplaySlot(DisplaySlot slot) throws IllegalStateException {
/*  66 */     CraftScoreboard scoreboard = checkState();
/*  67 */     Scoreboard board = scoreboard.board;
/*  68 */     ScoreboardObjective objective = this.objective;
/*     */     
/*  70 */     for (int i = 0; i < 3; i++) {
/*  71 */       if (board.getObjectiveForSlot(i) == objective) {
/*  72 */         board.setDisplaySlot(i, null);
/*     */       }
/*     */     } 
/*  75 */     if (slot != null) {
/*  76 */       int slotNumber = CraftScoreboardTranslations.fromBukkitSlot(slot);
/*  77 */       board.setDisplaySlot(slotNumber, getHandle());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public DisplaySlot getDisplaySlot() throws IllegalStateException {
/*  83 */     CraftScoreboard scoreboard = checkState();
/*  84 */     Scoreboard board = scoreboard.board;
/*  85 */     ScoreboardObjective objective = this.objective;
/*     */     
/*  87 */     for (int i = 0; i < 3; i++) {
/*  88 */       if (board.getObjectiveForSlot(i) == objective) {
/*  89 */         return CraftScoreboardTranslations.toBukkitSlot(i);
/*     */       }
/*     */     } 
/*  92 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRenderType(RenderType renderType) throws IllegalStateException {
/*  97 */     Validate.notNull(renderType, "RenderType cannot be null");
/*  98 */     CraftScoreboard scoreboard = checkState();
/*     */     
/* 100 */     this.objective.setRenderType(CraftScoreboardTranslations.fromBukkitRender(renderType));
/*     */   }
/*     */ 
/*     */   
/*     */   public RenderType getRenderType() throws IllegalStateException {
/* 105 */     CraftScoreboard scoreboard = checkState();
/*     */     
/* 107 */     return CraftScoreboardTranslations.toBukkitRender(this.objective.getRenderType());
/*     */   }
/*     */ 
/*     */   
/*     */   public Score getScore(OfflinePlayer player) throws IllegalArgumentException, IllegalStateException {
/* 112 */     Validate.notNull(player, "Player cannot be null");
/* 113 */     CraftScoreboard scoreboard = checkState();
/*     */     
/* 115 */     return new CraftScore(this, player.getName());
/*     */   }
/*     */ 
/*     */   
/*     */   public Score getScore(String entry) throws IllegalArgumentException, IllegalStateException {
/* 120 */     Validate.notNull(entry, "Entry cannot be null");
/* 121 */     Validate.isTrue((entry.length() <= 40), "Score '" + entry + "' is longer than the limit of 40 characters");
/* 122 */     CraftScoreboard scoreboard = checkState();
/*     */     
/* 124 */     return new CraftScore(this, entry);
/*     */   }
/*     */ 
/*     */   
/*     */   public void unregister() throws IllegalStateException {
/* 129 */     CraftScoreboard scoreboard = checkState();
/*     */     
/* 131 */     scoreboard.board.unregisterObjective(this.objective);
/*     */   }
/*     */ 
/*     */   
/*     */   CraftScoreboard checkState() throws IllegalStateException {
/* 136 */     if ((getScoreboard()).board.getObjective(this.objective.getName()) == null) {
/* 137 */       throw new IllegalStateException("Unregistered scoreboard component");
/*     */     }
/*     */     
/* 140 */     return getScoreboard();
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 145 */     int hash = 7;
/* 146 */     hash = 31 * hash + ((this.objective != null) ? this.objective.hashCode() : 0);
/* 147 */     return hash;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/* 152 */     if (obj == null) {
/* 153 */       return false;
/*     */     }
/* 155 */     if (getClass() != obj.getClass()) {
/* 156 */       return false;
/*     */     }
/* 158 */     CraftObjective other = (CraftObjective)obj;
/* 159 */     return (this.objective == other.objective || (this.objective != null && this.objective.equals(other.objective)));
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\scoreboard\CraftObjective.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
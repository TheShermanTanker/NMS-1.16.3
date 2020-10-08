/*    */ package org.bukkit.craftbukkit.v1_16_R2.scoreboard;
/*    */ 
/*    */ import java.util.Map;
/*    */ import net.minecraft.server.v1_16_R2.Scoreboard;
/*    */ import net.minecraft.server.v1_16_R2.ScoreboardObjective;
/*    */ import net.minecraft.server.v1_16_R2.ScoreboardScore;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.OfflinePlayer;
/*    */ import org.bukkit.scoreboard.Objective;
/*    */ import org.bukkit.scoreboard.Score;
/*    */ import org.bukkit.scoreboard.Scoreboard;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ final class CraftScore
/*    */   implements Score
/*    */ {
/*    */   private final String entry;
/*    */   private final CraftObjective objective;
/*    */   
/*    */   CraftScore(CraftObjective objective, String entry) {
/* 23 */     this.objective = objective;
/* 24 */     this.entry = entry;
/*    */   }
/*    */ 
/*    */   
/*    */   public OfflinePlayer getPlayer() {
/* 29 */     return Bukkit.getOfflinePlayer(this.entry);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getEntry() {
/* 34 */     return this.entry;
/*    */   }
/*    */ 
/*    */   
/*    */   public Objective getObjective() {
/* 39 */     return this.objective;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getScore() throws IllegalStateException {
/* 44 */     Scoreboard board = (this.objective.checkState()).board;
/*    */     
/* 46 */     if (board.getPlayers().contains(this.entry)) {
/* 47 */       Map<ScoreboardObjective, ScoreboardScore> scores = board.getPlayerObjectives(this.entry);
/* 48 */       ScoreboardScore score = scores.get(this.objective.getHandle());
/* 49 */       if (score != null) {
/* 50 */         return score.getScore();
/*    */       }
/*    */     } 
/*    */     
/* 54 */     return 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setScore(int score) throws IllegalStateException {
/* 59 */     (this.objective.checkState()).board.getPlayerScoreForObjective(this.entry, this.objective.getHandle()).setScore(score);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isScoreSet() throws IllegalStateException {
/* 64 */     Scoreboard board = (this.objective.checkState()).board;
/*    */     
/* 66 */     return (board.getPlayers().contains(this.entry) && board.getPlayerObjectives(this.entry).containsKey(this.objective.getHandle()));
/*    */   }
/*    */ 
/*    */   
/*    */   public CraftScoreboard getScoreboard() {
/* 71 */     return this.objective.getScoreboard();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\scoreboard\CraftScore.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
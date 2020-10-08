/*    */ package org.bukkit.craftbukkit.v1_16_R2.scoreboard;
/*    */ 
/*    */ abstract class CraftScoreboardComponent {
/*    */   private CraftScoreboard scoreboard;
/*    */   
/*    */   CraftScoreboardComponent(CraftScoreboard scoreboard) {
/*  7 */     this.scoreboard = scoreboard;
/*    */   }
/*    */   
/*    */   abstract CraftScoreboard checkState() throws IllegalStateException;
/*    */   
/*    */   public CraftScoreboard getScoreboard() {
/* 13 */     return this.scoreboard;
/*    */   }
/*    */   
/*    */   abstract void unregister() throws IllegalStateException;
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\scoreboard\CraftScoreboardComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
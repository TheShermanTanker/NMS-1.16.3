/*    */ package net.minecraft.server.v1_16_R2;
/*    */ import java.util.Comparator;
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ public class ScoreboardScore {
/*    */   static {
/*  7 */     a = ((var0, var1) -> (var0.getScore() > var1.getScore()) ? 1 : ((var0.getScore() < var1.getScore()) ? -1 : var1.getPlayerName().compareToIgnoreCase(var0.getPlayerName())));
/*    */   }
/*    */ 
/*    */   
/*    */   public static final Comparator<ScoreboardScore> a;
/*    */   
/*    */   private final Scoreboard b;
/*    */   
/*    */   @Nullable
/*    */   private final ScoreboardObjective c;
/*    */   
/*    */   private final String playerName;
/*    */   
/*    */   private int score;
/*    */   
/*    */   private boolean f;
/*    */   private boolean g;
/*    */   
/*    */   public ScoreboardScore(Scoreboard var0, ScoreboardObjective var1, String var2) {
/* 26 */     this.b = var0;
/* 27 */     this.c = var1;
/* 28 */     this.playerName = var2;
/* 29 */     this.f = true;
/* 30 */     this.g = true;
/*    */   }
/*    */   
/*    */   public void addScore(int var0) {
/* 34 */     if (this.c.getCriteria().isReadOnly()) {
/* 35 */       throw new IllegalStateException("Cannot modify read-only score");
/*    */     }
/* 37 */     setScore(getScore() + var0);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void incrementScore() {
/* 45 */     addScore(1);
/*    */   }
/*    */   
/*    */   public int getScore() {
/* 49 */     return this.score;
/*    */   }
/*    */   
/*    */   public void c() {
/* 53 */     setScore(0);
/*    */   }
/*    */   
/*    */   public void setScore(int var0) {
/* 57 */     int var1 = this.score;
/* 58 */     this.score = var0;
/* 59 */     if (var1 != var0 || this.g) {
/* 60 */       this.g = false;
/* 61 */       f().handleScoreChanged(this);
/*    */     } 
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public ScoreboardObjective getObjective() {
/* 67 */     return this.c;
/*    */   }
/*    */   
/*    */   public String getPlayerName() {
/* 71 */     return this.playerName;
/*    */   }
/*    */   
/*    */   public Scoreboard f() {
/* 75 */     return this.b;
/*    */   }
/*    */   
/*    */   public boolean g() {
/* 79 */     return this.f;
/*    */   }
/*    */   
/*    */   public void a(boolean var0) {
/* 83 */     this.f = var0;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ScoreboardScore.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
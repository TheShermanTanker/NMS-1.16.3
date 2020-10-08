/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.Arrays;
/*    */ import java.util.Collection;
/*    */ import java.util.Map;
/*    */ import java.util.stream.Collectors;
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class ScoreboardTeamBase
/*    */ {
/*    */   public boolean isAlly(@Nullable ScoreboardTeamBase var0) {
/* 16 */     if (var0 == null) {
/* 17 */       return false;
/*    */     }
/* 19 */     if (this == var0) {
/* 20 */       return true;
/*    */     }
/* 22 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public abstract String getName();
/*    */ 
/*    */   
/*    */   public abstract IChatMutableComponent getFormattedName(IChatBaseComponent paramIChatBaseComponent);
/*    */ 
/*    */   
/*    */   public abstract boolean allowFriendlyFire();
/*    */ 
/*    */   
/*    */   public abstract Collection<String> getPlayerNameSet();
/*    */ 
/*    */   
/*    */   public abstract EnumNameTagVisibility getDeathMessageVisibility();
/*    */   
/*    */   public abstract EnumTeamPush getCollisionRule();
/*    */   
/*    */   public enum EnumNameTagVisibility
/*    */   {
/* 44 */     ALWAYS("always", 0),
/* 45 */     NEVER("never", 1),
/* 46 */     HIDE_FOR_OTHER_TEAMS("hideForOtherTeams", 2),
/* 47 */     HIDE_FOR_OWN_TEAM("hideForOwnTeam", 3);
/*    */     static {
/* 49 */       g = (Map<String, EnumNameTagVisibility>)Arrays.<EnumNameTagVisibility>stream(values()).collect(Collectors.toMap(var0 -> var0.e, var0 -> var0));
/*    */     }
/*    */     private static final Map<String, EnumNameTagVisibility> g;
/*    */     public final String e;
/*    */     public final int f;
/*    */     
/*    */     @Nullable
/*    */     public static EnumNameTagVisibility a(String var0) {
/* 57 */       return g.get(var0);
/*    */     }
/*    */ 
/*    */ 
/*    */ 
/*    */     
/*    */     EnumNameTagVisibility(String var2, int var3) {
/* 64 */       this.e = var2;
/* 65 */       this.f = var3;
/*    */     }
/*    */     
/*    */     public IChatBaseComponent b() {
/* 69 */       return new ChatMessage("team.visibility." + this.e);
/*    */     }
/*    */   }
/*    */   
/*    */   public enum EnumTeamPush {
/* 74 */     ALWAYS("always", 0),
/* 75 */     NEVER("never", 1),
/* 76 */     PUSH_OTHER_TEAMS("pushOtherTeams", 2),
/* 77 */     PUSH_OWN_TEAM("pushOwnTeam", 3); private static final Map<String, EnumTeamPush> g;
/*    */     static {
/* 79 */       g = (Map<String, EnumTeamPush>)Arrays.<EnumTeamPush>stream(values()).collect(Collectors.toMap(var0 -> var0.e, var0 -> var0));
/*    */     }
/*    */     
/*    */     public final String e;
/*    */     public final int f;
/*    */     
/*    */     @Nullable
/*    */     public static EnumTeamPush a(String var0) {
/* 87 */       return g.get(var0);
/*    */     }
/*    */ 
/*    */ 
/*    */ 
/*    */     
/*    */     EnumTeamPush(String var2, int var3) {
/* 94 */       this.e = var2;
/* 95 */       this.f = var3;
/*    */     }
/*    */     
/*    */     public IChatBaseComponent b() {
/* 99 */       return new ChatMessage("team.collision." + this.e);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ScoreboardTeamBase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
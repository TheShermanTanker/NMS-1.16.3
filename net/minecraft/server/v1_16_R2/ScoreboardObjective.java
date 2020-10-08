/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ScoreboardObjective
/*    */ {
/*    */   private final Scoreboard a;
/*    */   private final String b;
/*    */   private final IScoreboardCriteria c;
/*    */   public IChatBaseComponent displayName;
/*    */   private IChatBaseComponent e;
/*    */   private IScoreboardCriteria.EnumScoreboardHealthDisplay f;
/*    */   
/*    */   public ScoreboardObjective(Scoreboard var0, String var1, IScoreboardCriteria var2, IChatBaseComponent var3, IScoreboardCriteria.EnumScoreboardHealthDisplay var4) {
/* 20 */     this.a = var0;
/* 21 */     this.b = var1;
/* 22 */     this.c = var2;
/* 23 */     this.displayName = var3;
/* 24 */     this.e = g();
/* 25 */     this.f = var4;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getName() {
/* 33 */     return this.b;
/*    */   }
/*    */   
/*    */   public IScoreboardCriteria getCriteria() {
/* 37 */     return this.c;
/*    */   }
/*    */   
/*    */   public IChatBaseComponent getDisplayName() {
/* 41 */     return this.displayName;
/*    */   }
/*    */   
/*    */   private IChatBaseComponent g() {
/* 45 */     return ChatComponentUtils.a(this.displayName
/* 46 */         .mutableCopy().format(var0 -> var0.setChatHoverable(new ChatHoverable((ChatHoverable.EnumHoverAction)ChatHoverable.EnumHoverAction.SHOW_TEXT, (T)new ChatComponentText(this.b)))));
/*    */   }
/*    */ 
/*    */   
/*    */   public IChatBaseComponent e() {
/* 51 */     return this.e;
/*    */   }
/*    */   
/*    */   public void setDisplayName(IChatBaseComponent var0) {
/* 55 */     this.displayName = var0;
/* 56 */     this.e = g();
/* 57 */     this.a.handleObjectiveChanged(this);
/*    */   }
/*    */   
/*    */   public IScoreboardCriteria.EnumScoreboardHealthDisplay getRenderType() {
/* 61 */     return this.f;
/*    */   }
/*    */   
/*    */   public void setRenderType(IScoreboardCriteria.EnumScoreboardHealthDisplay var0) {
/* 65 */     this.f = var0;
/* 66 */     this.a.handleObjectiveChanged(this);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ScoreboardObjective.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
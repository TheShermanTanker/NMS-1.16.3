/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public interface WorldData
/*    */ {
/*    */   default void a(CrashReportSystemDetails var0) {
/* 36 */     var0.a("Level spawn location", () -> CrashReportSystemDetails.a(a(), b(), c()));
/* 37 */     var0.a("Level time", () -> String.format("%d game time, %d day time", new Object[] { Long.valueOf(getTime()), Long.valueOf(getDayTime()) }));
/*    */   }
/*    */   
/*    */   int a();
/*    */   
/*    */   int b();
/*    */   
/*    */   int c();
/*    */   
/*    */   float d();
/*    */   
/*    */   long getTime();
/*    */   
/*    */   long getDayTime();
/*    */   
/*    */   boolean isThundering();
/*    */   
/*    */   boolean hasStorm();
/*    */   
/*    */   void setStorm(boolean paramBoolean);
/*    */   
/*    */   boolean isHardcore();
/*    */   
/*    */   GameRules q();
/*    */   
/*    */   EnumDifficulty getDifficulty();
/*    */   
/*    */   boolean isDifficultyLocked();
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
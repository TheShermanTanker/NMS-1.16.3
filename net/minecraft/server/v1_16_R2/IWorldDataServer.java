/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.UUID;
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
/*    */ public interface IWorldDataServer
/*    */   extends WorldDataMutable
/*    */ {
/*    */   default void a(CrashReportSystemDetails var0) {
/* 28 */     super.a(var0);
/* 29 */     var0.a("Level name", this::getName);
/* 30 */     var0.a("Level game mode", () -> String.format("Game mode: %s (ID %d). Hardcore: %b. Cheats: %b", new Object[] { getGameType().b(), Integer.valueOf(getGameType().getId()), Boolean.valueOf(isHardcore()), Boolean.valueOf(o()) }));
/* 31 */     var0.a("Level weather", () -> String.format("Rain time: %d (now: %b), thunder time: %d (now: %b)", new Object[] { Integer.valueOf(getWeatherDuration()), Boolean.valueOf(hasStorm()), Integer.valueOf(getThunderDuration()), Boolean.valueOf(isThundering()) }));
/*    */   }
/*    */   
/*    */   String getName();
/*    */   
/*    */   void setThundering(boolean paramBoolean);
/*    */   
/*    */   int getWeatherDuration();
/*    */   
/*    */   void setWeatherDuration(int paramInt);
/*    */   
/*    */   void setThunderDuration(int paramInt);
/*    */   
/*    */   int getThunderDuration();
/*    */   
/*    */   int h();
/*    */   
/*    */   void a(int paramInt);
/*    */   
/*    */   int v();
/*    */   
/*    */   void g(int paramInt);
/*    */   
/*    */   int w();
/*    */   
/*    */   void h(int paramInt);
/*    */   
/*    */   void a(UUID paramUUID);
/*    */   
/*    */   EnumGamemode getGameType();
/*    */   
/*    */   void a(WorldBorder.c paramc);
/*    */   
/*    */   WorldBorder.c r();
/*    */   
/*    */   boolean p();
/*    */   
/*    */   void c(boolean paramBoolean);
/*    */   
/*    */   boolean o();
/*    */   
/*    */   void setGameType(EnumGamemode paramEnumGamemode);
/*    */   
/*    */   CustomFunctionCallbackTimerQueue<MinecraftServer> u();
/*    */   
/*    */   void setTime(long paramLong);
/*    */   
/*    */   void setDayTime(long paramLong);
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\IWorldDataServer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
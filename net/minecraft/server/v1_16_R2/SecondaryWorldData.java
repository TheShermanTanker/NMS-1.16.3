/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.util.UUID;
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
/*     */ public class SecondaryWorldData
/*     */   implements IWorldDataServer
/*     */ {
/*     */   private final SaveData a;
/*     */   private final IWorldDataServer b;
/*     */   
/*     */   public SecondaryWorldData(SaveData var0, IWorldDataServer var1) {
/*  25 */     this.a = var0;
/*  26 */     this.b = var1;
/*     */   }
/*     */ 
/*     */   
/*     */   public int a() {
/*  31 */     return this.b.a();
/*     */   }
/*     */ 
/*     */   
/*     */   public int b() {
/*  36 */     return this.b.b();
/*     */   }
/*     */ 
/*     */   
/*     */   public int c() {
/*  41 */     return this.b.c();
/*     */   }
/*     */ 
/*     */   
/*     */   public float d() {
/*  46 */     return this.b.d();
/*     */   }
/*     */ 
/*     */   
/*     */   public long getTime() {
/*  51 */     return this.b.getTime();
/*     */   }
/*     */ 
/*     */   
/*     */   public long getDayTime() {
/*  56 */     return this.b.getDayTime();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getName() {
/*  61 */     return this.a.getName();
/*     */   }
/*     */ 
/*     */   
/*     */   public int h() {
/*  66 */     return this.b.h();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(int var0) {}
/*     */ 
/*     */   
/*     */   public boolean isThundering() {
/*  75 */     return this.b.isThundering();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getThunderDuration() {
/*  80 */     return this.b.getThunderDuration();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasStorm() {
/*  85 */     return this.b.hasStorm();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getWeatherDuration() {
/*  90 */     return this.b.getWeatherDuration();
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumGamemode getGameType() {
/*  95 */     return this.a.getGameType();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void b(int var0) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void c(int var0) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void d(int var0) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(float var0) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTime(long var0) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDayTime(long var0) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSpawn(BlockPosition var0, float var1) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void setThundering(boolean var0) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void setThunderDuration(int var0) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void setStorm(boolean var0) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void setWeatherDuration(int var0) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void setGameType(EnumGamemode var0) {}
/*     */ 
/*     */   
/*     */   public boolean isHardcore() {
/* 148 */     return this.a.isHardcore();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean o() {
/* 153 */     return this.a.o();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean p() {
/* 158 */     return this.b.p();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void c(boolean var0) {}
/*     */ 
/*     */   
/*     */   public GameRules q() {
/* 167 */     return this.a.q();
/*     */   }
/*     */ 
/*     */   
/*     */   public WorldBorder.c r() {
/* 172 */     return this.b.r();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(WorldBorder.c var0) {}
/*     */ 
/*     */   
/*     */   public EnumDifficulty getDifficulty() {
/* 181 */     return this.a.getDifficulty();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isDifficultyLocked() {
/* 186 */     return this.a.isDifficultyLocked();
/*     */   }
/*     */ 
/*     */   
/*     */   public CustomFunctionCallbackTimerQueue<MinecraftServer> u() {
/* 191 */     return this.b.u();
/*     */   }
/*     */ 
/*     */   
/*     */   public int v() {
/* 196 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void g(int var0) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public int w() {
/* 206 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void h(int var0) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(UUID var0) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(CrashReportSystemDetails var0) {
/* 225 */     var0.a("Derived", Boolean.valueOf(true));
/* 226 */     this.b.a(var0);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\SecondaryWorldData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
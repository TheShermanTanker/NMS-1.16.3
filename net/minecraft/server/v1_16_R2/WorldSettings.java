/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.serialization.Dynamic;
/*    */ import com.mojang.serialization.DynamicLike;
/*    */ 
/*    */ public final class WorldSettings {
/*    */   public String levelName;
/*    */   private final EnumGamemode gameType;
/*    */   public boolean hardcore;
/*    */   private final EnumDifficulty difficulty;
/*    */   private final boolean allowCommands;
/*    */   private final GameRules gameRules;
/*    */   private final DataPackConfiguration g;
/*    */   
/*    */   public WorldSettings(String var0, EnumGamemode var1, boolean var2, EnumDifficulty var3, boolean var4, GameRules var5, DataPackConfiguration var6) {
/* 16 */     this.levelName = var0;
/* 17 */     this.gameType = var1;
/* 18 */     this.hardcore = var2;
/* 19 */     this.difficulty = var3;
/* 20 */     this.allowCommands = var4;
/* 21 */     this.gameRules = var5;
/* 22 */     this.g = var6;
/*    */   }
/*    */   
/*    */   public static WorldSettings a(Dynamic<?> var0, DataPackConfiguration var1) {
/* 26 */     EnumGamemode var2 = EnumGamemode.getById(var0.get("GameType").asInt(0));
/* 27 */     return new WorldSettings(var0.get("LevelName").asString(""), var2, var0
/*    */         
/* 29 */         .get("hardcore").asBoolean(false), var0
/* 30 */         .get("Difficulty").asNumber().map(var0 -> EnumDifficulty.getById(var0.byteValue())).result().orElse(EnumDifficulty.NORMAL), var0
/* 31 */         .get("allowCommands").asBoolean((var2 == EnumGamemode.CREATIVE)), new GameRules((DynamicLike<?>)var0
/* 32 */           .get("GameRules")), var1);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getLevelName() {
/* 38 */     return this.levelName;
/*    */   }
/*    */   
/*    */   public EnumGamemode getGameType() {
/* 42 */     return this.gameType;
/*    */   }
/*    */   
/*    */   public boolean isHardcore() {
/* 46 */     return this.hardcore;
/*    */   }
/*    */   
/*    */   public EnumDifficulty getDifficulty() {
/* 50 */     return this.difficulty;
/*    */   }
/*    */   
/*    */   public boolean e() {
/* 54 */     return this.allowCommands;
/*    */   }
/*    */   
/*    */   public GameRules getGameRules() {
/* 58 */     return this.gameRules;
/*    */   }
/*    */   
/*    */   public DataPackConfiguration g() {
/* 62 */     return this.g;
/*    */   }
/*    */   
/*    */   public WorldSettings a(EnumGamemode var0) {
/* 66 */     return new WorldSettings(this.levelName, var0, this.hardcore, this.difficulty, this.allowCommands, this.gameRules, this.g);
/*    */   }
/*    */   
/*    */   public WorldSettings a(EnumDifficulty var0) {
/* 70 */     return new WorldSettings(this.levelName, this.gameType, this.hardcore, var0, this.allowCommands, this.gameRules, this.g);
/*    */   }
/*    */   
/*    */   public WorldSettings a(DataPackConfiguration var0) {
/* 74 */     return new WorldSettings(this.levelName, this.gameType, this.hardcore, this.difficulty, this.allowCommands, this.gameRules, var0);
/*    */   }
/*    */   
/*    */   public WorldSettings h() {
/* 78 */     return new WorldSettings(this.levelName, this.gameType, this.hardcore, this.difficulty, this.allowCommands, this.gameRules.b(), this.g);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldSettings.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
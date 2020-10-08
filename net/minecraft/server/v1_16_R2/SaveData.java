/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.Set;
/*    */ import javax.annotation.Nullable;
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
/*    */ public interface SaveData
/*    */ {
/*    */   default void a(CrashReportSystemDetails var0) {
/* 32 */     var0.a("Known server brands", () -> String.join(", ", (Iterable)G()));
/* 33 */     var0.a("Level was modded", () -> Boolean.toString(F()));
/* 34 */     var0.a("Level storage version", () -> {
/*    */           int var0 = z();
/*    */           return String.format("0x%05X - %s", new Object[] { Integer.valueOf(var0), i(var0) });
/*    */         });
/*    */   }
/*    */   
/*    */   default String i(int var0) {
/* 41 */     switch (var0) {
/*    */       case 19133:
/* 43 */         return "Anvil";
/*    */       case 19132:
/* 45 */         return "McRegion";
/*    */     } 
/* 47 */     return "Unknown?";
/*    */   }
/*    */   
/*    */   DataPackConfiguration D();
/*    */   
/*    */   void a(DataPackConfiguration paramDataPackConfiguration);
/*    */   
/*    */   boolean F();
/*    */   
/*    */   Set<String> G();
/*    */   
/*    */   void a(String paramString, boolean paramBoolean);
/*    */   
/*    */   @Nullable
/*    */   NBTTagCompound getCustomBossEvents();
/*    */   
/*    */   void setCustomBossEvents(@Nullable NBTTagCompound paramNBTTagCompound);
/*    */   
/*    */   IWorldDataServer H();
/*    */   
/*    */   NBTTagCompound a(IRegistryCustom paramIRegistryCustom, @Nullable NBTTagCompound paramNBTTagCompound);
/*    */   
/*    */   boolean isHardcore();
/*    */   
/*    */   int z();
/*    */   
/*    */   String getName();
/*    */   
/*    */   EnumGamemode getGameType();
/*    */   
/*    */   void setGameType(EnumGamemode paramEnumGamemode);
/*    */   
/*    */   boolean o();
/*    */   
/*    */   EnumDifficulty getDifficulty();
/*    */   
/*    */   void setDifficulty(EnumDifficulty paramEnumDifficulty);
/*    */   
/*    */   boolean isDifficultyLocked();
/*    */   
/*    */   void d(boolean paramBoolean);
/*    */   
/*    */   GameRules q();
/*    */   
/*    */   NBTTagCompound y();
/*    */   
/*    */   NBTTagCompound C();
/*    */   
/*    */   void a(NBTTagCompound paramNBTTagCompound);
/*    */   
/*    */   GeneratorSettings getGeneratorSettings();
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\SaveData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
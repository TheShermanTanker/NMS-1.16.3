/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.ImmutableList;
/*    */ import com.mojang.serialization.Codec;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WorldGenFeaturePillagerOutpost
/*    */   extends WorldGenFeatureJigsaw
/*    */ {
/* 18 */   private static final List<BiomeSettingsMobs.c> u = (List<BiomeSettingsMobs.c>)ImmutableList.of(new BiomeSettingsMobs.c(EntityTypes.PILLAGER, 1, 1, 1));
/*    */   
/*    */   public WorldGenFeaturePillagerOutpost(Codec<WorldGenFeatureVillageConfiguration> var0) {
/* 21 */     super(var0, 0, true, true);
/*    */   }
/*    */ 
/*    */   
/*    */   public List<BiomeSettingsMobs.c> c() {
/* 26 */     return u;
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean a(ChunkGenerator var0, WorldChunkManager var1, long var2, SeededRandom var4, int var5, int var6, BiomeBase var7, ChunkCoordIntPair var8, WorldGenFeatureVillageConfiguration var9) {
/* 31 */     int var10 = var5 >> 4;
/* 32 */     int var11 = var6 >> 4;
/*    */ 
/*    */     
/* 35 */     var4.setSeed((var10 ^ var11 << 4) ^ var2);
/* 36 */     var4.nextInt();
/*    */     
/* 38 */     if (var4.nextInt(5) != 0) {
/* 39 */       return false;
/*    */     }
/*    */     
/* 42 */     return !a(var0, var2, var4, var5, var6);
/*    */   }
/*    */   
/*    */   private boolean a(ChunkGenerator var0, long var1, SeededRandom var3, int var4, int var5) {
/* 46 */     StructureSettingsFeature var6 = var0.getSettings().a(StructureGenerator.VILLAGE);
/* 47 */     if (var6 == null) {
/* 48 */       return false;
/*    */     }
/* 50 */     for (int var7 = var4 - 10; var7 <= var4 + 10; var7++) {
/* 51 */       for (int var8 = var5 - 10; var8 <= var5 + 10; var8++) {
/* 52 */         ChunkCoordIntPair var9 = StructureGenerator.VILLAGE.a(var6, var1, var3, var7, var8);
/* 53 */         if (var7 == var9.x && var8 == var9.z) {
/* 54 */           return true;
/*    */         }
/*    */       } 
/*    */     } 
/*    */     
/* 59 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenFeaturePillagerOutpost.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
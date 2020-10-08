/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.serialization.Codec;
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
/*    */ public class WorldGenBuriedTreasure
/*    */   extends StructureGenerator<WorldGenFeatureConfigurationChance>
/*    */ {
/*    */   public WorldGenBuriedTreasure(Codec<WorldGenFeatureConfigurationChance> var0) {
/* 21 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean a(ChunkGenerator var0, WorldChunkManager var1, long var2, SeededRandom var4, int var5, int var6, BiomeBase var7, ChunkCoordIntPair var8, WorldGenFeatureConfigurationChance var9) {
/* 26 */     var4.a(var2, var5, var6, 10387320);
/* 27 */     return (var4.nextFloat() < var9.c);
/*    */   }
/*    */ 
/*    */   
/*    */   public StructureGenerator.a<WorldGenFeatureConfigurationChance> a() {
/* 32 */     return a::new;
/*    */   }
/*    */   
/*    */   public static class a extends StructureStart<WorldGenFeatureConfigurationChance> {
/*    */     public a(StructureGenerator<WorldGenFeatureConfigurationChance> var0, int var1, int var2, StructureBoundingBox var3, int var4, long var5) {
/* 37 */       super(var0, var1, var2, var3, var4, var5);
/*    */     }
/*    */ 
/*    */     
/*    */     public void a(IRegistryCustom var0, ChunkGenerator var1, DefinedStructureManager var2, int var3, int var4, BiomeBase var5, WorldGenFeatureConfigurationChance var6) {
/* 42 */       int var7 = var3 * 16;
/* 43 */       int var8 = var4 * 16;
/*    */       
/* 45 */       BlockPosition var9 = new BlockPosition(var7 + 9, 90, var8 + 9);
/*    */       
/* 47 */       this.b.add(new WorldGenBuriedTreasurePieces.a(var9));
/* 48 */       b();
/*    */     }
/*    */ 
/*    */     
/*    */     public BlockPosition a() {
/* 53 */       return new BlockPosition((f() << 4) + 9, 0, (g() << 4) + 9);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenBuriedTreasure.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WorldGenNether
/*    */   extends StructureGenerator<WorldGenFeatureEmptyConfiguration>
/*    */ {
/* 23 */   private static final List<BiomeSettingsMobs.c> u = (List<BiomeSettingsMobs.c>)ImmutableList.of(new BiomeSettingsMobs.c(EntityTypes.BLAZE, 10, 2, 3), new BiomeSettingsMobs.c(EntityTypes.ZOMBIFIED_PIGLIN, 5, 4, 4), new BiomeSettingsMobs.c(EntityTypes.WITHER_SKELETON, 8, 5, 5), new BiomeSettingsMobs.c(EntityTypes.SKELETON, 2, 5, 5), new BiomeSettingsMobs.c(EntityTypes.MAGMA_CUBE, 3, 4, 4));
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public WorldGenNether(Codec<WorldGenFeatureEmptyConfiguration> var0) {
/* 32 */     super(var0);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected boolean a(ChunkGenerator var0, WorldChunkManager var1, long var2, SeededRandom var4, int var5, int var6, BiomeBase var7, ChunkCoordIntPair var8, WorldGenFeatureEmptyConfiguration var9) {
/* 38 */     return (var4.nextInt(5) < 2);
/*    */   }
/*    */ 
/*    */   
/*    */   public StructureGenerator.a<WorldGenFeatureEmptyConfiguration> a() {
/* 43 */     return a::new;
/*    */   }
/*    */ 
/*    */   
/*    */   public List<BiomeSettingsMobs.c> c() {
/* 48 */     return u;
/*    */   }
/*    */   
/*    */   public static class a extends StructureStart<WorldGenFeatureEmptyConfiguration> {
/*    */     public a(StructureGenerator<WorldGenFeatureEmptyConfiguration> var0, int var1, int var2, StructureBoundingBox var3, int var4, long var5) {
/* 53 */       super(var0, var1, var2, var3, var4, var5);
/*    */     }
/*    */ 
/*    */     
/*    */     public void a(IRegistryCustom var0, ChunkGenerator var1, DefinedStructureManager var2, int var3, int var4, BiomeBase var5, WorldGenFeatureEmptyConfiguration var6) {
/* 58 */       WorldGenNetherPieces.WorldGenNetherPiece15 var7 = new WorldGenNetherPieces.WorldGenNetherPiece15(this.d, (var3 << 4) + 2, (var4 << 4) + 2);
/* 59 */       this.b.add(var7);
/* 60 */       var7.a(var7, this.b, this.d);
/*    */       
/* 62 */       List<StructurePiece> var8 = var7.d;
/* 63 */       while (!var8.isEmpty()) {
/* 64 */         int var9 = this.d.nextInt(var8.size());
/* 65 */         StructurePiece var10 = var8.remove(var9);
/* 66 */         var10.a(var7, this.b, this.d);
/*    */       } 
/*    */       
/* 69 */       b();
/* 70 */       a(this.d, 48, 70);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenNether.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
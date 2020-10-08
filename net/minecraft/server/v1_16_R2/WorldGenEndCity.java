/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.serialization.Codec;
/*    */ import java.util.Random;
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
/*    */ public class WorldGenEndCity
/*    */   extends StructureGenerator<WorldGenFeatureEmptyConfiguration>
/*    */ {
/*    */   public WorldGenEndCity(Codec<WorldGenFeatureEmptyConfiguration> var0) {
/* 25 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean b() {
/* 30 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean a(ChunkGenerator var0, WorldChunkManager var1, long var2, SeededRandom var4, int var5, int var6, BiomeBase var7, ChunkCoordIntPair var8, WorldGenFeatureEmptyConfiguration var9) {
/* 35 */     return (b(var5, var6, var0) >= 60);
/*    */   }
/*    */ 
/*    */   
/*    */   public StructureGenerator.a<WorldGenFeatureEmptyConfiguration> a() {
/* 40 */     return a::new;
/*    */   }
/*    */   
/*    */   private static int b(int var0, int var1, ChunkGenerator var2) {
/* 44 */     Random var3 = new Random((var0 + var1 * 10387313));
/* 45 */     EnumBlockRotation var4 = EnumBlockRotation.a(var3);
/*    */     
/* 47 */     int var5 = 5;
/* 48 */     int var6 = 5;
/* 49 */     if (var4 == EnumBlockRotation.CLOCKWISE_90) {
/* 50 */       var5 = -5;
/* 51 */     } else if (var4 == EnumBlockRotation.CLOCKWISE_180) {
/* 52 */       var5 = -5;
/* 53 */       var6 = -5;
/* 54 */     } else if (var4 == EnumBlockRotation.COUNTERCLOCKWISE_90) {
/* 55 */       var6 = -5;
/*    */     } 
/*    */     
/* 58 */     int var7 = (var0 << 4) + 7;
/* 59 */     int var8 = (var1 << 4) + 7;
/* 60 */     int var9 = var2.c(var7, var8, HeightMap.Type.WORLD_SURFACE_WG);
/* 61 */     int var10 = var2.c(var7, var8 + var6, HeightMap.Type.WORLD_SURFACE_WG);
/* 62 */     int var11 = var2.c(var7 + var5, var8, HeightMap.Type.WORLD_SURFACE_WG);
/* 63 */     int var12 = var2.c(var7 + var5, var8 + var6, HeightMap.Type.WORLD_SURFACE_WG);
/*    */     
/* 65 */     return Math.min(Math.min(var9, var10), Math.min(var11, var12));
/*    */   }
/*    */   
/*    */   public static class a extends StructureStart<WorldGenFeatureEmptyConfiguration> {
/*    */     public a(StructureGenerator<WorldGenFeatureEmptyConfiguration> var0, int var1, int var2, StructureBoundingBox var3, int var4, long var5) {
/* 70 */       super(var0, var1, var2, var3, var4, var5);
/*    */     }
/*    */ 
/*    */     
/*    */     public void a(IRegistryCustom var0, ChunkGenerator var1, DefinedStructureManager var2, int var3, int var4, BiomeBase var5, WorldGenFeatureEmptyConfiguration var6) {
/* 75 */       EnumBlockRotation var7 = EnumBlockRotation.a(this.d);
/*    */       
/* 77 */       int var8 = WorldGenEndCity.a(var3, var4, var1);
/*    */ 
/*    */       
/* 80 */       if (var8 < 60) {
/*    */         return;
/*    */       }
/*    */       
/* 84 */       BlockPosition var9 = new BlockPosition(var3 * 16 + 8, var8, var4 * 16 + 8);
/* 85 */       WorldGenEndCityPieces.a(var2, var9, var7, this.b, this.d);
/*    */       
/* 87 */       b();
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenEndCity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
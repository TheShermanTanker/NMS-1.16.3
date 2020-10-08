/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.ImmutableList;
/*    */ import com.mojang.serialization.Codec;
/*    */ import java.util.List;
/*    */ import java.util.Random;
/*    */ import java.util.Set;
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
/*    */ public class WorldGenMonument
/*    */   extends StructureGenerator<WorldGenFeatureEmptyConfiguration>
/*    */ {
/* 27 */   private static final List<BiomeSettingsMobs.c> u = (List<BiomeSettingsMobs.c>)ImmutableList.of(new BiomeSettingsMobs.c(EntityTypes.GUARDIAN, 1, 2, 4));
/*    */   
/*    */   public WorldGenMonument(Codec<WorldGenFeatureEmptyConfiguration> var0) {
/* 30 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean b() {
/* 35 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected boolean a(ChunkGenerator var0, WorldChunkManager var1, long var2, SeededRandom var4, int var5, int var6, BiomeBase var7, ChunkCoordIntPair var8, WorldGenFeatureEmptyConfiguration var9) {
/* 41 */     Set<BiomeBase> var10 = var1.a(var5 * 16 + 9, var0.getSeaLevel(), var6 * 16 + 9, 16);
/* 42 */     for (BiomeBase var12 : var10) {
/* 43 */       if (!var12.e().a(this)) {
/* 44 */         return false;
/*    */       }
/*    */     } 
/*    */     
/* 48 */     Set<BiomeBase> var11 = var1.a(var5 * 16 + 9, var0.getSeaLevel(), var6 * 16 + 9, 29);
/* 49 */     for (BiomeBase var13 : var11) {
/* 50 */       if (var13.t() != BiomeBase.Geography.OCEAN && var13.t() != BiomeBase.Geography.RIVER) {
/* 51 */         return false;
/*    */       }
/*    */     } 
/*    */     
/* 55 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public StructureGenerator.a<WorldGenFeatureEmptyConfiguration> a() {
/* 60 */     return a::new;
/*    */   }
/*    */   
/*    */   public static class a extends StructureStart<WorldGenFeatureEmptyConfiguration> {
/*    */     private boolean e;
/*    */     
/*    */     public a(StructureGenerator<WorldGenFeatureEmptyConfiguration> var0, int var1, int var2, StructureBoundingBox var3, int var4, long var5) {
/* 67 */       super(var0, var1, var2, var3, var4, var5);
/*    */     }
/*    */ 
/*    */     
/*    */     public void a(IRegistryCustom var0, ChunkGenerator var1, DefinedStructureManager var2, int var3, int var4, BiomeBase var5, WorldGenFeatureEmptyConfiguration var6) {
/* 72 */       b(var3, var4);
/*    */     }
/*    */     
/*    */     private void b(int var0, int var1) {
/* 76 */       int var2 = var0 * 16 - 29;
/* 77 */       int var3 = var1 * 16 - 29;
/* 78 */       EnumDirection var4 = EnumDirection.EnumDirectionLimit.HORIZONTAL.a(this.d);
/*    */       
/* 80 */       this.b.add(new WorldGenMonumentPieces.WorldGenMonumentPiece1(this.d, var2, var3, var4));
/* 81 */       b();
/*    */       
/* 83 */       this.e = true;
/*    */     }
/*    */ 
/*    */     
/*    */     public void a(GeneratorAccessSeed var0, StructureManager var1, ChunkGenerator var2, Random var3, StructureBoundingBox var4, ChunkCoordIntPair var5) {
/* 88 */       if (!this.e) {
/* 89 */         this.b.clear();
/* 90 */         b(f(), g());
/*    */       } 
/* 92 */       super.a(var0, var1, var2, var3, var4, var5);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public List<BiomeSettingsMobs.c> c() {
/* 98 */     return u;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenMonument.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
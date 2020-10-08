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
/*    */ public class WorldGenFeatureNetherFossil
/*    */   extends StructureGenerator<WorldGenFeatureEmptyConfiguration>
/*    */ {
/*    */   public WorldGenFeatureNetherFossil(Codec<WorldGenFeatureEmptyConfiguration> var0) {
/* 20 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public StructureGenerator.a<WorldGenFeatureEmptyConfiguration> a() {
/* 25 */     return a::new;
/*    */   }
/*    */   
/*    */   public static class a extends StructureAbstract<WorldGenFeatureEmptyConfiguration> {
/*    */     public a(StructureGenerator<WorldGenFeatureEmptyConfiguration> var0, int var1, int var2, StructureBoundingBox var3, int var4, long var5) {
/* 30 */       super(var0, var1, var2, var3, var4, var5);
/*    */     }
/*    */ 
/*    */     
/*    */     public void a(IRegistryCustom var0, ChunkGenerator var1, DefinedStructureManager var2, int var3, int var4, BiomeBase var5, WorldGenFeatureEmptyConfiguration var6) {
/* 35 */       ChunkCoordIntPair var7 = new ChunkCoordIntPair(var3, var4);
/* 36 */       int var8 = var7.d() + this.d.nextInt(16);
/* 37 */       int var9 = var7.e() + this.d.nextInt(16);
/*    */       
/* 39 */       int var10 = var1.getSeaLevel();
/*    */       
/* 41 */       int var11 = var10 + this.d.nextInt(var1.getGenerationDepth() - 2 - var10);
/*    */       
/* 43 */       IBlockAccess var12 = var1.a(var8, var9);
/*    */       
/* 45 */       BlockPosition.MutableBlockPosition var13 = new BlockPosition.MutableBlockPosition(var8, var11, var9);
/* 46 */       while (var11 > var10) {
/* 47 */         IBlockData var14 = var12.getType(var13);
/*    */         
/* 49 */         var13.c(EnumDirection.DOWN);
/*    */         
/* 51 */         IBlockData var15 = var12.getType(var13);
/* 52 */         if (var14.isAir() && (var15.a(Blocks.SOUL_SAND) || var15.d(var12, var13, EnumDirection.UP))) {
/*    */           break;
/*    */         }
/*    */         
/* 56 */         var11--;
/*    */       } 
/*    */       
/* 59 */       if (var11 <= var10) {
/*    */         return;
/*    */       }
/*    */       
/* 63 */       WorldGenNetherFossil.a(var2, this.b, this.d, new BlockPosition(var8, var11, var9));
/* 64 */       b();
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenFeatureNetherFossil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
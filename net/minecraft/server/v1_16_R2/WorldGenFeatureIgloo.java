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
/*    */ public class WorldGenFeatureIgloo
/*    */   extends StructureGenerator<WorldGenFeatureEmptyConfiguration>
/*    */ {
/*    */   public WorldGenFeatureIgloo(Codec<WorldGenFeatureEmptyConfiguration> var0) {
/* 17 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public StructureGenerator.a<WorldGenFeatureEmptyConfiguration> a() {
/* 22 */     return a::new;
/*    */   }
/*    */   
/*    */   public static class a extends StructureStart<WorldGenFeatureEmptyConfiguration> {
/*    */     public a(StructureGenerator<WorldGenFeatureEmptyConfiguration> var0, int var1, int var2, StructureBoundingBox var3, int var4, long var5) {
/* 27 */       super(var0, var1, var2, var3, var4, var5);
/*    */     }
/*    */ 
/*    */     
/*    */     public void a(IRegistryCustom var0, ChunkGenerator var1, DefinedStructureManager var2, int var3, int var4, BiomeBase var5, WorldGenFeatureEmptyConfiguration var6) {
/* 32 */       int var7 = var3 * 16;
/* 33 */       int var8 = var4 * 16;
/*    */       
/* 35 */       BlockPosition var9 = new BlockPosition(var7, 90, var8);
/* 36 */       EnumBlockRotation var10 = EnumBlockRotation.a(this.d);
/* 37 */       WorldGenIglooPiece.a(var2, var9, var10, this.b, this.d);
/* 38 */       b();
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenFeatureIgloo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
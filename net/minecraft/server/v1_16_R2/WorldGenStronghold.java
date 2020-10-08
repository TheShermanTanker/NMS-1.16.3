/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
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
/*    */ public class WorldGenStronghold
/*    */   extends StructureGenerator<WorldGenFeatureEmptyConfiguration>
/*    */ {
/*    */   public WorldGenStronghold(Codec<WorldGenFeatureEmptyConfiguration> var0) {
/* 21 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public StructureGenerator.a<WorldGenFeatureEmptyConfiguration> a() {
/* 26 */     return a::new;
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean a(ChunkGenerator var0, WorldChunkManager var1, long var2, SeededRandom var4, int var5, int var6, BiomeBase var7, ChunkCoordIntPair var8, WorldGenFeatureEmptyConfiguration var9) {
/* 31 */     return var0.a(new ChunkCoordIntPair(var5, var6));
/*    */   }
/*    */   
/*    */   public static class a extends StructureStart<WorldGenFeatureEmptyConfiguration> {
/*    */     private final long e;
/*    */     
/*    */     public a(StructureGenerator<WorldGenFeatureEmptyConfiguration> var0, int var1, int var2, StructureBoundingBox var3, int var4, long var5) {
/* 38 */       super(var0, var1, var2, var3, var4, var5);
/* 39 */       this.e = var5;
/*    */     }
/*    */     
/*    */     public void a(IRegistryCustom var0, ChunkGenerator var1, DefinedStructureManager var2, int var3, int var4, BiomeBase var5, WorldGenFeatureEmptyConfiguration var6) {
/*    */       WorldGenStrongholdPieces.WorldGenStrongholdStart var8;
/* 44 */       int var7 = 0;
/*    */ 
/*    */ 
/*    */ 
/*    */       
/*    */       do {
/* 50 */         this.b.clear();
/* 51 */         this.c = StructureBoundingBox.a();
/* 52 */         this.d.c(this.e + var7++, var3, var4);
/* 53 */         WorldGenStrongholdPieces.a();
/*    */         
/* 55 */         var8 = new WorldGenStrongholdPieces.WorldGenStrongholdStart(this.d, (var3 << 4) + 2, (var4 << 4) + 2);
/* 56 */         this.b.add(var8);
/* 57 */         var8.a(var8, this.b, this.d);
/*    */         
/* 59 */         List<StructurePiece> var9 = var8.c;
/* 60 */         while (!var9.isEmpty()) {
/* 61 */           int var10 = this.d.nextInt(var9.size());
/* 62 */           StructurePiece var11 = var9.remove(var10);
/* 63 */           var11.a(var8, this.b, this.d);
/*    */         } 
/*    */         
/* 66 */         b();
/* 67 */         a(var1.getSeaLevel(), this.d, 10);
/*    */       }
/* 69 */       while (this.b.isEmpty() || var8.b == null);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenStronghold.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.BitSet;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WorldGenDecoratorContext
/*    */ {
/*    */   private final GeneratorAccessSeed a;
/*    */   private final ChunkGenerator b;
/*    */   
/*    */   public WorldGenDecoratorContext(GeneratorAccessSeed var0, ChunkGenerator var1) {
/* 19 */     this.a = var0;
/* 20 */     this.b = var1;
/*    */   }
/*    */   
/*    */   public int a(HeightMap.Type var0, int var1, int var2) {
/* 24 */     return this.a.a(var0, var1, var2);
/*    */   }
/*    */   
/*    */   public int a() {
/* 28 */     return this.b.getGenerationDepth();
/*    */   }
/*    */   
/*    */   public int b() {
/* 32 */     return this.b.getSeaLevel();
/*    */   }
/*    */   
/*    */   public BitSet a(ChunkCoordIntPair var0, WorldGenStage.Features var1) {
/* 36 */     return ((ProtoChunk)this.a.getChunkAt(var0.x, var0.z)).b(var1);
/*    */   }
/*    */   
/*    */   public IBlockData a(BlockPosition var0) {
/* 40 */     return this.a.getType(var0);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenDecoratorContext.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
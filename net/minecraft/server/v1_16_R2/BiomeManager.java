/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.hash.Hashing;
/*    */ 
/*    */ 
/*    */ public class BiomeManager
/*    */ {
/*    */   private final Provider a;
/*    */   private final long b;
/*    */   private final GenLayerZoomer c;
/*    */   
/*    */   public BiomeManager(Provider var0, long var1, GenLayerZoomer var3) {
/* 13 */     this.a = var0;
/* 14 */     this.b = var1;
/* 15 */     this.c = var3;
/*    */   }
/*    */   
/*    */   public static long a(long var0) {
/* 19 */     return Hashing.sha256().hashLong(var0).asLong();
/*    */   }
/*    */   
/*    */   public BiomeManager a(WorldChunkManager var0) {
/* 23 */     return new BiomeManager(var0, this.b, this.c);
/*    */   }
/*    */   
/*    */   public BiomeBase a(BlockPosition var0) {
/* 27 */     return this.c.a(this.b, var0.getX(), var0.getY(), var0.getZ(), this.a);
/*    */   }
/*    */   
/*    */   public static interface Provider {
/*    */     BiomeBase getBiome(int param1Int1, int param1Int2, int param1Int3);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BiomeManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
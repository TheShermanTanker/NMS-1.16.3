/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import javax.annotation.Nullable;
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ 
/*    */ public class BiomeStorage
/*    */   implements BiomeManager.Provider {
/*  9 */   private static final Logger LOGGER = LogManager.getLogger();
/* 10 */   private static final int e = (int)Math.round(Math.log(16.0D) / Math.log(2.0D)) - 2;
/* 11 */   private static final int f = (int)Math.round(Math.log(256.0D) / Math.log(2.0D)) - 2;
/* 12 */   public static final int a = 1 << e + e + f;
/* 13 */   public static final int b = (1 << e) - 1;
/* 14 */   public static final int c = (1 << f) - 1;
/*    */   public final Registry<BiomeBase> g;
/*    */   private final BiomeBase[] h;
/*    */   
/*    */   public BiomeStorage(Registry<BiomeBase> registry, BiomeBase[] abiomebase) {
/* 19 */     this.g = registry;
/* 20 */     this.h = abiomebase;
/*    */   }
/*    */   
/*    */   private BiomeStorage(Registry<BiomeBase> registry) {
/* 24 */     this(registry, new BiomeBase[a]);
/*    */   }
/*    */   
/*    */   public BiomeStorage(Registry<BiomeBase> registry, ChunkCoordIntPair chunkcoordintpair, WorldChunkManager worldchunkmanager) {
/* 28 */     this(registry);
/* 29 */     int i = chunkcoordintpair.d() >> 2;
/* 30 */     int j = chunkcoordintpair.e() >> 2;
/*    */     
/* 32 */     for (int k = 0; k < this.h.length; k++) {
/* 33 */       int l = k & b;
/* 34 */       int i1 = k >> e + e & c;
/* 35 */       int j1 = k >> e & b;
/*    */       
/* 37 */       this.h[k] = worldchunkmanager.getBiome(i + l, i1, j + j1);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public BiomeStorage(Registry<BiomeBase> registry, ChunkCoordIntPair chunkcoordintpair, WorldChunkManager worldchunkmanager, @Nullable int[] aint) {
/* 43 */     this(registry);
/* 44 */     int i = chunkcoordintpair.d() >> 2;
/* 45 */     int j = chunkcoordintpair.e() >> 2;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 51 */     if (aint != null) {
/* 52 */       for (int k = 0; k < aint.length; k++) {
/* 53 */         this.h[k] = registry.fromId(aint[k]);
/* 54 */         if (this.h[k] == null) {
/* 55 */           int l = k & b;
/* 56 */           int i1 = k >> e + e & c;
/* 57 */           int j1 = k >> e & b;
/* 58 */           this.h[k] = worldchunkmanager.getBiome(i + l, i1, j + j1);
/*    */         } 
/*    */       } 
/*    */     } else {
/* 62 */       for (int k = 0; k < this.h.length; k++) {
/* 63 */         int l = k & b;
/* 64 */         int i1 = k >> e + e & c;
/* 65 */         int j1 = k >> e & b;
/* 66 */         this.h[k] = worldchunkmanager.getBiome(i + l, i1, j + j1);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public int[] a() {
/* 73 */     int[] aint = new int[this.h.length];
/*    */     
/* 75 */     for (int i = 0; i < this.h.length; i++) {
/* 76 */       aint[i] = this.g.a((T)this.h[i]);
/*    */     }
/*    */     
/* 79 */     return aint;
/*    */   }
/*    */ 
/*    */   
/*    */   public BiomeBase getBiome(int i, int j, int k) {
/* 84 */     int l = i & b;
/* 85 */     int i1 = MathHelper.clamp(j, 0, c);
/* 86 */     int j1 = k & b;
/*    */     
/* 88 */     return this.h[i1 << e + e | j1 << e | l];
/*    */   }
/*    */ 
/*    */   
/*    */   public void setBiome(int i, int j, int k, BiomeBase biome) {
/* 93 */     int l = i & b;
/* 94 */     int i1 = MathHelper.clamp(j, 0, c);
/* 95 */     int j1 = k & b;
/*    */     
/* 97 */     this.h[i1 << e + e | j1 << e | l] = biome;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BiomeStorage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
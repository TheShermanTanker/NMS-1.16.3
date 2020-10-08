/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.ImmutableList;
/*    */ import com.mojang.serialization.Codec;
/*    */ import java.util.List;
/*    */ import java.util.Random;
/*    */ 
/*    */ public class WorldGenSurfaceNetherForest extends WorldGenSurface<WorldGenSurfaceConfigurationBase> {
/*  9 */   private static final IBlockData b = Blocks.CAVE_AIR.getBlockData();
/*    */   protected long a;
/*    */   private NoiseGeneratorOctaves c;
/*    */   
/*    */   public WorldGenSurfaceNetherForest(Codec<WorldGenSurfaceConfigurationBase> codec) {
/* 14 */     super(codec);
/*    */   }
/*    */   
/*    */   public void a(Random random, IChunkAccess ichunkaccess, BiomeBase biomebase, int i, int j, int k, double d0, IBlockData iblockdata, IBlockData iblockdata1, int l, long i1, WorldGenSurfaceConfigurationBase worldgensurfaceconfigurationbase) {
/* 18 */     int j1 = l;
/* 19 */     int k1 = i & 0xF;
/* 20 */     int l1 = j & 0xF;
/* 21 */     double d1 = this.c.a(i * 0.1D, l, j * 0.1D);
/* 22 */     boolean flag = (d1 > 0.15D + random.nextDouble() * 0.35D);
/* 23 */     double d2 = this.c.a(i * 0.1D, 109.0D, j * 0.1D);
/* 24 */     boolean flag1 = (d2 > 0.25D + random.nextDouble() * 0.9D);
/* 25 */     int i2 = (int)(d0 / 3.0D + 3.0D + random.nextDouble() * 0.25D);
/* 26 */     BlockPosition.MutableBlockPosition blockposition_mutableblockposition = new BlockPosition.MutableBlockPosition();
/* 27 */     int j2 = -1;
/* 28 */     IBlockData iblockdata2 = worldgensurfaceconfigurationbase.b();
/*    */     
/* 30 */     for (int k2 = k; k2 >= 0; k2--) {
/* 31 */       blockposition_mutableblockposition.d(k1, k2, l1);
/* 32 */       IBlockData iblockdata3 = worldgensurfaceconfigurationbase.a();
/* 33 */       IBlockData iblockdata4 = ichunkaccess.getType(blockposition_mutableblockposition);
/*    */       
/* 35 */       if (iblockdata4.isAir()) {
/* 36 */         j2 = -1;
/* 37 */       } else if (iblockdata4.a(iblockdata.getBlock())) {
/* 38 */         if (j2 == -1) {
/* 39 */           boolean flag2 = false;
/*    */           
/* 41 */           if (i2 <= 0) {
/* 42 */             flag2 = true;
/* 43 */             iblockdata2 = worldgensurfaceconfigurationbase.b();
/*    */           } 
/*    */           
/* 46 */           if (flag) {
/* 47 */             iblockdata3 = worldgensurfaceconfigurationbase.b();
/* 48 */           } else if (flag1) {
/* 49 */             iblockdata3 = worldgensurfaceconfigurationbase.c();
/*    */           } 
/*    */           
/* 52 */           if (k2 < j1 && flag2) {
/* 53 */             iblockdata3 = iblockdata1;
/*    */           }
/*    */           
/* 56 */           j2 = i2;
/* 57 */           if (k2 >= j1 - 1) {
/* 58 */             ichunkaccess.setType(blockposition_mutableblockposition, iblockdata3, false);
/*    */           } else {
/* 60 */             ichunkaccess.setType(blockposition_mutableblockposition, iblockdata2, false);
/*    */           } 
/* 62 */         } else if (j2 > 0) {
/* 63 */           j2--;
/* 64 */           ichunkaccess.setType(blockposition_mutableblockposition, iblockdata2, false);
/*    */         } 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void a(long i) {
/* 73 */     if (this.a != i || this.c == null) {
/* 74 */       this.c = new NoiseGeneratorOctaves(new SeededRandom(i), (List<Integer>)ImmutableList.of(Integer.valueOf(0)));
/*    */     }
/*    */     
/* 77 */     this.a = i;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenSurfaceNetherForest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
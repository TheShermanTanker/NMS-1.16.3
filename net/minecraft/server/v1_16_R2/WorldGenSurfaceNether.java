/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.serialization.Codec;
/*    */ import java.util.Random;
/*    */ import java.util.stream.IntStream;
/*    */ 
/*    */ public class WorldGenSurfaceNether
/*    */   extends WorldGenSurface<WorldGenSurfaceConfigurationBase> {
/*  9 */   private static final IBlockData c = Blocks.CAVE_AIR.getBlockData();
/* 10 */   private static final IBlockData d = Blocks.GRAVEL.getBlockData();
/* 11 */   private static final IBlockData e = Blocks.SOUL_SAND.getBlockData();
/*    */   protected long a;
/*    */   protected NoiseGeneratorOctaves b;
/*    */   
/*    */   public WorldGenSurfaceNether(Codec<WorldGenSurfaceConfigurationBase> codec) {
/* 16 */     super(codec);
/*    */   }
/*    */   
/*    */   public void a(Random random, IChunkAccess ichunkaccess, BiomeBase biomebase, int i, int j, int k, double d0, IBlockData iblockdata, IBlockData iblockdata1, int l, long i1, WorldGenSurfaceConfigurationBase worldgensurfaceconfigurationbase) {
/* 20 */     int j1 = l;
/* 21 */     int k1 = i & 0xF;
/* 22 */     int l1 = j & 0xF;
/* 23 */     double d1 = 0.03125D;
/* 24 */     boolean flag = (this.b.a(i * 0.03125D, j * 0.03125D, 0.0D) * 75.0D + random.nextDouble() > 0.0D);
/* 25 */     boolean flag1 = (this.b.a(i * 0.03125D, 109.0D, j * 0.03125D) * 75.0D + random.nextDouble() > 0.0D);
/* 26 */     int i2 = (int)(d0 / 3.0D + 3.0D + random.nextDouble() * 0.25D);
/* 27 */     BlockPosition.MutableBlockPosition blockposition_mutableblockposition = new BlockPosition.MutableBlockPosition();
/* 28 */     int j2 = -1;
/* 29 */     IBlockData iblockdata2 = worldgensurfaceconfigurationbase.a();
/* 30 */     IBlockData iblockdata3 = worldgensurfaceconfigurationbase.b();
/*    */     
/* 32 */     for (int k2 = k; k2 >= 0; k2--) {
/* 33 */       blockposition_mutableblockposition.d(k1, k2, l1);
/* 34 */       IBlockData iblockdata4 = ichunkaccess.getType(blockposition_mutableblockposition);
/*    */       
/* 36 */       if (iblockdata4.isAir()) {
/* 37 */         j2 = -1;
/* 38 */       } else if (iblockdata4.a(iblockdata.getBlock())) {
/* 39 */         if (j2 == -1) {
/* 40 */           boolean flag2 = false;
/*    */           
/* 42 */           if (i2 <= 0) {
/* 43 */             flag2 = true;
/* 44 */             iblockdata3 = worldgensurfaceconfigurationbase.b();
/* 45 */           } else if (k2 >= j1 - 4 && k2 <= j1 + 1) {
/* 46 */             iblockdata2 = worldgensurfaceconfigurationbase.a();
/* 47 */             iblockdata3 = worldgensurfaceconfigurationbase.b();
/* 48 */             if (flag1) {
/* 49 */               iblockdata2 = d;
/* 50 */               iblockdata3 = worldgensurfaceconfigurationbase.b();
/*    */             } 
/*    */             
/* 53 */             if (flag) {
/* 54 */               iblockdata2 = e;
/* 55 */               iblockdata3 = e;
/*    */             } 
/*    */           } 
/*    */           
/* 59 */           if (k2 < j1 && flag2) {
/* 60 */             iblockdata2 = iblockdata1;
/*    */           }
/*    */           
/* 63 */           j2 = i2;
/* 64 */           if (k2 >= j1 - 1) {
/* 65 */             ichunkaccess.setType(blockposition_mutableblockposition, iblockdata2, false);
/*    */           } else {
/* 67 */             ichunkaccess.setType(blockposition_mutableblockposition, iblockdata3, false);
/*    */           } 
/* 69 */         } else if (j2 > 0) {
/* 70 */           j2--;
/* 71 */           ichunkaccess.setType(blockposition_mutableblockposition, iblockdata3, false);
/*    */         } 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void a(long i) {
/* 80 */     if (this.a != i || this.b == null) {
/* 81 */       this.b = new NoiseGeneratorOctaves(new SeededRandom(i), IntStream.rangeClosed(-3, 0));
/*    */     }
/*    */     
/* 84 */     this.a = i;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenSurfaceNether.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
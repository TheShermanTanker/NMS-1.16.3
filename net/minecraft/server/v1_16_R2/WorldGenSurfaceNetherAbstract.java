/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.ImmutableList;
/*    */ import com.google.common.collect.ImmutableMap;
/*    */ import com.google.common.collect.UnmodifiableIterator;
/*    */ import com.mojang.serialization.Codec;
/*    */ import java.util.Comparator;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import java.util.Random;
/*    */ 
/*    */ public abstract class WorldGenSurfaceNetherAbstract
/*    */   extends WorldGenSurface<WorldGenSurfaceConfigurationBase> {
/*    */   private long a;
/* 15 */   private ImmutableMap<IBlockData, NoiseGeneratorOctaves> b = ImmutableMap.of();
/* 16 */   private ImmutableMap<IBlockData, NoiseGeneratorOctaves> c = ImmutableMap.of();
/*    */   private NoiseGeneratorOctaves d;
/*    */   
/*    */   public WorldGenSurfaceNetherAbstract(Codec<WorldGenSurfaceConfigurationBase> codec) {
/* 20 */     super(codec);
/*    */   }
/*    */   
/*    */   public void a(Random random, IChunkAccess ichunkaccess, BiomeBase biomebase, int i, int j, int k, double d0, IBlockData iblockdata, IBlockData iblockdata1, int l, long i1, WorldGenSurfaceConfigurationBase worldgensurfaceconfigurationbase) {
/* 24 */     int j1 = l + 1;
/* 25 */     int k1 = i & 0xF;
/* 26 */     int l1 = j & 0xF;
/* 27 */     int i2 = (int)(d0 / 3.0D + 3.0D + random.nextDouble() * 0.25D);
/* 28 */     int j2 = (int)(d0 / 3.0D + 3.0D + random.nextDouble() * 0.25D);
/* 29 */     double d1 = 0.03125D;
/* 30 */     boolean flag = (this.d.a(i * 0.03125D, 109.0D, j * 0.03125D) * 75.0D + random.nextDouble() > 0.0D);
/*    */ 
/*    */     
/* 33 */     IBlockData iblockdata2 = (IBlockData)((Map.Entry)this.c.entrySet().stream().max(Comparator.comparing(entry -> Double.valueOf(((NoiseGeneratorOctaves)entry.getValue()).a(i, l, j)))).get()).getKey();
/*    */ 
/*    */     
/* 36 */     IBlockData iblockdata3 = (IBlockData)((Map.Entry)this.b.entrySet().stream().max(Comparator.comparing(entry -> Double.valueOf(((NoiseGeneratorOctaves)entry.getValue()).a(i, l, j)))).get()).getKey();
/* 37 */     BlockPosition.MutableBlockPosition blockposition_mutableblockposition = new BlockPosition.MutableBlockPosition();
/* 38 */     IBlockData iblockdata4 = ichunkaccess.getType(blockposition_mutableblockposition.d(k1, 128, l1));
/*    */     
/* 40 */     for (int k2 = k; k2 >= 0; k2--) {
/* 41 */       blockposition_mutableblockposition.d(k1, k2, l1);
/* 42 */       IBlockData iblockdata5 = ichunkaccess.getType(blockposition_mutableblockposition);
/*    */ 
/*    */       
/* 45 */       if (iblockdata4.a(iblockdata.getBlock()) && (iblockdata5.isAir() || iblockdata5 == iblockdata1)) {
/* 46 */         for (int l2 = 0; l2 < i2; l2++) {
/* 47 */           blockposition_mutableblockposition.c(EnumDirection.UP);
/* 48 */           if (!ichunkaccess.getType(blockposition_mutableblockposition).a(iblockdata.getBlock())) {
/*    */             break;
/*    */           }
/*    */           
/* 52 */           ichunkaccess.setType(blockposition_mutableblockposition, iblockdata2, false);
/*    */         } 
/*    */         
/* 55 */         blockposition_mutableblockposition.d(k1, k2, l1);
/*    */       } 
/*    */       
/* 58 */       if ((iblockdata4.isAir() || iblockdata4 == iblockdata1) && iblockdata5.a(iblockdata.getBlock())) {
/* 59 */         for (int l2 = 0; l2 < j2 && ichunkaccess.getType(blockposition_mutableblockposition).a(iblockdata.getBlock()); l2++) {
/* 60 */           if (flag && k2 >= j1 - 4 && k2 <= j1 + 1) {
/* 61 */             ichunkaccess.setType(blockposition_mutableblockposition, c(), false);
/*    */           } else {
/* 63 */             ichunkaccess.setType(blockposition_mutableblockposition, iblockdata3, false);
/*    */           } 
/*    */           
/* 66 */           blockposition_mutableblockposition.c(EnumDirection.DOWN);
/*    */         } 
/*    */       }
/*    */       
/* 70 */       iblockdata4 = iblockdata5;
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void a(long i) {
/* 77 */     if (this.a != i || this.d == null || this.b.isEmpty() || this.c.isEmpty()) {
/* 78 */       this.b = a(a(), i);
/* 79 */       this.c = a(b(), i + this.b.size());
/* 80 */       this.d = new NoiseGeneratorOctaves(new SeededRandom(i + this.b.size() + this.c.size()), (List<Integer>)ImmutableList.of(Integer.valueOf(0)));
/*    */     } 
/*    */     
/* 83 */     this.a = i;
/*    */   }
/*    */   
/*    */   private static ImmutableMap<IBlockData, NoiseGeneratorOctaves> a(ImmutableList<IBlockData> immutablelist, long i) {
/* 87 */     ImmutableMap.Builder<IBlockData, NoiseGeneratorOctaves> builder = new ImmutableMap.Builder();
/*    */     
/* 89 */     for (UnmodifiableIterator unmodifiableiterator = immutablelist.iterator(); unmodifiableiterator.hasNext(); i++) {
/* 90 */       IBlockData iblockdata = (IBlockData)unmodifiableiterator.next();
/*    */       
/* 92 */       builder.put(iblockdata, new NoiseGeneratorOctaves(new SeededRandom(i), (List<Integer>)ImmutableList.of(Integer.valueOf(-4))));
/*    */     } 
/*    */     
/* 95 */     return builder.build();
/*    */   }
/*    */   
/*    */   protected abstract ImmutableList<IBlockData> a();
/*    */   
/*    */   protected abstract ImmutableList<IBlockData> b();
/*    */   
/*    */   protected abstract IBlockData c();
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenSurfaceNetherAbstract.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
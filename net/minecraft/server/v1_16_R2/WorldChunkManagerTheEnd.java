/*    */ package net.minecraft.server.v1_16_R2;
/*    */ import com.mojang.datafixers.kinds.App;
/*    */ import com.mojang.datafixers.kinds.Applicative;
/*    */ import com.mojang.serialization.Codec;
/*    */ import com.mojang.serialization.codecs.RecordCodecBuilder;
/*    */ import java.util.function.BiFunction;
/*    */ 
/*    */ public class WorldChunkManagerTheEnd extends WorldChunkManager {
/*    */   static {
/* 10 */     e = RecordCodecBuilder.create(instance -> instance.group((App)RegistryLookupCodec.<BiomeBase>a(IRegistry.ay).forGetter(()), (App)Codec.LONG.fieldOf("seed").stable().forGetter(())).apply((Applicative)instance, instance.stable(WorldChunkManagerTheEnd::new)));
/*    */   }
/*    */ 
/*    */   
/*    */   public static final Codec<WorldChunkManagerTheEnd> e;
/*    */   
/*    */   private final NoiseGenerator3Handler f;
/*    */   
/*    */   private final IRegistry<BiomeBase> g;
/*    */   private final long h;
/*    */   private final BiomeBase i;
/*    */   private final BiomeBase j;
/*    */   private final BiomeBase k;
/*    */   private final BiomeBase l;
/*    */   private final BiomeBase m;
/*    */   
/*    */   public WorldChunkManagerTheEnd(IRegistry<BiomeBase> iregistry, long i) {
/* 27 */     this(iregistry, i, iregistry.d(Biomes.THE_END), iregistry.d(Biomes.END_HIGHLANDS), iregistry.d(Biomes.END_MIDLANDS), iregistry.d(Biomes.SMALL_END_ISLANDS), iregistry.d(Biomes.END_BARRENS));
/*    */   }
/*    */   
/*    */   private WorldChunkManagerTheEnd(IRegistry<BiomeBase> iregistry, long i, BiomeBase biomebase, BiomeBase biomebase1, BiomeBase biomebase2, BiomeBase biomebase3, BiomeBase biomebase4) {
/* 31 */     super((List<BiomeBase>)ImmutableList.of(biomebase, biomebase1, biomebase2, biomebase3, biomebase4));
/* 32 */     this.g = iregistry;
/* 33 */     this.h = i;
/* 34 */     this.i = biomebase;
/* 35 */     this.j = biomebase1;
/* 36 */     this.k = biomebase2;
/* 37 */     this.l = biomebase3;
/* 38 */     this.m = biomebase4;
/* 39 */     SeededRandom seededrandom = new SeededRandom(i);
/*    */     
/* 41 */     seededrandom.a(17292);
/* 42 */     this.f = new NoiseGenerator3Handler(seededrandom);
/*    */   }
/*    */ 
/*    */   
/*    */   protected Codec<? extends WorldChunkManager> a() {
/* 47 */     return (Codec)e;
/*    */   }
/*    */ 
/*    */   
/*    */   public BiomeBase getBiome(int i, int j, int k) {
/* 52 */     int l = i >> 2;
/* 53 */     int i1 = k >> 2;
/*    */     
/* 55 */     if (l * l + i1 * i1 <= 4096L) {
/* 56 */       return this.i;
/*    */     }
/* 58 */     float f = a(this.f, l * 2 + 1, i1 * 2 + 1);
/*    */     
/* 60 */     return (f > 40.0F) ? this.j : ((f >= 0.0F) ? this.k : ((f < -20.0F) ? this.l : this.m));
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean b(long i) {
/* 65 */     return (this.h == i);
/*    */   }
/*    */   
/*    */   public static float a(NoiseGenerator3Handler noisegenerator3handler, int i, int j) {
/* 69 */     int k = i / 2;
/* 70 */     int l = j / 2;
/* 71 */     int i1 = i % 2;
/* 72 */     int j1 = j % 2;
/*    */     
/* 74 */     float f = 100.0F - MathHelper.sqrt((i * i + j * j)) * 8.0F;
/*    */ 
/*    */     
/* 77 */     f = MathHelper.a(f, -100.0F, 80.0F);
/*    */     
/* 79 */     for (int k1 = -12; k1 <= 12; k1++) {
/* 80 */       for (int l1 = -12; l1 <= 12; l1++) {
/* 81 */         long i2 = (k + k1);
/* 82 */         long j2 = (l + l1);
/*    */         
/* 84 */         if (i2 * i2 + j2 * j2 > 4096L && noisegenerator3handler.a(i2, j2) < -0.8999999761581421D) {
/* 85 */           float f1 = (MathHelper.e((float)i2) * 3439.0F + MathHelper.e((float)j2) * 147.0F) % 13.0F + 9.0F;
/* 86 */           float f2 = (i1 - k1 * 2);
/* 87 */           float f3 = (j1 - l1 * 2);
/* 88 */           float f4 = 100.0F - MathHelper.c(f2 * f2 + f3 * f3) * f1;
/*    */           
/* 90 */           f4 = MathHelper.a(f4, -100.0F, 80.0F);
/* 91 */           f = Math.max(f, f4);
/*    */         } 
/*    */       } 
/*    */     } 
/*    */     
/* 96 */     return f;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldChunkManagerTheEnd.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
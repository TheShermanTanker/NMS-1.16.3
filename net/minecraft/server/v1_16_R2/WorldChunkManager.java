/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.ImmutableList;
/*     */ import com.google.common.collect.Maps;
/*     */ import com.google.common.collect.Sets;
/*     */ import com.mojang.serialization.Codec;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Random;
/*     */ import java.util.Set;
/*     */ import java.util.function.Function;
/*     */ import java.util.function.Predicate;
/*     */ import java.util.function.Supplier;
/*     */ import java.util.stream.Stream;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class WorldChunkManager
/*     */   implements BiomeManager.Provider
/*     */ {
/*     */   static {
/*  24 */     IRegistry.a(IRegistry.BIOME_SOURCE, "fixed", WorldChunkManagerHell.e);
/*  25 */     IRegistry.a(IRegistry.BIOME_SOURCE, "multi_noise", WorldChunkManagerMultiNoise.f);
/*  26 */     IRegistry.a(IRegistry.BIOME_SOURCE, "checkerboard", WorldChunkManagerCheckerBoard.e);
/*  27 */     IRegistry.a(IRegistry.BIOME_SOURCE, "vanilla_layered", WorldChunkManagerOverworld.e);
/*  28 */     IRegistry.a(IRegistry.BIOME_SOURCE, "the_end", WorldChunkManagerTheEnd.e);
/*     */   }
/*     */   
/*  31 */   public static final Codec<WorldChunkManager> a = IRegistry.BIOME_SOURCE.dispatchStable(WorldChunkManager::a, Function.identity());
/*     */   
/*  33 */   protected final Map<StructureGenerator<?>, Boolean> b = Maps.newHashMap();
/*  34 */   protected final Set<IBlockData> c = Sets.newHashSet();
/*     */   protected final List<BiomeBase> d;
/*     */   
/*     */   protected WorldChunkManager(Stream<Supplier<BiomeBase>> var0) {
/*  38 */     this((List<BiomeBase>)var0.map(Supplier::get).collect(ImmutableList.toImmutableList()));
/*     */   }
/*     */   
/*     */   protected WorldChunkManager(List<BiomeBase> var0) {
/*  42 */     this.d = var0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<BiomeBase> b() {
/*  50 */     return this.d;
/*     */   }
/*     */   
/*     */   public Set<BiomeBase> a(int var0, int var1, int var2, int var3) {
/*  54 */     int var4 = var0 - var3 >> 2;
/*  55 */     int var5 = var1 - var3 >> 2;
/*  56 */     int var6 = var2 - var3 >> 2;
/*  57 */     int var7 = var0 + var3 >> 2;
/*  58 */     int var8 = var1 + var3 >> 2;
/*  59 */     int var9 = var2 + var3 >> 2;
/*     */     
/*  61 */     int var10 = var7 - var4 + 1;
/*  62 */     int var11 = var8 - var5 + 1;
/*  63 */     int var12 = var9 - var6 + 1;
/*     */     
/*  65 */     Set<BiomeBase> var13 = Sets.newHashSet();
/*     */     
/*  67 */     for (int var14 = 0; var14 < var12; var14++) {
/*  68 */       for (int var15 = 0; var15 < var10; var15++) {
/*  69 */         for (int var16 = 0; var16 < var11; var16++) {
/*  70 */           int var17 = var4 + var15;
/*  71 */           int var18 = var5 + var16;
/*  72 */           int var19 = var6 + var14;
/*  73 */           var13.add(getBiome(var17, var18, var19));
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/*  78 */     return var13;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public BlockPosition a(int var0, int var1, int var2, int var3, Predicate<BiomeBase> var4, Random var5) {
/*  83 */     return a(var0, var1, var2, var3, 1, var4, var5, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public BlockPosition a(int var0, int var1, int var2, int var3, int var4, Predicate<BiomeBase> var5, Random var6, boolean var7) {
/*  95 */     int var8 = var0 >> 2;
/*  96 */     int var9 = var2 >> 2;
/*  97 */     int var10 = var3 >> 2;
/*     */     
/*  99 */     int var11 = var1 >> 2;
/*     */     
/* 101 */     BlockPosition var12 = null;
/* 102 */     int var13 = 0;
/*     */     
/* 104 */     int var14 = var7 ? 0 : var10; int var15;
/* 105 */     for (var15 = var14; var15 <= var10; var15 += var4) {
/* 106 */       int var16; for (var16 = -var15; var16 <= var15; var16 += var4) {
/* 107 */         boolean var17 = (Math.abs(var16) == var15); int var18;
/* 108 */         for (var18 = -var15; var18 <= var15; var18 += var4) {
/* 109 */           if (var7) {
/*     */             
/* 111 */             boolean bool = (Math.abs(var18) == var15);
/* 112 */             if (!bool && !var17) {
/*     */               continue;
/*     */             }
/*     */           } 
/*     */           
/* 117 */           int var19 = var8 + var18;
/* 118 */           int var20 = var9 + var16;
/* 119 */           if (var5.test(getBiome(var19, var11, var20))) {
/* 120 */             if (var12 == null || var6.nextInt(var13 + 1) == 0) {
/* 121 */               var12 = new BlockPosition(var19 << 2, var1, var20 << 2);
/* 122 */               if (var7) {
/* 123 */                 return var12;
/*     */               }
/*     */             } 
/* 126 */             var13++;
/*     */           } 
/*     */           continue;
/*     */         } 
/*     */       } 
/*     */     } 
/* 132 */     return var12;
/*     */   }
/*     */   
/*     */   public boolean a(StructureGenerator<?> var0) {
/* 136 */     return ((Boolean)this.b.computeIfAbsent(var0, var0 -> Boolean.valueOf(this.d.stream().anyMatch(())))).booleanValue();
/*     */   }
/*     */   
/*     */   public Set<IBlockData> c() {
/* 140 */     if (this.c.isEmpty()) {
/* 141 */       for (BiomeBase var1 : this.d) {
/* 142 */         this.c.add(var1.e().e().a());
/*     */       }
/*     */     }
/* 145 */     return this.c;
/*     */   }
/*     */   
/*     */   protected abstract Codec<? extends WorldChunkManager> a();
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldChunkManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
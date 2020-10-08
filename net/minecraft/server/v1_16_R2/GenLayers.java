/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.ints.Int2IntMap;
/*     */ import it.unimi.dsi.fastutil.ints.Int2IntOpenHashMap;
/*     */ import java.util.function.LongFunction;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GenLayers
/*     */ {
/*     */   private static final Int2IntMap a;
/*     */   
/*     */   private static <T extends Area, C extends AreaContextTransformed<T>> AreaFactory<T> a(long var0, AreaTransformer2 var2, AreaFactory<T> var3, int var4, LongFunction<C> var5) {
/*  24 */     AreaFactory<T> var6 = var3;
/*  25 */     for (int var7 = 0; var7 < var4; var7++) {
/*  26 */       var6 = var2.a((AreaContextTransformed<T>)var5.apply(var0 + var7), var6);
/*     */     }
/*  28 */     return var6;
/*     */   }
/*     */   
/*     */   private static <T extends Area, C extends AreaContextTransformed<T>> AreaFactory<T> a(boolean var0, int var1, int var2, LongFunction<C> var3) {
/*  32 */     AreaFactory<T> var4 = LayerIsland.INSTANCE.a((AreaContextTransformed<T>)var3.apply(1L));
/*  33 */     var4 = GenLayerZoom.FUZZY.a((AreaContextTransformed<T>)var3.apply(2000L), var4);
/*  34 */     var4 = GenLayerIsland.INSTANCE.a((AreaContextTransformed<T>)var3.apply(1L), var4);
/*  35 */     var4 = GenLayerZoom.NORMAL.a((AreaContextTransformed<T>)var3.apply(2001L), var4);
/*  36 */     var4 = GenLayerIsland.INSTANCE.a((AreaContextTransformed<T>)var3.apply(2L), var4);
/*  37 */     var4 = GenLayerIsland.INSTANCE.a((AreaContextTransformed<T>)var3.apply(50L), var4);
/*  38 */     var4 = GenLayerIsland.INSTANCE.a((AreaContextTransformed<T>)var3.apply(70L), var4);
/*  39 */     var4 = GenLayerIcePlains.INSTANCE.a((AreaContextTransformed<T>)var3.apply(2L), var4);
/*     */     
/*  41 */     AreaFactory<T> var5 = GenLayerOceanEdge.INSTANCE.a((AreaContextTransformed<T>)var3.apply(2L));
/*  42 */     var5 = a(2001L, GenLayerZoom.NORMAL, var5, 6, var3);
/*     */     
/*  44 */     var4 = GenLayerTopSoil.INSTANCE.a((AreaContextTransformed<T>)var3.apply(2L), var4);
/*  45 */     var4 = GenLayerIsland.INSTANCE.a((AreaContextTransformed<T>)var3.apply(3L), var4);
/*  46 */     var4 = GenLayerSpecial.Special1.INSTANCE.a((AreaContextTransformed<T>)var3.apply(2L), var4);
/*  47 */     var4 = GenLayerSpecial.Special2.INSTANCE.a((AreaContextTransformed<T>)var3.apply(2L), var4);
/*  48 */     var4 = GenLayerSpecial.Special3.INSTANCE.a((AreaContextTransformed<T>)var3.apply(3L), var4);
/*  49 */     var4 = GenLayerZoom.NORMAL.a((AreaContextTransformed<T>)var3.apply(2002L), var4);
/*  50 */     var4 = GenLayerZoom.NORMAL.a((AreaContextTransformed<T>)var3.apply(2003L), var4);
/*  51 */     var4 = GenLayerIsland.INSTANCE.a((AreaContextTransformed<T>)var3.apply(4L), var4);
/*  52 */     var4 = GenLayerMushroomIsland.INSTANCE.a((AreaContextTransformed<T>)var3.apply(5L), var4);
/*  53 */     var4 = GenLayerDeepOcean.INSTANCE.a((AreaContextTransformed<T>)var3.apply(4L), var4);
/*  54 */     var4 = a(1000L, GenLayerZoom.NORMAL, var4, 0, var3);
/*     */     
/*  56 */     AreaFactory<T> var6 = var4;
/*  57 */     var6 = a(1000L, GenLayerZoom.NORMAL, var6, 0, var3);
/*  58 */     var6 = GenLayerCleaner.INSTANCE.a((AreaContextTransformed<T>)var3.apply(100L), var6);
/*     */     
/*  60 */     AreaFactory<T> var7 = var4;
/*  61 */     var7 = (new GenLayerBiome(var0)).a((AreaContextTransformed<T>)var3.apply(200L), var7);
/*  62 */     var7 = GenLayerJungle.INSTANCE.a((AreaContextTransformed<T>)var3.apply(1001L), var7);
/*  63 */     var7 = a(1000L, GenLayerZoom.NORMAL, var7, 2, var3);
/*  64 */     var7 = GenLayerDesert.INSTANCE.a((AreaContextTransformed<T>)var3.apply(1000L), var7);
/*  65 */     AreaFactory<T> var8 = var6;
/*  66 */     var8 = a(1000L, GenLayerZoom.NORMAL, var8, 2, var3);
/*  67 */     var7 = GenLayerRegionHills.INSTANCE.a((AreaContextTransformed<T>)var3.apply(1000L), var7, var8);
/*     */     
/*  69 */     var6 = a(1000L, GenLayerZoom.NORMAL, var6, 2, var3);
/*  70 */     var6 = a(1000L, GenLayerZoom.NORMAL, var6, var2, var3);
/*  71 */     var6 = GenLayerRiver.INSTANCE.a((AreaContextTransformed<T>)var3.apply(1L), var6);
/*  72 */     var6 = GenLayerSmooth.INSTANCE.a((AreaContextTransformed<T>)var3.apply(1000L), var6);
/*     */     
/*  74 */     var7 = GenLayerPlains.INSTANCE.a((AreaContextTransformed<T>)var3.apply(1001L), var7);
/*  75 */     for (int var9 = 0; var9 < var1; var9++) {
/*  76 */       var7 = GenLayerZoom.NORMAL.a((AreaContextTransformed<T>)var3.apply((1000 + var9)), var7);
/*  77 */       if (var9 == 0) {
/*  78 */         var7 = GenLayerIsland.INSTANCE.a((AreaContextTransformed<T>)var3.apply(3L), var7);
/*     */       }
/*     */       
/*  81 */       if (var9 == 1 || var1 == 1) {
/*  82 */         var7 = GenLayerMushroomShore.INSTANCE.a((AreaContextTransformed<T>)var3.apply(1000L), var7);
/*     */       }
/*     */     } 
/*     */     
/*  86 */     var7 = GenLayerSmooth.INSTANCE.a((AreaContextTransformed<T>)var3.apply(1000L), var7);
/*     */     
/*  88 */     var7 = GenLayerRiverMix.INSTANCE.a((AreaContextTransformed<T>)var3.apply(100L), var7, var6);
/*  89 */     var7 = GenLayerOcean.INSTANCE.a((AreaContextTransformed<T>)var3.apply(100L), var7, var5);
/*     */     
/*  91 */     return var7;
/*     */   }
/*     */ 
/*     */   
/*     */   public static GenLayer a(long var0, boolean var2, int var3, int var4) {
/*  96 */     int var5 = 25;
/*  97 */     AreaFactory<AreaLazy> var6 = a(var2, var3, var4, var2 -> new WorldGenContextArea(25, var0, var2));
/*  98 */     return new GenLayer(var6);
/*     */   }
/*     */   
/*     */   public static boolean a(int var0, int var1) {
/* 102 */     if (var0 == var1) {
/* 103 */       return true;
/*     */     }
/*     */     
/* 106 */     return (a.get(var0) == a.get(var1));
/*     */   }
/*     */   
/*     */   enum Type {
/* 110 */     NONE,
/* 111 */     TAIGA,
/* 112 */     EXTREME_HILLS,
/* 113 */     JUNGLE,
/* 114 */     MESA,
/* 115 */     BADLANDS_PLATEAU,
/* 116 */     PLAINS,
/* 117 */     SAVANNA,
/* 118 */     ICY,
/* 119 */     BEACH,
/* 120 */     FOREST,
/* 121 */     OCEAN,
/* 122 */     DESERT,
/* 123 */     RIVER,
/* 124 */     SWAMP,
/* 125 */     MUSHROOM;
/*     */   }
/*     */   
/*     */   static {
/* 129 */     a = (Int2IntMap)SystemUtils.a(new Int2IntOpenHashMap(), var0 -> {
/*     */           a(var0, Type.BEACH, 16);
/*     */           a(var0, Type.BEACH, 26);
/*     */           a(var0, Type.DESERT, 2);
/*     */           a(var0, Type.DESERT, 17);
/*     */           a(var0, Type.DESERT, 130);
/*     */           a(var0, Type.EXTREME_HILLS, 131);
/*     */           a(var0, Type.EXTREME_HILLS, 162);
/*     */           a(var0, Type.EXTREME_HILLS, 20);
/*     */           a(var0, Type.EXTREME_HILLS, 3);
/*     */           a(var0, Type.EXTREME_HILLS, 34);
/*     */           a(var0, Type.FOREST, 27);
/*     */           a(var0, Type.FOREST, 28);
/*     */           a(var0, Type.FOREST, 29);
/*     */           a(var0, Type.FOREST, 157);
/*     */           a(var0, Type.FOREST, 132);
/*     */           a(var0, Type.FOREST, 4);
/*     */           a(var0, Type.FOREST, 155);
/*     */           a(var0, Type.FOREST, 156);
/*     */           a(var0, Type.FOREST, 18);
/*     */           a(var0, Type.ICY, 140);
/*     */           a(var0, Type.ICY, 13);
/*     */           a(var0, Type.ICY, 12);
/*     */           a(var0, Type.JUNGLE, 168);
/*     */           a(var0, Type.JUNGLE, 169);
/*     */           a(var0, Type.JUNGLE, 21);
/*     */           a(var0, Type.JUNGLE, 23);
/*     */           a(var0, Type.JUNGLE, 22);
/*     */           a(var0, Type.JUNGLE, 149);
/*     */           a(var0, Type.JUNGLE, 151);
/*     */           a(var0, Type.MESA, 37);
/*     */           a(var0, Type.MESA, 165);
/*     */           a(var0, Type.MESA, 167);
/*     */           a(var0, Type.MESA, 166);
/*     */           a(var0, Type.BADLANDS_PLATEAU, 39);
/*     */           a(var0, Type.BADLANDS_PLATEAU, 38);
/*     */           a(var0, Type.MUSHROOM, 14);
/*     */           a(var0, Type.MUSHROOM, 15);
/*     */           a(var0, Type.NONE, 25);
/*     */           a(var0, Type.OCEAN, 46);
/*     */           a(var0, Type.OCEAN, 49);
/*     */           a(var0, Type.OCEAN, 50);
/*     */           a(var0, Type.OCEAN, 48);
/*     */           a(var0, Type.OCEAN, 24);
/*     */           a(var0, Type.OCEAN, 47);
/*     */           a(var0, Type.OCEAN, 10);
/*     */           a(var0, Type.OCEAN, 45);
/*     */           a(var0, Type.OCEAN, 0);
/*     */           a(var0, Type.OCEAN, 44);
/*     */           a(var0, Type.PLAINS, 1);
/*     */           a(var0, Type.PLAINS, 129);
/*     */           a(var0, Type.RIVER, 11);
/*     */           a(var0, Type.RIVER, 7);
/*     */           a(var0, Type.SAVANNA, 35);
/*     */           a(var0, Type.SAVANNA, 36);
/*     */           a(var0, Type.SAVANNA, 163);
/*     */           a(var0, Type.SAVANNA, 164);
/*     */           a(var0, Type.SWAMP, 6);
/*     */           a(var0, Type.SWAMP, 134);
/*     */           a(var0, Type.TAIGA, 160);
/*     */           a(var0, Type.TAIGA, 161);
/*     */           a(var0, Type.TAIGA, 32);
/*     */           a(var0, Type.TAIGA, 33);
/*     */           a(var0, Type.TAIGA, 30);
/*     */           a(var0, Type.TAIGA, 31);
/*     */           a(var0, Type.TAIGA, 158);
/*     */           a(var0, Type.TAIGA, 5);
/*     */           a(var0, Type.TAIGA, 19);
/*     */           a(var0, Type.TAIGA, 133);
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void a(Int2IntOpenHashMap var0, Type var1, int var2) {
/* 216 */     var0.put(var2, var1.ordinal());
/*     */   }
/*     */   
/*     */   protected static boolean a(int var0) {
/* 220 */     return (var0 == 44 || var0 == 45 || var0 == 0 || var0 == 46 || var0 == 10 || var0 == 47 || var0 == 48 || var0 == 24 || var0 == 49 || var0 == 50);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static boolean b(int var0) {
/* 234 */     return (var0 == 44 || var0 == 45 || var0 == 0 || var0 == 46 || var0 == 10);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\GenLayers.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
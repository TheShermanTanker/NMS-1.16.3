/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.ints.Int2IntMap;
/*     */ import it.unimi.dsi.fastutil.ints.Int2IntOpenHashMap;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public enum GenLayerRegionHills
/*     */   implements AreaTransformer3, AreaTransformerOffset1
/*     */ {
/*  14 */   INSTANCE; private static final Int2IntMap c;
/*     */   static {
/*  16 */     LOGGER = LogManager.getLogger();
/*     */     
/*  18 */     c = (Int2IntMap)SystemUtils.a(new Int2IntOpenHashMap(), var0 -> {
/*     */           var0.put(1, 129);
/*     */           var0.put(2, 130);
/*     */           var0.put(3, 131);
/*     */           var0.put(4, 132);
/*     */           var0.put(5, 133);
/*     */           var0.put(6, 134);
/*     */           var0.put(12, 140);
/*     */           var0.put(21, 149);
/*     */           var0.put(23, 151);
/*     */           var0.put(27, 155);
/*     */           var0.put(28, 156);
/*     */           var0.put(29, 157);
/*     */           var0.put(30, 158);
/*     */           var0.put(32, 160);
/*     */           var0.put(33, 161);
/*     */           var0.put(34, 162);
/*     */           var0.put(35, 163);
/*     */           var0.put(36, 164);
/*     */           var0.put(37, 165);
/*     */           var0.put(38, 166);
/*     */           var0.put(39, 167);
/*     */         });
/*     */   }
/*     */   private static final Logger LOGGER;
/*     */   public int a(WorldGenContext var0, Area var1, Area var2, int var3, int var4) {
/*  44 */     int var5 = var1.a(a(var3 + 1), b(var4 + 1));
/*  45 */     int var6 = var2.a(a(var3 + 1), b(var4 + 1));
/*     */     
/*  47 */     if (var5 > 255) {
/*  48 */       LOGGER.debug("old! {}", Integer.valueOf(var5));
/*     */     }
/*     */     
/*  51 */     int var7 = (var6 - 2) % 29;
/*  52 */     if (!GenLayers.b(var5) && var6 >= 2 && var7 == 1) {
/*  53 */       return c.getOrDefault(var5, var5);
/*     */     }
/*     */     
/*  56 */     if (var0.a(3) == 0 || var7 == 0) {
/*  57 */       int var8 = var5;
/*  58 */       if (var5 == 2) {
/*  59 */         var8 = 17;
/*  60 */       } else if (var5 == 4) {
/*  61 */         var8 = 18;
/*  62 */       } else if (var5 == 27) {
/*  63 */         var8 = 28;
/*  64 */       } else if (var5 == 29) {
/*  65 */         var8 = 1;
/*  66 */       } else if (var5 == 5) {
/*  67 */         var8 = 19;
/*  68 */       } else if (var5 == 32) {
/*  69 */         var8 = 33;
/*  70 */       } else if (var5 == 30) {
/*  71 */         var8 = 31;
/*  72 */       } else if (var5 == 1) {
/*  73 */         var8 = (var0.a(3) == 0) ? 18 : 4;
/*  74 */       } else if (var5 == 12) {
/*  75 */         var8 = 13;
/*  76 */       } else if (var5 == 21) {
/*  77 */         var8 = 22;
/*  78 */       } else if (var5 == 168) {
/*  79 */         var8 = 169;
/*  80 */       } else if (var5 == 0) {
/*  81 */         var8 = 24;
/*  82 */       } else if (var5 == 45) {
/*  83 */         var8 = 48;
/*  84 */       } else if (var5 == 46) {
/*  85 */         var8 = 49;
/*  86 */       } else if (var5 == 10) {
/*  87 */         var8 = 50;
/*  88 */       } else if (var5 == 3) {
/*  89 */         var8 = 34;
/*  90 */       } else if (var5 == 35) {
/*  91 */         var8 = 36;
/*  92 */       } else if (GenLayers.a(var5, 38)) {
/*  93 */         var8 = 37;
/*  94 */       } else if ((var5 == 24 || var5 == 48 || var5 == 49 || var5 == 50) && 
/*  95 */         var0.a(3) == 0) {
/*  96 */         var8 = (var0.a(2) == 0) ? 1 : 4;
/*     */       } 
/*     */       
/*  99 */       if (var7 == 0 && var8 != var5) {
/* 100 */         var8 = c.getOrDefault(var8, var5);
/*     */       }
/*     */       
/* 103 */       if (var8 != var5) {
/* 104 */         int var9 = 0;
/* 105 */         if (GenLayers.a(var1.a(a(var3 + 1), b(var4 + 0)), var5)) {
/* 106 */           var9++;
/*     */         }
/* 108 */         if (GenLayers.a(var1.a(a(var3 + 2), b(var4 + 1)), var5)) {
/* 109 */           var9++;
/*     */         }
/* 111 */         if (GenLayers.a(var1.a(a(var3 + 0), b(var4 + 1)), var5)) {
/* 112 */           var9++;
/*     */         }
/* 114 */         if (GenLayers.a(var1.a(a(var3 + 1), b(var4 + 2)), var5)) {
/* 115 */           var9++;
/*     */         }
/* 117 */         if (var9 >= 3) {
/* 118 */           return var8;
/*     */         }
/*     */       } 
/*     */     } 
/* 122 */     return var5;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\GenLayerRegionHills.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
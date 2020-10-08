/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.mojang.datafixers.DSL;
/*     */ import com.mojang.datafixers.DataFix;
/*     */ import com.mojang.datafixers.DataFixUtils;
/*     */ import com.mojang.datafixers.OpticFinder;
/*     */ import com.mojang.datafixers.TypeRewriteRule;
/*     */ import com.mojang.datafixers.Typed;
/*     */ import com.mojang.datafixers.schemas.Schema;
/*     */ import com.mojang.datafixers.types.Type;
/*     */ import com.mojang.datafixers.types.templates.List;
/*     */ import com.mojang.datafixers.util.Pair;
/*     */ import com.mojang.serialization.Dynamic;
/*     */ import java.util.List;
/*     */ import java.util.stream.LongStream;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DataConverterBitStorageAlign
/*     */   extends DataFix
/*     */ {
/*     */   public DataConverterBitStorageAlign(Schema var0) {
/*  28 */     super(var0, false);
/*     */   }
/*     */ 
/*     */   
/*     */   protected TypeRewriteRule makeRule() {
/*  33 */     Type<?> var0 = getInputSchema().getType(DataConverterTypes.CHUNK);
/*  34 */     Type<?> var1 = var0.findFieldType("Level");
/*     */     
/*  36 */     OpticFinder<?> var2 = DSL.fieldFinder("Level", var1);
/*  37 */     OpticFinder<?> var3 = var2.type().findField("Sections");
/*     */     
/*  39 */     Type<?> var4 = ((List.ListType)var3.type()).getElement();
/*  40 */     OpticFinder<?> var5 = DSL.typeFinder(var4);
/*     */     
/*  42 */     Type<Pair<String, Dynamic<?>>> var6 = DSL.named(DataConverterTypes.BLOCK_STATE.typeName(), DSL.remainderType());
/*  43 */     OpticFinder<List<Pair<String, Dynamic<?>>>> var7 = DSL.fieldFinder("Palette", (Type)DSL.list(var6));
/*     */     
/*  45 */     return fixTypeEverywhereTyped("BitStorageAlignFix", var0, getOutputSchema().getType(DataConverterTypes.CHUNK), var4 -> var4.updateTyped(var0, ()));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Typed<?> a(Typed<?> var0) {
/*  53 */     return var0.update(DSL.remainderFinder(), var0 -> var0.update("Heightmaps", ()));
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
/*     */   private static Typed<?> a(OpticFinder<?> var0, OpticFinder<?> var1, OpticFinder<List<Pair<String, Dynamic<?>>>> var2, Typed<?> var3) {
/*  65 */     return var3.updateTyped(var0, var2 -> var2.updateTyped(var0, ()));
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
/*     */   private static Dynamic<?> a(Dynamic<?> var0, Dynamic<?> var1, int var2, int var3) {
/*  81 */     long[] var4 = var1.asLongStream().toArray();
/*  82 */     long[] var5 = a(var2, var3, var4);
/*  83 */     return var0.createLongList(LongStream.of(var5));
/*     */   }
/*     */   
/*     */   public static long[] a(int var0, int var1, long[] var2) {
/*  87 */     int var3 = var2.length;
/*  88 */     if (var3 == 0) {
/*  89 */       return var2;
/*     */     }
/*     */     
/*  92 */     long var4 = (1L << var1) - 1L;
/*  93 */     int var6 = 64 / var1;
/*  94 */     int var7 = (var0 + var6 - 1) / var6;
/*     */     
/*  96 */     long[] var8 = new long[var7];
/*     */     
/*  98 */     int var9 = 0;
/*  99 */     int var10 = 0;
/* 100 */     long var11 = 0L;
/*     */     
/* 102 */     int var13 = 0;
/* 103 */     long var14 = var2[0];
/* 104 */     long var16 = (var3 > 1) ? var2[1] : 0L;
/*     */     
/* 106 */     for (int var18 = 0; var18 < var0; var18++) {
/* 107 */       long var23; int var19 = var18 * var1;
/* 108 */       int var20 = var19 >> 6;
/* 109 */       int var21 = (var18 + 1) * var1 - 1 >> 6;
/* 110 */       int var22 = var19 ^ var20 << 6;
/*     */       
/* 112 */       if (var20 != var13) {
/* 113 */         var14 = var16;
/* 114 */         var16 = (var20 + 1 < var3) ? var2[var20 + 1] : 0L;
/* 115 */         var13 = var20;
/*     */       } 
/*     */ 
/*     */       
/* 119 */       if (var20 == var21) {
/* 120 */         var23 = var14 >>> var22 & var4;
/*     */       } else {
/* 122 */         int i = 64 - var22;
/* 123 */         var23 = (var14 >>> var22 | var16 << i) & var4;
/*     */       } 
/*     */       
/* 126 */       int var25 = var10 + var1;
/* 127 */       if (var25 >= 64) {
/* 128 */         var8[var9++] = var11;
/* 129 */         var11 = var23;
/* 130 */         var10 = var1;
/*     */       } else {
/* 132 */         var11 |= var23 << var10;
/* 133 */         var10 = var25;
/*     */       } 
/*     */     } 
/* 136 */     if (var11 != 0L) {
/* 137 */       var8[var9] = var11;
/*     */     }
/*     */     
/* 140 */     return var8;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataConverterBitStorageAlign.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
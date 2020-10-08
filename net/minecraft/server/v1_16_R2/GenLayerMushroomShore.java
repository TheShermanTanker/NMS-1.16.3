/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
/*    */ import it.unimi.dsi.fastutil.ints.IntSet;
/*    */ 
/*    */ public enum GenLayerMushroomShore
/*    */   implements AreaTransformer7
/*    */ {
/*  9 */   INSTANCE;
/*    */   static {
/* 11 */     b = (IntSet)new IntOpenHashSet(new int[] { 26, 11, 12, 13, 140, 30, 31, 158, 10 });
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 23 */     c = (IntSet)new IntOpenHashSet(new int[] { 168, 169, 21, 22, 23, 149, 151 });
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   private static final IntSet c;
/*    */ 
/*    */   
/*    */   private static final IntSet b;
/*    */ 
/*    */   
/*    */   public int a(WorldGenContext var0, int var1, int var2, int var3, int var4, int var5) {
/* 35 */     if (var5 == 14) {
/* 36 */       if (GenLayers.b(var1) || GenLayers.b(var2) || GenLayers.b(var3) || GenLayers.b(var4)) {
/* 37 */         return 15;
/*    */       }
/* 39 */     } else if (c.contains(var5)) {
/* 40 */       if (!c(var1) || !c(var2) || !c(var3) || !c(var4))
/* 41 */         return 23; 
/* 42 */       if (GenLayers.a(var1) || GenLayers.a(var2) || GenLayers.a(var3) || GenLayers.a(var4)) {
/* 43 */         return 16;
/*    */       }
/* 45 */     } else if (var5 == 3 || var5 == 34 || var5 == 20) {
/* 46 */       if (!GenLayers.a(var5) && (GenLayers.a(var1) || GenLayers.a(var2) || GenLayers.a(var3) || GenLayers.a(var4))) {
/* 47 */         return 25;
/*    */       }
/* 49 */     } else if (b.contains(var5)) {
/* 50 */       if (!GenLayers.a(var5) && (GenLayers.a(var1) || GenLayers.a(var2) || GenLayers.a(var3) || GenLayers.a(var4))) {
/* 51 */         return 26;
/*    */       }
/* 53 */     } else if (var5 == 37 || var5 == 38) {
/* 54 */       if (!GenLayers.a(var1) && !GenLayers.a(var2) && !GenLayers.a(var3) && !GenLayers.a(var4) && (!d(var1) || !d(var2) || !d(var3) || !d(var4))) {
/* 55 */         return 2;
/*    */       }
/* 57 */     } else if (!GenLayers.a(var5) && var5 != 7 && var5 != 6 && (
/* 58 */       GenLayers.a(var1) || GenLayers.a(var2) || GenLayers.a(var3) || GenLayers.a(var4))) {
/* 59 */       return 16;
/*    */     } 
/*    */     
/* 62 */     return var5;
/*    */   }
/*    */   
/*    */   private static boolean c(int var0) {
/* 66 */     return (c.contains(var0) || var0 == 4 || var0 == 5 || GenLayers.a(var0));
/*    */   }
/*    */   
/*    */   private boolean d(int var0) {
/* 70 */     return (var0 == 37 || var0 == 38 || var0 == 39 || var0 == 165 || var0 == 166 || var0 == 167);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\GenLayerMushroomShore.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
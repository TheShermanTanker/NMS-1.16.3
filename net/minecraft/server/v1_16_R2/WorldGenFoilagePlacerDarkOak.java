/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.datafixers.kinds.App;
/*    */ import com.mojang.datafixers.kinds.Applicative;
/*    */ import com.mojang.serialization.Codec;
/*    */ import com.mojang.serialization.codecs.RecordCodecBuilder;
/*    */ import java.util.Random;
/*    */ import java.util.Set;
/*    */ import java.util.function.BiFunction;
/*    */ 
/*    */ public class WorldGenFoilagePlacerDarkOak extends WorldGenFoilagePlacer {
/*    */   public static final Codec<WorldGenFoilagePlacerDarkOak> a;
/*    */   
/*    */   static {
/* 15 */     a = RecordCodecBuilder.create(var0 -> b(var0).apply((Applicative)var0, WorldGenFoilagePlacerDarkOak::new));
/*    */   }
/*    */   public WorldGenFoilagePlacerDarkOak(IntSpread var0, IntSpread var1) {
/* 18 */     super(var0, var1);
/*    */   }
/*    */ 
/*    */   
/*    */   protected WorldGenFoilagePlacers<?> a() {
/* 23 */     return WorldGenFoilagePlacers.i;
/*    */   }
/*    */ 
/*    */   
/*    */   protected void a(VirtualLevelWritable var0, Random var1, WorldGenFeatureTreeConfiguration var2, int var3, WorldGenFoilagePlacer.b var4, int var5, int var6, Set<BlockPosition> var7, int var8, StructureBoundingBox var9) {
/* 28 */     BlockPosition var10 = var4.a().up(var8);
/* 29 */     boolean var11 = var4.c();
/*    */     
/* 31 */     if (var11) {
/* 32 */       a(var0, var1, var2, var10, var6 + 2, var7, -1, var11, var9);
/* 33 */       a(var0, var1, var2, var10, var6 + 3, var7, 0, var11, var9);
/* 34 */       a(var0, var1, var2, var10, var6 + 2, var7, 1, var11, var9);
/* 35 */       if (var1.nextBoolean()) {
/* 36 */         a(var0, var1, var2, var10, var6, var7, 2, var11, var9);
/*    */       }
/*    */     } else {
/* 39 */       a(var0, var1, var2, var10, var6 + 2, var7, -1, var11, var9);
/* 40 */       a(var0, var1, var2, var10, var6 + 1, var7, 0, var11, var9);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public int a(Random var0, int var1, WorldGenFeatureTreeConfiguration var2) {
/* 46 */     return 4;
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean b(Random var0, int var1, int var2, int var3, int var4, boolean var5) {
/* 51 */     if (var2 == 0 && var5 && (
/* 52 */       var1 == -var4 || var1 >= var4) && (var3 == -var4 || var3 >= var4)) {
/* 53 */       return true;
/*    */     }
/*    */     
/* 56 */     return super.b(var0, var1, var2, var3, var4, var5);
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean a(Random var0, int var1, int var2, int var3, int var4, boolean var5) {
/* 61 */     if (var2 == -1 && !var5) {
/* 62 */       return (var1 == var4 && var3 == var4);
/*    */     }
/* 64 */     if (var2 == 1) {
/* 65 */       return (var1 + var3 > var4 * 2 - 2);
/*    */     }
/* 67 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenFoilagePlacerDarkOak.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
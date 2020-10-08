/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.datafixers.kinds.App;
/*    */ import com.mojang.datafixers.kinds.Applicative;
/*    */ import com.mojang.datafixers.util.Function3;
/*    */ import com.mojang.serialization.Codec;
/*    */ import com.mojang.serialization.codecs.RecordCodecBuilder;
/*    */ import java.util.Random;
/*    */ import java.util.Set;
/*    */ 
/*    */ public class WorldGenFoilagePlacerMegaPine
/*    */   extends WorldGenFoilagePlacer {
/*    */   public static final Codec<WorldGenFoilagePlacerMegaPine> a;
/*    */   private final IntSpread b;
/*    */   
/*    */   static {
/* 17 */     a = RecordCodecBuilder.create(var0 -> b(var0).and((App)IntSpread.a(0, 16, 8).fieldOf("crown_height").forGetter(())).apply((Applicative)var0, WorldGenFoilagePlacerMegaPine::new));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public WorldGenFoilagePlacerMegaPine(IntSpread var0, IntSpread var1, IntSpread var2) {
/* 24 */     super(var0, var1);
/* 25 */     this.b = var2;
/*    */   }
/*    */ 
/*    */   
/*    */   protected WorldGenFoilagePlacers<?> a() {
/* 30 */     return WorldGenFoilagePlacers.h;
/*    */   }
/*    */ 
/*    */   
/*    */   protected void a(VirtualLevelWritable var0, Random var1, WorldGenFeatureTreeConfiguration var2, int var3, WorldGenFoilagePlacer.b var4, int var5, int var6, Set<BlockPosition> var7, int var8, StructureBoundingBox var9) {
/* 35 */     BlockPosition var10 = var4.a();
/*    */     
/* 37 */     int var11 = 0;
/* 38 */     for (int var12 = var10.getY() - var5 + var8; var12 <= var10.getY() + var8; var12++) {
/* 39 */       int var15, var13 = var10.getY() - var12;
/* 40 */       int var14 = var6 + var4.b() + MathHelper.d(var13 / var5 * 3.5F);
/*    */       
/* 42 */       if (var13 > 0 && var14 == var11 && (var12 & 0x1) == 0) {
/* 43 */         var15 = var14 + 1;
/*    */       } else {
/* 45 */         var15 = var14;
/*    */       } 
/*    */       
/* 48 */       a(var0, var1, var2, new BlockPosition(var10.getX(), var12, var10.getZ()), var15, var7, 0, var4.c(), var9);
/* 49 */       var11 = var14;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public int a(Random var0, int var1, WorldGenFeatureTreeConfiguration var2) {
/* 55 */     return this.b.a(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean a(Random var0, int var1, int var2, int var3, int var4, boolean var5) {
/* 60 */     if (var1 + var3 >= 7) {
/* 61 */       return true;
/*    */     }
/* 63 */     return (var1 * var1 + var3 * var3 > var4 * var4);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenFoilagePlacerMegaPine.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
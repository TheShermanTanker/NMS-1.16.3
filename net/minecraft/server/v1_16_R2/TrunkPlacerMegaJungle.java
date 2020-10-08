/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.Lists;
/*    */ import com.mojang.datafixers.kinds.App;
/*    */ import com.mojang.datafixers.kinds.Applicative;
/*    */ import com.mojang.datafixers.util.Function3;
/*    */ import com.mojang.serialization.Codec;
/*    */ import com.mojang.serialization.codecs.RecordCodecBuilder;
/*    */ import java.util.List;
/*    */ import java.util.Random;
/*    */ import java.util.Set;
/*    */ 
/*    */ public class TrunkPlacerMegaJungle
/*    */   extends TrunkPlacerGiant {
/*    */   public static final Codec<TrunkPlacerMegaJungle> b;
/*    */   
/*    */   static {
/* 18 */     b = RecordCodecBuilder.create(var0 -> a(var0).apply((Applicative)var0, TrunkPlacerMegaJungle::new));
/*    */   }
/*    */   public TrunkPlacerMegaJungle(int var0, int var1, int var2) {
/* 21 */     super(var0, var1, var2);
/*    */   }
/*    */ 
/*    */   
/*    */   protected TrunkPlacers<?> a() {
/* 26 */     return TrunkPlacers.d;
/*    */   }
/*    */ 
/*    */   
/*    */   public List<WorldGenFoilagePlacer.b> a(VirtualLevelWritable var0, Random var1, int var2, BlockPosition var3, Set<BlockPosition> var4, StructureBoundingBox var5, WorldGenFeatureTreeConfiguration var6) {
/* 31 */     List<WorldGenFoilagePlacer.b> var7 = Lists.newArrayList();
/* 32 */     var7.addAll(super.a(var0, var1, var2, var3, var4, var5, var6));
/*    */     
/*    */     int var8;
/* 35 */     for (var8 = var2 - 2 - var1.nextInt(4); var8 > var2 / 2; var8 -= 2 + var1.nextInt(4)) {
/* 36 */       float var9 = var1.nextFloat() * 6.2831855F;
/* 37 */       int var10 = 0;
/* 38 */       int var11 = 0;
/*    */       
/* 40 */       for (int var12 = 0; var12 < 5; var12++) {
/* 41 */         var10 = (int)(1.5F + MathHelper.cos(var9) * var12);
/* 42 */         var11 = (int)(1.5F + MathHelper.sin(var9) * var12);
/* 43 */         BlockPosition var13 = var3.b(var10, var8 - 3 + var12 / 2, var11);
/* 44 */         a(var0, var1, var13, var4, var5, var6);
/*    */       } 
/*    */       
/* 47 */       var7.add(new WorldGenFoilagePlacer.b(var3.b(var10, var8, var11), -2, false));
/*    */     } 
/*    */     
/* 50 */     return var7;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\TrunkPlacerMegaJungle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
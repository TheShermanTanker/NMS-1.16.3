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
/*    */ public class TrunkPlacerForking
/*    */   extends TrunkPlacer {
/*    */   public static final Codec<TrunkPlacerForking> a;
/*    */   
/*    */   static {
/* 18 */     a = RecordCodecBuilder.create(var0 -> a(var0).apply((Applicative)var0, TrunkPlacerForking::new));
/*    */   }
/*    */   public TrunkPlacerForking(int var0, int var1, int var2) {
/* 21 */     super(var0, var1, var2);
/*    */   }
/*    */ 
/*    */   
/*    */   protected TrunkPlacers<?> a() {
/* 26 */     return TrunkPlacers.b;
/*    */   }
/*    */ 
/*    */   
/*    */   public List<WorldGenFoilagePlacer.b> a(VirtualLevelWritable var0, Random var1, int var2, BlockPosition var3, Set<BlockPosition> var4, StructureBoundingBox var5, WorldGenFeatureTreeConfiguration var6) {
/* 31 */     a(var0, var3.down());
/*    */     
/* 33 */     List<WorldGenFoilagePlacer.b> var7 = Lists.newArrayList();
/*    */     
/* 35 */     EnumDirection var8 = EnumDirection.EnumDirectionLimit.HORIZONTAL.a(var1);
/* 36 */     int var9 = var2 - var1.nextInt(4) - 1;
/* 37 */     int var10 = 3 - var1.nextInt(3);
/*    */     
/* 39 */     BlockPosition.MutableBlockPosition var11 = new BlockPosition.MutableBlockPosition();
/* 40 */     int var12 = var3.getX();
/* 41 */     int var13 = var3.getZ();
/* 42 */     int var14 = 0;
/* 43 */     for (int i = 0; i < var2; i++) {
/* 44 */       int var16 = var3.getY() + i;
/* 45 */       if (i >= var9 && var10 > 0) {
/* 46 */         var12 += var8.getAdjacentX();
/* 47 */         var13 += var8.getAdjacentZ();
/* 48 */         var10--;
/*    */       } 
/* 50 */       if (a(var0, var1, var11.d(var12, var16, var13), var4, var5, var6)) {
/* 51 */         var14 = var16 + 1;
/*    */       }
/*    */     } 
/* 54 */     var7.add(new WorldGenFoilagePlacer.b(new BlockPosition(var12, var14, var13), 1, false));
/*    */     
/* 56 */     var12 = var3.getX();
/* 57 */     var13 = var3.getZ();
/* 58 */     EnumDirection var15 = EnumDirection.EnumDirectionLimit.HORIZONTAL.a(var1);
/* 59 */     if (var15 != var8) {
/* 60 */       int var16 = var9 - var1.nextInt(2) - 1;
/* 61 */       int var17 = 1 + var1.nextInt(3);
/*    */       
/* 63 */       var14 = 0;
/* 64 */       for (int var18 = var16; var18 < var2 && var17 > 0; var18++, var17--) {
/* 65 */         if (var18 >= 1) {
/*    */ 
/*    */           
/* 68 */           int var19 = var3.getY() + var18;
/* 69 */           var12 += var15.getAdjacentX();
/* 70 */           var13 += var15.getAdjacentZ();
/* 71 */           if (a(var0, var1, var11.d(var12, var19, var13), var4, var5, var6))
/* 72 */             var14 = var19 + 1; 
/*    */         } 
/*    */       } 
/* 75 */       if (var14 > 1) {
/* 76 */         var7.add(new WorldGenFoilagePlacer.b(new BlockPosition(var12, var14, var13), 0, false));
/*    */       }
/*    */     } 
/*    */     
/* 80 */     return var7;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\TrunkPlacerForking.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
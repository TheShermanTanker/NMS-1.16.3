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
/*    */ public class TrunkPlacerDarkOak
/*    */   extends TrunkPlacer
/*    */ {
/*    */   public static final Codec<TrunkPlacerDarkOak> a;
/*    */   
/*    */   static {
/* 19 */     a = RecordCodecBuilder.create(var0 -> a(var0).apply((Applicative)var0, TrunkPlacerDarkOak::new));
/*    */   }
/*    */   public TrunkPlacerDarkOak(int var0, int var1, int var2) {
/* 22 */     super(var0, var1, var2);
/*    */   }
/*    */ 
/*    */   
/*    */   protected TrunkPlacers<?> a() {
/* 27 */     return TrunkPlacers.e;
/*    */   }
/*    */ 
/*    */   
/*    */   public List<WorldGenFoilagePlacer.b> a(VirtualLevelWritable var0, Random var1, int var2, BlockPosition var3, Set<BlockPosition> var4, StructureBoundingBox var5, WorldGenFeatureTreeConfiguration var6) {
/* 32 */     List<WorldGenFoilagePlacer.b> var7 = Lists.newArrayList();
/*    */     
/* 34 */     BlockPosition var8 = var3.down();
/* 35 */     a(var0, var8);
/* 36 */     a(var0, var8.east());
/* 37 */     a(var0, var8.south());
/* 38 */     a(var0, var8.south().east());
/*    */     
/* 40 */     EnumDirection var9 = EnumDirection.EnumDirectionLimit.HORIZONTAL.a(var1);
/* 41 */     int var10 = var2 - var1.nextInt(4);
/* 42 */     int var11 = 2 - var1.nextInt(3);
/*    */     
/* 44 */     int var12 = var3.getX();
/* 45 */     int var13 = var3.getY();
/* 46 */     int var14 = var3.getZ();
/*    */     
/* 48 */     int var15 = var12;
/* 49 */     int var16 = var14;
/* 50 */     int var17 = var13 + var2 - 1;
/*    */     
/*    */     int var18;
/* 53 */     for (var18 = 0; var18 < var2; var18++) {
/* 54 */       if (var18 >= var10 && var11 > 0) {
/* 55 */         var15 += var9.getAdjacentX();
/* 56 */         var16 += var9.getAdjacentZ();
/* 57 */         var11--;
/*    */       } 
/*    */       
/* 60 */       int var19 = var13 + var18;
/* 61 */       BlockPosition var20 = new BlockPosition(var15, var19, var16);
/* 62 */       if (WorldGenTrees.d(var0, var20)) {
/* 63 */         a(var0, var1, var20, var4, var5, var6);
/* 64 */         a(var0, var1, var20.east(), var4, var5, var6);
/* 65 */         a(var0, var1, var20.south(), var4, var5, var6);
/* 66 */         a(var0, var1, var20.east().south(), var4, var5, var6);
/*    */       } 
/*    */     } 
/*    */     
/* 70 */     var7.add(new WorldGenFoilagePlacer.b(new BlockPosition(var15, var17, var16), 0, true));
/*    */ 
/*    */     
/* 73 */     for (var18 = -1; var18 <= 2; var18++) {
/* 74 */       for (int var19 = -1; var19 <= 2; var19++) {
/* 75 */         if (var18 < 0 || var18 > 1 || var19 < 0 || var19 > 1)
/*    */         {
/*    */           
/* 78 */           if (var1.nextInt(3) <= 0) {
/*    */ 
/*    */             
/* 81 */             int var20 = var1.nextInt(3) + 2;
/* 82 */             for (int var21 = 0; var21 < var20; var21++) {
/* 83 */               a(var0, var1, new BlockPosition(var12 + var18, var17 - var21 - 1, var14 + var19), var4, var5, var6);
/*    */             }
/*    */             
/* 86 */             var7.add(new WorldGenFoilagePlacer.b(new BlockPosition(var15 + var18, var17, var16 + var19), 0, false));
/*    */           }  } 
/*    */       } 
/*    */     } 
/* 90 */     return var7;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\TrunkPlacerDarkOak.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
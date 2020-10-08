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
/*    */ public class WorldGenFoilagePlacerSpruce extends WorldGenFoilagePlacer {
/*    */   public static final Codec<WorldGenFoilagePlacerSpruce> a;
/*    */   
/*    */   static {
/* 15 */     a = RecordCodecBuilder.create(var0 -> b(var0).and((App)IntSpread.a(0, 16, 8).fieldOf("trunk_height").forGetter(())).apply((Applicative)var0, WorldGenFoilagePlacerSpruce::new));
/*    */   }
/*    */ 
/*    */   
/*    */   private final IntSpread b;
/*    */   
/*    */   public WorldGenFoilagePlacerSpruce(IntSpread var0, IntSpread var1, IntSpread var2) {
/* 22 */     super(var0, var1);
/* 23 */     this.b = var2;
/*    */   }
/*    */ 
/*    */   
/*    */   protected WorldGenFoilagePlacers<?> a() {
/* 28 */     return WorldGenFoilagePlacers.b;
/*    */   }
/*    */ 
/*    */   
/*    */   protected void a(VirtualLevelWritable var0, Random var1, WorldGenFeatureTreeConfiguration var2, int var3, WorldGenFoilagePlacer.b var4, int var5, int var6, Set<BlockPosition> var7, int var8, StructureBoundingBox var9) {
/* 33 */     BlockPosition var10 = var4.a();
/*    */     
/* 35 */     int var11 = var1.nextInt(2);
/* 36 */     int var12 = 1;
/* 37 */     int var13 = 0;
/*    */     
/* 39 */     for (int var14 = var8; var14 >= -var5; var14--) {
/* 40 */       a(var0, var1, var2, var10, var11, var7, var14, var4.c(), var9);
/*    */       
/* 42 */       if (var11 >= var12) {
/* 43 */         var11 = var13;
/* 44 */         var13 = 1;
/* 45 */         var12 = Math.min(var12 + 1, var6 + var4.b());
/*    */       } else {
/* 47 */         var11++;
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int a(Random var0, int var1, WorldGenFeatureTreeConfiguration var2) {
/* 55 */     return Math.max(4, var1 - this.b.a(var0));
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean a(Random var0, int var1, int var2, int var3, int var4, boolean var5) {
/* 60 */     return (var1 == var4 && var3 == var4 && var4 > 0);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenFoilagePlacerSpruce.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
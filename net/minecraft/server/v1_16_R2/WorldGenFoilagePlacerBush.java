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
/*    */ public class WorldGenFoilagePlacerBush extends WorldGenFoilagePlacerBlob {
/*    */   public static final Codec<WorldGenFoilagePlacerBush> c;
/*    */   
/*    */   static {
/* 15 */     c = RecordCodecBuilder.create(var0 -> a(var0).apply((Applicative)var0, WorldGenFoilagePlacerBush::new));
/*    */   }
/*    */   public WorldGenFoilagePlacerBush(IntSpread var0, IntSpread var1, int var2) {
/* 18 */     super(var0, var1, var2);
/*    */   }
/*    */ 
/*    */   
/*    */   protected WorldGenFoilagePlacers<?> a() {
/* 23 */     return WorldGenFoilagePlacers.e;
/*    */   }
/*    */ 
/*    */   
/*    */   protected void a(VirtualLevelWritable var0, Random var1, WorldGenFeatureTreeConfiguration var2, int var3, WorldGenFoilagePlacer.b var4, int var5, int var6, Set<BlockPosition> var7, int var8, StructureBoundingBox var9) {
/* 28 */     for (int var10 = var8; var10 >= var8 - var5; var10--) {
/* 29 */       int var11 = var6 + var4.b() - 1 - var10;
/* 30 */       a(var0, var1, var2, var4.a(), var11, var7, var10, var4.c(), var9);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean a(Random var0, int var1, int var2, int var3, int var4, boolean var5) {
/* 36 */     return (var1 == var4 && var3 == var4 && var0.nextInt(2) == 0);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenFoilagePlacerBush.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
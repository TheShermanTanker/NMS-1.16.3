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
/*    */ public class WorldGenFoilagePlacerFancy
/*    */   extends WorldGenFoilagePlacerBlob {
/*    */   public static final Codec<WorldGenFoilagePlacerFancy> c;
/*    */   
/*    */   static {
/* 16 */     c = RecordCodecBuilder.create(var0 -> a(var0).apply((Applicative)var0, WorldGenFoilagePlacerFancy::new));
/*    */   }
/*    */   public WorldGenFoilagePlacerFancy(IntSpread var0, IntSpread var1, int var2) {
/* 19 */     super(var0, var1, var2);
/*    */   }
/*    */ 
/*    */   
/*    */   protected WorldGenFoilagePlacers<?> a() {
/* 24 */     return WorldGenFoilagePlacers.f;
/*    */   }
/*    */ 
/*    */   
/*    */   protected void a(VirtualLevelWritable var0, Random var1, WorldGenFeatureTreeConfiguration var2, int var3, WorldGenFoilagePlacer.b var4, int var5, int var6, Set<BlockPosition> var7, int var8, StructureBoundingBox var9) {
/* 29 */     for (int var10 = var8; var10 >= var8 - var5; var10--) {
/* 30 */       int var11 = var6 + ((var10 == var8 || var10 == var8 - var5) ? 0 : 1);
/* 31 */       a(var0, var1, var2, var4.a(), var11, var7, var10, var4.c(), var9);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean a(Random var0, int var1, int var2, int var3, int var4, boolean var5) {
/* 37 */     return (MathHelper.k(var1 + 0.5F) + MathHelper.k(var3 + 0.5F) > (var4 * var4));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenFoilagePlacerFancy.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
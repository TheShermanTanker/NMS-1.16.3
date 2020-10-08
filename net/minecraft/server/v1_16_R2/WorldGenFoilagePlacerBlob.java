/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.datafixers.Products;
/*    */ import com.mojang.datafixers.kinds.App;
/*    */ import com.mojang.datafixers.kinds.Applicative;
/*    */ import com.mojang.datafixers.util.Function3;
/*    */ import com.mojang.serialization.Codec;
/*    */ import com.mojang.serialization.codecs.RecordCodecBuilder;
/*    */ import java.util.Random;
/*    */ import java.util.Set;
/*    */ 
/*    */ public class WorldGenFoilagePlacerBlob extends WorldGenFoilagePlacer {
/*    */   public static final Codec<WorldGenFoilagePlacerBlob> a;
/*    */   
/*    */   static {
/* 16 */     a = RecordCodecBuilder.create(var0 -> a(var0).apply((Applicative)var0, WorldGenFoilagePlacerBlob::new));
/*    */   } protected final int b;
/*    */   protected static <P extends WorldGenFoilagePlacerBlob> Products.P3<RecordCodecBuilder.Mu<P>, IntSpread, IntSpread, Integer> a(RecordCodecBuilder.Instance<P> var0) {
/* 19 */     return b((RecordCodecBuilder.Instance)var0).and(
/* 20 */         (App)Codec.intRange(0, 16).fieldOf("height").forGetter(var0 -> Integer.valueOf(var0.b)));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public WorldGenFoilagePlacerBlob(IntSpread var0, IntSpread var1, int var2) {
/* 27 */     super(var0, var1);
/* 28 */     this.b = var2;
/*    */   }
/*    */ 
/*    */   
/*    */   protected WorldGenFoilagePlacers<?> a() {
/* 33 */     return WorldGenFoilagePlacers.a;
/*    */   }
/*    */ 
/*    */   
/*    */   protected void a(VirtualLevelWritable var0, Random var1, WorldGenFeatureTreeConfiguration var2, int var3, WorldGenFoilagePlacer.b var4, int var5, int var6, Set<BlockPosition> var7, int var8, StructureBoundingBox var9) {
/* 38 */     for (int var10 = var8; var10 >= var8 - var5; var10--) {
/* 39 */       int var11 = Math.max(var6 + var4.b() - 1 - var10 / 2, 0);
/* 40 */       a(var0, var1, var2, var4.a(), var11, var7, var10, var4.c(), var9);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public int a(Random var0, int var1, WorldGenFeatureTreeConfiguration var2) {
/* 46 */     return this.b;
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean a(Random var0, int var1, int var2, int var3, int var4, boolean var5) {
/* 51 */     return (var1 == var4 && var3 == var4 && (var0.nextInt(2) == 0 || var2 == 0));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenFoilagePlacerBlob.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
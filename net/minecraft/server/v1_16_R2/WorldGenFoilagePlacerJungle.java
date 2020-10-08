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
/*    */ public class WorldGenFoilagePlacerJungle extends WorldGenFoilagePlacer {
/*    */   public static final Codec<WorldGenFoilagePlacerJungle> a;
/*    */   protected final int b;
/*    */   
/*    */   static {
/* 16 */     a = RecordCodecBuilder.create(var0 -> b(var0).and((App)Codec.intRange(0, 16).fieldOf("height").forGetter(())).apply((Applicative)var0, WorldGenFoilagePlacerJungle::new));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public WorldGenFoilagePlacerJungle(IntSpread var0, IntSpread var1, int var2) {
/* 23 */     super(var0, var1);
/* 24 */     this.b = var2;
/*    */   }
/*    */ 
/*    */   
/*    */   protected WorldGenFoilagePlacers<?> a() {
/* 29 */     return WorldGenFoilagePlacers.g;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected void a(VirtualLevelWritable var0, Random var1, WorldGenFeatureTreeConfiguration var2, int var3, WorldGenFoilagePlacer.b var4, int var5, int var6, Set<BlockPosition> var7, int var8, StructureBoundingBox var9) {
/* 35 */     int var10 = var4.c() ? var5 : (1 + var1.nextInt(2));
/*    */     
/* 37 */     for (int var11 = var8; var11 >= var8 - var10; var11--) {
/* 38 */       int var12 = var6 + var4.b() + 1 - var11;
/* 39 */       a(var0, var1, var2, var4.a(), var12, var7, var11, var4.c(), var9);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public int a(Random var0, int var1, WorldGenFeatureTreeConfiguration var2) {
/* 45 */     return this.b;
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean a(Random var0, int var1, int var2, int var3, int var4, boolean var5) {
/* 50 */     if (var1 + var3 >= 7) {
/* 51 */       return true;
/*    */     }
/* 53 */     return (var1 * var1 + var3 * var3 > var4 * var4);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenFoilagePlacerJungle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
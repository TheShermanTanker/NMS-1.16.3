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
/*    */ public class WorldGenFoilagePlacerAcacia extends WorldGenFoilagePlacer {
/*    */   public static final Codec<WorldGenFoilagePlacerAcacia> a;
/*    */   
/*    */   static {
/* 15 */     a = RecordCodecBuilder.create(var0 -> b(var0).apply((Applicative)var0, WorldGenFoilagePlacerAcacia::new));
/*    */   }
/*    */   public WorldGenFoilagePlacerAcacia(IntSpread var0, IntSpread var1) {
/* 18 */     super(var0, var1);
/*    */   }
/*    */ 
/*    */   
/*    */   protected WorldGenFoilagePlacers<?> a() {
/* 23 */     return WorldGenFoilagePlacers.d;
/*    */   }
/*    */ 
/*    */   
/*    */   protected void a(VirtualLevelWritable var0, Random var1, WorldGenFeatureTreeConfiguration var2, int var3, WorldGenFoilagePlacer.b var4, int var5, int var6, Set<BlockPosition> var7, int var8, StructureBoundingBox var9) {
/* 28 */     boolean var10 = var4.c();
/* 29 */     BlockPosition var11 = var4.a().up(var8);
/*    */     
/* 31 */     a(var0, var1, var2, var11, var6 + var4.b(), var7, -1 - var5, var10, var9);
/* 32 */     a(var0, var1, var2, var11, var6 - 1, var7, -var5, var10, var9);
/* 33 */     a(var0, var1, var2, var11, var6 + var4.b() - 1, var7, 0, var10, var9);
/*    */   }
/*    */ 
/*    */   
/*    */   public int a(Random var0, int var1, WorldGenFeatureTreeConfiguration var2) {
/* 38 */     return 0;
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean a(Random var0, int var1, int var2, int var3, int var4, boolean var5) {
/* 43 */     if (var2 == 0)
/*    */     {
/* 45 */       return ((var1 > 1 || var3 > 1) && var1 != 0 && var3 != 0);
/*    */     }
/* 47 */     return (var1 == var4 && var3 == var4 && var4 > 0);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenFoilagePlacerAcacia.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
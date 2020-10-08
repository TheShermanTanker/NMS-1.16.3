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
/*    */ public class WorldGenFoilagePlacerPine extends WorldGenFoilagePlacer {
/*    */   public static final Codec<WorldGenFoilagePlacerPine> a;
/*    */   
/*    */   static {
/* 15 */     a = RecordCodecBuilder.create(var0 -> b(var0).and((App)IntSpread.a(0, 16, 8).fieldOf("height").forGetter(())).apply((Applicative)var0, WorldGenFoilagePlacerPine::new));
/*    */   }
/*    */ 
/*    */   
/*    */   private final IntSpread b;
/*    */   
/*    */   public WorldGenFoilagePlacerPine(IntSpread var0, IntSpread var1, IntSpread var2) {
/* 22 */     super(var0, var1);
/* 23 */     this.b = var2;
/*    */   }
/*    */ 
/*    */   
/*    */   protected WorldGenFoilagePlacers<?> a() {
/* 28 */     return WorldGenFoilagePlacers.c;
/*    */   }
/*    */ 
/*    */   
/*    */   protected void a(VirtualLevelWritable var0, Random var1, WorldGenFeatureTreeConfiguration var2, int var3, WorldGenFoilagePlacer.b var4, int var5, int var6, Set<BlockPosition> var7, int var8, StructureBoundingBox var9) {
/* 33 */     int var10 = 0;
/*    */     
/* 35 */     for (int var11 = var8; var11 >= var8 - var5; var11--) {
/* 36 */       a(var0, var1, var2, var4.a(), var10, var7, var11, var4.c(), var9);
/*    */       
/* 38 */       if (var10 >= 1 && var11 == var8 - var5 + 1) {
/* 39 */         var10--;
/* 40 */       } else if (var10 < var6 + var4.b()) {
/* 41 */         var10++;
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public int a(Random var0, int var1) {
/* 48 */     return super.a(var0, var1) + var0.nextInt(var1 + 1);
/*    */   }
/*    */ 
/*    */   
/*    */   public int a(Random var0, int var1, WorldGenFeatureTreeConfiguration var2) {
/* 53 */     return this.b.a(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean a(Random var0, int var1, int var2, int var3, int var4, boolean var5) {
/* 58 */     return (var1 == var4 && var3 == var4 && var4 > 0);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenFoilagePlacerPine.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
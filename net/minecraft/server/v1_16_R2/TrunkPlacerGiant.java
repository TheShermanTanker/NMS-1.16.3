/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.ImmutableList;
/*    */ import com.mojang.datafixers.kinds.App;
/*    */ import com.mojang.datafixers.kinds.Applicative;
/*    */ import com.mojang.datafixers.util.Function3;
/*    */ import com.mojang.serialization.Codec;
/*    */ import com.mojang.serialization.codecs.RecordCodecBuilder;
/*    */ import java.util.List;
/*    */ import java.util.Random;
/*    */ import java.util.Set;
/*    */ 
/*    */ public class TrunkPlacerGiant extends TrunkPlacer {
/*    */   public static final Codec<TrunkPlacerGiant> a;
/*    */   
/*    */   static {
/* 17 */     a = RecordCodecBuilder.create(var0 -> a(var0).apply((Applicative)var0, TrunkPlacerGiant::new));
/*    */   }
/*    */   public TrunkPlacerGiant(int var0, int var1, int var2) {
/* 20 */     super(var0, var1, var2);
/*    */   }
/*    */ 
/*    */   
/*    */   protected TrunkPlacers<?> a() {
/* 25 */     return TrunkPlacers.c;
/*    */   }
/*    */ 
/*    */   
/*    */   public List<WorldGenFoilagePlacer.b> a(VirtualLevelWritable var0, Random var1, int var2, BlockPosition var3, Set<BlockPosition> var4, StructureBoundingBox var5, WorldGenFeatureTreeConfiguration var6) {
/* 30 */     BlockPosition var7 = var3.down();
/* 31 */     a(var0, var7);
/* 32 */     a(var0, var7.east());
/* 33 */     a(var0, var7.south());
/* 34 */     a(var0, var7.south().east());
/*    */     
/* 36 */     BlockPosition.MutableBlockPosition var8 = new BlockPosition.MutableBlockPosition();
/*    */     
/* 38 */     for (int var9 = 0; var9 < var2; var9++) {
/* 39 */       a(var0, var1, var8, var4, var5, var6, var3, 0, var9, 0);
/*    */       
/* 41 */       if (var9 < var2 - 1) {
/* 42 */         a(var0, var1, var8, var4, var5, var6, var3, 1, var9, 0);
/*    */         
/* 44 */         a(var0, var1, var8, var4, var5, var6, var3, 1, var9, 1);
/*    */         
/* 46 */         a(var0, var1, var8, var4, var5, var6, var3, 0, var9, 1);
/*    */       } 
/*    */     } 
/*    */     
/* 50 */     return (List<WorldGenFoilagePlacer.b>)ImmutableList.of(new WorldGenFoilagePlacer.b(var3.up(var2), 0, true));
/*    */   }
/*    */   
/*    */   private static void a(VirtualLevelWritable var0, Random var1, BlockPosition.MutableBlockPosition var2, Set<BlockPosition> var3, StructureBoundingBox var4, WorldGenFeatureTreeConfiguration var5, BlockPosition var6, int var7, int var8, int var9) {
/* 54 */     var2.a(var6, var7, var8, var9);
/* 55 */     a(var0, var1, var2, var3, var4, var5);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\TrunkPlacerGiant.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
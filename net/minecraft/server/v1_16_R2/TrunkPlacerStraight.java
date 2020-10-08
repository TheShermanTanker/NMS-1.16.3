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
/*    */ public class TrunkPlacerStraight extends TrunkPlacer {
/*    */   public static final Codec<TrunkPlacerStraight> a;
/*    */   
/*    */   static {
/* 17 */     a = RecordCodecBuilder.create(var0 -> a(var0).apply((Applicative)var0, TrunkPlacerStraight::new));
/*    */   }
/*    */   public TrunkPlacerStraight(int var0, int var1, int var2) {
/* 20 */     super(var0, var1, var2);
/*    */   }
/*    */ 
/*    */   
/*    */   protected TrunkPlacers<?> a() {
/* 25 */     return TrunkPlacers.a;
/*    */   }
/*    */ 
/*    */   
/*    */   public List<WorldGenFoilagePlacer.b> a(VirtualLevelWritable var0, Random var1, int var2, BlockPosition var3, Set<BlockPosition> var4, StructureBoundingBox var5, WorldGenFeatureTreeConfiguration var6) {
/* 30 */     a(var0, var3.down());
/*    */     
/* 32 */     for (int var7 = 0; var7 < var2; var7++) {
/* 33 */       a(var0, var1, var3.up(var7), var4, var5, var6);
/*    */     }
/* 35 */     return (List<WorldGenFoilagePlacer.b>)ImmutableList.of(new WorldGenFoilagePlacer.b(var3.up(var2), 0, false));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\TrunkPlacerStraight.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
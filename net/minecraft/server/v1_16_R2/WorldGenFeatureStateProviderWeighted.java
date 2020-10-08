/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.serialization.Codec;
/*    */ import com.mojang.serialization.DataResult;
/*    */ import java.util.Random;
/*    */ 
/*    */ public class WorldGenFeatureStateProviderWeighted extends WorldGenFeatureStateProvider {
/*    */   public static final Codec<WorldGenFeatureStateProviderWeighted> b;
/*    */   private final WeightedList<IBlockData> c;
/*    */   
/*    */   static {
/* 12 */     b = WeightedList.<U>a((Codec)IBlockData.b).comapFlatMap(WorldGenFeatureStateProviderWeighted::a, var0 -> var0.c).fieldOf("entries").codec();
/*    */   }
/*    */   
/*    */   private static DataResult<WorldGenFeatureStateProviderWeighted> a(WeightedList<IBlockData> var0) {
/* 16 */     if (var0.b()) {
/* 17 */       return DataResult.error("WeightedStateProvider with no states");
/*    */     }
/* 19 */     return DataResult.success(new WorldGenFeatureStateProviderWeighted(var0));
/*    */   }
/*    */   
/*    */   private WorldGenFeatureStateProviderWeighted(WeightedList<IBlockData> var0) {
/* 23 */     this.c = var0;
/*    */   }
/*    */ 
/*    */   
/*    */   protected WorldGenFeatureStateProviders<?> a() {
/* 28 */     return WorldGenFeatureStateProviders.b;
/*    */   }
/*    */   
/*    */   public WorldGenFeatureStateProviderWeighted() {
/* 32 */     this(new WeightedList<>());
/*    */   }
/*    */   
/*    */   public WorldGenFeatureStateProviderWeighted a(IBlockData var0, int var1) {
/* 36 */     this.c.a(var0, var1);
/* 37 */     return this;
/*    */   }
/*    */ 
/*    */   
/*    */   public IBlockData a(Random var0, BlockPosition var1) {
/* 42 */     return this.c.b(var0);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenFeatureStateProviderWeighted.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
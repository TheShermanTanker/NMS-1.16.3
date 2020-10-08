/*    */ package net.minecraft.server.v1_16_R2;
/*    */ import com.mojang.serialization.Codec;
/*    */ import java.util.Random;
/*    */ import java.util.function.Function;
/*    */ 
/*    */ public class WorldGenFeatureStateProviderSimpl extends WorldGenFeatureStateProvider {
/*    */   public static final Codec<WorldGenFeatureStateProviderSimpl> b;
/*    */   
/*    */   static {
/* 10 */     b = IBlockData.b.fieldOf("state").xmap(WorldGenFeatureStateProviderSimpl::new, var0 -> var0.c).codec();
/*    */   }
/*    */   private final IBlockData c;
/*    */   
/*    */   public WorldGenFeatureStateProviderSimpl(IBlockData var0) {
/* 15 */     this.c = var0;
/*    */   }
/*    */ 
/*    */   
/*    */   protected WorldGenFeatureStateProviders<?> a() {
/* 20 */     return WorldGenFeatureStateProviders.a;
/*    */   }
/*    */ 
/*    */   
/*    */   public IBlockData a(Random var0, BlockPosition var1) {
/* 25 */     return this.c;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenFeatureStateProviderSimpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
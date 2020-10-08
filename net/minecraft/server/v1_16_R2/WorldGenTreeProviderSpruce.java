/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.Random;
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WorldGenTreeProviderSpruce
/*    */   extends WorldGenMegaTreeProvider
/*    */ {
/*    */   @Nullable
/*    */   protected WorldGenFeatureConfigured<WorldGenFeatureTreeConfiguration, ?> a(Random var0, boolean var1) {
/* 14 */     return BiomeDecoratorGroups.SPRUCE;
/*    */   }
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   protected WorldGenFeatureConfigured<WorldGenFeatureTreeConfiguration, ?> a(Random var0) {
/* 20 */     return var0.nextBoolean() ? BiomeDecoratorGroups.MEGA_SPRUCE : BiomeDecoratorGroups.MEGA_PINE;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenTreeProviderSpruce.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
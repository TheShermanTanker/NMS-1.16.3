/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.Random;
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WorldGenMegaTreeProviderJungle
/*    */   extends WorldGenMegaTreeProvider
/*    */ {
/*    */   @Nullable
/*    */   protected WorldGenFeatureConfigured<WorldGenFeatureTreeConfiguration, ?> a(Random var0, boolean var1) {
/* 14 */     return BiomeDecoratorGroups.JUNGLE_TREE_NO_VINE;
/*    */   }
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   protected WorldGenFeatureConfigured<WorldGenFeatureTreeConfiguration, ?> a(Random var0) {
/* 20 */     return BiomeDecoratorGroups.MEGA_JUNGLE_TREE;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenMegaTreeProviderJungle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
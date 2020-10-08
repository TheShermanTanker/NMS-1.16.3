/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.Random;
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WorldGenTreeProviderBirch
/*    */   extends WorldGenTreeProvider
/*    */ {
/*    */   @Nullable
/*    */   protected WorldGenFeatureConfigured<WorldGenFeatureTreeConfiguration, ?> a(Random var0, boolean var1) {
/* 14 */     return var1 ? BiomeDecoratorGroups.BIRCH_BEES_005 : BiomeDecoratorGroups.BIRCH;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenTreeProviderBirch.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
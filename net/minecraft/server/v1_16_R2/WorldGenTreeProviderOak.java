/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.Random;
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WorldGenTreeProviderOak
/*    */   extends WorldGenTreeProvider
/*    */ {
/*    */   @Nullable
/*    */   protected WorldGenFeatureConfigured<WorldGenFeatureTreeConfiguration, ?> a(Random var0, boolean var1) {
/* 14 */     if (var0.nextInt(10) == 0) {
/* 15 */       return var1 ? BiomeDecoratorGroups.FANCY_OAK_BEES_005 : BiomeDecoratorGroups.FANCY_OAK;
/*    */     }
/* 17 */     return var1 ? BiomeDecoratorGroups.OAK_BEES_005 : BiomeDecoratorGroups.OAK;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenTreeProviderOak.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
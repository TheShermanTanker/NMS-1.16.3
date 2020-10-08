/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.Random;
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WorldGenMegaTreeProviderDarkOak
/*    */   extends WorldGenMegaTreeProvider
/*    */ {
/*    */   @Nullable
/*    */   protected WorldGenFeatureConfigured<WorldGenFeatureTreeConfiguration, ?> a(Random var0, boolean var1) {
/* 14 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   protected WorldGenFeatureConfigured<WorldGenFeatureTreeConfiguration, ?> a(Random var0) {
/* 20 */     return BiomeDecoratorGroups.DARK_OAK;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenMegaTreeProviderDarkOak.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
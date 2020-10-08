/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.stream.Stream;
/*    */ 
/*    */ 
/*    */ public interface WorldGenFeatureConfiguration
/*    */ {
/*  8 */   public static final WorldGenFeatureEmptyConfiguration k = WorldGenFeatureEmptyConfiguration.b;
/*    */   
/*    */   default Stream<WorldGenFeatureConfigured<?, ?>> an_() {
/* 11 */     return Stream.empty();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenFeatureConfiguration.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
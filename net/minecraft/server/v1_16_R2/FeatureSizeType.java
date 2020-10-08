/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.serialization.Codec;
/*    */ 
/*    */ public class FeatureSizeType<P extends FeatureSize>
/*    */ {
/*  7 */   public static final FeatureSizeType<FeatureSizeTwoLayers> a = a("two_layers_feature_size", FeatureSizeTwoLayers.c);
/*  8 */   public static final FeatureSizeType<FeatureSizeThreeLayers> b = a("three_layers_feature_size", FeatureSizeThreeLayers.c);
/*    */   
/*    */   private static <P extends FeatureSize> FeatureSizeType<P> a(String var0, Codec<P> var1) {
/* 11 */     return (FeatureSizeType<P>)IRegistry.<FeatureSizeType<?>>a(IRegistry.FEATURE_SIZE_TYPE, var0, new FeatureSizeType(var1));
/*    */   }
/*    */   
/*    */   private final Codec<P> c;
/*    */   
/*    */   private FeatureSizeType(Codec<P> var0) {
/* 17 */     this.c = var0;
/*    */   }
/*    */   
/*    */   public Codec<P> a() {
/* 21 */     return this.c;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\FeatureSizeType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
/*    */ package net.minecraft.server.v1_16_R2;
/*    */ import com.mojang.datafixers.kinds.App;
/*    */ import com.mojang.serialization.Codec;
/*    */ 
/*    */ public class WorldGenFeatureChanceDecoratorRangeConfiguration implements WorldGenFeatureDecoratorConfiguration {
/*    */   static {
/*  7 */     a = RecordCodecBuilder.create(var0 -> var0.group((App)Codec.INT.fieldOf("bottom_offset").orElse(Integer.valueOf(0)).forGetter(()), (App)Codec.INT.fieldOf("top_offset").orElse(Integer.valueOf(0)).forGetter(()), (App)Codec.INT.fieldOf("maximum").orElse(Integer.valueOf(0)).forGetter(())).apply((Applicative)var0, WorldGenFeatureChanceDecoratorRangeConfiguration::new));
/*    */   }
/*    */ 
/*    */   
/*    */   public static final Codec<WorldGenFeatureChanceDecoratorRangeConfiguration> a;
/*    */   
/*    */   public final int c;
/*    */   public final int d;
/*    */   public final int e;
/*    */   
/*    */   public WorldGenFeatureChanceDecoratorRangeConfiguration(int var0, int var1, int var2) {
/* 18 */     this.c = var0;
/* 19 */     this.d = var1;
/* 20 */     this.e = var2;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenFeatureChanceDecoratorRangeConfiguration.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
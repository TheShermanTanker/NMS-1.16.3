/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.serialization.Codec;
/*    */ import java.util.function.Function;
/*    */ 
/*    */ public class WorldGenDecoratorFrequencyConfiguration implements WorldGenFeatureDecoratorConfiguration, WorldGenFeatureConfiguration {
/*  7 */   public static final Codec<WorldGenDecoratorFrequencyConfiguration> a = IntSpread.a(-10, 128, 128).fieldOf("count")
/*  8 */     .xmap(WorldGenDecoratorFrequencyConfiguration::new, WorldGenDecoratorFrequencyConfiguration::a).codec();
/*    */   
/*    */   private final IntSpread c;
/*    */   
/*    */   public WorldGenDecoratorFrequencyConfiguration(int var0) {
/* 13 */     this.c = IntSpread.a(var0);
/*    */   }
/*    */   
/*    */   public WorldGenDecoratorFrequencyConfiguration(IntSpread var0) {
/* 17 */     this.c = var0;
/*    */   }
/*    */   
/*    */   public IntSpread a() {
/* 21 */     return this.c;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenDecoratorFrequencyConfiguration.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
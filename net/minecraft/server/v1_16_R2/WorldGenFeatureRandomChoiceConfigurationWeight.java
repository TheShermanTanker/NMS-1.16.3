/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.datafixers.kinds.App;
/*    */ import com.mojang.datafixers.kinds.Applicative;
/*    */ import com.mojang.serialization.Codec;
/*    */ import com.mojang.serialization.codecs.RecordCodecBuilder;
/*    */ import java.util.Random;
/*    */ import java.util.function.BiFunction;
/*    */ import java.util.function.Supplier;
/*    */ 
/*    */ public class WorldGenFeatureRandomChoiceConfigurationWeight {
/*    */   static {
/* 13 */     a = RecordCodecBuilder.create(var0 -> var0.group((App)WorldGenFeatureConfigured.b.fieldOf("feature").forGetter(()), (App)Codec.floatRange(0.0F, 1.0F).fieldOf("chance").forGetter(())).apply((Applicative)var0, WorldGenFeatureRandomChoiceConfigurationWeight::new));
/*    */   }
/*    */ 
/*    */   
/*    */   public static final Codec<WorldGenFeatureRandomChoiceConfigurationWeight> a;
/*    */   public final Supplier<WorldGenFeatureConfigured<?, ?>> b;
/*    */   public final float c;
/*    */   
/*    */   public WorldGenFeatureRandomChoiceConfigurationWeight(WorldGenFeatureConfigured<?, ?> var0, float var1) {
/* 22 */     this(() -> var0, var1);
/*    */   }
/*    */   
/*    */   private WorldGenFeatureRandomChoiceConfigurationWeight(Supplier<WorldGenFeatureConfigured<?, ?>> var0, float var1) {
/* 26 */     this.b = var0;
/* 27 */     this.c = var1;
/*    */   }
/*    */   
/*    */   public boolean a(GeneratorAccessSeed var0, ChunkGenerator var1, Random var2, BlockPosition var3) {
/* 31 */     return ((WorldGenFeatureConfigured)this.b.get()).a(var0, var1, var2, var3);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenFeatureRandomChoiceConfigurationWeight.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
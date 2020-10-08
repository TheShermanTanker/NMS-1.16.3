/*    */ package net.minecraft.server.v1_16_R2;
/*    */ import com.mojang.datafixers.kinds.App;
/*    */ import com.mojang.datafixers.kinds.Applicative;
/*    */ import com.mojang.serialization.codecs.RecordCodecBuilder;
/*    */ import java.util.function.BiFunction;
/*    */ import java.util.function.Supplier;
/*    */ import java.util.stream.Stream;
/*    */ 
/*    */ public class WorldGenFeatureChoiceConfiguration implements WorldGenFeatureConfiguration {
/*    */   static {
/* 11 */     a = RecordCodecBuilder.create(var0 -> var0.group((App)WorldGenFeatureConfigured.b.fieldOf("feature_true").forGetter(()), (App)WorldGenFeatureConfigured.b.fieldOf("feature_false").forGetter(())).apply((Applicative)var0, WorldGenFeatureChoiceConfiguration::new));
/*    */   }
/*    */ 
/*    */   
/*    */   public static final Codec<WorldGenFeatureChoiceConfiguration> a;
/*    */   public final Supplier<WorldGenFeatureConfigured<?, ?>> b;
/*    */   public final Supplier<WorldGenFeatureConfigured<?, ?>> c;
/*    */   
/*    */   public WorldGenFeatureChoiceConfiguration(Supplier<WorldGenFeatureConfigured<?, ?>> var0, Supplier<WorldGenFeatureConfigured<?, ?>> var1) {
/* 20 */     this.b = var0;
/* 21 */     this.c = var1;
/*    */   }
/*    */ 
/*    */   
/*    */   public Stream<WorldGenFeatureConfigured<?, ?>> an_() {
/* 26 */     return Stream.concat(((WorldGenFeatureConfigured)this.b.get()).d(), ((WorldGenFeatureConfigured)this.c.get()).d());
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenFeatureChoiceConfiguration.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.datafixers.kinds.App;
/*    */ import com.mojang.serialization.Codec;
/*    */ import com.mojang.serialization.codecs.RecordCodecBuilder;
/*    */ import java.util.List;
/*    */ import java.util.function.BiFunction;
/*    */ import java.util.function.Supplier;
/*    */ import java.util.stream.Stream;
/*    */ 
/*    */ public class WorldGenFeatureRandomChoiceConfiguration implements WorldGenFeatureConfiguration {
/*    */   static {
/* 13 */     a = RecordCodecBuilder.create(var0 -> var0.apply2(WorldGenFeatureRandomChoiceConfiguration::new, (App)WorldGenFeatureRandomChoiceConfigurationWeight.a.listOf().fieldOf("features").forGetter(()), (App)WorldGenFeatureConfigured.b.fieldOf("default").forGetter(())));
/*    */   }
/*    */ 
/*    */   
/*    */   public static final Codec<WorldGenFeatureRandomChoiceConfiguration> a;
/*    */   
/*    */   public final List<WorldGenFeatureRandomChoiceConfigurationWeight> b;
/*    */   public final Supplier<WorldGenFeatureConfigured<?, ?>> c;
/*    */   
/*    */   public WorldGenFeatureRandomChoiceConfiguration(List<WorldGenFeatureRandomChoiceConfigurationWeight> var0, WorldGenFeatureConfigured<?, ?> var1) {
/* 23 */     this(var0, () -> var0);
/*    */   }
/*    */   
/*    */   private WorldGenFeatureRandomChoiceConfiguration(List<WorldGenFeatureRandomChoiceConfigurationWeight> var0, Supplier<WorldGenFeatureConfigured<?, ?>> var1) {
/* 27 */     this.b = var0;
/* 28 */     this.c = var1;
/*    */   }
/*    */ 
/*    */   
/*    */   public Stream<WorldGenFeatureConfigured<?, ?>> an_() {
/* 33 */     return Stream.concat(this.b.stream().flatMap(var0 -> ((WorldGenFeatureConfigured)var0.b.get()).d()), ((WorldGenFeatureConfigured)this.c.get()).d());
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenFeatureRandomChoiceConfiguration.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
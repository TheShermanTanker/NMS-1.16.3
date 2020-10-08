/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.datafixers.kinds.App;
/*    */ import com.mojang.datafixers.kinds.Applicative;
/*    */ import com.mojang.serialization.Codec;
/*    */ import com.mojang.serialization.codecs.RecordCodecBuilder;
/*    */ import java.util.function.BiFunction;
/*    */ import java.util.function.Supplier;
/*    */ import java.util.stream.Stream;
/*    */ 
/*    */ public class WorldGenFeatureCompositeConfiguration implements WorldGenFeatureConfiguration {
/*    */   static {
/* 13 */     a = RecordCodecBuilder.create(var0 -> var0.group((App)WorldGenFeatureConfigured.b.fieldOf("feature").forGetter(()), (App)WorldGenDecoratorConfigured.a.fieldOf("decorator").forGetter(())).apply((Applicative)var0, WorldGenFeatureCompositeConfiguration::new));
/*    */   }
/*    */ 
/*    */   
/*    */   public static final Codec<WorldGenFeatureCompositeConfiguration> a;
/*    */   public final Supplier<WorldGenFeatureConfigured<?, ?>> b;
/*    */   public final WorldGenDecoratorConfigured<?> c;
/*    */   
/*    */   public WorldGenFeatureCompositeConfiguration(Supplier<WorldGenFeatureConfigured<?, ?>> var0, WorldGenDecoratorConfigured<?> var1) {
/* 22 */     this.b = var0;
/* 23 */     this.c = var1;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 28 */     return String.format("< %s [%s | %s] >", new Object[] { getClass().getSimpleName(), IRegistry.FEATURE.getKey((WorldGenerator<?>)((WorldGenFeatureConfigured)this.b.get()).b()), this.c });
/*    */   }
/*    */ 
/*    */   
/*    */   public Stream<WorldGenFeatureConfigured<?, ?>> an_() {
/* 33 */     return ((WorldGenFeatureConfigured)this.b.get()).d();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenFeatureCompositeConfiguration.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
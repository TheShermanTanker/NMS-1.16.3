/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.serialization.Codec;
/*    */ import java.util.List;
/*    */ import java.util.Random;
/*    */ import java.util.function.Supplier;
/*    */ import java.util.stream.Stream;
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WorldGenFeatureConfigured<FC extends WorldGenFeatureConfiguration, F extends WorldGenerator<FC>>
/*    */   implements IDecoratable<WorldGenFeatureConfigured<?, ?>>
/*    */ {
/*    */   public static final Codec<WorldGenFeatureConfigured<?, ?>> a;
/*    */   
/*    */   static {
/* 22 */     a = IRegistry.FEATURE.dispatch(var0 -> var0.e, WorldGenerator::a);
/*    */   }
/* 24 */   public static final Codec<Supplier<WorldGenFeatureConfigured<?, ?>>> b = RegistryFileCodec.a(IRegistry.au, a);
/* 25 */   public static final Codec<List<Supplier<WorldGenFeatureConfigured<?, ?>>>> c = RegistryFileCodec.b(IRegistry.au, a);
/*    */   
/* 27 */   public static final Logger LOGGER = LogManager.getLogger();
/*    */   
/*    */   public final F e;
/*    */   public final FC f;
/*    */   
/*    */   public WorldGenFeatureConfigured(F var0, FC var1) {
/* 33 */     this.e = var0;
/* 34 */     this.f = var1;
/*    */   }
/*    */   
/*    */   public F b() {
/* 38 */     return this.e;
/*    */   }
/*    */   
/*    */   public FC c() {
/* 42 */     return this.f;
/*    */   }
/*    */ 
/*    */   
/*    */   public WorldGenFeatureConfigured<?, ?> a(WorldGenDecoratorConfigured<?> var0) {
/* 47 */     return WorldGenerator.DECORATED.b(new WorldGenFeatureCompositeConfiguration(() -> this, var0));
/*    */   }
/*    */   
/*    */   public WorldGenFeatureRandomChoiceConfigurationWeight a(float var0) {
/* 51 */     return new WorldGenFeatureRandomChoiceConfigurationWeight(this, var0);
/*    */   }
/*    */   
/*    */   public boolean a(GeneratorAccessSeed var0, ChunkGenerator var1, Random var2, BlockPosition var3) {
/* 55 */     return this.e.generate(var0, var1, var2, var3, this.f);
/*    */   }
/*    */   
/*    */   public Stream<WorldGenFeatureConfigured<?, ?>> d() {
/* 59 */     return Stream.concat(Stream.of(this), this.f.an_());
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenFeatureConfigured.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
/*    */ package net.minecraft.server.v1_16_R2;
/*    */ import java.util.List;
/*    */ import java.util.function.Function;
/*    */ import java.util.function.Supplier;
/*    */ import java.util.stream.Stream;
/*    */ 
/*    */ public class WorldGenFeatureRandom2 implements WorldGenFeatureConfiguration {
/*    */   public static final Codec<WorldGenFeatureRandom2> a;
/*    */   
/*    */   static {
/* 11 */     a = WorldGenFeatureConfigured.c.fieldOf("features").xmap(WorldGenFeatureRandom2::new, var0 -> var0.b).codec();
/*    */   }
/*    */   public final List<Supplier<WorldGenFeatureConfigured<?, ?>>> b;
/*    */   
/*    */   public WorldGenFeatureRandom2(List<Supplier<WorldGenFeatureConfigured<?, ?>>> var0) {
/* 16 */     this.b = var0;
/*    */   }
/*    */ 
/*    */   
/*    */   public Stream<WorldGenFeatureConfigured<?, ?>> an_() {
/* 21 */     return this.b.stream().flatMap(var0 -> ((WorldGenFeatureConfigured)var0.get()).d());
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenFeatureRandom2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
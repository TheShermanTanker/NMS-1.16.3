/*    */ package net.minecraft.server.v1_16_R2;
/*    */ import com.mojang.datafixers.kinds.App;
/*    */ import com.mojang.datafixers.kinds.Applicative;
/*    */ import com.mojang.serialization.codecs.RecordCodecBuilder;
/*    */ 
/*    */ public class WorldGenDecoratorDecpratedConfiguration implements WorldGenFeatureDecoratorConfiguration {
/*    */   static {
/*  8 */     a = RecordCodecBuilder.create(var0 -> var0.group((App)WorldGenDecoratorConfigured.a.fieldOf("outer").forGetter(WorldGenDecoratorDecpratedConfiguration::a), (App)WorldGenDecoratorConfigured.a.fieldOf("inner").forGetter(WorldGenDecoratorDecpratedConfiguration::b)).apply((Applicative)var0, WorldGenDecoratorDecpratedConfiguration::new));
/*    */   }
/*    */ 
/*    */   
/*    */   public static final Codec<WorldGenDecoratorDecpratedConfiguration> a;
/*    */   
/*    */   private final WorldGenDecoratorConfigured<?> c;
/*    */   private final WorldGenDecoratorConfigured<?> d;
/*    */   
/*    */   public WorldGenDecoratorDecpratedConfiguration(WorldGenDecoratorConfigured<?> var0, WorldGenDecoratorConfigured<?> var1) {
/* 18 */     this.c = var0;
/* 19 */     this.d = var1;
/*    */   }
/*    */   
/*    */   public WorldGenDecoratorConfigured<?> a() {
/* 23 */     return this.c;
/*    */   }
/*    */   
/*    */   public WorldGenDecoratorConfigured<?> b() {
/* 27 */     return this.d;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenDecoratorDecpratedConfiguration.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
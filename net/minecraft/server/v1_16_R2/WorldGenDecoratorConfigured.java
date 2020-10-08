/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.serialization.Codec;
/*    */ import java.util.Random;
/*    */ import java.util.stream.Stream;
/*    */ 
/*    */ public class WorldGenDecoratorConfigured<DC extends WorldGenFeatureDecoratorConfiguration> implements IDecoratable<WorldGenDecoratorConfigured<?>> {
/*    */   public static final Codec<WorldGenDecoratorConfigured<?>> a;
/*    */   private final WorldGenDecorator<DC> b;
/*    */   private final DC c;
/*    */   
/*    */   static {
/* 13 */     a = IRegistry.DECORATOR.dispatch("type", var0 -> var0.b, WorldGenDecorator::a);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public WorldGenDecoratorConfigured(WorldGenDecorator<DC> var0, DC var1) {
/* 19 */     this.b = var0;
/* 20 */     this.c = var1;
/*    */   }
/*    */   
/*    */   public Stream<BlockPosition> a(WorldGenDecoratorContext var0, Random var1, BlockPosition var2) {
/* 24 */     return this.b.a(var0, var1, this.c, var2);
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 29 */     return String.format("[%s %s]", new Object[] { IRegistry.DECORATOR.getKey(this.b), this.c });
/*    */   }
/*    */ 
/*    */   
/*    */   public WorldGenDecoratorConfigured<?> a(WorldGenDecoratorConfigured<?> var0) {
/* 34 */     return new WorldGenDecoratorConfigured((WorldGenDecorator)WorldGenDecorator.B, (DC)new WorldGenDecoratorDecpratedConfiguration(var0, this));
/*    */   }
/*    */   
/*    */   public DC b() {
/* 38 */     return this.c;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenDecoratorConfigured.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
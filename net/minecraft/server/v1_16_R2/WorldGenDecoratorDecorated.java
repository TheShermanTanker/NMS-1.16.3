/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.serialization.Codec;
/*    */ import java.util.Random;
/*    */ import java.util.stream.Stream;
/*    */ 
/*    */ public class WorldGenDecoratorDecorated
/*    */   extends WorldGenDecorator<WorldGenDecoratorDecpratedConfiguration>
/*    */ {
/*    */   public WorldGenDecoratorDecorated(Codec<WorldGenDecoratorDecpratedConfiguration> var0) {
/* 11 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public Stream<BlockPosition> a(WorldGenDecoratorContext var0, Random var1, WorldGenDecoratorDecpratedConfiguration var2, BlockPosition var3) {
/* 16 */     return var2.a().a(var0, var1, var3).flatMap(var3 -> var0.b().a(var1, var2, var3));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenDecoratorDecorated.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
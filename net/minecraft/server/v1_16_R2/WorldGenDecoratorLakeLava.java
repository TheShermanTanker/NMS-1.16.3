/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.serialization.Codec;
/*    */ import java.util.Random;
/*    */ import java.util.stream.Stream;
/*    */ 
/*    */ public class WorldGenDecoratorLakeLava
/*    */   extends WorldGenDecorator<WorldGenDecoratorDungeonConfiguration>
/*    */ {
/*    */   public WorldGenDecoratorLakeLava(Codec<WorldGenDecoratorDungeonConfiguration> var0) {
/* 11 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public Stream<BlockPosition> a(WorldGenDecoratorContext var0, Random var1, WorldGenDecoratorDungeonConfiguration var2, BlockPosition var3) {
/* 16 */     if (var1.nextInt(var2.c / 10) == 0) {
/* 17 */       int var4 = var1.nextInt(16) + var3.getX();
/* 18 */       int var5 = var1.nextInt(16) + var3.getZ();
/* 19 */       int var6 = var1.nextInt(var1.nextInt(var0.a() - 8) + 8);
/* 20 */       if (var6 < var0.b() || var1.nextInt(var2.c / 8) == 0) {
/* 21 */         return Stream.of(new BlockPosition(var4, var6, var5));
/*    */       }
/*    */     } 
/*    */     
/* 25 */     return Stream.empty();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldGenDecoratorLakeLava.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
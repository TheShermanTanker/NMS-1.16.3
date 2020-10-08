/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public interface IDecoratable<R>
/*    */ {
/*    */   R a(WorldGenDecoratorConfigured<?> paramWorldGenDecoratorConfigured);
/*    */   
/*    */   default R a(int var0) {
/* 15 */     return a(WorldGenDecorator.b.b(new WorldGenDecoratorDungeonConfiguration(var0)));
/*    */   }
/*    */   
/*    */   default R a(IntSpread var0) {
/* 19 */     return a(WorldGenDecorator.c.b(new WorldGenDecoratorFrequencyConfiguration(var0)));
/*    */   }
/*    */   
/*    */   default R b(int var0) {
/* 23 */     return a(IntSpread.a(var0));
/*    */   }
/*    */   
/*    */   default R c(int var0) {
/* 27 */     return a(IntSpread.a(0, var0));
/*    */   }
/*    */   
/*    */   default R d(int var0) {
/* 31 */     return a(WorldGenDecorator.l.b(new WorldGenFeatureChanceDecoratorRangeConfiguration(0, 0, var0)));
/*    */   }
/*    */   
/*    */   default R a() {
/* 35 */     return a(WorldGenDecorator.g.b(WorldGenFeatureEmptyConfiguration2.c));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\IDecoratable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
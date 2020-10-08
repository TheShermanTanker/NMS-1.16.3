/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.function.Function;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TickListWorldGen<T>
/*    */   implements TickList<T>
/*    */ {
/*    */   private final Function<BlockPosition, TickList<T>> a;
/*    */   
/*    */   public TickListWorldGen(Function<BlockPosition, TickList<T>> var0) {
/* 13 */     this.a = var0;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(BlockPosition var0, T var1) {
/* 18 */     return ((TickList<T>)this.a.apply(var0)).a(var0, var1);
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(BlockPosition var0, T var1, int var2, TickListPriority var3) {
/* 23 */     ((TickList<T>)this.a.apply(var0)).a(var0, var1, var2, var3);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean b(BlockPosition var0, T var1) {
/* 28 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\TickListWorldGen.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
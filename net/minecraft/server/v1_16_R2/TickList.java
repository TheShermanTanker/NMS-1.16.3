/*   */ package net.minecraft.server.v1_16_R2;
/*   */ 
/*   */ 
/*   */ public interface TickList<T>
/*   */ {
/*   */   boolean a(BlockPosition paramBlockPosition, T paramT);
/*   */   
/*   */   default void a(BlockPosition var0, T var1, int var2) {
/* 9 */     a(var0, var1, var2, TickListPriority.NORMAL);
/*   */   }
/*   */   
/*   */   void a(BlockPosition paramBlockPosition, T paramT, int paramInt, TickListPriority paramTickListPriority);
/*   */   
/*   */   boolean b(BlockPosition paramBlockPosition, T paramT);
/*   */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\TickList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
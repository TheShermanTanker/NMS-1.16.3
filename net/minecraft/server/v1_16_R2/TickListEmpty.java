/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ public class TickListEmpty<T>
/*    */   implements TickList<T>
/*    */ {
/*  6 */   private static final TickListEmpty<Object> a = new TickListEmpty();
/*    */ 
/*    */   
/*    */   public static <T> TickListEmpty<T> b() {
/* 10 */     return (TickListEmpty)a;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(BlockPosition var0, T var1) {
/* 15 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void a(BlockPosition var0, T var1, int var2) {}
/*    */ 
/*    */ 
/*    */   
/*    */   public void a(BlockPosition var0, T var1, int var2, TickListPriority var3) {}
/*    */ 
/*    */   
/*    */   public boolean b(BlockPosition var0, T var1) {
/* 28 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\TickListEmpty.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
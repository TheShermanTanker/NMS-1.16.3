/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.function.Supplier;
/*    */ 
/*    */ public class LazyInitVar<T> {
/*    */   private Supplier<T> a;
/*    */   private T b;
/*    */   
/*    */   public LazyInitVar(Supplier<T> var0) {
/* 10 */     this.a = var0;
/*    */   }
/*    */   
/*    */   public T a() {
/* 14 */     Supplier<T> var0 = this.a;
/* 15 */     if (var0 != null) {
/* 16 */       this.b = var0.get();
/* 17 */       this.a = null;
/*    */     } 
/*    */     
/* 20 */     return this.b;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\LazyInitVar.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
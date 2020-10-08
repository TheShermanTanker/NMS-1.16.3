/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ public class ExceptionSuppressor<T extends Throwable> {
/*    */   @Nullable
/*    */   private T a;
/*    */   
/*    */   public void a(T var0) {
/* 10 */     if (this.a == null) {
/* 11 */       this.a = var0;
/*    */     } else {
/* 13 */       this.a.addSuppressed((Throwable)var0);
/*    */     } 
/*    */   }
/*    */   
/*    */   public void a() throws T {
/* 18 */     if (this.a != null)
/* 19 */       throw this.a; 
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ExceptionSuppressor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
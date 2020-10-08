/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ public class ReportedException extends RuntimeException {
/*    */   private final CrashReport a;
/*    */   
/*    */   public ReportedException(CrashReport var0) {
/*  7 */     this.a = var0;
/*    */   }
/*    */   
/*    */   public CrashReport a() {
/* 11 */     return this.a;
/*    */   }
/*    */ 
/*    */   
/*    */   public Throwable getCause() {
/* 16 */     return this.a.b();
/*    */   }
/*    */ 
/*    */   
/*    */   public String getMessage() {
/* 21 */     return this.a.a();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ReportedException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
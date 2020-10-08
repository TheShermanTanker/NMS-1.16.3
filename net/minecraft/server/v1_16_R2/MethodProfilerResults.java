/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.io.File;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public interface MethodProfilerResults
/*    */ {
/*    */   boolean a(File paramFile);
/*    */   
/*    */   long a();
/*    */   
/*    */   int b();
/*    */   
/*    */   long c();
/*    */   
/*    */   int d();
/*    */   
/*    */   default long g() {
/* 22 */     return c() - a();
/*    */   }
/*    */   
/*    */   default int f() {
/* 26 */     return d() - b();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   static String b(String var0) {
/* 32 */     return var0.replace('\036', '.');
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\MethodProfilerResults.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
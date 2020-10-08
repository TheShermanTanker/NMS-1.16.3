/*   */ package co.aikar.cleaner;
/*   */ 
/*   */ import sun.misc.Cleaner;
/*   */ 
/*   */ public class Java8CleanerImpl {
/*   */   public static void clean(Object object, Runnable run) {
/* 7 */     Cleaner.create(object, run);
/*   */   }
/*   */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\co\aikar\cleaner\Java8CleanerImpl.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
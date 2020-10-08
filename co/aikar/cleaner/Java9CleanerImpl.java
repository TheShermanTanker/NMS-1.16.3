/*   */ package co.aikar.cleaner;
/*   */ 
/*   */ import java.lang.ref.Cleaner;
/*   */ 
/*   */ public class Java9CleanerImpl {
/* 6 */   private static final Cleaner cleaner = Cleaner.create();
/*   */   public static void clean(Object object, Runnable run) {
/* 8 */     cleaner.register(object, run);
/*   */   }
/*   */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\co\aikar\cleaner\Java9CleanerImpl.class
 * Java compiler version: 9 (53.0)
 * JD-Core Version:       1.1.3
 */
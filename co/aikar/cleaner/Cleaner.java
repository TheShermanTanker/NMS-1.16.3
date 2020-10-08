/*    */ package co.aikar.cleaner;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Cleaner
/*    */ {
/*    */   static CleanerAPI create() {
/*    */     try {
/*  9 */       Class.forName("java.lang.ref.Cleaner");
/* 10 */       return Java9CleanerImpl::clean;
/* 11 */     } catch (ClassNotFoundException ignored) {
/*    */       try {
/* 13 */         Class.forName("sun.misc.Cleaner");
/* 14 */         return Java8CleanerImpl::clean;
/* 15 */       } catch (ClassNotFoundException e) {
/* 16 */         throw new RuntimeException("No cleaner method supported");
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/* 21 */   private static final CleanerAPI api = create();
/*    */   
/*    */   public static void register(Object obj, Runnable run) {
/* 24 */     api.register(obj, run);
/*    */   }
/*    */   
/*    */   public static interface CleanerAPI {
/*    */     void register(Object param1Object, Runnable param1Runnable);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\co\aikar\cleaner\Cleaner.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
/*   */ package org.sqlite;
/*   */ 
/*   */ public interface SQLiteUpdateListener
/*   */ {
/*   */   void onUpdate(Type paramType, String paramString1, String paramString2, long paramLong);
/*   */   
/*   */   public enum Type
/*   */   {
/* 9 */     INSERT, DELETE, UPDATE;
/*   */   }
/*   */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\sqlite\SQLiteUpdateListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
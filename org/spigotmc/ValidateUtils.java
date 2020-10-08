/*    */ package org.spigotmc;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ValidateUtils
/*    */ {
/*    */   public static String limit(String str, int limit) {
/*  8 */     if (str.length() > limit)
/*    */     {
/* 10 */       return str.substring(0, limit);
/*    */     }
/* 12 */     return str;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\spigotmc\ValidateUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
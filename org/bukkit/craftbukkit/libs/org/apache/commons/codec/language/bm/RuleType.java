/*    */ package org.bukkit.craftbukkit.libs.org.apache.commons.codec.language.bm;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public enum RuleType
/*    */ {
/* 29 */   APPROX("approx"),
/*    */   
/* 31 */   EXACT("exact"),
/*    */   
/* 33 */   RULES("rules");
/*    */   
/*    */   private final String name;
/*    */   
/*    */   RuleType(String name) {
/* 38 */     this.name = name;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String getName() {
/* 47 */     return this.name;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\libs\org\apache\commons\codec\language\bm\RuleType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
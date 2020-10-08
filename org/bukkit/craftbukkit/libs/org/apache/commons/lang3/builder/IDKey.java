/*    */ package org.bukkit.craftbukkit.libs.org.apache.commons.lang3.builder;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ final class IDKey
/*    */ {
/*    */   private final Object value;
/*    */   private final int id;
/*    */   
/*    */   public IDKey(Object _value) {
/* 39 */     this.id = System.identityHashCode(_value);
/*    */ 
/*    */ 
/*    */     
/* 43 */     this.value = _value;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 52 */     return this.id;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean equals(Object other) {
/* 62 */     if (!(other instanceof IDKey)) {
/* 63 */       return false;
/*    */     }
/* 65 */     IDKey idKey = (IDKey)other;
/* 66 */     if (this.id != idKey.id) {
/* 67 */       return false;
/*    */     }
/*    */     
/* 70 */     return (this.value == idKey.value);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\libs\org\apache\commons\lang3\builder\IDKey.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */
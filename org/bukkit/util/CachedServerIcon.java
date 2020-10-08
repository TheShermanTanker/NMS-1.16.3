/*    */ package org.bukkit.util;
/*    */ 
/*    */ import org.jetbrains.annotations.Nullable;
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
/*    */ public interface CachedServerIcon
/*    */ {
/*    */   @Nullable
/*    */   String getData();
/*    */   
/*    */   default boolean isEmpty() {
/* 23 */     return (getData() == null);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukki\\util\CachedServerIcon.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
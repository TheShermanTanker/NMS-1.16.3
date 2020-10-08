/*    */ package org.bukkit.plugin;
/*    */ 
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class PluginBase
/*    */   implements Plugin
/*    */ {
/*    */   public final int hashCode() {
/* 14 */     return getName().hashCode();
/*    */   }
/*    */ 
/*    */   
/*    */   public final boolean equals(Object obj) {
/* 19 */     if (this == obj) {
/* 20 */       return true;
/*    */     }
/* 22 */     if (obj == null) {
/* 23 */       return false;
/*    */     }
/* 25 */     if (!(obj instanceof Plugin)) {
/* 26 */       return false;
/*    */     }
/* 28 */     return getName().equals(((Plugin)obj).getName());
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public final String getName() {
/* 34 */     return getDescription().getName();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\plugin\PluginBase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
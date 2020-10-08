/*    */ package org.bukkit.craftbukkit.v1_16_R2.scheduler;
/*    */ 
/*    */ import org.bukkit.plugin.Plugin;
/*    */ 
/*    */ class CraftAsyncDebugger {
/*  6 */   private CraftAsyncDebugger next = null;
/*    */   private final int expiry;
/*    */   private final Plugin plugin;
/*    */   private final Class<?> clazz;
/*    */   
/*    */   CraftAsyncDebugger(int expiry, Plugin plugin, Class<?> clazz) {
/* 12 */     this.expiry = expiry;
/* 13 */     this.plugin = plugin;
/* 14 */     this.clazz = clazz;
/*    */   }
/*    */ 
/*    */   
/*    */   final CraftAsyncDebugger getNextHead(int time) {
/* 19 */     CraftAsyncDebugger current = this; CraftAsyncDebugger next;
/* 20 */     while (time > current.expiry && (next = current.next) != null) {
/* 21 */       current = next;
/*    */     }
/* 23 */     return current;
/*    */   }
/*    */   
/*    */   final CraftAsyncDebugger setNext(CraftAsyncDebugger next) {
/* 27 */     return this.next = next;
/*    */   }
/*    */   
/*    */   StringBuilder debugTo(StringBuilder string) {
/* 31 */     for (CraftAsyncDebugger next = this; next != null; next = next.next) {
/* 32 */       string.append(next.plugin.getDescription().getName()).append(':').append(next.clazz.getName()).append('@').append(next.expiry).append(',');
/*    */     }
/* 34 */     return string;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\scheduler\CraftAsyncDebugger.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
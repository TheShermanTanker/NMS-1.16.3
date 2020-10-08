/*    */ package org.bukkit.plugin.messaging;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ReservedChannelException
/*    */   extends RuntimeException
/*    */ {
/*    */   public ReservedChannelException() {
/* 10 */     this("Attempted to register for a reserved channel name.");
/*    */   }
/*    */   
/*    */   public ReservedChannelException(String name) {
/* 14 */     super("Attempted to register for a reserved channel name ('" + name + "')");
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\plugin\messaging\ReservedChannelException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
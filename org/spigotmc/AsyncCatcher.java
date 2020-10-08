/*    */ package org.spigotmc;
/*    */ 
/*    */ import com.tuinity.tuinity.util.TickThread;
/*    */ import org.bukkit.Bukkit;
/*    */ 
/*    */ 
/*    */ public class AsyncCatcher
/*    */ {
/*    */   public static boolean enabled = true;
/*    */   public static boolean shuttingDown = false;
/*    */   
/*    */   public static void catchOp(String reason) {
/* 13 */     if ((enabled || TickThread.STRICT_THREAD_CHECKS) && !Bukkit.isPrimaryThread())
/*    */     {
/* 15 */       throw new IllegalStateException("Asynchronous " + reason + "!");
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\spigotmc\AsyncCatcher.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
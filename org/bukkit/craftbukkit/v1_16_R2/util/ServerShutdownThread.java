/*    */ package org.bukkit.craftbukkit.v1_16_R2.util;
/*    */ 
/*    */ import net.minecraft.server.v1_16_R2.MinecraftServer;
/*    */ import org.spigotmc.AsyncCatcher;
/*    */ 
/*    */ public class ServerShutdownThread
/*    */   extends Thread {
/*    */   public ServerShutdownThread(MinecraftServer server) {
/*  9 */     this.server = server;
/*    */   }
/*    */   
/*    */   private final MinecraftServer server;
/*    */   
/*    */   public void run() {
/*    */     try {
/* 16 */       this.server.safeShutdown(false, false);
/* 17 */       for (int i = 1000; i > 0 && !this.server.hasStopped(); i -= 100) {
/* 18 */         Thread.sleep(100L);
/*    */       }
/* 20 */       if (this.server.hasStopped()) {
/* 21 */         for (; !this.server.hasFullyShutdown; Thread.sleep(1000L));
/*    */         
/*    */         return;
/*    */       } 
/* 25 */       AsyncCatcher.enabled = false;
/* 26 */       AsyncCatcher.shuttingDown = true;
/* 27 */       this.server.forceTicks = true;
/* 28 */       this.server.close();
/* 29 */       for (; !this.server.hasFullyShutdown; Thread.sleep(1000L));
/* 30 */     } catch (InterruptedException e) {
/* 31 */       e.printStackTrace();
/*    */     } finally {}
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R\\util\ServerShutdownThread.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
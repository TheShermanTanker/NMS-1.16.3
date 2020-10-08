/*     */ package org.spigotmc;
/*     */ 
/*     */ import com.google.common.collect.ImmutableList;
/*     */ import com.google.common.collect.UnmodifiableIterator;
/*     */ import java.io.File;
/*     */ import java.util.Locale;
/*     */ import net.minecraft.server.v1_16_R2.EntityPlayer;
/*     */ import net.minecraft.server.v1_16_R2.MinecraftServer;
/*     */ import org.bukkit.command.Command;
/*     */ import org.bukkit.command.CommandSender;
/*     */ 
/*     */ public class RestartCommand
/*     */   extends Command {
/*     */   public RestartCommand(String name) {
/*  15 */     super(name);
/*  16 */     this.description = "Restarts the server";
/*  17 */     this.usageMessage = "/restart";
/*  18 */     setPermission("bukkit.command.restart");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean execute(CommandSender sender, String currentAlias, String[] args) {
/*  24 */     if (testPermission(sender))
/*     */     {
/*  26 */       (MinecraftServer.getServer()).processQueue.add(new Runnable()
/*     */           {
/*     */             
/*     */             public void run()
/*     */             {
/*  31 */               RestartCommand.restart();
/*     */             }
/*     */           });
/*     */     }
/*  35 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void restart() {
/*  40 */     restart(SpigotConfig.restartScript);
/*     */   }
/*     */ 
/*     */   
/*     */   private static void restart(String restartScript) {
/*  45 */     AsyncCatcher.enabled = false;
/*  46 */     AsyncCatcher.shuttingDown = true;
/*     */ 
/*     */     
/*     */     try {
/*  50 */       boolean isRestarting = addShutdownHook(restartScript);
/*  51 */       if (isRestarting) {
/*     */         
/*  53 */         System.out.println("Attempting to restart with " + SpigotConfig.restartScript);
/*     */       } else {
/*     */         
/*  56 */         System.out.println("Startup script '" + SpigotConfig.restartScript + "' does not exist! Stopping server.");
/*     */       } 
/*     */       
/*  59 */       WatchdogThread.doStop();
/*     */       
/*  61 */       shutdownServer(isRestarting);
/*     */     }
/*  63 */     catch (Exception ex) {
/*     */       
/*  65 */       ex.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static void shutdownServer(boolean isRestarting) {
/*  72 */     if (MinecraftServer.getServer().isMainThread()) {
/*     */ 
/*     */       
/*  75 */       for (UnmodifiableIterator<EntityPlayer> unmodifiableIterator = ImmutableList.copyOf((MinecraftServer.getServer().getPlayerList()).players).iterator(); unmodifiableIterator.hasNext(); ) { EntityPlayer p = unmodifiableIterator.next();
/*     */         
/*  77 */         p.playerConnection.disconnect(SpigotConfig.restartMessage); }
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/*  82 */         Thread.sleep(100L);
/*  83 */       } catch (InterruptedException interruptedException) {}
/*     */ 
/*     */ 
/*     */       
/*  87 */       closeSocket();
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/*  92 */         MinecraftServer.getServer().close();
/*  93 */       } catch (Throwable throwable) {}
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  98 */       System.exit(0);
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 103 */       MinecraftServer.getServer().safeShutdown(false, isRestarting);
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 108 */         Thread.sleep(10000L);
/*     */       }
/* 110 */       catch (InterruptedException interruptedException) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 116 */       if (MinecraftServer.getServer().isStopped()) {
/*     */         return;
/*     */       }
/* 119 */       closeSocket();
/* 120 */       System.exit(0);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void closeSocket() {
/* 129 */     MinecraftServer.getServer().getServerConnection().b();
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 134 */       Thread.sleep(100L);
/* 135 */     } catch (InterruptedException interruptedException) {}
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean addShutdownHook(final String restartScript) {
/* 144 */     String[] split = restartScript.split(" ");
/* 145 */     if (split.length > 0 && (new File(split[0])).isFile()) {
/*     */       
/* 147 */       Thread shutdownHook = new Thread()
/*     */         {
/*     */ 
/*     */           
/*     */           public void run()
/*     */           {
/*     */             try {
/* 154 */               String os = System.getProperty("os.name").toLowerCase(Locale.ENGLISH);
/* 155 */               if (os.contains("win")) {
/*     */                 
/* 157 */                 Runtime.getRuntime().exec("cmd /c start " + restartScript);
/*     */               } else {
/*     */                 
/* 160 */                 Runtime.getRuntime().exec("sh " + restartScript);
/*     */               } 
/* 162 */             } catch (Exception e) {
/*     */               
/* 164 */               e.printStackTrace();
/*     */             } 
/*     */           }
/*     */         };
/*     */       
/* 169 */       shutdownHook.setDaemon(true);
/* 170 */       Runtime.getRuntime().addShutdownHook(shutdownHook);
/* 171 */       return true;
/*     */     } 
/*     */     
/* 174 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\spigotmc\RestartCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
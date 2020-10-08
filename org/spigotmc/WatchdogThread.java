/*     */ package org.spigotmc;
/*     */ import java.lang.management.ThreadInfo;
/*     */ import java.util.UUID;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import net.minecraft.server.v1_16_R2.Entity;
/*     */ import net.minecraft.server.v1_16_R2.EntityPlayer;
/*     */ import net.minecraft.server.v1_16_R2.MinecraftServer;
/*     */ import net.minecraft.server.v1_16_R2.PacketListener;
/*     */ import net.minecraft.server.v1_16_R2.Vec3D;
/*     */ import net.minecraft.server.v1_16_R2.World;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.CraftServer;
/*     */ 
/*     */ public class WatchdogThread extends Thread {
/*  16 */   public static final boolean DISABLE_WATCHDOG = Boolean.getBoolean("disable.watchdog");
/*     */   
/*     */   private static WatchdogThread instance;
/*     */   private final long timeoutTime;
/*     */   private final long earlyWarningEvery;
/*     */   private final long earlyWarningDelay;
/*     */   public static volatile boolean hasStarted;
/*     */   private long lastEarlyWarning;
/*     */   private final boolean restart;
/*     */   private volatile long lastTick;
/*     */   private volatile boolean stopping;
/*     */   
/*     */   private WatchdogThread(long timeoutTime, boolean restart) {
/*  29 */     super("Paper Watchdog Thread");
/*  30 */     this.timeoutTime = timeoutTime;
/*  31 */     this.restart = restart;
/*  32 */     this.earlyWarningEvery = Math.min(PaperConfig.watchdogPrintEarlyWarningEvery, timeoutTime);
/*  33 */     this.earlyWarningDelay = Math.min(PaperConfig.watchdogPrintEarlyWarningDelay, timeoutTime);
/*     */   }
/*     */ 
/*     */   
/*     */   private static long monotonicMillis() {
/*  38 */     return System.nanoTime() / 1000000L;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void doStart(int timeoutTime, boolean restart) {
/*  43 */     if (instance == null) {
/*     */       
/*  45 */       if (timeoutTime <= 0) timeoutTime = 300; 
/*  46 */       instance = new WatchdogThread(timeoutTime * 1000L, restart);
/*  47 */       instance.start();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void tick() {
/*  53 */     instance.lastTick = monotonicMillis();
/*     */   }
/*     */ 
/*     */   
/*     */   public static void doStop() {
/*  58 */     if (instance != null)
/*     */     {
/*  60 */       instance.stopping = true;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private void dumpTickingInfo() {
/*  66 */     Logger log = Bukkit.getServer().getLogger();
/*     */ 
/*     */     
/*  69 */     for (Entity entity : WorldServer.getCurrentlyTickingEntities()) {
/*     */       double posX, posY, posZ;
/*     */       Vec3D mot;
/*     */       double moveStartX, moveStartY, moveStartZ;
/*     */       Vec3D moveVec;
/*  74 */       synchronized (entity.posLock) {
/*  75 */         posX = entity.locX();
/*  76 */         posY = entity.locY();
/*  77 */         posZ = entity.locZ();
/*  78 */         mot = entity.getMot();
/*  79 */         moveStartX = entity.getMoveStartX();
/*  80 */         moveStartY = entity.getMoveStartY();
/*  81 */         moveStartZ = entity.getMoveStartZ();
/*  82 */         moveVec = entity.getMoveVector();
/*     */       } 
/*     */       
/*  85 */       String entityType = entity.getMinecraftKey().toString();
/*  86 */       UUID entityUUID = entity.getUniqueID();
/*  87 */       World world = entity.getWorld();
/*     */       
/*  89 */       log.log(Level.SEVERE, "Ticking entity: " + entityType);
/*  90 */       log.log(Level.SEVERE, "Position: world: '" + ((world == null) ? "unknown world?" : world.getWorld().getName()) + "' at location (" + posX + ", " + posY + ", " + posZ + ")");
/*  91 */       log.log(Level.SEVERE, "Velocity: " + ((mot == null) ? "unknown velocity" : mot.toString()) + " (in blocks per tick)");
/*  92 */       if (moveVec != null) {
/*  93 */         log.log(Level.SEVERE, "Move call information: ");
/*  94 */         log.log(Level.SEVERE, "Start position: (" + moveStartX + ", " + moveStartY + ", " + moveStartZ + ")");
/*  95 */         log.log(Level.SEVERE, "Move vector: " + moveVec.toString());
/*     */       } 
/*  97 */       log.log(Level.SEVERE, "UUID: " + entityUUID);
/*     */     } 
/*     */ 
/*     */     
/* 101 */     for (PacketListener packetListener : PlayerConnectionUtils.getCurrentPacketProcessors()) {
/* 102 */       if (packetListener instanceof PlayerConnection) {
/* 103 */         double posX, posY, posZ, moveStartX, moveStartY, moveStartZ; Vec3D moveVec; EntityPlayer player = ((PlayerConnection)packetListener).player;
/* 104 */         long totalPackets = PlayerConnectionUtils.getTotalProcessedPackets();
/* 105 */         if (player == null) {
/* 106 */           log.log(Level.SEVERE, "Handling packet for player connection (null player): " + packetListener);
/* 107 */           log.log(Level.SEVERE, "Total packets processed on the main thread for all players: " + totalPackets);
/*     */ 
/*     */           
/*     */           continue;
/*     */         } 
/*     */         
/* 113 */         synchronized (player.posLock) {
/* 114 */           posX = player.locX();
/* 115 */           posY = player.locY();
/* 116 */           posZ = player.locZ();
/* 117 */           moveStartX = player.getMoveStartX();
/* 118 */           moveStartY = player.getMoveStartY();
/* 119 */           moveStartZ = player.getMoveStartZ();
/* 120 */           moveVec = player.getMoveVector();
/*     */         } 
/*     */         
/* 123 */         UUID entityUUID = player.getUniqueID();
/* 124 */         World world = player.getWorld();
/*     */         
/* 126 */         log.log(Level.SEVERE, "Handling packet for player '" + player.getName() + "', UUID: " + entityUUID);
/* 127 */         log.log(Level.SEVERE, "Position: world: '" + ((world == null) ? "unknown world?" : world.getWorld().getName()) + "' at location (" + posX + ", " + posY + ", " + posZ + ")");
/* 128 */         if (moveVec != null) {
/* 129 */           log.log(Level.SEVERE, "Move call information: ");
/* 130 */           log.log(Level.SEVERE, "Start position: (" + moveStartX + ", " + moveStartY + ", " + moveStartZ + ")");
/* 131 */           log.log(Level.SEVERE, "Move vector: " + moveVec.toString());
/*     */         } 
/* 133 */         log.log(Level.SEVERE, "Total packets processed on the main thread for all players: " + totalPackets);
/*     */         continue;
/*     */       } 
/* 136 */       log.log(Level.SEVERE, "Handling packet for connection: " + packetListener);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void run() {
/* 145 */     while (!this.stopping) {
/*     */ 
/*     */       
/* 148 */       Logger log = Bukkit.getServer().getLogger();
/* 149 */       long currentTime = monotonicMillis();
/* 150 */       MinecraftServer server = MinecraftServer.getServer();
/* 151 */       if (this.lastTick != 0L && hasStarted && (!server.isRunning() || (currentTime > this.lastTick + this.earlyWarningEvery && !DISABLE_WATCHDOG))) {
/*     */         
/* 153 */         boolean isLongTimeout = (currentTime > this.lastTick + this.timeoutTime || (!server.isRunning() && !server.hasStopped() && currentTime > this.lastTick + 1000L));
/*     */         
/* 155 */         if ((!isLongTimeout && (this.earlyWarningEvery <= 0L || !hasStarted || currentTime < this.lastEarlyWarning + this.earlyWarningEvery || currentTime < this.lastTick + this.earlyWarningDelay)) || (
/* 156 */           !isLongTimeout && server.hasStopped()))
/* 157 */           continue;  this.lastEarlyWarning = currentTime;
/* 158 */         if (isLongTimeout) {
/*     */           
/* 160 */           log.log(Level.SEVERE, "------------------------------");
/* 161 */           log.log(Level.SEVERE, "The server has stopped responding! This is (probably) not a Paper bug.");
/* 162 */           log.log(Level.SEVERE, "If you see a plugin in the Server thread dump below, then please report it to that author");
/* 163 */           log.log(Level.SEVERE, "\t *Especially* if it looks like HTTP or MySQL operations are occurring");
/* 164 */           log.log(Level.SEVERE, "If you see a world save or edit, then it means you did far more than your server can handle at once");
/* 165 */           log.log(Level.SEVERE, "\t If this is the case, consider increasing timeout-time in spigot.yml but note that this will replace the crash with LARGE lag spikes");
/* 166 */           log.log(Level.SEVERE, "If you are unsure or still think this is a Paper bug, please report this to https://github.com/PaperMC/Paper/issues");
/* 167 */           log.log(Level.SEVERE, "Be sure to include ALL relevant console errors and Minecraft crash reports");
/* 168 */           log.log(Level.SEVERE, "Paper version: " + Bukkit.getServer().getVersion());
/*     */           
/* 170 */           if (World.lastPhysicsProblem != null) {
/*     */             
/* 172 */             log.log(Level.SEVERE, "------------------------------");
/* 173 */             log.log(Level.SEVERE, "During the run of the server, a physics stackoverflow was supressed");
/* 174 */             log.log(Level.SEVERE, "near " + World.lastPhysicsProblem);
/*     */           } 
/*     */           
/* 177 */           if (CraftServer.excessiveVelEx != null)
/*     */           {
/* 179 */             log.log(Level.SEVERE, "------------------------------");
/* 180 */             log.log(Level.SEVERE, "During the run of the server, a plugin set an excessive velocity on an entity");
/* 181 */             log.log(Level.SEVERE, "This may be the cause of the issue, or it may be entirely unrelated");
/* 182 */             log.log(Level.SEVERE, CraftServer.excessiveVelEx.getMessage());
/* 183 */             for (StackTraceElement stack : CraftServer.excessiveVelEx.getStackTrace())
/*     */             {
/* 185 */               log.log(Level.SEVERE, "\t\t" + stack);
/*     */             }
/*     */           }
/*     */         
/*     */         } else {
/*     */           
/* 191 */           log.log(Level.SEVERE, "--- DO NOT REPORT THIS TO PAPER - THIS IS NOT A BUG OR A CRASH  - " + Bukkit.getServer().getVersion() + " ---");
/* 192 */           log.log(Level.SEVERE, "The server has not responded for " + ((currentTime - this.lastTick) / 1000L) + " seconds! Creating thread dump");
/*     */         } 
/*     */         
/* 195 */         log.log(Level.SEVERE, "------------------------------");
/* 196 */         log.log(Level.SEVERE, "Server thread dump (Look for plugins here before reporting to Paper!):");
/* 197 */         ChunkTaskManager.dumpAllChunkLoadInfo();
/* 198 */         dumpTickingInfo();
/* 199 */         dumpThread(ManagementFactory.getThreadMXBean().getThreadInfo(server.serverThread.getId(), 2147483647), log);
/* 200 */         log.log(Level.SEVERE, "------------------------------");
/*     */ 
/*     */         
/* 203 */         if (isLongTimeout) {
/*     */           
/* 205 */           log.log(Level.SEVERE, "Entire Thread Dump:");
/* 206 */           ThreadInfo[] threads = ManagementFactory.getThreadMXBean().dumpAllThreads(true, true);
/* 207 */           for (ThreadInfo thread : threads)
/*     */           {
/* 209 */             dumpThread(thread, log);
/*     */           }
/*     */         } else {
/* 212 */           log.log(Level.SEVERE, "--- DO NOT REPORT THIS TO PAPER - THIS IS NOT A BUG OR A CRASH ---");
/*     */         } 
/*     */ 
/*     */         
/* 216 */         log.log(Level.SEVERE, "------------------------------");
/*     */         
/* 218 */         if (isLongTimeout) {
/*     */           
/* 220 */           if (!server.hasStopped()) {
/*     */             
/* 222 */             AsyncCatcher.enabled = false;
/* 223 */             AsyncCatcher.shuttingDown = true;
/* 224 */             server.forceTicks = true;
/* 225 */             if (this.restart) {
/* 226 */               RestartCommand.addShutdownHook(SpigotConfig.restartScript);
/*     */             }
/*     */             
/* 229 */             server.safeShutdown(false, this.restart);
/*     */             try {
/* 231 */               Thread.sleep(1000L);
/* 232 */             } catch (InterruptedException e) {
/* 233 */               e.printStackTrace();
/*     */             } 
/* 235 */             if (!server.hasStopped()) {
/* 236 */               server.close();
/*     */             }
/*     */           } 
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/*     */       
/*     */       try {
/* 245 */         sleep(1000L);
/* 246 */       } catch (InterruptedException ex) {
/*     */         
/* 248 */         interrupt();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static void dumpThread(ThreadInfo thread, Logger log) {
/* 255 */     log.log(Level.SEVERE, "------------------------------");
/*     */     
/* 257 */     log.log(Level.SEVERE, "Current Thread: " + thread.getThreadName());
/* 258 */     log.log(Level.SEVERE, "\tPID: " + thread.getThreadId() + " | Suspended: " + thread
/* 259 */         .isSuspended() + " | Native: " + thread
/* 260 */         .isInNative() + " | State: " + thread
/* 261 */         .getThreadState());
/* 262 */     if ((thread.getLockedMonitors()).length != 0) {
/*     */       
/* 264 */       log.log(Level.SEVERE, "\tThread is waiting on monitor(s):");
/* 265 */       for (MonitorInfo monitor : thread.getLockedMonitors())
/*     */       {
/* 267 */         log.log(Level.SEVERE, "\t\tLocked on:" + monitor.getLockedStackFrame());
/*     */       }
/*     */     } 
/* 270 */     log.log(Level.SEVERE, "\tStack:");
/*     */     
/* 272 */     for (StackTraceElement stack : thread.getStackTrace())
/*     */     {
/* 274 */       log.log(Level.SEVERE, "\t\t" + stack);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\spigotmc\WatchdogThread.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
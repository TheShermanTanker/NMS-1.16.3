/*     */ package co.aikar.timings;
/*     */ 
/*     */ import com.google.common.collect.MapMaker;
/*     */ import java.util.Map;
/*     */ import net.minecraft.server.v1_16_R2.Block;
/*     */ import net.minecraft.server.v1_16_R2.CustomFunction;
/*     */ import net.minecraft.server.v1_16_R2.Packet;
/*     */ import net.minecraft.server.v1_16_R2.TileEntity;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.scheduler.CraftTask;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ import org.bukkit.scheduler.BukkitTask;
/*     */ 
/*     */ public final class MinecraftTimings
/*     */ {
/*  15 */   public static final Timing serverOversleep = Timings.ofSafe("Server Oversleep");
/*  16 */   public static final Timing midTickChunkTasks = Timings.ofSafe("Mid Tick Chunk Tasks");
/*  17 */   public static final Timing playerListTimer = Timings.ofSafe("Player List");
/*  18 */   public static final Timing commandFunctionsTimer = Timings.ofSafe("Command Functions");
/*  19 */   public static final Timing connectionTimer = Timings.ofSafe("Connection Handler");
/*  20 */   public static final Timing tickablesTimer = Timings.ofSafe("Tickables");
/*  21 */   public static final Timing minecraftSchedulerTimer = Timings.ofSafe("Minecraft Scheduler");
/*  22 */   public static final Timing bukkitSchedulerTimer = Timings.ofSafe("Bukkit Scheduler");
/*  23 */   public static final Timing bukkitSchedulerPendingTimer = Timings.ofSafe("Bukkit Scheduler - Pending");
/*  24 */   public static final Timing bukkitSchedulerFinishTimer = Timings.ofSafe("Bukkit Scheduler - Finishing");
/*  25 */   public static final Timing chunkIOTickTimer = Timings.ofSafe("ChunkIOTick");
/*  26 */   public static final Timing timeUpdateTimer = Timings.ofSafe("Time Update");
/*  27 */   public static final Timing serverCommandTimer = Timings.ofSafe("Server Command");
/*  28 */   public static final Timing savePlayers = Timings.ofSafe("Save Players");
/*     */   
/*  30 */   public static final Timing tickEntityTimer = Timings.ofSafe("## tickEntity");
/*  31 */   public static final Timing tickTileEntityTimer = Timings.ofSafe("## tickTileEntity");
/*  32 */   public static final Timing packetProcessTimer = Timings.ofSafe("## Packet Processing");
/*  33 */   public static final Timing scheduledBlocksTimer = Timings.ofSafe("## Scheduled Blocks");
/*  34 */   public static final Timing structureGenerationTimer = Timings.ofSafe("Structure Generation");
/*     */   
/*  36 */   public static final Timing processQueueTimer = Timings.ofSafe("processQueue");
/*  37 */   public static final Timing processTasksTimer = Timings.ofSafe("processTasks");
/*     */   
/*  39 */   public static final Timing playerCommandTimer = Timings.ofSafe("playerCommand");
/*     */   
/*  41 */   public static final Timing entityActivationCheckTimer = Timings.ofSafe("entityActivationCheck");
/*     */   
/*  43 */   public static final Timing antiXrayUpdateTimer = Timings.ofSafe("anti-xray - update");
/*  44 */   public static final Timing antiXrayObfuscateTimer = Timings.ofSafe("anti-xray - obfuscate");
/*     */   
/*  46 */   public static final Timing scoreboardScoreSearch = Timings.ofSafe("Scoreboard score search");
/*  47 */   public static final Timing distanceManagerTick = Timings.ofSafe("Distance Manager Tick");
/*     */   
/*  49 */   private static final Map<Class<?>, String> taskNameCache = (new MapMaker()).weakKeys().makeMap();
/*     */ 
/*     */ 
/*     */   
/*     */   public static Timing getInternalTaskName(String taskName) {
/*  54 */     return Timings.ofSafe(taskName);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Timing getPluginTaskTimings(BukkitTask bukkitTask, long period) {
/*     */     Plugin plugin;
/*  64 */     if (!bukkitTask.isSync()) {
/*  65 */       return NullTimingHandler.NULL;
/*     */     }
/*     */ 
/*     */     
/*  69 */     CraftTask craftTask = (CraftTask)bukkitTask;
/*     */     
/*  71 */     Class<?> taskClass = craftTask.getTaskClass();
/*  72 */     if (bukkitTask.getOwner() != null) {
/*  73 */       plugin = bukkitTask.getOwner();
/*     */     } else {
/*  75 */       plugin = TimingsManager.getPluginByClassloader(taskClass);
/*     */     } 
/*     */     
/*  78 */     String taskname = taskNameCache.computeIfAbsent(taskClass, clazz -> {
/*     */           try {
/*     */             String clsName = !clazz.isMemberClass() ? clazz.getName() : clazz.getCanonicalName();
/*     */             
/*     */             if (clsName != null && clsName.contains("$Lambda$")) {
/*     */               clsName = clsName.replaceAll("(Lambda\\$.*?)/.*", "$1");
/*     */             }
/*     */             
/*     */             return (clsName != null) ? clsName : "UnknownTask";
/*  87 */           } catch (Throwable ex) {
/*     */             (new Exception("Error occurred detecting class name", ex)).printStackTrace();
/*     */             
/*     */             return "MangledClassFile";
/*     */           } 
/*     */         });
/*  93 */     StringBuilder name = new StringBuilder(64);
/*  94 */     name.append("Task: ").append(taskname);
/*  95 */     if (period > 0L) {
/*  96 */       name.append(" (interval:").append(period).append(")");
/*     */     } else {
/*  98 */       name.append(" (Single)");
/*     */     } 
/*     */     
/* 101 */     if (plugin == null) {
/* 102 */       return Timings.ofSafe((Plugin)null, name.toString());
/*     */     }
/*     */     
/* 105 */     return Timings.ofSafe(plugin, name.toString());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Timing getEntityTimings(String entityType, String type) {
/* 114 */     return Timings.ofSafe("Minecraft", "## tickEntity - " + entityType + " - " + type, tickEntityTimer);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Timing getTileEntityTimings(TileEntity entity) {
/* 123 */     String entityType = entity.getClass().getName();
/* 124 */     return Timings.ofSafe("Minecraft", "## tickTileEntity - " + entityType, tickTileEntityTimer);
/*     */   }
/*     */   public static Timing getCancelTasksTimer() {
/* 127 */     return Timings.ofSafe("Cancel Tasks");
/*     */   }
/*     */   public static Timing getCancelTasksTimer(Plugin plugin) {
/* 130 */     return Timings.ofSafe(plugin, "Cancel Tasks");
/*     */   }
/*     */   
/*     */   public static void stopServer() {
/* 134 */     TimingsManager.stopServer();
/*     */   }
/*     */   
/*     */   public static Timing getBlockTiming(Block block) {
/* 138 */     return Timings.ofSafe("## Scheduled Block: " + block.toString(), scheduledBlocksTimer);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Timing getPacketTiming(Packet packet) {
/* 146 */     return Timings.ofSafe("## Packet - " + packet.getClass().getName(), packetProcessTimer);
/*     */   }
/*     */   
/*     */   public static Timing getCommandFunctionTiming(CustomFunction function) {
/* 150 */     return Timings.ofSafe("Command Function - " + function.getMinecraftKey().toString());
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\co\aikar\timings\MinecraftTimings.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
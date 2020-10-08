/*     */ package co.aikar.timings;
/*     */ 
/*     */ import com.google.common.collect.ImmutableList;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.apache.commons.lang.Validate;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.command.CommandSender;
/*     */ import org.bukkit.command.defaults.BukkitCommand;
/*     */ import org.bukkit.util.StringUtil;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TimingsCommand
/*     */   extends BukkitCommand
/*     */ {
/*  40 */   private static final List<String> TIMINGS_SUBCOMMANDS = (List<String>)ImmutableList.of("report", "reset", "on", "off", "paste", "verbon", "verboff");
/*  41 */   private long lastResetAttempt = 0L;
/*     */   
/*     */   public TimingsCommand(@NotNull String name) {
/*  44 */     super(name);
/*  45 */     this.description = "Manages Spigot Timings data to see performance of the server.";
/*  46 */     this.usageMessage = "/timings <reset|report|on|off|verbon|verboff>";
/*  47 */     setPermission("bukkit.command.timings");
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean execute(@NotNull CommandSender sender, @NotNull String currentAlias, @NotNull String[] args) {
/*  52 */     if (!testPermission(sender)) {
/*  53 */       return true;
/*     */     }
/*  55 */     if (args.length < 1) {
/*  56 */       sender.sendMessage(ChatColor.RED + "Usage: " + this.usageMessage);
/*  57 */       return true;
/*     */     } 
/*  59 */     String arg = args[0];
/*  60 */     if ("on".equalsIgnoreCase(arg)) {
/*  61 */       Timings.setTimingsEnabled(true);
/*  62 */       sender.sendMessage("Enabled Timings & Reset");
/*  63 */       return true;
/*  64 */     }  if ("off".equalsIgnoreCase(arg)) {
/*  65 */       Timings.setTimingsEnabled(false);
/*  66 */       sender.sendMessage("Disabled Timings");
/*  67 */       return true;
/*     */     } 
/*     */     
/*  70 */     if (!Timings.isTimingsEnabled()) {
/*  71 */       sender.sendMessage("Please enable timings by typing /timings on");
/*  72 */       return true;
/*     */     } 
/*     */     
/*  75 */     long now = System.currentTimeMillis();
/*  76 */     if ("verbon".equalsIgnoreCase(arg)) {
/*  77 */       Timings.setVerboseTimingsEnabled(true);
/*  78 */       sender.sendMessage("Enabled Verbose Timings");
/*  79 */       return true;
/*  80 */     }  if ("verboff".equalsIgnoreCase(arg)) {
/*  81 */       Timings.setVerboseTimingsEnabled(false);
/*  82 */       sender.sendMessage("Disabled Verbose Timings");
/*  83 */       return true;
/*  84 */     }  if ("reset".equalsIgnoreCase(arg)) {
/*  85 */       if (now - this.lastResetAttempt < 30000L) {
/*  86 */         TimingsManager.reset();
/*  87 */         sender.sendMessage(ChatColor.RED + "Timings reset. Please wait 5-10 minutes before using /timings report.");
/*     */       } else {
/*  89 */         this.lastResetAttempt = now;
/*  90 */         sender.sendMessage(ChatColor.RED + "WARNING: Timings v2 should not be reset. If you are encountering lag, please wait 3 minutes and then issue a report. The best timings will include 10+ minutes, with data before and after your lag period. If you really want to reset, run this command again within 30 seconds.");
/*     */       } 
/*  92 */     } else if ("paste"
/*  93 */       .equalsIgnoreCase(arg) || "report"
/*  94 */       .equalsIgnoreCase(arg) || "get"
/*  95 */       .equalsIgnoreCase(arg) || "merged"
/*  96 */       .equalsIgnoreCase(arg) || "separate"
/*  97 */       .equalsIgnoreCase(arg)) {
/*     */       
/*  99 */       Timings.generateReport(sender);
/*     */     } else {
/* 101 */       sender.sendMessage(ChatColor.RED + "Usage: " + this.usageMessage);
/*     */     } 
/* 103 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public List<String> tabComplete(@NotNull CommandSender sender, @NotNull String alias, @NotNull String[] args) {
/* 109 */     Validate.notNull(sender, "Sender cannot be null");
/* 110 */     Validate.notNull(args, "Arguments cannot be null");
/* 111 */     Validate.notNull(alias, "Alias cannot be null");
/*     */     
/* 113 */     if (args.length == 1) {
/* 114 */       return (List<String>)StringUtil.copyPartialMatches(args[0], TIMINGS_SUBCOMMANDS, new ArrayList(TIMINGS_SUBCOMMANDS
/* 115 */             .size()));
/*     */     }
/* 117 */     return (List<String>)ImmutableList.of();
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\co\aikar\timings\TimingsCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
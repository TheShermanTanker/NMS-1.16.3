/*    */ package org.spigotmc;
/*    */ 
/*    */ import org.apache.commons.lang.StringUtils;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.command.Command;
/*    */ import org.bukkit.command.CommandSender;
/*    */ 
/*    */ public class TicksPerSecondCommand
/*    */   extends Command
/*    */ {
/*    */   private boolean hasShownMemoryWarning;
/*    */   
/*    */   public TicksPerSecondCommand(String name) {
/* 15 */     super(name);
/* 16 */     this.description = "Gets the current ticks per second for the server";
/* 17 */     this.usageMessage = "/tps";
/* 18 */     setPermission("bukkit.command.tps");
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean execute(CommandSender sender, String currentAlias, String[] args) {
/* 24 */     if (!testPermission(sender))
/*    */     {
/* 26 */       return true;
/*    */     }
/*    */ 
/*    */     
/* 30 */     double[] tps = Bukkit.getTPS();
/* 31 */     String[] tpsAvg = new String[tps.length];
/*    */     
/* 33 */     for (int i = 0; i < tps.length; i++) {
/* 34 */       tpsAvg[i] = format(tps[i]);
/*    */     }
/* 36 */     sender.sendMessage(ChatColor.GOLD + "TPS from last 1m, 5m, 15m: " + StringUtils.join((Object[])tpsAvg, ", "));
/* 37 */     if (args.length > 0 && args[0].equals("mem") && sender.hasPermission("bukkit.command.tpsmemory")) {
/* 38 */       sender.sendMessage(ChatColor.GOLD + "Current Memory Usage: " + ChatColor.GREEN + ((Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1048576L) + "/" + (Runtime.getRuntime().totalMemory() / 1048576L) + " mb (Max: " + (Runtime.getRuntime().maxMemory() / 1048576L) + " mb)");
/* 39 */       if (!this.hasShownMemoryWarning) {
/* 40 */         sender.sendMessage(ChatColor.RED + "Warning: " + ChatColor.GOLD + " Memory usage on modern garbage collectors is not a stable value and it is perfectly normal to see it reach max. Please do not pay it much attention.");
/* 41 */         this.hasShownMemoryWarning = true;
/*    */       } 
/*    */     } 
/*    */ 
/*    */     
/* 46 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   private static String format(double tps) {
/* 52 */     return ((tps > 18.0D) ? ChatColor.GREEN : ((tps > 16.0D) ? ChatColor.YELLOW : ChatColor.RED)).toString() + (
/* 53 */       (tps > 21.0D) ? "*" : "") + Math.min(Math.round(tps * 100.0D) / 100.0D, 20.0D);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\spigotmc\TicksPerSecondCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
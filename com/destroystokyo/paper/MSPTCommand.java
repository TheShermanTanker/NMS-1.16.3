/*    */ package com.destroystokyo.paper;
/*    */ 
/*    */ import java.text.DecimalFormat;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Arrays;
/*    */ import java.util.Collections;
/*    */ import java.util.List;
/*    */ import net.minecraft.server.v1_16_R2.MinecraftServer;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.command.Command;
/*    */ import org.bukkit.command.CommandSender;
/*    */ 
/*    */ public class MSPTCommand
/*    */   extends Command
/*    */ {
/* 16 */   private static final DecimalFormat DF = new DecimalFormat("########0.0");
/*    */   
/*    */   public MSPTCommand(String name) {
/* 19 */     super(name);
/* 20 */     this.description = "View server tick times";
/* 21 */     this.usageMessage = "/mspt";
/* 22 */     setPermission("bukkit.command.mspt");
/*    */   }
/*    */ 
/*    */   
/*    */   public List<String> tabComplete(CommandSender sender, String alias, String[] args, Location location) throws IllegalArgumentException {
/* 27 */     return Collections.emptyList();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean execute(CommandSender sender, String commandLabel, String[] args) {
/* 32 */     if (!testPermission(sender)) return true;
/*    */     
/* 34 */     MinecraftServer server = MinecraftServer.getServer();
/*    */     
/* 36 */     List<String> times = new ArrayList<>();
/* 37 */     times.addAll(eval(server.tickTimes5s.getTimes()));
/* 38 */     times.addAll(eval(server.tickTimes10s.getTimes()));
/* 39 */     times.addAll(eval(server.tickTimes60s.getTimes()));
/*    */     
/* 41 */     sender.sendMessage("§6Server tick times §e(§7avg§e/§7min§e/§7max§e)§6 from last 5s§7,§6 10s§7,§6 1m§e:");
/* 42 */     sender.sendMessage(String.format("§6◴ %s§7/%s§7/%s§e, %s§7/%s§7/%s§e, %s§7/%s§7/%s", times.toArray()));
/* 43 */     return true;
/*    */   }
/*    */   
/*    */   private static List<String> eval(long[] times) {
/* 47 */     long min = 2147483647L;
/* 48 */     long max = 0L;
/* 49 */     long total = 0L;
/* 50 */     for (long value : times) {
/* 51 */       if (value > 0L && value < min) min = value; 
/* 52 */       if (value > max) max = value; 
/* 53 */       total += value;
/*    */     } 
/* 55 */     double avgD = total / times.length * 1.0E-6D;
/* 56 */     double minD = min * 1.0E-6D;
/* 57 */     double maxD = max * 1.0E-6D;
/* 58 */     return Arrays.asList(new String[] { getColor(avgD), getColor(minD), getColor(maxD) });
/*    */   }
/*    */   
/*    */   private static String getColor(double avg) {
/* 62 */     return '§' + ((avg >= 50.0D) ? "c" : ((avg >= 40.0D) ? "e" : "a")) + DF.format(avg);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\MSPTCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
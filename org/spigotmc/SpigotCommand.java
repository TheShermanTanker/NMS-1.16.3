/*    */ package org.spigotmc;
/*    */ 
/*    */ import java.io.File;
/*    */ import net.minecraft.server.v1_16_R2.MinecraftServer;
/*    */ import net.minecraft.server.v1_16_R2.WorldServer;
/*    */ import org.bukkit.ChatColor;
/*    */ import org.bukkit.command.Command;
/*    */ import org.bukkit.command.CommandSender;
/*    */ 
/*    */ public class SpigotCommand
/*    */   extends Command {
/*    */   public SpigotCommand(String name) {
/* 13 */     super(name);
/* 14 */     this.description = "Spigot related commands";
/* 15 */     this.usageMessage = "/spigot reload";
/* 16 */     setPermission("bukkit.command.spigot");
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean execute(CommandSender sender, String commandLabel, String[] args) {
/* 21 */     if (!testPermission(sender)) return true;
/*    */     
/* 23 */     if (args.length != 1) {
/* 24 */       sender.sendMessage(ChatColor.RED + "Usage: " + this.usageMessage);
/* 25 */       return false;
/*    */     } 
/*    */     
/* 28 */     if (args[0].equals("reload")) {
/* 29 */       Command.broadcastCommandMessage(sender, ChatColor.RED + "Please note that this command is not supported and may cause issues.");
/* 30 */       Command.broadcastCommandMessage(sender, ChatColor.RED + "If you encounter any issues please use the /stop command to restart your server.");
/*    */       
/* 32 */       MinecraftServer console = MinecraftServer.getServer();
/* 33 */       SpigotConfig.init((File)console.options.valueOf("spigot-settings"));
/* 34 */       for (WorldServer world : console.getWorlds()) {
/* 35 */         world.spigotConfig.init();
/*    */       }
/* 37 */       console.server.reloadCount++;
/*    */       
/* 39 */       Command.broadcastCommandMessage(sender, ChatColor.GREEN + "Reload complete.");
/*    */     } 
/*    */     
/* 42 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\spigotmc\SpigotCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
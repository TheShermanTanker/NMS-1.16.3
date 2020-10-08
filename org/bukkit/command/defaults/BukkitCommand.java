/*    */ package org.bukkit.command.defaults;
/*    */ 
/*    */ import java.util.List;
/*    */ import org.bukkit.command.Command;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public abstract class BukkitCommand extends Command {
/*    */   protected BukkitCommand(@NotNull String name) {
/*  9 */     super(name);
/*    */   }
/*    */   
/*    */   protected BukkitCommand(@NotNull String name, @NotNull String description, @NotNull String usageMessage, @NotNull List<String> aliases) {
/* 13 */     super(name, description, usageMessage, aliases);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\command\defaults\BukkitCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
/*    */ package org.bukkit.craftbukkit.v1_16_R2.command;
/*    */ 
/*    */ import com.google.common.base.Joiner;
/*    */ import com.mojang.brigadier.ParseResults;
/*    */ import com.mojang.brigadier.suggestion.Suggestion;
/*    */ import com.mojang.brigadier.suggestion.Suggestions;
/*    */ import com.mojang.brigadier.tree.CommandNode;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Collections;
/*    */ import java.util.List;
/*    */ import net.minecraft.server.v1_16_R2.CommandDispatcher;
/*    */ import net.minecraft.server.v1_16_R2.CommandListenerWrapper;
/*    */ import net.minecraft.server.v1_16_R2.DedicatedServer;
/*    */ import net.minecraft.server.v1_16_R2.MinecraftServer;
/*    */ import org.apache.commons.lang.Validate;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.command.defaults.BukkitCommand;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.CraftServer;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.entity.CraftMinecartCommand;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.entity.CraftPlayer;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class VanillaCommandWrapper
/*    */   extends BukkitCommand
/*    */ {
/*    */   private final CommandDispatcher dispatcher;
/*    */   public final CommandNode<CommandListenerWrapper> vanillaCommand;
/*    */   
/*    */   public VanillaCommandWrapper(CommandDispatcher dispatcher, CommandNode<CommandListenerWrapper> vanillaCommand) {
/* 34 */     super(vanillaCommand.getName(), "A Mojang provided command.", vanillaCommand.getUsageText(), Collections.EMPTY_LIST);
/* 35 */     this.dispatcher = dispatcher;
/* 36 */     this.vanillaCommand = vanillaCommand;
/* 37 */     setPermission(getPermission(vanillaCommand));
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean execute(CommandSender sender, String commandLabel, String[] args) {
/* 42 */     if (!testPermission(sender)) return true;
/*    */     
/* 44 */     CommandListenerWrapper icommandlistener = getListener(sender);
/* 45 */     this.dispatcher.a(icommandlistener, toDispatcher(args, getName()), toDispatcher(args, commandLabel));
/* 46 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public List<String> tabComplete(CommandSender sender, String alias, String[] args, Location location) throws IllegalArgumentException {
/* 51 */     Validate.notNull(sender, "Sender cannot be null");
/* 52 */     Validate.notNull(args, "Arguments cannot be null");
/* 53 */     Validate.notNull(alias, "Alias cannot be null");
/*    */     
/* 55 */     CommandListenerWrapper icommandlistener = getListener(sender);
/* 56 */     ParseResults<CommandListenerWrapper> parsed = this.dispatcher.a().parse(toDispatcher(args, getName()), icommandlistener);
/*    */     
/* 58 */     List<String> results = new ArrayList<>();
/* 59 */     this.dispatcher.a().getCompletionSuggestions(parsed).thenAccept(suggestions -> suggestions.getList().forEach(()));
/*    */ 
/*    */ 
/*    */     
/* 63 */     return results;
/*    */   }
/*    */   
/*    */   public static CommandListenerWrapper getListener(CommandSender sender) {
/* 67 */     if (sender instanceof org.bukkit.entity.Player) {
/* 68 */       return ((CraftPlayer)sender).getHandle().getCommandListener();
/*    */     }
/* 70 */     if (sender instanceof org.bukkit.command.BlockCommandSender) {
/* 71 */       return ((CraftBlockCommandSender)sender).getWrapper();
/*    */     }
/* 73 */     if (sender instanceof org.bukkit.entity.minecart.CommandMinecart) {
/* 74 */       return ((CraftMinecartCommand)sender).getHandle().getCommandBlock().getWrapper();
/*    */     }
/* 76 */     if (sender instanceof org.bukkit.command.RemoteConsoleCommandSender) {
/* 77 */       return ((DedicatedServer)MinecraftServer.getServer()).remoteControlCommandListener.getWrapper();
/*    */     }
/* 79 */     if (sender instanceof org.bukkit.command.ConsoleCommandSender) {
/* 80 */       return ((CraftServer)sender.getServer()).getServer().getServerCommandListener();
/*    */     }
/* 82 */     if (sender instanceof org.bukkit.command.ProxiedCommandSender) {
/* 83 */       return ((ProxiedNativeCommandSender)sender).getHandle();
/*    */     }
/*    */     
/* 86 */     throw new IllegalArgumentException("Cannot make " + sender + " a vanilla command listener");
/*    */   }
/*    */   
/*    */   public static String getPermission(CommandNode<CommandListenerWrapper> vanillaCommand) {
/* 90 */     return "minecraft.command." + ((vanillaCommand.getRedirect() == null) ? vanillaCommand.getName() : vanillaCommand.getRedirect().getName());
/*    */   }
/*    */   
/*    */   private String toDispatcher(String[] args, String name) {
/* 94 */     return name + ((args.length > 0) ? (" " + Joiner.on(' ').join((Object[])args)) : "");
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\command\VanillaCommandWrapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
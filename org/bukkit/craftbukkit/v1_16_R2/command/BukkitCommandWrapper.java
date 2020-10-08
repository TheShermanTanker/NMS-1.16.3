/*    */ package org.bukkit.craftbukkit.v1_16_R2.command;
/*    */ import com.destroystokyo.paper.brigadier.BukkitBrigadierCommand;
/*    */ import com.destroystokyo.paper.event.brigadier.CommandRegisteredEvent;
/*    */ import com.mojang.brigadier.Command;
/*    */ import com.mojang.brigadier.CommandDispatcher;
/*    */ import com.mojang.brigadier.arguments.StringArgumentType;
/*    */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*    */ import com.mojang.brigadier.builder.RequiredArgumentBuilder;
/*    */ import com.mojang.brigadier.context.CommandContext;
/*    */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*    */ import com.mojang.brigadier.suggestion.SuggestionsBuilder;
/*    */ import com.mojang.brigadier.tree.ArgumentCommandNode;
/*    */ import com.mojang.brigadier.tree.CommandNode;
/*    */ import com.mojang.brigadier.tree.LiteralCommandNode;
/*    */ import com.mojang.brigadier.tree.RootCommandNode;
/*    */ import java.util.List;
/*    */ import java.util.concurrent.CompletableFuture;
/*    */ import net.minecraft.server.v1_16_R2.CommandListenerWrapper;
/*    */ import org.bukkit.command.Command;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.CraftServer;
/*    */ 
/*    */ public class BukkitCommandWrapper implements Command<CommandListenerWrapper>, Predicate<CommandListenerWrapper>, SuggestionProvider<CommandListenerWrapper>, BukkitBrigadierCommand<CommandListenerWrapper> {
/*    */   private final CraftServer server;
/*    */   
/*    */   public BukkitCommandWrapper(CraftServer server, Command command) {
/* 26 */     this.server = server;
/* 27 */     this.command = command;
/*    */   }
/*    */   private final Command command;
/*    */   
/*    */   public LiteralCommandNode<CommandListenerWrapper> register(CommandDispatcher<CommandListenerWrapper> dispatcher, String label) {
/* 32 */     RootCommandNode<CommandListenerWrapper> root = dispatcher.getRoot();
/* 33 */     LiteralCommandNode<CommandListenerWrapper> literal = ((LiteralArgumentBuilder)((LiteralArgumentBuilder)LiteralArgumentBuilder.literal(label).requires(this)).executes(this)).build();
/* 34 */     ArgumentCommandNode<CommandListenerWrapper, String> defaultArgs = ((RequiredArgumentBuilder)RequiredArgumentBuilder.argument("args", (ArgumentType)StringArgumentType.greedyString()).suggests(this).executes(this)).build();
/* 35 */     literal.addChild((CommandNode)defaultArgs);
/* 36 */     CommandRegisteredEvent<CommandListenerWrapper> event = new CommandRegisteredEvent(label, this, this.command, root, literal, defaultArgs);
/* 37 */     if (!event.callEvent()) {
/* 38 */       return null;
/*    */     }
/* 40 */     literal = event.getLiteral();
/* 41 */     root.addChild((CommandNode)literal);
/* 42 */     return literal;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean test(CommandListenerWrapper wrapper) {
/* 48 */     return this.command.testPermissionSilent(wrapper.getBukkitSender());
/*    */   }
/*    */ 
/*    */   
/*    */   public int run(CommandContext<CommandListenerWrapper> context) throws CommandSyntaxException {
/* 53 */     return this.server.dispatchCommand(((CommandListenerWrapper)context.getSource()).getBukkitSender(), context.getInput()) ? 1 : 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public CompletableFuture<Suggestions> getSuggestions(CommandContext<CommandListenerWrapper> context, SuggestionsBuilder builder) throws CommandSyntaxException {
/* 58 */     List<String> results = this.server.tabComplete(((CommandListenerWrapper)context.getSource()).getBukkitSender(), builder.getInput(), ((CommandListenerWrapper)context.getSource()).getWorld(), ((CommandListenerWrapper)context.getSource()).getPosition(), true);
/*    */ 
/*    */     
/* 61 */     builder = builder.createOffset(builder.getInput().lastIndexOf(' ') + 1);
/*    */     
/* 63 */     for (String s : results) {
/* 64 */       builder.suggest(s);
/*    */     }
/*    */     
/* 67 */     return builder.buildFuture();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\command\BukkitCommandWrapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
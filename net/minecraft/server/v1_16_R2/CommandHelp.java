/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.Iterables;
/*    */ import com.mojang.brigadier.CommandDispatcher;
/*    */ import com.mojang.brigadier.ParseResults;
/*    */ import com.mojang.brigadier.arguments.ArgumentType;
/*    */ import com.mojang.brigadier.arguments.StringArgumentType;
/*    */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*    */ import com.mojang.brigadier.context.CommandContext;
/*    */ import com.mojang.brigadier.context.ParsedCommandNode;
/*    */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*    */ import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
/*    */ import com.mojang.brigadier.tree.CommandNode;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CommandHelp
/*    */ {
/* 20 */   private static final SimpleCommandExceptionType a = new SimpleCommandExceptionType(new ChatMessage("commands.help.failed"));
/*    */   
/*    */   public static void a(CommandDispatcher<CommandListenerWrapper> var0) {
/* 23 */     var0.register(
/* 24 */         (LiteralArgumentBuilder)((LiteralArgumentBuilder)CommandDispatcher.a("help")
/* 25 */         .executes(var1 -> {
/*    */             Map<CommandNode<CommandListenerWrapper>, String> var2 = var0.getSmartUsage((CommandNode)var0.getRoot(), var1.getSource());
/*    */             
/*    */             for (String var4 : var2.values()) {
/*    */               ((CommandListenerWrapper)var1.getSource()).sendMessage(new ChatComponentText("/" + var4), false);
/*    */             }
/*    */             return var2.size();
/* 32 */           })).then(
/* 33 */           CommandDispatcher.<T>a("command", (ArgumentType<T>)StringArgumentType.greedyString())
/* 34 */           .executes(var1 -> {
/*    */               ParseResults<CommandListenerWrapper> var2 = var0.parse(StringArgumentType.getString(var1, "command"), var1.getSource());
/*    */               if (var2.getContext().getNodes().isEmpty())
/*    */                 throw a.create(); 
/*    */               Map<CommandNode<CommandListenerWrapper>, String> var3 = var0.getSmartUsage(((ParsedCommandNode)Iterables.getLast(var2.getContext().getNodes())).getNode(), var1.getSource());
/*    */               for (String var5 : var3.values())
/*    */                 ((CommandListenerWrapper)var1.getSource()).sendMessage(new ChatComponentText("/" + var2.getReader().getString() + " " + var5), false); 
/*    */               return var3.size();
/*    */             })));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CommandHelp.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
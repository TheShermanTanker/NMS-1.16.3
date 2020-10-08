/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.brigadier.CommandDispatcher;
/*    */ import com.mojang.brigadier.arguments.ArgumentType;
/*    */ import com.mojang.brigadier.arguments.StringArgumentType;
/*    */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*    */ import com.mojang.brigadier.context.CommandContext;
/*    */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CommandMe
/*    */ {
/*    */   public static void a(CommandDispatcher<CommandListenerWrapper> var0) {
/* 17 */     var0.register(
/* 18 */         (LiteralArgumentBuilder)CommandDispatcher.a("me")
/* 19 */         .then(
/* 20 */           CommandDispatcher.<T>a("action", (ArgumentType<T>)StringArgumentType.greedyString()).executes(var0 -> {
/*    */               ChatMessage var1 = new ChatMessage("chat.type.emote", new Object[] { ((CommandListenerWrapper)var0.getSource()).getScoreboardDisplayName(), StringArgumentType.getString(var0, "action") });
/*    */               Entity var2 = ((CommandListenerWrapper)var0.getSource()).getEntity();
/*    */               if (var2 != null) {
/*    */                 ((CommandListenerWrapper)var0.getSource()).getServer().getPlayerList().sendMessage(var1, ChatMessageType.CHAT, var2.getUniqueID());
/*    */               } else {
/*    */                 ((CommandListenerWrapper)var0.getSource()).getServer().getPlayerList().sendMessage(var1, ChatMessageType.SYSTEM, SystemUtils.b);
/*    */               } 
/*    */               return 1;
/*    */             })));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CommandMe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
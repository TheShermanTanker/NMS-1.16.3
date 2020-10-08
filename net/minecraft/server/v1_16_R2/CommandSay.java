/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.brigadier.CommandDispatcher;
/*    */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*    */ import com.mojang.brigadier.context.CommandContext;
/*    */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CommandSay
/*    */ {
/*    */   public static void a(CommandDispatcher<CommandListenerWrapper> var0) {
/* 19 */     var0.register(
/* 20 */         (LiteralArgumentBuilder)((LiteralArgumentBuilder)CommandDispatcher.a("say")
/* 21 */         .requires(var0 -> var0.hasPermission(2)))
/* 22 */         .then(
/* 23 */           CommandDispatcher.<T>a("message", ArgumentChat.a())
/* 24 */           .executes(var0 -> {
/*    */               IChatBaseComponent var1 = ArgumentChat.a(var0, "message");
/*    */               ChatMessage var2 = new ChatMessage("chat.type.announcement", new Object[] { ((CommandListenerWrapper)var0.getSource()).getScoreboardDisplayName(), var1 });
/*    */               Entity var3 = ((CommandListenerWrapper)var0.getSource()).getEntity();
/*    */               if (var3 != null) {
/*    */                 ((CommandListenerWrapper)var0.getSource()).getServer().getPlayerList().sendMessage(var2, ChatMessageType.CHAT, var3.getUniqueID());
/*    */               } else {
/*    */                 ((CommandListenerWrapper)var0.getSource()).getServer().getPlayerList().sendMessage(var2, ChatMessageType.SYSTEM, SystemUtils.b);
/*    */               } 
/*    */               return 1;
/*    */             })));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CommandSay.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
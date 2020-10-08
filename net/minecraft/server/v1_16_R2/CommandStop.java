/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.brigadier.CommandDispatcher;
/*    */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*    */ import com.mojang.brigadier.context.CommandContext;
/*    */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*    */ 
/*    */ 
/*    */ public class CommandStop
/*    */ {
/*    */   public static void a(CommandDispatcher<CommandListenerWrapper> var0) {
/* 12 */     var0.register(
/* 13 */         (LiteralArgumentBuilder)((LiteralArgumentBuilder)CommandDispatcher.a("stop")
/* 14 */         .requires(var0 -> var0.hasPermission(4)))
/* 15 */         .executes(var0 -> {
/*    */             ((CommandListenerWrapper)var0.getSource()).sendMessage(new ChatMessage("commands.stop.stopping"), true);
/*    */             ((CommandListenerWrapper)var0.getSource()).getServer().safeShutdown(false);
/*    */             return 1;
/*    */           }));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CommandStop.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.brigadier.CommandDispatcher;
/*    */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*    */ import com.mojang.brigadier.context.CommandContext;
/*    */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*    */ import java.util.List;
/*    */ import java.util.function.Function;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CommandList
/*    */ {
/*    */   public static void a(CommandDispatcher<CommandListenerWrapper> var0) {
/* 19 */     var0.register(
/* 20 */         (LiteralArgumentBuilder)((LiteralArgumentBuilder)CommandDispatcher.a("list")
/* 21 */         .executes(var0 -> a((CommandListenerWrapper)var0.getSource())))
/* 22 */         .then(
/* 23 */           CommandDispatcher.a("uuids")
/* 24 */           .executes(var0 -> b((CommandListenerWrapper)var0.getSource()))));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   private static int a(CommandListenerWrapper var0) {
/* 30 */     return a(var0, EntityHuman::getScoreboardDisplayName);
/*    */   }
/*    */   
/*    */   private static int b(CommandListenerWrapper var0) {
/* 34 */     return a(var0, var0 -> new ChatMessage("commands.list.nameAndId", new Object[] { var0.getDisplayName(), var0.getProfile().getId() }));
/*    */   }
/*    */   
/*    */   private static int a(CommandListenerWrapper var0, Function<EntityPlayer, IChatBaseComponent> var1) {
/* 38 */     PlayerList var2 = var0.getServer().getPlayerList();
/* 39 */     List<EntityPlayer> var3 = var2.getPlayers();
/* 40 */     IChatBaseComponent var4 = ChatComponentUtils.b(var3, var1);
/* 41 */     var0.sendMessage(new ChatMessage("commands.list.players", new Object[] { Integer.valueOf(var3.size()), Integer.valueOf(var2.getMaxPlayers()), var4 }), false);
/* 42 */     return var3.size();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CommandList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
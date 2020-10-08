/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.Iterables;
/*    */ import com.google.common.collect.Lists;
/*    */ import com.mojang.brigadier.CommandDispatcher;
/*    */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*    */ import com.mojang.brigadier.context.CommandContext;
/*    */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*    */ import java.util.Collection;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CommandBanList
/*    */ {
/*    */   public static void a(CommandDispatcher<CommandListenerWrapper> var0) {
/* 18 */     var0.register(
/* 19 */         (LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)CommandDispatcher.a("banlist")
/* 20 */         .requires(var0 -> var0.hasPermission(3)))
/* 21 */         .executes(var0 -> {
/*    */             PlayerList var1 = ((CommandListenerWrapper)var0.getSource()).getServer().getPlayerList();
/*    */             
/*    */             return a((CommandListenerWrapper)var0.getSource(), Lists.newArrayList(Iterables.concat(var1.getProfileBans().d(), var1.getIPBans().d())));
/* 25 */           })).then(
/* 26 */           CommandDispatcher.a("ips")
/* 27 */           .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), (Collection)((CommandListenerWrapper)var0.getSource()).getServer().getPlayerList().getIPBans().d()))))
/*    */         
/* 29 */         .then(
/* 30 */           CommandDispatcher.a("players")
/* 31 */           .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), (Collection)((CommandListenerWrapper)var0.getSource()).getServer().getPlayerList().getProfileBans().d()))));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   private static int a(CommandListenerWrapper var0, Collection<? extends ExpirableListEntry<?>> var1) {
/* 37 */     if (var1.isEmpty()) {
/* 38 */       var0.sendMessage(new ChatMessage("commands.banlist.none"), false);
/*    */     } else {
/* 40 */       var0.sendMessage(new ChatMessage("commands.banlist.list", new Object[] { Integer.valueOf(var1.size()) }), false);
/* 41 */       for (ExpirableListEntry<?> var3 : var1) {
/* 42 */         var0.sendMessage(new ChatMessage("commands.banlist.entry", new Object[] { var3.e(), var3.getSource(), var3.getReason() }), false);
/*    */       } 
/*    */     } 
/* 45 */     return var1.size();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CommandBanList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.brigadier.CommandDispatcher;
/*    */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*    */ import com.mojang.brigadier.context.CommandContext;
/*    */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*    */ import java.util.Collection;
/*    */ import java.util.Collections;
/*    */ import java.util.Iterator;
/*    */ 
/*    */ public class CommandGamemode {
/*    */   public static void a(CommandDispatcher<CommandListenerWrapper> com_mojang_brigadier_commanddispatcher) {
/* 13 */     LiteralArgumentBuilder<CommandListenerWrapper> literalargumentbuilder = (LiteralArgumentBuilder<CommandListenerWrapper>)CommandDispatcher.a("gamemode").requires(commandlistenerwrapper -> commandlistenerwrapper.hasPermission(2));
/*    */ 
/*    */     
/* 16 */     EnumGamemode[] aenumgamemode = EnumGamemode.values();
/* 17 */     int i = aenumgamemode.length;
/*    */     
/* 19 */     for (int j = 0; j < i; j++) {
/* 20 */       EnumGamemode enumgamemode = aenumgamemode[j];
/*    */       
/* 22 */       if (enumgamemode != EnumGamemode.NOT_SET) {
/* 23 */         literalargumentbuilder.then(((LiteralArgumentBuilder)CommandDispatcher.a(enumgamemode.b()).executes(commandcontext -> a(commandcontext, Collections.singleton(((CommandListenerWrapper)commandcontext.getSource()).h()), enumgamemode)))
/*    */             
/* 25 */             .then(CommandDispatcher.<T>a("target", ArgumentEntity.d()).executes(commandcontext -> a(commandcontext, ArgumentEntity.f(commandcontext, "target"), enumgamemode))));
/*    */       }
/*    */     } 
/*    */ 
/*    */ 
/*    */     
/* 31 */     com_mojang_brigadier_commanddispatcher.register(literalargumentbuilder);
/*    */   }
/*    */   
/*    */   private static void a(CommandListenerWrapper commandlistenerwrapper, EntityPlayer entityplayer, EnumGamemode enumgamemode) {
/* 35 */     ChatMessage chatmessage = new ChatMessage("gameMode." + enumgamemode.b());
/*    */     
/* 37 */     if (commandlistenerwrapper.getEntity() == entityplayer) {
/* 38 */       commandlistenerwrapper.sendMessage(new ChatMessage("commands.gamemode.success.self", new Object[] { chatmessage }), true);
/*    */     } else {
/* 40 */       if (commandlistenerwrapper.getWorld().getGameRules().getBoolean(GameRules.SEND_COMMAND_FEEDBACK)) {
/* 41 */         entityplayer.sendMessage(new ChatMessage("gameMode.changed", new Object[] { chatmessage }), SystemUtils.b);
/*    */       }
/*    */       
/* 44 */       commandlistenerwrapper.sendMessage(new ChatMessage("commands.gamemode.success.other", new Object[] { entityplayer.getScoreboardDisplayName(), chatmessage }), true);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   private static int a(CommandContext<CommandListenerWrapper> commandcontext, Collection<EntityPlayer> collection, EnumGamemode enumgamemode) {
/* 50 */     int i = 0;
/* 51 */     Iterator<EntityPlayer> iterator = collection.iterator();
/*    */     
/* 53 */     while (iterator.hasNext()) {
/* 54 */       EntityPlayer entityplayer = iterator.next();
/*    */       
/* 56 */       if (entityplayer.playerInteractManager.getGameMode() != enumgamemode) {
/* 57 */         entityplayer.a(enumgamemode);
/*    */         
/* 59 */         if (entityplayer.playerInteractManager.getGameMode() != enumgamemode) {
/* 60 */           ((CommandListenerWrapper)commandcontext.getSource()).sendFailureMessage(new ChatComponentText("Failed to set the gamemode of '" + entityplayer.getName() + "'"));
/*    */           
/*    */           continue;
/*    */         } 
/* 64 */         a((CommandListenerWrapper)commandcontext.getSource(), entityplayer, enumgamemode);
/* 65 */         i++;
/*    */       } 
/*    */     } 
/*    */     
/* 69 */     return i;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CommandGamemode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
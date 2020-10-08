/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.brigadier.CommandDispatcher;
/*    */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*    */ import com.mojang.brigadier.context.CommandContext;
/*    */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*    */ import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
/*    */ import com.mojang.brigadier.tree.CommandNode;
/*    */ import com.mojang.brigadier.tree.LiteralCommandNode;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CommandTeamMsg
/*    */ {
/* 25 */   private static final ChatModifier a = ChatModifier.a
/* 26 */     .setChatHoverable(new ChatHoverable((ChatHoverable.EnumHoverAction)ChatHoverable.EnumHoverAction.SHOW_TEXT, (T)new ChatMessage("chat.type.team.hover")))
/* 27 */     .setChatClickable(new ChatClickable(ChatClickable.EnumClickAction.SUGGEST_COMMAND, "/teammsg "));
/*    */   
/* 29 */   private static final SimpleCommandExceptionType b = new SimpleCommandExceptionType(new ChatMessage("commands.teammsg.failed.noteam"));
/*    */   
/*    */   public static void a(CommandDispatcher<CommandListenerWrapper> var0) {
/* 32 */     LiteralCommandNode<CommandListenerWrapper> var1 = var0.register(
/* 33 */         (LiteralArgumentBuilder)CommandDispatcher.a("teammsg")
/* 34 */         .then(
/* 35 */           CommandDispatcher.<T>a("message", ArgumentChat.a())
/* 36 */           .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), ArgumentChat.a(var0, "message")))));
/*    */ 
/*    */     
/* 39 */     var0.register((LiteralArgumentBuilder)CommandDispatcher.a("tm").redirect((CommandNode)var1));
/*    */   }
/*    */   
/*    */   private static int a(CommandListenerWrapper var0, IChatBaseComponent var1) throws CommandSyntaxException {
/* 43 */     Entity var2 = var0.g();
/* 44 */     ScoreboardTeam var3 = (ScoreboardTeam)var2.getScoreboardTeam();
/* 45 */     if (var3 == null) {
/* 46 */       throw b.create();
/*    */     }
/*    */     
/* 49 */     IChatBaseComponent var4 = var3.d().c(a);
/*    */     
/* 51 */     List<EntityPlayer> var5 = var0.getServer().getPlayerList().getPlayers();
/* 52 */     for (EntityPlayer var7 : var5) {
/* 53 */       if (var7 == var2) {
/* 54 */         var7.sendMessage(new ChatMessage("chat.type.team.sent", new Object[] { var4, var0.getScoreboardDisplayName(), var1 }), var2.getUniqueID()); continue;
/* 55 */       }  if (var7.getScoreboardTeam() == var3) {
/* 56 */         var7.sendMessage(new ChatMessage("chat.type.team.text", new Object[] { var4, var0.getScoreboardDisplayName(), var1 }), var2.getUniqueID());
/*    */       }
/*    */     } 
/*    */     
/* 60 */     return var5.size();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CommandTeamMsg.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
/*    */ package net.minecraft.server.v1_16_R2;
/*    */ import com.mojang.brigadier.CommandDispatcher;
/*    */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*    */ import com.mojang.brigadier.context.CommandContext;
/*    */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*    */ 
/*    */ public class CommandGamerule {
/*    */   public static void a(CommandDispatcher<CommandListenerWrapper> com_mojang_brigadier_commanddispatcher) {
/*  9 */     final LiteralArgumentBuilder<CommandListenerWrapper> literalargumentbuilder = (LiteralArgumentBuilder<CommandListenerWrapper>)CommandDispatcher.a("gamerule").requires(commandlistenerwrapper -> commandlistenerwrapper.hasPermission(2));
/*    */ 
/*    */ 
/*    */     
/* 13 */     GameRules.a(new GameRules.GameRuleVisitor()
/*    */         {
/*    */           public <T extends GameRules.GameRuleValue<T>> void a(GameRules.GameRuleKey<T> gamerules_gamerulekey, GameRules.GameRuleDefinition<T> gamerules_gameruledefinition) {
/* 16 */             literalargumentbuilder.then(((LiteralArgumentBuilder)CommandDispatcher.a(gamerules_gamerulekey.a()).executes(commandcontext -> CommandGamerule.b((CommandListenerWrapper)commandcontext.getSource(), gamerules_gamerulekey)))
/*    */                 
/* 18 */                 .then(gamerules_gameruledefinition.a("value").executes(commandcontext -> CommandGamerule.b(commandcontext, gamerules_gamerulekey))));
/*    */           }
/*    */         });
/*    */ 
/*    */     
/* 23 */     com_mojang_brigadier_commanddispatcher.register(literalargumentbuilder);
/*    */   }
/*    */   
/*    */   private static <T extends GameRules.GameRuleValue<T>> int b(CommandContext<CommandListenerWrapper> commandcontext, GameRules.GameRuleKey<T> gamerules_gamerulekey) {
/* 27 */     CommandListenerWrapper commandlistenerwrapper = (CommandListenerWrapper)commandcontext.getSource();
/* 28 */     T t0 = commandlistenerwrapper.getWorld().getGameRules().get(gamerules_gamerulekey);
/*    */     
/* 30 */     t0.b(commandcontext, "value");
/* 31 */     commandlistenerwrapper.sendMessage(new ChatMessage("commands.gamerule.set", new Object[] { gamerules_gamerulekey.a(), t0.toString() }), true);
/* 32 */     return t0.getIntValue();
/*    */   }
/*    */   
/*    */   private static <T extends GameRules.GameRuleValue<T>> int b(CommandListenerWrapper commandlistenerwrapper, GameRules.GameRuleKey<T> gamerules_gamerulekey) {
/* 36 */     T t0 = commandlistenerwrapper.getWorld().getGameRules().get(gamerules_gamerulekey);
/*    */     
/* 38 */     commandlistenerwrapper.sendMessage(new ChatMessage("commands.gamerule.query", new Object[] { gamerules_gamerulekey.a(), t0.toString() }), false);
/* 39 */     return t0.getIntValue();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CommandGamerule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
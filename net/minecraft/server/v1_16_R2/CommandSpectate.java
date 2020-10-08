/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.brigadier.CommandDispatcher;
/*    */ import com.mojang.brigadier.Message;
/*    */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*    */ import com.mojang.brigadier.builder.RequiredArgumentBuilder;
/*    */ import com.mojang.brigadier.context.CommandContext;
/*    */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*    */ import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
/*    */ import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CommandSpectate
/*    */ {
/*    */   private static final DynamicCommandExceptionType b;
/* 24 */   private static final SimpleCommandExceptionType a = new SimpleCommandExceptionType(new ChatMessage("commands.spectate.self")); static {
/* 25 */     b = new DynamicCommandExceptionType(var0 -> new ChatMessage("commands.spectate.not_spectator", new Object[] { var0 }));
/*    */   }
/*    */   public static void a(CommandDispatcher<CommandListenerWrapper> var0) {
/* 28 */     var0.register(
/* 29 */         (LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)CommandDispatcher.a("spectate")
/* 30 */         .requires(var0 -> var0.hasPermission(2)))
/* 31 */         .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), null, ((CommandListenerWrapper)var0.getSource()).h())))
/* 32 */         .then((
/* 33 */           (RequiredArgumentBuilder)CommandDispatcher.<T>a("target", ArgumentEntity.a())
/* 34 */           .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), ArgumentEntity.a(var0, "target"), ((CommandListenerWrapper)var0.getSource()).h())))
/* 35 */           .then(
/* 36 */             CommandDispatcher.<T>a("player", ArgumentEntity.c())
/* 37 */             .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), ArgumentEntity.a(var0, "target"), ArgumentEntity.e(var0, "player"))))));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private static int a(CommandListenerWrapper var0, @Nullable Entity var1, EntityPlayer var2) throws CommandSyntaxException {
/* 44 */     if (var2 == var1)
/* 45 */       throw a.create(); 
/* 46 */     if (var2.playerInteractManager.getGameMode() != EnumGamemode.SPECTATOR) {
/* 47 */       throw b.create(var2.getScoreboardDisplayName());
/*    */     }
/*    */     
/* 50 */     var2.setSpectatorTarget(var1);
/* 51 */     if (var1 != null) {
/* 52 */       var0.sendMessage(new ChatMessage("commands.spectate.success.started", new Object[] { var1.getScoreboardDisplayName() }), false);
/*    */     } else {
/* 54 */       var0.sendMessage(new ChatMessage("commands.spectate.success.stopped"), false);
/*    */     } 
/* 56 */     return 1;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CommandSpectate.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.authlib.GameProfile;
/*    */ import com.mojang.brigadier.CommandDispatcher;
/*    */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*    */ import com.mojang.brigadier.context.CommandContext;
/*    */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*    */ import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
/*    */ import com.mojang.brigadier.suggestion.SuggestionsBuilder;
/*    */ import java.util.Collection;
/*    */ import java.util.concurrent.CompletableFuture;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CommandDeop
/*    */ {
/* 21 */   private static final SimpleCommandExceptionType a = new SimpleCommandExceptionType(new ChatMessage("commands.deop.failed"));
/*    */   
/*    */   public static void a(CommandDispatcher<CommandListenerWrapper> var0) {
/* 24 */     var0.register(
/* 25 */         (LiteralArgumentBuilder)((LiteralArgumentBuilder)CommandDispatcher.a("deop")
/* 26 */         .requires(var0 -> var0.hasPermission(3)))
/* 27 */         .then(
/* 28 */           CommandDispatcher.<T>a("targets", ArgumentProfile.a())
/* 29 */           .suggests((var0, var1) -> ICompletionProvider.a(((CommandListenerWrapper)var0.getSource()).getServer().getPlayerList().l(), var1))
/* 30 */           .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), ArgumentProfile.a(var0, "targets")))));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   private static int a(CommandListenerWrapper var0, Collection<GameProfile> var1) throws CommandSyntaxException {
/* 36 */     PlayerList var2 = var0.getServer().getPlayerList();
/* 37 */     int var3 = 0;
/*    */     
/* 39 */     for (GameProfile var5 : var1) {
/* 40 */       if (var2.isOp(var5)) {
/* 41 */         var2.removeOp(var5);
/* 42 */         var3++;
/* 43 */         var0.sendMessage(new ChatMessage("commands.deop.success", new Object[] { ((GameProfile)var1.iterator().next()).getName() }), true);
/*    */       } 
/*    */     } 
/*    */     
/* 47 */     if (var3 == 0) {
/* 48 */       throw a.create();
/*    */     }
/*    */     
/* 51 */     var0.getServer().a(var0);
/* 52 */     return var3;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CommandDeop.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
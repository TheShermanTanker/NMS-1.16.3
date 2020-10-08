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
/*    */ public class CommandOp
/*    */ {
/* 21 */   private static final SimpleCommandExceptionType a = new SimpleCommandExceptionType(new ChatMessage("commands.op.failed"));
/*    */   
/*    */   public static void a(CommandDispatcher<CommandListenerWrapper> var0) {
/* 24 */     var0.register(
/* 25 */         (LiteralArgumentBuilder)((LiteralArgumentBuilder)CommandDispatcher.a("op")
/* 26 */         .requires(var0 -> var0.hasPermission(3)))
/* 27 */         .then(
/* 28 */           CommandDispatcher.<T>a("targets", ArgumentProfile.a())
/* 29 */           .suggests((var0, var1) -> {
/*    */               PlayerList var2 = ((CommandListenerWrapper)var0.getSource()).getServer().getPlayerList();
/*    */               
/*    */               return ICompletionProvider.b(var2.getPlayers().stream().filter(()).map(()), var1);
/* 33 */             }).executes(var0 -> a((CommandListenerWrapper)var0.getSource(), ArgumentProfile.a(var0, "targets")))));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   private static int a(CommandListenerWrapper var0, Collection<GameProfile> var1) throws CommandSyntaxException {
/* 39 */     PlayerList var2 = var0.getServer().getPlayerList();
/* 40 */     int var3 = 0;
/*    */     
/* 42 */     for (GameProfile var5 : var1) {
/* 43 */       if (!var2.isOp(var5)) {
/* 44 */         var2.addOp(var5);
/* 45 */         var3++;
/* 46 */         var0.sendMessage(new ChatMessage("commands.op.success", new Object[] { ((GameProfile)var1.iterator().next()).getName() }), true);
/*    */       } 
/*    */     } 
/*    */     
/* 50 */     if (var3 == 0) {
/* 51 */       throw a.create();
/*    */     }
/*    */     
/* 54 */     return var3;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CommandOp.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
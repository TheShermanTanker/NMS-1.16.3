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
/*    */ 
/*    */ public class CommandPardon
/*    */ {
/* 22 */   private static final SimpleCommandExceptionType a = new SimpleCommandExceptionType(new ChatMessage("commands.pardon.failed"));
/*    */   
/*    */   public static void a(CommandDispatcher<CommandListenerWrapper> var0) {
/* 25 */     var0.register(
/* 26 */         (LiteralArgumentBuilder)((LiteralArgumentBuilder)CommandDispatcher.a("pardon")
/* 27 */         .requires(var0 -> var0.hasPermission(3)))
/* 28 */         .then(
/* 29 */           CommandDispatcher.<T>a("targets", ArgumentProfile.a())
/* 30 */           .suggests((var0, var1) -> ICompletionProvider.a(((CommandListenerWrapper)var0.getSource()).getServer().getPlayerList().getProfileBans().getEntries(), var1))
/* 31 */           .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), ArgumentProfile.a(var0, "targets")))));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   private static int a(CommandListenerWrapper var0, Collection<GameProfile> var1) throws CommandSyntaxException {
/* 37 */     GameProfileBanList var2 = var0.getServer().getPlayerList().getProfileBans();
/* 38 */     int var3 = 0;
/*    */     
/* 40 */     for (GameProfile var5 : var1) {
/* 41 */       if (var2.isBanned(var5)) {
/* 42 */         var2.remove(var5);
/* 43 */         var3++;
/* 44 */         var0.sendMessage(new ChatMessage("commands.pardon.success", new Object[] { ChatComponentUtils.a(var5) }), true);
/*    */       } 
/*    */     } 
/*    */     
/* 48 */     if (var3 == 0) {
/* 49 */       throw a.create();
/*    */     }
/*    */     
/* 52 */     return var3;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CommandPardon.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
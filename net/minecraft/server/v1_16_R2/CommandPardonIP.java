/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.brigadier.CommandDispatcher;
/*    */ import com.mojang.brigadier.arguments.ArgumentType;
/*    */ import com.mojang.brigadier.arguments.StringArgumentType;
/*    */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*    */ import com.mojang.brigadier.context.CommandContext;
/*    */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*    */ import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
/*    */ import com.mojang.brigadier.suggestion.SuggestionsBuilder;
/*    */ import java.util.concurrent.CompletableFuture;
/*    */ import java.util.regex.Matcher;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CommandPardonIP
/*    */ {
/* 20 */   private static final SimpleCommandExceptionType a = new SimpleCommandExceptionType(new ChatMessage("commands.pardonip.invalid"));
/* 21 */   private static final SimpleCommandExceptionType b = new SimpleCommandExceptionType(new ChatMessage("commands.pardonip.failed"));
/*    */   
/*    */   public static void a(CommandDispatcher<CommandListenerWrapper> var0) {
/* 24 */     var0.register(
/* 25 */         (LiteralArgumentBuilder)((LiteralArgumentBuilder)CommandDispatcher.a("pardon-ip")
/* 26 */         .requires(var0 -> var0.hasPermission(3)))
/* 27 */         .then(
/* 28 */           CommandDispatcher.<T>a("target", (ArgumentType<T>)StringArgumentType.word())
/* 29 */           .suggests((var0, var1) -> ICompletionProvider.a(((CommandListenerWrapper)var0.getSource()).getServer().getPlayerList().getIPBans().getEntries(), var1))
/* 30 */           .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), StringArgumentType.getString(var0, "target")))));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   private static int a(CommandListenerWrapper var0, String var1) throws CommandSyntaxException {
/* 36 */     Matcher var2 = CommandBanIp.a.matcher(var1);
/* 37 */     if (!var2.matches()) {
/* 38 */       throw a.create();
/*    */     }
/*    */     
/* 41 */     IpBanList var3 = var0.getServer().getPlayerList().getIPBans();
/* 42 */     if (!var3.a(var1)) {
/* 43 */       throw b.create();
/*    */     }
/*    */     
/* 46 */     var3.remove(var1);
/* 47 */     var0.sendMessage(new ChatMessage("commands.pardonip.success", new Object[] { var1 }), true);
/* 48 */     return 1;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CommandPardonIP.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
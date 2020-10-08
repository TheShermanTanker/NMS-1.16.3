/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.brigadier.CommandDispatcher;
/*    */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*    */ import com.mojang.brigadier.context.CommandContext;
/*    */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*    */ import com.mojang.brigadier.suggestion.SuggestionProvider;
/*    */ import com.mojang.brigadier.suggestion.SuggestionsBuilder;
/*    */ import java.util.Collection;
/*    */ import java.util.concurrent.CompletableFuture;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CommandFunction
/*    */ {
/*    */   public static final SuggestionProvider<CommandListenerWrapper> a;
/*    */   
/*    */   static {
/* 20 */     a = ((var0, var1) -> {
/*    */         CustomFunctionData var2 = ((CommandListenerWrapper)var0.getSource()).getServer().getFunctionData();
/*    */         ICompletionProvider.a(var2.g(), var1, "#");
/*    */         return ICompletionProvider.a(var2.f(), var1);
/*    */       });
/*    */   }
/*    */   public static void a(CommandDispatcher<CommandListenerWrapper> var0) {
/* 27 */     var0.register(
/* 28 */         (LiteralArgumentBuilder)((LiteralArgumentBuilder)CommandDispatcher.a("function")
/* 29 */         .requires(var0 -> var0.hasPermission(2)))
/* 30 */         .then(
/* 31 */           CommandDispatcher.<T>a("name", ArgumentTag.a())
/* 32 */           .suggests(a)
/* 33 */           .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), ArgumentTag.a(var0, "name")))));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   private static int a(CommandListenerWrapper var0, Collection<CustomFunction> var1) {
/* 39 */     int var2 = 0;
/*    */     
/* 41 */     for (CustomFunction var4 : var1) {
/* 42 */       var2 += var0.getServer().getFunctionData().a(var4, var0.a().b(2));
/*    */     }
/*    */     
/* 45 */     if (var1.size() == 1) {
/* 46 */       var0.sendMessage(new ChatMessage("commands.function.success.single", new Object[] { Integer.valueOf(var2), ((CustomFunction)var1.iterator().next()).a() }), true);
/*    */     } else {
/* 48 */       var0.sendMessage(new ChatMessage("commands.function.success.multiple", new Object[] { Integer.valueOf(var2), Integer.valueOf(var1.size()) }), true);
/*    */     } 
/*    */     
/* 51 */     return var2;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CommandFunction.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
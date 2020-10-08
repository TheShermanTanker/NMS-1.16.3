/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.brigadier.StringReader;
/*    */ import com.mojang.brigadier.arguments.ArgumentType;
/*    */ import com.mojang.brigadier.context.CommandContext;
/*    */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*    */ import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
/*    */ import com.mojang.brigadier.suggestion.Suggestions;
/*    */ import com.mojang.brigadier.suggestion.SuggestionsBuilder;
/*    */ import java.util.Arrays;
/*    */ import java.util.Collection;
/*    */ import java.util.Collections;
/*    */ import java.util.concurrent.CompletableFuture;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ArgumentPosition
/*    */   implements ArgumentType<IVectorPosition>
/*    */ {
/* 22 */   private static final Collection<String> c = Arrays.asList(new String[] { "0 0 0", "~ ~ ~", "^ ^ ^", "^1 ^ ^-5", "~0.5 ~1 ~-5" });
/*    */   
/* 24 */   public static final SimpleCommandExceptionType a = new SimpleCommandExceptionType(new ChatMessage("argument.pos.unloaded"));
/* 25 */   public static final SimpleCommandExceptionType b = new SimpleCommandExceptionType(new ChatMessage("argument.pos.outofworld"));
/*    */   
/*    */   public static ArgumentPosition a() {
/* 28 */     return new ArgumentPosition();
/*    */   }
/*    */   
/*    */   public static BlockPosition a(CommandContext<CommandListenerWrapper> var0, String var1) throws CommandSyntaxException {
/* 32 */     BlockPosition var2 = ((IVectorPosition)var0.getArgument(var1, IVectorPosition.class)).c((CommandListenerWrapper)var0.getSource());
/* 33 */     if (!((CommandListenerWrapper)var0.getSource()).getWorld().isLoaded(var2)) {
/* 34 */       throw a.create();
/*    */     }
/* 36 */     ((CommandListenerWrapper)var0.getSource()).getWorld(); if (!WorldServer.isValidLocation(var2)) {
/* 37 */       throw b.create();
/*    */     }
/* 39 */     return var2;
/*    */   }
/*    */   
/*    */   public static BlockPosition b(CommandContext<CommandListenerWrapper> var0, String var1) throws CommandSyntaxException {
/* 43 */     return ((IVectorPosition)var0.getArgument(var1, IVectorPosition.class)).c((CommandListenerWrapper)var0.getSource());
/*    */   }
/*    */ 
/*    */   
/*    */   public IVectorPosition parse(StringReader var0) throws CommandSyntaxException {
/* 48 */     if (var0.canRead() && var0.peek() == '^') {
/* 49 */       return ArgumentVectorPosition.a(var0);
/*    */     }
/* 51 */     return VectorPosition.a(var0);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> var0, SuggestionsBuilder var1) {
/* 57 */     if (var0.getSource() instanceof ICompletionProvider) {
/* 58 */       Collection<ICompletionProvider.a> var3; String var2 = var1.getRemaining();
/*    */ 
/*    */ 
/*    */       
/* 62 */       if (!var2.isEmpty() && var2.charAt(0) == '^') {
/* 63 */         var3 = Collections.singleton(ICompletionProvider.a.a);
/*    */       } else {
/* 65 */         var3 = ((ICompletionProvider)var0.getSource()).s();
/*    */       } 
/*    */       
/* 68 */       return ICompletionProvider.a(var2, var3, var1, CommandDispatcher.a(this::parse));
/*    */     } 
/* 70 */     return Suggestions.empty();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Collection<String> getExamples() {
/* 76 */     return c;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ArgumentPosition.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
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
/*    */ 
/*    */ public class ArgumentVec2I
/*    */   implements ArgumentType<IVectorPosition>
/*    */ {
/* 23 */   private static final Collection<String> b = Arrays.asList(new String[] { "0 0", "~ ~", "~1 ~-2", "^ ^", "^-1 ^0" });
/* 24 */   public static final SimpleCommandExceptionType a = new SimpleCommandExceptionType(new ChatMessage("argument.pos2d.incomplete"));
/*    */   
/*    */   public static ArgumentVec2I a() {
/* 27 */     return new ArgumentVec2I();
/*    */   }
/*    */   
/*    */   public static BlockPosition2D a(CommandContext<CommandListenerWrapper> var0, String var1) {
/* 31 */     BlockPosition var2 = ((IVectorPosition)var0.getArgument(var1, IVectorPosition.class)).c((CommandListenerWrapper)var0.getSource());
/* 32 */     return new BlockPosition2D(var2.getX(), var2.getZ());
/*    */   }
/*    */ 
/*    */   
/*    */   public IVectorPosition parse(StringReader var0) throws CommandSyntaxException {
/* 37 */     int var1 = var0.getCursor();
/* 38 */     if (!var0.canRead()) {
/* 39 */       throw a.createWithContext(var0);
/*    */     }
/* 41 */     ArgumentParserPosition var2 = ArgumentParserPosition.a(var0);
/* 42 */     if (!var0.canRead() || var0.peek() != ' ') {
/* 43 */       var0.setCursor(var1);
/* 44 */       throw a.createWithContext(var0);
/*    */     } 
/* 46 */     var0.skip();
/* 47 */     ArgumentParserPosition var3 = ArgumentParserPosition.a(var0);
/* 48 */     return new VectorPosition(var2, new ArgumentParserPosition(true, 0.0D), var3);
/*    */   }
/*    */ 
/*    */   
/*    */   public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> var0, SuggestionsBuilder var1) {
/* 53 */     if (var0.getSource() instanceof ICompletionProvider) {
/* 54 */       Collection<ICompletionProvider.a> var3; String var2 = var1.getRemaining();
/*    */ 
/*    */ 
/*    */       
/* 58 */       if (!var2.isEmpty() && var2.charAt(0) == '^') {
/* 59 */         var3 = Collections.singleton(ICompletionProvider.a.a);
/*    */       } else {
/* 61 */         var3 = ((ICompletionProvider)var0.getSource()).s();
/*    */       } 
/*    */       
/* 64 */       return ICompletionProvider.b(var2, var3, var1, CommandDispatcher.a(this::parse));
/*    */     } 
/* 66 */     return Suggestions.empty();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Collection<String> getExamples() {
/* 72 */     return b;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ArgumentVec2I.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
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
/*    */ public class ArgumentVec2
/*    */   implements ArgumentType<IVectorPosition>
/*    */ {
/* 23 */   private static final Collection<String> b = Arrays.asList(new String[] { "0 0", "~ ~", "0.1 -0.5", "~1 ~-2" });
/* 24 */   public static final SimpleCommandExceptionType a = new SimpleCommandExceptionType(new ChatMessage("argument.pos2d.incomplete"));
/*    */   
/*    */   private final boolean c;
/*    */   
/*    */   public ArgumentVec2(boolean var0) {
/* 29 */     this.c = var0;
/*    */   }
/*    */   
/*    */   public static ArgumentVec2 a() {
/* 33 */     return new ArgumentVec2(true);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static Vec2F a(CommandContext<CommandListenerWrapper> var0, String var1) throws CommandSyntaxException {
/* 42 */     Vec3D var2 = ((IVectorPosition)var0.getArgument(var1, IVectorPosition.class)).a((CommandListenerWrapper)var0.getSource());
/* 43 */     return new Vec2F((float)var2.x, (float)var2.z);
/*    */   }
/*    */ 
/*    */   
/*    */   public IVectorPosition parse(StringReader var0) throws CommandSyntaxException {
/* 48 */     int var1 = var0.getCursor();
/* 49 */     if (!var0.canRead()) {
/* 50 */       throw a.createWithContext(var0);
/*    */     }
/* 52 */     ArgumentParserPosition var2 = ArgumentParserPosition.a(var0, this.c);
/* 53 */     if (!var0.canRead() || var0.peek() != ' ') {
/* 54 */       var0.setCursor(var1);
/* 55 */       throw a.createWithContext(var0);
/*    */     } 
/* 57 */     var0.skip();
/* 58 */     ArgumentParserPosition var3 = ArgumentParserPosition.a(var0, this.c);
/* 59 */     return new VectorPosition(var2, new ArgumentParserPosition(true, 0.0D), var3);
/*    */   }
/*    */ 
/*    */   
/*    */   public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> var0, SuggestionsBuilder var1) {
/* 64 */     if (var0.getSource() instanceof ICompletionProvider) {
/* 65 */       Collection<ICompletionProvider.a> var3; String var2 = var1.getRemaining();
/*    */ 
/*    */ 
/*    */       
/* 69 */       if (!var2.isEmpty() && var2.charAt(0) == '^') {
/* 70 */         var3 = Collections.singleton(ICompletionProvider.a.a);
/*    */       } else {
/* 72 */         var3 = ((ICompletionProvider)var0.getSource()).t();
/*    */       } 
/*    */       
/* 75 */       return ICompletionProvider.b(var2, var3, var1, CommandDispatcher.a(this::parse));
/*    */     } 
/* 77 */     return Suggestions.empty();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Collection<String> getExamples() {
/* 83 */     return b;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ArgumentVec2.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
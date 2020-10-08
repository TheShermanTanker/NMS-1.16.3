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
/*    */ public class ArgumentVec3
/*    */   implements ArgumentType<IVectorPosition>
/*    */ {
/* 22 */   private static final Collection<String> c = Arrays.asList(new String[] { "0 0 0", "~ ~ ~", "^ ^ ^", "^1 ^ ^-5", "0.1 -0.5 .9", "~0.5 ~1 ~-5" });
/*    */   
/* 24 */   public static final SimpleCommandExceptionType a = new SimpleCommandExceptionType(new ChatMessage("argument.pos3d.incomplete"));
/* 25 */   public static final SimpleCommandExceptionType b = new SimpleCommandExceptionType(new ChatMessage("argument.pos.mixed"));
/*    */   
/*    */   private final boolean d;
/*    */   
/*    */   public ArgumentVec3(boolean var0) {
/* 30 */     this.d = var0;
/*    */   }
/*    */   
/*    */   public static ArgumentVec3 a() {
/* 34 */     return new ArgumentVec3(true);
/*    */   }
/*    */   
/*    */   public static ArgumentVec3 a(boolean var0) {
/* 38 */     return new ArgumentVec3(var0);
/*    */   }
/*    */   
/*    */   public static Vec3D a(CommandContext<CommandListenerWrapper> var0, String var1) throws CommandSyntaxException {
/* 42 */     return ((IVectorPosition)var0.getArgument(var1, IVectorPosition.class)).a((CommandListenerWrapper)var0.getSource());
/*    */   }
/*    */   
/*    */   public static IVectorPosition b(CommandContext<CommandListenerWrapper> var0, String var1) {
/* 46 */     return (IVectorPosition)var0.getArgument(var1, IVectorPosition.class);
/*    */   }
/*    */ 
/*    */   
/*    */   public IVectorPosition parse(StringReader var0) throws CommandSyntaxException {
/* 51 */     if (var0.canRead() && var0.peek() == '^') {
/* 52 */       return ArgumentVectorPosition.a(var0);
/*    */     }
/* 54 */     return VectorPosition.a(var0, this.d);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> var0, SuggestionsBuilder var1) {
/* 60 */     if (var0.getSource() instanceof ICompletionProvider) {
/* 61 */       Collection<ICompletionProvider.a> var3; String var2 = var1.getRemaining();
/*    */ 
/*    */ 
/*    */       
/* 65 */       if (!var2.isEmpty() && var2.charAt(0) == '^') {
/* 66 */         var3 = Collections.singleton(ICompletionProvider.a.a);
/*    */       } else {
/* 68 */         var3 = ((ICompletionProvider)var0.getSource()).t();
/*    */       } 
/*    */       
/* 71 */       return ICompletionProvider.a(var2, var3, var1, CommandDispatcher.a(this::parse));
/*    */     } 
/* 73 */     return Suggestions.empty();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Collection<String> getExamples() {
/* 79 */     return c;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ArgumentVec3.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
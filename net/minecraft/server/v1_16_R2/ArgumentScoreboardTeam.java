/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.brigadier.Message;
/*    */ import com.mojang.brigadier.StringReader;
/*    */ import com.mojang.brigadier.arguments.ArgumentType;
/*    */ import com.mojang.brigadier.context.CommandContext;
/*    */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*    */ import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
/*    */ import com.mojang.brigadier.suggestion.Suggestions;
/*    */ import com.mojang.brigadier.suggestion.SuggestionsBuilder;
/*    */ import java.util.Arrays;
/*    */ import java.util.Collection;
/*    */ import java.util.concurrent.CompletableFuture;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ArgumentScoreboardTeam
/*    */   implements ArgumentType<String>
/*    */ {
/* 21 */   private static final Collection<String> a = Arrays.asList(new String[] { "foo", "123" }); static {
/* 22 */     b = new DynamicCommandExceptionType(var0 -> new ChatMessage("team.notFound", new Object[] { var0 }));
/*    */   }
/*    */   
/*    */   private static final DynamicCommandExceptionType b;
/*    */   
/*    */   public static ArgumentScoreboardTeam a() {
/* 28 */     return new ArgumentScoreboardTeam();
/*    */   }
/*    */   
/*    */   public static ScoreboardTeam a(CommandContext<CommandListenerWrapper> var0, String var1) throws CommandSyntaxException {
/* 32 */     String var2 = (String)var0.getArgument(var1, String.class);
/* 33 */     Scoreboard var3 = ((CommandListenerWrapper)var0.getSource()).getServer().getScoreboard();
/* 34 */     ScoreboardTeam var4 = var3.getTeam(var2);
/* 35 */     if (var4 == null) {
/* 36 */       throw b.create(var2);
/*    */     }
/* 38 */     return var4;
/*    */   }
/*    */ 
/*    */   
/*    */   public String parse(StringReader var0) throws CommandSyntaxException {
/* 43 */     return var0.readUnquotedString();
/*    */   }
/*    */ 
/*    */   
/*    */   public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> var0, SuggestionsBuilder var1) {
/* 48 */     if (var0.getSource() instanceof ICompletionProvider) {
/* 49 */       return ICompletionProvider.b(((ICompletionProvider)var0.getSource()).m(), var1);
/*    */     }
/* 51 */     return Suggestions.empty();
/*    */   }
/*    */ 
/*    */   
/*    */   public Collection<String> getExamples() {
/* 56 */     return a;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ArgumentScoreboardTeam.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
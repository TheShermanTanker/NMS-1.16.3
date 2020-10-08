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
/*    */ public class ArgumentScoreboardObjective
/*    */   implements ArgumentType<String>
/*    */ {
/*    */   private static final DynamicCommandExceptionType c;
/*    */   private static final DynamicCommandExceptionType d;
/*    */   public static final DynamicCommandExceptionType a;
/* 21 */   private static final Collection<String> b = Arrays.asList(new String[] { "foo", "*", "012" }); static {
/* 22 */     c = new DynamicCommandExceptionType(var0 -> new ChatMessage("arguments.objective.notFound", new Object[] { var0 }));
/* 23 */     d = new DynamicCommandExceptionType(var0 -> new ChatMessage("arguments.objective.readonly", new Object[] { var0 }));
/* 24 */     a = new DynamicCommandExceptionType(var0 -> new ChatMessage("commands.scoreboard.objectives.add.longName", new Object[] { var0 }));
/*    */   }
/*    */   public static ArgumentScoreboardObjective a() {
/* 27 */     return new ArgumentScoreboardObjective();
/*    */   }
/*    */   
/*    */   public static ScoreboardObjective a(CommandContext<CommandListenerWrapper> var0, String var1) throws CommandSyntaxException {
/* 31 */     String var2 = (String)var0.getArgument(var1, String.class);
/* 32 */     Scoreboard var3 = ((CommandListenerWrapper)var0.getSource()).getServer().getScoreboard();
/* 33 */     ScoreboardObjective var4 = var3.getObjective(var2);
/* 34 */     if (var4 == null) {
/* 35 */       throw c.create(var2);
/*    */     }
/* 37 */     return var4;
/*    */   }
/*    */   
/*    */   public static ScoreboardObjective b(CommandContext<CommandListenerWrapper> var0, String var1) throws CommandSyntaxException {
/* 41 */     ScoreboardObjective var2 = a(var0, var1);
/* 42 */     if (var2.getCriteria().isReadOnly()) {
/* 43 */       throw d.create(var2.getName());
/*    */     }
/* 45 */     return var2;
/*    */   }
/*    */ 
/*    */   
/*    */   public String parse(StringReader var0) throws CommandSyntaxException {
/* 50 */     String var1 = var0.readUnquotedString();
/* 51 */     if (var1.length() > 16) {
/* 52 */       throw a.create(Integer.valueOf(16));
/*    */     }
/* 54 */     return var1;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> var0, SuggestionsBuilder var1) {
/* 60 */     if (var0.getSource() instanceof CommandListenerWrapper)
/* 61 */       return ICompletionProvider.b(((CommandListenerWrapper)var0.getSource()).getServer().getScoreboard().d(), var1); 
/* 62 */     if (var0.getSource() instanceof ICompletionProvider) {
/* 63 */       ICompletionProvider var2 = (ICompletionProvider)var0.getSource();
/* 64 */       return var2.a((CommandContext)var0, var1);
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


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ArgumentScoreboardObjective.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
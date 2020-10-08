/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.Lists;
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
/*    */ import java.util.List;
/*    */ import java.util.concurrent.CompletableFuture;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ArgumentScoreboardCriteria
/*    */   implements ArgumentType<IScoreboardCriteria>
/*    */ {
/* 25 */   private static final Collection<String> b = Arrays.asList(new String[] { "foo", "foo.bar.baz", "minecraft:foo" }); static {
/* 26 */     a = new DynamicCommandExceptionType(var0 -> new ChatMessage("argument.criteria.invalid", new Object[] { var0 }));
/*    */   }
/*    */   
/*    */   public static final DynamicCommandExceptionType a;
/*    */   
/*    */   public static ArgumentScoreboardCriteria a() {
/* 32 */     return new ArgumentScoreboardCriteria();
/*    */   }
/*    */   
/*    */   public static IScoreboardCriteria a(CommandContext<CommandListenerWrapper> var0, String var1) {
/* 36 */     return (IScoreboardCriteria)var0.getArgument(var1, IScoreboardCriteria.class);
/*    */   }
/*    */ 
/*    */   
/*    */   public IScoreboardCriteria parse(StringReader var0) throws CommandSyntaxException {
/* 41 */     int var1 = var0.getCursor();
/* 42 */     while (var0.canRead() && var0.peek() != ' ') {
/* 43 */       var0.skip();
/*    */     }
/* 45 */     String var2 = var0.getString().substring(var1, var0.getCursor());
/* 46 */     return IScoreboardCriteria.a(var2).<Throwable>orElseThrow(() -> {
/*    */           var0.setCursor(var1);
/*    */           return a.create(var2);
/*    */         });
/*    */   }
/*    */ 
/*    */   
/*    */   public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> var0, SuggestionsBuilder var1) {
/* 54 */     List<String> var2 = Lists.newArrayList(IScoreboardCriteria.criteria.keySet());
/* 55 */     for (StatisticWrapper<?> var4 : IRegistry.STATS) {
/* 56 */       for (Object var6 : var4.getRegistry()) {
/* 57 */         String var7 = a(var4, var6);
/* 58 */         var2.add(var7);
/*    */       } 
/*    */     } 
/* 61 */     return ICompletionProvider.b(var2, var1);
/*    */   }
/*    */ 
/*    */   
/*    */   public <T> String a(StatisticWrapper<T> var0, Object var1) {
/* 66 */     return Statistic.a(var0, (T)var1);
/*    */   }
/*    */ 
/*    */   
/*    */   public Collection<String> getExamples() {
/* 71 */     return b;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ArgumentScoreboardCriteria.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
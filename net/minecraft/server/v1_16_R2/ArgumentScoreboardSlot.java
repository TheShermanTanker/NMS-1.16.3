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
/*    */ public class ArgumentScoreboardSlot
/*    */   implements ArgumentType<Integer>
/*    */ {
/* 20 */   private static final Collection<String> b = Arrays.asList(new String[] { "sidebar", "foo.bar" }); static {
/* 21 */     a = new DynamicCommandExceptionType(var0 -> new ChatMessage("argument.scoreboardDisplaySlot.invalid", new Object[] { var0 }));
/*    */   }
/*    */   
/*    */   public static final DynamicCommandExceptionType a;
/*    */   
/*    */   public static ArgumentScoreboardSlot a() {
/* 27 */     return new ArgumentScoreboardSlot();
/*    */   }
/*    */   
/*    */   public static int a(CommandContext<CommandListenerWrapper> var0, String var1) {
/* 31 */     return ((Integer)var0.getArgument(var1, Integer.class)).intValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public Integer parse(StringReader var0) throws CommandSyntaxException {
/* 36 */     String var1 = var0.readUnquotedString();
/* 37 */     int var2 = Scoreboard.getSlotForName(var1);
/* 38 */     if (var2 == -1) {
/* 39 */       throw a.create(var1);
/*    */     }
/* 41 */     return Integer.valueOf(var2);
/*    */   }
/*    */ 
/*    */   
/*    */   public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> var0, SuggestionsBuilder var1) {
/* 46 */     return ICompletionProvider.a(Scoreboard.h(), var1);
/*    */   }
/*    */ 
/*    */   
/*    */   public Collection<String> getExamples() {
/* 51 */     return b;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ArgumentScoreboardSlot.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
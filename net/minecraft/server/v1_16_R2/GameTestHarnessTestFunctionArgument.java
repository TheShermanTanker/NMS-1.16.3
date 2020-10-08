/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.brigadier.Message;
/*    */ import com.mojang.brigadier.StringReader;
/*    */ import com.mojang.brigadier.arguments.ArgumentType;
/*    */ import com.mojang.brigadier.context.CommandContext;
/*    */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*    */ import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
/*    */ import com.mojang.brigadier.suggestion.Suggestions;
/*    */ import com.mojang.brigadier.suggestion.SuggestionsBuilder;
/*    */ import java.util.Arrays;
/*    */ import java.util.Collection;
/*    */ import java.util.Optional;
/*    */ import java.util.concurrent.CompletableFuture;
/*    */ import java.util.stream.Stream;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GameTestHarnessTestFunctionArgument
/*    */   implements ArgumentType<GameTestHarnessTestFunction>
/*    */ {
/* 22 */   private static final Collection<String> a = Arrays.asList(new String[] { "techtests.piston", "techtests" });
/*    */ 
/*    */   
/*    */   public GameTestHarnessTestFunction parse(StringReader var0) throws CommandSyntaxException {
/* 26 */     String var1 = var0.readUnquotedString();
/* 27 */     Optional<GameTestHarnessTestFunction> var2 = GameTestHarnessRegistry.d(var1);
/* 28 */     if (var2.isPresent()) {
/* 29 */       return var2.get();
/*    */     }
/* 31 */     Message var3 = new ChatComponentText("No such test: " + var1);
/* 32 */     throw new CommandSyntaxException(new SimpleCommandExceptionType(var3), var3);
/*    */   }
/*    */ 
/*    */   
/*    */   public static GameTestHarnessTestFunctionArgument a() {
/* 37 */     return new GameTestHarnessTestFunctionArgument();
/*    */   }
/*    */   
/*    */   public static GameTestHarnessTestFunction a(CommandContext<CommandListenerWrapper> var0, String var1) {
/* 41 */     return (GameTestHarnessTestFunction)var0.getArgument(var1, GameTestHarnessTestFunction.class);
/*    */   }
/*    */ 
/*    */   
/*    */   public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> var0, SuggestionsBuilder var1) {
/* 46 */     Stream<String> var2 = GameTestHarnessRegistry.a().stream().map(GameTestHarnessTestFunction::a);
/* 47 */     return ICompletionProvider.b(var2, var1);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Collection<String> getExamples() {
/* 53 */     return a;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\GameTestHarnessTestFunctionArgument.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
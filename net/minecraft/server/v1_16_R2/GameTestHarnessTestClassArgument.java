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
/*    */ import java.util.concurrent.CompletableFuture;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GameTestHarnessTestClassArgument
/*    */   implements ArgumentType<String>
/*    */ {
/* 20 */   private static final Collection<String> a = Arrays.asList(new String[] { "techtests", "mobtests" });
/*    */ 
/*    */   
/*    */   public String parse(StringReader var0) throws CommandSyntaxException {
/* 24 */     String var1 = var0.readUnquotedString();
/* 25 */     if (GameTestHarnessRegistry.b(var1)) {
/* 26 */       return var1;
/*    */     }
/* 28 */     Message var2 = new ChatComponentText("No such test class: " + var1);
/* 29 */     throw new CommandSyntaxException(new SimpleCommandExceptionType(var2), var2);
/*    */   }
/*    */ 
/*    */   
/*    */   public static GameTestHarnessTestClassArgument a() {
/* 34 */     return new GameTestHarnessTestClassArgument();
/*    */   }
/*    */   
/*    */   public static String a(CommandContext<CommandListenerWrapper> var0, String var1) {
/* 38 */     return (String)var0.getArgument(var1, String.class);
/*    */   }
/*    */ 
/*    */   
/*    */   public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> var0, SuggestionsBuilder var1) {
/* 43 */     return ICompletionProvider.b(GameTestHarnessRegistry.b().stream(), var1);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Collection<String> getExamples() {
/* 49 */     return a;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\GameTestHarnessTestClassArgument.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
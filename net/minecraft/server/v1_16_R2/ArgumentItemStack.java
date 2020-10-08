/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.brigadier.StringReader;
/*    */ import com.mojang.brigadier.arguments.ArgumentType;
/*    */ import com.mojang.brigadier.context.CommandContext;
/*    */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*    */ import com.mojang.brigadier.suggestion.Suggestions;
/*    */ import com.mojang.brigadier.suggestion.SuggestionsBuilder;
/*    */ import java.util.Arrays;
/*    */ import java.util.Collection;
/*    */ import java.util.concurrent.CompletableFuture;
/*    */ 
/*    */ public class ArgumentItemStack
/*    */   implements ArgumentType<ArgumentPredicateItemStack>
/*    */ {
/* 16 */   private static final Collection<String> a = Arrays.asList(new String[] { "stick", "minecraft:stick", "stick{foo=bar}" });
/*    */   
/*    */   public static ArgumentItemStack a() {
/* 19 */     return new ArgumentItemStack();
/*    */   }
/*    */ 
/*    */   
/*    */   public ArgumentPredicateItemStack parse(StringReader var0) throws CommandSyntaxException {
/* 24 */     ArgumentParserItemStack var1 = (new ArgumentParserItemStack(var0, false)).h();
/*    */     
/* 26 */     return new ArgumentPredicateItemStack(var1.b(), var1.c());
/*    */   }
/*    */   
/*    */   public static <S> ArgumentPredicateItemStack a(CommandContext<S> var0, String var1) {
/* 30 */     return (ArgumentPredicateItemStack)var0.getArgument(var1, ArgumentPredicateItemStack.class);
/*    */   }
/*    */ 
/*    */   
/*    */   public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> var0, SuggestionsBuilder var1) {
/* 35 */     StringReader var2 = new StringReader(var1.getInput());
/* 36 */     var2.setCursor(var1.getStart());
/* 37 */     ArgumentParserItemStack var3 = new ArgumentParserItemStack(var2, false);
/*    */     try {
/* 39 */       var3.h();
/* 40 */     } catch (CommandSyntaxException commandSyntaxException) {}
/*    */     
/* 42 */     return var3.a(var1, TagsItem.a());
/*    */   }
/*    */ 
/*    */   
/*    */   public Collection<String> getExamples() {
/* 47 */     return a;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ArgumentItemStack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
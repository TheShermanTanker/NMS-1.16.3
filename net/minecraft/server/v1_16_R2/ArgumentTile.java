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
/*    */ 
/*    */ public class ArgumentTile
/*    */   implements ArgumentType<ArgumentTileLocation>
/*    */ {
/* 17 */   private static final Collection<String> a = Arrays.asList(new String[] { "stone", "minecraft:stone", "stone[foo=bar]", "foo{bar=baz}" });
/*    */   
/*    */   public static ArgumentTile a() {
/* 20 */     return new ArgumentTile();
/*    */   }
/*    */ 
/*    */   
/*    */   public ArgumentTileLocation parse(StringReader var0) throws CommandSyntaxException {
/* 25 */     ArgumentBlock var1 = (new ArgumentBlock(var0, false)).a(true);
/* 26 */     return new ArgumentTileLocation(var1.getBlockData(), var1.getStateMap().keySet(), var1.c());
/*    */   }
/*    */   
/*    */   public static ArgumentTileLocation a(CommandContext<CommandListenerWrapper> var0, String var1) {
/* 30 */     return (ArgumentTileLocation)var0.getArgument(var1, ArgumentTileLocation.class);
/*    */   }
/*    */ 
/*    */   
/*    */   public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> var0, SuggestionsBuilder var1) {
/* 35 */     StringReader var2 = new StringReader(var1.getInput());
/* 36 */     var2.setCursor(var1.getStart());
/* 37 */     ArgumentBlock var3 = new ArgumentBlock(var2, false);
/*    */     try {
/* 39 */       var3.a(true);
/* 40 */     } catch (CommandSyntaxException commandSyntaxException) {}
/*    */     
/* 42 */     return var3.a(var1, TagsBlock.a());
/*    */   }
/*    */ 
/*    */   
/*    */   public Collection<String> getExamples() {
/* 47 */     return a;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ArgumentTile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
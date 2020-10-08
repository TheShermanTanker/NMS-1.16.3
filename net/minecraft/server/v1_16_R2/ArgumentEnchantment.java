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
/*    */ public class ArgumentEnchantment
/*    */   implements ArgumentType<Enchantment>
/*    */ {
/*    */   public static final DynamicCommandExceptionType a;
/* 22 */   private static final Collection<String> b = Arrays.asList(new String[] { "unbreaking", "silk_touch" });
/*    */   static {
/* 24 */     a = new DynamicCommandExceptionType(var0 -> new ChatMessage("enchantment.unknown", new Object[] { var0 }));
/*    */   }
/*    */   public static ArgumentEnchantment a() {
/* 27 */     return new ArgumentEnchantment();
/*    */   }
/*    */   
/*    */   public static Enchantment a(CommandContext<CommandListenerWrapper> var0, String var1) {
/* 31 */     return (Enchantment)var0.getArgument(var1, Enchantment.class);
/*    */   }
/*    */ 
/*    */   
/*    */   public Enchantment parse(StringReader var0) throws CommandSyntaxException {
/* 36 */     MinecraftKey var1 = MinecraftKey.a(var0);
/* 37 */     return (Enchantment)IRegistry.ENCHANTMENT.getOptional(var1).orElseThrow(() -> a.create(var0));
/*    */   }
/*    */ 
/*    */   
/*    */   public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> var0, SuggestionsBuilder var1) {
/* 42 */     return ICompletionProvider.a(IRegistry.ENCHANTMENT.keySet(), var1);
/*    */   }
/*    */ 
/*    */   
/*    */   public Collection<String> getExamples() {
/* 47 */     return b;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ArgumentEnchantment.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
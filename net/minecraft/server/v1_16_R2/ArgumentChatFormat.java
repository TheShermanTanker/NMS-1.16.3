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
/*    */ public class ArgumentChatFormat
/*    */   implements ArgumentType<EnumChatFormat>
/*    */ {
/* 20 */   private static final Collection<String> b = Arrays.asList(new String[] { "red", "green" }); static {
/* 21 */     a = new DynamicCommandExceptionType(var0 -> new ChatMessage("argument.color.invalid", new Object[] { var0 }));
/*    */   }
/*    */   
/*    */   public static final DynamicCommandExceptionType a;
/*    */   
/*    */   public static ArgumentChatFormat a() {
/* 27 */     return new ArgumentChatFormat();
/*    */   }
/*    */   
/*    */   public static EnumChatFormat a(CommandContext<CommandListenerWrapper> var0, String var1) {
/* 31 */     return (EnumChatFormat)var0.getArgument(var1, EnumChatFormat.class);
/*    */   }
/*    */ 
/*    */   
/*    */   public EnumChatFormat parse(StringReader var0) throws CommandSyntaxException {
/* 36 */     String var1 = var0.readUnquotedString();
/* 37 */     EnumChatFormat var2 = EnumChatFormat.b(var1);
/* 38 */     if (var2 == null || var2.isFormat()) {
/* 39 */       throw a.create(var1);
/*    */     }
/* 41 */     return var2;
/*    */   }
/*    */ 
/*    */   
/*    */   public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> var0, SuggestionsBuilder var1) {
/* 46 */     return ICompletionProvider.b(EnumChatFormat.a(true, false), var1);
/*    */   }
/*    */ 
/*    */   
/*    */   public Collection<String> getExamples() {
/* 51 */     return b;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ArgumentChatFormat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
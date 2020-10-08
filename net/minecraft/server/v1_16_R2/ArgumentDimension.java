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
/*    */ import java.util.Collection;
/*    */ import java.util.concurrent.CompletableFuture;
/*    */ import java.util.stream.Collectors;
/*    */ import java.util.stream.Stream;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ArgumentDimension
/*    */   implements ArgumentType<MinecraftKey>
/*    */ {
/*    */   private static final Collection<String> a;
/*    */   private static final DynamicCommandExceptionType b;
/*    */   
/*    */   static {
/* 25 */     a = (Collection<String>)Stream.<ResourceKey>of(new ResourceKey[] { World.OVERWORLD, World.THE_NETHER }).map(var0 -> var0.a().toString()).collect(Collectors.toList());
/*    */     
/* 27 */     b = new DynamicCommandExceptionType(var0 -> new ChatMessage("argument.dimension.invalid", new Object[] { var0 }));
/*    */   }
/*    */   
/*    */   public MinecraftKey parse(StringReader var0) throws CommandSyntaxException {
/* 31 */     return MinecraftKey.a(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> var0, SuggestionsBuilder var1) {
/* 36 */     if (var0.getSource() instanceof ICompletionProvider) {
/* 37 */       return ICompletionProvider.a(((ICompletionProvider)var0.getSource()).p().stream().map(ResourceKey::a), var1);
/*    */     }
/* 39 */     return Suggestions.empty();
/*    */   }
/*    */ 
/*    */   
/*    */   public Collection<String> getExamples() {
/* 44 */     return a;
/*    */   }
/*    */   
/*    */   public static ArgumentDimension a() {
/* 48 */     return new ArgumentDimension();
/*    */   }
/*    */   
/*    */   public static WorldServer a(CommandContext<CommandListenerWrapper> var0, String var1) throws CommandSyntaxException {
/* 52 */     MinecraftKey var2 = (MinecraftKey)var0.getArgument(var1, MinecraftKey.class);
/* 53 */     ResourceKey<World> var3 = ResourceKey.a(IRegistry.L, var2);
/* 54 */     WorldServer var4 = ((CommandListenerWrapper)var0.getSource()).getServer().getWorldServer(var3);
/* 55 */     if (var4 == null) {
/* 56 */       throw b.create(var2);
/*    */     }
/* 58 */     return var4;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ArgumentDimension.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
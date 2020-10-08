/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.Maps;
/*    */ import com.mojang.brigadier.Message;
/*    */ import com.mojang.brigadier.context.CommandContext;
/*    */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*    */ import com.mojang.brigadier.suggestion.SuggestionProvider;
/*    */ import com.mojang.brigadier.suggestion.Suggestions;
/*    */ import com.mojang.brigadier.suggestion.SuggestionsBuilder;
/*    */ import java.util.Map;
/*    */ import java.util.concurrent.CompletableFuture;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CompletionProviders
/*    */ {
/* 21 */   private static final Map<MinecraftKey, SuggestionProvider<ICompletionProvider>> f = Maps.newHashMap();
/* 22 */   private static final MinecraftKey g = new MinecraftKey("ask_server"); public static final SuggestionProvider<ICompletionProvider> a; public static final SuggestionProvider<CommandListenerWrapper> b;
/*    */   static {
/* 24 */     a = a(g, (var0, var1) -> ((ICompletionProvider)var0.getSource()).a(var0, var1));
/* 25 */     b = a(new MinecraftKey("all_recipes"), (var0, var1) -> ICompletionProvider.a(((ICompletionProvider)var0.getSource()).o(), var1));
/* 26 */     c = a(new MinecraftKey("available_sounds"), (var0, var1) -> ICompletionProvider.a(((ICompletionProvider)var0.getSource()).n(), var1));
/* 27 */     d = a(new MinecraftKey("available_biomes"), (var0, var1) -> ICompletionProvider.a(((ICompletionProvider)var0.getSource()).q().<BiomeBase>b(IRegistry.ay).keySet(), var1));
/* 28 */     e = a(new MinecraftKey("summonable_entities"), (var0, var1) -> ICompletionProvider.a(IRegistry.ENTITY_TYPE.g().filter(EntityTypes::b), var1, EntityTypes::getName, ()));
/*    */   }
/*    */   public static final SuggestionProvider<CommandListenerWrapper> c; public static final SuggestionProvider<CommandListenerWrapper> d; public static final SuggestionProvider<CommandListenerWrapper> e;
/*    */   public static <S extends ICompletionProvider> SuggestionProvider<S> a(MinecraftKey var0, SuggestionProvider<ICompletionProvider> var1) {
/* 32 */     if (f.containsKey(var0)) {
/* 33 */       throw new IllegalArgumentException("A command suggestion provider is already registered with the name " + var0);
/*    */     }
/* 35 */     f.put(var0, var1);
/* 36 */     return new a(var0, var1);
/*    */   }
/*    */   
/*    */   public static SuggestionProvider<ICompletionProvider> a(MinecraftKey var0) {
/* 40 */     return f.getOrDefault(var0, a);
/*    */   }
/*    */   
/*    */   public static MinecraftKey a(SuggestionProvider<ICompletionProvider> var0) {
/* 44 */     if (var0 instanceof a) {
/* 45 */       return a.a((a)var0);
/*    */     }
/* 47 */     return g;
/*    */   }
/*    */ 
/*    */   
/*    */   public static SuggestionProvider<ICompletionProvider> b(SuggestionProvider<ICompletionProvider> var0) {
/* 52 */     if (var0 instanceof a) {
/* 53 */       return var0;
/*    */     }
/* 55 */     return a;
/*    */   }
/*    */   
/*    */   public static class a
/*    */     implements SuggestionProvider<ICompletionProvider> {
/*    */     private final SuggestionProvider<ICompletionProvider> a;
/*    */     private final MinecraftKey b;
/*    */     
/*    */     public a(MinecraftKey var0, SuggestionProvider<ICompletionProvider> var1) {
/* 64 */       this.a = var1;
/* 65 */       this.b = var0;
/*    */     }
/*    */ 
/*    */     
/*    */     public CompletableFuture<Suggestions> getSuggestions(CommandContext<ICompletionProvider> var0, SuggestionsBuilder var1) throws CommandSyntaxException {
/* 70 */       return this.a.getSuggestions(var0, var1);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CompletionProviders.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.mojang.authlib.GameProfile;
/*     */ import com.mojang.brigadier.StringReader;
/*     */ import com.mojang.brigadier.arguments.ArgumentType;
/*     */ import com.mojang.brigadier.context.CommandContext;
/*     */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*     */ import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
/*     */ import com.mojang.brigadier.suggestion.Suggestions;
/*     */ import com.mojang.brigadier.suggestion.SuggestionsBuilder;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import java.util.concurrent.CompletableFuture;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ArgumentProfile
/*     */   implements ArgumentType<ArgumentProfile.a>
/*     */ {
/*  26 */   private static final Collection<String> b = Arrays.asList(new String[] { "Player", "0123", "dd12be42-52a9-4a91-a8a1-11c01849e498", "@e" });
/*  27 */   public static final SimpleCommandExceptionType a = new SimpleCommandExceptionType(new ChatMessage("argument.player.unknown"));
/*     */   
/*     */   public static Collection<GameProfile> a(CommandContext<CommandListenerWrapper> var0, String var1) throws CommandSyntaxException {
/*  30 */     return ((a)var0.getArgument(var1, a.class)).getNames((CommandListenerWrapper)var0.getSource());
/*     */   }
/*     */   
/*     */   public static ArgumentProfile a() {
/*  34 */     return new ArgumentProfile();
/*     */   }
/*     */ 
/*     */   
/*     */   public a parse(StringReader var0) throws CommandSyntaxException {
/*  39 */     if (var0.canRead() && var0.peek() == '@') {
/*  40 */       ArgumentParserSelector argumentParserSelector = new ArgumentParserSelector(var0);
/*  41 */       EntitySelector entitySelector = argumentParserSelector.parse();
/*  42 */       if (entitySelector.b()) {
/*  43 */         throw ArgumentEntity.c.create();
/*     */       }
/*  45 */       return new b(entitySelector);
/*     */     } 
/*     */     
/*  48 */     int var1 = var0.getCursor();
/*  49 */     while (var0.canRead() && var0.peek() != ' ') {
/*  50 */       var0.skip();
/*     */     }
/*  52 */     String var2 = var0.getString().substring(var1, var0.getCursor());
/*  53 */     return var1 -> {
/*     */         GameProfile var2 = var1.getServer().getUserCache().getProfile(var0);
/*     */         if (var2 == null) {
/*     */           throw a.create();
/*     */         }
/*     */         return Collections.singleton(var2);
/*     */       };
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static class b
/*     */     implements a
/*     */   {
/*     */     private final EntitySelector a;
/*     */ 
/*     */     
/*     */     public b(EntitySelector var0) {
/*  71 */       this.a = var0;
/*     */     }
/*     */ 
/*     */     
/*     */     public Collection<GameProfile> getNames(CommandListenerWrapper var0) throws CommandSyntaxException {
/*  76 */       List<EntityPlayer> var1 = this.a.d(var0);
/*  77 */       if (var1.isEmpty()) {
/*  78 */         throw ArgumentEntity.e.create();
/*     */       }
/*  80 */       List<GameProfile> var2 = Lists.newArrayList();
/*  81 */       for (EntityPlayer var4 : var1) {
/*  82 */         var2.add(var4.getProfile());
/*     */       }
/*  84 */       return var2;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> var0, SuggestionsBuilder var1) {
/*  90 */     if (var0.getSource() instanceof ICompletionProvider) {
/*  91 */       StringReader var2 = new StringReader(var1.getInput());
/*  92 */       var2.setCursor(var1.getStart());
/*  93 */       ArgumentParserSelector var3 = new ArgumentParserSelector(var2);
/*     */       try {
/*  95 */         var3.parse();
/*  96 */       } catch (CommandSyntaxException commandSyntaxException) {}
/*     */       
/*  98 */       return var3.a(var1, var1 -> ICompletionProvider.b(((ICompletionProvider)var0.getSource()).l(), var1));
/*     */     } 
/* 100 */     return Suggestions.empty();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Collection<String> getExamples() {
/* 106 */     return b;
/*     */   }
/*     */   
/*     */   @FunctionalInterface
/*     */   public static interface a {
/*     */     Collection<GameProfile> getNames(CommandListenerWrapper param1CommandListenerWrapper) throws CommandSyntaxException;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ArgumentProfile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
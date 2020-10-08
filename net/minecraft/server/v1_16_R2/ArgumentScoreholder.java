/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.gson.JsonObject;
/*     */ import com.mojang.brigadier.StringReader;
/*     */ import com.mojang.brigadier.arguments.ArgumentType;
/*     */ import com.mojang.brigadier.context.CommandContext;
/*     */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*     */ import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
/*     */ import com.mojang.brigadier.suggestion.SuggestionProvider;
/*     */ import com.mojang.brigadier.suggestion.SuggestionsBuilder;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import java.util.concurrent.CompletableFuture;
/*     */ import java.util.function.Supplier;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ArgumentScoreholder
/*     */   implements ArgumentType<ArgumentScoreholder.a>
/*     */ {
/*     */   public static final SuggestionProvider<CommandListenerWrapper> a;
/*     */   
/*     */   static {
/*  27 */     a = ((var0, var1) -> {
/*     */         StringReader var2 = new StringReader(var1.getInput());
/*     */         var2.setCursor(var1.getStart());
/*     */         ArgumentParserSelector var3 = new ArgumentParserSelector(var2);
/*     */         try {
/*     */           var3.parse();
/*  33 */         } catch (CommandSyntaxException commandSyntaxException) {}
/*     */         return var3.a(var1, ());
/*     */       });
/*     */   }
/*     */   
/*  38 */   private static final Collection<String> b = Arrays.asList(new String[] { "Player", "0123", "*", "@e" });
/*  39 */   private static final SimpleCommandExceptionType c = new SimpleCommandExceptionType(new ChatMessage("argument.scoreHolder.empty"));
/*     */ 
/*     */   
/*     */   private final boolean d;
/*     */ 
/*     */   
/*     */   public ArgumentScoreholder(boolean var0) {
/*  46 */     this.d = var0;
/*     */   }
/*     */   
/*     */   public static String a(CommandContext<CommandListenerWrapper> var0, String var1) throws CommandSyntaxException {
/*  50 */     return b(var0, var1).iterator().next();
/*     */   }
/*     */   
/*     */   public static Collection<String> b(CommandContext<CommandListenerWrapper> var0, String var1) throws CommandSyntaxException {
/*  54 */     return a(var0, var1, Collections::emptyList);
/*     */   }
/*     */   
/*     */   public static Collection<String> c(CommandContext<CommandListenerWrapper> var0, String var1) throws CommandSyntaxException {
/*  58 */     return a(var0, var1, ((CommandListenerWrapper)var0.getSource()).getServer().getScoreboard()::getPlayers);
/*     */   }
/*     */   
/*     */   public static Collection<String> a(CommandContext<CommandListenerWrapper> var0, String var1, Supplier<Collection<String>> var2) throws CommandSyntaxException {
/*  62 */     Collection<String> var3 = ((a)var0.getArgument(var1, a.class)).getNames((CommandListenerWrapper)var0.getSource(), var2);
/*  63 */     if (var3.isEmpty()) {
/*  64 */       throw ArgumentEntity.d.create();
/*     */     }
/*  66 */     return var3;
/*     */   }
/*     */   
/*     */   public static ArgumentScoreholder a() {
/*  70 */     return new ArgumentScoreholder(false);
/*     */   }
/*     */   
/*     */   public static ArgumentScoreholder b() {
/*  74 */     return new ArgumentScoreholder(true);
/*     */   }
/*     */ 
/*     */   
/*     */   public a parse(StringReader var0) throws CommandSyntaxException {
/*  79 */     if (var0.canRead() && var0.peek() == '@') {
/*  80 */       ArgumentParserSelector argumentParserSelector = new ArgumentParserSelector(var0);
/*  81 */       EntitySelector entitySelector = argumentParserSelector.parse();
/*  82 */       if (!this.d && entitySelector.a() > 1) {
/*  83 */         throw ArgumentEntity.a.create();
/*     */       }
/*  85 */       return new b(entitySelector);
/*     */     } 
/*  87 */     int var1 = var0.getCursor();
/*  88 */     while (var0.canRead() && var0.peek() != ' ') {
/*  89 */       var0.skip();
/*     */     }
/*  91 */     String var2 = var0.getString().substring(var1, var0.getCursor());
/*  92 */     if (var2.equals("*")) {
/*  93 */       return (var0, var1) -> {
/*     */           Collection<String> var2 = var1.get();
/*     */           if (var2.isEmpty()) {
/*     */             throw c.create();
/*     */           }
/*     */           return var2;
/*     */         };
/*     */     }
/* 101 */     Collection<String> var3 = Collections.singleton(var2);
/* 102 */     return (var1, var2) -> var0;
/*     */   }
/*     */ 
/*     */   
/*     */   public Collection<String> getExamples() {
/* 107 */     return b;
/*     */   }
/*     */   
/*     */   @FunctionalInterface
/*     */   public static interface a {
/*     */     Collection<String> getNames(CommandListenerWrapper param1CommandListenerWrapper, Supplier<Collection<String>> param1Supplier) throws CommandSyntaxException;
/*     */   }
/*     */   
/*     */   public static class b implements a {
/*     */     private final EntitySelector a;
/*     */     
/*     */     public b(EntitySelector var0) {
/* 119 */       this.a = var0;
/*     */     }
/*     */ 
/*     */     
/*     */     public Collection<String> getNames(CommandListenerWrapper var0, Supplier<Collection<String>> var1) throws CommandSyntaxException {
/* 124 */       List<? extends Entity> var2 = this.a.getEntities(var0);
/* 125 */       if (var2.isEmpty()) {
/* 126 */         throw ArgumentEntity.d.create();
/*     */       }
/* 128 */       List<String> var3 = Lists.newArrayList();
/* 129 */       for (Entity var5 : var2) {
/* 130 */         var3.add(var5.getName());
/*     */       }
/* 132 */       return var3;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class c
/*     */     implements ArgumentSerializer<ArgumentScoreholder> {
/*     */     public void a(ArgumentScoreholder var0, PacketDataSerializer var1) {
/* 139 */       byte var2 = 0;
/* 140 */       if (ArgumentScoreholder.a(var0)) {
/* 141 */         var2 = (byte)(var2 | 0x1);
/*     */       }
/* 143 */       var1.writeByte(var2);
/*     */     }
/*     */ 
/*     */     
/*     */     public ArgumentScoreholder b(PacketDataSerializer var0) {
/* 148 */       byte var1 = var0.readByte();
/* 149 */       boolean var2 = ((var1 & 0x1) != 0);
/* 150 */       return new ArgumentScoreholder(var2);
/*     */     }
/*     */ 
/*     */     
/*     */     public void a(ArgumentScoreholder var0, JsonObject var1) {
/* 155 */       var1.addProperty("amount", ArgumentScoreholder.a(var0) ? "multiple" : "single");
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ArgumentScoreholder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
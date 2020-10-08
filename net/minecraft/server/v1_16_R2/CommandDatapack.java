/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.mojang.brigadier.CommandDispatcher;
/*     */ import com.mojang.brigadier.Message;
/*     */ import com.mojang.brigadier.arguments.ArgumentType;
/*     */ import com.mojang.brigadier.arguments.StringArgumentType;
/*     */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*     */ import com.mojang.brigadier.builder.RequiredArgumentBuilder;
/*     */ import com.mojang.brigadier.context.CommandContext;
/*     */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*     */ import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
/*     */ import com.mojang.brigadier.suggestion.SuggestionProvider;
/*     */ import com.mojang.brigadier.suggestion.SuggestionsBuilder;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import java.util.concurrent.CompletableFuture;
/*     */ import java.util.stream.Collectors;
/*     */ 
/*     */ public class CommandDatapack {
/*     */   private static final DynamicCommandExceptionType a;
/*     */   private static final DynamicCommandExceptionType b;
/*     */   private static final DynamicCommandExceptionType c;
/*     */   private static final SuggestionProvider<CommandListenerWrapper> d;
/*     */   private static final SuggestionProvider<CommandListenerWrapper> e;
/*     */   
/*     */   static {
/*  28 */     a = new DynamicCommandExceptionType(var0 -> new ChatMessage("commands.datapack.unknown", new Object[] { var0 }));
/*  29 */     b = new DynamicCommandExceptionType(var0 -> new ChatMessage("commands.datapack.enable.failed", new Object[] { var0 }));
/*  30 */     c = new DynamicCommandExceptionType(var0 -> new ChatMessage("commands.datapack.disable.failed", new Object[] { var0 }));
/*     */     
/*  32 */     d = ((var0, var1) -> ICompletionProvider.b(((CommandListenerWrapper)var0.getSource()).getServer().getResourcePackRepository().d().stream().map(StringArgumentType::escapeIfRequired), var1));
/*  33 */     e = ((var0, var1) -> {
/*     */         ResourcePackRepository var2 = ((CommandListenerWrapper)var0.getSource()).getServer().getResourcePackRepository();
/*     */         Collection<String> var3 = var2.d();
/*     */         return ICompletionProvider.b(var2.b().stream().filter(()).map(StringArgumentType::escapeIfRequired), var1);
/*     */       });
/*     */   }
/*     */   public static void a(CommandDispatcher<CommandListenerWrapper> var0) {
/*  40 */     var0.register(
/*  41 */         (LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)CommandDispatcher.a("datapack")
/*  42 */         .requires(var0 -> var0.hasPermission(2)))
/*  43 */         .then(
/*  44 */           CommandDispatcher.a("enable")
/*  45 */           .then((
/*  46 */             (RequiredArgumentBuilder)((RequiredArgumentBuilder)((RequiredArgumentBuilder)((RequiredArgumentBuilder)CommandDispatcher.<T>a("name", (ArgumentType<T>)StringArgumentType.string())
/*  47 */             .suggests(e)
/*  48 */             .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), a(var0, "name", true), ())))
/*  49 */             .then(
/*  50 */               CommandDispatcher.a("after")
/*  51 */               .then(
/*  52 */                 CommandDispatcher.<T>a("existing", (ArgumentType<T>)StringArgumentType.string())
/*  53 */                 .suggests(d)
/*  54 */                 .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), a(var0, "name", true), ())))))
/*     */ 
/*     */             
/*  57 */             .then(
/*  58 */               CommandDispatcher.a("before")
/*  59 */               .then(
/*  60 */                 CommandDispatcher.<T>a("existing", (ArgumentType<T>)StringArgumentType.string())
/*  61 */                 .suggests(d)
/*  62 */                 .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), a(var0, "name", true), ())))))
/*     */ 
/*     */             
/*  65 */             .then(
/*  66 */               CommandDispatcher.a("last")
/*  67 */               .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), a(var0, "name", true), List::add))))
/*     */             
/*  69 */             .then(
/*  70 */               CommandDispatcher.a("first")
/*  71 */               .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), a(var0, "name", true), ()))))))
/*     */ 
/*     */ 
/*     */         
/*  75 */         .then(
/*  76 */           CommandDispatcher.a("disable")
/*  77 */           .then(
/*  78 */             CommandDispatcher.<T>a("name", (ArgumentType<T>)StringArgumentType.string())
/*  79 */             .suggests(d)
/*  80 */             .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), a(var0, "name", false))))))
/*     */ 
/*     */         
/*  83 */         .then((
/*  84 */           (LiteralArgumentBuilder)((LiteralArgumentBuilder)CommandDispatcher.a("list")
/*  85 */           .executes(var0 -> a((CommandListenerWrapper)var0.getSource())))
/*  86 */           .then(
/*  87 */             CommandDispatcher.a("available")
/*  88 */             .executes(var0 -> b((CommandListenerWrapper)var0.getSource()))))
/*     */           
/*  90 */           .then(
/*  91 */             CommandDispatcher.a("enabled")
/*  92 */             .executes(var0 -> c((CommandListenerWrapper)var0.getSource())))));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static int a(CommandListenerWrapper var0, ResourcePackLoader var1, a var2) throws CommandSyntaxException {
/*  99 */     ResourcePackRepository var3 = var0.getServer().getResourcePackRepository();
/*     */     
/* 101 */     List<ResourcePackLoader> var4 = Lists.newArrayList(var3.e());
/* 102 */     var2.apply(var4, var1);
/*     */     
/* 104 */     var0.sendMessage(new ChatMessage("commands.datapack.modify.enable", new Object[] { var1.a(true) }), true);
/* 105 */     CommandReload.a((Collection<String>)var4.stream().map(ResourcePackLoader::e).collect(Collectors.toList()), var0);
/* 106 */     return var4.size();
/*     */   }
/*     */   
/*     */   private static int a(CommandListenerWrapper var0, ResourcePackLoader var1) {
/* 110 */     ResourcePackRepository var2 = var0.getServer().getResourcePackRepository();
/*     */     
/* 112 */     List<ResourcePackLoader> var3 = Lists.newArrayList(var2.e());
/* 113 */     var3.remove(var1);
/*     */     
/* 115 */     var0.sendMessage(new ChatMessage("commands.datapack.modify.disable", new Object[] { var1.a(true) }), true);
/* 116 */     CommandReload.a((Collection<String>)var3.stream().map(ResourcePackLoader::e).collect(Collectors.toList()), var0);
/* 117 */     return var3.size();
/*     */   }
/*     */   
/*     */   private static int a(CommandListenerWrapper var0) {
/* 121 */     return c(var0) + b(var0);
/*     */   }
/*     */   
/*     */   private static int b(CommandListenerWrapper var0) {
/* 125 */     ResourcePackRepository var1 = var0.getServer().getResourcePackRepository();
/* 126 */     var1.a();
/*     */     
/* 128 */     Collection<? extends ResourcePackLoader> var2 = var1.e();
/* 129 */     Collection<? extends ResourcePackLoader> var3 = var1.c();
/* 130 */     List<ResourcePackLoader> var4 = (List<ResourcePackLoader>)var3.stream().filter(var1 -> !var0.contains(var1)).collect(Collectors.toList());
/* 131 */     if (var4.isEmpty()) {
/* 132 */       var0.sendMessage(new ChatMessage("commands.datapack.list.available.none"), false);
/*     */     } else {
/* 134 */       var0.sendMessage(new ChatMessage("commands.datapack.list.available.success", new Object[] { Integer.valueOf(var4.size()), ChatComponentUtils.b(var4, var0 -> var0.a(false)) }), false);
/*     */     } 
/*     */     
/* 137 */     return var4.size();
/*     */   }
/*     */   
/*     */   private static int c(CommandListenerWrapper var0) {
/* 141 */     ResourcePackRepository var1 = var0.getServer().getResourcePackRepository();
/* 142 */     var1.a();
/*     */     
/* 144 */     Collection<? extends ResourcePackLoader> var2 = var1.e();
/* 145 */     if (var2.isEmpty()) {
/* 146 */       var0.sendMessage(new ChatMessage("commands.datapack.list.enabled.none"), false);
/*     */     } else {
/* 148 */       var0.sendMessage(new ChatMessage("commands.datapack.list.enabled.success", new Object[] { Integer.valueOf(var2.size()), ChatComponentUtils.b(var2, var0 -> var0.a(true)) }), false);
/*     */     } 
/*     */     
/* 151 */     return var2.size();
/*     */   }
/*     */   
/*     */   private static ResourcePackLoader a(CommandContext<CommandListenerWrapper> var0, String var1, boolean var2) throws CommandSyntaxException {
/* 155 */     String var3 = StringArgumentType.getString(var0, var1);
/* 156 */     ResourcePackRepository var4 = ((CommandListenerWrapper)var0.getSource()).getServer().getResourcePackRepository();
/* 157 */     ResourcePackLoader var5 = var4.a(var3);
/* 158 */     if (var5 == null) {
/* 159 */       throw a.create(var3);
/*     */     }
/* 161 */     boolean var6 = var4.e().contains(var5);
/* 162 */     if (var2 && var6) {
/* 163 */       throw b.create(var3);
/*     */     }
/* 165 */     if (!var2 && !var6) {
/* 166 */       throw c.create(var3);
/*     */     }
/* 168 */     return var5;
/*     */   }
/*     */   
/*     */   static interface a {
/*     */     void apply(List<ResourcePackLoader> param1List, ResourcePackLoader param1ResourcePackLoader) throws CommandSyntaxException;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CommandDatapack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
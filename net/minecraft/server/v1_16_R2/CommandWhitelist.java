/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.mojang.authlib.GameProfile;
/*     */ import com.mojang.brigadier.CommandDispatcher;
/*     */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*     */ import com.mojang.brigadier.context.CommandContext;
/*     */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*     */ import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
/*     */ import com.mojang.brigadier.suggestion.SuggestionsBuilder;
/*     */ import java.util.Collection;
/*     */ import java.util.concurrent.CompletableFuture;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CommandWhitelist
/*     */ {
/*  24 */   private static final SimpleCommandExceptionType a = new SimpleCommandExceptionType(new ChatMessage("commands.whitelist.alreadyOn"));
/*  25 */   private static final SimpleCommandExceptionType b = new SimpleCommandExceptionType(new ChatMessage("commands.whitelist.alreadyOff"));
/*  26 */   private static final SimpleCommandExceptionType c = new SimpleCommandExceptionType(new ChatMessage("commands.whitelist.add.failed"));
/*  27 */   private static final SimpleCommandExceptionType d = new SimpleCommandExceptionType(new ChatMessage("commands.whitelist.remove.failed"));
/*     */   
/*     */   public static void a(CommandDispatcher<CommandListenerWrapper> var0) {
/*  30 */     var0.register(
/*  31 */         (LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)CommandDispatcher.a("whitelist")
/*  32 */         .requires(var0 -> var0.hasPermission(3)))
/*  33 */         .then(
/*  34 */           CommandDispatcher.a("on")
/*  35 */           .executes(var0 -> b((CommandListenerWrapper)var0.getSource()))))
/*     */         
/*  37 */         .then(
/*  38 */           CommandDispatcher.a("off")
/*  39 */           .executes(var0 -> c((CommandListenerWrapper)var0.getSource()))))
/*     */         
/*  41 */         .then(
/*  42 */           CommandDispatcher.a("list")
/*  43 */           .executes(var0 -> d((CommandListenerWrapper)var0.getSource()))))
/*     */         
/*  45 */         .then(
/*  46 */           CommandDispatcher.a("add")
/*  47 */           .then(
/*  48 */             CommandDispatcher.<T>a("targets", ArgumentProfile.a())
/*  49 */             .suggests((var0, var1) -> {
/*     */                 PlayerList var2 = ((CommandListenerWrapper)var0.getSource()).getServer().getPlayerList();
/*     */                 
/*     */                 return ICompletionProvider.b(var2.getPlayers().stream().filter(()).map(()), var1);
/*  53 */               }).executes(var0 -> a((CommandListenerWrapper)var0.getSource(), ArgumentProfile.a(var0, "targets"))))))
/*     */ 
/*     */         
/*  56 */         .then(
/*  57 */           CommandDispatcher.a("remove")
/*  58 */           .then(
/*  59 */             CommandDispatcher.<T>a("targets", ArgumentProfile.a())
/*  60 */             .suggests((var0, var1) -> ICompletionProvider.a(((CommandListenerWrapper)var0.getSource()).getServer().getPlayerList().getWhitelisted(), var1))
/*  61 */             .executes(var0 -> b((CommandListenerWrapper)var0.getSource(), ArgumentProfile.a(var0, "targets"))))))
/*     */ 
/*     */         
/*  64 */         .then(
/*  65 */           CommandDispatcher.a("reload")
/*  66 */           .executes(var0 -> a((CommandListenerWrapper)var0.getSource()))));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static int a(CommandListenerWrapper var0) {
/*  72 */     var0.getServer().getPlayerList().reloadWhitelist();
/*  73 */     var0.sendMessage(new ChatMessage("commands.whitelist.reloaded"), true);
/*  74 */     var0.getServer().a(var0);
/*  75 */     return 1;
/*     */   }
/*     */   
/*     */   private static int a(CommandListenerWrapper var0, Collection<GameProfile> var1) throws CommandSyntaxException {
/*  79 */     WhiteList var2 = var0.getServer().getPlayerList().getWhitelist();
/*  80 */     int var3 = 0;
/*     */     
/*  82 */     for (GameProfile var5 : var1) {
/*  83 */       if (!var2.isWhitelisted(var5)) {
/*  84 */         WhiteListEntry var6 = new WhiteListEntry(var5);
/*  85 */         var2.add(var6);
/*  86 */         var0.sendMessage(new ChatMessage("commands.whitelist.add.success", new Object[] { ChatComponentUtils.a(var5) }), true);
/*  87 */         var3++;
/*     */       } 
/*     */     } 
/*     */     
/*  91 */     if (var3 == 0) {
/*  92 */       throw c.create();
/*     */     }
/*     */     
/*  95 */     return var3;
/*     */   }
/*     */   
/*     */   private static int b(CommandListenerWrapper var0, Collection<GameProfile> var1) throws CommandSyntaxException {
/*  99 */     WhiteList var2 = var0.getServer().getPlayerList().getWhitelist();
/* 100 */     int var3 = 0;
/*     */     
/* 102 */     for (GameProfile var5 : var1) {
/* 103 */       if (var2.isWhitelisted(var5)) {
/* 104 */         WhiteListEntry var6 = new WhiteListEntry(var5);
/* 105 */         var2.b(var6);
/* 106 */         var0.sendMessage(new ChatMessage("commands.whitelist.remove.success", new Object[] { ChatComponentUtils.a(var5) }), true);
/* 107 */         var3++;
/*     */       } 
/*     */     } 
/*     */     
/* 111 */     if (var3 == 0) {
/* 112 */       throw d.create();
/*     */     }
/*     */     
/* 115 */     var0.getServer().a(var0);
/* 116 */     return var3;
/*     */   }
/*     */   
/*     */   private static int b(CommandListenerWrapper var0) throws CommandSyntaxException {
/* 120 */     PlayerList var1 = var0.getServer().getPlayerList();
/* 121 */     if (var1.getHasWhitelist()) {
/* 122 */       throw a.create();
/*     */     }
/* 124 */     var1.setHasWhitelist(true);
/* 125 */     var0.sendMessage(new ChatMessage("commands.whitelist.enabled"), true);
/* 126 */     var0.getServer().a(var0);
/* 127 */     return 1;
/*     */   }
/*     */   
/*     */   private static int c(CommandListenerWrapper var0) throws CommandSyntaxException {
/* 131 */     PlayerList var1 = var0.getServer().getPlayerList();
/* 132 */     if (!var1.getHasWhitelist()) {
/* 133 */       throw b.create();
/*     */     }
/* 135 */     var1.setHasWhitelist(false);
/* 136 */     var0.sendMessage(new ChatMessage("commands.whitelist.disabled"), true);
/* 137 */     return 1;
/*     */   }
/*     */   
/*     */   private static int d(CommandListenerWrapper var0) {
/* 141 */     String[] var1 = var0.getServer().getPlayerList().getWhitelisted();
/* 142 */     if (var1.length == 0) {
/* 143 */       var0.sendMessage(new ChatMessage("commands.whitelist.none"), false);
/*     */     } else {
/* 145 */       var0.sendMessage(new ChatMessage("commands.whitelist.list", new Object[] { Integer.valueOf(var1.length), String.join(", ", (CharSequence[])var1) }), false);
/*     */     } 
/* 147 */     return var1.length;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CommandWhitelist.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
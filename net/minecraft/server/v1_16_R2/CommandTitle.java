/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.mojang.brigadier.CommandDispatcher;
/*     */ import com.mojang.brigadier.arguments.ArgumentType;
/*     */ import com.mojang.brigadier.arguments.IntegerArgumentType;
/*     */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*     */ import com.mojang.brigadier.builder.RequiredArgumentBuilder;
/*     */ import com.mojang.brigadier.context.CommandContext;
/*     */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*     */ import java.util.Collection;
/*     */ import java.util.Locale;
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
/*     */ 
/*     */ 
/*     */ public class CommandTitle
/*     */ {
/*     */   public static void a(CommandDispatcher<CommandListenerWrapper> var0) {
/*  27 */     var0.register(
/*  28 */         (LiteralArgumentBuilder)((LiteralArgumentBuilder)CommandDispatcher.a("title")
/*  29 */         .requires(var0 -> var0.hasPermission(2)))
/*  30 */         .then((
/*  31 */           (RequiredArgumentBuilder)((RequiredArgumentBuilder)((RequiredArgumentBuilder)((RequiredArgumentBuilder)((RequiredArgumentBuilder)CommandDispatcher.<T>a("targets", ArgumentEntity.d())
/*  32 */           .then(
/*  33 */             CommandDispatcher.a("clear")
/*  34 */             .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), ArgumentEntity.f(var0, "targets")))))
/*     */           
/*  36 */           .then(
/*  37 */             CommandDispatcher.a("reset")
/*  38 */             .executes(var0 -> b((CommandListenerWrapper)var0.getSource(), ArgumentEntity.f(var0, "targets")))))
/*     */           
/*  40 */           .then(
/*  41 */             CommandDispatcher.a("title")
/*  42 */             .then(
/*  43 */               CommandDispatcher.<T>a("title", ArgumentChatComponent.a())
/*  44 */               .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), ArgumentEntity.f(var0, "targets"), ArgumentChatComponent.a(var0, "title"), PacketPlayOutTitle.EnumTitleAction.TITLE)))))
/*     */ 
/*     */           
/*  47 */           .then(
/*  48 */             CommandDispatcher.a("subtitle")
/*  49 */             .then(
/*  50 */               CommandDispatcher.<T>a("title", ArgumentChatComponent.a())
/*  51 */               .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), ArgumentEntity.f(var0, "targets"), ArgumentChatComponent.a(var0, "title"), PacketPlayOutTitle.EnumTitleAction.SUBTITLE)))))
/*     */ 
/*     */           
/*  54 */           .then(
/*  55 */             CommandDispatcher.a("actionbar")
/*  56 */             .then(
/*  57 */               CommandDispatcher.<T>a("title", ArgumentChatComponent.a())
/*  58 */               .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), ArgumentEntity.f(var0, "targets"), ArgumentChatComponent.a(var0, "title"), PacketPlayOutTitle.EnumTitleAction.ACTIONBAR)))))
/*     */ 
/*     */           
/*  61 */           .then(
/*  62 */             CommandDispatcher.a("times")
/*  63 */             .then(
/*  64 */               CommandDispatcher.<T>a("fadeIn", (ArgumentType<T>)IntegerArgumentType.integer(0))
/*  65 */               .then(
/*  66 */                 CommandDispatcher.<T>a("stay", (ArgumentType<T>)IntegerArgumentType.integer(0))
/*  67 */                 .then(
/*  68 */                   CommandDispatcher.<T>a("fadeOut", (ArgumentType<T>)IntegerArgumentType.integer(0))
/*  69 */                   .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), ArgumentEntity.f(var0, "targets"), IntegerArgumentType.getInteger(var0, "fadeIn"), IntegerArgumentType.getInteger(var0, "stay"), IntegerArgumentType.getInteger(var0, "fadeOut")))))))));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static int a(CommandListenerWrapper var0, Collection<EntityPlayer> var1) {
/*  79 */     PacketPlayOutTitle var2 = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.CLEAR, null);
/*  80 */     for (EntityPlayer var4 : var1) {
/*  81 */       var4.playerConnection.sendPacket(var2);
/*     */     }
/*     */     
/*  84 */     if (var1.size() == 1) {
/*  85 */       var0.sendMessage(new ChatMessage("commands.title.cleared.single", new Object[] { ((EntityPlayer)var1.iterator().next()).getScoreboardDisplayName() }), true);
/*     */     } else {
/*  87 */       var0.sendMessage(new ChatMessage("commands.title.cleared.multiple", new Object[] { Integer.valueOf(var1.size()) }), true);
/*     */     } 
/*     */     
/*  90 */     return var1.size();
/*     */   }
/*     */   
/*     */   private static int b(CommandListenerWrapper var0, Collection<EntityPlayer> var1) {
/*  94 */     PacketPlayOutTitle var2 = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.RESET, null);
/*  95 */     for (EntityPlayer var4 : var1) {
/*  96 */       var4.playerConnection.sendPacket(var2);
/*     */     }
/*     */     
/*  99 */     if (var1.size() == 1) {
/* 100 */       var0.sendMessage(new ChatMessage("commands.title.reset.single", new Object[] { ((EntityPlayer)var1.iterator().next()).getScoreboardDisplayName() }), true);
/*     */     } else {
/* 102 */       var0.sendMessage(new ChatMessage("commands.title.reset.multiple", new Object[] { Integer.valueOf(var1.size()) }), true);
/*     */     } 
/*     */     
/* 105 */     return var1.size();
/*     */   }
/*     */   
/*     */   private static int a(CommandListenerWrapper var0, Collection<EntityPlayer> var1, IChatBaseComponent var2, PacketPlayOutTitle.EnumTitleAction var3) throws CommandSyntaxException {
/* 109 */     for (EntityPlayer var5 : var1) {
/* 110 */       var5.playerConnection.sendPacket(new PacketPlayOutTitle(var3, ChatComponentUtils.filterForDisplay(var0, var2, var5, 0)));
/*     */     }
/*     */     
/* 113 */     if (var1.size() == 1) {
/* 114 */       var0.sendMessage(new ChatMessage("commands.title.show." + var3.name().toLowerCase(Locale.ROOT) + ".single", new Object[] { ((EntityPlayer)var1.iterator().next()).getScoreboardDisplayName() }), true);
/*     */     } else {
/* 116 */       var0.sendMessage(new ChatMessage("commands.title.show." + var3.name().toLowerCase(Locale.ROOT) + ".multiple", new Object[] { Integer.valueOf(var1.size()) }), true);
/*     */     } 
/*     */     
/* 119 */     return var1.size();
/*     */   }
/*     */   
/*     */   private static int a(CommandListenerWrapper var0, Collection<EntityPlayer> var1, int var2, int var3, int var4) {
/* 123 */     PacketPlayOutTitle var5 = new PacketPlayOutTitle(var2, var3, var4);
/* 124 */     for (EntityPlayer var7 : var1) {
/* 125 */       var7.playerConnection.sendPacket(var5);
/*     */     }
/*     */     
/* 128 */     if (var1.size() == 1) {
/* 129 */       var0.sendMessage(new ChatMessage("commands.title.times.single", new Object[] { ((EntityPlayer)var1.iterator().next()).getScoreboardDisplayName() }), true);
/*     */     } else {
/* 131 */       var0.sendMessage(new ChatMessage("commands.title.times.multiple", new Object[] { Integer.valueOf(var1.size()) }), true);
/*     */     } 
/*     */     
/* 134 */     return var1.size();
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CommandTitle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
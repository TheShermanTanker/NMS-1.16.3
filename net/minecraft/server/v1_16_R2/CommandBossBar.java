/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.mojang.brigadier.CommandDispatcher;
/*     */ import com.mojang.brigadier.Message;
/*     */ import com.mojang.brigadier.arguments.ArgumentType;
/*     */ import com.mojang.brigadier.arguments.BoolArgumentType;
/*     */ import com.mojang.brigadier.arguments.IntegerArgumentType;
/*     */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*     */ import com.mojang.brigadier.builder.RequiredArgumentBuilder;
/*     */ import com.mojang.brigadier.context.CommandContext;
/*     */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*     */ import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
/*     */ import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
/*     */ import com.mojang.brigadier.suggestion.SuggestionProvider;
/*     */ import com.mojang.brigadier.suggestion.SuggestionsBuilder;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CommandBossBar
/*     */ {
/*     */   private static final DynamicCommandExceptionType b;
/*     */   private static final DynamicCommandExceptionType c;
/*     */   
/*     */   static {
/*  39 */     b = new DynamicCommandExceptionType(var0 -> new ChatMessage("commands.bossbar.create.failed", new Object[] { var0 }));
/*  40 */     c = new DynamicCommandExceptionType(var0 -> new ChatMessage("commands.bossbar.unknown", new Object[] { var0 }));
/*  41 */   } private static final SimpleCommandExceptionType d = new SimpleCommandExceptionType(new ChatMessage("commands.bossbar.set.players.unchanged"));
/*  42 */   private static final SimpleCommandExceptionType e = new SimpleCommandExceptionType(new ChatMessage("commands.bossbar.set.name.unchanged"));
/*  43 */   private static final SimpleCommandExceptionType f = new SimpleCommandExceptionType(new ChatMessage("commands.bossbar.set.color.unchanged"));
/*  44 */   private static final SimpleCommandExceptionType g = new SimpleCommandExceptionType(new ChatMessage("commands.bossbar.set.style.unchanged"));
/*  45 */   private static final SimpleCommandExceptionType h = new SimpleCommandExceptionType(new ChatMessage("commands.bossbar.set.value.unchanged"));
/*  46 */   private static final SimpleCommandExceptionType i = new SimpleCommandExceptionType(new ChatMessage("commands.bossbar.set.max.unchanged"));
/*  47 */   private static final SimpleCommandExceptionType j = new SimpleCommandExceptionType(new ChatMessage("commands.bossbar.set.visibility.unchanged.hidden")); public static final SuggestionProvider<CommandListenerWrapper> a;
/*  48 */   private static final SimpleCommandExceptionType k = new SimpleCommandExceptionType(new ChatMessage("commands.bossbar.set.visibility.unchanged.visible")); static {
/*  49 */     a = ((var0, var1) -> ICompletionProvider.a(((CommandListenerWrapper)var0.getSource()).getServer().getBossBattleCustomData().a(), var1));
/*     */   }
/*     */   public static void a(CommandDispatcher<CommandListenerWrapper> var0) {
/*  52 */     var0.register(
/*  53 */         (LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)CommandDispatcher.a("bossbar")
/*  54 */         .requires(var0 -> var0.hasPermission(2)))
/*  55 */         .then(
/*  56 */           CommandDispatcher.a("add")
/*  57 */           .then(
/*  58 */             CommandDispatcher.<T>a("id", ArgumentMinecraftKeyRegistered.a())
/*  59 */             .then(
/*  60 */               CommandDispatcher.<T>a("name", ArgumentChatComponent.a())
/*  61 */               .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), ArgumentMinecraftKeyRegistered.e(var0, "id"), ArgumentChatComponent.a(var0, "name")))))))
/*     */ 
/*     */ 
/*     */         
/*  65 */         .then(
/*  66 */           CommandDispatcher.a("remove")
/*  67 */           .then(
/*  68 */             CommandDispatcher.<T>a("id", ArgumentMinecraftKeyRegistered.a())
/*  69 */             .suggests(a)
/*  70 */             .executes(var0 -> e((CommandListenerWrapper)var0.getSource(), a(var0))))))
/*     */ 
/*     */         
/*  73 */         .then(
/*  74 */           CommandDispatcher.a("list")
/*  75 */           .executes(var0 -> a((CommandListenerWrapper)var0.getSource()))))
/*     */         
/*  77 */         .then(
/*  78 */           CommandDispatcher.a("set")
/*  79 */           .then((
/*  80 */             (RequiredArgumentBuilder)((RequiredArgumentBuilder)((RequiredArgumentBuilder)((RequiredArgumentBuilder)((RequiredArgumentBuilder)((RequiredArgumentBuilder)CommandDispatcher.<T>a("id", ArgumentMinecraftKeyRegistered.a())
/*  81 */             .suggests(a)
/*  82 */             .then(
/*  83 */               CommandDispatcher.a("name")
/*  84 */               .then(
/*  85 */                 CommandDispatcher.<T>a("name", ArgumentChatComponent.a())
/*  86 */                 .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), a(var0), ArgumentChatComponent.a(var0, "name"))))))
/*     */ 
/*     */             
/*  89 */             .then((
/*  90 */               (LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)CommandDispatcher.a("color")
/*  91 */               .then(
/*  92 */                 CommandDispatcher.a("pink")
/*  93 */                 .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), a(var0), BossBattle.BarColor.PINK))))
/*     */               
/*  95 */               .then(
/*  96 */                 CommandDispatcher.a("blue")
/*  97 */                 .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), a(var0), BossBattle.BarColor.BLUE))))
/*     */               
/*  99 */               .then(
/* 100 */                 CommandDispatcher.a("red")
/* 101 */                 .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), a(var0), BossBattle.BarColor.RED))))
/*     */               
/* 103 */               .then(
/* 104 */                 CommandDispatcher.a("green")
/* 105 */                 .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), a(var0), BossBattle.BarColor.GREEN))))
/*     */               
/* 107 */               .then(
/* 108 */                 CommandDispatcher.a("yellow")
/* 109 */                 .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), a(var0), BossBattle.BarColor.YELLOW))))
/*     */               
/* 111 */               .then(
/* 112 */                 CommandDispatcher.a("purple")
/* 113 */                 .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), a(var0), BossBattle.BarColor.PURPLE))))
/*     */               
/* 115 */               .then(
/* 116 */                 CommandDispatcher.a("white")
/* 117 */                 .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), a(var0), BossBattle.BarColor.WHITE)))))
/*     */ 
/*     */             
/* 120 */             .then((
/* 121 */               (LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)CommandDispatcher.a("style")
/* 122 */               .then(
/* 123 */                 CommandDispatcher.a("progress")
/* 124 */                 .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), a(var0), BossBattle.BarStyle.PROGRESS))))
/*     */               
/* 126 */               .then(
/* 127 */                 CommandDispatcher.a("notched_6")
/* 128 */                 .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), a(var0), BossBattle.BarStyle.NOTCHED_6))))
/*     */               
/* 130 */               .then(
/* 131 */                 CommandDispatcher.a("notched_10")
/* 132 */                 .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), a(var0), BossBattle.BarStyle.NOTCHED_10))))
/*     */               
/* 134 */               .then(
/* 135 */                 CommandDispatcher.a("notched_12")
/* 136 */                 .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), a(var0), BossBattle.BarStyle.NOTCHED_12))))
/*     */               
/* 138 */               .then(
/* 139 */                 CommandDispatcher.a("notched_20")
/* 140 */                 .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), a(var0), BossBattle.BarStyle.NOTCHED_20)))))
/*     */ 
/*     */             
/* 143 */             .then(
/* 144 */               CommandDispatcher.a("value")
/* 145 */               .then(
/* 146 */                 CommandDispatcher.<T>a("value", (ArgumentType<T>)IntegerArgumentType.integer(0))
/* 147 */                 .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), a(var0), IntegerArgumentType.getInteger(var0, "value"))))))
/*     */ 
/*     */             
/* 150 */             .then(
/* 151 */               CommandDispatcher.a("max")
/* 152 */               .then(
/* 153 */                 CommandDispatcher.<T>a("max", (ArgumentType<T>)IntegerArgumentType.integer(1))
/* 154 */                 .executes(var0 -> b((CommandListenerWrapper)var0.getSource(), a(var0), IntegerArgumentType.getInteger(var0, "max"))))))
/*     */ 
/*     */             
/* 157 */             .then(
/* 158 */               CommandDispatcher.a("visible")
/* 159 */               .then(
/* 160 */                 CommandDispatcher.<T>a("visible", (ArgumentType<T>)BoolArgumentType.bool())
/* 161 */                 .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), a(var0), BoolArgumentType.getBool(var0, "visible"))))))
/*     */ 
/*     */             
/* 164 */             .then((
/* 165 */               (LiteralArgumentBuilder)CommandDispatcher.a("players")
/* 166 */               .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), a(var0), Collections.emptyList())))
/* 167 */               .then(
/* 168 */                 CommandDispatcher.<T>a("targets", ArgumentEntity.d())
/* 169 */                 .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), a(var0), ArgumentEntity.d(var0, "targets"))))))))
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 174 */         .then(
/* 175 */           CommandDispatcher.a("get")
/* 176 */           .then((
/* 177 */             (RequiredArgumentBuilder)((RequiredArgumentBuilder)((RequiredArgumentBuilder)CommandDispatcher.<T>a("id", ArgumentMinecraftKeyRegistered.a())
/* 178 */             .suggests(a)
/* 179 */             .then(
/* 180 */               CommandDispatcher.a("value")
/* 181 */               .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), a(var0)))))
/*     */             
/* 183 */             .then(
/* 184 */               CommandDispatcher.a("max")
/* 185 */               .executes(var0 -> b((CommandListenerWrapper)var0.getSource(), a(var0)))))
/*     */             
/* 187 */             .then(
/* 188 */               CommandDispatcher.a("visible")
/* 189 */               .executes(var0 -> c((CommandListenerWrapper)var0.getSource(), a(var0)))))
/*     */             
/* 191 */             .then(
/* 192 */               CommandDispatcher.a("players")
/* 193 */               .executes(var0 -> d((CommandListenerWrapper)var0.getSource(), a(var0)))))));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static int a(CommandListenerWrapper var0, BossBattleCustom var1) {
/* 201 */     var0.sendMessage(new ChatMessage("commands.bossbar.get.value", new Object[] { var1.e(), Integer.valueOf(var1.c()) }), true);
/* 202 */     return var1.c();
/*     */   }
/*     */   
/*     */   private static int b(CommandListenerWrapper var0, BossBattleCustom var1) {
/* 206 */     var0.sendMessage(new ChatMessage("commands.bossbar.get.max", new Object[] { var1.e(), Integer.valueOf(var1.d()) }), true);
/* 207 */     return var1.d();
/*     */   }
/*     */   
/*     */   private static int c(CommandListenerWrapper var0, BossBattleCustom var1) {
/* 211 */     if (var1.g()) {
/* 212 */       var0.sendMessage(new ChatMessage("commands.bossbar.get.visible.visible", new Object[] { var1.e() }), true);
/* 213 */       return 1;
/*     */     } 
/* 215 */     var0.sendMessage(new ChatMessage("commands.bossbar.get.visible.hidden", new Object[] { var1.e() }), true);
/* 216 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   private static int d(CommandListenerWrapper var0, BossBattleCustom var1) {
/* 221 */     if (var1.getPlayers().isEmpty()) {
/* 222 */       var0.sendMessage(new ChatMessage("commands.bossbar.get.players.none", new Object[] { var1.e() }), true);
/*     */     } else {
/* 224 */       var0.sendMessage(new ChatMessage("commands.bossbar.get.players.some", new Object[] { var1.e(), Integer.valueOf(var1.getPlayers().size()), ChatComponentUtils.b(var1.getPlayers(), EntityHuman::getScoreboardDisplayName) }), true);
/*     */     } 
/* 226 */     return var1.getPlayers().size();
/*     */   }
/*     */   
/*     */   private static int a(CommandListenerWrapper var0, BossBattleCustom var1, boolean var2) throws CommandSyntaxException {
/* 230 */     if (var1.g() == var2) {
/* 231 */       if (var2) {
/* 232 */         throw k.create();
/*     */       }
/* 234 */       throw j.create();
/*     */     } 
/*     */     
/* 237 */     var1.setVisible(var2);
/* 238 */     if (var2) {
/* 239 */       var0.sendMessage(new ChatMessage("commands.bossbar.set.visible.success.visible", new Object[] { var1.e() }), true);
/*     */     } else {
/* 241 */       var0.sendMessage(new ChatMessage("commands.bossbar.set.visible.success.hidden", new Object[] { var1.e() }), true);
/*     */     } 
/* 243 */     return 0;
/*     */   }
/*     */   
/*     */   private static int a(CommandListenerWrapper var0, BossBattleCustom var1, int var2) throws CommandSyntaxException {
/* 247 */     if (var1.c() == var2) {
/* 248 */       throw h.create();
/*     */     }
/* 250 */     var1.a(var2);
/* 251 */     var0.sendMessage(new ChatMessage("commands.bossbar.set.value.success", new Object[] { var1.e(), Integer.valueOf(var2) }), true);
/* 252 */     return var2;
/*     */   }
/*     */   
/*     */   private static int b(CommandListenerWrapper var0, BossBattleCustom var1, int var2) throws CommandSyntaxException {
/* 256 */     if (var1.d() == var2) {
/* 257 */       throw i.create();
/*     */     }
/* 259 */     var1.b(var2);
/* 260 */     var0.sendMessage(new ChatMessage("commands.bossbar.set.max.success", new Object[] { var1.e(), Integer.valueOf(var2) }), true);
/* 261 */     return var2;
/*     */   }
/*     */   
/*     */   private static int a(CommandListenerWrapper var0, BossBattleCustom var1, BossBattle.BarColor var2) throws CommandSyntaxException {
/* 265 */     if (var1.l().equals(var2)) {
/* 266 */       throw f.create();
/*     */     }
/* 268 */     var1.a(var2);
/* 269 */     var0.sendMessage(new ChatMessage("commands.bossbar.set.color.success", new Object[] { var1.e() }), true);
/* 270 */     return 0;
/*     */   }
/*     */   
/*     */   private static int a(CommandListenerWrapper var0, BossBattleCustom var1, BossBattle.BarStyle var2) throws CommandSyntaxException {
/* 274 */     if (var1.m().equals(var2)) {
/* 275 */       throw g.create();
/*     */     }
/* 277 */     var1.a(var2);
/* 278 */     var0.sendMessage(new ChatMessage("commands.bossbar.set.style.success", new Object[] { var1.e() }), true);
/* 279 */     return 0;
/*     */   }
/*     */   
/*     */   private static int a(CommandListenerWrapper var0, BossBattleCustom var1, IChatBaseComponent var2) throws CommandSyntaxException {
/* 283 */     IChatBaseComponent var3 = ChatComponentUtils.filterForDisplay(var0, var2, null, 0);
/* 284 */     if (var1.j().equals(var3)) {
/* 285 */       throw e.create();
/*     */     }
/* 287 */     var1.a(var3);
/* 288 */     var0.sendMessage(new ChatMessage("commands.bossbar.set.name.success", new Object[] { var1.e() }), true);
/* 289 */     return 0;
/*     */   }
/*     */   
/*     */   private static int a(CommandListenerWrapper var0, BossBattleCustom var1, Collection<EntityPlayer> var2) throws CommandSyntaxException {
/* 293 */     boolean var3 = var1.a(var2);
/* 294 */     if (!var3) {
/* 295 */       throw d.create();
/*     */     }
/* 297 */     if (var1.getPlayers().isEmpty()) {
/* 298 */       var0.sendMessage(new ChatMessage("commands.bossbar.set.players.success.none", new Object[] { var1.e() }), true);
/*     */     } else {
/* 300 */       var0.sendMessage(new ChatMessage("commands.bossbar.set.players.success.some", new Object[] { var1.e(), Integer.valueOf(var2.size()), ChatComponentUtils.b(var2, EntityHuman::getScoreboardDisplayName) }), true);
/*     */     } 
/* 302 */     return var1.getPlayers().size();
/*     */   }
/*     */   
/*     */   private static int a(CommandListenerWrapper var0) {
/* 306 */     Collection<BossBattleCustom> var1 = var0.getServer().getBossBattleCustomData().getBattles();
/* 307 */     if (var1.isEmpty()) {
/* 308 */       var0.sendMessage(new ChatMessage("commands.bossbar.list.bars.none"), false);
/*     */     } else {
/* 310 */       var0.sendMessage(new ChatMessage("commands.bossbar.list.bars.some", new Object[] { Integer.valueOf(var1.size()), ChatComponentUtils.b(var1, BossBattleCustom::e) }), false);
/*     */     } 
/* 312 */     return var1.size();
/*     */   }
/*     */   
/*     */   private static int a(CommandListenerWrapper var0, MinecraftKey var1, IChatBaseComponent var2) throws CommandSyntaxException {
/* 316 */     BossBattleCustomData var3 = var0.getServer().getBossBattleCustomData();
/* 317 */     if (var3.a(var1) != null) {
/* 318 */       throw b.create(var1.toString());
/*     */     }
/* 320 */     BossBattleCustom var4 = var3.register(var1, ChatComponentUtils.filterForDisplay(var0, var2, null, 0));
/* 321 */     var0.sendMessage(new ChatMessage("commands.bossbar.create.success", new Object[] { var4.e() }), true);
/* 322 */     return var3.getBattles().size();
/*     */   }
/*     */   
/*     */   private static int e(CommandListenerWrapper var0, BossBattleCustom var1) {
/* 326 */     BossBattleCustomData var2 = var0.getServer().getBossBattleCustomData();
/* 327 */     var1.b();
/* 328 */     var2.remove(var1);
/* 329 */     var0.sendMessage(new ChatMessage("commands.bossbar.remove.success", new Object[] { var1.e() }), true);
/* 330 */     return var2.getBattles().size();
/*     */   }
/*     */   
/*     */   public static BossBattleCustom a(CommandContext<CommandListenerWrapper> var0) throws CommandSyntaxException {
/* 334 */     MinecraftKey var1 = ArgumentMinecraftKeyRegistered.e(var0, "id");
/* 335 */     BossBattleCustom var2 = ((CommandListenerWrapper)var0.getSource()).getServer().getBossBattleCustomData().a(var1);
/* 336 */     if (var2 == null) {
/* 337 */       throw c.create(var1.toString());
/*     */     }
/* 339 */     return var2;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CommandBossBar.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
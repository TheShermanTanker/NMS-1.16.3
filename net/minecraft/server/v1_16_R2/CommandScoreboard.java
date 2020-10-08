/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.mojang.brigadier.CommandDispatcher;
/*     */ import com.mojang.brigadier.Message;
/*     */ import com.mojang.brigadier.arguments.ArgumentType;
/*     */ import com.mojang.brigadier.arguments.IntegerArgumentType;
/*     */ import com.mojang.brigadier.arguments.StringArgumentType;
/*     */ import com.mojang.brigadier.builder.ArgumentBuilder;
/*     */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*     */ import com.mojang.brigadier.builder.RequiredArgumentBuilder;
/*     */ import com.mojang.brigadier.context.CommandContext;
/*     */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*     */ import com.mojang.brigadier.exceptions.Dynamic2CommandExceptionType;
/*     */ import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
/*     */ import com.mojang.brigadier.suggestion.Suggestions;
/*     */ import com.mojang.brigadier.suggestion.SuggestionsBuilder;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import java.util.Map;
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
/*     */ 
/*     */ 
/*     */ public class CommandScoreboard
/*     */ {
/*  54 */   private static final SimpleCommandExceptionType a = new SimpleCommandExceptionType(new ChatMessage("commands.scoreboard.objectives.add.duplicate"));
/*  55 */   private static final SimpleCommandExceptionType b = new SimpleCommandExceptionType(new ChatMessage("commands.scoreboard.objectives.display.alreadyEmpty"));
/*  56 */   private static final SimpleCommandExceptionType c = new SimpleCommandExceptionType(new ChatMessage("commands.scoreboard.objectives.display.alreadySet"));
/*  57 */   private static final SimpleCommandExceptionType d = new SimpleCommandExceptionType(new ChatMessage("commands.scoreboard.players.enable.failed")); private static final Dynamic2CommandExceptionType f;
/*  58 */   private static final SimpleCommandExceptionType e = new SimpleCommandExceptionType(new ChatMessage("commands.scoreboard.players.enable.invalid")); static {
/*  59 */     f = new Dynamic2CommandExceptionType((var0, var1) -> new ChatMessage("commands.scoreboard.players.get.null", new Object[] { var0, var1 }));
/*     */   }
/*     */   public static void a(CommandDispatcher<CommandListenerWrapper> var0) {
/*  62 */     var0.register(
/*  63 */         (LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)CommandDispatcher.a("scoreboard")
/*  64 */         .requires(var0 -> var0.hasPermission(2)))
/*  65 */         .then((
/*  66 */           (LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)CommandDispatcher.a("objectives")
/*  67 */           .then(
/*  68 */             CommandDispatcher.a("list")
/*  69 */             .executes(var0 -> b((CommandListenerWrapper)var0.getSource()))))
/*     */           
/*  71 */           .then(
/*  72 */             CommandDispatcher.a("add")
/*  73 */             .then(
/*  74 */               CommandDispatcher.<T>a("objective", (ArgumentType<T>)StringArgumentType.word())
/*  75 */               .then((
/*  76 */                 (RequiredArgumentBuilder)CommandDispatcher.<T>a("criteria", ArgumentScoreboardCriteria.a())
/*  77 */                 .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), StringArgumentType.getString(var0, "objective"), ArgumentScoreboardCriteria.a(var0, "criteria"), new ChatComponentText(StringArgumentType.getString(var0, "objective")))))
/*  78 */                 .then(
/*  79 */                   CommandDispatcher.<T>a("displayName", ArgumentChatComponent.a())
/*  80 */                   .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), StringArgumentType.getString(var0, "objective"), ArgumentScoreboardCriteria.a(var0, "criteria"), ArgumentChatComponent.a(var0, "displayName"))))))))
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*  85 */           .then(
/*  86 */             CommandDispatcher.a("modify")
/*  87 */             .then((
/*  88 */               (RequiredArgumentBuilder)CommandDispatcher.<T>a("objective", ArgumentScoreboardObjective.a())
/*  89 */               .then(
/*  90 */                 CommandDispatcher.a("displayname")
/*  91 */                 .then(
/*  92 */                   CommandDispatcher.<T>a("displayName", ArgumentChatComponent.a())
/*  93 */                   .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), ArgumentScoreboardObjective.a(var0, "objective"), ArgumentChatComponent.a(var0, "displayName"))))))
/*     */               
/*  95 */               .then((ArgumentBuilder)a()))))
/*     */ 
/*     */           
/*  98 */           .then(
/*  99 */             CommandDispatcher.a("remove")
/* 100 */             .then(
/* 101 */               CommandDispatcher.<T>a("objective", ArgumentScoreboardObjective.a())
/* 102 */               .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), ArgumentScoreboardObjective.a(var0, "objective"))))))
/*     */ 
/*     */           
/* 105 */           .then(
/* 106 */             CommandDispatcher.a("setdisplay")
/* 107 */             .then((
/* 108 */               (RequiredArgumentBuilder)CommandDispatcher.<T>a("slot", ArgumentScoreboardSlot.a())
/* 109 */               .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), ArgumentScoreboardSlot.a(var0, "slot"))))
/* 110 */               .then(
/* 111 */                 CommandDispatcher.<T>a("objective", ArgumentScoreboardObjective.a())
/* 112 */                 .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), ArgumentScoreboardSlot.a(var0, "slot"), ArgumentScoreboardObjective.a(var0, "objective"))))))))
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 117 */         .then((
/* 118 */           (LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)CommandDispatcher.a("players")
/* 119 */           .then((
/* 120 */             (LiteralArgumentBuilder)CommandDispatcher.a("list")
/* 121 */             .executes(var0 -> a((CommandListenerWrapper)var0.getSource())))
/* 122 */             .then(
/* 123 */               CommandDispatcher.<T>a("target", ArgumentScoreholder.a())
/* 124 */               .suggests(ArgumentScoreholder.a)
/* 125 */               .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), ArgumentScoreholder.a(var0, "target"))))))
/*     */ 
/*     */           
/* 128 */           .then(
/* 129 */             CommandDispatcher.a("set")
/* 130 */             .then(
/* 131 */               CommandDispatcher.<T>a("targets", ArgumentScoreholder.b())
/* 132 */               .suggests(ArgumentScoreholder.a)
/* 133 */               .then(
/* 134 */                 CommandDispatcher.<T>a("objective", ArgumentScoreboardObjective.a())
/* 135 */                 .then(
/* 136 */                   CommandDispatcher.<T>a("score", (ArgumentType<T>)IntegerArgumentType.integer())
/* 137 */                   .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), ArgumentScoreholder.c(var0, "targets"), ArgumentScoreboardObjective.b(var0, "objective"), IntegerArgumentType.getInteger(var0, "score"))))))))
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 142 */           .then(
/* 143 */             CommandDispatcher.a("get")
/* 144 */             .then(
/* 145 */               CommandDispatcher.<T>a("target", ArgumentScoreholder.a())
/* 146 */               .suggests(ArgumentScoreholder.a)
/* 147 */               .then(
/* 148 */                 CommandDispatcher.<T>a("objective", ArgumentScoreboardObjective.a())
/* 149 */                 .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), ArgumentScoreholder.a(var0, "target"), ArgumentScoreboardObjective.a(var0, "objective")))))))
/*     */ 
/*     */ 
/*     */           
/* 153 */           .then(
/* 154 */             CommandDispatcher.a("add")
/* 155 */             .then(
/* 156 */               CommandDispatcher.<T>a("targets", ArgumentScoreholder.b())
/* 157 */               .suggests(ArgumentScoreholder.a)
/* 158 */               .then(
/* 159 */                 CommandDispatcher.<T>a("objective", ArgumentScoreboardObjective.a())
/* 160 */                 .then(
/* 161 */                   CommandDispatcher.<T>a("score", (ArgumentType<T>)IntegerArgumentType.integer(0))
/* 162 */                   .executes(var0 -> b((CommandListenerWrapper)var0.getSource(), ArgumentScoreholder.c(var0, "targets"), ArgumentScoreboardObjective.b(var0, "objective"), IntegerArgumentType.getInteger(var0, "score"))))))))
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 167 */           .then(
/* 168 */             CommandDispatcher.a("remove")
/* 169 */             .then(
/* 170 */               CommandDispatcher.<T>a("targets", ArgumentScoreholder.b())
/* 171 */               .suggests(ArgumentScoreholder.a)
/* 172 */               .then(
/* 173 */                 CommandDispatcher.<T>a("objective", ArgumentScoreboardObjective.a())
/* 174 */                 .then(
/* 175 */                   CommandDispatcher.<T>a("score", (ArgumentType<T>)IntegerArgumentType.integer(0))
/* 176 */                   .executes(var0 -> c((CommandListenerWrapper)var0.getSource(), ArgumentScoreholder.c(var0, "targets"), ArgumentScoreboardObjective.b(var0, "objective"), IntegerArgumentType.getInteger(var0, "score"))))))))
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 181 */           .then(
/* 182 */             CommandDispatcher.a("reset")
/* 183 */             .then((
/* 184 */               (RequiredArgumentBuilder)CommandDispatcher.<T>a("targets", ArgumentScoreholder.b())
/* 185 */               .suggests(ArgumentScoreholder.a)
/* 186 */               .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), ArgumentScoreholder.c(var0, "targets"))))
/* 187 */               .then(
/* 188 */                 CommandDispatcher.<T>a("objective", ArgumentScoreboardObjective.a())
/* 189 */                 .executes(var0 -> b((CommandListenerWrapper)var0.getSource(), ArgumentScoreholder.c(var0, "targets"), ArgumentScoreboardObjective.a(var0, "objective")))))))
/*     */ 
/*     */ 
/*     */           
/* 193 */           .then(
/* 194 */             CommandDispatcher.a("enable")
/* 195 */             .then(
/* 196 */               CommandDispatcher.<T>a("targets", ArgumentScoreholder.b())
/* 197 */               .suggests(ArgumentScoreholder.a)
/* 198 */               .then(
/* 199 */                 CommandDispatcher.<T>a("objective", ArgumentScoreboardObjective.a())
/* 200 */                 .suggests((var0, var1) -> a((CommandListenerWrapper)var0.getSource(), ArgumentScoreholder.c(var0, "targets"), var1))
/* 201 */                 .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), ArgumentScoreholder.c(var0, "targets"), ArgumentScoreboardObjective.a(var0, "objective")))))))
/*     */ 
/*     */ 
/*     */           
/* 205 */           .then(
/* 206 */             CommandDispatcher.a("operation")
/* 207 */             .then(
/* 208 */               CommandDispatcher.<T>a("targets", ArgumentScoreholder.b())
/* 209 */               .suggests(ArgumentScoreholder.a)
/* 210 */               .then(
/* 211 */                 CommandDispatcher.<T>a("targetObjective", ArgumentScoreboardObjective.a())
/* 212 */                 .then(
/* 213 */                   CommandDispatcher.<T>a("operation", ArgumentMathOperation.a())
/* 214 */                   .then(
/* 215 */                     CommandDispatcher.<T>a("source", ArgumentScoreholder.b())
/* 216 */                     .suggests(ArgumentScoreholder.a)
/* 217 */                     .then(
/* 218 */                       CommandDispatcher.<T>a("sourceObjective", ArgumentScoreboardObjective.a())
/* 219 */                       .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), ArgumentScoreholder.c(var0, "targets"), ArgumentScoreboardObjective.b(var0, "targetObjective"), ArgumentMathOperation.a(var0, "operation"), ArgumentScoreholder.c(var0, "source"), ArgumentScoreboardObjective.a(var0, "sourceObjective")))))))))));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static LiteralArgumentBuilder<CommandListenerWrapper> a() {
/* 231 */     LiteralArgumentBuilder<CommandListenerWrapper> var0 = CommandDispatcher.a("rendertype");
/*     */     
/* 233 */     for (IScoreboardCriteria.EnumScoreboardHealthDisplay var4 : IScoreboardCriteria.EnumScoreboardHealthDisplay.values()) {
/* 234 */       var0.then(CommandDispatcher.a(var4.a())
/* 235 */           .executes(var1 -> a((CommandListenerWrapper)var1.getSource(), ArgumentScoreboardObjective.a(var1, "objective"), var0)));
/*     */     }
/*     */     
/* 238 */     return var0;
/*     */   }
/*     */   
/*     */   private static CompletableFuture<Suggestions> a(CommandListenerWrapper var0, Collection<String> var1, SuggestionsBuilder var2) {
/* 242 */     List<String> var3 = Lists.newArrayList();
/* 243 */     Scoreboard var4 = var0.getServer().getScoreboard();
/*     */     
/* 245 */     for (ScoreboardObjective var6 : var4.getObjectives()) {
/* 246 */       if (var6.getCriteria() == IScoreboardCriteria.TRIGGER) {
/* 247 */         boolean var7 = false;
/* 248 */         for (String var9 : var1) {
/* 249 */           if (!var4.b(var9, var6) || var4.getPlayerScoreForObjective(var9, var6).g()) {
/* 250 */             var7 = true;
/*     */             break;
/*     */           } 
/*     */         } 
/* 254 */         if (var7) {
/* 255 */           var3.add(var6.getName());
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 260 */     return ICompletionProvider.b(var3, var2);
/*     */   }
/*     */   
/*     */   private static int a(CommandListenerWrapper var0, String var1, ScoreboardObjective var2) throws CommandSyntaxException {
/* 264 */     Scoreboard var3 = var0.getServer().getScoreboard();
/* 265 */     if (!var3.b(var1, var2)) {
/* 266 */       throw f.create(var2.getName(), var1);
/*     */     }
/*     */     
/* 269 */     ScoreboardScore var4 = var3.getPlayerScoreForObjective(var1, var2);
/* 270 */     var0.sendMessage(new ChatMessage("commands.scoreboard.players.get.success", new Object[] { var1, Integer.valueOf(var4.getScore()), var2.e() }), false);
/*     */     
/* 272 */     return var4.getScore();
/*     */   }
/*     */   
/*     */   private static int a(CommandListenerWrapper var0, Collection<String> var1, ScoreboardObjective var2, ArgumentMathOperation.a var3, Collection<String> var4, ScoreboardObjective var5) throws CommandSyntaxException {
/* 276 */     Scoreboard var6 = var0.getServer().getScoreboard();
/* 277 */     int var7 = 0;
/*     */     
/* 279 */     for (String var9 : var1) {
/* 280 */       ScoreboardScore var10 = var6.getPlayerScoreForObjective(var9, var2);
/* 281 */       for (String var12 : var4) {
/* 282 */         ScoreboardScore var13 = var6.getPlayerScoreForObjective(var12, var5);
/* 283 */         var3.apply(var10, var13);
/*     */       } 
/* 285 */       var7 += var10.getScore();
/*     */     } 
/*     */     
/* 288 */     if (var1.size() == 1) {
/* 289 */       var0.sendMessage(new ChatMessage("commands.scoreboard.players.operation.success.single", new Object[] { var2.e(), var1.iterator().next(), Integer.valueOf(var7) }), true);
/*     */     } else {
/* 291 */       var0.sendMessage(new ChatMessage("commands.scoreboard.players.operation.success.multiple", new Object[] { var2.e(), Integer.valueOf(var1.size()) }), true);
/*     */     } 
/*     */     
/* 294 */     return var7;
/*     */   }
/*     */   
/*     */   private static int a(CommandListenerWrapper var0, Collection<String> var1, ScoreboardObjective var2) throws CommandSyntaxException {
/* 298 */     if (var2.getCriteria() != IScoreboardCriteria.TRIGGER) {
/* 299 */       throw e.create();
/*     */     }
/* 301 */     Scoreboard var3 = var0.getServer().getScoreboard();
/*     */     
/* 303 */     int var4 = 0;
/*     */     
/* 305 */     for (String var6 : var1) {
/* 306 */       ScoreboardScore var7 = var3.getPlayerScoreForObjective(var6, var2);
/* 307 */       if (var7.g()) {
/* 308 */         var7.a(false);
/* 309 */         var4++;
/*     */       } 
/*     */     } 
/*     */     
/* 313 */     if (var4 == 0) {
/* 314 */       throw d.create();
/*     */     }
/*     */     
/* 317 */     if (var1.size() == 1) {
/* 318 */       var0.sendMessage(new ChatMessage("commands.scoreboard.players.enable.success.single", new Object[] { var2.e(), var1.iterator().next() }), true);
/*     */     } else {
/* 320 */       var0.sendMessage(new ChatMessage("commands.scoreboard.players.enable.success.multiple", new Object[] { var2.e(), Integer.valueOf(var1.size()) }), true);
/*     */     } 
/*     */     
/* 323 */     return var4;
/*     */   }
/*     */   
/*     */   private static int a(CommandListenerWrapper var0, Collection<String> var1) {
/* 327 */     Scoreboard var2 = var0.getServer().getScoreboard();
/*     */     
/* 329 */     for (String var4 : var1) {
/* 330 */       var2.resetPlayerScores(var4, null);
/*     */     }
/*     */     
/* 333 */     if (var1.size() == 1) {
/* 334 */       var0.sendMessage(new ChatMessage("commands.scoreboard.players.reset.all.single", new Object[] { var1.iterator().next() }), true);
/*     */     } else {
/* 336 */       var0.sendMessage(new ChatMessage("commands.scoreboard.players.reset.all.multiple", new Object[] { Integer.valueOf(var1.size()) }), true);
/*     */     } 
/*     */     
/* 339 */     return var1.size();
/*     */   }
/*     */   
/*     */   private static int b(CommandListenerWrapper var0, Collection<String> var1, ScoreboardObjective var2) {
/* 343 */     Scoreboard var3 = var0.getServer().getScoreboard();
/*     */     
/* 345 */     for (String var5 : var1) {
/* 346 */       var3.resetPlayerScores(var5, var2);
/*     */     }
/*     */     
/* 349 */     if (var1.size() == 1) {
/* 350 */       var0.sendMessage(new ChatMessage("commands.scoreboard.players.reset.specific.single", new Object[] { var2.e(), var1.iterator().next() }), true);
/*     */     } else {
/* 352 */       var0.sendMessage(new ChatMessage("commands.scoreboard.players.reset.specific.multiple", new Object[] { var2.e(), Integer.valueOf(var1.size()) }), true);
/*     */     } 
/*     */     
/* 355 */     return var1.size();
/*     */   }
/*     */   
/*     */   private static int a(CommandListenerWrapper var0, Collection<String> var1, ScoreboardObjective var2, int var3) {
/* 359 */     Scoreboard var4 = var0.getServer().getScoreboard();
/*     */     
/* 361 */     for (String var6 : var1) {
/* 362 */       ScoreboardScore var7 = var4.getPlayerScoreForObjective(var6, var2);
/* 363 */       var7.setScore(var3);
/*     */     } 
/*     */     
/* 366 */     if (var1.size() == 1) {
/* 367 */       var0.sendMessage(new ChatMessage("commands.scoreboard.players.set.success.single", new Object[] { var2.e(), var1.iterator().next(), Integer.valueOf(var3) }), true);
/*     */     } else {
/* 369 */       var0.sendMessage(new ChatMessage("commands.scoreboard.players.set.success.multiple", new Object[] { var2.e(), Integer.valueOf(var1.size()), Integer.valueOf(var3) }), true);
/*     */     } 
/*     */     
/* 372 */     return var3 * var1.size();
/*     */   }
/*     */   
/*     */   private static int b(CommandListenerWrapper var0, Collection<String> var1, ScoreboardObjective var2, int var3) {
/* 376 */     Scoreboard var4 = var0.getServer().getScoreboard();
/* 377 */     int var5 = 0;
/*     */     
/* 379 */     for (String var7 : var1) {
/* 380 */       ScoreboardScore var8 = var4.getPlayerScoreForObjective(var7, var2);
/* 381 */       var8.setScore(var8.getScore() + var3);
/* 382 */       var5 += var8.getScore();
/*     */     } 
/*     */     
/* 385 */     if (var1.size() == 1) {
/* 386 */       var0.sendMessage(new ChatMessage("commands.scoreboard.players.add.success.single", new Object[] { Integer.valueOf(var3), var2.e(), var1.iterator().next(), Integer.valueOf(var5) }), true);
/*     */     } else {
/* 388 */       var0.sendMessage(new ChatMessage("commands.scoreboard.players.add.success.multiple", new Object[] { Integer.valueOf(var3), var2.e(), Integer.valueOf(var1.size()) }), true);
/*     */     } 
/*     */     
/* 391 */     return var5;
/*     */   }
/*     */   
/*     */   private static int c(CommandListenerWrapper var0, Collection<String> var1, ScoreboardObjective var2, int var3) {
/* 395 */     Scoreboard var4 = var0.getServer().getScoreboard();
/* 396 */     int var5 = 0;
/*     */     
/* 398 */     for (String var7 : var1) {
/* 399 */       ScoreboardScore var8 = var4.getPlayerScoreForObjective(var7, var2);
/* 400 */       var8.setScore(var8.getScore() - var3);
/* 401 */       var5 += var8.getScore();
/*     */     } 
/*     */     
/* 404 */     if (var1.size() == 1) {
/* 405 */       var0.sendMessage(new ChatMessage("commands.scoreboard.players.remove.success.single", new Object[] { Integer.valueOf(var3), var2.e(), var1.iterator().next(), Integer.valueOf(var5) }), true);
/*     */     } else {
/* 407 */       var0.sendMessage(new ChatMessage("commands.scoreboard.players.remove.success.multiple", new Object[] { Integer.valueOf(var3), var2.e(), Integer.valueOf(var1.size()) }), true);
/*     */     } 
/*     */     
/* 410 */     return var5;
/*     */   }
/*     */   
/*     */   private static int a(CommandListenerWrapper var0) {
/* 414 */     Collection<String> var1 = var0.getServer().getScoreboard().getPlayers();
/*     */     
/* 416 */     if (var1.isEmpty()) {
/* 417 */       var0.sendMessage(new ChatMessage("commands.scoreboard.players.list.empty"), false);
/*     */     } else {
/* 419 */       var0.sendMessage(new ChatMessage("commands.scoreboard.players.list.success", new Object[] { Integer.valueOf(var1.size()), ChatComponentUtils.a(var1) }), false);
/*     */     } 
/*     */     
/* 422 */     return var1.size();
/*     */   }
/*     */   
/*     */   private static int a(CommandListenerWrapper var0, String var1) {
/* 426 */     Map<ScoreboardObjective, ScoreboardScore> var2 = var0.getServer().getScoreboard().getPlayerObjectives(var1);
/*     */     
/* 428 */     if (var2.isEmpty()) {
/* 429 */       var0.sendMessage(new ChatMessage("commands.scoreboard.players.list.entity.empty", new Object[] { var1 }), false);
/*     */     } else {
/* 431 */       var0.sendMessage(new ChatMessage("commands.scoreboard.players.list.entity.success", new Object[] { var1, Integer.valueOf(var2.size()) }), false);
/* 432 */       for (Map.Entry<ScoreboardObjective, ScoreboardScore> var4 : var2.entrySet()) {
/* 433 */         var0.sendMessage(new ChatMessage("commands.scoreboard.players.list.entity.entry", new Object[] { ((ScoreboardObjective)var4.getKey()).e(), Integer.valueOf(((ScoreboardScore)var4.getValue()).getScore()) }), false);
/*     */       } 
/*     */     } 
/*     */     
/* 437 */     return var2.size();
/*     */   }
/*     */   
/*     */   private static int a(CommandListenerWrapper var0, int var1) throws CommandSyntaxException {
/* 441 */     Scoreboard var2 = var0.getServer().getScoreboard();
/*     */     
/* 443 */     if (var2.getObjectiveForSlot(var1) == null) {
/* 444 */       throw b.create();
/*     */     }
/*     */     
/* 447 */     var2.setDisplaySlot(var1, null);
/* 448 */     var0.sendMessage(new ChatMessage("commands.scoreboard.objectives.display.cleared", new Object[] { Scoreboard.h()[var1] }), true);
/*     */     
/* 450 */     return 0;
/*     */   }
/*     */   
/*     */   private static int a(CommandListenerWrapper var0, int var1, ScoreboardObjective var2) throws CommandSyntaxException {
/* 454 */     Scoreboard var3 = var0.getServer().getScoreboard();
/*     */     
/* 456 */     if (var3.getObjectiveForSlot(var1) == var2) {
/* 457 */       throw c.create();
/*     */     }
/*     */     
/* 460 */     var3.setDisplaySlot(var1, var2);
/* 461 */     var0.sendMessage(new ChatMessage("commands.scoreboard.objectives.display.set", new Object[] { Scoreboard.h()[var1], var2.getDisplayName() }), true);
/*     */     
/* 463 */     return 0;
/*     */   }
/*     */   
/*     */   private static int a(CommandListenerWrapper var0, ScoreboardObjective var1, IChatBaseComponent var2) {
/* 467 */     if (!var1.getDisplayName().equals(var2)) {
/* 468 */       var1.setDisplayName(var2);
/* 469 */       var0.sendMessage(new ChatMessage("commands.scoreboard.objectives.modify.displayname", new Object[] { var1.getName(), var1.e() }), true);
/*     */     } 
/*     */     
/* 472 */     return 0;
/*     */   }
/*     */   
/*     */   private static int a(CommandListenerWrapper var0, ScoreboardObjective var1, IScoreboardCriteria.EnumScoreboardHealthDisplay var2) {
/* 476 */     if (var1.getRenderType() != var2) {
/* 477 */       var1.setRenderType(var2);
/* 478 */       var0.sendMessage(new ChatMessage("commands.scoreboard.objectives.modify.rendertype", new Object[] { var1.e() }), true);
/*     */     } 
/*     */     
/* 481 */     return 0;
/*     */   }
/*     */   
/*     */   private static int a(CommandListenerWrapper var0, ScoreboardObjective var1) {
/* 485 */     Scoreboard var2 = var0.getServer().getScoreboard();
/* 486 */     var2.unregisterObjective(var1);
/* 487 */     var0.sendMessage(new ChatMessage("commands.scoreboard.objectives.remove.success", new Object[] { var1.e() }), true);
/* 488 */     return var2.getObjectives().size();
/*     */   }
/*     */   
/*     */   private static int a(CommandListenerWrapper var0, String var1, IScoreboardCriteria var2, IChatBaseComponent var3) throws CommandSyntaxException {
/* 492 */     Scoreboard var4 = var0.getServer().getScoreboard();
/*     */     
/* 494 */     if (var4.getObjective(var1) != null) {
/* 495 */       throw a.create();
/*     */     }
/* 497 */     if (var1.length() > 16) {
/* 498 */       throw ArgumentScoreboardObjective.a.create(Integer.valueOf(16));
/*     */     }
/*     */     
/* 501 */     var4.registerObjective(var1, var2, var3, var2.e());
/* 502 */     ScoreboardObjective var5 = var4.getObjective(var1);
/*     */     
/* 504 */     var0.sendMessage(new ChatMessage("commands.scoreboard.objectives.add.success", new Object[] { var5.e() }), true);
/*     */     
/* 506 */     return var4.getObjectives().size();
/*     */   }
/*     */   
/*     */   private static int b(CommandListenerWrapper var0) {
/* 510 */     Collection<ScoreboardObjective> var1 = var0.getServer().getScoreboard().getObjectives();
/*     */     
/* 512 */     if (var1.isEmpty()) {
/* 513 */       var0.sendMessage(new ChatMessage("commands.scoreboard.objectives.list.empty"), false);
/*     */     } else {
/* 515 */       var0.sendMessage(new ChatMessage("commands.scoreboard.objectives.list.success", new Object[] { Integer.valueOf(var1.size()), ChatComponentUtils.b(var1, ScoreboardObjective::e) }), false);
/*     */     } 
/*     */     
/* 518 */     return var1.size();
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CommandScoreboard.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
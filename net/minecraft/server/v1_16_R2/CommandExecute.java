/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.mojang.brigadier.Command;
/*     */ import com.mojang.brigadier.CommandDispatcher;
/*     */ import com.mojang.brigadier.Message;
/*     */ import com.mojang.brigadier.ResultConsumer;
/*     */ import com.mojang.brigadier.arguments.ArgumentType;
/*     */ import com.mojang.brigadier.arguments.DoubleArgumentType;
/*     */ import com.mojang.brigadier.builder.ArgumentBuilder;
/*     */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*     */ import com.mojang.brigadier.builder.RequiredArgumentBuilder;
/*     */ import com.mojang.brigadier.context.CommandContext;
/*     */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*     */ import com.mojang.brigadier.exceptions.Dynamic2CommandExceptionType;
/*     */ import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
/*     */ import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
/*     */ import com.mojang.brigadier.suggestion.SuggestionProvider;
/*     */ import com.mojang.brigadier.suggestion.SuggestionsBuilder;
/*     */ import com.mojang.brigadier.tree.CommandNode;
/*     */ import com.mojang.brigadier.tree.LiteralCommandNode;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.OptionalInt;
/*     */ import java.util.concurrent.CompletableFuture;
/*     */ import java.util.function.BiPredicate;
/*     */ import java.util.function.BinaryOperator;
/*     */ import java.util.function.IntFunction;
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
/*     */ 
/*     */ public class CommandExecute
/*     */ {
/*     */   private static final Dynamic2CommandExceptionType a;
/*     */   
/*     */   static {
/*  97 */     a = new Dynamic2CommandExceptionType((var0, var1) -> new ChatMessage("commands.execute.blocks.toobig", new Object[] { var0, var1 }));
/*     */   }
/*  99 */   private static final SimpleCommandExceptionType b = new SimpleCommandExceptionType(new ChatMessage("commands.execute.conditional.fail")); private static final DynamicCommandExceptionType c; static {
/* 100 */     c = new DynamicCommandExceptionType(var0 -> new ChatMessage("commands.execute.conditional.fail_count", new Object[] { var0 }));
/*     */   }
/*     */   
/*     */   private static final BinaryOperator<ResultConsumer<CommandListenerWrapper>> d = (var0, var1) -> ();
/*     */   private static final SuggestionProvider<CommandListenerWrapper> e;
/*     */   
/*     */   static {
/* 107 */     e = ((var0, var1) -> {
/*     */         LootPredicateManager var2 = ((CommandListenerWrapper)var0.getSource()).getServer().getLootPredicateManager();
/*     */         return ICompletionProvider.a(var2.a(), var1);
/*     */       });
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
/*     */   
/*     */   public static void a(CommandDispatcher<CommandListenerWrapper> var0) {
/* 123 */     LiteralCommandNode<CommandListenerWrapper> var1 = var0.register((LiteralArgumentBuilder)CommandDispatcher.a("execute").requires(var0 -> var0.hasPermission(2)));
/*     */     
/* 125 */     var0.register(
/* 126 */         (LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)CommandDispatcher.a("execute")
/* 127 */         .requires(var0 -> var0.hasPermission(2)))
/* 128 */         .then(
/* 129 */           CommandDispatcher.a("run")
/* 130 */           .redirect((CommandNode)var0.getRoot())))
/*     */         
/* 132 */         .then(
/* 133 */           a((CommandNode<CommandListenerWrapper>)var1, CommandDispatcher.a("if"), true)))
/*     */         
/* 135 */         .then(
/* 136 */           a((CommandNode<CommandListenerWrapper>)var1, CommandDispatcher.a("unless"), false)))
/*     */         
/* 138 */         .then(
/* 139 */           CommandDispatcher.a("as")
/* 140 */           .then(
/* 141 */             CommandDispatcher.<T>a("targets", ArgumentEntity.multipleEntities())
/* 142 */             .fork((CommandNode)var1, var0 -> {
/*     */                 List<CommandListenerWrapper> var1 = Lists.newArrayList();
/*     */ 
/*     */                 
/*     */                 for (Entity var3 : ArgumentEntity.c(var0, "targets")) {
/*     */                   var1.add(((CommandListenerWrapper)var0.getSource()).a(var3));
/*     */                 }
/*     */                 
/*     */                 return var1;
/* 151 */               })))).then(
/* 152 */           CommandDispatcher.a("at")
/* 153 */           .then(
/* 154 */             CommandDispatcher.<T>a("targets", ArgumentEntity.multipleEntities())
/* 155 */             .fork((CommandNode)var1, var0 -> {
/*     */                 List<CommandListenerWrapper> var1 = Lists.newArrayList();
/*     */ 
/*     */                 
/*     */                 for (Entity var3 : ArgumentEntity.c(var0, "targets")) {
/*     */                   var1.add(((CommandListenerWrapper)var0.getSource()).a((WorldServer)var3.world).a(var3.getPositionVector()).a(var3.bh()));
/*     */                 }
/*     */                 
/*     */                 return var1;
/* 164 */               })))).then((
/* 165 */           (LiteralArgumentBuilder)CommandDispatcher.a("store")
/* 166 */           .then(a(var1, CommandDispatcher.a("result"), true)))
/* 167 */           .then(a(var1, CommandDispatcher.a("success"), false))))
/*     */         
/* 169 */         .then((
/* 170 */           (LiteralArgumentBuilder)CommandDispatcher.a("positioned")
/* 171 */           .then(
/* 172 */             CommandDispatcher.<T>a("pos", ArgumentVec3.a())
/* 173 */             .redirect((CommandNode)var1, var0 -> ((CommandListenerWrapper)var0.getSource()).a(ArgumentVec3.a(var0, "pos")).a(ArgumentAnchor.Anchor.FEET))))
/*     */           
/* 175 */           .then(
/* 176 */             CommandDispatcher.a("as")
/* 177 */             .then(
/* 178 */               CommandDispatcher.<T>a("targets", ArgumentEntity.multipleEntities())
/* 179 */               .fork((CommandNode)var1, var0 -> {
/*     */                   List<CommandListenerWrapper> var1 = Lists.newArrayList();
/*     */ 
/*     */                   
/*     */                   for (Entity var3 : ArgumentEntity.c(var0, "targets")) {
/*     */                     var1.add(((CommandListenerWrapper)var0.getSource()).a(var3.getPositionVector()));
/*     */                   }
/*     */ 
/*     */                   
/*     */                   return var1;
/* 189 */                 }))))).then((
/* 190 */           (LiteralArgumentBuilder)CommandDispatcher.a("rotated")
/* 191 */           .then(
/* 192 */             CommandDispatcher.<T>a("rot", ArgumentRotation.a())
/* 193 */             .redirect((CommandNode)var1, var0 -> ((CommandListenerWrapper)var0.getSource()).a(ArgumentRotation.a(var0, "rot").b((CommandListenerWrapper)var0.getSource())))))
/*     */           
/* 195 */           .then(
/* 196 */             CommandDispatcher.a("as")
/* 197 */             .then(
/* 198 */               CommandDispatcher.<T>a("targets", ArgumentEntity.multipleEntities())
/* 199 */               .fork((CommandNode)var1, var0 -> {
/*     */                   List<CommandListenerWrapper> var1 = Lists.newArrayList();
/*     */ 
/*     */                   
/*     */                   for (Entity var3 : ArgumentEntity.c(var0, "targets")) {
/*     */                     var1.add(((CommandListenerWrapper)var0.getSource()).a(var3.bh()));
/*     */                   }
/*     */ 
/*     */                   
/*     */                   return var1;
/* 209 */                 }))))).then((
/* 210 */           (LiteralArgumentBuilder)CommandDispatcher.a("facing")
/* 211 */           .then(
/* 212 */             CommandDispatcher.a("entity")
/* 213 */             .then(
/* 214 */               CommandDispatcher.<T>a("targets", ArgumentEntity.multipleEntities())
/* 215 */               .then(
/* 216 */                 CommandDispatcher.<T>a("anchor", ArgumentAnchor.a())
/* 217 */                 .fork((CommandNode)var1, var0 -> {
/*     */                     List<CommandListenerWrapper> var1 = Lists.newArrayList();
/*     */ 
/*     */                     
/*     */                     ArgumentAnchor.Anchor var2 = ArgumentAnchor.a(var0, "anchor");
/*     */                     
/*     */                     for (Entity var4 : ArgumentEntity.c(var0, "targets")) {
/*     */                       var1.add(((CommandListenerWrapper)var0.getSource()).a(var4, var2));
/*     */                     }
/*     */                     
/*     */                     return var1;
/* 228 */                   }))))).then(
/* 229 */             CommandDispatcher.<T>a("pos", ArgumentVec3.a())
/* 230 */             .redirect((CommandNode)var1, var0 -> ((CommandListenerWrapper)var0.getSource()).b(ArgumentVec3.a(var0, "pos"))))))
/*     */ 
/*     */         
/* 233 */         .then(
/* 234 */           CommandDispatcher.a("align")
/* 235 */           .then(
/* 236 */             CommandDispatcher.<T>a("axes", ArgumentRotationAxis.a())
/* 237 */             .redirect((CommandNode)var1, var0 -> ((CommandListenerWrapper)var0.getSource()).a(((CommandListenerWrapper)var0.getSource()).getPosition().a(ArgumentRotationAxis.a(var0, "axes")))))))
/*     */ 
/*     */         
/* 240 */         .then(
/* 241 */           CommandDispatcher.a("anchored")
/* 242 */           .then(
/* 243 */             CommandDispatcher.<T>a("anchor", ArgumentAnchor.a())
/* 244 */             .redirect((CommandNode)var1, var0 -> ((CommandListenerWrapper)var0.getSource()).a(ArgumentAnchor.a(var0, "anchor"))))))
/*     */ 
/*     */         
/* 247 */         .then(
/* 248 */           CommandDispatcher.a("in")
/* 249 */           .then(
/* 250 */             CommandDispatcher.<T>a("dimension", ArgumentDimension.a())
/* 251 */             .redirect((CommandNode)var1, var0 -> ((CommandListenerWrapper)var0.getSource()).a(ArgumentDimension.a(var0, "dimension"))))));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static ArgumentBuilder<CommandListenerWrapper, ?> a(LiteralCommandNode<CommandListenerWrapper> var0, LiteralArgumentBuilder<CommandListenerWrapper> var1, boolean var2) {
/* 258 */     var1.then(
/* 259 */         CommandDispatcher.a("score")
/* 260 */         .then(
/* 261 */           CommandDispatcher.<T>a("targets", ArgumentScoreholder.b())
/* 262 */           .suggests(ArgumentScoreholder.a)
/* 263 */           .then(
/* 264 */             CommandDispatcher.<T>a("objective", ArgumentScoreboardObjective.a())
/* 265 */             .redirect((CommandNode)var0, var1 -> a((CommandListenerWrapper)var1.getSource(), ArgumentScoreholder.c(var1, "targets"), ArgumentScoreboardObjective.a(var1, "objective"), var0)))));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 270 */     var1.then(
/* 271 */         CommandDispatcher.a("bossbar")
/* 272 */         .then((
/* 273 */           (RequiredArgumentBuilder)CommandDispatcher.<T>a("id", ArgumentMinecraftKeyRegistered.a())
/* 274 */           .suggests(CommandBossBar.a)
/* 275 */           .then(
/* 276 */             CommandDispatcher.a("value")
/* 277 */             .redirect((CommandNode)var0, var1 -> a((CommandListenerWrapper)var1.getSource(), CommandBossBar.a(var1), true, var0))))
/*     */           
/* 279 */           .then(
/* 280 */             CommandDispatcher.a("max")
/* 281 */             .redirect((CommandNode)var0, var1 -> a((CommandListenerWrapper)var1.getSource(), CommandBossBar.a(var1), false, var0)))));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 286 */     for (Iterator<CommandData.c> iterator = CommandData.b.iterator(); iterator.hasNext(); ) { CommandData.c var4 = iterator.next();
/* 287 */       var4.a((ArgumentBuilder)var1, var3 -> var3.then(((RequiredArgumentBuilder)((RequiredArgumentBuilder)((RequiredArgumentBuilder)((RequiredArgumentBuilder)((RequiredArgumentBuilder)CommandDispatcher.<T>a("path", ArgumentNBTKey.a()).then(CommandDispatcher.a("int").then(CommandDispatcher.<T>a("scale", (ArgumentType<T>)DoubleArgumentType.doubleArg()).redirect((CommandNode)var0, ())))).then(CommandDispatcher.a("float").then(CommandDispatcher.<T>a("scale", (ArgumentType<T>)DoubleArgumentType.doubleArg()).redirect((CommandNode)var0, ())))).then(CommandDispatcher.a("short").then(CommandDispatcher.<T>a("scale", (ArgumentType<T>)DoubleArgumentType.doubleArg()).redirect((CommandNode)var0, ())))).then(CommandDispatcher.a("long").then(CommandDispatcher.<T>a("scale", (ArgumentType<T>)DoubleArgumentType.doubleArg()).redirect((CommandNode)var0, ())))).then(CommandDispatcher.a("double").then(CommandDispatcher.<T>a("scale", (ArgumentType<T>)DoubleArgumentType.doubleArg()).redirect((CommandNode)var0, ())))).then(CommandDispatcher.a("byte").then(CommandDispatcher.<T>a("scale", (ArgumentType<T>)DoubleArgumentType.doubleArg()).redirect((CommandNode)var0, ()))))); }
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
/* 341 */     return (ArgumentBuilder)var1;
/*     */   }
/*     */   
/*     */   private static CommandListenerWrapper a(CommandListenerWrapper var0, Collection<String> var1, ScoreboardObjective var2, boolean var3) {
/* 345 */     Scoreboard var4 = var0.getServer().getScoreboard();
/*     */     
/* 347 */     return var0.a((var4, var5, var6) -> { for (String var8 : var0) { ScoreboardScore var9 = var1.getPlayerScoreForObjective(var8, var2); int var10 = var3 ? var6 : (var5 ? 1 : 0); var9.setScore(var10); }  }d);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static CommandListenerWrapper a(CommandListenerWrapper var0, BossBattleCustom var1, boolean var2, boolean var3) {
/* 357 */     return var0.a((var3, var4, var5) -> { int var6 = var0 ? var5 : (var4 ? 1 : 0); if (var1) { var2.a(var6); } else { var2.b(var6); }  }d);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static CommandListenerWrapper a(CommandListenerWrapper var0, CommandDataAccessor var1, ArgumentNBTKey.h var2, IntFunction<NBTBase> var3, boolean var4) {
/* 368 */     return var0.a((var4, var5, var6) -> {
/*     */           try {
/*     */             NBTTagCompound var7 = var0.a();
/*     */             int var8 = var1 ? var6 : (var5 ? 1 : 0);
/*     */             var2.b(var7, ());
/*     */             var0.a(var7);
/* 374 */           } catch (CommandSyntaxException commandSyntaxException) {}
/*     */         }d);
/*     */   }
/*     */ 
/*     */   
/*     */   private static ArgumentBuilder<CommandListenerWrapper, ?> a(CommandNode<CommandListenerWrapper> var0, LiteralArgumentBuilder<CommandListenerWrapper> var1, boolean var2) {
/* 380 */     ((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)var1
/* 381 */       .then(
/* 382 */         CommandDispatcher.a("block")
/* 383 */         .then(
/* 384 */           CommandDispatcher.<T>a("pos", ArgumentPosition.a())
/* 385 */           .then(
/* 386 */             a(var0, (ArgumentBuilder<CommandListenerWrapper, ?>)CommandDispatcher.a("block", ArgumentBlockPredicate.a()), var2, var0 -> ArgumentBlockPredicate.a(var0, "block").test(new ShapeDetectorBlock(((CommandListenerWrapper)var0.getSource()).getWorld(), ArgumentPosition.a(var0, "pos"), true)))))))
/*     */ 
/*     */ 
/*     */       
/* 390 */       .then(
/* 391 */         CommandDispatcher.a("score")
/* 392 */         .then(
/* 393 */           CommandDispatcher.<T>a("target", ArgumentScoreholder.a())
/* 394 */           .suggests(ArgumentScoreholder.a)
/* 395 */           .then((
/* 396 */             (RequiredArgumentBuilder)((RequiredArgumentBuilder)((RequiredArgumentBuilder)((RequiredArgumentBuilder)((RequiredArgumentBuilder)CommandDispatcher.<T>a("targetObjective", ArgumentScoreboardObjective.a())
/* 397 */             .then(
/* 398 */               CommandDispatcher.a("=")
/* 399 */               .then(
/* 400 */                 CommandDispatcher.<T>a("source", ArgumentScoreholder.a())
/* 401 */                 .suggests(ArgumentScoreholder.a)
/* 402 */                 .then(
/* 403 */                   a(var0, (ArgumentBuilder<CommandListenerWrapper, ?>)CommandDispatcher.a("sourceObjective", ArgumentScoreboardObjective.a()), var2, var0 -> a(var0, Integer::equals))))))
/*     */ 
/*     */ 
/*     */             
/* 407 */             .then(
/* 408 */               CommandDispatcher.a("<")
/* 409 */               .then(
/* 410 */                 CommandDispatcher.<T>a("source", ArgumentScoreholder.a())
/* 411 */                 .suggests(ArgumentScoreholder.a)
/* 412 */                 .then(
/* 413 */                   a(var0, (ArgumentBuilder<CommandListenerWrapper, ?>)CommandDispatcher.a("sourceObjective", ArgumentScoreboardObjective.a()), var2, var0 -> a(var0, ()))))))
/*     */ 
/*     */ 
/*     */             
/* 417 */             .then(
/* 418 */               CommandDispatcher.a("<=")
/* 419 */               .then(
/* 420 */                 CommandDispatcher.<T>a("source", ArgumentScoreholder.a())
/* 421 */                 .suggests(ArgumentScoreholder.a)
/* 422 */                 .then(
/* 423 */                   a(var0, (ArgumentBuilder<CommandListenerWrapper, ?>)CommandDispatcher.a("sourceObjective", ArgumentScoreboardObjective.a()), var2, var0 -> a(var0, ()))))))
/*     */ 
/*     */ 
/*     */             
/* 427 */             .then(
/* 428 */               CommandDispatcher.a(">")
/* 429 */               .then(
/* 430 */                 CommandDispatcher.<T>a("source", ArgumentScoreholder.a())
/* 431 */                 .suggests(ArgumentScoreholder.a)
/* 432 */                 .then(
/* 433 */                   a(var0, (ArgumentBuilder<CommandListenerWrapper, ?>)CommandDispatcher.a("sourceObjective", ArgumentScoreboardObjective.a()), var2, var0 -> a(var0, ()))))))
/*     */ 
/*     */ 
/*     */             
/* 437 */             .then(
/* 438 */               CommandDispatcher.a(">=")
/* 439 */               .then(
/* 440 */                 CommandDispatcher.<T>a("source", ArgumentScoreholder.a())
/* 441 */                 .suggests(ArgumentScoreholder.a)
/* 442 */                 .then(
/* 443 */                   a(var0, (ArgumentBuilder<CommandListenerWrapper, ?>)CommandDispatcher.a("sourceObjective", ArgumentScoreboardObjective.a()), var2, var0 -> a(var0, ()))))))
/*     */ 
/*     */ 
/*     */             
/* 447 */             .then(
/* 448 */               CommandDispatcher.a("matches")
/* 449 */               .then(
/* 450 */                 a(var0, (ArgumentBuilder<CommandListenerWrapper, ?>)CommandDispatcher.a("range", ArgumentCriterionValue.a()), var2, var0 -> a(var0, ArgumentCriterionValue.b.a(var0, "range")))))))))
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 456 */       .then(
/* 457 */         CommandDispatcher.a("blocks")
/* 458 */         .then(
/* 459 */           CommandDispatcher.<T>a("start", ArgumentPosition.a())
/* 460 */           .then(
/* 461 */             CommandDispatcher.<T>a("end", ArgumentPosition.a())
/* 462 */             .then((
/* 463 */               (RequiredArgumentBuilder)CommandDispatcher.<T>a("destination", ArgumentPosition.a())
/* 464 */               .then(
/* 465 */                 a(var0, (ArgumentBuilder)CommandDispatcher.a("all"), var2, false)))
/*     */               
/* 467 */               .then(
/* 468 */                 a(var0, (ArgumentBuilder)CommandDispatcher.a("masked"), var2, true)))))))
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 474 */       .then(
/* 475 */         CommandDispatcher.a("entity")
/* 476 */         .then((
/* 477 */           (RequiredArgumentBuilder)CommandDispatcher.<T>a("entities", ArgumentEntity.multipleEntities())
/* 478 */           .fork(var0, var1 -> a(var1, var0, !ArgumentEntity.c(var1, "entities").isEmpty())))
/* 479 */           .executes(a(var2, var0 -> ArgumentEntity.c(var0, "entities").size())))))
/*     */ 
/*     */ 
/*     */       
/* 483 */       .then(
/* 484 */         CommandDispatcher.a("predicate")
/* 485 */         .then(
/* 486 */           a(var0, (ArgumentBuilder<CommandListenerWrapper, ?>)CommandDispatcher.<T>a("predicate", ArgumentMinecraftKeyRegistered.a()).suggests(e), var2, var0 -> a((CommandListenerWrapper)var0.getSource(), ArgumentMinecraftKeyRegistered.c(var0, "predicate")))));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 491 */     for (Iterator<CommandData.c> iterator = CommandData.c.iterator(); iterator.hasNext(); ) { CommandData.c var4 = iterator.next();
/* 492 */       var1
/* 493 */         .then(var4
/* 494 */           .a((ArgumentBuilder)CommandDispatcher.a("data"), var3 -> var3.then(((RequiredArgumentBuilder)CommandDispatcher.<T>a("path", ArgumentNBTKey.a()).fork(var0, ())).executes(a(var1, ()))))); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 504 */     return (ArgumentBuilder)var1;
/*     */   }
/*     */   
/*     */   private static Command<CommandListenerWrapper> a(boolean var0, a var1) {
/* 508 */     if (var0) {
/* 509 */       return var1 -> {
/*     */           int var2 = var0.test(var1);
/*     */           
/*     */           if (var2 > 0) {
/*     */             ((CommandListenerWrapper)var1.getSource()).sendMessage(new ChatMessage("commands.execute.conditional.pass_count", new Object[] { Integer.valueOf(var2) }), false);
/*     */             return var2;
/*     */           } 
/*     */           throw b.create();
/*     */         };
/*     */     }
/* 519 */     return var1 -> {
/*     */         int var2 = var0.test(var1);
/*     */         if (var2 == 0) {
/*     */           ((CommandListenerWrapper)var1.getSource()).sendMessage(new ChatMessage("commands.execute.conditional.pass"), false);
/*     */           return 1;
/*     */         } 
/*     */         throw c.create(Integer.valueOf(var2));
/*     */       };
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static int a(CommandDataAccessor var0, ArgumentNBTKey.h var1) throws CommandSyntaxException {
/* 532 */     return var1.b(var0.a());
/*     */   }
/*     */   
/*     */   private static boolean a(CommandContext<CommandListenerWrapper> var0, BiPredicate<Integer, Integer> var1) throws CommandSyntaxException {
/* 536 */     String var2 = ArgumentScoreholder.a(var0, "target");
/* 537 */     ScoreboardObjective var3 = ArgumentScoreboardObjective.a(var0, "targetObjective");
/* 538 */     String var4 = ArgumentScoreholder.a(var0, "source");
/* 539 */     ScoreboardObjective var5 = ArgumentScoreboardObjective.a(var0, "sourceObjective");
/*     */     
/* 541 */     Scoreboard var6 = ((CommandListenerWrapper)var0.getSource()).getServer().getScoreboard();
/*     */     
/* 543 */     if (!var6.b(var2, var3) || !var6.b(var4, var5)) {
/* 544 */       return false;
/*     */     }
/*     */     
/* 547 */     ScoreboardScore var7 = var6.getPlayerScoreForObjective(var2, var3);
/* 548 */     ScoreboardScore var8 = var6.getPlayerScoreForObjective(var4, var5);
/* 549 */     return var1.test(Integer.valueOf(var7.getScore()), Integer.valueOf(var8.getScore()));
/*     */   }
/*     */   
/*     */   private static boolean a(CommandContext<CommandListenerWrapper> var0, CriterionConditionValue.IntegerRange var1) throws CommandSyntaxException {
/* 553 */     String var2 = ArgumentScoreholder.a(var0, "target");
/* 554 */     ScoreboardObjective var3 = ArgumentScoreboardObjective.a(var0, "targetObjective");
/*     */     
/* 556 */     Scoreboard var4 = ((CommandListenerWrapper)var0.getSource()).getServer().getScoreboard();
/*     */     
/* 558 */     if (!var4.b(var2, var3)) {
/* 559 */       return false;
/*     */     }
/*     */     
/* 562 */     return var1.d(var4.getPlayerScoreForObjective(var2, var3).getScore());
/*     */   }
/*     */   
/*     */   private static boolean a(CommandListenerWrapper var0, LootItemCondition var1) {
/* 566 */     WorldServer var2 = var0.getWorld();
/*     */ 
/*     */ 
/*     */     
/* 570 */     LootTableInfo.Builder var3 = (new LootTableInfo.Builder(var2)).<Vec3D>set(LootContextParameters.ORIGIN, var0.getPosition()).setOptional(LootContextParameters.THIS_ENTITY, var0.getEntity());
/*     */     
/* 572 */     return var1.test(var3.build(LootContextParameterSets.COMMAND));
/*     */   }
/*     */   
/*     */   private static Collection<CommandListenerWrapper> a(CommandContext<CommandListenerWrapper> var0, boolean var1, boolean var2) {
/* 576 */     if (var2 == var1) {
/* 577 */       return Collections.singleton(var0.getSource());
/*     */     }
/* 579 */     return Collections.emptyList();
/*     */   }
/*     */ 
/*     */   
/*     */   private static ArgumentBuilder<CommandListenerWrapper, ?> a(CommandNode<CommandListenerWrapper> var0, ArgumentBuilder<CommandListenerWrapper, ?> var1, boolean var2, b var3) {
/* 584 */     return var1
/* 585 */       .fork(var0, var2 -> a(var2, var0, var1.test(var2)))
/* 586 */       .executes(var2 -> {
/*     */           if (var0 == var1.test(var2)) {
/*     */             ((CommandListenerWrapper)var2.getSource()).sendMessage(new ChatMessage("commands.execute.conditional.pass"), false);
/*     */             return 1;
/*     */           } 
/*     */           throw b.create();
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   private static ArgumentBuilder<CommandListenerWrapper, ?> a(CommandNode<CommandListenerWrapper> var0, ArgumentBuilder<CommandListenerWrapper, ?> var1, boolean var2, boolean var3) {
/* 597 */     return var1
/* 598 */       .fork(var0, var2 -> a(var2, var0, c(var2, var1).isPresent()))
/* 599 */       .executes(var2 ? (var1 -> a(var1, var0)) : (var1 -> b(var1, var0)));
/*     */   }
/*     */   
/*     */   private static int a(CommandContext<CommandListenerWrapper> var0, boolean var1) throws CommandSyntaxException {
/* 603 */     OptionalInt var2 = c(var0, var1);
/* 604 */     if (var2.isPresent()) {
/* 605 */       ((CommandListenerWrapper)var0.getSource()).sendMessage(new ChatMessage("commands.execute.conditional.pass_count", new Object[] { Integer.valueOf(var2.getAsInt()) }), false);
/* 606 */       return var2.getAsInt();
/*     */     } 
/* 608 */     throw b.create();
/*     */   }
/*     */ 
/*     */   
/*     */   private static int b(CommandContext<CommandListenerWrapper> var0, boolean var1) throws CommandSyntaxException {
/* 613 */     OptionalInt var2 = c(var0, var1);
/* 614 */     if (var2.isPresent()) {
/* 615 */       throw c.create(Integer.valueOf(var2.getAsInt()));
/*     */     }
/* 617 */     ((CommandListenerWrapper)var0.getSource()).sendMessage(new ChatMessage("commands.execute.conditional.pass"), false);
/* 618 */     return 1;
/*     */   }
/*     */ 
/*     */   
/*     */   private static OptionalInt c(CommandContext<CommandListenerWrapper> var0, boolean var1) throws CommandSyntaxException {
/* 623 */     return a(((CommandListenerWrapper)var0.getSource()).getWorld(), ArgumentPosition.a(var0, "start"), ArgumentPosition.a(var0, "end"), ArgumentPosition.a(var0, "destination"), var1);
/*     */   }
/*     */   
/*     */   private static OptionalInt a(WorldServer var0, BlockPosition var1, BlockPosition var2, BlockPosition var3, boolean var4) throws CommandSyntaxException {
/* 627 */     StructureBoundingBox var5 = new StructureBoundingBox(var1, var2);
/* 628 */     StructureBoundingBox var6 = new StructureBoundingBox(var3, var3.a(var5.c()));
/* 629 */     BlockPosition var7 = new BlockPosition(var6.a - var5.a, var6.b - var5.b, var6.c - var5.c);
/* 630 */     int var8 = var5.d() * var5.e() * var5.f();
/*     */     
/* 632 */     if (var8 > 32768) {
/* 633 */       throw a.create(Integer.valueOf(32768), Integer.valueOf(var8));
/*     */     }
/*     */     
/* 636 */     int var9 = 0;
/* 637 */     for (int var10 = var5.c; var10 <= var5.f; var10++) {
/* 638 */       for (int var11 = var5.b; var11 <= var5.e; var11++) {
/* 639 */         for (int var12 = var5.a; var12 <= var5.d; var12++) {
/* 640 */           BlockPosition var13 = new BlockPosition(var12, var11, var10);
/* 641 */           BlockPosition var14 = var13.a(var7);
/*     */           
/* 643 */           IBlockData var15 = var0.getType(var13);
/* 644 */           if (!var4 || !var15.a(Blocks.AIR)) {
/*     */ 
/*     */ 
/*     */             
/* 648 */             if (var15 != var0.getType(var14)) {
/* 649 */               return OptionalInt.empty();
/*     */             }
/*     */             
/* 652 */             TileEntity var16 = var0.getTileEntity(var13);
/* 653 */             TileEntity var17 = var0.getTileEntity(var14);
/* 654 */             if (var16 != null) {
/* 655 */               if (var17 == null) {
/* 656 */                 return OptionalInt.empty();
/*     */               }
/* 658 */               NBTTagCompound var18 = var16.save(new NBTTagCompound());
/* 659 */               var18.remove("x");
/* 660 */               var18.remove("y");
/* 661 */               var18.remove("z");
/*     */               
/* 663 */               NBTTagCompound var19 = var17.save(new NBTTagCompound());
/* 664 */               var19.remove("x");
/* 665 */               var19.remove("y");
/* 666 */               var19.remove("z");
/*     */               
/* 668 */               if (!var18.equals(var19)) {
/* 669 */                 return OptionalInt.empty();
/*     */               }
/*     */             } 
/*     */             
/* 673 */             var9++;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 678 */     return OptionalInt.of(var9);
/*     */   }
/*     */   
/*     */   @FunctionalInterface
/*     */   static interface a {
/*     */     int test(CommandContext<CommandListenerWrapper> param1CommandContext) throws CommandSyntaxException;
/*     */   }
/*     */   
/*     */   @FunctionalInterface
/*     */   static interface b {
/*     */     boolean test(CommandContext<CommandListenerWrapper> param1CommandContext) throws CommandSyntaxException;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CommandExecute.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
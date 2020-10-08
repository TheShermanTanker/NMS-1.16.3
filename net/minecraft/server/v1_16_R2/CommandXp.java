/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.mojang.brigadier.CommandDispatcher;
/*     */ import com.mojang.brigadier.arguments.ArgumentType;
/*     */ import com.mojang.brigadier.arguments.IntegerArgumentType;
/*     */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*     */ import com.mojang.brigadier.builder.RequiredArgumentBuilder;
/*     */ import com.mojang.brigadier.context.CommandContext;
/*     */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*     */ import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
/*     */ import com.mojang.brigadier.tree.CommandNode;
/*     */ import com.mojang.brigadier.tree.LiteralCommandNode;
/*     */ import java.util.Collection;
/*     */ import java.util.function.BiConsumer;
/*     */ import java.util.function.BiPredicate;
/*     */ import java.util.function.ToIntFunction;
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
/*     */ public class CommandXp
/*     */ {
/*  30 */   private static final SimpleCommandExceptionType a = new SimpleCommandExceptionType(new ChatMessage("commands.experience.set.points.invalid"));
/*     */   
/*     */   public static void a(CommandDispatcher<CommandListenerWrapper> var0) {
/*  33 */     LiteralCommandNode<CommandListenerWrapper> var1 = var0.register(
/*  34 */         (LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)CommandDispatcher.a("experience")
/*  35 */         .requires(var0 -> var0.hasPermission(2)))
/*  36 */         .then(
/*  37 */           CommandDispatcher.a("add")
/*  38 */           .then(
/*  39 */             CommandDispatcher.<T>a("targets", ArgumentEntity.d())
/*  40 */             .then((
/*  41 */               (RequiredArgumentBuilder)((RequiredArgumentBuilder)CommandDispatcher.<T>a("amount", (ArgumentType<T>)IntegerArgumentType.integer())
/*  42 */               .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), ArgumentEntity.f(var0, "targets"), IntegerArgumentType.getInteger(var0, "amount"), Unit.POINTS)))
/*  43 */               .then(
/*  44 */                 CommandDispatcher.a("points")
/*  45 */                 .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), ArgumentEntity.f(var0, "targets"), IntegerArgumentType.getInteger(var0, "amount"), Unit.POINTS))))
/*     */               
/*  47 */               .then(
/*  48 */                 CommandDispatcher.a("levels")
/*  49 */                 .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), ArgumentEntity.f(var0, "targets"), IntegerArgumentType.getInteger(var0, "amount"), Unit.LEVELS)))))))
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*  54 */         .then(
/*  55 */           CommandDispatcher.a("set")
/*  56 */           .then(
/*  57 */             CommandDispatcher.<T>a("targets", ArgumentEntity.d())
/*  58 */             .then((
/*  59 */               (RequiredArgumentBuilder)((RequiredArgumentBuilder)CommandDispatcher.<T>a("amount", (ArgumentType<T>)IntegerArgumentType.integer(0))
/*  60 */               .executes(var0 -> b((CommandListenerWrapper)var0.getSource(), ArgumentEntity.f(var0, "targets"), IntegerArgumentType.getInteger(var0, "amount"), Unit.POINTS)))
/*  61 */               .then(
/*  62 */                 CommandDispatcher.a("points")
/*  63 */                 .executes(var0 -> b((CommandListenerWrapper)var0.getSource(), ArgumentEntity.f(var0, "targets"), IntegerArgumentType.getInteger(var0, "amount"), Unit.POINTS))))
/*     */               
/*  65 */               .then(
/*  66 */                 CommandDispatcher.a("levels")
/*  67 */                 .executes(var0 -> b((CommandListenerWrapper)var0.getSource(), ArgumentEntity.f(var0, "targets"), IntegerArgumentType.getInteger(var0, "amount"), Unit.LEVELS)))))))
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*  72 */         .then(
/*  73 */           CommandDispatcher.a("query")
/*  74 */           .then((
/*  75 */             (RequiredArgumentBuilder)CommandDispatcher.<T>a("targets", ArgumentEntity.c())
/*  76 */             .then(
/*  77 */               CommandDispatcher.a("points")
/*  78 */               .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), ArgumentEntity.e(var0, "targets"), Unit.POINTS))))
/*     */             
/*  80 */             .then(
/*  81 */               CommandDispatcher.a("levels")
/*  82 */               .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), ArgumentEntity.e(var0, "targets"), Unit.LEVELS))))));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  88 */     var0.register(
/*  89 */         (LiteralArgumentBuilder)((LiteralArgumentBuilder)CommandDispatcher.a("xp")
/*  90 */         .requires(var0 -> var0.hasPermission(2)))
/*  91 */         .redirect((CommandNode)var1));
/*     */   }
/*     */ 
/*     */   
/*     */   private static int a(CommandListenerWrapper var0, EntityPlayer var1, Unit var2) {
/*  96 */     int var3 = Unit.a(var2).applyAsInt(var1);
/*  97 */     var0.sendMessage(new ChatMessage("commands.experience.query." + var2.e, new Object[] { var1.getScoreboardDisplayName(), Integer.valueOf(var3) }), false);
/*  98 */     return var3;
/*     */   }
/*     */   
/*     */   private static int a(CommandListenerWrapper var0, Collection<? extends EntityPlayer> var1, int var2, Unit var3) {
/* 102 */     for (EntityPlayer var5 : var1) {
/* 103 */       var3.c.accept(var5, Integer.valueOf(var2));
/*     */     }
/*     */     
/* 106 */     if (var1.size() == 1) {
/* 107 */       var0.sendMessage(new ChatMessage("commands.experience.add." + var3.e + ".success.single", new Object[] { Integer.valueOf(var2), ((EntityPlayer)var1.iterator().next()).getScoreboardDisplayName() }), true);
/*     */     } else {
/* 109 */       var0.sendMessage(new ChatMessage("commands.experience.add." + var3.e + ".success.multiple", new Object[] { Integer.valueOf(var2), Integer.valueOf(var1.size()) }), true);
/*     */     } 
/*     */     
/* 112 */     return var1.size();
/*     */   }
/*     */   
/*     */   private static int b(CommandListenerWrapper var0, Collection<? extends EntityPlayer> var1, int var2, Unit var3) throws CommandSyntaxException {
/* 116 */     int var4 = 0;
/*     */     
/* 118 */     for (EntityPlayer var6 : var1) {
/* 119 */       if (var3.d.test(var6, Integer.valueOf(var2))) {
/* 120 */         var4++;
/*     */       }
/*     */     } 
/*     */     
/* 124 */     if (var4 == 0) {
/* 125 */       throw a.create();
/*     */     }
/*     */     
/* 128 */     if (var1.size() == 1) {
/* 129 */       var0.sendMessage(new ChatMessage("commands.experience.set." + var3.e + ".success.single", new Object[] { Integer.valueOf(var2), ((EntityPlayer)var1.iterator().next()).getScoreboardDisplayName() }), true);
/*     */     } else {
/* 131 */       var0.sendMessage(new ChatMessage("commands.experience.set." + var3.e + ".success.multiple", new Object[] { Integer.valueOf(var2), Integer.valueOf(var1.size()) }), true);
/*     */     } 
/*     */     
/* 134 */     return var1.size();
/*     */   }
/*     */   enum Unit { POINTS, LEVELS;
/*     */     static {
/* 138 */       POINTS = new Unit("POINTS", 0, "points", EntityHuman::giveExp, (var0, var1) -> {
/*     */             if (var1.intValue() >= var0.getExpToLevel()) {
/*     */               return false;
/*     */             }
/*     */             var0.a(var1.intValue());
/*     */             return true;
/*     */           }var0 -> MathHelper.d(var0.exp * var0.getExpToLevel()));
/* 145 */       LEVELS = new Unit("LEVELS", 1, "levels", EntityPlayer::levelDown, (var0, var1) -> {
/*     */             var0.b(var1.intValue());
/*     */             return true;
/*     */           }var0 -> var0.expLevel);
/*     */     }
/*     */     public final BiConsumer<EntityPlayer, Integer> c;
/*     */     public final BiPredicate<EntityPlayer, Integer> d;
/*     */     public final String e;
/*     */     private final ToIntFunction<EntityPlayer> f;
/*     */     
/*     */     Unit(String var2, BiConsumer<EntityPlayer, Integer> var3, BiPredicate<EntityPlayer, Integer> var4, ToIntFunction<EntityPlayer> var5) {
/* 156 */       this.c = var3;
/* 157 */       this.e = var2;
/* 158 */       this.d = var4;
/* 159 */       this.f = var5;
/*     */     } }
/*     */ 
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CommandXp.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.mojang.brigadier.CommandDispatcher;
/*     */ import com.mojang.brigadier.arguments.ArgumentType;
/*     */ import com.mojang.brigadier.arguments.FloatArgumentType;
/*     */ import com.mojang.brigadier.arguments.IntegerArgumentType;
/*     */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*     */ import com.mojang.brigadier.builder.RequiredArgumentBuilder;
/*     */ import com.mojang.brigadier.context.CommandContext;
/*     */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*     */ import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
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
/*     */ public class CommandWorldBorder
/*     */ {
/*  25 */   private static final SimpleCommandExceptionType a = new SimpleCommandExceptionType(new ChatMessage("commands.worldborder.center.failed"));
/*  26 */   private static final SimpleCommandExceptionType b = new SimpleCommandExceptionType(new ChatMessage("commands.worldborder.set.failed.nochange"));
/*  27 */   private static final SimpleCommandExceptionType c = new SimpleCommandExceptionType(new ChatMessage("commands.worldborder.set.failed.small."));
/*  28 */   private static final SimpleCommandExceptionType d = new SimpleCommandExceptionType(new ChatMessage("commands.worldborder.set.failed.big."));
/*  29 */   private static final SimpleCommandExceptionType e = new SimpleCommandExceptionType(new ChatMessage("commands.worldborder.warning.time.failed"));
/*  30 */   private static final SimpleCommandExceptionType f = new SimpleCommandExceptionType(new ChatMessage("commands.worldborder.warning.distance.failed"));
/*  31 */   private static final SimpleCommandExceptionType g = new SimpleCommandExceptionType(new ChatMessage("commands.worldborder.damage.buffer.failed"));
/*  32 */   private static final SimpleCommandExceptionType h = new SimpleCommandExceptionType(new ChatMessage("commands.worldborder.damage.amount.failed"));
/*     */   
/*     */   public static void a(CommandDispatcher<CommandListenerWrapper> var0) {
/*  35 */     var0.register(
/*  36 */         (LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)CommandDispatcher.a("worldborder")
/*  37 */         .requires(var0 -> var0.hasPermission(2)))
/*  38 */         .then(
/*  39 */           CommandDispatcher.a("add")
/*  40 */           .then((
/*  41 */             (RequiredArgumentBuilder)CommandDispatcher.<T>a("distance", (ArgumentType<T>)FloatArgumentType.floatArg(-6.0E7F, 6.0E7F))
/*  42 */             .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), ((CommandListenerWrapper)var0.getSource()).getWorld().getWorldBorder().getSize() + FloatArgumentType.getFloat(var0, "distance"), 0L)))
/*  43 */             .then(
/*  44 */               CommandDispatcher.<T>a("time", (ArgumentType<T>)IntegerArgumentType.integer(0))
/*  45 */               .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), ((CommandListenerWrapper)var0.getSource()).getWorld().getWorldBorder().getSize() + FloatArgumentType.getFloat(var0, "distance"), ((CommandListenerWrapper)var0.getSource()).getWorld().getWorldBorder().j() + IntegerArgumentType.getInteger(var0, "time") * 1000L))))))
/*     */ 
/*     */ 
/*     */         
/*  49 */         .then(
/*  50 */           CommandDispatcher.a("set")
/*  51 */           .then((
/*  52 */             (RequiredArgumentBuilder)CommandDispatcher.<T>a("distance", (ArgumentType<T>)FloatArgumentType.floatArg(-6.0E7F, 6.0E7F))
/*  53 */             .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), FloatArgumentType.getFloat(var0, "distance"), 0L)))
/*  54 */             .then(
/*  55 */               CommandDispatcher.<T>a("time", (ArgumentType<T>)IntegerArgumentType.integer(0))
/*  56 */               .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), FloatArgumentType.getFloat(var0, "distance"), IntegerArgumentType.getInteger(var0, "time") * 1000L))))))
/*     */ 
/*     */ 
/*     */         
/*  60 */         .then(
/*  61 */           CommandDispatcher.a("center")
/*  62 */           .then(
/*  63 */             CommandDispatcher.<T>a("pos", ArgumentVec2.a())
/*  64 */             .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), ArgumentVec2.a(var0, "pos"))))))
/*     */ 
/*     */         
/*  67 */         .then((
/*  68 */           (LiteralArgumentBuilder)CommandDispatcher.a("damage")
/*  69 */           .then(
/*  70 */             CommandDispatcher.a("amount")
/*  71 */             .then(
/*  72 */               CommandDispatcher.<T>a("damagePerBlock", (ArgumentType<T>)FloatArgumentType.floatArg(0.0F))
/*  73 */               .executes(var0 -> b((CommandListenerWrapper)var0.getSource(), FloatArgumentType.getFloat(var0, "damagePerBlock"))))))
/*     */ 
/*     */           
/*  76 */           .then(
/*  77 */             CommandDispatcher.a("buffer")
/*  78 */             .then(
/*  79 */               CommandDispatcher.<T>a("distance", (ArgumentType<T>)FloatArgumentType.floatArg(0.0F))
/*  80 */               .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), FloatArgumentType.getFloat(var0, "distance")))))))
/*     */ 
/*     */ 
/*     */         
/*  84 */         .then(
/*  85 */           CommandDispatcher.a("get")
/*  86 */           .executes(var0 -> a((CommandListenerWrapper)var0.getSource()))))
/*     */         
/*  88 */         .then((
/*  89 */           (LiteralArgumentBuilder)CommandDispatcher.a("warning")
/*  90 */           .then(
/*  91 */             CommandDispatcher.a("distance")
/*  92 */             .then(
/*  93 */               CommandDispatcher.<T>a("distance", (ArgumentType<T>)IntegerArgumentType.integer(0))
/*  94 */               .executes(var0 -> b((CommandListenerWrapper)var0.getSource(), IntegerArgumentType.getInteger(var0, "distance"))))))
/*     */ 
/*     */           
/*  97 */           .then(
/*  98 */             CommandDispatcher.a("time")
/*  99 */             .then(
/* 100 */               CommandDispatcher.<T>a("time", (ArgumentType<T>)IntegerArgumentType.integer(0))
/* 101 */               .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), IntegerArgumentType.getInteger(var0, "time")))))));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static int a(CommandListenerWrapper var0, float var1) throws CommandSyntaxException {
/* 109 */     WorldBorder var2 = var0.getWorld().getWorldBorder();
/* 110 */     if (var2.getDamageBuffer() == var1) {
/* 111 */       throw g.create();
/*     */     }
/* 113 */     var2.setDamageBuffer(var1);
/* 114 */     var0.sendMessage(new ChatMessage("commands.worldborder.damage.buffer.success", new Object[] { String.format(Locale.ROOT, "%.2f", new Object[] { Float.valueOf(var1) }) }), true);
/* 115 */     return (int)var1;
/*     */   }
/*     */   
/*     */   private static int b(CommandListenerWrapper var0, float var1) throws CommandSyntaxException {
/* 119 */     WorldBorder var2 = var0.getWorld().getWorldBorder();
/* 120 */     if (var2.getDamageAmount() == var1) {
/* 121 */       throw h.create();
/*     */     }
/* 123 */     var2.setDamageAmount(var1);
/* 124 */     var0.sendMessage(new ChatMessage("commands.worldborder.damage.amount.success", new Object[] { String.format(Locale.ROOT, "%.2f", new Object[] { Float.valueOf(var1) }) }), true);
/* 125 */     return (int)var1;
/*     */   }
/*     */   
/*     */   private static int a(CommandListenerWrapper var0, int var1) throws CommandSyntaxException {
/* 129 */     WorldBorder var2 = var0.getWorld().getWorldBorder();
/* 130 */     if (var2.getWarningTime() == var1) {
/* 131 */       throw e.create();
/*     */     }
/* 133 */     var2.setWarningTime(var1);
/* 134 */     var0.sendMessage(new ChatMessage("commands.worldborder.warning.time.success", new Object[] { Integer.valueOf(var1) }), true);
/* 135 */     return var1;
/*     */   }
/*     */   
/*     */   private static int b(CommandListenerWrapper var0, int var1) throws CommandSyntaxException {
/* 139 */     WorldBorder var2 = var0.getWorld().getWorldBorder();
/* 140 */     if (var2.getWarningDistance() == var1) {
/* 141 */       throw f.create();
/*     */     }
/* 143 */     var2.setWarningDistance(var1);
/* 144 */     var0.sendMessage(new ChatMessage("commands.worldborder.warning.distance.success", new Object[] { Integer.valueOf(var1) }), true);
/* 145 */     return var1;
/*     */   }
/*     */   
/*     */   private static int a(CommandListenerWrapper var0) {
/* 149 */     double var1 = var0.getWorld().getWorldBorder().getSize();
/* 150 */     var0.sendMessage(new ChatMessage("commands.worldborder.get", new Object[] { String.format(Locale.ROOT, "%.0f", new Object[] { Double.valueOf(var1) }) }), false);
/* 151 */     return MathHelper.floor(var1 + 0.5D);
/*     */   }
/*     */   
/*     */   private static int a(CommandListenerWrapper var0, Vec2F var1) throws CommandSyntaxException {
/* 155 */     WorldBorder var2 = var0.getWorld().getWorldBorder();
/* 156 */     if (var2.getCenterX() == var1.i && var2.getCenterZ() == var1.j) {
/* 157 */       throw a.create();
/*     */     }
/*     */     
/* 160 */     var2.setCenter(var1.i, var1.j);
/* 161 */     var0.sendMessage(new ChatMessage("commands.worldborder.center.success", new Object[] { String.format(Locale.ROOT, "%.2f", new Object[] { Float.valueOf(var1.i) }), String.format("%.2f", new Object[] { Float.valueOf(var1.j) }) }), true);
/*     */     
/* 163 */     return 0;
/*     */   }
/*     */   
/*     */   private static int a(CommandListenerWrapper var0, double var1, long var3) throws CommandSyntaxException {
/* 167 */     WorldBorder var5 = var0.getWorld().getWorldBorder();
/* 168 */     double var6 = var5.getSize();
/*     */     
/* 170 */     if (var6 == var1) {
/* 171 */       throw b.create();
/*     */     }
/* 173 */     if (var1 < 1.0D) {
/* 174 */       throw c.create();
/*     */     }
/* 176 */     if (var1 > 6.0E7D) {
/* 177 */       throw d.create();
/*     */     }
/*     */     
/* 180 */     if (var3 > 0L) {
/* 181 */       var5.transitionSizeBetween(var6, var1, var3);
/* 182 */       if (var1 > var6) {
/* 183 */         var0.sendMessage(new ChatMessage("commands.worldborder.set.grow", new Object[] { String.format(Locale.ROOT, "%.1f", new Object[] { Double.valueOf(var1) }), Long.toString(var3 / 1000L) }), true);
/*     */       } else {
/* 185 */         var0.sendMessage(new ChatMessage("commands.worldborder.set.shrink", new Object[] { String.format(Locale.ROOT, "%.1f", new Object[] { Double.valueOf(var1) }), Long.toString(var3 / 1000L) }), true);
/*     */       } 
/*     */     } else {
/* 188 */       var5.setSize(var1);
/* 189 */       var0.sendMessage(new ChatMessage("commands.worldborder.set.immediate", new Object[] { String.format(Locale.ROOT, "%.1f", new Object[] { Double.valueOf(var1) }) }), true);
/*     */     } 
/*     */     
/* 192 */     return (int)(var1 - var6);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CommandWorldBorder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
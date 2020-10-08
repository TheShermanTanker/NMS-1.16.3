/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.brigadier.CommandDispatcher;
/*    */ import com.mojang.brigadier.arguments.ArgumentType;
/*    */ import com.mojang.brigadier.arguments.IntegerArgumentType;
/*    */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*    */ import com.mojang.brigadier.context.CommandContext;
/*    */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CommandWeather
/*    */ {
/*    */   public static void a(CommandDispatcher<CommandListenerWrapper> var0) {
/* 18 */     var0.register(
/* 19 */         (LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)CommandDispatcher.a("weather")
/* 20 */         .requires(var0 -> var0.hasPermission(2)))
/* 21 */         .then((
/* 22 */           (LiteralArgumentBuilder)CommandDispatcher.a("clear")
/* 23 */           .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), 6000)))
/* 24 */           .then(
/* 25 */             CommandDispatcher.<T>a("duration", (ArgumentType<T>)IntegerArgumentType.integer(0, 1000000))
/* 26 */             .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), IntegerArgumentType.getInteger(var0, "duration") * 20)))))
/*    */ 
/*    */         
/* 29 */         .then((
/* 30 */           (LiteralArgumentBuilder)CommandDispatcher.a("rain")
/* 31 */           .executes(var0 -> b((CommandListenerWrapper)var0.getSource(), 6000)))
/* 32 */           .then(
/* 33 */             CommandDispatcher.<T>a("duration", (ArgumentType<T>)IntegerArgumentType.integer(0, 1000000))
/* 34 */             .executes(var0 -> b((CommandListenerWrapper)var0.getSource(), IntegerArgumentType.getInteger(var0, "duration") * 20)))))
/*    */ 
/*    */         
/* 37 */         .then((
/* 38 */           (LiteralArgumentBuilder)CommandDispatcher.a("thunder")
/* 39 */           .executes(var0 -> c((CommandListenerWrapper)var0.getSource(), 6000)))
/* 40 */           .then(
/* 41 */             CommandDispatcher.<T>a("duration", (ArgumentType<T>)IntegerArgumentType.integer(0, 1000000))
/* 42 */             .executes(var0 -> c((CommandListenerWrapper)var0.getSource(), IntegerArgumentType.getInteger(var0, "duration") * 20)))));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private static int a(CommandListenerWrapper var0, int var1) {
/* 49 */     var0.getWorld().a(var1, 0, false, false);
/* 50 */     var0.sendMessage(new ChatMessage("commands.weather.set.clear"), true);
/* 51 */     return var1;
/*    */   }
/*    */   
/*    */   private static int b(CommandListenerWrapper var0, int var1) {
/* 55 */     var0.getWorld().a(0, var1, true, false);
/* 56 */     var0.sendMessage(new ChatMessage("commands.weather.set.rain"), true);
/* 57 */     return var1;
/*    */   }
/*    */   
/*    */   private static int c(CommandListenerWrapper var0, int var1) {
/* 61 */     var0.getWorld().a(0, var1, true, true);
/* 62 */     var0.sendMessage(new ChatMessage("commands.weather.set.thunder"), true);
/* 63 */     return var1;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CommandWeather.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
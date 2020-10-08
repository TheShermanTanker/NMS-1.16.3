/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.brigadier.CommandDispatcher;
/*    */ import com.mojang.brigadier.Message;
/*    */ import com.mojang.brigadier.arguments.ArgumentType;
/*    */ import com.mojang.brigadier.arguments.IntegerArgumentType;
/*    */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*    */ import com.mojang.brigadier.builder.RequiredArgumentBuilder;
/*    */ import com.mojang.brigadier.context.CommandContext;
/*    */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*    */ import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
/*    */ import java.util.Collection;
/*    */ import java.util.Collections;
/*    */ import java.util.function.Predicate;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CommandClear
/*    */ {
/*    */   private static final DynamicCommandExceptionType a;
/*    */   private static final DynamicCommandExceptionType b;
/*    */   
/*    */   static {
/* 26 */     a = new DynamicCommandExceptionType(var0 -> new ChatMessage("clear.failed.single", new Object[] { var0 }));
/* 27 */     b = new DynamicCommandExceptionType(var0 -> new ChatMessage("clear.failed.multiple", new Object[] { var0 }));
/*    */   }
/*    */   public static void a(CommandDispatcher<CommandListenerWrapper> var0) {
/* 30 */     var0.register(
/* 31 */         (LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)CommandDispatcher.a("clear")
/* 32 */         .requires(var0 -> var0.hasPermission(2)))
/* 33 */         .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), Collections.singleton(((CommandListenerWrapper)var0.getSource()).h()), (), -1)))
/* 34 */         .then((
/* 35 */           (RequiredArgumentBuilder)CommandDispatcher.<T>a("targets", ArgumentEntity.d())
/* 36 */           .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), ArgumentEntity.f(var0, "targets"), (), -1)))
/* 37 */           .then((
/* 38 */             (RequiredArgumentBuilder)CommandDispatcher.<T>a("item", ArgumentItemPredicate.a())
/* 39 */             .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), ArgumentEntity.f(var0, "targets"), ArgumentItemPredicate.a(var0, "item"), -1)))
/* 40 */             .then(
/* 41 */               CommandDispatcher.<T>a("maxCount", (ArgumentType<T>)IntegerArgumentType.integer(0))
/* 42 */               .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), ArgumentEntity.f(var0, "targets"), ArgumentItemPredicate.a(var0, "item"), IntegerArgumentType.getInteger(var0, "maxCount")))))));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private static int a(CommandListenerWrapper var0, Collection<EntityPlayer> var1, Predicate<ItemStack> var2, int var3) throws CommandSyntaxException {
/* 50 */     int var4 = 0;
/*    */     
/* 52 */     for (EntityPlayer var6 : var1) {
/* 53 */       var4 += var6.inventory.a(var2, var3, var6.defaultContainer.j());
/* 54 */       var6.activeContainer.c();
/*    */ 
/*    */       
/* 57 */       var6.defaultContainer.a(var6.inventory);
/*    */       
/* 59 */       var6.broadcastCarriedItem();
/*    */     } 
/*    */     
/* 62 */     if (var4 == 0) {
/* 63 */       if (var1.size() == 1) {
/* 64 */         throw a.create(((EntityPlayer)var1.iterator().next()).getDisplayName());
/*    */       }
/* 66 */       throw b.create(Integer.valueOf(var1.size()));
/*    */     } 
/*    */ 
/*    */     
/* 70 */     if (var3 == 0) {
/* 71 */       if (var1.size() == 1) {
/* 72 */         var0.sendMessage(new ChatMessage("commands.clear.test.single", new Object[] { Integer.valueOf(var4), ((EntityPlayer)var1.iterator().next()).getScoreboardDisplayName() }), true);
/*    */       } else {
/* 74 */         var0.sendMessage(new ChatMessage("commands.clear.test.multiple", new Object[] { Integer.valueOf(var4), Integer.valueOf(var1.size()) }), true);
/*    */       }
/*    */     
/* 77 */     } else if (var1.size() == 1) {
/* 78 */       var0.sendMessage(new ChatMessage("commands.clear.success.single", new Object[] { Integer.valueOf(var4), ((EntityPlayer)var1.iterator().next()).getScoreboardDisplayName() }), true);
/*    */     } else {
/* 80 */       var0.sendMessage(new ChatMessage("commands.clear.success.multiple", new Object[] { Integer.valueOf(var4), Integer.valueOf(var1.size()) }), true);
/*    */     } 
/*    */ 
/*    */     
/* 84 */     return var4;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CommandClear.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
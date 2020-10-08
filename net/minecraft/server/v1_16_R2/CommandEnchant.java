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
/*    */ import com.mojang.brigadier.exceptions.Dynamic2CommandExceptionType;
/*    */ import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
/*    */ import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
/*    */ import java.util.Collection;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CommandEnchant
/*    */ {
/*    */   private static final DynamicCommandExceptionType a;
/*    */   private static final DynamicCommandExceptionType b;
/*    */   private static final DynamicCommandExceptionType c;
/*    */   private static final Dynamic2CommandExceptionType d;
/*    */   
/*    */   static {
/* 29 */     a = new DynamicCommandExceptionType(var0 -> new ChatMessage("commands.enchant.failed.entity", new Object[] { var0 }));
/* 30 */     b = new DynamicCommandExceptionType(var0 -> new ChatMessage("commands.enchant.failed.itemless", new Object[] { var0 }));
/* 31 */     c = new DynamicCommandExceptionType(var0 -> new ChatMessage("commands.enchant.failed.incompatible", new Object[] { var0 }));
/* 32 */     d = new Dynamic2CommandExceptionType((var0, var1) -> new ChatMessage("commands.enchant.failed.level", new Object[] { var0, var1 }));
/* 33 */   } private static final SimpleCommandExceptionType e = new SimpleCommandExceptionType(new ChatMessage("commands.enchant.failed"));
/*    */   
/*    */   public static void a(CommandDispatcher<CommandListenerWrapper> var0) {
/* 36 */     var0.register(
/* 37 */         (LiteralArgumentBuilder)((LiteralArgumentBuilder)CommandDispatcher.a("enchant")
/* 38 */         .requires(var0 -> var0.hasPermission(2)))
/* 39 */         .then(
/* 40 */           CommandDispatcher.<T>a("targets", ArgumentEntity.multipleEntities())
/* 41 */           .then((
/* 42 */             (RequiredArgumentBuilder)CommandDispatcher.<T>a("enchantment", ArgumentEnchantment.a())
/* 43 */             .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), ArgumentEntity.b(var0, "targets"), ArgumentEnchantment.a(var0, "enchantment"), 1)))
/* 44 */             .then(
/* 45 */               CommandDispatcher.<T>a("level", (ArgumentType<T>)IntegerArgumentType.integer(0))
/* 46 */               .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), ArgumentEntity.b(var0, "targets"), ArgumentEnchantment.a(var0, "enchantment"), IntegerArgumentType.getInteger(var0, "level")))))));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private static int a(CommandListenerWrapper var0, Collection<? extends Entity> var1, Enchantment var2, int var3) throws CommandSyntaxException {
/* 54 */     if (var3 > var2.getMaxLevel()) {
/* 55 */       throw d.create(Integer.valueOf(var3), Integer.valueOf(var2.getMaxLevel()));
/*    */     }
/*    */     
/* 58 */     int var4 = 0;
/*    */     
/* 60 */     for (Entity var6 : var1) {
/* 61 */       if (var6 instanceof EntityLiving) {
/* 62 */         EntityLiving var7 = (EntityLiving)var6;
/*    */         
/* 64 */         ItemStack var8 = var7.getItemInMainHand();
/* 65 */         if (!var8.isEmpty()) {
/* 66 */           if (var2.canEnchant(var8) && EnchantmentManager.a(EnchantmentManager.a(var8).keySet(), var2)) {
/* 67 */             var8.addEnchantment(var2, var3);
/* 68 */             var4++; continue;
/* 69 */           }  if (var1.size() == 1)
/* 70 */             throw c.create(var8.getItem().h(var8).getString());  continue;
/*    */         } 
/* 72 */         if (var1.size() == 1)
/* 73 */           throw b.create(var7.getDisplayName().getString());  continue;
/*    */       } 
/* 75 */       if (var1.size() == 1) {
/* 76 */         throw a.create(var6.getDisplayName().getString());
/*    */       }
/*    */     } 
/*    */     
/* 80 */     if (var4 == 0)
/* 81 */       throw e.create(); 
/* 82 */     if (var1.size() == 1) {
/* 83 */       var0.sendMessage(new ChatMessage("commands.enchant.success.single", new Object[] { var2.d(var3), ((Entity)var1.iterator().next()).getScoreboardDisplayName() }), true);
/*    */     } else {
/* 85 */       var0.sendMessage(new ChatMessage("commands.enchant.success.multiple", new Object[] { var2.d(var3), Integer.valueOf(var1.size()) }), true);
/*    */     } 
/*    */     
/* 88 */     return var4;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CommandEnchant.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.brigadier.CommandDispatcher;
/*    */ import com.mojang.brigadier.arguments.ArgumentType;
/*    */ import com.mojang.brigadier.arguments.IntegerArgumentType;
/*    */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*    */ import com.mojang.brigadier.builder.RequiredArgumentBuilder;
/*    */ import com.mojang.brigadier.context.CommandContext;
/*    */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*    */ import java.util.Collection;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CommandGive
/*    */ {
/*    */   public static void a(CommandDispatcher<CommandListenerWrapper> var0) {
/* 28 */     var0.register(
/* 29 */         (LiteralArgumentBuilder)((LiteralArgumentBuilder)CommandDispatcher.a("give")
/* 30 */         .requires(var0 -> var0.hasPermission(2)))
/* 31 */         .then(
/* 32 */           CommandDispatcher.<T>a("targets", ArgumentEntity.d())
/* 33 */           .then((
/* 34 */             (RequiredArgumentBuilder)CommandDispatcher.<T>a("item", ArgumentItemStack.a())
/* 35 */             .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), ArgumentItemStack.a(var0, "item"), ArgumentEntity.f((CommandContext)var0, "targets"), 1)))
/* 36 */             .then(
/* 37 */               CommandDispatcher.<T>a("count", (ArgumentType<T>)IntegerArgumentType.integer(1))
/* 38 */               .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), ArgumentItemStack.a(var0, "item"), ArgumentEntity.f((CommandContext)var0, "targets"), IntegerArgumentType.getInteger(var0, "count")))))));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private static int a(CommandListenerWrapper var0, ArgumentPredicateItemStack var1, Collection<EntityPlayer> var2, int var3) throws CommandSyntaxException {
/* 46 */     for (EntityPlayer var5 : var2) {
/* 47 */       int var6 = var3;
/* 48 */       while (var6 > 0) {
/* 49 */         int var7 = Math.min(var1.a().getMaxStackSize(), var6);
/* 50 */         var6 -= var7;
/*    */         
/* 52 */         ItemStack var8 = var1.a(var7, false);
/* 53 */         boolean var9 = var5.inventory.pickup(var8);
/*    */         
/* 55 */         if (!var9 || !var8.isEmpty()) {
/* 56 */           EntityItem entityItem = var5.drop(var8, false);
/* 57 */           if (entityItem != null) {
/* 58 */             entityItem.n();
/* 59 */             entityItem.setOwner(var5.getUniqueID());
/*    */           } 
/*    */           continue;
/*    */         } 
/* 63 */         var8.setCount(1);
/* 64 */         EntityItem var10 = var5.drop(var8, false);
/* 65 */         if (var10 != null) {
/* 66 */           var10.s();
/*    */         }
/* 68 */         var5.world.playSound(null, var5.locX(), var5.locY(), var5.locZ(), SoundEffects.ENTITY_ITEM_PICKUP, SoundCategory.PLAYERS, 0.2F, ((var5.getRandom().nextFloat() - var5.getRandom().nextFloat()) * 0.7F + 1.0F) * 2.0F);
/* 69 */         var5.defaultContainer.c();
/*    */       } 
/*    */     } 
/*    */ 
/*    */     
/* 74 */     if (var2.size() == 1) {
/* 75 */       var0.sendMessage(new ChatMessage("commands.give.success.single", new Object[] { Integer.valueOf(var3), var1.a(var3, false).C(), ((EntityPlayer)var2.iterator().next()).getScoreboardDisplayName() }), true);
/*    */     } else {
/* 77 */       var0.sendMessage(new ChatMessage("commands.give.success.single", new Object[] { Integer.valueOf(var3), var1.a(var3, false).C(), Integer.valueOf(var2.size()) }), true);
/*    */     } 
/*    */     
/* 80 */     return var2.size();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CommandGive.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
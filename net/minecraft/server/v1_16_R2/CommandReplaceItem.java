/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.mojang.brigadier.CommandDispatcher;
/*     */ import com.mojang.brigadier.Message;
/*     */ import com.mojang.brigadier.arguments.ArgumentType;
/*     */ import com.mojang.brigadier.arguments.IntegerArgumentType;
/*     */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*     */ import com.mojang.brigadier.builder.RequiredArgumentBuilder;
/*     */ import com.mojang.brigadier.context.CommandContext;
/*     */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*     */ import com.mojang.brigadier.exceptions.Dynamic2CommandExceptionType;
/*     */ import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
/*     */ import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
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
/*     */ public class CommandReplaceItem
/*     */ {
/*     */   public static final DynamicCommandExceptionType b;
/*     */   public static final Dynamic2CommandExceptionType c;
/*  36 */   public static final SimpleCommandExceptionType a = new SimpleCommandExceptionType(new ChatMessage("commands.replaceitem.block.failed")); static {
/*  37 */     b = new DynamicCommandExceptionType(var0 -> new ChatMessage("commands.replaceitem.slot.inapplicable", new Object[] { var0 }));
/*  38 */     c = new Dynamic2CommandExceptionType((var0, var1) -> new ChatMessage("commands.replaceitem.entity.failed", new Object[] { var0, var1 }));
/*     */   }
/*     */   public static void a(CommandDispatcher<CommandListenerWrapper> var0) {
/*  41 */     var0.register(
/*  42 */         (LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)CommandDispatcher.a("replaceitem")
/*  43 */         .requires(var0 -> var0.hasPermission(2)))
/*  44 */         .then(
/*  45 */           CommandDispatcher.a("block")
/*  46 */           .then(
/*  47 */             CommandDispatcher.<T>a("pos", ArgumentPosition.a())
/*  48 */             .then(
/*  49 */               CommandDispatcher.<T>a("slot", ArgumentInventorySlot.a())
/*  50 */               .then((
/*  51 */                 (RequiredArgumentBuilder)CommandDispatcher.<T>a("item", ArgumentItemStack.a())
/*  52 */                 .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), ArgumentPosition.a(var0, "pos"), ArgumentInventorySlot.a(var0, "slot"), ArgumentItemStack.<CommandListenerWrapper>a(var0, "item").a(1, false))))
/*  53 */                 .then(
/*  54 */                   CommandDispatcher.<T>a("count", (ArgumentType<T>)IntegerArgumentType.integer(1, 64))
/*  55 */                   .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), ArgumentPosition.a(var0, "pos"), ArgumentInventorySlot.a(var0, "slot"), ArgumentItemStack.<CommandListenerWrapper>a(var0, "item").a(IntegerArgumentType.getInteger(var0, "count"), true)))))))))
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*  61 */         .then(
/*  62 */           CommandDispatcher.a("entity")
/*  63 */           .then(
/*  64 */             CommandDispatcher.<T>a("targets", ArgumentEntity.multipleEntities())
/*  65 */             .then(
/*  66 */               CommandDispatcher.<T>a("slot", ArgumentInventorySlot.a())
/*  67 */               .then((
/*  68 */                 (RequiredArgumentBuilder)CommandDispatcher.<T>a("item", ArgumentItemStack.a())
/*  69 */                 .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), ArgumentEntity.b(var0, "targets"), ArgumentInventorySlot.a(var0, "slot"), ArgumentItemStack.<CommandListenerWrapper>a(var0, "item").a(1, false))))
/*  70 */                 .then(
/*  71 */                   CommandDispatcher.<T>a("count", (ArgumentType<T>)IntegerArgumentType.integer(1, 64))
/*  72 */                   .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), ArgumentEntity.b(var0, "targets"), ArgumentInventorySlot.a(var0, "slot"), ArgumentItemStack.<CommandListenerWrapper>a(var0, "item").a(IntegerArgumentType.getInteger(var0, "count"), true)))))))));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static int a(CommandListenerWrapper var0, BlockPosition var1, int var2, ItemStack var3) throws CommandSyntaxException {
/*  82 */     TileEntity var4 = var0.getWorld().getTileEntity(var1);
/*  83 */     if (!(var4 instanceof IInventory)) {
/*  84 */       throw a.create();
/*     */     }
/*  86 */     IInventory var5 = (IInventory)var4;
/*  87 */     if (var2 < 0 || var2 >= var5.getSize()) {
/*  88 */       throw b.create(Integer.valueOf(var2));
/*     */     }
/*     */     
/*  91 */     var5.setItem(var2, var3);
/*  92 */     var0.sendMessage(new ChatMessage("commands.replaceitem.block.success", new Object[] { Integer.valueOf(var1.getX()), Integer.valueOf(var1.getY()), Integer.valueOf(var1.getZ()), var3.C() }), true);
/*  93 */     return 1;
/*     */   }
/*     */   
/*     */   private static int a(CommandListenerWrapper var0, Collection<? extends Entity> var1, int var2, ItemStack var3) throws CommandSyntaxException {
/*  97 */     List<Entity> var4 = Lists.newArrayListWithCapacity(var1.size());
/*     */     
/*  99 */     for (Entity var6 : var1) {
/* 100 */       if (var6 instanceof EntityPlayer) {
/* 101 */         ((EntityPlayer)var6).defaultContainer.c();
/*     */       }
/* 103 */       if (var6.a_(var2, var3.cloneItemStack())) {
/* 104 */         var4.add(var6);
/* 105 */         if (var6 instanceof EntityPlayer) {
/* 106 */           ((EntityPlayer)var6).defaultContainer.c();
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 111 */     if (var4.isEmpty()) {
/* 112 */       throw c.create(var3.C(), Integer.valueOf(var2));
/*     */     }
/*     */     
/* 115 */     if (var4.size() == 1) {
/* 116 */       var0.sendMessage(new ChatMessage("commands.replaceitem.entity.success.single", new Object[] { ((Entity)var4.iterator().next()).getScoreboardDisplayName(), var3.C() }), true);
/*     */     } else {
/* 118 */       var0.sendMessage(new ChatMessage("commands.replaceitem.entity.success.multiple", new Object[] { Integer.valueOf(var4.size()), var3.C() }), true);
/*     */     } 
/*     */     
/* 121 */     return var4.size();
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CommandReplaceItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
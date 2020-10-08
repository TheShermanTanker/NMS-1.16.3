/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.mojang.brigadier.CommandDispatcher;
/*     */ import com.mojang.brigadier.Message;
/*     */ import com.mojang.brigadier.arguments.ArgumentType;
/*     */ import com.mojang.brigadier.arguments.IntegerArgumentType;
/*     */ import com.mojang.brigadier.builder.ArgumentBuilder;
/*     */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*     */ import com.mojang.brigadier.builder.RequiredArgumentBuilder;
/*     */ import com.mojang.brigadier.context.CommandContext;
/*     */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*     */ import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
/*     */ import com.mojang.brigadier.suggestion.SuggestionProvider;
/*     */ import com.mojang.brigadier.suggestion.SuggestionsBuilder;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import java.util.Objects;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CommandLoot
/*     */ {
/*     */   public static final SuggestionProvider<CommandListenerWrapper> a;
/*     */   private static final DynamicCommandExceptionType b;
/*     */   private static final DynamicCommandExceptionType c;
/*     */   
/*     */   static {
/*  61 */     a = ((var0, var1) -> {
/*     */         LootTableRegistry var2 = ((CommandListenerWrapper)var0.getSource()).getServer().getLootTableRegistry();
/*     */         
/*     */         return ICompletionProvider.a(var2.a(), var1);
/*     */       });
/*  66 */     b = new DynamicCommandExceptionType(var0 -> new ChatMessage("commands.drop.no_held_items", new Object[] { var0 }));
/*  67 */     c = new DynamicCommandExceptionType(var0 -> new ChatMessage("commands.drop.no_loot_table", new Object[] { var0 }));
/*     */   }
/*     */   public static void a(CommandDispatcher<CommandListenerWrapper> var0) {
/*  70 */     var0.register(
/*  71 */         (LiteralArgumentBuilder)a(
/*  72 */           CommandDispatcher.a("loot")
/*  73 */           .requires(var0 -> var0.hasPermission(2)), (var0, var1) -> var0.then(CommandDispatcher.a("fish").then(CommandDispatcher.<T>a("loot_table", ArgumentMinecraftKeyRegistered.a()).suggests(a).then(((RequiredArgumentBuilder)((RequiredArgumentBuilder)((RequiredArgumentBuilder)CommandDispatcher.<T>a("pos", ArgumentPosition.a()).executes(())).then(CommandDispatcher.<T>a("tool", ArgumentItemStack.a()).executes(()))).then(CommandDispatcher.a("mainhand").executes(()))).then(CommandDispatcher.a("offhand").executes(()))))).then(CommandDispatcher.a("loot").then(CommandDispatcher.<T>a("loot_table", ArgumentMinecraftKeyRegistered.a()).suggests(a).executes(()))).then(CommandDispatcher.a("kill").then(CommandDispatcher.<T>a("target", ArgumentEntity.a()).executes(()))).then(CommandDispatcher.a("mine").then(((RequiredArgumentBuilder)((RequiredArgumentBuilder)((RequiredArgumentBuilder)CommandDispatcher.<T>a("pos", ArgumentPosition.a()).executes(())).then(CommandDispatcher.<T>a("tool", ArgumentItemStack.a()).executes(()))).then(CommandDispatcher.a("mainhand").executes(()))).then(CommandDispatcher.a("offhand").executes(()))))));
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static <T extends ArgumentBuilder<CommandListenerWrapper, T>> T a(T var0, c var1) {
/* 151 */     return (T)var0
/* 152 */       .then((
/* 153 */         (LiteralArgumentBuilder)CommandDispatcher.a("replace")
/* 154 */         .then(CommandDispatcher.a("entity")
/* 155 */           .then(
/* 156 */             CommandDispatcher.<T>a("entities", ArgumentEntity.multipleEntities())
/* 157 */             .then(var1
/* 158 */               .construct((ArgumentBuilder<CommandListenerWrapper, ?>)CommandDispatcher.a("slot", ArgumentInventorySlot.a()), (var0, var1, var2) -> a(ArgumentEntity.b(var0, "entities"), ArgumentInventorySlot.a(var0, "slot"), var1.size(), var1, var2))
/*     */ 
/*     */               
/* 161 */               .then(var1
/* 162 */                 .construct((ArgumentBuilder<CommandListenerWrapper, ?>)CommandDispatcher.a("count", (ArgumentType<?>)IntegerArgumentType.integer(0)), (var0, var1, var2) -> a(ArgumentEntity.b(var0, "entities"), ArgumentInventorySlot.a(var0, "slot"), IntegerArgumentType.getInteger(var0, "count"), var1, var2)))))))
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 169 */         .then(CommandDispatcher.a("block")
/* 170 */           .then(
/* 171 */             CommandDispatcher.<T>a("targetPos", ArgumentPosition.a())
/* 172 */             .then(var1
/* 173 */               .construct((ArgumentBuilder<CommandListenerWrapper, ?>)CommandDispatcher.a("slot", ArgumentInventorySlot.a()), (var0, var1, var2) -> a((CommandListenerWrapper)var0.getSource(), ArgumentPosition.a(var0, "targetPos"), ArgumentInventorySlot.a(var0, "slot"), var1.size(), var1, var2))
/*     */ 
/*     */               
/* 176 */               .then(var1
/* 177 */                 .construct((ArgumentBuilder<CommandListenerWrapper, ?>)CommandDispatcher.a("count", (ArgumentType<?>)IntegerArgumentType.integer(0)), (var0, var1, var2) -> a((CommandListenerWrapper)var0.getSource(), ArgumentPosition.a(var0, "targetPos"), IntegerArgumentType.getInteger(var0, "slot"), IntegerArgumentType.getInteger(var0, "count"), var1, var2)))))))
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 185 */       .then(
/* 186 */         CommandDispatcher.a("insert")
/* 187 */         .then(var1
/* 188 */           .construct((ArgumentBuilder<CommandListenerWrapper, ?>)CommandDispatcher.a("targetPos", ArgumentPosition.a()), (var0, var1, var2) -> a((CommandListenerWrapper)var0.getSource(), ArgumentPosition.a(var0, "targetPos"), var1, var2))))
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 193 */       .then(
/* 194 */         CommandDispatcher.a("give")
/* 195 */         .then(var1
/* 196 */           .construct((ArgumentBuilder<CommandListenerWrapper, ?>)CommandDispatcher.a("players", ArgumentEntity.d()), (var0, var1, var2) -> a(ArgumentEntity.f(var0, "players"), var1, var2))))
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 201 */       .then(
/* 202 */         CommandDispatcher.a("spawn")
/* 203 */         .then(var1
/* 204 */           .construct((ArgumentBuilder<CommandListenerWrapper, ?>)CommandDispatcher.a("targetPos", ArgumentVec3.a()), (var0, var1, var2) -> a((CommandListenerWrapper)var0.getSource(), ArgumentVec3.a(var0, "targetPos"), var1, var2))));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static IInventory a(CommandListenerWrapper var0, BlockPosition var1) throws CommandSyntaxException {
/* 212 */     TileEntity var2 = var0.getWorld().getTileEntity(var1);
/* 213 */     if (!(var2 instanceof IInventory)) {
/* 214 */       throw CommandReplaceItem.a.create();
/*     */     }
/*     */     
/* 217 */     return (IInventory)var2;
/*     */   }
/*     */   
/*     */   private static int a(CommandListenerWrapper var0, BlockPosition var1, List<ItemStack> var2, a var3) throws CommandSyntaxException {
/* 221 */     IInventory var4 = a(var0, var1);
/*     */     
/* 223 */     List<ItemStack> var5 = Lists.newArrayListWithCapacity(var2.size());
/* 224 */     for (ItemStack var7 : var2) {
/* 225 */       if (a(var4, var7.cloneItemStack())) {
/* 226 */         var4.update();
/* 227 */         var5.add(var7);
/*     */       } 
/*     */     } 
/*     */     
/* 231 */     var3.accept(var5);
/* 232 */     return var5.size();
/*     */   }
/*     */   
/*     */   private static boolean a(IInventory var0, ItemStack var1) {
/* 236 */     boolean var2 = false;
/*     */     
/* 238 */     for (int var3 = 0; var3 < var0.getSize() && !var1.isEmpty(); var3++) {
/* 239 */       ItemStack var4 = var0.getItem(var3);
/*     */       
/* 241 */       if (var0.b(var3, var1)) {
/* 242 */         if (var4.isEmpty()) {
/* 243 */           var0.setItem(var3, var1);
/* 244 */           var2 = true; break;
/*     */         } 
/* 246 */         if (a(var4, var1)) {
/* 247 */           int var5 = var1.getMaxStackSize() - var4.getCount();
/* 248 */           int var6 = Math.min(var1.getCount(), var5);
/*     */           
/* 250 */           var1.subtract(var6);
/* 251 */           var4.add(var6);
/* 252 */           var2 = true;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 257 */     return var2;
/*     */   }
/*     */   
/*     */   private static int a(CommandListenerWrapper var0, BlockPosition var1, int var2, int var3, List<ItemStack> var4, a var5) throws CommandSyntaxException {
/* 261 */     IInventory var6 = a(var0, var1);
/*     */     
/* 263 */     int var7 = var6.getSize();
/* 264 */     if (var2 < 0 || var2 >= var7) {
/* 265 */       throw CommandReplaceItem.b.create(Integer.valueOf(var2));
/*     */     }
/*     */     
/* 268 */     List<ItemStack> var8 = Lists.newArrayListWithCapacity(var4.size());
/*     */     
/* 270 */     for (int var9 = 0; var9 < var3; var9++) {
/* 271 */       int var10 = var2 + var9;
/* 272 */       ItemStack var11 = (var9 < var4.size()) ? var4.get(var9) : ItemStack.b;
/*     */       
/* 274 */       if (var6.b(var10, var11)) {
/* 275 */         var6.setItem(var10, var11);
/* 276 */         var8.add(var11);
/*     */       } 
/*     */     } 
/*     */     
/* 280 */     var5.accept(var8);
/* 281 */     return var8.size();
/*     */   }
/*     */   
/*     */   private static boolean a(ItemStack var0, ItemStack var1) {
/* 285 */     return (var0.getItem() == var1.getItem() && var0
/* 286 */       .getDamage() == var1.getDamage() && var0
/* 287 */       .getCount() <= var0.getMaxStackSize() && 
/* 288 */       Objects.equals(var0.getTag(), var1.getTag()));
/*     */   }
/*     */   
/*     */   private static int a(Collection<EntityPlayer> var0, List<ItemStack> var1, a var2) throws CommandSyntaxException {
/* 292 */     List<ItemStack> var3 = Lists.newArrayListWithCapacity(var1.size());
/* 293 */     for (ItemStack var5 : var1) {
/* 294 */       for (EntityPlayer var7 : var0) {
/* 295 */         if (var7.inventory.pickup(var5.cloneItemStack())) {
/* 296 */           var3.add(var5);
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 301 */     var2.accept(var3);
/* 302 */     return var3.size();
/*     */   }
/*     */   
/*     */   private static void a(Entity var0, List<ItemStack> var1, int var2, int var3, List<ItemStack> var4) {
/* 306 */     for (int var5 = 0; var5 < var3; var5++) {
/* 307 */       ItemStack var6 = (var5 < var1.size()) ? var1.get(var5) : ItemStack.b;
/* 308 */       if (var0.a_(var2 + var5, var6.cloneItemStack())) {
/* 309 */         var4.add(var6);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private static int a(Collection<? extends Entity> var0, int var1, int var2, List<ItemStack> var3, a var4) throws CommandSyntaxException {
/* 315 */     List<ItemStack> var5 = Lists.newArrayListWithCapacity(var3.size());
/*     */     
/* 317 */     for (Entity var7 : var0) {
/* 318 */       if (var7 instanceof EntityPlayer) {
/* 319 */         EntityPlayer var8 = (EntityPlayer)var7;
/* 320 */         var8.defaultContainer.c();
/* 321 */         a(var7, var3, var1, var2, var5);
/* 322 */         var8.defaultContainer.c(); continue;
/*     */       } 
/* 324 */       a(var7, var3, var1, var2, var5);
/*     */     } 
/*     */ 
/*     */     
/* 328 */     var4.accept(var5);
/* 329 */     return var5.size();
/*     */   }
/*     */   
/*     */   private static int a(CommandListenerWrapper var0, Vec3D var1, List<ItemStack> var2, a var3) throws CommandSyntaxException {
/* 333 */     WorldServer var4 = var0.getWorld();
/* 334 */     var2.forEach(var2 -> {
/*     */           EntityItem var3 = new EntityItem(var0, var1.x, var1.y, var1.z, var2.cloneItemStack());
/*     */           
/*     */           var3.defaultPickupDelay();
/*     */           var0.addEntity(var3);
/*     */         });
/* 340 */     var3.accept(var2);
/* 341 */     return var2.size();
/*     */   }
/*     */   
/*     */   private static void a(CommandListenerWrapper var0, List<ItemStack> var1) {
/* 345 */     if (var1.size() == 1) {
/* 346 */       ItemStack var2 = var1.get(0);
/* 347 */       var0.sendMessage(new ChatMessage("commands.drop.success.single", new Object[] { Integer.valueOf(var2.getCount()), var2.C() }), false);
/*     */     } else {
/* 349 */       var0.sendMessage(new ChatMessage("commands.drop.success.multiple", new Object[] { Integer.valueOf(var1.size()) }), false);
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void a(CommandListenerWrapper var0, List<ItemStack> var1, MinecraftKey var2) {
/* 354 */     if (var1.size() == 1) {
/* 355 */       ItemStack var3 = var1.get(0);
/* 356 */       var0.sendMessage(new ChatMessage("commands.drop.success.single_with_table", new Object[] { Integer.valueOf(var3.getCount()), var3.C(), var2 }), false);
/*     */     } else {
/* 358 */       var0.sendMessage(new ChatMessage("commands.drop.success.multiple_with_table", new Object[] { Integer.valueOf(var1.size()), var2 }), false);
/*     */     } 
/*     */   }
/*     */   
/*     */   private static ItemStack a(CommandListenerWrapper var0, EnumItemSlot var1) throws CommandSyntaxException {
/* 363 */     Entity var2 = var0.g();
/* 364 */     if (var2 instanceof EntityLiving) {
/* 365 */       return ((EntityLiving)var2).getEquipment(var1);
/*     */     }
/* 367 */     throw b.create(var2.getScoreboardDisplayName());
/*     */   }
/*     */ 
/*     */   
/*     */   private static int a(CommandContext<CommandListenerWrapper> var0, BlockPosition var1, ItemStack var2, b var3) throws CommandSyntaxException {
/* 372 */     CommandListenerWrapper var4 = (CommandListenerWrapper)var0.getSource();
/* 373 */     WorldServer var5 = var4.getWorld();
/* 374 */     IBlockData var6 = var5.getType(var1);
/* 375 */     TileEntity var7 = var5.getTileEntity(var1);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 382 */     LootTableInfo.Builder var8 = (new LootTableInfo.Builder(var5)).<Vec3D>set(LootContextParameters.ORIGIN, Vec3D.a(var1)).<IBlockData>set(LootContextParameters.BLOCK_STATE, var6).<TileEntity>setOptional(LootContextParameters.BLOCK_ENTITY, var7).<Entity>setOptional(LootContextParameters.THIS_ENTITY, var4.getEntity()).set(LootContextParameters.TOOL, var2);
/*     */     
/* 384 */     List<ItemStack> var9 = var6.a(var8);
/* 385 */     return var3.accept(var0, var9, var2 -> a(var0, var2, var1.getBlock().r()));
/*     */   }
/*     */   
/*     */   private static int a(CommandContext<CommandListenerWrapper> var0, Entity var1, b var2) throws CommandSyntaxException {
/* 389 */     if (!(var1 instanceof EntityLiving)) {
/* 390 */       throw c.create(var1.getScoreboardDisplayName());
/*     */     }
/*     */     
/* 393 */     MinecraftKey var3 = ((EntityLiving)var1).do_();
/* 394 */     CommandListenerWrapper var4 = (CommandListenerWrapper)var0.getSource();
/*     */     
/* 396 */     LootTableInfo.Builder var5 = new LootTableInfo.Builder(var4.getWorld());
/* 397 */     Entity var6 = var4.getEntity();
/* 398 */     if (var6 instanceof EntityHuman) {
/* 399 */       var5.set(LootContextParameters.LAST_DAMAGE_PLAYER, (EntityHuman)var6);
/*     */     }
/* 401 */     var5.set(LootContextParameters.DAMAGE_SOURCE, DamageSource.MAGIC);
/* 402 */     var5.setOptional(LootContextParameters.DIRECT_KILLER_ENTITY, var6);
/* 403 */     var5.setOptional(LootContextParameters.KILLER_ENTITY, var6);
/* 404 */     var5.set(LootContextParameters.THIS_ENTITY, var1);
/* 405 */     var5.set(LootContextParameters.ORIGIN, var4.getPosition());
/*     */     
/* 407 */     LootTable var7 = var4.getServer().getLootTableRegistry().getLootTable(var3);
/* 408 */     List<ItemStack> var8 = var7.populateLoot(var5.build(LootContextParameterSets.ENTITY));
/* 409 */     return var2.accept(var0, var8, var2 -> a(var0, var2, var1));
/*     */   }
/*     */   
/*     */   private static int a(CommandContext<CommandListenerWrapper> var0, MinecraftKey var1, b var2) throws CommandSyntaxException {
/* 413 */     CommandListenerWrapper var3 = (CommandListenerWrapper)var0.getSource();
/*     */ 
/*     */ 
/*     */     
/* 417 */     LootTableInfo.Builder var4 = (new LootTableInfo.Builder(var3.getWorld())).<Entity>setOptional(LootContextParameters.THIS_ENTITY, var3.getEntity()).set(LootContextParameters.ORIGIN, var3.getPosition());
/*     */     
/* 419 */     return a(var0, var1, var4.build(LootContextParameterSets.CHEST), var2);
/*     */   }
/*     */   
/*     */   private static int a(CommandContext<CommandListenerWrapper> var0, MinecraftKey var1, BlockPosition var2, ItemStack var3, b var4) throws CommandSyntaxException {
/* 423 */     CommandListenerWrapper var5 = (CommandListenerWrapper)var0.getSource();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 429 */     LootTableInfo var6 = (new LootTableInfo.Builder(var5.getWorld())).<Vec3D>set(LootContextParameters.ORIGIN, Vec3D.a(var2)).<ItemStack>set(LootContextParameters.TOOL, var3).<Entity>setOptional(LootContextParameters.THIS_ENTITY, var5.getEntity()).build(LootContextParameterSets.FISHING);
/*     */     
/* 431 */     return a(var0, var1, var6, var4);
/*     */   }
/*     */   
/*     */   private static int a(CommandContext<CommandListenerWrapper> var0, MinecraftKey var1, LootTableInfo var2, b var3) throws CommandSyntaxException {
/* 435 */     CommandListenerWrapper var4 = (CommandListenerWrapper)var0.getSource();
/* 436 */     LootTable var5 = var4.getServer().getLootTableRegistry().getLootTable(var1);
/* 437 */     List<ItemStack> var6 = var5.populateLoot(var2);
/* 438 */     return var3.accept(var0, var6, var1 -> a(var0, var1));
/*     */   }
/*     */   
/*     */   @FunctionalInterface
/*     */   static interface c {
/*     */     ArgumentBuilder<CommandListenerWrapper, ?> construct(ArgumentBuilder<CommandListenerWrapper, ?> param1ArgumentBuilder, CommandLoot.b param1b);
/*     */   }
/*     */   
/*     */   @FunctionalInterface
/*     */   static interface b {
/*     */     int accept(CommandContext<CommandListenerWrapper> param1CommandContext, List<ItemStack> param1List, CommandLoot.a param1a) throws CommandSyntaxException;
/*     */   }
/*     */   
/*     */   @FunctionalInterface
/*     */   static interface a {
/*     */     void accept(List<ItemStack> param1List) throws CommandSyntaxException;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CommandLoot.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
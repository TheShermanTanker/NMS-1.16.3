/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.ImmutableList;
/*     */ import com.google.common.collect.Iterables;
/*     */ import com.mojang.brigadier.CommandDispatcher;
/*     */ import com.mojang.brigadier.Message;
/*     */ import com.mojang.brigadier.arguments.ArgumentType;
/*     */ import com.mojang.brigadier.arguments.DoubleArgumentType;
/*     */ import com.mojang.brigadier.arguments.IntegerArgumentType;
/*     */ import com.mojang.brigadier.builder.ArgumentBuilder;
/*     */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*     */ import com.mojang.brigadier.builder.RequiredArgumentBuilder;
/*     */ import com.mojang.brigadier.context.CommandContext;
/*     */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*     */ import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
/*     */ import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.function.BiConsumer;
/*     */ import java.util.function.Function;
/*     */ import java.util.function.Supplier;
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
/*     */ public class CommandData
/*     */ {
/*  45 */   private static final SimpleCommandExceptionType d = new SimpleCommandExceptionType(new ChatMessage("commands.data.merge.failed")); private static final DynamicCommandExceptionType e; private static final DynamicCommandExceptionType f; static {
/*  46 */     e = new DynamicCommandExceptionType(var0 -> new ChatMessage("commands.data.get.invalid", new Object[] { var0 }));
/*  47 */     f = new DynamicCommandExceptionType(var0 -> new ChatMessage("commands.data.get.unknown", new Object[] { var0 }));
/*  48 */   } private static final SimpleCommandExceptionType g = new SimpleCommandExceptionType(new ChatMessage("commands.data.get.multiple")); private static final DynamicCommandExceptionType h; static {
/*  49 */     h = new DynamicCommandExceptionType(var0 -> new ChatMessage("commands.data.modify.expected_list", new Object[] { var0 }));
/*  50 */     i = new DynamicCommandExceptionType(var0 -> new ChatMessage("commands.data.modify.expected_object", new Object[] { var0 }));
/*  51 */     j = new DynamicCommandExceptionType(var0 -> new ChatMessage("commands.data.modify.invalid_index", new Object[] { var0 }));
/*     */   }
/*  53 */   private static final DynamicCommandExceptionType i; private static final DynamicCommandExceptionType j; public static final List<Function<String, c>> a = (List<Function<String, c>>)ImmutableList.of(CommandDataAccessorEntity.a, CommandDataAccessorTile.a, CommandDataStorage.a); public static final List<c> b; public static final List<c> c;
/*     */   static {
/*  55 */     b = (List<c>)a.stream().map(var0 -> (c)var0.apply("target")).collect(ImmutableList.toImmutableList());
/*  56 */     c = (List<c>)a.stream().map(var0 -> (c)var0.apply("source")).collect(ImmutableList.toImmutableList());
/*     */   }
/*     */   public static void a(CommandDispatcher<CommandListenerWrapper> var0) {
/*  59 */     LiteralArgumentBuilder<CommandListenerWrapper> var1 = (LiteralArgumentBuilder<CommandListenerWrapper>)CommandDispatcher.a("data").requires(var0 -> var0.hasPermission(2));
/*     */     
/*  61 */     for (c var3 : b) {
/*  62 */       ((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)var1
/*  63 */         .then(var3
/*  64 */           .a((ArgumentBuilder)CommandDispatcher.a("merge"), var1 -> var1.then(CommandDispatcher.<T>a("nbt", ArgumentNBTTag.a()).executes(())))))
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*  71 */         .then(var3
/*  72 */           .a((ArgumentBuilder)CommandDispatcher.a("get"), var1 -> var1.executes(()).then(((RequiredArgumentBuilder)CommandDispatcher.<T>a("path", ArgumentNBTKey.a()).executes(())).then(CommandDispatcher.<T>a("scale", (ArgumentType<T>)DoubleArgumentType.doubleArg()).executes(()))))))
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
/*  84 */         .then(var3
/*  85 */           .a((ArgumentBuilder)CommandDispatcher.a("remove"), var1 -> var1.then(CommandDispatcher.<T>a("path", ArgumentNBTKey.a()).executes(())))))
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*  92 */         .then(a((var0, var1) -> var0.then(CommandDispatcher.a("insert").then(CommandDispatcher.<T>a("index", (ArgumentType<T>)IntegerArgumentType.integer()).then(var1.create(())))).then(CommandDispatcher.a("prepend").then(var1.create(()))).then(CommandDispatcher.a("append").then(var1.create(()))).then(CommandDispatcher.a("set").then(var1.create(()))).then(CommandDispatcher.a("merge").then(var1.create(())))));
/*     */     }
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
/* 159 */     var0.register(var1);
/*     */   }
/*     */   
/*     */   private static int a(int var0, NBTTagCompound var1, ArgumentNBTKey.h var2, List<NBTBase> var3) throws CommandSyntaxException {
/* 163 */     Collection<NBTBase> var4 = var2.a(var1, NBTTagList::new);
/*     */     
/* 165 */     int var5 = 0;
/* 166 */     for (NBTBase var7 : var4) {
/* 167 */       if (!(var7 instanceof NBTList)) {
/* 168 */         throw h.create(var7);
/*     */       }
/*     */       
/* 171 */       boolean var8 = false;
/* 172 */       NBTList<?> var9 = (NBTList)var7;
/* 173 */       int var10 = (var0 < 0) ? (var9.size() + var0 + 1) : var0;
/* 174 */       for (NBTBase var12 : var3) {
/*     */         try {
/* 176 */           if (var9.b(var10, var12.clone())) {
/* 177 */             var10++;
/* 178 */             var8 = true;
/*     */           } 
/* 180 */         } catch (IndexOutOfBoundsException var13) {
/* 181 */           throw j.create(Integer.valueOf(var10));
/*     */         } 
/*     */       } 
/* 184 */       var5 += var8 ? 1 : 0;
/*     */     } 
/*     */     
/* 187 */     return var5;
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
/*     */   private static ArgumentBuilder<CommandListenerWrapper, ?> a(BiConsumer<ArgumentBuilder<CommandListenerWrapper, ?>, b> var0) {
/* 199 */     LiteralArgumentBuilder<CommandListenerWrapper> var1 = CommandDispatcher.a("modify");
/*     */     
/* 201 */     for (Iterator<c> iterator = b.iterator(); iterator.hasNext(); ) { c var3 = iterator.next();
/* 202 */       var3.a((ArgumentBuilder)var1, var2 -> {
/*     */             RequiredArgumentBuilder<CommandListenerWrapper, ?> requiredArgumentBuilder = CommandDispatcher.a("targetPath", ArgumentNBTKey.a());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/*     */             for (c var5 : c) {
/*     */               var0.accept(requiredArgumentBuilder, ());
/*     */             }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/*     */             var0.accept(requiredArgumentBuilder, ());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/*     */             return var2.then((ArgumentBuilder)requiredArgumentBuilder);
/*     */           }); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 241 */     return (ArgumentBuilder)var1;
/*     */   }
/*     */   
/*     */   private static int a(CommandContext<CommandListenerWrapper> var0, c var1, a var2, List<NBTBase> var3) throws CommandSyntaxException {
/* 245 */     CommandDataAccessor var4 = var1.a(var0);
/* 246 */     ArgumentNBTKey.h var5 = ArgumentNBTKey.a(var0, "targetPath");
/*     */     
/* 248 */     NBTTagCompound var6 = var4.a();
/*     */     
/* 250 */     int var7 = var2.modify(var0, var6, var5, var3);
/*     */     
/* 252 */     if (var7 == 0) {
/* 253 */       throw d.create();
/*     */     }
/*     */     
/* 256 */     var4.a(var6);
/* 257 */     ((CommandListenerWrapper)var0.getSource()).sendMessage(var4.b(), true);
/*     */     
/* 259 */     return var7;
/*     */   }
/*     */   
/*     */   private static int a(CommandListenerWrapper var0, CommandDataAccessor var1, ArgumentNBTKey.h var2) throws CommandSyntaxException {
/* 263 */     NBTTagCompound var3 = var1.a();
/*     */     
/* 265 */     int var4 = var2.c(var3);
/*     */     
/* 267 */     if (var4 == 0) {
/* 268 */       throw d.create();
/*     */     }
/*     */     
/* 271 */     var1.a(var3);
/* 272 */     var0.sendMessage(var1.b(), true);
/* 273 */     return var4;
/*     */   }
/*     */   
/*     */   private static NBTBase a(ArgumentNBTKey.h var0, CommandDataAccessor var1) throws CommandSyntaxException {
/* 277 */     Collection<NBTBase> var2 = var0.a(var1.a());
/* 278 */     Iterator<NBTBase> var3 = var2.iterator();
/* 279 */     NBTBase var4 = var3.next();
/* 280 */     if (var3.hasNext()) {
/* 281 */       throw g.create();
/*     */     }
/*     */     
/* 284 */     return var4;
/*     */   }
/*     */   private static int b(CommandListenerWrapper var0, CommandDataAccessor var1, ArgumentNBTKey.h var2) throws CommandSyntaxException {
/*     */     int var4;
/* 288 */     NBTBase var3 = a(var2, var1);
/*     */     
/* 290 */     if (var3 instanceof NBTNumber) {
/* 291 */       var4 = MathHelper.floor(((NBTNumber)var3).asDouble());
/* 292 */     } else if (var3 instanceof NBTList) {
/* 293 */       var4 = ((NBTList)var3).size();
/* 294 */     } else if (var3 instanceof NBTTagCompound) {
/* 295 */       var4 = ((NBTTagCompound)var3).e();
/* 296 */     } else if (var3 instanceof NBTTagString) {
/* 297 */       var4 = var3.asString().length();
/*     */     } else {
/* 299 */       throw f.create(var2.toString());
/*     */     } 
/* 301 */     var0.sendMessage(var1.a(var3), false);
/* 302 */     return var4;
/*     */   }
/*     */   
/*     */   private static int a(CommandListenerWrapper var0, CommandDataAccessor var1, ArgumentNBTKey.h var2, double var3) throws CommandSyntaxException {
/* 306 */     NBTBase var5 = a(var2, var1);
/* 307 */     if (!(var5 instanceof NBTNumber)) {
/* 308 */       throw e.create(var2.toString());
/*     */     }
/* 310 */     int var6 = MathHelper.floor(((NBTNumber)var5).asDouble() * var3);
/* 311 */     var0.sendMessage(var1.a(var2, var3, var6), false);
/* 312 */     return var6;
/*     */   }
/*     */   
/*     */   private static int a(CommandListenerWrapper var0, CommandDataAccessor var1) throws CommandSyntaxException {
/* 316 */     var0.sendMessage(var1.a(var1.a()), false);
/* 317 */     return 1;
/*     */   }
/*     */   
/*     */   private static int a(CommandListenerWrapper var0, CommandDataAccessor var1, NBTTagCompound var2) throws CommandSyntaxException {
/* 321 */     NBTTagCompound var3 = var1.a();
/* 322 */     NBTTagCompound var4 = var3.clone().a(var2);
/*     */     
/* 324 */     if (var3.equals(var4)) {
/* 325 */       throw d.create();
/*     */     }
/*     */     
/* 328 */     var1.a(var4);
/*     */     
/* 330 */     var0.sendMessage(var1.b(), true);
/* 331 */     return 1;
/*     */   }
/*     */   
/*     */   public static interface c {
/*     */     CommandDataAccessor a(CommandContext<CommandListenerWrapper> param1CommandContext) throws CommandSyntaxException;
/*     */     
/*     */     ArgumentBuilder<CommandListenerWrapper, ?> a(ArgumentBuilder<CommandListenerWrapper, ?> param1ArgumentBuilder, Function<ArgumentBuilder<CommandListenerWrapper, ?>, ArgumentBuilder<CommandListenerWrapper, ?>> param1Function);
/*     */   }
/*     */   
/*     */   static interface b {
/*     */     ArgumentBuilder<CommandListenerWrapper, ?> create(CommandData.a param1a);
/*     */   }
/*     */   
/*     */   static interface a {
/*     */     int modify(CommandContext<CommandListenerWrapper> param1CommandContext, NBTTagCompound param1NBTTagCompound, ArgumentNBTKey.h param1h, List<NBTBase> param1List) throws CommandSyntaxException;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CommandData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
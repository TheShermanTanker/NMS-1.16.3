/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.mojang.brigadier.CommandDispatcher;
/*     */ import com.mojang.brigadier.Message;
/*     */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*     */ import com.mojang.brigadier.builder.RequiredArgumentBuilder;
/*     */ import com.mojang.brigadier.context.CommandContext;
/*     */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*     */ import com.mojang.brigadier.exceptions.Dynamic2CommandExceptionType;
/*     */ import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import java.util.function.Predicate;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CommandFill
/*     */ {
/*     */   private static final Dynamic2CommandExceptionType a;
/*     */   
/*     */   static {
/*  38 */     a = new Dynamic2CommandExceptionType((var0, var1) -> new ChatMessage("commands.fill.toobig", new Object[] { var0, var1 }));
/*  39 */   } private static final ArgumentTileLocation b = new ArgumentTileLocation(Blocks.AIR.getBlockData(), Collections.emptySet(), null);
/*  40 */   private static final SimpleCommandExceptionType c = new SimpleCommandExceptionType(new ChatMessage("commands.fill.failed"));
/*     */   
/*     */   public static void a(CommandDispatcher<CommandListenerWrapper> var0) {
/*  43 */     var0.register(
/*  44 */         (LiteralArgumentBuilder)((LiteralArgumentBuilder)CommandDispatcher.a("fill")
/*  45 */         .requires(var0 -> var0.hasPermission(2)))
/*  46 */         .then(
/*  47 */           CommandDispatcher.<T>a("from", ArgumentPosition.a())
/*  48 */           .then(
/*  49 */             CommandDispatcher.<T>a("to", ArgumentPosition.a())
/*  50 */             .then((
/*  51 */               (RequiredArgumentBuilder)((RequiredArgumentBuilder)((RequiredArgumentBuilder)((RequiredArgumentBuilder)((RequiredArgumentBuilder)CommandDispatcher.<T>a("block", ArgumentTile.a())
/*  52 */               .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), new StructureBoundingBox(ArgumentPosition.a(var0, "from"), ArgumentPosition.a(var0, "to")), ArgumentTile.a(var0, "block"), Mode.REPLACE, null)))
/*  53 */               .then((
/*  54 */                 (LiteralArgumentBuilder)CommandDispatcher.a("replace")
/*  55 */                 .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), new StructureBoundingBox(ArgumentPosition.a(var0, "from"), ArgumentPosition.a(var0, "to")), ArgumentTile.a(var0, "block"), Mode.REPLACE, null)))
/*  56 */                 .then(
/*  57 */                   CommandDispatcher.<T>a("filter", ArgumentBlockPredicate.a())
/*  58 */                   .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), new StructureBoundingBox(ArgumentPosition.a(var0, "from"), ArgumentPosition.a(var0, "to")), ArgumentTile.a(var0, "block"), Mode.REPLACE, ArgumentBlockPredicate.a(var0, "filter"))))))
/*     */ 
/*     */               
/*  61 */               .then(
/*  62 */                 CommandDispatcher.a("keep")
/*  63 */                 .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), new StructureBoundingBox(ArgumentPosition.a(var0, "from"), ArgumentPosition.a(var0, "to")), ArgumentTile.a(var0, "block"), Mode.REPLACE, ()))))
/*     */               
/*  65 */               .then(
/*  66 */                 CommandDispatcher.a("outline")
/*  67 */                 .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), new StructureBoundingBox(ArgumentPosition.a(var0, "from"), ArgumentPosition.a(var0, "to")), ArgumentTile.a(var0, "block"), Mode.OUTLINE, null))))
/*     */               
/*  69 */               .then(
/*  70 */                 CommandDispatcher.a("hollow")
/*  71 */                 .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), new StructureBoundingBox(ArgumentPosition.a(var0, "from"), ArgumentPosition.a(var0, "to")), ArgumentTile.a(var0, "block"), Mode.HOLLOW, null))))
/*     */               
/*  73 */               .then(
/*  74 */                 CommandDispatcher.a("destroy")
/*  75 */                 .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), new StructureBoundingBox(ArgumentPosition.a(var0, "from"), ArgumentPosition.a(var0, "to")), ArgumentTile.a(var0, "block"), Mode.DESTROY, null)))))));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static int a(CommandListenerWrapper var0, StructureBoundingBox var1, ArgumentTileLocation var2, Mode var3, @Nullable Predicate<ShapeDetectorBlock> var4) throws CommandSyntaxException {
/*  84 */     int var5 = var1.d() * var1.e() * var1.f();
/*  85 */     if (var5 > 32768) {
/*  86 */       throw a.create(Integer.valueOf(32768), Integer.valueOf(var5));
/*     */     }
/*     */     
/*  89 */     List<BlockPosition> var6 = Lists.newArrayList();
/*  90 */     WorldServer var7 = var0.getWorld();
/*  91 */     int var8 = 0;
/*     */     
/*  93 */     for (BlockPosition var10 : BlockPosition.b(var1.a, var1.b, var1.c, var1.d, var1.e, var1.f)) {
/*  94 */       if (var4 != null && !var4.test(new ShapeDetectorBlock(var7, var10, true))) {
/*     */         continue;
/*     */       }
/*  97 */       ArgumentTileLocation var11 = var3.e.filter(var1, var10, var2, var7);
/*  98 */       if (var11 == null) {
/*     */         continue;
/*     */       }
/*     */       
/* 102 */       TileEntity var12 = var7.getTileEntity(var10);
/* 103 */       Clearable.a(var12);
/*     */       
/* 105 */       if (!var11.a(var7, var10, 2)) {
/*     */         continue;
/*     */       }
/*     */       
/* 109 */       var6.add(var10.immutableCopy());
/* 110 */       var8++;
/*     */     } 
/*     */     
/* 113 */     for (BlockPosition var10 : var6) {
/* 114 */       Block var11 = var7.getType(var10).getBlock();
/* 115 */       var7.update(var10, var11);
/*     */     } 
/*     */     
/* 118 */     if (var8 == 0) {
/* 119 */       throw c.create();
/*     */     }
/*     */     
/* 122 */     var0.sendMessage(new ChatMessage("commands.fill.success", new Object[] { Integer.valueOf(var8) }), true);
/*     */     
/* 124 */     return var8;
/*     */   }
/*     */   enum Mode { REPLACE, OUTLINE, HOLLOW, DESTROY;
/*     */     static {
/* 128 */       REPLACE = new Mode("REPLACE", 0, (var0, var1, var2, var3) -> var2);
/* 129 */       OUTLINE = new Mode("OUTLINE", 1, (var0, var1, var2, var3) -> 
/* 130 */           (var1.getX() == var0.a || var1.getX() == var0.d || var1.getY() == var0.b || var1.getY() == var0.e || var1.getZ() == var0.c || var1.getZ() == var0.f) ? var2 : null);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 136 */       HOLLOW = new Mode("HOLLOW", 2, (var0, var1, var2, var3) -> 
/* 137 */           (var1.getX() == var0.a || var1.getX() == var0.d || var1.getY() == var0.b || var1.getY() == var0.e || var1.getZ() == var0.c || var1.getZ() == var0.f) ? var2 : CommandFill.a());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 143 */       DESTROY = new Mode("DESTROY", 3, (var0, var1, var2, var3) -> {
/*     */             var3.b(var1, true);
/*     */             return var2;
/*     */           });
/*     */     }
/*     */     public final CommandSetBlock.Filter e;
/*     */     
/*     */     Mode(CommandSetBlock.Filter var2) {
/* 151 */       this.e = var2;
/*     */     } }
/*     */ 
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CommandFill.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
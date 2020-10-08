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
/*     */ import java.util.Deque;
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
/*     */ 
/*     */ 
/*     */ public class CommandClone
/*     */ {
/*     */   private static final Dynamic2CommandExceptionType c;
/*  38 */   private static final SimpleCommandExceptionType b = new SimpleCommandExceptionType(new ChatMessage("commands.clone.overlap")); static {
/*  39 */     c = new Dynamic2CommandExceptionType((var0, var1) -> new ChatMessage("commands.clone.toobig", new Object[] { var0, var1 }));
/*  40 */   } private static final SimpleCommandExceptionType d = new SimpleCommandExceptionType(new ChatMessage("commands.clone.failed")); static {
/*  41 */     a = (var0 -> !var0.a().isAir());
/*     */   } public static final Predicate<ShapeDetectorBlock> a;
/*     */   public static void a(CommandDispatcher<CommandListenerWrapper> var0) {
/*  44 */     var0.register(
/*  45 */         (LiteralArgumentBuilder)((LiteralArgumentBuilder)CommandDispatcher.a("clone")
/*  46 */         .requires(var0 -> var0.hasPermission(2)))
/*  47 */         .then(
/*  48 */           CommandDispatcher.<T>a("begin", ArgumentPosition.a())
/*  49 */           .then(
/*  50 */             CommandDispatcher.<T>a("end", ArgumentPosition.a())
/*  51 */             .then((
/*  52 */               (RequiredArgumentBuilder)((RequiredArgumentBuilder)((RequiredArgumentBuilder)CommandDispatcher.<T>a("destination", ArgumentPosition.a())
/*  53 */               .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), ArgumentPosition.a(var0, "begin"), ArgumentPosition.a(var0, "end"), ArgumentPosition.a(var0, "destination"), (), Mode.NORMAL)))
/*  54 */               .then((
/*  55 */                 (LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)CommandDispatcher.a("replace")
/*  56 */                 .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), ArgumentPosition.a(var0, "begin"), ArgumentPosition.a(var0, "end"), ArgumentPosition.a(var0, "destination"), (), Mode.NORMAL)))
/*  57 */                 .then(
/*  58 */                   CommandDispatcher.a("force")
/*  59 */                   .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), ArgumentPosition.a(var0, "begin"), ArgumentPosition.a(var0, "end"), ArgumentPosition.a(var0, "destination"), (), Mode.FORCE))))
/*     */                 
/*  61 */                 .then(
/*  62 */                   CommandDispatcher.a("move")
/*  63 */                   .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), ArgumentPosition.a(var0, "begin"), ArgumentPosition.a(var0, "end"), ArgumentPosition.a(var0, "destination"), (), Mode.MOVE))))
/*     */                 
/*  65 */                 .then(
/*  66 */                   CommandDispatcher.a("normal")
/*  67 */                   .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), ArgumentPosition.a(var0, "begin"), ArgumentPosition.a(var0, "end"), ArgumentPosition.a(var0, "destination"), (), Mode.NORMAL)))))
/*     */ 
/*     */               
/*  70 */               .then((
/*  71 */                 (LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)CommandDispatcher.a("masked")
/*  72 */                 .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), ArgumentPosition.a(var0, "begin"), ArgumentPosition.a(var0, "end"), ArgumentPosition.a(var0, "destination"), a, Mode.NORMAL)))
/*  73 */                 .then(
/*  74 */                   CommandDispatcher.a("force")
/*  75 */                   .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), ArgumentPosition.a(var0, "begin"), ArgumentPosition.a(var0, "end"), ArgumentPosition.a(var0, "destination"), a, Mode.FORCE))))
/*     */                 
/*  77 */                 .then(
/*  78 */                   CommandDispatcher.a("move")
/*  79 */                   .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), ArgumentPosition.a(var0, "begin"), ArgumentPosition.a(var0, "end"), ArgumentPosition.a(var0, "destination"), a, Mode.MOVE))))
/*     */                 
/*  81 */                 .then(
/*  82 */                   CommandDispatcher.a("normal")
/*  83 */                   .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), ArgumentPosition.a(var0, "begin"), ArgumentPosition.a(var0, "end"), ArgumentPosition.a(var0, "destination"), a, Mode.NORMAL)))))
/*     */ 
/*     */               
/*  86 */               .then(
/*  87 */                 CommandDispatcher.a("filtered")
/*  88 */                 .then((
/*  89 */                   (RequiredArgumentBuilder)((RequiredArgumentBuilder)((RequiredArgumentBuilder)CommandDispatcher.<T>a("filter", ArgumentBlockPredicate.a())
/*  90 */                   .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), ArgumentPosition.a(var0, "begin"), ArgumentPosition.a(var0, "end"), ArgumentPosition.a(var0, "destination"), ArgumentBlockPredicate.a(var0, "filter"), Mode.NORMAL)))
/*  91 */                   .then(
/*  92 */                     CommandDispatcher.a("force")
/*  93 */                     .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), ArgumentPosition.a(var0, "begin"), ArgumentPosition.a(var0, "end"), ArgumentPosition.a(var0, "destination"), ArgumentBlockPredicate.a(var0, "filter"), Mode.FORCE))))
/*     */                   
/*  95 */                   .then(
/*  96 */                     CommandDispatcher.a("move")
/*  97 */                     .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), ArgumentPosition.a(var0, "begin"), ArgumentPosition.a(var0, "end"), ArgumentPosition.a(var0, "destination"), ArgumentBlockPredicate.a(var0, "filter"), Mode.MOVE))))
/*     */                   
/*  99 */                   .then(
/* 100 */                     CommandDispatcher.a("normal")
/* 101 */                     .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), ArgumentPosition.a(var0, "begin"), ArgumentPosition.a(var0, "end"), ArgumentPosition.a(var0, "destination"), ArgumentBlockPredicate.a(var0, "filter"), Mode.NORMAL)))))))));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static int a(CommandListenerWrapper var0, BlockPosition var1, BlockPosition var2, BlockPosition var3, Predicate<ShapeDetectorBlock> var4, Mode var5) throws CommandSyntaxException {
/* 112 */     StructureBoundingBox var6 = new StructureBoundingBox(var1, var2);
/* 113 */     BlockPosition var7 = var3.a(var6.c());
/* 114 */     StructureBoundingBox var8 = new StructureBoundingBox(var3, var7);
/* 115 */     if (!var5.a() && var8.b(var6)) {
/* 116 */       throw b.create();
/*     */     }
/* 118 */     int var9 = var6.d() * var6.e() * var6.f();
/* 119 */     if (var9 > 32768) {
/* 120 */       throw c.create(Integer.valueOf(32768), Integer.valueOf(var9));
/*     */     }
/* 122 */     WorldServer var10 = var0.getWorld();
/* 123 */     if (!var10.areChunksLoadedBetween(var1, var2) || !var10.areChunksLoadedBetween(var3, var7)) {
/* 124 */       throw ArgumentPosition.a.create();
/*     */     }
/*     */     
/* 127 */     List<CommandCloneStoredTileEntity> var11 = Lists.newArrayList();
/* 128 */     List<CommandCloneStoredTileEntity> var12 = Lists.newArrayList();
/* 129 */     List<CommandCloneStoredTileEntity> var13 = Lists.newArrayList();
/* 130 */     Deque<BlockPosition> var14 = Lists.newLinkedList();
/*     */     
/* 132 */     BlockPosition var15 = new BlockPosition(var8.a - var6.a, var8.b - var6.b, var8.c - var6.c);
/* 133 */     for (int i = var6.c; i <= var6.f; i++) {
/* 134 */       for (int j = var6.b; j <= var6.e; j++) {
/* 135 */         for (int k = var6.a; k <= var6.d; k++) {
/* 136 */           BlockPosition var19 = new BlockPosition(k, j, i);
/* 137 */           BlockPosition var20 = var19.a(var15);
/* 138 */           ShapeDetectorBlock var21 = new ShapeDetectorBlock(var10, var19, false);
/* 139 */           IBlockData var22 = var21.a();
/* 140 */           if (var4.test(var21)) {
/*     */ 
/*     */ 
/*     */             
/* 144 */             TileEntity var23 = var10.getTileEntity(var19);
/* 145 */             if (var23 != null) {
/* 146 */               NBTTagCompound var24 = var23.save(new NBTTagCompound());
/* 147 */               var12.add(new CommandCloneStoredTileEntity(var20, var22, var24));
/* 148 */               var14.addLast(var19);
/* 149 */             } else if (var22.i(var10, var19) || var22.r(var10, var19)) {
/* 150 */               var11.add(new CommandCloneStoredTileEntity(var20, var22, null));
/* 151 */               var14.addLast(var19);
/*     */             } else {
/* 153 */               var13.add(new CommandCloneStoredTileEntity(var20, var22, null));
/* 154 */               var14.addFirst(var19);
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 160 */     if (var5 == Mode.MOVE) {
/* 161 */       for (BlockPosition blockPosition : var14) {
/* 162 */         TileEntity tileEntity = var10.getTileEntity(blockPosition);
/* 163 */         Clearable.a(tileEntity);
/* 164 */         var10.setTypeAndData(blockPosition, Blocks.BARRIER.getBlockData(), 2);
/*     */       } 
/* 166 */       for (BlockPosition blockPosition : var14) {
/* 167 */         var10.setTypeAndData(blockPosition, Blocks.AIR.getBlockData(), 3);
/*     */       }
/*     */     } 
/*     */     
/* 171 */     List<CommandCloneStoredTileEntity> var16 = Lists.newArrayList();
/* 172 */     var16.addAll(var11);
/* 173 */     var16.addAll(var12);
/* 174 */     var16.addAll(var13);
/*     */     
/* 176 */     List<CommandCloneStoredTileEntity> var17 = Lists.reverse(var16);
/* 177 */     for (CommandCloneStoredTileEntity var19 : var17) {
/* 178 */       TileEntity var20 = var10.getTileEntity(var19.a);
/* 179 */       Clearable.a(var20);
/* 180 */       var10.setTypeAndData(var19.a, Blocks.BARRIER.getBlockData(), 2);
/*     */     } 
/*     */     
/* 183 */     int var18 = 0;
/* 184 */     for (CommandCloneStoredTileEntity var20 : var16) {
/* 185 */       if (var10.setTypeAndData(var20.a, var20.b, 2)) {
/* 186 */         var18++;
/*     */       }
/*     */     } 
/* 189 */     for (CommandCloneStoredTileEntity var20 : var12) {
/* 190 */       TileEntity var21 = var10.getTileEntity(var20.a);
/* 191 */       if (var20.c != null && var21 != null) {
/* 192 */         var20.c.setInt("x", var20.a.getX());
/* 193 */         var20.c.setInt("y", var20.a.getY());
/* 194 */         var20.c.setInt("z", var20.a.getZ());
/* 195 */         var21.load(var20.b, var20.c);
/* 196 */         var21.update();
/*     */       } 
/* 198 */       var10.setTypeAndData(var20.a, var20.b, 2);
/*     */     } 
/*     */     
/* 201 */     for (CommandCloneStoredTileEntity var20 : var17) {
/* 202 */       var10.update(var20.a, var20.b.getBlock());
/*     */     }
/*     */     
/* 205 */     var10.getBlockTickList().a(var6, var15);
/*     */     
/* 207 */     if (var18 == 0) {
/* 208 */       throw d.create();
/*     */     }
/*     */     
/* 211 */     var0.sendMessage(new ChatMessage("commands.clone.success", new Object[] { Integer.valueOf(var18) }), true);
/*     */     
/* 213 */     return var18;
/*     */   }
/*     */   
/*     */   enum Mode {
/* 217 */     FORCE(true),
/* 218 */     MOVE(true),
/* 219 */     NORMAL(false);
/*     */     
/*     */     private final boolean d;
/*     */ 
/*     */     
/*     */     Mode(boolean var2) {
/* 225 */       this.d = var2;
/*     */     }
/*     */     
/*     */     public boolean a() {
/* 229 */       return this.d;
/*     */     }
/*     */   }
/*     */   
/*     */   static class CommandCloneStoredTileEntity {
/*     */     public final BlockPosition a;
/*     */     public final IBlockData b;
/*     */     @Nullable
/*     */     public final NBTTagCompound c;
/*     */     
/*     */     public CommandCloneStoredTileEntity(BlockPosition var0, IBlockData var1, @Nullable NBTTagCompound var2) {
/* 240 */       this.a = var0;
/* 241 */       this.b = var1;
/* 242 */       this.c = var2;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CommandClone.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.base.Joiner;
/*     */ import com.mojang.brigadier.CommandDispatcher;
/*     */ import com.mojang.brigadier.Message;
/*     */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*     */ import com.mojang.brigadier.builder.RequiredArgumentBuilder;
/*     */ import com.mojang.brigadier.context.CommandContext;
/*     */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*     */ import com.mojang.brigadier.exceptions.Dynamic2CommandExceptionType;
/*     */ import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
/*     */ import it.unimi.dsi.fastutil.longs.LongSet;
/*     */ import java.util.function.Function;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CommandForceload
/*     */ {
/*     */   private static final Dynamic2CommandExceptionType a;
/*     */   private static final Dynamic2CommandExceptionType b;
/*     */   
/*     */   static {
/*  28 */     a = new Dynamic2CommandExceptionType((var0, var1) -> new ChatMessage("commands.forceload.toobig", new Object[] { var0, var1 }));
/*  29 */     b = new Dynamic2CommandExceptionType((var0, var1) -> new ChatMessage("commands.forceload.query.failure", new Object[] { var0, var1 }));
/*  30 */   } private static final SimpleCommandExceptionType c = new SimpleCommandExceptionType(new ChatMessage("commands.forceload.added.failure"));
/*  31 */   private static final SimpleCommandExceptionType d = new SimpleCommandExceptionType(new ChatMessage("commands.forceload.removed.failure"));
/*     */   
/*     */   public static void a(CommandDispatcher<CommandListenerWrapper> var0) {
/*  34 */     var0.register(
/*  35 */         (LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)CommandDispatcher.a("forceload")
/*  36 */         .requires(var0 -> var0.hasPermission(2)))
/*  37 */         .then(
/*  38 */           CommandDispatcher.a("add")
/*  39 */           .then((
/*  40 */             (RequiredArgumentBuilder)CommandDispatcher.<T>a("from", ArgumentVec2I.a())
/*  41 */             .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), ArgumentVec2I.a(var0, "from"), ArgumentVec2I.a(var0, "from"), true)))
/*  42 */             .then(
/*  43 */               CommandDispatcher.<T>a("to", ArgumentVec2I.a())
/*  44 */               .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), ArgumentVec2I.a(var0, "from"), ArgumentVec2I.a(var0, "to"), true))))))
/*     */         
/*  46 */         .then((
/*  47 */           (LiteralArgumentBuilder)CommandDispatcher.a("remove")
/*  48 */           .then((
/*  49 */             (RequiredArgumentBuilder)CommandDispatcher.<T>a("from", ArgumentVec2I.a())
/*  50 */             .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), ArgumentVec2I.a(var0, "from"), ArgumentVec2I.a(var0, "from"), false)))
/*  51 */             .then(
/*  52 */               CommandDispatcher.<T>a("to", ArgumentVec2I.a())
/*  53 */               .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), ArgumentVec2I.a(var0, "from"), ArgumentVec2I.a(var0, "to"), false)))))
/*  54 */           .then(
/*  55 */             CommandDispatcher.a("all")
/*  56 */             .executes(var0 -> b((CommandListenerWrapper)var0.getSource())))))
/*     */ 
/*     */         
/*  59 */         .then((
/*  60 */           (LiteralArgumentBuilder)CommandDispatcher.a("query")
/*  61 */           .executes(var0 -> a((CommandListenerWrapper)var0.getSource())))
/*  62 */           .then(
/*  63 */             CommandDispatcher.<T>a("pos", ArgumentVec2I.a())
/*  64 */             .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), ArgumentVec2I.a(var0, "pos"))))));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static int a(CommandListenerWrapper var0, BlockPosition2D var1) throws CommandSyntaxException {
/*  71 */     ChunkCoordIntPair var2 = new ChunkCoordIntPair(var1.a >> 4, var1.b >> 4);
/*  72 */     WorldServer var3 = var0.getWorld();
/*  73 */     ResourceKey<World> var4 = var3.getDimensionKey();
/*  74 */     boolean var5 = var3.getForceLoadedChunks().contains(var2.pair());
/*     */     
/*  76 */     if (var5) {
/*  77 */       var0.sendMessage(new ChatMessage("commands.forceload.query.success", new Object[] { var2, var4.a() }), false);
/*  78 */       return 1;
/*     */     } 
/*  80 */     throw b.create(var2, var4.a());
/*     */   }
/*     */ 
/*     */   
/*     */   private static int a(CommandListenerWrapper var0) {
/*  85 */     WorldServer var1 = var0.getWorld();
/*  86 */     ResourceKey<World> var2 = var1.getDimensionKey();
/*  87 */     LongSet var3 = var1.getForceLoadedChunks();
/*  88 */     int var4 = var3.size();
/*     */     
/*  90 */     if (var4 > 0) {
/*  91 */       String var5 = Joiner.on(", ").join(var3.stream().sorted().map(ChunkCoordIntPair::new).map(ChunkCoordIntPair::toString).iterator());
/*     */       
/*  93 */       if (var4 == 1) {
/*  94 */         var0.sendMessage(new ChatMessage("commands.forceload.list.single", new Object[] { var2.a(), var5 }), false);
/*     */       } else {
/*  96 */         var0.sendMessage(new ChatMessage("commands.forceload.list.multiple", new Object[] { Integer.valueOf(var4), var2.a(), var5 }), false);
/*     */       } 
/*     */     } else {
/*  99 */       var0.sendFailureMessage(new ChatMessage("commands.forceload.added.none", new Object[] { var2.a() }));
/*     */     } 
/* 101 */     return var4;
/*     */   }
/*     */   
/*     */   private static int b(CommandListenerWrapper var0) {
/* 105 */     WorldServer var1 = var0.getWorld();
/* 106 */     ResourceKey<World> var2 = var1.getDimensionKey();
/* 107 */     LongSet var3 = var1.getForceLoadedChunks();
/* 108 */     var3.forEach(var1 -> var0.setForceLoaded(ChunkCoordIntPair.getX(var1), ChunkCoordIntPair.getZ(var1), false));
/* 109 */     var0.sendMessage(new ChatMessage("commands.forceload.removed.all", new Object[] { var2.a() }), true);
/* 110 */     return 0;
/*     */   }
/*     */   
/*     */   private static int a(CommandListenerWrapper var0, BlockPosition2D var1, BlockPosition2D var2, boolean var3) throws CommandSyntaxException {
/* 114 */     int var4 = Math.min(var1.a, var2.a);
/* 115 */     int var5 = Math.min(var1.b, var2.b);
/* 116 */     int var6 = Math.max(var1.a, var2.a);
/* 117 */     int var7 = Math.max(var1.b, var2.b);
/*     */     
/* 119 */     if (var4 < -30000000 || var5 < -30000000 || var6 >= 30000000 || var7 >= 30000000)
/*     */     {
/*     */ 
/*     */ 
/*     */       
/* 124 */       throw ArgumentPosition.b.create();
/*     */     }
/*     */     
/* 127 */     int var8 = var4 >> 4;
/* 128 */     int var9 = var5 >> 4;
/* 129 */     int var10 = var6 >> 4;
/* 130 */     int var11 = var7 >> 4;
/*     */     
/* 132 */     long var12 = ((var10 - var8) + 1L) * ((var11 - var9) + 1L);
/*     */     
/* 134 */     if (var12 > 256L) {
/* 135 */       throw a.create(Integer.valueOf(256), Long.valueOf(var12));
/*     */     }
/*     */     
/* 138 */     WorldServer var14 = var0.getWorld();
/* 139 */     ResourceKey<World> var15 = var14.getDimensionKey();
/*     */     
/* 141 */     ChunkCoordIntPair var16 = null;
/* 142 */     int var17 = 0;
/* 143 */     for (int var18 = var8; var18 <= var10; var18++) {
/* 144 */       for (int var19 = var9; var19 <= var11; var19++) {
/* 145 */         boolean var20 = var14.setForceLoaded(var18, var19, var3);
/* 146 */         if (var20) {
/* 147 */           var17++;
/* 148 */           if (var16 == null) {
/* 149 */             var16 = new ChunkCoordIntPair(var18, var19);
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 155 */     if (var17 == 0)
/* 156 */       throw (var3 ? c : d).create(); 
/* 157 */     if (var17 == 1) {
/* 158 */       var0.sendMessage(new ChatMessage("commands.forceload." + (var3 ? "added" : "removed") + ".single", new Object[] { var16, var15.a() }), true);
/*     */     } else {
/* 160 */       ChunkCoordIntPair chunkCoordIntPair1 = new ChunkCoordIntPair(var8, var9);
/* 161 */       ChunkCoordIntPair var19 = new ChunkCoordIntPair(var10, var11);
/* 162 */       var0.sendMessage(new ChatMessage("commands.forceload." + (var3 ? "added" : "removed") + ".multiple", new Object[] { Integer.valueOf(var17), var15.a(), chunkCoordIntPair1, var19 }), true);
/*     */     } 
/*     */     
/* 165 */     return var17;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CommandForceload.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
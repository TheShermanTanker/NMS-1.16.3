/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.mojang.brigadier.CommandDispatcher;
/*     */ import com.mojang.brigadier.arguments.ArgumentType;
/*     */ import com.mojang.brigadier.arguments.BoolArgumentType;
/*     */ import com.mojang.brigadier.arguments.IntegerArgumentType;
/*     */ import com.mojang.brigadier.arguments.StringArgumentType;
/*     */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*     */ import com.mojang.brigadier.builder.RequiredArgumentBuilder;
/*     */ import com.mojang.brigadier.context.CommandContext;
/*     */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import java.nio.file.Files;
/*     */ import java.nio.file.Path;
/*     */ import java.nio.file.Paths;
/*     */ import java.nio.file.attribute.FileAttribute;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.Optional;
/*     */ import java.util.function.Consumer;
/*     */ import java.util.stream.Collectors;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.craftbukkit.libs.org.apache.commons.io.IOUtils;
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
/*     */ public class GameTestHarnessTestCommand
/*     */ {
/*     */   public static void a(CommandDispatcher<CommandListenerWrapper> var0) {
/*  71 */     var0.register(
/*  72 */         (LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)CommandDispatcher.a("test")
/*  73 */         .then(
/*  74 */           CommandDispatcher.a("runthis")
/*  75 */           .executes(var0 -> a((CommandListenerWrapper)var0.getSource()))))
/*     */         
/*  77 */         .then(
/*  78 */           CommandDispatcher.a("runthese")
/*  79 */           .executes(var0 -> b((CommandListenerWrapper)var0.getSource()))))
/*     */         
/*  81 */         .then((
/*  82 */           (LiteralArgumentBuilder)CommandDispatcher.a("runfailed")
/*  83 */           .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), false, 0, 8)))
/*  84 */           .then(((RequiredArgumentBuilder)CommandDispatcher.<T>a("onlyRequiredTests", (ArgumentType<T>)BoolArgumentType.bool())
/*  85 */             .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), BoolArgumentType.getBool(var0, "onlyRequiredTests"), 0, 8)))
/*  86 */             .then(((RequiredArgumentBuilder)CommandDispatcher.<T>a("rotationSteps", (ArgumentType<T>)IntegerArgumentType.integer())
/*  87 */               .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), BoolArgumentType.getBool(var0, "onlyRequiredTests"), IntegerArgumentType.getInteger(var0, "rotationSteps"), 8)))
/*  88 */               .then(CommandDispatcher.<T>a("testsPerRow", (ArgumentType<T>)IntegerArgumentType.integer())
/*  89 */                 .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), BoolArgumentType.getBool(var0, "onlyRequiredTests"), IntegerArgumentType.getInteger(var0, "rotationSteps"), IntegerArgumentType.getInteger(var0, "testsPerRow"))))))))
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*  94 */         .then(
/*  95 */           CommandDispatcher.a("run")
/*  96 */           .then((
/*  97 */             (RequiredArgumentBuilder)CommandDispatcher.<T>a("testName", GameTestHarnessTestFunctionArgument.a())
/*  98 */             .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), GameTestHarnessTestFunctionArgument.a(var0, "testName"), 0)))
/*  99 */             .then(CommandDispatcher.<T>a("rotationSteps", (ArgumentType<T>)IntegerArgumentType.integer())
/* 100 */               .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), GameTestHarnessTestFunctionArgument.a(var0, "testName"), IntegerArgumentType.getInteger(var0, "rotationSteps")))))))
/*     */ 
/*     */ 
/*     */         
/* 104 */         .then((
/* 105 */           (LiteralArgumentBuilder)((LiteralArgumentBuilder)CommandDispatcher.a("runall")
/* 106 */           .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), 0, 8)))
/* 107 */           .then((
/* 108 */             (RequiredArgumentBuilder)CommandDispatcher.<T>a("testClassName", GameTestHarnessTestClassArgument.a())
/* 109 */             .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), GameTestHarnessTestClassArgument.a(var0, "testClassName"), 0, 8)))
/* 110 */             .then(((RequiredArgumentBuilder)CommandDispatcher.<T>a("rotationSteps", (ArgumentType<T>)IntegerArgumentType.integer())
/* 111 */               .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), GameTestHarnessTestClassArgument.a(var0, "testClassName"), IntegerArgumentType.getInteger(var0, "rotationSteps"), 8)))
/* 112 */               .then(CommandDispatcher.<T>a("testsPerRow", (ArgumentType<T>)IntegerArgumentType.integer())
/* 113 */                 .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), GameTestHarnessTestClassArgument.a(var0, "testClassName"), IntegerArgumentType.getInteger(var0, "rotationSteps"), IntegerArgumentType.getInteger(var0, "testsPerRow")))))))
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 118 */           .then(((RequiredArgumentBuilder)CommandDispatcher.<T>a("rotationSteps", (ArgumentType<T>)IntegerArgumentType.integer())
/* 119 */             .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), IntegerArgumentType.getInteger(var0, "rotationSteps"), 8)))
/* 120 */             .then(CommandDispatcher.<T>a("testsPerRow", (ArgumentType<T>)IntegerArgumentType.integer())
/* 121 */               .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), IntegerArgumentType.getInteger(var0, "rotationSteps"), IntegerArgumentType.getInteger(var0, "testsPerRow")))))))
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 126 */         .then(
/* 127 */           CommandDispatcher.a("export")
/* 128 */           .then(
/* 129 */             CommandDispatcher.<T>a("testName", (ArgumentType<T>)StringArgumentType.word())
/* 130 */             .executes(var0 -> c((CommandListenerWrapper)var0.getSource(), StringArgumentType.getString(var0, "testName"))))))
/*     */ 
/*     */         
/* 133 */         .then(
/* 134 */           CommandDispatcher.a("exportthis")
/* 135 */           .executes(var0 -> c((CommandListenerWrapper)var0.getSource()))))
/*     */         
/* 137 */         .then(
/* 138 */           CommandDispatcher.a("import")
/* 139 */           .then(
/* 140 */             CommandDispatcher.<T>a("testName", (ArgumentType<T>)StringArgumentType.word())
/* 141 */             .executes(var0 -> d((CommandListenerWrapper)var0.getSource(), StringArgumentType.getString(var0, "testName"))))))
/*     */ 
/*     */         
/* 144 */         .then((
/* 145 */           (LiteralArgumentBuilder)CommandDispatcher.a("pos")
/* 146 */           .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), "pos")))
/* 147 */           .then(
/* 148 */             CommandDispatcher.<T>a("var", (ArgumentType<T>)StringArgumentType.word())
/* 149 */             .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), StringArgumentType.getString(var0, "var"))))))
/*     */ 
/*     */         
/* 152 */         .then(
/* 153 */           CommandDispatcher.a("create")
/* 154 */           .then((
/* 155 */             (RequiredArgumentBuilder)CommandDispatcher.<T>a("testName", (ArgumentType<T>)StringArgumentType.word())
/* 156 */             .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), StringArgumentType.getString(var0, "testName"), 5, 5, 5)))
/* 157 */             .then((
/* 158 */               (RequiredArgumentBuilder)CommandDispatcher.<T>a("width", (ArgumentType<T>)IntegerArgumentType.integer())
/* 159 */               .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), StringArgumentType.getString(var0, "testName"), IntegerArgumentType.getInteger(var0, "width"), IntegerArgumentType.getInteger(var0, "width"), IntegerArgumentType.getInteger(var0, "width"))))
/*     */ 
/*     */ 
/*     */ 
/*     */               
/* 164 */               .then(
/* 165 */                 CommandDispatcher.<T>a("height", (ArgumentType<T>)IntegerArgumentType.integer())
/* 166 */                 .then(
/* 167 */                   CommandDispatcher.<T>a("depth", (ArgumentType<T>)IntegerArgumentType.integer())
/* 168 */                   .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), StringArgumentType.getString(var0, "testName"), IntegerArgumentType.getInteger(var0, "width"), IntegerArgumentType.getInteger(var0, "height"), IntegerArgumentType.getInteger(var0, "depth")))))))))
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
/* 179 */         .then((
/* 180 */           (LiteralArgumentBuilder)CommandDispatcher.a("clearall")
/* 181 */           .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), 200)))
/* 182 */           .then(
/* 183 */             CommandDispatcher.<T>a("radius", (ArgumentType<T>)IntegerArgumentType.integer())
/* 184 */             .executes(var0 -> a((CommandListenerWrapper)var0.getSource(), IntegerArgumentType.getInteger(var0, "radius"))))));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static int a(CommandListenerWrapper var0, String var1, int var2, int var3, int var4) {
/* 191 */     if (var2 > 48 || var3 > 48 || var4 > 48) {
/* 192 */       throw new IllegalArgumentException("The structure must be less than 48 blocks big in each axis");
/*     */     }
/*     */     
/* 195 */     WorldServer var5 = var0.getWorld();
/* 196 */     BlockPosition var6 = new BlockPosition(var0.getPosition());
/* 197 */     BlockPosition var7 = new BlockPosition(var6.getX(), var0.getWorld().getHighestBlockYAt(HeightMap.Type.WORLD_SURFACE, var6).getY(), var6.getZ() + 3);
/*     */     
/* 199 */     GameTestHarnessStructures.a(var1.toLowerCase(), var7, new BlockPosition(var2, var3, var4), EnumBlockRotation.NONE, var5);
/*     */     
/* 201 */     for (int var8 = 0; var8 < var2; var8++) {
/* 202 */       for (int var9 = 0; var9 < var4; var9++) {
/* 203 */         BlockPosition var10 = new BlockPosition(var7.getX() + var8, var7.getY() + 1, var7.getZ() + var9);
/* 204 */         Block var11 = Blocks.POLISHED_ANDESITE;
/* 205 */         ArgumentTileLocation var12 = new ArgumentTileLocation(var11.getBlockData(), Collections.EMPTY_SET, null);
/* 206 */         var12.a(var5, var10, 2);
/*     */       } 
/*     */     } 
/*     */     
/* 210 */     GameTestHarnessStructures.a(var7, new BlockPosition(1, 0, -1), EnumBlockRotation.NONE, var5);
/*     */     
/* 212 */     return 0;
/*     */   }
/*     */   
/*     */   private static int a(CommandListenerWrapper var0, String var1) throws CommandSyntaxException {
/* 216 */     MovingObjectPositionBlock var2 = (MovingObjectPositionBlock)var0.h().a(10.0D, 1.0F, false);
/*     */     
/* 218 */     BlockPosition var3 = var2.getBlockPosition();
/* 219 */     WorldServer var4 = var0.getWorld();
/*     */     
/* 221 */     Optional<BlockPosition> var5 = GameTestHarnessStructures.a(var3, 15, var4);
/* 222 */     if (!var5.isPresent())
/*     */     {
/* 224 */       var5 = GameTestHarnessStructures.a(var3, 200, var4);
/*     */     }
/*     */     
/* 227 */     if (!var5.isPresent()) {
/* 228 */       var0.sendFailureMessage(new ChatComponentText("Can't find a structure block that contains the targeted pos " + var3));
/* 229 */       return 0;
/*     */     } 
/* 231 */     TileEntityStructure var6 = (TileEntityStructure)var4.getTileEntity(var5.get());
/*     */     
/* 233 */     BlockPosition var7 = var3.b(var5.get());
/* 234 */     String var8 = var7.getX() + ", " + var7.getY() + ", " + var7.getZ();
/* 235 */     String var9 = var6.f();
/*     */ 
/*     */     
/* 238 */     IChatBaseComponent var10 = (new ChatComponentText(var8)).setChatModifier(ChatModifier.a
/* 239 */         .setBold(Boolean.valueOf(true))
/* 240 */         .setColor(EnumChatFormat.GREEN)
/* 241 */         .setChatHoverable(new ChatHoverable((ChatHoverable.EnumHoverAction)ChatHoverable.EnumHoverAction.SHOW_TEXT, (T)new ChatComponentText("Click to copy to clipboard")))
/* 242 */         .setChatClickable(new ChatClickable(ChatClickable.EnumClickAction.COPY_TO_CLIPBOARD, "final BlockPos " + var1 + " = new BlockPos(" + var8 + ");")));
/*     */     
/* 244 */     var0.sendMessage((new ChatComponentText("Position relative to " + var9 + ": ")).addSibling(var10), false);
/*     */     
/* 246 */     PacketDebug.a(var4, new BlockPosition(var3), var8, -2147418368, 10000);
/*     */     
/* 248 */     return 1;
/*     */   }
/*     */   
/*     */   private static int a(CommandListenerWrapper var0) {
/* 252 */     BlockPosition var1 = new BlockPosition(var0.getPosition());
/* 253 */     WorldServer var2 = var0.getWorld();
/* 254 */     BlockPosition var3 = GameTestHarnessStructures.b(var1, 15, var2);
/* 255 */     if (var3 == null) {
/* 256 */       a(var2, "Couldn't find any structure block within 15 radius", EnumChatFormat.RED);
/* 257 */       return 0;
/*     */     } 
/*     */     
/* 260 */     GameTestHarnessRunner.a(var2);
/*     */     
/* 262 */     a(var2, var3, (GameTestHarnessCollector)null);
/*     */     
/* 264 */     return 1;
/*     */   }
/*     */   
/*     */   private static int b(CommandListenerWrapper var0) {
/* 268 */     BlockPosition var1 = new BlockPosition(var0.getPosition());
/* 269 */     WorldServer var2 = var0.getWorld();
/* 270 */     Collection<BlockPosition> var3 = GameTestHarnessStructures.c(var1, 200, var2);
/*     */     
/* 272 */     if (var3.isEmpty()) {
/* 273 */       a(var2, "Couldn't find any structure blocks within 200 block radius", EnumChatFormat.RED);
/* 274 */       return 1;
/*     */     } 
/*     */     
/* 277 */     GameTestHarnessRunner.a(var2);
/*     */     
/* 279 */     b(var0, "Running " + var3.size() + " tests...");
/*     */     
/* 281 */     GameTestHarnessCollector var4 = new GameTestHarnessCollector();
/* 282 */     var3.forEach(var2 -> a(var0, var2, var1));
/*     */     
/* 284 */     return 1;
/*     */   }
/*     */   
/*     */   private static void a(WorldServer var0, BlockPosition var1, @Nullable GameTestHarnessCollector var2) {
/* 288 */     TileEntityStructure var3 = (TileEntityStructure)var0.getTileEntity(var1);
/* 289 */     String var4 = var3.f();
/* 290 */     GameTestHarnessTestFunction var5 = GameTestHarnessRegistry.e(var4);
/* 291 */     GameTestHarnessInfo var6 = new GameTestHarnessInfo(var5, var3.l(), var0);
/* 292 */     if (var2 != null) {
/* 293 */       var2.a(var6);
/* 294 */       var6.a(new a(var0, var2));
/*     */     } 
/* 296 */     a(var5, var0);
/* 297 */     AxisAlignedBB var7 = GameTestHarnessStructures.a(var3);
/* 298 */     BlockPosition var8 = new BlockPosition(var7.minX, var7.minY, var7.minZ);
/* 299 */     GameTestHarnessRunner.a(var6, var8, GameTestHarnessTicker.a);
/*     */   }
/*     */   
/*     */   private static void b(WorldServer var0, GameTestHarnessCollector var1) {
/* 303 */     if (var1.i()) {
/* 304 */       a(var0, "GameTest done! " + var1.h() + " tests were run", EnumChatFormat.WHITE);
/* 305 */       if (var1.d()) {
/* 306 */         a(var0, "" + var1.a() + " required tests failed :(", EnumChatFormat.RED);
/*     */       } else {
/* 308 */         a(var0, "All required tests passed :)", EnumChatFormat.GREEN);
/*     */       } 
/* 310 */       if (var1.e()) {
/* 311 */         a(var0, "" + var1.b() + " optional tests failed", EnumChatFormat.GRAY);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private static int a(CommandListenerWrapper var0, int var1) {
/* 317 */     WorldServer var2 = var0.getWorld();
/* 318 */     GameTestHarnessRunner.a(var2);
/* 319 */     BlockPosition var3 = new BlockPosition((var0.getPosition()).x, var0.getWorld().getHighestBlockYAt(HeightMap.Type.WORLD_SURFACE, new BlockPosition(var0.getPosition())).getY(), (var0.getPosition()).z);
/* 320 */     GameTestHarnessRunner.a(var2, var3, GameTestHarnessTicker.a, MathHelper.clamp(var1, 0, 1024));
/* 321 */     return 1;
/*     */   }
/*     */   
/*     */   private static int a(CommandListenerWrapper var0, GameTestHarnessTestFunction var1, int var2) {
/* 325 */     WorldServer var3 = var0.getWorld();
/* 326 */     BlockPosition var4 = new BlockPosition(var0.getPosition());
/* 327 */     int var5 = var0.getWorld().getHighestBlockYAt(HeightMap.Type.WORLD_SURFACE, var4).getY();
/* 328 */     BlockPosition var6 = new BlockPosition(var4.getX(), var5, var4.getZ() + 3);
/* 329 */     GameTestHarnessRunner.a(var3);
/* 330 */     a(var1, var3);
/* 331 */     EnumBlockRotation var7 = GameTestHarnessStructures.a(var2);
/* 332 */     GameTestHarnessInfo var8 = new GameTestHarnessInfo(var1, var7, var3);
/* 333 */     GameTestHarnessRunner.a(var8, var6, GameTestHarnessTicker.a);
/* 334 */     return 1;
/*     */   }
/*     */   
/*     */   private static void a(GameTestHarnessTestFunction var0, WorldServer var1) {
/* 338 */     Consumer<WorldServer> var2 = GameTestHarnessRegistry.c(var0.e());
/* 339 */     if (var2 != null) {
/* 340 */       var2.accept(var1);
/*     */     }
/*     */   }
/*     */   
/*     */   private static int a(CommandListenerWrapper var0, int var1, int var2) {
/* 345 */     GameTestHarnessRunner.a(var0.getWorld());
/* 346 */     Collection<GameTestHarnessTestFunction> var3 = GameTestHarnessRegistry.a();
/* 347 */     b(var0, "Running all " + var3.size() + " tests...");
/* 348 */     GameTestHarnessRegistry.d();
/* 349 */     a(var0, var3, var1, var2);
/* 350 */     return 1;
/*     */   }
/*     */   
/*     */   private static int a(CommandListenerWrapper var0, String var1, int var2, int var3) {
/* 354 */     Collection<GameTestHarnessTestFunction> var4 = GameTestHarnessRegistry.a(var1);
/* 355 */     GameTestHarnessRunner.a(var0.getWorld());
/* 356 */     b(var0, "Running " + var4.size() + " tests from " + var1 + "...");
/* 357 */     GameTestHarnessRegistry.d();
/* 358 */     a(var0, var4, var2, var3);
/* 359 */     return 1;
/*     */   }
/*     */   
/*     */   private static int a(CommandListenerWrapper var0, boolean var1, int var2, int var3) {
/*     */     Collection<GameTestHarnessTestFunction> var4;
/* 364 */     if (var1) {
/* 365 */       var4 = (Collection<GameTestHarnessTestFunction>)GameTestHarnessRegistry.c().stream().filter(GameTestHarnessTestFunction::d).collect(Collectors.toList());
/*     */     } else {
/* 367 */       var4 = GameTestHarnessRegistry.c();
/*     */     } 
/* 369 */     if (var4.isEmpty()) {
/* 370 */       b(var0, "No failed tests to rerun");
/* 371 */       return 0;
/*     */     } 
/* 373 */     GameTestHarnessRunner.a(var0.getWorld());
/* 374 */     b(var0, "Rerunning " + var4.size() + " failed tests (" + (var1 ? "only required tests" : "including optional tests") + ")");
/* 375 */     a(var0, var4, var2, var3);
/* 376 */     return 1;
/*     */   }
/*     */   
/*     */   private static void a(CommandListenerWrapper var0, Collection<GameTestHarnessTestFunction> var1, int var2, int var3) {
/* 380 */     BlockPosition var4 = new BlockPosition(var0.getPosition());
/* 381 */     BlockPosition var5 = new BlockPosition(var4.getX(), var0.getWorld().getHighestBlockYAt(HeightMap.Type.WORLD_SURFACE, var4).getY(), var4.getZ() + 3);
/* 382 */     WorldServer var6 = var0.getWorld();
/* 383 */     EnumBlockRotation var7 = GameTestHarnessStructures.a(var2);
/* 384 */     Collection<GameTestHarnessInfo> var8 = GameTestHarnessRunner.b(var1, var5, var7, var6, GameTestHarnessTicker.a, var3);
/* 385 */     GameTestHarnessCollector var9 = new GameTestHarnessCollector(var8);
/* 386 */     var9.a(new a(var6, var9));
/* 387 */     var9.a(var0 -> GameTestHarnessRegistry.a(var0.u()));
/*     */   }
/*     */   
/*     */   private static void b(CommandListenerWrapper var0, String var1) {
/* 391 */     var0.sendMessage(new ChatComponentText(var1), false);
/*     */   }
/*     */   
/*     */   private static int c(CommandListenerWrapper var0) {
/* 395 */     BlockPosition var1 = new BlockPosition(var0.getPosition());
/* 396 */     WorldServer var2 = var0.getWorld();
/* 397 */     BlockPosition var3 = GameTestHarnessStructures.b(var1, 15, var2);
/* 398 */     if (var3 == null) {
/* 399 */       a(var2, "Couldn't find any structure block within 15 radius", EnumChatFormat.RED);
/* 400 */       return 0;
/*     */     } 
/* 402 */     TileEntityStructure var4 = (TileEntityStructure)var2.getTileEntity(var3);
/* 403 */     String var5 = var4.f();
/* 404 */     return c(var0, var5);
/*     */   }
/*     */   
/*     */   private static int c(CommandListenerWrapper var0, String var1) {
/* 408 */     Path var2 = Paths.get(GameTestHarnessStructures.a, new String[0]);
/*     */     
/* 410 */     MinecraftKey var3 = new MinecraftKey("minecraft", var1);
/* 411 */     Path var4 = var0.getWorld().n().a(var3, ".nbt");
/* 412 */     Path var5 = DebugReportNBT.a(var4, var1, var2);
/* 413 */     if (var5 == null) {
/* 414 */       b(var0, "Failed to export " + var4);
/* 415 */       return 1;
/*     */     } 
/*     */     
/*     */     try {
/* 419 */       Files.createDirectories(var5.getParent(), (FileAttribute<?>[])new FileAttribute[0]);
/* 420 */     } catch (IOException var6) {
/* 421 */       b(var0, "Could not create folder " + var5.getParent());
/* 422 */       var6.printStackTrace();
/* 423 */       return 1;
/*     */     } 
/*     */     
/* 426 */     b(var0, "Exported " + var1 + " to " + var5.toAbsolutePath());
/* 427 */     return 0;
/*     */   }
/*     */   
/*     */   private static int d(CommandListenerWrapper var0, String var1) {
/* 431 */     Path var2 = Paths.get(GameTestHarnessStructures.a, new String[] { var1 + ".snbt" });
/*     */     
/* 433 */     MinecraftKey var3 = new MinecraftKey("minecraft", var1);
/* 434 */     Path var4 = var0.getWorld().n().a(var3, ".nbt");
/*     */     
/*     */     try {
/* 437 */       BufferedReader var5 = Files.newBufferedReader(var2);
/* 438 */       String var6 = IOUtils.toString(var5);
/* 439 */       Files.createDirectories(var4.getParent(), (FileAttribute<?>[])new FileAttribute[0]);
/* 440 */       try (OutputStream var7 = Files.newOutputStream(var4, new java.nio.file.OpenOption[0])) {
/* 441 */         NBTCompressedStreamTools.a(MojangsonParser.parse(var6), var7);
/*     */       } 
/* 443 */       b(var0, "Imported to " + var4.toAbsolutePath());
/* 444 */       return 0;
/* 445 */     } catch (IOException|CommandSyntaxException var5) {
/* 446 */       System.err.println("Failed to load structure " + var1);
/* 447 */       var5.printStackTrace();
/* 448 */       return 1;
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void a(WorldServer var0, String var1, EnumChatFormat var2) {
/* 453 */     var0.a(var0 -> true).forEach(var2 -> var2.sendMessage(new ChatComponentText(var0 + var1), SystemUtils.b));
/*     */   }
/*     */   
/*     */   static class a
/*     */     implements GameTestHarnessListener
/*     */   {
/*     */     private final WorldServer a;
/*     */     private final GameTestHarnessCollector b;
/*     */     
/*     */     public a(WorldServer var0, GameTestHarnessCollector var1) {
/* 463 */       this.a = var0;
/* 464 */       this.b = var1;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void a(GameTestHarnessInfo var0) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void c(GameTestHarnessInfo var0) {
/* 478 */       GameTestHarnessTestCommand.a(this.a, this.b);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\GameTestHarnessTestCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
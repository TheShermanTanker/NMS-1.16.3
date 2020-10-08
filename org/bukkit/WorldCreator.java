/*     */ package org.bukkit;
/*     */ 
/*     */ import java.util.Random;
/*     */ import org.bukkit.command.CommandSender;
/*     */ import org.bukkit.command.ConsoleCommandSender;
/*     */ import org.bukkit.generator.ChunkGenerator;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ 
/*     */ public class WorldCreator
/*     */ {
/*     */   private final String name;
/*     */   private long seed;
/*  16 */   private World.Environment environment = World.Environment.NORMAL;
/*  17 */   private ChunkGenerator generator = null;
/*  18 */   private WorldType type = WorldType.NORMAL;
/*     */   private boolean generateStructures = true;
/*  20 */   private String generatorSettings = "";
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean hardcore = false;
/*     */ 
/*     */ 
/*     */   
/*     */   public WorldCreator(@NotNull String name) {
/*  29 */     if (name == null) {
/*  30 */       throw new IllegalArgumentException("World name cannot be null");
/*     */     }
/*     */     
/*  33 */     this.name = name;
/*  34 */     this.seed = (new Random()).nextLong();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public WorldCreator copy(@NotNull World world) {
/*  45 */     if (world == null) {
/*  46 */       throw new IllegalArgumentException("World cannot be null");
/*     */     }
/*     */     
/*  49 */     this.seed = world.getSeed();
/*  50 */     this.environment = world.getEnvironment();
/*  51 */     this.generator = world.getGenerator();
/*  52 */     this.type = world.getWorldType();
/*  53 */     this.generateStructures = world.canGenerateStructures();
/*  54 */     this.hardcore = world.isHardcore();
/*     */     
/*  56 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public WorldCreator copy(@NotNull WorldCreator creator) {
/*  67 */     if (creator == null) {
/*  68 */       throw new IllegalArgumentException("Creator cannot be null");
/*     */     }
/*     */     
/*  71 */     this.seed = creator.seed();
/*  72 */     this.environment = creator.environment();
/*  73 */     this.generator = creator.generator();
/*  74 */     this.type = creator.type();
/*  75 */     this.generateStructures = creator.generateStructures();
/*  76 */     this.generatorSettings = creator.generatorSettings();
/*  77 */     this.hardcore = creator.hardcore();
/*     */     
/*  79 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public String name() {
/*  89 */     return this.name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long seed() {
/*  98 */     return this.seed;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public WorldCreator seed(long seed) {
/* 109 */     this.seed = seed;
/*     */     
/* 111 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public World.Environment environment() {
/* 121 */     return this.environment;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public WorldCreator environment(@NotNull World.Environment env) {
/* 132 */     this.environment = env;
/*     */     
/* 134 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public WorldType type() {
/* 144 */     return this.type;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public WorldCreator type(@NotNull WorldType type) {
/* 155 */     this.type = type;
/*     */     
/* 157 */     return this;
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
/*     */   @Nullable
/*     */   public ChunkGenerator generator() {
/* 170 */     return this.generator;
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
/*     */   @NotNull
/*     */   public WorldCreator generator(@Nullable ChunkGenerator generator) {
/* 184 */     this.generator = generator;
/*     */     
/* 186 */     return this;
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
/*     */   @NotNull
/*     */   public WorldCreator generator(@Nullable String generator) {
/* 204 */     this.generator = getGeneratorForName(this.name, generator, (CommandSender)Bukkit.getConsoleSender());
/*     */     
/* 206 */     return this;
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
/*     */   @NotNull
/*     */   public WorldCreator generator(@Nullable String generator, @Nullable CommandSender output) {
/* 226 */     this.generator = getGeneratorForName(this.name, generator, output);
/*     */     
/* 228 */     return this;
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
/*     */   @NotNull
/*     */   public WorldCreator generatorSettings(@NotNull String generatorSettings) {
/* 250 */     this.generatorSettings = generatorSettings;
/*     */     
/* 252 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public String generatorSettings() {
/* 263 */     return this.generatorSettings;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public WorldCreator generateStructures(boolean generate) {
/* 275 */     this.generateStructures = generate;
/*     */     
/* 277 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean generateStructures() {
/* 286 */     return this.generateStructures;
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
/*     */   @NotNull
/*     */   public WorldCreator hardcore(boolean hardcore) {
/* 299 */     this.hardcore = hardcore;
/*     */     
/* 301 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hardcore() {
/* 312 */     return this.hardcore;
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
/*     */   @Nullable
/*     */   public World createWorld() {
/* 325 */     return Bukkit.createWorld(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public static WorldCreator name(@NotNull String name) {
/* 336 */     return new WorldCreator(name);
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
/*     */   @Nullable
/*     */   public static ChunkGenerator getGeneratorForName(@NotNull String world, @Nullable String name, @Nullable CommandSender output) {
/*     */     ConsoleCommandSender consoleCommandSender;
/* 357 */     ChunkGenerator result = null;
/*     */     
/* 359 */     if (world == null) {
/* 360 */       throw new IllegalArgumentException("World name must be specified");
/*     */     }
/*     */     
/* 363 */     if (output == null) {
/* 364 */       consoleCommandSender = Bukkit.getConsoleSender();
/*     */     }
/*     */     
/* 367 */     if (name != null) {
/* 368 */       String[] split = name.split(":", 2);
/* 369 */       String id = (split.length > 1) ? split[1] : null;
/* 370 */       Plugin plugin = Bukkit.getPluginManager().getPlugin(split[0]);
/*     */       
/* 372 */       if (plugin == null) {
/* 373 */         consoleCommandSender.sendMessage("Could not set generator for world '" + world + "': Plugin '" + split[0] + "' does not exist");
/* 374 */       } else if (!plugin.isEnabled()) {
/* 375 */         consoleCommandSender.sendMessage("Could not set generator for world '" + world + "': Plugin '" + plugin.getDescription().getFullName() + "' is not enabled");
/*     */       } else {
/* 377 */         result = plugin.getDefaultWorldGenerator(world, id);
/*     */       } 
/*     */     } 
/*     */     
/* 381 */     return result;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\WorldCreator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
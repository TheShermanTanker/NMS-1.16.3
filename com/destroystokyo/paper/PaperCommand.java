/*     */ package com.destroystokyo.paper;
/*     */ import com.google.common.collect.Maps;
/*     */ import com.google.gson.stream.JsonWriter;
/*     */ import com.mojang.datafixers.util.Either;
/*     */ import java.io.File;
/*     */ import java.io.PrintStream;
/*     */ import java.io.StringWriter;
/*     */ import java.nio.file.Path;
/*     */ import java.time.LocalDateTime;
/*     */ import java.time.format.DateTimeFormatter;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Deque;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import net.minecraft.server.v1_16_R2.BlockPosition;
/*     */ import net.minecraft.server.v1_16_R2.Chunk;
/*     */ import net.minecraft.server.v1_16_R2.ChunkCoordIntPair;
/*     */ import net.minecraft.server.v1_16_R2.ChunkProviderServer;
/*     */ import net.minecraft.server.v1_16_R2.Entity;
/*     */ import net.minecraft.server.v1_16_R2.LightEngineThreaded;
/*     */ import net.minecraft.server.v1_16_R2.MinecraftKey;
/*     */ import net.minecraft.server.v1_16_R2.MinecraftServer;
/*     */ import net.minecraft.server.v1_16_R2.PlayerChunk;
/*     */ import net.minecraft.server.v1_16_R2.WorldServer;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.World;
/*     */ import org.bukkit.command.Command;
/*     */ import org.bukkit.command.CommandSender;
/*     */ import org.bukkit.craftbukkit.libs.org.apache.commons.lang3.tuple.MutablePair;
/*     */ import org.bukkit.craftbukkit.libs.org.apache.commons.lang3.tuple.Pair;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.entity.CraftPlayer;
/*     */ 
/*     */ public class PaperCommand extends Command {
/*     */   public PaperCommand(String name) {
/*  40 */     super(name);
/*  41 */     this.description = "Paper related commands";
/*  42 */     this.usageMessage = "/paper [heap | entity | reload | version | debug | dumpwaiting | chunkinfo | syncloadinfo | fixlight | dumpitem]";
/*  43 */     setPermission("bukkit.command.paper");
/*     */   }
/*     */   
/*     */   public List<String> tabComplete(CommandSender sender, String alias, String[] args, Location location) throws IllegalArgumentException {
/*     */     List<String> worldNames;
/*  48 */     if (args.length <= 1) {
/*  49 */       return getListMatchingLast(args, new String[] { "heap", "entity", "reload", "version", "debug", "dumpwaiting", "chunkinfo", "syncloadinfo", "fixlight", "dumpitem" });
/*     */     }
/*  51 */     switch (args[0].toLowerCase(Locale.ENGLISH)) {
/*     */       
/*     */       case "entity":
/*  54 */         if (args.length == 2)
/*  55 */           return getListMatchingLast(args, new String[] { "help", "list" }); 
/*  56 */         if (args.length == 3)
/*  57 */           return getListMatchingLast(args, (String[])EntityTypes.getEntityNameList().stream().map(MinecraftKey::toString).sorted().toArray(x$0 -> new String[x$0])); 
/*     */         break;
/*     */       case "debug":
/*  60 */         if (args.length == 2) {
/*  61 */           return getListMatchingLast(args, new String[] { "help", "chunks" });
/*     */         }
/*     */         break;
/*     */       case "chunkinfo":
/*  65 */         worldNames = new ArrayList<>();
/*  66 */         worldNames.add("*");
/*  67 */         for (World world : Bukkit.getWorlds()) {
/*  68 */           worldNames.add(world.getName());
/*     */         }
/*  70 */         if (args.length == 2) {
/*  71 */           return getListMatchingLast(args, worldNames);
/*     */         }
/*     */         break;
/*     */     } 
/*  75 */     return Collections.emptyList();
/*     */   }
/*     */ 
/*     */   
/*     */   public static List<String> getListMatchingLast(String[] args, String... matches) {
/*  80 */     return getListMatchingLast(args, Arrays.asList((Object[])matches));
/*     */   }
/*     */   
/*     */   public static boolean matches(String s, String s1) {
/*  84 */     return s1.regionMatches(true, 0, s, 0, s.length());
/*     */   }
/*     */   
/*     */   public static List<String> getListMatchingLast(String[] strings, Collection<?> collection) {
/*  88 */     String last = strings[strings.length - 1];
/*  89 */     ArrayList<String> results = Lists.newArrayList();
/*     */     
/*  91 */     if (!collection.isEmpty()) {
/*  92 */       Iterator<String> iterator = Iterables.transform(collection, Functions.toStringFunction()).iterator();
/*     */       
/*  94 */       while (iterator.hasNext()) {
/*  95 */         String s1 = iterator.next();
/*     */         
/*  97 */         if (matches(last, s1)) {
/*  98 */           results.add(s1);
/*     */         }
/*     */       } 
/*     */       
/* 102 */       if (results.isEmpty()) {
/* 103 */         iterator = (Iterator)collection.iterator();
/*     */         
/* 105 */         while (iterator.hasNext()) {
/* 106 */           Object object = iterator.next();
/*     */           
/* 108 */           if (object instanceof MinecraftKey && matches(last, ((MinecraftKey)object).getKey())) {
/* 109 */             results.add(String.valueOf(object));
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 115 */     return results;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean execute(CommandSender sender, String commandLabel, String[] args) {
/*     */     Command ver;
/* 121 */     if (!testPermission(sender)) return true;
/*     */     
/* 123 */     if (args.length == 0) {
/* 124 */       sender.sendMessage(ChatColor.RED + "Usage: " + this.usageMessage);
/* 125 */       return false;
/*     */     } 
/*     */     
/* 128 */     switch (args[0].toLowerCase(Locale.ENGLISH)) {
/*     */       case "heap":
/* 130 */         dumpHeap(sender);
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
/* 169 */         return true;case "entity": listEntities(sender, args); return true;case "reload": doReload(sender); return true;case "dumpitem": doDumpItem(sender); return true;case "debug": doDebug(sender, args); return true;case "dumpwaiting": ChunkTaskManager.dumpAllChunkLoadInfo(); return true;case "chunkinfo": doChunkInfo(sender, args); return true;case "syncloadinfo": doSyncLoadInfo(sender, args); return true;case "fixlight": doFixLight(sender, args); return true;case "ver": case "version": ver = Bukkit.getServer().getCommandMap().getCommand("version"); if (ver != null) { ver.execute(sender, commandLabel, new String[0]); return true; }
/*     */          break;
/*     */     } 
/*     */     sender.sendMessage(ChatColor.RED + "Usage: " + this.usageMessage);
/* 173 */     return false; } private void doDumpItem(CommandSender sender) { ItemStack itemInHand = ((CraftPlayer)sender).getItemInHand();
/* 174 */     ItemStack itemStack = CraftItemStack.asNMSCopy(itemInHand);
/* 175 */     NBTTagCompound tag = itemStack.getTag();
/* 176 */     if (tag != null) {
/* 177 */       String nbt = tag.toString();
/* 178 */       Bukkit.getConsoleSender().sendMessage(nbt);
/* 179 */       sender.sendMessage(nbt);
/*     */     } else {
/* 181 */       sender.sendMessage("Item does not have NBT");
/*     */     }  }
/*     */ 
/*     */   
/*     */   private void doFixLight(CommandSender sender, String[] args) {
/* 186 */     if (!(sender instanceof Player)) {
/* 187 */       sender.sendMessage("Only players can use this command");
/*     */       return;
/*     */     } 
/* 190 */     int radius = 2;
/* 191 */     if (args.length > 1) {
/*     */       try {
/* 193 */         radius = Math.min(5, Integer.parseInt(args[1]));
/* 194 */       } catch (Exception e) {
/* 195 */         sender.sendMessage("Not a number");
/*     */         
/*     */         return;
/*     */       } 
/*     */     }
/*     */     
/* 201 */     CraftPlayer player = (CraftPlayer)sender;
/* 202 */     EntityPlayer handle = player.getHandle();
/* 203 */     WorldServer world = (WorldServer)handle.world;
/* 204 */     LightEngineThreaded lightengine = world.getChunkProvider().getLightEngine();
/*     */     
/* 206 */     BlockPosition center = MCUtil.toBlockPosition(player.getLocation());
/* 207 */     Deque<ChunkCoordIntPair> queue = new ArrayDeque<>(MCUtil.getSpiralOutChunks(center, radius));
/* 208 */     updateLight(sender, world, lightengine, queue);
/*     */   }
/*     */   
/*     */   private void updateLight(CommandSender sender, WorldServer world, LightEngineThreaded lightengine, Deque<ChunkCoordIntPair> queue) {
/* 212 */     ChunkCoordIntPair coord = queue.poll();
/* 213 */     if (coord == null) {
/* 214 */       sender.sendMessage("All Chunks Light updated");
/*     */       return;
/*     */     } 
/* 217 */     world.getChunkProvider().getChunkAtAsynchronously(coord.x, coord.z, false, false).whenCompleteAsync((either, ex) -> {
/*     */           if (ex != null) {
/*     */             sender.sendMessage("Error loading chunk " + coord);
/*     */             
/*     */             updateLight(sender, world, lightengine, queue);
/*     */             
/*     */             return;
/*     */           } 
/*     */           
/*     */           Chunk chunk = either.left().orElse(null);
/*     */           
/*     */           if (chunk == null) {
/*     */             updateLight(sender, world, lightengine, queue);
/*     */             
/*     */             return;
/*     */           } 
/*     */           lightengine.a(world.paperConfig.lightQueueSize + 4096);
/*     */           sender.sendMessage("Updating Light " + coord);
/*     */           int cx = (chunk.getPos()).x << 4;
/*     */           int cz = (chunk.getPos()).z << 4;
/*     */           for (int y = 0; y < world.getHeight(); y++) {
/*     */             for (int x = 0; x < 16; x++) {
/*     */               for (int z = 0; z < 16; z++) {
/*     */                 BlockPosition pos = new BlockPosition(cx + x, y, cz + z);
/*     */                 lightengine.a(pos);
/*     */               } 
/*     */             } 
/*     */           } 
/*     */           lightengine.queueUpdate();
/*     */           PlayerChunk visibleChunk = (world.getChunkProvider()).playerChunkMap.getVisibleChunk(chunk.coordinateKey);
/*     */           if (visibleChunk != null) {
/*     */             (world.getChunkProvider()).playerChunkMap.addLightTask(visibleChunk, ());
/*     */           } else {
/*     */             updateLight(sender, world, lightengine, queue);
/*     */           } 
/*     */           lightengine.a(world.paperConfig.lightQueueSize);
/* 253 */         }(Executor)MinecraftServer.getServer());
/*     */   }
/*     */   
/*     */   private void doSyncLoadInfo(CommandSender sender, String[] args) {
/* 257 */     if (!SyncLoadFinder.ENABLED) {
/* 258 */       sender.sendMessage(ChatColor.RED + "This command requires the server startup flag '-Dpaper.debug-sync-loads=true' to be set.");
/*     */       
/*     */       return;
/*     */     } 
/* 262 */     File file = new File(new File(new File("."), "debug"), "sync-load-info" + DateTimeFormatter.ofPattern("yyyy-MM-dd_HH.mm.ss").format(LocalDateTime.now()) + ".txt");
/* 263 */     file.getParentFile().mkdirs();
/* 264 */     sender.sendMessage(ChatColor.GREEN + "Writing sync load info to " + file.toString());
/*     */ 
/*     */     
/*     */     try {
/* 268 */       JsonObject data = SyncLoadFinder.serialize();
/*     */       
/* 270 */       StringWriter stringWriter = new StringWriter();
/* 271 */       JsonWriter jsonWriter = new JsonWriter(stringWriter);
/* 272 */       jsonWriter.setIndent(" ");
/* 273 */       jsonWriter.setLenient(false);
/* 274 */       Streams.write((JsonElement)data, jsonWriter);
/*     */       
/* 276 */       String fileData = stringWriter.toString();
/*     */ 
/*     */       
/* 279 */       PrintStream out = new PrintStream(new FileOutputStream(file), false, "UTF-8");
/*     */       
/* 281 */       try { out.print(fileData);
/* 282 */         out.close(); } catch (Throwable throwable) { try { out.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }  throw throwable; }
/* 283 */        sender.sendMessage(ChatColor.GREEN + "Successfully written sync load information!");
/* 284 */     } catch (Throwable thr) {
/* 285 */       sender.sendMessage(ChatColor.RED + "Failed to write sync load information");
/* 286 */       thr.printStackTrace();
/*     */     } 
/*     */   }
/*     */   
/*     */   private void doChunkInfo(CommandSender sender, String[] args) {
/*     */     List<World> worlds;
/* 292 */     if (args.length < 2 || args[1].equals("*")) {
/* 293 */       worlds = Bukkit.getWorlds();
/*     */     } else {
/* 295 */       worlds = new ArrayList<>(args.length - 1);
/* 296 */       for (int i = 1; i < args.length; i++) {
/* 297 */         World world = Bukkit.getWorld(args[i]);
/* 298 */         if (world == null) {
/* 299 */           sender.sendMessage(ChatColor.RED + "World '" + args[i] + "' is invalid");
/*     */           return;
/*     */         } 
/* 302 */         worlds.add(world);
/*     */       } 
/*     */     } 
/*     */     
/* 306 */     for (World bukkitWorld : worlds) {
/* 307 */       WorldServer world = ((CraftWorld)bukkitWorld).getHandle();
/*     */       
/* 309 */       int total = 0;
/* 310 */       int inactive = 0;
/* 311 */       int border = 0;
/* 312 */       int ticking = 0;
/* 313 */       int entityTicking = 0;
/*     */       
/* 315 */       for (ObjectIterator<PlayerChunk> objectIterator = (world.getChunkProvider()).playerChunkMap.updatingChunks.values().iterator(); objectIterator.hasNext(); ) { PlayerChunk chunk = objectIterator.next();
/* 316 */         if (chunk.getFullChunkIfCached() == null) {
/*     */           continue;
/*     */         }
/*     */         
/* 320 */         total++;
/*     */         
/* 322 */         PlayerChunk.State state = PlayerChunk.getChunkState(chunk.getTicketLevel());
/*     */         
/* 324 */         switch (state) {
/*     */           case INACCESSIBLE:
/* 326 */             inactive++;
/*     */           
/*     */           case BORDER:
/* 329 */             border++;
/*     */           
/*     */           case TICKING:
/* 332 */             ticking++;
/*     */           
/*     */           case ENTITY_TICKING:
/* 335 */             entityTicking++;
/*     */         } 
/*     */         
/*     */          }
/*     */       
/* 340 */       sender.sendMessage(ChatColor.BLUE + "Chunks in " + ChatColor.GREEN + bukkitWorld.getName() + ChatColor.DARK_AQUA + ":");
/* 341 */       sender.sendMessage(ChatColor.BLUE + "Total: " + ChatColor.DARK_AQUA + total + ChatColor.BLUE + " Inactive: " + ChatColor.DARK_AQUA + inactive + ChatColor.BLUE + " Border: " + ChatColor.DARK_AQUA + border + ChatColor.BLUE + " Ticking: " + ChatColor.DARK_AQUA + ticking + ChatColor.BLUE + " Entity: " + ChatColor.DARK_AQUA + entityTicking);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void doDebug(CommandSender sender, String[] args) {
/* 348 */     if (args.length < 2) {
/* 349 */       sender.sendMessage(ChatColor.RED + "Use /paper debug [chunks] help for more information on a specific command");
/*     */       
/*     */       return;
/*     */     } 
/* 353 */     String debugType = args[1].toLowerCase(Locale.ENGLISH);
/* 354 */     switch (debugType) {
/*     */       case "chunks":
/* 356 */         if (args.length >= 3 && args[2].toLowerCase(Locale.ENGLISH).equals("help")) {
/* 357 */           sender.sendMessage(ChatColor.RED + "Use /paper debug chunks to dump loaded chunk information to a file");
/*     */         }
/*     */         else {
/*     */           
/* 361 */           File file = new File(new File(new File("."), "debug"), "chunks-" + DateTimeFormatter.ofPattern("yyyy-MM-dd_HH.mm.ss").format(LocalDateTime.now()) + ".txt");
/* 362 */           sender.sendMessage(ChatColor.GREEN + "Writing chunk information dump to " + file.toString());
/*     */           try {
/* 364 */             MCUtil.dumpChunks(file);
/* 365 */             sender.sendMessage(ChatColor.GREEN + "Successfully written chunk information!");
/* 366 */           } catch (Throwable thr) {
/* 367 */             MinecraftServer.LOGGER.warn("Failed to dump chunk information to file " + file.toString(), thr);
/* 368 */             sender.sendMessage(ChatColor.RED + "Failed to dump chunk information, see console");
/*     */           } 
/*     */         } 
/*     */         return;
/*     */     } 
/*     */ 
/*     */     
/* 375 */     sender.sendMessage(ChatColor.RED + "Use /paper debug [chunks] help for more information on a specific command"); } private void listEntities(CommandSender sender, String[] args) { String filter; String cleanfilter; Set<MinecraftKey> names; String worldName; Map<MinecraftKey, MutablePair<Integer, Map<ChunkCoordIntPair, Integer>>> list;
/*     */     World bukkitWorld;
/*     */     WorldServer world;
/*     */     Map<MinecraftKey, Integer> nonEntityTicking;
/*     */     ChunkProviderServer chunkProviderServer;
/*     */     ObjectCollection objectCollection;
/*     */     List<Pair<MinecraftKey, Integer>> info;
/*     */     int count;
/*     */     int nonTickingCount;
/* 384 */     if (args.length < 2 || args[1].toLowerCase(Locale.ENGLISH).equals("help")) {
/* 385 */       sender.sendMessage(ChatColor.RED + "Use /paper entity [list] help for more information on a specific command.");
/*     */       
/*     */       return;
/*     */     } 
/* 389 */     switch (args[1].toLowerCase(Locale.ENGLISH)) {
/*     */       case "list":
/* 391 */         filter = "*";
/* 392 */         if (args.length > 2) {
/* 393 */           if (args[2].toLowerCase(Locale.ENGLISH).equals("help")) {
/* 394 */             sender.sendMessage(ChatColor.RED + "Use /paper entity list [filter] [worldName] to get entity info that matches the optional filter.");
/*     */             return;
/*     */           } 
/* 397 */           filter = args[2];
/*     */         } 
/* 399 */         cleanfilter = filter.replace("?", ".?").replace("*", ".*?");
/*     */ 
/*     */         
/* 402 */         names = (Set<MinecraftKey>)EntityTypes.getEntityNameList().stream().filter(n -> n.toString().matches(cleanfilter)).collect(Collectors.toSet());
/*     */         
/* 404 */         if (names.isEmpty()) {
/* 405 */           sender.sendMessage(ChatColor.RED + "Invalid filter, does not match any entities. Use /paper entity list for a proper list");
/*     */           
/*     */           return;
/*     */         } 
/*     */         
/* 410 */         if (args.length > 3) {
/* 411 */           worldName = args[3];
/* 412 */         } else if (sender instanceof Player) {
/* 413 */           worldName = ((Player)sender).getWorld().getName();
/*     */         } else {
/* 415 */           sender.sendMessage(ChatColor.RED + "Please specify the name of a world");
/* 416 */           sender.sendMessage(ChatColor.RED + "To do so without a filter, specify '*' as the filter");
/*     */           
/*     */           return;
/*     */         } 
/* 420 */         list = Maps.newHashMap();
/* 421 */         bukkitWorld = Bukkit.getWorld(worldName);
/* 422 */         if (bukkitWorld == null) {
/* 423 */           sender.sendMessage(ChatColor.RED + "Could not load world for " + worldName + ". Please select a valid world.");
/*     */           return;
/*     */         } 
/* 426 */         world = ((CraftWorld)Bukkit.getWorld(worldName)).getHandle();
/*     */         
/* 428 */         nonEntityTicking = Maps.newHashMap();
/* 429 */         chunkProviderServer = world.getChunkProvider();
/*     */         
/* 431 */         objectCollection = world.entitiesById.values();
/* 432 */         objectCollection.forEach(e -> {
/*     */               MinecraftKey key = e.getMinecraftKey();
/*     */               if (e.shouldBeRemoved)
/*     */                 return; 
/*     */               MutablePair<Integer, Map<ChunkCoordIntPair, Integer>> info = list.computeIfAbsent(key, ());
/*     */               ChunkCoordIntPair chunk = new ChunkCoordIntPair(e.chunkX, e.chunkZ);
/*     */               MutablePair<Integer, Map<ChunkCoordIntPair, Integer>> mutablePair1 = info;
/*     */               Integer integer = (Integer)mutablePair1.left;
/*     */               mutablePair1.left = Integer.valueOf(((Integer)mutablePair1.left).intValue() + 1);
/*     */               ((Map<ChunkCoordIntPair, Integer>)info.right).put(chunk, Integer.valueOf(((Integer)((Map)info.right).getOrDefault(chunk, Integer.valueOf(0))).intValue() + 1));
/*     */               if (!chunkProviderServer.isInEntityTickingChunk(e))
/*     */                 nonEntityTicking.merge(key, Integer.valueOf(1), Integer::sum); 
/*     */             });
/* 445 */         if (names.size() == 1) {
/* 446 */           MinecraftKey name = names.iterator().next();
/* 447 */           Pair<Integer, Map<ChunkCoordIntPair, Integer>> pair = (Pair<Integer, Map<ChunkCoordIntPair, Integer>>)list.get(name);
/* 448 */           int nonTicking = ((Integer)nonEntityTicking.getOrDefault(name, Integer.valueOf(0))).intValue();
/* 449 */           if (pair == null) {
/* 450 */             sender.sendMessage(ChatColor.RED + "No entities found.");
/*     */             return;
/*     */           } 
/* 453 */           sender.sendMessage("Entity: " + name + " Total Ticking: " + (((Integer)pair.getLeft()).intValue() - nonTicking) + ", Total Non-Ticking: " + nonTicking);
/* 454 */           ((Map)pair.getRight()).entrySet().stream()
/* 455 */             .sorted((a, b) -> !((Integer)a.getValue()).equals(b.getValue()) ? (((Integer)b.getValue()).intValue() - ((Integer)a.getValue()).intValue()) : ((ChunkCoordIntPair)a.getKey()).toString().compareTo(((ChunkCoordIntPair)b.getKey()).toString()))
/* 456 */             .limit(10L).forEach(e -> sender.sendMessage("  " + e.getValue() + ": " + ((ChunkCoordIntPair)e.getKey()).x + ", " + ((ChunkCoordIntPair)e.getKey()).z + (chunkProviderServer.isEntityTickingChunk((ChunkCoordIntPair)e.getKey()) ? " (Ticking)" : " (Non-Ticking)")));
/*     */ 
/*     */           
/*     */           break;
/*     */         } 
/*     */         
/* 462 */         info = (List<Pair<MinecraftKey, Integer>>)list.entrySet().stream().filter(e -> names.contains(e.getKey())).map(e -> Pair.of(e.getKey(), ((MutablePair)e.getValue()).left)).sorted((a, b) -> !((Integer)a.getRight()).equals(b.getRight()) ? (((Integer)b.getRight()).intValue() - ((Integer)a.getRight()).intValue()) : ((MinecraftKey)a.getKey()).toString().compareTo(((MinecraftKey)b.getKey()).toString())).collect(Collectors.toList());
/*     */         
/* 464 */         if (info == null || info.size() == 0) {
/* 465 */           sender.sendMessage(ChatColor.RED + "No entities found.");
/*     */           
/*     */           return;
/*     */         } 
/* 469 */         count = info.stream().mapToInt(Pair::getRight).sum();
/* 470 */         nonTickingCount = nonEntityTicking.values().stream().mapToInt(Integer::intValue).sum();
/* 471 */         sender.sendMessage("Total Ticking: " + (count - nonTickingCount) + ", Total Non-Ticking: " + nonTickingCount);
/* 472 */         info.forEach(e -> {
/*     */               int nonTicking = ((Integer)nonEntityTicking.getOrDefault(e.getKey(), Integer.valueOf(0))).intValue();
/*     */               sender.sendMessage("  " + (((Integer)e.getValue()).intValue() - nonTicking) + " (" + nonTicking + ") : " + e.getKey());
/*     */             });
/* 476 */         sender.sendMessage("* First number is ticking entities, second number is non-ticking entities");
/*     */         break;
/*     */     }  }
/*     */ 
/*     */ 
/*     */   
/*     */   private void dumpHeap(CommandSender sender) {
/* 483 */     Path dir = Paths.get("./dumps", new String[0]);
/* 484 */     String name = "heap-dump-" + DateTimeFormatter.ofPattern("yyyy-MM-dd_HH.mm.ss").format(LocalDateTime.now());
/*     */     
/* 486 */     Command.broadcastCommandMessage(sender, ChatColor.YELLOW + "Writing JVM heap data...");
/*     */     
/* 488 */     Path file = CraftServer.dumpHeap(dir, name);
/* 489 */     if (file != null) {
/* 490 */       Command.broadcastCommandMessage(sender, ChatColor.GREEN + "Heap dump saved to " + file);
/*     */     } else {
/* 492 */       Command.broadcastCommandMessage(sender, ChatColor.RED + "Failed to write heap dump, see sever log for details");
/*     */     } 
/*     */   }
/*     */   
/*     */   private void doReload(CommandSender sender) {
/* 497 */     Command.broadcastCommandMessage(sender, ChatColor.RED + "Please note that this command is not supported and may cause issues.");
/* 498 */     Command.broadcastCommandMessage(sender, ChatColor.RED + "If you encounter any issues please use the /stop command to restart your server.");
/*     */     
/* 500 */     MinecraftServer console = MinecraftServer.getServer();
/* 501 */     PaperConfig.init((File)console.options.valueOf("paper-settings"));
/* 502 */     for (WorldServer world : console.getWorlds()) {
/* 503 */       world.paperConfig.init();
/*     */     }
/* 505 */     console.server.reloadCount++;
/*     */     
/* 507 */     Command.broadcastCommandMessage(sender, ChatColor.GREEN + "Paper config reload complete.");
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\PaperCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
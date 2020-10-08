/*      */ package org.bukkit;
/*      */ 
/*      */ import com.destroystokyo.paper.HeightmapType;
/*      */ import io.papermc.paper.world.MoonPhase;
/*      */ import java.io.File;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collection;
/*      */ import java.util.HashMap;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.Objects;
/*      */ import java.util.UUID;
/*      */ import java.util.concurrent.CompletableFuture;
/*      */ import java.util.function.Consumer;
/*      */ import java.util.function.Predicate;
/*      */ import org.bukkit.block.Biome;
/*      */ import org.bukkit.block.Block;
/*      */ import org.bukkit.block.data.BlockData;
/*      */ import org.bukkit.boss.DragonBattle;
/*      */ import org.bukkit.entity.Arrow;
/*      */ import org.bukkit.entity.Entity;
/*      */ import org.bukkit.entity.EntityType;
/*      */ import org.bukkit.entity.FallingBlock;
/*      */ import org.bukkit.entity.Item;
/*      */ import org.bukkit.entity.LightningStrike;
/*      */ import org.bukkit.entity.LivingEntity;
/*      */ import org.bukkit.entity.Player;
/*      */ import org.bukkit.event.entity.CreatureSpawnEvent;
/*      */ import org.bukkit.generator.BlockPopulator;
/*      */ import org.bukkit.generator.ChunkGenerator;
/*      */ import org.bukkit.inventory.ItemStack;
/*      */ import org.bukkit.material.MaterialData;
/*      */ import org.bukkit.metadata.Metadatable;
/*      */ import org.bukkit.plugin.Plugin;
/*      */ import org.bukkit.plugin.messaging.PluginMessageRecipient;
/*      */ import org.bukkit.util.BoundingBox;
/*      */ import org.bukkit.util.Consumer;
/*      */ import org.bukkit.util.RayTraceResult;
/*      */ import org.bukkit.util.Vector;
/*      */ import org.jetbrains.annotations.Contract;
/*      */ import org.jetbrains.annotations.NotNull;
/*      */ import org.jetbrains.annotations.Nullable;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public interface World
/*      */   extends PluginMessageRecipient, Metadatable
/*      */ {
/*      */   int getEntityCount();
/*      */   
/*      */   int getTileEntityCount();
/*      */   
/*      */   int getTickableTileEntityCount();
/*      */   
/*      */   int getChunkCount();
/*      */   
/*      */   int getPlayerCount();
/*      */   
/*      */   @NotNull
/*      */   MoonPhase getMoonPhase();
/*      */   
/*      */   @NotNull
/*      */   Block getBlockAt(int paramInt1, int paramInt2, int paramInt3);
/*      */   
/*      */   @NotNull
/*      */   Block getBlockAt(@NotNull Location paramLocation);
/*      */   
/*      */   @NotNull
/*      */   default Block getBlockAtKey(long key) {
/*  111 */     int x = Block.getBlockKeyX(key);
/*  112 */     int y = Block.getBlockKeyY(key);
/*  113 */     int z = Block.getBlockKeyZ(key);
/*  114 */     return getBlockAt(x, y, z);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   default Location getLocationAtKey(long key) {
/*  126 */     int x = Block.getBlockKeyX(key);
/*  127 */     int y = Block.getBlockKeyY(key);
/*  128 */     int z = Block.getBlockKeyZ(key);
/*  129 */     return new Location(this, x, y, z);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   int getHighestBlockYAt(int paramInt1, int paramInt2);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   int getHighestBlockYAt(@NotNull Location paramLocation);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   Block getHighestBlockAt(int paramInt1, int paramInt2);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   Block getHighestBlockAt(@NotNull Location paramLocation);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   int getHighestBlockYAt(int paramInt1, int paramInt2, @NotNull HeightmapType paramHeightmapType) throws UnsupportedOperationException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   default int getHighestBlockYAt(@NotNull Location location, @NotNull HeightmapType heightmap) throws UnsupportedOperationException {
/*  208 */     return getHighestBlockYAt(location.getBlockX(), location.getBlockZ(), heightmap);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   @NotNull
/*      */   default Block getHighestBlockAt(int x, int z, @NotNull HeightmapType heightmap) throws UnsupportedOperationException {
/*  228 */     return getBlockAt(x, getHighestBlockYAt(x, z, heightmap), z);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   @NotNull
/*      */   default Block getHighestBlockAt(@NotNull Location location, @NotNull HeightmapType heightmap) throws UnsupportedOperationException {
/*  248 */     return getHighestBlockAt(location.getBlockX(), location.getBlockZ(), heightmap);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   int getHighestBlockYAt(int paramInt1, int paramInt2, @NotNull HeightMap paramHeightMap);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   int getHighestBlockYAt(@NotNull Location paramLocation, @NotNull HeightMap paramHeightMap);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   Block getHighestBlockAt(int paramInt1, int paramInt2, @NotNull HeightMap paramHeightMap);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   Block getHighestBlockAt(@NotNull Location paramLocation, @NotNull HeightMap paramHeightMap);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   Chunk getChunkAt(int paramInt1, int paramInt2);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   Chunk getChunkAt(@NotNull Location paramLocation);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   Chunk getChunkAt(@NotNull Block paramBlock);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   default Chunk getChunkAt(long chunkKey) {
/*  343 */     return getChunkAt((int)chunkKey, (int)(chunkKey >> 32L));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   default boolean isChunkGenerated(long chunkKey) {
/*  354 */     return isChunkGenerated((int)chunkKey, (int)(chunkKey >> 32L));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public static interface ChunkLoadCallback
/*      */     extends Consumer<Chunk>
/*      */   {
/*      */     default void accept(@NotNull Chunk chunk) {
/*  379 */       onLoad(chunk);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     void onLoad(@NotNull Chunk param1Chunk);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   default void getChunkAtAsync(int x, int z, @NotNull ChunkLoadCallback cb) {
/*  404 */     Objects.requireNonNull(cb); getChunkAtAsync(x, z, true).thenAccept(cb::onLoad);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   default void getChunkAtAsync(@NotNull Location loc, @NotNull ChunkLoadCallback cb) {
/*  427 */     Objects.requireNonNull(cb); getChunkAtAsync(loc, true).thenAccept(cb::onLoad);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   default void getChunkAtAsync(@NotNull Block block, @NotNull ChunkLoadCallback cb) {
/*  450 */     Objects.requireNonNull(cb); getChunkAtAsync(block, true).thenAccept(cb::onLoad);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   default void getChunkAtAsync(int x, int z, @NotNull Consumer<Chunk> cb) {
/*  472 */     getChunkAtAsync(x, z, true).thenAccept(cb);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   default void getChunkAtAsync(int x, int z, boolean gen, @NotNull Consumer<Chunk> cb) {
/*  495 */     getChunkAtAsync(x, z, gen).thenAccept(cb);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   default void getChunkAtAsync(@NotNull Location loc, @NotNull Consumer<Chunk> cb) {
/*  516 */     getChunkAtAsync((int)Math.floor(loc.getX()) >> 4, (int)Math.floor(loc.getZ()) >> 4, true, cb);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   default void getChunkAtAsync(@NotNull Location loc, boolean gen, @NotNull Consumer<Chunk> cb) {
/*  538 */     getChunkAtAsync((int)Math.floor(loc.getX()) >> 4, (int)Math.floor(loc.getZ()) >> 4, gen, cb);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   default void getChunkAtAsync(@NotNull Block block, @NotNull Consumer<Chunk> cb) {
/*  559 */     getChunkAtAsync(block.getX() >> 4, block.getZ() >> 4, true, cb);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   default void getChunkAtAsync(@NotNull Block block, boolean gen, @NotNull Consumer<Chunk> cb) {
/*  581 */     getChunkAtAsync(block.getX() >> 4, block.getZ() >> 4, gen, cb);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   default CompletableFuture<Chunk> getChunkAtAsync(@NotNull Location loc) {
/*  601 */     return getChunkAtAsync((int)Math.floor(loc.getX()) >> 4, (int)Math.floor(loc.getZ()) >> 4, true);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   default CompletableFuture<Chunk> getChunkAtAsync(@NotNull Location loc, boolean gen) {
/*  622 */     return getChunkAtAsync((int)Math.floor(loc.getX()) >> 4, (int)Math.floor(loc.getZ()) >> 4, gen);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   default CompletableFuture<Chunk> getChunkAtAsync(@NotNull Block block) {
/*  642 */     return getChunkAtAsync(block.getX() >> 4, block.getZ() >> 4, true);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   default CompletableFuture<Chunk> getChunkAtAsync(@NotNull Block block, boolean gen) {
/*  663 */     return getChunkAtAsync(block.getX() >> 4, block.getZ() >> 4, gen);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   default CompletableFuture<Chunk> getChunkAtAsync(int x, int z) {
/*  685 */     return getChunkAtAsync(x, z, true);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   default CompletableFuture<Chunk> getChunkAtAsync(int x, int z, boolean gen) {
/*  708 */     return getChunkAtAsync(x, z, gen, false);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   default CompletableFuture<Chunk> getChunkAtAsyncUrgently(@NotNull Location loc) {
/*  728 */     return getChunkAtAsync((int)Math.floor(loc.getX()) >> 4, (int)Math.floor(loc.getZ()) >> 4, true, true);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   default CompletableFuture<Chunk> getChunkAtAsyncUrgently(@NotNull Location loc, boolean gen) {
/*  749 */     return getChunkAtAsync((int)Math.floor(loc.getX()) >> 4, (int)Math.floor(loc.getZ()) >> 4, gen, true);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   default CompletableFuture<Chunk> getChunkAtAsyncUrgently(@NotNull Block block) {
/*  769 */     return getChunkAtAsync(block.getX() >> 4, block.getZ() >> 4, true, true);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   default CompletableFuture<Chunk> getChunkAtAsyncUrgently(@NotNull Block block, boolean gen) {
/*  790 */     return getChunkAtAsync(block.getX() >> 4, block.getZ() >> 4, gen, true);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   default CompletableFuture<Chunk> getChunkAtAsyncUrgently(int x, int z) {
/*  812 */     return getChunkAtAsync(x, z, true, true);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   CompletableFuture<Chunk> getChunkAtAsync(int paramInt1, int paramInt2, boolean paramBoolean1, boolean paramBoolean2);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean isChunkLoaded(@NotNull Chunk paramChunk);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   Chunk[] getLoadedChunks();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void loadChunk(@NotNull Chunk paramChunk);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean isChunkLoaded(int paramInt1, int paramInt2);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean isChunkGenerated(int paramInt1, int paramInt2);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   boolean isChunkInUse(int paramInt1, int paramInt2);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void loadChunk(int paramInt1, int paramInt2);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean loadChunk(int paramInt1, int paramInt2, boolean paramBoolean);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean unloadChunk(@NotNull Chunk paramChunk);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean unloadChunk(int paramInt1, int paramInt2);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean unloadChunk(int paramInt1, int paramInt2, boolean paramBoolean);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean unloadChunkRequest(int paramInt1, int paramInt2);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   boolean regenerateChunk(int paramInt1, int paramInt2);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   boolean refreshChunk(int paramInt1, int paramInt2);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean isChunkForceLoaded(int paramInt1, int paramInt2);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setChunkForceLoaded(int paramInt1, int paramInt2, boolean paramBoolean);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   Collection<Chunk> getForceLoadedChunks();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean addPluginChunkTicket(int paramInt1, int paramInt2, @NotNull Plugin paramPlugin);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean removePluginChunkTicket(int paramInt1, int paramInt2, @NotNull Plugin paramPlugin);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void removePluginChunkTickets(@NotNull Plugin paramPlugin);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   Collection<Plugin> getPluginChunkTickets(int paramInt1, int paramInt2);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   Map<Plugin, Collection<Chunk>> getPluginChunkTickets();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   Item dropItem(@NotNull Location paramLocation, @NotNull ItemStack paramItemStack);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   Item dropItemNaturally(@NotNull Location paramLocation, @NotNull ItemStack paramItemStack);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   Arrow spawnArrow(@NotNull Location paramLocation, @NotNull Vector paramVector, float paramFloat1, float paramFloat2);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   <T extends org.bukkit.entity.AbstractArrow> T spawnArrow(@NotNull Location paramLocation, @NotNull Vector paramVector, float paramFloat1, float paramFloat2, @NotNull Class<T> paramClass);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean generateTree(@NotNull Location paramLocation, @NotNull TreeType paramTreeType);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean generateTree(@NotNull Location paramLocation, @NotNull TreeType paramTreeType, @NotNull BlockChangeDelegate paramBlockChangeDelegate);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   Entity spawnEntity(@NotNull Location paramLocation, @NotNull EntityType paramEntityType);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   LightningStrike strikeLightning(@NotNull Location paramLocation);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   LightningStrike strikeLightningEffect(@NotNull Location paramLocation);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   List<Entity> getEntities();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   List<LivingEntity> getLivingEntities();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   @NotNull
/*      */   <T extends Entity> Collection<T> getEntitiesByClass(@NotNull Class<T>... paramVarArgs);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   <T extends Entity> Collection<T> getEntitiesByClass(@NotNull Class<T> paramClass);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   Collection<Entity> getEntitiesByClasses(@NotNull Class<?>... paramVarArgs);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   default Collection<LivingEntity> getNearbyLivingEntities(@NotNull Location loc, double radius) {
/* 1259 */     return getNearbyEntitiesByType(LivingEntity.class, loc, radius, radius, radius);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   default Collection<LivingEntity> getNearbyLivingEntities(@NotNull Location loc, double xzRadius, double yRadius) {
/* 1271 */     return getNearbyEntitiesByType(LivingEntity.class, loc, xzRadius, yRadius, xzRadius);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   default Collection<LivingEntity> getNearbyLivingEntities(@NotNull Location loc, double xRadius, double yRadius, double zRadius) {
/* 1284 */     return getNearbyEntitiesByType(LivingEntity.class, loc, xRadius, yRadius, zRadius);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   default Collection<LivingEntity> getNearbyLivingEntities(@NotNull Location loc, double radius, @Nullable Predicate<LivingEntity> predicate) {
/* 1296 */     return getNearbyEntitiesByType((Class)LivingEntity.class, loc, radius, radius, radius, predicate);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   default Collection<LivingEntity> getNearbyLivingEntities(@NotNull Location loc, double xzRadius, double yRadius, @Nullable Predicate<LivingEntity> predicate) {
/* 1309 */     return getNearbyEntitiesByType((Class)LivingEntity.class, loc, xzRadius, yRadius, xzRadius, predicate);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   default Collection<LivingEntity> getNearbyLivingEntities(@NotNull Location loc, double xRadius, double yRadius, double zRadius, @Nullable Predicate<LivingEntity> predicate) {
/* 1323 */     return getNearbyEntitiesByType((Class)LivingEntity.class, loc, xRadius, yRadius, zRadius, predicate);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   default Collection<Player> getNearbyPlayers(@NotNull Location loc, double radius) {
/* 1334 */     return getNearbyEntitiesByType(Player.class, loc, radius, radius, radius);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   default Collection<Player> getNearbyPlayers(@NotNull Location loc, double xzRadius, double yRadius) {
/* 1346 */     return getNearbyEntitiesByType(Player.class, loc, xzRadius, yRadius, xzRadius);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   default Collection<Player> getNearbyPlayers(@NotNull Location loc, double xRadius, double yRadius, double zRadius) {
/* 1359 */     return getNearbyEntitiesByType(Player.class, loc, xRadius, yRadius, zRadius);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   default Collection<Player> getNearbyPlayers(@NotNull Location loc, double radius, @Nullable Predicate<Player> predicate) {
/* 1371 */     return getNearbyEntitiesByType((Class)Player.class, loc, radius, radius, radius, predicate);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   default Collection<Player> getNearbyPlayers(@NotNull Location loc, double xzRadius, double yRadius, @Nullable Predicate<Player> predicate) {
/* 1384 */     return getNearbyEntitiesByType((Class)Player.class, loc, xzRadius, yRadius, xzRadius, predicate);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   default Collection<Player> getNearbyPlayers(@NotNull Location loc, double xRadius, double yRadius, double zRadius, @Nullable Predicate<Player> predicate) {
/* 1398 */     return getNearbyEntitiesByType((Class)Player.class, loc, xRadius, yRadius, zRadius, predicate);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   default <T extends Entity> Collection<T> getNearbyEntitiesByType(@Nullable Class<? extends T> clazz, @NotNull Location loc, double radius) {
/* 1411 */     return getNearbyEntitiesByType(clazz, loc, radius, radius, radius, null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   default <T extends Entity> Collection<T> getNearbyEntitiesByType(@Nullable Class<? extends T> clazz, @NotNull Location loc, double xzRadius, double yRadius) {
/* 1425 */     return getNearbyEntitiesByType(clazz, loc, xzRadius, yRadius, xzRadius, null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   default <T extends Entity> Collection<T> getNearbyEntitiesByType(@Nullable Class<? extends T> clazz, @NotNull Location loc, double xRadius, double yRadius, double zRadius) {
/* 1440 */     return getNearbyEntitiesByType(clazz, loc, xRadius, yRadius, zRadius, null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   default <T extends Entity> Collection<T> getNearbyEntitiesByType(@Nullable Class<? extends T> clazz, @NotNull Location loc, double radius, @Nullable Predicate<T> predicate) {
/* 1454 */     return getNearbyEntitiesByType(clazz, loc, radius, radius, radius, predicate);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   default <T extends Entity> Collection<T> getNearbyEntitiesByType(@Nullable Class<? extends T> clazz, @NotNull Location loc, double xzRadius, double yRadius, @Nullable Predicate<T> predicate) {
/* 1469 */     return getNearbyEntitiesByType(clazz, loc, xzRadius, yRadius, xzRadius, predicate);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   default <T extends Entity> Collection<T> getNearbyEntitiesByType(@Nullable Class<? extends Entity> clazz, @NotNull Location loc, double xRadius, double yRadius, double zRadius, @Nullable Predicate<T> predicate) {
/* 1485 */     if (clazz == null) {
/* 1486 */       clazz = Entity.class;
/*      */     }
/* 1488 */     List<T> nearby = new ArrayList<>();
/* 1489 */     for (Entity bukkitEntity : getNearbyEntities(loc, xRadius, yRadius, zRadius)) {
/*      */       
/* 1491 */       if (clazz.isAssignableFrom(bukkitEntity.getClass()) && (predicate == null || predicate.test((T)bukkitEntity)))
/*      */       {
/* 1493 */         nearby.add((T)bukkitEntity);
/*      */       }
/*      */     } 
/* 1496 */     return nearby;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   List<Player> getPlayers();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   Collection<Entity> getNearbyEntities(@NotNull Location paramLocation, double paramDouble1, double paramDouble2, double paramDouble3);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   Entity getEntity(@NotNull UUID paramUUID);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   Collection<Entity> getNearbyEntities(@NotNull Location paramLocation, double paramDouble1, double paramDouble2, double paramDouble3, @Nullable Predicate<Entity> paramPredicate);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   Collection<Entity> getNearbyEntities(@NotNull BoundingBox paramBoundingBox);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   Collection<Entity> getNearbyEntities(@NotNull BoundingBox paramBoundingBox, @Nullable Predicate<Entity> paramPredicate);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   RayTraceResult rayTraceEntities(@NotNull Location paramLocation, @NotNull Vector paramVector, double paramDouble);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   RayTraceResult rayTraceEntities(@NotNull Location paramLocation, @NotNull Vector paramVector, double paramDouble1, double paramDouble2);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   RayTraceResult rayTraceEntities(@NotNull Location paramLocation, @NotNull Vector paramVector, double paramDouble, @Nullable Predicate<Entity> paramPredicate);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   RayTraceResult rayTraceEntities(@NotNull Location paramLocation, @NotNull Vector paramVector, double paramDouble1, double paramDouble2, @Nullable Predicate<Entity> paramPredicate);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   RayTraceResult rayTraceBlocks(@NotNull Location paramLocation, @NotNull Vector paramVector, double paramDouble);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   RayTraceResult rayTraceBlocks(@NotNull Location paramLocation, @NotNull Vector paramVector, double paramDouble, @NotNull FluidCollisionMode paramFluidCollisionMode);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   RayTraceResult rayTraceBlocks(@NotNull Location paramLocation, @NotNull Vector paramVector, double paramDouble, @NotNull FluidCollisionMode paramFluidCollisionMode, boolean paramBoolean);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   RayTraceResult rayTrace(@NotNull Location paramLocation, @NotNull Vector paramVector, double paramDouble1, @NotNull FluidCollisionMode paramFluidCollisionMode, boolean paramBoolean, double paramDouble2, @Nullable Predicate<Entity> paramPredicate);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   String getName();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   UUID getUID();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   Location getSpawnLocation();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean setSpawnLocation(@NotNull Location paramLocation);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean setSpawnLocation(int paramInt1, int paramInt2, int paramInt3, float paramFloat);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean setSpawnLocation(int paramInt1, int paramInt2, int paramInt3);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   long getTime();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setTime(long paramLong);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   long getFullTime();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setFullTime(long paramLong);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean isDayTime();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean hasStorm();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setStorm(boolean paramBoolean);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   int getWeatherDuration();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setWeatherDuration(int paramInt);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean isThundering();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setThundering(boolean paramBoolean);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   int getThunderDuration();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setThunderDuration(int paramInt);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean createExplosion(double paramDouble1, double paramDouble2, double paramDouble3, float paramFloat);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean createExplosion(double paramDouble1, double paramDouble2, double paramDouble3, float paramFloat, boolean paramBoolean);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean createExplosion(double paramDouble1, double paramDouble2, double paramDouble3, float paramFloat, boolean paramBoolean1, boolean paramBoolean2);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean createExplosion(double paramDouble1, double paramDouble2, double paramDouble3, float paramFloat, boolean paramBoolean1, boolean paramBoolean2, @Nullable Entity paramEntity);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean createExplosion(@NotNull Location paramLocation, float paramFloat);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean createExplosion(@NotNull Location paramLocation, float paramFloat, boolean paramBoolean);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean createExplosion(@Nullable Entity paramEntity, @NotNull Location paramLocation, float paramFloat, boolean paramBoolean1, boolean paramBoolean2);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   default boolean createExplosion(@Nullable Entity source, @NotNull Location loc, float power, boolean setFire) {
/* 2024 */     return createExplosion(source, loc, power, setFire, true);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   default boolean createExplosion(@Nullable Entity source, @NotNull Location loc, float power) {
/* 2036 */     return createExplosion(source, loc, power, true, true);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   default boolean createExplosion(@NotNull Entity source, float power, boolean setFire, boolean breakBlocks) {
/* 2049 */     return createExplosion(source, source.getLocation(), power, setFire, breakBlocks);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   default boolean createExplosion(@NotNull Entity source, float power, boolean setFire) {
/* 2063 */     return createExplosion(source, source.getLocation(), power, setFire, true);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   default boolean createExplosion(@NotNull Entity source, float power) {
/* 2075 */     return createExplosion(source, source.getLocation(), power, true, true);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean createExplosion(@NotNull Location paramLocation, float paramFloat, boolean paramBoolean1, boolean paramBoolean2);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean createExplosion(@NotNull Location paramLocation, float paramFloat, boolean paramBoolean1, boolean paramBoolean2, @Nullable Entity paramEntity);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   Environment getEnvironment();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   long getSeed();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean getPVP();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setPVP(boolean paramBoolean);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   ChunkGenerator getGenerator();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void save();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   List<BlockPopulator> getPopulators();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   <T extends Entity> T spawn(@NotNull Location paramLocation, @NotNull Class<T> paramClass) throws IllegalArgumentException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   default <T extends Entity> T spawn(@NotNull Location location, @NotNull Class<T> clazz, @NotNull CreatureSpawnEvent.SpawnReason reason) throws IllegalArgumentException {
/* 2170 */     return spawn(location, clazz, reason, (Consumer<T>)null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   default <T extends Entity> T spawn(@NotNull Location location, @NotNull Class<T> clazz, @Nullable Consumer<T> function) throws IllegalArgumentException {
/* 2191 */     return spawn(location, clazz, CreatureSpawnEvent.SpawnReason.CUSTOM, function);
/*      */   }
/*      */   
/*      */   @NotNull
/*      */   default <T extends Entity> T spawn(@NotNull Location location, @NotNull Class<T> clazz, @NotNull CreatureSpawnEvent.SpawnReason reason, @Nullable Consumer<T> function) throws IllegalArgumentException {
/* 2196 */     return spawn(location, clazz, function, reason);
/*      */   }
/*      */   
/*      */   @NotNull
/*      */   default Entity spawnEntity(@NotNull Location loc, @NotNull EntityType type, @NotNull CreatureSpawnEvent.SpawnReason reason) {
/* 2201 */     return spawn(loc, type.getEntityClass(), reason, (Consumer<Entity>)null);
/*      */   }
/*      */   
/*      */   @NotNull
/*      */   default Entity spawnEntity(@NotNull Location loc, @NotNull EntityType type, @NotNull CreatureSpawnEvent.SpawnReason reason, @Nullable Consumer<Entity> function) {
/* 2206 */     return spawn(loc, type.getEntityClass(), reason, function);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   <T extends Entity> T spawn(@NotNull Location paramLocation, @NotNull Class<T> paramClass, @Nullable Consumer<T> paramConsumer, @NotNull CreatureSpawnEvent.SpawnReason paramSpawnReason) throws IllegalArgumentException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   FallingBlock spawnFallingBlock(@NotNull Location paramLocation, @NotNull MaterialData paramMaterialData) throws IllegalArgumentException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   FallingBlock spawnFallingBlock(@NotNull Location paramLocation, @NotNull BlockData paramBlockData) throws IllegalArgumentException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   @NotNull
/*      */   FallingBlock spawnFallingBlock(@NotNull Location paramLocation, @NotNull Material paramMaterial, byte paramByte) throws IllegalArgumentException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void playEffect(@NotNull Location paramLocation, @NotNull Effect paramEffect, int paramInt);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void playEffect(@NotNull Location paramLocation, @NotNull Effect paramEffect, int paramInt1, int paramInt2);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   <T> void playEffect(@NotNull Location paramLocation, @NotNull Effect paramEffect, @Nullable T paramT);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   <T> void playEffect(@NotNull Location paramLocation, @NotNull Effect paramEffect, @Nullable T paramT, int paramInt);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   ChunkSnapshot getEmptyChunkSnapshot(int paramInt1, int paramInt2, boolean paramBoolean1, boolean paramBoolean2);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setSpawnFlags(boolean paramBoolean1, boolean paramBoolean2);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean getAllowAnimals();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean getAllowMonsters();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   @NotNull
/*      */   Biome getBiome(int paramInt1, int paramInt2);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   Biome getBiome(int paramInt1, int paramInt2, int paramInt3);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   void setBiome(int paramInt1, int paramInt2, @NotNull Biome paramBiome);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setBiome(int paramInt1, int paramInt2, int paramInt3, @NotNull Biome paramBiome);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   double getTemperature(int paramInt1, int paramInt2);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   double getTemperature(int paramInt1, int paramInt2, int paramInt3);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   double getHumidity(int paramInt1, int paramInt2);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   double getHumidity(int paramInt1, int paramInt2, int paramInt3);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   int getMaxHeight();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   int getSeaLevel();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean getKeepSpawnInMemory();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setKeepSpawnInMemory(boolean paramBoolean);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean isAutoSave();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setAutoSave(boolean paramBoolean);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setDifficulty(@NotNull Difficulty paramDifficulty);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   Difficulty getDifficulty();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   File getWorldFolder();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   @Nullable
/*      */   WorldType getWorldType();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean canGenerateStructures();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean isHardcore();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setHardcore(boolean paramBoolean);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   long getTicksPerAnimalSpawns();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setTicksPerAnimalSpawns(int paramInt);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   long getTicksPerMonsterSpawns();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setTicksPerMonsterSpawns(int paramInt);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   long getTicksPerWaterSpawns();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setTicksPerWaterSpawns(int paramInt);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   long getTicksPerWaterAmbientSpawns();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setTicksPerWaterAmbientSpawns(int paramInt);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   long getTicksPerAmbientSpawns();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setTicksPerAmbientSpawns(int paramInt);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   int getMonsterSpawnLimit();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setMonsterSpawnLimit(int paramInt);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   int getAnimalSpawnLimit();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setAnimalSpawnLimit(int paramInt);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   int getWaterAnimalSpawnLimit();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setWaterAnimalSpawnLimit(int paramInt);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   int getWaterAmbientSpawnLimit();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setWaterAmbientSpawnLimit(int paramInt);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   int getAmbientSpawnLimit();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setAmbientSpawnLimit(int paramInt);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void playSound(@NotNull Location paramLocation, @NotNull Sound paramSound, float paramFloat1, float paramFloat2);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void playSound(@NotNull Location paramLocation, @NotNull String paramString, float paramFloat1, float paramFloat2);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void playSound(@NotNull Location paramLocation, @NotNull Sound paramSound, @NotNull SoundCategory paramSoundCategory, float paramFloat1, float paramFloat2);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void playSound(@NotNull Location paramLocation, @NotNull String paramString, @NotNull SoundCategory paramSoundCategory, float paramFloat1, float paramFloat2);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   String[] getGameRules();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   @Contract("null -> null; !null -> !null")
/*      */   @Nullable
/*      */   String getGameRuleValue(@Nullable String paramString);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   boolean setGameRuleValue(@NotNull String paramString1, @NotNull String paramString2);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   boolean isGameRule(@NotNull String paramString);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   <T> T getGameRuleValue(@NotNull GameRule<T> paramGameRule);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   <T> T getGameRuleDefault(@NotNull GameRule<T> paramGameRule);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   <T> boolean setGameRule(@NotNull GameRule<T> paramGameRule, @NotNull T paramT);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   WorldBorder getWorldBorder();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void spawnParticle(@NotNull Particle paramParticle, @NotNull Location paramLocation, int paramInt);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void spawnParticle(@NotNull Particle paramParticle, double paramDouble1, double paramDouble2, double paramDouble3, int paramInt);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   <T> void spawnParticle(@NotNull Particle paramParticle, @NotNull Location paramLocation, int paramInt, @Nullable T paramT);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   <T> void spawnParticle(@NotNull Particle paramParticle, double paramDouble1, double paramDouble2, double paramDouble3, int paramInt, @Nullable T paramT);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void spawnParticle(@NotNull Particle paramParticle, @NotNull Location paramLocation, int paramInt, double paramDouble1, double paramDouble2, double paramDouble3);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void spawnParticle(@NotNull Particle paramParticle, double paramDouble1, double paramDouble2, double paramDouble3, int paramInt, double paramDouble4, double paramDouble5, double paramDouble6);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   <T> void spawnParticle(@NotNull Particle paramParticle, @NotNull Location paramLocation, int paramInt, double paramDouble1, double paramDouble2, double paramDouble3, @Nullable T paramT);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   <T> void spawnParticle(@NotNull Particle paramParticle, double paramDouble1, double paramDouble2, double paramDouble3, int paramInt, double paramDouble4, double paramDouble5, double paramDouble6, @Nullable T paramT);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void spawnParticle(@NotNull Particle paramParticle, @NotNull Location paramLocation, int paramInt, double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void spawnParticle(@NotNull Particle paramParticle, double paramDouble1, double paramDouble2, double paramDouble3, int paramInt, double paramDouble4, double paramDouble5, double paramDouble6, double paramDouble7);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   <T> void spawnParticle(@NotNull Particle paramParticle, @NotNull Location paramLocation, int paramInt, double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, @Nullable T paramT);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   default <T> void spawnParticle(@NotNull Particle particle, double x, double y, double z, int count, double offsetX, double offsetY, double offsetZ, double extra, @Nullable T data) {
/* 3253 */     spawnParticle(particle, null, null, x, y, z, count, offsetX, offsetY, offsetZ, extra, data, true);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   default <T> void spawnParticle(@NotNull Particle particle, @Nullable List<Player> receivers, @NotNull Player source, double x, double y, double z, int count, double offsetX, double offsetY, double offsetZ, double extra, @Nullable T data) {
/* 3276 */     spawnParticle(particle, receivers, source, x, y, z, count, offsetX, offsetY, offsetZ, extra, data, true);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   <T> void spawnParticle(@NotNull Particle paramParticle, @Nullable List<Player> paramList, @Nullable Player paramPlayer, double paramDouble1, double paramDouble2, double paramDouble3, int paramInt, double paramDouble4, double paramDouble5, double paramDouble6, double paramDouble7, @Nullable T paramT, boolean paramBoolean);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   <T> void spawnParticle(@NotNull Particle paramParticle, @NotNull Location paramLocation, int paramInt, double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, @Nullable T paramT, boolean paramBoolean);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   <T> void spawnParticle(@NotNull Particle paramParticle, double paramDouble1, double paramDouble2, double paramDouble3, int paramInt, double paramDouble4, double paramDouble5, double paramDouble6, double paramDouble7, @Nullable T paramT, boolean paramBoolean);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   Location locateNearestStructure(@NotNull Location paramLocation, @NotNull StructureType paramStructureType, int paramInt, boolean paramBoolean);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   int getViewDistance();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setViewDistance(int paramInt);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   int getNoTickViewDistance();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   void setNoTickViewDistance(int paramInt);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   Spigot spigot();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   Raid locateNearestRaid(@NotNull Location paramLocation, int paramInt);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   List<Raid> getRaids();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   DragonBattle getEnderDragonBattle();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static class Spigot
/*      */   {
/*      */     @NotNull
/*      */     public LightningStrike strikeLightning(@NotNull Location loc, boolean isSilent) {
/* 3431 */       throw new UnsupportedOperationException("Not supported yet.");
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     @NotNull
/*      */     public LightningStrike strikeLightningEffect(@NotNull Location loc, boolean isSilent) {
/* 3443 */       throw new UnsupportedOperationException("Not supported yet.");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public enum Environment
/*      */   {
/* 3492 */     NORMAL(0),
/*      */ 
/*      */ 
/*      */     
/* 3496 */     NETHER(-1),
/*      */ 
/*      */ 
/*      */     
/* 3500 */     THE_END(1);
/*      */     
/*      */     private final int id;
/* 3503 */     private static final Map<Integer, Environment> lookup = new HashMap<>();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     static {
/* 3534 */       for (Environment env : values())
/* 3535 */         lookup.put(Integer.valueOf(env.getId()), env); 
/*      */     }
/*      */     
/*      */     Environment(int id) {
/*      */       this.id = id;
/*      */     }
/*      */     
/*      */     @Deprecated
/*      */     public int getId() {
/*      */       return this.id;
/*      */     }
/*      */     
/*      */     @Deprecated
/*      */     @Nullable
/*      */     public static Environment getEnvironment(int id) {
/*      */       return lookup.get(Integer.valueOf(id));
/*      */     }
/*      */   }
/*      */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\World.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
/*     */ package org.bukkit;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.block.BlockState;
/*     */ import org.bukkit.block.data.BlockData;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public interface Chunk
/*     */ {
/*     */   int getX();
/*     */   
/*     */   int getZ();
/*     */   
/*     */   default long getChunkKey() {
/*  35 */     return getChunkKey(getX(), getZ());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static long getChunkKey(@NotNull Location loc) {
/*  43 */     return getChunkKey((int)Math.floor(loc.getX()) >> 4, (int)Math.floor(loc.getZ()) >> 4);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static long getChunkKey(int x, int z) {
/*  52 */     return x & 0xFFFFFFFFL | (z & 0xFFFFFFFFL) << 32L;
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
/*     */   World getWorld();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   Block getBlock(int paramInt1, int paramInt2, int paramInt3);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   ChunkSnapshot getChunkSnapshot();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   ChunkSnapshot getChunkSnapshot(boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   Entity[] getEntities();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   default BlockState[] getTileEntities() {
/* 113 */     return getTileEntities(true);
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   BlockState[] getTileEntities(boolean paramBoolean);
/*     */   
/*     */   boolean isLoaded();
/*     */   
/*     */   boolean load(boolean paramBoolean);
/*     */   
/*     */   boolean load();
/*     */   
/*     */   boolean unload(boolean paramBoolean);
/*     */   
/*     */   boolean unload();
/*     */   
/*     */   boolean isSlimeChunk();
/*     */   
/*     */   boolean isForceLoaded();
/*     */   
/*     */   void setForceLoaded(boolean paramBoolean);
/*     */   
/*     */   boolean addPluginChunkTicket(@NotNull Plugin paramPlugin);
/*     */   
/*     */   boolean removePluginChunkTicket(@NotNull Plugin paramPlugin);
/*     */   
/*     */   @NotNull
/*     */   Collection<Plugin> getPluginChunkTickets();
/*     */   
/*     */   long getInhabitedTime();
/*     */   
/*     */   void setInhabitedTime(long paramLong);
/*     */   
/*     */   boolean contains(@NotNull BlockData paramBlockData);
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\Chunk.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
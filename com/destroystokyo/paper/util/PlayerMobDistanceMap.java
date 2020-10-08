/*     */ package com.destroystokyo.paper.util;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
/*     */ import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectIterator;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectLinkedOpenHashSet;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectListIterator;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import net.minecraft.server.v1_16_R2.ChunkCoordIntPair;
/*     */ import net.minecraft.server.v1_16_R2.EntityPlayer;
/*     */ import net.minecraft.server.v1_16_R2.SectionPosition;
/*     */ import org.spigotmc.AsyncCatcher;
/*     */ 
/*     */ public final class PlayerMobDistanceMap
/*     */ {
/*  18 */   private static final PooledHashSets.PooledObjectLinkedOpenHashSet<EntityPlayer> EMPTY_SET = new PooledHashSets.PooledObjectLinkedOpenHashSet<>();
/*     */   
/*  20 */   private final Map<EntityPlayer, SectionPosition> players = new HashMap<>();
/*     */   
/*  22 */   private final Long2ObjectOpenHashMap<PooledHashSets.PooledObjectLinkedOpenHashSet<EntityPlayer>> playerMap = new Long2ObjectOpenHashMap(32, 0.5F);
/*     */   
/*     */   private int viewDistance;
/*  25 */   private final PooledHashSets<EntityPlayer> pooledHashSets = new PooledHashSets<>();
/*     */   
/*     */   public PooledHashSets.PooledObjectLinkedOpenHashSet<EntityPlayer> getPlayersInRange(ChunkCoordIntPair chunkPos) {
/*  28 */     return getPlayersInRange(chunkPos.x, chunkPos.z);
/*     */   }
/*     */   
/*     */   public PooledHashSets.PooledObjectLinkedOpenHashSet<EntityPlayer> getPlayersInRange(int chunkX, int chunkZ) {
/*  32 */     return (PooledHashSets.PooledObjectLinkedOpenHashSet<EntityPlayer>)this.playerMap.getOrDefault(ChunkCoordIntPair.pair(chunkX, chunkZ), EMPTY_SET);
/*     */   }
/*     */   
/*     */   public void update(List<EntityPlayer> currentPlayers, int newViewDistance) {
/*  36 */     AsyncCatcher.catchOp("Distance map update");
/*  37 */     ObjectLinkedOpenHashSet<EntityPlayer> gone = new ObjectLinkedOpenHashSet(this.players.keySet());
/*     */     
/*  39 */     int oldViewDistance = this.viewDistance;
/*  40 */     this.viewDistance = newViewDistance;
/*     */     
/*  42 */     for (EntityPlayer player : currentPlayers) {
/*  43 */       if (player.isSpectator() || !player.affectsSpawning) {
/*     */         continue;
/*     */       }
/*     */       
/*  47 */       gone.remove(player);
/*     */       
/*  49 */       SectionPosition newPosition = player.getPlayerMapSection();
/*  50 */       SectionPosition oldPosition = this.players.put(player, newPosition);
/*     */       
/*  52 */       if (oldPosition == null) {
/*  53 */         addNewPlayer(player, newPosition, newViewDistance); continue;
/*     */       } 
/*  55 */       updatePlayer(player, oldPosition, newPosition, oldViewDistance, newViewDistance);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  60 */     for (ObjectListIterator<EntityPlayer> objectListIterator = gone.iterator(); objectListIterator.hasNext(); ) { EntityPlayer player = objectListIterator.next();
/*  61 */       SectionPosition oldPosition = this.players.remove(player);
/*  62 */       if (oldPosition != null) {
/*  63 */         removePlayer(player, oldPosition, oldViewDistance);
/*     */       } }
/*     */   
/*     */   }
/*     */ 
/*     */   
/*     */   private void validatePlayer(EntityPlayer player, int viewDistance) {
/*  70 */     int entiesGot = 0;
/*  71 */     int expectedEntries = 2 * viewDistance + 1;
/*  72 */     expectedEntries *= expectedEntries;
/*     */     
/*  74 */     SectionPosition currPosition = player.getPlayerMapSection();
/*     */     
/*  76 */     int centerX = currPosition.getX();
/*  77 */     int centerZ = currPosition.getZ();
/*     */     
/*  79 */     for (ObjectIterator<Long2ObjectMap.Entry<PooledHashSets.PooledObjectLinkedOpenHashSet<EntityPlayer>>> objectIterator = this.playerMap.long2ObjectEntrySet().iterator(); objectIterator.hasNext(); ) { Long2ObjectMap.Entry<PooledHashSets.PooledObjectLinkedOpenHashSet<EntityPlayer>> entry = objectIterator.next();
/*  80 */       long key = entry.getLongKey();
/*  81 */       PooledHashSets.PooledObjectLinkedOpenHashSet<EntityPlayer> map = (PooledHashSets.PooledObjectLinkedOpenHashSet<EntityPlayer>)entry.getValue();
/*     */       
/*  83 */       if (map.referenceCount == 0) {
/*  84 */         throw new IllegalStateException("Invalid map");
/*     */       }
/*     */       
/*  87 */       if (map.set.contains(player)) {
/*  88 */         entiesGot++;
/*     */         
/*  90 */         int chunkX = ChunkCoordIntPair.getX(key);
/*  91 */         int chunkZ = ChunkCoordIntPair.getZ(key);
/*     */         
/*  93 */         int dist = Math.max(Math.abs(chunkX - centerX), Math.abs(chunkZ - centerZ));
/*     */         
/*  95 */         if (dist > viewDistance) {
/*  96 */           throw new IllegalStateException("Expected view distance " + viewDistance + ", got " + dist);
/*     */         }
/*     */       }  }
/*     */ 
/*     */     
/* 101 */     if (entiesGot != expectedEntries) {
/* 102 */       throw new IllegalStateException("Expected " + expectedEntries + ", got " + entiesGot);
/*     */     }
/*     */   }
/*     */   
/*     */   private void addPlayerTo(EntityPlayer player, int chunkX, int chunkZ) {
/* 107 */     this.playerMap.compute(ChunkCoordIntPair.pair(chunkX, chunkZ), (key, players) -> (players == null) ? player.cachedSingleMobDistanceMap : this.pooledHashSets.findMapWith(players, player));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void removePlayerFrom(EntityPlayer player, int chunkX, int chunkZ) {
/* 117 */     this.playerMap.compute(ChunkCoordIntPair.pair(chunkX, chunkZ), (keyInMap, players) -> this.pooledHashSets.findMapWithout(players, player));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void updatePlayer(EntityPlayer player, SectionPosition oldPosition, SectionPosition newPosition, int oldViewDistance, int newViewDistance) {
/* 123 */     int toX = newPosition.getX();
/* 124 */     int toZ = newPosition.getZ();
/* 125 */     int fromX = oldPosition.getX();
/* 126 */     int fromZ = oldPosition.getZ();
/*     */     
/* 128 */     int dx = toX - fromX;
/* 129 */     int dz = toZ - fromZ;
/*     */     
/* 131 */     int totalX = Math.abs(fromX - toX);
/* 132 */     int totalZ = Math.abs(fromZ - toZ);
/*     */     
/* 134 */     if (Math.max(totalX, totalZ) > 2 * oldViewDistance) {
/*     */       
/* 136 */       removePlayer(player, oldPosition, oldViewDistance);
/* 137 */       addNewPlayer(player, newPosition, newViewDistance);
/*     */ 
/*     */ 
/*     */       
/*     */       return;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 146 */     if (oldViewDistance == newViewDistance) {
/*     */ 
/*     */ 
/*     */       
/* 150 */       int up = 0x1 | dz >> 31;
/* 151 */       int right = 0x1 | dx >> 31;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 165 */       if (dx != 0) {
/*     */ 
/*     */         
/* 168 */         int maxX = toX + oldViewDistance * right + right;
/* 169 */         int minX = fromX + oldViewDistance * right + right;
/* 170 */         int maxZ = fromZ + oldViewDistance * up + up;
/* 171 */         int minZ = toZ - oldViewDistance * up;
/*     */         int currX;
/* 173 */         for (currX = minX; currX != maxX; currX += right) {
/* 174 */           int currZ; for (currZ = minZ; currZ != maxZ; currZ += up) {
/* 175 */             addPlayerTo(player, currX, currZ);
/*     */           }
/*     */         } 
/*     */       } 
/*     */       
/* 180 */       if (dz != 0) {
/*     */ 
/*     */         
/* 183 */         int maxX = toX + oldViewDistance * right + right;
/* 184 */         int minX = toX - oldViewDistance * right;
/* 185 */         int maxZ = toZ + oldViewDistance * up + up;
/* 186 */         int minZ = fromZ + oldViewDistance * up + up;
/*     */         int currX;
/* 188 */         for (currX = minX; currX != maxX; currX += right) {
/* 189 */           int currZ; for (currZ = minZ; currZ != maxZ; currZ += up) {
/* 190 */             addPlayerTo(player, currX, currZ);
/*     */           }
/*     */         } 
/*     */       } 
/*     */       
/* 195 */       if (dx != 0) {
/*     */ 
/*     */         
/* 198 */         int maxX = toX - oldViewDistance * right;
/* 199 */         int minX = fromX - oldViewDistance * right;
/* 200 */         int maxZ = fromZ + oldViewDistance * up + up;
/* 201 */         int minZ = toZ - oldViewDistance * up;
/*     */         int currX;
/* 203 */         for (currX = minX; currX != maxX; currX += right) {
/* 204 */           int currZ; for (currZ = minZ; currZ != maxZ; currZ += up) {
/* 205 */             removePlayerFrom(player, currX, currZ);
/*     */           }
/*     */         } 
/*     */       } 
/*     */       
/* 210 */       if (dz != 0) {
/*     */ 
/*     */         
/* 213 */         int maxX = fromX + oldViewDistance * right + right;
/* 214 */         int minX = fromX - oldViewDistance * right;
/* 215 */         int maxZ = toZ - oldViewDistance * up;
/* 216 */         int minZ = fromZ - oldViewDistance * up;
/*     */         int currX;
/* 218 */         for (currX = minX; currX != maxX; currX += right) {
/* 219 */           int currZ; for (currZ = minZ; currZ != maxZ; currZ += up) {
/* 220 */             removePlayerFrom(player, currX, currZ);
/*     */           }
/*     */         }
/*     */       
/*     */       } 
/*     */     } else {
/*     */       
/* 227 */       removePlayer(player, oldPosition, oldViewDistance);
/* 228 */       addNewPlayer(player, newPosition, newViewDistance);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void removePlayer(EntityPlayer player, SectionPosition position, int viewDistance) {
/* 233 */     int x = position.getX();
/* 234 */     int z = position.getZ();
/*     */     
/* 236 */     for (int xoff = -viewDistance; xoff <= viewDistance; xoff++) {
/* 237 */       for (int zoff = -viewDistance; zoff <= viewDistance; zoff++) {
/* 238 */         removePlayerFrom(player, x + xoff, z + zoff);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private void addNewPlayer(EntityPlayer player, SectionPosition position, int viewDistance) {
/* 244 */     int x = position.getX();
/* 245 */     int z = position.getZ();
/*     */     
/* 247 */     for (int xoff = -viewDistance; xoff <= viewDistance; xoff++) {
/* 248 */       for (int zoff = -viewDistance; zoff <= viewDistance; zoff++)
/* 249 */         addPlayerTo(player, x + xoff, z + zoff); 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\pape\\util\PlayerMobDistanceMap.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
/*     */ package com.destroystokyo.paper.util.misc;
/*     */ 
/*     */ import com.destroystokyo.paper.util.math.IntegerUtil;
/*     */ import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
/*     */ import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
/*     */ import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
/*     */ import it.unimi.dsi.fastutil.objects.Object2LongOpenHashMap;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectIterator;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.server.v1_16_R2.ChunkCoordIntPair;
/*     */ import net.minecraft.server.v1_16_R2.MCUtil;
/*     */ import net.minecraft.server.v1_16_R2.MinecraftServer;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AreaMap<E>
/*     */ {
/*  19 */   protected final Object2LongOpenHashMap<E> objectToLastCoordinate = new Object2LongOpenHashMap();
/*  20 */   protected final Object2IntOpenHashMap<E> objectToViewDistance = new Object2IntOpenHashMap(); protected final Long2ObjectOpenHashMap<PooledLinkedHashSets.PooledObjectLinkedOpenHashSet<E>> areaMap; protected final PooledLinkedHashSets<E> pooledHashSets; protected final ChangeCallback<E> addCallback; protected final ChangeCallback<E> removeCallback;
/*     */   protected final ChangeSourceCallback<E> changeSourceCallback;
/*     */   
/*  23 */   public AreaMap(PooledLinkedHashSets<E> pooledHashSets, ChangeCallback<E> addCallback, ChangeCallback<E> removeCallback, ChangeSourceCallback<E> changeSourceCallback) { this.objectToViewDistance.defaultReturnValue(-1);
/*  24 */     this.objectToLastCoordinate.defaultReturnValue(Long.MIN_VALUE);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  29 */     this.areaMap = new Long2ObjectOpenHashMap(1024, 0.7F);
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
/*  49 */     this.pooledHashSets = pooledHashSets;
/*  50 */     this.addCallback = addCallback;
/*  51 */     this.removeCallback = removeCallback;
/*  52 */     this.changeSourceCallback = changeSourceCallback; }
/*     */   
/*     */   public AreaMap() {
/*     */     this(new PooledLinkedHashSets<>());
/*     */   } @Nullable
/*  57 */   public final PooledLinkedHashSets.PooledObjectLinkedOpenHashSet<E> getObjectsInRange(long key) { return (PooledLinkedHashSets.PooledObjectLinkedOpenHashSet<E>)this.areaMap.get(key); }
/*     */   public AreaMap(PooledLinkedHashSets<E> pooledHashSets) { this(pooledHashSets, null, null); } public AreaMap(PooledLinkedHashSets<E> pooledHashSets, ChangeCallback<E> addCallback, ChangeCallback<E> removeCallback) {
/*     */     this(pooledHashSets, addCallback, removeCallback, null);
/*     */   } @Nullable
/*     */   public final PooledLinkedHashSets.PooledObjectLinkedOpenHashSet<E> getObjectsInRange(ChunkCoordIntPair chunkPos) {
/*  62 */     return (PooledLinkedHashSets.PooledObjectLinkedOpenHashSet<E>)this.areaMap.get(MCUtil.getCoordinateKey(chunkPos));
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public final PooledLinkedHashSets.PooledObjectLinkedOpenHashSet<E> getObjectsInRange(int chunkX, int chunkZ) {
/*  67 */     return (PooledLinkedHashSets.PooledObjectLinkedOpenHashSet<E>)this.areaMap.get(MCUtil.getCoordinateKey(chunkX, chunkZ));
/*     */   }
/*     */ 
/*     */   
/*     */   public final long getLastCoordinate(E object) {
/*  72 */     return this.objectToLastCoordinate.getOrDefault(object, Long.MIN_VALUE);
/*     */   }
/*     */ 
/*     */   
/*     */   public final int getLastViewDistance(E object) {
/*  77 */     return this.objectToViewDistance.getOrDefault(object, -1);
/*     */   }
/*     */ 
/*     */   
/*     */   public final int size() {
/*  82 */     return this.areaMap.size();
/*     */   }
/*     */   
/*     */   public final void addOrUpdate(E object, int chunkX, int chunkZ, int viewDistance) {
/*  86 */     int oldViewDistance = this.objectToViewDistance.put(object, viewDistance);
/*  87 */     long newPos = MCUtil.getCoordinateKey(chunkX, chunkZ);
/*  88 */     long oldPos = this.objectToLastCoordinate.put(object, newPos);
/*     */     
/*  90 */     if (oldViewDistance == -1) {
/*  91 */       addObject(object, chunkX, chunkZ, -2147483648, -2147483648, viewDistance);
/*  92 */       addObjectCallback(object, chunkX, chunkZ, viewDistance);
/*     */     } else {
/*  94 */       updateObject(object, oldPos, newPos, oldViewDistance, viewDistance);
/*  95 */       updateObjectCallback(object, oldPos, newPos, oldViewDistance, viewDistance);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public final boolean update(E object, int chunkX, int chunkZ, int viewDistance) {
/* 101 */     int oldViewDistance = this.objectToViewDistance.replace(object, viewDistance);
/* 102 */     if (oldViewDistance == -1) {
/* 103 */       return false;
/*     */     }
/* 105 */     long newPos = MCUtil.getCoordinateKey(chunkX, chunkZ);
/* 106 */     long oldPos = this.objectToLastCoordinate.put(object, newPos);
/* 107 */     updateObject(object, oldPos, newPos, oldViewDistance, viewDistance);
/* 108 */     updateObjectCallback(object, oldPos, newPos, oldViewDistance, viewDistance);
/*     */ 
/*     */     
/* 111 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void updateObjectCallback(E e, long oldPosition, long newPosition, int oldViewDistance, int newViewDistance) {
/* 116 */     if (newPosition != oldPosition && this.changeSourceCallback != null) {
/* 117 */       this.changeSourceCallback.accept(e, oldPosition, newPosition);
/*     */     }
/*     */   }
/*     */   
/*     */   public final boolean add(E object, int chunkX, int chunkZ, int viewDistance) {
/* 122 */     int oldViewDistance = this.objectToViewDistance.putIfAbsent(object, viewDistance);
/* 123 */     if (oldViewDistance != -1) {
/* 124 */       return false;
/*     */     }
/*     */     
/* 127 */     long newPos = MCUtil.getCoordinateKey(chunkX, chunkZ);
/* 128 */     this.objectToLastCoordinate.put(object, newPos);
/* 129 */     addObject(object, chunkX, chunkZ, -2147483648, -2147483648, viewDistance);
/* 130 */     addObjectCallback(object, chunkX, chunkZ, viewDistance);
/*     */ 
/*     */ 
/*     */     
/* 134 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void addObjectCallback(E object, int chunkX, int chunkZ, int viewDistance) {}
/*     */   
/*     */   public final boolean remove(E object) {
/* 141 */     long position = this.objectToLastCoordinate.removeLong(object);
/* 142 */     int viewDistance = this.objectToViewDistance.removeInt(object);
/*     */     
/* 144 */     if (viewDistance == -1) {
/* 145 */       return false;
/*     */     }
/*     */     
/* 148 */     int currentX = MCUtil.getCoordinateX(position);
/* 149 */     int currentZ = MCUtil.getCoordinateZ(position);
/*     */     
/* 151 */     removeObject(object, currentX, currentZ, currentX, currentZ, viewDistance);
/* 152 */     removeObjectCallback(object, currentX, currentZ, viewDistance);
/*     */     
/* 154 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void removeObjectCallback(E object, int chunkX, int chunkZ, int viewDistance) {}
/*     */ 
/*     */ 
/*     */   
/*     */   protected void validate(E object, int viewDistance) {
/* 164 */     int entiesGot = 0;
/* 165 */     int expectedEntries = 2 * viewDistance + 1;
/* 166 */     expectedEntries *= expectedEntries;
/* 167 */     if (viewDistance < 0) {
/* 168 */       expectedEntries = 0;
/*     */     }
/*     */     
/* 171 */     long currPosition = this.objectToLastCoordinate.getLong(object);
/*     */     
/* 173 */     int centerX = MCUtil.getCoordinateX(currPosition);
/* 174 */     int centerZ = MCUtil.getCoordinateZ(currPosition);
/*     */     
/* 176 */     ObjectIterator<Long2ObjectMap.Entry<PooledLinkedHashSets.PooledObjectLinkedOpenHashSet<E>>> objectIterator = this.areaMap.long2ObjectEntrySet().fastIterator();
/* 177 */     while (objectIterator.hasNext()) {
/*     */       
/* 179 */       Long2ObjectMap.Entry<PooledLinkedHashSets.PooledObjectLinkedOpenHashSet<E>> entry = objectIterator.next();
/* 180 */       long key = entry.getLongKey();
/* 181 */       PooledLinkedHashSets.PooledObjectLinkedOpenHashSet<E> map = (PooledLinkedHashSets.PooledObjectLinkedOpenHashSet<E>)entry.getValue();
/*     */       
/* 183 */       if (map.referenceCount == 0) {
/* 184 */         throw new IllegalStateException("Invalid map");
/*     */       }
/*     */       
/* 187 */       if (map.contains(object)) {
/* 188 */         entiesGot++;
/*     */         
/* 190 */         int chunkX = MCUtil.getCoordinateX(key);
/* 191 */         int chunkZ = MCUtil.getCoordinateZ(key);
/*     */         
/* 193 */         int dist = Math.max(IntegerUtil.branchlessAbs(chunkX - centerX), IntegerUtil.branchlessAbs(chunkZ - centerZ));
/*     */         
/* 195 */         if (dist > viewDistance) {
/* 196 */           throw new IllegalStateException("Expected view distance " + viewDistance + ", got " + dist);
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 201 */     if (entiesGot != expectedEntries) {
/* 202 */       throw new IllegalStateException("Expected " + expectedEntries + ", got " + entiesGot);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private void addObjectTo(E object, int chunkX, int chunkZ, int currChunkX, int currChunkZ, int prevChunkX, int prevChunkZ) {
/* 208 */     long key = MCUtil.getCoordinateKey(chunkX, chunkZ);
/*     */     
/* 210 */     PooledLinkedHashSets.PooledObjectLinkedOpenHashSet<E> empty = getEmptySetFor(object);
/* 211 */     PooledLinkedHashSets.PooledObjectLinkedOpenHashSet<E> current = (PooledLinkedHashSets.PooledObjectLinkedOpenHashSet<E>)this.areaMap.putIfAbsent(key, empty);
/*     */     
/* 213 */     if (current != null) {
/* 214 */       PooledLinkedHashSets.PooledObjectLinkedOpenHashSet<E> next = this.pooledHashSets.findMapWith(current, object);
/* 215 */       if (next == current) {
/* 216 */         throw new IllegalStateException("Expected different map: got " + next.toString());
/*     */       }
/* 218 */       this.areaMap.put(key, next);
/*     */       
/* 220 */       current = next;
/*     */     } else {
/*     */       
/* 223 */       current = empty;
/*     */     } 
/*     */     
/* 226 */     if (this.addCallback != null) {
/*     */       try {
/* 228 */         this.addCallback.accept(object, chunkX, chunkZ, currChunkX, currChunkZ, prevChunkX, prevChunkZ, current);
/* 229 */       } catch (Throwable ex) {
/* 230 */         if (ex instanceof ThreadDeath) {
/* 231 */           throw (ThreadDeath)ex;
/*     */         }
/* 233 */         MinecraftServer.LOGGER.error("Add callback for map threw exception ", ex);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private void removeObjectFrom(E object, int chunkX, int chunkZ, int currChunkX, int currChunkZ, int prevChunkX, int prevChunkZ) {
/* 240 */     long key = MCUtil.getCoordinateKey(chunkX, chunkZ);
/*     */     
/* 242 */     PooledLinkedHashSets.PooledObjectLinkedOpenHashSet<E> current = (PooledLinkedHashSets.PooledObjectLinkedOpenHashSet<E>)this.areaMap.get(key);
/*     */     
/* 244 */     if (current == null) {
/* 245 */       throw new IllegalStateException("Current map may not be null for " + object + ", (" + chunkX + "," + chunkZ + ")");
/*     */     }
/*     */     
/* 248 */     PooledLinkedHashSets.PooledObjectLinkedOpenHashSet<E> next = this.pooledHashSets.findMapWithout(current, object);
/*     */     
/* 250 */     if (next == current) {
/* 251 */       throw new IllegalStateException("Current map [" + next.toString() + "] should have contained " + object + ", (" + chunkX + "," + chunkZ + ")");
/*     */     }
/*     */     
/* 254 */     if (next != null) {
/* 255 */       this.areaMap.put(key, next);
/*     */     } else {
/* 257 */       this.areaMap.remove(key);
/*     */     } 
/*     */     
/* 260 */     if (this.removeCallback != null) {
/*     */       try {
/* 262 */         this.removeCallback.accept(object, chunkX, chunkZ, currChunkX, currChunkZ, prevChunkX, prevChunkZ, next);
/* 263 */       } catch (Throwable ex) {
/* 264 */         if (ex instanceof ThreadDeath) {
/* 265 */           throw (ThreadDeath)ex;
/*     */         }
/* 267 */         MinecraftServer.LOGGER.error("Remove callback for map threw exception ", ex);
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   private void addObject(E object, int chunkX, int chunkZ, int prevChunkX, int prevChunkZ, int viewDistance) {
/* 273 */     int maxX = chunkX + viewDistance;
/* 274 */     int maxZ = chunkZ + viewDistance;
/* 275 */     int minX = chunkX - viewDistance;
/* 276 */     int minZ = chunkZ - viewDistance;
/* 277 */     for (int x = minX; x <= maxX; x++) {
/* 278 */       for (int z = minZ; z <= maxZ; z++) {
/* 279 */         addObjectTo(object, x, z, chunkX, chunkZ, prevChunkX, prevChunkZ);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private void removeObject(E object, int chunkX, int chunkZ, int currentChunkX, int currentChunkZ, int viewDistance) {
/* 285 */     int maxX = chunkX + viewDistance;
/* 286 */     int maxZ = chunkZ + viewDistance;
/* 287 */     int minX = chunkX - viewDistance;
/* 288 */     int minZ = chunkZ - viewDistance;
/* 289 */     for (int x = minX; x <= maxX; x++) {
/* 290 */       for (int z = minZ; z <= maxZ; z++) {
/* 291 */         removeObjectFrom(object, x, z, currentChunkX, currentChunkZ, chunkX, chunkZ);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected static int sign(int val) {
/* 298 */     return 0x1 | val >> 31;
/*     */   }
/*     */   
/*     */   private void updateObject(E object, long oldPosition, long newPosition, int oldViewDistance, int newViewDistance) {
/* 302 */     int toX = MCUtil.getCoordinateX(newPosition);
/* 303 */     int toZ = MCUtil.getCoordinateZ(newPosition);
/* 304 */     int fromX = MCUtil.getCoordinateX(oldPosition);
/* 305 */     int fromZ = MCUtil.getCoordinateZ(oldPosition);
/*     */     
/* 307 */     int dx = toX - fromX;
/* 308 */     int dz = toZ - fromZ;
/*     */     
/* 310 */     int totalX = IntegerUtil.branchlessAbs(fromX - toX);
/* 311 */     int totalZ = IntegerUtil.branchlessAbs(fromZ - toZ);
/*     */     
/* 313 */     if (Math.max(totalX, totalZ) > 2 * Math.max(newViewDistance, oldViewDistance)) {
/*     */       
/* 315 */       removeObject(object, fromX, fromZ, fromX, fromZ, oldViewDistance);
/* 316 */       addObject(object, toX, toZ, fromX, fromZ, newViewDistance);
/*     */       
/*     */       return;
/*     */     } 
/* 320 */     if (oldViewDistance != newViewDistance) {
/*     */ 
/*     */       
/* 323 */       int oldMinX = fromX - oldViewDistance;
/* 324 */       int oldMinZ = fromZ - oldViewDistance;
/* 325 */       int oldMaxX = fromX + oldViewDistance;
/* 326 */       int oldMaxZ = fromZ + oldViewDistance;
/* 327 */       for (int currX = oldMinX; currX <= oldMaxX; currX++) {
/* 328 */         for (int currZ = oldMinZ; currZ <= oldMaxZ; currZ++) {
/*     */ 
/*     */           
/* 331 */           if (Math.max(IntegerUtil.branchlessAbs(currX - toX), IntegerUtil.branchlessAbs(currZ - toZ)) > newViewDistance) {
/* 332 */             removeObjectFrom(object, currX, currZ, toX, toZ, fromX, fromZ);
/*     */           }
/*     */         } 
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 339 */       int newMinX = toX - newViewDistance;
/* 340 */       int newMinZ = toZ - newViewDistance;
/* 341 */       int newMaxX = toX + newViewDistance;
/* 342 */       int newMaxZ = toZ + newViewDistance;
/* 343 */       for (int i = newMinX; i <= newMaxX; i++) {
/* 344 */         for (int currZ = newMinZ; currZ <= newMaxZ; currZ++) {
/*     */ 
/*     */           
/* 347 */           if (Math.max(IntegerUtil.branchlessAbs(i - fromX), IntegerUtil.branchlessAbs(currZ - fromZ)) > oldViewDistance) {
/* 348 */             addObjectTo(object, i, currZ, toX, toZ, fromX, fromZ);
/*     */           }
/*     */         } 
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       return;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 364 */     int up = sign(dz);
/* 365 */     int right = sign(dx);
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
/* 379 */     if (dx != 0) {
/*     */ 
/*     */       
/* 382 */       int maxX = toX + oldViewDistance * right + right;
/* 383 */       int minX = fromX + oldViewDistance * right + right;
/* 384 */       int maxZ = fromZ + oldViewDistance * up + up;
/* 385 */       int minZ = toZ - oldViewDistance * up;
/*     */       int currX;
/* 387 */       for (currX = minX; currX != maxX; currX += right) {
/* 388 */         int currZ; for (currZ = minZ; currZ != maxZ; currZ += up) {
/* 389 */           addObjectTo(object, currX, currZ, toX, toZ, fromX, fromZ);
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 394 */     if (dz != 0) {
/*     */ 
/*     */       
/* 397 */       int maxX = toX + oldViewDistance * right + right;
/* 398 */       int minX = toX - oldViewDistance * right;
/* 399 */       int maxZ = toZ + oldViewDistance * up + up;
/* 400 */       int minZ = fromZ + oldViewDistance * up + up;
/*     */       int currX;
/* 402 */       for (currX = minX; currX != maxX; currX += right) {
/* 403 */         int currZ; for (currZ = minZ; currZ != maxZ; currZ += up) {
/* 404 */           addObjectTo(object, currX, currZ, toX, toZ, fromX, fromZ);
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 409 */     if (dx != 0) {
/*     */ 
/*     */       
/* 412 */       int maxX = toX - oldViewDistance * right;
/* 413 */       int minX = fromX - oldViewDistance * right;
/* 414 */       int maxZ = fromZ + oldViewDistance * up + up;
/* 415 */       int minZ = toZ - oldViewDistance * up;
/*     */       int currX;
/* 417 */       for (currX = minX; currX != maxX; currX += right) {
/* 418 */         int currZ; for (currZ = minZ; currZ != maxZ; currZ += up) {
/* 419 */           removeObjectFrom(object, currX, currZ, toX, toZ, fromX, fromZ);
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 424 */     if (dz != 0) {
/*     */ 
/*     */       
/* 427 */       int maxX = fromX + oldViewDistance * right + right;
/* 428 */       int minX = fromX - oldViewDistance * right;
/* 429 */       int maxZ = toZ - oldViewDistance * up;
/* 430 */       int minZ = fromZ - oldViewDistance * up;
/*     */       int currX;
/* 432 */       for (currX = minX; currX != maxX; currX += right) {
/* 433 */         int currZ; for (currZ = minZ; currZ != maxZ; currZ += up)
/* 434 */           removeObjectFrom(object, currX, currZ, toX, toZ, fromX, fromZ); 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected abstract PooledLinkedHashSets.PooledObjectLinkedOpenHashSet<E> getEmptySetFor(E paramE);
/*     */   
/*     */   @FunctionalInterface
/*     */   public static interface ChangeCallback<E> {
/*     */     void accept(E param1E, int param1Int1, int param1Int2, int param1Int3, int param1Int4, int param1Int5, int param1Int6, PooledLinkedHashSets.PooledObjectLinkedOpenHashSet<E> param1PooledObjectLinkedOpenHashSet);
/*     */   }
/*     */   
/*     */   @FunctionalInterface
/*     */   public static interface ChangeSourceCallback<E> {
/*     */     void accept(E param1E, long param1Long1, long param1Long2);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\pape\\util\misc\AreaMap.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
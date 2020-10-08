/*     */ package com.destroystokyo.paper.util.misc;
/*     */ 
/*     */ import com.destroystokyo.paper.util.math.IntegerUtil;
/*     */ import it.unimi.dsi.fastutil.longs.Long2IntOpenHashMap;
/*     */ import net.minecraft.server.v1_16_R2.ChunkCoordIntPair;
/*     */ import net.minecraft.server.v1_16_R2.MCUtil;
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class DistanceTrackingAreaMap<E>
/*     */   extends AreaMap<E>
/*     */ {
/*  13 */   protected final Long2IntOpenHashMap chunkToNearestDistance = new Long2IntOpenHashMap(1024, 0.7F);
/*     */ 
/*     */   
/*     */   protected final DistanceChangeCallback<E> distanceChangeCallback;
/*     */ 
/*     */ 
/*     */   
/*     */   public DistanceTrackingAreaMap() {
/*  21 */     this(new PooledLinkedHashSets<>());
/*     */   }
/*     */ 
/*     */   
/*     */   public DistanceTrackingAreaMap(PooledLinkedHashSets<E> pooledHashSets) {
/*  26 */     this(pooledHashSets, (AreaMap.ChangeCallback<E>)null, (AreaMap.ChangeCallback<E>)null, (DistanceChangeCallback<E>)null);
/*     */   }
/*     */ 
/*     */   
/*     */   public DistanceTrackingAreaMap(PooledLinkedHashSets<E> pooledHashSets, AreaMap.ChangeCallback<E> addCallback, AreaMap.ChangeCallback<E> removeCallback, DistanceChangeCallback<E> distanceChangeCallback) {
/*  31 */     super(pooledHashSets, addCallback, removeCallback); this.chunkToNearestDistance.defaultReturnValue(-1);
/*  32 */     this.distanceChangeCallback = distanceChangeCallback;
/*     */   }
/*     */ 
/*     */   
/*     */   public final int getNearestObjectDistance(long key) {
/*  37 */     return this.chunkToNearestDistance.get(key);
/*     */   }
/*     */ 
/*     */   
/*     */   public final int getNearestObjectDistance(ChunkCoordIntPair chunkPos) {
/*  42 */     return this.chunkToNearestDistance.get(MCUtil.getCoordinateKey(chunkPos));
/*     */   }
/*     */ 
/*     */   
/*     */   public final int getNearestObjectDistance(int chunkX, int chunkZ) {
/*  47 */     return this.chunkToNearestDistance.get(MCUtil.getCoordinateKey(chunkX, chunkZ));
/*     */   }
/*     */   
/*     */   protected final void recalculateDistance(int chunkX, int chunkZ) {
/*  51 */     long key = MCUtil.getCoordinateKey(chunkX, chunkZ);
/*  52 */     PooledLinkedHashSets.PooledObjectLinkedOpenHashSet<E> state = (PooledLinkedHashSets.PooledObjectLinkedOpenHashSet<E>)this.areaMap.get(key);
/*  53 */     if (state == null) {
/*  54 */       int j = this.chunkToNearestDistance.remove(key);
/*     */       
/*  56 */       if (j == -1) {
/*     */         return;
/*     */       }
/*     */       
/*  60 */       if (this.distanceChangeCallback != null) {
/*  61 */         this.distanceChangeCallback.accept(chunkX, chunkZ, j, -1, null);
/*     */       }
/*     */       
/*     */       return;
/*     */     } 
/*  66 */     int newDistance = Integer.MAX_VALUE;
/*     */     
/*  68 */     Object[] rawData = (Object[])state.getBackingSet();
/*  69 */     for (int i = 0, len = rawData.length; i < len; i++) {
/*  70 */       Object raw = rawData[i];
/*     */       
/*  72 */       if (raw != null) {
/*     */ 
/*     */ 
/*     */         
/*  76 */         E object = (E)raw;
/*  77 */         long location = this.objectToLastCoordinate.getLong(object);
/*     */         
/*  79 */         int distance = Math.max(IntegerUtil.branchlessAbs(chunkX - MCUtil.getCoordinateX(location)), IntegerUtil.branchlessAbs(chunkZ - MCUtil.getCoordinateZ(location)));
/*     */         
/*  81 */         if (distance < newDistance) {
/*  82 */           newDistance = distance;
/*     */         }
/*     */       } 
/*     */     } 
/*  86 */     int oldDistance = this.chunkToNearestDistance.put(key, newDistance);
/*     */     
/*  88 */     if (oldDistance != newDistance && 
/*  89 */       this.distanceChangeCallback != null) {
/*  90 */       this.distanceChangeCallback.accept(chunkX, chunkZ, oldDistance, newDistance, state);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void addObjectCallback(E object, int chunkX, int chunkZ, int viewDistance) {
/*  97 */     int maxX = chunkX + viewDistance;
/*  98 */     int maxZ = chunkZ + viewDistance;
/*  99 */     int minX = chunkX - viewDistance;
/* 100 */     int minZ = chunkZ - viewDistance;
/* 101 */     for (int x = minX; x <= maxX; x++) {
/* 102 */       for (int z = minZ; z <= maxZ; z++) {
/* 103 */         recalculateDistance(x, z);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void removeObjectCallback(E object, int chunkX, int chunkZ, int viewDistance) {
/* 110 */     int maxX = chunkX + viewDistance;
/* 111 */     int maxZ = chunkZ + viewDistance;
/* 112 */     int minX = chunkX - viewDistance;
/* 113 */     int minZ = chunkZ - viewDistance;
/* 114 */     for (int x = minX; x <= maxX; x++) {
/* 115 */       for (int z = minZ; z <= maxZ; z++) {
/* 116 */         recalculateDistance(x, z);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void updateObjectCallback(E object, long oldPosition, long newPosition, int oldViewDistance, int newViewDistance) {
/* 123 */     if (oldPosition == newPosition && newViewDistance == oldViewDistance) {
/*     */       return;
/*     */     }
/*     */     
/* 127 */     int toX = MCUtil.getCoordinateX(newPosition);
/* 128 */     int toZ = MCUtil.getCoordinateZ(newPosition);
/* 129 */     int fromX = MCUtil.getCoordinateX(oldPosition);
/* 130 */     int fromZ = MCUtil.getCoordinateZ(oldPosition);
/*     */     
/* 132 */     int totalX = IntegerUtil.branchlessAbs(fromX - toX);
/* 133 */     int totalZ = IntegerUtil.branchlessAbs(fromZ - toZ);
/*     */     
/* 135 */     if (Math.max(totalX, totalZ) > 2 * Math.max(newViewDistance, oldViewDistance)) {
/*     */       
/* 137 */       removeObjectCallback(object, fromX, fromZ, oldViewDistance);
/* 138 */       addObjectCallback(object, toX, toZ, newViewDistance);
/*     */       
/*     */       return;
/*     */     } 
/* 142 */     int minX = Math.min(fromX - oldViewDistance, toX - newViewDistance);
/* 143 */     int maxX = Math.max(fromX + oldViewDistance, toX + newViewDistance);
/* 144 */     int minZ = Math.min(fromZ - oldViewDistance, toZ - newViewDistance);
/* 145 */     int maxZ = Math.max(fromZ + oldViewDistance, toZ + newViewDistance);
/*     */     
/* 147 */     for (int x = minX; x <= maxX; x++) {
/* 148 */       for (int z = minZ; z <= maxZ; z++) {
/* 149 */         int distXOld = IntegerUtil.branchlessAbs(x - fromX);
/* 150 */         int distZOld = IntegerUtil.branchlessAbs(z - fromZ);
/*     */         
/* 152 */         if (Math.max(distXOld, distZOld) <= oldViewDistance) {
/* 153 */           recalculateDistance(x, z);
/*     */         }
/*     */         else {
/*     */           
/* 157 */           int distXNew = IntegerUtil.branchlessAbs(x - toX);
/* 158 */           int distZNew = IntegerUtil.branchlessAbs(z - toZ);
/*     */           
/* 160 */           if (Math.max(distXNew, distZNew) <= newViewDistance)
/* 161 */             recalculateDistance(x, z); 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   @FunctionalInterface
/*     */   public static interface DistanceChangeCallback<E> {
/*     */     void accept(int param1Int1, int param1Int2, int param1Int3, int param1Int4, PooledLinkedHashSets.PooledObjectLinkedOpenHashSet<E> param1PooledObjectLinkedOpenHashSet);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\pape\\util\misc\DistanceTrackingAreaMap.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
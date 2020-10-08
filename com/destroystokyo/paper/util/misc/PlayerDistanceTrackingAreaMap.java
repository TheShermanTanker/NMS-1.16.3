/*    */ package com.destroystokyo.paper.util.misc;
/*    */ 
/*    */ import net.minecraft.server.v1_16_R2.EntityPlayer;
/*    */ 
/*    */ 
/*    */ public class PlayerDistanceTrackingAreaMap
/*    */   extends DistanceTrackingAreaMap<EntityPlayer>
/*    */ {
/*    */   public PlayerDistanceTrackingAreaMap() {}
/*    */   
/*    */   public PlayerDistanceTrackingAreaMap(PooledLinkedHashSets<EntityPlayer> pooledHashSets) {
/* 12 */     super(pooledHashSets);
/*    */   }
/*    */ 
/*    */   
/*    */   public PlayerDistanceTrackingAreaMap(PooledLinkedHashSets<EntityPlayer> pooledHashSets, AreaMap.ChangeCallback<EntityPlayer> addCallback, AreaMap.ChangeCallback<EntityPlayer> removeCallback, DistanceTrackingAreaMap.DistanceChangeCallback<EntityPlayer> distanceChangeCallback) {
/* 17 */     super(pooledHashSets, addCallback, removeCallback, distanceChangeCallback);
/*    */   }
/*    */ 
/*    */   
/*    */   protected PooledLinkedHashSets.PooledObjectLinkedOpenHashSet<EntityPlayer> getEmptySetFor(EntityPlayer player) {
/* 22 */     return player.cachedSingleHashSet;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\pape\\util\misc\PlayerDistanceTrackingAreaMap.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
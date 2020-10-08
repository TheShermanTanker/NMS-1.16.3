/*    */ package com.destroystokyo.paper.util.misc;
/*    */ 
/*    */ import net.minecraft.server.v1_16_R2.EntityPlayer;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class PlayerAreaMap
/*    */   extends AreaMap<EntityPlayer>
/*    */ {
/*    */   public PlayerAreaMap() {}
/*    */   
/*    */   public PlayerAreaMap(PooledLinkedHashSets<EntityPlayer> pooledHashSets) {
/* 15 */     super(pooledHashSets);
/*    */   }
/*    */ 
/*    */   
/*    */   public PlayerAreaMap(PooledLinkedHashSets<EntityPlayer> pooledHashSets, AreaMap.ChangeCallback<EntityPlayer> addCallback, AreaMap.ChangeCallback<EntityPlayer> removeCallback) {
/* 20 */     this(pooledHashSets, addCallback, removeCallback, null);
/*    */   }
/*    */ 
/*    */   
/*    */   public PlayerAreaMap(PooledLinkedHashSets<EntityPlayer> pooledHashSets, AreaMap.ChangeCallback<EntityPlayer> addCallback, AreaMap.ChangeCallback<EntityPlayer> removeCallback, AreaMap.ChangeSourceCallback<EntityPlayer> changeSourceCallback) {
/* 25 */     super(pooledHashSets, addCallback, removeCallback, changeSourceCallback);
/*    */   }
/*    */ 
/*    */   
/*    */   protected PooledLinkedHashSets.PooledObjectLinkedOpenHashSet<EntityPlayer> getEmptySetFor(EntityPlayer player) {
/* 30 */     return player.cachedSingleHashSet;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\pape\\util\misc\PlayerAreaMap.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
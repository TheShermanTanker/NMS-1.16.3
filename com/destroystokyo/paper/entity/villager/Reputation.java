/*    */ package com.destroystokyo.paper.entity.villager;
/*    */ 
/*    */ import com.google.common.base.Preconditions;
/*    */ import java.util.Map;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class Reputation
/*    */ {
/* 11 */   private static final ReputationType[] REPUTATION_TYPES = ReputationType.values();
/*    */   @NotNull
/*    */   private final int[] reputation;
/*    */   
/*    */   public Reputation() {
/* 16 */     this(new int[REPUTATION_TYPES.length]);
/*    */   }
/*    */ 
/*    */   
/*    */   Reputation(@NotNull int[] reputation) {
/* 21 */     this.reputation = reputation;
/*    */   }
/*    */   
/*    */   public Reputation(@NotNull Map<ReputationType, Integer> reputation) {
/* 25 */     this();
/* 26 */     Preconditions.checkNotNull(reputation, "reputation cannot be null");
/*    */     
/* 28 */     for (Map.Entry<ReputationType, Integer> entry : reputation.entrySet()) {
/* 29 */       setReputation(entry.getKey(), ((Integer)entry.getValue()).intValue());
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getReputation(@NotNull ReputationType type) {
/* 40 */     Preconditions.checkNotNull(type, "the reputation type cannot be null");
/* 41 */     return this.reputation[type.ordinal()];
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setReputation(@NotNull ReputationType type, int value) {
/* 51 */     Preconditions.checkNotNull(type, "the reputation type cannot be null");
/* 52 */     this.reputation[type.ordinal()] = value;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\entity\villager\Reputation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
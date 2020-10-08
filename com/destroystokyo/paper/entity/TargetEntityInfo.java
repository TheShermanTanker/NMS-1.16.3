/*    */ package com.destroystokyo.paper.entity;
/*    */ 
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.util.Vector;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TargetEntityInfo
/*    */ {
/*    */   private final Entity entity;
/*    */   private final Vector hitVec;
/*    */   
/*    */   public TargetEntityInfo(@NotNull Entity entity, @NotNull Vector hitVec) {
/* 15 */     this.entity = entity;
/* 16 */     this.hitVec = hitVec;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public Entity getEntity() {
/* 26 */     return this.entity;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public Vector getHitVector() {
/* 36 */     return this.hitVec;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\entity\TargetEntityInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
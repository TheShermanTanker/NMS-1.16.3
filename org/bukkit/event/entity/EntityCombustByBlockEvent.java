/*    */ package org.bukkit.event.entity;
/*    */ 
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ 
/*    */ public class EntityCombustByBlockEvent
/*    */   extends EntityCombustEvent
/*    */ {
/*    */   private final Block combuster;
/*    */   
/*    */   public EntityCombustByBlockEvent(@Nullable Block combuster, @NotNull Entity combustee, int duration) {
/* 15 */     super(combustee, duration);
/* 16 */     this.combuster = combuster;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public Block getCombuster() {
/* 28 */     return this.combuster;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\entity\EntityCombustByBlockEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
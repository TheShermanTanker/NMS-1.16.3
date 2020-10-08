/*    */ package org.bukkit.event.entity;
/*    */ 
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ public class EntityCombustByEntityEvent
/*    */   extends EntityCombustEvent
/*    */ {
/*    */   private final Entity combuster;
/*    */   
/*    */   public EntityCombustByEntityEvent(@NotNull Entity combuster, @NotNull Entity combustee, int duration) {
/* 13 */     super(combustee, duration);
/* 14 */     this.combuster = combuster;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public Entity getCombuster() {
/* 24 */     return this.combuster;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\entity\EntityCombustByEntityEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
/*    */ package org.bukkit.event.entity;
/*    */ 
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EntityBreakDoorEvent
/*    */   extends EntityChangeBlockEvent
/*    */ {
/*    */   public EntityBreakDoorEvent(@NotNull LivingEntity entity, @NotNull Block targetBlock) {
/* 16 */     super((Entity)entity, targetBlock, Material.AIR.createBlockData());
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public LivingEntity getEntity() {
/* 22 */     return (LivingEntity)this.entity;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\entity\EntityBreakDoorEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
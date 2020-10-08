/*    */ package org.bukkit.event.block;
/*    */ 
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.block.BlockState;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EntityBlockFormEvent
/*    */   extends BlockFormEvent
/*    */ {
/*    */   private final Entity entity;
/*    */   
/*    */   public EntityBlockFormEvent(@NotNull Entity entity, @NotNull Block block, @NotNull BlockState blockstate) {
/* 21 */     super(block, blockstate);
/*    */     
/* 23 */     this.entity = entity;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public Entity getEntity() {
/* 33 */     return this.entity;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\block\EntityBlockFormEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
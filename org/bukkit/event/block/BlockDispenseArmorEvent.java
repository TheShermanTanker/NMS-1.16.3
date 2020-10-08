/*    */ package org.bukkit.event.block;
/*    */ 
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.entity.LivingEntity;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.bukkit.util.Vector;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockDispenseArmorEvent
/*    */   extends BlockDispenseEvent
/*    */ {
/*    */   private final LivingEntity target;
/*    */   
/*    */   public BlockDispenseArmorEvent(@NotNull Block block, @NotNull ItemStack dispensed, @NotNull LivingEntity target) {
/* 21 */     super(block, dispensed, new Vector(0, 0, 0));
/* 22 */     this.target = target;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public LivingEntity getTargetEntity() {
/* 32 */     return this.target;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\block\BlockDispenseArmorEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
/*    */ package org.bukkit.event.inventory;
/*    */ 
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.event.block.BlockCookEvent;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FurnaceSmeltEvent
/*    */   extends BlockCookEvent
/*    */ {
/*    */   public FurnaceSmeltEvent(@NotNull Block furnace, @NotNull ItemStack source, @NotNull ItemStack result) {
/* 14 */     super(furnace, source, result);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\inventory\FurnaceSmeltEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
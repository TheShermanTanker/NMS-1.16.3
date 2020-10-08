/*    */ package com.destroystokyo.paper.event.inventory;
/*    */ 
/*    */ import org.bukkit.inventory.GrindstoneInventory;
/*    */ import org.bukkit.inventory.Inventory;
/*    */ import org.bukkit.inventory.InventoryView;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Deprecated
/*    */ public class PrepareGrindstoneEvent
/*    */   extends PrepareResultEvent
/*    */ {
/*    */   public PrepareGrindstoneEvent(@NotNull InventoryView inventory, @Nullable ItemStack result) {
/* 19 */     super(inventory, result);
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public GrindstoneInventory getInventory() {
/* 25 */     return (GrindstoneInventory)super.getInventory();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\event\inventory\PrepareGrindstoneEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
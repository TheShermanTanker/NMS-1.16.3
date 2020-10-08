/*    */ package org.bukkit.craftbukkit.v1_16_R2.inventory;
/*    */ 
/*    */ import net.minecraft.server.v1_16_R2.IInventory;
/*    */ import org.bukkit.block.Lectern;
/*    */ import org.bukkit.inventory.InventoryHolder;
/*    */ import org.bukkit.inventory.LecternInventory;
/*    */ 
/*    */ public class CraftInventoryLectern extends CraftInventory implements LecternInventory {
/*    */   public CraftInventoryLectern(IInventory inventory) {
/* 10 */     super(inventory);
/*    */   }
/*    */ 
/*    */   
/*    */   public Lectern getHolder() {
/* 15 */     return (Lectern)this.inventory.getOwner();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\inventory\CraftInventoryLectern.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
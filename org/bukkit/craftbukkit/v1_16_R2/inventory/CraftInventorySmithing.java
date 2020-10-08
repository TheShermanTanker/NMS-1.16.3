/*    */ package org.bukkit.craftbukkit.v1_16_R2.inventory;
/*    */ 
/*    */ import net.minecraft.server.v1_16_R2.IInventory;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.inventory.SmithingInventory;
/*    */ 
/*    */ public class CraftInventorySmithing
/*    */   extends CraftResultInventory implements SmithingInventory {
/*    */   private final Location location;
/*    */   
/*    */   public CraftInventorySmithing(Location location, IInventory inventory, IInventory resultInventory) {
/* 12 */     super(inventory, resultInventory);
/* 13 */     this.location = location;
/*    */   }
/*    */ 
/*    */   
/*    */   public Location getLocation() {
/* 18 */     return this.location;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\inventory\CraftInventorySmithing.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
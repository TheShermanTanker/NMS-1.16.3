/*    */ package org.bukkit.craftbukkit.v1_16_R2.inventory;
/*    */ 
/*    */ import com.google.common.base.Preconditions;
/*    */ import net.minecraft.server.v1_16_R2.ContainerAnvil;
/*    */ import net.minecraft.server.v1_16_R2.IInventory;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.inventory.AnvilInventory;
/*    */ 
/*    */ public class CraftInventoryAnvil
/*    */   extends CraftResultInventory implements AnvilInventory {
/*    */   private final Location location;
/*    */   private final ContainerAnvil container;
/*    */   
/*    */   public CraftInventoryAnvil(Location location, IInventory inventory, IInventory resultInventory, ContainerAnvil container) {
/* 15 */     super(inventory, resultInventory);
/* 16 */     this.location = location;
/* 17 */     this.container = container;
/*    */   }
/*    */ 
/*    */   
/*    */   public Location getLocation() {
/* 22 */     return this.location;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getRenameText() {
/* 27 */     return this.container.renameText;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getRepairCost() {
/* 32 */     return this.container.levelCost.get();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setRepairCost(int i) {
/* 37 */     this.container.levelCost.set(i);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getMaximumRepairCost() {
/* 42 */     return this.container.maximumRepairCost;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setMaximumRepairCost(int levels) {
/* 47 */     Preconditions.checkArgument((levels >= 0), "Maximum repair cost must be positive (or 0)");
/* 48 */     this.container.maximumRepairCost = levels;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\inventory\CraftInventoryAnvil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
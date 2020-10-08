/*    */ package org.bukkit.craftbukkit.v1_16_R2.entity;
/*    */ 
/*    */ import com.destroystokyo.paper.loottable.PaperLootableEntityInventory;
/*    */ import net.minecraft.server.v1_16_R2.EntityMinecartAbstract;
/*    */ import net.minecraft.server.v1_16_R2.EntityMinecartHopper;
/*    */ import net.minecraft.server.v1_16_R2.IInventory;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.CraftServer;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftInventory;
/*    */ import org.bukkit.entity.EntityType;
/*    */ import org.bukkit.entity.minecart.HopperMinecart;
/*    */ import org.bukkit.inventory.Inventory;
/*    */ 
/*    */ public final class CraftMinecartHopper extends CraftMinecartContainer implements HopperMinecart, PaperLootableEntityInventory {
/*    */   public CraftMinecartHopper(CraftServer server, EntityMinecartHopper entity) {
/* 15 */     super(server, (EntityMinecartAbstract)entity);
/* 16 */     this.inventory = new CraftInventory((IInventory)entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 21 */     return "CraftMinecartHopper{inventory=" + this.inventory + '}';
/*    */   }
/*    */   private final CraftInventory inventory;
/*    */   
/*    */   public EntityType getType() {
/* 26 */     return EntityType.MINECART_HOPPER;
/*    */   }
/*    */ 
/*    */   
/*    */   public Inventory getInventory() {
/* 31 */     return (Inventory)this.inventory;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isEnabled() {
/* 36 */     return ((EntityMinecartHopper)getHandle()).isEnabled();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setEnabled(boolean enabled) {
/* 41 */     ((EntityMinecartHopper)getHandle()).setEnabled(enabled);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\entity\CraftMinecartHopper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
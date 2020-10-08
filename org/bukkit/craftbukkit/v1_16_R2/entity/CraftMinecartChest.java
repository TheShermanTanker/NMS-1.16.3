/*    */ package org.bukkit.craftbukkit.v1_16_R2.entity;
/*    */ 
/*    */ import com.destroystokyo.paper.loottable.PaperLootableEntityInventory;
/*    */ import net.minecraft.server.v1_16_R2.EntityMinecartAbstract;
/*    */ import net.minecraft.server.v1_16_R2.EntityMinecartChest;
/*    */ import net.minecraft.server.v1_16_R2.IInventory;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.CraftServer;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftInventory;
/*    */ import org.bukkit.entity.EntityType;
/*    */ import org.bukkit.entity.minecart.StorageMinecart;
/*    */ import org.bukkit.inventory.Inventory;
/*    */ 
/*    */ public class CraftMinecartChest
/*    */   extends CraftMinecartContainer implements StorageMinecart, PaperLootableEntityInventory {
/*    */   public CraftMinecartChest(CraftServer server, EntityMinecartChest entity) {
/* 16 */     super(server, (EntityMinecartAbstract)entity);
/* 17 */     this.inventory = new CraftInventory((IInventory)entity);
/*    */   }
/*    */   private final CraftInventory inventory;
/*    */   
/*    */   public Inventory getInventory() {
/* 22 */     return (Inventory)this.inventory;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 27 */     return "CraftMinecartChest{inventory=" + this.inventory + '}';
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityType getType() {
/* 32 */     return EntityType.MINECART_CHEST;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\entity\CraftMinecartChest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
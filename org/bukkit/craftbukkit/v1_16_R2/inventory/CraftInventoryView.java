/*    */ package org.bukkit.craftbukkit.v1_16_R2.inventory;
/*    */ 
/*    */ import net.minecraft.server.v1_16_R2.Container;
/*    */ import net.minecraft.server.v1_16_R2.ItemStack;
/*    */ import org.bukkit.GameMode;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.entity.CraftHumanEntity;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.util.CraftChatMessage;
/*    */ import org.bukkit.entity.HumanEntity;
/*    */ import org.bukkit.event.inventory.InventoryType;
/*    */ import org.bukkit.inventory.Inventory;
/*    */ import org.bukkit.inventory.InventoryView;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ public class CraftInventoryView extends InventoryView {
/*    */   private final Container container;
/*    */   private final CraftHumanEntity player;
/*    */   private final CraftInventory viewing;
/*    */   
/*    */   public CraftInventoryView(HumanEntity player, Inventory viewing, Container container) {
/* 20 */     this.player = (CraftHumanEntity)player;
/* 21 */     this.viewing = (CraftInventory)viewing;
/* 22 */     this.container = container;
/*    */   }
/*    */ 
/*    */   
/*    */   public Inventory getTopInventory() {
/* 27 */     return this.viewing;
/*    */   }
/*    */ 
/*    */   
/*    */   public Inventory getBottomInventory() {
/* 32 */     return (Inventory)this.player.getInventory();
/*    */   }
/*    */ 
/*    */   
/*    */   public HumanEntity getPlayer() {
/* 37 */     return (HumanEntity)this.player;
/*    */   }
/*    */ 
/*    */   
/*    */   public InventoryType getType() {
/* 42 */     InventoryType type = this.viewing.getType();
/* 43 */     if (type == InventoryType.CRAFTING && this.player.getGameMode() == GameMode.CREATIVE) {
/* 44 */       return InventoryType.CREATIVE;
/*    */     }
/* 46 */     return type;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setItem(int slot, ItemStack item) {
/* 51 */     ItemStack stack = CraftItemStack.asNMSCopy(item);
/* 52 */     if (slot >= 0) {
/* 53 */       this.container.getSlot(slot).set(stack);
/*    */     } else {
/* 55 */       this.player.getHandle().drop(stack, false);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public ItemStack getItem(int slot) {
/* 61 */     if (slot < 0) {
/* 62 */       return null;
/*    */     }
/* 64 */     return CraftItemStack.asCraftMirror(this.container.getSlot(slot).getItem());
/*    */   }
/*    */ 
/*    */   
/*    */   public String getTitle() {
/* 69 */     return CraftChatMessage.fromComponent(this.container.getTitle());
/*    */   }
/*    */   
/*    */   public boolean isInTop(int rawSlot) {
/* 73 */     return (rawSlot < this.viewing.getSize());
/*    */   }
/*    */   
/*    */   public Container getHandle() {
/* 77 */     return this.container;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\inventory\CraftInventoryView.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
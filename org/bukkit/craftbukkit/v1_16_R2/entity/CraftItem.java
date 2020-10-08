/*    */ package org.bukkit.craftbukkit.v1_16_R2.entity;
/*    */ 
/*    */ import java.util.UUID;
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.server.v1_16_R2.Entity;
/*    */ import net.minecraft.server.v1_16_R2.EntityItem;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.CraftServer;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftItemStack;
/*    */ import org.bukkit.entity.EntityType;
/*    */ import org.bukkit.entity.Item;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ public class CraftItem
/*    */   extends CraftEntity
/*    */   implements Item
/*    */ {
/*    */   private final EntityItem item;
/*    */   
/*    */   public CraftItem(CraftServer server, Entity entity, EntityItem item) {
/* 20 */     super(server, entity);
/* 21 */     this.item = item;
/*    */   }
/*    */   
/*    */   public CraftItem(CraftServer server, EntityItem entity) {
/* 25 */     this(server, (Entity)entity, entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public ItemStack getItemStack() {
/* 30 */     return (ItemStack)CraftItemStack.asCraftMirror(this.item.getItemStack());
/*    */   }
/*    */ 
/*    */   
/*    */   public void setItemStack(ItemStack stack) {
/* 35 */     this.item.setItemStack(CraftItemStack.asNMSCopy(stack));
/*    */   }
/*    */ 
/*    */   
/*    */   public int getPickupDelay() {
/* 40 */     return this.item.pickupDelay;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setPickupDelay(int delay) {
/* 45 */     this.item.pickupDelay = Math.min(delay, 32767);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setTicksLived(int value) {
/* 50 */     super.setTicksLived(value);
/*    */ 
/*    */     
/* 53 */     this.item.age = value;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canMobPickup() {
/* 58 */     return this.item.canMobPickup;
/*    */   }
/*    */   
/*    */   public void setCanMobPickup(boolean canMobPickup) {
/* 62 */     this.item.canMobPickup = canMobPickup;
/*    */   }
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public UUID getOwner() {
/* 68 */     return this.item.getOwner();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setOwner(@Nullable UUID owner) {
/* 73 */     this.item.setOwner(owner);
/*    */   }
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public UUID getThrower() {
/* 79 */     return this.item.getThrower();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setThrower(@Nullable UUID thrower) {
/* 84 */     this.item.setThrower(thrower);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 90 */     return "CraftItem";
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityType getType() {
/* 95 */     return EntityType.DROPPED_ITEM;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\entity\CraftItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
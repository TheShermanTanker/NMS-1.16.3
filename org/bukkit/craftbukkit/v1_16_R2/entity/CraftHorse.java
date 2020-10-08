/*    */ package org.bukkit.craftbukkit.v1_16_R2.entity;
/*    */ import net.minecraft.server.v1_16_R2.Entity;
/*    */ import net.minecraft.server.v1_16_R2.EntityAnimal;
/*    */ import net.minecraft.server.v1_16_R2.EntityHorse;
/*    */ import net.minecraft.server.v1_16_R2.EntityHorseAbstract;
/*    */ import net.minecraft.server.v1_16_R2.EntityLiving;
/*    */ import org.apache.commons.lang.Validate;
/*    */ import org.bukkit.entity.EntityType;
/*    */ import org.bukkit.entity.Horse;
/*    */ import org.bukkit.inventory.AbstractHorseInventory;
/*    */ import org.bukkit.inventory.HorseInventory;
/*    */ import org.bukkit.inventory.Inventory;
/*    */ 
/*    */ public class CraftHorse extends CraftAbstractHorse implements Horse {
/*    */   public CraftHorse(CraftServer server, EntityHorse entity) {
/* 16 */     super(server, (EntityHorseAbstract)entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityHorse getHandle() {
/* 21 */     return (EntityHorse)super.getHandle();
/*    */   }
/*    */ 
/*    */   
/*    */   public Horse.Variant getVariant() {
/* 26 */     return Horse.Variant.HORSE;
/*    */   }
/*    */ 
/*    */   
/*    */   public Horse.Color getColor() {
/* 31 */     return Horse.Color.values()[getHandle().getColor().a()];
/*    */   }
/*    */ 
/*    */   
/*    */   public void setColor(Horse.Color color) {
/* 36 */     Validate.notNull(color, "Color cannot be null");
/* 37 */     getHandle().setVariant(HorseColor.a(color.ordinal()), getHandle().getStyle());
/*    */   }
/*    */ 
/*    */   
/*    */   public Horse.Style getStyle() {
/* 42 */     return Horse.Style.values()[getHandle().getStyle().a()];
/*    */   }
/*    */ 
/*    */   
/*    */   public void setStyle(Horse.Style style) {
/* 47 */     Validate.notNull(style, "Style cannot be null");
/* 48 */     getHandle().setVariant(getHandle().getColor(), HorseStyle.a(style.ordinal()));
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isCarryingChest() {
/* 53 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCarryingChest(boolean chest) {
/* 58 */     throw new UnsupportedOperationException("Not supported.");
/*    */   }
/*    */ 
/*    */   
/*    */   public HorseInventory getInventory() {
/* 63 */     return (HorseInventory)new CraftInventoryHorse((IInventory)(getHandle()).inventoryChest);
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 68 */     return "CraftHorse{variant=" + getVariant() + ", owner=" + getOwner() + '}';
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityType getType() {
/* 73 */     return EntityType.HORSE;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\entity\CraftHorse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
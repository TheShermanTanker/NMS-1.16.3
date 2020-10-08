/*    */ package org.bukkit.craftbukkit.v1_16_R2.entity;
/*    */ import net.minecraft.server.v1_16_R2.Entity;
/*    */ import net.minecraft.server.v1_16_R2.EntityCreature;
/*    */ import net.minecraft.server.v1_16_R2.EntityInsentient;
/*    */ import net.minecraft.server.v1_16_R2.EntityLiving;
/*    */ import net.minecraft.server.v1_16_R2.EntityMonster;
/*    */ import net.minecraft.server.v1_16_R2.EntityRaider;
/*    */ import net.minecraft.server.v1_16_R2.EntityWitch;
/*    */ import net.minecraft.server.v1_16_R2.IRangedEntity;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.CraftServer;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftItemStack;
/*    */ import org.bukkit.entity.EntityType;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ public class CraftWitch extends CraftRaider implements Witch, CraftRangedEntity<EntityWitch> {
/*    */   public CraftWitch(CraftServer server, EntityWitch entity) {
/* 17 */     super(server, (EntityRaider)entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityWitch getHandle() {
/* 22 */     return (EntityWitch)this.entity;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 27 */     return "CraftWitch";
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityType getType() {
/* 32 */     return EntityType.WITCH;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isDrinkingPotion() {
/* 37 */     return getHandle().isDrinkingPotion();
/*    */   }
/*    */   
/*    */   public int getPotionUseTimeLeft() {
/* 41 */     return getHandle().getPotionUseTimeLeft();
/*    */   }
/*    */   
/*    */   public ItemStack getDrinkingPotion() {
/* 45 */     return (ItemStack)CraftItemStack.asCraftMirror(getHandle().getItemInMainHand());
/*    */   }
/*    */   
/*    */   public void setDrinkingPotion(ItemStack potion) {
/* 49 */     Preconditions.checkArgument((potion == null || potion.getType().isEmpty() || potion.getType() == Material.POTION), "must be potion, air, or null");
/* 50 */     getHandle().setDrinkingPotion(CraftItemStack.asNMSCopy(potion));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\entity\CraftWitch.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
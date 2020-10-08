/*    */ package org.bukkit.craftbukkit.v1_16_R2.entity;
/*    */ import com.google.common.base.Preconditions;
/*    */ import net.minecraft.server.v1_16_R2.Entity;
/*    */ import net.minecraft.server.v1_16_R2.EntityHorseAbstract;
/*    */ import net.minecraft.server.v1_16_R2.EntityHorseChestedAbstract;
/*    */ import net.minecraft.server.v1_16_R2.EntityLiving;
/*    */ import net.minecraft.server.v1_16_R2.EntityLlama;
/*    */ import org.bukkit.entity.EntityType;
/*    */ import org.bukkit.entity.Horse;
/*    */ import org.bukkit.entity.Llama;
/*    */ import org.bukkit.inventory.AbstractHorseInventory;
/*    */ import org.bukkit.inventory.Inventory;
/*    */ 
/*    */ public class CraftLlama extends CraftChestedHorse implements Llama, CraftRangedEntity<EntityLlama> {
/*    */   public CraftLlama(CraftServer server, EntityLlama entity) {
/* 16 */     super(server, (EntityHorseChestedAbstract)entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityLlama getHandle() {
/* 21 */     return (EntityLlama)super.getHandle();
/*    */   }
/*    */ 
/*    */   
/*    */   public Llama.Color getColor() {
/* 26 */     return Llama.Color.values()[getHandle().getVariant()];
/*    */   }
/*    */ 
/*    */   
/*    */   public void setColor(Llama.Color color) {
/* 31 */     Preconditions.checkArgument((color != null), "color");
/*    */     
/* 33 */     getHandle().setVariant(color.ordinal());
/*    */   }
/*    */ 
/*    */   
/*    */   public LlamaInventory getInventory() {
/* 38 */     return (LlamaInventory)new CraftInventoryLlama((IInventory)(getHandle()).inventoryChest);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getStrength() {
/* 43 */     return getHandle().getStrength();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setStrength(int strength) {
/* 48 */     Preconditions.checkArgument((1 <= strength && strength <= 5), "strength must be [1,5]");
/* 49 */     if (strength == getStrength())
/* 50 */       return;  getHandle().setStrength(strength);
/* 51 */     getHandle().loadChest();
/*    */   }
/*    */ 
/*    */   
/*    */   public Horse.Variant getVariant() {
/* 56 */     return Horse.Variant.LLAMA;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 61 */     return "CraftLlama";
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityType getType() {
/* 66 */     return EntityType.LLAMA;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\entity\CraftLlama.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
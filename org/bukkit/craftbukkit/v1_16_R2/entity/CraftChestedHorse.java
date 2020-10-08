/*    */ package org.bukkit.craftbukkit.v1_16_R2.entity;
/*    */ import net.minecraft.server.v1_16_R2.Entity;
/*    */ import net.minecraft.server.v1_16_R2.EntityAnimal;
/*    */ import net.minecraft.server.v1_16_R2.EntityHorseAbstract;
/*    */ import net.minecraft.server.v1_16_R2.EntityHorseChestedAbstract;
/*    */ import net.minecraft.server.v1_16_R2.EntityLiving;
/*    */ 
/*    */ public abstract class CraftChestedHorse extends CraftAbstractHorse implements ChestedHorse {
/*    */   public CraftChestedHorse(CraftServer server, EntityHorseChestedAbstract entity) {
/* 10 */     super(server, (EntityHorseAbstract)entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityHorseChestedAbstract getHandle() {
/* 15 */     return (EntityHorseChestedAbstract)super.getHandle();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isCarryingChest() {
/* 20 */     return getHandle().isCarryingChest();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCarryingChest(boolean chest) {
/* 25 */     if (chest == isCarryingChest())
/* 26 */       return;  getHandle().setCarryingChest(chest);
/* 27 */     getHandle().loadChest();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\entity\CraftChestedHorse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
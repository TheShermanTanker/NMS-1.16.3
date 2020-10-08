/*    */ package org.bukkit.craftbukkit.v1_16_R2.entity;
/*    */ import net.minecraft.server.v1_16_R2.Entity;
/*    */ import net.minecraft.server.v1_16_R2.EntityIllagerAbstract;
/*    */ import net.minecraft.server.v1_16_R2.EntityLiving;
/*    */ import net.minecraft.server.v1_16_R2.EntityMonster;
/*    */ import net.minecraft.server.v1_16_R2.EntityPillager;
/*    */ import net.minecraft.server.v1_16_R2.EntityRaider;
/*    */ import org.bukkit.entity.EntityType;
/*    */ import org.bukkit.inventory.Inventory;
/*    */ 
/*    */ public class CraftPillager extends CraftIllager implements Pillager {
/*    */   public CraftPillager(CraftServer server, EntityPillager entity) {
/* 13 */     super(server, (EntityIllagerAbstract)entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityPillager getHandle() {
/* 18 */     return (EntityPillager)super.getHandle();
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityType getType() {
/* 23 */     return EntityType.PILLAGER;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 28 */     return "CraftPillager";
/*    */   }
/*    */ 
/*    */   
/*    */   public Inventory getInventory() {
/* 33 */     return (Inventory)new CraftInventory((IInventory)(getHandle()).inventory);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\entity\CraftPillager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
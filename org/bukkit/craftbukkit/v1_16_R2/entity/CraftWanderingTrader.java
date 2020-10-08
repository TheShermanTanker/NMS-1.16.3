/*    */ package org.bukkit.craftbukkit.v1_16_R2.entity;
/*    */ import net.minecraft.server.v1_16_R2.Entity;
/*    */ import net.minecraft.server.v1_16_R2.EntityLiving;
/*    */ import net.minecraft.server.v1_16_R2.EntityVillagerAbstract;
/*    */ import net.minecraft.server.v1_16_R2.EntityVillagerTrader;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.CraftServer;
/*    */ import org.bukkit.entity.EntityType;
/*    */ 
/*    */ public class CraftWanderingTrader extends CraftAbstractVillager implements WanderingTrader {
/*    */   public CraftWanderingTrader(CraftServer server, EntityVillagerTrader entity) {
/* 11 */     super(server, (EntityVillagerAbstract)entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityVillagerTrader getHandle() {
/* 16 */     return (EntityVillagerTrader)this.entity;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 21 */     return "CraftWanderingTrader";
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityType getType() {
/* 26 */     return EntityType.WANDERING_TRADER;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\entity\CraftWanderingTrader.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
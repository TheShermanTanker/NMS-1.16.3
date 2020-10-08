/*    */ package org.bukkit.craftbukkit.v1_16_R2.entity;
/*    */ import net.minecraft.server.v1_16_R2.Entity;
/*    */ import net.minecraft.server.v1_16_R2.EntityAnimal;
/*    */ import net.minecraft.server.v1_16_R2.EntityLiving;
/*    */ import net.minecraft.server.v1_16_R2.EntityPolarBear;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.CraftServer;
/*    */ import org.bukkit.entity.EntityType;
/*    */ 
/*    */ public class CraftPolarBear extends CraftAnimals implements PolarBear {
/*    */   public CraftPolarBear(CraftServer server, EntityPolarBear entity) {
/* 11 */     super(server, (EntityAnimal)entity);
/*    */   }
/*    */   
/*    */   public EntityPolarBear getHandle() {
/* 15 */     return (EntityPolarBear)this.entity;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 20 */     return "CraftPolarBear";
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityType getType() {
/* 25 */     return EntityType.POLAR_BEAR;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\entity\CraftPolarBear.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
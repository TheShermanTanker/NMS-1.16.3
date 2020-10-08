/*    */ package org.bukkit.craftbukkit.v1_16_R2.entity;
/*    */ import net.minecraft.server.v1_16_R2.Entity;
/*    */ import net.minecraft.server.v1_16_R2.EntityCod;
/*    */ import net.minecraft.server.v1_16_R2.EntityFish;
/*    */ import net.minecraft.server.v1_16_R2.EntityLiving;
/*    */ import net.minecraft.server.v1_16_R2.EntityWaterAnimal;
/*    */ import org.bukkit.entity.EntityType;
/*    */ 
/*    */ public class CraftCod extends CraftFish implements Cod {
/*    */   public CraftCod(CraftServer server, EntityCod entity) {
/* 11 */     super(server, (EntityFish)entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityCod getHandle() {
/* 16 */     return (EntityCod)super.getHandle();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 21 */     return "CraftCod";
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityType getType() {
/* 26 */     return EntityType.COD;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\entity\CraftCod.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
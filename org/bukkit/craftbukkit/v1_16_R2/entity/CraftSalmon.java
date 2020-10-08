/*    */ package org.bukkit.craftbukkit.v1_16_R2.entity;
/*    */ import net.minecraft.server.v1_16_R2.Entity;
/*    */ import net.minecraft.server.v1_16_R2.EntityFish;
/*    */ import net.minecraft.server.v1_16_R2.EntityLiving;
/*    */ import net.minecraft.server.v1_16_R2.EntitySalmon;
/*    */ import net.minecraft.server.v1_16_R2.EntityWaterAnimal;
/*    */ import org.bukkit.entity.EntityType;
/*    */ 
/*    */ public class CraftSalmon extends CraftFish implements Salmon {
/*    */   public CraftSalmon(CraftServer server, EntitySalmon entity) {
/* 11 */     super(server, (EntityFish)entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public EntitySalmon getHandle() {
/* 16 */     return (EntitySalmon)super.getHandle();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 21 */     return "CraftSalmon";
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityType getType() {
/* 26 */     return EntityType.SALMON;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\entity\CraftSalmon.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
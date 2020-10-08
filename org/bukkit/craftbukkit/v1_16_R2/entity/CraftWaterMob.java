/*    */ package org.bukkit.craftbukkit.v1_16_R2.entity;
/*    */ import net.minecraft.server.v1_16_R2.Entity;
/*    */ import net.minecraft.server.v1_16_R2.EntityCreature;
/*    */ import net.minecraft.server.v1_16_R2.EntityLiving;
/*    */ import net.minecraft.server.v1_16_R2.EntityWaterAnimal;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.CraftServer;
/*    */ 
/*    */ public class CraftWaterMob extends CraftCreature implements WaterMob {
/*    */   public CraftWaterMob(CraftServer server, EntityWaterAnimal entity) {
/* 10 */     super(server, (EntityCreature)entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityWaterAnimal getHandle() {
/* 15 */     return (EntityWaterAnimal)this.entity;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 20 */     return "CraftWaterMob";
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\entity\CraftWaterMob.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
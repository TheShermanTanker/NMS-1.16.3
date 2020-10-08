/*    */ package org.bukkit.craftbukkit.v1_16_R2.entity;
/*    */ import net.minecraft.server.v1_16_R2.Entity;
/*    */ import net.minecraft.server.v1_16_R2.EntityFish;
/*    */ import net.minecraft.server.v1_16_R2.EntityLiving;
/*    */ import net.minecraft.server.v1_16_R2.EntityWaterAnimal;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.CraftServer;
/*    */ 
/*    */ public class CraftFish extends CraftWaterMob implements Fish {
/*    */   public CraftFish(CraftServer server, EntityFish entity) {
/* 10 */     super(server, (EntityWaterAnimal)entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityFish getHandle() {
/* 15 */     return (EntityFish)this.entity;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 20 */     return "CraftFish";
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\entity\CraftFish.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
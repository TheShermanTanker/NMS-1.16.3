/*    */ package org.bukkit.craftbukkit.v1_16_R2.entity;
/*    */ import net.minecraft.server.v1_16_R2.Entity;
/*    */ import net.minecraft.server.v1_16_R2.EntityDolphin;
/*    */ import net.minecraft.server.v1_16_R2.EntityLiving;
/*    */ import net.minecraft.server.v1_16_R2.EntityWaterAnimal;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.CraftServer;
/*    */ import org.bukkit.entity.EntityType;
/*    */ 
/*    */ public class CraftDolphin extends CraftWaterMob implements Dolphin {
/*    */   public CraftDolphin(CraftServer server, EntityDolphin entity) {
/* 11 */     super(server, (EntityWaterAnimal)entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityDolphin getHandle() {
/* 16 */     return (EntityDolphin)super.getHandle();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 21 */     return "CraftDolphin";
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityType getType() {
/* 26 */     return EntityType.DOLPHIN;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\entity\CraftDolphin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
/*    */ package org.bukkit.craftbukkit.v1_16_R2.entity;
/*    */ import net.minecraft.server.v1_16_R2.Entity;
/*    */ import net.minecraft.server.v1_16_R2.EntityLiving;
/*    */ import net.minecraft.server.v1_16_R2.EntitySquid;
/*    */ import net.minecraft.server.v1_16_R2.EntityWaterAnimal;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.CraftServer;
/*    */ import org.bukkit.entity.EntityType;
/*    */ 
/*    */ public class CraftSquid extends CraftWaterMob implements Squid {
/*    */   public CraftSquid(CraftServer server, EntitySquid entity) {
/* 11 */     super(server, (EntityWaterAnimal)entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public EntitySquid getHandle() {
/* 16 */     return (EntitySquid)this.entity;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 21 */     return "CraftSquid";
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityType getType() {
/* 26 */     return EntityType.SQUID;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\entity\CraftSquid.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
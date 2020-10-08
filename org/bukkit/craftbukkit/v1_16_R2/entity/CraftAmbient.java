/*    */ package org.bukkit.craftbukkit.v1_16_R2.entity;
/*    */ import net.minecraft.server.v1_16_R2.Entity;
/*    */ import net.minecraft.server.v1_16_R2.EntityAmbient;
/*    */ import net.minecraft.server.v1_16_R2.EntityInsentient;
/*    */ import net.minecraft.server.v1_16_R2.EntityLiving;
/*    */ import org.bukkit.entity.EntityType;
/*    */ 
/*    */ public class CraftAmbient extends CraftMob implements Ambient {
/*    */   public CraftAmbient(CraftServer server, EntityAmbient entity) {
/* 10 */     super(server, (EntityInsentient)entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityAmbient getHandle() {
/* 15 */     return (EntityAmbient)this.entity;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 20 */     return "CraftAmbient";
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityType getType() {
/* 25 */     return EntityType.UNKNOWN;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\entity\CraftAmbient.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
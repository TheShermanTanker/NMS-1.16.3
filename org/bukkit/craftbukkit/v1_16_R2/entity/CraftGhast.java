/*    */ package org.bukkit.craftbukkit.v1_16_R2.entity;
/*    */ import net.minecraft.server.v1_16_R2.Entity;
/*    */ import net.minecraft.server.v1_16_R2.EntityFlying;
/*    */ import net.minecraft.server.v1_16_R2.EntityGhast;
/*    */ import net.minecraft.server.v1_16_R2.EntityLiving;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.CraftServer;
/*    */ import org.bukkit.entity.EntityType;
/*    */ 
/*    */ public class CraftGhast extends CraftFlying implements Ghast {
/*    */   public CraftGhast(CraftServer server, EntityGhast entity) {
/* 11 */     super(server, (EntityFlying)entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityGhast getHandle() {
/* 16 */     return (EntityGhast)this.entity;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 21 */     return "CraftGhast";
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityType getType() {
/* 26 */     return EntityType.GHAST;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\entity\CraftGhast.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
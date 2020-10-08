/*    */ package org.bukkit.craftbukkit.v1_16_R2.entity;
/*    */ import net.minecraft.server.v1_16_R2.Entity;
/*    */ import net.minecraft.server.v1_16_R2.EntityLiving;
/*    */ import net.minecraft.server.v1_16_R2.EntityMonster;
/*    */ import net.minecraft.server.v1_16_R2.EntityRaider;
/*    */ import net.minecraft.server.v1_16_R2.EntityRavager;
/*    */ import org.bukkit.entity.EntityType;
/*    */ 
/*    */ public class CraftRavager extends CraftRaider implements Ravager {
/*    */   public CraftRavager(CraftServer server, EntityRavager entity) {
/* 11 */     super(server, (EntityRaider)entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityRavager getHandle() {
/* 16 */     return (EntityRavager)super.getHandle();
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityType getType() {
/* 21 */     return EntityType.RAVAGER;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 26 */     return "CraftRavager";
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\entity\CraftRavager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
/*    */ package org.bukkit.craftbukkit.v1_16_R2.entity;
/*    */ import net.minecraft.server.v1_16_R2.Entity;
/*    */ import net.minecraft.server.v1_16_R2.EntityCreature;
/*    */ import net.minecraft.server.v1_16_R2.EntityInsentient;
/*    */ import net.minecraft.server.v1_16_R2.EntityLiving;
/*    */ 
/*    */ public class CraftCreature extends CraftMob implements Creature {
/*    */   public CraftCreature(CraftServer server, EntityCreature entity) {
/*  9 */     super(server, (EntityInsentient)entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityCreature getHandle() {
/* 14 */     return (EntityCreature)this.entity;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 19 */     return "CraftCreature";
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\entity\CraftCreature.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
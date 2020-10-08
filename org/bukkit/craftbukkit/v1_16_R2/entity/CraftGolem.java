/*    */ package org.bukkit.craftbukkit.v1_16_R2.entity;
/*    */ import net.minecraft.server.v1_16_R2.Entity;
/*    */ import net.minecraft.server.v1_16_R2.EntityCreature;
/*    */ import net.minecraft.server.v1_16_R2.EntityGolem;
/*    */ import net.minecraft.server.v1_16_R2.EntityLiving;
/*    */ 
/*    */ public class CraftGolem extends CraftCreature implements Golem {
/*    */   public CraftGolem(CraftServer server, EntityGolem entity) {
/*  9 */     super(server, (EntityCreature)entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityGolem getHandle() {
/* 14 */     return (EntityGolem)this.entity;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 19 */     return "CraftGolem";
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\entity\CraftGolem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
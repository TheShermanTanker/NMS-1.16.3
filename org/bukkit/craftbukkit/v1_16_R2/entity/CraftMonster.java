/*    */ package org.bukkit.craftbukkit.v1_16_R2.entity;
/*    */ import net.minecraft.server.v1_16_R2.Entity;
/*    */ import net.minecraft.server.v1_16_R2.EntityCreature;
/*    */ import net.minecraft.server.v1_16_R2.EntityLiving;
/*    */ import net.minecraft.server.v1_16_R2.EntityMonster;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.CraftServer;
/*    */ 
/*    */ public class CraftMonster extends CraftCreature implements Monster {
/*    */   public CraftMonster(CraftServer server, EntityMonster entity) {
/* 10 */     super(server, (EntityCreature)entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityMonster getHandle() {
/* 15 */     return (EntityMonster)this.entity;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 20 */     return "CraftMonster";
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\entity\CraftMonster.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
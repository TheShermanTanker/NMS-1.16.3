/*    */ package org.bukkit.craftbukkit.v1_16_R2.entity;
/*    */ import net.minecraft.server.v1_16_R2.Entity;
/*    */ import net.minecraft.server.v1_16_R2.EntityFireballFireball;
/*    */ import net.minecraft.server.v1_16_R2.EntityLargeFireball;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.CraftServer;
/*    */ import org.bukkit.entity.EntityType;
/*    */ 
/*    */ public class CraftLargeFireball extends CraftSizedFireball implements LargeFireball {
/*    */   public CraftLargeFireball(CraftServer server, EntityLargeFireball entity) {
/* 10 */     super(server, (EntityFireballFireball)entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setYield(float yield) {
/* 15 */     super.setYield(yield);
/* 16 */     (getHandle()).yield = (int)yield;
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityLargeFireball getHandle() {
/* 21 */     return (EntityLargeFireball)this.entity;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 26 */     return "CraftLargeFireball";
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityType getType() {
/* 31 */     return EntityType.FIREBALL;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\entity\CraftLargeFireball.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
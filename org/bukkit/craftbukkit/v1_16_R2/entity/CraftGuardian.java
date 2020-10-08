/*    */ package org.bukkit.craftbukkit.v1_16_R2.entity;
/*    */ 
/*    */ import net.minecraft.server.v1_16_R2.EntityGuardian;
/*    */ import net.minecraft.server.v1_16_R2.EntityMonster;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.CraftServer;
/*    */ import org.bukkit.entity.EntityType;
/*    */ import org.bukkit.entity.Guardian;
/*    */ 
/*    */ public class CraftGuardian extends CraftMonster implements Guardian {
/*    */   public CraftGuardian(CraftServer server, EntityGuardian entity) {
/* 11 */     super(server, (EntityMonster)entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 16 */     return "CraftGuardian";
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityType getType() {
/* 21 */     return EntityType.GUARDIAN;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isElder() {
/* 26 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setElder(boolean shouldBeElder) {
/* 31 */     throw new UnsupportedOperationException("Not supported.");
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\entity\CraftGuardian.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
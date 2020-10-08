/*    */ package org.bukkit.craftbukkit.v1_16_R2.entity;
/*    */ 
/*    */ import net.minecraft.server.v1_16_R2.EntityGuardian;
/*    */ import net.minecraft.server.v1_16_R2.EntityGuardianElder;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.CraftServer;
/*    */ import org.bukkit.entity.ElderGuardian;
/*    */ import org.bukkit.entity.EntityType;
/*    */ 
/*    */ public class CraftElderGuardian extends CraftGuardian implements ElderGuardian {
/*    */   public CraftElderGuardian(CraftServer server, EntityGuardianElder entity) {
/* 11 */     super(server, (EntityGuardian)entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 16 */     return "CraftElderGuardian";
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityType getType() {
/* 21 */     return EntityType.ELDER_GUARDIAN;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isElder() {
/* 26 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\entity\CraftElderGuardian.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
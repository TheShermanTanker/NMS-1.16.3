/*    */ package org.bukkit.craftbukkit.v1_16_R2.entity;
/*    */ import net.minecraft.server.v1_16_R2.Entity;
/*    */ import net.minecraft.server.v1_16_R2.EntityComplexPart;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.CraftServer;
/*    */ import org.bukkit.entity.ComplexLivingEntity;
/*    */ import org.bukkit.entity.EnderDragon;
/*    */ import org.bukkit.entity.Entity;
/*    */ 
/*    */ public class CraftEnderDragonPart extends CraftComplexPart implements EnderDragonPart {
/*    */   public CraftEnderDragonPart(CraftServer server, EntityComplexPart entity) {
/* 11 */     super(server, entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public EnderDragon getParent() {
/* 16 */     return (EnderDragon)super.getParent();
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityComplexPart getHandle() {
/* 21 */     return (EntityComplexPart)this.entity;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 26 */     return "CraftEnderDragonPart";
/*    */   }
/*    */ 
/*    */   
/*    */   public void damage(double amount) {
/* 31 */     getParent().damage(amount);
/*    */   }
/*    */ 
/*    */   
/*    */   public void damage(double amount, Entity source) {
/* 36 */     getParent().damage(amount, source);
/*    */   }
/*    */ 
/*    */   
/*    */   public double getHealth() {
/* 41 */     return getParent().getHealth();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setHealth(double health) {
/* 46 */     getParent().setHealth(health);
/*    */   }
/*    */ 
/*    */   
/*    */   public double getAbsorptionAmount() {
/* 51 */     return getParent().getAbsorptionAmount();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setAbsorptionAmount(double amount) {
/* 56 */     getParent().setAbsorptionAmount(amount);
/*    */   }
/*    */ 
/*    */   
/*    */   public double getMaxHealth() {
/* 61 */     return getParent().getMaxHealth();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setMaxHealth(double health) {
/* 66 */     getParent().setMaxHealth(health);
/*    */   }
/*    */ 
/*    */   
/*    */   public void resetMaxHealth() {
/* 71 */     getParent().resetMaxHealth();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\entity\CraftEnderDragonPart.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
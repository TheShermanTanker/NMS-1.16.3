/*    */ package org.bukkit.craftbukkit.v1_16_R2.entity;
/*    */ import com.google.common.base.Preconditions;
/*    */ import net.minecraft.server.v1_16_R2.Entity;
/*    */ import net.minecraft.server.v1_16_R2.EntityAnimal;
/*    */ import net.minecraft.server.v1_16_R2.EntityLiving;
/*    */ import net.minecraft.server.v1_16_R2.EntityPig;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.CraftServer;
/*    */ import org.bukkit.entity.EntityType;
/*    */ 
/*    */ public class CraftPig extends CraftAnimals implements Pig {
/*    */   public CraftPig(CraftServer server, EntityPig entity) {
/* 13 */     super(server, (EntityAnimal)entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean hasSaddle() {
/* 18 */     return getHandle().hasSaddle();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setSaddle(boolean saddled) {
/* 23 */     (getHandle()).saddleStorage.setSaddle(saddled);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getBoostTicks() {
/* 28 */     return (getHandle()).saddleStorage.boosting ? (getHandle()).saddleStorage.boostTicks : 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setBoostTicks(int ticks) {
/* 33 */     Preconditions.checkArgument((ticks >= 0), "ticks must be >= 0");
/*    */     
/* 35 */     (getHandle()).saddleStorage.setBoostTicks(ticks);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getCurrentBoostTicks() {
/* 40 */     return (getHandle()).saddleStorage.boosting ? (getHandle()).saddleStorage.currentBoostTicks : 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCurrentBoostTicks(int ticks) {
/* 45 */     if (!(getHandle()).saddleStorage.boosting) {
/*    */       return;
/*    */     }
/*    */     
/* 49 */     int max = (getHandle()).saddleStorage.boostTicks;
/* 50 */     Preconditions.checkArgument((ticks >= 0 && ticks <= max), "boost ticks must not exceed 0 or %d (inclusive)", max);
/*    */     
/* 52 */     (getHandle()).saddleStorage.currentBoostTicks = ticks;
/*    */   }
/*    */ 
/*    */   
/*    */   public Material getSteerMaterial() {
/* 57 */     return Material.CARROT_ON_A_STICK;
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityPig getHandle() {
/* 62 */     return (EntityPig)this.entity;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 67 */     return "CraftPig";
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityType getType() {
/* 72 */     return EntityType.PIG;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\entity\CraftPig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
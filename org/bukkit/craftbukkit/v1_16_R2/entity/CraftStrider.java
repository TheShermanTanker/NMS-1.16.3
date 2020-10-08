/*    */ package org.bukkit.craftbukkit.v1_16_R2.entity;
/*    */ import com.google.common.base.Preconditions;
/*    */ import net.minecraft.server.v1_16_R2.Entity;
/*    */ import net.minecraft.server.v1_16_R2.EntityAnimal;
/*    */ import net.minecraft.server.v1_16_R2.EntityLiving;
/*    */ import net.minecraft.server.v1_16_R2.EntityStrider;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.CraftServer;
/*    */ import org.bukkit.entity.EntityType;
/*    */ 
/*    */ public class CraftStrider extends CraftAnimals implements Strider {
/*    */   public CraftStrider(CraftServer server, EntityStrider entity) {
/* 13 */     super(server, (EntityAnimal)entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isShivering() {
/* 18 */     return getHandle().isShivering();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setShivering(boolean shivering) {
/* 23 */     getHandle().setShivering(shivering);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean hasSaddle() {
/* 28 */     return getHandle().hasSaddle();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setSaddle(boolean saddled) {
/* 33 */     (getHandle()).saddleStorage.setSaddle(saddled);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getBoostTicks() {
/* 38 */     return (getHandle()).saddleStorage.boosting ? (getHandle()).saddleStorage.boostTicks : 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setBoostTicks(int ticks) {
/* 43 */     Preconditions.checkArgument((ticks >= 0), "ticks must be >= 0");
/*    */     
/* 45 */     (getHandle()).saddleStorage.setBoostTicks(ticks);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getCurrentBoostTicks() {
/* 50 */     return (getHandle()).saddleStorage.boosting ? (getHandle()).saddleStorage.currentBoostTicks : 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCurrentBoostTicks(int ticks) {
/* 55 */     if (!(getHandle()).saddleStorage.boosting) {
/*    */       return;
/*    */     }
/*    */     
/* 59 */     int max = (getHandle()).saddleStorage.boostTicks;
/* 60 */     Preconditions.checkArgument((ticks >= 0 && ticks <= max), "boost ticks must not exceed 0 or %d (inclusive)", max);
/*    */     
/* 62 */     (getHandle()).saddleStorage.currentBoostTicks = ticks;
/*    */   }
/*    */ 
/*    */   
/*    */   public Material getSteerMaterial() {
/* 67 */     return Material.WARPED_FUNGUS_ON_A_STICK;
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityStrider getHandle() {
/* 72 */     return (EntityStrider)this.entity;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 77 */     return "CraftStrider";
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityType getType() {
/* 82 */     return EntityType.STRIDER;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\entity\CraftStrider.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
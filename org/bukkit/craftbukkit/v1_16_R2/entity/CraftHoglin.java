/*    */ package org.bukkit.craftbukkit.v1_16_R2.entity;
/*    */ import net.minecraft.server.v1_16_R2.Entity;
/*    */ import net.minecraft.server.v1_16_R2.EntityAnimal;
/*    */ import net.minecraft.server.v1_16_R2.EntityHoglin;
/*    */ import net.minecraft.server.v1_16_R2.EntityInsentient;
/*    */ import net.minecraft.server.v1_16_R2.EntityLiving;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.CraftServer;
/*    */ import org.bukkit.entity.EntityType;
/*    */ 
/*    */ public class CraftHoglin extends CraftAnimals implements Hoglin {
/*    */   public CraftHoglin(CraftServer server, EntityHoglin entity) {
/* 12 */     super(server, (EntityAnimal)entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isImmuneToZombification() {
/* 17 */     return getHandle().isImmuneToZombification();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setImmuneToZombification(boolean flag) {
/* 22 */     getHandle().setImmuneToZombification(flag);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isAbleToBeHunted() {
/* 27 */     return (getHandle()).cannotBeHunted;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setIsAbleToBeHunted(boolean flag) {
/* 32 */     (getHandle()).cannotBeHunted = flag;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getConversionTime() {
/* 37 */     Preconditions.checkState(isConverting(), "Entity not converting");
/* 38 */     return (getHandle()).conversionTicks;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setConversionTime(int time) {
/* 43 */     if (time < 0) {
/* 44 */       (getHandle()).conversionTicks = -1;
/* 45 */       getHandle().setImmuneToZombification(false);
/*    */     } else {
/* 47 */       (getHandle()).conversionTicks = time;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isConverting() {
/* 53 */     return getHandle().isConverting();
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityHoglin getHandle() {
/* 58 */     return (EntityHoglin)this.entity;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 63 */     return "CraftHoglin";
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityType getType() {
/* 68 */     return EntityType.HOGLIN;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\entity\CraftHoglin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
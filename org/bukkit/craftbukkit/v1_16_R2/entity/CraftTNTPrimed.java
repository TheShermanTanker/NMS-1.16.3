/*    */ package org.bukkit.craftbukkit.v1_16_R2.entity;
/*    */ 
/*    */ import net.minecraft.server.v1_16_R2.Entity;
/*    */ import net.minecraft.server.v1_16_R2.EntityLiving;
/*    */ import net.minecraft.server.v1_16_R2.EntityTNTPrimed;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.CraftServer;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.EntityType;
/*    */ import org.bukkit.entity.TNTPrimed;
/*    */ 
/*    */ public class CraftTNTPrimed extends CraftEntity implements TNTPrimed {
/*    */   public CraftTNTPrimed(CraftServer server, EntityTNTPrimed entity) {
/* 13 */     super(server, (Entity)entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public float getYield() {
/* 18 */     return (getHandle()).yield;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isIncendiary() {
/* 23 */     return (getHandle()).isIncendiary;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setIsIncendiary(boolean isIncendiary) {
/* 28 */     (getHandle()).isIncendiary = isIncendiary;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setYield(float yield) {
/* 33 */     (getHandle()).yield = yield;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getFuseTicks() {
/* 38 */     return getHandle().getFuseTicks();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setFuseTicks(int fuseTicks) {
/* 43 */     getHandle().setFuseTicks(fuseTicks);
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityTNTPrimed getHandle() {
/* 48 */     return (EntityTNTPrimed)this.entity;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 53 */     return "CraftTNTPrimed";
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityType getType() {
/* 58 */     return EntityType.PRIMED_TNT;
/*    */   }
/*    */ 
/*    */   
/*    */   public Entity getSource() {
/* 63 */     EntityLiving source = getHandle().getSource();
/*    */     
/* 65 */     return (source != null) ? source.getBukkitEntity() : null;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\entity\CraftTNTPrimed.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
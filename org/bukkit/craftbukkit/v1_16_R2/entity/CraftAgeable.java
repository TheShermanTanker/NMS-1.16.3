/*    */ package org.bukkit.craftbukkit.v1_16_R2.entity;
/*    */ import net.minecraft.server.v1_16_R2.Entity;
/*    */ import net.minecraft.server.v1_16_R2.EntityAgeable;
/*    */ import net.minecraft.server.v1_16_R2.EntityCreature;
/*    */ import net.minecraft.server.v1_16_R2.EntityLiving;
/*    */ 
/*    */ public class CraftAgeable extends CraftCreature implements Ageable {
/*    */   public CraftAgeable(CraftServer server, EntityAgeable entity) {
/*  9 */     super(server, (EntityCreature)entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getAge() {
/* 14 */     return getHandle().getAge();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setAge(int age) {
/* 19 */     getHandle().setAgeRaw(age);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setAgeLock(boolean lock) {
/* 24 */     (getHandle()).ageLocked = lock;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean getAgeLock() {
/* 29 */     return (getHandle()).ageLocked;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setBaby() {
/* 34 */     if (isAdult()) {
/* 35 */       setAge(-24000);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void setAdult() {
/* 41 */     if (!isAdult()) {
/* 42 */       setAge(0);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isAdult() {
/* 48 */     return (getAge() >= 0);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean canBreed() {
/* 54 */     return (getAge() == 0);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setBreed(boolean breed) {
/* 59 */     if (breed) {
/* 60 */       setAge(0);
/* 61 */     } else if (isAdult()) {
/* 62 */       setAge(6000);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityAgeable getHandle() {
/* 68 */     return (EntityAgeable)this.entity;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 73 */     return "CraftAgeable";
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\entity\CraftAgeable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
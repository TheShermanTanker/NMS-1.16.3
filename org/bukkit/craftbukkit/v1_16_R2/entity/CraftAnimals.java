/*    */ package org.bukkit.craftbukkit.v1_16_R2.entity;
/*    */ import java.util.UUID;
/*    */ import net.minecraft.server.v1_16_R2.Entity;
/*    */ import net.minecraft.server.v1_16_R2.EntityAgeable;
/*    */ import net.minecraft.server.v1_16_R2.EntityAnimal;
/*    */ import net.minecraft.server.v1_16_R2.EntityInsentient;
/*    */ import net.minecraft.server.v1_16_R2.EntityLiving;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.CraftServer;
/*    */ 
/*    */ public class CraftAnimals extends CraftAgeable implements Animals {
/*    */   public CraftAnimals(CraftServer server, EntityAnimal entity) {
/* 12 */     super(server, (EntityAgeable)entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityAnimal getHandle() {
/* 17 */     return (EntityAnimal)this.entity;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 22 */     return "CraftAnimals";
/*    */   }
/*    */ 
/*    */   
/*    */   public UUID getBreedCause() {
/* 27 */     return (getHandle()).breedCause;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setBreedCause(UUID uuid) {
/* 32 */     (getHandle()).breedCause = uuid;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isLoveMode() {
/* 37 */     return getHandle().isInLove();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setLoveModeTicks(int ticks) {
/* 42 */     Preconditions.checkArgument((ticks >= 0), "Love mode ticks must be positive or 0");
/* 43 */     getHandle().setLoveTicks(ticks);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getLoveModeTicks() {
/* 48 */     return (getHandle()).loveTicks;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\entity\CraftAnimals.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
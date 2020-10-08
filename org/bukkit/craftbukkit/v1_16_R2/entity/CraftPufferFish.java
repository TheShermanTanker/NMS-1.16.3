/*    */ package org.bukkit.craftbukkit.v1_16_R2.entity;
/*    */ import net.minecraft.server.v1_16_R2.Entity;
/*    */ import net.minecraft.server.v1_16_R2.EntityFish;
/*    */ import net.minecraft.server.v1_16_R2.EntityLiving;
/*    */ import net.minecraft.server.v1_16_R2.EntityPufferFish;
/*    */ import net.minecraft.server.v1_16_R2.EntityWaterAnimal;
/*    */ import org.bukkit.entity.EntityType;
/*    */ 
/*    */ public class CraftPufferFish extends CraftFish implements PufferFish {
/*    */   public CraftPufferFish(CraftServer server, EntityPufferFish entity) {
/* 11 */     super(server, (EntityFish)entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityPufferFish getHandle() {
/* 16 */     return (EntityPufferFish)super.getHandle();
/*    */   }
/*    */ 
/*    */   
/*    */   public int getPuffState() {
/* 21 */     return getHandle().getPuffState();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setPuffState(int state) {
/* 26 */     getHandle().setPuffState(state);
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 31 */     return "CraftPufferFish";
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityType getType() {
/* 36 */     return EntityType.PUFFERFISH;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\entity\CraftPufferFish.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
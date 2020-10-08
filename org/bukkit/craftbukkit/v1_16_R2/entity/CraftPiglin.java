/*    */ package org.bukkit.craftbukkit.v1_16_R2.entity;
/*    */ import net.minecraft.server.v1_16_R2.Entity;
/*    */ import net.minecraft.server.v1_16_R2.EntityLiving;
/*    */ import net.minecraft.server.v1_16_R2.EntityMonster;
/*    */ import net.minecraft.server.v1_16_R2.EntityPiglin;
/*    */ import net.minecraft.server.v1_16_R2.EntityPiglinAbstract;
/*    */ import org.bukkit.entity.EntityType;
/*    */ 
/*    */ public class CraftPiglin extends CraftPiglinAbstract implements Piglin {
/*    */   public CraftPiglin(CraftServer server, EntityPiglin entity) {
/* 11 */     super(server, (EntityPiglinAbstract)entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isAbleToHunt() {
/* 16 */     return (getHandle()).cannotHunt;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setIsAbleToHunt(boolean flag) {
/* 21 */     (getHandle()).cannotHunt = flag;
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityPiglin getHandle() {
/* 26 */     return (EntityPiglin)super.getHandle();
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityType getType() {
/* 31 */     return EntityType.PIGLIN;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 36 */     return "CraftPiglin";
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\entity\CraftPiglin.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
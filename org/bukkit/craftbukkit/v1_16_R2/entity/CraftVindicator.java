/*    */ package org.bukkit.craftbukkit.v1_16_R2.entity;
/*    */ import net.minecraft.server.v1_16_R2.Entity;
/*    */ import net.minecraft.server.v1_16_R2.EntityIllagerAbstract;
/*    */ import net.minecraft.server.v1_16_R2.EntityLiving;
/*    */ import net.minecraft.server.v1_16_R2.EntityMonster;
/*    */ import net.minecraft.server.v1_16_R2.EntityVindicator;
/*    */ import org.bukkit.entity.EntityType;
/*    */ 
/*    */ public class CraftVindicator extends CraftIllager implements Vindicator {
/*    */   public CraftVindicator(CraftServer server, EntityVindicator entity) {
/* 11 */     super(server, (EntityIllagerAbstract)entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityVindicator getHandle() {
/* 16 */     return (EntityVindicator)super.getHandle();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 21 */     return "CraftVindicator";
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityType getType() {
/* 26 */     return EntityType.VINDICATOR;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isJohnny() {
/* 31 */     return getHandle().isJohnny();
/*    */   }
/*    */   
/*    */   public void setJohnny(boolean johnny) {
/* 35 */     getHandle().setJohnny(johnny);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\entity\CraftVindicator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
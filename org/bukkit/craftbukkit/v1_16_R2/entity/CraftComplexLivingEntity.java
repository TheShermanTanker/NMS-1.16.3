/*    */ package org.bukkit.craftbukkit.v1_16_R2.entity;
/*    */ import net.minecraft.server.v1_16_R2.Entity;
/*    */ import net.minecraft.server.v1_16_R2.EntityInsentient;
/*    */ import net.minecraft.server.v1_16_R2.EntityLiving;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.CraftServer;
/*    */ import org.bukkit.entity.ComplexLivingEntity;
/*    */ 
/*    */ public abstract class CraftComplexLivingEntity extends CraftMob implements ComplexLivingEntity {
/*    */   public CraftComplexLivingEntity(CraftServer server, EntityInsentient entity) {
/* 10 */     super(server, entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityInsentient getHandle() {
/* 15 */     return (EntityInsentient)this.entity;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 20 */     return "CraftComplexLivingEntity";
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\entity\CraftComplexLivingEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
/*    */ package org.bukkit.craftbukkit.v1_16_R2.entity;
/*    */ 
/*    */ import net.minecraft.server.v1_16_R2.Entity;
/*    */ import net.minecraft.server.v1_16_R2.EntityComplexPart;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.CraftServer;
/*    */ import org.bukkit.entity.ComplexEntityPart;
/*    */ import org.bukkit.entity.ComplexLivingEntity;
/*    */ import org.bukkit.entity.EntityType;
/*    */ import org.bukkit.event.entity.EntityDamageEvent;
/*    */ 
/*    */ public class CraftComplexPart extends CraftEntity implements ComplexEntityPart {
/*    */   public CraftComplexPart(CraftServer server, EntityComplexPart entity) {
/* 13 */     super(server, (Entity)entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public ComplexLivingEntity getParent() {
/* 18 */     return (ComplexLivingEntity)(getHandle()).owner.getBukkitEntity();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setLastDamageCause(EntityDamageEvent cause) {
/* 23 */     getParent().setLastDamageCause(cause);
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityDamageEvent getLastDamageCause() {
/* 28 */     return getParent().getLastDamageCause();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isValid() {
/* 33 */     return getParent().isValid();
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityComplexPart getHandle() {
/* 38 */     return (EntityComplexPart)this.entity;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 43 */     return "CraftComplexPart";
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityType getType() {
/* 48 */     return EntityType.UNKNOWN;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\entity\CraftComplexPart.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
/*    */ package org.bukkit.craftbukkit.v1_16_R2.entity;
/*    */ 
/*    */ import net.minecraft.server.v1_16_R2.Entity;
/*    */ import net.minecraft.server.v1_16_R2.EntityLlamaSpit;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.CraftServer;
/*    */ import org.bukkit.entity.EntityType;
/*    */ import org.bukkit.entity.LlamaSpit;
/*    */ import org.bukkit.projectiles.ProjectileSource;
/*    */ 
/*    */ public class CraftLlamaSpit extends AbstractProjectile implements LlamaSpit {
/*    */   public CraftLlamaSpit(CraftServer server, EntityLlamaSpit entity) {
/* 12 */     super(server, (Entity)entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityLlamaSpit getHandle() {
/* 17 */     return (EntityLlamaSpit)super.getHandle();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 22 */     return "CraftLlamaSpit";
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityType getType() {
/* 27 */     return EntityType.LLAMA_SPIT;
/*    */   }
/*    */ 
/*    */   
/*    */   public ProjectileSource getShooter() {
/* 32 */     return (getHandle().getShooter() != null) ? (ProjectileSource)getHandle().getShooter().getBukkitEntity() : null;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setShooter(ProjectileSource source) {
/* 37 */     getHandle().setShooter((source != null) ? (Entity)((CraftLivingEntity)source).getHandle() : null);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\entity\CraftLlamaSpit.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
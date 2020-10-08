/*    */ package org.bukkit.craftbukkit.v1_16_R2.entity;
/*    */ import net.minecraft.server.v1_16_R2.Entity;
/*    */ import net.minecraft.server.v1_16_R2.EntityArrow;
/*    */ import net.minecraft.server.v1_16_R2.EntitySpectralArrow;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.CraftServer;
/*    */ import org.bukkit.entity.EntityType;
/*    */ import org.bukkit.entity.SpectralArrow;
/*    */ 
/*    */ public class CraftSpectralArrow extends CraftArrow implements SpectralArrow {
/*    */   public CraftSpectralArrow(CraftServer server, EntitySpectralArrow entity) {
/* 11 */     super(server, (EntityArrow)entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public EntitySpectralArrow getHandle() {
/* 16 */     return (EntitySpectralArrow)this.entity;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 21 */     return "CraftSpectralArrow";
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityType getType() {
/* 26 */     return EntityType.SPECTRAL_ARROW;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getGlowingTicks() {
/* 31 */     return (getHandle()).duration;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setGlowingTicks(int duration) {
/* 36 */     (getHandle()).duration = duration;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\entity\CraftSpectralArrow.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
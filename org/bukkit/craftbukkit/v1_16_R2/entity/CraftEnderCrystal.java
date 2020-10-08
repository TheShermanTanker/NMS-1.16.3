/*    */ package org.bukkit.craftbukkit.v1_16_R2.entity;
/*    */ import net.minecraft.server.v1_16_R2.BlockPosition;
/*    */ import net.minecraft.server.v1_16_R2.Entity;
/*    */ import net.minecraft.server.v1_16_R2.EntityEnderCrystal;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.CraftServer;
/*    */ import org.bukkit.entity.EnderCrystal;
/*    */ import org.bukkit.entity.EntityType;
/*    */ 
/*    */ public class CraftEnderCrystal extends CraftEntity implements EnderCrystal {
/*    */   public CraftEnderCrystal(CraftServer server, EntityEnderCrystal entity) {
/* 12 */     super(server, (Entity)entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isShowingBottom() {
/* 17 */     return getHandle().isShowingBottom();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setShowingBottom(boolean showing) {
/* 22 */     getHandle().setShowingBottom(showing);
/*    */   }
/*    */ 
/*    */   
/*    */   public Location getBeamTarget() {
/* 27 */     BlockPosition pos = getHandle().getBeamTarget();
/* 28 */     return (pos == null) ? null : new Location(getWorld(), pos.getX(), pos.getY(), pos.getZ());
/*    */   }
/*    */ 
/*    */   
/*    */   public void setBeamTarget(Location location) {
/* 33 */     if (location == null)
/* 34 */     { getHandle().setBeamTarget((BlockPosition)null); }
/* 35 */     else { if (location.getWorld() != getWorld()) {
/* 36 */         throw new IllegalArgumentException("Cannot set beam target location to different world");
/*    */       }
/* 38 */       getHandle().setBeamTarget(new BlockPosition(location.getBlockX(), location.getBlockY(), location.getBlockZ())); }
/*    */   
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityEnderCrystal getHandle() {
/* 44 */     return (EntityEnderCrystal)this.entity;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 49 */     return "CraftEnderCrystal";
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityType getType() {
/* 54 */     return EntityType.ENDER_CRYSTAL;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\entity\CraftEnderCrystal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
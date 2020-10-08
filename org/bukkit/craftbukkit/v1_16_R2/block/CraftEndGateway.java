/*    */ package org.bukkit.craftbukkit.v1_16_R2.block;
/*    */ 
/*    */ import java.util.Objects;
/*    */ import net.minecraft.server.v1_16_R2.BlockPosition;
/*    */ import net.minecraft.server.v1_16_R2.TileEntity;
/*    */ import net.minecraft.server.v1_16_R2.TileEntityEndGateway;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.block.EndGateway;
/*    */ 
/*    */ public class CraftEndGateway extends CraftBlockEntityState<TileEntityEndGateway> implements EndGateway {
/*    */   public CraftEndGateway(Block block) {
/* 14 */     super(block, TileEntityEndGateway.class);
/*    */   }
/*    */   
/*    */   public CraftEndGateway(Material material, TileEntityEndGateway te) {
/* 18 */     super(material, te);
/*    */   }
/*    */ 
/*    */   
/*    */   public Location getExitLocation() {
/* 23 */     BlockPosition pos = (getSnapshot()).exitPortal;
/* 24 */     return (pos == null) ? null : new Location(isPlaced() ? getWorld() : null, pos.getX(), pos.getY(), pos.getZ());
/*    */   }
/*    */ 
/*    */   
/*    */   public void setExitLocation(Location location) {
/* 29 */     if (location == null)
/* 30 */     { (getSnapshot()).exitPortal = null; }
/* 31 */     else { if (!Objects.equals(location.getWorld(), isPlaced() ? getWorld() : null)) {
/* 32 */         throw new IllegalArgumentException("Cannot set exit location to different world");
/*    */       }
/* 34 */       (getSnapshot()).exitPortal = new BlockPosition(location.getBlockX(), location.getBlockY(), location.getBlockZ()); }
/*    */   
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isExactTeleport() {
/* 40 */     return (getSnapshot()).exactTeleport;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setExactTeleport(boolean exact) {
/* 45 */     (getSnapshot()).exactTeleport = exact;
/*    */   }
/*    */ 
/*    */   
/*    */   public long getAge() {
/* 50 */     return (getSnapshot()).age;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setAge(long age) {
/* 55 */     (getSnapshot()).age = age;
/*    */   }
/*    */ 
/*    */   
/*    */   public void applyTo(TileEntityEndGateway endGateway) {
/* 60 */     super.applyTo(endGateway);
/*    */     
/* 62 */     if ((getSnapshot()).exitPortal == null)
/* 63 */       endGateway.exitPortal = null; 
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\CraftEndGateway.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
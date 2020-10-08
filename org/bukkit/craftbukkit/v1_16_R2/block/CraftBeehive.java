/*    */ package org.bukkit.craftbukkit.v1_16_R2.block;
/*    */ 
/*    */ import com.google.common.base.Preconditions;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import net.minecraft.server.v1_16_R2.BlockPosition;
/*    */ import net.minecraft.server.v1_16_R2.Entity;
/*    */ import net.minecraft.server.v1_16_R2.TileEntityBeehive;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.block.Beehive;
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.entity.CraftBee;
/*    */ import org.bukkit.entity.Bee;
/*    */ import org.bukkit.entity.Entity;
/*    */ 
/*    */ public class CraftBeehive
/*    */   extends CraftBlockEntityState<TileEntityBeehive> implements Beehive {
/*    */   public CraftBeehive(Block block) {
/* 20 */     super(block, TileEntityBeehive.class);
/*    */   }
/*    */   
/*    */   public CraftBeehive(Material material, TileEntityBeehive te) {
/* 24 */     super(material, te);
/*    */   }
/*    */ 
/*    */   
/*    */   public Location getFlower() {
/* 29 */     BlockPosition flower = (getSnapshot()).flowerPos;
/* 30 */     return (flower == null) ? null : new Location(getWorld(), flower.getX(), flower.getY(), flower.getZ());
/*    */   }
/*    */ 
/*    */   
/*    */   public void setFlower(Location location) {
/* 35 */     Preconditions.checkArgument((location == null || getWorld().equals(location.getWorld())), "Flower must be in same world");
/* 36 */     (getSnapshot()).flowerPos = (location == null) ? null : new BlockPosition(location.getBlockX(), location.getBlockY(), location.getBlockZ());
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isFull() {
/* 41 */     return getSnapshot().isFull();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isSedated() {
/* 46 */     return (isPlaced() && getTileEntity().isSedated());
/*    */   }
/*    */ 
/*    */   
/*    */   public int getEntityCount() {
/* 51 */     return getSnapshot().getBeeCount();
/*    */   }
/*    */ 
/*    */   
/*    */   public int getMaxEntities() {
/* 56 */     return (getSnapshot()).maxBees;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setMaxEntities(int max) {
/* 61 */     Preconditions.checkArgument((max > 0), "Max bees must be more than 0");
/*    */     
/* 63 */     (getSnapshot()).maxBees = max;
/*    */   }
/*    */ 
/*    */   
/*    */   public List<Bee> releaseEntities() {
/* 68 */     List<Bee> bees = new ArrayList<>();
/*    */     
/* 70 */     if (isPlaced()) {
/* 71 */       TileEntityBeehive beehive = (TileEntityBeehive)getTileEntityFromWorld();
/* 72 */       for (Entity bee : beehive.releaseBees(getHandle(), TileEntityBeehive.ReleaseStatus.BEE_RELEASED, true)) {
/* 73 */         bees.add((Bee)bee.getBukkitEntity());
/*    */       }
/*    */     } 
/*    */     
/* 77 */     return bees;
/*    */   }
/*    */ 
/*    */   
/*    */   public void addEntity(Bee entity) {
/* 82 */     Preconditions.checkArgument((entity != null), "Entity must not be null");
/*    */     
/* 84 */     getSnapshot().addBee((Entity)((CraftBee)entity).getHandle(), false);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\CraftBeehive.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
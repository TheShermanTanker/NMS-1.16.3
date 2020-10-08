/*    */ package org.bukkit.craftbukkit.v1_16_R2.entity;
/*    */ import net.minecraft.server.v1_16_R2.Entity;
/*    */ import net.minecraft.server.v1_16_R2.EntityHanging;
/*    */ import net.minecraft.server.v1_16_R2.EnumDirection;
/*    */ import org.bukkit.block.BlockFace;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.CraftServer;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.block.CraftBlock;
/*    */ import org.bukkit.entity.EntityType;
/*    */ import org.bukkit.entity.Hanging;
/*    */ 
/*    */ public class CraftHanging extends CraftEntity implements Hanging {
/*    */   public CraftHanging(CraftServer server, EntityHanging entity) {
/* 13 */     super(server, (Entity)entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public BlockFace getAttachedFace() {
/* 18 */     return getFacing().getOppositeFace();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setFacingDirection(BlockFace face) {
/* 23 */     setFacingDirection(face, false);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean setFacingDirection(BlockFace face, boolean force) {
/* 28 */     EntityHanging hanging = getHandle();
/* 29 */     EnumDirection dir = hanging.getDirection();
/* 30 */     switch (face) {
/*    */       
/*    */       default:
/* 33 */         getHandle().setDirection(EnumDirection.SOUTH);
/*    */         break;
/*    */       case WEST:
/* 36 */         getHandle().setDirection(EnumDirection.WEST);
/*    */         break;
/*    */       case NORTH:
/* 39 */         getHandle().setDirection(EnumDirection.NORTH);
/*    */         break;
/*    */       case EAST:
/* 42 */         getHandle().setDirection(EnumDirection.EAST);
/*    */         break;
/*    */     } 
/* 45 */     if (!force && !hanging.survives()) {
/*    */       
/* 47 */       hanging.setDirection(dir);
/* 48 */       return false;
/*    */     } 
/* 50 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public BlockFace getFacing() {
/* 55 */     EnumDirection direction = getHandle().getDirection();
/* 56 */     if (direction == null) return BlockFace.SELF; 
/* 57 */     return CraftBlock.notchToBlockFace(direction);
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityHanging getHandle() {
/* 62 */     return (EntityHanging)this.entity;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 67 */     return "CraftHanging";
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityType getType() {
/* 72 */     return EntityType.UNKNOWN;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\entity\CraftHanging.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
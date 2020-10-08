/*    */ package org.bukkit.craftbukkit.v1_16_R2.util;
/*    */ 
/*    */ import net.minecraft.server.v1_16_R2.BlockPosition;
/*    */ import net.minecraft.server.v1_16_R2.MovingObjectPosition;
/*    */ import net.minecraft.server.v1_16_R2.MovingObjectPositionBlock;
/*    */ import net.minecraft.server.v1_16_R2.MovingObjectPositionEntity;
/*    */ import net.minecraft.server.v1_16_R2.Vec3D;
/*    */ import org.bukkit.World;
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.block.BlockFace;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.block.CraftBlock;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.entity.CraftEntity;
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.util.RayTraceResult;
/*    */ import org.bukkit.util.Vector;
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class CraftRayTraceResult
/*    */ {
/*    */   public static RayTraceResult fromNMS(World world, MovingObjectPosition nmsHitResult) {
/* 22 */     if (nmsHitResult == null || nmsHitResult.getType() == MovingObjectPosition.EnumMovingObjectType.MISS) return null;
/*    */     
/* 24 */     Vec3D nmsHitPos = nmsHitResult.getPos();
/* 25 */     Vector hitPosition = new Vector(nmsHitPos.x, nmsHitPos.y, nmsHitPos.z);
/* 26 */     BlockFace hitBlockFace = null;
/*    */     
/* 28 */     if (nmsHitResult.getType() == MovingObjectPosition.EnumMovingObjectType.ENTITY) {
/* 29 */       CraftEntity craftEntity = ((MovingObjectPositionEntity)nmsHitResult).getEntity().getBukkitEntity();
/* 30 */       return new RayTraceResult(hitPosition, (Entity)craftEntity, null);
/*    */     } 
/*    */     
/* 33 */     Block hitBlock = null;
/* 34 */     BlockPosition nmsBlockPos = null;
/* 35 */     if (nmsHitResult.getType() == MovingObjectPosition.EnumMovingObjectType.BLOCK) {
/* 36 */       MovingObjectPositionBlock blockHitResult = (MovingObjectPositionBlock)nmsHitResult;
/* 37 */       hitBlockFace = CraftBlock.notchToBlockFace(blockHitResult.getDirection());
/* 38 */       nmsBlockPos = blockHitResult.getBlockPosition();
/*    */     } 
/* 40 */     if (nmsBlockPos != null && world != null) {
/* 41 */       hitBlock = world.getBlockAt(nmsBlockPos.getX(), nmsBlockPos.getY(), nmsBlockPos.getZ());
/*    */     }
/* 43 */     return new RayTraceResult(hitPosition, hitBlock, hitBlockFace);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R\\util\CraftRayTraceResult.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
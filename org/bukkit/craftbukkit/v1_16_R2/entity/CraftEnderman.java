/*    */ package org.bukkit.craftbukkit.v1_16_R2.entity;
/*    */ import net.minecraft.server.v1_16_R2.Entity;
/*    */ import net.minecraft.server.v1_16_R2.EntityEnderman;
/*    */ import net.minecraft.server.v1_16_R2.EntityInsentient;
/*    */ import net.minecraft.server.v1_16_R2.EntityLiving;
/*    */ import net.minecraft.server.v1_16_R2.EntityMonster;
/*    */ import net.minecraft.server.v1_16_R2.IBlockData;
/*    */ import org.bukkit.block.data.BlockData;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.CraftServer;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.util.CraftMagicNumbers;
/*    */ import org.bukkit.entity.EntityType;
/*    */ import org.bukkit.material.MaterialData;
/*    */ 
/*    */ public class CraftEnderman extends CraftMonster implements Enderman {
/*    */   public CraftEnderman(CraftServer server, EntityEnderman entity) {
/* 16 */     super(server, (EntityMonster)entity);
/*    */   }
/*    */   public boolean teleportRandomly() {
/* 19 */     return getHandle().teleportRandomly();
/*    */   }
/*    */   public MaterialData getCarriedMaterial() {
/* 22 */     IBlockData blockData = getHandle().getCarried();
/* 23 */     return (blockData == null) ? Material.AIR.getNewData((byte)0) : CraftMagicNumbers.getMaterial(blockData);
/*    */   }
/*    */ 
/*    */   
/*    */   public BlockData getCarriedBlock() {
/* 28 */     IBlockData blockData = getHandle().getCarried();
/* 29 */     return (blockData == null) ? null : (BlockData)CraftBlockData.fromData(blockData);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCarriedMaterial(MaterialData data) {
/* 34 */     getHandle().setCarried(CraftMagicNumbers.getBlock(data));
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCarriedBlock(BlockData blockData) {
/* 39 */     getHandle().setCarried((blockData == null) ? null : ((CraftBlockData)blockData).getState());
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityEnderman getHandle() {
/* 44 */     return (EntityEnderman)this.entity;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 49 */     return "CraftEnderman";
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityType getType() {
/* 54 */     return EntityType.ENDERMAN;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\entity\CraftEnderman.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
/*    */ package org.bukkit.craftbukkit.v1_16_R2.entity;
/*    */ 
/*    */ import net.minecraft.server.v1_16_R2.Entity;
/*    */ import net.minecraft.server.v1_16_R2.EntityFallingBlock;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.block.data.BlockData;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.CraftServer;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.block.data.CraftBlockData;
/*    */ import org.bukkit.entity.EntityType;
/*    */ import org.bukkit.entity.FallingBlock;
/*    */ 
/*    */ public class CraftFallingBlock extends CraftEntity implements FallingBlock {
/*    */   public CraftFallingBlock(CraftServer server, EntityFallingBlock entity) {
/* 14 */     super(server, (Entity)entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityFallingBlock getHandle() {
/* 19 */     return (EntityFallingBlock)this.entity;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 24 */     return "CraftFallingBlock";
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityType getType() {
/* 29 */     return EntityType.FALLING_BLOCK;
/*    */   }
/*    */ 
/*    */   
/*    */   public Material getMaterial() {
/* 34 */     return getBlockData().getMaterial();
/*    */   }
/*    */ 
/*    */   
/*    */   public BlockData getBlockData() {
/* 39 */     return (BlockData)CraftBlockData.fromData(getHandle().getBlock());
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean getDropItem() {
/* 44 */     return (getHandle()).dropItem;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setDropItem(boolean drop) {
/* 49 */     (getHandle()).dropItem = drop;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean canHurtEntities() {
/* 54 */     return (getHandle()).hurtEntities;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setHurtEntities(boolean hurtEntities) {
/* 59 */     (getHandle()).hurtEntities = hurtEntities;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setTicksLived(int value) {
/* 64 */     super.setTicksLived(value);
/*    */ 
/*    */     
/* 67 */     (getHandle()).ticksLived = value;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\entity\CraftFallingBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
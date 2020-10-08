/*    */ package org.bukkit.craftbukkit.v1_16_R2.entity;
/*    */ import net.minecraft.server.v1_16_R2.BlockPosition;
/*    */ import net.minecraft.server.v1_16_R2.Entity;
/*    */ import net.minecraft.server.v1_16_R2.EntityCreature;
/*    */ import net.minecraft.server.v1_16_R2.EntityInsentient;
/*    */ import net.minecraft.server.v1_16_R2.EntityLiving;
/*    */ import net.minecraft.server.v1_16_R2.EntityMonster;
/*    */ import net.minecraft.server.v1_16_R2.EntityRaider;
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.CraftServer;
/*    */ 
/*    */ public abstract class CraftRaider extends CraftMonster implements Raider {
/*    */   public CraftRaider(CraftServer server, EntityRaider entity) {
/* 14 */     super(server, (EntityMonster)entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityRaider getHandle() {
/* 19 */     return (EntityRaider)super.getHandle();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 24 */     return "CraftRaider";
/*    */   }
/*    */ 
/*    */   
/*    */   public Block getPatrolTarget() {
/* 29 */     return (getHandle().getPatrolTarget() == null) ? null : (Block)CraftBlock.at((GeneratorAccess)(getHandle()).world, getHandle().getPatrolTarget());
/*    */   }
/*    */ 
/*    */   
/*    */   public void setPatrolTarget(Block block) {
/* 34 */     if (block == null) {
/* 35 */       getHandle().setPatrolTarget((BlockPosition)null);
/*    */     } else {
/* 37 */       Preconditions.checkArgument(block.getWorld().equals(getWorld()), "Block must be in same world");
/*    */       
/* 39 */       getHandle().setPatrolTarget(new BlockPosition(block.getX(), block.getY(), block.getZ()));
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isPatrolLeader() {
/* 45 */     return getHandle().isPatrolLeader();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setPatrolLeader(boolean leader) {
/* 50 */     getHandle().setPatrolLeader(leader);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isCanJoinRaid() {
/* 55 */     return getHandle().isCanJoinRaid();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCanJoinRaid(boolean join) {
/* 60 */     getHandle().setCanJoinRaid(join);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\entity\CraftRaider.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
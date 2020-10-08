/*     */ package org.bukkit.craftbukkit.v1_16_R2.entity;
/*     */ import net.minecraft.server.v1_16_R2.Blocks;
/*     */ import net.minecraft.server.v1_16_R2.Entity;
/*     */ import net.minecraft.server.v1_16_R2.EntityMinecartAbstract;
/*     */ import net.minecraft.server.v1_16_R2.IBlockData;
/*     */ import org.bukkit.block.data.BlockData;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.CraftServer;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.block.data.CraftBlockData;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.util.CraftMagicNumbers;
/*     */ import org.bukkit.entity.Minecart;
/*     */ import org.bukkit.material.MaterialData;
/*     */ import org.bukkit.util.Vector;
/*     */ 
/*     */ public abstract class CraftMinecart extends CraftVehicle implements Minecart {
/*     */   public CraftMinecart(CraftServer server, EntityMinecartAbstract entity) {
/*  16 */     super(server, (Entity)entity);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDamage(double damage) {
/*  21 */     getHandle().setDamage((float)damage);
/*     */   }
/*     */ 
/*     */   
/*     */   public double getDamage() {
/*  26 */     return getHandle().getDamage();
/*     */   }
/*     */ 
/*     */   
/*     */   public double getMaxSpeed() {
/*  31 */     return (getHandle()).maxSpeed;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMaxSpeed(double speed) {
/*  36 */     if (speed >= 0.0D) {
/*  37 */       (getHandle()).maxSpeed = speed;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isSlowWhenEmpty() {
/*  43 */     return (getHandle()).slowWhenEmpty;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSlowWhenEmpty(boolean slow) {
/*  48 */     (getHandle()).slowWhenEmpty = slow;
/*     */   }
/*     */ 
/*     */   
/*     */   public Vector getFlyingVelocityMod() {
/*  53 */     return getHandle().getFlyingVelocityMod();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFlyingVelocityMod(Vector flying) {
/*  58 */     getHandle().setFlyingVelocityMod(flying);
/*     */   }
/*     */ 
/*     */   
/*     */   public Vector getDerailedVelocityMod() {
/*  63 */     return getHandle().getDerailedVelocityMod();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDerailedVelocityMod(Vector derailed) {
/*  68 */     getHandle().setDerailedVelocityMod(derailed);
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityMinecartAbstract getHandle() {
/*  73 */     return (EntityMinecartAbstract)this.entity;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDisplayBlock(MaterialData material) {
/*  78 */     if (material != null) {
/*  79 */       IBlockData block = CraftMagicNumbers.getBlock(material);
/*  80 */       getHandle().setDisplayBlock(block);
/*     */     } else {
/*     */       
/*  83 */       getHandle().setDisplayBlock(Blocks.AIR.getBlockData());
/*  84 */       getHandle().a(false);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDisplayBlockData(BlockData blockData) {
/*  90 */     if (blockData != null) {
/*  91 */       IBlockData block = ((CraftBlockData)blockData).getState();
/*  92 */       getHandle().setDisplayBlock(block);
/*     */     } else {
/*     */       
/*  95 */       getHandle().setDisplayBlock(Blocks.AIR.getBlockData());
/*  96 */       getHandle().a(false);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public MaterialData getDisplayBlock() {
/* 102 */     IBlockData blockData = getHandle().getDisplayBlock();
/* 103 */     return CraftMagicNumbers.getMaterial(blockData);
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockData getDisplayBlockData() {
/* 108 */     IBlockData blockData = getHandle().getDisplayBlock();
/* 109 */     return (BlockData)CraftBlockData.fromData(blockData);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDisplayBlockOffset(int offset) {
/* 114 */     getHandle().setDisplayBlockOffset(offset);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getDisplayBlockOffset() {
/* 119 */     return getHandle().getDisplayBlockOffset();
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\entity\CraftMinecart.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
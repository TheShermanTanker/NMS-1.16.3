/*     */ package org.bukkit.craftbukkit.v1_16_R2.entity;
/*     */ import com.google.common.base.Preconditions;
/*     */ import net.minecraft.server.v1_16_R2.BlockPosition;
/*     */ import net.minecraft.server.v1_16_R2.Entity;
/*     */ import net.minecraft.server.v1_16_R2.EntityArrow;
/*     */ import org.apache.commons.lang.Validate;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.CraftServer;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftItemStack;
/*     */ import org.bukkit.entity.AbstractArrow;
/*     */ import org.bukkit.entity.EntityType;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.projectiles.ProjectileSource;
/*     */ 
/*     */ public class CraftArrow extends AbstractProjectile implements AbstractArrow {
/*     */   public CraftArrow(CraftServer server, EntityArrow entity) {
/*  17 */     super(server, (Entity)entity);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setKnockbackStrength(int knockbackStrength) {
/*  22 */     Validate.isTrue((knockbackStrength >= 0), "Knockback cannot be negative");
/*  23 */     getHandle().setKnockbackStrength(knockbackStrength);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getKnockbackStrength() {
/*  28 */     return (getHandle()).knockbackStrength;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getDamage() {
/*  33 */     return getHandle().getDamage();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDamage(double damage) {
/*  38 */     Preconditions.checkArgument((damage >= 0.0D), "Damage must be positive");
/*  39 */     getHandle().setDamage(damage);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getPierceLevel() {
/*  44 */     return getHandle().getPierceLevel();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPierceLevel(int pierceLevel) {
/*  49 */     Preconditions.checkArgument((0 <= pierceLevel && pierceLevel <= 127), "Pierce level out of range, expected 0 < level < 127");
/*     */     
/*  51 */     getHandle().setPierceLevel((byte)pierceLevel);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isCritical() {
/*  56 */     return getHandle().isCritical();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCritical(boolean critical) {
/*  61 */     getHandle().setCritical(critical);
/*     */   }
/*     */ 
/*     */   
/*     */   public ProjectileSource getShooter() {
/*  66 */     return (getHandle()).projectileSource;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setShooter(ProjectileSource shooter) {
/*  71 */     if (shooter instanceof org.bukkit.entity.Entity) {
/*  72 */       getHandle().setShooter(((CraftEntity)shooter).getHandle());
/*     */     } else {
/*  74 */       getHandle().setShooter(null);
/*     */     } 
/*  76 */     (getHandle()).projectileSource = shooter;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isInBlock() {
/*  81 */     return (getHandle()).inGround;
/*     */   }
/*     */ 
/*     */   
/*     */   public Block getAttachedBlock() {
/*  86 */     if (!isInBlock()) {
/*  87 */       return null;
/*     */     }
/*     */     
/*  90 */     BlockPosition pos = getHandle().getChunkCoordinates();
/*  91 */     return getWorld().getBlockAt(pos.getX(), pos.getY(), pos.getZ());
/*     */   }
/*     */ 
/*     */   
/*     */   public AbstractArrow.PickupStatus getPickupStatus() {
/*  96 */     return AbstractArrow.PickupStatus.values()[(getHandle()).fromPlayer.ordinal()];
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPickupStatus(AbstractArrow.PickupStatus status) {
/* 101 */     Preconditions.checkNotNull(status, "status");
/* 102 */     (getHandle()).fromPlayer = EntityArrow.PickupStatus.a(status.ordinal());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public CraftItemStack getItemStack() {
/* 108 */     return CraftItemStack.asCraftMirror(getHandle().getOriginalItemStack());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTicksLived(int value) {
/* 114 */     super.setTicksLived(value);
/*     */ 
/*     */     
/* 117 */     (getHandle()).despawnCounter = value;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isShotFromCrossbow() {
/* 122 */     return getHandle().isShotFromCrossbow();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setShotFromCrossbow(boolean shotFromCrossbow) {
/* 127 */     getHandle().setShotFromCrossbow(shotFromCrossbow);
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityArrow getHandle() {
/* 132 */     return (EntityArrow)this.entity;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 137 */     return "CraftArrow";
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityType getType() {
/* 142 */     return EntityType.UNKNOWN;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\entity\CraftArrow.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
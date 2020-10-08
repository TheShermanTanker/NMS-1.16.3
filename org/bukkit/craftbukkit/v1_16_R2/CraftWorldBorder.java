/*     */ package org.bukkit.craftbukkit.v1_16_R2;
/*     */ 
/*     */ import com.google.common.base.Preconditions;
/*     */ import net.minecraft.server.v1_16_R2.BlockPosition;
/*     */ import net.minecraft.server.v1_16_R2.WorldBorder;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.World;
/*     */ import org.bukkit.WorldBorder;
/*     */ 
/*     */ public class CraftWorldBorder implements WorldBorder {
/*     */   private final World world;
/*     */   private final WorldBorder handle;
/*     */   
/*     */   public CraftWorldBorder(CraftWorld world) {
/*  15 */     this.world = world;
/*  16 */     this.handle = world.getHandle().getWorldBorder();
/*     */   }
/*     */ 
/*     */   
/*     */   public void reset() {
/*  21 */     setSize(6.0E7D);
/*  22 */     setDamageAmount(0.2D);
/*  23 */     setDamageBuffer(5.0D);
/*  24 */     setWarningDistance(5);
/*  25 */     setWarningTime(15);
/*  26 */     setCenter(0.0D, 0.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   public double getSize() {
/*  31 */     return this.handle.getSize();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSize(double newSize) {
/*  36 */     setSize(newSize, 0L);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSize(double newSize, long time) {
/*  42 */     newSize = Math.min(6.0E7D, Math.max(1.0D, newSize));
/*  43 */     time = Math.min(9223372036854775L, Math.max(0L, time));
/*     */     
/*  45 */     if (time > 0L) {
/*  46 */       this.handle.transitionSizeBetween(this.handle.getSize(), newSize, time * 1000L);
/*     */     } else {
/*  48 */       this.handle.setSize(newSize);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public Location getCenter() {
/*  54 */     double x = this.handle.getCenterX();
/*  55 */     double z = this.handle.getCenterZ();
/*     */     
/*  57 */     return new Location(this.world, x, 0.0D, z);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCenter(double x, double z) {
/*  63 */     x = Math.min(3.0E7D, Math.max(-3.0E7D, x));
/*  64 */     z = Math.min(3.0E7D, Math.max(-3.0E7D, z));
/*     */     
/*  66 */     this.handle.setCenter(x, z);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCenter(Location location) {
/*  71 */     setCenter(location.getX(), location.getZ());
/*     */   }
/*     */ 
/*     */   
/*     */   public double getDamageBuffer() {
/*  76 */     return this.handle.getDamageBuffer();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDamageBuffer(double blocks) {
/*  81 */     this.handle.setDamageBuffer(blocks);
/*     */   }
/*     */ 
/*     */   
/*     */   public double getDamageAmount() {
/*  86 */     return this.handle.getDamageAmount();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDamageAmount(double damage) {
/*  91 */     this.handle.setDamageAmount(damage);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getWarningTime() {
/*  96 */     return this.handle.getWarningTime();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setWarningTime(int time) {
/* 101 */     this.handle.setWarningTime(time);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getWarningDistance() {
/* 106 */     return this.handle.getWarningDistance();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setWarningDistance(int distance) {
/* 111 */     this.handle.setWarningDistance(distance);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isInside(Location location) {
/* 116 */     Preconditions.checkArgument((location != null), "location");
/*     */     
/* 118 */     return (location.getWorld().equals(this.world) && this.handle.a(new BlockPosition(location.getX(), location.getY(), location.getZ())));
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\CraftWorldBorder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
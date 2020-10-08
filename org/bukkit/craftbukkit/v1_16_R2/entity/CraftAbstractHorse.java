/*     */ package org.bukkit.craftbukkit.v1_16_R2.entity;
/*     */ import java.util.UUID;
/*     */ import net.minecraft.server.v1_16_R2.Entity;
/*     */ import net.minecraft.server.v1_16_R2.EntityAgeable;
/*     */ import net.minecraft.server.v1_16_R2.EntityAnimal;
/*     */ import net.minecraft.server.v1_16_R2.EntityCreature;
/*     */ import net.minecraft.server.v1_16_R2.EntityHorseAbstract;
/*     */ import net.minecraft.server.v1_16_R2.EntityInsentient;
/*     */ import net.minecraft.server.v1_16_R2.EntityLiving;
/*     */ import org.apache.commons.lang.Validate;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.CraftServer;
/*     */ import org.bukkit.entity.AnimalTamer;
/*     */ import org.bukkit.inventory.AbstractHorseInventory;
/*     */ import org.bukkit.inventory.Inventory;
/*     */ 
/*     */ public abstract class CraftAbstractHorse extends CraftAnimals implements AbstractHorse {
/*     */   public CraftAbstractHorse(CraftServer server, EntityHorseAbstract entity) {
/*  18 */     super(server, (EntityAnimal)entity);
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityHorseAbstract getHandle() {
/*  23 */     return (EntityHorseAbstract)this.entity;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setVariant(Horse.Variant variant) {
/*  28 */     throw new UnsupportedOperationException("Not supported.");
/*     */   }
/*     */ 
/*     */   
/*     */   public int getDomestication() {
/*  33 */     return getHandle().getTemper();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDomestication(int value) {
/*  38 */     Validate.isTrue((value >= 0), "Domestication cannot be less than zero");
/*  39 */     Validate.isTrue((value <= getMaxDomestication()), "Domestication cannot be greater than the max domestication");
/*  40 */     getHandle().setTemper(value);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMaxDomestication() {
/*  45 */     return getHandle().getMaxDomestication();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMaxDomestication(int value) {
/*  50 */     Validate.isTrue((value > 0), "Max domestication cannot be zero or less");
/*  51 */     (getHandle()).maxDomestication = value;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getJumpStrength() {
/*  56 */     return getHandle().getJumpStrength();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setJumpStrength(double strength) {
/*  61 */     Validate.isTrue((strength >= 0.0D), "Jump strength cannot be less than zero");
/*  62 */     getHandle().getAttributeInstance(GenericAttributes.JUMP_STRENGTH).setValue(strength);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isTamed() {
/*  67 */     return getHandle().isTamed();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTamed(boolean tamed) {
/*  72 */     getHandle().setTamed(tamed);
/*     */   }
/*     */ 
/*     */   
/*     */   public AnimalTamer getOwner() {
/*  77 */     if (getOwnerUUID() == null) return null; 
/*  78 */     return (AnimalTamer)getServer().getOfflinePlayer(getOwnerUUID());
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOwner(AnimalTamer owner) {
/*  83 */     if (owner != null) {
/*  84 */       setTamed(true);
/*  85 */       getHandle().setGoalTarget(null, null, false);
/*  86 */       setOwnerUUID(owner.getUniqueId());
/*     */     } else {
/*  88 */       setTamed(false);
/*  89 */       setOwnerUUID((UUID)null);
/*     */     } 
/*     */   }
/*     */   
/*     */   public UUID getOwnerUniqueId() {
/*  94 */     return getOwnerUUID();
/*     */   }
/*     */   public UUID getOwnerUUID() {
/*  97 */     return getHandle().getOwnerUUID();
/*     */   }
/*     */   
/*     */   public void setOwnerUUID(UUID uuid) {
/* 101 */     getHandle().setOwnerUUID(uuid);
/*     */   }
/*     */ 
/*     */   
/*     */   public AbstractHorseInventory getInventory() {
/* 106 */     return (AbstractHorseInventory)new CraftSaddledInventory((IInventory)(getHandle()).inventoryChest);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\entity\CraftAbstractHorse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
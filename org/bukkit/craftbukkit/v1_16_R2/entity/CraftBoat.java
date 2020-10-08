/*     */ package org.bukkit.craftbukkit.v1_16_R2.entity;
/*     */ 
/*     */ import net.minecraft.server.v1_16_R2.Entity;
/*     */ import net.minecraft.server.v1_16_R2.EntityBoat;
/*     */ import org.bukkit.TreeSpecies;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.CraftServer;
/*     */ import org.bukkit.entity.Boat;
/*     */ import org.bukkit.entity.EntityType;
/*     */ 
/*     */ public class CraftBoat extends CraftVehicle implements Boat {
/*     */   public CraftBoat(CraftServer server, EntityBoat entity) {
/*  12 */     super(server, (Entity)entity);
/*     */   }
/*     */ 
/*     */   
/*     */   public TreeSpecies getWoodType() {
/*  17 */     return getTreeSpecies(getHandle().getType());
/*     */   }
/*     */ 
/*     */   
/*     */   public void setWoodType(TreeSpecies species) {
/*  22 */     getHandle().setType(getBoatType(species));
/*     */   }
/*     */ 
/*     */   
/*     */   public double getMaxSpeed() {
/*  27 */     return (getHandle()).maxSpeed;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMaxSpeed(double speed) {
/*  32 */     if (speed >= 0.0D) {
/*  33 */       (getHandle()).maxSpeed = speed;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public double getOccupiedDeceleration() {
/*  39 */     return (getHandle()).occupiedDeceleration;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOccupiedDeceleration(double speed) {
/*  44 */     if (speed >= 0.0D) {
/*  45 */       (getHandle()).occupiedDeceleration = speed;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public double getUnoccupiedDeceleration() {
/*  51 */     return (getHandle()).unoccupiedDeceleration;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setUnoccupiedDeceleration(double speed) {
/*  56 */     (getHandle()).unoccupiedDeceleration = speed;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getWorkOnLand() {
/*  61 */     return (getHandle()).landBoats;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setWorkOnLand(boolean workOnLand) {
/*  66 */     (getHandle()).landBoats = workOnLand;
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityBoat getHandle() {
/*  71 */     return (EntityBoat)this.entity;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/*  76 */     return "CraftBoat";
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityType getType() {
/*  81 */     return EntityType.BOAT;
/*     */   }
/*     */   
/*     */   public static TreeSpecies getTreeSpecies(EntityBoat.EnumBoatType boatType) {
/*  85 */     switch (boatType) {
/*     */       case REDWOOD:
/*  87 */         return TreeSpecies.REDWOOD;
/*     */       case BIRCH:
/*  89 */         return TreeSpecies.BIRCH;
/*     */       case JUNGLE:
/*  91 */         return TreeSpecies.JUNGLE;
/*     */       case ACACIA:
/*  93 */         return TreeSpecies.ACACIA;
/*     */       case DARK_OAK:
/*  95 */         return TreeSpecies.DARK_OAK;
/*     */     } 
/*     */     
/*  98 */     return TreeSpecies.GENERIC;
/*     */   }
/*     */ 
/*     */   
/*     */   public static EntityBoat.EnumBoatType getBoatType(TreeSpecies species) {
/* 103 */     switch (species) {
/*     */       case REDWOOD:
/* 105 */         return EntityBoat.EnumBoatType.SPRUCE;
/*     */       case BIRCH:
/* 107 */         return EntityBoat.EnumBoatType.BIRCH;
/*     */       case JUNGLE:
/* 109 */         return EntityBoat.EnumBoatType.JUNGLE;
/*     */       case ACACIA:
/* 111 */         return EntityBoat.EnumBoatType.ACACIA;
/*     */       case DARK_OAK:
/* 113 */         return EntityBoat.EnumBoatType.DARK_OAK;
/*     */     } 
/*     */     
/* 116 */     return EntityBoat.EnumBoatType.OAK;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\entity\CraftBoat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
/*    */ package org.bukkit.craftbukkit.v1_16_R2.entity;
/*    */ import com.google.common.base.Preconditions;
/*    */ import net.minecraft.server.v1_16_R2.BlockPosition;
/*    */ import net.minecraft.server.v1_16_R2.Entity;
/*    */ import net.minecraft.server.v1_16_R2.EntityAnimal;
/*    */ import net.minecraft.server.v1_16_R2.EntityBee;
/*    */ import net.minecraft.server.v1_16_R2.EntityLiving;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.CraftServer;
/*    */ import org.bukkit.entity.EntityType;
/*    */ 
/*    */ public class CraftBee extends CraftAnimals implements Bee {
/*    */   public CraftBee(CraftServer server, EntityBee entity) {
/* 14 */     super(server, (EntityAnimal)entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityBee getHandle() {
/* 19 */     return (EntityBee)this.entity;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 24 */     return "CraftBee";
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityType getType() {
/* 29 */     return EntityType.BEE;
/*    */   }
/*    */ 
/*    */   
/*    */   public Location getHive() {
/* 34 */     BlockPosition hive = getHandle().getHivePos();
/* 35 */     return (hive == null) ? null : new Location(getWorld(), hive.getX(), hive.getY(), hive.getZ());
/*    */   }
/*    */ 
/*    */   
/*    */   public void setHive(Location location) {
/* 40 */     Preconditions.checkArgument((location == null || getWorld().equals(location.getWorld())), "Hive must be in same world");
/* 41 */     (getHandle()).hivePos = (location == null) ? null : new BlockPosition(location.getBlockX(), location.getBlockY(), location.getBlockZ());
/*    */   }
/*    */ 
/*    */   
/*    */   public Location getFlower() {
/* 46 */     BlockPosition flower = getHandle().getFlowerPos();
/* 47 */     return (flower == null) ? null : new Location(getWorld(), flower.getX(), flower.getY(), flower.getZ());
/*    */   }
/*    */ 
/*    */   
/*    */   public void setFlower(Location location) {
/* 52 */     Preconditions.checkArgument((location == null || getWorld().equals(location.getWorld())), "Flower must be in same world");
/* 53 */     getHandle().setFlowerPos((location == null) ? null : new BlockPosition(location.getBlockX(), location.getBlockY(), location.getBlockZ()));
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean hasNectar() {
/* 58 */     return getHandle().hasNectar();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setHasNectar(boolean nectar) {
/* 63 */     getHandle().setHasNectar(nectar);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean hasStung() {
/* 68 */     return getHandle().hasStung();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setHasStung(boolean stung) {
/* 73 */     getHandle().setHasStung(stung);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getAnger() {
/* 78 */     return getHandle().getAnger();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setAnger(int anger) {
/* 83 */     getHandle().setAnger(anger);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getCannotEnterHiveTicks() {
/* 88 */     return (getHandle()).cannotEnterHiveTicks;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCannotEnterHiveTicks(int ticks) {
/* 93 */     getHandle().setCannotEnterHiveTicks(ticks);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\entity\CraftBee.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
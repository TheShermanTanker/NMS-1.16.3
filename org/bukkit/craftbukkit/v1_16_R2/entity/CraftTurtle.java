/*    */ package org.bukkit.craftbukkit.v1_16_R2.entity;
/*    */ import net.minecraft.server.v1_16_R2.Entity;
/*    */ import net.minecraft.server.v1_16_R2.EntityAnimal;
/*    */ import net.minecraft.server.v1_16_R2.EntityLiving;
/*    */ import net.minecraft.server.v1_16_R2.EntityTurtle;
/*    */ import net.minecraft.server.v1_16_R2.MCUtil;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.CraftServer;
/*    */ import org.bukkit.entity.EntityType;
/*    */ 
/*    */ public class CraftTurtle extends CraftAnimals implements Turtle {
/*    */   public CraftTurtle(CraftServer server, EntityTurtle entity) {
/* 13 */     super(server, (EntityAnimal)entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityTurtle getHandle() {
/* 18 */     return (EntityTurtle)super.getHandle();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 23 */     return "CraftTurtle";
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityType getType() {
/* 28 */     return EntityType.TURTLE;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Location getHome() {
/* 34 */     return MCUtil.toLocation((getHandle()).world, getHandle().getHomePos());
/*    */   }
/*    */ 
/*    */   
/*    */   public void setHome(Location location) {
/* 39 */     getHandle().setHomePos(MCUtil.toBlockPosition(location));
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isGoingHome() {
/* 44 */     return getHandle().isGoingHome();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isDigging() {
/* 49 */     return getHandle().isDigging();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean hasEgg() {
/* 54 */     return getHandle().hasEgg();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setHasEgg(boolean hasEgg) {
/* 59 */     getHandle().setHasEgg(hasEgg);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\entity\CraftTurtle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
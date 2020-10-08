/*    */ package org.bukkit.craftbukkit.v1_16_R2.entity;
/*    */ import net.minecraft.server.v1_16_R2.Entity;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.CraftServer;
/*    */ import org.bukkit.entity.Vehicle;
/*    */ 
/*    */ public abstract class CraftVehicle extends CraftEntity implements Vehicle {
/*    */   public CraftVehicle(CraftServer server, Entity entity) {
/*  8 */     super(server, entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 13 */     return "CraftVehicle{passenger=" + getPassenger() + '}';
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\entity\CraftVehicle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
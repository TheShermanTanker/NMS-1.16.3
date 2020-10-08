/*    */ package org.bukkit.craftbukkit.v1_16_R2.entity;
/*    */ import net.minecraft.server.v1_16_R2.Entity;
/*    */ import net.minecraft.server.v1_16_R2.EntityFlying;
/*    */ import net.minecraft.server.v1_16_R2.EntityInsentient;
/*    */ import net.minecraft.server.v1_16_R2.EntityLiving;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.CraftServer;
/*    */ 
/*    */ public class CraftFlying extends CraftMob implements Flying {
/*    */   public CraftFlying(CraftServer server, EntityFlying entity) {
/* 10 */     super(server, (EntityInsentient)entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityFlying getHandle() {
/* 15 */     return (EntityFlying)this.entity;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 20 */     return "CraftFlying";
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\entity\CraftFlying.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
/*    */ package org.bukkit.craftbukkit.v1_16_R2.entity;
/*    */ import net.minecraft.server.v1_16_R2.Entity;
/*    */ import net.minecraft.server.v1_16_R2.EntityFireball;
/*    */ import net.minecraft.server.v1_16_R2.EntityWitherSkull;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.CraftServer;
/*    */ import org.bukkit.entity.EntityType;
/*    */ 
/*    */ public class CraftWitherSkull extends CraftFireball implements WitherSkull {
/*    */   public CraftWitherSkull(CraftServer server, EntityWitherSkull entity) {
/* 10 */     super(server, (EntityFireball)entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCharged(boolean charged) {
/* 15 */     getHandle().setCharged(charged);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isCharged() {
/* 20 */     return getHandle().isCharged();
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityWitherSkull getHandle() {
/* 25 */     return (EntityWitherSkull)this.entity;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 30 */     return "CraftWitherSkull";
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityType getType() {
/* 35 */     return EntityType.WITHER_SKULL;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\entity\CraftWitherSkull.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
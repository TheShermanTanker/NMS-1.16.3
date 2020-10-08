/*    */ package org.bukkit.craftbukkit.v1_16_R2.entity;
/*    */ import net.minecraft.server.v1_16_R2.Entity;
/*    */ import net.minecraft.server.v1_16_R2.EntityFireballFireball;
/*    */ import net.minecraft.server.v1_16_R2.EntitySmallFireball;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.CraftServer;
/*    */ import org.bukkit.entity.EntityType;
/*    */ 
/*    */ public class CraftSmallFireball extends CraftSizedFireball implements SmallFireball {
/*    */   public CraftSmallFireball(CraftServer server, EntitySmallFireball entity) {
/* 10 */     super(server, (EntityFireballFireball)entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public EntitySmallFireball getHandle() {
/* 15 */     return (EntitySmallFireball)this.entity;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 20 */     return "CraftSmallFireball";
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityType getType() {
/* 25 */     return EntityType.SMALL_FIREBALL;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\entity\CraftSmallFireball.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
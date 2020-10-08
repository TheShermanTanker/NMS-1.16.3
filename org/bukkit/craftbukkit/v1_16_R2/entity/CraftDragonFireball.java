/*    */ package org.bukkit.craftbukkit.v1_16_R2.entity;
/*    */ import net.minecraft.server.v1_16_R2.EntityDragonFireball;
/*    */ import net.minecraft.server.v1_16_R2.EntityFireball;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.CraftServer;
/*    */ import org.bukkit.entity.DragonFireball;
/*    */ import org.bukkit.entity.EntityType;
/*    */ 
/*    */ public class CraftDragonFireball extends CraftFireball implements DragonFireball {
/*    */   public CraftDragonFireball(CraftServer server, EntityDragonFireball entity) {
/* 10 */     super(server, (EntityFireball)entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 15 */     return "CraftDragonFireball";
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityType getType() {
/* 20 */     return EntityType.DRAGON_FIREBALL;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\entity\CraftDragonFireball.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
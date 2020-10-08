/*    */ package org.bukkit.craftbukkit.v1_16_R2.entity;
/*    */ import net.minecraft.server.v1_16_R2.EntityMinecartAbstract;
/*    */ import net.minecraft.server.v1_16_R2.EntityMinecartMobSpawner;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.CraftServer;
/*    */ import org.bukkit.entity.EntityType;
/*    */ import org.bukkit.entity.minecart.SpawnerMinecart;
/*    */ 
/*    */ final class CraftMinecartMobSpawner extends CraftMinecart implements SpawnerMinecart {
/*    */   CraftMinecartMobSpawner(CraftServer server, EntityMinecartMobSpawner entity) {
/* 10 */     super(server, (EntityMinecartAbstract)entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 15 */     return "CraftMinecartMobSpawner";
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityType getType() {
/* 20 */     return EntityType.MINECART_MOB_SPAWNER;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\entity\CraftMinecartMobSpawner.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
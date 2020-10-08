/*    */ package org.bukkit.craftbukkit.v1_16_R2.entity;
/*    */ 
/*    */ import net.minecraft.server.v1_16_R2.EntityMinecartAbstract;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.CraftServer;
/*    */ import org.bukkit.entity.EntityType;
/*    */ import org.bukkit.entity.minecart.RideableMinecart;
/*    */ 
/*    */ public class CraftMinecartRideable extends CraftMinecart implements RideableMinecart {
/*    */   public CraftMinecartRideable(CraftServer server, EntityMinecartAbstract entity) {
/* 10 */     super(server, entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 15 */     return "CraftMinecartRideable";
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityType getType() {
/* 20 */     return EntityType.MINECART;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\entity\CraftMinecartRideable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
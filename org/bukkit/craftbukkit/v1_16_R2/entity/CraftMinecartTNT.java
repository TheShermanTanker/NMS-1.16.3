/*    */ package org.bukkit.craftbukkit.v1_16_R2.entity;
/*    */ import net.minecraft.server.v1_16_R2.EntityMinecartAbstract;
/*    */ import net.minecraft.server.v1_16_R2.EntityMinecartTNT;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.CraftServer;
/*    */ import org.bukkit.entity.EntityType;
/*    */ import org.bukkit.entity.minecart.ExplosiveMinecart;
/*    */ 
/*    */ final class CraftMinecartTNT extends CraftMinecart implements ExplosiveMinecart {
/*    */   CraftMinecartTNT(CraftServer server, EntityMinecartTNT entity) {
/* 10 */     super(server, (EntityMinecartAbstract)entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 15 */     return "CraftMinecartTNT";
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityType getType() {
/* 20 */     return EntityType.MINECART_TNT;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\entity\CraftMinecartTNT.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
/*    */ package org.bukkit.craftbukkit.v1_16_R2.entity;
/*    */ 
/*    */ import net.minecraft.server.v1_16_R2.EntityMinecartAbstract;
/*    */ import net.minecraft.server.v1_16_R2.EntityMinecartFurnace;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.CraftServer;
/*    */ import org.bukkit.entity.EntityType;
/*    */ import org.bukkit.entity.minecart.PoweredMinecart;
/*    */ 
/*    */ public class CraftMinecartFurnace extends CraftMinecart implements PoweredMinecart {
/*    */   public CraftMinecartFurnace(CraftServer server, EntityMinecartFurnace entity) {
/* 11 */     super(server, (EntityMinecartAbstract)entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 16 */     return "CraftMinecartFurnace";
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityType getType() {
/* 21 */     return EntityType.MINECART_FURNACE;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\entity\CraftMinecartFurnace.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
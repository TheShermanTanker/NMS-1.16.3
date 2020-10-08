/*    */ package org.bukkit.craftbukkit.v1_16_R2.entity;
/*    */ 
/*    */ import net.minecraft.server.v1_16_R2.EntityZombie;
/*    */ import net.minecraft.server.v1_16_R2.EntityZombieHusk;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.CraftServer;
/*    */ import org.bukkit.entity.EntityType;
/*    */ import org.bukkit.entity.Husk;
/*    */ 
/*    */ public class CraftHusk extends CraftZombie implements Husk {
/*    */   public CraftHusk(CraftServer server, EntityZombieHusk entity) {
/* 11 */     super(server, (EntityZombie)entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 16 */     return "CraftHusk";
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityType getType() {
/* 21 */     return EntityType.HUSK;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\entity\CraftHusk.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
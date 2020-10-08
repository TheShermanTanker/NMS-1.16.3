/*    */ package org.bukkit.craftbukkit.v1_16_R2.entity;
/*    */ import net.minecraft.server.v1_16_R2.Entity;
/*    */ import net.minecraft.server.v1_16_R2.EntityArrow;
/*    */ import net.minecraft.server.v1_16_R2.EntityThrownTrident;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.CraftServer;
/*    */ import org.bukkit.entity.EntityType;
/*    */ import org.bukkit.entity.Trident;
/*    */ 
/*    */ public class CraftTrident extends CraftArrow implements Trident {
/*    */   public CraftTrident(CraftServer server, EntityThrownTrident entity) {
/* 11 */     super(server, (EntityArrow)entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityThrownTrident getHandle() {
/* 16 */     return (EntityThrownTrident)super.getHandle();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 21 */     return "CraftTrident";
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityType getType() {
/* 26 */     return EntityType.TRIDENT;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\entity\CraftTrident.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
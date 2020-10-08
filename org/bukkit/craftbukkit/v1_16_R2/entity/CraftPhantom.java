/*    */ package org.bukkit.craftbukkit.v1_16_R2.entity;
/*    */ import net.minecraft.server.v1_16_R2.Entity;
/*    */ import net.minecraft.server.v1_16_R2.EntityFlying;
/*    */ import net.minecraft.server.v1_16_R2.EntityLiving;
/*    */ import net.minecraft.server.v1_16_R2.EntityPhantom;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.CraftServer;
/*    */ import org.bukkit.entity.EntityType;
/*    */ 
/*    */ public class CraftPhantom extends CraftFlying implements Phantom {
/*    */   public CraftPhantom(CraftServer server, EntityPhantom entity) {
/* 11 */     super(server, (EntityFlying)entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityPhantom getHandle() {
/* 16 */     return (EntityPhantom)super.getHandle();
/*    */   }
/*    */ 
/*    */   
/*    */   public int getSize() {
/* 21 */     return getHandle().getSize();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setSize(int sz) {
/* 26 */     getHandle().setSize(sz);
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 31 */     return "CraftPhantom";
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityType getType() {
/* 36 */     return EntityType.PHANTOM;
/*    */   }
/*    */ 
/*    */   
/*    */   public UUID getSpawningEntity() {
/* 41 */     return getHandle().getSpawningEntity();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\entity\CraftPhantom.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
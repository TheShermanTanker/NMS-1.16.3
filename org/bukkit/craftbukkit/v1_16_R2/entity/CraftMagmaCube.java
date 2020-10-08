/*    */ package org.bukkit.craftbukkit.v1_16_R2.entity;
/*    */ import net.minecraft.server.v1_16_R2.Entity;
/*    */ import net.minecraft.server.v1_16_R2.EntityLiving;
/*    */ import net.minecraft.server.v1_16_R2.EntityMagmaCube;
/*    */ import net.minecraft.server.v1_16_R2.EntitySlime;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.CraftServer;
/*    */ import org.bukkit.entity.EntityType;
/*    */ 
/*    */ public class CraftMagmaCube extends CraftSlime implements MagmaCube {
/*    */   public CraftMagmaCube(CraftServer server, EntityMagmaCube entity) {
/* 11 */     super(server, (EntitySlime)entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityMagmaCube getHandle() {
/* 16 */     return (EntityMagmaCube)this.entity;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 21 */     return "CraftMagmaCube";
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityType getType() {
/* 26 */     return EntityType.MAGMA_CUBE;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\entity\CraftMagmaCube.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
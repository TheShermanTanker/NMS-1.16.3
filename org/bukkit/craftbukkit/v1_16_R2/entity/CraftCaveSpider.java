/*    */ package org.bukkit.craftbukkit.v1_16_R2.entity;
/*    */ import net.minecraft.server.v1_16_R2.Entity;
/*    */ import net.minecraft.server.v1_16_R2.EntityCaveSpider;
/*    */ import net.minecraft.server.v1_16_R2.EntityLiving;
/*    */ import net.minecraft.server.v1_16_R2.EntityMonster;
/*    */ import net.minecraft.server.v1_16_R2.EntitySpider;
/*    */ 
/*    */ public class CraftCaveSpider extends CraftSpider implements CaveSpider {
/*    */   public CraftCaveSpider(CraftServer server, EntityCaveSpider entity) {
/* 10 */     super(server, (EntitySpider)entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityCaveSpider getHandle() {
/* 15 */     return (EntityCaveSpider)this.entity;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 20 */     return "CraftCaveSpider";
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityType getType() {
/* 25 */     return EntityType.CAVE_SPIDER;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\entity\CraftCaveSpider.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
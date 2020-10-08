/*    */ package org.bukkit.craftbukkit.v1_16_R2.entity;
/*    */ import net.minecraft.server.v1_16_R2.Entity;
/*    */ import net.minecraft.server.v1_16_R2.EntityBlaze;
/*    */ import net.minecraft.server.v1_16_R2.EntityLiving;
/*    */ import net.minecraft.server.v1_16_R2.EntityMonster;
/*    */ import org.bukkit.entity.EntityType;
/*    */ 
/*    */ public class CraftBlaze extends CraftMonster implements Blaze {
/*    */   public CraftBlaze(CraftServer server, EntityBlaze entity) {
/* 10 */     super(server, (EntityMonster)entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityBlaze getHandle() {
/* 15 */     return (EntityBlaze)this.entity;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 20 */     return "CraftBlaze";
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityType getType() {
/* 25 */     return EntityType.BLAZE;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\entity\CraftBlaze.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
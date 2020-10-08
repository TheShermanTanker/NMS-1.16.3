/*    */ package org.bukkit.craftbukkit.v1_16_R2.entity;
/*    */ import net.minecraft.server.v1_16_R2.Entity;
/*    */ import net.minecraft.server.v1_16_R2.EntityIllagerAbstract;
/*    */ import net.minecraft.server.v1_16_R2.EntityLiving;
/*    */ import net.minecraft.server.v1_16_R2.EntityMonster;
/*    */ import net.minecraft.server.v1_16_R2.EntityRaider;
/*    */ 
/*    */ public class CraftIllager extends CraftRaider implements Illager {
/*    */   public CraftIllager(CraftServer server, EntityIllagerAbstract entity) {
/* 10 */     super(server, (EntityRaider)entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityIllagerAbstract getHandle() {
/* 15 */     return (EntityIllagerAbstract)super.getHandle();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 20 */     return "CraftIllager";
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\entity\CraftIllager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
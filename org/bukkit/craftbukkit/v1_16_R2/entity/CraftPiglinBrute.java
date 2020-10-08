/*    */ package org.bukkit.craftbukkit.v1_16_R2.entity;
/*    */ import net.minecraft.server.v1_16_R2.Entity;
/*    */ import net.minecraft.server.v1_16_R2.EntityLiving;
/*    */ import net.minecraft.server.v1_16_R2.EntityMonster;
/*    */ import net.minecraft.server.v1_16_R2.EntityPiglinAbstract;
/*    */ import net.minecraft.server.v1_16_R2.EntityPiglinBrute;
/*    */ import org.bukkit.entity.EntityType;
/*    */ 
/*    */ public class CraftPiglinBrute extends CraftPiglinAbstract implements PiglinBrute {
/*    */   public CraftPiglinBrute(CraftServer server, EntityPiglinBrute entity) {
/* 11 */     super(server, (EntityPiglinAbstract)entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityPiglinBrute getHandle() {
/* 16 */     return (EntityPiglinBrute)super.getHandle();
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityType getType() {
/* 21 */     return EntityType.PIGLIN_BRUTE;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 26 */     return "CraftPiglinBrute";
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\entity\CraftPiglinBrute.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
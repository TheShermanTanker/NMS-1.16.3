/*    */ package org.bukkit.craftbukkit.v1_16_R2.entity;
/*    */ import net.minecraft.server.v1_16_R2.Entity;
/*    */ import net.minecraft.server.v1_16_R2.EntityIllagerAbstract;
/*    */ import net.minecraft.server.v1_16_R2.EntityIllagerIllusioner;
/*    */ import net.minecraft.server.v1_16_R2.EntityIllagerWizard;
/*    */ import net.minecraft.server.v1_16_R2.EntityLiving;
/*    */ import net.minecraft.server.v1_16_R2.EntityMonster;
/*    */ import org.bukkit.entity.EntityType;
/*    */ 
/*    */ public class CraftIllusioner extends CraftSpellcaster implements Illusioner, CraftRangedEntity<EntityIllagerIllusioner> {
/*    */   public CraftIllusioner(CraftServer server, EntityIllagerIllusioner entity) {
/* 12 */     super(server, (EntityIllagerWizard)entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityIllagerIllusioner getHandle() {
/* 17 */     return (EntityIllagerIllusioner)super.getHandle();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 22 */     return "CraftIllusioner";
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityType getType() {
/* 27 */     return EntityType.ILLUSIONER;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\entity\CraftIllusioner.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
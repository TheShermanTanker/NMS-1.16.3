/*    */ package org.bukkit.craftbukkit.v1_16_R2.entity;
/*    */ import net.minecraft.server.v1_16_R2.Entity;
/*    */ import net.minecraft.server.v1_16_R2.EntityEvoker;
/*    */ import net.minecraft.server.v1_16_R2.EntityIllagerAbstract;
/*    */ import net.minecraft.server.v1_16_R2.EntityIllagerWizard;
/*    */ import net.minecraft.server.v1_16_R2.EntitySheep;
/*    */ import org.bukkit.entity.Evoker;
/*    */ import org.bukkit.entity.Sheep;
/*    */ 
/*    */ public class CraftEvoker extends CraftSpellcaster implements Evoker {
/*    */   public CraftEvoker(CraftServer server, EntityEvoker entity) {
/* 12 */     super(server, (EntityIllagerWizard)entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityEvoker getHandle() {
/* 17 */     return (EntityEvoker)super.getHandle();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 22 */     return "CraftEvoker";
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityType getType() {
/* 27 */     return EntityType.EVOKER;
/*    */   }
/*    */ 
/*    */   
/*    */   public Evoker.Spell getCurrentSpell() {
/* 32 */     return Evoker.Spell.values()[getHandle().getSpell().ordinal()];
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCurrentSpell(Evoker.Spell spell) {
/* 37 */     getHandle().setSpell((spell == null) ? EntityIllagerWizard.Spell.NONE : EntityIllagerWizard.Spell.a(spell.ordinal()));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Sheep getWololoTarget() {
/* 43 */     EntitySheep sheep = getHandle().getWololoTarget();
/* 44 */     return (sheep == null) ? null : (Sheep)sheep.getBukkitEntity();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setWololoTarget(Sheep sheep) {
/* 49 */     getHandle().setWololoTarget((sheep == null) ? null : ((CraftSheep)sheep).getHandle());
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\entity\CraftEvoker.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
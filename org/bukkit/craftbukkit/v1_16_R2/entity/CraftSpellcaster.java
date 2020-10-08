/*    */ package org.bukkit.craftbukkit.v1_16_R2.entity;
/*    */ import net.minecraft.server.v1_16_R2.Entity;
/*    */ import net.minecraft.server.v1_16_R2.EntityIllagerAbstract;
/*    */ import net.minecraft.server.v1_16_R2.EntityIllagerWizard;
/*    */ import net.minecraft.server.v1_16_R2.EntityLiving;
/*    */ import net.minecraft.server.v1_16_R2.EntityMonster;
/*    */ import org.bukkit.entity.Spellcaster;
/*    */ 
/*    */ public class CraftSpellcaster extends CraftIllager implements Spellcaster {
/*    */   public CraftSpellcaster(CraftServer server, EntityIllagerWizard entity) {
/* 11 */     super(server, (EntityIllagerAbstract)entity);
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityIllagerWizard getHandle() {
/* 16 */     return (EntityIllagerWizard)super.getHandle();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 21 */     return "CraftSpellcaster";
/*    */   }
/*    */ 
/*    */   
/*    */   public Spellcaster.Spell getSpell() {
/* 26 */     return toBukkitSpell(getHandle().getSpell());
/*    */   }
/*    */ 
/*    */   
/*    */   public void setSpell(Spellcaster.Spell spell) {
/* 31 */     Preconditions.checkArgument((spell != null), "Use Spell.NONE");
/*    */     
/* 33 */     getHandle().setSpell(toNMSSpell(spell));
/*    */   }
/*    */   
/*    */   public static Spellcaster.Spell toBukkitSpell(EntityIllagerWizard.Spell spell) {
/* 37 */     return Spellcaster.Spell.valueOf(spell.name());
/*    */   }
/*    */   
/*    */   public static EntityIllagerWizard.Spell toNMSSpell(Spellcaster.Spell spell) {
/* 41 */     return EntityIllagerWizard.Spell.a(spell.ordinal());
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\entity\CraftSpellcaster.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
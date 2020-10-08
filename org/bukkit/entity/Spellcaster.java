/*    */ package org.bukkit.entity;
/*    */ 
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public interface Spellcaster
/*    */   extends Illager
/*    */ {
/*    */   @NotNull
/*    */   Spell getSpell();
/*    */   
/*    */   void setSpell(@NotNull Spell paramSpell);
/*    */   
/*    */   public enum Spell
/*    */   {
/* 18 */     NONE,
/*    */ 
/*    */ 
/*    */     
/* 22 */     SUMMON_VEX,
/*    */ 
/*    */ 
/*    */     
/* 26 */     FANGS,
/*    */ 
/*    */ 
/*    */     
/* 30 */     WOLOLO,
/*    */ 
/*    */ 
/*    */     
/* 34 */     DISAPPEAR,
/*    */ 
/*    */ 
/*    */     
/* 38 */     BLINDNESS;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\entity\Spellcaster.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
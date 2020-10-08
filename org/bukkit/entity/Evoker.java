/*    */ package org.bukkit.entity;
/*    */ 
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ public interface Evoker
/*    */   extends Spellcaster
/*    */ {
/*    */   @Deprecated
/*    */   @NotNull
/*    */   Spell getCurrentSpell();
/*    */   
/*    */   @Deprecated
/*    */   void setCurrentSpell(@Nullable Spell paramSpell);
/*    */   
/*    */   @Nullable
/*    */   Sheep getWololoTarget();
/*    */   
/*    */   void setWololoTarget(@Nullable Sheep paramSheep);
/*    */   
/*    */   @Deprecated
/*    */   public enum Spell {
/* 23 */     NONE,
/*    */ 
/*    */ 
/*    */     
/* 27 */     SUMMON,
/*    */ 
/*    */ 
/*    */     
/* 31 */     FANGS,
/*    */ 
/*    */ 
/*    */     
/* 35 */     WOLOLO,
/*    */ 
/*    */ 
/*    */     
/* 39 */     DISAPPEAR,
/*    */ 
/*    */ 
/*    */     
/* 43 */     BLINDNESS;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\entity\Evoker.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
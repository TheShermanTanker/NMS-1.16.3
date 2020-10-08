/*    */ package org.bukkit.potion;
/*    */ 
/*    */ import org.bukkit.Color;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public class PotionEffectTypeWrapper extends PotionEffectType {
/*    */   protected PotionEffectTypeWrapper(int id) {
/*  8 */     super(id);
/*    */   }
/*    */ 
/*    */   
/*    */   public double getDurationModifier() {
/* 13 */     return getType().getDurationModifier();
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public String getName() {
/* 19 */     return getType().getName();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public PotionEffectType getType() {
/* 29 */     return PotionEffectType.getById(getId());
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isInstant() {
/* 34 */     return getType().isInstant();
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public Color getColor() {
/* 40 */     return getType().getColor();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\potion\PotionEffectTypeWrapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
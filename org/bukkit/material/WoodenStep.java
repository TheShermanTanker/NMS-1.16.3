/*    */ package org.bukkit.material;
/*    */ 
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.TreeSpecies;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Deprecated
/*    */ public class WoodenStep
/*    */   extends Wood
/*    */ {
/* 16 */   protected static final Material DEFAULT_TYPE = Material.LEGACY_WOOD_STEP;
/*    */ 
/*    */   
/*    */   protected static final boolean DEFAULT_INVERTED = false;
/*    */ 
/*    */   
/*    */   public WoodenStep() {
/* 23 */     this(DEFAULT_SPECIES, false);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public WoodenStep(TreeSpecies species) {
/* 32 */     this(species, false);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public WoodenStep(TreeSpecies species, boolean inv) {
/* 43 */     super(DEFAULT_TYPE, species);
/* 44 */     setInverted(inv);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Deprecated
/*    */   public WoodenStep(Material type, byte data) {
/* 54 */     super(type, data);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isInverted() {
/* 64 */     return ((getData() & 0x8) != 0);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setInverted(boolean inv) {
/* 75 */     int dat = getData() & 0x7;
/* 76 */     if (inv) {
/* 77 */       dat |= 0x8;
/*    */     }
/* 79 */     setData((byte)dat);
/*    */   }
/*    */ 
/*    */   
/*    */   public WoodenStep clone() {
/* 84 */     return (WoodenStep)super.clone();
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 89 */     return super.toString() + " " + getSpecies() + (isInverted() ? " inverted" : "");
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\material\WoodenStep.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
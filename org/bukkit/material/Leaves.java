/*     */ package org.bukkit.material;
/*     */ 
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.TreeSpecies;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Deprecated
/*     */ public class Leaves
/*     */   extends Wood
/*     */ {
/*  18 */   protected static final Material DEFAULT_TYPE = Material.LEGACY_LEAVES;
/*     */ 
/*     */   
/*     */   protected static final boolean DEFAULT_DECAYABLE = true;
/*     */ 
/*     */   
/*     */   public Leaves() {
/*  25 */     this(DEFAULT_TYPE, DEFAULT_SPECIES, true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Leaves(TreeSpecies species) {
/*  34 */     this(DEFAULT_TYPE, species, true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Leaves(TreeSpecies species, boolean isDecayable) {
/*  45 */     this(DEFAULT_TYPE, species, isDecayable);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Leaves(Material type) {
/*  54 */     this(type, DEFAULT_SPECIES, true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Leaves(Material type, TreeSpecies species) {
/*  64 */     this(type, species, true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Leaves(Material type, TreeSpecies species, boolean isDecayable) {
/*  76 */     super(type, species);
/*  77 */     setDecayable(isDecayable);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public Leaves(Material type, byte data) {
/*  87 */     super(type, data);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isDecaying() {
/*  96 */     return ((getData() & 0x8) != 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDecaying(boolean isDecaying) {
/* 105 */     setData(
/*     */         
/* 107 */         (byte)(getData() & 0x3 | (isDecaying ? 8 : (getData() & 0x4))));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isDecayable() {
/* 118 */     return ((getData() & 0x4) == 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDecayable(boolean isDecayable) {
/* 127 */     setData(
/*     */         
/* 129 */         (byte)(getData() & 0x3 | (isDecayable ? (getData() & 0x8) : 4)));
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 134 */     return getSpecies() + (isDecayable() ? " DECAYABLE " : " PERMANENT ") + (isDecaying() ? " DECAYING " : " ") + super.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   public Leaves clone() {
/* 139 */     return (Leaves)super.clone();
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\material\Leaves.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
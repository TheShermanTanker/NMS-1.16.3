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
/*     */ 
/*     */ 
/*     */ @Deprecated
/*     */ public class Sapling
/*     */   extends Wood
/*     */ {
/*     */   public Sapling() {
/*  21 */     this(DEFAULT_SPECIES);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Sapling(TreeSpecies species) {
/*  30 */     this(species, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Sapling(TreeSpecies species, boolean isInstantGrowable) {
/*  41 */     this(Material.LEGACY_SAPLING, species, isInstantGrowable);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Sapling(Material type) {
/*  50 */     this(type, DEFAULT_SPECIES, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Sapling(Material type, TreeSpecies species) {
/*  60 */     this(type, species, false);
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
/*     */   
/*     */   public Sapling(Material type, TreeSpecies species, boolean isInstantGrowable) {
/*  73 */     super(type, species);
/*  74 */     setIsInstantGrowable(isInstantGrowable);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public Sapling(Material type, byte data) {
/*  84 */     super(type, data);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isInstantGrowable() {
/*  93 */     return ((getData() & 0x8) == 8);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setIsInstantGrowable(boolean isInstantGrowable) {
/* 103 */     setData(isInstantGrowable ? (byte)(getData() & 0x7 | 0x8) : (byte)(getData() & 0x7));
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 108 */     return getSpecies() + " " + (isInstantGrowable() ? " IS_INSTANT_GROWABLE " : "") + " " + super.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   public Sapling clone() {
/* 113 */     return (Sapling)super.clone();
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\material\Sapling.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
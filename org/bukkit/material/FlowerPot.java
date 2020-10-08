/*     */ package org.bukkit.material;
/*     */ 
/*     */ import org.bukkit.GrassSpecies;
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
/*     */ public class FlowerPot
/*     */   extends MaterialData
/*     */ {
/*     */   public FlowerPot() {
/*  20 */     super(Material.LEGACY_FLOWER_POT);
/*     */   }
/*     */   
/*     */   public FlowerPot(Material type) {
/*  24 */     super(type);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public FlowerPot(Material type, byte data) {
/*  34 */     super(type, data);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MaterialData getContents() {
/*  44 */     switch (getData()) {
/*     */       case 1:
/*  46 */         return new MaterialData(Material.LEGACY_RED_ROSE);
/*     */       case 2:
/*  48 */         return new MaterialData(Material.LEGACY_YELLOW_FLOWER);
/*     */       case 3:
/*  50 */         return new Tree(TreeSpecies.GENERIC);
/*     */       case 4:
/*  52 */         return new Tree(TreeSpecies.REDWOOD);
/*     */       case 5:
/*  54 */         return new Tree(TreeSpecies.BIRCH);
/*     */       case 6:
/*  56 */         return new Tree(TreeSpecies.JUNGLE);
/*     */       case 7:
/*  58 */         return new MaterialData(Material.LEGACY_RED_MUSHROOM);
/*     */       case 8:
/*  60 */         return new MaterialData(Material.LEGACY_BROWN_MUSHROOM);
/*     */       case 9:
/*  62 */         return new MaterialData(Material.LEGACY_CACTUS);
/*     */       case 10:
/*  64 */         return new MaterialData(Material.LEGACY_DEAD_BUSH);
/*     */       case 11:
/*  66 */         return new LongGrass(GrassSpecies.FERN_LIKE);
/*     */     } 
/*  68 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setContents(MaterialData materialData) {
/*  78 */     Material mat = materialData.getItemType();
/*     */     
/*  80 */     if (mat == Material.LEGACY_RED_ROSE) {
/*  81 */       setData((byte)1);
/*  82 */     } else if (mat == Material.LEGACY_YELLOW_FLOWER) {
/*  83 */       setData((byte)2);
/*  84 */     } else if (mat == Material.LEGACY_RED_MUSHROOM) {
/*  85 */       setData((byte)7);
/*  86 */     } else if (mat == Material.LEGACY_BROWN_MUSHROOM) {
/*  87 */       setData((byte)8);
/*  88 */     } else if (mat == Material.LEGACY_CACTUS) {
/*  89 */       setData((byte)9);
/*  90 */     } else if (mat == Material.LEGACY_DEAD_BUSH) {
/*  91 */       setData((byte)10);
/*  92 */     } else if (mat == Material.LEGACY_SAPLING) {
/*  93 */       TreeSpecies species = ((Tree)materialData).getSpecies();
/*     */       
/*  95 */       if (species == TreeSpecies.GENERIC) {
/*  96 */         setData((byte)3);
/*  97 */       } else if (species == TreeSpecies.REDWOOD) {
/*  98 */         setData((byte)4);
/*  99 */       } else if (species == TreeSpecies.BIRCH) {
/* 100 */         setData((byte)5);
/*     */       } else {
/* 102 */         setData((byte)6);
/*     */       } 
/* 104 */     } else if (mat == Material.LEGACY_LONG_GRASS) {
/* 105 */       GrassSpecies species = ((LongGrass)materialData).getSpecies();
/*     */       
/* 107 */       if (species == GrassSpecies.FERN_LIKE) {
/* 108 */         setData((byte)11);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 115 */     return super.toString() + " containing " + getContents();
/*     */   }
/*     */ 
/*     */   
/*     */   public FlowerPot clone() {
/* 120 */     return (FlowerPot)super.clone();
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\material\FlowerPot.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
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
/*     */ public class Wood
/*     */   extends MaterialData
/*     */ {
/*  18 */   protected static final Material DEFAULT_TYPE = Material.LEGACY_WOOD;
/*  19 */   protected static final TreeSpecies DEFAULT_SPECIES = TreeSpecies.GENERIC;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Wood() {
/*  25 */     this(DEFAULT_TYPE, DEFAULT_SPECIES);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Wood(TreeSpecies species) {
/*  34 */     this(DEFAULT_TYPE, species);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Wood(Material type) {
/*  43 */     this(type, DEFAULT_SPECIES);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Wood(Material type, TreeSpecies species) {
/*  54 */     super(getSpeciesType(type, species));
/*  55 */     setSpecies(species);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public Wood(Material type, byte data) {
/*  65 */     super(type, data);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TreeSpecies getSpecies() {
/*  74 */     switch (getItemType()) {
/*     */       case GENERIC:
/*     */       case REDWOOD:
/*  77 */         return TreeSpecies.getByData(getData());
/*     */       case BIRCH:
/*     */       case JUNGLE:
/*  80 */         return TreeSpecies.getByData((byte)(getData() & 0x3));
/*     */       case ACACIA:
/*     */       case DARK_OAK:
/*  83 */         return TreeSpecies.getByData((byte)(getData() & 0x3 | 0x4));
/*     */       case null:
/*     */       case null:
/*  86 */         return TreeSpecies.getByData((byte)(getData() & 0x7));
/*     */     } 
/*  88 */     throw new IllegalArgumentException("Invalid block type for tree species");
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
/*     */   private static Material getSpeciesType(Material type, TreeSpecies species) {
/* 100 */     switch (species) {
/*     */       case GENERIC:
/*     */       case REDWOOD:
/*     */       case BIRCH:
/*     */       case JUNGLE:
/* 105 */         switch (type) {
/*     */           case ACACIA:
/* 107 */             return Material.LEGACY_LOG;
/*     */           case DARK_OAK:
/* 109 */             return Material.LEGACY_LEAVES;
/*     */         } 
/*     */         
/*     */         break;
/*     */       case ACACIA:
/*     */       case DARK_OAK:
/* 115 */         switch (type) {
/*     */           case BIRCH:
/* 117 */             return Material.LEGACY_LOG_2;
/*     */           case JUNGLE:
/* 119 */             return Material.LEGACY_LEAVES_2;
/*     */         } 
/*     */         
/*     */         break;
/*     */     } 
/* 124 */     return type;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSpecies(TreeSpecies species) {
/* 133 */     boolean firstType = false;
/* 134 */     switch (getItemType()) {
/*     */       case GENERIC:
/*     */       case REDWOOD:
/* 137 */         setData(species.getData());
/*     */         return;
/*     */       case BIRCH:
/*     */       case JUNGLE:
/* 141 */         firstType = true;
/*     */       
/*     */       case ACACIA:
/*     */       case DARK_OAK:
/* 145 */         switch (species) {
/*     */           case GENERIC:
/*     */           case REDWOOD:
/*     */           case BIRCH:
/*     */           case JUNGLE:
/* 150 */             if (!firstType) {
/* 151 */               throw new IllegalArgumentException("Invalid tree species for block type, use block type 2 instead");
/*     */             }
/*     */             break;
/*     */           case ACACIA:
/*     */           case DARK_OAK:
/* 156 */             if (firstType) {
/* 157 */               throw new IllegalArgumentException("Invalid tree species for block type 2, use block type instead");
/*     */             }
/*     */             break;
/*     */         } 
/* 161 */         setData((byte)(getData() & 0xC | species.getData() & 0x3));
/*     */         return;
/*     */       case null:
/*     */       case null:
/* 165 */         setData((byte)(getData() & 0x8 | species.getData()));
/*     */         return;
/*     */     } 
/* 168 */     throw new IllegalArgumentException("Invalid block type for tree species");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 174 */     return getSpecies() + " " + super.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   public Wood clone() {
/* 179 */     return (Wood)super.clone();
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\material\Wood.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
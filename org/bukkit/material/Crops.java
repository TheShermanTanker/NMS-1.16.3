/*     */ package org.bukkit.material;
/*     */ 
/*     */ import org.bukkit.CropState;
/*     */ import org.bukkit.Material;
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
/*     */ public class Crops
/*     */   extends MaterialData
/*     */ {
/*  20 */   protected static final Material DEFAULT_TYPE = Material.LEGACY_CROPS;
/*  21 */   protected static final CropState DEFAULT_STATE = CropState.SEEDED;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Crops() {
/*  27 */     this(DEFAULT_TYPE, DEFAULT_STATE);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Crops(CropState state) {
/*  36 */     this(DEFAULT_TYPE, state);
/*  37 */     setState(state);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Crops(Material type, CropState state) {
/*  47 */     super(type);
/*  48 */     setState(state);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Crops(Material type) {
/*  57 */     this(type, DEFAULT_STATE);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public Crops(Material type, byte data) {
/*  67 */     super(type, data);
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
/*     */   public CropState getState() {
/*  79 */     switch (getItemType()) {
/*     */       
/*     */       case LEGACY_CROPS:
/*     */       case LEGACY_CARROT:
/*     */       case LEGACY_POTATO:
/*  84 */         return CropState.getByData((byte)(getData() & 0x7));
/*     */ 
/*     */       
/*     */       case LEGACY_BEETROOT_BLOCK:
/*     */       case LEGACY_NETHER_WARTS:
/*  89 */         return CropState.getByData((byte)(((getData() & 0x3) * 7 + 2) / 3));
/*     */     } 
/*  91 */     throw new IllegalArgumentException("Block type is not a crop");
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setState(CropState state) {
/* 110 */     switch (getItemType()) {
/*     */       
/*     */       case LEGACY_CROPS:
/*     */       case LEGACY_CARROT:
/*     */       case LEGACY_POTATO:
/* 115 */         setData((byte)(getData() & 0x8 | state.getData()));
/*     */         return;
/*     */       
/*     */       case LEGACY_BEETROOT_BLOCK:
/*     */       case LEGACY_NETHER_WARTS:
/* 120 */         setData((byte)(getData() & 0xC | state.getData() >> 1));
/*     */         return;
/*     */     } 
/* 123 */     throw new IllegalArgumentException("Block type is not a crop");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 129 */     return getState() + " " + super.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   public Crops clone() {
/* 134 */     return (Crops)super.clone();
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\material\Crops.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
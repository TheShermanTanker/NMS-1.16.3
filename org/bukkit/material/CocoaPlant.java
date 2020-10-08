/*     */ package org.bukkit.material;
/*     */ 
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.block.BlockFace;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Deprecated
/*     */ public class CocoaPlant
/*     */   extends MaterialData
/*     */   implements Directional, Attachable
/*     */ {
/*     */   public enum CocoaPlantSize
/*     */   {
/*  16 */     SMALL,
/*  17 */     MEDIUM,
/*  18 */     LARGE;
/*     */   }
/*     */   
/*     */   public CocoaPlant() {
/*  22 */     super(Material.LEGACY_COCOA);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public CocoaPlant(Material type, byte data) {
/*  32 */     super(type, data);
/*     */   }
/*     */   
/*     */   public CocoaPlant(CocoaPlantSize sz) {
/*  36 */     this();
/*  37 */     setSize(sz);
/*     */   }
/*     */   
/*     */   public CocoaPlant(CocoaPlantSize sz, BlockFace dir) {
/*  41 */     this();
/*  42 */     setSize(sz);
/*  43 */     setFacingDirection(dir);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CocoaPlantSize getSize() {
/*  52 */     switch (getData() & 0xC) {
/*     */       case 0:
/*  54 */         return CocoaPlantSize.SMALL;
/*     */       case 4:
/*  56 */         return CocoaPlantSize.MEDIUM;
/*     */     } 
/*  58 */     return CocoaPlantSize.LARGE;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSize(CocoaPlantSize sz) {
/*  68 */     int dat = getData() & 0x3;
/*  69 */     switch (sz) {
/*     */ 
/*     */       
/*     */       case WEST:
/*  73 */         dat |= 0x4;
/*     */         break;
/*     */       case NORTH:
/*  76 */         dat |= 0x8;
/*     */         break;
/*     */     } 
/*  79 */     setData((byte)dat);
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockFace getAttachedFace() {
/*  84 */     return getFacing().getOppositeFace();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFacingDirection(BlockFace face) {
/*  89 */     int dat = getData() & 0xC;
/*  90 */     switch (face) {
/*     */ 
/*     */ 
/*     */       
/*     */       case WEST:
/*  95 */         dat |= 0x1;
/*     */         break;
/*     */       case NORTH:
/*  98 */         dat |= 0x2;
/*     */         break;
/*     */       case EAST:
/* 101 */         dat |= 0x3;
/*     */         break;
/*     */     } 
/* 104 */     setData((byte)dat);
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockFace getFacing() {
/* 109 */     switch (getData() & 0x3) {
/*     */       case 0:
/* 111 */         return BlockFace.SOUTH;
/*     */       case 1:
/* 113 */         return BlockFace.WEST;
/*     */       case 2:
/* 115 */         return BlockFace.NORTH;
/*     */       case 3:
/* 117 */         return BlockFace.EAST;
/*     */     } 
/* 119 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public CocoaPlant clone() {
/* 124 */     return (CocoaPlant)super.clone();
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 129 */     return super.toString() + " facing " + getFacing() + " " + getSize();
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\material\CocoaPlant.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
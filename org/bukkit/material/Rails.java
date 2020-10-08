/*     */ package org.bukkit.material;
/*     */ 
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.block.BlockFace;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Deprecated
/*     */ public class Rails
/*     */   extends MaterialData
/*     */ {
/*     */   public Rails() {
/*  16 */     super(Material.LEGACY_RAILS);
/*     */   }
/*     */   
/*     */   public Rails(Material type) {
/*  20 */     super(type);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public Rails(Material type, byte data) {
/*  30 */     super(type, data);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isOnSlope() {
/*  37 */     byte d = getConvertedData();
/*     */     
/*  39 */     return (d == 2 || d == 3 || d == 4 || d == 5);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isCurve() {
/*  46 */     byte d = getConvertedData();
/*     */     
/*  48 */     return (d == 6 || d == 7 || d == 8 || d == 9);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BlockFace getDirection() {
/*  59 */     byte d = getConvertedData();
/*     */     
/*  61 */     switch (d) {
/*     */       
/*     */       default:
/*  64 */         return BlockFace.SOUTH;
/*     */       
/*     */       case 1:
/*  67 */         return BlockFace.EAST;
/*     */       
/*     */       case 2:
/*  70 */         return BlockFace.EAST;
/*     */       
/*     */       case 3:
/*  73 */         return BlockFace.WEST;
/*     */       
/*     */       case 4:
/*  76 */         return BlockFace.NORTH;
/*     */       
/*     */       case 5:
/*  79 */         return BlockFace.SOUTH;
/*     */       
/*     */       case 6:
/*  82 */         return BlockFace.NORTH_WEST;
/*     */       
/*     */       case 7:
/*  85 */         return BlockFace.NORTH_EAST;
/*     */       
/*     */       case 8:
/*  88 */         return BlockFace.SOUTH_EAST;
/*     */       case 9:
/*     */         break;
/*  91 */     }  return BlockFace.SOUTH_WEST;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/*  97 */     return super.toString() + " facing " + getDirection() + (isCurve() ? " on a curve" : (isOnSlope() ? " on a slope" : ""));
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
/*     */   @Deprecated
/*     */   protected byte getConvertedData() {
/* 110 */     return getData();
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
/*     */   public void setDirection(BlockFace face, boolean isOnSlope) {
/* 124 */     switch (face) {
/*     */       case EAST:
/* 126 */         setData((byte)(isOnSlope ? 2 : 1));
/*     */         break;
/*     */       
/*     */       case WEST:
/* 130 */         setData((byte)(isOnSlope ? 3 : 1));
/*     */         break;
/*     */       
/*     */       case NORTH:
/* 134 */         setData((byte)(isOnSlope ? 4 : 0));
/*     */         break;
/*     */       
/*     */       case SOUTH:
/* 138 */         setData((byte)(isOnSlope ? 5 : 0));
/*     */         break;
/*     */       
/*     */       case NORTH_WEST:
/* 142 */         setData((byte)6);
/*     */         break;
/*     */       
/*     */       case NORTH_EAST:
/* 146 */         setData((byte)7);
/*     */         break;
/*     */       
/*     */       case SOUTH_EAST:
/* 150 */         setData((byte)8);
/*     */         break;
/*     */       
/*     */       case SOUTH_WEST:
/* 154 */         setData((byte)9);
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public Rails clone() {
/* 161 */     return (Rails)super.clone();
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\material\Rails.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
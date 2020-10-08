/*     */ package org.bukkit.material;
/*     */ 
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.block.BlockFace;
/*     */ 
/*     */ 
/*     */ 
/*     */ @Deprecated
/*     */ public class Banner
/*     */   extends MaterialData
/*     */   implements Attachable
/*     */ {
/*     */   public Banner() {
/*  14 */     super(Material.LEGACY_BANNER);
/*     */   }
/*     */   
/*     */   public Banner(Material type) {
/*  18 */     super(type);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public Banner(Material type, byte data) {
/*  28 */     super(type, data);
/*     */   }
/*     */   
/*     */   public boolean isWallBanner() {
/*  32 */     return (getItemType() == Material.LEGACY_WALL_BANNER);
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockFace getAttachedFace() {
/*  37 */     if (isWallBanner()) {
/*  38 */       byte data = getData();
/*     */       
/*  40 */       switch (data) {
/*     */         case 2:
/*  42 */           return BlockFace.SOUTH;
/*     */         
/*     */         case 3:
/*  45 */           return BlockFace.NORTH;
/*     */         
/*     */         case 4:
/*  48 */           return BlockFace.EAST;
/*     */         
/*     */         case 5:
/*  51 */           return BlockFace.WEST;
/*     */       } 
/*     */       
/*  54 */       return null;
/*     */     } 
/*  56 */     return BlockFace.DOWN;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public BlockFace getFacing() {
/*  62 */     byte data = getData();
/*     */     
/*  64 */     if (!isWallBanner()) {
/*  65 */       switch (data) {
/*     */         case 0:
/*  67 */           return BlockFace.SOUTH;
/*     */         
/*     */         case 1:
/*  70 */           return BlockFace.SOUTH_SOUTH_WEST;
/*     */         
/*     */         case 2:
/*  73 */           return BlockFace.SOUTH_WEST;
/*     */         
/*     */         case 3:
/*  76 */           return BlockFace.WEST_SOUTH_WEST;
/*     */         
/*     */         case 4:
/*  79 */           return BlockFace.WEST;
/*     */         
/*     */         case 5:
/*  82 */           return BlockFace.WEST_NORTH_WEST;
/*     */         
/*     */         case 6:
/*  85 */           return BlockFace.NORTH_WEST;
/*     */         
/*     */         case 7:
/*  88 */           return BlockFace.NORTH_NORTH_WEST;
/*     */         
/*     */         case 8:
/*  91 */           return BlockFace.NORTH;
/*     */         
/*     */         case 9:
/*  94 */           return BlockFace.NORTH_NORTH_EAST;
/*     */         
/*     */         case 10:
/*  97 */           return BlockFace.NORTH_EAST;
/*     */         
/*     */         case 11:
/* 100 */           return BlockFace.EAST_NORTH_EAST;
/*     */         
/*     */         case 12:
/* 103 */           return BlockFace.EAST;
/*     */         
/*     */         case 13:
/* 106 */           return BlockFace.EAST_SOUTH_EAST;
/*     */         
/*     */         case 14:
/* 109 */           return BlockFace.SOUTH_EAST;
/*     */         
/*     */         case 15:
/* 112 */           return BlockFace.SOUTH_SOUTH_EAST;
/*     */       } 
/*     */       
/* 115 */       return null;
/*     */     } 
/* 117 */     return getAttachedFace().getOppositeFace();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFacingDirection(BlockFace face) {
/*     */     byte data;
/* 125 */     if (isWallBanner()) {
/* 126 */       switch (face) {
/*     */         case NORTH:
/* 128 */           data = 2;
/*     */           break;
/*     */         
/*     */         case SOUTH:
/* 132 */           data = 3;
/*     */           break;
/*     */         
/*     */         case WEST:
/* 136 */           data = 4;
/*     */           break;
/*     */ 
/*     */         
/*     */         default:
/* 141 */           data = 5; break;
/*     */       } 
/*     */     } else {
/* 144 */       switch (face) {
/*     */         case SOUTH:
/* 146 */           data = 0;
/*     */           break;
/*     */         
/*     */         case SOUTH_SOUTH_WEST:
/* 150 */           data = 1;
/*     */           break;
/*     */         
/*     */         case SOUTH_WEST:
/* 154 */           data = 2;
/*     */           break;
/*     */         
/*     */         case WEST_SOUTH_WEST:
/* 158 */           data = 3;
/*     */           break;
/*     */         
/*     */         case WEST:
/* 162 */           data = 4;
/*     */           break;
/*     */         
/*     */         case WEST_NORTH_WEST:
/* 166 */           data = 5;
/*     */           break;
/*     */         
/*     */         case NORTH_WEST:
/* 170 */           data = 6;
/*     */           break;
/*     */         
/*     */         case NORTH_NORTH_WEST:
/* 174 */           data = 7;
/*     */           break;
/*     */         
/*     */         case NORTH:
/* 178 */           data = 8;
/*     */           break;
/*     */         
/*     */         case NORTH_NORTH_EAST:
/* 182 */           data = 9;
/*     */           break;
/*     */         
/*     */         case NORTH_EAST:
/* 186 */           data = 10;
/*     */           break;
/*     */         
/*     */         case EAST_NORTH_EAST:
/* 190 */           data = 11;
/*     */           break;
/*     */         
/*     */         case EAST:
/* 194 */           data = 12;
/*     */           break;
/*     */         
/*     */         case EAST_SOUTH_EAST:
/* 198 */           data = 13;
/*     */           break;
/*     */         
/*     */         case SOUTH_SOUTH_EAST:
/* 202 */           data = 15;
/*     */           break;
/*     */ 
/*     */         
/*     */         default:
/* 207 */           data = 14;
/*     */           break;
/*     */       } 
/*     */     } 
/* 211 */     setData(data);
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 216 */     return super.toString() + " facing " + getFacing();
/*     */   }
/*     */ 
/*     */   
/*     */   public Banner clone() {
/* 221 */     return (Banner)super.clone();
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\material\Banner.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
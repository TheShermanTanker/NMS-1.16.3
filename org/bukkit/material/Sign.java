/*     */ package org.bukkit.material;
/*     */ 
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.block.BlockFace;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Deprecated
/*     */ public class Sign
/*     */   extends MaterialData
/*     */   implements Attachable
/*     */ {
/*     */   public Sign() {
/*  15 */     super(Material.LEGACY_SIGN_POST);
/*     */   }
/*     */   
/*     */   public Sign(Material type) {
/*  19 */     super(type);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public Sign(Material type, byte data) {
/*  29 */     super(type, data);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isWallSign() {
/*  39 */     return (getItemType() == Material.LEGACY_WALL_SIGN);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BlockFace getAttachedFace() {
/*  49 */     if (isWallSign()) {
/*  50 */       byte data = getData();
/*     */       
/*  52 */       switch (data) {
/*     */         case 2:
/*  54 */           return BlockFace.SOUTH;
/*     */         
/*     */         case 3:
/*  57 */           return BlockFace.NORTH;
/*     */         
/*     */         case 4:
/*  60 */           return BlockFace.EAST;
/*     */         
/*     */         case 5:
/*  63 */           return BlockFace.WEST;
/*     */       } 
/*     */       
/*  66 */       return null;
/*     */     } 
/*  68 */     return BlockFace.DOWN;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BlockFace getFacing() {
/*  79 */     byte data = getData();
/*     */     
/*  81 */     if (!isWallSign()) {
/*  82 */       switch (data) {
/*     */         case 0:
/*  84 */           return BlockFace.SOUTH;
/*     */         
/*     */         case 1:
/*  87 */           return BlockFace.SOUTH_SOUTH_WEST;
/*     */         
/*     */         case 2:
/*  90 */           return BlockFace.SOUTH_WEST;
/*     */         
/*     */         case 3:
/*  93 */           return BlockFace.WEST_SOUTH_WEST;
/*     */         
/*     */         case 4:
/*  96 */           return BlockFace.WEST;
/*     */         
/*     */         case 5:
/*  99 */           return BlockFace.WEST_NORTH_WEST;
/*     */         
/*     */         case 6:
/* 102 */           return BlockFace.NORTH_WEST;
/*     */         
/*     */         case 7:
/* 105 */           return BlockFace.NORTH_NORTH_WEST;
/*     */         
/*     */         case 8:
/* 108 */           return BlockFace.NORTH;
/*     */         
/*     */         case 9:
/* 111 */           return BlockFace.NORTH_NORTH_EAST;
/*     */         
/*     */         case 10:
/* 114 */           return BlockFace.NORTH_EAST;
/*     */         
/*     */         case 11:
/* 117 */           return BlockFace.EAST_NORTH_EAST;
/*     */         
/*     */         case 12:
/* 120 */           return BlockFace.EAST;
/*     */         
/*     */         case 13:
/* 123 */           return BlockFace.EAST_SOUTH_EAST;
/*     */         
/*     */         case 14:
/* 126 */           return BlockFace.SOUTH_EAST;
/*     */         
/*     */         case 15:
/* 129 */           return BlockFace.SOUTH_SOUTH_EAST;
/*     */       } 
/*     */       
/* 132 */       return null;
/*     */     } 
/* 134 */     return getAttachedFace().getOppositeFace();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFacingDirection(BlockFace face) {
/*     */     byte data;
/* 142 */     if (isWallSign()) {
/* 143 */       switch (face) {
/*     */         case NORTH:
/* 145 */           data = 2;
/*     */           break;
/*     */         
/*     */         case SOUTH:
/* 149 */           data = 3;
/*     */           break;
/*     */         
/*     */         case WEST:
/* 153 */           data = 4;
/*     */           break;
/*     */ 
/*     */         
/*     */         default:
/* 158 */           data = 5; break;
/*     */       } 
/*     */     } else {
/* 161 */       switch (face) {
/*     */         case SOUTH:
/* 163 */           data = 0;
/*     */           break;
/*     */         
/*     */         case SOUTH_SOUTH_WEST:
/* 167 */           data = 1;
/*     */           break;
/*     */         
/*     */         case SOUTH_WEST:
/* 171 */           data = 2;
/*     */           break;
/*     */         
/*     */         case WEST_SOUTH_WEST:
/* 175 */           data = 3;
/*     */           break;
/*     */         
/*     */         case WEST:
/* 179 */           data = 4;
/*     */           break;
/*     */         
/*     */         case WEST_NORTH_WEST:
/* 183 */           data = 5;
/*     */           break;
/*     */         
/*     */         case NORTH_WEST:
/* 187 */           data = 6;
/*     */           break;
/*     */         
/*     */         case NORTH_NORTH_WEST:
/* 191 */           data = 7;
/*     */           break;
/*     */         
/*     */         case NORTH:
/* 195 */           data = 8;
/*     */           break;
/*     */         
/*     */         case NORTH_NORTH_EAST:
/* 199 */           data = 9;
/*     */           break;
/*     */         
/*     */         case NORTH_EAST:
/* 203 */           data = 10;
/*     */           break;
/*     */         
/*     */         case EAST_NORTH_EAST:
/* 207 */           data = 11;
/*     */           break;
/*     */         
/*     */         case EAST:
/* 211 */           data = 12;
/*     */           break;
/*     */         
/*     */         case EAST_SOUTH_EAST:
/* 215 */           data = 13;
/*     */           break;
/*     */         
/*     */         case SOUTH_SOUTH_EAST:
/* 219 */           data = 15;
/*     */           break;
/*     */ 
/*     */         
/*     */         default:
/* 224 */           data = 14;
/*     */           break;
/*     */       } 
/*     */     } 
/* 228 */     setData(data);
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 233 */     return super.toString() + " facing " + getFacing();
/*     */   }
/*     */ 
/*     */   
/*     */   public Sign clone() {
/* 238 */     return (Sign)super.clone();
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\material\Sign.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
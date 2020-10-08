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
/*     */ 
/*     */ 
/*     */ @Deprecated
/*     */ public class Bed
/*     */   extends MaterialData
/*     */   implements Directional
/*     */ {
/*     */   public Bed() {
/*  19 */     super(Material.LEGACY_BED_BLOCK);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Bed(BlockFace direction) {
/*  28 */     this();
/*  29 */     setFacingDirection(direction);
/*     */   }
/*     */   
/*     */   public Bed(Material type) {
/*  33 */     super(type);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public Bed(Material type, byte data) {
/*  43 */     super(type, data);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isHeadOfBed() {
/*  52 */     return ((getData() & 0x8) == 8);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setHeadOfBed(boolean isHeadOfBed) {
/*  61 */     setData((byte)(isHeadOfBed ? (getData() | 0x8) : (getData() & 0xFFFFFFF7)));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFacingDirection(BlockFace face) {
/*     */     byte data;
/*  72 */     switch (face) {
/*     */       case SOUTH:
/*  74 */         data = 0;
/*     */         break;
/*     */       
/*     */       case WEST:
/*  78 */         data = 1;
/*     */         break;
/*     */       
/*     */       case NORTH:
/*  82 */         data = 2;
/*     */         break;
/*     */ 
/*     */       
/*     */       default:
/*  87 */         data = 3;
/*     */         break;
/*     */     } 
/*  90 */     if (isHeadOfBed()) {
/*  91 */       data = (byte)(data | 0x8);
/*     */     }
/*     */     
/*  94 */     setData(data);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BlockFace getFacing() {
/* 104 */     byte data = (byte)(getData() & 0x7);
/*     */     
/* 106 */     switch (data) {
/*     */       case 0:
/* 108 */         return BlockFace.SOUTH;
/*     */       
/*     */       case 1:
/* 111 */         return BlockFace.WEST;
/*     */       
/*     */       case 2:
/* 114 */         return BlockFace.NORTH;
/*     */     } 
/*     */ 
/*     */     
/* 118 */     return BlockFace.EAST;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 124 */     return (isHeadOfBed() ? "HEAD" : "FOOT") + " of " + super.toString() + " facing " + getFacing();
/*     */   }
/*     */ 
/*     */   
/*     */   public Bed clone() {
/* 129 */     return (Bed)super.clone();
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\material\Bed.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
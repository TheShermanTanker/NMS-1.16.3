/*     */ package org.bukkit.material;
/*     */ 
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.block.BlockFace;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Deprecated
/*     */ public class Stairs
/*     */   extends MaterialData
/*     */   implements Directional
/*     */ {
/*     */   public Stairs(Material type) {
/*  16 */     super(type);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public Stairs(Material type, byte data) {
/*  26 */     super(type, data);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BlockFace getAscendingDirection() {
/*  33 */     byte data = getData();
/*     */     
/*  35 */     switch (data & 0x3) {
/*     */       
/*     */       default:
/*  38 */         return BlockFace.EAST;
/*     */       
/*     */       case 1:
/*  41 */         return BlockFace.WEST;
/*     */       
/*     */       case 2:
/*  44 */         return BlockFace.SOUTH;
/*     */       case 3:
/*     */         break;
/*  47 */     }  return BlockFace.NORTH;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BlockFace getDescendingDirection() {
/*  55 */     return getAscendingDirection().getOppositeFace();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFacingDirection(BlockFace face) {
/*     */     byte data;
/*  65 */     switch (face) {
/*     */       case NORTH:
/*  67 */         data = 3;
/*     */         break;
/*     */       
/*     */       case SOUTH:
/*  71 */         data = 2;
/*     */         break;
/*     */ 
/*     */       
/*     */       default:
/*  76 */         data = 0;
/*     */         break;
/*     */       
/*     */       case WEST:
/*  80 */         data = 1;
/*     */         break;
/*     */     } 
/*     */     
/*  84 */     setData((byte)(getData() & 0xC | data));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BlockFace getFacing() {
/*  92 */     return getDescendingDirection();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isInverted() {
/* 101 */     return ((getData() & 0x4) != 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setInverted(boolean inv) {
/* 111 */     int dat = getData() & 0x3;
/* 112 */     if (inv) {
/* 113 */       dat |= 0x4;
/*     */     }
/* 115 */     setData((byte)dat);
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 120 */     return super.toString() + " facing " + getFacing() + (isInverted() ? " inverted" : "");
/*     */   }
/*     */ 
/*     */   
/*     */   public Stairs clone() {
/* 125 */     return (Stairs)super.clone();
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\material\Stairs.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
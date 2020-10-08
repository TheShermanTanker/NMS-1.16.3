/*     */ package org.bukkit.material;
/*     */ 
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.block.BlockFace;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Deprecated
/*     */ public class Lever
/*     */   extends SimpleAttachableMaterialData
/*     */   implements Redstone
/*     */ {
/*     */   public Lever() {
/*  15 */     super(Material.LEGACY_LEVER);
/*     */   }
/*     */   
/*     */   public Lever(Material type) {
/*  19 */     super(type);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public Lever(Material type, byte data) {
/*  29 */     super(type, data);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isPowered() {
/*  40 */     return ((getData() & 0x8) == 8);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPowered(boolean isPowered) {
/*  49 */     setData((byte)(isPowered ? (getData() | 0x8) : (getData() & 0xFFFFFFF7)));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BlockFace getAttachedFace() {
/*  59 */     byte data = (byte)(getData() & 0x7);
/*     */     
/*  61 */     switch (data) {
/*     */       case 1:
/*  63 */         return BlockFace.WEST;
/*     */       
/*     */       case 2:
/*  66 */         return BlockFace.EAST;
/*     */       
/*     */       case 3:
/*  69 */         return BlockFace.NORTH;
/*     */       
/*     */       case 4:
/*  72 */         return BlockFace.SOUTH;
/*     */       
/*     */       case 5:
/*     */       case 6:
/*  76 */         return BlockFace.DOWN;
/*     */       
/*     */       case 0:
/*     */       case 7:
/*  80 */         return BlockFace.UP;
/*     */     } 
/*     */ 
/*     */     
/*  84 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFacingDirection(BlockFace face) {
/*  92 */     byte data = (byte)(getData() & 0x8);
/*  93 */     BlockFace attach = getAttachedFace();
/*     */     
/*  95 */     if (attach == BlockFace.DOWN) {
/*  96 */       switch (face) {
/*     */         case SOUTH:
/*     */         case NORTH:
/*  99 */           data = (byte)(data | 0x5);
/*     */           break;
/*     */         
/*     */         case EAST:
/*     */         case WEST:
/* 104 */           data = (byte)(data | 0x6);
/*     */           break;
/*     */       } 
/* 107 */     } else if (attach == BlockFace.UP) {
/* 108 */       switch (face) {
/*     */         case SOUTH:
/*     */         case NORTH:
/* 111 */           data = (byte)(data | 0x7);
/*     */           break;
/*     */         
/*     */         case EAST:
/*     */         case WEST:
/* 116 */           data = (byte)(data | 0x0);
/*     */           break;
/*     */       } 
/*     */     } else {
/* 120 */       switch (face) {
/*     */         case EAST:
/* 122 */           data = (byte)(data | 0x1);
/*     */           break;
/*     */         
/*     */         case WEST:
/* 126 */           data = (byte)(data | 0x2);
/*     */           break;
/*     */         
/*     */         case SOUTH:
/* 130 */           data = (byte)(data | 0x3);
/*     */           break;
/*     */         
/*     */         case NORTH:
/* 134 */           data = (byte)(data | 0x4);
/*     */           break;
/*     */       } 
/*     */     } 
/* 138 */     setData(data);
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 143 */     return super.toString() + " facing " + getFacing() + " " + (isPowered() ? "" : "NOT ") + "POWERED";
/*     */   }
/*     */ 
/*     */   
/*     */   public Lever clone() {
/* 148 */     return (Lever)super.clone();
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\material\Lever.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
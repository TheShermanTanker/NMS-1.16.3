/*     */ package org.bukkit.material;
/*     */ 
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.block.BlockFace;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Deprecated
/*     */ public class Button
/*     */   extends SimpleAttachableMaterialData
/*     */   implements Redstone
/*     */ {
/*     */   public Button() {
/*  15 */     super(Material.LEGACY_STONE_BUTTON);
/*     */   }
/*     */   
/*     */   public Button(Material type) {
/*  19 */     super(type);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public Button(Material type, byte data) {
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
/*     */   
/*     */   public void setPowered(boolean bool) {
/*  50 */     setData((byte)(bool ? (getData() | 0x8) : (getData() & 0xFFFFFFF7)));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BlockFace getAttachedFace() {
/*  60 */     byte data = (byte)(getData() & 0x7);
/*     */     
/*  62 */     switch (data) {
/*     */       case 0:
/*  64 */         return BlockFace.UP;
/*     */       
/*     */       case 1:
/*  67 */         return BlockFace.WEST;
/*     */       
/*     */       case 2:
/*  70 */         return BlockFace.EAST;
/*     */       
/*     */       case 3:
/*  73 */         return BlockFace.NORTH;
/*     */       
/*     */       case 4:
/*  76 */         return BlockFace.SOUTH;
/*     */       
/*     */       case 5:
/*  79 */         return BlockFace.DOWN;
/*     */     } 
/*     */     
/*  82 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFacingDirection(BlockFace face) {
/*  90 */     byte data = (byte)(getData() & 0x8);
/*     */     
/*  92 */     switch (face) {
/*     */       case DOWN:
/*  94 */         data = (byte)(data | 0x0);
/*     */         break;
/*     */       
/*     */       case EAST:
/*  98 */         data = (byte)(data | 0x1);
/*     */         break;
/*     */       
/*     */       case WEST:
/* 102 */         data = (byte)(data | 0x2);
/*     */         break;
/*     */       
/*     */       case SOUTH:
/* 106 */         data = (byte)(data | 0x3);
/*     */         break;
/*     */       
/*     */       case NORTH:
/* 110 */         data = (byte)(data | 0x4);
/*     */         break;
/*     */       
/*     */       case UP:
/* 114 */         data = (byte)(data | 0x5);
/*     */         break;
/*     */     } 
/*     */     
/* 118 */     setData(data);
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 123 */     return super.toString() + " " + (isPowered() ? "" : "NOT ") + "POWERED";
/*     */   }
/*     */ 
/*     */   
/*     */   public Button clone() {
/* 128 */     return (Button)super.clone();
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\material\Button.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
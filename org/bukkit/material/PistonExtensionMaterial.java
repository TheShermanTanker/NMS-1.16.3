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
/*     */ public class PistonExtensionMaterial
/*     */   extends MaterialData
/*     */   implements Attachable
/*     */ {
/*     */   public PistonExtensionMaterial(Material type) {
/*  16 */     super(type);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public PistonExtensionMaterial(Material type, byte data) {
/*  26 */     super(type, data);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFacingDirection(BlockFace face) {
/*  31 */     byte data = (byte)(getData() & 0x8);
/*     */     
/*  33 */     switch (face) {
/*     */       case UP:
/*  35 */         data = (byte)(data | 0x1);
/*     */         break;
/*     */       case NORTH:
/*  38 */         data = (byte)(data | 0x2);
/*     */         break;
/*     */       case SOUTH:
/*  41 */         data = (byte)(data | 0x3);
/*     */         break;
/*     */       case WEST:
/*  44 */         data = (byte)(data | 0x4);
/*     */         break;
/*     */       case EAST:
/*  47 */         data = (byte)(data | 0x5);
/*     */         break;
/*     */     } 
/*  50 */     setData(data);
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockFace getFacing() {
/*  55 */     byte dir = (byte)(getData() & 0x7);
/*     */     
/*  57 */     switch (dir) {
/*     */       case 0:
/*  59 */         return BlockFace.DOWN;
/*     */       case 1:
/*  61 */         return BlockFace.UP;
/*     */       case 2:
/*  63 */         return BlockFace.NORTH;
/*     */       case 3:
/*  65 */         return BlockFace.SOUTH;
/*     */       case 4:
/*  67 */         return BlockFace.WEST;
/*     */       case 5:
/*  69 */         return BlockFace.EAST;
/*     */     } 
/*  71 */     return BlockFace.SELF;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isSticky() {
/*  81 */     return ((getData() & 0x8) == 8);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSticky(boolean sticky) {
/*  90 */     setData((byte)(sticky ? (getData() | 0x8) : (getData() & 0xFFFFFFF7)));
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockFace getAttachedFace() {
/*  95 */     return getFacing().getOppositeFace();
/*     */   }
/*     */ 
/*     */   
/*     */   public PistonExtensionMaterial clone() {
/* 100 */     return (PistonExtensionMaterial)super.clone();
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\material\PistonExtensionMaterial.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
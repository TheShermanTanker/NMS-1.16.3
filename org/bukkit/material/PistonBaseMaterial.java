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
/*     */ public class PistonBaseMaterial
/*     */   extends MaterialData
/*     */   implements Directional, Redstone
/*     */ {
/*     */   public PistonBaseMaterial(Material type) {
/*  16 */     super(type);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public PistonBaseMaterial(Material type, byte data) {
/*  28 */     super(type, data);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFacingDirection(BlockFace face) {
/*  33 */     byte data = (byte)(getData() & 0x8);
/*     */     
/*  35 */     switch (face) {
/*     */       case UP:
/*  37 */         data = (byte)(data | 0x1);
/*     */         break;
/*     */       case NORTH:
/*  40 */         data = (byte)(data | 0x2);
/*     */         break;
/*     */       case SOUTH:
/*  43 */         data = (byte)(data | 0x3);
/*     */         break;
/*     */       case WEST:
/*  46 */         data = (byte)(data | 0x4);
/*     */         break;
/*     */       case EAST:
/*  49 */         data = (byte)(data | 0x5);
/*     */         break;
/*     */     } 
/*  52 */     setData(data);
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockFace getFacing() {
/*  57 */     byte dir = (byte)(getData() & 0x7);
/*     */     
/*  59 */     switch (dir) {
/*     */       case 0:
/*  61 */         return BlockFace.DOWN;
/*     */       case 1:
/*  63 */         return BlockFace.UP;
/*     */       case 2:
/*  65 */         return BlockFace.NORTH;
/*     */       case 3:
/*  67 */         return BlockFace.SOUTH;
/*     */       case 4:
/*  69 */         return BlockFace.WEST;
/*     */       case 5:
/*  71 */         return BlockFace.EAST;
/*     */     } 
/*  73 */     return BlockFace.SELF;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isPowered() {
/*  79 */     return ((getData() & 0x8) == 8);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPowered(boolean powered) {
/*  88 */     setData((byte)(powered ? (getData() | 0x8) : (getData() & 0xFFFFFFF7)));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isSticky() {
/*  97 */     return (getItemType() == Material.LEGACY_PISTON_STICKY_BASE);
/*     */   }
/*     */ 
/*     */   
/*     */   public PistonBaseMaterial clone() {
/* 102 */     return (PistonBaseMaterial)super.clone();
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\material\PistonBaseMaterial.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
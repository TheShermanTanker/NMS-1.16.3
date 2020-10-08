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
/*     */ public class Hopper
/*     */   extends MaterialData
/*     */   implements Directional, Redstone
/*     */ {
/*  18 */   protected static final BlockFace DEFAULT_DIRECTION = BlockFace.DOWN;
/*     */ 
/*     */   
/*     */   protected static final boolean DEFAULT_ACTIVE = true;
/*     */ 
/*     */ 
/*     */   
/*     */   public Hopper() {
/*  26 */     this(DEFAULT_DIRECTION, true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Hopper(BlockFace facingDirection) {
/*  37 */     this(facingDirection, true);
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
/*     */   public Hopper(BlockFace facingDirection, boolean isActive) {
/*  51 */     super(Material.LEGACY_HOPPER);
/*  52 */     setFacingDirection(facingDirection);
/*  53 */     setActive(isActive);
/*     */   }
/*     */   
/*     */   public Hopper(Material type) {
/*  57 */     super(type);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public Hopper(Material type, byte data) {
/*  67 */     super(type, data);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setActive(boolean isActive) {
/*  77 */     setData((byte)(getData() & 0x7 | (isActive ? 0 : 8)));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isActive() {
/*  86 */     return ((getData() & 0x8) == 0);
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
/*     */   public void setFacingDirection(BlockFace face) {
/*  98 */     int data = getData() & 0x8;
/*     */     
/* 100 */     switch (face) {
/*     */       case DOWN:
/* 102 */         data |= 0x0;
/*     */         break;
/*     */       case NORTH:
/* 105 */         data |= 0x2;
/*     */         break;
/*     */       case SOUTH:
/* 108 */         data |= 0x3;
/*     */         break;
/*     */       case WEST:
/* 111 */         data |= 0x4;
/*     */         break;
/*     */       case EAST:
/* 114 */         data |= 0x5;
/*     */         break;
/*     */     } 
/*     */     
/* 118 */     setData((byte)data);
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
/*     */   public BlockFace getFacing() {
/* 130 */     byte data = (byte)(getData() & 0x7);
/*     */     
/* 132 */     switch (data)
/*     */     
/*     */     { default:
/* 135 */         return BlockFace.DOWN;
/*     */       case 2:
/* 137 */         return BlockFace.NORTH;
/*     */       case 3:
/* 139 */         return BlockFace.SOUTH;
/*     */       case 4:
/* 141 */         return BlockFace.WEST;
/*     */       case 5:
/* 143 */         break; }  return BlockFace.EAST;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 149 */     return super.toString() + " facing " + getFacing();
/*     */   }
/*     */ 
/*     */   
/*     */   public Hopper clone() {
/* 154 */     return (Hopper)super.clone();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isPowered() {
/* 164 */     return ((getData() & 0x8) != 0);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\material\Hopper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
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
/*     */ @Deprecated
/*     */ public class Comparator
/*     */   extends MaterialData
/*     */   implements Directional, Redstone
/*     */ {
/*  17 */   protected static final BlockFace DEFAULT_DIRECTION = BlockFace.NORTH;
/*     */   
/*     */   protected static final boolean DEFAULT_SUBTRACTION_MODE = false;
/*     */   
/*     */   protected static final boolean DEFAULT_STATE = false;
/*     */ 
/*     */   
/*     */   public Comparator() {
/*  25 */     this(DEFAULT_DIRECTION, false, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Comparator(BlockFace facingDirection) {
/*  36 */     this(facingDirection, false, false);
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
/*     */   public Comparator(BlockFace facingDirection, boolean isSubtraction) {
/*  48 */     this(facingDirection, isSubtraction, false);
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
/*     */   public Comparator(BlockFace facingDirection, boolean isSubtraction, boolean state) {
/*  61 */     super(state ? Material.LEGACY_REDSTONE_COMPARATOR_ON : Material.LEGACY_REDSTONE_COMPARATOR_OFF);
/*  62 */     setFacingDirection(facingDirection);
/*  63 */     setSubtractionMode(isSubtraction);
/*     */   }
/*     */   
/*     */   public Comparator(Material type) {
/*  67 */     super(type);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public Comparator(Material type, byte data) {
/*  77 */     super(type, data);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSubtractionMode(boolean isSubtraction) {
/*  86 */     setData((byte)(getData() & 0xB | (isSubtraction ? 4 : 0)));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isSubtractionMode() {
/*  95 */     return ((getData() & 0x4) != 0);
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
/* 107 */     int data = getData() & 0xC;
/*     */     
/* 109 */     switch (face) {
/*     */       case EAST:
/* 111 */         data |= 0x1;
/*     */         break;
/*     */       
/*     */       case SOUTH:
/* 115 */         data |= 0x2;
/*     */         break;
/*     */       
/*     */       case WEST:
/* 119 */         data |= 0x3;
/*     */         break;
/*     */ 
/*     */       
/*     */       default:
/* 124 */         data |= 0x0;
/*     */         break;
/*     */     } 
/* 127 */     setData((byte)data);
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
/* 139 */     byte data = (byte)(getData() & 0x3);
/*     */     
/* 141 */     switch (data) {
/*     */       
/*     */       default:
/* 144 */         return BlockFace.NORTH;
/*     */       
/*     */       case 1:
/* 147 */         return BlockFace.EAST;
/*     */       
/*     */       case 2:
/* 150 */         return BlockFace.SOUTH;
/*     */       case 3:
/*     */         break;
/* 153 */     }  return BlockFace.WEST;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 159 */     return super.toString() + " facing " + getFacing() + " in " + (isSubtractionMode() ? "subtraction" : "comparator") + " mode";
/*     */   }
/*     */ 
/*     */   
/*     */   public Comparator clone() {
/* 164 */     return (Comparator)super.clone();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isPowered() {
/* 174 */     return (getItemType() == Material.LEGACY_REDSTONE_COMPARATOR_ON);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isBeingPowered() {
/* 183 */     return ((getData() & 0x8) != 0);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\material\Comparator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
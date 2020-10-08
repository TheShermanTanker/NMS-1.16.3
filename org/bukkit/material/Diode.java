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
/*     */ 
/*     */ @Deprecated
/*     */ public class Diode
/*     */   extends MaterialData
/*     */   implements Directional, Redstone
/*     */ {
/*  19 */   protected static final BlockFace DEFAULT_DIRECTION = BlockFace.NORTH;
/*     */ 
/*     */ 
/*     */   
/*     */   protected static final int DEFAULT_DELAY = 1;
/*     */ 
/*     */   
/*     */   protected static final boolean DEFAULT_STATE = false;
/*     */ 
/*     */ 
/*     */   
/*     */   public Diode() {
/*  31 */     this(DEFAULT_DIRECTION, 1, true);
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
/*     */   public Diode(BlockFace facingDirection) {
/*  43 */     this(facingDirection, 1, false);
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
/*     */   public Diode(BlockFace facingDirection, int delay) {
/*  57 */     this(facingDirection, delay, false);
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
/*     */   
/*     */   public Diode(BlockFace facingDirection, int delay, boolean state) {
/*  72 */     super(state ? Material.LEGACY_DIODE_BLOCK_ON : Material.LEGACY_DIODE_BLOCK_OFF);
/*  73 */     setFacingDirection(facingDirection);
/*  74 */     setDelay(delay);
/*     */   }
/*     */   
/*     */   public Diode(Material type) {
/*  78 */     super(type);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public Diode(Material type, byte data) {
/*  88 */     super(type, data);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDelay(int delay) {
/*  97 */     if (delay > 4) {
/*  98 */       delay = 4;
/*     */     }
/* 100 */     if (delay < 1) {
/* 101 */       delay = 1;
/*     */     }
/* 103 */     byte newData = (byte)(getData() & 0x3);
/*     */     
/* 105 */     setData((byte)(newData | delay - 1 << 2));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getDelay() {
/* 114 */     return (getData() >> 2) + 1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFacingDirection(BlockFace face) {
/*     */     byte data;
/* 126 */     int delay = getDelay();
/*     */ 
/*     */     
/* 129 */     switch (face) {
/*     */       case EAST:
/* 131 */         data = 1;
/*     */         break;
/*     */       case SOUTH:
/* 134 */         data = 2;
/*     */         break;
/*     */       case WEST:
/* 137 */         data = 3;
/*     */         break;
/*     */       
/*     */       default:
/* 141 */         data = 0;
/*     */         break;
/*     */     } 
/* 144 */     setData(data);
/* 145 */     setDelay(delay);
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
/* 157 */     byte data = (byte)(getData() & 0x3);
/*     */     
/* 159 */     switch (data) {
/*     */       
/*     */       default:
/* 162 */         return BlockFace.NORTH;
/*     */       
/*     */       case 1:
/* 165 */         return BlockFace.EAST;
/*     */       
/*     */       case 2:
/* 168 */         return BlockFace.SOUTH;
/*     */       case 3:
/*     */         break;
/* 171 */     }  return BlockFace.WEST;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 177 */     return super.toString() + " facing " + getFacing() + " with " + getDelay() + " ticks delay";
/*     */   }
/*     */ 
/*     */   
/*     */   public Diode clone() {
/* 182 */     return (Diode)super.clone();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isPowered() {
/* 192 */     return (getItemType() == Material.LEGACY_DIODE_BLOCK_ON);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\material\Diode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
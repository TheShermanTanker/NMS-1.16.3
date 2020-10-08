/*     */ package org.bukkit.material;
/*     */ 
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.block.BlockFace;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Deprecated
/*     */ public class Gate
/*     */   extends MaterialData
/*     */   implements Directional, Openable
/*     */ {
/*     */   private static final byte OPEN_BIT = 4;
/*     */   private static final byte DIR_BIT = 3;
/*     */   private static final byte GATE_SOUTH = 0;
/*     */   private static final byte GATE_WEST = 1;
/*     */   private static final byte GATE_NORTH = 2;
/*     */   private static final byte GATE_EAST = 3;
/*     */   
/*     */   public Gate() {
/*  22 */     super(Material.LEGACY_FENCE_GATE);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public Gate(Material type, byte data) {
/*  32 */     super(type, data);
/*     */   }
/*     */   
/*     */   public Gate(byte data) {
/*  36 */     super(Material.LEGACY_FENCE_GATE, data);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFacingDirection(BlockFace face) {
/*  41 */     byte data = (byte)(getData() & 0xFFFFFFFC);
/*     */     
/*  43 */     switch (face) {
/*     */       
/*     */       default:
/*  46 */         data = (byte)(data | 0x0);
/*     */         break;
/*     */       case SOUTH:
/*  49 */         data = (byte)(data | 0x1);
/*     */         break;
/*     */       case WEST:
/*  52 */         data = (byte)(data | 0x2);
/*     */         break;
/*     */       case NORTH:
/*  55 */         data = (byte)(data | 0x3);
/*     */         break;
/*     */     } 
/*     */     
/*  59 */     setData(data);
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockFace getFacing() {
/*  64 */     switch (getData() & 0x3) {
/*     */       case 0:
/*  66 */         return BlockFace.EAST;
/*     */       case 1:
/*  68 */         return BlockFace.SOUTH;
/*     */       case 2:
/*  70 */         return BlockFace.WEST;
/*     */       case 3:
/*  72 */         return BlockFace.NORTH;
/*     */     } 
/*     */     
/*  75 */     return BlockFace.EAST;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isOpen() {
/*  80 */     return ((getData() & 0x4) > 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOpen(boolean isOpen) {
/*  85 */     byte data = getData();
/*     */     
/*  87 */     if (isOpen) {
/*  88 */       data = (byte)(data | 0x4);
/*     */     } else {
/*  90 */       data = (byte)(data & 0xFFFFFFFB);
/*     */     } 
/*     */     
/*  93 */     setData(data);
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/*  98 */     return (isOpen() ? "OPEN " : "CLOSED ") + " facing and opening " + getFacing();
/*     */   }
/*     */ 
/*     */   
/*     */   public Gate clone() {
/* 103 */     return (Gate)super.clone();
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\material\Gate.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
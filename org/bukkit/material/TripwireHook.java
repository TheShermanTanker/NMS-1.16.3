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
/*     */ public class TripwireHook
/*     */   extends SimpleAttachableMaterialData
/*     */   implements Redstone
/*     */ {
/*     */   public TripwireHook() {
/*  16 */     super(Material.LEGACY_TRIPWIRE_HOOK);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public TripwireHook(Material type, byte data) {
/*  26 */     super(type, data);
/*     */   }
/*     */   
/*     */   public TripwireHook(BlockFace dir) {
/*  30 */     this();
/*  31 */     setFacingDirection(dir);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isConnected() {
/*  40 */     return ((getData() & 0x4) != 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setConnected(boolean connected) {
/*  49 */     int dat = getData() & 0xB;
/*  50 */     if (connected) {
/*  51 */       dat |= 0x4;
/*     */     }
/*  53 */     setData((byte)dat);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isActivated() {
/*  62 */     return ((getData() & 0x8) != 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setActivated(boolean act) {
/*  71 */     int dat = getData() & 0x7;
/*  72 */     if (act) {
/*  73 */       dat |= 0x8;
/*     */     }
/*  75 */     setData((byte)dat);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFacingDirection(BlockFace face) {
/*  80 */     int dat = getData() & 0xC;
/*  81 */     switch (face) {
/*     */       case WEST:
/*  83 */         dat |= 0x1;
/*     */         break;
/*     */       case NORTH:
/*  86 */         dat |= 0x2;
/*     */         break;
/*     */       case EAST:
/*  89 */         dat |= 0x3;
/*     */         break;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  95 */     setData((byte)dat);
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockFace getAttachedFace() {
/* 100 */     switch (getData() & 0x3) {
/*     */       case 0:
/* 102 */         return BlockFace.NORTH;
/*     */       case 1:
/* 104 */         return BlockFace.EAST;
/*     */       case 2:
/* 106 */         return BlockFace.SOUTH;
/*     */       case 3:
/* 108 */         return BlockFace.WEST;
/*     */     } 
/* 110 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isPowered() {
/* 115 */     return isActivated();
/*     */   }
/*     */ 
/*     */   
/*     */   public TripwireHook clone() {
/* 120 */     return (TripwireHook)super.clone();
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 125 */     return super.toString() + " facing " + getFacing() + (isActivated() ? " Activated" : "") + (isConnected() ? " Connected" : "");
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\material\TripwireHook.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
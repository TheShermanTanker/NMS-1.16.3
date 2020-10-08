/*     */ package org.bukkit.material;
/*     */ 
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.block.BlockFace;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Deprecated
/*     */ public class TrapDoor
/*     */   extends SimpleAttachableMaterialData
/*     */   implements Openable
/*     */ {
/*     */   public TrapDoor() {
/*  15 */     super(Material.LEGACY_TRAP_DOOR);
/*     */   }
/*     */   
/*     */   public TrapDoor(Material type) {
/*  19 */     super(type);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public TrapDoor(Material type, byte data) {
/*  29 */     super(type, data);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isOpen() {
/*  34 */     return ((getData() & 0x4) == 4);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOpen(boolean isOpen) {
/*  39 */     byte data = getData();
/*     */     
/*  41 */     if (isOpen) {
/*  42 */       data = (byte)(data | 0x4);
/*     */     } else {
/*  44 */       data = (byte)(data & 0xFFFFFFFB);
/*     */     } 
/*     */     
/*  47 */     setData(data);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isInverted() {
/*  56 */     return ((getData() & 0x8) != 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setInverted(boolean inv) {
/*  65 */     int dat = getData() & 0x7;
/*  66 */     if (inv) {
/*  67 */       dat |= 0x8;
/*     */     }
/*  69 */     setData((byte)dat);
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockFace getAttachedFace() {
/*  74 */     byte data = (byte)(getData() & 0x3);
/*     */     
/*  76 */     switch (data) {
/*     */       case 0:
/*  78 */         return BlockFace.SOUTH;
/*     */       
/*     */       case 1:
/*  81 */         return BlockFace.NORTH;
/*     */       
/*     */       case 2:
/*  84 */         return BlockFace.EAST;
/*     */       
/*     */       case 3:
/*  87 */         return BlockFace.WEST;
/*     */     } 
/*     */     
/*  90 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFacingDirection(BlockFace face) {
/*  96 */     byte data = (byte)(getData() & 0xC);
/*     */     
/*  98 */     switch (face) {
/*     */       case SOUTH:
/* 100 */         data = (byte)(data | 0x1);
/*     */         break;
/*     */       case WEST:
/* 103 */         data = (byte)(data | 0x2);
/*     */         break;
/*     */       case EAST:
/* 106 */         data = (byte)(data | 0x3);
/*     */         break;
/*     */     } 
/*     */     
/* 110 */     setData(data);
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 115 */     return (isOpen() ? "OPEN " : "CLOSED ") + super.toString() + " with hinges set " + getAttachedFace() + (isInverted() ? " inverted" : "");
/*     */   }
/*     */ 
/*     */   
/*     */   public TrapDoor clone() {
/* 120 */     return (TrapDoor)super.clone();
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\material\TrapDoor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
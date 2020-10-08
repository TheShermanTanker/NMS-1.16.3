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
/*     */ public class Observer
/*     */   extends MaterialData
/*     */   implements Directional, Redstone
/*     */ {
/*     */   public Observer() {
/*  16 */     super(Material.LEGACY_OBSERVER);
/*     */   }
/*     */   
/*     */   public Observer(BlockFace direction) {
/*  20 */     this();
/*  21 */     setFacingDirection(direction);
/*     */   }
/*     */   
/*     */   public Observer(Material type) {
/*  25 */     super(type);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public Observer(Material type, byte data) {
/*  35 */     super(type, data);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isPowered() {
/*  40 */     return ((getData() & 0x8) == 8);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFacingDirection(BlockFace face) {
/*  45 */     byte data = (byte)(getData() & 0x8);
/*     */     
/*  47 */     switch (face) {
/*     */       case DOWN:
/*  49 */         data = (byte)(data | 0x0);
/*     */         break;
/*     */       case UP:
/*  52 */         data = (byte)(data | 0x1);
/*     */         break;
/*     */       case SOUTH:
/*  55 */         data = (byte)(data | 0x2);
/*     */         break;
/*     */       case NORTH:
/*  58 */         data = (byte)(data | 0x3);
/*     */         break;
/*     */       case EAST:
/*  61 */         data = (byte)(data | 0x4);
/*     */         break;
/*     */       case WEST:
/*  64 */         data = (byte)(data | 0x5);
/*     */         break;
/*     */     } 
/*     */     
/*  68 */     setData(data);
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockFace getFacing() {
/*  73 */     int data = getData() & 0x7;
/*     */     
/*  75 */     switch (data) {
/*     */       case 0:
/*  77 */         return BlockFace.DOWN;
/*     */       case 1:
/*  79 */         return BlockFace.UP;
/*     */       case 2:
/*  81 */         return BlockFace.SOUTH;
/*     */       case 3:
/*  83 */         return BlockFace.NORTH;
/*     */       case 4:
/*  85 */         return BlockFace.EAST;
/*     */       case 5:
/*  87 */         return BlockFace.WEST;
/*     */     } 
/*  89 */     throw new IllegalArgumentException("Illegal facing direction " + data);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/*  95 */     return super.toString() + " facing " + getFacing();
/*     */   }
/*     */ 
/*     */   
/*     */   public Observer clone() {
/* 100 */     return (Observer)super.clone();
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\material\Observer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
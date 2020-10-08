/*     */ package org.bukkit.material;
/*     */ 
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.block.BlockFace;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Deprecated
/*     */ public class Skull
/*     */   extends MaterialData
/*     */   implements Directional
/*     */ {
/*     */   public Skull() {
/*  15 */     super(Material.LEGACY_SKULL);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Skull(BlockFace direction) {
/*  24 */     this();
/*  25 */     setFacingDirection(direction);
/*     */   }
/*     */   
/*     */   public Skull(Material type) {
/*  29 */     super(type);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public Skull(Material type, byte data) {
/*  39 */     super(type, data);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFacingDirection(BlockFace face) {
/*     */     int data;
/*  46 */     switch (face) {
/*     */       
/*     */       default:
/*  49 */         data = 1;
/*     */         break;
/*     */       
/*     */       case NORTH:
/*  53 */         data = 2;
/*     */         break;
/*     */       
/*     */       case WEST:
/*  57 */         data = 4;
/*     */         break;
/*     */       
/*     */       case SOUTH:
/*  61 */         data = 3;
/*     */         break;
/*     */       
/*     */       case EAST:
/*  65 */         data = 5;
/*     */         break;
/*     */     } 
/*  68 */     setData((byte)data);
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockFace getFacing() {
/*  73 */     int data = getData();
/*     */     
/*  75 */     switch (data) {
/*     */       
/*     */       default:
/*  78 */         return BlockFace.SELF;
/*     */       
/*     */       case 2:
/*  81 */         return BlockFace.NORTH;
/*     */       
/*     */       case 3:
/*  84 */         return BlockFace.SOUTH;
/*     */       
/*     */       case 4:
/*  87 */         return BlockFace.WEST;
/*     */       case 5:
/*     */         break;
/*  90 */     }  return BlockFace.EAST;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/*  96 */     return super.toString() + " facing " + getFacing();
/*     */   }
/*     */ 
/*     */   
/*     */   public Skull clone() {
/* 101 */     return (Skull)super.clone();
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\material\Skull.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
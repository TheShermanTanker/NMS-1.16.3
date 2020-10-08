/*     */ package org.bukkit.material;
/*     */ 
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.TreeSpecies;
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
/*     */ public class Tree
/*     */   extends Wood
/*     */ {
/*  18 */   protected static final Material DEFAULT_TYPE = Material.LEGACY_LOG;
/*  19 */   protected static final BlockFace DEFAULT_DIRECTION = BlockFace.UP;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Tree() {
/*  25 */     this(DEFAULT_TYPE, DEFAULT_SPECIES, DEFAULT_DIRECTION);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Tree(TreeSpecies species) {
/*  34 */     this(DEFAULT_TYPE, species, DEFAULT_DIRECTION);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Tree(TreeSpecies species, BlockFace dir) {
/*  45 */     this(DEFAULT_TYPE, species, dir);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Tree(Material type) {
/*  54 */     this(type, DEFAULT_SPECIES, DEFAULT_DIRECTION);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Tree(Material type, TreeSpecies species) {
/*  64 */     this(type, species, DEFAULT_DIRECTION);
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
/*     */   public Tree(Material type, TreeSpecies species, BlockFace dir) {
/*  76 */     super(type, species);
/*  77 */     setDirection(dir);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public Tree(Material type, byte data) {
/*  87 */     super(type, data);
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
/*     */   
/*     */   public BlockFace getDirection() {
/* 103 */     switch (getData() >> 2 & 0x3)
/*     */     
/*     */     { default:
/* 106 */         return BlockFace.UP;
/*     */       case 1:
/* 108 */         return BlockFace.WEST;
/*     */       case 2:
/* 110 */         return BlockFace.NORTH;
/*     */       case 3:
/* 112 */         break; }  return BlockFace.SELF;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDirection(BlockFace dir) {
/*     */     int dat;
/* 124 */     switch (dir) {
/*     */ 
/*     */       
/*     */       default:
/* 128 */         dat = 0;
/*     */         break;
/*     */       case WEST:
/*     */       case EAST:
/* 132 */         dat = 4;
/*     */         break;
/*     */       case NORTH:
/*     */       case SOUTH:
/* 136 */         dat = 8;
/*     */         break;
/*     */       case SELF:
/* 139 */         dat = 12;
/*     */         break;
/*     */     } 
/* 142 */     setData((byte)(getData() & 0x3 | dat));
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 147 */     return getSpecies() + " " + getDirection() + " " + super.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   public Tree clone() {
/* 152 */     return (Tree)super.clone();
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\material\Tree.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
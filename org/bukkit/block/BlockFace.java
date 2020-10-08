/*     */ package org.bukkit.block;
/*     */ 
/*     */ import org.bukkit.util.Vector;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ 
/*     */ 
/*     */ public enum BlockFace
/*     */ {
/*  10 */   NORTH(0, 0, -1),
/*  11 */   EAST(1, 0, 0),
/*  12 */   SOUTH(0, 0, 1),
/*  13 */   WEST(-1, 0, 0),
/*  14 */   UP(0, 1, 0),
/*  15 */   DOWN(0, -1, 0),
/*  16 */   NORTH_EAST(NORTH, EAST),
/*  17 */   NORTH_WEST(NORTH, WEST),
/*  18 */   SOUTH_EAST(SOUTH, EAST),
/*  19 */   SOUTH_WEST(SOUTH, WEST),
/*  20 */   WEST_NORTH_WEST(WEST, NORTH_WEST),
/*  21 */   NORTH_NORTH_WEST(NORTH, NORTH_WEST),
/*  22 */   NORTH_NORTH_EAST(NORTH, NORTH_EAST),
/*  23 */   EAST_NORTH_EAST(EAST, NORTH_EAST),
/*  24 */   EAST_SOUTH_EAST(EAST, SOUTH_EAST),
/*  25 */   SOUTH_SOUTH_EAST(SOUTH, SOUTH_EAST),
/*  26 */   SOUTH_SOUTH_WEST(SOUTH, SOUTH_WEST),
/*  27 */   WEST_SOUTH_WEST(WEST, SOUTH_WEST),
/*  28 */   SELF(0, 0, 0);
/*     */   
/*     */   private final int modX;
/*     */   private final int modY;
/*     */   private final int modZ;
/*     */   
/*     */   BlockFace(int modX, int modY, int modZ) {
/*  35 */     this.modX = modX;
/*  36 */     this.modY = modY;
/*  37 */     this.modZ = modZ;
/*     */   }
/*     */   
/*     */   BlockFace(BlockFace face1, BlockFace face2) {
/*  41 */     this.modX = face1.getModX() + face2.getModX();
/*  42 */     this.modY = face1.getModY() + face2.getModY();
/*  43 */     this.modZ = face1.getModZ() + face2.getModZ();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getModX() {
/*  52 */     return this.modX;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getModY() {
/*  61 */     return this.modY;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getModZ() {
/*  70 */     return this.modZ;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Vector getDirection() {
/*  80 */     Vector direction = new Vector(this.modX, this.modY, this.modZ);
/*  81 */     if (this.modX != 0 || this.modY != 0 || this.modZ != 0) {
/*  82 */       direction.normalize();
/*     */     }
/*  84 */     return direction;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public BlockFace getOppositeFace() {
/*  89 */     switch (this) {
/*     */       case NORTH:
/*  91 */         return SOUTH;
/*     */       
/*     */       case SOUTH:
/*  94 */         return NORTH;
/*     */       
/*     */       case EAST:
/*  97 */         return WEST;
/*     */       
/*     */       case WEST:
/* 100 */         return EAST;
/*     */       
/*     */       case UP:
/* 103 */         return DOWN;
/*     */       
/*     */       case DOWN:
/* 106 */         return UP;
/*     */       
/*     */       case NORTH_EAST:
/* 109 */         return SOUTH_WEST;
/*     */       
/*     */       case NORTH_WEST:
/* 112 */         return SOUTH_EAST;
/*     */       
/*     */       case SOUTH_EAST:
/* 115 */         return NORTH_WEST;
/*     */       
/*     */       case SOUTH_WEST:
/* 118 */         return NORTH_EAST;
/*     */       
/*     */       case WEST_NORTH_WEST:
/* 121 */         return EAST_SOUTH_EAST;
/*     */       
/*     */       case NORTH_NORTH_WEST:
/* 124 */         return SOUTH_SOUTH_EAST;
/*     */       
/*     */       case NORTH_NORTH_EAST:
/* 127 */         return SOUTH_SOUTH_WEST;
/*     */       
/*     */       case EAST_NORTH_EAST:
/* 130 */         return WEST_SOUTH_WEST;
/*     */       
/*     */       case EAST_SOUTH_EAST:
/* 133 */         return WEST_NORTH_WEST;
/*     */       
/*     */       case SOUTH_SOUTH_EAST:
/* 136 */         return NORTH_NORTH_WEST;
/*     */       
/*     */       case SOUTH_SOUTH_WEST:
/* 139 */         return NORTH_NORTH_EAST;
/*     */       
/*     */       case WEST_SOUTH_WEST:
/* 142 */         return EAST_NORTH_EAST;
/*     */       
/*     */       case SELF:
/* 145 */         return SELF;
/*     */     } 
/*     */     
/* 148 */     return SELF;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\block\BlockFace.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
/*     */ package org.bukkit.craftbukkit.v1_16_R2.block.data;
/*     */ import net.minecraft.server.v1_16_R2.BlockStateInteger;
/*     */ import net.minecraft.server.v1_16_R2.IBlockState;
/*     */ import org.bukkit.block.BlockFace;
/*     */ 
/*     */ public abstract class CraftRotatable extends CraftBlockData implements Rotatable {
/*   7 */   private static final BlockStateInteger ROTATION = getInteger("rotation");
/*     */ 
/*     */   
/*     */   public BlockFace getRotation() {
/*  11 */     int data = ((Integer)get((IBlockState<Integer>)ROTATION)).intValue();
/*  12 */     switch (data) {
/*     */       case 0:
/*  14 */         return BlockFace.SOUTH;
/*     */       case 1:
/*  16 */         return BlockFace.SOUTH_SOUTH_WEST;
/*     */       case 2:
/*  18 */         return BlockFace.SOUTH_WEST;
/*     */       case 3:
/*  20 */         return BlockFace.WEST_SOUTH_WEST;
/*     */       case 4:
/*  22 */         return BlockFace.WEST;
/*     */       case 5:
/*  24 */         return BlockFace.WEST_NORTH_WEST;
/*     */       case 6:
/*  26 */         return BlockFace.NORTH_WEST;
/*     */       case 7:
/*  28 */         return BlockFace.NORTH_NORTH_WEST;
/*     */       case 8:
/*  30 */         return BlockFace.NORTH;
/*     */       case 9:
/*  32 */         return BlockFace.NORTH_NORTH_EAST;
/*     */       case 10:
/*  34 */         return BlockFace.NORTH_EAST;
/*     */       case 11:
/*  36 */         return BlockFace.EAST_NORTH_EAST;
/*     */       case 12:
/*  38 */         return BlockFace.EAST;
/*     */       case 13:
/*  40 */         return BlockFace.EAST_SOUTH_EAST;
/*     */       case 14:
/*  42 */         return BlockFace.SOUTH_EAST;
/*     */       case 15:
/*  44 */         return BlockFace.SOUTH_SOUTH_EAST;
/*     */     } 
/*  46 */     throw new IllegalArgumentException("Unknown rotation " + data);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRotation(BlockFace rotation) {
/*     */     int val;
/*  53 */     switch (rotation) {
/*     */       case SOUTH:
/*  55 */         val = 0;
/*     */         break;
/*     */       case SOUTH_SOUTH_WEST:
/*  58 */         val = 1;
/*     */         break;
/*     */       case SOUTH_WEST:
/*  61 */         val = 2;
/*     */         break;
/*     */       case WEST_SOUTH_WEST:
/*  64 */         val = 3;
/*     */         break;
/*     */       case WEST:
/*  67 */         val = 4;
/*     */         break;
/*     */       case WEST_NORTH_WEST:
/*  70 */         val = 5;
/*     */         break;
/*     */       case NORTH_WEST:
/*  73 */         val = 6;
/*     */         break;
/*     */       case NORTH_NORTH_WEST:
/*  76 */         val = 7;
/*     */         break;
/*     */       case NORTH:
/*  79 */         val = 8;
/*     */         break;
/*     */       case NORTH_NORTH_EAST:
/*  82 */         val = 9;
/*     */         break;
/*     */       case NORTH_EAST:
/*  85 */         val = 10;
/*     */         break;
/*     */       case EAST_NORTH_EAST:
/*  88 */         val = 11;
/*     */         break;
/*     */       case EAST:
/*  91 */         val = 12;
/*     */         break;
/*     */       case EAST_SOUTH_EAST:
/*  94 */         val = 13;
/*     */         break;
/*     */       case SOUTH_EAST:
/*  97 */         val = 14;
/*     */         break;
/*     */       case SOUTH_SOUTH_EAST:
/* 100 */         val = 15;
/*     */         break;
/*     */       default:
/* 103 */         throw new IllegalArgumentException("Illegal rotation " + rotation);
/*     */     } 
/* 105 */     set((IBlockState<Comparable>)ROTATION, Integer.valueOf(val));
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\data\CraftRotatable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
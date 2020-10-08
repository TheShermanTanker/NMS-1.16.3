/*     */ package org.bukkit.craftbukkit.v1_16_R2.block.impl;
/*     */ 
/*     */ import net.minecraft.server.v1_16_R2.BlockSkullPlayer;
/*     */ import net.minecraft.server.v1_16_R2.BlockStateInteger;
/*     */ import net.minecraft.server.v1_16_R2.IBlockData;
/*     */ import net.minecraft.server.v1_16_R2.IBlockState;
/*     */ import org.bukkit.block.BlockFace;
/*     */ import org.bukkit.block.data.Rotatable;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.block.data.CraftBlockData;
/*     */ 
/*     */ public final class CraftSkullPlayer extends CraftBlockData implements Rotatable {
/*     */   public CraftSkullPlayer(IBlockData state) {
/*  13 */     super(state);
/*     */   }
/*     */   
/*     */   public CraftSkullPlayer() {}
/*     */   
/*  18 */   private static final BlockStateInteger ROTATION = getInteger(BlockSkullPlayer.class, "rotation");
/*     */ 
/*     */   
/*     */   public BlockFace getRotation() {
/*  22 */     int data = ((Integer)get((IBlockState)ROTATION)).intValue();
/*  23 */     switch (data) {
/*     */       case 0:
/*  25 */         return BlockFace.SOUTH;
/*     */       case 1:
/*  27 */         return BlockFace.SOUTH_SOUTH_WEST;
/*     */       case 2:
/*  29 */         return BlockFace.SOUTH_WEST;
/*     */       case 3:
/*  31 */         return BlockFace.WEST_SOUTH_WEST;
/*     */       case 4:
/*  33 */         return BlockFace.WEST;
/*     */       case 5:
/*  35 */         return BlockFace.WEST_NORTH_WEST;
/*     */       case 6:
/*  37 */         return BlockFace.NORTH_WEST;
/*     */       case 7:
/*  39 */         return BlockFace.NORTH_NORTH_WEST;
/*     */       case 8:
/*  41 */         return BlockFace.NORTH;
/*     */       case 9:
/*  43 */         return BlockFace.NORTH_NORTH_EAST;
/*     */       case 10:
/*  45 */         return BlockFace.NORTH_EAST;
/*     */       case 11:
/*  47 */         return BlockFace.EAST_NORTH_EAST;
/*     */       case 12:
/*  49 */         return BlockFace.EAST;
/*     */       case 13:
/*  51 */         return BlockFace.EAST_SOUTH_EAST;
/*     */       case 14:
/*  53 */         return BlockFace.SOUTH_EAST;
/*     */       case 15:
/*  55 */         return BlockFace.SOUTH_SOUTH_EAST;
/*     */     } 
/*  57 */     throw new IllegalArgumentException("Unknown rotation " + data);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRotation(BlockFace rotation) {
/*     */     int val;
/*  64 */     switch (rotation) {
/*     */       case SOUTH:
/*  66 */         val = 0;
/*     */         break;
/*     */       case SOUTH_SOUTH_WEST:
/*  69 */         val = 1;
/*     */         break;
/*     */       case SOUTH_WEST:
/*  72 */         val = 2;
/*     */         break;
/*     */       case WEST_SOUTH_WEST:
/*  75 */         val = 3;
/*     */         break;
/*     */       case WEST:
/*  78 */         val = 4;
/*     */         break;
/*     */       case WEST_NORTH_WEST:
/*  81 */         val = 5;
/*     */         break;
/*     */       case NORTH_WEST:
/*  84 */         val = 6;
/*     */         break;
/*     */       case NORTH_NORTH_WEST:
/*  87 */         val = 7;
/*     */         break;
/*     */       case NORTH:
/*  90 */         val = 8;
/*     */         break;
/*     */       case NORTH_NORTH_EAST:
/*  93 */         val = 9;
/*     */         break;
/*     */       case NORTH_EAST:
/*  96 */         val = 10;
/*     */         break;
/*     */       case EAST_NORTH_EAST:
/*  99 */         val = 11;
/*     */         break;
/*     */       case EAST:
/* 102 */         val = 12;
/*     */         break;
/*     */       case EAST_SOUTH_EAST:
/* 105 */         val = 13;
/*     */         break;
/*     */       case SOUTH_EAST:
/* 108 */         val = 14;
/*     */         break;
/*     */       case SOUTH_SOUTH_EAST:
/* 111 */         val = 15;
/*     */         break;
/*     */       default:
/* 114 */         throw new IllegalArgumentException("Illegal rotation " + rotation);
/*     */     } 
/* 116 */     set((IBlockState)ROTATION, Integer.valueOf(val));
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\impl\CraftSkullPlayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
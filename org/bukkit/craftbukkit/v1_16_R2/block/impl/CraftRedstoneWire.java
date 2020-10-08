/*    */ package org.bukkit.craftbukkit.v1_16_R2.block.impl;
/*    */ import java.util.Set;
/*    */ import net.minecraft.server.v1_16_R2.BlockRedstoneWire;
/*    */ import net.minecraft.server.v1_16_R2.BlockStateEnum;
/*    */ import net.minecraft.server.v1_16_R2.IBlockData;
/*    */ import net.minecraft.server.v1_16_R2.IBlockState;
/*    */ import org.bukkit.block.BlockFace;
/*    */ import org.bukkit.block.data.AnaloguePowerable;
/*    */ import org.bukkit.block.data.type.RedstoneWire;
/*    */ 
/*    */ public final class CraftRedstoneWire extends CraftBlockData implements RedstoneWire, AnaloguePowerable {
/*    */   public CraftRedstoneWire(IBlockData state) {
/* 13 */     super(state);
/*    */   }
/*    */   
/*    */   public CraftRedstoneWire() {}
/*    */   
/* 18 */   private static final BlockStateEnum<?> NORTH = getEnum(BlockRedstoneWire.class, "north");
/* 19 */   private static final BlockStateEnum<?> EAST = getEnum(BlockRedstoneWire.class, "east");
/* 20 */   private static final BlockStateEnum<?> SOUTH = getEnum(BlockRedstoneWire.class, "south");
/* 21 */   private static final BlockStateEnum<?> WEST = getEnum(BlockRedstoneWire.class, "west");
/*    */ 
/*    */   
/*    */   public RedstoneWire.Connection getFace(BlockFace face) {
/* 25 */     switch (face) {
/*    */       case NORTH:
/* 27 */         return (RedstoneWire.Connection)get(NORTH, RedstoneWire.Connection.class);
/*    */       case EAST:
/* 29 */         return (RedstoneWire.Connection)get(EAST, RedstoneWire.Connection.class);
/*    */       case SOUTH:
/* 31 */         return (RedstoneWire.Connection)get(SOUTH, RedstoneWire.Connection.class);
/*    */       case WEST:
/* 33 */         return (RedstoneWire.Connection)get(WEST, RedstoneWire.Connection.class);
/*    */     } 
/* 35 */     throw new IllegalArgumentException("Cannot have face " + face);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void setFace(BlockFace face, RedstoneWire.Connection connection) {
/* 41 */     switch (face) {
/*    */       case NORTH:
/* 43 */         set(NORTH, (Enum)connection);
/*    */         return;
/*    */       case EAST:
/* 46 */         set(EAST, (Enum)connection);
/*    */         return;
/*    */       case SOUTH:
/* 49 */         set(SOUTH, (Enum)connection);
/*    */         return;
/*    */       case WEST:
/* 52 */         set(WEST, (Enum)connection);
/*    */         return;
/*    */     } 
/* 55 */     throw new IllegalArgumentException("Cannot have face " + face);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Set<BlockFace> getAllowedFaces() {
/* 61 */     return (Set<BlockFace>)ImmutableSet.of(BlockFace.NORTH, BlockFace.EAST, BlockFace.SOUTH, BlockFace.WEST);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 66 */   private static final BlockStateInteger POWER = getInteger(BlockRedstoneWire.class, "power");
/*    */ 
/*    */   
/*    */   public int getPower() {
/* 70 */     return ((Integer)get((IBlockState)POWER)).intValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setPower(int power) {
/* 75 */     set((IBlockState)POWER, Integer.valueOf(power));
/*    */   }
/*    */ 
/*    */   
/*    */   public int getMaximumPower() {
/* 80 */     return getMax(POWER);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\impl\CraftRedstoneWire.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
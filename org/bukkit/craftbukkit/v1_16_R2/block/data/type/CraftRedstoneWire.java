/*    */ package org.bukkit.craftbukkit.v1_16_R2.block.data.type;
/*    */ import java.util.Set;
/*    */ import net.minecraft.server.v1_16_R2.BlockStateEnum;
/*    */ import org.bukkit.block.BlockFace;
/*    */ import org.bukkit.block.data.type.RedstoneWire;
/*    */ 
/*    */ public abstract class CraftRedstoneWire extends CraftBlockData implements RedstoneWire {
/*  8 */   private static final BlockStateEnum<?> NORTH = getEnum("north");
/*  9 */   private static final BlockStateEnum<?> EAST = getEnum("east");
/* 10 */   private static final BlockStateEnum<?> SOUTH = getEnum("south");
/* 11 */   private static final BlockStateEnum<?> WEST = getEnum("west");
/*    */ 
/*    */   
/*    */   public RedstoneWire.Connection getFace(BlockFace face) {
/* 15 */     switch (face) {
/*    */       case NORTH:
/* 17 */         return (RedstoneWire.Connection)get(NORTH, RedstoneWire.Connection.class);
/*    */       case EAST:
/* 19 */         return (RedstoneWire.Connection)get(EAST, RedstoneWire.Connection.class);
/*    */       case SOUTH:
/* 21 */         return (RedstoneWire.Connection)get(SOUTH, RedstoneWire.Connection.class);
/*    */       case WEST:
/* 23 */         return (RedstoneWire.Connection)get(WEST, RedstoneWire.Connection.class);
/*    */     } 
/* 25 */     throw new IllegalArgumentException("Cannot have face " + face);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void setFace(BlockFace face, RedstoneWire.Connection connection) {
/* 31 */     switch (face) {
/*    */       case NORTH:
/* 33 */         set(NORTH, (Enum)connection);
/*    */         return;
/*    */       case EAST:
/* 36 */         set(EAST, (Enum)connection);
/*    */         return;
/*    */       case SOUTH:
/* 39 */         set(SOUTH, (Enum)connection);
/*    */         return;
/*    */       case WEST:
/* 42 */         set(WEST, (Enum)connection);
/*    */         return;
/*    */     } 
/* 45 */     throw new IllegalArgumentException("Cannot have face " + face);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Set<BlockFace> getAllowedFaces() {
/* 51 */     return (Set<BlockFace>)ImmutableSet.of(BlockFace.NORTH, BlockFace.EAST, BlockFace.SOUTH, BlockFace.WEST);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\data\type\CraftRedstoneWire.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
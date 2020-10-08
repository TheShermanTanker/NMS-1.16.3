/*    */ package org.bukkit.craftbukkit.v1_16_R2.block.impl;
/*    */ import java.util.Set;
/*    */ import net.minecraft.server.v1_16_R2.BlockBed;
/*    */ import net.minecraft.server.v1_16_R2.BlockStateBoolean;
/*    */ import net.minecraft.server.v1_16_R2.BlockStateEnum;
/*    */ import net.minecraft.server.v1_16_R2.IBlockData;
/*    */ import net.minecraft.server.v1_16_R2.IBlockState;
/*    */ import org.bukkit.block.BlockFace;
/*    */ import org.bukkit.block.data.type.Bed;
/*    */ 
/*    */ public final class CraftBed extends CraftBlockData implements Bed, Directional {
/*    */   public CraftBed(IBlockData state) {
/* 13 */     super(state);
/*    */   }
/*    */   
/*    */   public CraftBed() {}
/*    */   
/* 18 */   private static final BlockStateEnum<?> PART = getEnum(BlockBed.class, "part");
/* 19 */   private static final BlockStateBoolean OCCUPIED = getBoolean(BlockBed.class, "occupied");
/*    */ 
/*    */   
/*    */   public Bed.Part getPart() {
/* 23 */     return (Bed.Part)get(PART, Bed.Part.class);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setPart(Bed.Part part) {
/* 28 */     set(PART, (Enum)part);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isOccupied() {
/* 33 */     return ((Boolean)get((IBlockState)OCCUPIED)).booleanValue();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 38 */   private static final BlockStateEnum<?> FACING = getEnum(BlockBed.class, "facing");
/*    */ 
/*    */   
/*    */   public BlockFace getFacing() {
/* 42 */     return (BlockFace)get(FACING, BlockFace.class);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setFacing(BlockFace facing) {
/* 47 */     set(FACING, (Enum)facing);
/*    */   }
/*    */ 
/*    */   
/*    */   public Set<BlockFace> getFaces() {
/* 52 */     return getValues(FACING, BlockFace.class);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\impl\CraftBed.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
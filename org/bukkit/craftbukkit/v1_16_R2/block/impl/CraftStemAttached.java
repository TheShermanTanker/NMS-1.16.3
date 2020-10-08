/*    */ package org.bukkit.craftbukkit.v1_16_R2.block.impl;
/*    */ 
/*    */ import java.util.Set;
/*    */ import net.minecraft.server.v1_16_R2.BlockStateEnum;
/*    */ import net.minecraft.server.v1_16_R2.BlockStemAttached;
/*    */ import net.minecraft.server.v1_16_R2.IBlockData;
/*    */ import org.bukkit.block.BlockFace;
/*    */ import org.bukkit.block.data.Directional;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.block.data.CraftBlockData;
/*    */ 
/*    */ public final class CraftStemAttached extends CraftBlockData implements Directional {
/*    */   public CraftStemAttached(IBlockData state) {
/* 13 */     super(state);
/*    */   }
/*    */   
/*    */   public CraftStemAttached() {}
/*    */   
/* 18 */   private static final BlockStateEnum<?> FACING = getEnum(BlockStemAttached.class, "facing");
/*    */ 
/*    */   
/*    */   public BlockFace getFacing() {
/* 22 */     return (BlockFace)get(FACING, BlockFace.class);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setFacing(BlockFace facing) {
/* 27 */     set(FACING, (Enum)facing);
/*    */   }
/*    */ 
/*    */   
/*    */   public Set<BlockFace> getFaces() {
/* 32 */     return getValues(FACING, BlockFace.class);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\impl\CraftStemAttached.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
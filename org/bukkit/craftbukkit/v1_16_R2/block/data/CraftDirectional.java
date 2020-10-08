/*    */ package org.bukkit.craftbukkit.v1_16_R2.block.data;
/*    */ import java.util.Set;
/*    */ import net.minecraft.server.v1_16_R2.BlockStateEnum;
/*    */ import org.bukkit.block.BlockFace;
/*    */ 
/*    */ public abstract class CraftDirectional extends CraftBlockData implements Directional {
/*  7 */   private static final BlockStateEnum<?> FACING = getEnum("facing");
/*    */ 
/*    */   
/*    */   public BlockFace getFacing() {
/* 11 */     return get(FACING, BlockFace.class);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setFacing(BlockFace facing) {
/* 16 */     set(FACING, (Enum<Enum>)facing);
/*    */   }
/*    */ 
/*    */   
/*    */   public Set<BlockFace> getFaces() {
/* 21 */     return getValues(FACING, BlockFace.class);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\data\CraftDirectional.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
/*    */ package org.bukkit.craftbukkit.v1_16_R2.block.data;
/*    */ import net.minecraft.server.v1_16_R2.BlockStateBoolean;
/*    */ import net.minecraft.server.v1_16_R2.IBlockState;
/*    */ import org.bukkit.block.data.Attachable;
/*    */ 
/*    */ public abstract class CraftAttachable extends CraftBlockData implements Attachable {
/*  7 */   private static final BlockStateBoolean ATTACHED = getBoolean("attached");
/*    */ 
/*    */   
/*    */   public boolean isAttached() {
/* 11 */     return ((Boolean)get((IBlockState<Boolean>)ATTACHED)).booleanValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setAttached(boolean attached) {
/* 16 */     set((IBlockState<Comparable>)ATTACHED, Boolean.valueOf(attached));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\data\CraftAttachable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
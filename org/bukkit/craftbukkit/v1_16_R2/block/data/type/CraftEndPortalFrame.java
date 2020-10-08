/*    */ package org.bukkit.craftbukkit.v1_16_R2.block.data.type;
/*    */ import net.minecraft.server.v1_16_R2.BlockStateBoolean;
/*    */ import net.minecraft.server.v1_16_R2.IBlockState;
/*    */ import org.bukkit.block.data.type.EndPortalFrame;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.block.data.CraftBlockData;
/*    */ 
/*    */ public abstract class CraftEndPortalFrame extends CraftBlockData implements EndPortalFrame {
/*  8 */   private static final BlockStateBoolean EYE = getBoolean("eye");
/*    */ 
/*    */   
/*    */   public boolean hasEye() {
/* 12 */     return ((Boolean)get((IBlockState)EYE)).booleanValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setEye(boolean eye) {
/* 17 */     set((IBlockState)EYE, Boolean.valueOf(eye));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\data\type\CraftEndPortalFrame.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
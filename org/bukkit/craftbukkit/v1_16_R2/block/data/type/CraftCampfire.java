/*    */ package org.bukkit.craftbukkit.v1_16_R2.block.data.type;
/*    */ import net.minecraft.server.v1_16_R2.BlockStateBoolean;
/*    */ import net.minecraft.server.v1_16_R2.IBlockState;
/*    */ import org.bukkit.block.data.type.Campfire;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.block.data.CraftBlockData;
/*    */ 
/*    */ public abstract class CraftCampfire extends CraftBlockData implements Campfire {
/*  8 */   private static final BlockStateBoolean SIGNAL_FIRE = getBoolean("signal_fire");
/*    */ 
/*    */   
/*    */   public boolean isSignalFire() {
/* 12 */     return ((Boolean)get((IBlockState)SIGNAL_FIRE)).booleanValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setSignalFire(boolean signalFire) {
/* 17 */     set((IBlockState)SIGNAL_FIRE, Boolean.valueOf(signalFire));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\data\type\CraftCampfire.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
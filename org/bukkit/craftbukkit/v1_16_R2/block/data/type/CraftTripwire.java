/*    */ package org.bukkit.craftbukkit.v1_16_R2.block.data.type;
/*    */ import net.minecraft.server.v1_16_R2.BlockStateBoolean;
/*    */ import net.minecraft.server.v1_16_R2.IBlockState;
/*    */ import org.bukkit.block.data.type.Tripwire;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.block.data.CraftBlockData;
/*    */ 
/*    */ public abstract class CraftTripwire extends CraftBlockData implements Tripwire {
/*  8 */   private static final BlockStateBoolean DISARMED = getBoolean("disarmed");
/*    */ 
/*    */   
/*    */   public boolean isDisarmed() {
/* 12 */     return ((Boolean)get((IBlockState)DISARMED)).booleanValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setDisarmed(boolean disarmed) {
/* 17 */     set((IBlockState)DISARMED, Boolean.valueOf(disarmed));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\data\type\CraftTripwire.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
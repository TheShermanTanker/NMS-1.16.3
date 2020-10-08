/*    */ package org.bukkit.craftbukkit.v1_16_R2.block.data.type;
/*    */ import net.minecraft.server.v1_16_R2.BlockStateBoolean;
/*    */ import net.minecraft.server.v1_16_R2.IBlockState;
/*    */ import org.bukkit.block.data.type.Dispenser;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.block.data.CraftBlockData;
/*    */ 
/*    */ public abstract class CraftDispenser extends CraftBlockData implements Dispenser {
/*  8 */   private static final BlockStateBoolean TRIGGERED = getBoolean("triggered");
/*    */ 
/*    */   
/*    */   public boolean isTriggered() {
/* 12 */     return ((Boolean)get((IBlockState)TRIGGERED)).booleanValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setTriggered(boolean triggered) {
/* 17 */     set((IBlockState)TRIGGERED, Boolean.valueOf(triggered));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\data\type\CraftDispenser.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
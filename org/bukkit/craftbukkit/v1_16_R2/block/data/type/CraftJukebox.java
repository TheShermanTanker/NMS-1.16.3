/*    */ package org.bukkit.craftbukkit.v1_16_R2.block.data.type;
/*    */ import net.minecraft.server.v1_16_R2.BlockStateBoolean;
/*    */ import net.minecraft.server.v1_16_R2.IBlockState;
/*    */ import org.bukkit.block.data.type.Jukebox;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.block.data.CraftBlockData;
/*    */ 
/*    */ public abstract class CraftJukebox extends CraftBlockData implements Jukebox {
/*  8 */   private static final BlockStateBoolean HAS_RECORD = getBoolean("has_record");
/*    */ 
/*    */   
/*    */   public boolean hasRecord() {
/* 12 */     return ((Boolean)get((IBlockState)HAS_RECORD)).booleanValue();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\data\type\CraftJukebox.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
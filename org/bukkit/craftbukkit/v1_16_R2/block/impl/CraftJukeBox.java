/*    */ package org.bukkit.craftbukkit.v1_16_R2.block.impl;
/*    */ 
/*    */ import net.minecraft.server.v1_16_R2.BlockJukeBox;
/*    */ import net.minecraft.server.v1_16_R2.BlockStateBoolean;
/*    */ import net.minecraft.server.v1_16_R2.IBlockData;
/*    */ import net.minecraft.server.v1_16_R2.IBlockState;
/*    */ import org.bukkit.block.data.type.Jukebox;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.block.data.CraftBlockData;
/*    */ 
/*    */ public final class CraftJukeBox
/*    */   extends CraftBlockData implements Jukebox {
/*    */   public CraftJukeBox(IBlockData state) {
/* 13 */     super(state);
/*    */   }
/*    */   
/*    */   public CraftJukeBox() {}
/*    */   
/* 18 */   private static final BlockStateBoolean HAS_RECORD = getBoolean(BlockJukeBox.class, "has_record");
/*    */ 
/*    */   
/*    */   public boolean hasRecord() {
/* 22 */     return ((Boolean)get((IBlockState)HAS_RECORD)).booleanValue();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\impl\CraftJukeBox.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
/*    */ package org.bukkit.craftbukkit.v1_16_R2.block.data.type;
/*    */ import net.minecraft.server.v1_16_R2.BlockStateBoolean;
/*    */ import net.minecraft.server.v1_16_R2.IBlockState;
/*    */ import org.bukkit.block.data.type.Lectern;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.block.data.CraftBlockData;
/*    */ 
/*    */ public abstract class CraftLectern extends CraftBlockData implements Lectern {
/*  8 */   private static final BlockStateBoolean HAS_BOOK = getBoolean("has_book");
/*    */ 
/*    */   
/*    */   public boolean hasBook() {
/* 12 */     return ((Boolean)get((IBlockState)HAS_BOOK)).booleanValue();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\data\type\CraftLectern.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
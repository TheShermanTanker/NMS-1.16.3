/*    */ package org.bukkit.craftbukkit.v1_16_R2.block;
/*    */ 
/*    */ import net.minecraft.server.v1_16_R2.TileEntityBlastFurnace;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.block.BlastFurnace;
/*    */ import org.bukkit.block.Block;
/*    */ 
/*    */ public class CraftBlastFurnace
/*    */   extends CraftFurnace<TileEntityBlastFurnace> implements BlastFurnace {
/*    */   public CraftBlastFurnace(Block block) {
/* 11 */     super(block, TileEntityBlastFurnace.class);
/*    */   }
/*    */   
/*    */   public CraftBlastFurnace(Material material, TileEntityBlastFurnace te) {
/* 15 */     super(material, te);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\CraftBlastFurnace.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
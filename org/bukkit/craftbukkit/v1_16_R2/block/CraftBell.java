/*    */ package org.bukkit.craftbukkit.v1_16_R2.block;
/*    */ 
/*    */ import net.minecraft.server.v1_16_R2.TileEntityBell;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.block.Bell;
/*    */ import org.bukkit.block.Block;
/*    */ 
/*    */ public class CraftBell
/*    */   extends CraftBlockEntityState<TileEntityBell> implements Bell {
/*    */   public CraftBell(Block block) {
/* 11 */     super(block, TileEntityBell.class);
/*    */   }
/*    */   
/*    */   public CraftBell(Material material, TileEntityBell te) {
/* 15 */     super(material, te);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\CraftBell.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
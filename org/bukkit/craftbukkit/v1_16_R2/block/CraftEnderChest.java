/*    */ package org.bukkit.craftbukkit.v1_16_R2.block;
/*    */ 
/*    */ import net.minecraft.server.v1_16_R2.TileEntityEnderChest;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.block.EnderChest;
/*    */ 
/*    */ public class CraftEnderChest
/*    */   extends CraftBlockEntityState<TileEntityEnderChest> implements EnderChest {
/*    */   public CraftEnderChest(Block block) {
/* 11 */     super(block, TileEntityEnderChest.class);
/*    */   }
/*    */   
/*    */   public CraftEnderChest(Material material, TileEntityEnderChest te) {
/* 15 */     super(material, te);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\CraftEnderChest.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
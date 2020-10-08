/*    */ package org.bukkit.craftbukkit.v1_16_R2.block;
/*    */ 
/*    */ import net.minecraft.server.v1_16_R2.TileEntityConduit;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.block.Conduit;
/*    */ 
/*    */ public class CraftConduit
/*    */   extends CraftBlockEntityState<TileEntityConduit> implements Conduit {
/*    */   public CraftConduit(Block block) {
/* 11 */     super(block, TileEntityConduit.class);
/*    */   }
/*    */   
/*    */   public CraftConduit(Material material, TileEntityConduit te) {
/* 15 */     super(material, te);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\CraftConduit.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
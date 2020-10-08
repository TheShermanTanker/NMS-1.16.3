/*    */ package org.bukkit.craftbukkit.v1_16_R2.block;
/*    */ 
/*    */ import net.minecraft.server.v1_16_R2.TileEntityComparator;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.block.Comparator;
/*    */ 
/*    */ public class CraftComparator
/*    */   extends CraftBlockEntityState<TileEntityComparator> implements Comparator {
/*    */   public CraftComparator(Block block) {
/* 11 */     super(block, TileEntityComparator.class);
/*    */   }
/*    */   
/*    */   public CraftComparator(Material material, TileEntityComparator te) {
/* 15 */     super(material, te);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\CraftComparator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
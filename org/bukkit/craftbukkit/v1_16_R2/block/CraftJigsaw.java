/*    */ package org.bukkit.craftbukkit.v1_16_R2.block;
/*    */ 
/*    */ import net.minecraft.server.v1_16_R2.TileEntityJigsaw;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.block.Jigsaw;
/*    */ 
/*    */ public class CraftJigsaw
/*    */   extends CraftBlockEntityState<TileEntityJigsaw> implements Jigsaw {
/*    */   public CraftJigsaw(Block block) {
/* 11 */     super(block, TileEntityJigsaw.class);
/*    */   }
/*    */   
/*    */   public CraftJigsaw(Material material, TileEntityJigsaw te) {
/* 15 */     super(material, te);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\CraftJigsaw.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
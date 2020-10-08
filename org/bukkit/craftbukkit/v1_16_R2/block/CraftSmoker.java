/*    */ package org.bukkit.craftbukkit.v1_16_R2.block;
/*    */ 
/*    */ import net.minecraft.server.v1_16_R2.TileEntitySmoker;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.block.Smoker;
/*    */ 
/*    */ public class CraftSmoker
/*    */   extends CraftFurnace<TileEntitySmoker> implements Smoker {
/*    */   public CraftSmoker(Block block) {
/* 11 */     super(block, TileEntitySmoker.class);
/*    */   }
/*    */   
/*    */   public CraftSmoker(Material material, TileEntitySmoker te) {
/* 15 */     super(material, te);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\CraftSmoker.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
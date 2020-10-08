/*    */ package org.bukkit.craftbukkit.v1_16_R2.block;
/*    */ 
/*    */ import net.minecraft.server.v1_16_R2.TileEntityFurnaceFurnace;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.block.Block;
/*    */ 
/*    */ public class CraftFurnaceFurnace
/*    */   extends CraftFurnace<TileEntityFurnaceFurnace> {
/*    */   public CraftFurnaceFurnace(Block block) {
/* 10 */     super(block, TileEntityFurnaceFurnace.class);
/*    */   }
/*    */   
/*    */   public CraftFurnaceFurnace(Material material, TileEntityFurnaceFurnace te) {
/* 14 */     super(material, te);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\CraftFurnaceFurnace.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
/*    */ package org.bukkit.craftbukkit.v1_16_R2.block;
/*    */ 
/*    */ import net.minecraft.server.v1_16_R2.TileEntityLightDetector;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.block.DaylightDetector;
/*    */ 
/*    */ public class CraftDaylightDetector
/*    */   extends CraftBlockEntityState<TileEntityLightDetector> implements DaylightDetector {
/*    */   public CraftDaylightDetector(Block block) {
/* 11 */     super(block, TileEntityLightDetector.class);
/*    */   }
/*    */   
/*    */   public CraftDaylightDetector(Material material, TileEntityLightDetector te) {
/* 15 */     super(material, te);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\CraftDaylightDetector.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
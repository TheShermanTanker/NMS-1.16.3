/*    */ package org.bukkit.craftbukkit.v1_16_R2.block;
/*    */ 
/*    */ import net.minecraft.server.v1_16_R2.TileEntity;
/*    */ import net.minecraft.server.v1_16_R2.TileEntityEnchantTable;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.block.EnchantingTable;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.util.CraftChatMessage;
/*    */ 
/*    */ public class CraftEnchantingTable extends CraftBlockEntityState<TileEntityEnchantTable> implements EnchantingTable {
/*    */   public CraftEnchantingTable(Block block) {
/* 12 */     super(block, TileEntityEnchantTable.class);
/*    */   }
/*    */   
/*    */   public CraftEnchantingTable(Material material, TileEntityEnchantTable te) {
/* 16 */     super(material, te);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getCustomName() {
/* 21 */     TileEntityEnchantTable enchant = getSnapshot();
/* 22 */     return enchant.hasCustomName() ? CraftChatMessage.fromComponent(enchant.getCustomName()) : null;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCustomName(String name) {
/* 27 */     getSnapshot().setCustomName(CraftChatMessage.fromStringOrNull(name));
/*    */   }
/*    */ 
/*    */   
/*    */   public void applyTo(TileEntityEnchantTable enchantingTable) {
/* 32 */     super.applyTo(enchantingTable);
/*    */     
/* 34 */     if (!getSnapshot().hasCustomName())
/* 35 */       enchantingTable.setCustomName(null); 
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\CraftEnchantingTable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
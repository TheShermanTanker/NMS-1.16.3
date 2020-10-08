/*    */ package org.bukkit.craftbukkit.v1_16_R2.block;
/*    */ import com.google.common.base.Preconditions;
/*    */ import net.minecraft.server.v1_16_R2.BlockFurnace;
/*    */ import net.minecraft.server.v1_16_R2.IBlockState;
/*    */ import net.minecraft.server.v1_16_R2.TileEntityFurnace;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftInventoryFurnace;
/*    */ import org.bukkit.inventory.FurnaceInventory;
/*    */ import org.bukkit.inventory.Inventory;
/*    */ 
/*    */ public abstract class CraftFurnace<T extends TileEntityFurnace> extends CraftContainer<T> implements Furnace {
/*    */   public CraftFurnace(Block block, Class<T> tileEntityClass) {
/* 14 */     super(block, tileEntityClass);
/*    */   }
/*    */   
/*    */   public CraftFurnace(Material material, T te) {
/* 18 */     super(material, te);
/*    */   }
/*    */ 
/*    */   
/*    */   public FurnaceInventory getSnapshotInventory() {
/* 23 */     return (FurnaceInventory)new CraftInventoryFurnace((TileEntityFurnace)getSnapshot());
/*    */   }
/*    */ 
/*    */   
/*    */   public FurnaceInventory getInventory() {
/* 28 */     if (!isPlaced()) {
/* 29 */       return getSnapshotInventory();
/*    */     }
/*    */     
/* 32 */     return (FurnaceInventory)new CraftInventoryFurnace((TileEntityFurnace)getTileEntity());
/*    */   }
/*    */ 
/*    */   
/*    */   public short getBurnTime() {
/* 37 */     return (short)((TileEntityFurnace)getSnapshot()).burnTime;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setBurnTime(short burnTime) {
/* 42 */     ((TileEntityFurnace)getSnapshot()).burnTime = burnTime;
/*    */     
/* 44 */     this.data = (IBlockData)this.data.set((IBlockState)BlockFurnace.LIT, Boolean.valueOf((burnTime > 0)));
/*    */   }
/*    */ 
/*    */   
/*    */   public short getCookTime() {
/* 49 */     return (short)((TileEntityFurnace)getSnapshot()).cookTime;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCookTime(short cookTime) {
/* 54 */     ((TileEntityFurnace)getSnapshot()).cookTime = cookTime;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getCookTimeTotal() {
/* 59 */     return ((TileEntityFurnace)getSnapshot()).cookTimeTotal;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCookTimeTotal(int cookTimeTotal) {
/* 64 */     ((TileEntityFurnace)getSnapshot()).cookTimeTotal = cookTimeTotal;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public double getCookSpeedMultiplier() {
/* 70 */     return ((TileEntityFurnace)getSnapshot()).cookSpeedMultiplier;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCookSpeedMultiplier(double multiplier) {
/* 75 */     Preconditions.checkArgument((multiplier >= 0.0D), "Furnace speed multiplier cannot be negative");
/* 76 */     Preconditions.checkArgument((multiplier <= 200.0D), "Furnace speed multiplier cannot more than 200");
/* 77 */     ((TileEntityFurnace)getSnapshot()).cookSpeedMultiplier = multiplier;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\CraftFurnace.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
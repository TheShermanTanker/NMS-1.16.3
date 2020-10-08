/*    */ package org.bukkit.craftbukkit.v1_16_R2.block;
/*    */ 
/*    */ import net.minecraft.server.v1_16_R2.BlockLectern;
/*    */ import net.minecraft.server.v1_16_R2.TileEntityLectern;
/*    */ import net.minecraft.server.v1_16_R2.World;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.block.Lectern;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftInventoryLectern;
/*    */ import org.bukkit.inventory.Inventory;
/*    */ 
/*    */ public class CraftLectern extends CraftBlockEntityState<TileEntityLectern> implements Lectern {
/*    */   public CraftLectern(Block block) {
/* 14 */     super(block, TileEntityLectern.class);
/*    */   }
/*    */   
/*    */   public CraftLectern(Material material, TileEntityLectern te) {
/* 18 */     super(material, te);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getPage() {
/* 23 */     return getSnapshot().getPage();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setPage(int page) {
/* 28 */     getSnapshot().setPage(page);
/*    */   }
/*    */ 
/*    */   
/*    */   public Inventory getSnapshotInventory() {
/* 33 */     return (Inventory)new CraftInventoryLectern((getSnapshot()).inventory);
/*    */   }
/*    */ 
/*    */   
/*    */   public Inventory getInventory() {
/* 38 */     if (!isPlaced()) {
/* 39 */       return getSnapshotInventory();
/*    */     }
/*    */     
/* 42 */     return (Inventory)new CraftInventoryLectern((getTileEntity()).inventory);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean update(boolean force, boolean applyPhysics) {
/* 47 */     boolean result = super.update(force, applyPhysics);
/*    */     
/* 49 */     if (result && isPlaced() && getType() == Material.LECTERN) {
/* 50 */       BlockLectern.a((World)this.world.getHandle(), getPosition(), getHandle());
/*    */     }
/*    */     
/* 53 */     return result;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\CraftLectern.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
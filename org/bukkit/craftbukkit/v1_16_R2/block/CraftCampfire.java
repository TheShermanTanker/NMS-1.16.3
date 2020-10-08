/*    */ package org.bukkit.craftbukkit.v1_16_R2.block;
/*    */ 
/*    */ import net.minecraft.server.v1_16_R2.ItemStack;
/*    */ import net.minecraft.server.v1_16_R2.TileEntityCampfire;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.block.Campfire;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftItemStack;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ 
/*    */ public class CraftCampfire extends CraftBlockEntityState<TileEntityCampfire> implements Campfire {
/*    */   public CraftCampfire(Block block) {
/* 13 */     super(block, TileEntityCampfire.class);
/*    */   }
/*    */   
/*    */   public CraftCampfire(Material material, TileEntityCampfire te) {
/* 17 */     super(material, te);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getSize() {
/* 22 */     return getSnapshot().getItems().size();
/*    */   }
/*    */ 
/*    */   
/*    */   public ItemStack getItem(int index) {
/* 27 */     ItemStack item = (ItemStack)getSnapshot().getItems().get(index);
/* 28 */     return item.isEmpty() ? null : (ItemStack)CraftItemStack.asCraftMirror(item);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setItem(int index, ItemStack item) {
/* 33 */     getSnapshot().getItems().set(index, CraftItemStack.asNMSCopy(item));
/*    */   }
/*    */ 
/*    */   
/*    */   public int getCookTime(int index) {
/* 38 */     return (getSnapshot()).cookingTimes[index];
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCookTime(int index, int cookTime) {
/* 43 */     (getSnapshot()).cookingTimes[index] = cookTime;
/*    */   }
/*    */ 
/*    */   
/*    */   public int getCookTimeTotal(int index) {
/* 48 */     return (getSnapshot()).cookingTotalTimes[index];
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCookTimeTotal(int index, int cookTimeTotal) {
/* 53 */     (getSnapshot()).cookingTotalTimes[index] = cookTimeTotal;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\CraftCampfire.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
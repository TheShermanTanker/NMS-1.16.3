/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.List;
/*    */ import java.util.Set;
/*    */ import org.bukkit.Location;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.entity.CraftHumanEntity;
/*    */ import org.bukkit.entity.HumanEntity;
/*    */ import org.bukkit.inventory.InventoryHolder;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public interface IInventory
/*    */   extends Clearable
/*    */ {
/*    */   public static final int MAX_STACK = 64;
/*    */   
/*    */   int getSize();
/*    */   
/*    */   boolean isEmpty();
/*    */   
/*    */   ItemStack getItem(int paramInt);
/*    */   
/*    */   ItemStack splitStack(int paramInt1, int paramInt2);
/*    */   
/*    */   ItemStack splitWithoutUpdate(int paramInt);
/*    */   
/*    */   default boolean b(int i, ItemStack itemstack) {
/* 31 */     return true;
/*    */   } void setItem(int paramInt, ItemStack paramItemStack); int getMaxStackSize(); void update(); boolean a(EntityHuman paramEntityHuman); default void startOpen(EntityHuman entityhuman) {}
/*    */   default void closeContainer(EntityHuman entityhuman) {}
/*    */   default int a(Item item) {
/* 35 */     int i = 0;
/*    */     
/* 37 */     for (int j = 0; j < getSize(); j++) {
/* 38 */       ItemStack itemstack = getItem(j);
/*    */       
/* 40 */       if (itemstack.getItem().equals(item)) {
/* 41 */         i += itemstack.getCount();
/*    */       }
/*    */     } 
/*    */     
/* 45 */     return i;
/*    */   }
/*    */   
/*    */   default boolean a(Set<Item> set) {
/* 49 */     for (int i = 0; i < getSize(); i++) {
/* 50 */       ItemStack itemstack = getItem(i);
/*    */       
/* 52 */       if (set.contains(itemstack.getItem()) && itemstack.getCount() > 0) {
/* 53 */         return true;
/*    */       }
/*    */     } 
/*    */     
/* 57 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   List<ItemStack> getContents();
/*    */   
/*    */   void onOpen(CraftHumanEntity paramCraftHumanEntity);
/*    */   
/*    */   void onClose(CraftHumanEntity paramCraftHumanEntity);
/*    */   
/*    */   List<HumanEntity> getViewers();
/*    */   
/*    */   InventoryHolder getOwner();
/*    */   
/*    */   void setMaxStackSize(int paramInt);
/*    */   
/*    */   Location getLocation();
/*    */   
/*    */   default IRecipe getCurrentRecipe() {
/* 76 */     return null;
/*    */   }
/*    */   
/*    */   default void setCurrentRecipe(IRecipe recipe) {}
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\IInventory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
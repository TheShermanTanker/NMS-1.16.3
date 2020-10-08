/*     */ package org.bukkit.craftbukkit.v1_16_R2.inventory;
/*     */ 
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import net.minecraft.server.v1_16_R2.IInventory;
/*     */ import net.minecraft.server.v1_16_R2.IRecipe;
/*     */ import net.minecraft.server.v1_16_R2.ItemStack;
/*     */ import org.bukkit.inventory.CraftingInventory;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.Recipe;
/*     */ 
/*     */ public class CraftInventoryCrafting
/*     */   extends CraftInventory implements CraftingInventory {
/*     */   public CraftInventoryCrafting(IInventory inventory, IInventory resultInventory) {
/*  15 */     super(inventory);
/*  16 */     this.resultInventory = resultInventory;
/*     */   }
/*     */   private final IInventory resultInventory;
/*     */   public IInventory getResultInventory() {
/*  20 */     return this.resultInventory;
/*     */   }
/*     */   
/*     */   public IInventory getMatrixInventory() {
/*  24 */     return this.inventory;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getSize() {
/*  29 */     return getResultInventory().getSize() + getMatrixInventory().getSize();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setContents(ItemStack[] items) {
/*  34 */     if (getSize() > items.length) {
/*  35 */       throw new IllegalArgumentException("Invalid inventory size; expected " + getSize() + " or less");
/*     */     }
/*  37 */     setContents(items[0], Arrays.<ItemStack>copyOfRange(items, 1, items.length));
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack[] getContents() {
/*  42 */     ItemStack[] items = new ItemStack[getSize()];
/*  43 */     List<ItemStack> mcResultItems = getResultInventory().getContents();
/*     */     
/*  45 */     int i = 0;
/*  46 */     for (i = 0; i < mcResultItems.size(); i++) {
/*  47 */       items[i] = CraftItemStack.asCraftMirror(mcResultItems.get(i));
/*     */     }
/*     */     
/*  50 */     List<ItemStack> mcItems = getMatrixInventory().getContents();
/*     */     
/*  52 */     for (int j = 0; j < mcItems.size(); j++) {
/*  53 */       items[i + j] = CraftItemStack.asCraftMirror(mcItems.get(j));
/*     */     }
/*     */     
/*  56 */     return items;
/*     */   }
/*     */   
/*     */   public void setContents(ItemStack result, ItemStack[] contents) {
/*  60 */     setResult(result);
/*  61 */     setMatrix(contents);
/*     */   }
/*     */ 
/*     */   
/*     */   public CraftItemStack getItem(int index) {
/*  66 */     if (index < getResultInventory().getSize()) {
/*  67 */       ItemStack itemStack = getResultInventory().getItem(index);
/*  68 */       return itemStack.isEmpty() ? null : CraftItemStack.asCraftMirror(itemStack);
/*     */     } 
/*  70 */     ItemStack item = getMatrixInventory().getItem(index - getResultInventory().getSize());
/*  71 */     return item.isEmpty() ? null : CraftItemStack.asCraftMirror(item);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setItem(int index, ItemStack item) {
/*  77 */     if (index < getResultInventory().getSize()) {
/*  78 */       getResultInventory().setItem(index, CraftItemStack.asNMSCopy(item));
/*     */     } else {
/*  80 */       getMatrixInventory().setItem(index - getResultInventory().getSize(), CraftItemStack.asNMSCopy(item));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack[] getMatrix() {
/*  86 */     List<ItemStack> matrix = getMatrixInventory().getContents();
/*     */     
/*  88 */     return asCraftMirror(matrix);
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack getResult() {
/*  93 */     ItemStack item = getResultInventory().getItem(0);
/*  94 */     if (!item.isEmpty()) return CraftItemStack.asCraftMirror(item); 
/*  95 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMatrix(ItemStack[] contents) {
/* 100 */     if (getMatrixInventory().getSize() > contents.length) {
/* 101 */       throw new IllegalArgumentException("Invalid inventory size; expected " + getMatrixInventory().getSize() + " or less");
/*     */     }
/*     */     
/* 104 */     for (int i = 0; i < getMatrixInventory().getSize(); i++) {
/* 105 */       if (i < contents.length) {
/* 106 */         getMatrixInventory().setItem(i, CraftItemStack.asNMSCopy(contents[i]));
/*     */       } else {
/* 108 */         getMatrixInventory().setItem(i, ItemStack.b);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void setResult(ItemStack item) {
/* 115 */     List<ItemStack> contents = getResultInventory().getContents();
/* 116 */     contents.set(0, CraftItemStack.asNMSCopy(item));
/*     */   }
/*     */ 
/*     */   
/*     */   public Recipe getRecipe() {
/* 121 */     IRecipe recipe = getInventory().getCurrentRecipe();
/* 122 */     return (recipe == null) ? null : recipe.toBukkitRecipe();
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\inventory\CraftInventoryCrafting.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
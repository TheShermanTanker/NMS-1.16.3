/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.entity.CraftHumanEntity;
/*     */ import org.bukkit.entity.HumanEntity;
/*     */ import org.bukkit.inventory.InventoryHolder;
/*     */ 
/*     */ public class InventoryCraftResult
/*     */   implements IInventory, RecipeHolder
/*     */ {
/*     */   private final NonNullList<ItemStack> items;
/*     */   @Nullable
/*     */   private IRecipe<?> b;
/*  18 */   private int maxStack = 64;
/*     */   
/*     */   public List<ItemStack> getContents() {
/*  21 */     return this.items;
/*     */   }
/*     */   
/*     */   public InventoryHolder getOwner() {
/*  25 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onOpen(CraftHumanEntity who) {}
/*     */   
/*     */   public List<HumanEntity> getViewers() {
/*  32 */     return new ArrayList<>();
/*     */   }
/*     */   public void onClose(CraftHumanEntity who) {}
/*     */   
/*     */   public int getMaxStackSize() {
/*  37 */     return this.maxStack;
/*     */   }
/*     */   
/*     */   public void setMaxStackSize(int size) {
/*  41 */     this.maxStack = size;
/*     */   }
/*     */ 
/*     */   
/*     */   public Location getLocation() {
/*  46 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public InventoryCraftResult() {
/*  51 */     this.items = NonNullList.a(1, ItemStack.b);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getSize() {
/*  56 */     return 1;
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/*     */     ItemStack itemstack;
/*  61 */     Iterator<ItemStack> iterator = this.items.iterator();
/*     */ 
/*     */ 
/*     */     
/*     */     do {
/*  66 */       if (!iterator.hasNext()) {
/*  67 */         return true;
/*     */       }
/*     */       
/*  70 */       itemstack = iterator.next();
/*  71 */     } while (itemstack.isEmpty());
/*     */     
/*  73 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack getItem(int i) {
/*  78 */     return this.items.get(0);
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack splitStack(int i, int j) {
/*  83 */     return ContainerUtil.a(this.items, 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack splitWithoutUpdate(int i) {
/*  88 */     return ContainerUtil.a(this.items, 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setItem(int i, ItemStack itemstack) {
/*  93 */     this.items.set(0, itemstack);
/*     */   }
/*     */ 
/*     */   
/*     */   public void update() {}
/*     */ 
/*     */   
/*     */   public boolean a(EntityHuman entityhuman) {
/* 101 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void clear() {
/* 106 */     this.items.clear();
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(@Nullable IRecipe<?> irecipe) {
/* 111 */     this.b = irecipe;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public IRecipe<?> ak_() {
/* 117 */     return this.b;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\InventoryCraftResult.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
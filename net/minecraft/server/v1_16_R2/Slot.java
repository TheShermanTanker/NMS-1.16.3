/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Slot
/*     */ {
/*     */   public final int index;
/*     */   public final IInventory inventory;
/*     */   public int rawSlotIndex;
/*     */   public final int e;
/*     */   public final int f;
/*     */   
/*     */   public Slot(IInventory var0, int var1, int var2, int var3) {
/*  20 */     this.inventory = var0;
/*  21 */     this.index = var1;
/*  22 */     this.e = var2;
/*  23 */     this.f = var3;
/*     */   }
/*     */   
/*     */   public void a(ItemStack var0, ItemStack var1) {
/*  27 */     int var2 = var1.getCount() - var0.getCount();
/*  28 */     if (var2 > 0) {
/*  29 */       a(var1, var2);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void a(ItemStack var0, int var1) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void b(int var0) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void c(ItemStack var0) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack a(EntityHuman var0, ItemStack var1) {
/*  65 */     d();
/*  66 */     return var1;
/*     */   }
/*     */   
/*     */   public boolean isAllowed(ItemStack var0) {
/*  70 */     return true;
/*     */   }
/*     */   
/*     */   public ItemStack getItem() {
/*  74 */     return this.inventory.getItem(this.index);
/*     */   }
/*     */   
/*     */   public boolean hasItem() {
/*  78 */     return !getItem().isEmpty();
/*     */   }
/*     */   
/*     */   public void set(ItemStack var0) {
/*  82 */     this.inventory.setItem(this.index, var0);
/*  83 */     d();
/*     */   }
/*     */   
/*     */   public void d() {
/*  87 */     this.inventory.update();
/*     */   }
/*     */   
/*     */   public int getMaxStackSize() {
/*  91 */     return this.inventory.getMaxStackSize();
/*     */   }
/*     */   
/*     */   public int getMaxStackSize(ItemStack var0) {
/*  95 */     return getMaxStackSize();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack a(int var0) {
/* 104 */     return this.inventory.splitStack(this.index, var0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAllowed(EntityHuman var0) {
/* 112 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\Slot.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
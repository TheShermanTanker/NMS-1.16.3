/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.entity.CraftHumanEntity;
/*     */ import org.bukkit.entity.HumanEntity;
/*     */ 
/*     */ 
/*     */ public class TileEntityDispenser
/*     */   extends TileEntityLootable
/*     */ {
/*  13 */   private static final Random a = new Random();
/*     */   
/*     */   private NonNullList<ItemStack> items;
/*     */   
/*  17 */   public List<HumanEntity> transaction = new ArrayList<>();
/*  18 */   private int maxStack = 64;
/*     */   
/*     */   public List<ItemStack> getContents() {
/*  21 */     return this.items;
/*     */   }
/*     */   
/*     */   public void onOpen(CraftHumanEntity who) {
/*  25 */     this.transaction.add(who);
/*     */   }
/*     */   
/*     */   public void onClose(CraftHumanEntity who) {
/*  29 */     this.transaction.remove(who);
/*     */   }
/*     */   
/*     */   public List<HumanEntity> getViewers() {
/*  33 */     return this.transaction;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMaxStackSize() {
/*  38 */     return this.maxStack;
/*     */   }
/*     */   
/*     */   public void setMaxStackSize(int size) {
/*  42 */     this.maxStack = size;
/*     */   }
/*     */ 
/*     */   
/*     */   protected TileEntityDispenser(TileEntityTypes<?> tileentitytypes) {
/*  47 */     super(tileentitytypes);
/*  48 */     this.items = NonNullList.a(9, ItemStack.b);
/*     */   }
/*     */   
/*     */   public TileEntityDispenser() {
/*  52 */     this(TileEntityTypes.DISPENSER);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getSize() {
/*  57 */     return 9;
/*     */   }
/*     */   
/*     */   public int h() {
/*  61 */     d((EntityHuman)null);
/*  62 */     int i = -1;
/*  63 */     int j = 1;
/*     */     
/*  65 */     for (int k = 0; k < this.items.size(); k++) {
/*  66 */       if (!((ItemStack)this.items.get(k)).isEmpty() && a.nextInt(j++) == 0) {
/*  67 */         i = k;
/*     */       }
/*     */     } 
/*     */     
/*  71 */     return i;
/*     */   }
/*     */   
/*     */   public int addItem(ItemStack itemstack) {
/*  75 */     for (int i = 0; i < this.items.size(); i++) {
/*  76 */       if (((ItemStack)this.items.get(i)).isEmpty()) {
/*  77 */         setItem(i, itemstack);
/*  78 */         return i;
/*     */       } 
/*     */     } 
/*     */     
/*  82 */     return -1;
/*     */   }
/*     */ 
/*     */   
/*     */   protected IChatBaseComponent getContainerName() {
/*  87 */     return new ChatMessage("container.dispenser");
/*     */   }
/*     */ 
/*     */   
/*     */   public void load(IBlockData iblockdata, NBTTagCompound nbttagcompound) {
/*  92 */     super.load(iblockdata, nbttagcompound);
/*  93 */     this.items = NonNullList.a(getSize(), ItemStack.b);
/*  94 */     if (!b(nbttagcompound)) {
/*  95 */       ContainerUtil.b(nbttagcompound, this.items);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public NBTTagCompound save(NBTTagCompound nbttagcompound) {
/* 102 */     super.save(nbttagcompound);
/* 103 */     if (!c(nbttagcompound)) {
/* 104 */       ContainerUtil.a(nbttagcompound, this.items);
/*     */     }
/*     */     
/* 107 */     return nbttagcompound;
/*     */   }
/*     */ 
/*     */   
/*     */   protected NonNullList<ItemStack> f() {
/* 112 */     return this.items;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(NonNullList<ItemStack> nonnulllist) {
/* 117 */     this.items = nonnulllist;
/*     */   }
/*     */ 
/*     */   
/*     */   protected Container createContainer(int i, PlayerInventory playerinventory) {
/* 122 */     return new ContainerDispenser(i, playerinventory, this);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\TileEntityDispenser.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
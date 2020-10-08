/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftInventoryFurnace;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftInventoryView;
/*     */ import org.bukkit.entity.HumanEntity;
/*     */ import org.bukkit.inventory.Inventory;
/*     */ import org.bukkit.inventory.InventoryView;
/*     */ 
/*     */ public abstract class ContainerFurnace
/*     */   extends ContainerRecipeBook<IInventory>
/*     */ {
/*     */   private final IInventory furnace;
/*     */   private final IContainerProperties e;
/*     */   protected final World c;
/*     */   private final Recipes<? extends RecipeCooking> f;
/*     */   private final RecipeBookType g;
/*  17 */   private CraftInventoryView bukkitEntity = null;
/*     */   
/*     */   private PlayerInventory player;
/*     */   
/*     */   public CraftInventoryView getBukkitView() {
/*  22 */     if (this.bukkitEntity != null) {
/*  23 */       return this.bukkitEntity;
/*     */     }
/*     */     
/*  26 */     CraftInventoryFurnace inventory = new CraftInventoryFurnace((TileEntityFurnace)this.furnace);
/*  27 */     this.bukkitEntity = new CraftInventoryView((HumanEntity)this.player.player.getBukkitEntity(), (Inventory)inventory, this);
/*  28 */     return this.bukkitEntity;
/*     */   }
/*     */ 
/*     */   
/*     */   protected ContainerFurnace(Containers<?> containers, Recipes<? extends RecipeCooking> recipes, RecipeBookType recipebooktype, int i, PlayerInventory playerinventory) {
/*  33 */     this(containers, recipes, recipebooktype, i, playerinventory, new InventorySubcontainer(3), new ContainerProperties(4));
/*     */   }
/*     */   
/*     */   protected ContainerFurnace(Containers<?> containers, Recipes<? extends RecipeCooking> recipes, RecipeBookType recipebooktype, int i, PlayerInventory playerinventory, IInventory iinventory, IContainerProperties icontainerproperties) {
/*  37 */     super(containers, i);
/*  38 */     this.f = recipes;
/*  39 */     this.g = recipebooktype;
/*  40 */     a(iinventory, 3);
/*  41 */     a(icontainerproperties, 4);
/*  42 */     this.furnace = iinventory;
/*  43 */     this.e = icontainerproperties;
/*  44 */     this.c = playerinventory.player.world;
/*  45 */     a(new Slot(iinventory, 0, 56, 17));
/*  46 */     a(new SlotFurnaceFuel(this, iinventory, 1, 56, 53));
/*  47 */     a(new SlotFurnaceResult(playerinventory.player, iinventory, 2, 116, 35));
/*  48 */     this.player = playerinventory;
/*     */     
/*     */     int j;
/*     */     
/*  52 */     for (j = 0; j < 3; j++) {
/*  53 */       for (int k = 0; k < 9; k++) {
/*  54 */         a(new Slot(playerinventory, k + j * 9 + 9, 8 + k * 18, 84 + j * 18));
/*     */       }
/*     */     } 
/*     */     
/*  58 */     for (j = 0; j < 9; j++) {
/*  59 */       a(new Slot(playerinventory, j, 8 + j * 18, 142));
/*     */     }
/*     */     
/*  62 */     a(icontainerproperties);
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(AutoRecipeStackManager autorecipestackmanager) {
/*  67 */     if (this.furnace instanceof AutoRecipeOutput) {
/*  68 */       ((AutoRecipeOutput)this.furnace).a(autorecipestackmanager);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void e() {
/*  75 */     this.furnace.clear();
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(boolean flag, IRecipe<?> irecipe, EntityPlayer entityplayer) {
/*  80 */     (new AutoRecipeFurnace(this)).a(entityplayer, irecipe, flag);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(IRecipe<? super IInventory> irecipe) {
/*  85 */     return irecipe.a(this.furnace, this.c);
/*     */   }
/*     */ 
/*     */   
/*     */   public int f() {
/*  90 */     return 2;
/*     */   }
/*     */ 
/*     */   
/*     */   public int g() {
/*  95 */     return 1;
/*     */   }
/*     */ 
/*     */   
/*     */   public int h() {
/* 100 */     return 1;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canUse(EntityHuman entityhuman) {
/* 105 */     if (!this.checkReachable) return true; 
/* 106 */     return this.furnace.a(entityhuman);
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack shiftClick(EntityHuman entityhuman, int i) {
/* 111 */     ItemStack itemstack = ItemStack.b;
/* 112 */     Slot slot = this.slots.get(i);
/*     */     
/* 114 */     if (slot != null && slot.hasItem()) {
/* 115 */       ItemStack itemstack1 = slot.getItem();
/*     */       
/* 117 */       itemstack = itemstack1.cloneItemStack();
/* 118 */       if (i == 2) {
/* 119 */         if (!a(itemstack1, 3, 39, true)) {
/* 120 */           return ItemStack.b;
/*     */         }
/*     */         
/* 123 */         slot.a(itemstack1, itemstack);
/* 124 */       } else if (i != 1 && i != 0) {
/* 125 */         if (a(itemstack1)) {
/* 126 */           if (!a(itemstack1, 0, 1, false)) {
/* 127 */             return ItemStack.b;
/*     */           }
/* 129 */         } else if (b(itemstack1)) {
/* 130 */           if (!a(itemstack1, 1, 2, false)) {
/* 131 */             return ItemStack.b;
/*     */           }
/* 133 */         } else if (i >= 3 && i < 30) {
/* 134 */           if (!a(itemstack1, 30, 39, false)) {
/* 135 */             return ItemStack.b;
/*     */           }
/* 137 */         } else if (i >= 30 && i < 39 && !a(itemstack1, 3, 30, false)) {
/* 138 */           return ItemStack.b;
/*     */         } 
/* 140 */       } else if (!a(itemstack1, 3, 39, false)) {
/* 141 */         return ItemStack.b;
/*     */       } 
/*     */       
/* 144 */       if (itemstack1.isEmpty()) {
/* 145 */         slot.set(ItemStack.b);
/*     */       } else {
/* 147 */         slot.d();
/*     */       } 
/*     */       
/* 150 */       if (itemstack1.getCount() == itemstack.getCount()) {
/* 151 */         return ItemStack.b;
/*     */       }
/*     */       
/* 154 */       slot.a(entityhuman, itemstack1);
/*     */     } 
/*     */     
/* 157 */     return itemstack;
/*     */   }
/*     */   
/*     */   protected boolean a(ItemStack itemstack) {
/* 161 */     return this.c.getCraftingManager().<InventorySubcontainer, RecipeCooking>craft(this.f, new InventorySubcontainer(new ItemStack[] { itemstack }, ), this.c).isPresent();
/*     */   }
/*     */   
/*     */   protected boolean b(ItemStack itemstack) {
/* 165 */     return TileEntityFurnace.isFuel(itemstack);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ContainerFurnace.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.util.Optional;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.event.CraftEventFactory;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftInventoryCrafting;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftInventoryView;
/*     */ import org.bukkit.entity.HumanEntity;
/*     */ import org.bukkit.inventory.Inventory;
/*     */ import org.bukkit.inventory.InventoryView;
/*     */ 
/*     */ public class ContainerWorkbench extends ContainerRecipeBook<InventoryCrafting> {
/*     */   private final InventoryCrafting craftInventory;
/*     */   private final InventoryCraftResult resultInventory;
/*     */   public final ContainerAccess containerAccess;
/*     */   private final EntityHuman f;
/*  16 */   private CraftInventoryView bukkitEntity = null;
/*     */   
/*     */   private PlayerInventory player;
/*     */   
/*     */   public ContainerWorkbench(int i, PlayerInventory playerinventory) {
/*  21 */     this(i, playerinventory, ContainerAccess.a);
/*     */   }
/*     */   
/*     */   public ContainerWorkbench(int i, PlayerInventory playerinventory, ContainerAccess containeraccess) {
/*  25 */     super(Containers.CRAFTING, i);
/*     */     
/*  27 */     this.resultInventory = new InventoryCraftResult();
/*  28 */     this.craftInventory = new InventoryCrafting(this, 3, 3, playerinventory.player);
/*  29 */     this.craftInventory.resultInventory = this.resultInventory;
/*  30 */     this.player = playerinventory;
/*     */     
/*  32 */     this.containerAccess = containeraccess;
/*  33 */     this.f = playerinventory.player;
/*  34 */     a(new SlotResult(playerinventory.player, this.craftInventory, this.resultInventory, 0, 124, 35));
/*     */ 
/*     */     
/*     */     int j;
/*     */     
/*  39 */     for (j = 0; j < 3; j++) {
/*  40 */       for (int k = 0; k < 3; k++) {
/*  41 */         a(new Slot(this.craftInventory, k + j * 3, 30 + k * 18, 17 + j * 18));
/*     */       }
/*     */     } 
/*     */     
/*  45 */     for (j = 0; j < 3; j++) {
/*  46 */       for (int k = 0; k < 9; k++) {
/*  47 */         a(new Slot(playerinventory, k + j * 9 + 9, 8 + k * 18, 84 + j * 18));
/*     */       }
/*     */     } 
/*     */     
/*  51 */     for (j = 0; j < 9; j++) {
/*  52 */       a(new Slot(playerinventory, j, 8 + j * 18, 142));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected static void a(int i, World world, EntityHuman entityhuman, InventoryCrafting inventorycrafting, InventoryCraftResult inventorycraftresult, Container container) {
/*  58 */     if (!world.isClientSide) {
/*  59 */       EntityPlayer entityplayer = (EntityPlayer)entityhuman;
/*  60 */       ItemStack itemstack = ItemStack.b;
/*  61 */       Optional<RecipeCrafting> optional = world.getMinecraftServer().getCraftingManager().craft(Recipes.CRAFTING, inventorycrafting, world);
/*     */       
/*  63 */       if (optional.isPresent()) {
/*  64 */         RecipeCrafting recipecrafting = optional.get();
/*     */         
/*  66 */         if (inventorycraftresult.a(world, entityplayer, recipecrafting)) {
/*  67 */           itemstack = recipecrafting.a(inventorycrafting);
/*     */         }
/*     */       } 
/*  70 */       itemstack = CraftEventFactory.callPreCraftEvent(inventorycrafting, inventorycraftresult, itemstack, container.getBukkitView(), false);
/*     */       
/*  72 */       inventorycraftresult.setItem(0, itemstack);
/*  73 */       entityplayer.playerConnection.sendPacket(new PacketPlayOutSetSlot(i, 0, itemstack));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(IInventory iinventory) {
/*  79 */     this.containerAccess.a((world, blockposition) -> a(this.windowId, world, this.f, this.craftInventory, this.resultInventory, this));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(AutoRecipeStackManager autorecipestackmanager) {
/*  86 */     this.craftInventory.a(autorecipestackmanager);
/*     */   }
/*     */ 
/*     */   
/*     */   public void e() {
/*  91 */     this.craftInventory.clear();
/*  92 */     this.resultInventory.clear();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(IRecipe<? super InventoryCrafting> irecipe) {
/*  97 */     return irecipe.a(this.craftInventory, this.f.world);
/*     */   }
/*     */ 
/*     */   
/*     */   public void b(EntityHuman entityhuman) {
/* 102 */     super.b(entityhuman);
/* 103 */     this.containerAccess.a((world, blockposition) -> a(entityhuman, world, this.craftInventory));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canUse(EntityHuman entityhuman) {
/* 110 */     if (!this.checkReachable) return true; 
/* 111 */     return a(this.containerAccess, entityhuman, Blocks.CRAFTING_TABLE);
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack shiftClick(EntityHuman entityhuman, int i) {
/* 116 */     ItemStack itemstack = ItemStack.b;
/* 117 */     Slot slot = this.slots.get(i);
/*     */     
/* 119 */     if (slot != null && slot.hasItem()) {
/* 120 */       ItemStack itemstack1 = slot.getItem();
/*     */       
/* 122 */       itemstack = itemstack1.cloneItemStack();
/* 123 */       if (i == 0) {
/* 124 */         this.containerAccess.a((world, blockposition) -> itemstack1.getItem().b(itemstack1, world, entityhuman));
/*     */ 
/*     */         
/* 127 */         if (!a(itemstack1, 10, 46, true)) {
/* 128 */           return ItemStack.b;
/*     */         }
/*     */         
/* 131 */         slot.a(itemstack1, itemstack);
/* 132 */       } else if (i >= 10 && i < 46) {
/* 133 */         if (!a(itemstack1, 1, 10, false)) {
/* 134 */           if (i < 37) {
/* 135 */             if (!a(itemstack1, 37, 46, false)) {
/* 136 */               return ItemStack.b;
/*     */             }
/* 138 */           } else if (!a(itemstack1, 10, 37, false)) {
/* 139 */             return ItemStack.b;
/*     */           } 
/*     */         }
/* 142 */       } else if (!a(itemstack1, 10, 46, false)) {
/* 143 */         return ItemStack.b;
/*     */       } 
/*     */       
/* 146 */       if (itemstack1.isEmpty()) {
/* 147 */         slot.set(ItemStack.b);
/*     */       } else {
/* 149 */         slot.d();
/*     */       } 
/*     */       
/* 152 */       if (itemstack1.getCount() == itemstack.getCount()) {
/* 153 */         return ItemStack.b;
/*     */       }
/*     */       
/* 156 */       ItemStack itemstack2 = slot.a(entityhuman, itemstack1);
/*     */       
/* 158 */       if (i == 0) {
/* 159 */         entityhuman.drop(itemstack2, false);
/*     */       }
/*     */     } 
/*     */     
/* 163 */     return itemstack;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(ItemStack itemstack, Slot slot) {
/* 168 */     return (slot.inventory != this.resultInventory && super.a(itemstack, slot));
/*     */   }
/*     */ 
/*     */   
/*     */   public int f() {
/* 173 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public int g() {
/* 178 */     return this.craftInventory.g();
/*     */   }
/*     */ 
/*     */   
/*     */   public int h() {
/* 183 */     return this.craftInventory.f();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public CraftInventoryView getBukkitView() {
/* 189 */     if (this.bukkitEntity != null) {
/* 190 */       return this.bukkitEntity;
/*     */     }
/*     */     
/* 193 */     CraftInventoryCrafting inventory = new CraftInventoryCrafting(this.craftInventory, this.resultInventory);
/* 194 */     this.bukkitEntity = new CraftInventoryView((HumanEntity)this.player.player.getBukkitEntity(), (Inventory)inventory, this);
/* 195 */     return this.bukkitEntity;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ContainerWorkbench.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
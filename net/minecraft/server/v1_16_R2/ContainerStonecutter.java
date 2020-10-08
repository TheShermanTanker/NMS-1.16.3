/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.List;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.event.CraftEventFactory;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftInventoryStonecutter;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftInventoryView;
/*     */ import org.bukkit.entity.HumanEntity;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.inventory.Inventory;
/*     */ import org.bukkit.inventory.InventoryView;
/*     */ 
/*     */ public class ContainerStonecutter extends Container {
/*     */   private final ContainerAccess containerAccess;
/*     */   private final ContainerProperty containerProperty;
/*     */   private final World world;
/*     */   private List<RecipeStonecutting> i;
/*     */   private ItemStack j;
/*     */   private long k;
/*     */   final Slot c;
/*     */   final Slot d;
/*     */   private Runnable l;
/*     */   public final IInventory inventory;
/*     */   private final InventoryCraftResult resultInventory;
/*  26 */   private CraftInventoryView bukkitEntity = null;
/*     */   
/*     */   private Player player;
/*     */   
/*     */   public CraftInventoryView getBukkitView() {
/*  31 */     if (this.bukkitEntity != null) {
/*  32 */       return this.bukkitEntity;
/*     */     }
/*     */     
/*  35 */     CraftInventoryStonecutter inventory = new CraftInventoryStonecutter(this.inventory, this.resultInventory);
/*  36 */     this.bukkitEntity = new CraftInventoryView((HumanEntity)this.player, (Inventory)inventory, this);
/*  37 */     return this.bukkitEntity;
/*     */   }
/*     */ 
/*     */   
/*     */   public ContainerStonecutter(int i, PlayerInventory playerinventory) {
/*  42 */     this(i, playerinventory, ContainerAccess.a);
/*     */   }
/*     */   
/*     */   public ContainerStonecutter(int i, PlayerInventory playerinventory, final ContainerAccess containeraccess) {
/*  46 */     super(Containers.STONECUTTER, i);
/*  47 */     this.containerProperty = ContainerProperty.a();
/*  48 */     this.i = Lists.newArrayList();
/*  49 */     this.j = ItemStack.b;
/*  50 */     this.l = (() -> {
/*     */       
/*  52 */       }); this.inventory = new InventorySubcontainer(1)
/*     */       {
/*     */         public void update() {
/*  55 */           super.update();
/*  56 */           ContainerStonecutter.this.a(this);
/*  57 */           ContainerStonecutter.this.l.run();
/*     */         }
/*     */ 
/*     */ 
/*     */         
/*     */         public Location getLocation() {
/*  63 */           return containeraccess.getLocation();
/*     */         }
/*     */       };
/*     */     
/*  67 */     this.resultInventory = new InventoryCraftResult();
/*  68 */     this.containerAccess = containeraccess;
/*  69 */     this.world = playerinventory.player.world;
/*  70 */     this.c = a(new Slot(this.inventory, 0, 20, 33));
/*  71 */     this.d = a(new Slot(this.resultInventory, 1, 143, 33)
/*     */         {
/*     */           public boolean isAllowed(ItemStack itemstack) {
/*  74 */             return false;
/*     */           }
/*     */ 
/*     */           
/*     */           public ItemStack a(EntityHuman entityhuman, ItemStack itemstack) {
/*  79 */             itemstack.a(entityhuman.world, entityhuman, itemstack.getCount());
/*  80 */             ContainerStonecutter.this.resultInventory.b(entityhuman);
/*  81 */             ItemStack itemstack1 = ContainerStonecutter.this.c.a(1);
/*     */             
/*  83 */             if (!itemstack1.isEmpty()) {
/*  84 */               ContainerStonecutter.this.i();
/*     */             }
/*     */             
/*  87 */             containeraccess.a((world, blockposition) -> {
/*     */                   long j = world.getTime();
/*     */                   
/*     */                   if (ContainerStonecutter.this.k != j) {
/*     */                     world.playSound((EntityHuman)null, blockposition, SoundEffects.UI_STONECUTTER_TAKE_RESULT, SoundCategory.BLOCKS, 1.0F, 1.0F);
/*     */                     
/*     */                     ContainerStonecutter.this.k = j;
/*     */                   } 
/*     */                 });
/*  96 */             return super.a(entityhuman, itemstack);
/*     */           }
/*     */         });
/*     */     
/*     */     int j;
/*     */     
/* 102 */     for (j = 0; j < 3; j++) {
/* 103 */       for (int k = 0; k < 9; k++) {
/* 104 */         a(new Slot(playerinventory, k + j * 9 + 9, 8 + k * 18, 84 + j * 18));
/*     */       }
/*     */     } 
/*     */     
/* 108 */     for (j = 0; j < 9; j++) {
/* 109 */       a(new Slot(playerinventory, j, 8 + j * 18, 142));
/*     */     }
/*     */     
/* 112 */     a(this.containerProperty);
/* 113 */     this.player = (Player)playerinventory.player.getBukkitEntity();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canUse(EntityHuman entityhuman) {
/* 118 */     if (!this.checkReachable) return true; 
/* 119 */     return a(this.containerAccess, entityhuman, Blocks.STONECUTTER);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(EntityHuman entityhuman, int i) {
/* 124 */     if (d(i)) {
/* 125 */       this.containerProperty.set(i);
/* 126 */       i();
/*     */     } 
/*     */     
/* 129 */     return true;
/*     */   }
/*     */   
/*     */   private boolean d(int i) {
/* 133 */     return (i >= 0 && i < this.i.size());
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(IInventory iinventory) {
/* 138 */     ItemStack itemstack = this.c.getItem();
/*     */     
/* 140 */     if (itemstack.getItem() != this.j.getItem()) {
/* 141 */       this.j = itemstack.cloneItemStack();
/* 142 */       a(iinventory, itemstack);
/*     */     } 
/*     */     
/* 145 */     CraftEventFactory.callPrepareResultEvent(this, 1);
/*     */   }
/*     */   
/*     */   private void a(IInventory iinventory, ItemStack itemstack) {
/* 149 */     this.i.clear();
/* 150 */     this.containerProperty.set(-1);
/* 151 */     this.d.set(ItemStack.b);
/* 152 */     if (!itemstack.isEmpty()) {
/* 153 */       this.i = this.world.getCraftingManager().b(Recipes.STONECUTTING, iinventory, this.world);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private void i() {
/* 159 */     if (!this.i.isEmpty() && d(this.containerProperty.get())) {
/* 160 */       RecipeStonecutting recipestonecutting = this.i.get(this.containerProperty.get());
/*     */       
/* 162 */       this.resultInventory.a(recipestonecutting);
/* 163 */       this.d.set(recipestonecutting.a(this.inventory));
/*     */     } else {
/* 165 */       this.d.set(ItemStack.b);
/*     */     } 
/*     */     
/* 168 */     c();
/*     */   }
/*     */ 
/*     */   
/*     */   public Containers<?> getType() {
/* 173 */     return Containers.STONECUTTER;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(ItemStack itemstack, Slot slot) {
/* 178 */     return (slot.inventory != this.resultInventory && super.a(itemstack, slot));
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack shiftClick(EntityHuman entityhuman, int i) {
/* 183 */     ItemStack itemstack = ItemStack.b;
/* 184 */     Slot slot = this.slots.get(i);
/*     */     
/* 186 */     if (slot != null && slot.hasItem()) {
/* 187 */       ItemStack itemstack1 = slot.getItem();
/* 188 */       Item item = itemstack1.getItem();
/*     */       
/* 190 */       itemstack = itemstack1.cloneItemStack();
/* 191 */       if (i == 1) {
/* 192 */         item.b(itemstack1, entityhuman.world, entityhuman);
/* 193 */         if (!a(itemstack1, 2, 38, true)) {
/* 194 */           return ItemStack.b;
/*     */         }
/*     */         
/* 197 */         slot.a(itemstack1, itemstack);
/* 198 */       } else if (i == 0) {
/* 199 */         if (!a(itemstack1, 2, 38, false)) {
/* 200 */           return ItemStack.b;
/*     */         }
/* 202 */       } else if (this.world.getCraftingManager().<InventorySubcontainer, RecipeStonecutting>craft(Recipes.STONECUTTING, new InventorySubcontainer(new ItemStack[] { itemstack1 }, ), this.world).isPresent()) {
/* 203 */         if (!a(itemstack1, 0, 1, false)) {
/* 204 */           return ItemStack.b;
/*     */         }
/* 206 */       } else if (i >= 2 && i < 29) {
/* 207 */         if (!a(itemstack1, 29, 38, false)) {
/* 208 */           return ItemStack.b;
/*     */         }
/* 210 */       } else if (i >= 29 && i < 38 && !a(itemstack1, 2, 29, false)) {
/* 211 */         return ItemStack.b;
/*     */       } 
/*     */       
/* 214 */       if (itemstack1.isEmpty()) {
/* 215 */         slot.set(ItemStack.b);
/*     */       }
/*     */       
/* 218 */       slot.d();
/* 219 */       if (itemstack1.getCount() == itemstack.getCount()) {
/* 220 */         return ItemStack.b;
/*     */       }
/*     */       
/* 223 */       slot.a(entityhuman, itemstack1);
/* 224 */       c();
/*     */     } 
/*     */     
/* 227 */     return itemstack;
/*     */   }
/*     */ 
/*     */   
/*     */   public void b(EntityHuman entityhuman) {
/* 232 */     super.b(entityhuman);
/* 233 */     this.resultInventory.splitWithoutUpdate(1);
/* 234 */     this.containerAccess.a((world, blockposition) -> a(entityhuman, entityhuman.world, this.inventory));
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ContainerStonecutter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
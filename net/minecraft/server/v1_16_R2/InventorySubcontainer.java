/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.stream.Collectors;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.entity.CraftHumanEntity;
/*     */ import org.bukkit.entity.HumanEntity;
/*     */ import org.bukkit.inventory.InventoryHolder;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class InventorySubcontainer
/*     */   implements IInventory, AutoRecipeOutput
/*     */ {
/*     */   private final int a;
/*     */   public final NonNullList<ItemStack> items;
/*     */   private List<IInventoryListener> c;
/*  21 */   public List<HumanEntity> transaction = new ArrayList<>();
/*  22 */   private int maxStack = 64;
/*     */   protected InventoryHolder bukkitOwner;
/*     */   
/*     */   public List<ItemStack> getContents() {
/*  26 */     return this.items;
/*     */   }
/*     */   
/*     */   public void onOpen(CraftHumanEntity who) {
/*  30 */     this.transaction.add(who);
/*     */   }
/*     */   
/*     */   public void onClose(CraftHumanEntity who) {
/*  34 */     this.transaction.remove(who);
/*     */   }
/*     */   
/*     */   public List<HumanEntity> getViewers() {
/*  38 */     return this.transaction;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMaxStackSize() {
/*  43 */     return this.maxStack;
/*     */   }
/*     */   
/*     */   public void setMaxStackSize(int i) {
/*  47 */     this.maxStack = i;
/*     */   }
/*     */   
/*     */   public InventoryHolder getOwner() {
/*  51 */     return this.bukkitOwner;
/*     */   }
/*     */ 
/*     */   
/*     */   public Location getLocation() {
/*  56 */     return null;
/*     */   }
/*     */   
/*     */   public InventorySubcontainer(int i) {
/*  60 */     this(i, null);
/*     */   }
/*     */   
/*     */   public InventorySubcontainer(int i, InventoryHolder owner) {
/*  64 */     this.bukkitOwner = owner;
/*     */     
/*  66 */     this.a = i;
/*  67 */     this.items = NonNullList.a(i, ItemStack.b);
/*     */   }
/*     */   
/*     */   public InventorySubcontainer(ItemStack... aitemstack) {
/*  71 */     this.a = aitemstack.length;
/*  72 */     this.items = NonNullList.a(ItemStack.b, aitemstack);
/*     */   }
/*     */   
/*     */   public void a(IInventoryListener iinventorylistener) {
/*  76 */     if (this.c == null) {
/*  77 */       this.c = Lists.newArrayList();
/*     */     }
/*     */     
/*  80 */     this.c.add(iinventorylistener);
/*     */   }
/*     */   
/*     */   public void b(IInventoryListener iinventorylistener) {
/*  84 */     this.c.remove(iinventorylistener);
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack getItem(int i) {
/*  89 */     return (i >= 0 && i < this.items.size()) ? this.items.get(i) : ItemStack.b;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public List<ItemStack> f() {
/*  95 */     List<ItemStack> list = (List<ItemStack>)this.items.stream().filter(itemstack -> !itemstack.isEmpty()).collect(Collectors.toList());
/*     */     
/*  97 */     clear();
/*  98 */     return list;
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack splitStack(int i, int j) {
/* 103 */     ItemStack itemstack = ContainerUtil.a(this.items, i, j);
/*     */     
/* 105 */     if (!itemstack.isEmpty()) {
/* 106 */       update();
/*     */     }
/*     */     
/* 109 */     return itemstack;
/*     */   }
/*     */   
/*     */   public ItemStack a(Item item, int i) {
/* 113 */     ItemStack itemstack = new ItemStack(item, 0);
/*     */     
/* 115 */     for (int j = this.a - 1; j >= 0; j--) {
/* 116 */       ItemStack itemstack1 = getItem(j);
/*     */       
/* 118 */       if (itemstack1.getItem().equals(item)) {
/* 119 */         int k = i - itemstack.getCount();
/* 120 */         ItemStack itemstack2 = itemstack1.cloneAndSubtract(k);
/*     */         
/* 122 */         itemstack.add(itemstack2.getCount());
/* 123 */         if (itemstack.getCount() == i) {
/*     */           break;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 129 */     if (!itemstack.isEmpty()) {
/* 130 */       update();
/*     */     }
/*     */     
/* 133 */     return itemstack;
/*     */   }
/*     */   
/*     */   public ItemStack a(ItemStack itemstack) {
/* 137 */     ItemStack itemstack1 = itemstack.cloneItemStack();
/*     */     
/* 139 */     d(itemstack1);
/* 140 */     if (itemstack1.isEmpty()) {
/* 141 */       return ItemStack.b;
/*     */     }
/* 143 */     c(itemstack1);
/* 144 */     return itemstack1.isEmpty() ? ItemStack.b : itemstack1;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean b(ItemStack itemstack) {
/* 149 */     boolean flag = false;
/* 150 */     Iterator<ItemStack> iterator = this.items.iterator();
/*     */     
/* 152 */     while (iterator.hasNext()) {
/* 153 */       ItemStack itemstack1 = iterator.next();
/*     */       
/* 155 */       if (itemstack1.isEmpty() || (a(itemstack1, itemstack) && itemstack1.getCount() < itemstack1.getMaxStackSize())) {
/* 156 */         flag = true;
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/* 161 */     return flag;
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack splitWithoutUpdate(int i) {
/* 166 */     ItemStack itemstack = this.items.get(i);
/*     */     
/* 168 */     if (itemstack.isEmpty()) {
/* 169 */       return ItemStack.b;
/*     */     }
/* 171 */     this.items.set(i, ItemStack.b);
/* 172 */     return itemstack;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setItem(int i, ItemStack itemstack) {
/* 178 */     this.items.set(i, itemstack);
/* 179 */     if (!itemstack.isEmpty() && itemstack.getCount() > getMaxStackSize()) {
/* 180 */       itemstack.setCount(getMaxStackSize());
/*     */     }
/*     */     
/* 183 */     update();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getSize() {
/* 188 */     return this.a;
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/*     */     ItemStack itemstack;
/* 193 */     Iterator<ItemStack> iterator = this.items.iterator();
/*     */ 
/*     */ 
/*     */     
/*     */     do {
/* 198 */       if (!iterator.hasNext()) {
/* 199 */         return true;
/*     */       }
/*     */       
/* 202 */       itemstack = iterator.next();
/* 203 */     } while (itemstack.isEmpty());
/*     */     
/* 205 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void update() {
/* 210 */     if (this.c != null) {
/* 211 */       Iterator<IInventoryListener> iterator = this.c.iterator();
/*     */       
/* 213 */       while (iterator.hasNext()) {
/* 214 */         IInventoryListener iinventorylistener = iterator.next();
/*     */         
/* 216 */         iinventorylistener.a(this);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean a(EntityHuman entityhuman) {
/* 224 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void clear() {
/* 229 */     this.items.clear();
/* 230 */     update();
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(AutoRecipeStackManager autorecipestackmanager) {
/* 235 */     Iterator<ItemStack> iterator = this.items.iterator();
/*     */     
/* 237 */     while (iterator.hasNext()) {
/* 238 */       ItemStack itemstack = iterator.next();
/*     */       
/* 240 */       autorecipestackmanager.b(itemstack);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 246 */     return ((List)this.items.stream().filter(itemstack -> !itemstack.isEmpty())
/*     */       
/* 248 */       .collect(Collectors.toList())).toString();
/*     */   }
/*     */   
/*     */   private void c(ItemStack itemstack) {
/* 252 */     for (int i = 0; i < this.a; i++) {
/* 253 */       ItemStack itemstack1 = getItem(i);
/*     */       
/* 255 */       if (itemstack1.isEmpty()) {
/* 256 */         setItem(i, itemstack.cloneItemStack());
/* 257 */         itemstack.setCount(0);
/*     */         return;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void d(ItemStack itemstack) {
/* 265 */     for (int i = 0; i < this.a; i++) {
/* 266 */       ItemStack itemstack1 = getItem(i);
/*     */       
/* 268 */       if (a(itemstack1, itemstack)) {
/* 269 */         b(itemstack, itemstack1);
/* 270 */         if (itemstack.isEmpty()) {
/*     */           return;
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean a(ItemStack itemstack, ItemStack itemstack1) {
/* 279 */     return (itemstack.getItem() == itemstack1.getItem() && ItemStack.equals(itemstack, itemstack1));
/*     */   }
/*     */   
/*     */   private void b(ItemStack itemstack, ItemStack itemstack1) {
/* 283 */     int i = Math.min(getMaxStackSize(), itemstack1.getMaxStackSize());
/* 284 */     int j = Math.min(itemstack.getCount(), i - itemstack1.getCount());
/*     */     
/* 286 */     if (j > 0) {
/* 287 */       itemstack1.add(j);
/* 288 */       itemstack.subtract(j);
/* 289 */       update();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(NBTTagList nbttaglist) {
/* 295 */     for (int i = 0; i < nbttaglist.size(); i++) {
/* 296 */       ItemStack itemstack = ItemStack.a(nbttaglist.getCompound(i));
/*     */       
/* 298 */       if (!itemstack.isEmpty()) {
/* 299 */         a(itemstack);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public NBTTagList g() {
/* 306 */     NBTTagList nbttaglist = new NBTTagList();
/*     */     
/* 308 */     for (int i = 0; i < getSize(); i++) {
/* 309 */       ItemStack itemstack = getItem(i);
/*     */       
/* 311 */       if (!itemstack.isEmpty()) {
/* 312 */         nbttaglist.add(itemstack.save(new NBTTagCompound()));
/*     */       }
/*     */     } 
/*     */     
/* 316 */     return nbttaglist;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\InventorySubcontainer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
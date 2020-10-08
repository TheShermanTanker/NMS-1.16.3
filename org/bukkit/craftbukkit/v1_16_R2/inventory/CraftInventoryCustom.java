/*     */ package org.bukkit.craftbukkit.v1_16_R2.inventory;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import net.minecraft.server.v1_16_R2.EntityHuman;
/*     */ import net.minecraft.server.v1_16_R2.IInventory;
/*     */ import net.minecraft.server.v1_16_R2.ItemStack;
/*     */ import net.minecraft.server.v1_16_R2.NonNullList;
/*     */ import org.apache.commons.lang.Validate;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.entity.CraftHumanEntity;
/*     */ import org.bukkit.entity.HumanEntity;
/*     */ import org.bukkit.event.inventory.InventoryType;
/*     */ import org.bukkit.inventory.InventoryHolder;
/*     */ 
/*     */ public class CraftInventoryCustom extends CraftInventory {
/*     */   public CraftInventoryCustom(InventoryHolder owner, InventoryType type) {
/*  19 */     super(new MinecraftInventory(owner, type));
/*     */   }
/*     */   
/*     */   public CraftInventoryCustom(InventoryHolder owner, InventoryType type, String title) {
/*  23 */     super(new MinecraftInventory(owner, type, title));
/*     */   }
/*     */   
/*     */   public CraftInventoryCustom(InventoryHolder owner, int size) {
/*  27 */     super(new MinecraftInventory(owner, size));
/*     */   }
/*     */   
/*     */   public CraftInventoryCustom(InventoryHolder owner, int size, String title) {
/*  31 */     super(new MinecraftInventory(owner, size, title));
/*     */   }
/*     */   
/*     */   static class MinecraftInventory implements IInventory {
/*     */     private final NonNullList<ItemStack> items;
/*  36 */     private int maxStack = 64;
/*     */     private final List<HumanEntity> viewers;
/*     */     private final String title;
/*     */     private InventoryType type;
/*     */     private final InventoryHolder owner;
/*     */     
/*     */     public MinecraftInventory(InventoryHolder owner, InventoryType type) {
/*  43 */       this(owner, type.getDefaultSize(), type.getDefaultTitle());
/*  44 */       this.type = type;
/*     */     }
/*     */     
/*     */     public MinecraftInventory(InventoryHolder owner, InventoryType type, String title) {
/*  48 */       this(owner, type.getDefaultSize(), title);
/*  49 */       this.type = type;
/*     */     }
/*     */     
/*     */     public MinecraftInventory(InventoryHolder owner, int size) {
/*  53 */       this(owner, size, "Chest");
/*     */     }
/*     */     
/*     */     public MinecraftInventory(InventoryHolder owner, int size, String title) {
/*  57 */       Validate.notNull(title, "Title cannot be null");
/*  58 */       this.items = NonNullList.a(size, ItemStack.b);
/*  59 */       this.title = title;
/*  60 */       this.viewers = new ArrayList<>();
/*  61 */       this.owner = owner;
/*  62 */       this.type = InventoryType.CHEST;
/*     */     }
/*     */ 
/*     */     
/*     */     public int getSize() {
/*  67 */       return this.items.size();
/*     */     }
/*     */ 
/*     */     
/*     */     public ItemStack getItem(int i) {
/*  72 */       return (ItemStack)this.items.get(i);
/*     */     }
/*     */ 
/*     */     
/*     */     public ItemStack splitStack(int i, int j) {
/*  77 */       ItemStack result, stack = getItem(i);
/*     */       
/*  79 */       if (stack == ItemStack.b) return stack; 
/*  80 */       if (stack.getCount() <= j) {
/*  81 */         setItem(i, ItemStack.b);
/*  82 */         result = stack;
/*     */       } else {
/*  84 */         result = CraftItemStack.copyNMSStack(stack, j);
/*  85 */         stack.subtract(j);
/*     */       } 
/*  87 */       update();
/*  88 */       return result;
/*     */     }
/*     */ 
/*     */     
/*     */     public ItemStack splitWithoutUpdate(int i) {
/*  93 */       ItemStack result, stack = getItem(i);
/*     */       
/*  95 */       if (stack == ItemStack.b) return stack; 
/*  96 */       if (stack.getCount() <= 1) {
/*  97 */         setItem(i, null);
/*  98 */         result = stack;
/*     */       } else {
/* 100 */         result = CraftItemStack.copyNMSStack(stack, 1);
/* 101 */         stack.subtract(1);
/*     */       } 
/* 103 */       return result;
/*     */     }
/*     */ 
/*     */     
/*     */     public void setItem(int i, ItemStack itemstack) {
/* 108 */       this.items.set(i, itemstack);
/* 109 */       if (itemstack != ItemStack.b && getMaxStackSize() > 0 && itemstack.getCount() > getMaxStackSize()) {
/* 110 */         itemstack.setCount(getMaxStackSize());
/*     */       }
/*     */     }
/*     */ 
/*     */     
/*     */     public int getMaxStackSize() {
/* 116 */       return this.maxStack;
/*     */     }
/*     */ 
/*     */     
/*     */     public void setMaxStackSize(int size) {
/* 121 */       this.maxStack = size;
/*     */     }
/*     */ 
/*     */     
/*     */     public void update() {}
/*     */ 
/*     */     
/*     */     public boolean a(EntityHuman entityhuman) {
/* 129 */       return true;
/*     */     }
/*     */ 
/*     */     
/*     */     public List<ItemStack> getContents() {
/* 134 */       return (List<ItemStack>)this.items;
/*     */     }
/*     */ 
/*     */     
/*     */     public void onOpen(CraftHumanEntity who) {
/* 139 */       this.viewers.add(who);
/*     */     }
/*     */ 
/*     */     
/*     */     public void onClose(CraftHumanEntity who) {
/* 144 */       this.viewers.remove(who);
/*     */     }
/*     */ 
/*     */     
/*     */     public List<HumanEntity> getViewers() {
/* 149 */       return this.viewers;
/*     */     }
/*     */     
/*     */     public InventoryType getType() {
/* 153 */       return this.type;
/*     */     }
/*     */ 
/*     */     
/*     */     public InventoryHolder getOwner() {
/* 158 */       return this.owner;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean b(int i, ItemStack itemstack) {
/* 163 */       return true;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void startOpen(EntityHuman entityHuman) {}
/*     */ 
/*     */ 
/*     */     
/*     */     public void closeContainer(EntityHuman entityHuman) {}
/*     */ 
/*     */ 
/*     */     
/*     */     public void clear() {
/* 178 */       this.items.clear();
/*     */     }
/*     */ 
/*     */     
/*     */     public Location getLocation() {
/* 183 */       return null;
/*     */     }
/*     */     
/*     */     public String getTitle() {
/* 187 */       return this.title;
/*     */     }
/*     */     
/*     */     public boolean isEmpty() {
/*     */       ItemStack itemstack;
/* 192 */       Iterator<ItemStack> iterator = this.items.iterator();
/*     */ 
/*     */ 
/*     */       
/*     */       do {
/* 197 */         if (!iterator.hasNext()) {
/* 198 */           return true;
/*     */         }
/*     */         
/* 201 */         itemstack = iterator.next();
/* 202 */       } while (itemstack.isEmpty());
/*     */       
/* 204 */       return false;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\inventory\CraftInventoryCustom.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
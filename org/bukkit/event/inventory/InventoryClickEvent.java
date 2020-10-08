/*     */ package org.bukkit.event.inventory;
/*     */ 
/*     */ import org.bukkit.event.HandlerList;
/*     */ import org.bukkit.inventory.Inventory;
/*     */ import org.bukkit.inventory.InventoryView;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class InventoryClickEvent
/*     */   extends InventoryInteractEvent
/*     */ {
/*  49 */   private static final HandlerList handlers = new HandlerList();
/*     */   private final ClickType click;
/*     */   private final InventoryAction action;
/*     */   private InventoryType.SlotType slot_type;
/*     */   private int whichSlot;
/*     */   private int rawSlot;
/*  55 */   private ItemStack current = null;
/*  56 */   private int hotbarKey = -1;
/*     */   
/*     */   public InventoryClickEvent(@NotNull InventoryView view, @NotNull InventoryType.SlotType type, int slot, @NotNull ClickType click, @NotNull InventoryAction action) {
/*  59 */     super(view);
/*  60 */     this.slot_type = type;
/*  61 */     this.rawSlot = slot;
/*  62 */     this.whichSlot = view.convertSlot(slot);
/*  63 */     this.click = click;
/*  64 */     this.action = action;
/*     */   }
/*     */   
/*     */   public InventoryClickEvent(@NotNull InventoryView view, @NotNull InventoryType.SlotType type, int slot, @NotNull ClickType click, @NotNull InventoryAction action, int key) {
/*  68 */     this(view, type, slot, click, action);
/*  69 */     this.hotbarKey = key;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public InventoryType.SlotType getSlotType() {
/*  79 */     return this.slot_type;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public ItemStack getCursor() {
/*  89 */     return getView().getCursor();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public ItemStack getCurrentItem() {
/*  99 */     if (this.slot_type == InventoryType.SlotType.OUTSIDE) {
/* 100 */       return this.current;
/*     */     }
/* 102 */     return getView().getItem(this.rawSlot);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isRightClick() {
/* 113 */     return this.click.isRightClick();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isLeftClick() {
/* 124 */     return this.click.isLeftClick();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isShiftClick() {
/* 135 */     return this.click.isShiftClick();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public void setCursor(@Nullable ItemStack stack) {
/* 149 */     getView().setCursor(stack);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCurrentItem(@Nullable ItemStack stack) {
/* 158 */     if (this.slot_type == InventoryType.SlotType.OUTSIDE) {
/* 159 */       this.current = stack;
/*     */     } else {
/* 161 */       getView().setItem(this.rawSlot, stack);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public Inventory getClickedInventory() {
/* 173 */     return getView().getInventory(this.rawSlot);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSlot() {
/* 184 */     return this.whichSlot;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRawSlot() {
/* 194 */     return this.rawSlot;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getHotbarButton() {
/* 205 */     return this.hotbarKey;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public InventoryAction getAction() {
/* 219 */     return this.action;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public ClickType getClick() {
/* 231 */     return this.click;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public HandlerList getHandlers() {
/* 237 */     return handlers;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public static HandlerList getHandlerList() {
/* 242 */     return handlers;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\inventory\InventoryClickEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
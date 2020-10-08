/*     */ package org.bukkit.event.inventory;
/*     */ 
/*     */ import com.google.common.collect.ImmutableSet;
/*     */ import java.util.Collections;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.lang.Validate;
/*     */ import org.bukkit.event.HandlerList;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class InventoryDragEvent
/*     */   extends InventoryInteractEvent
/*     */ {
/*  58 */   private static final HandlerList handlers = new HandlerList();
/*     */   private final DragType type;
/*     */   private final Map<Integer, ItemStack> addedItems;
/*     */   private final Set<Integer> containerSlots;
/*     */   private final ItemStack oldCursor;
/*     */   private ItemStack newCursor;
/*     */   
/*     */   public InventoryDragEvent(@NotNull InventoryView what, @Nullable ItemStack newCursor, @NotNull ItemStack oldCursor, boolean right, @NotNull Map<Integer, ItemStack> slots) {
/*  66 */     super(what);
/*     */     
/*  68 */     Validate.notNull(oldCursor);
/*  69 */     Validate.notNull(slots);
/*     */     
/*  71 */     this.type = right ? DragType.SINGLE : DragType.EVEN;
/*  72 */     this.newCursor = newCursor;
/*  73 */     this.oldCursor = oldCursor;
/*  74 */     this.addedItems = slots;
/*  75 */     ImmutableSet.Builder<Integer> b = ImmutableSet.builder();
/*  76 */     for (Integer slot : slots.keySet()) {
/*  77 */       b.add(Integer.valueOf(what.convertSlot(slot.intValue())));
/*     */     }
/*  79 */     this.containerSlots = (Set<Integer>)b.build();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Map<Integer, ItemStack> getNewItems() {
/*  89 */     return Collections.unmodifiableMap(this.addedItems);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Set<Integer> getRawSlots() {
/*  99 */     return this.addedItems.keySet();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Set<Integer> getInventorySlots() {
/* 110 */     return this.containerSlots;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public ItemStack getCursor() {
/* 121 */     return this.newCursor;
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
/*     */   public void setCursor(@Nullable ItemStack newCursor) {
/* 134 */     this.newCursor = newCursor;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public ItemStack getOldCursor() {
/* 145 */     return this.oldCursor.clone();
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
/*     */   public DragType getType() {
/* 159 */     return this.type;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public HandlerList getHandlers() {
/* 165 */     return handlers;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public static HandlerList getHandlerList() {
/* 170 */     return handlers;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\inventory\InventoryDragEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
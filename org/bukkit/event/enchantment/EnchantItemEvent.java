/*     */ package org.bukkit.event.enchantment;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import org.apache.commons.lang.Validate;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.enchantments.Enchantment;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.Cancellable;
/*     */ import org.bukkit.event.HandlerList;
/*     */ import org.bukkit.event.inventory.InventoryEvent;
/*     */ import org.bukkit.inventory.InventoryView;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ 
/*     */ public class EnchantItemEvent
/*     */   extends InventoryEvent
/*     */   implements Cancellable
/*     */ {
/*  21 */   private static final HandlerList handlers = new HandlerList();
/*     */   private final Block table;
/*     */   private final ItemStack item;
/*     */   private int level;
/*     */   private boolean cancelled;
/*     */   private final Map<Enchantment, Integer> enchants;
/*     */   private final Player enchanter;
/*     */   private final int button;
/*     */   
/*     */   public EnchantItemEvent(@NotNull Player enchanter, @NotNull InventoryView view, @NotNull Block table, @NotNull ItemStack item, int level, @NotNull Map<Enchantment, Integer> enchants, int i) {
/*  31 */     super(view);
/*  32 */     this.enchanter = enchanter;
/*  33 */     this.table = table;
/*  34 */     this.item = item;
/*  35 */     this.level = level;
/*  36 */     this.enchants = new HashMap<>(enchants);
/*  37 */     this.cancelled = false;
/*  38 */     this.button = i;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Player getEnchanter() {
/*  48 */     return this.enchanter;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Block getEnchantBlock() {
/*  58 */     return this.table;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public ItemStack getItem() {
/*  68 */     return this.item;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getExpLevelCost() {
/*  78 */     return this.level;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setExpLevelCost(int level) {
/*  88 */     Validate.isTrue((level > 0), "The cost must be greater than 0!");
/*     */     
/*  90 */     this.level = level;
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
/*     */   public Map<Enchantment, Integer> getEnchantsToAdd() {
/* 102 */     return this.enchants;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int whichButton() {
/* 111 */     return this.button;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isCancelled() {
/* 116 */     return this.cancelled;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCancelled(boolean cancel) {
/* 121 */     this.cancelled = cancel;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public HandlerList getHandlers() {
/* 127 */     return handlers;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public static HandlerList getHandlerList() {
/* 132 */     return handlers;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\enchantment\EnchantItemEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
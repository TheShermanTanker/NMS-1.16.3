/*     */ package org.bukkit.event.enchantment;
/*     */ 
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.enchantments.EnchantmentOffer;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.Cancellable;
/*     */ import org.bukkit.event.HandlerList;
/*     */ import org.bukkit.event.inventory.InventoryEvent;
/*     */ import org.bukkit.inventory.InventoryView;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ 
/*     */ public class PrepareItemEnchantEvent
/*     */   extends InventoryEvent
/*     */   implements Cancellable
/*     */ {
/*  18 */   private static final HandlerList handlers = new HandlerList();
/*     */   private final Block table;
/*     */   private final ItemStack item;
/*     */   private final EnchantmentOffer[] offers;
/*     */   private final int bonus;
/*     */   private boolean cancelled;
/*     */   private final Player enchanter;
/*     */   
/*     */   public PrepareItemEnchantEvent(@NotNull Player enchanter, @NotNull InventoryView view, @NotNull Block table, @NotNull ItemStack item, @NotNull EnchantmentOffer[] offers, int bonus) {
/*  27 */     super(view);
/*  28 */     this.enchanter = enchanter;
/*  29 */     this.table = table;
/*  30 */     this.item = item;
/*  31 */     this.offers = offers;
/*  32 */     this.bonus = bonus;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Player getEnchanter() {
/*  42 */     return this.enchanter;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Block getEnchantBlock() {
/*  52 */     return this.table;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public ItemStack getItem() {
/*  62 */     return this.item;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public int[] getExpLevelCostsOffered() {
/*  73 */     int[] levelOffers = new int[this.offers.length];
/*  74 */     for (int i = 0; i < this.offers.length; i++) {
/*  75 */       levelOffers[i] = (this.offers[i] != null) ? this.offers[i].getCost() : 0;
/*     */     }
/*  77 */     return levelOffers;
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
/*     */   @NotNull
/*     */   public EnchantmentOffer[] getOffers() {
/*  90 */     return this.offers;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getEnchantmentBonus() {
/*  99 */     return this.bonus;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isCancelled() {
/* 104 */     return this.cancelled;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCancelled(boolean cancel) {
/* 109 */     this.cancelled = cancel;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public HandlerList getHandlers() {
/* 115 */     return handlers;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public static HandlerList getHandlerList() {
/* 120 */     return handlers;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\enchantment\PrepareItemEnchantEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
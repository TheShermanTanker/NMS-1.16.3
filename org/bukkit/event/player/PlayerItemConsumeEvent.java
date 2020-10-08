/*     */ package org.bukkit.event.player;
/*     */ 
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.Cancellable;
/*     */ import org.bukkit.event.HandlerList;
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
/*     */ public class PlayerItemConsumeEvent
/*     */   extends PlayerEvent
/*     */   implements Cancellable
/*     */ {
/*  22 */   private static final HandlerList handlers = new HandlerList();
/*     */   
/*     */   private boolean isCancelled = false;
/*     */   
/*     */   private ItemStack item;
/*     */   
/*     */   @Nullable
/*     */   private ItemStack replacement;
/*     */   
/*     */   public PlayerItemConsumeEvent(@NotNull Player player, @NotNull ItemStack item) {
/*  32 */     super(player);
/*     */     
/*  34 */     this.item = item;
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
/*     */   public ItemStack getItem() {
/*  46 */     return this.item.clone();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setItem(@Nullable ItemStack item) {
/*  55 */     if (item == null) {
/*  56 */       this.item = new ItemStack(Material.AIR);
/*     */     } else {
/*  58 */       this.item = item;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public ItemStack getReplacement() {
/*  71 */     return this.replacement;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setReplacement(@Nullable ItemStack replacement) {
/*  81 */     this.replacement = replacement;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isCancelled() {
/*  87 */     return this.isCancelled;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCancelled(boolean cancel) {
/*  92 */     this.isCancelled = cancel;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public HandlerList getHandlers() {
/*  98 */     return handlers;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public static HandlerList getHandlerList() {
/* 103 */     return handlers;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\player\PlayerItemConsumeEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
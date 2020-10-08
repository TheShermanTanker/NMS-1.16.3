/*     */ package org.bukkit.event.world;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import org.bukkit.World;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.event.Cancellable;
/*     */ import org.bukkit.event.HandlerList;
/*     */ import org.bukkit.inventory.InventoryHolder;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.loot.LootContext;
/*     */ import org.bukkit.loot.LootTable;
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
/*     */ public class LootGenerateEvent
/*     */   extends WorldEvent
/*     */   implements Cancellable
/*     */ {
/*  28 */   private static final HandlerList handlers = new HandlerList();
/*     */   private boolean cancelled;
/*     */   private final Entity entity;
/*     */   private final InventoryHolder inventoryHolder;
/*     */   private final LootTable lootTable;
/*     */   private final LootContext lootContext;
/*     */   private final List<ItemStack> loot;
/*     */   private final boolean plugin;
/*     */   
/*     */   public LootGenerateEvent(@NotNull World world, @Nullable Entity entity, @Nullable InventoryHolder inventoryHolder, @NotNull LootTable lootTable, @NotNull LootContext lootContext, @NotNull List<ItemStack> items, boolean plugin) {
/*  38 */     super(world);
/*  39 */     this.entity = entity;
/*  40 */     this.inventoryHolder = inventoryHolder;
/*  41 */     this.lootTable = lootTable;
/*  42 */     this.lootContext = lootContext;
/*  43 */     this.loot = items;
/*  44 */     this.plugin = plugin;
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
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public Entity getEntity() {
/*  60 */     return this.entity;
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
/*     */   @Nullable
/*     */   public InventoryHolder getInventoryHolder() {
/*  73 */     return this.inventoryHolder;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public LootTable getLootTable() {
/*  83 */     return this.lootTable;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public LootContext getLootContext() {
/*  94 */     return this.lootContext;
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
/*     */   public void setLoot(@Nullable Collection<ItemStack> loot) {
/* 106 */     this.loot.clear();
/* 107 */     if (loot != null) {
/* 108 */       this.loot.addAll(loot);
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
/*     */   
/*     */   @NotNull
/*     */   public List<ItemStack> getLoot() {
/* 122 */     return this.loot;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isPlugin() {
/* 133 */     return this.plugin;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCancelled(boolean cancel) {
/* 138 */     this.cancelled = cancel;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isCancelled() {
/* 143 */     return this.cancelled;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public HandlerList getHandlers() {
/* 149 */     return handlers;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public static HandlerList getHandlerList() {
/* 154 */     return handlers;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\world\LootGenerateEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
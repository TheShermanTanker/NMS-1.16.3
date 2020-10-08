/*     */ package com.destroystokyo.paper.event.block;
/*     */ 
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.block.data.BlockData;
/*     */ import org.bukkit.event.Cancellable;
/*     */ import org.bukkit.event.HandlerList;
/*     */ import org.bukkit.event.inventory.InventoryEvent;
/*     */ import org.bukkit.inventory.AnvilInventory;
/*     */ import org.bukkit.inventory.Inventory;
/*     */ import org.bukkit.inventory.InventoryView;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ public class AnvilDamagedEvent
/*     */   extends InventoryEvent
/*     */   implements Cancellable {
/*  17 */   private static final HandlerList handlers = new HandlerList();
/*     */   private boolean cancel;
/*     */   private DamageState damageState;
/*     */   
/*     */   public AnvilDamagedEvent(@NotNull InventoryView inventory, @NotNull BlockData blockData) {
/*  22 */     super(inventory);
/*  23 */     this.damageState = DamageState.getState(blockData);
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public AnvilInventory getInventory() {
/*  29 */     return (AnvilInventory)super.getInventory();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public DamageState getDamageState() {
/*  39 */     return this.damageState;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDamageState(@NotNull DamageState damageState) {
/*  48 */     this.damageState = damageState;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isBreaking() {
/*  57 */     return (this.damageState == DamageState.BROKEN);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBreaking(boolean breaking) {
/*  66 */     if (breaking) {
/*  67 */       this.damageState = DamageState.BROKEN;
/*  68 */     } else if (this.damageState == DamageState.BROKEN) {
/*  69 */       this.damageState = DamageState.DAMAGED;
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean isCancelled() {
/*  74 */     return this.cancel;
/*     */   }
/*     */   
/*     */   public void setCancelled(boolean cancel) {
/*  78 */     this.cancel = cancel;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public HandlerList getHandlers() {
/*  83 */     return handlers;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public static HandlerList getHandlerList() {
/*  88 */     return handlers;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public enum DamageState
/*     */   {
/*  95 */     FULL((String)Material.ANVIL),
/*  96 */     CHIPPED((String)Material.CHIPPED_ANVIL),
/*  97 */     DAMAGED((String)Material.DAMAGED_ANVIL),
/*  98 */     BROKEN((String)Material.AIR);
/*     */     
/*     */     private Material material;
/*     */     
/*     */     DamageState(Material material) {
/* 103 */       this.material = material;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     @NotNull
/*     */     public Material getMaterial() {
/* 113 */       return this.material;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     @NotNull
/*     */     public static DamageState getState(@Nullable BlockData blockData) {
/* 125 */       return (blockData == null) ? BROKEN : getState(blockData.getMaterial());
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     @NotNull
/*     */     public static DamageState getState(@Nullable Material material) {
/* 137 */       if (material == null) {
/* 138 */         return BROKEN;
/*     */       }
/* 140 */       for (DamageState state : values()) {
/* 141 */         if (state.material == material) {
/* 142 */           return state;
/*     */         }
/*     */       } 
/* 145 */       throw new IllegalArgumentException("Material not an anvil");
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\event\block\AnvilDamagedEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
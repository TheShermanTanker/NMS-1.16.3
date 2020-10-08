/*     */ package org.bukkit.event.player;
/*     */ 
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.block.BlockFace;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.Cancellable;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.HandlerList;
/*     */ import org.bukkit.event.block.Action;
/*     */ import org.bukkit.inventory.EquipmentSlot;
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
/*     */ public class PlayerInteractEvent
/*     */   extends PlayerEvent
/*     */   implements Cancellable
/*     */ {
/*  29 */   private static final HandlerList handlers = new HandlerList();
/*     */   protected ItemStack item;
/*     */   protected Action action;
/*     */   protected Block blockClicked;
/*     */   protected BlockFace blockFace;
/*     */   private Event.Result useClickedBlock;
/*     */   private Event.Result useItemInHand;
/*     */   private EquipmentSlot hand;
/*     */   
/*     */   public PlayerInteractEvent(@NotNull Player who, @NotNull Action action, @Nullable ItemStack item, @Nullable Block clickedBlock, @NotNull BlockFace clickedFace) {
/*  39 */     this(who, action, item, clickedBlock, clickedFace, EquipmentSlot.HAND);
/*     */   }
/*     */   
/*     */   public PlayerInteractEvent(@NotNull Player who, @NotNull Action action, @Nullable ItemStack item, @Nullable Block clickedBlock, @NotNull BlockFace clickedFace, @Nullable EquipmentSlot hand) {
/*  43 */     super(who);
/*  44 */     this.action = action;
/*  45 */     this.item = item;
/*  46 */     this.blockClicked = clickedBlock;
/*  47 */     this.blockFace = clickedFace;
/*  48 */     this.hand = hand;
/*     */     
/*  50 */     this.useItemInHand = Event.Result.DEFAULT;
/*  51 */     this.useClickedBlock = (clickedBlock == null) ? Event.Result.DENY : Event.Result.ALLOW;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Action getAction() {
/*  61 */     return this.action;
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
/*     */   
/*     */   @Deprecated
/*     */   public boolean isCancelled() {
/*  78 */     return (useInteractedBlock() == Event.Result.DENY);
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
/*     */   public void setCancelled(boolean cancel) {
/*  93 */     setUseInteractedBlock(cancel ? Event.Result.DENY : ((useInteractedBlock() == Event.Result.DENY) ? Event.Result.DEFAULT : useInteractedBlock()));
/*  94 */     setUseItemInHand(cancel ? Event.Result.DENY : ((useItemInHand() == Event.Result.DENY) ? Event.Result.DEFAULT : useItemInHand()));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public ItemStack getItem() {
/* 104 */     return this.item;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Material getMaterial() {
/* 115 */     if (!hasItem()) {
/* 116 */       return Material.AIR;
/*     */     }
/*     */     
/* 119 */     return this.item.getType();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasBlock() {
/* 128 */     return (this.blockClicked != null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasItem() {
/* 137 */     return (this.item != null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isBlockInHand() {
/* 147 */     if (!hasItem()) {
/* 148 */       return false;
/*     */     }
/*     */     
/* 151 */     return this.item.getType().isBlock();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public Block getClickedBlock() {
/* 161 */     return this.blockClicked;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public BlockFace getBlockFace() {
/* 171 */     return this.blockFace;
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
/*     */   public Event.Result useInteractedBlock() {
/* 183 */     return this.useClickedBlock;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setUseInteractedBlock(@NotNull Event.Result useInteractedBlock) {
/* 190 */     this.useClickedBlock = useInteractedBlock;
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
/*     */   public Event.Result useItemInHand() {
/* 203 */     return this.useItemInHand;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setUseItemInHand(@NotNull Event.Result useItemInHand) {
/* 210 */     this.useItemInHand = useItemInHand;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public EquipmentSlot getHand() {
/* 221 */     return this.hand;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public HandlerList getHandlers() {
/* 227 */     return handlers;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public static HandlerList getHandlerList() {
/* 232 */     return handlers;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\player\PlayerInteractEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
/*    */ package org.bukkit.event.player;
/*    */ 
/*    */ import org.bukkit.entity.ExperienceOrb;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.Cancellable;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PlayerItemMendEvent
/*    */   extends PlayerEvent
/*    */   implements Cancellable
/*    */ {
/* 18 */   private static final HandlerList handlers = new HandlerList();
/*    */   
/*    */   private final ItemStack item;
/*    */   private final ExperienceOrb experienceOrb;
/*    */   private int repairAmount;
/*    */   private boolean cancelled;
/*    */   
/*    */   public PlayerItemMendEvent(@NotNull Player who, @NotNull ItemStack item, @NotNull ExperienceOrb experienceOrb, int repairAmount) {
/* 26 */     super(who);
/* 27 */     this.item = item;
/* 28 */     this.experienceOrb = experienceOrb;
/* 29 */     this.repairAmount = repairAmount;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public ItemStack getItem() {
/* 41 */     return this.item;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public ExperienceOrb getExperienceOrb() {
/* 51 */     return this.experienceOrb;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getRepairAmount() {
/* 63 */     return this.repairAmount;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setRepairAmount(int amount) {
/* 74 */     this.repairAmount = amount;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isCancelled() {
/* 79 */     return this.cancelled;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCancelled(boolean cancelled) {
/* 84 */     this.cancelled = cancelled;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/* 90 */     return handlers;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static HandlerList getHandlerList() {
/* 95 */     return handlers;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\player\PlayerItemMendEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
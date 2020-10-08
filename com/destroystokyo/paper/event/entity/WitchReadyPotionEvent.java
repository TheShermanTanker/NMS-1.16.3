/*    */ package com.destroystokyo.paper.event.entity;
/*    */ public class WitchReadyPotionEvent extends EntityEvent implements Cancellable { private ItemStack potion;
/*    */   @Nullable
/*    */   public static ItemStack process(@NotNull Witch witch, @Nullable ItemStack potion) {
/*    */     WitchReadyPotionEvent event = new WitchReadyPotionEvent(witch, potion);
/*    */     if (!event.callEvent() || event.getPotion() == null)
/*    */       return new ItemStack(Material.AIR); 
/*    */     return event.getPotion();
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public Witch getEntity() {
/*    */     return (Witch)super.getEntity();
/*    */   }
/*    */   
/* 16 */   public WitchReadyPotionEvent(@NotNull Witch witch, @Nullable ItemStack potion) { super((Entity)witch);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 69 */     this.cancelled = false;
/*    */     this.potion = potion; }
/*    */   @Nullable
/*    */   public ItemStack getPotion() { return this.potion; }
/* 73 */   public void setPotion(@Nullable ItemStack potion) { this.potion = (potion != null) ? potion.clone() : null; } public boolean isCancelled() { return this.cancelled; } private static final HandlerList handlers = new HandlerList(); private boolean cancelled; @NotNull
/*    */   public HandlerList getHandlers() { return handlers; }
/*    */   @NotNull
/*    */   public static HandlerList getHandlerList() { return handlers; }
/*    */   public void setCancelled(boolean cancel) {
/* 78 */     this.cancelled = cancel;
/*    */   } }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\event\entity\WitchReadyPotionEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
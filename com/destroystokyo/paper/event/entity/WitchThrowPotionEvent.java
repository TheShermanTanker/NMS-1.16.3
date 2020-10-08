/*    */ package com.destroystokyo.paper.event.entity;
/*    */ 
/*    */ 
/*    */ public class WitchThrowPotionEvent extends EntityEvent implements Cancellable {
/*    */   @NotNull
/*    */   private final LivingEntity target;
/*    */   @Nullable
/*    */   private ItemStack potion;
/*    */   
/*    */   @NotNull
/*    */   public Witch getEntity() {
/*    */     return (Witch)super.getEntity();
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public LivingEntity getTarget() {
/*    */     return this.target;
/*    */   }
/*    */   
/* 20 */   public WitchThrowPotionEvent(@NotNull Witch witch, @NotNull LivingEntity target, @Nullable ItemStack potion) { super((Entity)witch);
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
/* 67 */     this.cancelled = false;
/*    */     this.target = target;
/*    */     this.potion = potion; }
/*    */   @Nullable
/*    */   public ItemStack getPotion() { return this.potion; } public void setPotion(@Nullable ItemStack potion) {
/*    */     this.potion = (potion != null) ? potion.clone() : null;
/*    */   } public boolean isCancelled() {
/* 74 */     return (this.cancelled || this.potion == null); } private static final HandlerList handlers = new HandlerList(); private boolean cancelled; @NotNull
/*    */   public HandlerList getHandlers() { return handlers; }
/*    */   @NotNull
/*    */   public static HandlerList getHandlerList() { return handlers; }
/*    */   public void setCancelled(boolean cancel) {
/* 79 */     this.cancelled = cancel;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\event\entity\WitchThrowPotionEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
/*    */ package org.bukkit.event.player;
/*    */ 
/*    */ import org.bukkit.NamespacedKey;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.Cancellable;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ public class PlayerRecipeDiscoverEvent
/*    */   extends PlayerEvent
/*    */   implements Cancellable
/*    */ {
/* 14 */   private static final HandlerList handlers = new HandlerList();
/*    */   
/*    */   private boolean cancel = false;
/*    */   private final NamespacedKey recipe;
/*    */   
/*    */   public PlayerRecipeDiscoverEvent(@NotNull Player who, @NotNull NamespacedKey recipe) {
/* 20 */     super(who);
/* 21 */     this.recipe = recipe;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public NamespacedKey getRecipe() {
/* 31 */     return this.recipe;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isCancelled() {
/* 36 */     return this.cancel;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCancelled(boolean cancel) {
/* 41 */     this.cancel = cancel;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/* 47 */     return handlers;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static HandlerList getHandlerList() {
/* 52 */     return handlers;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\player\PlayerRecipeDiscoverEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
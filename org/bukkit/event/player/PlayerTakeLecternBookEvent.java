/*    */ package org.bukkit.event.player;
/*    */ 
/*    */ import org.bukkit.block.Lectern;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.Cancellable;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PlayerTakeLecternBookEvent
/*    */   extends PlayerEvent
/*    */   implements Cancellable
/*    */ {
/* 17 */   private static final HandlerList handlers = new HandlerList();
/*    */   
/*    */   private boolean cancelled;
/*    */   private final Lectern lectern;
/*    */   
/*    */   public PlayerTakeLecternBookEvent(@NotNull Player who, @NotNull Lectern lectern) {
/* 23 */     super(who);
/* 24 */     this.lectern = lectern;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public Lectern getLectern() {
/* 34 */     return this.lectern;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public ItemStack getBook() {
/* 44 */     return this.lectern.getInventory().getItem(0);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isCancelled() {
/* 49 */     return this.cancelled;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCancelled(boolean cancel) {
/* 54 */     this.cancelled = cancel;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/* 60 */     return handlers;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static HandlerList getHandlerList() {
/* 65 */     return handlers;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\player\PlayerTakeLecternBookEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
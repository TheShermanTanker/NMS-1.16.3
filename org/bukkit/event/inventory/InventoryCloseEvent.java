/*    */ package org.bukkit.event.inventory;
/*    */ 
/*    */ import org.bukkit.entity.HumanEntity;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.bukkit.inventory.InventoryView;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class InventoryCloseEvent
/*    */   extends InventoryEvent
/*    */ {
/* 13 */   private static final HandlerList handlers = new HandlerList();
/*    */   private final Reason reason;
/*    */   
/*    */   @NotNull
/*    */   public Reason getReason() {
/* 18 */     return this.reason;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public enum Reason
/*    */   {
/* 25 */     UNKNOWN,
/*    */ 
/*    */ 
/*    */     
/* 29 */     TELEPORT,
/*    */ 
/*    */ 
/*    */     
/* 33 */     CANT_USE,
/*    */ 
/*    */ 
/*    */     
/* 37 */     UNLOADED,
/*    */ 
/*    */ 
/*    */     
/* 41 */     OPEN_NEW,
/*    */ 
/*    */ 
/*    */     
/* 45 */     PLAYER,
/*    */ 
/*    */ 
/*    */     
/* 49 */     DISCONNECT,
/*    */ 
/*    */ 
/*    */     
/* 53 */     DEATH,
/*    */ 
/*    */ 
/*    */     
/* 57 */     PLUGIN;
/*    */   }
/*    */   
/*    */   public InventoryCloseEvent(@NotNull InventoryView transaction) {
/* 61 */     this(transaction, Reason.UNKNOWN);
/*    */   }
/*    */   
/*    */   public InventoryCloseEvent(@NotNull InventoryView transaction, @NotNull Reason reason) {
/* 65 */     super(transaction);
/* 66 */     this.reason = reason;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public final HumanEntity getPlayer() {
/* 77 */     return this.transaction.getPlayer();
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/* 83 */     return handlers;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static HandlerList getHandlerList() {
/* 88 */     return handlers;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\inventory\InventoryCloseEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
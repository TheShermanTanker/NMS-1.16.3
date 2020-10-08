/*    */ package org.bukkit.event.player;
/*    */ 
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.bukkit.inventory.EquipmentSlot;
/*    */ import org.bukkit.util.Vector;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PlayerInteractAtEntityEvent
/*    */   extends PlayerInteractEntityEvent
/*    */ {
/* 21 */   private static final HandlerList handlers = new HandlerList();
/*    */   private final Vector position;
/*    */   
/*    */   public PlayerInteractAtEntityEvent(@NotNull Player who, @NotNull Entity clickedEntity, @NotNull Vector position) {
/* 25 */     this(who, clickedEntity, position, EquipmentSlot.HAND);
/*    */   }
/*    */   
/*    */   public PlayerInteractAtEntityEvent(@NotNull Player who, @NotNull Entity clickedEntity, @NotNull Vector position, @NotNull EquipmentSlot hand) {
/* 29 */     super(who, clickedEntity, hand);
/* 30 */     this.position = position;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public Vector getClickedPosition() {
/* 35 */     return this.position.clone();
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/* 41 */     return handlers;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static HandlerList getHandlerList() {
/* 46 */     return handlers;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\player\PlayerInteractAtEntityEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
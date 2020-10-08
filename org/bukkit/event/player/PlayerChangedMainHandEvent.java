/*    */ package org.bukkit.event.player;
/*    */ 
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.bukkit.inventory.MainHand;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PlayerChangedMainHandEvent
/*    */   extends PlayerEvent
/*    */ {
/* 13 */   private static final HandlerList handlers = new HandlerList();
/*    */   
/*    */   private final MainHand mainHand;
/*    */   
/*    */   public PlayerChangedMainHandEvent(@NotNull Player who, @NotNull MainHand mainHand) {
/* 18 */     super(who);
/* 19 */     this.mainHand = mainHand;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public MainHand getMainHand() {
/* 30 */     return this.mainHand;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/* 36 */     return handlers;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static HandlerList getHandlerList() {
/* 41 */     return handlers;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\player\PlayerChangedMainHandEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
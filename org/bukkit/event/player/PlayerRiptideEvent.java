/*    */ package org.bukkit.event.player;
/*    */ 
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PlayerRiptideEvent
/*    */   extends PlayerEvent
/*    */ {
/* 17 */   private static final HandlerList handlers = new HandlerList();
/*    */   private final ItemStack item;
/*    */   
/*    */   public PlayerRiptideEvent(@NotNull Player who, @NotNull ItemStack item) {
/* 21 */     super(who);
/* 22 */     this.item = item;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public ItemStack getItem() {
/* 32 */     return this.item;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/* 38 */     return handlers;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static HandlerList getHandlerList() {
/* 43 */     return handlers;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\player\PlayerRiptideEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
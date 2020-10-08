/*    */ package org.bukkit.event.player;
/*    */ 
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ 
/*    */ public class PlayerQuitEvent
/*    */   extends PlayerEvent
/*    */ {
/* 12 */   private static final HandlerList handlers = new HandlerList();
/*    */   private String quitMessage;
/*    */   
/*    */   public PlayerQuitEvent(@NotNull Player who, @Nullable String quitMessage) {
/* 16 */     super(who);
/* 17 */     this.quitMessage = quitMessage;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public String getQuitMessage() {
/* 27 */     return this.quitMessage;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setQuitMessage(@Nullable String quitMessage) {
/* 36 */     this.quitMessage = quitMessage;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/* 42 */     return handlers;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static HandlerList getHandlerList() {
/* 47 */     return handlers;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\player\PlayerQuitEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
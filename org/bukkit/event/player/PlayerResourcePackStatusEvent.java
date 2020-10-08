/*    */ package org.bukkit.event.player;
/*    */ 
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PlayerResourcePackStatusEvent
/*    */   extends PlayerEvent
/*    */ {
/* 13 */   private static final HandlerList handlers = new HandlerList();
/*    */   @Deprecated
/*    */   private final String hash;
/*    */   private final Status status;
/*    */   
/*    */   public PlayerResourcePackStatusEvent(@NotNull Player who, @NotNull Status resourcePackStatus) {
/* 19 */     super(who);
/* 20 */     this.hash = null;
/* 21 */     this.status = resourcePackStatus;
/*    */   }
/*    */   
/*    */   @Deprecated
/*    */   public PlayerResourcePackStatusEvent(Player who, Status resourcePackStatus, String hash) {
/* 26 */     super(who);
/* 27 */     this.hash = hash;
/* 28 */     this.status = resourcePackStatus;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Deprecated
/*    */   public String getHash() {
/* 36 */     return this.hash;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public Status getStatus() {
/* 47 */     return this.status;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/* 53 */     return handlers;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static HandlerList getHandlerList() {
/* 58 */     return handlers;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public enum Status
/*    */   {
/* 70 */     SUCCESSFULLY_LOADED,
/*    */ 
/*    */ 
/*    */     
/* 74 */     DECLINED,
/*    */ 
/*    */ 
/*    */     
/* 78 */     FAILED_DOWNLOAD,
/*    */ 
/*    */ 
/*    */     
/* 82 */     ACCEPTED;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\player\PlayerResourcePackStatusEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
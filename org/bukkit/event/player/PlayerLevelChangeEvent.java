/*    */ package org.bukkit.event.player;
/*    */ 
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ public class PlayerLevelChangeEvent
/*    */   extends PlayerEvent
/*    */ {
/* 11 */   private static final HandlerList handlers = new HandlerList();
/*    */   private final int oldLevel;
/*    */   private final int newLevel;
/*    */   
/*    */   public PlayerLevelChangeEvent(@NotNull Player player, int oldLevel, int newLevel) {
/* 16 */     super(player);
/* 17 */     this.oldLevel = oldLevel;
/* 18 */     this.newLevel = newLevel;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getOldLevel() {
/* 27 */     return this.oldLevel;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getNewLevel() {
/* 36 */     return this.newLevel;
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


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\player\PlayerLevelChangeEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
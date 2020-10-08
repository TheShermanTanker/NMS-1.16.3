/*    */ package org.bukkit.event.player;
/*    */ 
/*    */ import org.bukkit.advancement.Advancement;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PlayerAdvancementDoneEvent
/*    */   extends PlayerEvent
/*    */ {
/* 13 */   private static final HandlerList handlers = new HandlerList();
/*    */   
/*    */   private final Advancement advancement;
/*    */   
/*    */   public PlayerAdvancementDoneEvent(@NotNull Player who, @NotNull Advancement advancement) {
/* 18 */     super(who);
/* 19 */     this.advancement = advancement;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public Advancement getAdvancement() {
/* 29 */     return this.advancement;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/* 35 */     return handlers;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static HandlerList getHandlerList() {
/* 40 */     return handlers;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\player\PlayerAdvancementDoneEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
/*    */ package org.bukkit.event.player;
/*    */ 
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.Event;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ public abstract class PlayerEvent
/*    */   extends Event
/*    */ {
/*    */   protected Player player;
/*    */   
/*    */   public PlayerEvent(@NotNull Player who) {
/* 14 */     this.player = who;
/*    */   }
/*    */   
/*    */   public PlayerEvent(@NotNull Player who, boolean async) {
/* 18 */     super(async);
/* 19 */     this.player = who;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public final Player getPlayer() {
/* 30 */     return this.player;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\player\PlayerEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
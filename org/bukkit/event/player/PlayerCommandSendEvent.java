/*    */ package org.bukkit.event.player;
/*    */ 
/*    */ import java.util.Collection;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.HandlerList;
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
/*    */ public class PlayerCommandSendEvent
/*    */   extends PlayerEvent
/*    */ {
/* 19 */   private static final HandlerList handlers = new HandlerList();
/*    */   private final Collection<String> commands;
/*    */   
/*    */   public PlayerCommandSendEvent(@NotNull Player player, @NotNull Collection<String> commands) {
/* 23 */     super(player);
/* 24 */     this.commands = commands;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public Collection<String> getCommands() {
/* 37 */     return this.commands;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/* 43 */     return handlers;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static HandlerList getHandlerList() {
/* 48 */     return handlers;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\player\PlayerCommandSendEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
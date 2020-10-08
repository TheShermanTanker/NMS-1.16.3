/*    */ package org.bukkit.event.raid;
/*    */ 
/*    */ import java.util.Collections;
/*    */ import java.util.List;
/*    */ import org.bukkit.Raid;
/*    */ import org.bukkit.World;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RaidFinishEvent
/*    */   extends RaidEvent
/*    */ {
/* 16 */   private static final HandlerList handlers = new HandlerList();
/*    */   
/*    */   private final List<Player> winners;
/*    */   
/*    */   public RaidFinishEvent(@NotNull Raid raid, @NotNull World world, @NotNull List<Player> winners) {
/* 21 */     super(raid, world);
/* 22 */     this.winners = winners;
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
/*    */   public List<Player> getWinners() {
/* 35 */     return Collections.unmodifiableList(this.winners);
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


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\raid\RaidFinishEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
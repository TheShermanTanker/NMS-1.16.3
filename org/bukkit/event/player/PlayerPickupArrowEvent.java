/*    */ package org.bukkit.event.player;
/*    */ 
/*    */ import org.bukkit.entity.AbstractArrow;
/*    */ import org.bukkit.entity.Item;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PlayerPickupArrowEvent
/*    */   extends PlayerPickupItemEvent
/*    */ {
/*    */   private final AbstractArrow arrow;
/*    */   
/*    */   public PlayerPickupArrowEvent(@NotNull Player player, @NotNull Item item, @NotNull AbstractArrow arrow) {
/* 16 */     super(player, item, 0);
/* 17 */     this.arrow = arrow;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public AbstractArrow getArrow() {
/* 27 */     return this.arrow;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\player\PlayerPickupArrowEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
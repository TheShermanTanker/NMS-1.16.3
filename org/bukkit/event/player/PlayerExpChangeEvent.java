/*    */ package org.bukkit.event.player;
/*    */ 
/*    */ import org.bukkit.entity.Entity;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PlayerExpChangeEvent
/*    */   extends PlayerEvent
/*    */ {
/* 14 */   private static final HandlerList handlers = new HandlerList();
/*    */   
/*    */   @Nullable
/*    */   private final Entity source;
/*    */   private int exp;
/*    */   
/*    */   public PlayerExpChangeEvent(@NotNull Player player, int expAmount) {
/* 21 */     this(player, null, expAmount);
/*    */   }
/*    */   
/*    */   public PlayerExpChangeEvent(@NotNull Player player, @Nullable Entity sourceEntity, int expAmount) {
/* 25 */     super(player);
/* 26 */     this.source = sourceEntity;
/* 27 */     this.exp = expAmount;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public Entity getSource() {
/* 37 */     return this.source;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getAmount() {
/* 47 */     return this.exp;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setAmount(int amount) {
/* 56 */     this.exp = amount;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/* 62 */     return handlers;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static HandlerList getHandlerList() {
/* 67 */     return handlers;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\player\PlayerExpChangeEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
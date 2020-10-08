/*    */ package org.bukkit.event.player;
/*    */ 
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PlayerBedLeaveEvent
/*    */   extends PlayerEvent
/*    */ {
/* 13 */   private static final HandlerList handlers = new HandlerList();
/*    */   private final Block bed;
/*    */   private boolean setBedSpawn;
/*    */   
/*    */   public PlayerBedLeaveEvent(@NotNull Player who, @NotNull Block bed, boolean setBedSpawn) {
/* 18 */     super(who);
/* 19 */     this.bed = bed;
/* 20 */     this.setBedSpawn = setBedSpawn;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public Block getBed() {
/* 30 */     return this.bed;
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
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean shouldSetSpawnLocation() {
/* 46 */     return this.setBedSpawn;
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
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setSpawnLocation(boolean setBedSpawn) {
/* 62 */     this.setBedSpawn = setBedSpawn;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/* 68 */     return handlers;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static HandlerList getHandlerList() {
/* 73 */     return handlers;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\player\PlayerBedLeaveEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
/*    */ package org.bukkit.event.block;
/*    */ 
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.Cancellable;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SignChangeEvent
/*    */   extends BlockEvent
/*    */   implements Cancellable
/*    */ {
/* 16 */   private static final HandlerList handlers = new HandlerList();
/*    */   private boolean cancel = false;
/*    */   private final Player player;
/*    */   private final String[] lines;
/*    */   
/*    */   public SignChangeEvent(@NotNull Block theBlock, @NotNull Player thePlayer, @NotNull String[] theLines) {
/* 22 */     super(theBlock);
/* 23 */     this.player = thePlayer;
/* 24 */     this.lines = theLines;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public Player getPlayer() {
/* 34 */     return this.player;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public String[] getLines() {
/* 44 */     return this.lines;
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
/*    */   @Nullable
/*    */   public String getLine(int index) throws IndexOutOfBoundsException {
/* 58 */     return this.lines[index];
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
/*    */   public void setLine(int index, @Nullable String line) throws IndexOutOfBoundsException {
/* 70 */     this.lines[index] = line;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isCancelled() {
/* 75 */     return this.cancel;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCancelled(boolean cancel) {
/* 80 */     this.cancel = cancel;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/* 86 */     return handlers;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static HandlerList getHandlerList() {
/* 91 */     return handlers;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\block\SignChangeEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
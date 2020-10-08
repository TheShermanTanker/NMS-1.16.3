/*    */ package org.bukkit.event.block;
/*    */ 
/*    */ import org.bukkit.Instrument;
/*    */ import org.bukkit.Note;
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.event.Cancellable;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class NotePlayEvent
/*    */   extends BlockEvent
/*    */   implements Cancellable
/*    */ {
/* 16 */   private static HandlerList handlers = new HandlerList();
/*    */   private Instrument instrument;
/*    */   private Note note;
/*    */   private boolean cancelled = false;
/*    */   
/*    */   public NotePlayEvent(@NotNull Block block, @NotNull Instrument instrument, @NotNull Note note) {
/* 22 */     super(block);
/* 23 */     this.instrument = instrument;
/* 24 */     this.note = note;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isCancelled() {
/* 29 */     return this.cancelled;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCancelled(boolean cancel) {
/* 34 */     this.cancelled = cancel;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public Instrument getInstrument() {
/* 44 */     return this.instrument;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public Note getNote() {
/* 54 */     return this.note;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Deprecated
/*    */   public void setInstrument(@NotNull Instrument instrument) {
/* 65 */     if (instrument != null) {
/* 66 */       this.instrument = instrument;
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @Deprecated
/*    */   public void setNote(@NotNull Note note) {
/* 78 */     if (note != null) {
/* 79 */       this.note = note;
/*    */     }
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


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\block\NotePlayEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
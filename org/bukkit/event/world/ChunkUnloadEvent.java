/*    */ package org.bukkit.event.world;
/*    */ 
/*    */ import org.bukkit.Chunk;
/*    */ import org.bukkit.event.HandlerList;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ public class ChunkUnloadEvent
/*    */   extends ChunkEvent
/*    */ {
/* 11 */   private static final HandlerList handlers = new HandlerList();
/*    */   private boolean saveChunk;
/*    */   
/*    */   public ChunkUnloadEvent(@NotNull Chunk chunk) {
/* 15 */     this(chunk, true);
/*    */   }
/*    */   
/*    */   public ChunkUnloadEvent(@NotNull Chunk chunk, boolean save) {
/* 19 */     super(chunk);
/* 20 */     this.saveChunk = save;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean isSaveChunk() {
/* 29 */     return this.saveChunk;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setSaveChunk(boolean saveChunk) {
/* 38 */     this.saveChunk = saveChunk;
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public HandlerList getHandlers() {
/* 44 */     return handlers;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static HandlerList getHandlerList() {
/* 49 */     return handlers;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\world\ChunkUnloadEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
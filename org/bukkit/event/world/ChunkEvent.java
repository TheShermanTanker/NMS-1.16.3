/*    */ package org.bukkit.event.world;
/*    */ 
/*    */ import org.bukkit.Chunk;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ public abstract class ChunkEvent
/*    */   extends WorldEvent
/*    */ {
/*    */   protected Chunk chunk;
/*    */   
/*    */   protected ChunkEvent(@NotNull Chunk chunk) {
/* 13 */     super(chunk.getWorld());
/* 14 */     this.chunk = chunk;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public Chunk getChunk() {
/* 24 */     return this.chunk;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\world\ChunkEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
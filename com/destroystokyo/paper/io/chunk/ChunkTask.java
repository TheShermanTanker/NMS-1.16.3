/*    */ package com.destroystokyo.paper.io.chunk;
/*    */ 
/*    */ import com.destroystokyo.paper.io.PaperFileIOThread;
/*    */ import com.destroystokyo.paper.io.PrioritizedTaskQueue;
/*    */ import net.minecraft.server.v1_16_R2.WorldServer;
/*    */ 
/*    */ abstract class ChunkTask
/*    */   extends PrioritizedTaskQueue.PrioritizedTask
/*    */   implements Runnable {
/*    */   public final WorldServer world;
/*    */   public final int chunkX;
/*    */   public final int chunkZ;
/*    */   public final ChunkTaskManager taskManager;
/*    */   
/*    */   public ChunkTask(WorldServer world, int chunkX, int chunkZ, int priority, ChunkTaskManager taskManager) {
/* 16 */     super(priority);
/* 17 */     this.world = world;
/* 18 */     this.chunkX = chunkX;
/* 19 */     this.chunkZ = chunkZ;
/* 20 */     this.taskManager = taskManager;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 25 */     return "Chunk task: class:" + getClass().getName() + ", for world '" + this.world.getWorld().getName() + "', (" + this.chunkX + "," + this.chunkZ + "), hashcode:" + 
/* 26 */       hashCode() + ", priority: " + getPriority();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean raisePriority(int priority) {
/* 31 */     PaperFileIOThread.Holder.INSTANCE.bumpPriority(this.world, this.chunkX, this.chunkZ, priority);
/* 32 */     return super.raisePriority(priority);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean updatePriority(int priority) {
/* 37 */     PaperFileIOThread.Holder.INSTANCE.setPriority(this.world, this.chunkX, this.chunkZ, priority);
/* 38 */     return super.updatePriority(priority);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\io\chunk\ChunkTask.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
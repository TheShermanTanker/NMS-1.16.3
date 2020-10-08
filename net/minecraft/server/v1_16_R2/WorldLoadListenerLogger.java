/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import javax.annotation.Nullable;
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ 
/*    */ public class WorldLoadListenerLogger
/*    */   implements WorldLoadListener {
/*  9 */   private static final Logger LOGGER = LogManager.getLogger();
/*    */   private int b;
/*    */   private int c;
/*    */   private long d;
/* 13 */   private long e = Long.MAX_VALUE;
/*    */ 
/*    */   
/*    */   public WorldLoadListenerLogger(int i) {
/* 17 */     setChunkRadius(i);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void setChunkRadius(int radius) {
/* 23 */     int j = radius * 2 + 1;
/*    */     
/* 25 */     this.b = j * j;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void a(ChunkCoordIntPair chunkcoordintpair) {
/* 31 */     this.e = SystemUtils.getMonotonicMillis();
/* 32 */     this.d = this.e;
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(ChunkCoordIntPair chunkcoordintpair, @Nullable ChunkStatus chunkstatus) {
/* 37 */     if (chunkstatus == ChunkStatus.FULL) {
/* 38 */       this.c++;
/*    */     }
/*    */     
/* 41 */     int i = c();
/*    */     
/* 43 */     if (SystemUtils.getMonotonicMillis() > this.e) {
/* 44 */       this.e += 500L;
/* 45 */       LOGGER.info((new ChatMessage("menu.preparingSpawn", new Object[] { Integer.valueOf(MathHelper.clamp(i, 0, 100)) })).getString());
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void b() {
/* 52 */     LOGGER.info("Time elapsed: {} ms", Long.valueOf(SystemUtils.getMonotonicMillis() - this.d));
/* 53 */     this.e = Long.MAX_VALUE;
/*    */   }
/*    */   
/*    */   public int c() {
/* 57 */     return MathHelper.d(this.c * 100.0F / this.b);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldLoadListenerLogger.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
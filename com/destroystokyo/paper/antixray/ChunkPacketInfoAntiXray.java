/*    */ package com.destroystokyo.paper.antixray;
/*    */ 
/*    */ import net.minecraft.server.v1_16_R2.Chunk;
/*    */ import net.minecraft.server.v1_16_R2.IBlockData;
/*    */ import net.minecraft.server.v1_16_R2.PacketPlayOutMapChunk;
/*    */ 
/*    */ public final class ChunkPacketInfoAntiXray
/*    */   extends ChunkPacketInfo<IBlockData>
/*    */   implements Runnable {
/*    */   private Chunk[] nearbyChunks;
/*    */   private final ChunkPacketBlockControllerAntiXray chunkPacketBlockControllerAntiXray;
/*    */   
/*    */   public ChunkPacketInfoAntiXray(PacketPlayOutMapChunk packetPlayOutMapChunk, Chunk chunk, int chunkSectionSelector, ChunkPacketBlockControllerAntiXray chunkPacketBlockControllerAntiXray) {
/* 14 */     super(packetPlayOutMapChunk, chunk, chunkSectionSelector);
/* 15 */     this.chunkPacketBlockControllerAntiXray = chunkPacketBlockControllerAntiXray;
/*    */   }
/*    */   
/*    */   public Chunk[] getNearbyChunks() {
/* 19 */     return this.nearbyChunks;
/*    */   }
/*    */   
/*    */   public void setNearbyChunks(Chunk... nearbyChunks) {
/* 23 */     this.nearbyChunks = nearbyChunks;
/*    */   }
/*    */ 
/*    */   
/*    */   public void run() {
/* 28 */     this.chunkPacketBlockControllerAntiXray.obfuscate(this);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\antixray\ChunkPacketInfoAntiXray.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
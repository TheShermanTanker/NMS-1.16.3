/*    */ package com.destroystokyo.paper.antixray;
/*    */ 
/*    */ import net.minecraft.server.v1_16_R2.BlockPosition;
/*    */ import net.minecraft.server.v1_16_R2.Chunk;
/*    */ import net.minecraft.server.v1_16_R2.ChunkSection;
/*    */ import net.minecraft.server.v1_16_R2.EnumDirection;
/*    */ import net.minecraft.server.v1_16_R2.IBlockData;
/*    */ import net.minecraft.server.v1_16_R2.IChunkAccess;
/*    */ import net.minecraft.server.v1_16_R2.PacketPlayOutMapChunk;
/*    */ import net.minecraft.server.v1_16_R2.PlayerInteractManager;
/*    */ import net.minecraft.server.v1_16_R2.World;
/*    */ 
/*    */ public class ChunkPacketBlockController
/*    */ {
/* 15 */   public static final ChunkPacketBlockController NO_OPERATION_INSTANCE = new ChunkPacketBlockController();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public IBlockData[] getPredefinedBlockData(World world, IChunkAccess chunk, ChunkSection chunkSection, boolean initializeBlocks) {
/* 22 */     return null;
/*    */   }
/*    */   
/*    */   public ChunkPacketInfo<IBlockData> getChunkPacketInfo(PacketPlayOutMapChunk packetPlayOutMapChunk, Chunk chunk, int chunkSectionSelector) {
/* 26 */     return null;
/*    */   }
/*    */   
/*    */   public void modifyBlocks(PacketPlayOutMapChunk packetPlayOutMapChunk, ChunkPacketInfo<IBlockData> chunkPacketInfo) {
/* 30 */     packetPlayOutMapChunk.setReady(true);
/*    */   }
/*    */   
/*    */   public void onBlockChange(World world, BlockPosition blockPosition, IBlockData newBlockData, IBlockData oldBlockData, int flag) {}
/*    */   
/*    */   public void onPlayerLeftClickBlock(PlayerInteractManager playerInteractManager, BlockPosition blockPosition, EnumDirection enumDirection) {}
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\antixray\ChunkPacketBlockController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
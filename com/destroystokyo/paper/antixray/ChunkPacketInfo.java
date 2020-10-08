/*    */ package com.destroystokyo.paper.antixray;
/*    */ 
/*    */ import net.minecraft.server.v1_16_R2.Chunk;
/*    */ import net.minecraft.server.v1_16_R2.DataPalette;
/*    */ import net.minecraft.server.v1_16_R2.PacketPlayOutMapChunk;
/*    */ 
/*    */ public class ChunkPacketInfo<T>
/*    */ {
/*    */   private final PacketPlayOutMapChunk packetPlayOutMapChunk;
/*    */   private final Chunk chunk;
/*    */   private final int chunkSectionSelector;
/*    */   private byte[] data;
/* 13 */   private final int[] bitsPerObject = new int[16];
/* 14 */   private final Object[] dataPalettes = new Object[16];
/* 15 */   private final int[] dataBitsIndexes = new int[16];
/* 16 */   private final Object[][] predefinedObjects = new Object[16][];
/*    */   
/*    */   public ChunkPacketInfo(PacketPlayOutMapChunk packetPlayOutMapChunk, Chunk chunk, int chunkSectionSelector) {
/* 19 */     this.packetPlayOutMapChunk = packetPlayOutMapChunk;
/* 20 */     this.chunk = chunk;
/* 21 */     this.chunkSectionSelector = chunkSectionSelector;
/*    */   }
/*    */   
/*    */   public PacketPlayOutMapChunk getPacketPlayOutMapChunk() {
/* 25 */     return this.packetPlayOutMapChunk;
/*    */   }
/*    */   
/*    */   public Chunk getChunk() {
/* 29 */     return this.chunk;
/*    */   }
/*    */   
/*    */   public int getChunkSectionSelector() {
/* 33 */     return this.chunkSectionSelector;
/*    */   }
/*    */   
/*    */   public byte[] getData() {
/* 37 */     return this.data;
/*    */   }
/*    */   
/*    */   public void setData(byte[] data) {
/* 41 */     this.data = data;
/*    */   }
/*    */   
/*    */   public int getBitsPerObject(int chunkSectionIndex) {
/* 45 */     return this.bitsPerObject[chunkSectionIndex];
/*    */   }
/*    */   
/*    */   public void setBitsPerObject(int chunkSectionIndex, int bitsPerObject) {
/* 49 */     this.bitsPerObject[chunkSectionIndex] = bitsPerObject;
/*    */   }
/*    */ 
/*    */   
/*    */   public DataPalette<T> getDataPalette(int chunkSectionIndex) {
/* 54 */     return (DataPalette<T>)this.dataPalettes[chunkSectionIndex];
/*    */   }
/*    */   
/*    */   public void setDataPalette(int chunkSectionIndex, DataPalette<T> dataPalette) {
/* 58 */     this.dataPalettes[chunkSectionIndex] = dataPalette;
/*    */   }
/*    */   
/*    */   public int getDataBitsIndex(int chunkSectionIndex) {
/* 62 */     return this.dataBitsIndexes[chunkSectionIndex];
/*    */   }
/*    */   
/*    */   public void setDataBitsIndex(int chunkSectionIndex, int dataBitsIndex) {
/* 66 */     this.dataBitsIndexes[chunkSectionIndex] = dataBitsIndex;
/*    */   }
/*    */ 
/*    */   
/*    */   public T[] getPredefinedObjects(int chunkSectionIndex) {
/* 71 */     return (T[])this.predefinedObjects[chunkSectionIndex];
/*    */   }
/*    */   
/*    */   public void setPredefinedObjects(int chunkSectionIndex, T[] predefinedObjects) {
/* 75 */     this.predefinedObjects[chunkSectionIndex] = (Object[])predefinedObjects;
/*    */   }
/*    */   
/*    */   public boolean isWritten(int chunkSectionIndex) {
/* 79 */     return (this.bitsPerObject[chunkSectionIndex] != 0);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\antixray\ChunkPacketInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
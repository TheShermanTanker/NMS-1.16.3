/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.destroystokyo.paper.antixray.ChunkPacketInfo;
/*     */ import com.google.common.collect.Lists;
/*     */ import io.netty.buffer.ByteBuf;
/*     */ import io.netty.buffer.Unpooled;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ public class PacketPlayOutMapChunk implements Packet<PacketListenerPlayOut> {
/*     */   private int a;
/*     */   private int b;
/*     */   private int c;
/*     */   private NBTTagCompound d;
/*     */   @Nullable
/*     */   private int[] e;
/*     */   
/*  21 */   private byte[] getData() { return this.f; } private byte[] f; private List<NBTTagCompound> g; private boolean h; private volatile boolean ready; private final List<Packet> extraPackets; private List<NBTTagCompound> getTileEntityData() {
/*  22 */     return this.g;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PacketPlayOutMapChunk()
/*     */   {
/*  33 */     this.extraPackets = new ArrayList<>();
/*  34 */     this.ready = true; } private static final int TE_LIMIT = Integer.getInteger("tuinity.excessive-te-limit", 750).intValue();
/*  35 */   private static final int TE_SPLIT_LIMIT = Math.max(4097, Integer.getInteger("tuinity.te-split-limit", 15000).intValue());
/*     */   
/*     */   private boolean mustSplit;
/*     */   
/*     */   public List<Packet> getExtraPackets() {
/*  40 */     return this.extraPackets;
/*     */   } public PacketPlayOutMapChunk(Chunk chunk, int i) {
/*     */     this.extraPackets = new ArrayList<>();
/*  43 */     int chunkSectionBitSet = i;
/*  44 */     ChunkPacketInfo<IBlockData> chunkPacketInfo = chunk.world.chunkPacketBlockController.getChunkPacketInfo(this, chunk, i);
/*  45 */     ChunkCoordIntPair chunkcoordintpair = chunk.getPos();
/*     */     
/*  47 */     this.a = chunkcoordintpair.x;
/*  48 */     this.b = chunkcoordintpair.z;
/*  49 */     this.h = (i == 65535);
/*  50 */     this.d = new NBTTagCompound();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  58 */     this.g = Lists.newArrayList();
/*  59 */     Iterator<Map.Entry> iterator = chunk.getTileEntities().entrySet().iterator();
/*  60 */     int totalTileEntities = 0;
/*     */     
/*  62 */     while (iterator.hasNext()) {
/*  63 */       Map.Entry entry = iterator.next();
/*  64 */       BlockPosition blockposition = (BlockPosition)entry.getKey();
/*  65 */       TileEntity tileentity = (TileEntity)entry.getValue();
/*  66 */       int j = blockposition.getY() >> 4;
/*     */       
/*  68 */       if (f() || (i & 1 << j) != 0) {
/*     */         
/*  70 */         totalTileEntities++;
/*  71 */         if (totalTileEntities > TE_SPLIT_LIMIT) {
/*  72 */           this.mustSplit = true;
/*  73 */           getTileEntityData().clear();
/*  74 */           this.extraPackets.clear();
/*     */           
/*     */           break;
/*     */         } 
/*  78 */         if (totalTileEntities > TE_LIMIT) {
/*  79 */           PacketPlayOutTileEntityData updatePacket = tileentity.getUpdatePacket();
/*  80 */           if (updatePacket != null) {
/*  81 */             this.extraPackets.add(updatePacket);
/*     */             
/*     */             continue;
/*     */           } 
/*     */         } 
/*  86 */         NBTTagCompound nbttagcompound = tileentity.b();
/*  87 */         if (tileentity instanceof TileEntitySkull) TileEntitySkull.sanitizeTileEntityUUID(nbttagcompound);
/*     */         
/*  89 */         this.g.add(nbttagcompound);
/*     */       } 
/*     */     } 
/*     */     
/*  93 */     iterator = (Iterator)chunk.f().iterator();
/*     */     
/*  95 */     while (iterator.hasNext()) {
/*  96 */       Map.Entry entry = iterator.next();
/*  97 */       if (((HeightMap.Type)entry.getKey()).c()) {
/*  98 */         this.d.set(((HeightMap.Type)entry.getKey()).b(), new NBTTagLongArray(((HeightMap)entry.getValue()).a()));
/*     */       }
/*     */     } 
/*     */     
/* 102 */     if (this.h) {
/* 103 */       this.e = chunk.getBiomeIndex().a();
/*     */     }
/*     */     
/* 106 */     this.f = new byte[a(chunk, i)];
/*     */     
/* 108 */     if (chunkPacketInfo != null) {
/* 109 */       chunkPacketInfo.setData(getData());
/*     */     }
/* 111 */     this.c = writeChunk(new PacketDataSerializer(j()), chunk, i, chunkPacketInfo);
/*     */     
/* 113 */     chunk.world.chunkPacketBlockController.modifyBlocks(this, chunkPacketInfo);
/*     */     
/* 115 */     if (this.mustSplit) {
/* 116 */       int chunkSectionBitSetCopy = chunkSectionBitSet;
/* 117 */       for (int a = 0, len = Integer.bitCount(chunkSectionBitSet); a < len; a++) {
/* 118 */         int trailingBit = IntegerUtil.getTrailingBit(chunkSectionBitSetCopy);
/* 119 */         int sectionIndex = Integer.numberOfTrailingZeros(trailingBit);
/* 120 */         chunkSectionBitSetCopy ^= trailingBit;
/*     */         
/* 122 */         if (chunk.getSections()[sectionIndex] != null) {
/* 123 */           this.extraPackets.add(new PacketPlayOutMapChunk(chunk, trailingBit));
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isReady() {
/* 133 */     return this.ready;
/*     */   }
/*     */   
/*     */   public void setReady(boolean ready) {
/* 137 */     this.ready = ready;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(PacketDataSerializer packetdataserializer) throws IOException {
/* 143 */     this.a = packetdataserializer.readInt();
/* 144 */     this.b = packetdataserializer.readInt();
/* 145 */     this.h = packetdataserializer.readBoolean();
/* 146 */     this.c = packetdataserializer.i();
/* 147 */     this.d = packetdataserializer.l();
/* 148 */     if (this.h) {
/* 149 */       this.e = packetdataserializer.c(BiomeStorage.a);
/*     */     }
/*     */     
/* 152 */     int i = packetdataserializer.i();
/*     */     
/* 154 */     if (i > 2097152) {
/* 155 */       throw new RuntimeException("Chunk Packet trying to allocate too much memory on read.");
/*     */     }
/* 157 */     this.f = new byte[i];
/* 158 */     packetdataserializer.readBytes(this.f);
/* 159 */     int j = packetdataserializer.i();
/*     */     
/* 161 */     this.g = Lists.newArrayList();
/*     */     
/* 163 */     for (int k = 0; k < j; k++) {
/* 164 */       this.g.add(packetdataserializer.l());
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void b(PacketDataSerializer packetdataserializer) throws IOException {
/* 172 */     packetdataserializer.writeInt(this.a);
/* 173 */     packetdataserializer.writeInt(this.b);
/* 174 */     packetdataserializer.writeBoolean(this.h);
/* 175 */     packetdataserializer.d(this.c);
/* 176 */     packetdataserializer.a(this.d);
/* 177 */     if (this.e != null) {
/* 178 */       packetdataserializer.a(this.e);
/*     */     }
/*     */     
/* 181 */     packetdataserializer.d(this.f.length);
/* 182 */     packetdataserializer.writeBytes(this.f);
/* 183 */     packetdataserializer.d(this.g.size());
/* 184 */     Iterator<NBTTagCompound> iterator = this.g.iterator();
/*     */     
/* 186 */     while (iterator.hasNext()) {
/* 187 */       NBTTagCompound nbttagcompound = iterator.next();
/*     */       
/* 189 */       packetdataserializer.a(nbttagcompound);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(PacketListenerPlayOut packetlistenerplayout) {
/* 195 */     packetlistenerplayout.a(this);
/*     */   }
/*     */   
/*     */   private ByteBuf j() {
/* 199 */     ByteBuf bytebuf = Unpooled.wrappedBuffer(this.f);
/*     */     
/* 201 */     bytebuf.writerIndex(0);
/* 202 */     return bytebuf;
/*     */   }
/*     */   
/*     */   @Deprecated
/* 206 */   public int writeChunk(PacketDataSerializer packetDataSerializer, Chunk chunk, int chunkSectionSelector) { return a(packetDataSerializer, chunk, chunkSectionSelector); } @Deprecated
/* 207 */   public int a(PacketDataSerializer packetdataserializer, Chunk chunk, int i) { return writeChunk(packetdataserializer, chunk, i, null); } public int writeChunk(PacketDataSerializer packetDataSerializer, Chunk chunk, int chunkSectionSelector, ChunkPacketInfo<IBlockData> chunkPacketInfo) {
/* 208 */     return a(packetDataSerializer, chunk, chunkSectionSelector, chunkPacketInfo);
/*     */   }
/*     */   public int a(PacketDataSerializer packetdataserializer, Chunk chunk, int i, ChunkPacketInfo<IBlockData> chunkPacketInfo) {
/* 211 */     int j = 0;
/* 212 */     ChunkSection[] achunksection = chunk.getSections();
/* 213 */     int k = 0;
/*     */     
/* 215 */     for (int l = achunksection.length; k < l; k++) {
/* 216 */       ChunkSection chunksection = achunksection[k];
/*     */       
/* 218 */       if (!this.mustSplit && chunksection != Chunk.a && (!f() || !chunksection.c()) && (i & 1 << k) != 0) {
/* 219 */         j |= 1 << k;
/* 220 */         chunksection.writeChunkSection(packetdataserializer, chunkPacketInfo);
/*     */       } 
/*     */     } 
/*     */     
/* 224 */     return j;
/*     */   }
/*     */   
/*     */   protected int a(Chunk chunk, int i) {
/* 228 */     int j = 0;
/* 229 */     ChunkSection[] achunksection = chunk.getSections();
/* 230 */     int k = 0;
/*     */     
/* 232 */     for (int l = achunksection.length; k < l; k++) {
/* 233 */       ChunkSection chunksection = achunksection[k];
/*     */       
/* 235 */       if (!this.mustSplit && chunksection != Chunk.a && (!f() || !chunksection.c()) && (i & 1 << k) != 0) {
/* 236 */         j += chunksection.j();
/*     */       }
/*     */     } 
/*     */     
/* 240 */     return j;
/*     */   }
/*     */   
/*     */   public boolean f() {
/* 244 */     return this.h;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketPlayOutMapChunk.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
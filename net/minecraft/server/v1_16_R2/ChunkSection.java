/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.destroystokyo.paper.antixray.ChunkPacketInfo;
/*     */ import com.destroystokyo.paper.util.maplist.IBlockDataList;
/*     */ import java.util.function.Predicate;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ public class ChunkSection {
/*   9 */   public static final DataPalette<IBlockData> GLOBAL_PALETTE = new DataPaletteGlobal<>(Block.REGISTRY_ID, Blocks.AIR.getBlockData());
/*     */   
/*     */   final int yPos;
/*     */   short nonEmptyBlockCount;
/*     */   short tickingBlockCount;
/*     */   private short e;
/*     */   final DataPaletteBlock<IBlockData> blockIds;
/*  16 */   final IBlockDataList tickingList = new IBlockDataList();
/*     */   @Deprecated
/*     */   public ChunkSection(int i) {
/*  19 */     this(i, (IChunkAccess)null, (World)null, true);
/*     */   } public ChunkSection(int i, IChunkAccess chunk, World world, boolean initializeBlocks) {
/*  21 */     this(i, (short)0, (short)0, (short)0, chunk, world, initializeBlocks);
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public ChunkSection(int i, short short0, short short1, short short2) {
/*  26 */     this(i, short0, short1, short2, null, null, true);
/*     */   }
/*     */   public ChunkSection(int i, short short0, short short1, short short2, IChunkAccess chunk, World world, boolean initializeBlocks) {
/*  29 */     this.yPos = i;
/*  30 */     this.nonEmptyBlockCount = short0;
/*  31 */     this.tickingBlockCount = short1;
/*  32 */     this.e = short2;
/*  33 */     this.blockIds = new DataPaletteBlock<>(GLOBAL_PALETTE, Block.REGISTRY_ID, GameProfileSerializer::c, GameProfileSerializer::a, Blocks.AIR.getBlockData(), (world == null) ? null : world.chunkPacketBlockController.getPredefinedBlockData(world, chunk, this, initializeBlocks), initializeBlocks);
/*     */   }
/*     */   
/*     */   public final IBlockData getType(int i, int j, int k) {
/*  37 */     return this.blockIds.a(j << 8 | k << 4 | i);
/*     */   }
/*     */   
/*     */   public Fluid b(int i, int j, int k) {
/*  41 */     return ((IBlockData)this.blockIds.a(i, j, k)).getFluid();
/*     */   }
/*     */   
/*     */   public void a() {
/*  45 */     this.blockIds.a();
/*     */   }
/*     */   
/*     */   public void b() {
/*  49 */     this.blockIds.b();
/*     */   }
/*     */   
/*     */   public IBlockData setType(int i, int j, int k, IBlockData iblockdata) {
/*  53 */     return setType(i, j, k, iblockdata, true);
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData setType(int i, int j, int k, IBlockData iblockdata, boolean flag) {
/*     */     IBlockData iblockdata1;
/*  59 */     if (flag) {
/*  60 */       iblockdata1 = this.blockIds.setBlock(i, j, k, iblockdata);
/*     */     } else {
/*  62 */       iblockdata1 = this.blockIds.b(i, j, k, iblockdata);
/*     */     } 
/*     */     
/*  65 */     Fluid fluid = iblockdata1.getFluid();
/*  66 */     Fluid fluid1 = iblockdata.getFluid();
/*     */     
/*  68 */     if (!iblockdata1.isAir()) {
/*  69 */       this.nonEmptyBlockCount = (short)(this.nonEmptyBlockCount - 1);
/*  70 */       if (iblockdata1.isTicking()) {
/*  71 */         this.tickingBlockCount = (short)(this.tickingBlockCount - 1);
/*     */         
/*  73 */         this.tickingList.remove(i, j, k);
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/*  78 */     if (!fluid.isEmpty()) {
/*  79 */       this.e = (short)(this.e - 1);
/*     */     }
/*     */     
/*  82 */     if (!iblockdata.isAir()) {
/*  83 */       this.nonEmptyBlockCount = (short)(this.nonEmptyBlockCount + 1);
/*  84 */       if (iblockdata.isTicking()) {
/*  85 */         this.tickingBlockCount = (short)(this.tickingBlockCount + 1);
/*     */         
/*  87 */         this.tickingList.add(i, j, k, iblockdata);
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/*  92 */     if (!fluid1.isEmpty()) {
/*  93 */       this.e = (short)(this.e + 1);
/*     */     }
/*     */     
/*  96 */     return iblockdata1;
/*     */   }
/*     */   public final boolean isFullOfAir() {
/*  99 */     return c();
/*     */   } public boolean c() {
/* 101 */     return (this.nonEmptyBlockCount == 0);
/*     */   }
/*     */   public static boolean isEmpty(@Nullable ChunkSection chunksection) {
/* 104 */     return a(chunksection);
/*     */   } public static boolean a(@Nullable ChunkSection chunksection) {
/* 106 */     return (chunksection == Chunk.a || chunksection.c());
/*     */   }
/*     */   
/*     */   public boolean d() {
/* 110 */     return (shouldTick() || f());
/*     */   }
/*     */   
/*     */   public boolean shouldTick() {
/* 114 */     return (this.tickingBlockCount > 0);
/*     */   }
/*     */   
/*     */   public boolean f() {
/* 118 */     return (this.e > 0);
/*     */   }
/*     */   
/*     */   public int getYPosition() {
/* 122 */     return this.yPos;
/*     */   }
/*     */ 
/*     */   
/*     */   public void recalcBlockCounts() {
/* 127 */     this.tickingList.clear();
/*     */     
/* 129 */     this.nonEmptyBlockCount = 0;
/* 130 */     this.tickingBlockCount = 0;
/* 131 */     this.e = 0;
/* 132 */     this.blockIds.forEachLocation((iblockdata, location) -> {
/*     */           Fluid fluid = iblockdata.getFluid();
/*     */           if (!iblockdata.isAir()) {
/*     */             this.nonEmptyBlockCount = (short)(this.nonEmptyBlockCount + 1);
/*     */             if (iblockdata.isTicking()) {
/*     */               this.tickingBlockCount = (short)(this.tickingBlockCount + 1);
/*     */               this.tickingList.add(location, iblockdata);
/*     */             } 
/*     */           } 
/*     */           if (!fluid.isEmpty()) {
/*     */             this.nonEmptyBlockCount = (short)(this.nonEmptyBlockCount + 1);
/*     */             if (fluid.f()) {
/*     */               this.e = (short)(this.e + 1);
/*     */             }
/*     */           } 
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DataPaletteBlock<IBlockData> getBlocks() {
/* 156 */     return this.blockIds;
/*     */   }
/*     */   
/*     */   @Deprecated
/* 160 */   public final void writeChunkSection(PacketDataSerializer packetDataSerializer) { b(packetDataSerializer); } @Deprecated
/* 161 */   public final void b(PacketDataSerializer packetdataserializer) { writeChunkSection(packetdataserializer, null); } public final void writeChunkSection(PacketDataSerializer packetDataSerializer, ChunkPacketInfo<IBlockData> chunkPacketInfo) {
/* 162 */     b(packetDataSerializer, chunkPacketInfo);
/*     */   }
/*     */   public void b(PacketDataSerializer packetdataserializer, ChunkPacketInfo<IBlockData> chunkPacketInfo) {
/* 165 */     packetdataserializer.writeShort(this.nonEmptyBlockCount);
/* 166 */     this.blockIds.writeDataPaletteBlock(packetdataserializer, chunkPacketInfo, this.yPos >> 4);
/*     */   }
/*     */   
/*     */   public int j() {
/* 170 */     return 2 + this.blockIds.c();
/*     */   }
/*     */   
/*     */   public boolean a(Predicate<IBlockData> predicate) {
/* 174 */     return this.blockIds.contains(predicate);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ChunkSection.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
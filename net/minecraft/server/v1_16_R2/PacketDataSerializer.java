/*      */ package net.minecraft.server.v1_16_R2;
/*      */ 
/*      */ import com.mojang.serialization.Codec;
/*      */ import com.mojang.serialization.DataResult;
/*      */ import io.netty.buffer.ByteBuf;
/*      */ import io.netty.buffer.ByteBufAllocator;
/*      */ import io.netty.buffer.ByteBufInputStream;
/*      */ import io.netty.buffer.ByteBufOutputStream;
/*      */ import io.netty.handler.codec.DecoderException;
/*      */ import io.netty.handler.codec.EncoderException;
/*      */ import io.netty.util.ByteProcessor;
/*      */ import io.netty.util.ReferenceCounted;
/*      */ import java.io.DataInput;
/*      */ import java.io.DataOutput;
/*      */ import java.io.IOException;
/*      */ import java.io.InputStream;
/*      */ import java.io.OutputStream;
/*      */ import java.nio.ByteBuffer;
/*      */ import java.nio.ByteOrder;
/*      */ import java.nio.channels.FileChannel;
/*      */ import java.nio.channels.GatheringByteChannel;
/*      */ import java.nio.channels.ScatteringByteChannel;
/*      */ import java.nio.charset.Charset;
/*      */ import java.nio.charset.StandardCharsets;
/*      */ import java.util.Date;
/*      */ import java.util.UUID;
/*      */ import javax.annotation.Nullable;
/*      */ 
/*      */ 
/*      */ public class PacketDataSerializer
/*      */   extends ByteBuf
/*      */ {
/*      */   private final ByteBuf a;
/*      */   
/*      */   public PacketDataSerializer(ByteBuf bytebuf) {
/*   36 */     this.a = bytebuf;
/*      */   }
/*      */   public static int countBytes(int i) {
/*   39 */     return a(i);
/*      */   } public static int a(int i) {
/*   41 */     for (int j = 1; j < 5; j++) {
/*   42 */       if ((i & -1 << j * 7) == 0) {
/*   43 */         return j;
/*      */       }
/*      */     } 
/*      */     
/*   47 */     return 5;
/*      */   }
/*      */   
/*      */   public <T> T a(Codec<T> codec) throws IOException {
/*   51 */     NBTTagCompound nbttagcompound = m();
/*   52 */     DataResult<T> dataresult = codec.parse(DynamicOpsNBT.a, nbttagcompound);
/*      */     
/*   54 */     if (dataresult.error().isPresent()) {
/*   55 */       throw new IOException("Failed to decode: " + ((DataResult.PartialResult)dataresult.error().get()).message() + " " + nbttagcompound);
/*      */     }
/*   57 */     return dataresult.result().get();
/*      */   }
/*      */ 
/*      */   
/*      */   public <T> void a(Codec<T> codec, T t0) throws IOException {
/*   62 */     DataResult<NBTBase> dataresult = codec.encodeStart(DynamicOpsNBT.a, t0);
/*      */     
/*   64 */     if (dataresult.error().isPresent()) {
/*   65 */       throw new IOException("Failed to encode: " + ((DataResult.PartialResult)dataresult.error().get()).message() + " " + t0);
/*      */     }
/*   67 */     a(dataresult.result().get());
/*      */   }
/*      */ 
/*      */   
/*      */   public PacketDataSerializer a(byte[] abyte) {
/*   72 */     d(abyte.length);
/*   73 */     writeBytes(abyte);
/*   74 */     return this;
/*      */   }
/*      */   
/*      */   public byte[] a() {
/*   78 */     return b(readableBytes());
/*      */   }
/*      */   
/*      */   public byte[] b(int i) {
/*   82 */     int j = i();
/*      */     
/*   84 */     if (j > i) {
/*   85 */       throw new DecoderException("ByteArray with size " + j + " is bigger than allowed " + i);
/*      */     }
/*   87 */     byte[] abyte = new byte[j];
/*      */     
/*   89 */     readBytes(abyte);
/*   90 */     return abyte;
/*      */   }
/*      */ 
/*      */   
/*      */   public PacketDataSerializer a(int[] aint) {
/*   95 */     d(aint.length);
/*   96 */     int[] aint1 = aint;
/*   97 */     int i = aint.length;
/*      */     
/*   99 */     for (int j = 0; j < i; j++) {
/*  100 */       int k = aint1[j];
/*      */       
/*  102 */       d(k);
/*      */     } 
/*      */     
/*  105 */     return this;
/*      */   }
/*      */   
/*      */   public int[] b() {
/*  109 */     return c(readableBytes());
/*      */   }
/*      */   
/*      */   public int[] c(int i) {
/*  113 */     int j = i();
/*      */     
/*  115 */     if (j > i) {
/*  116 */       throw new DecoderException("VarIntArray with size " + j + " is bigger than allowed " + i);
/*      */     }
/*  118 */     int[] aint = new int[j];
/*      */     
/*  120 */     for (int k = 0; k < aint.length; k++) {
/*  121 */       aint[k] = i();
/*      */     }
/*      */     
/*  124 */     return aint;
/*      */   }
/*      */ 
/*      */   
/*      */   public PacketDataSerializer a(long[] along) {
/*  129 */     d(along.length);
/*  130 */     long[] along1 = along;
/*  131 */     int i = along.length;
/*      */     
/*  133 */     for (int j = 0; j < i; j++) {
/*  134 */       long k = along1[j];
/*      */       
/*  136 */       writeLong(k);
/*      */     } 
/*      */     
/*  139 */     return this;
/*      */   }
/*      */   
/*      */   public BlockPosition e() {
/*  143 */     return BlockPosition.fromLong(readLong());
/*      */   }
/*      */   
/*      */   public PacketDataSerializer a(BlockPosition blockposition) {
/*  147 */     writeLong(blockposition.asLong());
/*  148 */     return this;
/*      */   }
/*      */   
/*      */   public IChatBaseComponent h() {
/*  152 */     return IChatBaseComponent.ChatSerializer.a(e(262144));
/*      */   }
/*      */   
/*      */   public PacketDataSerializer a(IChatBaseComponent ichatbasecomponent) {
/*  156 */     return a(IChatBaseComponent.ChatSerializer.a(ichatbasecomponent), 262144);
/*      */   }
/*      */   
/*      */   public <T extends Enum<T>> T a(Class<T> oclass) {
/*  160 */     return (T)((Enum[])oclass.getEnumConstants())[i()];
/*      */   }
/*      */   
/*      */   public PacketDataSerializer a(Enum<?> oenum) {
/*  164 */     return d(oenum.ordinal());
/*      */   }
/*      */   
/*  167 */   public int readVarInt() { return i(); } public int i() {
/*      */     byte b0;
/*  169 */     int i = 0;
/*  170 */     int j = 0;
/*      */ 
/*      */ 
/*      */     
/*      */     do {
/*  175 */       b0 = readByte();
/*  176 */       i |= (b0 & Byte.MAX_VALUE) << j++ * 7;
/*  177 */       if (j > 5) {
/*  178 */         throw new RuntimeException("VarInt too big");
/*      */       }
/*  180 */     } while ((b0 & 0x80) == 128);
/*      */     
/*  182 */     return i;
/*      */   }
/*      */   public long j() {
/*      */     byte b0;
/*  186 */     long i = 0L;
/*  187 */     int j = 0;
/*      */ 
/*      */ 
/*      */     
/*      */     do {
/*  192 */       b0 = readByte();
/*  193 */       i |= (b0 & Byte.MAX_VALUE) << j++ * 7;
/*  194 */       if (j > 10) {
/*  195 */         throw new RuntimeException("VarLong too big");
/*      */       }
/*  197 */     } while ((b0 & 0x80) == 128);
/*      */     
/*  199 */     return i;
/*      */   }
/*      */   
/*      */   public PacketDataSerializer a(UUID uuid) {
/*  203 */     writeLong(uuid.getMostSignificantBits());
/*  204 */     writeLong(uuid.getLeastSignificantBits());
/*  205 */     return this;
/*      */   }
/*      */   public UUID readUUID() {
/*  208 */     return k();
/*      */   } public UUID k() {
/*  210 */     return new UUID(readLong(), readLong());
/*      */   }
/*      */   
/*      */   public PacketDataSerializer d(int i) {
/*  214 */     while ((i & 0xFFFFFF80) != 0) {
/*  215 */       writeByte(i & 0x7F | 0x80);
/*  216 */       i >>>= 7;
/*      */     } 
/*      */     
/*  219 */     writeByte(i);
/*  220 */     return this;
/*      */   }
/*      */   
/*      */   public PacketDataSerializer b(long i) {
/*  224 */     while ((i & 0xFFFFFFFFFFFFFF80L) != 0L) {
/*  225 */       writeByte((int)(i & 0x7FL) | 0x80);
/*  226 */       i >>>= 7L;
/*      */     } 
/*      */     
/*  229 */     writeByte((int)i);
/*  230 */     return this;
/*      */   }
/*      */   
/*      */   public PacketDataSerializer a(@Nullable NBTTagCompound nbttagcompound) {
/*  234 */     if (nbttagcompound == null) {
/*  235 */       writeByte(0);
/*      */     } else {
/*      */       try {
/*  238 */         NBTCompressedStreamTools.a(nbttagcompound, (DataOutput)new ByteBufOutputStream(this));
/*  239 */       } catch (Exception ioexception) {
/*  240 */         throw new EncoderException(ioexception);
/*      */       } 
/*      */     } 
/*      */     
/*  244 */     return this;
/*      */   }
/*      */   
/*      */   @Nullable
/*      */   public NBTTagCompound l() {
/*  249 */     return a(new NBTReadLimiter(2097152L));
/*      */   }
/*      */   
/*      */   @Nullable
/*      */   public NBTTagCompound m() {
/*  254 */     return a(NBTReadLimiter.a);
/*      */   }
/*      */   
/*      */   @Nullable
/*      */   public NBTTagCompound a(NBTReadLimiter nbtreadlimiter) {
/*  259 */     int i = readerIndex();
/*  260 */     byte b0 = readByte();
/*      */     
/*  262 */     if (b0 == 0) {
/*  263 */       return null;
/*      */     }
/*  265 */     readerIndex(i);
/*      */     
/*      */     try {
/*  268 */       return NBTCompressedStreamTools.a((DataInput)new ByteBufInputStream(this), nbtreadlimiter);
/*  269 */     } catch (IOException ioexception) {
/*  270 */       throw new EncoderException(ioexception);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public PacketDataSerializer a(ItemStack itemstack) {
/*  276 */     if (itemstack.isEmpty() || itemstack.getItem() == null) {
/*  277 */       writeBoolean(false);
/*      */     } else {
/*  279 */       writeBoolean(true);
/*  280 */       Item item = itemstack.getItem();
/*      */       
/*  282 */       d(Item.getId(item));
/*  283 */       writeByte(itemstack.getCount());
/*  284 */       NBTTagCompound nbttagcompound = null;
/*      */       
/*  286 */       if (item.usesDurability() || item.n()) {
/*      */         
/*  288 */         itemstack = itemstack.cloneItemStack();
/*      */ 
/*      */         
/*  291 */         nbttagcompound = itemstack.getTag();
/*      */         
/*  293 */         if (nbttagcompound != null && nbttagcompound.hasKeyOfType("SkullOwner", 10)) {
/*  294 */           NBTTagCompound owner = nbttagcompound.getCompound("SkullOwner");
/*  295 */           if (owner.hasUUID("Id")) {
/*  296 */             nbttagcompound.setUUID("SkullOwnerOrig", owner.getUUID("Id"));
/*  297 */             TileEntitySkull.sanitizeUUID(owner);
/*      */           } 
/*      */         } 
/*      */       } 
/*      */ 
/*      */       
/*  303 */       a(nbttagcompound);
/*      */     } 
/*      */     
/*  306 */     return this;
/*      */   }
/*      */   
/*      */   public ItemStack n() {
/*  310 */     if (!readBoolean()) {
/*  311 */       return ItemStack.b;
/*      */     }
/*  313 */     int i = i();
/*  314 */     byte b0 = readByte();
/*  315 */     ItemStack itemstack = new ItemStack(Item.getById(i), b0);
/*      */     
/*  317 */     itemstack.setTag(l());
/*      */     
/*  319 */     if (itemstack.getTag() != null)
/*      */     {
/*  321 */       if (itemstack.tag.hasKey("SkullOwnerOrig")) {
/*  322 */         NBTTagCompound owner = itemstack.tag.getCompound("SkullOwner");
/*  323 */         if (itemstack.tag.hasKey("SkullOwnerOrig")) {
/*  324 */           owner.map.put("Id", itemstack.tag.map.get("SkullOwnerOrig"));
/*  325 */           itemstack.tag.remove("SkullOwnerOrig");
/*      */         } 
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  332 */     return itemstack;
/*      */   }
/*      */   
/*      */   public String readUTF(int maxLength) {
/*  336 */     return e(maxLength);
/*      */   } public String e(int i) {
/*  338 */     int j = i();
/*      */     
/*  340 */     if (j > i * 4)
/*  341 */       throw new DecoderException("The received encoded string buffer length is longer than maximum allowed (" + j + " > " + (i * 4) + ")"); 
/*  342 */     if (j < 0) {
/*  343 */       throw new DecoderException("The received encoded string buffer length is less than zero! Weird string!");
/*      */     }
/*  345 */     String s = toString(readerIndex(), j, StandardCharsets.UTF_8);
/*      */     
/*  347 */     readerIndex(readerIndex() + j);
/*  348 */     if (s.length() > i) {
/*  349 */       throw new DecoderException("The received string length is longer than maximum allowed (" + j + " > " + i + ")");
/*      */     }
/*  351 */     return s;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public PacketDataSerializer a(String s) {
/*  357 */     return a(s, 32767);
/*      */   }
/*      */   
/*      */   public PacketDataSerializer a(String s, int i) {
/*  361 */     byte[] abyte = s.getBytes(StandardCharsets.UTF_8);
/*      */     
/*  363 */     if (abyte.length > i) {
/*  364 */       throw new EncoderException("String too big (was " + abyte.length + " bytes encoded, max " + i + ")");
/*      */     }
/*  366 */     d(abyte.length);
/*  367 */     writeBytes(abyte);
/*  368 */     return this;
/*      */   }
/*      */ 
/*      */   
/*      */   public MinecraftKey p() {
/*  373 */     return new MinecraftKey(e(32767));
/*      */   }
/*      */   
/*      */   public PacketDataSerializer a(MinecraftKey minecraftkey) {
/*  377 */     a(minecraftkey.toString());
/*  378 */     return this;
/*      */   }
/*      */   
/*      */   public Date q() {
/*  382 */     return new Date(readLong());
/*      */   }
/*      */   
/*      */   public PacketDataSerializer a(Date date) {
/*  386 */     writeLong(date.getTime());
/*  387 */     return this;
/*      */   }
/*      */   
/*      */   public MovingObjectPositionBlock r() {
/*  391 */     BlockPosition blockposition = e();
/*  392 */     EnumDirection enumdirection = a(EnumDirection.class);
/*  393 */     float f = readFloat();
/*  394 */     float f1 = readFloat();
/*  395 */     float f2 = readFloat();
/*  396 */     boolean flag = readBoolean();
/*      */     
/*  398 */     return new MovingObjectPositionBlock(new Vec3D(blockposition.getX() + f, blockposition.getY() + f1, blockposition.getZ() + f2), enumdirection, blockposition, flag);
/*      */   }
/*      */   
/*      */   public void a(MovingObjectPositionBlock movingobjectpositionblock) {
/*  402 */     BlockPosition blockposition = movingobjectpositionblock.getBlockPosition();
/*      */     
/*  404 */     a(blockposition);
/*  405 */     a(movingobjectpositionblock.getDirection());
/*  406 */     Vec3D vec3d = movingobjectpositionblock.getPos();
/*      */     
/*  408 */     writeFloat((float)(vec3d.x - blockposition.getX()));
/*  409 */     writeFloat((float)(vec3d.y - blockposition.getY()));
/*  410 */     writeFloat((float)(vec3d.z - blockposition.getZ()));
/*  411 */     writeBoolean(movingobjectpositionblock.d());
/*      */   }
/*      */   
/*      */   public int capacity() {
/*  415 */     return this.a.capacity();
/*      */   }
/*      */   
/*      */   public ByteBuf capacity(int i) {
/*  419 */     return this.a.capacity(i);
/*      */   }
/*      */   
/*      */   public int maxCapacity() {
/*  423 */     return this.a.maxCapacity();
/*      */   }
/*      */   
/*      */   public ByteBufAllocator alloc() {
/*  427 */     return this.a.alloc();
/*      */   }
/*      */   
/*      */   public ByteOrder order() {
/*  431 */     return this.a.order();
/*      */   }
/*      */   
/*      */   public ByteBuf order(ByteOrder byteorder) {
/*  435 */     return this.a.order(byteorder);
/*      */   }
/*      */   
/*      */   public ByteBuf unwrap() {
/*  439 */     return this.a.unwrap();
/*      */   }
/*      */   
/*      */   public boolean isDirect() {
/*  443 */     return this.a.isDirect();
/*      */   }
/*      */   
/*      */   public boolean isReadOnly() {
/*  447 */     return this.a.isReadOnly();
/*      */   }
/*      */   
/*      */   public ByteBuf asReadOnly() {
/*  451 */     return this.a.asReadOnly();
/*      */   }
/*      */   
/*      */   public int readerIndex() {
/*  455 */     return this.a.readerIndex();
/*      */   }
/*      */   
/*      */   public ByteBuf readerIndex(int i) {
/*  459 */     return this.a.readerIndex(i);
/*      */   }
/*      */   
/*      */   public int writerIndex() {
/*  463 */     return this.a.writerIndex();
/*      */   }
/*      */   
/*      */   public ByteBuf writerIndex(int i) {
/*  467 */     return this.a.writerIndex(i);
/*      */   }
/*      */   
/*      */   public ByteBuf setIndex(int i, int j) {
/*  471 */     return this.a.setIndex(i, j);
/*      */   }
/*      */   
/*      */   public int readableBytes() {
/*  475 */     return this.a.readableBytes();
/*      */   }
/*      */   
/*      */   public int writableBytes() {
/*  479 */     return this.a.writableBytes();
/*      */   }
/*      */   
/*      */   public int maxWritableBytes() {
/*  483 */     return this.a.maxWritableBytes();
/*      */   }
/*      */   
/*      */   public boolean isReadable() {
/*  487 */     return this.a.isReadable();
/*      */   }
/*      */   
/*      */   public boolean isReadable(int i) {
/*  491 */     return this.a.isReadable(i);
/*      */   }
/*      */   
/*      */   public boolean isWritable() {
/*  495 */     return this.a.isWritable();
/*      */   }
/*      */   
/*      */   public boolean isWritable(int i) {
/*  499 */     return this.a.isWritable(i);
/*      */   }
/*      */   
/*      */   public ByteBuf clear() {
/*  503 */     return this.a.clear();
/*      */   }
/*      */   
/*      */   public ByteBuf markReaderIndex() {
/*  507 */     return this.a.markReaderIndex();
/*      */   }
/*      */   
/*      */   public ByteBuf resetReaderIndex() {
/*  511 */     return this.a.resetReaderIndex();
/*      */   }
/*      */   
/*      */   public ByteBuf markWriterIndex() {
/*  515 */     return this.a.markWriterIndex();
/*      */   }
/*      */   
/*      */   public ByteBuf resetWriterIndex() {
/*  519 */     return this.a.resetWriterIndex();
/*      */   }
/*      */   
/*      */   public ByteBuf discardReadBytes() {
/*  523 */     return this.a.discardReadBytes();
/*      */   }
/*      */   
/*      */   public ByteBuf discardSomeReadBytes() {
/*  527 */     return this.a.discardSomeReadBytes();
/*      */   }
/*      */   
/*      */   public ByteBuf ensureWritable(int i) {
/*  531 */     return this.a.ensureWritable(i);
/*      */   }
/*      */   
/*      */   public int ensureWritable(int i, boolean flag) {
/*  535 */     return this.a.ensureWritable(i, flag);
/*      */   }
/*      */   
/*      */   public boolean getBoolean(int i) {
/*  539 */     return this.a.getBoolean(i);
/*      */   }
/*      */   
/*      */   public byte getByte(int i) {
/*  543 */     return this.a.getByte(i);
/*      */   }
/*      */   
/*      */   public short getUnsignedByte(int i) {
/*  547 */     return this.a.getUnsignedByte(i);
/*      */   }
/*      */   
/*      */   public short getShort(int i) {
/*  551 */     return this.a.getShort(i);
/*      */   }
/*      */   
/*      */   public short getShortLE(int i) {
/*  555 */     return this.a.getShortLE(i);
/*      */   }
/*      */   
/*      */   public int getUnsignedShort(int i) {
/*  559 */     return this.a.getUnsignedShort(i);
/*      */   }
/*      */   
/*      */   public int getUnsignedShortLE(int i) {
/*  563 */     return this.a.getUnsignedShortLE(i);
/*      */   }
/*      */   
/*      */   public int getMedium(int i) {
/*  567 */     return this.a.getMedium(i);
/*      */   }
/*      */   
/*      */   public int getMediumLE(int i) {
/*  571 */     return this.a.getMediumLE(i);
/*      */   }
/*      */   
/*      */   public int getUnsignedMedium(int i) {
/*  575 */     return this.a.getUnsignedMedium(i);
/*      */   }
/*      */   
/*      */   public int getUnsignedMediumLE(int i) {
/*  579 */     return this.a.getUnsignedMediumLE(i);
/*      */   }
/*      */   
/*      */   public int getInt(int i) {
/*  583 */     return this.a.getInt(i);
/*      */   }
/*      */   
/*      */   public int getIntLE(int i) {
/*  587 */     return this.a.getIntLE(i);
/*      */   }
/*      */   
/*      */   public long getUnsignedInt(int i) {
/*  591 */     return this.a.getUnsignedInt(i);
/*      */   }
/*      */   
/*      */   public long getUnsignedIntLE(int i) {
/*  595 */     return this.a.getUnsignedIntLE(i);
/*      */   }
/*      */   
/*      */   public long getLong(int i) {
/*  599 */     return this.a.getLong(i);
/*      */   }
/*      */   
/*      */   public long getLongLE(int i) {
/*  603 */     return this.a.getLongLE(i);
/*      */   }
/*      */   
/*      */   public char getChar(int i) {
/*  607 */     return this.a.getChar(i);
/*      */   }
/*      */   
/*      */   public float getFloat(int i) {
/*  611 */     return this.a.getFloat(i);
/*      */   }
/*      */   
/*      */   public double getDouble(int i) {
/*  615 */     return this.a.getDouble(i);
/*      */   }
/*      */   
/*      */   public ByteBuf getBytes(int i, ByteBuf bytebuf) {
/*  619 */     return this.a.getBytes(i, bytebuf);
/*      */   }
/*      */   
/*      */   public ByteBuf getBytes(int i, ByteBuf bytebuf, int j) {
/*  623 */     return this.a.getBytes(i, bytebuf, j);
/*      */   }
/*      */   
/*      */   public ByteBuf getBytes(int i, ByteBuf bytebuf, int j, int k) {
/*  627 */     return this.a.getBytes(i, bytebuf, j, k);
/*      */   }
/*      */   
/*      */   public ByteBuf getBytes(int i, byte[] abyte) {
/*  631 */     return this.a.getBytes(i, abyte);
/*      */   }
/*      */   
/*      */   public ByteBuf getBytes(int i, byte[] abyte, int j, int k) {
/*  635 */     return this.a.getBytes(i, abyte, j, k);
/*      */   }
/*      */   
/*      */   public ByteBuf getBytes(int i, ByteBuffer bytebuffer) {
/*  639 */     return this.a.getBytes(i, bytebuffer);
/*      */   }
/*      */   
/*      */   public ByteBuf getBytes(int i, OutputStream outputstream, int j) throws IOException {
/*  643 */     return this.a.getBytes(i, outputstream, j);
/*      */   }
/*      */   
/*      */   public int getBytes(int i, GatheringByteChannel gatheringbytechannel, int j) throws IOException {
/*  647 */     return this.a.getBytes(i, gatheringbytechannel, j);
/*      */   }
/*      */   
/*      */   public int getBytes(int i, FileChannel filechannel, long j, int k) throws IOException {
/*  651 */     return this.a.getBytes(i, filechannel, j, k);
/*      */   }
/*      */   
/*      */   public CharSequence getCharSequence(int i, int j, Charset charset) {
/*  655 */     return this.a.getCharSequence(i, j, charset);
/*      */   }
/*      */   
/*      */   public ByteBuf setBoolean(int i, boolean flag) {
/*  659 */     return this.a.setBoolean(i, flag);
/*      */   }
/*      */   
/*      */   public ByteBuf setByte(int i, int j) {
/*  663 */     return this.a.setByte(i, j);
/*      */   }
/*      */   
/*      */   public ByteBuf setShort(int i, int j) {
/*  667 */     return this.a.setShort(i, j);
/*      */   }
/*      */   
/*      */   public ByteBuf setShortLE(int i, int j) {
/*  671 */     return this.a.setShortLE(i, j);
/*      */   }
/*      */   
/*      */   public ByteBuf setMedium(int i, int j) {
/*  675 */     return this.a.setMedium(i, j);
/*      */   }
/*      */   
/*      */   public ByteBuf setMediumLE(int i, int j) {
/*  679 */     return this.a.setMediumLE(i, j);
/*      */   }
/*      */   
/*      */   public ByteBuf setInt(int i, int j) {
/*  683 */     return this.a.setInt(i, j);
/*      */   }
/*      */   
/*      */   public ByteBuf setIntLE(int i, int j) {
/*  687 */     return this.a.setIntLE(i, j);
/*      */   }
/*      */   
/*      */   public ByteBuf setLong(int i, long j) {
/*  691 */     return this.a.setLong(i, j);
/*      */   }
/*      */   
/*      */   public ByteBuf setLongLE(int i, long j) {
/*  695 */     return this.a.setLongLE(i, j);
/*      */   }
/*      */   
/*      */   public ByteBuf setChar(int i, int j) {
/*  699 */     return this.a.setChar(i, j);
/*      */   }
/*      */   
/*      */   public ByteBuf setFloat(int i, float f) {
/*  703 */     return this.a.setFloat(i, f);
/*      */   }
/*      */   
/*      */   public ByteBuf setDouble(int i, double d0) {
/*  707 */     return this.a.setDouble(i, d0);
/*      */   }
/*      */   
/*      */   public ByteBuf setBytes(int i, ByteBuf bytebuf) {
/*  711 */     return this.a.setBytes(i, bytebuf);
/*      */   }
/*      */   
/*      */   public ByteBuf setBytes(int i, ByteBuf bytebuf, int j) {
/*  715 */     return this.a.setBytes(i, bytebuf, j);
/*      */   }
/*      */   
/*      */   public ByteBuf setBytes(int i, ByteBuf bytebuf, int j, int k) {
/*  719 */     return this.a.setBytes(i, bytebuf, j, k);
/*      */   }
/*      */   
/*      */   public ByteBuf setBytes(int i, byte[] abyte) {
/*  723 */     return this.a.setBytes(i, abyte);
/*      */   }
/*      */   
/*      */   public ByteBuf setBytes(int i, byte[] abyte, int j, int k) {
/*  727 */     return this.a.setBytes(i, abyte, j, k);
/*      */   }
/*      */   
/*      */   public ByteBuf setBytes(int i, ByteBuffer bytebuffer) {
/*  731 */     return this.a.setBytes(i, bytebuffer);
/*      */   }
/*      */   
/*      */   public int setBytes(int i, InputStream inputstream, int j) throws IOException {
/*  735 */     return this.a.setBytes(i, inputstream, j);
/*      */   }
/*      */   
/*      */   public int setBytes(int i, ScatteringByteChannel scatteringbytechannel, int j) throws IOException {
/*  739 */     return this.a.setBytes(i, scatteringbytechannel, j);
/*      */   }
/*      */   
/*      */   public int setBytes(int i, FileChannel filechannel, long j, int k) throws IOException {
/*  743 */     return this.a.setBytes(i, filechannel, j, k);
/*      */   }
/*      */   
/*      */   public ByteBuf setZero(int i, int j) {
/*  747 */     return this.a.setZero(i, j);
/*      */   }
/*      */   
/*      */   public int setCharSequence(int i, CharSequence charsequence, Charset charset) {
/*  751 */     return this.a.setCharSequence(i, charsequence, charset);
/*      */   }
/*      */   
/*      */   public boolean readBoolean() {
/*  755 */     return this.a.readBoolean();
/*      */   }
/*      */   
/*      */   public byte readByte() {
/*  759 */     return this.a.readByte();
/*      */   }
/*      */   
/*      */   public short readUnsignedByte() {
/*  763 */     return this.a.readUnsignedByte();
/*      */   }
/*      */   
/*      */   public short readShort() {
/*  767 */     return this.a.readShort();
/*      */   }
/*      */   
/*      */   public short readShortLE() {
/*  771 */     return this.a.readShortLE();
/*      */   }
/*      */   
/*      */   public int readUnsignedShort() {
/*  775 */     return this.a.readUnsignedShort();
/*      */   }
/*      */   
/*      */   public int readUnsignedShortLE() {
/*  779 */     return this.a.readUnsignedShortLE();
/*      */   }
/*      */   
/*      */   public int readMedium() {
/*  783 */     return this.a.readMedium();
/*      */   }
/*      */   
/*      */   public int readMediumLE() {
/*  787 */     return this.a.readMediumLE();
/*      */   }
/*      */   
/*      */   public int readUnsignedMedium() {
/*  791 */     return this.a.readUnsignedMedium();
/*      */   }
/*      */   
/*      */   public int readUnsignedMediumLE() {
/*  795 */     return this.a.readUnsignedMediumLE();
/*      */   }
/*      */   
/*      */   public int readInt() {
/*  799 */     return this.a.readInt();
/*      */   }
/*      */   
/*      */   public int readIntLE() {
/*  803 */     return this.a.readIntLE();
/*      */   }
/*      */   
/*      */   public long readUnsignedInt() {
/*  807 */     return this.a.readUnsignedInt();
/*      */   }
/*      */   
/*      */   public long readUnsignedIntLE() {
/*  811 */     return this.a.readUnsignedIntLE();
/*      */   }
/*      */   
/*      */   public long readLong() {
/*  815 */     return this.a.readLong();
/*      */   }
/*      */   
/*      */   public long readLongLE() {
/*  819 */     return this.a.readLongLE();
/*      */   }
/*      */   
/*      */   public char readChar() {
/*  823 */     return this.a.readChar();
/*      */   }
/*      */   
/*      */   public float readFloat() {
/*  827 */     return this.a.readFloat();
/*      */   }
/*      */   
/*      */   public double readDouble() {
/*  831 */     return this.a.readDouble();
/*      */   }
/*      */   
/*      */   public ByteBuf readBytes(int i) {
/*  835 */     return this.a.readBytes(i);
/*      */   }
/*      */   
/*      */   public ByteBuf readSlice(int i) {
/*  839 */     return this.a.readSlice(i);
/*      */   }
/*      */   
/*      */   public ByteBuf readRetainedSlice(int i) {
/*  843 */     return this.a.readRetainedSlice(i);
/*      */   }
/*      */   
/*      */   public ByteBuf readBytes(ByteBuf bytebuf) {
/*  847 */     return this.a.readBytes(bytebuf);
/*      */   }
/*      */   
/*      */   public ByteBuf readBytes(ByteBuf bytebuf, int i) {
/*  851 */     return this.a.readBytes(bytebuf, i);
/*      */   }
/*      */   
/*      */   public ByteBuf readBytes(ByteBuf bytebuf, int i, int j) {
/*  855 */     return this.a.readBytes(bytebuf, i, j);
/*      */   }
/*      */   
/*      */   public ByteBuf readBytes(byte[] abyte) {
/*  859 */     return this.a.readBytes(abyte);
/*      */   }
/*      */   
/*      */   public ByteBuf readBytes(byte[] abyte, int i, int j) {
/*  863 */     return this.a.readBytes(abyte, i, j);
/*      */   }
/*      */   
/*      */   public ByteBuf readBytes(ByteBuffer bytebuffer) {
/*  867 */     return this.a.readBytes(bytebuffer);
/*      */   }
/*      */   
/*      */   public ByteBuf readBytes(OutputStream outputstream, int i) throws IOException {
/*  871 */     return this.a.readBytes(outputstream, i);
/*      */   }
/*      */   
/*      */   public int readBytes(GatheringByteChannel gatheringbytechannel, int i) throws IOException {
/*  875 */     return this.a.readBytes(gatheringbytechannel, i);
/*      */   }
/*      */   
/*      */   public CharSequence readCharSequence(int i, Charset charset) {
/*  879 */     return this.a.readCharSequence(i, charset);
/*      */   }
/*      */   
/*      */   public int readBytes(FileChannel filechannel, long i, int j) throws IOException {
/*  883 */     return this.a.readBytes(filechannel, i, j);
/*      */   }
/*      */   
/*      */   public ByteBuf skipBytes(int i) {
/*  887 */     return this.a.skipBytes(i);
/*      */   }
/*      */   
/*      */   public ByteBuf writeBoolean(boolean flag) {
/*  891 */     return this.a.writeBoolean(flag);
/*      */   }
/*      */   
/*      */   public ByteBuf writeByte(int i) {
/*  895 */     return this.a.writeByte(i);
/*      */   }
/*      */   
/*      */   public ByteBuf writeShort(int i) {
/*  899 */     return this.a.writeShort(i);
/*      */   }
/*      */   
/*      */   public ByteBuf writeShortLE(int i) {
/*  903 */     return this.a.writeShortLE(i);
/*      */   }
/*      */   
/*      */   public ByteBuf writeMedium(int i) {
/*  907 */     return this.a.writeMedium(i);
/*      */   }
/*      */   
/*      */   public ByteBuf writeMediumLE(int i) {
/*  911 */     return this.a.writeMediumLE(i);
/*      */   }
/*      */   
/*      */   public ByteBuf writeInt(int i) {
/*  915 */     return this.a.writeInt(i);
/*      */   }
/*      */   
/*      */   public ByteBuf writeIntLE(int i) {
/*  919 */     return this.a.writeIntLE(i);
/*      */   }
/*      */   
/*      */   public ByteBuf writeLong(long i) {
/*  923 */     return this.a.writeLong(i);
/*      */   }
/*      */   
/*      */   public ByteBuf writeLongLE(long i) {
/*  927 */     return this.a.writeLongLE(i);
/*      */   }
/*      */   
/*      */   public ByteBuf writeChar(int i) {
/*  931 */     return this.a.writeChar(i);
/*      */   }
/*      */   
/*      */   public ByteBuf writeFloat(float f) {
/*  935 */     return this.a.writeFloat(f);
/*      */   }
/*      */   
/*      */   public ByteBuf writeDouble(double d0) {
/*  939 */     return this.a.writeDouble(d0);
/*      */   }
/*      */   
/*      */   public ByteBuf writeBytes(ByteBuf bytebuf) {
/*  943 */     return this.a.writeBytes(bytebuf);
/*      */   }
/*      */   
/*      */   public ByteBuf writeBytes(ByteBuf bytebuf, int i) {
/*  947 */     return this.a.writeBytes(bytebuf, i);
/*      */   }
/*      */   
/*      */   public ByteBuf writeBytes(ByteBuf bytebuf, int i, int j) {
/*  951 */     return this.a.writeBytes(bytebuf, i, j);
/*      */   }
/*      */   
/*      */   public ByteBuf writeBytes(byte[] abyte) {
/*  955 */     return this.a.writeBytes(abyte);
/*      */   }
/*      */   
/*      */   public ByteBuf writeBytes(byte[] abyte, int i, int j) {
/*  959 */     return this.a.writeBytes(abyte, i, j);
/*      */   }
/*      */   
/*      */   public ByteBuf writeBytes(ByteBuffer bytebuffer) {
/*  963 */     return this.a.writeBytes(bytebuffer);
/*      */   }
/*      */   
/*      */   public int writeBytes(InputStream inputstream, int i) throws IOException {
/*  967 */     return this.a.writeBytes(inputstream, i);
/*      */   }
/*      */   
/*      */   public int writeBytes(ScatteringByteChannel scatteringbytechannel, int i) throws IOException {
/*  971 */     return this.a.writeBytes(scatteringbytechannel, i);
/*      */   }
/*      */   
/*      */   public int writeBytes(FileChannel filechannel, long i, int j) throws IOException {
/*  975 */     return this.a.writeBytes(filechannel, i, j);
/*      */   }
/*      */   
/*      */   public ByteBuf writeZero(int i) {
/*  979 */     return this.a.writeZero(i);
/*      */   }
/*      */   
/*      */   public int writeCharSequence(CharSequence charsequence, Charset charset) {
/*  983 */     return this.a.writeCharSequence(charsequence, charset);
/*      */   }
/*      */   
/*      */   public int indexOf(int i, int j, byte b0) {
/*  987 */     return this.a.indexOf(i, j, b0);
/*      */   }
/*      */   
/*      */   public int bytesBefore(byte b0) {
/*  991 */     return this.a.bytesBefore(b0);
/*      */   }
/*      */   
/*      */   public int bytesBefore(int i, byte b0) {
/*  995 */     return this.a.bytesBefore(i, b0);
/*      */   }
/*      */   
/*      */   public int bytesBefore(int i, int j, byte b0) {
/*  999 */     return this.a.bytesBefore(i, j, b0);
/*      */   }
/*      */   
/*      */   public int forEachByte(ByteProcessor byteprocessor) {
/* 1003 */     return this.a.forEachByte(byteprocessor);
/*      */   }
/*      */   
/*      */   public int forEachByte(int i, int j, ByteProcessor byteprocessor) {
/* 1007 */     return this.a.forEachByte(i, j, byteprocessor);
/*      */   }
/*      */   
/*      */   public int forEachByteDesc(ByteProcessor byteprocessor) {
/* 1011 */     return this.a.forEachByteDesc(byteprocessor);
/*      */   }
/*      */   
/*      */   public int forEachByteDesc(int i, int j, ByteProcessor byteprocessor) {
/* 1015 */     return this.a.forEachByteDesc(i, j, byteprocessor);
/*      */   }
/*      */   
/*      */   public ByteBuf copy() {
/* 1019 */     return this.a.copy();
/*      */   }
/*      */   
/*      */   public ByteBuf copy(int i, int j) {
/* 1023 */     return this.a.copy(i, j);
/*      */   }
/*      */   
/*      */   public ByteBuf slice() {
/* 1027 */     return this.a.slice();
/*      */   }
/*      */   
/*      */   public ByteBuf retainedSlice() {
/* 1031 */     return this.a.retainedSlice();
/*      */   }
/*      */   
/*      */   public ByteBuf slice(int i, int j) {
/* 1035 */     return this.a.slice(i, j);
/*      */   }
/*      */   
/*      */   public ByteBuf retainedSlice(int i, int j) {
/* 1039 */     return this.a.retainedSlice(i, j);
/*      */   }
/*      */   
/*      */   public ByteBuf duplicate() {
/* 1043 */     return this.a.duplicate();
/*      */   }
/*      */   
/*      */   public ByteBuf retainedDuplicate() {
/* 1047 */     return this.a.retainedDuplicate();
/*      */   }
/*      */   
/*      */   public int nioBufferCount() {
/* 1051 */     return this.a.nioBufferCount();
/*      */   }
/*      */   
/*      */   public ByteBuffer nioBuffer() {
/* 1055 */     return this.a.nioBuffer();
/*      */   }
/*      */   
/*      */   public ByteBuffer nioBuffer(int i, int j) {
/* 1059 */     return this.a.nioBuffer(i, j);
/*      */   }
/*      */   
/*      */   public ByteBuffer internalNioBuffer(int i, int j) {
/* 1063 */     return this.a.internalNioBuffer(i, j);
/*      */   }
/*      */   
/*      */   public ByteBuffer[] nioBuffers() {
/* 1067 */     return this.a.nioBuffers();
/*      */   }
/*      */   
/*      */   public ByteBuffer[] nioBuffers(int i, int j) {
/* 1071 */     return this.a.nioBuffers(i, j);
/*      */   }
/*      */   
/*      */   public boolean hasArray() {
/* 1075 */     return this.a.hasArray();
/*      */   }
/*      */   
/*      */   public byte[] array() {
/* 1079 */     return this.a.array();
/*      */   }
/*      */   
/*      */   public int arrayOffset() {
/* 1083 */     return this.a.arrayOffset();
/*      */   }
/*      */   
/*      */   public boolean hasMemoryAddress() {
/* 1087 */     return this.a.hasMemoryAddress();
/*      */   }
/*      */   
/*      */   public long memoryAddress() {
/* 1091 */     return this.a.memoryAddress();
/*      */   }
/*      */   
/*      */   public String toString(Charset charset) {
/* 1095 */     return this.a.toString(charset);
/*      */   }
/*      */   
/*      */   public String toString(int i, int j, Charset charset) {
/* 1099 */     return this.a.toString(i, j, charset);
/*      */   }
/*      */   
/*      */   public int hashCode() {
/* 1103 */     return this.a.hashCode();
/*      */   }
/*      */   
/*      */   public boolean equals(Object object) {
/* 1107 */     return this.a.equals(object);
/*      */   }
/*      */   
/*      */   public int compareTo(ByteBuf bytebuf) {
/* 1111 */     return this.a.compareTo(bytebuf);
/*      */   }
/*      */   
/*      */   public String toString() {
/* 1115 */     return this.a.toString();
/*      */   }
/*      */   
/*      */   public ByteBuf retain(int i) {
/* 1119 */     return this.a.retain(i);
/*      */   }
/*      */   
/*      */   public ByteBuf retain() {
/* 1123 */     return this.a.retain();
/*      */   }
/*      */   
/*      */   public ByteBuf touch() {
/* 1127 */     return this.a.touch();
/*      */   }
/*      */   
/*      */   public ByteBuf touch(Object object) {
/* 1131 */     return this.a.touch(object);
/*      */   }
/*      */   
/*      */   public int refCnt() {
/* 1135 */     return this.a.refCnt();
/*      */   }
/*      */   
/*      */   public boolean release() {
/* 1139 */     return this.a.release();
/*      */   }
/*      */   
/*      */   public boolean release(int i) {
/* 1143 */     return this.a.release(i);
/*      */   }
/*      */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketDataSerializer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
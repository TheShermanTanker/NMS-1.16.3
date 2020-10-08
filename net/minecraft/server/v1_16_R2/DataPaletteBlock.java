/*     */ package net.minecraft.server.v1_16_R2;
/*     */ import it.unimi.dsi.fastutil.ints.Int2IntOpenHashMap;
/*     */ import java.util.function.Function;
/*     */ 
/*     */ public class DataPaletteBlock<T> implements DataPaletteExpandable<T> {
/*     */   private final DataPalette<T> b;
/*     */   private final DataPaletteExpandable<T> c = (i, object) -> 0;
/*     */   private final RegistryBlockID<T> d;
/*     */   private final Function<NBTTagCompound, T> e;
/*     */   private final Function<T, NBTTagCompound> f;
/*     */   private final T g;
/*     */   
/*     */   private final DataPalette<T> getDataPaletteGlobal() {
/*  14 */     return this.b;
/*     */   }
/*     */   
/*     */   private final T[] predefinedObjects;
/*     */   protected DataBits a;
/*     */   private DataPalette<T> h;
/*     */   private int i;
/*     */   
/*     */   public final DataBits getDataBits() {
/*  23 */     return this.a; }
/*  24 */   private DataPalette<T> getDataPalette() { return this.h; } private int getBitsPerObject() {
/*  25 */     return this.i;
/*  26 */   } private final ReentrantLock j = new ReentrantLock();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void b() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public DataPaletteBlock(DataPalette<T> datapalette, RegistryBlockID<T> registryblockid, Function<NBTTagCompound, T> function, Function<T, NBTTagCompound> function1, T t0) {
/*  48 */     this(datapalette, registryblockid, function, function1, t0, null, true);
/*     */   }
/*     */   public DataPaletteBlock(DataPalette<T> datapalette, RegistryBlockID<T> registryblockid, Function<NBTTagCompound, T> function, Function<T, NBTTagCompound> function1, T t0, T[] predefinedObjects, boolean initialize) {
/*  51 */     this.b = datapalette;
/*  52 */     this.d = registryblockid;
/*  53 */     this.e = function;
/*  54 */     this.f = function1;
/*  55 */     this.g = t0;
/*     */     
/*  57 */     this.predefinedObjects = predefinedObjects;
/*     */     
/*  59 */     if (initialize) {
/*  60 */       if (predefinedObjects == null) {
/*     */         
/*  62 */         initialize(4);
/*     */       
/*     */       }
/*     */       else {
/*     */ 
/*     */         
/*  68 */         int maxIndex = predefinedObjects.length >> 4;
/*  69 */         int bitCount = 32 - Integer.numberOfLeadingZeros(Math.max(16, maxIndex) - 1);
/*     */ 
/*     */         
/*  72 */         initialize(((1 << bitCount) - predefinedObjects.length < 16) ? (bitCount + 1) : bitCount);
/*  73 */         addPredefinedObjects();
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void addPredefinedObjects() {
/*  81 */     if (this.predefinedObjects != null && getDataPalette() != getDataPaletteGlobal()) {
/*  82 */       for (int i = 0; i < this.predefinedObjects.length; i++) {
/*  83 */         getDataPalette().getOrCreateIdFor(this.predefinedObjects[i]);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private static int b(int i, int j, int k) {
/*  90 */     return j << 8 | k << 4 | i;
/*     */   }
/*     */   private void initialize(int bitsPerObject) {
/*  93 */     b(bitsPerObject);
/*     */   } private void b(int i) {
/*  95 */     if (i != this.i) {
/*  96 */       this.i = i;
/*  97 */       if (this.i <= 4) {
/*  98 */         this.i = 4;
/*  99 */         this.h = new DataPaletteLinear<>(this.d, this.i, this, this.e);
/* 100 */       } else if (this.i < 9) {
/* 101 */         this.h = new DataPaletteHash<>(this.d, this.i, this, this.e, this.f);
/*     */       } else {
/* 103 */         this.h = this.b;
/* 104 */         this.i = MathHelper.e(this.d.a());
/*     */       } 
/*     */       
/* 107 */       this.h.a(this.g);
/* 108 */       this.a = new DataBits(this.i, 4096);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public synchronized int onResize(int i, T t0) {
/* 114 */     a();
/* 115 */     DataBits databits = this.a;
/* 116 */     DataPalette<T> datapalette = this.h;
/*     */     
/* 118 */     b(i);
/*     */ 
/*     */ 
/*     */     
/* 122 */     addPredefinedObjects(); int j;
/* 123 */     for (j = 0; j < databits.b(); j++) {
/* 124 */       T t1 = datapalette.a(databits.a(j));
/*     */       
/* 126 */       if (t1 != null) {
/* 127 */         setBlockIndex(j, t1);
/*     */       }
/*     */     } 
/*     */     
/* 131 */     j = this.h.a(t0);
/* 132 */     b();
/* 133 */     return j;
/*     */   }
/*     */ 
/*     */   
/*     */   public T setBlock(int i, int j, int k, T t0) {
/* 138 */     return a(b(i, j, k), t0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public T b(int i, int j, int k, T t0) {
/* 145 */     return a(b(i, j, k), t0);
/*     */   }
/*     */   
/*     */   protected synchronized T a(int i, T t0) {
/* 149 */     int j = this.h.a(t0);
/* 150 */     int k = this.a.a(i, j);
/* 151 */     T t1 = this.h.a(k);
/*     */     
/* 153 */     return (t1 == null) ? this.g : t1;
/*     */   }
/*     */   
/*     */   protected void setBlockIndex(int i, T t0) {
/* 157 */     int j = this.h.a(t0);
/*     */     
/* 159 */     this.a.b(i, j);
/*     */   }
/*     */   
/*     */   public T a(int i, int j, int k) {
/* 163 */     return a(j << 8 | k << 4 | i);
/*     */   }
/*     */   public final T rawGet(int index) {
/* 166 */     return a(index);
/*     */   } protected T a(int i) {
/* 168 */     T t0 = this.h.a(this.a.a(i));
/*     */     
/* 170 */     return (t0 == null) ? this.g : t0;
/*     */   }
/*     */   
/*     */   @Deprecated
/* 174 */   public void writeDataPaletteBlock(PacketDataSerializer packetDataSerializer) { b(packetDataSerializer); } @Deprecated
/* 175 */   public void b(PacketDataSerializer packetdataserializer) { writeDataPaletteBlock(packetdataserializer, null, 0); } public void writeDataPaletteBlock(PacketDataSerializer packetDataSerializer, ChunkPacketInfo<T> chunkPacketInfo, int chunkSectionIndex) {
/* 176 */     b(packetDataSerializer, chunkPacketInfo, chunkSectionIndex);
/*     */   }
/*     */   public synchronized void b(PacketDataSerializer packetdataserializer, ChunkPacketInfo<T> chunkPacketInfo, int chunkSectionIndex) {
/* 179 */     a();
/* 180 */     packetdataserializer.writeByte(this.i);
/* 181 */     this.h.b(packetdataserializer);
/*     */     
/* 183 */     if (chunkPacketInfo != null) {
/* 184 */       chunkPacketInfo.setBitsPerObject(chunkSectionIndex, getBitsPerObject());
/* 185 */       chunkPacketInfo.setDataPalette(chunkSectionIndex, getDataPalette());
/* 186 */       chunkPacketInfo.setDataBitsIndex(chunkSectionIndex, packetdataserializer.writerIndex() + PacketDataSerializer.countBytes((getDataBits().getDataBits()).length));
/* 187 */       chunkPacketInfo.setPredefinedObjects(chunkSectionIndex, (Object[])this.predefinedObjects);
/*     */     } 
/*     */     
/* 190 */     packetdataserializer.a(this.a.a());
/* 191 */     b();
/*     */   }
/*     */   
/*     */   public synchronized void a(NBTTagList nbttaglist, long[] along) {
/* 195 */     a();
/*     */     
/* 197 */     int i = Math.max(4, MathHelper.e(nbttaglist.size() + ((this.predefinedObjects == null) ? 0 : this.predefinedObjects.length)));
/*     */ 
/*     */     
/* 200 */     b(i);
/*     */ 
/*     */     
/* 203 */     this.h.a(nbttaglist);
/* 204 */     addPredefinedObjects();
/* 205 */     int j = along.length * 64 / 4096;
/*     */     
/* 207 */     if (this.h == this.b) {
/* 208 */       DataPalette<T> datapalette = new DataPaletteHash<>(this.d, i, this.c, this.e, this.f);
/*     */       
/* 210 */       datapalette.a(nbttaglist);
/* 211 */       DataBits databits = new DataBits(i, 4096, along);
/*     */       
/* 213 */       for (int k = 0; k < 4096; k++) {
/* 214 */         this.a.b(k, this.b.a(datapalette.a(databits.a(k))));
/*     */       }
/* 216 */     } else if (j == this.i) {
/* 217 */       System.arraycopy(along, 0, this.a.a(), 0, along.length);
/*     */     } else {
/* 219 */       DataBits databits1 = new DataBits(j, 4096, along);
/*     */       
/* 221 */       for (int l = 0; l < 4096; l++) {
/* 222 */         this.a.b(l, databits1.a(l));
/*     */       }
/*     */     } 
/*     */     
/* 226 */     b();
/*     */   }
/*     */   
/*     */   public synchronized void a(NBTTagCompound nbttagcompound, String s, String s1) {
/* 230 */     a();
/* 231 */     DataPaletteHash<T> datapalettehash = new DataPaletteHash<>(this.d, this.i, this.c, this.e, this.f);
/* 232 */     T t0 = this.g;
/* 233 */     int i = datapalettehash.a(this.g);
/* 234 */     int[] aint = new int[4096];
/*     */     
/* 236 */     for (int j = 0; j < 4096; j++) {
/* 237 */       T t1 = a(j);
/*     */       
/* 239 */       if (t1 != t0) {
/* 240 */         t0 = t1;
/* 241 */         i = datapalettehash.a(t1);
/*     */       } 
/*     */       
/* 244 */       aint[j] = i;
/*     */     } 
/*     */     
/* 247 */     NBTTagList nbttaglist = new NBTTagList();
/*     */     
/* 249 */     datapalettehash.b(nbttaglist);
/* 250 */     nbttagcompound.set(s, nbttaglist);
/* 251 */     int k = Math.max(4, MathHelper.e(nbttaglist.size()));
/* 252 */     DataBits databits = new DataBits(k, 4096);
/*     */     
/* 254 */     for (int l = 0; l < aint.length; l++) {
/* 255 */       databits.b(l, aint[l]);
/*     */     }
/*     */     
/* 258 */     nbttagcompound.a(s1, databits.a());
/* 259 */     b();
/*     */   }
/*     */   
/*     */   public int c() {
/* 263 */     return 1 + this.h.a() + PacketDataSerializer.a(this.a.b()) + (this.a.a()).length * 8;
/*     */   }
/*     */   
/*     */   public boolean contains(Predicate<T> predicate) {
/* 267 */     return this.h.a(predicate);
/*     */   }
/*     */   
/*     */   public void a(a<T> datapaletteblock_a) {
/* 271 */     Int2IntOpenHashMap int2intopenhashmap = new Int2IntOpenHashMap();
/*     */     
/* 273 */     this.a.a(i -> int2intopenhashmap.put(i, int2intopenhashmap.get(i) + 1));
/*     */ 
/*     */     
/* 276 */     int2intopenhashmap.int2IntEntrySet().forEach(entry -> datapaletteblock_a.accept(this.h.a(entry.getIntKey()), entry.getIntValue()));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void forEachLocation(a<T> datapaletteblock_a) {
/* 283 */     getDataBits().forEach((location, data) -> datapaletteblock_a.accept(getDataPalette().getObject(data), location));
/*     */   }
/*     */   
/*     */   @FunctionalInterface
/*     */   public static interface a<T> {
/*     */     void accept(T param1T, int param1Int);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataPaletteBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
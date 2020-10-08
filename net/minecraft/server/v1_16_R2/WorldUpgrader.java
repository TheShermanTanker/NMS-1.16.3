/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.ImmutableList;
/*     */ import com.google.common.collect.ImmutableMap;
/*     */ import com.google.common.collect.ImmutableSet;
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.UnmodifiableIterator;
/*     */ import com.google.common.util.concurrent.ThreadFactoryBuilder;
/*     */ import com.mojang.datafixers.DataFixer;
/*     */ import it.unimi.dsi.fastutil.objects.Object2FloatMap;
/*     */ import it.unimi.dsi.fastutil.objects.Object2FloatMaps;
/*     */ import it.unimi.dsi.fastutil.objects.Object2FloatOpenCustomHashMap;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.util.List;
/*     */ import java.util.ListIterator;
/*     */ import java.util.concurrent.ThreadFactory;
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ 
/*     */ public class WorldUpgrader
/*     */ {
/*  26 */   private static final Logger LOGGER = LogManager.getLogger();
/*  27 */   private static final ThreadFactory b = (new ThreadFactoryBuilder()).setDaemon(true).build();
/*     */   private final ImmutableSet<ResourceKey<DimensionManager>> c;
/*     */   private final boolean d;
/*     */   private final Convertable.ConversionSession e;
/*     */   private final Thread f;
/*     */   private final DataFixer g;
/*     */   private volatile boolean h = true;
/*     */   private volatile boolean i;
/*     */   private volatile float j;
/*     */   private volatile int k;
/*     */   private volatile int l;
/*     */   private volatile int m;
/*  39 */   private final Object2FloatMap<ResourceKey<DimensionManager>> n = Object2FloatMaps.synchronize((Object2FloatMap)new Object2FloatOpenCustomHashMap(SystemUtils.k()));
/*  40 */   private volatile IChatBaseComponent o = new ChatMessage("optimizeWorld.stage.counting");
/*  41 */   private static final Pattern p = Pattern.compile("^r\\.(-?[0-9]+)\\.(-?[0-9]+)\\.mca$");
/*     */   private final WorldPersistentData q;
/*     */   
/*     */   public WorldUpgrader(Convertable.ConversionSession convertable_conversionsession, DataFixer datafixer, ImmutableSet<ResourceKey<DimensionManager>> immutableset, boolean flag) {
/*  45 */     this.c = immutableset;
/*  46 */     this.d = flag;
/*  47 */     this.g = datafixer;
/*  48 */     this.e = convertable_conversionsession;
/*  49 */     this.q = new WorldPersistentData(new File(this.e.a(World.OVERWORLD), "data"), datafixer);
/*  50 */     this.f = b.newThread(this::i);
/*  51 */     this.f.setUncaughtExceptionHandler((thread, throwable) -> {
/*     */           LOGGER.error("Error upgrading world", throwable);
/*     */           this.o = new ChatMessage("optimizeWorld.stage.failed");
/*     */           this.i = true;
/*     */         });
/*  56 */     this.f.start();
/*     */   }
/*     */   
/*     */   public void a() {
/*  60 */     this.h = false;
/*     */     
/*     */     try {
/*  63 */       this.f.join();
/*  64 */     } catch (InterruptedException interruptedException) {}
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void i() {
/*  71 */     this.k = 0;
/*  72 */     ImmutableMap.Builder<ResourceKey<DimensionManager>, ListIterator<ChunkCoordIntPair>> builder = ImmutableMap.builder();
/*     */ 
/*     */ 
/*     */     
/*  76 */     for (UnmodifiableIterator unmodifiableiterator = this.c.iterator(); unmodifiableiterator.hasNext(); this.k += list.size()) {
/*  77 */       ResourceKey<DimensionManager> resourcekey = (ResourceKey<DimensionManager>)unmodifiableiterator.next();
/*     */       
/*  79 */       List<ChunkCoordIntPair> list = b(resourcekey);
/*  80 */       builder.put(resourcekey, list.listIterator());
/*     */     } 
/*     */     
/*  83 */     if (this.k == 0) {
/*  84 */       this.i = true;
/*     */     } else {
/*  86 */       float f = this.k;
/*  87 */       ImmutableMap<ResourceKey<DimensionManager>, ListIterator<ChunkCoordIntPair>> immutablemap = builder.build();
/*  88 */       ImmutableMap.Builder<ResourceKey<DimensionManager>, IChunkLoader> builder1 = ImmutableMap.builder();
/*  89 */       UnmodifiableIterator unmodifiableiterator1 = this.c.iterator();
/*     */       
/*  91 */       while (unmodifiableiterator1.hasNext()) {
/*  92 */         ResourceKey<DimensionManager> resourcekey1 = (ResourceKey<DimensionManager>)unmodifiableiterator1.next();
/*  93 */         File file = this.e.a(null);
/*     */         
/*  95 */         builder1.put(resourcekey1, new IChunkLoader(new File(file, "region"), this.g, true));
/*     */       } 
/*     */       
/*  98 */       ImmutableMap<ResourceKey<DimensionManager>, IChunkLoader> immutablemap1 = builder1.build();
/*  99 */       long i = SystemUtils.getMonotonicMillis();
/*     */       
/* 101 */       this.o = new ChatMessage("optimizeWorld.stage.upgrading");
/*     */       
/* 103 */       while (this.h) {
/* 104 */         boolean flag = false;
/* 105 */         float f1 = 0.0F;
/*     */ 
/*     */ 
/*     */         
/* 109 */         for (UnmodifiableIterator unmodifiableiterator2 = this.c.iterator(); unmodifiableiterator2.hasNext(); f1 += f2) {
/* 110 */           ResourceKey<DimensionManager> resourcekey2 = (ResourceKey<DimensionManager>)unmodifiableiterator2.next();
/* 111 */           ListIterator<ChunkCoordIntPair> listiterator = (ListIterator<ChunkCoordIntPair>)immutablemap.get(resourcekey2);
/* 112 */           IChunkLoader ichunkloader = (IChunkLoader)immutablemap1.get(resourcekey2);
/*     */           
/* 114 */           if (listiterator.hasNext()) {
/* 115 */             ChunkCoordIntPair chunkcoordintpair = listiterator.next();
/* 116 */             boolean flag1 = false;
/*     */             
/*     */             try {
/* 119 */               NBTTagCompound nbttagcompound = ichunkloader.read(chunkcoordintpair);
/*     */               
/* 121 */               if (nbttagcompound != null) {
/* 122 */                 int j = IChunkLoader.a(nbttagcompound);
/* 123 */                 NBTTagCompound nbttagcompound1 = ichunkloader.getChunkData(resourcekey2, () -> this.q, nbttagcompound, chunkcoordintpair, null);
/*     */ 
/*     */                 
/* 126 */                 NBTTagCompound nbttagcompound2 = nbttagcompound1.getCompound("Level");
/* 127 */                 ChunkCoordIntPair chunkcoordintpair1 = new ChunkCoordIntPair(nbttagcompound2.getInt("xPos"), nbttagcompound2.getInt("zPos"));
/*     */                 
/* 129 */                 if (!chunkcoordintpair1.equals(chunkcoordintpair)) {
/* 130 */                   LOGGER.warn("Chunk {} has invalid position {}", chunkcoordintpair, chunkcoordintpair1);
/*     */                 }
/*     */                 
/* 133 */                 boolean flag2 = (j < SharedConstants.getGameVersion().getWorldVersion());
/*     */                 
/* 135 */                 if (this.d) {
/* 136 */                   flag2 = (flag2 || nbttagcompound2.hasKey("Heightmaps"));
/* 137 */                   nbttagcompound2.remove("Heightmaps");
/* 138 */                   flag2 = (flag2 || nbttagcompound2.hasKey("isLightOn"));
/* 139 */                   nbttagcompound2.remove("isLightOn");
/*     */                 } 
/*     */                 
/* 142 */                 if (flag2) {
/* 143 */                   ichunkloader.a(chunkcoordintpair, nbttagcompound1);
/* 144 */                   flag1 = true;
/*     */                 } 
/*     */               } 
/* 147 */             } catch (ReportedException reportedexception) {
/* 148 */               Throwable throwable = reportedexception.getCause();
/*     */               
/* 150 */               if (!(throwable instanceof IOException)) {
/* 151 */                 throw reportedexception;
/*     */               }
/*     */               
/* 154 */               LOGGER.error("Error upgrading chunk {}", chunkcoordintpair, throwable);
/* 155 */             } catch (IOException ioexception) {
/* 156 */               LOGGER.error("Error upgrading chunk {}", chunkcoordintpair, ioexception);
/*     */             } 
/*     */             
/* 159 */             if (flag1) {
/* 160 */               this.l++;
/*     */             } else {
/* 162 */               this.m++;
/*     */             } 
/*     */             
/* 165 */             flag = true;
/*     */           } 
/*     */           
/* 168 */           float f2 = listiterator.nextIndex() / f;
/* 169 */           this.n.put(resourcekey2, f2);
/*     */         } 
/*     */         
/* 172 */         this.j = f1;
/* 173 */         if (!flag) {
/* 174 */           this.h = false;
/*     */         }
/*     */       } 
/*     */       
/* 178 */       this.o = new ChatMessage("optimizeWorld.stage.finished");
/* 179 */       UnmodifiableIterator unmodifiableiterator3 = immutablemap1.values().iterator();
/*     */       
/* 181 */       while (unmodifiableiterator3.hasNext()) {
/* 182 */         IChunkLoader ichunkloader1 = (IChunkLoader)unmodifiableiterator3.next();
/*     */         
/*     */         try {
/* 185 */           ichunkloader1.close();
/* 186 */         } catch (IOException ioexception1) {
/* 187 */           LOGGER.error("Error upgrading chunk", ioexception1);
/*     */         } 
/*     */       } 
/*     */       
/* 191 */       this.q.a();
/* 192 */       i = SystemUtils.getMonotonicMillis() - i;
/* 193 */       LOGGER.info("World optimizaton finished after {} ms", Long.valueOf(i));
/* 194 */       this.i = true;
/*     */     } 
/*     */   }
/*     */   
/*     */   private List<ChunkCoordIntPair> b(ResourceKey<DimensionManager> resourcekey) {
/* 199 */     File file = this.e.a(null);
/* 200 */     File file1 = new File(file, "region");
/* 201 */     File[] afile = file1.listFiles((file2, s) -> s.endsWith(".mca"));
/*     */ 
/*     */ 
/*     */     
/* 205 */     if (afile == null) {
/* 206 */       return (List<ChunkCoordIntPair>)ImmutableList.of();
/*     */     }
/* 208 */     List<ChunkCoordIntPair> list = Lists.newArrayList();
/* 209 */     File[] afile1 = afile;
/* 210 */     int i = afile.length;
/*     */     
/* 212 */     for (int j = 0; j < i; j++) {
/* 213 */       File file2 = afile1[j];
/* 214 */       Matcher matcher = p.matcher(file2.getName());
/*     */       
/* 216 */       if (matcher.matches()) {
/* 217 */         int k = Integer.parseInt(matcher.group(1)) << 5;
/* 218 */         int l = Integer.parseInt(matcher.group(2)) << 5;
/*     */         
/*     */         try {
/* 221 */           RegionFile regionfile = new RegionFile(file2, file1, true, true);
/* 222 */           Throwable throwable = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         }
/* 251 */         catch (Throwable throwable) {}
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 257 */     return list;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean b() {
/* 262 */     return this.i;
/*     */   }
/*     */   
/*     */   public int e() {
/* 266 */     return this.k;
/*     */   }
/*     */   
/*     */   public int f() {
/* 270 */     return this.l;
/*     */   }
/*     */   
/*     */   public int g() {
/* 274 */     return this.m;
/*     */   }
/*     */   
/*     */   public IChatBaseComponent h() {
/* 278 */     return this.o;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldUpgrader.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
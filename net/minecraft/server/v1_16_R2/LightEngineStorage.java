/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
/*     */ import it.unimi.dsi.fastutil.longs.Long2ObjectMaps;
/*     */ import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
/*     */ import it.unimi.dsi.fastutil.longs.LongIterator;
/*     */ import it.unimi.dsi.fastutil.longs.LongOpenHashSet;
/*     */ import it.unimi.dsi.fastutil.longs.LongSet;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectIterator;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ public abstract class LightEngineStorage<M extends LightEngineStorageArray<M>>
/*     */   extends LightEngineGraphSection
/*     */ {
/*  15 */   protected static final NibbleArray a = new NibbleArray();
/*  16 */   private static final EnumDirection[] k = EnumDirection.values();
/*     */   private final EnumSkyBlock l;
/*     */   private final ILightAccess m;
/*  19 */   protected final LongSet b = (LongSet)new LongOpenHashSet();
/*  20 */   protected final LongSet c = (LongSet)new LongOpenHashSet();
/*  21 */   protected final LongSet d = (LongSet)new LongOpenHashSet(); protected volatile M e_visible;
/*  22 */   protected final Object visibleUpdateLock = new Object(); protected final M f;
/*     */   protected final M updating;
/*  24 */   protected final LongSet g = (LongSet)new LongOpenHashSet(); protected final LongSet h;
/*  25 */   LongSet dirty = this.h = (LongSet)new LongOpenHashSet();
/*  26 */   protected final Long2ObjectOpenHashMap<NibbleArray> i_synchronized_map_real = new Long2ObjectOpenHashMap();
/*  27 */   protected final Long2ObjectMap<NibbleArray> i = Long2ObjectMaps.synchronize((Long2ObjectMap)this.i_synchronized_map_real);
/*  28 */   private final LongSet n = (LongSet)new LongOpenHashSet();
/*  29 */   private final LongSet o = (LongSet)new LongOpenHashSet();
/*  30 */   private final LongSet p = (LongSet)new LongOpenHashSet();
/*     */   protected volatile boolean j;
/*     */   
/*     */   protected LightEngineStorage(EnumSkyBlock enumskyblock, ILightAccess ilightaccess, M m0) {
/*  34 */     super(3, 256, 256);
/*  35 */     this.l = enumskyblock;
/*  36 */     this.m = ilightaccess;
/*  37 */     this.f = m0; this.updating = m0;
/*  38 */     this.e_visible = m0.b();
/*  39 */     this.e_visible.d();
/*     */   }
/*     */   
/*     */   protected final boolean g(long i) {
/*  43 */     return (this.updating.getUpdatingOptimized(i) != null);
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   protected NibbleArray a(long i, boolean flag) {
/*  49 */     if (flag) {
/*  50 */       return this.updating.getUpdatingOptimized(i);
/*     */     }
/*  52 */     synchronized (this.visibleUpdateLock) {
/*  53 */       return ((LightEngineStorageArray)this.e_visible).lookup.apply(i);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   protected final NibbleArray a(M m0, long i) {
/*  61 */     return m0.c(i);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public NibbleArray h(long i) {
/*  66 */     NibbleArray nibblearray = (NibbleArray)this.i.get(i);
/*     */     
/*  68 */     return (nibblearray != null) ? nibblearray : a(i, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int i(long i) {
/*  75 */     int x = (int)(i >> 38L);
/*  76 */     int y = (int)(i << 52L >> 52L);
/*  77 */     int z = (int)(i << 26L >> 38L);
/*  78 */     long j = SectionPosition.blockPosAsSectionLong(x, y, z);
/*  79 */     NibbleArray nibblearray = this.updating.getUpdatingOptimized(j);
/*     */     
/*  81 */     if (nibblearray == null) {
/*  82 */       nibblearray = ((LightEngineStorageArray)this.e_visible).lookup.apply(j);
/*     */     }
/*  84 */     if (nibblearray == null) {
/*  85 */       System.err.println("Null nibble, preventing crash " + BlockPosition.fromLong(i));
/*  86 */       return 0;
/*     */     } 
/*     */     
/*  89 */     return nibblearray.a(x & 0xF, y & 0xF, z & 0xF);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void b(long i, int j) {
/*  95 */     int x = (int)(i >> 38L);
/*  96 */     int y = (int)(i << 52L >> 52L);
/*  97 */     int z = (int)(i << 26L >> 38L);
/*  98 */     long k = SectionPosition.blockPosAsSectionLong(x, y, z);
/*     */ 
/*     */     
/* 101 */     if (this.g.add(k)) {
/* 102 */       this.f.a(k);
/*     */     }
/*     */     
/* 105 */     NibbleArray nibblearray = a(k, true);
/* 106 */     nibblearray.a(x & 0xF, y & 0xF, z & 0xF, j);
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
/* 120 */     for (int z2 = z - 1 >> 4; z2 <= z + 1 >> 4; z2++) {
/* 121 */       for (int x2 = x - 1 >> 4; x2 <= x + 1 >> 4; x2++) {
/* 122 */         for (int y2 = y - 1 >> 4; y2 <= y + 1 >> 4; y2++) {
/* 123 */           this.dirty.add(SectionPosition.asLong(x2, y2, z2));
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected int c(long i) {
/* 133 */     return (i == Long.MAX_VALUE) ? 2 : (this.b.contains(i) ? 0 : ((!this.p.contains(i) && this.f.b(i)) ? 1 : 2));
/*     */   }
/*     */ 
/*     */   
/*     */   protected int b(long i) {
/* 138 */     return this.c.contains(i) ? 2 : ((!this.b.contains(i) && !this.d.contains(i)) ? 2 : 0);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(long i, int j) {
/* 143 */     int k = c(i);
/*     */     
/* 145 */     if (k != 0 && j == 0) {
/* 146 */       this.b.add(i);
/* 147 */       this.d.remove(i);
/*     */     } 
/*     */     
/* 150 */     if (k == 0 && j != 0) {
/* 151 */       this.b.remove(i);
/* 152 */       this.c.remove(i);
/*     */     } 
/*     */     
/* 155 */     if (k >= 2 && j != 2 && 
/* 156 */       !this.p.remove(i)) {
/*     */ 
/*     */       
/* 159 */       this.f.a(i, j(i));
/* 160 */       this.g.add(i);
/* 161 */       k(i);
/*     */ 
/*     */       
/* 164 */       int x = (int)(i >> 38L);
/* 165 */       int y = (int)(i << 52L >> 52L);
/* 166 */       int z = (int)(i << 26L >> 38L);
/*     */       
/* 168 */       for (int z2 = z - 1 >> 4; z2 <= z + 1 >> 4; z2++) {
/* 169 */         for (int x2 = x - 1 >> 4; x2 <= x + 1 >> 4; x2++) {
/* 170 */           for (int y2 = y - 1 >> 4; y2 <= y + 1 >> 4; y2++) {
/* 171 */             this.dirty.add(SectionPosition.asLong(x2, y2, z2));
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 179 */     if (k != 2 && j >= 2) {
/* 180 */       this.p.add(i);
/*     */     }
/*     */     
/* 183 */     this.j = !this.p.isEmpty();
/*     */   }
/*     */   
/*     */   protected NibbleArray j(long i) {
/* 187 */     NibbleArray nibblearray = (NibbleArray)this.i.get(i);
/*     */     
/* 189 */     return (nibblearray != null) ? nibblearray : (new NibbleArray()).markPoolSafe();
/*     */   }
/*     */   
/*     */   protected void a(LightEngineLayer<?, ?> lightenginelayer, long i) {
/* 193 */     if (lightenginelayer.c() < 8192) {
/* 194 */       lightenginelayer.a(j -> (SectionPosition.e(j) == i));
/*     */     }
/*     */     else {
/*     */       
/* 198 */       int j = (int)(i >> 42L) << 4;
/* 199 */       int k = (int)(i << 44L >> 44L) << 4;
/* 200 */       int l = (int)(i << 22L >> 42L) << 4;
/*     */       
/* 202 */       for (int i1 = 0; i1 < 16; i1++) {
/* 203 */         for (int j1 = 0; j1 < 16; j1++) {
/* 204 */           for (int k1 = 0; k1 < 16; k1++) {
/* 205 */             long l1 = BlockPosition.a(j + i1, k + j1, l + k1);
/*     */             
/* 207 */             lightenginelayer.e(l1);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean a() {
/* 216 */     return this.j;
/*     */   }
/*     */   
/*     */   protected void a(LightEngineLayer<M, ?> lightenginelayer, boolean flag, boolean flag1) {
/* 220 */     if (a() || !this.i.isEmpty()) {
/* 221 */       LongIterator longiterator = this.p.iterator();
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 226 */       while (longiterator.hasNext()) {
/* 227 */         long i = longiterator.nextLong();
/* 228 */         a(lightenginelayer, i);
/* 229 */         NibbleArray nibblearray1 = (NibbleArray)this.i.remove(i);
/*     */         
/* 231 */         NibbleArray nibblearray = this.f.d(i);
/* 232 */         if (this.o.contains(SectionPosition.f(i))) {
/* 233 */           if (nibblearray1 != null) {
/* 234 */             this.i.put(i, nibblearray1); continue;
/* 235 */           }  if (nibblearray != null) {
/* 236 */             this.i.put(i, nibblearray);
/*     */           }
/*     */         } 
/*     */       } 
/*     */       
/* 241 */       this.f.c();
/* 242 */       longiterator = this.p.iterator();
/*     */       
/* 244 */       while (longiterator.hasNext()) {
/* 245 */         long i = longiterator.nextLong();
/* 246 */         l(i);
/*     */       } 
/*     */       
/* 249 */       this.p.clear();
/* 250 */       this.j = false;
/* 251 */       ObjectIterator objectiterator = this.i_synchronized_map_real.long2ObjectEntrySet().fastIterator();
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 256 */       NibbleArray test = null;
/* 257 */       while (objectiterator.hasNext()) {
/* 258 */         Long2ObjectMap.Entry entry = (Long2ObjectMap.Entry)objectiterator.next();
/* 259 */         long j = entry.getLongKey();
/* 260 */         if ((test = this.updating.getUpdatingOptimized(j)) != null) {
/* 261 */           NibbleArray nibblearray = (NibbleArray)entry.getValue();
/* 262 */           if (test != nibblearray) {
/* 263 */             a(lightenginelayer, j);
/* 264 */             this.f.a(j, nibblearray);
/* 265 */             this.g.add(j);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 270 */       this.f.c();
/* 271 */       if (!flag1) {
/* 272 */         longiterator = this.i.keySet().iterator();
/*     */         
/* 274 */         while (longiterator.hasNext()) {
/* 275 */           long i = longiterator.nextLong();
/* 276 */           b(lightenginelayer, i);
/*     */         } 
/*     */       } else {
/* 279 */         longiterator = this.n.iterator();
/*     */         
/* 281 */         while (longiterator.hasNext()) {
/* 282 */           long i = longiterator.nextLong();
/* 283 */           b(lightenginelayer, i);
/*     */         } 
/*     */       } 
/*     */       
/* 287 */       this.n.clear();
/* 288 */       objectiterator = this.i_synchronized_map_real.long2ObjectEntrySet().fastIterator();
/*     */       
/* 290 */       while (objectiterator.hasNext()) {
/* 291 */         Long2ObjectMap.Entry entry = (Long2ObjectMap.Entry)objectiterator.next();
/* 292 */         long j = entry.getLongKey();
/* 293 */         if (g(j)) {
/* 294 */           objectiterator.remove();
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void b(LightEngineLayer<M, ?> lightenginelayer, long i) {
/* 302 */     if (g(i)) {
/*     */       
/* 304 */       int secX = (int)(i >> 42L);
/* 305 */       int secY = (int)(i << 44L >> 44L);
/* 306 */       int secZ = (int)(i << 22L >> 42L);
/* 307 */       int j = secX << 4;
/* 308 */       int k = secY << 4;
/* 309 */       int l = secZ << 4;
/*     */       
/* 311 */       EnumDirection[] aenumdirection = LightEngineStorage.k;
/* 312 */       int i1 = aenumdirection.length;
/*     */       
/* 314 */       for (int j1 = 0; j1 < i1; j1++) {
/* 315 */         EnumDirection enumdirection = aenumdirection[j1];
/* 316 */         long k1 = SectionPosition.getAdjacentFromSectionPos(secX, secY, secZ, enumdirection);
/*     */         
/* 318 */         if (!this.i.containsKey(k1) && g(k1)) {
/* 319 */           for (int l1 = 0; l1 < 16; l1++) {
/* 320 */             for (int i2 = 0; i2 < 16; i2++) {
/*     */               long j2, k2;
/*     */ 
/*     */               
/* 324 */               switch (enumdirection) {
/*     */                 case DOWN:
/* 326 */                   j2 = BlockPosition.a(j + i2, k, l + l1);
/* 327 */                   k2 = BlockPosition.a(j + i2, k - 1, l + l1);
/*     */                   break;
/*     */                 case UP:
/* 330 */                   j2 = BlockPosition.a(j + i2, k + 16 - 1, l + l1);
/* 331 */                   k2 = BlockPosition.a(j + i2, k + 16, l + l1);
/*     */                   break;
/*     */                 case NORTH:
/* 334 */                   j2 = BlockPosition.a(j + l1, k + i2, l);
/* 335 */                   k2 = BlockPosition.a(j + l1, k + i2, l - 1);
/*     */                   break;
/*     */                 case SOUTH:
/* 338 */                   j2 = BlockPosition.a(j + l1, k + i2, l + 16 - 1);
/* 339 */                   k2 = BlockPosition.a(j + l1, k + i2, l + 16);
/*     */                   break;
/*     */                 case WEST:
/* 342 */                   j2 = BlockPosition.a(j, k + l1, l + i2);
/* 343 */                   k2 = BlockPosition.a(j - 1, k + l1, l + i2);
/*     */                   break;
/*     */                 default:
/* 346 */                   j2 = BlockPosition.a(j + 16 - 1, k + l1, l + i2);
/* 347 */                   k2 = BlockPosition.a(j + 16, k + l1, l + i2);
/*     */                   break;
/*     */               } 
/* 350 */               lightenginelayer.a(j2, k2, lightenginelayer.b(j2, k2, lightenginelayer.c(j2)), false);
/* 351 */               lightenginelayer.a(k2, j2, lightenginelayer.b(k2, j2, lightenginelayer.c(k2)), false);
/*     */             } 
/*     */           } 
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void k(long i) {}
/*     */   
/*     */   protected void l(long i) {}
/*     */   
/*     */   protected void b(long i, boolean flag) {}
/*     */   
/*     */   public void c(long i, boolean flag) {
/* 367 */     if (flag) {
/* 368 */       this.o.add(i);
/*     */     } else {
/* 370 */       this.o.remove(i);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(long i, @Nullable NibbleArray nibblearray, boolean flag) {
/* 376 */     if (nibblearray != null) {
/* 377 */       NibbleArray remove = (NibbleArray)this.i.put(i, nibblearray); if (remove != null && remove.cleaner != null) remove.cleaner.run(); 
/* 378 */       if (!flag) {
/* 379 */         this.n.add(i);
/*     */       }
/*     */     } else {
/* 382 */       NibbleArray remove = (NibbleArray)this.i.remove(i); if (remove != null && remove.cleaner != null) remove.cleaner.run();
/*     */     
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void d(long i, boolean flag) {
/* 388 */     boolean flag1 = this.b.contains(i);
/*     */     
/* 390 */     if (!flag1 && !flag) {
/* 391 */       this.d.add(i);
/* 392 */       a(Long.MAX_VALUE, i, 0, true);
/*     */     } 
/*     */     
/* 395 */     if (flag1 && flag) {
/* 396 */       this.c.add(i);
/* 397 */       a(Long.MAX_VALUE, i, 2, false);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void d() {
/* 403 */     if (b()) {
/* 404 */       b(2147483647);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void e() {
/* 410 */     if (!this.g.isEmpty()) {
/* 411 */       synchronized (this.visibleUpdateLock) {
/* 412 */         M m0 = this.f.b();
/*     */         
/* 414 */         m0.d();
/* 415 */         this.e_visible = m0;
/*     */       } 
/* 417 */       this.g.clear();
/*     */     } 
/*     */     
/* 420 */     if (!this.h.isEmpty()) {
/* 421 */       LongIterator longiterator = this.h.iterator();
/*     */       
/* 423 */       while (longiterator.hasNext()) {
/* 424 */         long i = longiterator.nextLong();
/*     */         
/* 426 */         this.m.a(this.l, SectionPosition.a(i));
/*     */       } 
/*     */       
/* 429 */       this.h.clear();
/*     */     } 
/*     */   }
/*     */   
/*     */   protected abstract int d(long paramLong);
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\LightEngineStorage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
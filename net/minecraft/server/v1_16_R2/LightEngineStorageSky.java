/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.destroystokyo.paper.util.map.QueuedChangesMapLong2Int;
/*     */ import com.destroystokyo.paper.util.map.QueuedChangesMapLong2Object;
/*     */ import it.unimi.dsi.fastutil.longs.LongIterator;
/*     */ import it.unimi.dsi.fastutil.longs.LongOpenHashSet;
/*     */ import it.unimi.dsi.fastutil.longs.LongSet;
/*     */ import java.util.Arrays;
/*     */ 
/*     */ public class LightEngineStorageSky
/*     */   extends LightEngineStorage<LightEngineStorageSky.a> {
/*  12 */   private static final EnumDirection[] k = new EnumDirection[] { EnumDirection.NORTH, EnumDirection.SOUTH, EnumDirection.WEST, EnumDirection.EAST };
/*  13 */   private final LongSet l = (LongSet)new LongOpenHashSet();
/*  14 */   private final LongSet m = (LongSet)new LongOpenHashSet();
/*  15 */   private final LongSet n = (LongSet)new LongOpenHashSet();
/*  16 */   private final LongSet o = (LongSet)new LongOpenHashSet();
/*     */   private volatile boolean p;
/*     */   
/*     */   protected LightEngineStorageSky(ILightAccess ilightaccess) {
/*  20 */     super(EnumSkyBlock.SKY, ilightaccess, new a(new QueuedChangesMapLong2Object(), new QueuedChangesMapLong2Int(), 2147483647, false));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected int d(long i) {
/*  26 */     int baseX = (int)(i >> 38L);
/*  27 */     int baseY = (int)(i << 52L >> 52L);
/*  28 */     int baseZ = (int)(i << 26L >> 38L);
/*  29 */     long j = SectionPosition.blockPosAsSectionLong(baseX, baseY, baseZ);
/*     */     
/*  31 */     int k = SectionPosition.c(j);
/*  32 */     synchronized (this.visibleUpdateLock) {
/*  33 */       a lightenginestoragesky_a = this.e_visible;
/*  34 */       int l = lightenginestoragesky_a.otherData.getVisibleAsync(SectionPosition.f(j));
/*     */       
/*  36 */       if (l != lightenginestoragesky_a.b && k < l) {
/*  37 */         NibbleArray nibblearray = a(lightenginestoragesky_a, j);
/*     */         
/*  39 */         if (nibblearray == null) {
/*  40 */           for (i = BlockPosition.f(i); nibblearray == null; nibblearray = a(lightenginestoragesky_a, j)) {
/*  41 */             j = SectionPosition.a(j, EnumDirection.UP);
/*  42 */             k++;
/*  43 */             if (k >= l) {
/*  44 */               return 15;
/*     */             }
/*     */             
/*  47 */             i = BlockPosition.a(i, 0, 16, 0);
/*     */           } 
/*     */         }
/*     */         
/*  51 */         return nibblearray.a(baseX & 0xF, (int)(i << 52L >> 52L) & 0xF, baseZ & 0xF);
/*     */       } 
/*  53 */       return 15;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void k(long i) {
/*  60 */     int j = SectionPosition.c(i);
/*     */     
/*  62 */     if (this.f.b > j) {
/*  63 */       this.f.b = j;
/*  64 */       this.f.otherData.queueDefaultReturnValue(this.f.b);
/*     */     } 
/*     */     
/*  67 */     long k = SectionPosition.f(i);
/*  68 */     int l = this.f.otherData.getUpdating(k);
/*     */     
/*  70 */     if (l < j + 1) {
/*  71 */       this.f.otherData.queueUpdate(k, j + 1);
/*  72 */       if (this.o.contains(k)) {
/*  73 */         q(i);
/*  74 */         if (l > this.f.b) {
/*  75 */           long i1 = SectionPosition.b(SectionPosition.b(i), l - 1, SectionPosition.d(i));
/*     */           
/*  77 */           p(i1);
/*     */         } 
/*     */         
/*  80 */         f();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void p(long i) {
/*  87 */     this.n.add(i);
/*  88 */     this.m.remove(i);
/*     */   }
/*     */   
/*     */   private void q(long i) {
/*  92 */     this.m.add(i);
/*  93 */     this.n.remove(i);
/*     */   }
/*     */   
/*     */   private void f() {
/*  97 */     this.p = (!this.m.isEmpty() || !this.n.isEmpty());
/*     */   }
/*     */ 
/*     */   
/*     */   protected void l(long i) {
/* 102 */     long j = SectionPosition.f(i);
/* 103 */     boolean flag = this.o.contains(j);
/*     */     
/* 105 */     if (flag) {
/* 106 */       p(i);
/*     */     }
/*     */     
/* 109 */     int k = SectionPosition.c(i);
/*     */     
/* 111 */     if (this.f.otherData.getUpdating(j) == k + 1) {
/*     */       long l;
/*     */       
/* 114 */       for (l = i; !g(l) && a(k); l = SectionPosition.a(l, EnumDirection.DOWN)) {
/* 115 */         k--;
/*     */       }
/*     */       
/* 118 */       if (g(l)) {
/* 119 */         this.f.otherData.queueUpdate(j, k + 1);
/* 120 */         if (flag) {
/* 121 */           q(l);
/*     */         }
/*     */       } else {
/* 124 */         this.f.otherData.queueRemove(j);
/*     */       } 
/*     */     } 
/*     */     
/* 128 */     if (flag) {
/* 129 */       f();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void b(long i, boolean flag) {
/* 136 */     d();
/* 137 */     if (flag && this.o.add(i)) {
/* 138 */       int j = this.f.otherData.getUpdating(i);
/*     */       
/* 140 */       if (j != this.f.b) {
/* 141 */         long k = SectionPosition.b(SectionPosition.b(i), j - 1, SectionPosition.d(i));
/*     */         
/* 143 */         q(k);
/* 144 */         f();
/*     */       } 
/* 146 */     } else if (!flag) {
/* 147 */       this.o.remove(i);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean a() {
/* 154 */     return (super.a() || this.p);
/*     */   }
/*     */ 
/*     */   
/*     */   protected NibbleArray j(long i) {
/* 159 */     NibbleArray nibblearray = (NibbleArray)this.i.get(i);
/*     */     
/* 161 */     if (nibblearray != null) {
/* 162 */       return nibblearray;
/*     */     }
/* 164 */     long j = SectionPosition.a(i, EnumDirection.UP);
/* 165 */     int k = this.f.otherData.getUpdating(SectionPosition.f(i));
/*     */     
/* 167 */     if (k != this.f.b && SectionPosition.c(j) < k) {
/*     */       NibbleArray nibblearray1;
/*     */       
/* 170 */       while ((nibblearray1 = this.updating.getUpdatingOptimized(j)) == null) {
/* 171 */         j = SectionPosition.a(j, EnumDirection.UP);
/*     */       }
/*     */       
/* 174 */       return (new NibbleArray()).markPoolSafe((new NibbleArrayFlat(nibblearray1, 0)).asBytes());
/*     */     } 
/* 176 */     return (new NibbleArray()).markPoolSafe();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void a(LightEngineLayer<a, ?> lightenginelayer, boolean flag, boolean flag1) {
/* 183 */     super.a(lightenginelayer, flag, flag1);
/* 184 */     if (flag) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 190 */       if (!this.m.isEmpty()) {
/* 191 */         LongIterator longiterator = this.m.iterator();
/*     */         
/* 193 */         while (longiterator.hasNext()) {
/* 194 */           long i = longiterator.nextLong();
/* 195 */           int baseX = (int)(i >> 42L) << 4;
/* 196 */           int baseY = (int)(i << 44L >> 44L) << 4;
/* 197 */           int baseZ = (int)(i << 22L >> 42L) << 4;
/* 198 */           int j = c(i);
/* 199 */           if (j != 2 && !this.n.contains(i) && this.l.add(i)) {
/*     */ 
/*     */             
/* 202 */             if (j == 1) {
/* 203 */               a(lightenginelayer, i);
/* 204 */               if (this.g.add(i)) {
/* 205 */                 this.f.a(i);
/*     */               }
/*     */               
/* 208 */               Arrays.fill(this.updating.getUpdatingOptimized(i).asBytesPoolSafe(), (byte)-1);
/* 209 */               int m = baseX;
/* 210 */               int l = baseY;
/* 211 */               int i1 = baseZ;
/* 212 */               EnumDirection[] aenumdirection = LightEngineStorageSky.k;
/* 213 */               int j1 = aenumdirection.length;
/*     */ 
/*     */ 
/*     */               
/* 217 */               for (int l1 = 0; l1 < j1; l1++) {
/* 218 */                 EnumDirection enumdirection = aenumdirection[l1];
/*     */                 
/* 220 */                 long k1 = SectionPosition.getAdjacentFromBlockPos(baseX, baseY, baseZ, enumdirection);
/* 221 */                 if ((this.n.contains(k1) || (!this.l.contains(k1) && !this.m.contains(k1))) && g(k1)) {
/* 222 */                   for (int i2 = 0; i2 < 16; i2++) {
/* 223 */                     for (int j2 = 0; j2 < 16; j2++) {
/*     */                       long k2, l2;
/*     */ 
/*     */                       
/* 227 */                       switch (enumdirection) {
/*     */                         case NORTH:
/* 229 */                           k2 = BlockPosition.a(m + i2, l + j2, i1);
/* 230 */                           l2 = BlockPosition.a(m + i2, l + j2, i1 - 1);
/*     */                           break;
/*     */                         case SOUTH:
/* 233 */                           k2 = BlockPosition.a(m + i2, l + j2, i1 + 16 - 1);
/* 234 */                           l2 = BlockPosition.a(m + i2, l + j2, i1 + 16);
/*     */                           break;
/*     */                         case WEST:
/* 237 */                           k2 = BlockPosition.a(m, l + i2, i1 + j2);
/* 238 */                           l2 = BlockPosition.a(m - 1, l + i2, i1 + j2);
/*     */                           break;
/*     */                         default:
/* 241 */                           k2 = BlockPosition.a(m + 16 - 1, l + i2, i1 + j2);
/* 242 */                           l2 = BlockPosition.a(m + 16, l + i2, i1 + j2);
/*     */                           break;
/*     */                       } 
/* 245 */                       lightenginelayer.a(k2, l2, lightenginelayer.b(k2, l2, 0), true);
/*     */                     } 
/*     */                   } 
/*     */                 }
/*     */               } 
/*     */               
/* 251 */               for (int i3 = 0; i3 < 16; i3++) {
/* 252 */                 for (j1 = 0; j1 < 16; j1++) {
/* 253 */                   long j3 = BlockPosition.a(baseX + i3, baseY, baseZ + j1);
/*     */                   
/* 255 */                   long k1 = BlockPosition.a(baseX + i3, baseY - 1, baseZ + j1);
/* 256 */                   lightenginelayer.a(j3, k1, lightenginelayer.b(j3, k1, 0), true);
/*     */                 } 
/*     */               }  continue;
/*     */             } 
/* 260 */             for (int k = 0; k < 16; k++) {
/* 261 */               for (int l = 0; l < 16; l++) {
/* 262 */                 long k3 = BlockPosition.a(baseX + k, baseY + 16 - 1, baseZ + l);
/*     */                 
/* 264 */                 lightenginelayer.a(Long.MAX_VALUE, k3, 0, true);
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 272 */       this.m.clear();
/* 273 */       if (!this.n.isEmpty()) {
/* 274 */         LongIterator longiterator = this.n.iterator();
/*     */         
/* 276 */         while (longiterator.hasNext()) {
/* 277 */           long i = longiterator.nextLong();
/* 278 */           int baseX = (int)(i >> 42L) << 4;
/* 279 */           int baseY = (int)(i << 44L >> 44L) << 4;
/* 280 */           int baseZ = (int)(i << 22L >> 42L) << 4;
/* 281 */           if (this.l.remove(i) && g(i)) {
/* 282 */             for (int j = 0; j < 16; j++) {
/* 283 */               for (int k = 0; k < 16; k++) {
/* 284 */                 long l3 = BlockPosition.a(baseX + j, baseY + 16 - 1, baseZ + k);
/*     */                 
/* 286 */                 lightenginelayer.a(Long.MAX_VALUE, l3, 15, false);
/*     */               } 
/*     */             } 
/*     */           }
/*     */         } 
/*     */       } 
/*     */       
/* 293 */       this.n.clear();
/* 294 */       this.p = false;
/*     */     } 
/*     */   }
/*     */   
/*     */   protected boolean a(int i) {
/* 299 */     return (i >= this.f.b);
/*     */   }
/*     */   
/*     */   protected boolean m(long i) {
/* 303 */     int j = BlockPosition.c(i);
/*     */     
/* 305 */     if ((j & 0xF) != 15) {
/* 306 */       return false;
/*     */     }
/* 308 */     long k = SectionPosition.e(i);
/* 309 */     long l = SectionPosition.f(k);
/*     */     
/* 311 */     if (!this.o.contains(l)) {
/* 312 */       return false;
/*     */     }
/* 314 */     int i1 = this.f.otherData.getUpdating(l);
/*     */     
/* 316 */     return (SectionPosition.c(i1) == j + 16);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean n(long i) {
/* 322 */     long j = SectionPosition.f(i);
/* 323 */     int k = this.f.otherData.getUpdating(j);
/*     */     
/* 325 */     return (k == this.f.b || SectionPosition.c(i) >= k);
/*     */   }
/*     */   
/*     */   protected boolean o(long i) {
/* 329 */     long j = SectionPosition.f(i);
/*     */     
/* 331 */     return this.o.contains(j);
/*     */   }
/*     */   
/*     */   public static final class a
/*     */     extends LightEngineStorageArray<a>
/*     */   {
/*     */     private int b;
/*     */     private final QueuedChangesMapLong2Int otherData;
/*     */     
/*     */     public a(QueuedChangesMapLong2Object<NibbleArray> data, QueuedChangesMapLong2Int otherData, int i, boolean isVisible) {
/* 341 */       super(data, isVisible);
/* 342 */       this.otherData = otherData;
/* 343 */       otherData.queueDefaultReturnValue(i);
/*     */       
/* 345 */       this.b = i;
/*     */     }
/*     */ 
/*     */     
/*     */     public a b() {
/* 350 */       this.otherData.performUpdatesLockMap();
/* 351 */       return new a(this.data, this.otherData, this.b, true);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\LightEngineStorageSky.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
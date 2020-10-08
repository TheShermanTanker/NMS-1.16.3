/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.destroystokyo.paper.util.map.QueuedChangesMapLong2Object;
/*     */ import java.util.Objects;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ public abstract class LightEngineStorageArray<M extends LightEngineStorageArray<M>> {
/*     */   private final NibbleArray[] c;
/*   9 */   private final NibbleArray[] cache = this.c = new NibbleArray[] { NibbleArray.EMPTY_NIBBLE_ARRAY, NibbleArray.EMPTY_NIBBLE_ARRAY };
/*     */ 
/*     */   
/*     */   private boolean d;
/*     */   
/*     */   protected final QueuedChangesMapLong2Object<NibbleArray> data;
/*     */   
/*     */   protected final boolean isVisible;
/*     */   
/*     */   public final NibbleArrayAccess lookup;
/*     */ 
/*     */   
/*     */   protected LightEngineStorageArray(QueuedChangesMapLong2Object<NibbleArray> data, boolean isVisible) {
/*  22 */     if (isVisible) {
/*  23 */       data.performUpdatesLockMap();
/*     */     }
/*  25 */     this.data = data;
/*  26 */     this.isVisible = isVisible;
/*     */ 
/*     */     
/*  29 */     if (isVisible) {
/*  30 */       Objects.requireNonNull(data); this.lookup = data::getVisibleAsync;
/*     */     } else {
/*  32 */       Objects.requireNonNull(data.getUpdatingMap()); this.lookup = data.getUpdatingMap()::get;
/*     */     } 
/*     */     
/*  35 */     c();
/*  36 */     this.d = true;
/*     */   }
/*     */   
/*     */   public abstract M b();
/*     */   
/*     */   public void a(long i) {
/*  42 */     if (this.isVisible) throw new IllegalStateException("writing to visible data"); 
/*  43 */     NibbleArray updating = (NibbleArray)this.data.getUpdating(i);
/*  44 */     NibbleArray nibblearray = (new NibbleArray()).markPoolSafe(updating.getCloneIfSet());
/*  45 */     nibblearray.lightCacheKey = i;
/*  46 */     this.data.queueUpdate(i, nibblearray);
/*  47 */     if (updating.cleaner != null) MCUtil.scheduleTask(2, updating.cleaner, "Light Engine Release"); 
/*  48 */     c();
/*     */   }
/*     */   
/*     */   public boolean b(long i) {
/*  52 */     return (this.lookup.apply(i) != null);
/*     */   }
/*     */ 
/*     */   
/*     */   public final NibbleArray getUpdatingOptimized(long i) {
/*  57 */     NibbleArray[] cache = this.cache;
/*  58 */     if ((cache[0]).lightCacheKey == i) return cache[0]; 
/*  59 */     if ((cache[1]).lightCacheKey == i) return cache[1];
/*     */     
/*  61 */     NibbleArray nibblearray = this.lookup.apply(i);
/*  62 */     if (nibblearray == null) {
/*  63 */       return null;
/*     */     }
/*  65 */     cache[1] = cache[0];
/*  66 */     cache[0] = nibblearray;
/*  67 */     return nibblearray;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public final NibbleArray c(long i) {
/*  75 */     if (this.d)
/*     */     {
/*  77 */       return getUpdatingOptimized(i);
/*     */     }
/*     */     
/*  80 */     return this.lookup.apply(i);
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public NibbleArray d(long i) {
/*  86 */     if (this.isVisible) throw new IllegalStateException("writing to visible data"); 
/*  87 */     return (NibbleArray)this.data.queueRemove(i);
/*     */   }
/*     */   
/*     */   public void a(long i, NibbleArray nibblearray) {
/*  91 */     if (this.isVisible) throw new IllegalStateException("writing to visible data"); 
/*  92 */     nibblearray.lightCacheKey = i;
/*  93 */     this.data.queueUpdate(i, nibblearray);
/*     */   }
/*     */   
/*     */   public void c() {
/*  97 */     for (int i = 0; i < 2; i++)
/*     */     {
/*  99 */       this.c[i] = NibbleArray.EMPTY_NIBBLE_ARRAY;
/*     */     }
/*     */   }
/*     */   
/*     */   public void d() {
/* 104 */     this.d = false;
/*     */   }
/*     */   
/*     */   public static interface NibbleArrayAccess {
/*     */     NibbleArray apply(long param1Long);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\LightEngineStorageArray.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
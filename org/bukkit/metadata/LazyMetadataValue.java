/*     */ package org.bukkit.metadata;
/*     */ 
/*     */ import java.lang.ref.SoftReference;
/*     */ import java.util.concurrent.Callable;
/*     */ import org.apache.commons.lang.Validate;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
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
/*     */ public class LazyMetadataValue
/*     */   extends MetadataValueAdapter
/*     */ {
/*     */   private Callable<Object> lazyValue;
/*     */   private CacheStrategy cacheStrategy;
/*     */   private SoftReference<Object> internalValue;
/*  25 */   private static final Object ACTUALLY_NULL = new Object();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public LazyMetadataValue(@NotNull Plugin owningPlugin, @NotNull Callable<Object> lazyValue) {
/*  36 */     this(owningPlugin, CacheStrategy.CACHE_AFTER_FIRST_EVAL, lazyValue);
/*     */   }
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
/*     */   public LazyMetadataValue(@NotNull Plugin owningPlugin, @NotNull CacheStrategy cacheStrategy, @NotNull Callable<Object> lazyValue) {
/*  49 */     super(owningPlugin);
/*  50 */     Validate.notNull(cacheStrategy, "cacheStrategy cannot be null");
/*  51 */     Validate.notNull(lazyValue, "lazyValue cannot be null");
/*  52 */     this.internalValue = new SoftReference(null);
/*  53 */     this.lazyValue = lazyValue;
/*  54 */     this.cacheStrategy = cacheStrategy;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected LazyMetadataValue(@NotNull Plugin owningPlugin) {
/*  64 */     super(owningPlugin);
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public Object value() {
/*  70 */     eval();
/*  71 */     Object value = this.internalValue.get();
/*  72 */     if (value == ACTUALLY_NULL) {
/*  73 */       return null;
/*     */     }
/*  75 */     return value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private synchronized void eval() throws MetadataEvaluationException {
/*  85 */     if (this.cacheStrategy == CacheStrategy.NEVER_CACHE || this.internalValue.get() == null) {
/*     */       try {
/*  87 */         Object value = this.lazyValue.call();
/*  88 */         if (value == null) {
/*  89 */           value = ACTUALLY_NULL;
/*     */         }
/*  91 */         this.internalValue = new SoftReference(value);
/*  92 */       } catch (Exception e) {
/*  93 */         throw new MetadataEvaluationException(e);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public synchronized void invalidate() {
/* 100 */     if (this.cacheStrategy != CacheStrategy.CACHE_ETERNALLY) {
/* 101 */       this.internalValue.clear();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public enum CacheStrategy
/*     */   {
/* 113 */     CACHE_AFTER_FIRST_EVAL,
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 118 */     NEVER_CACHE,
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 124 */     CACHE_ETERNALLY;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\metadata\LazyMetadataValue.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
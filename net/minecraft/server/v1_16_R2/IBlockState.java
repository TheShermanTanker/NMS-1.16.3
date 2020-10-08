/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.base.MoreObjects;
/*     */ import com.mojang.serialization.Codec;
/*     */ import com.mojang.serialization.DataResult;
/*     */ import java.util.Collection;
/*     */ import java.util.Optional;
/*     */ import java.util.concurrent.atomic.AtomicInteger;
/*     */ import java.util.stream.Stream;
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
/*     */ public abstract class IBlockState<T extends Comparable<T>>
/*     */ {
/*     */   private final Class<T> a;
/*     */   private final String b;
/*     */   private Integer c;
/*     */   private final Codec<T> d;
/*     */   private final Codec<a<T>> e;
/*     */   
/*     */   protected IBlockState(String s, Class<T> oclass) {
/*  66 */     this.hashCode = 92821 * hashId.getAndIncrement(); this.d = Codec.STRING.comapFlatMap(s1 -> (DataResult)b(s1).<DataResult>map(DataResult::success).orElseGet(()), this::a); this.e = this.d.xmap(this::b, param -> param.b()); this.a = oclass; this.b = s;
/*     */   }
/*  68 */   public a<T> b(T t0) { return new a<>(this, (Comparable)t0); } public a<T> a(IBlockDataHolder<?, ?> iblockdataholder) { return new a<>(this, iblockdataholder.get(this)); } public Stream<a<T>> c() { return getValues().stream().map(this::b); } public Codec<a<T>> e() { return this.e; } public String getName() { return this.b; } public final int hashCode() { if (this.c == null) {
/*  69 */       this.c = Integer.valueOf(b());
/*     */     }
/*     */     
/*  72 */     return this.hashCode; }
/*     */   public Class<T> getType() { return this.a; }
/*     */   public String toString() { return MoreObjects.toStringHelper(this).add("name", this.b).add("clazz", this.a).add("values", getValues()).toString(); }
/*     */   public boolean equals(Object object) { return (this == object); } private static final AtomicInteger hashId = new AtomicInteger(1); private final int hashCode; public int b() {
/*  76 */     return 31 * this.a.hashCode() + this.b.hashCode();
/*     */   }
/*     */   public abstract Collection<T> getValues();
/*     */   public abstract String a(T paramT);
/*     */   public abstract Optional<T> b(String paramString);
/*     */   public static final class a<T extends Comparable<T>> { private final IBlockState<T> a;
/*     */     private final T b;
/*     */     
/*     */     private a(IBlockState<T> iblockstate, T t0) {
/*  85 */       if (!iblockstate.getValues().contains(t0)) {
/*  86 */         throw new IllegalArgumentException("Value " + t0 + " does not belong to property " + iblockstate);
/*     */       }
/*  88 */       this.a = iblockstate;
/*  89 */       this.b = t0;
/*     */     }
/*     */ 
/*     */     
/*     */     public IBlockState<T> a() {
/*  94 */       return this.a;
/*     */     }
/*     */     
/*     */     public T b() {
/*  98 */       return this.b;
/*     */     }
/*     */     
/*     */     public String toString() {
/* 102 */       return this.a.getName() + "=" + this.a.a(this.b);
/*     */     }
/*     */     
/*     */     public boolean equals(Object object) {
/* 106 */       if (this == object)
/* 107 */         return true; 
/* 108 */       if (!(object instanceof a)) {
/* 109 */         return false;
/*     */       }
/* 111 */       a<?> iblockstate_a = (a)object;
/*     */       
/* 113 */       return (this.a == iblockstate_a.a && this.b.equals(iblockstate_a.b));
/*     */     }
/*     */ 
/*     */     
/*     */     public int hashCode() {
/* 118 */       int i = this.a.hashCode();
/*     */       
/* 120 */       i = 31 * i + this.b.hashCode();
/* 121 */       return i;
/*     */     } }
/*     */ 
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\IBlockState.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
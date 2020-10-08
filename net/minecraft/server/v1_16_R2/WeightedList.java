/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.mojang.datafixers.util.Pair;
/*     */ import com.mojang.serialization.Codec;
/*     */ import com.mojang.serialization.DataResult;
/*     */ import com.mojang.serialization.Dynamic;
/*     */ import com.mojang.serialization.DynamicOps;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Comparator;
/*     */ import java.util.List;
/*     */ import java.util.Objects;
/*     */ import java.util.Random;
/*     */ import java.util.function.Function;
/*     */ import java.util.function.Supplier;
/*     */ import java.util.stream.Stream;
/*     */ 
/*     */ public class WeightedList<U> {
/*     */   protected final List<a<U>> list;
/*     */   
/*     */   public WeightedList() {
/*  22 */     this(true);
/*     */   } private final Random b; private final boolean isUnsafe; public WeightedList(boolean isUnsafe) {
/*  24 */     this(Lists.newArrayList(), isUnsafe);
/*     */   }
/*     */   private WeightedList(List<a<U>> list) {
/*  27 */     this(list, true);
/*     */   } private WeightedList(List<a<U>> list, boolean isUnsafe) {
/*  29 */     this.isUnsafe = isUnsafe;
/*     */     
/*  31 */     this.b = new Random();
/*  32 */     this.list = Lists.newArrayList(list);
/*     */   }
/*     */   
/*     */   public static <U> Codec<WeightedList<U>> a(Codec<U> codec) {
/*  36 */     return a.<E>a(codec).listOf().xmap(WeightedList::new, weightedlist -> weightedlist.list);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public WeightedList<U> a(U u0, int i) {
/*  42 */     this.list.add(new a<>(u0, i));
/*  43 */     return this;
/*     */   }
/*     */   
/*     */   public WeightedList<U> a() {
/*  47 */     return a(this.b);
/*     */   }
/*     */ 
/*     */   
/*     */   public WeightedList<U> a(Random random) {
/*  52 */     List<a<U>> list = this.isUnsafe ? new ArrayList<>(this.list) : this.list;
/*  53 */     list.forEach(weightedlist_a -> weightedlist_a.a(random.nextFloat()));
/*  54 */     list.sort(Comparator.comparingDouble(rec$ -> ((a)rec$).c()));
/*  55 */     return this.isUnsafe ? new WeightedList(list, this.isUnsafe) : this;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean b() {
/*  60 */     return this.list.isEmpty();
/*     */   }
/*     */   
/*     */   public Stream<U> c() {
/*  64 */     return this.list.stream().map(a::a);
/*     */   }
/*     */   
/*     */   public U b(Random random) {
/*  68 */     return (U)a(random).c().findFirst().orElseThrow(RuntimeException::new);
/*     */   }
/*     */   
/*     */   public String toString() {
/*  72 */     return "WeightedList[" + this.list + "]";
/*     */   }
/*     */   
/*     */   public static class a<T>
/*     */   {
/*     */     private final T a;
/*     */     private final int b;
/*     */     private double c;
/*     */     
/*     */     private a(T t0, int i) {
/*  82 */       this.b = i;
/*  83 */       this.a = t0;
/*     */     }
/*     */     
/*     */     private double c() {
/*  87 */       return this.c;
/*     */     }
/*     */     
/*     */     private void a(float f) {
/*  91 */       this.c = -Math.pow(f, (1.0F / this.b));
/*     */     }
/*     */     
/*     */     public T a() {
/*  95 */       return this.a;
/*     */     }
/*     */     
/*     */     public String toString() {
/*  99 */       return "" + this.b + ":" + this.a;
/*     */     }
/*     */     
/*     */     public static <E> Codec<a<E>> a(final Codec<E> codec) {
/* 103 */       return new Codec<a<E>>() {
/*     */           public <T> DataResult<Pair<WeightedList.a<E>, T>> decode(DynamicOps<T> dynamicops, T t0) {
/* 105 */             Dynamic<T> dynamic = new Dynamic(dynamicops, t0);
/* 106 */             Objects.requireNonNull(codec); return dynamic.get("data").flatMap(codec::parse).map(object -> new WeightedList.a(object, dynamic.get("weight").asInt(1)))
/*     */               
/* 108 */               .map(weightedlist_a -> Pair.of(weightedlist_a, dynamicops.empty()));
/*     */           }
/*     */ 
/*     */ 
/*     */           
/*     */           public <T> DataResult<T> encode(WeightedList.a<E> weightedlist_a, DynamicOps<T> dynamicops, T t0) {
/* 114 */             return dynamicops.mapBuilder().add("weight", dynamicops.createInt(weightedlist_a.b)).add("data", codec.encodeStart(dynamicops, weightedlist_a.a)).build(t0);
/*     */           }
/*     */         };
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WeightedList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
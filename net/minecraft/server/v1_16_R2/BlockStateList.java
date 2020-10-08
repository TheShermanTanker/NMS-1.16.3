/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.base.MoreObjects;
/*     */ import com.google.common.collect.ImmutableList;
/*     */ import com.google.common.collect.ImmutableMap;
/*     */ import com.google.common.collect.ImmutableSortedMap;
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Maps;
/*     */ import com.google.common.collect.UnmodifiableIterator;
/*     */ import com.mojang.datafixers.util.Pair;
/*     */ import com.mojang.serialization.Codec;
/*     */ import com.mojang.serialization.Decoder;
/*     */ import com.mojang.serialization.Encoder;
/*     */ import com.mojang.serialization.MapCodec;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.function.Function;
/*     */ import java.util.function.Supplier;
/*     */ import java.util.regex.Pattern;
/*     */ import java.util.stream.Collectors;
/*     */ import java.util.stream.Stream;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ public class BlockStateList<O, S extends IBlockDataHolder<O, S>>
/*     */ {
/*  28 */   private static final Pattern a = Pattern.compile("^[a-z0-9_]+$");
/*     */   
/*     */   private final O b;
/*     */   private final ImmutableSortedMap<String, IBlockState<?>> c;
/*     */   private final ImmutableList<S> d;
/*     */   
/*     */   protected BlockStateList(Function<O, S> var0, O var1, b<O, S> var2, Map<String, IBlockState<?>> var3) {
/*  35 */     this.b = var1;
/*  36 */     this.c = ImmutableSortedMap.copyOf(var3);
/*     */     
/*  38 */     Supplier<S> var4 = () -> (IBlockDataHolder)var0.apply(var1);
/*  39 */     MapCodec<S> var5 = MapCodec.of(Encoder.empty(), Decoder.unit(var4));
/*  40 */     for (UnmodifiableIterator<Map.Entry<String, IBlockState<?>>> unmodifiableIterator = this.c.entrySet().iterator(); unmodifiableIterator.hasNext(); ) { Map.Entry<String, IBlockState<?>> entry = unmodifiableIterator.next();
/*  41 */       var5 = a(var5, var4, entry.getKey(), (IBlockState<Comparable>)entry.getValue()); }
/*     */ 
/*     */     
/*  44 */     MapCodec<S> var6 = var5;
/*     */ 
/*     */     
/*  47 */     Map<Map<IBlockState<?>, Comparable<?>>, S> var7 = Maps.newLinkedHashMap();
/*  48 */     List<S> var8 = Lists.newArrayList();
/*     */     
/*  50 */     Stream<List<Pair<IBlockState<?>, Comparable<?>>>> var9 = Stream.of(Collections.emptyList());
/*  51 */     for (UnmodifiableIterator<IBlockState> unmodifiableIterator1 = this.c.values().iterator(); unmodifiableIterator1.hasNext(); ) { IBlockState<?> var11 = unmodifiableIterator1.next();
/*  52 */       var9 = var9.flatMap(var1 -> var0.getValues().stream().map(())); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  59 */     var9.forEach(var5 -> {
/*     */           ImmutableMap<IBlockState<?>, Comparable<?>> var6 = (ImmutableMap<IBlockState<?>, Comparable<?>>)var5.stream().collect(ImmutableMap.toImmutableMap(Pair::getFirst, Pair::getSecond));
/*     */           
/*     */           IBlockDataHolder iBlockDataHolder = var0.create(var1, var6, var2);
/*     */           
/*     */           var3.put(var6, iBlockDataHolder);
/*     */           var4.add(iBlockDataHolder);
/*     */         });
/*  67 */     for (IBlockDataHolder iBlockDataHolder : var8) {
/*  68 */       iBlockDataHolder.a(var7);
/*     */     }
/*     */     
/*  71 */     this.d = ImmutableList.copyOf(var8);
/*     */   }
/*     */   
/*     */   private static <S extends IBlockDataHolder<?, S>, T extends Comparable<T>> MapCodec<S> a(MapCodec<S> var0, Supplier<S> var1, String var2, IBlockState<T> var3) {
/*  75 */     return Codec.mapPair(var0, var3.e().fieldOf(var2).setPartial(() -> var0.a(var1.get()))).xmap(var1 -> (IBlockDataHolder)((IBlockDataHolder)var1.getFirst()).set(var0, ((IBlockState.a)var1.getSecond()).b()), var1 -> Pair.of(var1, var0.a(var1)));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ImmutableList<S> a() {
/*  82 */     return this.d;
/*     */   }
/*     */   
/*     */   public S getBlockData() {
/*  86 */     return (S)this.d.get(0);
/*     */   }
/*     */   
/*     */   public O getBlock() {
/*  90 */     return this.b;
/*     */   }
/*     */   
/*     */   public Collection<IBlockState<?>> d() {
/*  94 */     return (Collection<IBlockState<?>>)this.c.values();
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/*  99 */     return MoreObjects.toStringHelper(this)
/* 100 */       .add("block", this.b)
/* 101 */       .add("properties", this.c.values().stream().map(IBlockState::getName).collect(Collectors.toList()))
/* 102 */       .toString();
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public IBlockState<?> a(String var0) {
/* 107 */     return (IBlockState)this.c.get(var0);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static class a<O, S extends IBlockDataHolder<O, S>>
/*     */   {
/*     */     private final O a;
/*     */     
/* 116 */     private final Map<String, IBlockState<?>> b = Maps.newHashMap();
/*     */     
/*     */     public a(O var0) {
/* 119 */       this.a = var0;
/*     */     }
/*     */     
/*     */     public a<O, S> a(IBlockState<?>... var0) {
/* 123 */       for (IBlockState<?> var4 : var0) {
/* 124 */         a(var4);
/* 125 */         this.b.put(var4.getName(), var4);
/*     */       } 
/* 127 */       return this;
/*     */     }
/*     */     
/*     */     private <T extends Comparable<T>> void a(IBlockState<T> var0) {
/* 131 */       String var1 = var0.getName();
/* 132 */       if (!BlockStateList.e().matcher(var1).matches()) {
/* 133 */         throw new IllegalArgumentException((new StringBuilder()).append(this.a).append(" has invalidly named property: ").append(var1).toString());
/*     */       }
/*     */       
/* 136 */       Collection<T> var2 = var0.getValues();
/* 137 */       if (var2.size() <= 1) {
/* 138 */         throw new IllegalArgumentException((new StringBuilder()).append(this.a).append(" attempted use property ").append(var1).append(" with <= 1 possible values").toString());
/*     */       }
/*     */       
/* 141 */       for (Comparable comparable : var2) {
/* 142 */         String var5 = var0.a((T)comparable);
/* 143 */         if (!BlockStateList.e().matcher(var5).matches()) {
/* 144 */           throw new IllegalArgumentException((new StringBuilder()).append(this.a).append(" has property: ").append(var1).append(" with invalidly named value: ").append(var5).toString());
/*     */         }
/*     */       } 
/*     */       
/* 148 */       if (this.b.containsKey(var1)) {
/* 149 */         throw new IllegalArgumentException((new StringBuilder()).append(this.a).append(" has duplicate property: ").append(var1).toString());
/*     */       }
/*     */     }
/*     */     
/*     */     public BlockStateList<O, S> a(Function<O, S> var0, BlockStateList.b<O, S> var1) {
/* 154 */       return new BlockStateList<>(var0, this.a, var1, this.b);
/*     */     }
/*     */   }
/*     */   
/*     */   public static interface b<O, S> {
/*     */     S create(O param1O, ImmutableMap<IBlockState<?>, Comparable<?>> param1ImmutableMap, MapCodec<S> param1MapCodec);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockStateList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
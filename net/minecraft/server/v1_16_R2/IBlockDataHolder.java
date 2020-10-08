/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.ArrayTable;
/*     */ import com.google.common.collect.HashBasedTable;
/*     */ import com.google.common.collect.ImmutableMap;
/*     */ import com.google.common.collect.Maps;
/*     */ import com.google.common.collect.Table;
/*     */ import com.google.common.collect.UnmodifiableIterator;
/*     */ import com.mojang.serialization.Codec;
/*     */ import com.mojang.serialization.MapCodec;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Optional;
/*     */ import java.util.function.Function;
/*     */ import java.util.stream.Collectors;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ 
/*     */ public abstract class IBlockDataHolder<O, S>
/*     */ {
/*  23 */   public static final Function<Map.Entry<IBlockState<?>, Comparable<?>>, String> STATE_TO_VALUE = new Function<Map.Entry<IBlockState<?>, Comparable<?>>, String>() {
/*     */       public String apply(@Nullable Map.Entry<IBlockState<?>, Comparable<?>> entry) {
/*  25 */         if (entry == null) {
/*  26 */           return "<NULL>";
/*     */         }
/*  28 */         IBlockState<?> iblockstate = entry.getKey();
/*     */         
/*  30 */         return iblockstate.getName() + "=" + a(iblockstate, entry.getValue());
/*     */       }
/*     */ 
/*     */       
/*     */       private <T extends Comparable<T>> String a(IBlockState<T> iblockstate, T comparable) {
/*  35 */         return iblockstate.a(comparable);
/*     */       }
/*     */     };
/*     */   protected final O c;
/*     */   private final ImmutableMap<IBlockState<?>, Comparable<?>> b;
/*     */   private Table<IBlockState<?>, Comparable<?>, S> e;
/*     */   protected final MapCodec<S> d;
/*     */   
/*     */   protected IBlockDataHolder(O o0, ImmutableMap<IBlockState<?>, Comparable<?>> immutablemap, MapCodec<S> mapcodec) {
/*  44 */     this.c = o0;
/*  45 */     this.b = immutablemap;
/*  46 */     this.d = mapcodec;
/*     */   }
/*     */   
/*     */   public <T extends Comparable<T>> S a(IBlockState<T> iblockstate) {
/*  50 */     return set(iblockstate, a(iblockstate.getValues(), get(iblockstate)));
/*     */   }
/*     */   
/*     */   protected static <T> T a(Collection<T> collection, T t0) {
/*  54 */     Iterator<T> iterator = collection.iterator();
/*     */     
/*     */     do {
/*  57 */       if (!iterator.hasNext()) {
/*  58 */         return iterator.next();
/*     */       }
/*  60 */     } while (!iterator.next().equals(t0));
/*     */     
/*  62 */     if (iterator.hasNext()) {
/*  63 */       return iterator.next();
/*     */     }
/*  65 */     return collection.iterator().next();
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/*  70 */     StringBuilder stringbuilder = new StringBuilder();
/*     */     
/*  72 */     stringbuilder.append(this.c);
/*  73 */     if (!getStateMap().isEmpty()) {
/*  74 */       stringbuilder.append('[');
/*  75 */       stringbuilder.append(getStateMap().entrySet().stream().<CharSequence>map((Function)STATE_TO_VALUE).collect(Collectors.joining(",")));
/*  76 */       stringbuilder.append(']');
/*     */     } 
/*     */     
/*  79 */     return stringbuilder.toString();
/*     */   }
/*     */   
/*     */   public Collection<IBlockState<?>> r() {
/*  83 */     return Collections.unmodifiableCollection((Collection<? extends IBlockState<?>>)this.b.keySet());
/*     */   }
/*     */   public <T extends Comparable<T>> boolean contains(IBlockState<T> iblockstate) {
/*  86 */     return b(iblockstate);
/*     */   } public <T extends Comparable<T>> boolean b(IBlockState<T> iblockstate) {
/*  88 */     return this.b.containsKey(iblockstate);
/*     */   }
/*     */   
/*     */   public <T extends Comparable<T>> T get(IBlockState<T> iblockstate) {
/*  92 */     Comparable<?> comparable = (Comparable)this.b.get(iblockstate);
/*     */     
/*  94 */     if (comparable == null) {
/*  95 */       throw new IllegalArgumentException("Cannot get property " + iblockstate + " as it does not exist in " + this.c);
/*     */     }
/*  97 */     return (T)iblockstate.getType().cast(comparable);
/*     */   }
/*     */ 
/*     */   
/*     */   public <T extends Comparable<T>> Optional<T> d(IBlockState<T> iblockstate) {
/* 102 */     Comparable<?> comparable = (Comparable)this.b.get(iblockstate);
/*     */     
/* 104 */     return (comparable == null) ? Optional.<T>empty() : Optional.<T>of((T)iblockstate.getType().cast(comparable));
/*     */   }
/*     */   
/*     */   public <T extends Comparable<T>, V extends T> S set(IBlockState<T> iblockstate, V v0) {
/* 108 */     Comparable<?> comparable = (Comparable)this.b.get(iblockstate);
/*     */     
/* 110 */     if (comparable == null)
/* 111 */       throw new IllegalArgumentException("Cannot set property " + iblockstate + " as it does not exist in " + this.c); 
/* 112 */     if (comparable == v0) {
/* 113 */       return (S)this;
/*     */     }
/* 115 */     S s0 = (S)this.e.get(iblockstate, v0);
/*     */     
/* 117 */     if (s0 == null) {
/* 118 */       throw new IllegalArgumentException("Cannot set property " + iblockstate + " to " + v0 + " on " + this.c + ", it is not an allowed value");
/*     */     }
/* 120 */     return s0;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(Map<Map<IBlockState<?>, Comparable<?>>, S> map) {
/* 126 */     if (this.e != null) {
/* 127 */       throw new IllegalStateException();
/*     */     }
/* 129 */     HashBasedTable hashBasedTable = HashBasedTable.create();
/* 130 */     UnmodifiableIterator unmodifiableiterator = this.b.entrySet().iterator();
/*     */     
/* 132 */     while (unmodifiableiterator.hasNext()) {
/* 133 */       Map.Entry<IBlockState<?>, Comparable<?>> entry = (Map.Entry<IBlockState<?>, Comparable<?>>)unmodifiableiterator.next();
/* 134 */       IBlockState<?> iblockstate = entry.getKey();
/* 135 */       Iterator<Comparable> iterator = iblockstate.getValues().iterator();
/*     */       
/* 137 */       while (iterator.hasNext()) {
/* 138 */         Comparable<?> comparable = iterator.next();
/*     */         
/* 140 */         if (comparable != entry.getValue()) {
/* 141 */           hashBasedTable.put(iblockstate, comparable, map.get(b(iblockstate, comparable)));
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 146 */     this.e = hashBasedTable.isEmpty() ? (Table<IBlockState<?>, Comparable<?>, S>)hashBasedTable : (Table<IBlockState<?>, Comparable<?>, S>)ArrayTable.create((Table)hashBasedTable);
/*     */   }
/*     */ 
/*     */   
/*     */   private Map<IBlockState<?>, Comparable<?>> b(IBlockState<?> iblockstate, Comparable<?> comparable) {
/* 151 */     Map<IBlockState<?>, Comparable<?>> map = Maps.newHashMap((Map)this.b);
/*     */     
/* 153 */     map.put(iblockstate, comparable);
/* 154 */     return map;
/*     */   }
/*     */   
/*     */   public ImmutableMap<IBlockState<?>, Comparable<?>> getStateMap() {
/* 158 */     return this.b;
/*     */   }
/*     */   
/*     */   protected static <O, S extends IBlockDataHolder<O, S>> Codec<S> a(Codec<O> codec, Function<O, S> function) {
/* 162 */     return codec.dispatch("Name", iblockdataholder -> iblockdataholder.c, object -> {
/*     */           IBlockDataHolder iBlockDataHolder = function.apply(object);
/*     */           return iBlockDataHolder.getStateMap().isEmpty() ? Codec.unit(iBlockDataHolder) : iBlockDataHolder.d.fieldOf("Properties").codec();
/*     */         });
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\IBlockDataHolder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
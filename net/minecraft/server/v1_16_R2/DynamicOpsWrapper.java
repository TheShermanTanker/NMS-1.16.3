/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.mojang.datafixers.util.Pair;
/*     */ import com.mojang.serialization.DataResult;
/*     */ import com.mojang.serialization.DynamicOps;
/*     */ import com.mojang.serialization.ListBuilder;
/*     */ import com.mojang.serialization.MapLike;
/*     */ import com.mojang.serialization.RecordBuilder;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.util.List;
/*     */ import java.util.function.BiConsumer;
/*     */ import java.util.function.Consumer;
/*     */ import java.util.stream.IntStream;
/*     */ import java.util.stream.LongStream;
/*     */ import java.util.stream.Stream;
/*     */ 
/*     */ public abstract class DynamicOpsWrapper<T>
/*     */   implements DynamicOps<T> {
/*     */   protected final DynamicOps<T> a;
/*     */   
/*     */   protected DynamicOpsWrapper(DynamicOps<T> var0) {
/*  22 */     this.a = var0;
/*     */   }
/*     */ 
/*     */   
/*     */   public T empty() {
/*  27 */     return (T)this.a.empty();
/*     */   }
/*     */ 
/*     */   
/*     */   public <U> U convertTo(DynamicOps<U> var0, T var1) {
/*  32 */     return (U)this.a.convertTo(var0, var1);
/*     */   }
/*     */ 
/*     */   
/*     */   public DataResult<Number> getNumberValue(T var0) {
/*  37 */     return this.a.getNumberValue(var0);
/*     */   }
/*     */ 
/*     */   
/*     */   public T createNumeric(Number var0) {
/*  42 */     return (T)this.a.createNumeric(var0);
/*     */   }
/*     */ 
/*     */   
/*     */   public T createByte(byte var0) {
/*  47 */     return (T)this.a.createByte(var0);
/*     */   }
/*     */ 
/*     */   
/*     */   public T createShort(short var0) {
/*  52 */     return (T)this.a.createShort(var0);
/*     */   }
/*     */ 
/*     */   
/*     */   public T createInt(int var0) {
/*  57 */     return (T)this.a.createInt(var0);
/*     */   }
/*     */ 
/*     */   
/*     */   public T createLong(long var0) {
/*  62 */     return (T)this.a.createLong(var0);
/*     */   }
/*     */ 
/*     */   
/*     */   public T createFloat(float var0) {
/*  67 */     return (T)this.a.createFloat(var0);
/*     */   }
/*     */ 
/*     */   
/*     */   public T createDouble(double var0) {
/*  72 */     return (T)this.a.createDouble(var0);
/*     */   }
/*     */ 
/*     */   
/*     */   public DataResult<Boolean> getBooleanValue(T var0) {
/*  77 */     return this.a.getBooleanValue(var0);
/*     */   }
/*     */ 
/*     */   
/*     */   public T createBoolean(boolean var0) {
/*  82 */     return (T)this.a.createBoolean(var0);
/*     */   }
/*     */ 
/*     */   
/*     */   public DataResult<String> getStringValue(T var0) {
/*  87 */     return this.a.getStringValue(var0);
/*     */   }
/*     */ 
/*     */   
/*     */   public T createString(String var0) {
/*  92 */     return (T)this.a.createString(var0);
/*     */   }
/*     */ 
/*     */   
/*     */   public DataResult<T> mergeToList(T var0, T var1) {
/*  97 */     return this.a.mergeToList(var0, var1);
/*     */   }
/*     */ 
/*     */   
/*     */   public DataResult<T> mergeToList(T var0, List<T> var1) {
/* 102 */     return this.a.mergeToList(var0, var1);
/*     */   }
/*     */ 
/*     */   
/*     */   public DataResult<T> mergeToMap(T var0, T var1, T var2) {
/* 107 */     return this.a.mergeToMap(var0, var1, var2);
/*     */   }
/*     */ 
/*     */   
/*     */   public DataResult<T> mergeToMap(T var0, MapLike<T> var1) {
/* 112 */     return this.a.mergeToMap(var0, var1);
/*     */   }
/*     */ 
/*     */   
/*     */   public DataResult<Stream<Pair<T, T>>> getMapValues(T var0) {
/* 117 */     return this.a.getMapValues(var0);
/*     */   }
/*     */ 
/*     */   
/*     */   public DataResult<Consumer<BiConsumer<T, T>>> getMapEntries(T var0) {
/* 122 */     return this.a.getMapEntries(var0);
/*     */   }
/*     */ 
/*     */   
/*     */   public T createMap(Stream<Pair<T, T>> var0) {
/* 127 */     return (T)this.a.createMap(var0);
/*     */   }
/*     */ 
/*     */   
/*     */   public DataResult<MapLike<T>> getMap(T var0) {
/* 132 */     return this.a.getMap(var0);
/*     */   }
/*     */ 
/*     */   
/*     */   public DataResult<Stream<T>> getStream(T var0) {
/* 137 */     return this.a.getStream(var0);
/*     */   }
/*     */ 
/*     */   
/*     */   public DataResult<Consumer<Consumer<T>>> getList(T var0) {
/* 142 */     return this.a.getList(var0);
/*     */   }
/*     */ 
/*     */   
/*     */   public T createList(Stream<T> var0) {
/* 147 */     return (T)this.a.createList(var0);
/*     */   }
/*     */ 
/*     */   
/*     */   public DataResult<ByteBuffer> getByteBuffer(T var0) {
/* 152 */     return this.a.getByteBuffer(var0);
/*     */   }
/*     */ 
/*     */   
/*     */   public T createByteList(ByteBuffer var0) {
/* 157 */     return (T)this.a.createByteList(var0);
/*     */   }
/*     */ 
/*     */   
/*     */   public DataResult<IntStream> getIntStream(T var0) {
/* 162 */     return this.a.getIntStream(var0);
/*     */   }
/*     */ 
/*     */   
/*     */   public T createIntList(IntStream var0) {
/* 167 */     return (T)this.a.createIntList(var0);
/*     */   }
/*     */ 
/*     */   
/*     */   public DataResult<LongStream> getLongStream(T var0) {
/* 172 */     return this.a.getLongStream(var0);
/*     */   }
/*     */ 
/*     */   
/*     */   public T createLongList(LongStream var0) {
/* 177 */     return (T)this.a.createLongList(var0);
/*     */   }
/*     */ 
/*     */   
/*     */   public T remove(T var0, String var1) {
/* 182 */     return (T)this.a.remove(var0, var1);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean compressMaps() {
/* 187 */     return this.a.compressMaps();
/*     */   }
/*     */ 
/*     */   
/*     */   public ListBuilder<T> listBuilder() {
/* 192 */     return this.a.listBuilder();
/*     */   }
/*     */ 
/*     */   
/*     */   public RecordBuilder<T> mapBuilder() {
/* 197 */     return this.a.mapBuilder();
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DynamicOpsWrapper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
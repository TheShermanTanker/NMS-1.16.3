/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.Iterators;
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Maps;
/*     */ import com.google.common.collect.PeekingIterator;
/*     */ import com.mojang.datafixers.DataFixUtils;
/*     */ import com.mojang.datafixers.util.Pair;
/*     */ import com.mojang.serialization.DataResult;
/*     */ import com.mojang.serialization.DynamicOps;
/*     */ import com.mojang.serialization.MapLike;
/*     */ import com.mojang.serialization.RecordBuilder;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.util.Arrays;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Objects;
/*     */ import java.util.function.BiConsumer;
/*     */ import java.util.function.Consumer;
/*     */ import java.util.stream.IntStream;
/*     */ import java.util.stream.LongStream;
/*     */ import java.util.stream.Stream;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ public class DynamicOpsNBT implements DynamicOps<NBTBase> {
/*  27 */   public static final DynamicOpsNBT a = new DynamicOpsNBT();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NBTBase empty() {
/*  34 */     return NBTTagEnd.b;
/*     */   }
/*     */ 
/*     */   
/*     */   public <U> U convertTo(DynamicOps<U> var0, NBTBase var1) {
/*  39 */     switch (var1.getTypeId()) {
/*     */       case 0:
/*  41 */         return (U)var0.empty();
/*     */       case 1:
/*  43 */         return (U)var0.createByte(((NBTNumber)var1).asByte());
/*     */       case 2:
/*  45 */         return (U)var0.createShort(((NBTNumber)var1).asShort());
/*     */       case 3:
/*  47 */         return (U)var0.createInt(((NBTNumber)var1).asInt());
/*     */       case 4:
/*  49 */         return (U)var0.createLong(((NBTNumber)var1).asLong());
/*     */       case 5:
/*  51 */         return (U)var0.createFloat(((NBTNumber)var1).asFloat());
/*     */       case 6:
/*  53 */         return (U)var0.createDouble(((NBTNumber)var1).asDouble());
/*     */       case 7:
/*  55 */         return (U)var0.createByteList(ByteBuffer.wrap(((NBTTagByteArray)var1).getBytes()));
/*     */       case 8:
/*  57 */         return (U)var0.createString(var1.asString());
/*     */       case 9:
/*  59 */         return (U)convertList(var0, var1);
/*     */       case 10:
/*  61 */         return (U)convertMap(var0, var1);
/*     */       case 11:
/*  63 */         return (U)var0.createIntList(Arrays.stream(((NBTTagIntArray)var1).getInts()));
/*     */       case 12:
/*  65 */         return (U)var0.createLongList(Arrays.stream(((NBTTagLongArray)var1).getLongs()));
/*     */     } 
/*  67 */     throw new IllegalStateException("Unknown tag type: " + var1);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public DataResult<Number> getNumberValue(NBTBase var0) {
/*  73 */     if (var0 instanceof NBTNumber) {
/*  74 */       return DataResult.success(((NBTNumber)var0).k());
/*     */     }
/*  76 */     return DataResult.error("Not a number");
/*     */   }
/*     */ 
/*     */   
/*     */   public NBTBase createNumeric(Number var0) {
/*  81 */     return NBTTagDouble.a(var0.doubleValue());
/*     */   }
/*     */ 
/*     */   
/*     */   public NBTBase createByte(byte var0) {
/*  86 */     return NBTTagByte.a(var0);
/*     */   }
/*     */ 
/*     */   
/*     */   public NBTBase createShort(short var0) {
/*  91 */     return NBTTagShort.a(var0);
/*     */   }
/*     */ 
/*     */   
/*     */   public NBTBase createInt(int var0) {
/*  96 */     return NBTTagInt.a(var0);
/*     */   }
/*     */ 
/*     */   
/*     */   public NBTBase createLong(long var0) {
/* 101 */     return NBTTagLong.a(var0);
/*     */   }
/*     */ 
/*     */   
/*     */   public NBTBase createFloat(float var0) {
/* 106 */     return NBTTagFloat.a(var0);
/*     */   }
/*     */ 
/*     */   
/*     */   public NBTBase createDouble(double var0) {
/* 111 */     return NBTTagDouble.a(var0);
/*     */   }
/*     */ 
/*     */   
/*     */   public NBTBase createBoolean(boolean var0) {
/* 116 */     return NBTTagByte.a(var0);
/*     */   }
/*     */ 
/*     */   
/*     */   public DataResult<String> getStringValue(NBTBase var0) {
/* 121 */     if (var0 instanceof NBTTagString) {
/* 122 */       return DataResult.success(var0.asString());
/*     */     }
/* 124 */     return DataResult.error("Not a string");
/*     */   }
/*     */ 
/*     */   
/*     */   public NBTBase createString(String var0) {
/* 129 */     return NBTTagString.a(var0);
/*     */   }
/*     */   
/*     */   private static NBTList<?> a(byte var0, byte var1) {
/* 133 */     if (a(var0, var1, (byte)4)) {
/* 134 */       return new NBTTagLongArray(new long[0]);
/*     */     }
/* 136 */     if (a(var0, var1, (byte)1)) {
/* 137 */       return new NBTTagByteArray(new byte[0]);
/*     */     }
/* 139 */     if (a(var0, var1, (byte)3)) {
/* 140 */       return new NBTTagIntArray(new int[0]);
/*     */     }
/* 142 */     return new NBTTagList();
/*     */   }
/*     */   
/*     */   private static boolean a(byte var0, byte var1, byte var2) {
/* 146 */     return (var0 == var2 && (var1 == var2 || var1 == 0));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static <T extends NBTBase> void a(NBTList<T> var0, NBTBase var1, NBTBase var2) {
/* 153 */     if (var1 instanceof NBTList) {
/* 154 */       NBTList<?> var3 = (NBTList)var1;
/* 155 */       var3.forEach(var1 -> var0.add(var1));
/*     */     } 
/*     */     
/* 158 */     var0.add((T)var2);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static <T extends NBTBase> void a(NBTList<T> var0, NBTBase var1, List<NBTBase> var2) {
/* 164 */     if (var1 instanceof NBTList) {
/* 165 */       NBTList<?> var3 = (NBTList)var1;
/* 166 */       var3.forEach(var1 -> var0.add(var1));
/*     */     } 
/*     */     
/* 169 */     var2.forEach(var1 -> var0.add(var1));
/*     */   }
/*     */ 
/*     */   
/*     */   public DataResult<NBTBase> mergeToList(NBTBase var0, NBTBase var1) {
/* 174 */     if (!(var0 instanceof NBTList) && !(var0 instanceof NBTTagEnd)) {
/* 175 */       return DataResult.error("mergeToList called with not a list: " + var0, var0);
/*     */     }
/*     */     
/* 178 */     NBTList<?> var2 = a((var0 instanceof NBTList) ? ((NBTList)var0)
/* 179 */         .d_() : 0, var1
/* 180 */         .getTypeId());
/*     */     
/* 182 */     a(var2, var0, var1);
/* 183 */     return DataResult.success(var2);
/*     */   }
/*     */ 
/*     */   
/*     */   public DataResult<NBTBase> mergeToList(NBTBase var0, List<NBTBase> var1) {
/* 188 */     if (!(var0 instanceof NBTList) && !(var0 instanceof NBTTagEnd)) {
/* 189 */       return DataResult.error("mergeToList called with not a list: " + var0, var0);
/*     */     }
/*     */     
/* 192 */     NBTList<?> var2 = a((var0 instanceof NBTList) ? ((NBTList)var0)
/* 193 */         .d_() : 0, ((Byte)var1
/* 194 */         .stream().findFirst().map(NBTBase::getTypeId).orElse(Byte.valueOf((byte)0))).byteValue());
/*     */     
/* 196 */     a(var2, var0, var1);
/* 197 */     return DataResult.success(var2);
/*     */   }
/*     */ 
/*     */   
/*     */   public DataResult<NBTBase> mergeToMap(NBTBase var0, NBTBase var1, NBTBase var2) {
/* 202 */     if (!(var0 instanceof NBTTagCompound) && !(var0 instanceof NBTTagEnd)) {
/* 203 */       return DataResult.error("mergeToMap called with not a map: " + var0, var0);
/*     */     }
/* 205 */     if (!(var1 instanceof NBTTagString)) {
/* 206 */       return DataResult.error("key is not a string: " + var1, var0);
/*     */     }
/*     */     
/* 209 */     NBTTagCompound var3 = new NBTTagCompound();
/* 210 */     if (var0 instanceof NBTTagCompound) {
/* 211 */       NBTTagCompound var4 = (NBTTagCompound)var0;
/* 212 */       var4.getKeys().forEach(var2 -> var0.set(var2, var1.get(var2)));
/*     */     } 
/* 214 */     var3.set(var1.asString(), var2);
/* 215 */     return DataResult.success(var3);
/*     */   }
/*     */ 
/*     */   
/*     */   public DataResult<NBTBase> mergeToMap(NBTBase var0, MapLike<NBTBase> var1) {
/* 220 */     if (!(var0 instanceof NBTTagCompound) && !(var0 instanceof NBTTagEnd)) {
/* 221 */       return DataResult.error("mergeToMap called with not a map: " + var0, var0);
/*     */     }
/*     */     
/* 224 */     NBTTagCompound var2 = new NBTTagCompound();
/* 225 */     if (var0 instanceof NBTTagCompound) {
/* 226 */       NBTTagCompound nBTTagCompound = (NBTTagCompound)var0;
/* 227 */       nBTTagCompound.getKeys().forEach(var2 -> var0.set(var2, var1.get(var2)));
/*     */     } 
/*     */     
/* 230 */     List<NBTBase> var3 = Lists.newArrayList();
/*     */     
/* 232 */     var1.entries().forEach(var2 -> {
/*     */           NBTBase var3 = (NBTBase)var2.getFirst();
/*     */           
/*     */           if (!(var3 instanceof NBTTagString)) {
/*     */             var0.add(var3);
/*     */             
/*     */             return;
/*     */           } 
/*     */           var1.set(var3.asString(), (NBTBase)var2.getSecond());
/*     */         });
/* 242 */     if (!var3.isEmpty()) {
/* 243 */       return DataResult.error("some keys are not strings: " + var3, var2);
/*     */     }
/*     */     
/* 246 */     return DataResult.success(var2);
/*     */   }
/*     */ 
/*     */   
/*     */   public DataResult<Stream<Pair<NBTBase, NBTBase>>> getMapValues(NBTBase var0) {
/* 251 */     if (!(var0 instanceof NBTTagCompound)) {
/* 252 */       return DataResult.error("Not a map: " + var0);
/*     */     }
/* 254 */     NBTTagCompound var1 = (NBTTagCompound)var0;
/* 255 */     return DataResult.success(var1.getKeys().stream().map(var1 -> Pair.of(createString(var1), var0.get(var1))));
/*     */   }
/*     */ 
/*     */   
/*     */   public DataResult<Consumer<BiConsumer<NBTBase, NBTBase>>> getMapEntries(NBTBase var0) {
/* 260 */     if (!(var0 instanceof NBTTagCompound)) {
/* 261 */       return DataResult.error("Not a map: " + var0);
/*     */     }
/* 263 */     NBTTagCompound var1 = (NBTTagCompound)var0;
/* 264 */     return DataResult.success(var1 -> var0.getKeys().forEach(()));
/*     */   }
/*     */ 
/*     */   
/*     */   public DataResult<MapLike<NBTBase>> getMap(NBTBase var0) {
/* 269 */     if (!(var0 instanceof NBTTagCompound)) {
/* 270 */       return DataResult.error("Not a map: " + var0);
/*     */     }
/* 272 */     NBTTagCompound var1 = (NBTTagCompound)var0;
/* 273 */     return DataResult.success(new MapLike<NBTBase>(this, var1)
/*     */         {
/*     */           @Nullable
/*     */           public NBTBase get(NBTBase var0) {
/* 277 */             return this.a.get(var0.asString());
/*     */           }
/*     */ 
/*     */           
/*     */           @Nullable
/*     */           public NBTBase get(String var0) {
/* 283 */             return this.a.get(var0);
/*     */           }
/*     */ 
/*     */           
/*     */           public Stream<Pair<NBTBase, NBTBase>> entries() {
/* 288 */             return this.a.getKeys().stream().map(var1 -> Pair.of(this.b.createString(var1), var0.get(var1)));
/*     */           }
/*     */ 
/*     */           
/*     */           public String toString() {
/* 293 */             return "MapLike[" + this.a + "]";
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   public NBTBase createMap(Stream<Pair<NBTBase, NBTBase>> var0) {
/* 300 */     NBTTagCompound var1 = new NBTTagCompound();
/* 301 */     var0.forEach(var1 -> var0.set(((NBTBase)var1.getFirst()).asString(), (NBTBase)var1.getSecond()));
/*     */ 
/*     */     
/* 304 */     return var1;
/*     */   }
/*     */ 
/*     */   
/*     */   public DataResult<Stream<NBTBase>> getStream(NBTBase var0) {
/* 309 */     if (var0 instanceof NBTList) {
/* 310 */       return DataResult.success(((NBTList)var0).stream().map(var0 -> var0));
/*     */     }
/* 312 */     return DataResult.error("Not a list");
/*     */   }
/*     */ 
/*     */   
/*     */   public DataResult<Consumer<Consumer<NBTBase>>> getList(NBTBase var0) {
/* 317 */     if (var0 instanceof NBTList) {
/* 318 */       NBTList<?> var1 = (NBTList)var0;
/* 319 */       return DataResult.success(var1::forEach);
/*     */     } 
/* 321 */     return DataResult.error("Not a list: " + var0);
/*     */   }
/*     */ 
/*     */   
/*     */   public DataResult<ByteBuffer> getByteBuffer(NBTBase var0) {
/* 326 */     if (var0 instanceof NBTTagByteArray) {
/* 327 */       return DataResult.success(ByteBuffer.wrap(((NBTTagByteArray)var0).getBytes()));
/*     */     }
/* 329 */     return super.getByteBuffer(var0);
/*     */   }
/*     */ 
/*     */   
/*     */   public NBTBase createByteList(ByteBuffer var0) {
/* 334 */     return new NBTTagByteArray(DataFixUtils.toArray(var0));
/*     */   }
/*     */ 
/*     */   
/*     */   public DataResult<IntStream> getIntStream(NBTBase var0) {
/* 339 */     if (var0 instanceof NBTTagIntArray) {
/* 340 */       return DataResult.success(Arrays.stream(((NBTTagIntArray)var0).getInts()));
/*     */     }
/* 342 */     return super.getIntStream(var0);
/*     */   }
/*     */ 
/*     */   
/*     */   public NBTBase createIntList(IntStream var0) {
/* 347 */     return new NBTTagIntArray(var0.toArray());
/*     */   }
/*     */ 
/*     */   
/*     */   public DataResult<LongStream> getLongStream(NBTBase var0) {
/* 352 */     if (var0 instanceof NBTTagLongArray) {
/* 353 */       return DataResult.success(Arrays.stream(((NBTTagLongArray)var0).getLongs()));
/*     */     }
/* 355 */     return super.getLongStream(var0);
/*     */   }
/*     */ 
/*     */   
/*     */   public NBTBase createLongList(LongStream var0) {
/* 360 */     return new NBTTagLongArray(var0.toArray());
/*     */   }
/*     */ 
/*     */   
/*     */   public NBTBase createList(Stream<NBTBase> var0) {
/* 365 */     PeekingIterator<NBTBase> var1 = Iterators.peekingIterator(var0.iterator());
/* 366 */     if (!var1.hasNext()) {
/* 367 */       return new NBTTagList();
/*     */     }
/* 369 */     NBTBase var2 = (NBTBase)var1.peek();
/* 370 */     if (var2 instanceof NBTTagByte) {
/* 371 */       List<Byte> list = Lists.newArrayList(Iterators.transform((Iterator)var1, var0 -> Byte.valueOf(((NBTTagByte)var0).asByte())));
/* 372 */       return new NBTTagByteArray(list);
/*     */     } 
/* 374 */     if (var2 instanceof NBTTagInt) {
/* 375 */       List<Integer> list = Lists.newArrayList(Iterators.transform((Iterator)var1, var0 -> Integer.valueOf(((NBTTagInt)var0).asInt())));
/* 376 */       return new NBTTagIntArray(list);
/*     */     } 
/* 378 */     if (var2 instanceof NBTTagLong) {
/* 379 */       List<Long> list = Lists.newArrayList(Iterators.transform((Iterator)var1, var0 -> Long.valueOf(((NBTTagLong)var0).asLong())));
/* 380 */       return new NBTTagLongArray(list);
/*     */     } 
/* 382 */     NBTTagList var3 = new NBTTagList();
/* 383 */     while (var1.hasNext()) {
/* 384 */       NBTBase var4 = (NBTBase)var1.next();
/* 385 */       if (var4 instanceof NBTTagEnd) {
/*     */         continue;
/*     */       }
/* 388 */       var3.add(var4);
/*     */     } 
/* 390 */     return var3;
/*     */   }
/*     */ 
/*     */   
/*     */   public NBTBase remove(NBTBase var0, String var1) {
/* 395 */     if (var0 instanceof NBTTagCompound) {
/* 396 */       NBTTagCompound var2 = (NBTTagCompound)var0;
/* 397 */       NBTTagCompound var3 = new NBTTagCompound();
/* 398 */       var2.getKeys().stream().filter(var1 -> !Objects.equals(var1, var0)).forEach(var2 -> var0.set(var2, var1.get(var2)));
/* 399 */       return var3;
/*     */     } 
/* 401 */     return var0;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 406 */     return "NBT";
/*     */   }
/*     */ 
/*     */   
/*     */   public RecordBuilder<NBTBase> mapBuilder() {
/* 411 */     return (RecordBuilder<NBTBase>)new a(this);
/*     */   }
/*     */   
/*     */   class a extends RecordBuilder.AbstractStringBuilder<NBTBase, NBTTagCompound> {
/*     */     protected a(DynamicOpsNBT this$0) {
/* 416 */       super(this$0);
/*     */     }
/*     */ 
/*     */     
/*     */     protected NBTTagCompound initBuilder() {
/* 421 */       return new NBTTagCompound();
/*     */     }
/*     */ 
/*     */     
/*     */     protected NBTTagCompound append(String var0, NBTBase var1, NBTTagCompound var2) {
/* 426 */       var2.set(var0, var1);
/* 427 */       return var2;
/*     */     }
/*     */ 
/*     */     
/*     */     protected DataResult<NBTBase> build(NBTTagCompound var0, NBTBase var1) {
/* 432 */       if (var1 == null || var1 == NBTTagEnd.b) {
/* 433 */         return DataResult.success(var0);
/*     */       }
/* 435 */       if (var1 instanceof NBTTagCompound) {
/* 436 */         NBTTagCompound var2 = new NBTTagCompound(Maps.newHashMap(((NBTTagCompound)var1).h()));
/* 437 */         for (Map.Entry<String, NBTBase> var4 : var0.h().entrySet()) {
/* 438 */           var2.set(var4.getKey(), var4.getValue());
/*     */         }
/* 440 */         return DataResult.success(var2);
/*     */       } 
/* 442 */       return DataResult.error("mergeToMap called with not a map: " + var1, var1);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DynamicOpsNBT.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.datafixers.util.Pair;
/*    */ import com.mojang.serialization.Codec;
/*    */ import com.mojang.serialization.DataResult;
/*    */ import com.mojang.serialization.DynamicOps;
/*    */ import com.mojang.serialization.Keyable;
/*    */ import java.util.Arrays;
/*    */ import java.util.Optional;
/*    */ import java.util.function.Function;
/*    */ import java.util.function.IntFunction;
/*    */ import java.util.function.Supplier;
/*    */ import java.util.function.ToIntFunction;
/*    */ import java.util.stream.IntStream;
/*    */ import java.util.stream.Stream;
/*    */ 
/*    */ 
/*    */ 
/*    */ public interface INamable
/*    */ {
/*    */   static <E extends Enum<E> & INamable> Codec<E> a(Supplier<E[]> var0, Function<? super String, ? extends E> var1) {
/* 22 */     Enum[] arrayOfEnum = (Enum[])var0.get();
/* 23 */     return (Codec)a(Enum::ordinal, var1 -> var0[var1], var1);
/*    */   }
/*    */   
/*    */   static <E extends INamable> Codec<E> a(ToIntFunction<E> var0, IntFunction<E> var1, Function<? super String, ? extends E> var2) {
/* 27 */     return new Codec<E>(var0, var1, var2)
/*    */       {
/*    */         public <T> DataResult<T> encode(E var0, DynamicOps<T> var1, T var2) {
/* 30 */           if (var1.compressMaps()) {
/* 31 */             return var1.mergeToPrimitive(var2, var1.createInt(this.a.applyAsInt(var0)));
/*    */           }
/* 33 */           return var1.mergeToPrimitive(var2, var1.createString(var0.getName()));
/*    */         }
/*    */ 
/*    */         
/*    */         public <T> DataResult<Pair<E, T>> decode(DynamicOps<T> var0, T var1) {
/* 38 */           if (var0.compressMaps()) {
/* 39 */             return var0.getNumberValue(var1).flatMap(var1 -> (DataResult)Optional.ofNullable(var0.apply(var1.intValue())).map(DataResult::success).orElseGet(()))
/*    */ 
/*    */               
/* 42 */               .map(var1 -> Pair.of(var1, var0.empty()));
/*    */           }
/* 44 */           return var0.getStringValue(var1).flatMap(var1 -> (DataResult)Optional.ofNullable(var0.apply(var1)).map(DataResult::success).orElseGet(()))
/*    */ 
/*    */             
/* 47 */             .map(var1 -> Pair.of(var1, var0.empty()));
/*    */         }
/*    */ 
/*    */         
/*    */         public String toString() {
/* 52 */           return "StringRepresentable[" + this.a + "]";
/*    */         }
/*    */       };
/*    */   }
/*    */   
/*    */   static Keyable a(INamable[] var0) {
/* 58 */     return new Keyable(var0)
/*    */       {
/*    */         public <T> Stream<T> keys(DynamicOps<T> var0) {
/* 61 */           if (var0.compressMaps()) {
/* 62 */             return IntStream.range(0, this.a.length).mapToObj(var0::createInt);
/*    */           }
/* 64 */           return Arrays.<INamable>stream(this.a).map(INamable::getName).map(var0::createString);
/*    */         }
/*    */       };
/*    */   }
/*    */   
/*    */   String getName();
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\INamable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
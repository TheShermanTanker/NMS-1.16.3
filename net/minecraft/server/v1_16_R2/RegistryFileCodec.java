/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.datafixers.util.Either;
/*    */ import com.mojang.datafixers.util.Pair;
/*    */ import com.mojang.serialization.Codec;
/*    */ import com.mojang.serialization.DataResult;
/*    */ import com.mojang.serialization.DynamicOps;
/*    */ import java.util.List;
/*    */ import java.util.function.Supplier;
/*    */ 
/*    */ public final class RegistryFileCodec<E>
/*    */   implements Codec<Supplier<E>>
/*    */ {
/*    */   private final ResourceKey<? extends IRegistry<E>> a;
/*    */   private final Codec<E> b;
/*    */   private final boolean c;
/*    */   
/*    */   public static <E> RegistryFileCodec<E> a(ResourceKey<? extends IRegistry<E>> var0, Codec<E> var1) {
/* 19 */     return a(var0, var1, true);
/*    */   }
/*    */   
/*    */   public static <E> Codec<List<Supplier<E>>> b(ResourceKey<? extends IRegistry<E>> var0, Codec<E> var1) {
/* 23 */     return Codec.either(
/* 24 */         a(var0, var1, false).listOf(), var1
/* 25 */         .xmap(var0 -> (), Supplier::get).listOf())
/* 26 */       .xmap(var0 -> (List)var0.map((), ()), Either::left);
/*    */   }
/*    */   
/*    */   private static <E> RegistryFileCodec<E> a(ResourceKey<? extends IRegistry<E>> var0, Codec<E> var1, boolean var2) {
/* 30 */     return new RegistryFileCodec<>(var0, var1, var2);
/*    */   }
/*    */   
/*    */   private RegistryFileCodec(ResourceKey<? extends IRegistry<E>> var0, Codec<E> var1, boolean var2) {
/* 34 */     this.a = var0;
/* 35 */     this.b = var1;
/* 36 */     this.c = var2;
/*    */   }
/*    */ 
/*    */   
/*    */   public <T> DataResult<T> encode(Supplier<E> var0, DynamicOps<T> var1, T var2) {
/* 41 */     if (var1 instanceof RegistryWriteOps) {
/* 42 */       return ((RegistryWriteOps<T>)var1).a(var0.get(), var2, this.a, this.b);
/*    */     }
/* 44 */     return this.b.encode(var0.get(), var1, var2);
/*    */   }
/*    */ 
/*    */   
/*    */   public <T> DataResult<Pair<Supplier<E>, T>> decode(DynamicOps<T> var0, T var1) {
/* 49 */     if (var0 instanceof RegistryReadOps) {
/* 50 */       return ((RegistryReadOps<T>)var0).a(var1, this.a, this.b, this.c);
/*    */     }
/* 52 */     return this.b.decode(var0, var1).map(var0 -> var0.mapFirst(()));
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 57 */     return "RegistryFileCodec[" + this.a + " " + this.b + "]";
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\RegistryFileCodec.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
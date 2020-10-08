/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.datafixers.util.Pair;
/*    */ import com.mojang.serialization.Codec;
/*    */ import com.mojang.serialization.DataResult;
/*    */ import com.mojang.serialization.DynamicOps;
/*    */ import com.mojang.serialization.Lifecycle;
/*    */ 
/*    */ public final class RegistryDataPackCodec<E>
/*    */   implements Codec<RegistryMaterials<E>>
/*    */ {
/*    */   private final Codec<RegistryMaterials<E>> a;
/*    */   private final ResourceKey<? extends IRegistry<E>> b;
/*    */   private final Codec<E> c;
/*    */   
/*    */   public static <E> RegistryDataPackCodec<E> a(ResourceKey<? extends IRegistry<E>> var0, Lifecycle var1, Codec<E> var2) {
/* 17 */     return new RegistryDataPackCodec<>(var0, var1, var2);
/*    */   }
/*    */   
/*    */   private RegistryDataPackCodec(ResourceKey<? extends IRegistry<E>> var0, Lifecycle var1, Codec<E> var2) {
/* 21 */     this.a = RegistryMaterials.c(var0, var1, var2);
/* 22 */     this.b = var0;
/* 23 */     this.c = var2;
/*    */   }
/*    */ 
/*    */   
/*    */   public <T> DataResult<T> encode(RegistryMaterials<E> var0, DynamicOps<T> var1, T var2) {
/* 28 */     return this.a.encode(var0, var1, var2);
/*    */   }
/*    */ 
/*    */   
/*    */   public <T> DataResult<Pair<RegistryMaterials<E>, T>> decode(DynamicOps<T> var0, T var1) {
/* 33 */     DataResult<Pair<RegistryMaterials<E>, T>> var2 = this.a.decode(var0, var1);
/* 34 */     if (var0 instanceof RegistryReadOps) {
/* 35 */       return var2.flatMap(var1 -> ((RegistryReadOps)var0).a((RegistryMaterials)var1.getFirst(), this.b, this.c).map(()));
/*    */     }
/* 37 */     return var2;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 42 */     return "RegistryDataPackCodec[" + this.a + " " + this.b + " " + this.c + "]";
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\RegistryDataPackCodec.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
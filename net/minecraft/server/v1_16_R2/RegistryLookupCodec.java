/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.serialization.DataResult;
/*    */ import com.mojang.serialization.DynamicOps;
/*    */ import com.mojang.serialization.MapCodec;
/*    */ import com.mojang.serialization.MapLike;
/*    */ import com.mojang.serialization.RecordBuilder;
/*    */ import java.util.stream.Stream;
/*    */ 
/*    */ public final class RegistryLookupCodec<E>
/*    */   extends MapCodec<IRegistry<E>>
/*    */ {
/*    */   private final ResourceKey<? extends IRegistry<E>> a;
/*    */   
/*    */   public static <E> RegistryLookupCodec<E> a(ResourceKey<? extends IRegistry<E>> var0) {
/* 16 */     return new RegistryLookupCodec<>(var0);
/*    */   }
/*    */   
/*    */   private RegistryLookupCodec(ResourceKey<? extends IRegistry<E>> var0) {
/* 20 */     this.a = var0;
/*    */   }
/*    */ 
/*    */   
/*    */   public <T> RecordBuilder<T> encode(IRegistry<E> var0, DynamicOps<T> var1, RecordBuilder<T> var2) {
/* 25 */     return var2;
/*    */   }
/*    */ 
/*    */   
/*    */   public <T> DataResult<IRegistry<E>> decode(DynamicOps<T> var0, MapLike<T> var1) {
/* 30 */     if (var0 instanceof RegistryReadOps) {
/* 31 */       return ((RegistryReadOps)var0).a(this.a);
/*    */     }
/* 33 */     return DataResult.error("Not a registry ops");
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 38 */     return "RegistryLookupCodec[" + this.a + "]";
/*    */   }
/*    */ 
/*    */   
/*    */   public <T> Stream<T> keys(DynamicOps<T> var0) {
/* 43 */     return Stream.empty();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\RegistryLookupCodec.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.serialization.Codec;
/*    */ import com.mojang.serialization.DataResult;
/*    */ import com.mojang.serialization.DynamicOps;
/*    */ import java.util.Optional;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RegistryWriteOps<T>
/*    */   extends DynamicOpsWrapper<T>
/*    */ {
/*    */   private final IRegistryCustom b;
/*    */   
/*    */   public static <T> RegistryWriteOps<T> a(DynamicOps<T> var0, IRegistryCustom var1) {
/* 16 */     return new RegistryWriteOps<>(var0, var1);
/*    */   }
/*    */   
/*    */   private RegistryWriteOps(DynamicOps<T> var0, IRegistryCustom var1) {
/* 20 */     super(var0);
/* 21 */     this.b = var1;
/*    */   }
/*    */   
/*    */   protected <E> DataResult<T> a(E var0, T var1, ResourceKey<? extends IRegistry<E>> var2, Codec<E> var3) {
/* 25 */     Optional<IRegistryWritable<E>> var4 = this.b.a(var2);
/* 26 */     if (var4.isPresent()) {
/* 27 */       IRegistryWritable<E> var5 = var4.get();
/* 28 */       Optional<ResourceKey<E>> var6 = var5.c(var0);
/* 29 */       if (var6.isPresent()) {
/* 30 */         ResourceKey<E> var7 = var6.get();
/* 31 */         return MinecraftKey.a.encode(var7.a(), this.a, var1);
/*    */       } 
/*    */     } 
/*    */     
/* 35 */     return var3.encode(var0, this, var1);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\RegistryWriteOps.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
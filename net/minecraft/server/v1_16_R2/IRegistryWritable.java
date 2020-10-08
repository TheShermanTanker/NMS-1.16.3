/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.serialization.Lifecycle;
/*    */ import java.util.OptionalInt;
/*    */ 
/*    */ public abstract class IRegistryWritable<T>
/*    */   extends IRegistry<T>
/*    */ {
/*    */   public IRegistryWritable(ResourceKey<? extends IRegistry<T>> var0, Lifecycle var1) {
/* 10 */     super(var0, var1);
/*    */   }
/*    */   
/*    */   public abstract <V extends T> V a(int paramInt, ResourceKey<T> paramResourceKey, V paramV, Lifecycle paramLifecycle);
/*    */   
/*    */   public abstract <V extends T> V a(ResourceKey<T> paramResourceKey, V paramV, Lifecycle paramLifecycle);
/*    */   
/*    */   public abstract <V extends T> V a(OptionalInt paramOptionalInt, ResourceKey<T> paramResourceKey, V paramV, Lifecycle paramLifecycle);
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\IRegistryWritable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
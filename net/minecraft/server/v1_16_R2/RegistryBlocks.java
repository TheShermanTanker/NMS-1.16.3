/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.serialization.Lifecycle;
/*    */ import java.util.Optional;
/*    */ import java.util.Random;
/*    */ import javax.annotation.Nonnull;
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RegistryBlocks<T>
/*    */   extends RegistryMaterials<T>
/*    */ {
/*    */   private final MinecraftKey bf;
/*    */   private T bg;
/*    */   
/*    */   public RegistryBlocks(String var0, ResourceKey<? extends IRegistry<T>> var1, Lifecycle var2) {
/* 18 */     super(var1, var2);
/* 19 */     this.bf = new MinecraftKey(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public <V extends T> V a(int var0, ResourceKey<T> var1, V var2, Lifecycle var3) {
/* 24 */     if (this.bf.equals(var1.a())) {
/* 25 */       this.bg = (T)var2;
/*    */     }
/*    */     
/* 28 */     return super.a(var0, var1, var2, var3);
/*    */   }
/*    */ 
/*    */   
/*    */   public int a(@Nullable T var0) {
/* 33 */     int var1 = super.a(var0);
/* 34 */     return (var1 == -1) ? super.a(this.bg) : var1;
/*    */   }
/*    */ 
/*    */   
/*    */   @Nonnull
/*    */   public MinecraftKey getKey(T var0) {
/* 40 */     MinecraftKey var1 = super.getKey(var0);
/* 41 */     return (var1 == null) ? this.bf : var1;
/*    */   }
/*    */ 
/*    */   
/*    */   @Nonnull
/*    */   public T get(@Nullable MinecraftKey var0) {
/* 47 */     T var1 = super.get(var0);
/* 48 */     return (var1 == null) ? this.bg : var1;
/*    */   }
/*    */ 
/*    */   
/*    */   public Optional<T> getOptional(@Nullable MinecraftKey var0) {
/* 53 */     return Optional.ofNullable(super.get(var0));
/*    */   }
/*    */ 
/*    */   
/*    */   @Nonnull
/*    */   public T fromId(int var0) {
/* 59 */     T var1 = super.fromId(var0);
/* 60 */     return (var1 == null) ? this.bg : var1;
/*    */   }
/*    */ 
/*    */   
/*    */   @Nonnull
/*    */   public T a(Random var0) {
/* 66 */     T var1 = super.a(var0);
/* 67 */     return (var1 == null) ? this.bg : var1;
/*    */   }
/*    */   
/*    */   public MinecraftKey a() {
/* 71 */     return this.bf;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\RegistryBlocks.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
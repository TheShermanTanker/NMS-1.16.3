/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.Maps;
/*    */ import java.util.Collections;
/*    */ import java.util.Map;
/*    */ import java.util.function.Function;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ResourceKey<T>
/*    */ {
/* 16 */   private static final Map<String, ResourceKey<?>> a = Collections.synchronizedMap(Maps.newIdentityHashMap());
/*    */   
/*    */   private final MinecraftKey b;
/*    */   private final MinecraftKey c;
/*    */   
/*    */   public static <T> ResourceKey<T> a(ResourceKey<? extends IRegistry<T>> var0, MinecraftKey var1) {
/* 22 */     return a(var0.c, var1);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static <T> ResourceKey<IRegistry<T>> a(MinecraftKey var0) {
/* 29 */     return a(IRegistry.d, var0);
/*    */   }
/*    */ 
/*    */   
/*    */   private static <T> ResourceKey<T> a(MinecraftKey var0, MinecraftKey var1) {
/* 34 */     String var2 = (var0 + ":" + var1).intern();
/* 35 */     return (ResourceKey<T>)a.computeIfAbsent(var2, var2 -> new ResourceKey(var0, var1));
/*    */   }
/*    */   
/*    */   private ResourceKey(MinecraftKey var0, MinecraftKey var1) {
/* 39 */     this.b = var0;
/* 40 */     this.c = var1;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 45 */     return "ResourceKey[" + this.b + " / " + this.c + ']';
/*    */   }
/*    */   
/*    */   public boolean a(ResourceKey<? extends IRegistry<?>> var0) {
/* 49 */     return this.b.equals(var0.a());
/*    */   }
/*    */   
/*    */   public MinecraftKey a() {
/* 53 */     return this.c;
/*    */   }
/*    */   
/*    */   public static <T> Function<MinecraftKey, ResourceKey<T>> b(ResourceKey<? extends IRegistry<T>> var0) {
/* 57 */     return var1 -> a(var0, var1);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ResourceKey.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
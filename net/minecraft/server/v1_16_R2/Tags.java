/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.BiMap;
/*     */ import com.google.common.collect.ImmutableBiMap;
/*     */ import com.google.common.collect.ImmutableSet;
/*     */ import com.google.common.collect.Maps;
/*     */ import java.util.Collection;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public interface Tags<T>
/*     */ {
/*     */   Map<MinecraftKey, Tag<T>> a();
/*     */   
/*     */   @Nullable
/*     */   default Tag<T> a(MinecraftKey var0) {
/*  23 */     return a().get(var0);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   Tag<T> b(MinecraftKey paramMinecraftKey);
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   MinecraftKey a(Tag<T> paramTag);
/*     */ 
/*     */   
/*     */   default MinecraftKey b(Tag<T> var0) {
/*  37 */     MinecraftKey var1 = a(var0);
/*  38 */     if (var1 == null) {
/*  39 */       throw new IllegalStateException("Unrecognized tag");
/*     */     }
/*  41 */     return var1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default Collection<MinecraftKey> b() {
/*  49 */     return a().keySet();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default void a(PacketDataSerializer var0, RegistryBlocks<T> var1) {
/*  63 */     Map<MinecraftKey, Tag<T>> var2 = a();
/*  64 */     var0.d(var2.size());
/*  65 */     for (Map.Entry<MinecraftKey, Tag<T>> var4 : var2.entrySet()) {
/*  66 */       var0.a(var4.getKey());
/*  67 */       var0.d(((Tag)var4.getValue()).getTagged().size());
/*  68 */       for (T var6 : ((Tag)var4.getValue()).getTagged()) {
/*  69 */         var0.d(var1.a(var6));
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   static <T> Tags<T> a(PacketDataSerializer var0, IRegistry<T> var1) {
/*  75 */     Map<MinecraftKey, Tag<T>> var2 = Maps.newHashMap();
/*  76 */     int var3 = var0.i();
/*  77 */     for (int var4 = 0; var4 < var3; var4++) {
/*  78 */       MinecraftKey var5 = var0.p();
/*  79 */       int var6 = var0.i();
/*  80 */       ImmutableSet.Builder<T> var7 = ImmutableSet.builder();
/*  81 */       for (int var8 = 0; var8 < var6; var8++) {
/*  82 */         var7.add(var1.fromId(var0.i()));
/*     */       }
/*  84 */       var2.put(var5, Tag.b((Set<T>)var7.build()));
/*     */     } 
/*     */     
/*  87 */     return a(var2);
/*     */   }
/*     */   
/*     */   static <T> Tags<T> c() {
/*  91 */     return a((Map<MinecraftKey, Tag<T>>)ImmutableBiMap.of());
/*     */   }
/*     */   
/*     */   static <T> Tags<T> a(Map<MinecraftKey, Tag<T>> var0) {
/*  95 */     ImmutableBiMap immutableBiMap = ImmutableBiMap.copyOf(var0);
/*  96 */     return new Tags<T>((BiMap)immutableBiMap) {
/*  97 */         private final Tag<T> b = TagSet.a();
/*     */ 
/*     */         
/*     */         public Tag<T> b(MinecraftKey var0) {
/* 101 */           return (Tag<T>)this.a.getOrDefault(var0, this.b);
/*     */         }
/*     */ 
/*     */         
/*     */         @Nullable
/*     */         public MinecraftKey a(Tag<T> var0) {
/* 107 */           if (var0 instanceof Tag.e) {
/* 108 */             return ((Tag.e)var0).a();
/*     */           }
/* 110 */           return (MinecraftKey)this.a.inverse().get(var0);
/*     */         }
/*     */ 
/*     */         
/*     */         public Map<MinecraftKey, Tag<T>> a() {
/* 115 */           return (Map<MinecraftKey, Tag<T>>)this.a;
/*     */         }
/*     */       };
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\Tags.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
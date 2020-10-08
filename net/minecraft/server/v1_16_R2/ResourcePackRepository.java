/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.base.Functions;
/*     */ import com.google.common.collect.ImmutableList;
/*     */ import com.google.common.collect.ImmutableMap;
/*     */ import com.google.common.collect.ImmutableSet;
/*     */ import com.google.common.collect.Maps;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Objects;
/*     */ import java.util.Set;
/*     */ import java.util.function.Function;
/*     */ import java.util.stream.Collector;
/*     */ import java.util.stream.Collectors;
/*     */ import java.util.stream.Stream;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ public class ResourcePackRepository implements AutoCloseable {
/*     */   private final Set<ResourcePackSource> a;
/*  21 */   private Map<String, ResourcePackLoader> b = (Map<String, ResourcePackLoader>)ImmutableMap.of();
/*  22 */   private List<ResourcePackLoader> c = (List<ResourcePackLoader>)ImmutableList.of();
/*     */   private final ResourcePackLoader.a d;
/*     */   
/*     */   public ResourcePackRepository(ResourcePackLoader.a var0, ResourcePackSource... var1) {
/*  26 */     this.d = var0;
/*  27 */     this.a = (Set<ResourcePackSource>)ImmutableSet.copyOf((Object[])var1);
/*     */   }
/*     */   
/*     */   public ResourcePackRepository(ResourcePackSource... var0) {
/*  31 */     this(ResourcePackLoader::new, var0);
/*     */   }
/*     */   
/*     */   public void a() {
/*  35 */     List<String> var0 = (List<String>)this.c.stream().map(ResourcePackLoader::e).collect(ImmutableList.toImmutableList());
/*  36 */     close();
/*  37 */     this.b = g();
/*  38 */     this.c = b(var0);
/*     */   }
/*     */   
/*     */   private Map<String, ResourcePackLoader> g() {
/*  42 */     Map<String, ResourcePackLoader> var0 = Maps.newTreeMap();
/*  43 */     for (ResourcePackSource var2 : this.a) {
/*  44 */       var2.a(var1 -> (ResourcePackLoader)var0.put(var1.e(), var1), this.d);
/*     */     }
/*  46 */     return (Map<String, ResourcePackLoader>)ImmutableMap.copyOf(var0);
/*     */   }
/*     */   
/*     */   public void a(Collection<String> var0) {
/*  50 */     this.c = b(var0);
/*     */   }
/*     */   
/*     */   private List<ResourcePackLoader> b(Collection<String> var0) {
/*  54 */     List<ResourcePackLoader> var1 = c(var0).collect((Collector)Collectors.toList());
/*     */     
/*  56 */     for (ResourcePackLoader var3 : this.b.values()) {
/*     */       
/*  58 */       if (var3.f() && !var1.contains(var3)) {
/*  59 */         var3.h().a(var1, var3, (Function<ResourcePackLoader, ResourcePackLoader>)Functions.identity(), false);
/*     */       }
/*     */     } 
/*  62 */     return (List<ResourcePackLoader>)ImmutableList.copyOf(var1);
/*     */   }
/*     */   
/*     */   private Stream<ResourcePackLoader> c(Collection<String> var0) {
/*  66 */     return var0.stream().map(this.b::get).filter(Objects::nonNull);
/*     */   }
/*     */   
/*     */   public Collection<String> b() {
/*  70 */     return this.b.keySet();
/*     */   }
/*     */   
/*     */   public Collection<ResourcePackLoader> c() {
/*  74 */     return this.b.values();
/*     */   }
/*     */   
/*     */   public Collection<String> d() {
/*  78 */     return (Collection<String>)this.c.stream().map(ResourcePackLoader::e).collect(ImmutableSet.toImmutableSet());
/*     */   }
/*     */   
/*     */   public Collection<ResourcePackLoader> e() {
/*  82 */     return this.c;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public ResourcePackLoader a(String var0) {
/*  87 */     return this.b.get(var0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void close() {
/*  92 */     this.b.values().forEach(ResourcePackLoader::close);
/*     */   }
/*     */   
/*     */   public boolean b(String var0) {
/*  96 */     return this.b.containsKey(var0);
/*     */   }
/*     */   
/*     */   public List<IResourcePack> f() {
/* 100 */     return (List<IResourcePack>)this.c.stream().map(ResourcePackLoader::d).collect(ImmutableList.toImmutableList());
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ResourcePackRepository.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
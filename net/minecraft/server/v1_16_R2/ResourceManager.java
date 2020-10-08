/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Maps;
/*     */ import com.google.common.collect.Sets;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.IOException;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.concurrent.CompletableFuture;
/*     */ import java.util.concurrent.Executor;
/*     */ import java.util.function.Predicate;
/*     */ import java.util.stream.Collectors;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import org.apache.logging.log4j.util.Supplier;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ResourceManager
/*     */   implements IReloadableResourceManager
/*     */ {
/*  28 */   private static final Logger LOGGER = LogManager.getLogger();
/*     */   
/*  30 */   private final Map<String, ResourceManagerFallback> b = Maps.newHashMap();
/*  31 */   private final List<IReloadListener> c = Lists.newArrayList();
/*  32 */   private final List<IReloadListener> d = Lists.newArrayList();
/*  33 */   private final Set<String> e = Sets.newLinkedHashSet();
/*  34 */   private final List<IResourcePack> f = Lists.newArrayList();
/*     */   private final EnumResourcePackType g;
/*     */   
/*     */   public ResourceManager(EnumResourcePackType var0) {
/*  38 */     this.g = var0;
/*     */   }
/*     */   
/*     */   public void a(IResourcePack var0) {
/*  42 */     this.f.add(var0);
/*  43 */     for (String var2 : var0.a(this.g)) {
/*  44 */       this.e.add(var2);
/*  45 */       ResourceManagerFallback var3 = this.b.get(var2);
/*  46 */       if (var3 == null) {
/*  47 */         var3 = new ResourceManagerFallback(this.g, var2);
/*  48 */         this.b.put(var2, var3);
/*     */       } 
/*  50 */       var3.a(var0);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IResource a(MinecraftKey var0) throws IOException {
/*  61 */     IResourceManager var1 = this.b.get(var0.getNamespace());
/*     */     
/*  63 */     if (var1 != null) {
/*  64 */       return var1.a(var0);
/*     */     }
/*     */     
/*  67 */     throw new FileNotFoundException(var0.toString());
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
/*     */ 
/*     */   
/*     */   public List<IResource> c(MinecraftKey var0) throws IOException {
/*  83 */     IResourceManager var1 = this.b.get(var0.getNamespace());
/*     */     
/*  85 */     if (var1 != null) {
/*  86 */       return var1.c(var0);
/*     */     }
/*     */     
/*  89 */     throw new FileNotFoundException(var0.toString());
/*     */   }
/*     */ 
/*     */   
/*     */   public Collection<MinecraftKey> a(String var0, Predicate<String> var1) {
/*  94 */     Set<MinecraftKey> var2 = Sets.newHashSet();
/*     */     
/*  96 */     for (ResourceManagerFallback var4 : this.b.values()) {
/*  97 */       var2.addAll(var4.a(var0, var1));
/*     */     }
/*     */     
/* 100 */     List<MinecraftKey> var3 = Lists.newArrayList(var2);
/* 101 */     Collections.sort(var3);
/* 102 */     return var3;
/*     */   }
/*     */   
/*     */   private void c() {
/* 106 */     this.b.clear();
/* 107 */     this.e.clear();
/* 108 */     this.f.forEach(IResourcePack::close);
/* 109 */     this.f.clear();
/*     */   }
/*     */ 
/*     */   
/*     */   public void close() {
/* 114 */     c();
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(IReloadListener var0) {
/* 119 */     this.c.add(var0);
/* 120 */     this.d.add(var0);
/*     */   }
/*     */   
/*     */   protected IReloadable b(Executor var0, Executor var1, List<IReloadListener> var2, CompletableFuture<Unit> var3) {
/*     */     IReloadable<Void> var4;
/* 125 */     if (LOGGER.isDebugEnabled()) {
/* 126 */       var4 = new ReloadableProfiled(this, Lists.newArrayList(var2), var0, var1, var3);
/*     */     } else {
/* 128 */       var4 = Reloadable.a(this, Lists.newArrayList(var2), var0, var1, var3);
/*     */     } 
/* 130 */     this.d.clear();
/* 131 */     return var4;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IReloadable a(Executor var0, Executor var1, CompletableFuture<Unit> var2, List<IResourcePack> var3) {
/* 141 */     c();
/*     */     
/* 143 */     LOGGER.info("Reloading ResourceManager: {}", new Supplier[] { () -> (String)var0.stream().map(IResourcePack::a).collect(Collectors.joining(", ")) });
/*     */     
/* 145 */     for (IResourcePack var5 : var3) {
/*     */       try {
/* 147 */         a(var5);
/* 148 */       } catch (Exception var6) {
/* 149 */         LOGGER.error("Failed to add resource pack {}", var5.a(), var6);
/* 150 */         return new a(new b(var5, var6));
/*     */       } 
/*     */     } 
/*     */     
/* 154 */     return b(var0, var1, this.c, var2);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static class b
/*     */     extends RuntimeException
/*     */   {
/*     */     private final IResourcePack a;
/*     */ 
/*     */     
/*     */     public b(IResourcePack var0, Throwable var1) {
/* 166 */       super(var0.a(), var1);
/* 167 */       this.a = var0;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   static class a
/*     */     implements IReloadable
/*     */   {
/*     */     private final ResourceManager.b a;
/*     */     
/*     */     private final CompletableFuture<Unit> b;
/*     */     
/*     */     public a(ResourceManager.b var0) {
/* 180 */       this.a = var0;
/* 181 */       this.b = new CompletableFuture<>();
/* 182 */       this.b.completeExceptionally(var0);
/*     */     }
/*     */ 
/*     */     
/*     */     public CompletableFuture<Unit> a() {
/* 187 */       return this.b;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ResourceManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
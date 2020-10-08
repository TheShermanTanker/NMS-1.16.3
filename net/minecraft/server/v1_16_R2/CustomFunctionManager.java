/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.ImmutableMap;
/*     */ import com.google.common.collect.Maps;
/*     */ import com.mojang.brigadier.CommandDispatcher;
/*     */ import com.mojang.datafixers.util.Pair;
/*     */ import java.io.IOException;
/*     */ import java.nio.charset.StandardCharsets;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Optional;
/*     */ import java.util.concurrent.CompletableFuture;
/*     */ import java.util.concurrent.CompletionException;
/*     */ import java.util.concurrent.CompletionStage;
/*     */ import java.util.concurrent.Executor;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import org.bukkit.craftbukkit.libs.org.apache.commons.io.IOUtils;
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
/*     */ public class CustomFunctionManager
/*     */   implements IReloadListener
/*     */ {
/*  35 */   private static final Logger LOGGER = LogManager.getLogger();
/*     */ 
/*     */   
/*  38 */   private static final int b = "functions/".length();
/*  39 */   private static final int c = ".mcfunction".length();
/*     */   
/*  41 */   private volatile Map<MinecraftKey, CustomFunction> d = (Map<MinecraftKey, CustomFunction>)ImmutableMap.of();
/*  42 */   private final TagDataPack<CustomFunction> e = new TagDataPack<>(this::a, "tags/functions", "function");
/*  43 */   private volatile Tags<CustomFunction> f = Tags.c();
/*     */   
/*     */   private final int g;
/*     */   private final CommandDispatcher<CommandListenerWrapper> h;
/*     */   
/*     */   public Optional<CustomFunction> a(MinecraftKey var0) {
/*  49 */     return Optional.ofNullable(this.d.get(var0));
/*     */   }
/*     */   
/*     */   public Map<MinecraftKey, CustomFunction> a() {
/*  53 */     return this.d;
/*     */   }
/*     */   
/*     */   public Tags<CustomFunction> b() {
/*  57 */     return this.f;
/*     */   }
/*     */   
/*     */   public Tag<CustomFunction> b(MinecraftKey var0) {
/*  61 */     return this.f.b(var0);
/*     */   }
/*     */   
/*     */   public CustomFunctionManager(int var0, CommandDispatcher<CommandListenerWrapper> var1) {
/*  65 */     this.g = var0;
/*  66 */     this.h = var1;
/*     */   }
/*     */ 
/*     */   
/*     */   public CompletableFuture<Void> a(IReloadListener.a var0, IResourceManager var1, GameProfilerFiller var2, GameProfilerFiller var3, Executor var4, Executor var5) {
/*  71 */     CompletableFuture<Map<MinecraftKey, Tag.a>> var6 = this.e.a(var1, var4);
/*     */ 
/*     */ 
/*     */     
/*  75 */     CompletableFuture<Map<MinecraftKey, CompletableFuture<CustomFunction>>> var7 = CompletableFuture.supplyAsync(() -> var0.a("functions", ()), var4).thenCompose(var2 -> {
/*     */           Map<MinecraftKey, CompletableFuture<CustomFunction>> var3 = Maps.newHashMap();
/*     */ 
/*     */           
/*     */           CommandListenerWrapper var4 = new CommandListenerWrapper(ICommandListener.DUMMY, Vec3D.ORIGIN, Vec2F.a, null, this.g, "", ChatComponentText.d, null, null);
/*     */ 
/*     */           
/*     */           for (MinecraftKey var6 : var2) {
/*     */             String var7 = var6.getKey();
/*     */             
/*     */             MinecraftKey var8 = new MinecraftKey(var6.getNamespace(), var7.substring(b, var7.length() - c));
/*     */             
/*     */             var3.put(var8, CompletableFuture.supplyAsync((), var1));
/*     */           } 
/*     */           
/*     */           CompletableFuture[] arrayOfCompletableFuture = (CompletableFuture[])var3.values().toArray((Object[])new CompletableFuture[0]);
/*     */           
/*     */           return CompletableFuture.allOf((CompletableFuture<?>[])arrayOfCompletableFuture).handle(());
/*     */         });
/*     */     
/*  95 */     return var6.thenCombine(var7, Pair::of)
/*  96 */       .thenCompose(var0::a)
/*  97 */       .thenAcceptAsync(var0 -> {
/*     */           Map<MinecraftKey, CompletableFuture<CustomFunction>> var1 = (Map<MinecraftKey, CompletableFuture<CustomFunction>>)var0.getSecond();
/*     */           ImmutableMap.Builder<MinecraftKey, CustomFunction> var2 = ImmutableMap.builder();
/*     */           var1.forEach(());
/*     */           this.d = (Map<MinecraftKey, CustomFunction>)var2.build();
/*     */           this.f = this.e.a((Map<MinecraftKey, Tag.a>)var0.getFirst());
/*     */         }var5);
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
/*     */   private static List<String> a(IResourceManager var0, MinecraftKey var1) {
/* 117 */     try (IResource var2 = var0.a(var1)) {
/* 118 */       return IOUtils.readLines(var2.b(), StandardCharsets.UTF_8);
/* 119 */     } catch (IOException var2) {
/* 120 */       throw new CompletionException(var2);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CustomFunctionManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
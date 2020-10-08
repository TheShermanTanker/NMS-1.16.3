/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.Maps;
/*     */ import com.google.gson.Gson;
/*     */ import com.google.gson.JsonObject;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.Reader;
/*     */ import java.nio.charset.StandardCharsets;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Objects;
/*     */ import java.util.Optional;
/*     */ import java.util.concurrent.CompletableFuture;
/*     */ import java.util.concurrent.Executor;
/*     */ import java.util.function.Function;
/*     */ import java.util.stream.Collectors;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import org.bukkit.craftbukkit.libs.org.apache.commons.io.IOUtils;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TagDataPack<T>
/*     */ {
/*  30 */   private static final Logger LOGGER = LogManager.getLogger();
/*  31 */   private static final Gson b = new Gson();
/*     */   
/*  33 */   private static final int c = ".json".length();
/*     */   
/*     */   private final Function<MinecraftKey, Optional<T>> d;
/*     */   private final String e;
/*     */   private final String f;
/*     */   
/*     */   public TagDataPack(Function<MinecraftKey, Optional<T>> var0, String var1, String var2) {
/*  40 */     this.d = var0;
/*  41 */     this.e = var1;
/*  42 */     this.f = var2;
/*     */   }
/*     */   
/*     */   public CompletableFuture<Map<MinecraftKey, Tag.a>> a(IResourceManager var0, Executor var1) {
/*  46 */     return CompletableFuture.supplyAsync(() -> {
/*     */           Map<MinecraftKey, Tag.a> var1 = Maps.newHashMap();
/*     */           
/*     */           for (MinecraftKey var3 : var0.a(this.e, ())) {
/*     */             String var4 = var3.getKey();
/*     */             
/*     */             MinecraftKey var5 = new MinecraftKey(var3.getNamespace(), var4.substring(this.e.length() + 1, var4.length() - c));
/*     */             try {
/*     */               for (IResource var7 : var0.c(var3)) {
/*     */                 try(InputStream var8 = var7.b(); Reader var10 = new BufferedReader(new InputStreamReader(var8, StandardCharsets.UTF_8))) {
/*     */                   JsonObject var12 = ChatDeserializer.<JsonObject>a(b, var10, JsonObject.class);
/*     */                   if (var12 == null) {
/*     */                     LOGGER.error("Couldn't load {} tag list {} from {} in data pack {} as it is empty or null", this.f, var5, var3, var7.d());
/*     */                   } else {
/*     */                     ((Tag.a)var1.computeIfAbsent(var5, ())).a(var12, var7.d());
/*     */                   } 
/*  62 */                 } catch (IOException|RuntimeException var8) {
/*     */                   LOGGER.error("Couldn't read {} tag list {} from {} in data pack {}", this.f, var5, var3, var7.d(), var8);
/*     */                 } finally {
/*     */                   IOUtils.closeQuietly(var7);
/*     */                 } 
/*     */               } 
/*  68 */             } catch (IOException var6) {
/*     */               LOGGER.error("Couldn't read {} tag list {} from {}", this.f, var5, var3, var6);
/*     */             } 
/*     */           } 
/*     */           return var1;
/*     */         }var1);
/*     */   }
/*     */ 
/*     */   
/*     */   public Tags<T> a(Map<MinecraftKey, Tag.a> var0) {
/*  78 */     Map<MinecraftKey, Tag<T>> var1 = Maps.newHashMap();
/*  79 */     Function<MinecraftKey, Tag<T>> var2 = var1::get;
/*  80 */     Function<MinecraftKey, T> var3 = var0 -> ((Optional)this.d.apply(var0)).orElse(null);
/*     */     
/*  82 */     while (!var0.isEmpty()) {
/*  83 */       boolean var4 = false;
/*  84 */       for (Iterator<Map.Entry<MinecraftKey, Tag.a>> var5 = var0.entrySet().iterator(); var5.hasNext(); ) {
/*  85 */         Map.Entry<MinecraftKey, Tag.a> var6 = var5.next();
/*  86 */         Optional<Tag<T>> var7 = ((Tag.a)var6.getValue()).a(var2, var3);
/*  87 */         if (var7.isPresent()) {
/*  88 */           var1.put(var6.getKey(), var7.get());
/*  89 */           var5.remove();
/*  90 */           var4 = true;
/*     */         } 
/*     */       } 
/*  93 */       if (!var4) {
/*     */         break;
/*     */       }
/*     */     } 
/*     */     
/*  98 */     var0.forEach((var2, var3) -> LOGGER.error("Couldn't load {} tag {} as it is missing following references: {}", this.f, var2, var3.b(var0, var1).<CharSequence>map(Objects::toString).collect(Collectors.joining(","))));
/*     */     
/* 100 */     return Tags.a(var1);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\TagDataPack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
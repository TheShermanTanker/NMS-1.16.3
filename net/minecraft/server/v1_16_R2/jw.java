/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.Maps;
/*     */ import com.google.gson.Gson;
/*     */ import com.google.gson.GsonBuilder;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonObject;
/*     */ import java.io.BufferedWriter;
/*     */ import java.io.IOException;
/*     */ import java.nio.file.Files;
/*     */ import java.nio.file.Path;
/*     */ import java.nio.file.attribute.FileAttribute;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Objects;
/*     */ import java.util.function.Function;
/*     */ import java.util.stream.Collector;
/*     */ import java.util.stream.Collectors;
/*     */ import java.util.stream.Stream;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class jw<T>
/*     */   implements DebugReportProvider
/*     */ {
/*  29 */   private static final Logger LOGGER = LogManager.getLogger();
/*  30 */   private static final Gson e = (new GsonBuilder()).setPrettyPrinting().create();
/*     */   
/*     */   protected final DebugReportGenerator b;
/*     */   
/*     */   protected final IRegistry<T> c;
/*  35 */   private final Map<MinecraftKey, Tag.a> f = Maps.newLinkedHashMap();
/*     */   
/*     */   protected jw(DebugReportGenerator var0, IRegistry<T> var1) {
/*  38 */     this.b = var0;
/*  39 */     this.c = var1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(HashCache var0) {
/*  46 */     this.f.clear();
/*  47 */     b();
/*     */     
/*  49 */     Tag<T> var1 = TagSet.a();
/*  50 */     Function<MinecraftKey, Tag<T>> var2 = var1 -> this.f.containsKey(var1) ? var0 : null;
/*  51 */     Function<MinecraftKey, T> var3 = var0 -> this.c.getOptional(var0).orElse(null);
/*  52 */     this.f.forEach((var3, var4) -> {
/*     */           List<Tag.b> var5 = var4.b(var0, var1).collect((Collector)Collectors.toList());
/*     */           
/*     */           if (!var5.isEmpty()) {
/*     */             throw new IllegalArgumentException(String.format("Couldn't define tag %s as it is missing following references: %s", new Object[] { var3, var5.stream().map(Objects::toString).collect(Collectors.joining(",")) }));
/*     */           }
/*     */           
/*     */           JsonObject var6 = var4.c();
/*     */           Path var7 = a(var3);
/*     */           try {
/*     */             String var8 = e.toJson((JsonElement)var6);
/*     */             String var9 = a.hashUnencodedChars(var8).toString();
/*     */             if (!Objects.equals(var2.a(var7), var9) || !Files.exists(var7, new java.nio.file.LinkOption[0])) {
/*     */               Files.createDirectories(var7.getParent(), (FileAttribute<?>[])new FileAttribute[0]);
/*     */               try (BufferedWriter var10 = Files.newBufferedWriter(var7, new java.nio.file.OpenOption[0])) {
/*     */                 var10.write(var8);
/*     */               } 
/*     */             } 
/*     */             var2.a(var7, var9);
/*  71 */           } catch (IOException var8) {
/*     */             LOGGER.error("Couldn't save tags to {}", var7, var8);
/*     */           } 
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected a<T> a(Tag.e<T> var0) {
/*  80 */     Tag.a var1 = b(var0);
/*  81 */     return new a<>(var1, this.c, "vanilla");
/*     */   }
/*     */   
/*     */   protected Tag.a b(Tag.e<T> var0) {
/*  85 */     return this.f.computeIfAbsent(var0.a(), var0 -> new Tag.a());
/*     */   }
/*     */   protected abstract void b();
/*     */   
/*     */   protected abstract Path a(MinecraftKey paramMinecraftKey);
/*     */   
/*     */   public static class a<T> { private final Tag.a a;
/*     */     
/*     */     private a(Tag.a var0, IRegistry<T> var1, String var2) {
/*  94 */       this.a = var0;
/*  95 */       this.b = var1;
/*  96 */       this.c = var2;
/*     */     }
/*     */     private final IRegistry<T> b; private final String c;
/*     */     public a<T> a(T var0) {
/* 100 */       this.a.a(this.b.getKey(var0), this.c);
/* 101 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public a<T> a(Tag.e<T> var0) {
/* 110 */       this.a.c(var0.a(), this.c);
/* 111 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     @SafeVarargs
/*     */     public final a<T> a(T... var0) {
/* 122 */       Stream.<T>of(var0).map(this.b::getKey).forEach(var0 -> this.a.a(var0, this.c));
/* 123 */       return this;
/*     */     } }
/*     */ 
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\jw.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
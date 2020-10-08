/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.gson.JsonObject;
/*     */ import com.mojang.bridge.game.GameVersion;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.time.ZonedDateTime;
/*     */ import java.util.Date;
/*     */ import java.util.UUID;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MinecraftVersion
/*     */   implements GameVersion
/*     */ {
/*  18 */   private static final Logger LOGGER = LogManager.getLogger();
/*     */   
/*  20 */   public static final GameVersion a = new MinecraftVersion();
/*     */   
/*     */   private final String c;
/*     */   private final String d;
/*     */   private final boolean e;
/*     */   private final int f;
/*     */   private final int g;
/*     */   private final int h;
/*     */   private final Date i;
/*     */   private final String j;
/*     */   
/*     */   private MinecraftVersion() {
/*  32 */     this.c = UUID.randomUUID().toString().replaceAll("-", "");
/*  33 */     this.d = "1.16.3";
/*  34 */     this.e = true;
/*  35 */     this.f = 2580;
/*  36 */     this.g = 753;
/*  37 */     this.h = 6;
/*  38 */     this.i = new Date();
/*  39 */     this.j = "1.16.3";
/*     */   }
/*     */   
/*     */   private MinecraftVersion(JsonObject var0) {
/*  43 */     this.c = ChatDeserializer.h(var0, "id");
/*  44 */     this.d = ChatDeserializer.h(var0, "name");
/*  45 */     this.j = ChatDeserializer.h(var0, "release_target");
/*  46 */     this.e = ChatDeserializer.j(var0, "stable");
/*  47 */     this.f = ChatDeserializer.n(var0, "world_version");
/*  48 */     this.g = ChatDeserializer.n(var0, "protocol_version");
/*  49 */     this.h = ChatDeserializer.n(var0, "pack_version");
/*  50 */     this.i = Date.from(ZonedDateTime.parse(ChatDeserializer.h(var0, "build_time")).toInstant());
/*     */   }
/*     */   
/*     */   public static GameVersion a() {
/*  54 */     try (InputStream var0 = MinecraftVersion.class.getResourceAsStream("/version.json")) {
/*  55 */       if (var0 == null) {
/*  56 */         LOGGER.warn("Missing version information!");
/*  57 */         return a;
/*     */       
/*     */       }
/*     */ 
/*     */     
/*     */     }
/*  63 */     catch (IOException|com.google.gson.JsonParseException var0) {
/*  64 */       throw new IllegalStateException("Game version information is corrupt", var0);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public String getId() {
/*  70 */     return this.c;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getName() {
/*  75 */     return this.d;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getReleaseTarget() {
/*  80 */     return this.j;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getWorldVersion() {
/*  85 */     return this.f;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getProtocolVersion() {
/*  90 */     return this.g;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getPackVersion() {
/*  95 */     return this.h;
/*     */   }
/*     */ 
/*     */   
/*     */   public Date getBuildTime() {
/* 100 */     return this.i;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isStable() {
/* 105 */     return this.e;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\MinecraftVersion.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
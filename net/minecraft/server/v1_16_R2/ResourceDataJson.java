/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.Maps;
/*    */ import com.google.gson.Gson;
/*    */ import com.google.gson.JsonElement;
/*    */ import com.google.gson.JsonParseException;
/*    */ import java.io.BufferedReader;
/*    */ import java.io.InputStream;
/*    */ import java.io.InputStreamReader;
/*    */ import java.io.Reader;
/*    */ import java.nio.charset.StandardCharsets;
/*    */ import java.util.Map;
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class ResourceDataJson
/*    */   extends ResourceDataAbstract<Map<MinecraftKey, JsonElement>>
/*    */ {
/* 22 */   private static final Logger LOGGER = LogManager.getLogger();
/*    */   
/* 24 */   private static final int b = ".json".length();
/*    */   
/*    */   private final Gson c;
/*    */   private final String d;
/*    */   
/*    */   public ResourceDataJson(Gson var0, String var1) {
/* 30 */     this.c = var0;
/* 31 */     this.d = var1;
/*    */   }
/*    */ 
/*    */   
/*    */   protected Map<MinecraftKey, JsonElement> b(IResourceManager var0, GameProfilerFiller var1) {
/* 36 */     Map<MinecraftKey, JsonElement> var2 = Maps.newHashMap();
/*    */     
/* 38 */     int var3 = this.d.length() + 1;
/*    */     
/* 40 */     for (MinecraftKey var5 : var0.a(this.d, var0 -> var0.endsWith(".json"))) {
/* 41 */       String var6 = var5.getKey();
/* 42 */       MinecraftKey var7 = new MinecraftKey(var5.getNamespace(), var6.substring(var3, var6.length() - b));
/*    */       
/* 44 */       try(IResource var8 = var0.a(var5); 
/* 45 */           InputStream var10 = var8.b(); 
/* 46 */           Reader var12 = new BufferedReader(new InputStreamReader(var10, StandardCharsets.UTF_8))) {
/* 47 */         JsonElement var14 = ChatDeserializer.<JsonElement>a(this.c, var12, JsonElement.class);
/* 48 */         if (var14 != null) {
/* 49 */           JsonElement var15 = var2.put(var7, var14);
/* 50 */           if (var15 != null) {
/* 51 */             throw new IllegalStateException("Duplicate data file ignored with ID " + var7);
/*    */           }
/*    */         } else {
/* 54 */           LOGGER.error("Couldn't load data file {} from {} as it's null or empty", var7, var5);
/*    */         } 
/* 56 */       } catch (JsonParseException|IllegalArgumentException|java.io.IOException var8) {
/* 57 */         LOGGER.error("Couldn't parse data file {} from {}", var7, var5, var8);
/*    */       } 
/*    */     } 
/*    */     
/* 61 */     return var2;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ResourceDataJson.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
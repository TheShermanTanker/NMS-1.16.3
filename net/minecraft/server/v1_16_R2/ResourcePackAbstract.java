/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.gson.JsonObject;
/*    */ import com.google.gson.JsonParseException;
/*    */ import java.io.BufferedReader;
/*    */ import java.io.File;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import java.io.InputStreamReader;
/*    */ import java.nio.charset.StandardCharsets;
/*    */ import javax.annotation.Nullable;
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class ResourcePackAbstract
/*    */   implements IResourcePack
/*    */ {
/* 20 */   private static final Logger LOGGER = LogManager.getLogger();
/*    */   
/*    */   protected final File a;
/*    */   
/*    */   public ResourcePackAbstract(File var0) {
/* 25 */     this.a = var0;
/*    */   }
/*    */   
/*    */   private static String c(EnumResourcePackType var0, MinecraftKey var1) {
/* 29 */     return String.format("%s/%s/%s", new Object[] { var0.a(), var1.getNamespace(), var1.getKey() });
/*    */   }
/*    */   
/*    */   protected static String a(File var0, File var1) {
/* 33 */     return var0.toURI().relativize(var1.toURI()).getPath();
/*    */   }
/*    */ 
/*    */   
/*    */   public InputStream a(EnumResourcePackType var0, MinecraftKey var1) throws IOException {
/* 38 */     return a(c(var0, var1));
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean b(EnumResourcePackType var0, MinecraftKey var1) {
/* 43 */     return c(c(var0, var1));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected abstract InputStream a(String paramString) throws IOException;
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected abstract boolean c(String paramString);
/*    */ 
/*    */ 
/*    */   
/*    */   protected void d(String var0) {
/* 59 */     LOGGER.warn("ResourcePack: ignored non-lowercase namespace: {} in {}", var0, this.a);
/*    */   }
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public <T> T a(ResourcePackMetaParser<T> var0) throws IOException {
/* 65 */     try (InputStream var1 = a("pack.mcmeta")) {
/* 66 */       return (T)a((ResourcePackMetaParser)var0, var1);
/*    */     } 
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public static <T> T a(ResourcePackMetaParser<T> var0, InputStream var1) {
/*    */     JsonObject var2;
/* 73 */     try (BufferedReader var3 = new BufferedReader(new InputStreamReader(var1, StandardCharsets.UTF_8))) {
/* 74 */       var2 = ChatDeserializer.a(var3);
/* 75 */     } catch (IOException|JsonParseException var3) {
/* 76 */       LOGGER.error("Couldn't load {} metadata", var0.a(), var3);
/* 77 */       return null;
/*    */     } 
/*    */     
/* 80 */     if (!var2.has(var0.a())) {
/* 81 */       return null;
/*    */     }
/*    */     try {
/* 84 */       return var0.a(ChatDeserializer.t(var2, var0.a()));
/* 85 */     } catch (JsonParseException var3) {
/* 86 */       LOGGER.error("Couldn't load {} metadata", var0.a(), var3);
/* 87 */       return null;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public String a() {
/* 93 */     return this.a.getName();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ResourcePackAbstract.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
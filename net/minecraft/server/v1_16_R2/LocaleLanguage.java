/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.ImmutableMap;
/*    */ import com.google.gson.Gson;
/*    */ import com.google.gson.JsonElement;
/*    */ import com.google.gson.JsonObject;
/*    */ import com.google.gson.JsonParseException;
/*    */ import java.io.InputStream;
/*    */ import java.io.InputStreamReader;
/*    */ import java.nio.charset.StandardCharsets;
/*    */ import java.util.Iterator;
/*    */ import java.util.Map;
/*    */ import java.util.Objects;
/*    */ import java.util.function.BiConsumer;
/*    */ import java.util.regex.Pattern;
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class LocaleLanguage
/*    */ {
/* 23 */   private static final Logger LOGGER = LogManager.getLogger();
/* 24 */   private static final Gson b = new Gson();
/* 25 */   private static final Pattern c = Pattern.compile("%(\\d+\\$)?[\\d.]*[df]");
/* 26 */   private static volatile LocaleLanguage d = c();
/*    */ 
/*    */ 
/*    */   
/*    */   private static LocaleLanguage c() {
/* 31 */     ImmutableMap.Builder<String, String> builder = ImmutableMap.builder();
/* 32 */     Objects.requireNonNull(builder); BiConsumer<String, String> biconsumer = builder::put;
/*    */     
/*    */     try {
/* 35 */       InputStream inputstream = LocaleLanguage.class.getResourceAsStream("/assets/minecraft/lang/en_us.json");
/* 36 */       Throwable throwable = null;
/*    */       
/*    */       try {
/* 39 */         a(inputstream, biconsumer);
/* 40 */       } catch (Throwable throwable1) {
/* 41 */         throwable = throwable1;
/* 42 */         throw throwable1;
/*    */       } finally {
/* 44 */         if (inputstream != null) {
/* 45 */           if (throwable != null) {
/*    */             try {
/* 47 */               inputstream.close();
/* 48 */             } catch (Throwable throwable2) {
/* 49 */               throwable.addSuppressed(throwable2);
/*    */             } 
/*    */           } else {
/* 52 */             inputstream.close();
/*    */           }
/*    */         
/*    */         }
/*    */       } 
/* 57 */     } catch (JsonParseException|java.io.IOException ioexception) {
/* 58 */       LOGGER.error("Couldn't read strings from /assets/minecraft/lang/en_us.json", ioexception);
/*    */     } 
/*    */     
/* 61 */     final ImmutableMap map = builder.build();
/*    */     
/* 63 */     return new LocaleLanguage()
/*    */       {
/*    */         public String a(String s) {
/* 66 */           return (String)map.getOrDefault(s, s);
/*    */         }
/*    */ 
/*    */         
/*    */         public boolean b(String s) {
/* 71 */           return map.containsKey(s);
/*    */         }
/*    */       };
/*    */   }
/*    */   
/*    */   public static void a(InputStream inputstream, BiConsumer<String, String> biconsumer) {
/* 77 */     JsonObject jsonobject = (JsonObject)b.fromJson(new InputStreamReader(inputstream, StandardCharsets.UTF_8), JsonObject.class);
/* 78 */     Iterator<Map.Entry<String, JsonElement>> iterator = jsonobject.entrySet().iterator();
/*    */     
/* 80 */     while (iterator.hasNext()) {
/* 81 */       Map.Entry<String, JsonElement> entry = iterator.next();
/* 82 */       String s = c.matcher(ChatDeserializer.a(entry.getValue(), entry.getKey())).replaceAll("%$1s");
/*    */       
/* 84 */       biconsumer.accept(entry.getKey(), s);
/*    */     } 
/*    */   }
/*    */   
/*    */   public static LocaleLanguage getInstance() {
/* 89 */     return a();
/*    */   } public static LocaleLanguage a() {
/* 91 */     return d;
/*    */   }
/*    */   public String translateKey(String key) {
/* 94 */     return a(key);
/*    */   }
/*    */   
/*    */   public abstract String a(String paramString);
/*    */   
/*    */   public abstract boolean b(String paramString);
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\LocaleLanguage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
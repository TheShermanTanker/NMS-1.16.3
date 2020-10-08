/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.gson.Gson;
/*     */ import com.google.gson.GsonBuilder;
/*     */ import com.google.gson.JsonArray;
/*     */ import com.google.gson.JsonDeserializationContext;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonObject;
/*     */ import com.google.gson.JsonParseException;
/*     */ import com.google.gson.JsonPrimitive;
/*     */ import com.google.gson.JsonSyntaxException;
/*     */ import com.google.gson.stream.JsonReader;
/*     */ import java.io.IOException;
/*     */ import java.io.Reader;
/*     */ import java.io.StringReader;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.craftbukkit.libs.org.apache.commons.lang3.StringUtils;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ChatDeserializer
/*     */ {
/*  27 */   private static final Gson a = (new GsonBuilder()).create();
/*     */   
/*     */   public static boolean a(JsonObject var0, String var1) {
/*  30 */     if (!f(var0, var1)) {
/*  31 */       return false;
/*     */     }
/*  33 */     return var0.getAsJsonPrimitive(var1).isString();
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
/*     */ 
/*     */   
/*     */   public static boolean b(JsonElement var0) {
/*  51 */     if (!var0.isJsonPrimitive()) {
/*  52 */       return false;
/*     */     }
/*  54 */     return var0.getAsJsonPrimitive().isNumber();
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
/*     */ 
/*     */   
/*     */   public static boolean d(JsonObject var0, String var1) {
/*  72 */     if (!g(var0, var1)) {
/*  73 */       return false;
/*     */     }
/*  75 */     return var0.get(var1).isJsonArray();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean f(JsonObject var0, String var1) {
/*  86 */     if (!g(var0, var1)) {
/*  87 */       return false;
/*     */     }
/*  89 */     return var0.get(var1).isJsonPrimitive();
/*     */   }
/*     */   
/*     */   public static boolean g(JsonObject var0, String var1) {
/*  93 */     if (var0 == null) {
/*  94 */       return false;
/*     */     }
/*  96 */     return (var0.get(var1) != null);
/*     */   }
/*     */   
/*     */   public static String a(JsonElement var0, String var1) {
/* 100 */     if (var0.isJsonPrimitive()) {
/* 101 */       return var0.getAsString();
/*     */     }
/* 103 */     throw new JsonSyntaxException("Expected " + var1 + " to be a string, was " + d(var0));
/*     */   }
/*     */ 
/*     */   
/*     */   public static String h(JsonObject var0, String var1) {
/* 108 */     if (var0.has(var1)) {
/* 109 */       return a(var0.get(var1), var1);
/*     */     }
/* 111 */     throw new JsonSyntaxException("Missing " + var1 + ", expected to find a string");
/*     */   }
/*     */ 
/*     */   
/*     */   public static String a(JsonObject var0, String var1, String var2) {
/* 116 */     if (var0.has(var1)) {
/* 117 */       return a(var0.get(var1), var1);
/*     */     }
/* 119 */     return var2;
/*     */   }
/*     */ 
/*     */   
/*     */   public static Item b(JsonElement var0, String var1) {
/* 124 */     if (var0.isJsonPrimitive()) {
/* 125 */       String var2 = var0.getAsString();
/* 126 */       return (Item)IRegistry.ITEM.getOptional(new MinecraftKey(var2))
/* 127 */         .orElseThrow(() -> new JsonSyntaxException("Expected " + var0 + " to be an item, was unknown string '" + var1 + "'"));
/*     */     } 
/* 129 */     throw new JsonSyntaxException("Expected " + var1 + " to be an item, was " + d(var0));
/*     */   }
/*     */ 
/*     */   
/*     */   public static Item i(JsonObject var0, String var1) {
/* 134 */     if (var0.has(var1)) {
/* 135 */       return b(var0.get(var1), var1);
/*     */     }
/* 137 */     throw new JsonSyntaxException("Missing " + var1 + ", expected to find an item");
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
/*     */   public static boolean c(JsonElement var0, String var1) {
/* 150 */     if (var0.isJsonPrimitive()) {
/* 151 */       return var0.getAsBoolean();
/*     */     }
/* 153 */     throw new JsonSyntaxException("Expected " + var1 + " to be a Boolean, was " + d(var0));
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean j(JsonObject var0, String var1) {
/* 158 */     if (var0.has(var1)) {
/* 159 */       return c(var0.get(var1), var1);
/*     */     }
/* 161 */     throw new JsonSyntaxException("Missing " + var1 + ", expected to find a Boolean");
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean a(JsonObject var0, String var1, boolean var2) {
/* 166 */     if (var0.has(var1)) {
/* 167 */       return c(var0.get(var1), var1);
/*     */     }
/* 169 */     return var2;
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
/*     */   public static float e(JsonElement var0, String var1) {
/* 198 */     if (var0.isJsonPrimitive() && var0.getAsJsonPrimitive().isNumber()) {
/* 199 */       return var0.getAsFloat();
/*     */     }
/* 201 */     throw new JsonSyntaxException("Expected " + var1 + " to be a Float, was " + d(var0));
/*     */   }
/*     */ 
/*     */   
/*     */   public static float l(JsonObject var0, String var1) {
/* 206 */     if (var0.has(var1)) {
/* 207 */       return e(var0.get(var1), var1);
/*     */     }
/* 209 */     throw new JsonSyntaxException("Missing " + var1 + ", expected to find a Float");
/*     */   }
/*     */ 
/*     */   
/*     */   public static float a(JsonObject var0, String var1, float var2) {
/* 214 */     if (var0.has(var1)) {
/* 215 */       return e(var0.get(var1), var1);
/*     */     }
/* 217 */     return var2;
/*     */   }
/*     */ 
/*     */   
/*     */   public static long f(JsonElement var0, String var1) {
/* 222 */     if (var0.isJsonPrimitive() && var0.getAsJsonPrimitive().isNumber()) {
/* 223 */       return var0.getAsLong();
/*     */     }
/* 225 */     throw new JsonSyntaxException("Expected " + var1 + " to be a Long, was " + d(var0));
/*     */   }
/*     */ 
/*     */   
/*     */   public static long m(JsonObject var0, String var1) {
/* 230 */     if (var0.has(var1)) {
/* 231 */       return f(var0.get(var1), var1);
/*     */     }
/* 233 */     throw new JsonSyntaxException("Missing " + var1 + ", expected to find a Long");
/*     */   }
/*     */ 
/*     */   
/*     */   public static long a(JsonObject var0, String var1, long var2) {
/* 238 */     if (var0.has(var1)) {
/* 239 */       return f(var0.get(var1), var1);
/*     */     }
/* 241 */     return var2;
/*     */   }
/*     */ 
/*     */   
/*     */   public static int g(JsonElement var0, String var1) {
/* 246 */     if (var0.isJsonPrimitive() && var0.getAsJsonPrimitive().isNumber()) {
/* 247 */       return var0.getAsInt();
/*     */     }
/* 249 */     throw new JsonSyntaxException("Expected " + var1 + " to be a Int, was " + d(var0));
/*     */   }
/*     */ 
/*     */   
/*     */   public static int n(JsonObject var0, String var1) {
/* 254 */     if (var0.has(var1)) {
/* 255 */       return g(var0.get(var1), var1);
/*     */     }
/* 257 */     throw new JsonSyntaxException("Missing " + var1 + ", expected to find a Int");
/*     */   }
/*     */ 
/*     */   
/*     */   public static int a(JsonObject var0, String var1, int var2) {
/* 262 */     if (var0.has(var1)) {
/* 263 */       return g(var0.get(var1), var1);
/*     */     }
/* 265 */     return var2;
/*     */   }
/*     */ 
/*     */   
/*     */   public static byte h(JsonElement var0, String var1) {
/* 270 */     if (var0.isJsonPrimitive() && var0.getAsJsonPrimitive().isNumber()) {
/* 271 */       return var0.getAsByte();
/*     */     }
/* 273 */     throw new JsonSyntaxException("Expected " + var1 + " to be a Byte, was " + d(var0));
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
/*     */   public static byte a(JsonObject var0, String var1, byte var2) {
/* 286 */     if (var0.has(var1)) {
/* 287 */       return h(var0.get(var1), var1);
/*     */     }
/* 289 */     return var2;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static JsonObject m(JsonElement var0, String var1) {
/* 390 */     if (var0.isJsonObject()) {
/* 391 */       return var0.getAsJsonObject();
/*     */     }
/* 393 */     throw new JsonSyntaxException("Expected " + var1 + " to be a JsonObject, was " + d(var0));
/*     */   }
/*     */ 
/*     */   
/*     */   public static JsonObject t(JsonObject var0, String var1) {
/* 398 */     if (var0.has(var1)) {
/* 399 */       return m(var0.get(var1), var1);
/*     */     }
/* 401 */     throw new JsonSyntaxException("Missing " + var1 + ", expected to find a JsonObject");
/*     */   }
/*     */ 
/*     */   
/*     */   public static JsonObject a(JsonObject var0, String var1, JsonObject var2) {
/* 406 */     if (var0.has(var1)) {
/* 407 */       return m(var0.get(var1), var1);
/*     */     }
/* 409 */     return var2;
/*     */   }
/*     */ 
/*     */   
/*     */   public static JsonArray n(JsonElement var0, String var1) {
/* 414 */     if (var0.isJsonArray()) {
/* 415 */       return var0.getAsJsonArray();
/*     */     }
/* 417 */     throw new JsonSyntaxException("Expected " + var1 + " to be a JsonArray, was " + d(var0));
/*     */   }
/*     */ 
/*     */   
/*     */   public static JsonArray u(JsonObject var0, String var1) {
/* 422 */     if (var0.has(var1)) {
/* 423 */       return n(var0.get(var1), var1);
/*     */     }
/* 425 */     throw new JsonSyntaxException("Missing " + var1 + ", expected to find a JsonArray");
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public static JsonArray a(JsonObject var0, String var1, @Nullable JsonArray var2) {
/* 431 */     if (var0.has(var1)) {
/* 432 */       return n(var0.get(var1), var1);
/*     */     }
/* 434 */     return var2;
/*     */   }
/*     */ 
/*     */   
/*     */   public static <T> T a(@Nullable JsonElement var0, String var1, JsonDeserializationContext var2, Class<? extends T> var3) {
/* 439 */     if (var0 != null) {
/* 440 */       return (T)var2.deserialize(var0, var3);
/*     */     }
/* 442 */     throw new JsonSyntaxException("Missing " + var1);
/*     */   }
/*     */ 
/*     */   
/*     */   public static <T> T a(JsonObject var0, String var1, JsonDeserializationContext var2, Class<? extends T> var3) {
/* 447 */     if (var0.has(var1)) {
/* 448 */       return a(var0.get(var1), var1, var2, var3);
/*     */     }
/* 450 */     throw new JsonSyntaxException("Missing " + var1);
/*     */   }
/*     */ 
/*     */   
/*     */   public static <T> T a(JsonObject var0, String var1, T var2, JsonDeserializationContext var3, Class<? extends T> var4) {
/* 455 */     if (var0.has(var1)) {
/* 456 */       return a(var0.get(var1), var1, var3, var4);
/*     */     }
/* 458 */     return var2;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String d(JsonElement var0) {
/* 463 */     String var1 = StringUtils.abbreviateMiddle(String.valueOf(var0), "...", 10);
/* 464 */     if (var0 == null) {
/* 465 */       return "null (missing)";
/*     */     }
/* 467 */     if (var0.isJsonNull()) {
/* 468 */       return "null (json)";
/*     */     }
/* 470 */     if (var0.isJsonArray()) {
/* 471 */       return "an array (" + var1 + ")";
/*     */     }
/* 473 */     if (var0.isJsonObject()) {
/* 474 */       return "an object (" + var1 + ")";
/*     */     }
/* 476 */     if (var0.isJsonPrimitive()) {
/* 477 */       JsonPrimitive var2 = var0.getAsJsonPrimitive();
/* 478 */       if (var2.isNumber()) {
/* 479 */         return "a number (" + var1 + ")";
/*     */       }
/* 481 */       if (var2.isBoolean()) {
/* 482 */         return "a boolean (" + var1 + ")";
/*     */       }
/*     */     } 
/* 485 */     return var1;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public static <T> T a(Gson var0, Reader var1, Class<T> var2, boolean var3) {
/*     */     try {
/* 491 */       JsonReader var4 = new JsonReader(var1);
/* 492 */       var4.setLenient(var3);
/* 493 */       return (T)var0.getAdapter(var2).read(var4);
/* 494 */     } catch (IOException var4) {
/* 495 */       throw new JsonParseException(var4);
/*     */     } 
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public static <T> T a(Gson var0, String var1, Class<T> var2, boolean var3) {
/* 517 */     return a(var0, new StringReader(var1), var2, var3);
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
/*     */   @Nullable
/*     */   public static <T> T a(Gson var0, Reader var1, Class<T> var2) {
/* 532 */     return a(var0, var1, var2, false);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public static <T> T a(Gson var0, String var1, Class<T> var2) {
/* 537 */     return a(var0, var1, var2, false);
/*     */   }
/*     */   
/*     */   public static JsonObject a(String var0, boolean var1) {
/* 541 */     return a(new StringReader(var0), var1);
/*     */   }
/*     */   
/*     */   public static JsonObject a(Reader var0, boolean var1) {
/* 545 */     return a(a, var0, JsonObject.class, var1);
/*     */   }
/*     */   
/*     */   public static JsonObject a(String var0) {
/* 549 */     return a(var0, false);
/*     */   }
/*     */   
/*     */   public static JsonObject a(Reader var0) {
/* 553 */     return a(var0, false);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ChatDeserializer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
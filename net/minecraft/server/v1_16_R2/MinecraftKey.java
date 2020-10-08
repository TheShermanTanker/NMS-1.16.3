/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.gson.JsonDeserializationContext;
/*     */ import com.google.gson.JsonDeserializer;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonParseException;
/*     */ import com.google.gson.JsonPrimitive;
/*     */ import com.google.gson.JsonSerializationContext;
/*     */ import com.google.gson.JsonSerializer;
/*     */ import com.mojang.brigadier.StringReader;
/*     */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*     */ import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
/*     */ import com.mojang.serialization.Codec;
/*     */ import com.mojang.serialization.DataResult;
/*     */ import java.lang.reflect.Type;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.craftbukkit.libs.org.apache.commons.lang3.StringUtils;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MinecraftKey
/*     */   implements Comparable<MinecraftKey>
/*     */ {
/*  24 */   public static final Codec<MinecraftKey> a = Codec.STRING.comapFlatMap(MinecraftKey::c, MinecraftKey::toString).stable();
/*  25 */   private static final SimpleCommandExceptionType d = new SimpleCommandExceptionType(new ChatMessage("argument.id.invalid"));
/*     */ 
/*     */   
/*     */   protected final String namespace;
/*     */ 
/*     */   
/*     */   protected final String key;
/*     */ 
/*     */   
/*     */   protected MinecraftKey(String[] var0) {
/*  35 */     this.namespace = StringUtils.isEmpty(var0[0]) ? "minecraft" : var0[0];
/*  36 */     this.key = var0[1];
/*  37 */     if (!e(this.namespace)) {
/*  38 */       throw new ResourceKeyInvalidException("Non [a-z0-9_.-] character in namespace of location: " + this.namespace + ':' + this.key);
/*     */     }
/*  40 */     if (!d(this.key)) {
/*  41 */       throw new ResourceKeyInvalidException("Non [a-z0-9/._-] character in path of location: " + this.namespace + ':' + this.key);
/*     */     }
/*     */   }
/*     */   
/*     */   public MinecraftKey(String var0) {
/*  46 */     this(b(var0, ':'));
/*     */   }
/*     */   
/*     */   public MinecraftKey(String var0, String var1) {
/*  50 */     this(new String[] { var0, var1 });
/*     */   }
/*     */   
/*     */   public static MinecraftKey a(String var0, char var1) {
/*  54 */     return new MinecraftKey(b(var0, var1));
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public static MinecraftKey a(String var0) {
/*     */     try {
/*  60 */       return new MinecraftKey(var0);
/*  61 */     } catch (ResourceKeyInvalidException var1) {
/*  62 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   protected static String[] b(String var0, char var1) {
/*  67 */     String[] var2 = { "minecraft", var0 };
/*  68 */     int var3 = var0.indexOf(var1);
/*  69 */     if (var3 >= 0) {
/*  70 */       var2[1] = var0.substring(var3 + 1, var0.length());
/*  71 */       if (var3 >= 1) {
/*  72 */         var2[0] = var0.substring(0, var3);
/*     */       }
/*     */     } 
/*     */     
/*  76 */     return var2;
/*     */   }
/*     */   
/*     */   private static DataResult<MinecraftKey> c(String var0) {
/*     */     try {
/*  81 */       return DataResult.success(new MinecraftKey(var0));
/*  82 */     } catch (ResourceKeyInvalidException var1) {
/*  83 */       return DataResult.error("Not a valid resource location: " + var0 + " " + var1.getMessage());
/*     */     } 
/*     */   }
/*     */   
/*     */   public String getKey() {
/*  88 */     return this.key;
/*     */   }
/*     */   
/*     */   public String getNamespace() {
/*  92 */     return this.namespace;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/*  97 */     return this.namespace + ':' + this.key;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object var0) {
/* 102 */     if (this == var0) {
/* 103 */       return true;
/*     */     }
/*     */     
/* 106 */     if (var0 instanceof MinecraftKey) {
/* 107 */       MinecraftKey var1 = (MinecraftKey)var0;
/*     */       
/* 109 */       return (this.namespace.equals(var1.namespace) && this.key.equals(var1.key));
/*     */     } 
/*     */     
/* 112 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 117 */     return 31 * this.namespace.hashCode() + this.key.hashCode();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int compareTo(MinecraftKey var0) {
/* 123 */     int var1 = this.key.compareTo(var0.key);
/* 124 */     if (var1 == 0) {
/* 125 */       var1 = this.namespace.compareTo(var0.namespace);
/*     */     }
/* 127 */     return var1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class a
/*     */     implements JsonDeserializer<MinecraftKey>, JsonSerializer<MinecraftKey>
/*     */   {
/*     */     public MinecraftKey deserialize(JsonElement var0, Type var1, JsonDeserializationContext var2) throws JsonParseException {
/* 137 */       return new MinecraftKey(ChatDeserializer.a(var0, "location"));
/*     */     }
/*     */ 
/*     */     
/*     */     public JsonElement serialize(MinecraftKey var0, Type var1, JsonSerializationContext var2) {
/* 142 */       return (JsonElement)new JsonPrimitive(var0.toString());
/*     */     }
/*     */   }
/*     */   
/*     */   public static MinecraftKey a(StringReader var0) throws CommandSyntaxException {
/* 147 */     int var1 = var0.getCursor();
/* 148 */     while (var0.canRead() && a(var0.peek())) {
/* 149 */       var0.skip();
/*     */     }
/* 151 */     String var2 = var0.getString().substring(var1, var0.getCursor());
/*     */     try {
/* 153 */       return new MinecraftKey(var2);
/* 154 */     } catch (ResourceKeyInvalidException var3) {
/* 155 */       var0.setCursor(var1);
/* 156 */       throw d.createWithContext(var0);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static boolean a(char var0) {
/* 161 */     return ((var0 >= '0' && var0 <= '9') || (var0 >= 'a' && var0 <= 'z') || var0 == '_' || var0 == ':' || var0 == '/' || var0 == '.' || var0 == '-');
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static boolean d(String var0) {
/* 169 */     for (int var1 = 0; var1 < var0.length(); var1++) {
/* 170 */       if (!b(var0.charAt(var1))) {
/* 171 */         return false;
/*     */       }
/*     */     } 
/* 174 */     return true;
/*     */   }
/*     */   
/*     */   private static boolean e(String var0) {
/* 178 */     for (int var1 = 0; var1 < var0.length(); var1++) {
/* 179 */       if (!c(var0.charAt(var1))) {
/* 180 */         return false;
/*     */       }
/*     */     } 
/* 183 */     return true;
/*     */   }
/*     */   
/*     */   public static boolean b(char var0) {
/* 187 */     return (var0 == '_' || var0 == '-' || (var0 >= 'a' && var0 <= 'z') || (var0 >= '0' && var0 <= '9') || var0 == '/' || var0 == '.');
/*     */   }
/*     */   
/*     */   private static boolean c(char var0) {
/* 191 */     return (var0 == '_' || var0 == '-' || (var0 >= 'a' && var0 <= 'z') || (var0 >= '0' && var0 <= '9') || var0 == '.');
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\MinecraftKey.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
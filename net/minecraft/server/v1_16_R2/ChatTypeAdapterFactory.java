/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.Maps;
/*    */ import com.google.gson.Gson;
/*    */ import com.google.gson.TypeAdapter;
/*    */ import com.google.gson.TypeAdapterFactory;
/*    */ import com.google.gson.reflect.TypeToken;
/*    */ import com.google.gson.stream.JsonReader;
/*    */ import com.google.gson.stream.JsonToken;
/*    */ import com.google.gson.stream.JsonWriter;
/*    */ import java.io.IOException;
/*    */ import java.util.Locale;
/*    */ import java.util.Map;
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ChatTypeAdapterFactory
/*    */   implements TypeAdapterFactory
/*    */ {
/*    */   @Nullable
/*    */   public <T> TypeAdapter<T> create(Gson var0, TypeToken<T> var1) {
/* 24 */     Class<T> var2 = var1.getRawType();
/* 25 */     if (!var2.isEnum()) {
/* 26 */       return null;
/*    */     }
/*    */     
/* 29 */     Map<String, T> var3 = Maps.newHashMap();
/* 30 */     for (T var7 : var2.getEnumConstants()) {
/* 31 */       var3.put(a(var7), var7);
/*    */     }
/*    */     
/* 34 */     return new TypeAdapter<T>(this, var3)
/*    */       {
/*    */         public void write(JsonWriter var0, T var1) throws IOException {
/* 37 */           if (var1 == null) {
/* 38 */             var0.nullValue();
/*    */           } else {
/* 40 */             var0.value(ChatTypeAdapterFactory.a(this.b, var1));
/*    */           } 
/*    */         }
/*    */ 
/*    */         
/*    */         @Nullable
/*    */         public T read(JsonReader var0) throws IOException {
/* 47 */           if (var0.peek() == JsonToken.NULL) {
/* 48 */             var0.nextNull();
/* 49 */             return null;
/*    */           } 
/* 51 */           return (T)this.a.get(var0.nextString());
/*    */         }
/*    */       };
/*    */   }
/*    */ 
/*    */   
/*    */   private String a(Object var0) {
/* 58 */     if (var0 instanceof Enum) {
/* 59 */       return ((Enum)var0).name().toLowerCase(Locale.ROOT);
/*    */     }
/* 61 */     return var0.toString().toLowerCase(Locale.ROOT);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ChatTypeAdapterFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
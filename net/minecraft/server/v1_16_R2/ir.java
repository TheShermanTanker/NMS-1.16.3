/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.Maps;
/*    */ import com.google.gson.JsonArray;
/*    */ import com.google.gson.JsonElement;
/*    */ import com.google.gson.JsonObject;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import java.util.function.Supplier;
/*    */ 
/*    */ public class ir
/*    */   implements Supplier<JsonElement>
/*    */ {
/* 14 */   private final Map<it<?>, it<?>.a> a = Maps.newLinkedHashMap();
/*    */   
/*    */   public <T> ir a(it<T> var0, T var1) {
/* 17 */     it<?>.a var2 = this.a.put(var0, var0.a(var1));
/* 18 */     if (var2 != null) {
/* 19 */       throw new IllegalStateException("Replacing value of " + var2 + " with " + var1);
/*    */     }
/* 21 */     return this;
/*    */   }
/*    */   
/*    */   public static ir a() {
/* 25 */     return new ir();
/*    */   }
/*    */   
/*    */   public static ir a(ir var0, ir var1) {
/* 29 */     ir var2 = new ir();
/* 30 */     var2.a.putAll(var0.a);
/* 31 */     var2.a.putAll(var1.a);
/* 32 */     return var2;
/*    */   }
/*    */ 
/*    */   
/*    */   public JsonElement get() {
/* 37 */     JsonObject var0 = new JsonObject();
/* 38 */     this.a.values().forEach(var1 -> var1.a(var0));
/* 39 */     return (JsonElement)var0;
/*    */   }
/*    */   
/*    */   public static JsonElement a(List<ir> var0) {
/* 43 */     if (var0.size() == 1) {
/* 44 */       return ((ir)var0.get(0)).get();
/*    */     }
/*    */     
/* 47 */     JsonArray var1 = new JsonArray();
/* 48 */     var0.forEach(var1 -> var0.add(var1.get()));
/* 49 */     return (JsonElement)var1;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ir.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
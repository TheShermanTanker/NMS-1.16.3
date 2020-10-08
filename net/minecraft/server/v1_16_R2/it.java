/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.gson.JsonElement;
/*    */ import com.google.gson.JsonObject;
/*    */ import java.util.function.Function;
/*    */ 
/*    */ public class it<T>
/*    */ {
/*    */   private final String a;
/*    */   private final Function<T, JsonElement> b;
/*    */   
/*    */   public it(String var0, Function<T, JsonElement> var1) {
/* 13 */     this.a = var0;
/* 14 */     this.b = var1;
/*    */   }
/*    */   
/*    */   public a a(T var0) {
/* 18 */     return new a(this, var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 23 */     return this.a;
/*    */   }
/*    */   
/*    */   public class a {
/*    */     private final T b;
/*    */     
/*    */     public a(it var0, T var1) {
/* 30 */       this.b = var1;
/*    */     }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/*    */     public void a(JsonObject var0) {
/* 38 */       var0.add(it.a(this.a), it.b(this.a).apply(this.b));
/*    */     }
/*    */ 
/*    */     
/*    */     public String toString() {
/* 43 */       return it.a(this.a) + "=" + this.b;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\it.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
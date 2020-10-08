/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.gson.JsonObject;
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ public abstract class JsonListEntry<T>
/*    */ {
/*    */   @Nullable
/*    */   private final T a;
/*    */   
/*    */   public JsonListEntry(@Nullable T var0) {
/* 12 */     this.a = var0;
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public T getKey() {
/* 17 */     return this.a;
/*    */   }
/*    */   
/*    */   boolean hasExpired() {
/* 21 */     return false;
/*    */   }
/*    */   
/*    */   protected abstract void a(JsonObject paramJsonObject);
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\JsonListEntry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
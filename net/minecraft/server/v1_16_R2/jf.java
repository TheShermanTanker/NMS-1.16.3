/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.gson.JsonObject;
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public interface jf
/*    */ {
/*    */   void a(JsonObject paramJsonObject);
/*    */   
/*    */   default JsonObject a() {
/* 14 */     JsonObject var0 = new JsonObject();
/* 15 */     var0.addProperty("type", IRegistry.RECIPE_SERIALIZER.getKey(c()).toString());
/* 16 */     a(var0);
/* 17 */     return var0;
/*    */   }
/*    */   
/*    */   MinecraftKey b();
/*    */   
/*    */   RecipeSerializer<?> c();
/*    */   
/*    */   @Nullable
/*    */   JsonObject d();
/*    */   
/*    */   @Nullable
/*    */   MinecraftKey e();
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\jf.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.gson.JsonElement;
/*    */ import com.google.gson.JsonObject;
/*    */ import java.util.function.Supplier;
/*    */ 
/*    */ public class iv
/*    */   implements Supplier<JsonElement>
/*    */ {
/*    */   private final MinecraftKey a;
/*    */   
/*    */   public iv(MinecraftKey var0) {
/* 13 */     this.a = var0;
/*    */   }
/*    */ 
/*    */   
/*    */   public JsonElement get() {
/* 18 */     JsonObject var0 = new JsonObject();
/* 19 */     var0.addProperty("parent", this.a.toString());
/* 20 */     return (JsonElement)var0;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\iv.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
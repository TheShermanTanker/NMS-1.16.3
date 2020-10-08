/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.gson.JsonObject;
/*    */ import com.google.gson.JsonParseException;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ResourcePackInfoDeserializer
/*    */   implements ResourcePackMetaParser<ResourcePackInfo>
/*    */ {
/*    */   public ResourcePackInfo a(JsonObject var0) {
/* 12 */     IChatBaseComponent var1 = IChatBaseComponent.ChatSerializer.a(var0.get("description"));
/* 13 */     if (var1 == null) {
/* 14 */       throw new JsonParseException("Invalid/missing description!");
/*    */     }
/* 16 */     int var2 = ChatDeserializer.n(var0, "pack_format");
/* 17 */     return new ResourcePackInfo(var1, var2);
/*    */   }
/*    */ 
/*    */   
/*    */   public String a() {
/* 22 */     return "pack";
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ResourcePackInfoDeserializer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
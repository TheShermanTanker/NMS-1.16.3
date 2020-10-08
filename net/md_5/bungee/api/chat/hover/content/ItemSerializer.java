/*    */ package net.md_5.bungee.api.chat.hover.content;
/*    */ 
/*    */ import com.google.gson.JsonDeserializationContext;
/*    */ import com.google.gson.JsonDeserializer;
/*    */ import com.google.gson.JsonElement;
/*    */ import com.google.gson.JsonObject;
/*    */ import com.google.gson.JsonParseException;
/*    */ import com.google.gson.JsonPrimitive;
/*    */ import com.google.gson.JsonSerializationContext;
/*    */ import com.google.gson.JsonSerializer;
/*    */ import java.lang.reflect.Type;
/*    */ import net.md_5.bungee.api.chat.ItemTag;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ItemSerializer
/*    */   implements JsonSerializer<Item>, JsonDeserializer<Item>
/*    */ {
/*    */   public Item deserialize(JsonElement element, Type type, JsonDeserializationContext context) throws JsonParseException {
/* 20 */     JsonObject value = element.getAsJsonObject();
/*    */     
/* 22 */     int count = -1;
/* 23 */     if (value.has("Count")) {
/*    */       
/* 25 */       JsonPrimitive countObj = value.get("Count").getAsJsonPrimitive();
/*    */       
/* 27 */       if (countObj.isNumber()) {
/*    */         
/* 29 */         count = countObj.getAsInt();
/* 30 */       } else if (countObj.isString()) {
/*    */         
/* 32 */         String cString = countObj.getAsString();
/* 33 */         char last = cString.charAt(cString.length() - 1);
/*    */         
/* 35 */         if (last == 'b' || last == 's' || last == 'l' || last == 'f' || last == 'd')
/*    */         {
/* 37 */           cString = cString.substring(0, cString.length() - 1);
/*    */         }
/*    */         
/*    */         try {
/* 41 */           count = Integer.parseInt(cString);
/* 42 */         } catch (NumberFormatException ex) {
/*    */           
/* 44 */           throw new JsonParseException("Could not parse count: " + ex);
/*    */         } 
/*    */       } 
/*    */     } 
/*    */     
/* 49 */     return new Item(
/* 50 */         value.has("id") ? value.get("id").getAsString() : null, count, 
/*    */         
/* 52 */         value.has("tag") ? (ItemTag)context.deserialize(value.get("tag"), ItemTag.class) : null);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public JsonElement serialize(Item content, Type type, JsonSerializationContext context) {
/* 59 */     JsonObject object = new JsonObject();
/* 60 */     object.addProperty("id", (content.getId() == null) ? "minecraft:air" : content.getId());
/* 61 */     if (content.getCount() != -1)
/*    */     {
/* 63 */       object.addProperty("Count", Integer.valueOf(content.getCount()));
/*    */     }
/* 65 */     if (content.getTag() != null)
/*    */     {
/* 67 */       object.add("tag", context.serialize(content.getTag()));
/*    */     }
/* 69 */     return (JsonElement)object;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\md_5\bungee\api\chat\hover\content\ItemSerializer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
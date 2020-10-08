/*    */ package net.md_5.bungee.api.chat.hover.content;
/*    */ 
/*    */ import com.google.gson.JsonDeserializationContext;
/*    */ import com.google.gson.JsonDeserializer;
/*    */ import com.google.gson.JsonElement;
/*    */ import com.google.gson.JsonObject;
/*    */ import com.google.gson.JsonParseException;
/*    */ import com.google.gson.JsonSerializationContext;
/*    */ import com.google.gson.JsonSerializer;
/*    */ import java.lang.reflect.Type;
/*    */ import net.md_5.bungee.api.chat.BaseComponent;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EntitySerializer
/*    */   implements JsonSerializer<Entity>, JsonDeserializer<Entity>
/*    */ {
/*    */   public Entity deserialize(JsonElement element, Type type, JsonDeserializationContext context) throws JsonParseException {
/* 19 */     JsonObject value = element.getAsJsonObject();
/*    */     
/* 21 */     return new Entity(
/* 22 */         value.has("type") ? value.get("type").getAsString() : null, value
/* 23 */         .get("id").getAsString(), 
/* 24 */         value.has("name") ? (BaseComponent)context.deserialize(value.get("name"), BaseComponent.class) : null);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public JsonElement serialize(Entity content, Type type, JsonSerializationContext context) {
/* 31 */     JsonObject object = new JsonObject();
/* 32 */     object.addProperty("type", (content.getType() != null) ? content.getType() : "minecraft:pig");
/* 33 */     object.addProperty("id", content.getId());
/* 34 */     if (content.getName() != null)
/*    */     {
/* 36 */       object.add("name", context.serialize(content.getName()));
/*    */     }
/* 38 */     return (JsonElement)object;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\md_5\bungee\api\chat\hover\content\EntitySerializer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
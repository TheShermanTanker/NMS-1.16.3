/*    */ package net.md_5.bungee.chat;
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
/*    */ import net.md_5.bungee.api.chat.ScoreComponent;
/*    */ 
/*    */ public class ScoreComponentSerializer
/*    */   extends BaseComponentSerializer
/*    */   implements JsonSerializer<ScoreComponent>, JsonDeserializer<ScoreComponent>
/*    */ {
/*    */   public ScoreComponent deserialize(JsonElement element, Type type, JsonDeserializationContext context) throws JsonParseException {
/* 19 */     JsonObject json = element.getAsJsonObject();
/* 20 */     if (!json.has("score"))
/*    */     {
/* 22 */       throw new JsonParseException("Could not parse JSON: missing 'score' property");
/*    */     }
/* 24 */     JsonObject score = json.get("score").getAsJsonObject();
/* 25 */     if (!score.has("name") || !score.has("objective"))
/*    */     {
/* 27 */       throw new JsonParseException("A score component needs at least a name and an objective");
/*    */     }
/*    */     
/* 30 */     String name = score.get("name").getAsString();
/* 31 */     String objective = score.get("objective").getAsString();
/* 32 */     ScoreComponent component = new ScoreComponent(name, objective);
/* 33 */     if (score.has("value") && !score.get("value").getAsString().isEmpty())
/*    */     {
/* 35 */       component.setValue(score.get("value").getAsString());
/*    */     }
/*    */     
/* 38 */     deserialize(json, (BaseComponent)component, context);
/* 39 */     return component;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public JsonElement serialize(ScoreComponent component, Type type, JsonSerializationContext context) {
/* 45 */     JsonObject root = new JsonObject();
/* 46 */     serialize(root, (BaseComponent)component, context);
/* 47 */     JsonObject json = new JsonObject();
/* 48 */     json.addProperty("name", component.getName());
/* 49 */     json.addProperty("objective", component.getObjective());
/* 50 */     json.addProperty("value", component.getValue());
/* 51 */     root.add("score", (JsonElement)json);
/* 52 */     return (JsonElement)root;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\md_5\bungee\chat\ScoreComponentSerializer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
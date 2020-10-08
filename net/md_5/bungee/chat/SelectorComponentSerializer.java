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
/*    */ import net.md_5.bungee.api.chat.SelectorComponent;
/*    */ 
/*    */ public class SelectorComponentSerializer
/*    */   extends BaseComponentSerializer
/*    */   implements JsonSerializer<SelectorComponent>, JsonDeserializer<SelectorComponent>
/*    */ {
/*    */   public SelectorComponent deserialize(JsonElement element, Type type, JsonDeserializationContext context) throws JsonParseException {
/* 19 */     JsonObject object = element.getAsJsonObject();
/* 20 */     if (!object.has("selector"))
/*    */     {
/* 22 */       throw new JsonParseException("Could not parse JSON: missing 'selector' property");
/*    */     }
/* 24 */     SelectorComponent component = new SelectorComponent(object.get("selector").getAsString());
/* 25 */     deserialize(object, (BaseComponent)component, context);
/* 26 */     return component;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public JsonElement serialize(SelectorComponent component, Type type, JsonSerializationContext context) {
/* 32 */     JsonObject object = new JsonObject();
/* 33 */     serialize(object, (BaseComponent)component, context);
/* 34 */     object.addProperty("selector", component.getSelector());
/* 35 */     return (JsonElement)object;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\md_5\bungee\chat\SelectorComponentSerializer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
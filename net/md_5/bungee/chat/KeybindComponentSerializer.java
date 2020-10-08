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
/*    */ import net.md_5.bungee.api.chat.KeybindComponent;
/*    */ 
/*    */ public class KeybindComponentSerializer
/*    */   extends BaseComponentSerializer
/*    */   implements JsonSerializer<KeybindComponent>, JsonDeserializer<KeybindComponent>
/*    */ {
/*    */   public KeybindComponent deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
/* 19 */     JsonObject object = json.getAsJsonObject();
/* 20 */     if (!object.has("keybind"))
/*    */     {
/* 22 */       throw new JsonParseException("Could not parse JSON: missing 'keybind' property");
/*    */     }
/* 24 */     KeybindComponent component = new KeybindComponent();
/* 25 */     deserialize(object, (BaseComponent)component, context);
/* 26 */     component.setKeybind(object.get("keybind").getAsString());
/* 27 */     return component;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public JsonElement serialize(KeybindComponent src, Type typeOfSrc, JsonSerializationContext context) {
/* 33 */     JsonObject object = new JsonObject();
/* 34 */     serialize(object, (BaseComponent)src, context);
/* 35 */     object.addProperty("keybind", src.getKeybind());
/* 36 */     return (JsonElement)object;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\md_5\bungee\chat\KeybindComponentSerializer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
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
/*    */ import java.util.List;
/*    */ import net.md_5.bungee.api.chat.BaseComponent;
/*    */ import net.md_5.bungee.api.chat.TextComponent;
/*    */ 
/*    */ 
/*    */ public class TextComponentSerializer
/*    */   extends BaseComponentSerializer
/*    */   implements JsonSerializer<TextComponent>, JsonDeserializer<TextComponent>
/*    */ {
/*    */   public TextComponent deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
/* 21 */     TextComponent component = new TextComponent();
/* 22 */     JsonObject object = json.getAsJsonObject();
/* 23 */     if (!object.has("text"))
/*    */     {
/* 25 */       throw new JsonParseException("Could not parse JSON: missing 'text' property");
/*    */     }
/* 27 */     component.setText(object.get("text").getAsString());
/* 28 */     deserialize(object, (BaseComponent)component, context);
/* 29 */     return component;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public JsonElement serialize(TextComponent src, Type typeOfSrc, JsonSerializationContext context) {
/* 35 */     List<BaseComponent> extra = src.getExtra();
/* 36 */     JsonObject object = new JsonObject();
/* 37 */     object.addProperty("text", src.getText());
/* 38 */     if (src.hasFormatting() || (extra != null && !extra.isEmpty()))
/*    */     {
/* 40 */       serialize(object, (BaseComponent)src, context);
/*    */     }
/* 42 */     return (JsonElement)object;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\md_5\bungee\chat\TextComponentSerializer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
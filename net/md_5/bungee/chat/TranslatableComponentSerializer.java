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
/*    */ import java.util.Arrays;
/*    */ import net.md_5.bungee.api.chat.BaseComponent;
/*    */ import net.md_5.bungee.api.chat.TranslatableComponent;
/*    */ 
/*    */ 
/*    */ public class TranslatableComponentSerializer
/*    */   extends BaseComponentSerializer
/*    */   implements JsonSerializer<TranslatableComponent>, JsonDeserializer<TranslatableComponent>
/*    */ {
/*    */   public TranslatableComponent deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
/* 21 */     TranslatableComponent component = new TranslatableComponent();
/* 22 */     JsonObject object = json.getAsJsonObject();
/* 23 */     deserialize(object, (BaseComponent)component, context);
/* 24 */     if (!object.has("translate"))
/*    */     {
/* 26 */       throw new JsonParseException("Could not parse JSON: missing 'translate' property");
/*    */     }
/* 28 */     component.setTranslate(object.get("translate").getAsString());
/* 29 */     if (object.has("with"))
/*    */     {
/* 31 */       component.setWith(Arrays.asList((Object[])context.deserialize(object.get("with"), BaseComponent[].class)));
/*    */     }
/* 33 */     return component;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public JsonElement serialize(TranslatableComponent src, Type typeOfSrc, JsonSerializationContext context) {
/* 39 */     JsonObject object = new JsonObject();
/* 40 */     serialize(object, (BaseComponent)src, context);
/* 41 */     object.addProperty("translate", src.getTranslate());
/* 42 */     if (src.getWith() != null)
/*    */     {
/* 44 */       object.add("with", context.serialize(src.getWith()));
/*    */     }
/* 46 */     return (JsonElement)object;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\md_5\bungee\chat\TranslatableComponentSerializer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
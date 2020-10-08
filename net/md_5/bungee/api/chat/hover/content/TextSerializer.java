/*    */ package net.md_5.bungee.api.chat.hover.content;
/*    */ 
/*    */ import com.google.gson.JsonDeserializationContext;
/*    */ import com.google.gson.JsonDeserializer;
/*    */ import com.google.gson.JsonElement;
/*    */ import com.google.gson.JsonParseException;
/*    */ import com.google.gson.JsonSerializationContext;
/*    */ import com.google.gson.JsonSerializer;
/*    */ import java.lang.reflect.Type;
/*    */ import net.md_5.bungee.api.chat.BaseComponent;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TextSerializer
/*    */   implements JsonSerializer<Text>, JsonDeserializer<Text>
/*    */ {
/*    */   public Text deserialize(JsonElement element, Type type, JsonDeserializationContext context) throws JsonParseException {
/* 18 */     if (element.isJsonArray())
/*    */     {
/* 20 */       return new Text((BaseComponent[])context.deserialize(element, BaseComponent[].class)); } 
/* 21 */     if (element.isJsonPrimitive())
/*    */     {
/* 23 */       return new Text(element.getAsJsonPrimitive().getAsString());
/*    */     }
/*    */     
/* 26 */     return new Text(new BaseComponent[] { (BaseComponent)context
/*    */           
/* 28 */           .deserialize(element, BaseComponent.class) });
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public JsonElement serialize(Text content, Type type, JsonSerializationContext context) {
/* 36 */     return context.serialize(content.getValue());
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\md_5\bungee\api\chat\hover\content\TextSerializer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
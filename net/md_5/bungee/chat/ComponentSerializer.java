/*     */ package net.md_5.bungee.chat;
/*     */ 
/*     */ import com.google.gson.Gson;
/*     */ import com.google.gson.GsonBuilder;
/*     */ import com.google.gson.JsonDeserializationContext;
/*     */ import com.google.gson.JsonDeserializer;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonObject;
/*     */ import com.google.gson.JsonParseException;
/*     */ import com.google.gson.JsonParser;
/*     */ import java.lang.reflect.Type;
/*     */ import java.util.Set;
/*     */ import net.md_5.bungee.api.chat.BaseComponent;
/*     */ import net.md_5.bungee.api.chat.ItemTag;
/*     */ import net.md_5.bungee.api.chat.KeybindComponent;
/*     */ import net.md_5.bungee.api.chat.ScoreComponent;
/*     */ import net.md_5.bungee.api.chat.SelectorComponent;
/*     */ import net.md_5.bungee.api.chat.TextComponent;
/*     */ import net.md_5.bungee.api.chat.TranslatableComponent;
/*     */ import net.md_5.bungee.api.chat.hover.content.Entity;
/*     */ import net.md_5.bungee.api.chat.hover.content.EntitySerializer;
/*     */ import net.md_5.bungee.api.chat.hover.content.Item;
/*     */ import net.md_5.bungee.api.chat.hover.content.ItemSerializer;
/*     */ import net.md_5.bungee.api.chat.hover.content.Text;
/*     */ import net.md_5.bungee.api.chat.hover.content.TextSerializer;
/*     */ 
/*     */ public class ComponentSerializer
/*     */   implements JsonDeserializer<BaseComponent>
/*     */ {
/*  30 */   private static final JsonParser JSON_PARSER = new JsonParser();
/*  31 */   private static final Gson gson = (new GsonBuilder())
/*  32 */     .registerTypeAdapter(BaseComponent.class, new ComponentSerializer())
/*  33 */     .registerTypeAdapter(TextComponent.class, new TextComponentSerializer())
/*  34 */     .registerTypeAdapter(TranslatableComponent.class, new TranslatableComponentSerializer())
/*  35 */     .registerTypeAdapter(KeybindComponent.class, new KeybindComponentSerializer())
/*  36 */     .registerTypeAdapter(ScoreComponent.class, new ScoreComponentSerializer())
/*  37 */     .registerTypeAdapter(SelectorComponent.class, new SelectorComponentSerializer())
/*  38 */     .registerTypeAdapter(Entity.class, new EntitySerializer())
/*  39 */     .registerTypeAdapter(Text.class, new TextSerializer())
/*  40 */     .registerTypeAdapter(Item.class, new ItemSerializer())
/*  41 */     .registerTypeAdapter(ItemTag.class, new ItemTag.Serializer())
/*  42 */     .create();
/*     */   
/*  44 */   public static final ThreadLocal<Set<BaseComponent>> serializedComponents = new ThreadLocal<>();
/*     */ 
/*     */   
/*     */   public static BaseComponent[] parse(String json) {
/*  48 */     JsonElement jsonElement = JSON_PARSER.parse(json);
/*     */     
/*  50 */     if (jsonElement.isJsonArray())
/*     */     {
/*  52 */       return (BaseComponent[])gson.fromJson(jsonElement, BaseComponent[].class);
/*     */     }
/*     */     
/*  55 */     return new BaseComponent[] { (BaseComponent)gson
/*     */         
/*  57 */         .fromJson(jsonElement, BaseComponent.class) };
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String toString(Object object) {
/*  64 */     return gson.toJson(object);
/*     */   }
/*     */ 
/*     */   
/*     */   public static String toString(BaseComponent component) {
/*  69 */     return gson.toJson(component);
/*     */   }
/*     */ 
/*     */   
/*     */   public static String toString(BaseComponent... components) {
/*  74 */     if (components.length == 1)
/*     */     {
/*  76 */       return gson.toJson(components[0]);
/*     */     }
/*     */     
/*  79 */     return gson.toJson(new TextComponent(components));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BaseComponent deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
/*  86 */     if (json.isJsonPrimitive())
/*     */     {
/*  88 */       return (BaseComponent)new TextComponent(json.getAsString());
/*     */     }
/*  90 */     JsonObject object = json.getAsJsonObject();
/*  91 */     if (object.has("translate"))
/*     */     {
/*  93 */       return (BaseComponent)context.deserialize(json, TranslatableComponent.class);
/*     */     }
/*  95 */     if (object.has("keybind"))
/*     */     {
/*  97 */       return (BaseComponent)context.deserialize(json, KeybindComponent.class);
/*     */     }
/*  99 */     if (object.has("score"))
/*     */     {
/* 101 */       return (BaseComponent)context.deserialize(json, ScoreComponent.class);
/*     */     }
/* 103 */     if (object.has("selector"))
/*     */     {
/* 105 */       return (BaseComponent)context.deserialize(json, SelectorComponent.class);
/*     */     }
/* 107 */     return (BaseComponent)context.deserialize(json, TextComponent.class);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\md_5\bungee\chat\ComponentSerializer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
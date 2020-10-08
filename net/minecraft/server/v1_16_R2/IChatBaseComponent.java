/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.Streams;
/*     */ import com.google.gson.Gson;
/*     */ import com.google.gson.GsonBuilder;
/*     */ import com.google.gson.JsonArray;
/*     */ import com.google.gson.JsonDeserializationContext;
/*     */ import com.google.gson.JsonDeserializer;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonObject;
/*     */ import com.google.gson.JsonParseException;
/*     */ import com.google.gson.JsonPrimitive;
/*     */ import com.google.gson.JsonSerializationContext;
/*     */ import com.google.gson.JsonSerializer;
/*     */ import com.google.gson.stream.JsonReader;
/*     */ import com.mojang.brigadier.Message;
/*     */ import com.mojang.brigadier.StringReader;
/*     */ import java.io.StringReader;
/*     */ import java.lang.reflect.Field;
/*     */ import java.lang.reflect.Type;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Optional;
/*     */ import java.util.stream.Stream;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ public interface IChatBaseComponent
/*     */   extends Message, IChatFormatted, Iterable<IChatBaseComponent>
/*     */ {
/*     */   default Stream<IChatBaseComponent> stream() {
/*  32 */     return Streams.concat(new Stream[] { Stream.of(this), getSiblings().stream().flatMap(IChatBaseComponent::stream) });
/*     */   }
/*     */ 
/*     */   
/*     */   default Iterator<IChatBaseComponent> iterator() {
/*  37 */     return stream().iterator();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default String getString() {
/*  47 */     return super.getString();
/*     */   }
/*     */   
/*     */   default String a(int i) {
/*  51 */     StringBuilder stringbuilder = new StringBuilder();
/*     */     
/*  53 */     a(s -> {
/*     */           int j = i - stringbuilder.length();
/*     */           
/*     */           if (j <= 0) {
/*     */             return b;
/*     */           }
/*     */           
/*     */           stringbuilder.append((s.length() <= j) ? s : s.substring(0, j));
/*     */           return Optional.empty();
/*     */         });
/*  63 */     return stringbuilder.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   default <T> Optional<T> a(IChatFormatted.a<T> ichatformatted_a) {
/*  74 */     Optional<T> optional1, optional = b(ichatformatted_a);
/*     */     
/*  76 */     if (optional.isPresent()) {
/*  77 */       return optional;
/*     */     }
/*  79 */     Iterator<IChatBaseComponent> iterator = getSiblings().iterator();
/*     */ 
/*     */ 
/*     */     
/*     */     do {
/*  84 */       if (!iterator.hasNext()) {
/*  85 */         return Optional.empty();
/*     */       }
/*     */       
/*  88 */       IChatBaseComponent ichatbasecomponent = iterator.next();
/*     */       
/*  90 */       optional1 = ichatbasecomponent.a(ichatformatted_a);
/*  91 */     } while (!optional1.isPresent());
/*     */     
/*  93 */     return optional1;
/*     */   }
/*     */ 
/*     */   
/*     */   default <T> Optional<T> b(IChatFormatted.a<T> ichatformatted_a) {
/*  98 */     return ichatformatted_a.accept(getText());
/*     */   } ChatModifier getChatModifier(); String getText(); List<IChatBaseComponent> getSiblings(); IChatMutableComponent g();
/*     */   IChatMutableComponent mutableCopy();
/*     */   public static class ChatSerializer implements JsonDeserializer<IChatMutableComponent>, JsonSerializer<IChatBaseComponent> { private static final Gson a;
/*     */     static {
/* 103 */       a = SystemUtils.<Gson>a(() -> {
/*     */             GsonBuilder gsonbuilder = new GsonBuilder();
/*     */             
/*     */             gsonbuilder.disableHtmlEscaping();
/*     */             gsonbuilder.registerTypeHierarchyAdapter(IChatBaseComponent.class, new ChatSerializer());
/*     */             gsonbuilder.registerTypeHierarchyAdapter(ChatModifier.class, new ChatModifier.ChatModifierSerializer());
/*     */             gsonbuilder.registerTypeAdapterFactory(new ChatTypeAdapterFactory());
/*     */             return gsonbuilder.create();
/*     */           });
/* 112 */       b = SystemUtils.<Field>a(() -> {
/*     */             try {
/*     */               new JsonReader(new StringReader(""));
/*     */               
/*     */               Field field = JsonReader.class.getDeclaredField("pos");
/*     */               field.setAccessible(true);
/*     */               return field;
/* 119 */             } catch (NoSuchFieldException nosuchfieldexception) {
/*     */               throw new IllegalStateException("Couldn't get field 'pos' for JsonReader", nosuchfieldexception);
/*     */             } 
/*     */           });
/* 123 */       c = SystemUtils.<Field>a(() -> {
/*     */             try {
/*     */               new JsonReader(new StringReader(""));
/*     */               
/*     */               Field field = JsonReader.class.getDeclaredField("lineStart");
/*     */               field.setAccessible(true);
/*     */               return field;
/* 130 */             } catch (NoSuchFieldException nosuchfieldexception) {
/*     */               throw new IllegalStateException("Couldn't get field 'lineStart' for JsonReader", nosuchfieldexception);
/*     */             } 
/*     */           });
/*     */     }
/*     */     private static final Field b; private static final Field c;
/*     */     public IChatMutableComponent deserialize(JsonElement jsonelement, Type type, JsonDeserializationContext jsondeserializationcontext) throws JsonParseException {
/*     */       Object object;
/* 138 */       if (jsonelement.isJsonPrimitive())
/* 139 */         return new ChatComponentText(jsonelement.getAsString()); 
/* 140 */       if (!jsonelement.isJsonObject()) {
/* 141 */         if (jsonelement.isJsonArray()) {
/* 142 */           JsonArray jsonarray = jsonelement.getAsJsonArray();
/* 143 */           IChatMutableComponent ichatmutablecomponent = null;
/* 144 */           Iterator<JsonElement> iterator = jsonarray.iterator();
/*     */           
/* 146 */           while (iterator.hasNext()) {
/* 147 */             JsonElement jsonelement1 = iterator.next();
/* 148 */             IChatMutableComponent ichatmutablecomponent1 = deserialize(jsonelement1, jsonelement1.getClass(), jsondeserializationcontext);
/*     */             
/* 150 */             if (ichatmutablecomponent == null) {
/* 151 */               ichatmutablecomponent = ichatmutablecomponent1; continue;
/*     */             } 
/* 153 */             ichatmutablecomponent.addSibling(ichatmutablecomponent1);
/*     */           } 
/*     */ 
/*     */           
/* 157 */           return ichatmutablecomponent;
/*     */         } 
/* 159 */         throw new JsonParseException("Don't know how to turn " + jsonelement + " into a Component");
/*     */       } 
/*     */       
/* 162 */       JsonObject jsonobject = jsonelement.getAsJsonObject();
/*     */ 
/*     */       
/* 165 */       if (jsonobject.has("text")) {
/* 166 */         object = new ChatComponentText(ChatDeserializer.h(jsonobject, "text"));
/*     */ 
/*     */       
/*     */       }
/* 170 */       else if (jsonobject.has("translate")) {
/* 171 */         String s = ChatDeserializer.h(jsonobject, "translate");
/* 172 */         if (jsonobject.has("with")) {
/* 173 */           JsonArray jsonarray1 = ChatDeserializer.u(jsonobject, "with");
/* 174 */           Object[] aobject = new Object[jsonarray1.size()];
/*     */           
/* 176 */           for (int i = 0; i < aobject.length; i++) {
/* 177 */             aobject[i] = deserialize(jsonarray1.get(i), type, jsondeserializationcontext);
/* 178 */             if (aobject[i] instanceof ChatComponentText) {
/* 179 */               ChatComponentText chatcomponenttext = (ChatComponentText)aobject[i];
/*     */               
/* 181 */               if (chatcomponenttext.getChatModifier().g() && chatcomponenttext.getSiblings().isEmpty()) {
/* 182 */                 aobject[i] = chatcomponenttext.h();
/*     */               }
/*     */             } 
/*     */           } 
/*     */           
/* 187 */           object = new ChatMessage(s, aobject);
/*     */         } else {
/* 189 */           object = new ChatMessage(s);
/*     */         } 
/* 191 */       } else if (jsonobject.has("score")) {
/* 192 */         JsonObject jsonobject1 = ChatDeserializer.t(jsonobject, "score");
/*     */         
/* 194 */         if (!jsonobject1.has("name") || !jsonobject1.has("objective")) {
/* 195 */           throw new JsonParseException("A score component needs a least a name and an objective");
/*     */         }
/*     */         
/* 198 */         object = new ChatComponentScore(ChatDeserializer.h(jsonobject1, "name"), ChatDeserializer.h(jsonobject1, "objective"));
/* 199 */       } else if (jsonobject.has("selector")) {
/* 200 */         object = new ChatComponentSelector(ChatDeserializer.h(jsonobject, "selector"));
/* 201 */       } else if (jsonobject.has("keybind")) {
/* 202 */         object = new ChatComponentKeybind(ChatDeserializer.h(jsonobject, "keybind"));
/*     */       } else {
/* 204 */         if (!jsonobject.has("nbt")) {
/* 205 */           throw new JsonParseException("Don't know how to turn " + jsonelement + " into a Component");
/*     */         }
/*     */         
/* 208 */         String s = ChatDeserializer.h(jsonobject, "nbt");
/* 209 */         boolean flag = ChatDeserializer.a(jsonobject, "interpret", false);
/*     */         
/* 211 */         if (jsonobject.has("block")) {
/* 212 */           object = new ChatComponentNBT.a(s, flag, ChatDeserializer.h(jsonobject, "block"));
/* 213 */         } else if (jsonobject.has("entity")) {
/* 214 */           object = new ChatComponentNBT.b(s, flag, ChatDeserializer.h(jsonobject, "entity"));
/*     */         } else {
/* 216 */           if (!jsonobject.has("storage")) {
/* 217 */             throw new JsonParseException("Don't know how to turn " + jsonelement + " into a Component");
/*     */           }
/*     */           
/* 220 */           object = new ChatComponentNBT.c(s, flag, new MinecraftKey(ChatDeserializer.h(jsonobject, "storage")));
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 225 */       if (jsonobject.has("extra")) {
/* 226 */         JsonArray jsonarray2 = ChatDeserializer.u(jsonobject, "extra");
/*     */         
/* 228 */         if (jsonarray2.size() <= 0) {
/* 229 */           throw new JsonParseException("Unexpected empty array of components");
/*     */         }
/*     */         
/* 232 */         for (int j = 0; j < jsonarray2.size(); j++) {
/* 233 */           ((IChatMutableComponent)object).addSibling(deserialize(jsonarray2.get(j), type, jsondeserializationcontext));
/*     */         }
/*     */       } 
/*     */       
/* 237 */       ((IChatMutableComponent)object).setChatModifier((ChatModifier)jsondeserializationcontext.deserialize(jsonelement, ChatModifier.class));
/* 238 */       return (IChatMutableComponent)object;
/*     */     }
/*     */ 
/*     */     
/*     */     private void a(ChatModifier chatmodifier, JsonObject jsonobject, JsonSerializationContext jsonserializationcontext) {
/* 243 */       JsonElement jsonelement = jsonserializationcontext.serialize(chatmodifier);
/*     */       
/* 245 */       if (jsonelement.isJsonObject()) {
/* 246 */         JsonObject jsonobject1 = (JsonObject)jsonelement;
/* 247 */         Iterator<Map.Entry<String, JsonElement>> iterator = jsonobject1.entrySet().iterator();
/*     */         
/* 249 */         while (iterator.hasNext()) {
/* 250 */           Map.Entry<String, JsonElement> entry = iterator.next();
/*     */           
/* 252 */           jsonobject.add(entry.getKey(), entry.getValue());
/*     */         } 
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public JsonElement serialize(IChatBaseComponent ichatbasecomponent, Type type, JsonSerializationContext jsonserializationcontext) {
/* 259 */       JsonObject jsonobject = new JsonObject();
/*     */       
/* 261 */       if (!ichatbasecomponent.getChatModifier().g()) {
/* 262 */         a(ichatbasecomponent.getChatModifier(), jsonobject, jsonserializationcontext);
/*     */       }
/*     */       
/* 265 */       if (!ichatbasecomponent.getSiblings().isEmpty()) {
/* 266 */         JsonArray jsonarray = new JsonArray();
/* 267 */         Iterator<IChatBaseComponent> iterator = ichatbasecomponent.getSiblings().iterator();
/*     */         
/* 269 */         while (iterator.hasNext()) {
/* 270 */           IChatBaseComponent ichatbasecomponent1 = iterator.next();
/*     */           
/* 272 */           jsonarray.add(serialize(ichatbasecomponent1, ichatbasecomponent1.getClass(), jsonserializationcontext));
/*     */         } 
/*     */         
/* 275 */         jsonobject.add("extra", (JsonElement)jsonarray);
/*     */       } 
/*     */       
/* 278 */       if (ichatbasecomponent instanceof ChatComponentText) {
/* 279 */         jsonobject.addProperty("text", ((ChatComponentText)ichatbasecomponent).h());
/* 280 */       } else if (ichatbasecomponent instanceof ChatMessage) {
/* 281 */         ChatMessage chatmessage = (ChatMessage)ichatbasecomponent;
/*     */         
/* 283 */         jsonobject.addProperty("translate", chatmessage.getKey());
/* 284 */         if (chatmessage.getArgs() != null && (chatmessage.getArgs()).length > 0) {
/* 285 */           JsonArray jsonarray1 = new JsonArray();
/* 286 */           Object[] aobject = chatmessage.getArgs();
/* 287 */           int i = aobject.length;
/*     */           
/* 289 */           for (int j = 0; j < i; j++) {
/* 290 */             Object object = aobject[j];
/*     */             
/* 292 */             if (object instanceof IChatBaseComponent) {
/* 293 */               jsonarray1.add(serialize((IChatBaseComponent)object, object.getClass(), jsonserializationcontext));
/*     */             } else {
/* 295 */               jsonarray1.add((JsonElement)new JsonPrimitive(String.valueOf(object)));
/*     */             } 
/*     */           } 
/*     */           
/* 299 */           jsonobject.add("with", (JsonElement)jsonarray1);
/*     */         } 
/* 301 */       } else if (ichatbasecomponent instanceof ChatComponentScore) {
/* 302 */         ChatComponentScore chatcomponentscore = (ChatComponentScore)ichatbasecomponent;
/* 303 */         JsonObject jsonobject1 = new JsonObject();
/*     */         
/* 305 */         jsonobject1.addProperty("name", chatcomponentscore.h());
/* 306 */         jsonobject1.addProperty("objective", chatcomponentscore.j());
/* 307 */         jsonobject.add("score", (JsonElement)jsonobject1);
/* 308 */       } else if (ichatbasecomponent instanceof ChatComponentSelector) {
/* 309 */         ChatComponentSelector chatcomponentselector = (ChatComponentSelector)ichatbasecomponent;
/*     */         
/* 311 */         jsonobject.addProperty("selector", chatcomponentselector.h());
/* 312 */       } else if (ichatbasecomponent instanceof ChatComponentKeybind) {
/* 313 */         ChatComponentKeybind chatcomponentkeybind = (ChatComponentKeybind)ichatbasecomponent;
/*     */         
/* 315 */         jsonobject.addProperty("keybind", chatcomponentkeybind.i());
/*     */       } else {
/* 317 */         if (!(ichatbasecomponent instanceof ChatComponentNBT)) {
/* 318 */           throw new IllegalArgumentException("Don't know how to serialize " + ichatbasecomponent + " as a Component");
/*     */         }
/*     */         
/* 321 */         ChatComponentNBT chatcomponentnbt = (ChatComponentNBT)ichatbasecomponent;
/*     */         
/* 323 */         jsonobject.addProperty("nbt", chatcomponentnbt.h());
/* 324 */         jsonobject.addProperty("interpret", Boolean.valueOf(chatcomponentnbt.i()));
/* 325 */         if (ichatbasecomponent instanceof ChatComponentNBT.a) {
/* 326 */           ChatComponentNBT.a chatcomponentnbt_a = (ChatComponentNBT.a)ichatbasecomponent;
/*     */           
/* 328 */           jsonobject.addProperty("block", chatcomponentnbt_a.j());
/* 329 */         } else if (ichatbasecomponent instanceof ChatComponentNBT.b) {
/* 330 */           ChatComponentNBT.b chatcomponentnbt_b = (ChatComponentNBT.b)ichatbasecomponent;
/*     */           
/* 332 */           jsonobject.addProperty("entity", chatcomponentnbt_b.j());
/*     */         } else {
/* 334 */           if (!(ichatbasecomponent instanceof ChatComponentNBT.c)) {
/* 335 */             throw new IllegalArgumentException("Don't know how to serialize " + ichatbasecomponent + " as a Component");
/*     */           }
/*     */           
/* 338 */           ChatComponentNBT.c chatcomponentnbt_c = (ChatComponentNBT.c)ichatbasecomponent;
/*     */           
/* 340 */           jsonobject.addProperty("storage", chatcomponentnbt_c.j().toString());
/*     */         } 
/*     */       } 
/*     */       
/* 344 */       return (JsonElement)jsonobject;
/*     */     }
/*     */     public static String componentToJson(IChatBaseComponent ichatbasecomponent) {
/* 347 */       return a(ichatbasecomponent);
/*     */     } public static String a(IChatBaseComponent ichatbasecomponent) {
/* 349 */       return a.toJson(ichatbasecomponent);
/*     */     }
/*     */     
/*     */     public static JsonElement b(IChatBaseComponent ichatbasecomponent) {
/* 353 */       return a.toJsonTree(ichatbasecomponent);
/*     */     } @Nullable
/*     */     public static IChatBaseComponent jsonToComponent(String json) {
/* 356 */       return a(json);
/*     */     } @Nullable
/*     */     public static IChatMutableComponent a(String s) {
/* 359 */       return ChatDeserializer.<IChatMutableComponent>a(a, s, IChatMutableComponent.class, false);
/*     */     }
/*     */     
/*     */     @Nullable
/*     */     public static IChatMutableComponent a(JsonElement jsonelement) {
/* 364 */       return (IChatMutableComponent)a.fromJson(jsonelement, IChatMutableComponent.class);
/*     */     }
/*     */     
/*     */     @Nullable
/*     */     public static IChatMutableComponent b(String s) {
/* 369 */       return ChatDeserializer.<IChatMutableComponent>a(a, s, IChatMutableComponent.class, true);
/*     */     }
/*     */     
/*     */     public static IChatMutableComponent a(StringReader com_mojang_brigadier_stringreader) {
/*     */       try {
/* 374 */         JsonReader jsonreader = new JsonReader(new StringReader(com_mojang_brigadier_stringreader.getRemaining()));
/*     */         
/* 376 */         jsonreader.setLenient(false);
/* 377 */         IChatMutableComponent ichatmutablecomponent = (IChatMutableComponent)a.getAdapter(IChatMutableComponent.class).read(jsonreader);
/*     */         
/* 379 */         com_mojang_brigadier_stringreader.setCursor(com_mojang_brigadier_stringreader.getCursor() + a(jsonreader));
/* 380 */         return ichatmutablecomponent;
/* 381 */       } catch (StackOverflowError|java.io.IOException ioexception) {
/* 382 */         throw new JsonParseException(ioexception);
/*     */       } 
/*     */     }
/*     */     
/*     */     private static int a(JsonReader jsonreader) {
/*     */       try {
/* 388 */         return b.getInt(jsonreader) - c.getInt(jsonreader) + 1;
/* 389 */       } catch (IllegalAccessException illegalaccessexception) {
/* 390 */         throw new IllegalStateException("Couldn't read position of JsonReader", illegalaccessexception);
/*     */       } 
/*     */     } }
/*     */ 
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\IChatBaseComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
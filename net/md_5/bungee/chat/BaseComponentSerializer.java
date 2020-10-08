/*     */ package net.md_5.bungee.chat;
/*     */ 
/*     */ import com.google.common.base.Preconditions;
/*     */ import com.google.gson.JsonDeserializationContext;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonObject;
/*     */ import com.google.gson.JsonParseException;
/*     */ import com.google.gson.JsonSerializationContext;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collections;
/*     */ import java.util.IdentityHashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Locale;
/*     */ import java.util.Set;
/*     */ import net.md_5.bungee.api.ChatColor;
/*     */ import net.md_5.bungee.api.chat.BaseComponent;
/*     */ import net.md_5.bungee.api.chat.ClickEvent;
/*     */ import net.md_5.bungee.api.chat.HoverEvent;
/*     */ import net.md_5.bungee.api.chat.hover.content.Content;
/*     */ 
/*     */ public class BaseComponentSerializer {
/*     */   protected void deserialize(JsonObject object, BaseComponent component, JsonDeserializationContext context) {
/*     */     HoverEvent hoverEvent;
/*  25 */     if (object.has("color"))
/*     */     {
/*  27 */       component.setColor(ChatColor.of(object.get("color").getAsString()));
/*     */     }
/*  29 */     if (object.has("font"))
/*     */     {
/*  31 */       component.setFont(object.get("font").getAsString());
/*     */     }
/*  33 */     if (object.has("bold"))
/*     */     {
/*  35 */       component.setBold(Boolean.valueOf(object.get("bold").getAsBoolean()));
/*     */     }
/*  37 */     if (object.has("italic"))
/*     */     {
/*  39 */       component.setItalic(Boolean.valueOf(object.get("italic").getAsBoolean()));
/*     */     }
/*  41 */     if (object.has("underlined"))
/*     */     {
/*  43 */       component.setUnderlined(Boolean.valueOf(object.get("underlined").getAsBoolean()));
/*     */     }
/*  45 */     if (object.has("strikethrough"))
/*     */     {
/*  47 */       component.setStrikethrough(Boolean.valueOf(object.get("strikethrough").getAsBoolean()));
/*     */     }
/*  49 */     if (object.has("obfuscated"))
/*     */     {
/*  51 */       component.setObfuscated(Boolean.valueOf(object.get("obfuscated").getAsBoolean()));
/*     */     }
/*  53 */     if (object.has("insertion"))
/*     */     {
/*  55 */       component.setInsertion(object.get("insertion").getAsString());
/*     */     }
/*  57 */     if (object.has("extra"))
/*     */     {
/*  59 */       component.setExtra(Arrays.asList((Object[])context.deserialize(object.get("extra"), BaseComponent[].class)));
/*     */     }
/*     */ 
/*     */     
/*  63 */     if (object.has("clickEvent")) {
/*     */       
/*  65 */       JsonObject event = object.getAsJsonObject("clickEvent");
/*  66 */       component.setClickEvent(new ClickEvent(
/*  67 */             ClickEvent.Action.valueOf(event.get("action").getAsString().toUpperCase(Locale.ROOT)), event
/*  68 */             .get("value").getAsString()));
/*     */     } 
/*  70 */     if (object.has("hoverEvent"))
/*     */     
/*  72 */     { JsonObject event = object.getAsJsonObject("hoverEvent");
/*  73 */       hoverEvent = null;
/*  74 */       HoverEvent.Action action = HoverEvent.Action.valueOf(event.get("action").getAsString().toUpperCase(Locale.ROOT));
/*     */       
/*  76 */       Iterator<String> iterator = Arrays.<String>asList(new String[] { "value", "contents" }).iterator(); while (true) { if (iterator.hasNext()) { String type = iterator.next();
/*     */           
/*  78 */           if (!event.has(type)) {
/*     */             continue;
/*     */           }
/*     */           
/*  82 */           JsonElement contents = event.get(type);
/*     */ 
/*     */           
/*     */           try {
/*     */             BaseComponent[] components;
/*     */ 
/*     */             
/*  89 */             if (contents.isJsonArray()) {
/*     */               
/*  91 */               components = (BaseComponent[])context.deserialize(contents, BaseComponent[].class);
/*     */             
/*     */             }
/*     */             else {
/*     */               
/*  96 */               components = new BaseComponent[] { (BaseComponent)context.deserialize(contents, BaseComponent.class) };
/*     */             } 
/*     */             
/*  99 */             hoverEvent = new HoverEvent(action, components); break;
/* 100 */           } catch (JsonParseException ex) {
/*     */             Content[] list;
/*     */             
/* 103 */             if (contents.isJsonArray()) {
/*     */               
/* 105 */               list = (Content[])context.deserialize(contents, HoverEvent.getClass(action, true));
/*     */             
/*     */             }
/*     */             else {
/*     */               
/* 110 */               list = new Content[] { (Content)context.deserialize(contents, HoverEvent.getClass(action, false)) };
/*     */             } 
/*     */             
/* 113 */             hoverEvent = new HoverEvent(action, new ArrayList(Arrays.asList((Object[])list)));
/*     */           }  }
/*     */         else
/*     */         { break; }
/*     */ 
/*     */         
/* 119 */         if (hoverEvent != null)
/*     */         {
/* 121 */           component.setHoverEvent(hoverEvent); }  return; }  } else { return; }  if (hoverEvent != null) component.setHoverEvent(hoverEvent);
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void serialize(JsonObject object, BaseComponent component, JsonSerializationContext context) {
/* 128 */     boolean first = false;
/* 129 */     if (ComponentSerializer.serializedComponents.get() == null) {
/*     */       
/* 131 */       first = true;
/* 132 */       ComponentSerializer.serializedComponents.set(Collections.newSetFromMap(new IdentityHashMap<>()));
/*     */     } 
/*     */     
/*     */     try {
/* 136 */       Preconditions.checkArgument(!((Set)ComponentSerializer.serializedComponents.get()).contains(component), "Component loop");
/* 137 */       ((Set<BaseComponent>)ComponentSerializer.serializedComponents.get()).add(component);
/* 138 */       if (component.getColorRaw() != null)
/*     */       {
/* 140 */         object.addProperty("color", component.getColorRaw().getName());
/*     */       }
/* 142 */       if (component.getFontRaw() != null)
/*     */       {
/* 144 */         object.addProperty("font", component.getFontRaw());
/*     */       }
/* 146 */       if (component.isBoldRaw() != null)
/*     */       {
/* 148 */         object.addProperty("bold", component.isBoldRaw());
/*     */       }
/* 150 */       if (component.isItalicRaw() != null)
/*     */       {
/* 152 */         object.addProperty("italic", component.isItalicRaw());
/*     */       }
/* 154 */       if (component.isUnderlinedRaw() != null)
/*     */       {
/* 156 */         object.addProperty("underlined", component.isUnderlinedRaw());
/*     */       }
/* 158 */       if (component.isStrikethroughRaw() != null)
/*     */       {
/* 160 */         object.addProperty("strikethrough", component.isStrikethroughRaw());
/*     */       }
/* 162 */       if (component.isObfuscatedRaw() != null)
/*     */       {
/* 164 */         object.addProperty("obfuscated", component.isObfuscatedRaw());
/*     */       }
/* 166 */       if (component.getInsertion() != null)
/*     */       {
/* 168 */         object.addProperty("insertion", component.getInsertion());
/*     */       }
/*     */       
/* 171 */       if (component.getExtra() != null)
/*     */       {
/* 173 */         object.add("extra", context.serialize(component.getExtra()));
/*     */       }
/*     */ 
/*     */       
/* 177 */       if (component.getClickEvent() != null) {
/*     */         
/* 179 */         JsonObject clickEvent = new JsonObject();
/* 180 */         clickEvent.addProperty("action", component.getClickEvent().getAction().toString().toLowerCase(Locale.ROOT));
/* 181 */         clickEvent.addProperty("value", component.getClickEvent().getValue());
/* 182 */         object.add("clickEvent", (JsonElement)clickEvent);
/*     */       } 
/* 184 */       if (component.getHoverEvent() != null) {
/*     */         
/* 186 */         JsonObject hoverEvent = new JsonObject();
/* 187 */         hoverEvent.addProperty("action", component.getHoverEvent().getAction().toString().toLowerCase(Locale.ROOT));
/* 188 */         if (component.getHoverEvent().isLegacy()) {
/*     */           
/* 190 */           hoverEvent.add("value", context.serialize(component.getHoverEvent().getContents().get(0)));
/*     */         } else {
/*     */           
/* 193 */           hoverEvent.add("contents", context.serialize((component.getHoverEvent().getContents().size() == 1) ? component
/* 194 */                 .getHoverEvent().getContents().get(0) : component.getHoverEvent().getContents()));
/*     */         } 
/* 196 */         object.add("hoverEvent", (JsonElement)hoverEvent);
/*     */       } 
/*     */     } finally {
/*     */       
/* 200 */       ((Set)ComponentSerializer.serializedComponents.get()).remove(component);
/* 201 */       if (first)
/*     */       {
/* 203 */         ComponentSerializer.serializedComponents.set(null);
/*     */       }
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\md_5\bungee\chat\BaseComponentSerializer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
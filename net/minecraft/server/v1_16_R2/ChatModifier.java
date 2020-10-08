/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.gson.JsonDeserializationContext;
/*     */ import com.google.gson.JsonDeserializer;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonObject;
/*     */ import com.google.gson.JsonParseException;
/*     */ import com.google.gson.JsonSerializationContext;
/*     */ import com.google.gson.JsonSerializer;
/*     */ import com.google.gson.JsonSyntaxException;
/*     */ import java.lang.reflect.Type;
/*     */ import java.util.Objects;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ public class ChatModifier
/*     */ {
/*  17 */   public static final ChatModifier a = new ChatModifier((ChatHexColor)null, (Boolean)null, (Boolean)null, (Boolean)null, (Boolean)null, (Boolean)null, (ChatClickable)null, (ChatHoverable)null, (String)null, (MinecraftKey)null);
/*  18 */   public static final MinecraftKey b = new MinecraftKey("minecraft", "default");
/*     */   @Nullable
/*     */   private final ChatHexColor color;
/*     */   @Nullable
/*     */   private final Boolean bold;
/*     */   @Nullable
/*     */   private final Boolean italic;
/*     */   @Nullable
/*     */   private final Boolean underlined;
/*     */   @Nullable
/*     */   private final Boolean strikethrough;
/*     */   @Nullable
/*     */   private final Boolean obfuscated;
/*     */   @Nullable
/*     */   private final ChatClickable clickEvent;
/*     */   @Nullable
/*     */   private final ChatHoverable hoverEvent;
/*     */   @Nullable
/*     */   private final String insertion;
/*     */   @Nullable
/*     */   private final MinecraftKey font;
/*     */   
/*     */   private ChatModifier(@Nullable ChatHexColor chathexcolor, @Nullable Boolean obool, @Nullable Boolean obool1, @Nullable Boolean obool2, @Nullable Boolean obool3, @Nullable Boolean obool4, @Nullable ChatClickable chatclickable, @Nullable ChatHoverable chathoverable, @Nullable String s, @Nullable MinecraftKey minecraftkey) {
/*  41 */     this.color = chathexcolor;
/*  42 */     this.bold = obool;
/*  43 */     this.italic = obool1;
/*  44 */     this.underlined = obool2;
/*  45 */     this.strikethrough = obool3;
/*  46 */     this.obfuscated = obool4;
/*  47 */     this.clickEvent = chatclickable;
/*  48 */     this.hoverEvent = chathoverable;
/*  49 */     this.insertion = s;
/*  50 */     this.font = minecraftkey;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public ChatHexColor getColor() {
/*  55 */     return this.color;
/*     */   }
/*     */   
/*     */   public boolean isBold() {
/*  59 */     return (this.bold == Boolean.TRUE);
/*     */   }
/*     */   
/*     */   public boolean isItalic() {
/*  63 */     return (this.italic == Boolean.TRUE);
/*     */   }
/*     */   
/*     */   public boolean isStrikethrough() {
/*  67 */     return (this.strikethrough == Boolean.TRUE);
/*     */   }
/*     */   
/*     */   public boolean isUnderlined() {
/*  71 */     return (this.underlined == Boolean.TRUE);
/*     */   }
/*     */   
/*     */   public boolean isRandom() {
/*  75 */     return (this.obfuscated == Boolean.TRUE);
/*     */   }
/*     */   
/*     */   public boolean g() {
/*  79 */     return (this == a);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public ChatClickable getClickEvent() {
/*  84 */     return this.clickEvent;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public ChatHoverable getHoverEvent() {
/*  89 */     return this.hoverEvent;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public String getInsertion() {
/*  94 */     return this.insertion;
/*     */   }
/*     */   
/*     */   public MinecraftKey getFont() {
/*  98 */     return (this.font != null) ? this.font : b;
/*     */   }
/*     */   
/*     */   public ChatModifier setColor(@Nullable ChatHexColor chathexcolor) {
/* 102 */     return new ChatModifier(chathexcolor, this.bold, this.italic, this.underlined, this.strikethrough, this.obfuscated, this.clickEvent, this.hoverEvent, this.insertion, this.font);
/*     */   }
/*     */   
/*     */   public ChatModifier setColor(@Nullable EnumChatFormat enumchatformat) {
/* 106 */     return setColor((enumchatformat != null) ? ChatHexColor.a(enumchatformat) : null);
/*     */   }
/*     */   
/*     */   public ChatModifier setBold(@Nullable Boolean obool) {
/* 110 */     return new ChatModifier(this.color, obool, this.italic, this.underlined, this.strikethrough, this.obfuscated, this.clickEvent, this.hoverEvent, this.insertion, this.font);
/*     */   }
/*     */   
/*     */   public ChatModifier setItalic(@Nullable Boolean obool) {
/* 114 */     return new ChatModifier(this.color, this.bold, obool, this.underlined, this.strikethrough, this.obfuscated, this.clickEvent, this.hoverEvent, this.insertion, this.font);
/*     */   }
/*     */ 
/*     */   
/*     */   public ChatModifier setStrikethrough(@Nullable Boolean obool) {
/* 119 */     return new ChatModifier(this.color, this.bold, this.italic, this.underlined, obool, this.obfuscated, this.clickEvent, this.hoverEvent, this.insertion, this.font);
/*     */   }
/*     */   
/*     */   public ChatModifier setUnderline(@Nullable Boolean obool) {
/* 123 */     return new ChatModifier(this.color, this.bold, this.italic, obool, this.strikethrough, this.obfuscated, this.clickEvent, this.hoverEvent, this.insertion, this.font);
/*     */   }
/*     */   
/*     */   public ChatModifier setRandom(@Nullable Boolean obool) {
/* 127 */     return new ChatModifier(this.color, this.bold, this.italic, this.underlined, this.strikethrough, obool, this.clickEvent, this.hoverEvent, this.insertion, this.font);
/*     */   }
/*     */ 
/*     */   
/*     */   public ChatModifier setChatClickable(@Nullable ChatClickable chatclickable) {
/* 132 */     return new ChatModifier(this.color, this.bold, this.italic, this.underlined, this.strikethrough, this.obfuscated, chatclickable, this.hoverEvent, this.insertion, this.font);
/*     */   }
/*     */   
/*     */   public ChatModifier setChatHoverable(@Nullable ChatHoverable chathoverable) {
/* 136 */     return new ChatModifier(this.color, this.bold, this.italic, this.underlined, this.strikethrough, this.obfuscated, this.clickEvent, chathoverable, this.insertion, this.font);
/*     */   }
/*     */   
/*     */   public ChatModifier setInsertion(@Nullable String s) {
/* 140 */     return new ChatModifier(this.color, this.bold, this.italic, this.underlined, this.strikethrough, this.obfuscated, this.clickEvent, this.hoverEvent, s, this.font);
/*     */   }
/*     */   
/*     */   public ChatModifier b(EnumChatFormat enumchatformat) {
/* 144 */     ChatHexColor chathexcolor = this.color;
/* 145 */     Boolean obool = this.bold;
/* 146 */     Boolean obool1 = this.italic;
/* 147 */     Boolean obool2 = this.strikethrough;
/* 148 */     Boolean obool3 = this.underlined;
/* 149 */     Boolean obool4 = this.obfuscated;
/*     */     
/* 151 */     switch (enumchatformat)
/*     */     { case OBFUSCATED:
/* 153 */         obool4 = Boolean.valueOf(true);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 173 */         return new ChatModifier(chathexcolor, obool, obool1, obool3, obool2, obool4, this.clickEvent, this.hoverEvent, this.insertion, this.font);case BOLD: obool = Boolean.valueOf(true); return new ChatModifier(chathexcolor, obool, obool1, obool3, obool2, obool4, this.clickEvent, this.hoverEvent, this.insertion, this.font);case STRIKETHROUGH: obool2 = Boolean.valueOf(true); return new ChatModifier(chathexcolor, obool, obool1, obool3, obool2, obool4, this.clickEvent, this.hoverEvent, this.insertion, this.font);case UNDERLINE: obool3 = Boolean.valueOf(true); return new ChatModifier(chathexcolor, obool, obool1, obool3, obool2, obool4, this.clickEvent, this.hoverEvent, this.insertion, this.font);case ITALIC: obool1 = Boolean.valueOf(true); return new ChatModifier(chathexcolor, obool, obool1, obool3, obool2, obool4, this.clickEvent, this.hoverEvent, this.insertion, this.font);case RESET: return a; }  chathexcolor = ChatHexColor.a(enumchatformat); return new ChatModifier(chathexcolor, obool, obool1, obool3, obool2, obool4, this.clickEvent, this.hoverEvent, this.insertion, this.font);
/*     */   }
/*     */   
/*     */   public ChatModifier a(EnumChatFormat... aenumchatformat) {
/* 177 */     ChatHexColor chathexcolor = this.color;
/* 178 */     Boolean obool = this.bold;
/* 179 */     Boolean obool1 = this.italic;
/* 180 */     Boolean obool2 = this.strikethrough;
/* 181 */     Boolean obool3 = this.underlined;
/* 182 */     Boolean obool4 = this.obfuscated;
/* 183 */     EnumChatFormat[] aenumchatformat1 = aenumchatformat;
/* 184 */     int i = aenumchatformat.length;
/*     */     
/* 186 */     for (int j = 0; j < i; j++) {
/* 187 */       EnumChatFormat enumchatformat = aenumchatformat1[j];
/*     */       
/* 189 */       switch (enumchatformat) {
/*     */         case OBFUSCATED:
/* 191 */           obool4 = Boolean.valueOf(true);
/*     */           break;
/*     */         case BOLD:
/* 194 */           obool = Boolean.valueOf(true);
/*     */           break;
/*     */         case STRIKETHROUGH:
/* 197 */           obool2 = Boolean.valueOf(true);
/*     */           break;
/*     */         case UNDERLINE:
/* 200 */           obool3 = Boolean.valueOf(true);
/*     */           break;
/*     */         case ITALIC:
/* 203 */           obool1 = Boolean.valueOf(true);
/*     */           break;
/*     */         case RESET:
/* 206 */           return a;
/*     */         default:
/* 208 */           chathexcolor = ChatHexColor.a(enumchatformat);
/*     */           break;
/*     */       } 
/*     */     } 
/* 212 */     return new ChatModifier(chathexcolor, obool, obool1, obool3, obool2, obool4, this.clickEvent, this.hoverEvent, this.insertion, this.font);
/*     */   }
/*     */   
/*     */   public ChatModifier setChatModifier(ChatModifier chatmodifier) {
/* 216 */     return (this == a) ? chatmodifier : ((chatmodifier == a) ? this : new ChatModifier((this.color != null) ? this.color : chatmodifier.color, (this.bold != null) ? this.bold : chatmodifier.bold, (this.italic != null) ? this.italic : chatmodifier.italic, (this.underlined != null) ? this.underlined : chatmodifier.underlined, (this.strikethrough != null) ? this.strikethrough : chatmodifier.strikethrough, (this.obfuscated != null) ? this.obfuscated : chatmodifier.obfuscated, (this.clickEvent != null) ? this.clickEvent : chatmodifier.clickEvent, (this.hoverEvent != null) ? this.hoverEvent : chatmodifier.hoverEvent, (this.insertion != null) ? this.insertion : chatmodifier.insertion, (this.font != null) ? this.font : chatmodifier.font));
/*     */   }
/*     */   
/*     */   public String toString() {
/* 220 */     return "Style{ color=" + this.color + ", bold=" + this.bold + ", italic=" + this.italic + ", underlined=" + this.underlined + ", strikethrough=" + this.strikethrough + ", obfuscated=" + this.obfuscated + ", clickEvent=" + getClickEvent() + ", hoverEvent=" + getHoverEvent() + ", insertion=" + getInsertion() + ", font=" + getFont() + '}';
/*     */   }
/*     */   
/*     */   public boolean equals(Object object) {
/* 224 */     if (this == object)
/* 225 */       return true; 
/* 226 */     if (!(object instanceof ChatModifier)) {
/* 227 */       return false;
/*     */     }
/* 229 */     ChatModifier chatmodifier = (ChatModifier)object;
/*     */     
/* 231 */     return (isBold() == chatmodifier.isBold() && Objects.equals(getColor(), chatmodifier.getColor()) && isItalic() == chatmodifier.isItalic() && isRandom() == chatmodifier.isRandom() && isStrikethrough() == chatmodifier.isStrikethrough() && isUnderlined() == chatmodifier.isUnderlined() && Objects.equals(getClickEvent(), chatmodifier.getClickEvent()) && Objects.equals(getHoverEvent(), chatmodifier.getHoverEvent()) && Objects.equals(getInsertion(), chatmodifier.getInsertion()) && Objects.equals(getFont(), chatmodifier.getFont()));
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 236 */     return Objects.hash(new Object[] { this.color, this.bold, this.italic, this.underlined, this.strikethrough, this.obfuscated, this.clickEvent, this.hoverEvent, this.insertion });
/*     */   }
/*     */ 
/*     */   
/*     */   public static class ChatModifierSerializer
/*     */     implements JsonDeserializer<ChatModifier>, JsonSerializer<ChatModifier>
/*     */   {
/*     */     @Nullable
/*     */     public ChatModifier deserialize(JsonElement jsonelement, Type type, JsonDeserializationContext jsondeserializationcontext) throws JsonParseException {
/* 245 */       if (jsonelement.isJsonObject()) {
/* 246 */         JsonObject jsonobject = jsonelement.getAsJsonObject();
/*     */         
/* 248 */         if (jsonobject == null) {
/* 249 */           return null;
/*     */         }
/* 251 */         Boolean obool = a(jsonobject, "bold");
/* 252 */         Boolean obool1 = a(jsonobject, "italic");
/* 253 */         Boolean obool2 = a(jsonobject, "underlined");
/* 254 */         Boolean obool3 = a(jsonobject, "strikethrough");
/* 255 */         Boolean obool4 = a(jsonobject, "obfuscated");
/* 256 */         ChatHexColor chathexcolor = e(jsonobject);
/* 257 */         String s = d(jsonobject);
/* 258 */         ChatClickable chatclickable = c(jsonobject);
/* 259 */         ChatHoverable chathoverable = b(jsonobject);
/* 260 */         MinecraftKey minecraftkey = a(jsonobject);
/*     */         
/* 262 */         return new ChatModifier(chathexcolor, obool, obool1, obool2, obool3, obool4, chatclickable, chathoverable, s, minecraftkey);
/*     */       } 
/*     */       
/* 265 */       return null;
/*     */     }
/*     */ 
/*     */     
/*     */     @Nullable
/*     */     private static MinecraftKey a(JsonObject jsonobject) {
/* 271 */       if (jsonobject.has("font")) {
/* 272 */         String s = ChatDeserializer.h(jsonobject, "font");
/*     */         
/*     */         try {
/* 275 */           return new MinecraftKey(s);
/* 276 */         } catch (ResourceKeyInvalidException resourcekeyinvalidexception) {
/* 277 */           throw new JsonSyntaxException("Invalid font name: " + s);
/*     */         } 
/*     */       } 
/* 280 */       return null;
/*     */     }
/*     */ 
/*     */     
/*     */     @Nullable
/*     */     private static ChatHoverable b(JsonObject jsonobject) {
/* 286 */       if (jsonobject.has("hoverEvent")) {
/* 287 */         JsonObject jsonobject1 = ChatDeserializer.t(jsonobject, "hoverEvent");
/* 288 */         ChatHoverable chathoverable = ChatHoverable.a(jsonobject1);
/*     */         
/* 290 */         if (chathoverable != null && chathoverable.a().a()) {
/* 291 */           return chathoverable;
/*     */         }
/*     */       } 
/*     */       
/* 295 */       return null;
/*     */     }
/*     */     
/*     */     @Nullable
/*     */     private static ChatClickable c(JsonObject jsonobject) {
/* 300 */       if (jsonobject.has("clickEvent")) {
/* 301 */         JsonObject jsonobject1 = ChatDeserializer.t(jsonobject, "clickEvent");
/* 302 */         String s = ChatDeserializer.a(jsonobject1, "action", (String)null);
/* 303 */         ChatClickable.EnumClickAction chatclickable_enumclickaction = (s == null) ? null : ChatClickable.EnumClickAction.a(s);
/* 304 */         String s1 = ChatDeserializer.a(jsonobject1, "value", (String)null);
/*     */         
/* 306 */         if (chatclickable_enumclickaction != null && s1 != null && chatclickable_enumclickaction.a()) {
/* 307 */           return new ChatClickable(chatclickable_enumclickaction, s1);
/*     */         }
/*     */       } 
/*     */       
/* 311 */       return null;
/*     */     }
/*     */     
/*     */     @Nullable
/*     */     private static String d(JsonObject jsonobject) {
/* 316 */       return ChatDeserializer.a(jsonobject, "insertion", (String)null);
/*     */     }
/*     */     
/*     */     @Nullable
/*     */     private static ChatHexColor e(JsonObject jsonobject) {
/* 321 */       if (jsonobject.has("color")) {
/* 322 */         String s = ChatDeserializer.h(jsonobject, "color");
/*     */         
/* 324 */         return ChatHexColor.a(s);
/*     */       } 
/* 326 */       return null;
/*     */     }
/*     */ 
/*     */     
/*     */     @Nullable
/*     */     private static Boolean a(JsonObject jsonobject, String s) {
/* 332 */       return jsonobject.has(s) ? Boolean.valueOf(jsonobject.get(s).getAsBoolean()) : null;
/*     */     }
/*     */     
/*     */     @Nullable
/*     */     public JsonElement serialize(ChatModifier chatmodifier, Type type, JsonSerializationContext jsonserializationcontext) {
/* 337 */       if (chatmodifier.g()) {
/* 338 */         return null;
/*     */       }
/* 340 */       JsonObject jsonobject = new JsonObject();
/*     */       
/* 342 */       if (chatmodifier.bold != null) {
/* 343 */         jsonobject.addProperty("bold", chatmodifier.bold);
/*     */       }
/*     */       
/* 346 */       if (chatmodifier.italic != null) {
/* 347 */         jsonobject.addProperty("italic", chatmodifier.italic);
/*     */       }
/*     */       
/* 350 */       if (chatmodifier.underlined != null) {
/* 351 */         jsonobject.addProperty("underlined", chatmodifier.underlined);
/*     */       }
/*     */       
/* 354 */       if (chatmodifier.strikethrough != null) {
/* 355 */         jsonobject.addProperty("strikethrough", chatmodifier.strikethrough);
/*     */       }
/*     */       
/* 358 */       if (chatmodifier.obfuscated != null) {
/* 359 */         jsonobject.addProperty("obfuscated", chatmodifier.obfuscated);
/*     */       }
/*     */       
/* 362 */       if (chatmodifier.color != null) {
/* 363 */         jsonobject.addProperty("color", chatmodifier.color.b());
/*     */       }
/*     */       
/* 366 */       if (chatmodifier.insertion != null) {
/* 367 */         jsonobject.add("insertion", jsonserializationcontext.serialize(chatmodifier.insertion));
/*     */       }
/*     */       
/* 370 */       if (chatmodifier.clickEvent != null) {
/* 371 */         JsonObject jsonobject1 = new JsonObject();
/*     */         
/* 373 */         jsonobject1.addProperty("action", chatmodifier.clickEvent.a().b());
/* 374 */         jsonobject1.addProperty("value", chatmodifier.clickEvent.b());
/* 375 */         jsonobject.add("clickEvent", (JsonElement)jsonobject1);
/*     */       } 
/*     */       
/* 378 */       if (chatmodifier.hoverEvent != null) {
/* 379 */         jsonobject.add("hoverEvent", (JsonElement)chatmodifier.hoverEvent.b());
/*     */       }
/*     */       
/* 382 */       if (chatmodifier.font != null) {
/* 383 */         jsonobject.addProperty("font", chatmodifier.font.toString());
/*     */       }
/*     */       
/* 386 */       return (JsonElement)jsonobject;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ChatModifier.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
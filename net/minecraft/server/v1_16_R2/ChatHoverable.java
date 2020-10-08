/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.ImmutableMap;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonObject;
/*     */ import com.google.gson.JsonSyntaxException;
/*     */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*     */ import java.util.Map;
/*     */ import java.util.Objects;
/*     */ import java.util.UUID;
/*     */ import java.util.function.Function;
/*     */ import java.util.stream.Stream;
/*     */ import javax.annotation.Nullable;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
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
/*     */ public class ChatHoverable
/*     */ {
/*  29 */   private static final Logger LOGGER = LogManager.getLogger();
/*     */   
/*     */   private final EnumHoverAction<?> b;
/*     */   private final Object c;
/*     */   
/*     */   public <T> ChatHoverable(EnumHoverAction<T> var0, T var1) {
/*  35 */     this.b = var0;
/*  36 */     this.c = var1;
/*     */   }
/*     */   
/*     */   public EnumHoverAction<?> a() {
/*  40 */     return this.b;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public <T> T a(EnumHoverAction<T> var0) {
/*  45 */     if (this.b == var0) {
/*  46 */       return (T)EnumHoverAction.a(var0, this.c);
/*     */     }
/*  48 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object var0) {
/*  53 */     if (this == var0) {
/*  54 */       return true;
/*     */     }
/*  56 */     if (var0 == null || getClass() != var0.getClass()) {
/*  57 */       return false;
/*     */     }
/*     */     
/*  60 */     ChatHoverable var1 = (ChatHoverable)var0;
/*     */     
/*  62 */     return (this.b == var1.b && Objects.equals(this.c, var1.c));
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/*  67 */     return "HoverEvent{action=" + this.b + ", value='" + this.c + '\'' + '}';
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/*  75 */     int var0 = this.b.hashCode();
/*  76 */     var0 = 31 * var0 + ((this.c != null) ? this.c.hashCode() : 0);
/*  77 */     return var0;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public static ChatHoverable a(JsonObject var0) {
/*  82 */     String var1 = ChatDeserializer.a(var0, "action", (String)null);
/*  83 */     if (var1 == null) {
/*  84 */       return null;
/*     */     }
/*     */     
/*  87 */     EnumHoverAction<?> var2 = EnumHoverAction.a(var1);
/*  88 */     if (var2 == null) {
/*  89 */       return null;
/*     */     }
/*     */     
/*  92 */     JsonElement var3 = var0.get("contents");
/*  93 */     if (var3 != null) {
/*  94 */       return var2.a(var3);
/*     */     }
/*     */     
/*  97 */     IChatBaseComponent var4 = IChatBaseComponent.ChatSerializer.a(var0.get("value"));
/*  98 */     if (var4 != null) {
/*  99 */       return var2.a(var4);
/*     */     }
/*     */     
/* 102 */     return null;
/*     */   }
/*     */   
/*     */   public JsonObject b() {
/* 106 */     JsonObject var0 = new JsonObject();
/* 107 */     var0.addProperty("action", this.b.b());
/* 108 */     var0.add("contents", this.b.a(this.c));
/* 109 */     return var0;
/*     */   }
/*     */ 
/*     */   
/*     */   public static class b
/*     */   {
/*     */     public final EntityTypes<?> a;
/*     */     
/*     */     public final UUID b;
/*     */     @Nullable
/*     */     public final IChatBaseComponent c;
/*     */     
/*     */     public b(EntityTypes<?> var0, UUID var1, @Nullable IChatBaseComponent var2) {
/* 122 */       this.a = var0;
/* 123 */       this.b = var1;
/* 124 */       this.c = var2;
/*     */     }
/*     */     
/*     */     @Nullable
/*     */     public static b a(JsonElement var0) {
/* 129 */       if (!var0.isJsonObject()) {
/* 130 */         return null;
/*     */       }
/* 132 */       JsonObject var1 = var0.getAsJsonObject();
/* 133 */       EntityTypes<?> var2 = IRegistry.ENTITY_TYPE.get(new MinecraftKey(ChatDeserializer.h(var1, "type")));
/* 134 */       UUID var3 = UUID.fromString(ChatDeserializer.h(var1, "id"));
/* 135 */       IChatBaseComponent var4 = IChatBaseComponent.ChatSerializer.a(var1.get("name"));
/* 136 */       return new b(var2, var3, var4);
/*     */     }
/*     */     
/*     */     @Nullable
/*     */     public static b a(IChatBaseComponent var0) {
/*     */       try {
/* 142 */         NBTTagCompound var1 = MojangsonParser.parse(var0.getString());
/* 143 */         IChatBaseComponent var2 = IChatBaseComponent.ChatSerializer.a(var1.getString("name"));
/* 144 */         EntityTypes<?> var3 = IRegistry.ENTITY_TYPE.get(new MinecraftKey(var1.getString("type")));
/* 145 */         UUID var4 = UUID.fromString(var1.getString("id"));
/* 146 */         return new b(var3, var4, var2);
/* 147 */       } catch (JsonSyntaxException|CommandSyntaxException var1) {
/* 148 */         return null;
/*     */       } 
/*     */     }
/*     */     
/*     */     public JsonElement a() {
/* 153 */       JsonObject var0 = new JsonObject();
/* 154 */       var0.addProperty("type", IRegistry.ENTITY_TYPE.getKey(this.a).toString());
/* 155 */       var0.addProperty("id", this.b.toString());
/* 156 */       if (this.c != null) {
/* 157 */         var0.add("name", IChatBaseComponent.ChatSerializer.b(this.c));
/*     */       }
/* 159 */       return (JsonElement)var0;
/*     */     }
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
/*     */     public boolean equals(Object var0) {
/* 176 */       if (this == var0) return true; 
/* 177 */       if (var0 == null || getClass() != var0.getClass()) return false;
/*     */       
/* 179 */       b var1 = (b)var0;
/* 180 */       return (this.a.equals(var1.a) && this.b.equals(var1.b) && Objects.equals(this.c, var1.c));
/*     */     }
/*     */ 
/*     */     
/*     */     public int hashCode() {
/* 185 */       int var0 = this.a.hashCode();
/* 186 */       var0 = 31 * var0 + this.b.hashCode();
/* 187 */       var0 = 31 * var0 + ((this.c != null) ? this.c.hashCode() : 0);
/* 188 */       return var0;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static class c
/*     */   {
/*     */     private final Item a;
/*     */     
/*     */     private final int b;
/*     */     
/*     */     @Nullable
/*     */     private final NBTTagCompound c;
/*     */     
/*     */     c(Item var0, int var1, @Nullable NBTTagCompound var2) {
/* 203 */       this.a = var0;
/* 204 */       this.b = var1;
/* 205 */       this.c = var2;
/*     */     }
/*     */     
/*     */     public c(ItemStack var0) {
/* 209 */       this(var0.getItem(), var0.getCount(), (var0.getTag() != null) ? var0.getTag().clone() : null);
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean equals(Object var0) {
/* 214 */       if (this == var0) return true; 
/* 215 */       if (var0 == null || getClass() != var0.getClass()) return false;
/*     */       
/* 217 */       c var1 = (c)var0;
/* 218 */       return (this.b == var1.b && this.a.equals(var1.a) && Objects.equals(this.c, var1.c));
/*     */     }
/*     */ 
/*     */     
/*     */     public int hashCode() {
/* 223 */       int var0 = this.a.hashCode();
/* 224 */       var0 = 31 * var0 + this.b;
/* 225 */       var0 = 31 * var0 + ((this.c != null) ? this.c.hashCode() : 0);
/* 226 */       return var0;
/*     */     }
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
/*     */     private static c b(JsonElement var0) {
/* 240 */       if (var0.isJsonPrimitive()) {
/* 241 */         return new c(IRegistry.ITEM.get(new MinecraftKey(var0.getAsString())), 1, null);
/*     */       }
/*     */       
/* 244 */       JsonObject var1 = ChatDeserializer.m(var0, "item");
/* 245 */       Item var2 = IRegistry.ITEM.get(new MinecraftKey(ChatDeserializer.h(var1, "id")));
/* 246 */       int var3 = ChatDeserializer.a(var1, "count", 1);
/* 247 */       if (var1.has("tag")) {
/* 248 */         String var4 = ChatDeserializer.h(var1, "tag");
/*     */         try {
/* 250 */           NBTTagCompound var5 = MojangsonParser.parse(var4);
/* 251 */           return new c(var2, var3, var5);
/* 252 */         } catch (CommandSyntaxException var5) {
/* 253 */           ChatHoverable.c().warn("Failed to parse tag: {}", var4, var5);
/*     */         } 
/*     */       } 
/*     */       
/* 257 */       return new c(var2, var3, null);
/*     */     }
/*     */     
/*     */     @Nullable
/*     */     private static c b(IChatBaseComponent var0) {
/*     */       try {
/* 263 */         NBTTagCompound var1 = MojangsonParser.parse(var0.getString());
/* 264 */         return new c(ItemStack.a(var1));
/* 265 */       } catch (CommandSyntaxException var1) {
/* 266 */         ChatHoverable.c().warn("Failed to parse item tag: {}", var0, var1);
/* 267 */         return null;
/*     */       } 
/*     */     }
/*     */     
/*     */     private JsonElement b() {
/* 272 */       JsonObject var0 = new JsonObject();
/* 273 */       var0.addProperty("id", IRegistry.ITEM.getKey(this.a).toString());
/* 274 */       if (this.b != 1) {
/* 275 */         var0.addProperty("count", Integer.valueOf(this.b));
/*     */       }
/* 277 */       if (this.c != null) {
/* 278 */         var0.addProperty("tag", this.c.toString());
/*     */       }
/* 280 */       return (JsonElement)var0;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class EnumHoverAction<T> {
/* 285 */     public static final EnumHoverAction<IChatBaseComponent> SHOW_TEXT = new EnumHoverAction("show_text", true, IChatBaseComponent.ChatSerializer::a, IChatBaseComponent.ChatSerializer::b, 
/*     */ 
/*     */         
/* 288 */         (Function)Function.identity());
/*     */     static {
/* 290 */       SHOW_ITEM = new EnumHoverAction("show_item", true, var0 -> ChatHoverable.c.a(var0), var0 -> ChatHoverable.c.a((ChatHoverable.c)var0), var0 -> ChatHoverable.c.a(var0));
/*     */     }
/*     */ 
/*     */     
/*     */     public static final EnumHoverAction<ChatHoverable.c> SHOW_ITEM;
/* 295 */     public static final EnumHoverAction<ChatHoverable.b> SHOW_ENTITY = new EnumHoverAction("show_entity", true, ChatHoverable.b::a, ChatHoverable.b::a, ChatHoverable.b::a);
/*     */     private static final Map<String, EnumHoverAction> d;
/*     */     private final String e;
/*     */     private final boolean f;
/*     */     
/*     */     static {
/* 301 */       d = (Map<String, EnumHoverAction>)Stream.<EnumHoverAction>of(new EnumHoverAction[] { SHOW_TEXT, SHOW_ITEM, SHOW_ENTITY }).collect(ImmutableMap.toImmutableMap(EnumHoverAction::b, var0 -> var0));
/*     */     }
/*     */ 
/*     */     
/*     */     private final Function<JsonElement, T> g;
/*     */     private final Function<T, JsonElement> h;
/*     */     private final Function<IChatBaseComponent, T> i;
/*     */     
/*     */     public EnumHoverAction(String var0, boolean var1, Function<JsonElement, T> var2, Function<T, JsonElement> var3, Function<IChatBaseComponent, T> var4) {
/* 310 */       this.e = var0;
/* 311 */       this.f = var1;
/* 312 */       this.g = var2;
/* 313 */       this.h = var3;
/* 314 */       this.i = var4;
/*     */     }
/*     */     
/*     */     public boolean a() {
/* 318 */       return this.f;
/*     */     }
/*     */     
/*     */     public String b() {
/* 322 */       return this.e;
/*     */     }
/*     */     
/*     */     @Nullable
/*     */     public static EnumHoverAction a(String var0) {
/* 327 */       return d.get(var0);
/*     */     }
/*     */ 
/*     */     
/*     */     private T b(Object var0) {
/* 332 */       return (T)var0;
/*     */     }
/*     */     
/*     */     @Nullable
/*     */     public ChatHoverable a(JsonElement var0) {
/* 337 */       T var1 = this.g.apply(var0);
/* 338 */       if (var1 == null) {
/* 339 */         return null;
/*     */       }
/* 341 */       return new ChatHoverable(this, var1);
/*     */     }
/*     */     
/*     */     @Nullable
/*     */     public ChatHoverable a(IChatBaseComponent var0) {
/* 346 */       T var1 = this.i.apply(var0);
/* 347 */       if (var1 == null) {
/* 348 */         return null;
/*     */       }
/* 350 */       return new ChatHoverable(this, var1);
/*     */     }
/*     */     
/*     */     public JsonElement a(Object var0) {
/* 354 */       return this.h.apply(b(var0));
/*     */     }
/*     */ 
/*     */     
/*     */     public String toString() {
/* 359 */       return "<action " + this.e + ">";
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ChatHoverable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
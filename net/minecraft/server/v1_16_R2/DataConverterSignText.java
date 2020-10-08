/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.gson.Gson;
/*     */ import com.google.gson.GsonBuilder;
/*     */ import com.google.gson.JsonArray;
/*     */ import com.google.gson.JsonDeserializationContext;
/*     */ import com.google.gson.JsonDeserializer;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonParseException;
/*     */ import com.mojang.datafixers.DSL;
/*     */ import com.mojang.datafixers.Typed;
/*     */ import com.mojang.datafixers.schemas.Schema;
/*     */ import com.mojang.serialization.Dynamic;
/*     */ import java.lang.reflect.Type;
/*     */ import org.bukkit.craftbukkit.libs.org.apache.commons.lang3.StringUtils;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DataConverterSignText
/*     */   extends DataConverterNamedEntity
/*     */ {
/*     */   public DataConverterSignText(Schema var0, boolean var1) {
/*  24 */     super(var0, var1, "BlockEntitySignTextStrictJsonFix", DataConverterTypes.BLOCK_ENTITY, "Sign");
/*     */   }
/*     */   
/*  27 */   public static final Gson a = (new GsonBuilder())
/*  28 */     .registerTypeAdapter(IChatBaseComponent.class, new JsonDeserializer<IChatBaseComponent>()
/*     */       {
/*     */         public IChatMutableComponent deserialize(JsonElement var0, Type var1, JsonDeserializationContext var2) throws JsonParseException {
/*  31 */           if (var0.isJsonPrimitive())
/*     */           {
/*  33 */             return new ChatComponentText(var0.getAsString()); } 
/*  34 */           if (var0.isJsonArray()) {
/*     */             
/*  36 */             JsonArray var3 = var0.getAsJsonArray();
/*  37 */             IChatMutableComponent var4 = null;
/*     */             
/*  39 */             for (JsonElement var6 : var3) {
/*  40 */               IChatMutableComponent var7 = deserialize(var6, var6.getClass(), var2);
/*  41 */               if (var4 == null) {
/*  42 */                 var4 = var7; continue;
/*     */               } 
/*  44 */               var4.addSibling(var7);
/*     */             } 
/*     */ 
/*     */             
/*  48 */             return var4;
/*     */           } 
/*  50 */           throw new JsonParseException("Don't know how to turn " + var0 + " into a Component");
/*     */         }
/*  54 */       }).create();
/*     */   
/*     */   private Dynamic<?> a(Dynamic<?> var0, String var1) {
/*  57 */     String var2 = var0.get(var1).asString("");
/*     */     
/*  59 */     IChatBaseComponent var3 = null;
/*  60 */     if ("null".equals(var2) || StringUtils.isEmpty(var2)) {
/*  61 */       var3 = ChatComponentText.d;
/*  62 */     } else if ((var2
/*  63 */       .charAt(0) == '"' && var2.charAt(var2.length() - 1) == '"') || (var2
/*  64 */       .charAt(0) == '{' && var2.charAt(var2.length() - 1) == '}')) {
/*     */       
/*     */       try {
/*  67 */         var3 = ChatDeserializer.<IChatBaseComponent>a(a, var2, IChatBaseComponent.class, true);
/*  68 */         if (var3 == null) {
/*  69 */           var3 = ChatComponentText.d;
/*     */         }
/*  71 */       } catch (JsonParseException jsonParseException) {}
/*     */ 
/*     */       
/*  74 */       if (var3 == null) {
/*     */         try {
/*  76 */           var3 = IChatBaseComponent.ChatSerializer.a(var2);
/*  77 */         } catch (JsonParseException jsonParseException) {}
/*     */       }
/*     */ 
/*     */       
/*  81 */       if (var3 == null) {
/*     */         try {
/*  83 */           var3 = IChatBaseComponent.ChatSerializer.b(var2);
/*  84 */         } catch (JsonParseException jsonParseException) {}
/*     */       }
/*     */ 
/*     */       
/*  88 */       if (var3 == null) {
/*  89 */         var3 = new ChatComponentText(var2);
/*     */       }
/*     */     } else {
/*  92 */       var3 = new ChatComponentText(var2);
/*     */     } 
/*     */     
/*  95 */     return var0.set(var1, var0.createString(IChatBaseComponent.ChatSerializer.a(var3)));
/*     */   }
/*     */ 
/*     */   
/*     */   protected Typed<?> a(Typed<?> var0) {
/* 100 */     return var0.update(DSL.remainderFinder(), var0 -> {
/*     */           var0 = a(var0, "Text1");
/*     */           var0 = a(var0, "Text2");
/*     */           var0 = a(var0, "Text3");
/*     */           return a(var0, "Text4");
/*     */         });
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataConverterSignText.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
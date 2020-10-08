/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.ImmutableList;
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.gson.JsonArray;
/*     */ import com.google.gson.JsonDeserializationContext;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonObject;
/*     */ import com.google.gson.JsonPrimitive;
/*     */ import com.google.gson.JsonSerializationContext;
/*     */ import com.google.gson.JsonSyntaxException;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import java.util.UUID;
/*     */ import javax.annotation.Nullable;
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
/*     */ public class LootItemFunctionSetAttribute
/*     */   extends LootItemFunctionConditional
/*     */ {
/*     */   private final List<b> a;
/*     */   
/*     */   private LootItemFunctionSetAttribute(LootItemCondition[] var0, List<b> var1) {
/*  35 */     super(var0);
/*  36 */     this.a = (List<b>)ImmutableList.copyOf(var1);
/*     */   }
/*     */ 
/*     */   
/*     */   public LootItemFunctionType b() {
/*  41 */     return LootItemFunctions.i;
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack a(ItemStack var0, LootTableInfo var1) {
/*  46 */     Random var2 = var1.a();
/*  47 */     for (b var4 : this.a) {
/*  48 */       UUID var5 = b.a(var4);
/*  49 */       if (var5 == null) {
/*  50 */         var5 = UUID.randomUUID();
/*     */       }
/*  52 */       EnumItemSlot var6 = SystemUtils.<EnumItemSlot>a(b.b(var4), var2);
/*  53 */       var0.a(b.c(var4), new AttributeModifier(var5, b.d(var4), b.e(var4).b(var2), b.f(var4)), var6);
/*     */     } 
/*  55 */     return var0;
/*     */   }
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
/*     */ 
/*     */   
/*     */   public static class d
/*     */     extends LootItemFunctionConditional.c<LootItemFunctionSetAttribute>
/*     */   {
/*     */     public void a(JsonObject var0, LootItemFunctionSetAttribute var1, JsonSerializationContext var2) {
/* 120 */       super.a(var0, var1, var2);
/*     */       
/* 122 */       JsonArray var3 = new JsonArray();
/* 123 */       for (LootItemFunctionSetAttribute.b var5 : LootItemFunctionSetAttribute.a(var1)) {
/* 124 */         var3.add((JsonElement)var5.a(var2));
/*     */       }
/* 126 */       var0.add("modifiers", (JsonElement)var3);
/*     */     }
/*     */ 
/*     */     
/*     */     public LootItemFunctionSetAttribute b(JsonObject var0, JsonDeserializationContext var1, LootItemCondition[] var2) {
/* 131 */       JsonArray var3 = ChatDeserializer.u(var0, "modifiers");
/* 132 */       List<LootItemFunctionSetAttribute.b> var4 = Lists.newArrayListWithExpectedSize(var3.size());
/*     */       
/* 134 */       for (JsonElement var6 : var3) {
/* 135 */         var4.add(LootItemFunctionSetAttribute.b.a(ChatDeserializer.m(var6, "modifier"), var1));
/*     */       }
/*     */       
/* 138 */       if (var4.isEmpty()) {
/* 139 */         throw new JsonSyntaxException("Invalid attribute modifiers array; cannot be empty");
/*     */       }
/* 141 */       return new LootItemFunctionSetAttribute(var2, var4);
/*     */     }
/*     */   }
/*     */   
/*     */   static class b {
/*     */     private final String a;
/*     */     private final AttributeBase b;
/*     */     private final AttributeModifier.Operation c;
/*     */     private final LootValueBounds d;
/*     */     @Nullable
/*     */     private final UUID e;
/*     */     private final EnumItemSlot[] f;
/*     */     
/*     */     private b(String var0, AttributeBase var1, AttributeModifier.Operation var2, LootValueBounds var3, EnumItemSlot[] var4, @Nullable UUID var5) {
/* 155 */       this.a = var0;
/* 156 */       this.b = var1;
/* 157 */       this.c = var2;
/* 158 */       this.d = var3;
/* 159 */       this.e = var5;
/* 160 */       this.f = var4;
/*     */     }
/*     */     
/*     */     public JsonObject a(JsonSerializationContext var0) {
/* 164 */       JsonObject var1 = new JsonObject();
/* 165 */       var1.addProperty("name", this.a);
/* 166 */       var1.addProperty("attribute", IRegistry.ATTRIBUTE.getKey(this.b).toString());
/* 167 */       var1.addProperty("operation", a(this.c));
/* 168 */       var1.add("amount", var0.serialize(this.d));
/* 169 */       if (this.e != null) {
/* 170 */         var1.addProperty("id", this.e.toString());
/*     */       }
/* 172 */       if (this.f.length == 1) {
/* 173 */         var1.addProperty("slot", this.f[0].getSlotName());
/*     */       } else {
/* 175 */         JsonArray var2 = new JsonArray();
/* 176 */         for (EnumItemSlot var6 : this.f) {
/* 177 */           var2.add((JsonElement)new JsonPrimitive(var6.getSlotName()));
/*     */         }
/* 179 */         var1.add("slot", (JsonElement)var2);
/*     */       } 
/* 181 */       return var1;
/*     */     }
/*     */     public static b a(JsonObject var0, JsonDeserializationContext var1) {
/*     */       EnumItemSlot[] var7;
/* 185 */       String var2 = ChatDeserializer.h(var0, "name");
/* 186 */       MinecraftKey var3 = new MinecraftKey(ChatDeserializer.h(var0, "attribute"));
/* 187 */       AttributeBase var4 = IRegistry.ATTRIBUTE.get(var3);
/* 188 */       if (var4 == null) {
/* 189 */         throw new JsonSyntaxException("Unknown attribute: " + var3);
/*     */       }
/* 191 */       AttributeModifier.Operation var5 = a(ChatDeserializer.h(var0, "operation"));
/* 192 */       LootValueBounds var6 = ChatDeserializer.<LootValueBounds>a(var0, "amount", var1, LootValueBounds.class);
/*     */       
/* 194 */       UUID var8 = null;
/*     */       
/* 196 */       if (ChatDeserializer.a(var0, "slot")) {
/* 197 */         var7 = new EnumItemSlot[] { EnumItemSlot.fromName(ChatDeserializer.h(var0, "slot")) };
/* 198 */       } else if (ChatDeserializer.d(var0, "slot")) {
/* 199 */         JsonArray var9 = ChatDeserializer.u(var0, "slot");
/* 200 */         var7 = new EnumItemSlot[var9.size()];
/* 201 */         int var10 = 0;
/* 202 */         for (JsonElement var12 : var9) {
/* 203 */           var7[var10++] = EnumItemSlot.fromName(ChatDeserializer.a(var12, "slot"));
/*     */         }
/* 205 */         if (var7.length == 0) {
/* 206 */           throw new JsonSyntaxException("Invalid attribute modifier slot; must contain at least one entry.");
/*     */         }
/*     */       } else {
/* 209 */         throw new JsonSyntaxException("Invalid or missing attribute modifier slot; must be either string or array of strings.");
/*     */       } 
/*     */       
/* 212 */       if (var0.has("id")) {
/* 213 */         String var9 = ChatDeserializer.h(var0, "id");
/*     */         try {
/* 215 */           var8 = UUID.fromString(var9);
/* 216 */         } catch (IllegalArgumentException var10) {
/* 217 */           throw new JsonSyntaxException("Invalid attribute modifier id '" + var9 + "' (must be UUID format, with dashes)");
/*     */         } 
/*     */       } 
/*     */       
/* 221 */       return new b(var2, var4, var5, var6, var7, var8);
/*     */     }
/*     */     
/*     */     private static String a(AttributeModifier.Operation var0) {
/* 225 */       switch (LootItemFunctionSetAttribute.null.a[var0.ordinal()]) {
/*     */         case 1:
/* 227 */           return "addition";
/*     */         case 2:
/* 229 */           return "multiply_base";
/*     */         case 3:
/* 231 */           return "multiply_total";
/*     */       } 
/* 233 */       throw new IllegalArgumentException("Unknown operation " + var0);
/*     */     }
/*     */     
/*     */     private static AttributeModifier.Operation a(String var0) {
/* 237 */       switch (var0) {
/*     */         case "addition":
/* 239 */           return AttributeModifier.Operation.ADDITION;
/*     */         case "multiply_base":
/* 241 */           return AttributeModifier.Operation.MULTIPLY_BASE;
/*     */         case "multiply_total":
/* 243 */           return AttributeModifier.Operation.MULTIPLY_TOTAL;
/*     */       } 
/* 245 */       throw new JsonSyntaxException("Unknown attribute modifier operation " + var0);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\LootItemFunctionSetAttribute.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
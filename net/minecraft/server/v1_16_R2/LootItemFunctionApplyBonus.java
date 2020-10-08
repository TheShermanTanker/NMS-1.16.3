/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.ImmutableSet;
/*     */ import com.google.common.collect.Maps;
/*     */ import com.google.gson.JsonDeserializationContext;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonObject;
/*     */ import com.google.gson.JsonParseException;
/*     */ import com.google.gson.JsonSerializationContext;
/*     */ import java.util.Map;
/*     */ import java.util.Random;
/*     */ import java.util.Set;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LootItemFunctionApplyBonus
/*     */   extends LootItemFunctionConditional
/*     */ {
/*     */   static interface b
/*     */   {
/*     */     int a(Random param1Random, int param1Int1, int param1Int2);
/*     */     
/*     */     void a(JsonObject param1JsonObject, JsonSerializationContext param1JsonSerializationContext);
/*     */     
/*     */     MinecraftKey a();
/*     */   }
/*     */   
/*     */   static interface c
/*     */   {
/*     */     LootItemFunctionApplyBonus.b deserialize(JsonObject param1JsonObject, JsonDeserializationContext param1JsonDeserializationContext);
/*     */   }
/*     */   
/*     */   static final class a
/*     */     implements b
/*     */   {
/*  38 */     public static final MinecraftKey a = new MinecraftKey("binomial_with_bonus_count");
/*     */     
/*     */     private final int b;
/*     */     private final float c;
/*     */     
/*     */     public a(int var0, float var1) {
/*  44 */       this.b = var0;
/*  45 */       this.c = var1;
/*     */     }
/*     */ 
/*     */     
/*     */     public int a(Random var0, int var1, int var2) {
/*  50 */       for (int var3 = 0; var3 < var2 + this.b; var3++) {
/*  51 */         if (var0.nextFloat() < this.c) {
/*  52 */           var1++;
/*     */         }
/*     */       } 
/*  55 */       return var1;
/*     */     }
/*     */ 
/*     */     
/*     */     public void a(JsonObject var0, JsonSerializationContext var1) {
/*  60 */       var0.addProperty("extra", Integer.valueOf(this.b));
/*  61 */       var0.addProperty("probability", Float.valueOf(this.c));
/*     */     }
/*     */     
/*     */     public static LootItemFunctionApplyBonus.b a(JsonObject var0, JsonDeserializationContext var1) {
/*  65 */       int var2 = ChatDeserializer.n(var0, "extra");
/*  66 */       float var3 = ChatDeserializer.l(var0, "probability");
/*  67 */       return new a(var2, var3);
/*     */     }
/*     */ 
/*     */     
/*     */     public MinecraftKey a() {
/*  72 */       return a;
/*     */     }
/*     */   }
/*     */   
/*     */   static final class f implements b {
/*  77 */     public static final MinecraftKey a = new MinecraftKey("uniform_bonus_count");
/*     */     
/*     */     private final int b;
/*     */     
/*     */     public f(int var0) {
/*  82 */       this.b = var0;
/*     */     }
/*     */ 
/*     */     
/*     */     public int a(Random var0, int var1, int var2) {
/*  87 */       return var1 + var0.nextInt(this.b * var2 + 1);
/*     */     }
/*     */ 
/*     */     
/*     */     public void a(JsonObject var0, JsonSerializationContext var1) {
/*  92 */       var0.addProperty("bonusMultiplier", Integer.valueOf(this.b));
/*     */     }
/*     */     
/*     */     public static LootItemFunctionApplyBonus.b a(JsonObject var0, JsonDeserializationContext var1) {
/*  96 */       int var2 = ChatDeserializer.n(var0, "bonusMultiplier");
/*  97 */       return new f(var2);
/*     */     }
/*     */ 
/*     */     
/*     */     public MinecraftKey a() {
/* 102 */       return a;
/*     */     }
/*     */   }
/*     */   
/*     */   static final class d implements b {
/* 107 */     public static final MinecraftKey a = new MinecraftKey("ore_drops");
/*     */     private d() {}
/*     */     
/*     */     public int a(Random var0, int var1, int var2) {
/* 111 */       if (var2 > 0) {
/* 112 */         int var3 = var0.nextInt(var2 + 2) - 1;
/* 113 */         if (var3 < 0) {
/* 114 */           var3 = 0;
/*     */         }
/* 116 */         return var1 * (var3 + 1);
/*     */       } 
/*     */       
/* 119 */       return var1;
/*     */     }
/*     */ 
/*     */     
/*     */     public void a(JsonObject var0, JsonSerializationContext var1) {}
/*     */ 
/*     */     
/*     */     public static LootItemFunctionApplyBonus.b a(JsonObject var0, JsonDeserializationContext var1) {
/* 127 */       return new d();
/*     */     }
/*     */ 
/*     */     
/*     */     public MinecraftKey a() {
/* 132 */       return a;
/*     */     }
/*     */   }
/*     */   
/* 136 */   private static final Map<MinecraftKey, c> a = Maps.newHashMap(); private final Enchantment b;
/*     */   
/*     */   static {
/* 139 */     a.put(a.a, a::a);
/* 140 */     a.put(d.a, d::a);
/* 141 */     a.put(f.a, f::a);
/*     */   }
/*     */ 
/*     */   
/*     */   private final b d;
/*     */ 
/*     */   
/*     */   private LootItemFunctionApplyBonus(LootItemCondition[] var0, Enchantment var1, b var2) {
/* 149 */     super(var0);
/* 150 */     this.b = var1;
/* 151 */     this.d = var2;
/*     */   }
/*     */ 
/*     */   
/*     */   public LootItemFunctionType b() {
/* 156 */     return LootItemFunctions.p;
/*     */   }
/*     */ 
/*     */   
/*     */   public Set<LootContextParameter<?>> a() {
/* 161 */     return (Set<LootContextParameter<?>>)ImmutableSet.of(LootContextParameters.TOOL);
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack a(ItemStack var0, LootTableInfo var1) {
/* 166 */     ItemStack var2 = var1.<ItemStack>getContextParameter(LootContextParameters.TOOL);
/*     */     
/* 168 */     if (var2 != null) {
/* 169 */       int var3 = EnchantmentManager.getEnchantmentLevel(this.b, var2);
/* 170 */       int var4 = this.d.a(var1.a(), var0.getCount(), var3);
/* 171 */       var0.setCount(var4);
/*     */     } 
/* 173 */     return var0;
/*     */   }
/*     */   
/*     */   public static LootItemFunctionConditional.a<?> a(Enchantment var0, float var1, int var2) {
/* 177 */     return a(var3 -> new LootItemFunctionApplyBonus(var3, var0, new a(var1, var2)));
/*     */   }
/*     */   
/*     */   public static LootItemFunctionConditional.a<?> a(Enchantment var0) {
/* 181 */     return a(var1 -> new LootItemFunctionApplyBonus(var1, var0, new d()));
/*     */   }
/*     */   
/*     */   public static LootItemFunctionConditional.a<?> b(Enchantment var0) {
/* 185 */     return a(var1 -> new LootItemFunctionApplyBonus(var1, var0, new f(1)));
/*     */   }
/*     */   
/*     */   public static LootItemFunctionConditional.a<?> a(Enchantment var0, int var1) {
/* 189 */     return a(var2 -> new LootItemFunctionApplyBonus(var2, var0, new f(var1)));
/*     */   }
/*     */   
/*     */   public static class e
/*     */     extends LootItemFunctionConditional.c<LootItemFunctionApplyBonus> {
/*     */     public void a(JsonObject var0, LootItemFunctionApplyBonus var1, JsonSerializationContext var2) {
/* 195 */       super.a(var0, var1, var2);
/*     */       
/* 197 */       var0.addProperty("enchantment", IRegistry.ENCHANTMENT.getKey(LootItemFunctionApplyBonus.a(var1)).toString());
/* 198 */       var0.addProperty("formula", LootItemFunctionApplyBonus.b(var1).a().toString());
/*     */       
/* 200 */       JsonObject var3 = new JsonObject();
/* 201 */       LootItemFunctionApplyBonus.b(var1).a(var3, var2);
/* 202 */       if (var3.size() > 0) {
/* 203 */         var0.add("parameters", (JsonElement)var3);
/*     */       }
/*     */     }
/*     */     
/*     */     public LootItemFunctionApplyBonus b(JsonObject var0, JsonDeserializationContext var1, LootItemCondition[] var2) {
/*     */       LootItemFunctionApplyBonus.b var7;
/* 209 */       MinecraftKey var3 = new MinecraftKey(ChatDeserializer.h(var0, "enchantment"));
/* 210 */       Enchantment var4 = (Enchantment)IRegistry.ENCHANTMENT.getOptional(var3).orElseThrow(() -> new JsonParseException("Invalid enchantment id: " + var0));
/* 211 */       MinecraftKey var5 = new MinecraftKey(ChatDeserializer.h(var0, "formula"));
/* 212 */       LootItemFunctionApplyBonus.c var6 = (LootItemFunctionApplyBonus.c)LootItemFunctionApplyBonus.c().get(var5);
/* 213 */       if (var6 == null) {
/* 214 */         throw new JsonParseException("Invalid formula id: " + var5);
/*     */       }
/*     */ 
/*     */       
/* 218 */       if (var0.has("parameters")) {
/* 219 */         var7 = var6.deserialize(ChatDeserializer.t(var0, "parameters"), var1);
/*     */       } else {
/* 221 */         var7 = var6.deserialize(new JsonObject(), var1);
/*     */       } 
/*     */       
/* 224 */       return new LootItemFunctionApplyBonus(var2, var4, var7);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\LootItemFunctionApplyBonus.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
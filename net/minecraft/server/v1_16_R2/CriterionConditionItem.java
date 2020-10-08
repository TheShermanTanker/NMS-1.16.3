/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.gson.JsonArray;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonNull;
/*     */ import com.google.gson.JsonObject;
/*     */ import com.google.gson.JsonParseException;
/*     */ import com.google.gson.JsonSyntaxException;
/*     */ import java.util.List;
/*     */ import java.util.Map;
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
/*     */ 
/*     */ 
/*     */ public class CriterionConditionItem
/*     */ {
/*  30 */   public static final CriterionConditionItem a = new CriterionConditionItem();
/*     */   
/*     */   @Nullable
/*     */   private final Tag<Item> b;
/*     */   @Nullable
/*     */   private final Item c;
/*     */   private final CriterionConditionValue.IntegerRange d;
/*     */   private final CriterionConditionValue.IntegerRange e;
/*     */   private final CriterionConditionEnchantments[] f;
/*     */   private final CriterionConditionEnchantments[] g;
/*     */   @Nullable
/*     */   private final PotionRegistry h;
/*     */   private final CriterionConditionNBT i;
/*     */   
/*     */   public CriterionConditionItem() {
/*  45 */     this.b = null;
/*  46 */     this.c = null;
/*  47 */     this.h = null;
/*  48 */     this.d = CriterionConditionValue.IntegerRange.e;
/*  49 */     this.e = CriterionConditionValue.IntegerRange.e;
/*  50 */     this.f = CriterionConditionEnchantments.b;
/*  51 */     this.g = CriterionConditionEnchantments.b;
/*  52 */     this.i = CriterionConditionNBT.a;
/*     */   }
/*     */   
/*     */   public CriterionConditionItem(@Nullable Tag<Item> var0, @Nullable Item var1, CriterionConditionValue.IntegerRange var2, CriterionConditionValue.IntegerRange var3, CriterionConditionEnchantments[] var4, CriterionConditionEnchantments[] var5, @Nullable PotionRegistry var6, CriterionConditionNBT var7) {
/*  56 */     this.b = var0;
/*  57 */     this.c = var1;
/*  58 */     this.d = var2;
/*  59 */     this.e = var3;
/*  60 */     this.f = var4;
/*  61 */     this.g = var5;
/*  62 */     this.h = var6;
/*  63 */     this.i = var7;
/*     */   }
/*     */   
/*     */   public boolean a(ItemStack var0) {
/*  67 */     if (this == a) {
/*  68 */       return true;
/*     */     }
/*     */     
/*  71 */     if (this.b != null && !this.b.isTagged(var0.getItem())) {
/*  72 */       return false;
/*     */     }
/*  74 */     if (this.c != null && var0.getItem() != this.c) {
/*  75 */       return false;
/*     */     }
/*  77 */     if (!this.d.d(var0.getCount())) {
/*  78 */       return false;
/*     */     }
/*  80 */     if (!this.e.c() && !var0.e()) {
/*  81 */       return false;
/*     */     }
/*  83 */     if (!this.e.d(var0.h() - var0.getDamage())) {
/*  84 */       return false;
/*     */     }
/*  86 */     if (!this.i.a(var0)) {
/*  87 */       return false;
/*     */     }
/*  89 */     if (this.f.length > 0) {
/*  90 */       Map<Enchantment, Integer> map = EnchantmentManager.a(var0.getEnchantments());
/*  91 */       for (CriterionConditionEnchantments var5 : this.f) {
/*  92 */         if (!var5.a(map)) {
/*  93 */           return false;
/*     */         }
/*     */       } 
/*     */     } 
/*  97 */     if (this.g.length > 0) {
/*  98 */       Map<Enchantment, Integer> map = EnchantmentManager.a(ItemEnchantedBook.d(var0));
/*  99 */       for (CriterionConditionEnchantments var5 : this.g) {
/* 100 */         if (!var5.a(map)) {
/* 101 */           return false;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 106 */     PotionRegistry var1 = PotionUtil.d(var0);
/* 107 */     if (this.h != null && this.h != var1) {
/* 108 */       return false;
/*     */     }
/* 110 */     return true;
/*     */   }
/*     */   
/*     */   public static CriterionConditionItem a(@Nullable JsonElement var0) {
/* 114 */     if (var0 == null || var0.isJsonNull()) {
/* 115 */       return a;
/*     */     }
/* 117 */     JsonObject var1 = ChatDeserializer.m(var0, "item");
/* 118 */     CriterionConditionValue.IntegerRange var2 = CriterionConditionValue.IntegerRange.a(var1.get("count"));
/* 119 */     CriterionConditionValue.IntegerRange var3 = CriterionConditionValue.IntegerRange.a(var1.get("durability"));
/* 120 */     if (var1.has("data")) {
/* 121 */       throw new JsonParseException("Disallowed data tag found");
/*     */     }
/* 123 */     CriterionConditionNBT var4 = CriterionConditionNBT.a(var1.get("nbt"));
/* 124 */     Item var5 = null;
/* 125 */     if (var1.has("item")) {
/* 126 */       MinecraftKey minecraftKey = new MinecraftKey(ChatDeserializer.h(var1, "item"));
/* 127 */       var5 = (Item)IRegistry.ITEM.getOptional(minecraftKey).orElseThrow(() -> new JsonSyntaxException("Unknown item id '" + var0 + "'"));
/*     */     } 
/* 129 */     Tag<Item> var6 = null;
/* 130 */     if (var1.has("tag")) {
/* 131 */       MinecraftKey minecraftKey = new MinecraftKey(ChatDeserializer.h(var1, "tag"));
/* 132 */       var6 = TagsInstance.a().getItemTags().a(minecraftKey);
/* 133 */       if (var6 == null) {
/* 134 */         throw new JsonSyntaxException("Unknown item tag '" + minecraftKey + "'");
/*     */       }
/*     */     } 
/* 137 */     PotionRegistry var7 = null;
/* 138 */     if (var1.has("potion")) {
/* 139 */       MinecraftKey minecraftKey = new MinecraftKey(ChatDeserializer.h(var1, "potion"));
/* 140 */       var7 = (PotionRegistry)IRegistry.POTION.getOptional(minecraftKey).orElseThrow(() -> new JsonSyntaxException("Unknown potion '" + var0 + "'"));
/*     */     } 
/*     */     
/* 143 */     CriterionConditionEnchantments[] var8 = CriterionConditionEnchantments.b(var1.get("enchantments"));
/* 144 */     CriterionConditionEnchantments[] var9 = CriterionConditionEnchantments.b(var1.get("stored_enchantments"));
/* 145 */     return new CriterionConditionItem(var6, var5, var2, var3, var8, var9, var7, var4);
/*     */   }
/*     */   
/*     */   public JsonElement a() {
/* 149 */     if (this == a) {
/* 150 */       return (JsonElement)JsonNull.INSTANCE;
/*     */     }
/*     */     
/* 153 */     JsonObject var0 = new JsonObject();
/*     */     
/* 155 */     if (this.c != null) {
/* 156 */       var0.addProperty("item", IRegistry.ITEM.getKey(this.c).toString());
/*     */     }
/*     */     
/* 159 */     if (this.b != null) {
/* 160 */       var0.addProperty("tag", TagsInstance.a().getItemTags().b(this.b).toString());
/*     */     }
/*     */     
/* 163 */     var0.add("count", this.d.d());
/* 164 */     var0.add("durability", this.e.d());
/* 165 */     var0.add("nbt", this.i.a());
/*     */     
/* 167 */     if (this.f.length > 0) {
/* 168 */       JsonArray var1 = new JsonArray();
/* 169 */       for (CriterionConditionEnchantments var5 : this.f) {
/* 170 */         var1.add(var5.a());
/*     */       }
/* 172 */       var0.add("enchantments", (JsonElement)var1);
/*     */     } 
/*     */     
/* 175 */     if (this.g.length > 0) {
/* 176 */       JsonArray var1 = new JsonArray();
/* 177 */       for (CriterionConditionEnchantments var5 : this.g) {
/* 178 */         var1.add(var5.a());
/*     */       }
/* 180 */       var0.add("stored_enchantments", (JsonElement)var1);
/*     */     } 
/*     */     
/* 183 */     if (this.h != null) {
/* 184 */       var0.addProperty("potion", IRegistry.POTION.getKey(this.h).toString());
/*     */     }
/*     */     
/* 187 */     return (JsonElement)var0;
/*     */   }
/*     */   
/*     */   public static CriterionConditionItem[] b(@Nullable JsonElement var0) {
/* 191 */     if (var0 == null || var0.isJsonNull()) {
/* 192 */       return new CriterionConditionItem[0];
/*     */     }
/*     */     
/* 195 */     JsonArray var1 = ChatDeserializer.n(var0, "items");
/* 196 */     CriterionConditionItem[] var2 = new CriterionConditionItem[var1.size()];
/*     */     
/* 198 */     for (int var3 = 0; var3 < var2.length; var3++) {
/* 199 */       var2[var3] = a(var1.get(var3));
/*     */     }
/*     */     
/* 202 */     return var2;
/*     */   }
/*     */   
/*     */   public static class a {
/* 206 */     private final List<CriterionConditionEnchantments> a = Lists.newArrayList();
/* 207 */     private final List<CriterionConditionEnchantments> b = Lists.newArrayList();
/*     */     @Nullable
/*     */     private Item c;
/*     */     @Nullable
/*     */     private Tag<Item> d;
/* 212 */     private CriterionConditionValue.IntegerRange e = CriterionConditionValue.IntegerRange.e;
/* 213 */     private CriterionConditionValue.IntegerRange f = CriterionConditionValue.IntegerRange.e;
/*     */     @Nullable
/*     */     private PotionRegistry g;
/* 216 */     private CriterionConditionNBT h = CriterionConditionNBT.a;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public static a a() {
/* 222 */       return new a();
/*     */     }
/*     */     
/*     */     public a a(IMaterial var0) {
/* 226 */       this.c = var0.getItem();
/* 227 */       return this;
/*     */     }
/*     */     
/*     */     public a a(Tag<Item> var0) {
/* 231 */       this.d = var0;
/* 232 */       return this;
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
/*     */ 
/*     */     
/*     */     public a a(NBTTagCompound var0) {
/* 251 */       this.h = new CriterionConditionNBT(var0);
/* 252 */       return this;
/*     */     }
/*     */     
/*     */     public a a(CriterionConditionEnchantments var0) {
/* 256 */       this.a.add(var0);
/* 257 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public CriterionConditionItem b() {
/* 266 */       return new CriterionConditionItem(this.d, this.c, this.e, this.f, this.a.<CriterionConditionEnchantments>toArray(CriterionConditionEnchantments.b), this.b.<CriterionConditionEnchantments>toArray(CriterionConditionEnchantments.b), this.g, this.h);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CriterionConditionItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
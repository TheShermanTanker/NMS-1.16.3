/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.gson.JsonDeserializationContext;
/*     */ import com.google.gson.JsonDeserializer;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonObject;
/*     */ import com.google.gson.JsonParseException;
/*     */ import com.google.gson.JsonSerializationContext;
/*     */ import com.google.gson.JsonSerializer;
/*     */ import java.lang.reflect.Type;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import java.util.function.BiFunction;
/*     */ import java.util.function.Consumer;
/*     */ import java.util.function.Predicate;
/*     */ import org.bukkit.craftbukkit.libs.org.apache.commons.lang3.ArrayUtils;
/*     */ import org.bukkit.craftbukkit.libs.org.apache.commons.lang3.mutable.MutableInt;
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
/*     */ public class LootSelector
/*     */ {
/*     */   private final LootEntryAbstract[] a;
/*     */   private final LootItemCondition[] b;
/*     */   private final Predicate<LootTableInfo> c;
/*     */   private final LootItemFunction[] d;
/*     */   private final BiFunction<ItemStack, LootTableInfo, ItemStack> e;
/*     */   private final LootValue f;
/*     */   private final LootValueBounds g;
/*     */   
/*     */   private LootSelector(LootEntryAbstract[] var0, LootItemCondition[] var1, LootItemFunction[] var2, LootValue var3, LootValueBounds var4) {
/*  42 */     this.a = var0;
/*  43 */     this.b = var1;
/*  44 */     this.c = LootItemConditions.a((Predicate<LootTableInfo>[])var1);
/*  45 */     this.d = var2;
/*  46 */     this.e = LootItemFunctions.a((BiFunction<ItemStack, LootTableInfo, ItemStack>[])var2);
/*  47 */     this.f = var3;
/*  48 */     this.g = var4;
/*     */   }
/*     */   
/*     */   private void b(Consumer<ItemStack> var0, LootTableInfo var1) {
/*  52 */     Random var2 = var1.a();
/*  53 */     List<LootEntry> var3 = Lists.newArrayList();
/*  54 */     MutableInt var4 = new MutableInt();
/*  55 */     for (LootEntryAbstract var8 : this.a) {
/*  56 */       var8.expand(var1, var3 -> {
/*     */             int var4 = var3.a(var0.getLuck());
/*     */             
/*     */             if (var4 > 0) {
/*     */               var1.add(var3);
/*     */               var2.add(var4);
/*     */             } 
/*     */           });
/*     */     } 
/*  65 */     int var5 = var3.size();
/*  66 */     if (var4.intValue() == 0 || var5 == 0) {
/*     */       return;
/*     */     }
/*     */     
/*  70 */     if (var5 == 1) {
/*  71 */       ((LootEntry)var3.get(0)).a(var0, var1);
/*     */       
/*     */       return;
/*     */     } 
/*  75 */     int var6 = var2.nextInt(var4.intValue());
/*  76 */     for (LootEntry var8 : var3) {
/*  77 */       var6 -= var8.a(var1.getLuck());
/*  78 */       if (var6 < 0) {
/*  79 */         var8.a(var0, var1);
/*     */         return;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void a(Consumer<ItemStack> var0, LootTableInfo var1) {
/*  86 */     if (!this.c.test(var1)) {
/*     */       return;
/*     */     }
/*     */     
/*  90 */     Consumer<ItemStack> var2 = LootItemFunction.a(this.e, var0, var1);
/*     */     
/*  92 */     Random var3 = var1.a();
/*  93 */     int var4 = this.f.a(var3) + MathHelper.d(this.g.b(var3) * var1.getLuck());
/*  94 */     for (int var5 = 0; var5 < var4; var5++)
/*  95 */       b(var2, var1); 
/*     */   }
/*     */   
/*     */   public void a(LootCollector var0) {
/*     */     int var1;
/* 100 */     for (var1 = 0; var1 < this.b.length; var1++) {
/* 101 */       this.b[var1].a(var0.b(".condition[" + var1 + "]"));
/*     */     }
/*     */     
/* 104 */     for (var1 = 0; var1 < this.d.length; var1++) {
/* 105 */       this.d[var1].a(var0.b(".functions[" + var1 + "]"));
/*     */     }
/*     */     
/* 108 */     for (var1 = 0; var1 < this.a.length; var1++)
/* 109 */       this.a[var1].a(var0.b(".entries[" + var1 + "]")); 
/*     */   }
/*     */   
/*     */   public static class a
/*     */     implements LootItemFunctionUser<a>, LootItemConditionUser<a> {
/* 114 */     private final List<LootEntryAbstract> a = Lists.newArrayList();
/* 115 */     private final List<LootItemCondition> b = Lists.newArrayList();
/* 116 */     private final List<LootItemFunction> c = Lists.newArrayList();
/* 117 */     private LootValue d = new LootValueBounds(1.0F);
/* 118 */     private LootValueBounds e = new LootValueBounds(0.0F, 0.0F);
/*     */     
/*     */     public a a(LootValue var0) {
/* 121 */       this.d = var0;
/* 122 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public a c() {
/* 127 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public a a(LootEntryAbstract.a<?> var0) {
/* 136 */       this.a.add(var0.b());
/* 137 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public a b(LootItemCondition.a var0) {
/* 142 */       this.b.add(var0.build());
/* 143 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public a b(LootItemFunction.a var0) {
/* 148 */       this.c.add(var0.b());
/* 149 */       return this;
/*     */     }
/*     */     
/*     */     public LootSelector b() {
/* 153 */       if (this.d == null) {
/* 154 */         throw new IllegalArgumentException("Rolls not set");
/*     */       }
/*     */       
/* 157 */       return new LootSelector(this.a.<LootEntryAbstract>toArray(new LootEntryAbstract[0]), this.b.<LootItemCondition>toArray(new LootItemCondition[0]), this.c.<LootItemFunction>toArray(new LootItemFunction[0]), this.d, this.e);
/*     */     }
/*     */   }
/*     */   
/*     */   public static a a() {
/* 162 */     return new a();
/*     */   }
/*     */   
/*     */   public static class b
/*     */     implements JsonDeserializer<LootSelector>, JsonSerializer<LootSelector> {
/*     */     public LootSelector deserialize(JsonElement var0, Type var1, JsonDeserializationContext var2) throws JsonParseException {
/* 168 */       JsonObject var3 = ChatDeserializer.m(var0, "loot pool");
/* 169 */       LootEntryAbstract[] var4 = ChatDeserializer.<LootEntryAbstract[]>a(var3, "entries", var2, (Class)LootEntryAbstract[].class);
/* 170 */       LootItemCondition[] var5 = ChatDeserializer.<LootItemCondition[]>a(var3, "conditions", new LootItemCondition[0], var2, (Class)LootItemCondition[].class);
/* 171 */       LootItemFunction[] var6 = ChatDeserializer.<LootItemFunction[]>a(var3, "functions", new LootItemFunction[0], var2, (Class)LootItemFunction[].class);
/* 172 */       LootValue var7 = LootValueGenerators.a(var3.get("rolls"), var2);
/* 173 */       LootValueBounds var8 = ChatDeserializer.<LootValueBounds>a(var3, "bonus_rolls", new LootValueBounds(0.0F, 0.0F), var2, LootValueBounds.class);
/* 174 */       return new LootSelector(var4, var5, var6, var7, var8);
/*     */     }
/*     */ 
/*     */     
/*     */     public JsonElement serialize(LootSelector var0, Type var1, JsonSerializationContext var2) {
/* 179 */       JsonObject var3 = new JsonObject();
/* 180 */       var3.add("rolls", LootValueGenerators.a(LootSelector.a(var0), var2));
/* 181 */       var3.add("entries", var2.serialize(LootSelector.b(var0)));
/* 182 */       if (LootSelector.c(var0).b() != 0.0F && LootSelector.c(var0).c() != 0.0F) {
/* 183 */         var3.add("bonus_rolls", var2.serialize(LootSelector.c(var0)));
/*     */       }
/* 185 */       if (!ArrayUtils.isEmpty((Object[])LootSelector.d(var0))) {
/* 186 */         var3.add("conditions", var2.serialize(LootSelector.d(var0)));
/*     */       }
/* 188 */       if (!ArrayUtils.isEmpty((Object[])LootSelector.e(var0))) {
/* 189 */         var3.add("functions", var2.serialize(LootSelector.e(var0)));
/*     */       }
/* 191 */       return (JsonElement)var3;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\LootSelector.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
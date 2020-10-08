/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.ImmutableList;
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Sets;
/*     */ import com.google.gson.JsonArray;
/*     */ import com.google.gson.JsonDeserializationContext;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonObject;
/*     */ import com.google.gson.JsonPrimitive;
/*     */ import com.google.gson.JsonSerializationContext;
/*     */ import com.google.gson.JsonSyntaxException;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import java.util.Set;
/*     */ import java.util.stream.Collectors;
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
/*     */ public class LootItemFunctionEnchant
/*     */   extends LootItemFunctionConditional
/*     */ {
/*  34 */   private static final Logger LOGGER = LogManager.getLogger();
/*     */   
/*     */   private final List<Enchantment> b;
/*     */   
/*     */   private LootItemFunctionEnchant(LootItemCondition[] var0, Collection<Enchantment> var1) {
/*  39 */     super(var0);
/*  40 */     this.b = (List<Enchantment>)ImmutableList.copyOf(var1);
/*     */   }
/*     */ 
/*     */   
/*     */   public LootItemFunctionType b() {
/*  45 */     return LootItemFunctions.d;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack a(ItemStack var0, LootTableInfo var1) {
/*     */     Enchantment var2;
/*  52 */     Random var3 = var1.a();
/*  53 */     if (this.b.isEmpty()) {
/*  54 */       boolean var4 = (var0.getItem() == Items.BOOK);
/*     */ 
/*     */ 
/*     */       
/*  58 */       List<Enchantment> var5 = (List<Enchantment>)IRegistry.ENCHANTMENT.g().filter(Enchantment::i).filter(var2 -> (var0 || var2.canEnchant(var1))).collect(Collectors.toList());
/*     */       
/*  60 */       if (var5.isEmpty()) {
/*  61 */         LOGGER.warn("Couldn't find a compatible enchantment for {}", var0);
/*  62 */         return var0;
/*     */       } 
/*  64 */       var2 = var5.get(var3.nextInt(var5.size()));
/*     */     } else {
/*  66 */       var2 = this.b.get(var3.nextInt(this.b.size()));
/*     */     } 
/*     */     
/*  69 */     return a(var0, var2, var3);
/*     */   }
/*     */   
/*     */   private static ItemStack a(ItemStack var0, Enchantment var1, Random var2) {
/*  73 */     int var3 = MathHelper.nextInt(var2, var1.getStartLevel(), var1.getMaxLevel());
/*     */     
/*  75 */     if (var0.getItem() == Items.BOOK) {
/*  76 */       var0 = new ItemStack(Items.ENCHANTED_BOOK);
/*  77 */       ItemEnchantedBook.a(var0, new WeightedRandomEnchant(var1, var3));
/*     */     } else {
/*  79 */       var0.addEnchantment(var1, var3);
/*     */     } 
/*  81 */     return var0;
/*     */   }
/*     */   
/*     */   public static class a extends LootItemFunctionConditional.a<a> {
/*  85 */     private final Set<Enchantment> a = Sets.newHashSet();
/*     */ 
/*     */     
/*     */     protected a d() {
/*  89 */       return this;
/*     */     }
/*     */     
/*     */     public a a(Enchantment var0) {
/*  93 */       this.a.add(var0);
/*  94 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public LootItemFunction b() {
/*  99 */       return new LootItemFunctionEnchant(g(), this.a);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static LootItemFunctionConditional.a<?> d() {
/* 108 */     return a(var0 -> new LootItemFunctionEnchant(var0, (Collection<Enchantment>)ImmutableList.of()));
/*     */   }
/*     */   
/*     */   public static class b
/*     */     extends LootItemFunctionConditional.c<LootItemFunctionEnchant> {
/*     */     public void a(JsonObject var0, LootItemFunctionEnchant var1, JsonSerializationContext var2) {
/* 114 */       super.a(var0, var1, var2);
/*     */       
/* 116 */       if (!LootItemFunctionEnchant.a(var1).isEmpty()) {
/* 117 */         JsonArray var3 = new JsonArray();
/* 118 */         for (Enchantment var5 : LootItemFunctionEnchant.a(var1)) {
/* 119 */           MinecraftKey var6 = IRegistry.ENCHANTMENT.getKey(var5);
/* 120 */           if (var6 == null) {
/* 121 */             throw new IllegalArgumentException("Don't know how to serialize enchantment " + var5);
/*     */           }
/* 123 */           var3.add((JsonElement)new JsonPrimitive(var6.toString()));
/*     */         } 
/* 125 */         var0.add("enchantments", (JsonElement)var3);
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public LootItemFunctionEnchant b(JsonObject var0, JsonDeserializationContext var1, LootItemCondition[] var2) {
/* 131 */       List<Enchantment> var3 = Lists.newArrayList();
/* 132 */       if (var0.has("enchantments")) {
/* 133 */         JsonArray var4 = ChatDeserializer.u(var0, "enchantments");
/* 134 */         for (JsonElement var6 : var4) {
/* 135 */           String var7 = ChatDeserializer.a(var6, "enchantment");
/*     */           
/* 137 */           Enchantment var8 = (Enchantment)IRegistry.ENCHANTMENT.getOptional(new MinecraftKey(var7)).orElseThrow(() -> new JsonSyntaxException("Unknown enchantment '" + var0 + "'"));
/* 138 */           var3.add(var8);
/*     */         } 
/*     */       } 
/* 141 */       return new LootItemFunctionEnchant(var2, var3);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\LootItemFunctionEnchant.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
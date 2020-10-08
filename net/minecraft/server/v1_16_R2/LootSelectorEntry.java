/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.destroystokyo.paper.PaperConfig;
/*     */ import com.google.gson.JsonDeserializationContext;
/*     */ import com.google.gson.JsonObject;
/*     */ import com.google.gson.JsonSerializationContext;
/*     */ import java.util.function.BiFunction;
/*     */ import java.util.function.Consumer;
/*     */ 
/*     */ public abstract class LootSelectorEntry extends LootEntryAbstract {
/*     */   protected final int c;
/*     */   protected final int e;
/*     */   
/*  14 */   public int getWeight() { return this.c; } protected final LootItemFunction[] f; private final BiFunction<ItemStack, LootTableInfo, ItemStack> g; public int getQuality() {
/*  15 */     return this.e;
/*     */   }
/*     */   
/*  18 */   private final LootEntry h = new c()
/*     */     {
/*     */       public void a(Consumer<ItemStack> consumer, LootTableInfo loottableinfo) {
/*  21 */         LootSelectorEntry.this.a(LootItemFunction.a(LootSelectorEntry.this.g, consumer, loottableinfo), loottableinfo);
/*     */       }
/*     */     };
/*     */   private Float lastLuck; private int lastWeight;
/*     */   protected LootSelectorEntry(int i, int j, LootItemCondition[] alootitemcondition, LootItemFunction[] alootitemfunction) {
/*  26 */     super(alootitemcondition);
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
/* 177 */     this.lastLuck = null;
/* 178 */     this.lastWeight = 0;
/*     */     this.c = i;
/*     */     this.e = j;
/*     */     this.f = alootitemfunction;
/*     */     this.g = LootItemFunctions.a((BiFunction<ItemStack, LootTableInfo, ItemStack>[])alootitemfunction);
/*     */   }
/*     */   
/*     */   public void a(LootCollector lootcollector) {
/*     */     super.a(lootcollector);
/*     */     for (int i = 0; i < this.f.length; i++)
/*     */       this.f[i].a(lootcollector.b(".functions[" + i + "]")); 
/*     */   }
/*     */   
/*     */   public boolean expand(LootTableInfo loottableinfo, Consumer<LootEntry> consumer) {
/*     */     if (a(loottableinfo)) {
/*     */       consumer.accept(this.h);
/*     */       return true;
/*     */     } 
/*     */     return false;
/*     */   }
/*     */   
/*     */   public static a<?> a(d lootselectorentry_d) {
/*     */     return new b(lootselectorentry_d);
/*     */   }
/*     */   
/*     */   protected abstract void a(Consumer<ItemStack> paramConsumer, LootTableInfo paramLootTableInfo);
/*     */   
/*     */   public static abstract class e<T extends LootSelectorEntry> extends LootEntryAbstract.Serializer<T> {
/*     */     public void a(JsonObject jsonobject, T t0, JsonSerializationContext jsonserializationcontext) {
/*     */       if (((LootSelectorEntry)t0).c != 1)
/*     */         jsonobject.addProperty("weight", Integer.valueOf(((LootSelectorEntry)t0).c)); 
/*     */       if (((LootSelectorEntry)t0).e != 0)
/*     */         jsonobject.addProperty("quality", Integer.valueOf(((LootSelectorEntry)t0).e)); 
/*     */       if (!ArrayUtils.isEmpty((Object[])((LootSelectorEntry)t0).f))
/*     */         jsonobject.add("functions", jsonserializationcontext.serialize(((LootSelectorEntry)t0).f)); 
/*     */     }
/*     */     
/*     */     public final T deserializeType(JsonObject jsonobject, JsonDeserializationContext jsondeserializationcontext, LootItemCondition[] alootitemcondition) {
/*     */       int i = ChatDeserializer.a(jsonobject, "weight", 1);
/*     */       int j = ChatDeserializer.a(jsonobject, "quality", 0);
/*     */       LootItemFunction[] alootitemfunction = ChatDeserializer.<LootItemFunction[]>a(jsonobject, "functions", new LootItemFunction[0], jsondeserializationcontext, (Class)LootItemFunction[].class);
/*     */       return b(jsonobject, jsondeserializationcontext, i, j, alootitemcondition, alootitemfunction);
/*     */     }
/*     */     
/*     */     protected abstract T b(JsonObject param1JsonObject, JsonDeserializationContext param1JsonDeserializationContext, int param1Int1, int param1Int2, LootItemCondition[] param1ArrayOfLootItemCondition, LootItemFunction[] param1ArrayOfLootItemFunction);
/*     */   }
/*     */   
/*     */   static class b extends a<b> {
/*     */     private final LootSelectorEntry.d c;
/*     */     
/*     */     public b(LootSelectorEntry.d lootselectorentry_d) {
/*     */       this.c = lootselectorentry_d;
/*     */     }
/*     */     
/*     */     protected b d() {
/*     */       return this;
/*     */     }
/*     */     
/*     */     public LootEntryAbstract b() {
/*     */       return this.c.build(this.a, this.b, f(), a());
/*     */     }
/*     */   }
/*     */   
/*     */   public static abstract class a<T extends a<T>> extends LootEntryAbstract.a<T> implements LootItemFunctionUser<T> {
/*     */     protected int a = 1;
/*     */     protected int b = 0;
/*     */     private final List<LootItemFunction> c = Lists.newArrayList();
/*     */     
/*     */     public T b(LootItemFunction.a lootitemfunction_a) {
/*     */       this.c.add(lootitemfunction_a.b());
/*     */       return d();
/*     */     }
/*     */     
/*     */     protected LootItemFunction[] a() {
/*     */       return this.c.<LootItemFunction>toArray(new LootItemFunction[0]);
/*     */     }
/*     */     
/*     */     public T a(int i) {
/*     */       this.a = i;
/*     */       return d();
/*     */     }
/*     */     
/*     */     public T b(int i) {
/*     */       this.b = i;
/*     */       return d();
/*     */     }
/*     */   }
/*     */   
/*     */   public abstract class c implements LootEntry {
/*     */     public int a(float f) {
/*     */       if (LootSelectorEntry.this.lastLuck != null && LootSelectorEntry.this.lastLuck.floatValue() == f)
/*     */         return LootSelectorEntry.this.lastWeight; 
/*     */       float qualityModifer = LootSelectorEntry.this.getQuality() * f;
/*     */       double baseWeight = (LootSelectorEntry.this.getWeight() + qualityModifer);
/*     */       if (PaperConfig.useAlternativeLuckFormula) {
/*     */         int weightBoost = 100;
/*     */         baseWeight *= 100.0D;
/*     */         double impacted = baseWeight * (baseWeight - 100.0D) / 100.0D / 100.0D;
/*     */         float luckModifier = Math.min(100.0F, f * 10.0F) / 100.0F;
/*     */         baseWeight = Math.ceil(baseWeight - impacted * luckModifier);
/*     */       } 
/*     */       LootSelectorEntry.this.lastLuck = Float.valueOf(f);
/*     */       LootSelectorEntry.this.lastWeight = (int)Math.max(0.0D, Math.floor(baseWeight));
/*     */       return LootSelectorEntry.this.lastWeight;
/*     */     }
/*     */   }
/*     */   
/*     */   @FunctionalInterface
/*     */   public static interface d {
/*     */     LootSelectorEntry build(int param1Int1, int param1Int2, LootItemCondition[] param1ArrayOfLootItemCondition, LootItemFunction[] param1ArrayOfLootItemFunction);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\LootSelectorEntry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
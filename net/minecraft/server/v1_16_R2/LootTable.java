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
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Objects;
/*     */ import java.util.Random;
/*     */ import java.util.function.BiFunction;
/*     */ import java.util.function.Consumer;
/*     */ import java.util.stream.Collectors;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import org.bukkit.craftbukkit.libs.org.apache.commons.lang3.ArrayUtils;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.event.CraftEventFactory;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftItemStack;
/*     */ import org.bukkit.event.world.LootGenerateEvent;
/*     */ 
/*     */ 
/*     */ public class LootTable
/*     */ {
/*  30 */   private static final Logger LOGGER = LogManager.getLogger();
/*  31 */   public static final LootTable EMPTY = new LootTable(LootContextParameterSets.EMPTY, new LootSelector[0], new LootItemFunction[0]);
/*  32 */   public static final LootContextParameterSet b = LootContextParameterSets.GENERIC;
/*     */   private final LootContextParameterSet d;
/*     */   private final LootSelector[] e;
/*     */   private final LootItemFunction[] f;
/*     */   private final BiFunction<ItemStack, LootTableInfo, ItemStack> g;
/*     */   
/*     */   private LootTable(LootContextParameterSet lootcontextparameterset, LootSelector[] alootselector, LootItemFunction[] alootitemfunction) {
/*  39 */     this.d = lootcontextparameterset;
/*  40 */     this.e = alootselector;
/*  41 */     this.f = alootitemfunction;
/*  42 */     this.g = LootItemFunctions.a((BiFunction<ItemStack, LootTableInfo, ItemStack>[])alootitemfunction);
/*     */   }
/*     */   
/*     */   public static Consumer<ItemStack> a(Consumer<ItemStack> consumer) {
/*  46 */     return itemstack -> {
/*     */         if (itemstack.getCount() < itemstack.getMaxStackSize()) {
/*     */           consumer.accept(itemstack);
/*     */         } else {
/*     */           int i = itemstack.getCount();
/*     */           while (i > 0) {
/*     */             ItemStack itemstack1 = itemstack.cloneItemStack();
/*     */             itemstack1.setCount(Math.min(itemstack.getMaxStackSize(), i));
/*     */             i -= itemstack1.getCount();
/*     */             consumer.accept(itemstack1);
/*     */           } 
/*     */         } 
/*     */       };
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void populateLootDirect(LootTableInfo loottableinfo, Consumer<ItemStack> consumer) {
/*  65 */     if (loottableinfo.a(this)) {
/*  66 */       Consumer<ItemStack> consumer1 = LootItemFunction.a(this.g, consumer, loottableinfo);
/*  67 */       LootSelector[] alootselector = this.e;
/*  68 */       int i = alootselector.length;
/*     */       
/*  70 */       for (int j = 0; j < i; j++) {
/*  71 */         LootSelector lootselector = alootselector[j];
/*     */         
/*  73 */         lootselector.a(consumer1, loottableinfo);
/*     */       } 
/*     */       
/*  76 */       loottableinfo.b(this);
/*     */     } else {
/*  78 */       LOGGER.warn("Detected infinite loop in loot tables");
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void populateLoot(LootTableInfo loottableinfo, Consumer<ItemStack> consumer) {
/*  84 */     populateLootDirect(loottableinfo, a(consumer));
/*     */   }
/*     */   
/*     */   public List<ItemStack> populateLoot(LootTableInfo loottableinfo) {
/*  88 */     List<ItemStack> list = Lists.newArrayList();
/*     */     
/*  90 */     Objects.requireNonNull(list); populateLoot(loottableinfo, list::add);
/*  91 */     return list;
/*     */   }
/*     */   
/*     */   public LootContextParameterSet getLootContextParameterSet() {
/*  95 */     return this.d;
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(LootCollector lootcollector) {
/*     */     int i;
/* 101 */     for (i = 0; i < this.e.length; i++) {
/* 102 */       this.e[i].a(lootcollector.b(".pools[" + i + "]"));
/*     */     }
/*     */     
/* 105 */     for (i = 0; i < this.f.length; i++) {
/* 106 */       this.f[i].a(lootcollector.b(".functions[" + i + "]"));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void fillInventory(IInventory iinventory, LootTableInfo loottableinfo) {
/* 113 */     fillInventory(iinventory, loottableinfo, false);
/*     */   }
/*     */ 
/*     */   
/*     */   public void fillInventory(IInventory iinventory, LootTableInfo loottableinfo, boolean plugin) {
/* 118 */     List<ItemStack> list = populateLoot(loottableinfo);
/* 119 */     Random random = loottableinfo.a();
/*     */     
/* 121 */     LootGenerateEvent event = CraftEventFactory.callLootGenerateEvent(iinventory, this, loottableinfo, list, plugin);
/* 122 */     if (event.isCancelled()) {
/*     */       return;
/*     */     }
/* 125 */     list = (List<ItemStack>)event.getLoot().stream().map(CraftItemStack::asNMSCopy).collect(Collectors.toList());
/*     */     
/* 127 */     List<Integer> list1 = a(iinventory, random);
/*     */     
/* 129 */     a(list, list1.size(), random);
/* 130 */     Iterator<ItemStack> iterator = list.iterator();
/*     */     
/* 132 */     while (iterator.hasNext()) {
/* 133 */       ItemStack itemstack = iterator.next();
/*     */       
/* 135 */       if (list1.isEmpty()) {
/* 136 */         LOGGER.warn("Tried to over-fill a container");
/*     */         
/*     */         return;
/*     */       } 
/* 140 */       if (itemstack.isEmpty()) {
/* 141 */         iinventory.setItem(((Integer)list1.remove(list1.size() - 1)).intValue(), ItemStack.b); continue;
/*     */       } 
/* 143 */       iinventory.setItem(((Integer)list1.remove(list1.size() - 1)).intValue(), itemstack);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void a(List<ItemStack> list, int i, Random random) {
/* 150 */     List<ItemStack> list1 = Lists.newArrayList();
/* 151 */     Iterator<ItemStack> iterator = list.iterator();
/*     */     
/* 153 */     while (iterator.hasNext()) {
/* 154 */       ItemStack itemstack = iterator.next();
/*     */       
/* 156 */       if (itemstack.isEmpty()) {
/* 157 */         iterator.remove(); continue;
/* 158 */       }  if (itemstack.getCount() > 1) {
/* 159 */         list1.add(itemstack);
/* 160 */         iterator.remove();
/*     */       } 
/*     */     } 
/*     */     
/* 164 */     while (i - list.size() - list1.size() > 0 && !list1.isEmpty()) {
/* 165 */       ItemStack itemstack1 = list1.remove(MathHelper.nextInt(random, 0, list1.size() - 1));
/* 166 */       int j = MathHelper.nextInt(random, 1, itemstack1.getCount() / 2);
/* 167 */       ItemStack itemstack2 = itemstack1.cloneAndSubtract(j);
/*     */       
/* 169 */       if (itemstack1.getCount() > 1 && random.nextBoolean()) {
/* 170 */         list1.add(itemstack1);
/*     */       } else {
/* 172 */         list.add(itemstack1);
/*     */       } 
/*     */       
/* 175 */       if (itemstack2.getCount() > 1 && random.nextBoolean()) {
/* 176 */         list1.add(itemstack2); continue;
/*     */       } 
/* 178 */       list.add(itemstack2);
/*     */     } 
/*     */ 
/*     */     
/* 182 */     list.addAll(list1);
/* 183 */     Collections.shuffle(list, random);
/*     */   }
/*     */   
/*     */   private List<Integer> a(IInventory iinventory, Random random) {
/* 187 */     List<Integer> list = Lists.newArrayList();
/*     */     
/* 189 */     for (int i = 0; i < iinventory.getSize(); i++) {
/* 190 */       if (iinventory.getItem(i).isEmpty()) {
/* 191 */         list.add(Integer.valueOf(i));
/*     */       }
/*     */     } 
/*     */     
/* 195 */     Collections.shuffle(list, random);
/* 196 */     return list;
/*     */   }
/*     */   
/*     */   public static a b() {
/* 200 */     return new a();
/*     */   }
/*     */ 
/*     */   
/*     */   public static class b
/*     */     implements JsonDeserializer<LootTable>, JsonSerializer<LootTable>
/*     */   {
/*     */     public LootTable deserialize(JsonElement jsonelement, Type type, JsonDeserializationContext jsondeserializationcontext) throws JsonParseException {
/* 208 */       JsonObject jsonobject = ChatDeserializer.m(jsonelement, "loot table");
/* 209 */       LootSelector[] alootselector = ChatDeserializer.<LootSelector[]>a(jsonobject, "pools", new LootSelector[0], jsondeserializationcontext, (Class)LootSelector[].class);
/* 210 */       LootContextParameterSet lootcontextparameterset = null;
/*     */       
/* 212 */       if (jsonobject.has("type")) {
/* 213 */         String s = ChatDeserializer.h(jsonobject, "type");
/*     */         
/* 215 */         lootcontextparameterset = LootContextParameterSets.a(new MinecraftKey(s));
/*     */       } 
/*     */       
/* 218 */       LootItemFunction[] alootitemfunction = ChatDeserializer.<LootItemFunction[]>a(jsonobject, "functions", new LootItemFunction[0], jsondeserializationcontext, (Class)LootItemFunction[].class);
/*     */       
/* 220 */       return new LootTable((lootcontextparameterset != null) ? lootcontextparameterset : LootContextParameterSets.GENERIC, alootselector, alootitemfunction);
/*     */     }
/*     */     
/*     */     public JsonElement serialize(LootTable loottable, Type type, JsonSerializationContext jsonserializationcontext) {
/* 224 */       JsonObject jsonobject = new JsonObject();
/*     */       
/* 226 */       if (loottable.d != LootTable.b) {
/* 227 */         MinecraftKey minecraftkey = LootContextParameterSets.a(loottable.d);
/*     */         
/* 229 */         if (minecraftkey != null) {
/* 230 */           jsonobject.addProperty("type", minecraftkey.toString());
/*     */         } else {
/* 232 */           LootTable.LOGGER.warn("Failed to find id for param set " + loottable.d);
/*     */         } 
/*     */       } 
/*     */       
/* 236 */       if (loottable.e.length > 0) {
/* 237 */         jsonobject.add("pools", jsonserializationcontext.serialize(loottable.e));
/*     */       }
/*     */       
/* 240 */       if (!ArrayUtils.isEmpty((Object[])loottable.f)) {
/* 241 */         jsonobject.add("functions", jsonserializationcontext.serialize(loottable.f));
/*     */       }
/*     */       
/* 244 */       return (JsonElement)jsonobject;
/*     */     }
/*     */   }
/*     */   
/*     */   public static class a
/*     */     implements LootItemFunctionUser<a> {
/* 250 */     private final List<LootSelector> a = Lists.newArrayList();
/* 251 */     private final List<LootItemFunction> b = Lists.newArrayList();
/*     */     private LootContextParameterSet c;
/*     */     
/*     */     public a() {
/* 255 */       this.c = LootTable.b;
/*     */     }
/*     */     
/*     */     public a a(LootSelector.a lootselector_a) {
/* 259 */       this.a.add(lootselector_a.b());
/* 260 */       return this;
/*     */     }
/*     */     
/*     */     public a a(LootContextParameterSet lootcontextparameterset) {
/* 264 */       this.c = lootcontextparameterset;
/* 265 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public a b(LootItemFunction.a lootitemfunction_a) {
/* 270 */       this.b.add(lootitemfunction_a.b());
/* 271 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public a c() {
/* 276 */       return this;
/*     */     }
/*     */     
/*     */     public LootTable b() {
/* 280 */       return new LootTable(this.c, this.a.<LootSelector>toArray(new LootSelector[0]), this.b.<LootItemFunction>toArray(new LootItemFunction[0]));
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\LootTable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
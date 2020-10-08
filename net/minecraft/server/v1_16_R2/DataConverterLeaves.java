/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.ImmutableList;
/*     */ import com.google.common.collect.ImmutableSet;
/*     */ import com.google.common.collect.Lists;
/*     */ import com.mojang.datafixers.DSL;
/*     */ import com.mojang.datafixers.DataFix;
/*     */ import com.mojang.datafixers.DataFixUtils;
/*     */ import com.mojang.datafixers.OpticFinder;
/*     */ import com.mojang.datafixers.TypeRewriteRule;
/*     */ import com.mojang.datafixers.Typed;
/*     */ import com.mojang.datafixers.schemas.Schema;
/*     */ import com.mojang.datafixers.types.Type;
/*     */ import com.mojang.datafixers.types.templates.List;
/*     */ import com.mojang.datafixers.util.Pair;
/*     */ import com.mojang.serialization.Dynamic;
/*     */ import it.unimi.dsi.fastutil.ints.Int2IntMap;
/*     */ import it.unimi.dsi.fastutil.ints.Int2IntOpenHashMap;
/*     */ import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
/*     */ import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
/*     */ import it.unimi.dsi.fastutil.ints.IntIterator;
/*     */ import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
/*     */ import it.unimi.dsi.fastutil.ints.IntSet;
/*     */ import it.unimi.dsi.fastutil.objects.Object2IntMap;
/*     */ import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectIterator;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Objects;
/*     */ import java.util.Optional;
/*     */ import java.util.Set;
/*     */ import java.util.stream.Collectors;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DataConverterLeaves
/*     */   extends DataFix
/*     */ {
/*  45 */   private static final int[][] a = new int[][] { { -1, 0, 0 }, { 1, 0, 0 }, { 0, -1, 0 }, { 0, 1, 0 }, { 0, 0, -1 }, { 0, 0, 1 } };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static final Object2IntMap<String> b;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static {
/*  58 */     b = (Object2IntMap<String>)DataFixUtils.make(new Object2IntOpenHashMap(), var0 -> {
/*     */           var0.put("minecraft:acacia_leaves", 0);
/*     */           var0.put("minecraft:birch_leaves", 1);
/*     */           var0.put("minecraft:dark_oak_leaves", 2);
/*     */           var0.put("minecraft:jungle_leaves", 3);
/*     */           var0.put("minecraft:oak_leaves", 4);
/*     */           var0.put("minecraft:spruce_leaves", 5);
/*     */         });
/*     */   }
/*  67 */   private static final Set<String> c = (Set<String>)ImmutableSet.of("minecraft:acacia_bark", "minecraft:birch_bark", "minecraft:dark_oak_bark", "minecraft:jungle_bark", "minecraft:oak_bark", "minecraft:spruce_bark", (Object[])new String[] { "minecraft:acacia_log", "minecraft:birch_log", "minecraft:dark_oak_log", "minecraft:jungle_log", "minecraft:oak_log", "minecraft:spruce_log", "minecraft:stripped_acacia_log", "minecraft:stripped_birch_log", "minecraft:stripped_dark_oak_log", "minecraft:stripped_jungle_log", "minecraft:stripped_oak_log", "minecraft:stripped_spruce_log" });
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
/*     */   public DataConverterLeaves(Schema var0, boolean var1) {
/*  89 */     super(var0, var1);
/*     */   }
/*     */ 
/*     */   
/*     */   protected TypeRewriteRule makeRule() {
/*  94 */     Type<?> var0 = getInputSchema().getType(DataConverterTypes.CHUNK);
/*     */     
/*  96 */     OpticFinder<?> var1 = var0.findField("Level");
/*  97 */     OpticFinder<?> var2 = var1.type().findField("Sections");
/*  98 */     Type<?> var3 = var2.type();
/*  99 */     if (!(var3 instanceof List.ListType)) {
/* 100 */       throw new IllegalStateException("Expecting sections to be a list.");
/*     */     }
/* 102 */     Type<?> var4 = ((List.ListType)var3).getElement();
/* 103 */     OpticFinder<?> var5 = DSL.typeFinder(var4);
/*     */     
/* 105 */     return fixTypeEverywhereTyped("Leaves fix", var0, var3 -> var3.updateTyped(var0, ()));
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
/*     */   public static abstract class b
/*     */   {
/* 193 */     private final Type<Pair<String, Dynamic<?>>> e = DSL.named(DataConverterTypes.BLOCK_STATE.typeName(), DSL.remainderType());
/* 194 */     protected final OpticFinder<List<Pair<String, Dynamic<?>>>> a = DSL.fieldFinder("Palette", (Type)DSL.list(this.e));
/*     */     
/*     */     protected final List<Dynamic<?>> b;
/*     */     protected final int c;
/*     */     @Nullable
/*     */     protected DataBitsPacked d;
/*     */     
/*     */     public b(Typed<?> var0, Schema var1) {
/* 202 */       if (!Objects.equals(var1.getType(DataConverterTypes.BLOCK_STATE), this.e)) {
/* 203 */         throw new IllegalStateException("Block state type is not what was expected.");
/*     */       }
/*     */       
/* 206 */       Optional<List<Pair<String, Dynamic<?>>>> var2 = var0.getOptional(this.a);
/*     */       
/* 208 */       this.b = (List<Dynamic<?>>)var2.map(var0 -> (List)var0.stream().map(Pair::getSecond).collect(Collectors.toList())).orElse(ImmutableList.of());
/*     */       
/* 210 */       Dynamic<?> var3 = (Dynamic)var0.get(DSL.remainderFinder());
/* 211 */       this.c = var3.get("Y").asInt(0);
/*     */       
/* 213 */       a(var3);
/*     */     }
/*     */     
/*     */     protected void a(Dynamic<?> var0) {
/* 217 */       if (a()) {
/* 218 */         this.d = null;
/*     */       } else {
/* 220 */         long[] var1 = var0.get("BlockStates").asLongStream().toArray();
/* 221 */         int var2 = Math.max(4, DataFixUtils.ceillog2(this.b.size()));
/* 222 */         this.d = new DataBitsPacked(var2, 4096, var1);
/*     */       } 
/*     */     }
/*     */     
/*     */     public Typed<?> a(Typed<?> var0) {
/* 227 */       if (b()) {
/* 228 */         return var0;
/*     */       }
/* 230 */       return var0
/* 231 */         .update(DSL.remainderFinder(), var0 -> var0.set("BlockStates", var0.createLongList(Arrays.stream(this.d.a()))))
/* 232 */         .set(this.a, this.b.stream().map(var0 -> Pair.of(DataConverterTypes.BLOCK_STATE.typeName(), var0)).collect(Collectors.toList()));
/*     */     }
/*     */     
/*     */     public boolean b() {
/* 236 */       return (this.d == null);
/*     */     }
/*     */     
/*     */     public int c(int var0) {
/* 240 */       return this.d.a(var0);
/*     */     }
/*     */     
/*     */     protected int a(String var0, boolean var1, int var2) {
/* 244 */       return DataConverterLeaves.a().get(var0).intValue() << 5 | (var1 ? 16 : 0) | var2;
/*     */     }
/*     */     
/*     */     int c() {
/* 248 */       return this.c;
/*     */     }
/*     */ 
/*     */     
/*     */     protected abstract boolean a();
/*     */   }
/*     */ 
/*     */   
/*     */   public static final class a
/*     */     extends b
/*     */   {
/*     */     @Nullable
/*     */     private IntSet e;
/*     */     
/*     */     @Nullable
/*     */     private IntSet f;
/*     */     @Nullable
/*     */     private Int2IntMap g;
/*     */     
/*     */     public a(Typed<?> var0, Schema var1) {
/* 268 */       super(var0, var1);
/*     */     }
/*     */ 
/*     */     
/*     */     protected boolean a() {
/* 273 */       this.e = (IntSet)new IntOpenHashSet();
/* 274 */       this.f = (IntSet)new IntOpenHashSet();
/* 275 */       this.g = (Int2IntMap)new Int2IntOpenHashMap();
/*     */       
/* 277 */       for (int var0 = 0; var0 < this.b.size(); var0++) {
/* 278 */         Dynamic<?> var1 = this.b.get(var0);
/* 279 */         String var2 = var1.get("Name").asString("");
/* 280 */         if (DataConverterLeaves.a().containsKey(var2)) {
/* 281 */           boolean var3 = Objects.equals(var1.get("Properties").get("decayable").asString(""), "false");
/* 282 */           this.e.add(var0);
/* 283 */           this.g.put(a(var2, var3, 7), var0);
/* 284 */           this.b.set(var0, a(var1, var2, var3, 7));
/*     */         } 
/* 286 */         if (DataConverterLeaves.b().contains(var2)) {
/* 287 */           this.f.add(var0);
/*     */         }
/*     */       } 
/*     */       
/* 291 */       return (this.e.isEmpty() && this.f.isEmpty());
/*     */     }
/*     */     
/*     */     private Dynamic<?> a(Dynamic<?> var0, String var1, boolean var2, int var3) {
/* 295 */       Dynamic<?> var4 = var0.emptyMap();
/* 296 */       var4 = var4.set("persistent", var4.createString(var2 ? "true" : "false"));
/* 297 */       var4 = var4.set("distance", var4.createString(Integer.toString(var3)));
/*     */       
/* 299 */       Dynamic<?> var5 = var0.emptyMap();
/* 300 */       var5 = var5.set("Properties", var4);
/* 301 */       var5 = var5.set("Name", var5.createString(var1));
/* 302 */       return var5;
/*     */     }
/*     */     
/*     */     public boolean a(int var0) {
/* 306 */       return this.f.contains(var0);
/*     */     }
/*     */     
/*     */     public boolean b(int var0) {
/* 310 */       return this.e.contains(var0);
/*     */     }
/*     */     
/*     */     private int d(int var0) {
/* 314 */       if (a(var0)) {
/* 315 */         return 0;
/*     */       }
/* 317 */       return Integer.parseInt(((Dynamic)this.b.get(var0)).get("Properties").get("distance").asString(""));
/*     */     }
/*     */     
/*     */     private void a(int var0, int var1, int var2) {
/* 321 */       Dynamic<?> var3 = this.b.get(var1);
/* 322 */       String var4 = var3.get("Name").asString("");
/* 323 */       boolean var5 = Objects.equals(var3.get("Properties").get("persistent").asString(""), "true");
/* 324 */       int var6 = a(var4, var5, var2);
/*     */       
/* 326 */       if (!this.g.containsKey(var6)) {
/* 327 */         int i = this.b.size();
/* 328 */         this.e.add(i);
/* 329 */         this.g.put(var6, i);
/* 330 */         this.b.add(a(var3, var4, var5, var2));
/*     */       } 
/*     */       
/* 333 */       int var7 = this.g.get(var6);
/* 334 */       if (1 << this.d.b() <= var7) {
/* 335 */         DataBitsPacked var8 = new DataBitsPacked(this.d.b() + 1, 4096);
/* 336 */         for (int var9 = 0; var9 < 4096; var9++) {
/* 337 */           var8.a(var9, this.d.a(var9));
/*     */         }
/* 339 */         this.d = var8;
/*     */       } 
/* 341 */       this.d.a(var0, var7);
/*     */     }
/*     */   }
/*     */   
/*     */   public static int a(int var0, int var1, int var2) {
/* 346 */     return var1 << 8 | var2 << 4 | var0;
/*     */   }
/*     */   
/*     */   private int a(int var0) {
/* 350 */     return var0 & 0xF;
/*     */   }
/*     */   
/*     */   private int b(int var0) {
/* 354 */     return var0 >> 8 & 0xFF;
/*     */   }
/*     */   
/*     */   private int c(int var0) {
/* 358 */     return var0 >> 4 & 0xF;
/*     */   }
/*     */   
/*     */   public static int a(boolean var0, boolean var1, boolean var2, boolean var3) {
/* 362 */     int var4 = 0;
/* 363 */     if (var2) {
/* 364 */       if (var1) {
/* 365 */         var4 |= 0x2;
/* 366 */       } else if (var0) {
/* 367 */         var4 |= 0x80;
/*     */       } else {
/* 369 */         var4 |= 0x1;
/*     */       } 
/* 371 */     } else if (var3) {
/* 372 */       if (var0) {
/* 373 */         var4 |= 0x20;
/* 374 */       } else if (var1) {
/* 375 */         var4 |= 0x8;
/*     */       } else {
/* 377 */         var4 |= 0x10;
/*     */       } 
/* 379 */     } else if (var1) {
/* 380 */       var4 |= 0x4;
/* 381 */     } else if (var0) {
/* 382 */       var4 |= 0x40;
/*     */     } 
/* 384 */     return var4;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataConverterLeaves.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
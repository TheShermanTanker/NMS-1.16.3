/*     */ package net.minecraft.server.v1_16_R2;
/*     */ import com.google.common.base.Splitter;
/*     */ import com.google.common.collect.ImmutableMap;
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Maps;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.mojang.datafixers.DataFix;
/*     */ import com.mojang.datafixers.TypeRewriteRule;
/*     */ import com.mojang.datafixers.Typed;
/*     */ import com.mojang.datafixers.schemas.Schema;
/*     */ import com.mojang.datafixers.types.Type;
/*     */ import com.mojang.datafixers.util.Pair;
/*     */ import com.mojang.serialization.DataResult;
/*     */ import com.mojang.serialization.Dynamic;
/*     */ import com.mojang.serialization.DynamicOps;
/*     */ import com.mojang.serialization.JsonOps;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import java.util.Optional;
/*     */ import java.util.stream.Collectors;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ public class DataConverterLevelDataGeneratorOptions extends DataFix {
/*     */   static {
/*  29 */     a = SystemUtils.<Map<String, String>>a(Maps.newHashMap(), var0 -> {
/*     */           var0.put("0", "minecraft:ocean");
/*     */           var0.put("1", "minecraft:plains");
/*     */           var0.put("2", "minecraft:desert");
/*     */           var0.put("3", "minecraft:mountains");
/*     */           var0.put("4", "minecraft:forest");
/*     */           var0.put("5", "minecraft:taiga");
/*     */           var0.put("6", "minecraft:swamp");
/*     */           var0.put("7", "minecraft:river");
/*     */           var0.put("8", "minecraft:nether");
/*     */           var0.put("9", "minecraft:the_end");
/*     */           var0.put("10", "minecraft:frozen_ocean");
/*     */           var0.put("11", "minecraft:frozen_river");
/*     */           var0.put("12", "minecraft:snowy_tundra");
/*     */           var0.put("13", "minecraft:snowy_mountains");
/*     */           var0.put("14", "minecraft:mushroom_fields");
/*     */           var0.put("15", "minecraft:mushroom_field_shore");
/*     */           var0.put("16", "minecraft:beach");
/*     */           var0.put("17", "minecraft:desert_hills");
/*     */           var0.put("18", "minecraft:wooded_hills");
/*     */           var0.put("19", "minecraft:taiga_hills");
/*     */           var0.put("20", "minecraft:mountain_edge");
/*     */           var0.put("21", "minecraft:jungle");
/*     */           var0.put("22", "minecraft:jungle_hills");
/*     */           var0.put("23", "minecraft:jungle_edge");
/*     */           var0.put("24", "minecraft:deep_ocean");
/*     */           var0.put("25", "minecraft:stone_shore");
/*     */           var0.put("26", "minecraft:snowy_beach");
/*     */           var0.put("27", "minecraft:birch_forest");
/*     */           var0.put("28", "minecraft:birch_forest_hills");
/*     */           var0.put("29", "minecraft:dark_forest");
/*     */           var0.put("30", "minecraft:snowy_taiga");
/*     */           var0.put("31", "minecraft:snowy_taiga_hills");
/*     */           var0.put("32", "minecraft:giant_tree_taiga");
/*     */           var0.put("33", "minecraft:giant_tree_taiga_hills");
/*     */           var0.put("34", "minecraft:wooded_mountains");
/*     */           var0.put("35", "minecraft:savanna");
/*     */           var0.put("36", "minecraft:savanna_plateau");
/*     */           var0.put("37", "minecraft:badlands");
/*     */           var0.put("38", "minecraft:wooded_badlands_plateau");
/*     */           var0.put("39", "minecraft:badlands_plateau");
/*     */           var0.put("40", "minecraft:small_end_islands");
/*     */           var0.put("41", "minecraft:end_midlands");
/*     */           var0.put("42", "minecraft:end_highlands");
/*     */           var0.put("43", "minecraft:end_barrens");
/*     */           var0.put("44", "minecraft:warm_ocean");
/*     */           var0.put("45", "minecraft:lukewarm_ocean");
/*     */           var0.put("46", "minecraft:cold_ocean");
/*     */           var0.put("47", "minecraft:deep_warm_ocean");
/*     */           var0.put("48", "minecraft:deep_lukewarm_ocean");
/*     */           var0.put("49", "minecraft:deep_cold_ocean");
/*     */           var0.put("50", "minecraft:deep_frozen_ocean");
/*     */           var0.put("127", "minecraft:the_void");
/*     */           var0.put("129", "minecraft:sunflower_plains");
/*     */           var0.put("130", "minecraft:desert_lakes");
/*     */           var0.put("131", "minecraft:gravelly_mountains");
/*     */           var0.put("132", "minecraft:flower_forest");
/*     */           var0.put("133", "minecraft:taiga_mountains");
/*     */           var0.put("134", "minecraft:swamp_hills");
/*     */           var0.put("140", "minecraft:ice_spikes");
/*     */           var0.put("149", "minecraft:modified_jungle");
/*     */           var0.put("151", "minecraft:modified_jungle_edge");
/*     */           var0.put("155", "minecraft:tall_birch_forest");
/*     */           var0.put("156", "minecraft:tall_birch_hills");
/*     */           var0.put("157", "minecraft:dark_forest_hills");
/*     */           var0.put("158", "minecraft:snowy_taiga_mountains");
/*     */           var0.put("160", "minecraft:giant_spruce_taiga");
/*     */           var0.put("161", "minecraft:giant_spruce_taiga_hills");
/*     */           var0.put("162", "minecraft:modified_gravelly_mountains");
/*     */           var0.put("163", "minecraft:shattered_savanna");
/*     */           var0.put("164", "minecraft:shattered_savanna_plateau");
/*     */           var0.put("165", "minecraft:eroded_badlands");
/*     */           var0.put("166", "minecraft:modified_wooded_badlands_plateau");
/*     */           var0.put("167", "minecraft:modified_badlands_plateau");
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   static final Map<String, String> a;
/*     */   
/*     */   public DataConverterLevelDataGeneratorOptions(Schema var0, boolean var1) {
/* 110 */     super(var0, var1);
/*     */   }
/*     */ 
/*     */   
/*     */   protected TypeRewriteRule makeRule() {
/* 115 */     Type<?> var0 = getOutputSchema().getType(DataConverterTypes.LEVEL);
/* 116 */     return fixTypeEverywhereTyped("LevelDataGeneratorOptionsFix", getInputSchema().getType(DataConverterTypes.LEVEL), var0, var1 -> (Typed)var1.write().flatMap(()).map(Pair::getFirst).result().orElseThrow(()));
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
/*     */   private static <T> Dynamic<T> a(String var0, DynamicOps<T> var1) {
/*     */     List<Pair<Integer, String>> var3;
/* 135 */     Iterator<String> var2 = Splitter.on(';').split(var0).iterator();
/*     */ 
/*     */     
/* 138 */     String var4 = "minecraft:plains";
/* 139 */     Map<String, Map<String, String>> var5 = Maps.newHashMap();
/*     */     
/* 141 */     if (!var0.isEmpty() && var2.hasNext()) {
/* 142 */       var3 = b(var2.next());
/*     */       
/* 144 */       if (!var3.isEmpty()) {
/* 145 */         if (var2.hasNext()) {
/* 146 */           var4 = a.getOrDefault(var2.next(), "minecraft:plains");
/*     */         }
/*     */         
/* 149 */         if (var2.hasNext()) {
/* 150 */           String[] arrayOfString = ((String)var2.next()).toLowerCase(Locale.ROOT).split(",");
/*     */           
/* 152 */           for (String var10 : arrayOfString) {
/* 153 */             String[] var11 = var10.split("\\(", 2);
/*     */             
/* 155 */             if (!var11[0].isEmpty()) {
/* 156 */               var5.put(var11[0], Maps.newHashMap());
/*     */               
/* 158 */               if (var11.length > 1 && var11[1].endsWith(")") && var11[1].length() > 1) {
/* 159 */                 String[] var12 = var11[1].substring(0, var11[1].length() - 1).split(" ");
/*     */                 
/* 161 */                 for (String var16 : var12) {
/* 162 */                   String[] var17 = var16.split("=", 2);
/* 163 */                   if (var17.length == 2) {
/* 164 */                     ((Map<String, String>)var5.get(var11[0])).put(var17[0], var17[1]);
/*     */                   }
/*     */                 } 
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } else {
/* 171 */           var5.put("village", Maps.newHashMap());
/*     */         } 
/*     */       } 
/*     */     } else {
/* 175 */       var3 = Lists.newArrayList();
/* 176 */       var3.add(Pair.of(Integer.valueOf(1), "minecraft:bedrock"));
/* 177 */       var3.add(Pair.of(Integer.valueOf(2), "minecraft:dirt"));
/* 178 */       var3.add(Pair.of(Integer.valueOf(1), "minecraft:grass_block"));
/* 179 */       var5.put("village", Maps.newHashMap());
/*     */     } 
/*     */     
/* 182 */     T var6 = (T)var1.createList(var3.stream().map(var1 -> var0.createMap((Map)ImmutableMap.of(var0.createString("height"), var0.createInt(((Integer)var1.getFirst()).intValue()), var0.createString("block"), var0.createString((String)var1.getSecond())))));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 187 */     T var7 = (T)var1.createMap((Map)var5.entrySet().stream().map(var1 -> Pair.of(var0.createString(((String)var1.getKey()).toLowerCase(Locale.ROOT)), var0.createMap((Map)((Map)var1.getValue()).entrySet().stream().map(()).collect(Collectors.toMap(Pair::getFirst, Pair::getSecond)))))
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 194 */         .collect(Collectors.toMap(Pair::getFirst, Pair::getSecond)));
/*     */     
/* 196 */     return new Dynamic(var1, var1.createMap((Map)ImmutableMap.of(var1
/* 197 */             .createString("layers"), var6, var1
/* 198 */             .createString("biome"), var1.createString(var4), var1
/* 199 */             .createString("structures"), var7)));
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   private static Pair<Integer, String> a(String var0) {
/*     */     int var2;
/* 205 */     String[] var1 = var0.split("\\*", 2);
/*     */ 
/*     */     
/* 208 */     if (var1.length == 2) {
/*     */       try {
/* 210 */         var2 = Integer.parseInt(var1[0]);
/* 211 */       } catch (NumberFormatException numberFormatException) {
/* 212 */         return null;
/*     */       } 
/*     */     } else {
/* 215 */       var2 = 1;
/*     */     } 
/*     */     
/* 218 */     String var3 = var1[var1.length - 1];
/* 219 */     return Pair.of(Integer.valueOf(var2), var3);
/*     */   }
/*     */   
/*     */   private static List<Pair<Integer, String>> b(String var0) {
/* 223 */     List<Pair<Integer, String>> var1 = Lists.newArrayList();
/* 224 */     String[] var2 = var0.split(",");
/*     */     
/* 226 */     for (String var6 : var2) {
/* 227 */       Pair<Integer, String> var7 = a(var6);
/* 228 */       if (var7 == null) {
/* 229 */         return Collections.emptyList();
/*     */       }
/* 231 */       var1.add(var7);
/*     */     } 
/*     */     
/* 234 */     return var1;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataConverterLevelDataGeneratorOptions.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
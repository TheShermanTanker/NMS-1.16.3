/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Maps;
/*     */ import com.google.common.collect.Sets;
/*     */ import com.mojang.datafixers.DataFix;
/*     */ import com.mojang.datafixers.DataFixUtils;
/*     */ import com.mojang.datafixers.TypeRewriteRule;
/*     */ import com.mojang.datafixers.schemas.Schema;
/*     */ import com.mojang.datafixers.types.Type;
/*     */ import com.mojang.serialization.Dynamic;
/*     */ import it.unimi.dsi.fastutil.ints.Int2ObjectLinkedOpenHashMap;
/*     */ import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
/*     */ import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
/*     */ import it.unimi.dsi.fastutil.ints.IntArrayList;
/*     */ import it.unimi.dsi.fastutil.ints.IntList;
/*     */ import it.unimi.dsi.fastutil.ints.IntListIterator;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectIterator;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.util.Arrays;
/*     */ import java.util.BitSet;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Objects;
/*     */ import java.util.Optional;
/*     */ import java.util.Set;
/*     */ import java.util.function.Supplier;
/*     */ import java.util.stream.Stream;
/*     */ import javax.annotation.Nullable;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ChunkConverterPalette
/*     */   extends DataFix
/*     */ {
/*     */   public ChunkConverterPalette(Schema var0, boolean var1) {
/*  43 */     super(var0, var1);
/*     */   }
/*     */   
/*  46 */   private static final Logger LOGGER = LogManager.getLogger();
/*     */   
/*  48 */   private static final BitSet b = new BitSet(256);
/*  49 */   private static final BitSet c = new BitSet(256);
/*  50 */   private static final Dynamic<?> d = DataConverterFlattenData.b("{Name:'minecraft:pumpkin'}");
/*  51 */   private static final Dynamic<?> e = DataConverterFlattenData.b("{Name:'minecraft:podzol',Properties:{snowy:'true'}}");
/*  52 */   private static final Dynamic<?> f = DataConverterFlattenData.b("{Name:'minecraft:grass_block',Properties:{snowy:'true'}}");
/*  53 */   private static final Dynamic<?> g = DataConverterFlattenData.b("{Name:'minecraft:mycelium',Properties:{snowy:'true'}}");
/*  54 */   private static final Dynamic<?> h = DataConverterFlattenData.b("{Name:'minecraft:sunflower',Properties:{half:'upper'}}");
/*  55 */   private static final Dynamic<?> i = DataConverterFlattenData.b("{Name:'minecraft:lilac',Properties:{half:'upper'}}");
/*  56 */   private static final Dynamic<?> j = DataConverterFlattenData.b("{Name:'minecraft:tall_grass',Properties:{half:'upper'}}");
/*  57 */   private static final Dynamic<?> k = DataConverterFlattenData.b("{Name:'minecraft:large_fern',Properties:{half:'upper'}}");
/*  58 */   private static final Dynamic<?> l = DataConverterFlattenData.b("{Name:'minecraft:rose_bush',Properties:{half:'upper'}}");
/*  59 */   private static final Dynamic<?> m = DataConverterFlattenData.b("{Name:'minecraft:peony',Properties:{half:'upper'}}");
/*     */   private static final Map<String, Dynamic<?>> n; private static final Map<String, Dynamic<?>> o; private static final Map<String, Dynamic<?>> p; private static final Map<String, Dynamic<?>> q; private static final Int2ObjectMap<String> r; private static final Map<String, Dynamic<?>> s; private static final Map<String, Dynamic<?>> t; private static void a(Map<String, Dynamic<?>> var0, int var1, String var2, String var3) { var0.put(var1 + "north", DataConverterFlattenData.b("{Name:'minecraft:" + var2 + "_wall_" + var3 + "',Properties:{facing:'north'}}")); var0.put(var1 + "east", DataConverterFlattenData.b("{Name:'minecraft:" + var2 + "_wall_" + var3 + "',Properties:{facing:'east'}}")); var0.put(var1 + "south", DataConverterFlattenData.b("{Name:'minecraft:" + var2 + "_wall_" + var3 + "',Properties:{facing:'south'}}")); var0.put(var1 + "west", DataConverterFlattenData.b("{Name:'minecraft:" + var2 + "_wall_" + var3 + "',Properties:{facing:'west'}}")); for (int var4 = 0; var4 < 16; var4++)
/*  61 */       var0.put(var1 + "" + var4, DataConverterFlattenData.b("{Name:'minecraft:" + var2 + "_" + var3 + "',Properties:{rotation:'" + var4 + "'}}"));  } static { n = (Map<String, Dynamic<?>>)DataFixUtils.make(Maps.newHashMap(), var0 -> {
/*     */           var0.put("minecraft:air0", DataConverterFlattenData.b("{Name:'minecraft:flower_pot'}"));
/*     */           
/*     */           var0.put("minecraft:red_flower0", DataConverterFlattenData.b("{Name:'minecraft:potted_poppy'}"));
/*     */           var0.put("minecraft:red_flower1", DataConverterFlattenData.b("{Name:'minecraft:potted_blue_orchid'}"));
/*     */           var0.put("minecraft:red_flower2", DataConverterFlattenData.b("{Name:'minecraft:potted_allium'}"));
/*     */           var0.put("minecraft:red_flower3", DataConverterFlattenData.b("{Name:'minecraft:potted_azure_bluet'}"));
/*     */           var0.put("minecraft:red_flower4", DataConverterFlattenData.b("{Name:'minecraft:potted_red_tulip'}"));
/*     */           var0.put("minecraft:red_flower5", DataConverterFlattenData.b("{Name:'minecraft:potted_orange_tulip'}"));
/*     */           var0.put("minecraft:red_flower6", DataConverterFlattenData.b("{Name:'minecraft:potted_white_tulip'}"));
/*     */           var0.put("minecraft:red_flower7", DataConverterFlattenData.b("{Name:'minecraft:potted_pink_tulip'}"));
/*     */           var0.put("minecraft:red_flower8", DataConverterFlattenData.b("{Name:'minecraft:potted_oxeye_daisy'}"));
/*     */           var0.put("minecraft:yellow_flower0", DataConverterFlattenData.b("{Name:'minecraft:potted_dandelion'}"));
/*     */           var0.put("minecraft:sapling0", DataConverterFlattenData.b("{Name:'minecraft:potted_oak_sapling'}"));
/*     */           var0.put("minecraft:sapling1", DataConverterFlattenData.b("{Name:'minecraft:potted_spruce_sapling'}"));
/*     */           var0.put("minecraft:sapling2", DataConverterFlattenData.b("{Name:'minecraft:potted_birch_sapling'}"));
/*     */           var0.put("minecraft:sapling3", DataConverterFlattenData.b("{Name:'minecraft:potted_jungle_sapling'}"));
/*     */           var0.put("minecraft:sapling4", DataConverterFlattenData.b("{Name:'minecraft:potted_acacia_sapling'}"));
/*     */           var0.put("minecraft:sapling5", DataConverterFlattenData.b("{Name:'minecraft:potted_dark_oak_sapling'}"));
/*     */           var0.put("minecraft:red_mushroom0", DataConverterFlattenData.b("{Name:'minecraft:potted_red_mushroom'}"));
/*     */           var0.put("minecraft:brown_mushroom0", DataConverterFlattenData.b("{Name:'minecraft:potted_brown_mushroom'}"));
/*     */           var0.put("minecraft:deadbush0", DataConverterFlattenData.b("{Name:'minecraft:potted_dead_bush'}"));
/*     */           var0.put("minecraft:tallgrass2", DataConverterFlattenData.b("{Name:'minecraft:potted_fern'}"));
/*     */           var0.put("minecraft:cactus0", DataConverterFlattenData.b(2240));
/*     */         });
/*  86 */     o = (Map<String, Dynamic<?>>)DataFixUtils.make(Maps.newHashMap(), var0 -> {
/*     */           a(var0, 0, "skeleton", "skull");
/*     */ 
/*     */           
/*     */           a(var0, 1, "wither_skeleton", "skull");
/*     */ 
/*     */           
/*     */           a(var0, 2, "zombie", "head");
/*     */ 
/*     */           
/*     */           a(var0, 3, "player", "head");
/*     */ 
/*     */           
/*     */           a(var0, 4, "creeper", "head");
/*     */ 
/*     */           
/*     */           a(var0, 5, "dragon", "head");
/*     */         });
/*     */     
/* 105 */     p = (Map<String, Dynamic<?>>)DataFixUtils.make(Maps.newHashMap(), var0 -> {
/*     */           a(var0, "oak_door", 1024);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           a(var0, "iron_door", 1136);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           a(var0, "spruce_door", 3088);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           a(var0, "birch_door", 3104);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           a(var0, "jungle_door", 3120);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           a(var0, "acacia_door", 3136);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           a(var0, "dark_oak_door", 3152);
/*     */         });
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 182 */     q = (Map<String, Dynamic<?>>)DataFixUtils.make(Maps.newHashMap(), var0 -> {
/*     */           for (int var1 = 0; var1 < 26; var1++) {
/*     */             var0.put("true" + var1, DataConverterFlattenData.b("{Name:'minecraft:note_block',Properties:{powered:'true',note:'" + var1 + "'}}"));
/*     */             
/*     */             var0.put("false" + var1, DataConverterFlattenData.b("{Name:'minecraft:note_block',Properties:{powered:'false',note:'" + var1 + "'}}"));
/*     */           } 
/*     */         });
/* 189 */     r = (Int2ObjectMap<String>)DataFixUtils.make(new Int2ObjectOpenHashMap(), var0 -> {
/*     */           var0.put(0, "white");
/*     */           
/*     */           var0.put(1, "orange");
/*     */           var0.put(2, "magenta");
/*     */           var0.put(3, "light_blue");
/*     */           var0.put(4, "yellow");
/*     */           var0.put(5, "lime");
/*     */           var0.put(6, "pink");
/*     */           var0.put(7, "gray");
/*     */           var0.put(8, "light_gray");
/*     */           var0.put(9, "cyan");
/*     */           var0.put(10, "purple");
/*     */           var0.put(11, "blue");
/*     */           var0.put(12, "brown");
/*     */           var0.put(13, "green");
/*     */           var0.put(14, "red");
/*     */           var0.put(15, "black");
/*     */         });
/* 208 */     s = (Map<String, Dynamic<?>>)DataFixUtils.make(Maps.newHashMap(), var0 -> {
/*     */           ObjectIterator<Int2ObjectMap.Entry<String>> objectIterator = r.int2ObjectEntrySet().iterator();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           while (objectIterator.hasNext()) {
/*     */             Int2ObjectMap.Entry<String> var2 = objectIterator.next();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/*     */             if (!Objects.equals(var2.getValue(), "red")) {
/*     */               a(var0, var2.getIntKey(), (String)var2.getValue());
/*     */             }
/*     */           } 
/*     */         });
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 231 */     t = (Map<String, Dynamic<?>>)DataFixUtils.make(Maps.newHashMap(), var0 -> {
/*     */           ObjectIterator<Int2ObjectMap.Entry<String>> objectIterator = r.int2ObjectEntrySet().iterator();
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           while (objectIterator.hasNext()) {
/*     */             Int2ObjectMap.Entry<String> var2 = objectIterator.next();
/*     */ 
/*     */ 
/*     */ 
/*     */             
/*     */             if (!Objects.equals(var2.getValue(), "white")) {
/*     */               b(var0, 15 - var2.getIntKey(), (String)var2.getValue());
/*     */             }
/*     */           } 
/*     */         });
/*     */ 
/*     */ 
/*     */     
/* 251 */     c.set(2);
/* 252 */     c.set(3);
/* 253 */     c.set(110);
/*     */     
/* 255 */     c.set(140);
/* 256 */     c.set(144);
/*     */     
/* 258 */     c.set(25);
/*     */     
/* 260 */     c.set(86);
/*     */ 
/*     */     
/* 263 */     c.set(26);
/* 264 */     c.set(176);
/* 265 */     c.set(177);
/*     */     
/* 267 */     c.set(175);
/*     */     
/* 269 */     c.set(64);
/* 270 */     c.set(71);
/* 271 */     c.set(193);
/* 272 */     c.set(194);
/* 273 */     c.set(195);
/* 274 */     c.set(196);
/* 275 */     c.set(197);
/*     */     
/* 277 */     b.set(54);
/* 278 */     b.set(146);
/*     */     
/* 280 */     b.set(25);
/*     */     
/* 282 */     b.set(26);
/*     */     
/* 284 */     b.set(51);
/*     */     
/* 286 */     b.set(53);
/* 287 */     b.set(67);
/* 288 */     b.set(108);
/* 289 */     b.set(109);
/* 290 */     b.set(114);
/* 291 */     b.set(128);
/* 292 */     b.set(134);
/* 293 */     b.set(135);
/* 294 */     b.set(136);
/* 295 */     b.set(156);
/* 296 */     b.set(163);
/* 297 */     b.set(164);
/* 298 */     b.set(180);
/* 299 */     b.set(203);
/*     */     
/* 301 */     b.set(55);
/*     */     
/* 303 */     b.set(85);
/* 304 */     b.set(113);
/* 305 */     b.set(188);
/* 306 */     b.set(189);
/* 307 */     b.set(190);
/* 308 */     b.set(191);
/* 309 */     b.set(192);
/*     */     
/* 311 */     b.set(93);
/* 312 */     b.set(94);
/*     */     
/* 314 */     b.set(101);
/* 315 */     b.set(102);
/* 316 */     b.set(160);
/*     */     
/* 318 */     b.set(106);
/*     */ 
/*     */     
/* 321 */     b.set(107);
/* 322 */     b.set(183);
/* 323 */     b.set(184);
/* 324 */     b.set(185);
/* 325 */     b.set(186);
/* 326 */     b.set(187);
/*     */     
/* 328 */     b.set(132);
/* 329 */     b.set(139);
/*     */     
/* 331 */     b.set(199); }
/*     */   private static void a(Map<String, Dynamic<?>> var0, String var1, int var2) { var0.put("minecraft:" + var1 + "eastlowerleftfalsefalse", DataConverterFlattenData.b("{Name:'minecraft:" + var1 + "',Properties:{facing:'east',half:'lower',hinge:'left',open:'false',powered:'false'}}")); var0.put("minecraft:" + var1 + "eastlowerleftfalsetrue", DataConverterFlattenData.b("{Name:'minecraft:" + var1 + "',Properties:{facing:'east',half:'lower',hinge:'left',open:'false',powered:'true'}}")); var0.put("minecraft:" + var1 + "eastlowerlefttruefalse", DataConverterFlattenData.b("{Name:'minecraft:" + var1 + "',Properties:{facing:'east',half:'lower',hinge:'left',open:'true',powered:'false'}}")); var0.put("minecraft:" + var1 + "eastlowerlefttruetrue", DataConverterFlattenData.b("{Name:'minecraft:" + var1 + "',Properties:{facing:'east',half:'lower',hinge:'left',open:'true',powered:'true'}}")); var0.put("minecraft:" + var1 + "eastlowerrightfalsefalse", DataConverterFlattenData.b(var2)); var0.put("minecraft:" + var1 + "eastlowerrightfalsetrue", DataConverterFlattenData.b("{Name:'minecraft:" + var1 + "',Properties:{facing:'east',half:'lower',hinge:'right',open:'false',powered:'true'}}")); var0.put("minecraft:" + var1 + "eastlowerrighttruefalse", DataConverterFlattenData.b(var2 + 4)); var0.put("minecraft:" + var1 + "eastlowerrighttruetrue", DataConverterFlattenData.b("{Name:'minecraft:" + var1 + "',Properties:{facing:'east',half:'lower',hinge:'right',open:'true',powered:'true'}}")); var0.put("minecraft:" + var1 + "eastupperleftfalsefalse", DataConverterFlattenData.b(var2 + 8)); var0.put("minecraft:" + var1 + "eastupperleftfalsetrue", DataConverterFlattenData.b(var2 + 10)); var0.put("minecraft:" + var1 + "eastupperlefttruefalse", DataConverterFlattenData.b("{Name:'minecraft:" + var1 + "',Properties:{facing:'east',half:'upper',hinge:'left',open:'true',powered:'false'}}")); var0.put("minecraft:" + var1 + "eastupperlefttruetrue", DataConverterFlattenData.b("{Name:'minecraft:" + var1 + "',Properties:{facing:'east',half:'upper',hinge:'left',open:'true',powered:'true'}}")); var0.put("minecraft:" + var1 + "eastupperrightfalsefalse", DataConverterFlattenData.b(var2 + 9)); var0.put("minecraft:" + var1 + "eastupperrightfalsetrue", DataConverterFlattenData.b(var2 + 11)); var0.put("minecraft:" + var1 + "eastupperrighttruefalse", DataConverterFlattenData.b("{Name:'minecraft:" + var1 + "',Properties:{facing:'east',half:'upper',hinge:'right',open:'true',powered:'false'}}")); var0.put("minecraft:" + var1 + "eastupperrighttruetrue", DataConverterFlattenData.b("{Name:'minecraft:" + var1 + "',Properties:{facing:'east',half:'upper',hinge:'right',open:'true',powered:'true'}}")); var0.put("minecraft:" + var1 + "northlowerleftfalsefalse", DataConverterFlattenData.b("{Name:'minecraft:" + var1 + "',Properties:{facing:'north',half:'lower',hinge:'left',open:'false',powered:'false'}}")); var0.put("minecraft:" + var1 + "northlowerleftfalsetrue", DataConverterFlattenData.b("{Name:'minecraft:" + var1 + "',Properties:{facing:'north',half:'lower',hinge:'left',open:'false',powered:'true'}}")); var0.put("minecraft:" + var1 + "northlowerlefttruefalse", DataConverterFlattenData.b("{Name:'minecraft:" + var1 + "',Properties:{facing:'north',half:'lower',hinge:'left',open:'true',powered:'false'}}")); var0.put("minecraft:" + var1 + "northlowerlefttruetrue", DataConverterFlattenData.b("{Name:'minecraft:" + var1 + "',Properties:{facing:'north',half:'lower',hinge:'left',open:'true',powered:'true'}}")); var0.put("minecraft:" + var1 + "northlowerrightfalsefalse", DataConverterFlattenData.b(var2 + 3)); var0.put("minecraft:" + var1 + "northlowerrightfalsetrue", DataConverterFlattenData.b("{Name:'minecraft:" + var1 + "',Properties:{facing:'north',half:'lower',hinge:'right',open:'false',powered:'true'}}")); var0.put("minecraft:" + var1 + "northlowerrighttruefalse", DataConverterFlattenData.b(var2 + 7)); var0.put("minecraft:" + var1 + "northlowerrighttruetrue", DataConverterFlattenData.b("{Name:'minecraft:" + var1 + "',Properties:{facing:'north',half:'lower',hinge:'right',open:'true',powered:'true'}}")); var0.put("minecraft:" + var1 + "northupperleftfalsefalse", DataConverterFlattenData.b("{Name:'minecraft:" + var1 + "',Properties:{facing:'north',half:'upper',hinge:'left',open:'false',powered:'false'}}")); var0.put("minecraft:" + var1 + "northupperleftfalsetrue", DataConverterFlattenData.b("{Name:'minecraft:" + var1 + "',Properties:{facing:'north',half:'upper',hinge:'left',open:'false',powered:'true'}}")); var0.put("minecraft:" + var1 + "northupperlefttruefalse", DataConverterFlattenData.b("{Name:'minecraft:" + var1 + "',Properties:{facing:'north',half:'upper',hinge:'left',open:'true',powered:'false'}}")); var0.put("minecraft:" + var1 + "northupperlefttruetrue", DataConverterFlattenData.b("{Name:'minecraft:" + var1 + "',Properties:{facing:'north',half:'upper',hinge:'left',open:'true',powered:'true'}}")); var0.put("minecraft:" + var1 + "northupperrightfalsefalse", DataConverterFlattenData.b("{Name:'minecraft:" + var1 + "',Properties:{facing:'north',half:'upper',hinge:'right',open:'false',powered:'false'}}")); var0.put("minecraft:" + var1 + "northupperrightfalsetrue", DataConverterFlattenData.b("{Name:'minecraft:" + var1 + "',Properties:{facing:'north',half:'upper',hinge:'right',open:'false',powered:'true'}}")); var0.put("minecraft:" + var1 + "northupperrighttruefalse", DataConverterFlattenData.b("{Name:'minecraft:" + var1 + "',Properties:{facing:'north',half:'upper',hinge:'right',open:'true',powered:'false'}}")); var0.put("minecraft:" + var1 + "northupperrighttruetrue", DataConverterFlattenData.b("{Name:'minecraft:" + var1 + "',Properties:{facing:'north',half:'upper',hinge:'right',open:'true',powered:'true'}}")); var0.put("minecraft:" + var1 + "southlowerleftfalsefalse", DataConverterFlattenData.b("{Name:'minecraft:" + var1 + "',Properties:{facing:'south',half:'lower',hinge:'left',open:'false',powered:'false'}}")); var0.put("minecraft:" + var1 + "southlowerleftfalsetrue", DataConverterFlattenData.b("{Name:'minecraft:" + var1 + "',Properties:{facing:'south',half:'lower',hinge:'left',open:'false',powered:'true'}}")); var0.put("minecraft:" + var1 + "southlowerlefttruefalse", DataConverterFlattenData.b("{Name:'minecraft:" + var1 + "',Properties:{facing:'south',half:'lower',hinge:'left',open:'true',powered:'false'}}")); var0.put("minecraft:" + var1 + "southlowerlefttruetrue", DataConverterFlattenData.b("{Name:'minecraft:" + var1 + "',Properties:{facing:'south',half:'lower',hinge:'left',open:'true',powered:'true'}}")); var0.put("minecraft:" + var1 + "southlowerrightfalsefalse", DataConverterFlattenData.b(var2 + 1)); var0.put("minecraft:" + var1 + "southlowerrightfalsetrue", DataConverterFlattenData.b("{Name:'minecraft:" + var1 + "',Properties:{facing:'south',half:'lower',hinge:'right',open:'false',powered:'true'}}")); var0.put("minecraft:" + var1 + "southlowerrighttruefalse", DataConverterFlattenData.b(var2 + 5)); var0.put("minecraft:" + var1 + "southlowerrighttruetrue", DataConverterFlattenData.b("{Name:'minecraft:" + var1 + "',Properties:{facing:'south',half:'lower',hinge:'right',open:'true',powered:'true'}}")); var0.put("minecraft:" + var1 + "southupperleftfalsefalse", DataConverterFlattenData.b("{Name:'minecraft:" + var1 + "',Properties:{facing:'south',half:'upper',hinge:'left',open:'false',powered:'false'}}")); var0.put("minecraft:" + var1 + "southupperleftfalsetrue", DataConverterFlattenData.b("{Name:'minecraft:" + var1 + "',Properties:{facing:'south',half:'upper',hinge:'left',open:'false',powered:'true'}}")); var0.put("minecraft:" + var1 + "southupperlefttruefalse", DataConverterFlattenData.b("{Name:'minecraft:" + var1 + "',Properties:{facing:'south',half:'upper',hinge:'left',open:'true',powered:'false'}}")); var0.put("minecraft:" + var1 + "southupperlefttruetrue", DataConverterFlattenData.b("{Name:'minecraft:" + var1 + "',Properties:{facing:'south',half:'upper',hinge:'left',open:'true',powered:'true'}}")); var0.put("minecraft:" + var1 + "southupperrightfalsefalse", DataConverterFlattenData.b("{Name:'minecraft:" + var1 + "',Properties:{facing:'south',half:'upper',hinge:'right',open:'false',powered:'false'}}")); var0.put("minecraft:" + var1 + "southupperrightfalsetrue", DataConverterFlattenData.b("{Name:'minecraft:" + var1 + "',Properties:{facing:'south',half:'upper',hinge:'right',open:'false',powered:'true'}}")); var0.put("minecraft:" + var1 + "southupperrighttruefalse", DataConverterFlattenData.b("{Name:'minecraft:" + var1 + "',Properties:{facing:'south',half:'upper',hinge:'right',open:'true',powered:'false'}}")); var0.put("minecraft:" + var1 + "southupperrighttruetrue", DataConverterFlattenData.b("{Name:'minecraft:" + var1 + "',Properties:{facing:'south',half:'upper',hinge:'right',open:'true',powered:'true'}}")); var0.put("minecraft:" + var1 + "westlowerleftfalsefalse", DataConverterFlattenData.b("{Name:'minecraft:" + var1 + "',Properties:{facing:'west',half:'lower',hinge:'left',open:'false',powered:'false'}}")); var0.put("minecraft:" + var1 + "westlowerleftfalsetrue", DataConverterFlattenData.b("{Name:'minecraft:" + var1 + "',Properties:{facing:'west',half:'lower',hinge:'left',open:'false',powered:'true'}}")); var0.put("minecraft:" + var1 + "westlowerlefttruefalse", DataConverterFlattenData.b("{Name:'minecraft:" + var1 + "',Properties:{facing:'west',half:'lower',hinge:'left',open:'true',powered:'false'}}")); var0.put("minecraft:" + var1 + "westlowerlefttruetrue", DataConverterFlattenData.b("{Name:'minecraft:" + var1 + "',Properties:{facing:'west',half:'lower',hinge:'left',open:'true',powered:'true'}}")); var0.put("minecraft:" + var1 + "westlowerrightfalsefalse", DataConverterFlattenData.b(var2 + 2)); var0.put("minecraft:" + var1 + "westlowerrightfalsetrue", DataConverterFlattenData.b("{Name:'minecraft:" + var1 + "',Properties:{facing:'west',half:'lower',hinge:'right',open:'false',powered:'true'}}")); var0.put("minecraft:" + var1 + "westlowerrighttruefalse", DataConverterFlattenData.b(var2 + 6)); var0.put("minecraft:" + var1 + "westlowerrighttruetrue", DataConverterFlattenData.b("{Name:'minecraft:" + var1 + "',Properties:{facing:'west',half:'lower',hinge:'right',open:'true',powered:'true'}}")); var0.put("minecraft:" + var1 + "westupperleftfalsefalse", DataConverterFlattenData.b("{Name:'minecraft:" + var1 + "',Properties:{facing:'west',half:'upper',hinge:'left',open:'false',powered:'false'}}")); var0.put("minecraft:" + var1 + "westupperleftfalsetrue", DataConverterFlattenData.b("{Name:'minecraft:" + var1 + "',Properties:{facing:'west',half:'upper',hinge:'left',open:'false',powered:'true'}}")); var0.put("minecraft:" + var1 + "westupperlefttruefalse", DataConverterFlattenData.b("{Name:'minecraft:" + var1 + "',Properties:{facing:'west',half:'upper',hinge:'left',open:'true',powered:'false'}}")); var0.put("minecraft:" + var1 + "westupperlefttruetrue", DataConverterFlattenData.b("{Name:'minecraft:" + var1 + "',Properties:{facing:'west',half:'upper',hinge:'left',open:'true',powered:'true'}}")); var0.put("minecraft:" + var1 + "westupperrightfalsefalse", DataConverterFlattenData.b("{Name:'minecraft:" + var1 + "',Properties:{facing:'west',half:'upper',hinge:'right',open:'false',powered:'false'}}")); var0.put("minecraft:" + var1 + "westupperrightfalsetrue", DataConverterFlattenData.b("{Name:'minecraft:" + var1 + "',Properties:{facing:'west',half:'upper',hinge:'right',open:'false',powered:'true'}}"));
/*     */     var0.put("minecraft:" + var1 + "westupperrighttruefalse", DataConverterFlattenData.b("{Name:'minecraft:" + var1 + "',Properties:{facing:'west',half:'upper',hinge:'right',open:'true',powered:'false'}}"));
/* 334 */     var0.put("minecraft:" + var1 + "westupperrighttruetrue", DataConverterFlattenData.b("{Name:'minecraft:" + var1 + "',Properties:{facing:'west',half:'upper',hinge:'right',open:'true',powered:'true'}}")); } private static final Dynamic<?> u = DataConverterFlattenData.b(0); private static void a(Map<String, Dynamic<?>> var0, int var1, String var2) { var0.put("southfalsefoot" + var1, DataConverterFlattenData.b("{Name:'minecraft:" + var2 + "_bed',Properties:{facing:'south',occupied:'false',part:'foot'}}")); var0.put("westfalsefoot" + var1, DataConverterFlattenData.b("{Name:'minecraft:" + var2 + "_bed',Properties:{facing:'west',occupied:'false',part:'foot'}}")); var0.put("northfalsefoot" + var1, DataConverterFlattenData.b("{Name:'minecraft:" + var2 + "_bed',Properties:{facing:'north',occupied:'false',part:'foot'}}")); var0.put("eastfalsefoot" + var1, DataConverterFlattenData.b("{Name:'minecraft:" + var2 + "_bed',Properties:{facing:'east',occupied:'false',part:'foot'}}")); var0.put("southfalsehead" + var1, DataConverterFlattenData.b("{Name:'minecraft:" + var2 + "_bed',Properties:{facing:'south',occupied:'false',part:'head'}}")); var0.put("westfalsehead" + var1, DataConverterFlattenData.b("{Name:'minecraft:" + var2 + "_bed',Properties:{facing:'west',occupied:'false',part:'head'}}")); var0.put("northfalsehead" + var1, DataConverterFlattenData.b("{Name:'minecraft:" + var2 + "_bed',Properties:{facing:'north',occupied:'false',part:'head'}}")); var0.put("eastfalsehead" + var1, DataConverterFlattenData.b("{Name:'minecraft:" + var2 + "_bed',Properties:{facing:'east',occupied:'false',part:'head'}}")); var0.put("southtruehead" + var1, DataConverterFlattenData.b("{Name:'minecraft:" + var2 + "_bed',Properties:{facing:'south',occupied:'true',part:'head'}}")); var0.put("westtruehead" + var1, DataConverterFlattenData.b("{Name:'minecraft:" + var2 + "_bed',Properties:{facing:'west',occupied:'true',part:'head'}}")); var0.put("northtruehead" + var1, DataConverterFlattenData.b("{Name:'minecraft:" + var2 + "_bed',Properties:{facing:'north',occupied:'true',part:'head'}}")); var0.put("easttruehead" + var1, DataConverterFlattenData.b("{Name:'minecraft:" + var2 + "_bed',Properties:{facing:'east',occupied:'true',part:'head'}}")); }
/*     */   private static void b(Map<String, Dynamic<?>> var0, int var1, String var2) { for (int var3 = 0; var3 < 16; var3++)
/*     */       var0.put("" + var3 + "_" + var1, DataConverterFlattenData.b("{Name:'minecraft:" + var2 + "_banner',Properties:{rotation:'" + var3 + "'}}"));  var0.put("north_" + var1, DataConverterFlattenData.b("{Name:'minecraft:" + var2 + "_wall_banner',Properties:{facing:'north'}}")); var0.put("south_" + var1, DataConverterFlattenData.b("{Name:'minecraft:" + var2 + "_wall_banner',Properties:{facing:'south'}}")); var0.put("west_" + var1, DataConverterFlattenData.b("{Name:'minecraft:" + var2 + "_wall_banner',Properties:{facing:'west'}}"));
/*     */     var0.put("east_" + var1, DataConverterFlattenData.b("{Name:'minecraft:" + var2 + "_wall_banner',Properties:{facing:'east'}}")); }
/* 338 */   public static String a(Dynamic<?> var0) { return var0.get("Name").asString(""); }
/*     */ 
/*     */   
/*     */   public static String a(Dynamic<?> var0, String var1) {
/* 342 */     return var0.get("Properties").get(var1).asString("");
/*     */   }
/*     */   
/*     */   public static int a(RegistryID<Dynamic<?>> var0, Dynamic<?> var1) {
/* 346 */     int var2 = var0.getId(var1);
/* 347 */     if (var2 == -1) {
/* 348 */       var2 = var0.c(var1);
/*     */     }
/* 350 */     return var2;
/*     */   }
/*     */   
/*     */   private Dynamic<?> b(Dynamic<?> var0) {
/* 354 */     Optional<? extends Dynamic<?>> var1 = var0.get("Level").result();
/* 355 */     if (var1.isPresent() && ((Dynamic)var1.get()).get("Sections").asStreamOpt().result().isPresent()) {
/* 356 */       return var0.set("Level", (new d(var1.get())).a());
/*     */     }
/* 358 */     return var0;
/*     */   }
/*     */ 
/*     */   
/*     */   public TypeRewriteRule makeRule() {
/* 363 */     Type<?> var0 = getInputSchema().getType(DataConverterTypes.CHUNK);
/* 364 */     Type<?> var1 = getOutputSchema().getType(DataConverterTypes.CHUNK);
/* 365 */     return writeFixAndRead("ChunkPalettedStorageFix", var0, var1, this::b);
/*     */   }
/*     */   
/*     */   static class c {
/* 369 */     private final RegistryID<Dynamic<?>> b = new RegistryID<>(32);
/*     */     
/*     */     private final List<Dynamic<?>> c;
/*     */     private final Dynamic<?> d;
/*     */     private final boolean e;
/* 374 */     private final Int2ObjectMap<IntList> f = (Int2ObjectMap<IntList>)new Int2ObjectLinkedOpenHashMap();
/*     */     
/* 376 */     private final IntList g = (IntList)new IntArrayList();
/*     */     public final int a;
/* 378 */     private final Set<Dynamic<?>> h = Sets.newIdentityHashSet();
/* 379 */     private final int[] i = new int[4096];
/*     */     
/*     */     public c(Dynamic<?> var0) {
/* 382 */       this.c = Lists.newArrayList();
/* 383 */       this.d = var0;
/* 384 */       this.a = var0.get("Y").asInt(0);
/* 385 */       this.e = var0.get("Blocks").result().isPresent();
/*     */     }
/*     */     
/*     */     public Dynamic<?> a(int var0) {
/* 389 */       if (var0 < 0 || var0 > 4095) {
/* 390 */         return ChunkConverterPalette.a();
/*     */       }
/*     */       
/* 393 */       Dynamic<?> var1 = this.b.fromId(this.i[var0]);
/* 394 */       return (var1 == null) ? ChunkConverterPalette.a() : var1;
/*     */     }
/*     */     
/*     */     public void a(int var0, Dynamic<?> var1) {
/* 398 */       if (this.h.add(var1)) {
/* 399 */         this.c.add("%%FILTER_ME%%".equals(ChunkConverterPalette.a(var1)) ? ChunkConverterPalette.a() : var1);
/*     */       }
/* 401 */       this.i[var0] = ChunkConverterPalette.a(this.b, var1);
/*     */     }
/*     */     
/*     */     public int b(int var0) {
/* 405 */       if (!this.e) {
/* 406 */         return var0;
/*     */       }
/* 408 */       ByteBuffer var1 = this.d.get("Blocks").asByteBufferOpt().result().get();
/* 409 */       ChunkConverterPalette.a var2 = this.d.get("Data").asByteBufferOpt().map(var0 -> new ChunkConverterPalette.a(DataFixUtils.toArray(var0))).result().orElseGet(a::new);
/* 410 */       ChunkConverterPalette.a var3 = this.d.get("Add").asByteBufferOpt().map(var0 -> new ChunkConverterPalette.a(DataFixUtils.toArray(var0))).result().orElseGet(a::new);
/*     */       
/* 412 */       this.h.add(ChunkConverterPalette.a());
/* 413 */       ChunkConverterPalette.a(this.b, ChunkConverterPalette.a());
/* 414 */       this.c.add(ChunkConverterPalette.a());
/*     */       
/* 416 */       for (int var4 = 0; var4 < 4096; var4++) {
/* 417 */         int var5 = var4 & 0xF;
/* 418 */         int var6 = var4 >> 8 & 0xF;
/* 419 */         int var7 = var4 >> 4 & 0xF;
/* 420 */         int var8 = var3.a(var5, var6, var7) << 12 | (var1.get(var4) & 0xFF) << 4 | var2.a(var5, var6, var7);
/*     */         
/* 422 */         if (ChunkConverterPalette.b().get(var8 >> 4)) {
/* 423 */           a(var8 >> 4, var4);
/*     */         }
/* 425 */         if (ChunkConverterPalette.c().get(var8 >> 4)) {
/*     */           
/* 427 */           int var9 = ChunkConverterPalette.a((var5 == 0), (var5 == 15), (var7 == 0), (var7 == 15));
/* 428 */           if (var9 == 0) {
/*     */             
/* 430 */             this.g.add(var4);
/*     */           } else {
/* 432 */             var0 |= var9;
/*     */           } 
/*     */         } 
/*     */         
/* 436 */         a(var4, DataConverterFlattenData.b(var8));
/*     */       } 
/*     */       
/* 439 */       return var0;
/*     */     }
/*     */     private void a(int var0, int var1) {
/*     */       IntArrayList intArrayList;
/* 443 */       IntList var2 = (IntList)this.f.get(var0);
/* 444 */       if (var2 == null) {
/* 445 */         intArrayList = new IntArrayList();
/* 446 */         this.f.put(var0, intArrayList);
/*     */       } 
/* 448 */       intArrayList.add(var1);
/*     */     }
/*     */     
/*     */     public Dynamic<?> a() {
/* 452 */       Dynamic<?> var0 = this.d;
/* 453 */       if (!this.e) {
/* 454 */         return var0;
/*     */       }
/* 456 */       var0 = var0.set("Palette", var0.createList(this.c.stream()));
/*     */       
/* 458 */       int var1 = Math.max(4, DataFixUtils.ceillog2(this.h.size()));
/* 459 */       DataBitsPacked var2 = new DataBitsPacked(var1, 4096);
/* 460 */       for (int var3 = 0; var3 < this.i.length; var3++) {
/* 461 */         var2.a(var3, this.i[var3]);
/*     */       }
/*     */       
/* 464 */       var0 = var0.set("BlockStates", var0.createLongList(Arrays.stream(var2.a())));
/*     */       
/* 466 */       var0 = var0.remove("Blocks");
/* 467 */       var0 = var0.remove("Data");
/* 468 */       var0 = var0.remove("Add");
/*     */       
/* 470 */       return var0;
/*     */     }
/*     */   }
/*     */   
/*     */   static final class d
/*     */   {
/*     */     private int a;
/* 477 */     private final ChunkConverterPalette.c[] b = new ChunkConverterPalette.c[16];
/*     */     
/*     */     private final Dynamic<?> c;
/*     */     private final int d;
/*     */     private final int e;
/* 482 */     private final Int2ObjectMap<Dynamic<?>> f = (Int2ObjectMap<Dynamic<?>>)new Int2ObjectLinkedOpenHashMap(16);
/*     */     
/*     */     public d(Dynamic<?> var0) {
/* 485 */       this.c = var0;
/* 486 */       this.d = var0.get("xPos").asInt(0) << 4;
/* 487 */       this.e = var0.get("zPos").asInt(0) << 4;
/*     */       
/* 489 */       var0.get("TileEntities").asStreamOpt().result().ifPresent(var0 -> var0.forEach(()));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 502 */       boolean var1 = var0.get("convertedFromAlphaFormat").asBoolean(false);
/*     */       
/* 504 */       var0.get("Sections").asStreamOpt().result().ifPresent(var0 -> var0.forEach(()));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 512 */       for (ChunkConverterPalette.c var5 : this.b) {
/* 513 */         if (var5 != null)
/*     */         {
/*     */ 
/*     */           
/* 517 */           for (ObjectIterator<Map.Entry<Integer, IntList>> objectIterator = ChunkConverterPalette.c.a(var5).entrySet().iterator(); objectIterator.hasNext(); ) { IntListIterator<Integer> intListIterator; Map.Entry<Integer, IntList> var7 = objectIterator.next();
/* 518 */             int var8 = var5.a << 12;
/* 519 */             switch (((Integer)var7.getKey()).intValue()) {
/*     */               case 2:
/* 521 */                 for (intListIterator = ((IntList)var7.getValue()).iterator(); intListIterator.hasNext(); ) { int var10 = ((Integer)intListIterator.next()).intValue();
/* 522 */                   var10 |= var8;
/*     */                   
/* 524 */                   Dynamic<?> var11 = a(var10);
/* 525 */                   if ("minecraft:grass_block".equals(ChunkConverterPalette.a(var11))) {
/* 526 */                     String var12 = ChunkConverterPalette.a(a(a(var10, ChunkConverterPalette.Direction.UP)));
/* 527 */                     if ("minecraft:snow".equals(var12) || "minecraft:snow_layer".equals(var12)) {
/* 528 */                       a(var10, ChunkConverterPalette.d());
/*     */                     }
/*     */                   }  }
/*     */               
/*     */ 
/*     */               
/*     */               case 3:
/* 535 */                 for (intListIterator = ((IntList)var7.getValue()).iterator(); intListIterator.hasNext(); ) { int var10 = ((Integer)intListIterator.next()).intValue();
/* 536 */                   var10 |= var8;
/*     */                   
/* 538 */                   Dynamic<?> var11 = a(var10);
/* 539 */                   if ("minecraft:podzol".equals(ChunkConverterPalette.a(var11))) {
/* 540 */                     String var12 = ChunkConverterPalette.a(a(a(var10, ChunkConverterPalette.Direction.UP)));
/* 541 */                     if ("minecraft:snow".equals(var12) || "minecraft:snow_layer".equals(var12)) {
/* 542 */                       a(var10, ChunkConverterPalette.e());
/*     */                     }
/*     */                   }  }
/*     */               
/*     */ 
/*     */               
/*     */               case 110:
/* 549 */                 for (intListIterator = ((IntList)var7.getValue()).iterator(); intListIterator.hasNext(); ) { int var10 = ((Integer)intListIterator.next()).intValue();
/* 550 */                   var10 |= var8;
/*     */                   
/* 552 */                   Dynamic<?> var11 = a(var10);
/* 553 */                   if ("minecraft:mycelium".equals(ChunkConverterPalette.a(var11))) {
/* 554 */                     String var12 = ChunkConverterPalette.a(a(a(var10, ChunkConverterPalette.Direction.UP)));
/* 555 */                     if ("minecraft:snow".equals(var12) || "minecraft:snow_layer".equals(var12)) {
/* 556 */                       a(var10, ChunkConverterPalette.f());
/*     */                     }
/*     */                   }  }
/*     */               
/*     */ 
/*     */               
/*     */               case 25:
/* 563 */                 for (intListIterator = ((IntList)var7.getValue()).iterator(); intListIterator.hasNext(); ) { int var10 = ((Integer)intListIterator.next()).intValue();
/* 564 */                   var10 |= var8;
/* 565 */                   Dynamic<?> var11 = c(var10);
/* 566 */                   if (var11 != null) {
/* 567 */                     String var12 = Boolean.toString(var11.get("powered").asBoolean(false)) + (byte)Math.min(Math.max(var11.get("note").asInt(0), 0), 24);
/* 568 */                     a(var10, (Dynamic)ChunkConverterPalette.g().getOrDefault(var12, ChunkConverterPalette.g().get("false0")));
/*     */                   }  }
/*     */               
/*     */ 
/*     */               
/*     */               case 26:
/* 574 */                 for (intListIterator = ((IntList)var7.getValue()).iterator(); intListIterator.hasNext(); ) { int var10 = ((Integer)intListIterator.next()).intValue();
/* 575 */                   var10 |= var8;
/* 576 */                   Dynamic<?> var11 = b(var10);
/* 577 */                   Dynamic<?> var12 = a(var10);
/* 578 */                   if (var11 != null) {
/* 579 */                     int var13 = var11.get("color").asInt(0);
/* 580 */                     if (var13 != 14 && var13 >= 0 && var13 < 16) {
/* 581 */                       String var14 = ChunkConverterPalette.a(var12, "facing") + ChunkConverterPalette.a(var12, "occupied") + ChunkConverterPalette.a(var12, "part") + var13;
/* 582 */                       if (ChunkConverterPalette.h().containsKey(var14)) {
/* 583 */                         a(var10, (Dynamic)ChunkConverterPalette.h().get(var14));
/*     */                       }
/*     */                     } 
/*     */                   }  }
/*     */               
/*     */ 
/*     */               
/*     */               case 176:
/*     */               case 177:
/* 592 */                 for (intListIterator = ((IntList)var7.getValue()).iterator(); intListIterator.hasNext(); ) { int var10 = ((Integer)intListIterator.next()).intValue();
/* 593 */                   var10 |= var8;
/* 594 */                   Dynamic<?> var11 = b(var10);
/* 595 */                   Dynamic<?> var12 = a(var10);
/* 596 */                   if (var11 != null) {
/* 597 */                     int var13 = var11.get("Base").asInt(0);
/* 598 */                     if (var13 != 15 && var13 >= 0 && var13 < 16) {
/* 599 */                       String var14 = ChunkConverterPalette.a(var12, (((Integer)var7.getKey()).intValue() == 176) ? "rotation" : "facing") + "_" + var13;
/* 600 */                       if (ChunkConverterPalette.i().containsKey(var14)) {
/* 601 */                         a(var10, (Dynamic)ChunkConverterPalette.i().get(var14));
/*     */                       }
/*     */                     } 
/*     */                   }  }
/*     */               
/*     */ 
/*     */               
/*     */               case 86:
/* 609 */                 for (intListIterator = ((IntList)var7.getValue()).iterator(); intListIterator.hasNext(); ) { int var10 = ((Integer)intListIterator.next()).intValue();
/* 610 */                   var10 |= var8;
/*     */                   
/* 612 */                   Dynamic<?> var11 = a(var10);
/* 613 */                   if ("minecraft:carved_pumpkin".equals(ChunkConverterPalette.a(var11))) {
/* 614 */                     String var12 = ChunkConverterPalette.a(a(a(var10, ChunkConverterPalette.Direction.DOWN)));
/* 615 */                     if ("minecraft:grass_block".equals(var12) || "minecraft:dirt".equals(var12)) {
/* 616 */                       a(var10, ChunkConverterPalette.j());
/*     */                     }
/*     */                   }  }
/*     */               
/*     */ 
/*     */               
/*     */               case 140:
/* 623 */                 for (intListIterator = ((IntList)var7.getValue()).iterator(); intListIterator.hasNext(); ) { int var10 = ((Integer)intListIterator.next()).intValue();
/* 624 */                   var10 |= var8;
/* 625 */                   Dynamic<?> var11 = c(var10);
/* 626 */                   if (var11 != null) {
/* 627 */                     String var12 = var11.get("Item").asString("") + var11.get("Data").asInt(0);
/* 628 */                     a(var10, (Dynamic)ChunkConverterPalette.k().getOrDefault(var12, ChunkConverterPalette.k().get("minecraft:air0")));
/*     */                   }  }
/*     */               
/*     */ 
/*     */               
/*     */               case 144:
/* 634 */                 for (intListIterator = ((IntList)var7.getValue()).iterator(); intListIterator.hasNext(); ) { int var10 = ((Integer)intListIterator.next()).intValue();
/* 635 */                   var10 |= var8;
/* 636 */                   Dynamic<?> var11 = b(var10);
/* 637 */                   if (var11 != null) {
/* 638 */                     String var14, var12 = String.valueOf(var11.get("SkullType").asInt(0));
/* 639 */                     String var13 = ChunkConverterPalette.a(a(var10), "facing");
/*     */                     
/* 641 */                     if ("up".equals(var13) || "down".equals(var13)) {
/* 642 */                       var14 = var12 + String.valueOf(var11.get("Rot").asInt(0));
/*     */                     } else {
/* 644 */                       var14 = var12 + var13;
/*     */                     } 
/*     */                     
/* 647 */                     var11.remove("SkullType");
/* 648 */                     var11.remove("facing");
/* 649 */                     var11.remove("Rot");
/*     */                     
/* 651 */                     a(var10, (Dynamic)ChunkConverterPalette.l().getOrDefault(var14, ChunkConverterPalette.l().get("0north")));
/*     */                   }  }
/*     */               
/*     */               
/*     */               case 64:
/*     */               case 71:
/*     */               case 193:
/*     */               case 194:
/*     */               case 195:
/*     */               case 196:
/*     */               case 197:
/* 662 */                 for (intListIterator = ((IntList)var7.getValue()).iterator(); intListIterator.hasNext(); ) { int var10 = ((Integer)intListIterator.next()).intValue();
/* 663 */                   var10 |= var8;
/*     */                   
/* 665 */                   Dynamic<?> var11 = a(var10);
/* 666 */                   if (ChunkConverterPalette.a(var11).endsWith("_door")) {
/* 667 */                     Dynamic<?> var12 = a(var10);
/* 668 */                     if ("lower".equals(ChunkConverterPalette.a(var12, "half"))) {
/* 669 */                       int var13 = a(var10, ChunkConverterPalette.Direction.UP);
/* 670 */                       Dynamic<?> var14 = a(var13);
/* 671 */                       String var15 = ChunkConverterPalette.a(var12);
/* 672 */                       if (var15.equals(ChunkConverterPalette.a(var14))) {
/* 673 */                         String var16 = ChunkConverterPalette.a(var12, "facing");
/* 674 */                         String var17 = ChunkConverterPalette.a(var12, "open");
/* 675 */                         String var18 = var1 ? "left" : ChunkConverterPalette.a(var14, "hinge");
/* 676 */                         String var19 = var1 ? "false" : ChunkConverterPalette.a(var14, "powered");
/* 677 */                         a(var10, (Dynamic)ChunkConverterPalette.m().get(var15 + var16 + "lower" + var18 + var17 + var19));
/* 678 */                         a(var13, (Dynamic)ChunkConverterPalette.m().get(var15 + var16 + "upper" + var18 + var17 + var19));
/*     */                       } 
/*     */                     } 
/*     */                   }  }
/*     */               
/*     */ 
/*     */               
/*     */               case 175:
/* 686 */                 for (intListIterator = ((IntList)var7.getValue()).iterator(); intListIterator.hasNext(); ) { int var10 = ((Integer)intListIterator.next()).intValue();
/* 687 */                   var10 |= var8;
/*     */                   
/* 689 */                   Dynamic<?> var11 = a(var10);
/* 690 */                   if ("upper".equals(ChunkConverterPalette.a(var11, "half"))) {
/* 691 */                     Dynamic<?> var12 = a(a(var10, ChunkConverterPalette.Direction.DOWN));
/* 692 */                     String var13 = ChunkConverterPalette.a(var12);
/* 693 */                     if ("minecraft:sunflower".equals(var13)) {
/* 694 */                       a(var10, ChunkConverterPalette.n()); continue;
/* 695 */                     }  if ("minecraft:lilac".equals(var13)) {
/* 696 */                       a(var10, ChunkConverterPalette.o()); continue;
/* 697 */                     }  if ("minecraft:tall_grass".equals(var13)) {
/* 698 */                       a(var10, ChunkConverterPalette.p()); continue;
/* 699 */                     }  if ("minecraft:large_fern".equals(var13)) {
/* 700 */                       a(var10, ChunkConverterPalette.q()); continue;
/* 701 */                     }  if ("minecraft:rose_bush".equals(var13)) {
/* 702 */                       a(var10, ChunkConverterPalette.r()); continue;
/* 703 */                     }  if ("minecraft:peony".equals(var13)) {
/* 704 */                       a(var10, ChunkConverterPalette.s());
/*     */                     }
/*     */                   }  }
/*     */               
/*     */             }  }
/*     */         
/*     */         }
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     @Nullable
/*     */     private Dynamic<?> b(int var0) {
/* 717 */       return (Dynamic)this.f.get(var0);
/*     */     }
/*     */     
/*     */     @Nullable
/*     */     private Dynamic<?> c(int var0) {
/* 722 */       return (Dynamic)this.f.remove(var0); } public static int a(int var0, ChunkConverterPalette.Direction var1) {
/*     */       int var2;
/*     */       int var3;
/*     */       int var4;
/* 726 */       switch (ChunkConverterPalette.null.a[var1.b().ordinal()]) {
/*     */         case 1:
/* 728 */           var2 = (var0 & 0xF) + var1.a().a();
/* 729 */           return (var2 < 0 || var2 > 15) ? -1 : (var0 & 0xFFFFFFF0 | var2);
/*     */         case 2:
/* 731 */           var3 = (var0 >> 8) + var1.a().a();
/* 732 */           return (var3 < 0 || var3 > 255) ? -1 : (var0 & 0xFF | var3 << 8);
/*     */         case 3:
/* 734 */           var4 = (var0 >> 4 & 0xF) + var1.a().a();
/* 735 */           return (var4 < 0 || var4 > 15) ? -1 : (var0 & 0xFFFFFF0F | var4 << 4);
/*     */       } 
/* 737 */       return -1;
/*     */     }
/*     */     
/*     */     private void a(int var0, Dynamic<?> var1) {
/* 741 */       if (var0 < 0 || var0 > 65535) {
/*     */         return;
/*     */       }
/*     */       
/* 745 */       ChunkConverterPalette.c var2 = d(var0);
/*     */       
/* 747 */       if (var2 == null) {
/*     */         return;
/*     */       }
/*     */       
/* 751 */       var2.a(var0 & 0xFFF, var1);
/*     */     }
/*     */     
/*     */     @Nullable
/*     */     private ChunkConverterPalette.c d(int var0) {
/* 756 */       int var1 = var0 >> 12;
/* 757 */       return (var1 < this.b.length) ? this.b[var1] : null;
/*     */     }
/*     */     
/*     */     public Dynamic<?> a(int var0) {
/* 761 */       if (var0 < 0 || var0 > 65535) {
/* 762 */         return ChunkConverterPalette.a();
/*     */       }
/*     */       
/* 765 */       ChunkConverterPalette.c var1 = d(var0);
/*     */       
/* 767 */       if (var1 == null) {
/* 768 */         return ChunkConverterPalette.a();
/*     */       }
/*     */       
/* 771 */       return var1.a(var0 & 0xFFF);
/*     */     }
/*     */     
/*     */     public Dynamic<?> a() {
/* 775 */       Dynamic<?> var0 = this.c;
/* 776 */       if (this.f.isEmpty()) {
/* 777 */         var0 = var0.remove("TileEntities");
/*     */       } else {
/* 779 */         var0 = var0.set("TileEntities", var0.createList(this.f.values().stream()));
/*     */       } 
/*     */       
/* 782 */       Dynamic<?> var1 = var0.emptyMap();
/* 783 */       List<Dynamic<?>> var2 = Lists.newArrayList();
/* 784 */       for (ChunkConverterPalette.c var6 : this.b) {
/* 785 */         if (var6 != null) {
/* 786 */           var2.add(var6.a());
/* 787 */           var1 = var1.set(String.valueOf(var6.a), var1.createIntList(Arrays.stream(ChunkConverterPalette.c.b(var6).toIntArray())));
/*     */         } 
/*     */       } 
/*     */       
/* 791 */       Dynamic<?> var3 = var0.emptyMap();
/* 792 */       var3 = var3.set("Sides", var3.createByte((byte)this.a));
/* 793 */       var3 = var3.set("Indices", var1);
/* 794 */       return var0.set("UpgradeData", var3).set("Sections", var3.createList(var2.stream()));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   static class a
/*     */   {
/*     */     private final byte[] a;
/*     */ 
/*     */     
/*     */     public a() {
/* 805 */       this.a = new byte[2048];
/*     */     }
/*     */     
/*     */     public a(byte[] var0) {
/* 809 */       this.a = var0;
/*     */       
/* 811 */       if (var0.length != 2048) {
/* 812 */         throw new IllegalArgumentException("ChunkNibbleArrays should be 2048 bytes not: " + var0.length);
/*     */       }
/*     */     }
/*     */     
/*     */     public int a(int var0, int var1, int var2) {
/* 817 */       int var3 = b(var1 << 8 | var2 << 4 | var0);
/*     */       
/* 819 */       if (a(var1 << 8 | var2 << 4 | var0)) {
/* 820 */         return this.a[var3] & 0xF;
/*     */       }
/* 822 */       return this.a[var3] >> 4 & 0xF;
/*     */     }
/*     */ 
/*     */     
/*     */     private boolean a(int var0) {
/* 827 */       return ((var0 & 0x1) == 0);
/*     */     }
/*     */     
/*     */     private int b(int var0) {
/* 831 */       return var0 >> 1;
/*     */     }
/*     */   }
/*     */   
/*     */   public static int a(boolean var0, boolean var1, boolean var2, boolean var3) {
/* 836 */     int var4 = 0;
/* 837 */     if (var2) {
/* 838 */       if (var1) {
/* 839 */         var4 |= 0x2;
/* 840 */       } else if (var0) {
/* 841 */         var4 |= 0x80;
/*     */       } else {
/* 843 */         var4 |= 0x1;
/*     */       } 
/* 845 */     } else if (var3) {
/* 846 */       if (var0) {
/* 847 */         var4 |= 0x20;
/* 848 */       } else if (var1) {
/* 849 */         var4 |= 0x8;
/*     */       } else {
/* 851 */         var4 |= 0x10;
/*     */       } 
/* 853 */     } else if (var1) {
/* 854 */       var4 |= 0x4;
/* 855 */     } else if (var0) {
/* 856 */       var4 |= 0x40;
/*     */     } 
/* 858 */     return var4;
/*     */   }
/*     */   
/*     */   public enum Direction {
/* 862 */     DOWN((String)AxisDirection.NEGATIVE, Axis.Y),
/* 863 */     UP((String)AxisDirection.POSITIVE, Axis.Y),
/* 864 */     NORTH((String)AxisDirection.NEGATIVE, Axis.Z),
/* 865 */     SOUTH((String)AxisDirection.POSITIVE, Axis.Z),
/* 866 */     WEST((String)AxisDirection.NEGATIVE, Axis.X),
/* 867 */     EAST((String)AxisDirection.POSITIVE, Axis.X);
/*     */     
/*     */     private final Axis g;
/*     */     
/*     */     private final AxisDirection h;
/*     */     
/*     */     Direction(AxisDirection var2, Axis var3) {
/* 874 */       this.g = var3;
/* 875 */       this.h = var2;
/*     */     }
/*     */     
/*     */     public AxisDirection a() {
/* 879 */       return this.h;
/*     */     }
/*     */     
/*     */     public Axis b() {
/* 883 */       return this.g;
/*     */     }
/*     */     
/*     */     public enum Axis {
/* 887 */       X,
/* 888 */       Y,
/* 889 */       Z;
/*     */     }
/*     */     
/*     */     public enum AxisDirection {
/* 893 */       POSITIVE(1),
/* 894 */       NEGATIVE(-1);
/*     */       
/*     */       private final int c;
/*     */ 
/*     */       
/*     */       AxisDirection(int var2) {
/* 900 */         this.c = var2;
/*     */       }
/*     */       
/*     */       public int a() {
/* 904 */         return this.c;
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ChunkConverterPalette.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
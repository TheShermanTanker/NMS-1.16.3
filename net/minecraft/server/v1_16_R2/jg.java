/*      */ package net.minecraft.server.v1_16_R2;
/*      */ 
/*      */ import com.google.common.collect.Sets;
/*      */ import com.google.gson.Gson;
/*      */ import com.google.gson.GsonBuilder;
/*      */ import com.google.gson.JsonElement;
/*      */ import com.google.gson.JsonObject;
/*      */ import java.io.BufferedWriter;
/*      */ import java.io.IOException;
/*      */ import java.nio.file.Files;
/*      */ import java.nio.file.Path;
/*      */ import java.nio.file.attribute.FileAttribute;
/*      */ import java.util.Objects;
/*      */ import java.util.Set;
/*      */ import java.util.function.Consumer;
/*      */ import org.apache.logging.log4j.LogManager;
/*      */ import org.apache.logging.log4j.Logger;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class jg
/*      */   implements DebugReportProvider
/*      */ {
/*   53 */   private static final Logger LOGGER = LogManager.getLogger();
/*   54 */   private static final Gson c = (new GsonBuilder()).setPrettyPrinting().create();
/*      */   
/*      */   private final DebugReportGenerator d;
/*      */   
/*      */   public jg(DebugReportGenerator var0) {
/*   59 */     this.d = var0;
/*      */   }
/*      */ 
/*      */   
/*      */   public void a(HashCache var0) throws IOException {
/*   64 */     Path var1 = this.d.b();
/*   65 */     Set<MinecraftKey> var2 = Sets.newHashSet();
/*   66 */     a(var3 -> {
/*      */           if (!var0.add(var3.b())) {
/*      */             throw new IllegalStateException("Duplicate recipe " + var3.b());
/*      */           }
/*      */           
/*      */           a(var1, var3.a(), var2.resolve("data/" + var3.b().getNamespace() + "/recipes/" + var3.b().getKey() + ".json"));
/*      */           JsonObject var4 = var3.d();
/*      */           if (var4 != null) {
/*      */             b(var1, var4, var2.resolve("data/" + var3.b().getNamespace() + "/advancements/" + var3.e().getKey() + ".json"));
/*      */           }
/*      */         });
/*   77 */     b(var0, Advancement.SerializedAdvancement.a().a("impossible", new CriterionTriggerImpossible.a()).b(), var1.resolve("data/minecraft/advancements/recipes/root.json"));
/*      */   }
/*      */   
/*      */   private static void a(HashCache var0, JsonObject var1, Path var2) {
/*      */     try {
/*   82 */       String var3 = c.toJson((JsonElement)var1);
/*   83 */       String var4 = a.hashUnencodedChars(var3).toString();
/*      */       
/*   85 */       if (!Objects.equals(var0.a(var2), var4) || !Files.exists(var2, new java.nio.file.LinkOption[0])) {
/*   86 */         Files.createDirectories(var2.getParent(), (FileAttribute<?>[])new FileAttribute[0]);
/*   87 */         try (BufferedWriter var5 = Files.newBufferedWriter(var2, new java.nio.file.OpenOption[0])) {
/*   88 */           var5.write(var3);
/*      */         } 
/*      */       } 
/*   91 */       var0.a(var2, var4);
/*   92 */     } catch (IOException var3) {
/*   93 */       LOGGER.error("Couldn't save recipe {}", var2, var3);
/*      */     } 
/*      */   }
/*      */   
/*      */   private static void b(HashCache var0, JsonObject var1, Path var2) {
/*      */     try {
/*   99 */       String var3 = c.toJson((JsonElement)var1);
/*  100 */       String var4 = a.hashUnencodedChars(var3).toString();
/*      */       
/*  102 */       if (!Objects.equals(var0.a(var2), var4) || !Files.exists(var2, new java.nio.file.LinkOption[0])) {
/*  103 */         Files.createDirectories(var2.getParent(), (FileAttribute<?>[])new FileAttribute[0]);
/*  104 */         try (BufferedWriter var5 = Files.newBufferedWriter(var2, new java.nio.file.OpenOption[0])) {
/*  105 */           var5.write(var3);
/*      */         } 
/*      */       } 
/*  108 */       var0.a(var2, var4);
/*  109 */     } catch (IOException var3) {
/*  110 */       LOGGER.error("Couldn't save recipe advancement {}", var2, var3);
/*      */     } 
/*      */   }
/*      */   
/*      */   private static void a(Consumer<jf> var0) {
/*  115 */     a(var0, Blocks.ACACIA_PLANKS, TagsItem.ACACIA_LOGS);
/*  116 */     b(var0, Blocks.BIRCH_PLANKS, TagsItem.BIRCH_LOGS);
/*  117 */     b(var0, Blocks.CRIMSON_PLANKS, TagsItem.CRIMSON_STEMS);
/*  118 */     a(var0, Blocks.DARK_OAK_PLANKS, TagsItem.DARK_OAK_LOGS);
/*  119 */     b(var0, Blocks.JUNGLE_PLANKS, TagsItem.JUNGLE_LOGS);
/*  120 */     b(var0, Blocks.OAK_PLANKS, TagsItem.OAK_LOGS);
/*  121 */     b(var0, Blocks.SPRUCE_PLANKS, TagsItem.SPRUCE_LOGS);
/*  122 */     b(var0, Blocks.WARPED_PLANKS, TagsItem.WARPED_STEMS);
/*      */     
/*  124 */     a(var0, Blocks.ACACIA_WOOD, Blocks.ACACIA_LOG);
/*  125 */     a(var0, Blocks.BIRCH_WOOD, Blocks.BIRCH_LOG);
/*  126 */     a(var0, Blocks.DARK_OAK_WOOD, Blocks.DARK_OAK_LOG);
/*  127 */     a(var0, Blocks.JUNGLE_WOOD, Blocks.JUNGLE_LOG);
/*  128 */     a(var0, Blocks.OAK_WOOD, Blocks.OAK_LOG);
/*  129 */     a(var0, Blocks.SPRUCE_WOOD, Blocks.SPRUCE_LOG);
/*  130 */     a(var0, Blocks.CRIMSON_HYPHAE, Blocks.CRIMSON_STEM);
/*  131 */     a(var0, Blocks.WARPED_HYPHAE, Blocks.WARPED_STEM);
/*      */     
/*  133 */     a(var0, Blocks.STRIPPED_ACACIA_WOOD, Blocks.STRIPPED_ACACIA_LOG);
/*  134 */     a(var0, Blocks.STRIPPED_BIRCH_WOOD, Blocks.STRIPPED_BIRCH_LOG);
/*  135 */     a(var0, Blocks.STRIPPED_DARK_OAK_WOOD, Blocks.STRIPPED_DARK_OAK_LOG);
/*  136 */     a(var0, Blocks.STRIPPED_JUNGLE_WOOD, Blocks.STRIPPED_JUNGLE_LOG);
/*  137 */     a(var0, Blocks.STRIPPED_OAK_WOOD, Blocks.STRIPPED_OAK_LOG);
/*  138 */     a(var0, Blocks.STRIPPED_SPRUCE_WOOD, Blocks.STRIPPED_SPRUCE_LOG);
/*  139 */     a(var0, Blocks.STRIPPED_CRIMSON_HYPHAE, Blocks.STRIPPED_CRIMSON_STEM);
/*  140 */     a(var0, Blocks.STRIPPED_WARPED_HYPHAE, Blocks.STRIPPED_WARPED_STEM);
/*      */     
/*  142 */     b(var0, Items.ACACIA_BOAT, Blocks.ACACIA_PLANKS);
/*  143 */     b(var0, Items.BIRCH_BOAT, Blocks.BIRCH_PLANKS);
/*  144 */     b(var0, Items.DARK_OAK_BOAT, Blocks.DARK_OAK_PLANKS);
/*  145 */     b(var0, Items.JUNGLE_BOAT, Blocks.JUNGLE_PLANKS);
/*  146 */     b(var0, Items.OAK_BOAT, Blocks.OAK_PLANKS);
/*  147 */     b(var0, Items.SPRUCE_BOAT, Blocks.SPRUCE_PLANKS);
/*      */     
/*  149 */     c(var0, Blocks.ACACIA_BUTTON, Blocks.ACACIA_PLANKS);
/*  150 */     d(var0, Blocks.ACACIA_DOOR, Blocks.ACACIA_PLANKS);
/*  151 */     e(var0, Blocks.ACACIA_FENCE, Blocks.ACACIA_PLANKS);
/*  152 */     f(var0, Blocks.ACACIA_FENCE_GATE, Blocks.ACACIA_PLANKS);
/*  153 */     g(var0, Blocks.ACACIA_PRESSURE_PLATE, Blocks.ACACIA_PLANKS);
/*  154 */     h(var0, Blocks.ACACIA_SLAB, Blocks.ACACIA_PLANKS);
/*  155 */     i(var0, Blocks.ACACIA_STAIRS, Blocks.ACACIA_PLANKS);
/*  156 */     j(var0, Blocks.ACACIA_TRAPDOOR, Blocks.ACACIA_PLANKS);
/*  157 */     k(var0, Blocks.ACACIA_SIGN, Blocks.ACACIA_PLANKS);
/*      */     
/*  159 */     c(var0, Blocks.BIRCH_BUTTON, Blocks.BIRCH_PLANKS);
/*  160 */     d(var0, Blocks.BIRCH_DOOR, Blocks.BIRCH_PLANKS);
/*  161 */     e(var0, Blocks.BIRCH_FENCE, Blocks.BIRCH_PLANKS);
/*  162 */     f(var0, Blocks.BIRCH_FENCE_GATE, Blocks.BIRCH_PLANKS);
/*  163 */     g(var0, Blocks.BIRCH_PRESSURE_PLATE, Blocks.BIRCH_PLANKS);
/*  164 */     h(var0, Blocks.BIRCH_SLAB, Blocks.BIRCH_PLANKS);
/*  165 */     i(var0, Blocks.BIRCH_STAIRS, Blocks.BIRCH_PLANKS);
/*  166 */     j(var0, Blocks.BIRCH_TRAPDOOR, Blocks.BIRCH_PLANKS);
/*  167 */     k(var0, Blocks.BIRCH_SIGN, Blocks.BIRCH_PLANKS);
/*      */     
/*  169 */     c(var0, Blocks.CRIMSON_BUTTON, Blocks.CRIMSON_PLANKS);
/*  170 */     d(var0, Blocks.CRIMSON_DOOR, Blocks.CRIMSON_PLANKS);
/*  171 */     e(var0, Blocks.CRIMSON_FENCE, Blocks.CRIMSON_PLANKS);
/*  172 */     f(var0, Blocks.CRIMSON_FENCE_GATE, Blocks.CRIMSON_PLANKS);
/*  173 */     g(var0, Blocks.CRIMSON_PRESSURE_PLATE, Blocks.CRIMSON_PLANKS);
/*  174 */     h(var0, Blocks.CRIMSON_SLAB, Blocks.CRIMSON_PLANKS);
/*  175 */     i(var0, Blocks.CRIMSON_STAIRS, Blocks.CRIMSON_PLANKS);
/*  176 */     j(var0, Blocks.CRIMSON_TRAPDOOR, Blocks.CRIMSON_PLANKS);
/*  177 */     k(var0, Blocks.CRIMSON_SIGN, Blocks.CRIMSON_PLANKS);
/*      */     
/*  179 */     c(var0, Blocks.DARK_OAK_BUTTON, Blocks.DARK_OAK_PLANKS);
/*  180 */     d(var0, Blocks.DARK_OAK_DOOR, Blocks.DARK_OAK_PLANKS);
/*  181 */     e(var0, Blocks.DARK_OAK_FENCE, Blocks.DARK_OAK_PLANKS);
/*  182 */     f(var0, Blocks.DARK_OAK_FENCE_GATE, Blocks.DARK_OAK_PLANKS);
/*  183 */     g(var0, Blocks.DARK_OAK_PRESSURE_PLATE, Blocks.DARK_OAK_PLANKS);
/*  184 */     h(var0, Blocks.DARK_OAK_SLAB, Blocks.DARK_OAK_PLANKS);
/*  185 */     i(var0, Blocks.DARK_OAK_STAIRS, Blocks.DARK_OAK_PLANKS);
/*  186 */     j(var0, Blocks.DARK_OAK_TRAPDOOR, Blocks.DARK_OAK_PLANKS);
/*  187 */     k(var0, Blocks.DARK_OAK_SIGN, Blocks.DARK_OAK_PLANKS);
/*      */     
/*  189 */     c(var0, Blocks.JUNGLE_BUTTON, Blocks.JUNGLE_PLANKS);
/*  190 */     d(var0, Blocks.JUNGLE_DOOR, Blocks.JUNGLE_PLANKS);
/*  191 */     e(var0, Blocks.JUNGLE_FENCE, Blocks.JUNGLE_PLANKS);
/*  192 */     f(var0, Blocks.JUNGLE_FENCE_GATE, Blocks.JUNGLE_PLANKS);
/*  193 */     g(var0, Blocks.JUNGLE_PRESSURE_PLATE, Blocks.JUNGLE_PLANKS);
/*  194 */     h(var0, Blocks.JUNGLE_SLAB, Blocks.JUNGLE_PLANKS);
/*  195 */     i(var0, Blocks.JUNGLE_STAIRS, Blocks.JUNGLE_PLANKS);
/*  196 */     j(var0, Blocks.JUNGLE_TRAPDOOR, Blocks.JUNGLE_PLANKS);
/*  197 */     k(var0, Blocks.JUNGLE_SIGN, Blocks.JUNGLE_PLANKS);
/*      */     
/*  199 */     c(var0, Blocks.OAK_BUTTON, Blocks.OAK_PLANKS);
/*  200 */     d(var0, Blocks.OAK_DOOR, Blocks.OAK_PLANKS);
/*  201 */     e(var0, Blocks.OAK_FENCE, Blocks.OAK_PLANKS);
/*  202 */     f(var0, Blocks.OAK_FENCE_GATE, Blocks.OAK_PLANKS);
/*  203 */     g(var0, Blocks.OAK_PRESSURE_PLATE, Blocks.OAK_PLANKS);
/*  204 */     h(var0, Blocks.OAK_SLAB, Blocks.OAK_PLANKS);
/*  205 */     i(var0, Blocks.OAK_STAIRS, Blocks.OAK_PLANKS);
/*  206 */     j(var0, Blocks.OAK_TRAPDOOR, Blocks.OAK_PLANKS);
/*  207 */     k(var0, Blocks.OAK_SIGN, Blocks.OAK_PLANKS);
/*      */     
/*  209 */     c(var0, Blocks.SPRUCE_BUTTON, Blocks.SPRUCE_PLANKS);
/*  210 */     d(var0, Blocks.SPRUCE_DOOR, Blocks.SPRUCE_PLANKS);
/*  211 */     e(var0, Blocks.SPRUCE_FENCE, Blocks.SPRUCE_PLANKS);
/*  212 */     f(var0, Blocks.SPRUCE_FENCE_GATE, Blocks.SPRUCE_PLANKS);
/*  213 */     g(var0, Blocks.SPRUCE_PRESSURE_PLATE, Blocks.SPRUCE_PLANKS);
/*  214 */     h(var0, Blocks.SPRUCE_SLAB, Blocks.SPRUCE_PLANKS);
/*  215 */     i(var0, Blocks.SPRUCE_STAIRS, Blocks.SPRUCE_PLANKS);
/*  216 */     j(var0, Blocks.SPRUCE_TRAPDOOR, Blocks.SPRUCE_PLANKS);
/*  217 */     k(var0, Blocks.SPRUCE_SIGN, Blocks.SPRUCE_PLANKS);
/*      */     
/*  219 */     c(var0, Blocks.WARPED_BUTTON, Blocks.WARPED_PLANKS);
/*  220 */     d(var0, Blocks.WARPED_DOOR, Blocks.WARPED_PLANKS);
/*  221 */     e(var0, Blocks.WARPED_FENCE, Blocks.WARPED_PLANKS);
/*  222 */     f(var0, Blocks.WARPED_FENCE_GATE, Blocks.WARPED_PLANKS);
/*  223 */     g(var0, Blocks.WARPED_PRESSURE_PLATE, Blocks.WARPED_PLANKS);
/*  224 */     h(var0, Blocks.WARPED_SLAB, Blocks.WARPED_PLANKS);
/*  225 */     i(var0, Blocks.WARPED_STAIRS, Blocks.WARPED_PLANKS);
/*  226 */     j(var0, Blocks.WARPED_TRAPDOOR, Blocks.WARPED_PLANKS);
/*  227 */     k(var0, Blocks.WARPED_SIGN, Blocks.WARPED_PLANKS);
/*      */ 
/*      */     
/*  230 */     l(var0, Blocks.BLACK_WOOL, Items.BLACK_DYE);
/*  231 */     m(var0, Blocks.BLACK_CARPET, Blocks.BLACK_WOOL);
/*  232 */     n(var0, Blocks.BLACK_CARPET, Items.BLACK_DYE);
/*  233 */     o(var0, Items.BLACK_BED, Blocks.BLACK_WOOL);
/*  234 */     p(var0, Items.BLACK_BED, Items.BLACK_DYE);
/*  235 */     q(var0, Items.BLACK_BANNER, Blocks.BLACK_WOOL);
/*      */     
/*  237 */     l(var0, Blocks.BLUE_WOOL, Items.BLUE_DYE);
/*  238 */     m(var0, Blocks.BLUE_CARPET, Blocks.BLUE_WOOL);
/*  239 */     n(var0, Blocks.BLUE_CARPET, Items.BLUE_DYE);
/*  240 */     o(var0, Items.BLUE_BED, Blocks.BLUE_WOOL);
/*  241 */     p(var0, Items.BLUE_BED, Items.BLUE_DYE);
/*  242 */     q(var0, Items.BLUE_BANNER, Blocks.BLUE_WOOL);
/*      */     
/*  244 */     l(var0, Blocks.BROWN_WOOL, Items.BROWN_DYE);
/*  245 */     m(var0, Blocks.BROWN_CARPET, Blocks.BROWN_WOOL);
/*  246 */     n(var0, Blocks.BROWN_CARPET, Items.BROWN_DYE);
/*  247 */     o(var0, Items.BROWN_BED, Blocks.BROWN_WOOL);
/*  248 */     p(var0, Items.BROWN_BED, Items.BROWN_DYE);
/*  249 */     q(var0, Items.BROWN_BANNER, Blocks.BROWN_WOOL);
/*      */     
/*  251 */     l(var0, Blocks.CYAN_WOOL, Items.CYAN_DYE);
/*  252 */     m(var0, Blocks.CYAN_CARPET, Blocks.CYAN_WOOL);
/*  253 */     n(var0, Blocks.CYAN_CARPET, Items.CYAN_DYE);
/*  254 */     o(var0, Items.CYAN_BED, Blocks.CYAN_WOOL);
/*  255 */     p(var0, Items.CYAN_BED, Items.CYAN_DYE);
/*  256 */     q(var0, Items.CYAN_BANNER, Blocks.CYAN_WOOL);
/*      */     
/*  258 */     l(var0, Blocks.GRAY_WOOL, Items.GRAY_DYE);
/*  259 */     m(var0, Blocks.GRAY_CARPET, Blocks.GRAY_WOOL);
/*  260 */     n(var0, Blocks.GRAY_CARPET, Items.GRAY_DYE);
/*  261 */     o(var0, Items.GRAY_BED, Blocks.GRAY_WOOL);
/*  262 */     p(var0, Items.GRAY_BED, Items.GRAY_DYE);
/*  263 */     q(var0, Items.GRAY_BANNER, Blocks.GRAY_WOOL);
/*      */     
/*  265 */     l(var0, Blocks.GREEN_WOOL, Items.GREEN_DYE);
/*  266 */     m(var0, Blocks.GREEN_CARPET, Blocks.GREEN_WOOL);
/*  267 */     n(var0, Blocks.GREEN_CARPET, Items.GREEN_DYE);
/*  268 */     o(var0, Items.GREEN_BED, Blocks.GREEN_WOOL);
/*  269 */     p(var0, Items.GREEN_BED, Items.GREEN_DYE);
/*  270 */     q(var0, Items.GREEN_BANNER, Blocks.GREEN_WOOL);
/*      */     
/*  272 */     l(var0, Blocks.LIGHT_BLUE_WOOL, Items.LIGHT_BLUE_DYE);
/*  273 */     m(var0, Blocks.LIGHT_BLUE_CARPET, Blocks.LIGHT_BLUE_WOOL);
/*  274 */     n(var0, Blocks.LIGHT_BLUE_CARPET, Items.LIGHT_BLUE_DYE);
/*  275 */     o(var0, Items.LIGHT_BLUE_BED, Blocks.LIGHT_BLUE_WOOL);
/*  276 */     p(var0, Items.LIGHT_BLUE_BED, Items.LIGHT_BLUE_DYE);
/*  277 */     q(var0, Items.LIGHT_BLUE_BANNER, Blocks.LIGHT_BLUE_WOOL);
/*      */     
/*  279 */     l(var0, Blocks.LIGHT_GRAY_WOOL, Items.LIGHT_GRAY_DYE);
/*  280 */     m(var0, Blocks.LIGHT_GRAY_CARPET, Blocks.LIGHT_GRAY_WOOL);
/*  281 */     n(var0, Blocks.LIGHT_GRAY_CARPET, Items.LIGHT_GRAY_DYE);
/*  282 */     o(var0, Items.LIGHT_GRAY_BED, Blocks.LIGHT_GRAY_WOOL);
/*  283 */     p(var0, Items.LIGHT_GRAY_BED, Items.LIGHT_GRAY_DYE);
/*  284 */     q(var0, Items.LIGHT_GRAY_BANNER, Blocks.LIGHT_GRAY_WOOL);
/*      */     
/*  286 */     l(var0, Blocks.LIME_WOOL, Items.LIME_DYE);
/*  287 */     m(var0, Blocks.LIME_CARPET, Blocks.LIME_WOOL);
/*  288 */     n(var0, Blocks.LIME_CARPET, Items.LIME_DYE);
/*  289 */     o(var0, Items.LIME_BED, Blocks.LIME_WOOL);
/*  290 */     p(var0, Items.LIME_BED, Items.LIME_DYE);
/*  291 */     q(var0, Items.LIME_BANNER, Blocks.LIME_WOOL);
/*      */     
/*  293 */     l(var0, Blocks.MAGENTA_WOOL, Items.MAGENTA_DYE);
/*  294 */     m(var0, Blocks.MAGENTA_CARPET, Blocks.MAGENTA_WOOL);
/*  295 */     n(var0, Blocks.MAGENTA_CARPET, Items.MAGENTA_DYE);
/*  296 */     o(var0, Items.MAGENTA_BED, Blocks.MAGENTA_WOOL);
/*  297 */     p(var0, Items.MAGENTA_BED, Items.MAGENTA_DYE);
/*  298 */     q(var0, Items.MAGENTA_BANNER, Blocks.MAGENTA_WOOL);
/*      */     
/*  300 */     l(var0, Blocks.ORANGE_WOOL, Items.ORANGE_DYE);
/*  301 */     m(var0, Blocks.ORANGE_CARPET, Blocks.ORANGE_WOOL);
/*  302 */     n(var0, Blocks.ORANGE_CARPET, Items.ORANGE_DYE);
/*  303 */     o(var0, Items.ORANGE_BED, Blocks.ORANGE_WOOL);
/*  304 */     p(var0, Items.ORANGE_BED, Items.ORANGE_DYE);
/*  305 */     q(var0, Items.ORANGE_BANNER, Blocks.ORANGE_WOOL);
/*      */     
/*  307 */     l(var0, Blocks.PINK_WOOL, Items.PINK_DYE);
/*  308 */     m(var0, Blocks.PINK_CARPET, Blocks.PINK_WOOL);
/*  309 */     n(var0, Blocks.PINK_CARPET, Items.PINK_DYE);
/*  310 */     o(var0, Items.PINK_BED, Blocks.PINK_WOOL);
/*  311 */     p(var0, Items.PINK_BED, Items.PINK_DYE);
/*  312 */     q(var0, Items.PINK_BANNER, Blocks.PINK_WOOL);
/*      */     
/*  314 */     l(var0, Blocks.PURPLE_WOOL, Items.PURPLE_DYE);
/*  315 */     m(var0, Blocks.PURPLE_CARPET, Blocks.PURPLE_WOOL);
/*  316 */     n(var0, Blocks.PURPLE_CARPET, Items.PURPLE_DYE);
/*  317 */     o(var0, Items.PURPLE_BED, Blocks.PURPLE_WOOL);
/*  318 */     p(var0, Items.PURPLE_BED, Items.PURPLE_DYE);
/*  319 */     q(var0, Items.PURPLE_BANNER, Blocks.PURPLE_WOOL);
/*      */     
/*  321 */     l(var0, Blocks.RED_WOOL, Items.RED_DYE);
/*  322 */     m(var0, Blocks.RED_CARPET, Blocks.RED_WOOL);
/*  323 */     n(var0, Blocks.RED_CARPET, Items.RED_DYE);
/*  324 */     o(var0, Items.RED_BED, Blocks.RED_WOOL);
/*  325 */     p(var0, Items.RED_BED, Items.RED_DYE);
/*  326 */     q(var0, Items.RED_BANNER, Blocks.RED_WOOL);
/*      */     
/*  328 */     m(var0, Blocks.WHITE_CARPET, Blocks.WHITE_WOOL);
/*  329 */     o(var0, Items.WHITE_BED, Blocks.WHITE_WOOL);
/*  330 */     q(var0, Items.WHITE_BANNER, Blocks.WHITE_WOOL);
/*      */     
/*  332 */     l(var0, Blocks.YELLOW_WOOL, Items.YELLOW_DYE);
/*  333 */     m(var0, Blocks.YELLOW_CARPET, Blocks.YELLOW_WOOL);
/*  334 */     n(var0, Blocks.YELLOW_CARPET, Items.YELLOW_DYE);
/*  335 */     o(var0, Items.YELLOW_BED, Blocks.YELLOW_WOOL);
/*  336 */     p(var0, Items.YELLOW_BED, Items.YELLOW_DYE);
/*  337 */     q(var0, Items.YELLOW_BANNER, Blocks.YELLOW_WOOL);
/*      */ 
/*      */     
/*  340 */     r(var0, Blocks.BLACK_STAINED_GLASS, Items.BLACK_DYE);
/*  341 */     s(var0, Blocks.BLACK_STAINED_GLASS_PANE, Blocks.BLACK_STAINED_GLASS);
/*  342 */     t(var0, Blocks.BLACK_STAINED_GLASS_PANE, Items.BLACK_DYE);
/*      */     
/*  344 */     r(var0, Blocks.BLUE_STAINED_GLASS, Items.BLUE_DYE);
/*  345 */     s(var0, Blocks.BLUE_STAINED_GLASS_PANE, Blocks.BLUE_STAINED_GLASS);
/*  346 */     t(var0, Blocks.BLUE_STAINED_GLASS_PANE, Items.BLUE_DYE);
/*      */     
/*  348 */     r(var0, Blocks.BROWN_STAINED_GLASS, Items.BROWN_DYE);
/*  349 */     s(var0, Blocks.BROWN_STAINED_GLASS_PANE, Blocks.BROWN_STAINED_GLASS);
/*  350 */     t(var0, Blocks.BROWN_STAINED_GLASS_PANE, Items.BROWN_DYE);
/*      */     
/*  352 */     r(var0, Blocks.CYAN_STAINED_GLASS, Items.CYAN_DYE);
/*  353 */     s(var0, Blocks.CYAN_STAINED_GLASS_PANE, Blocks.CYAN_STAINED_GLASS);
/*  354 */     t(var0, Blocks.CYAN_STAINED_GLASS_PANE, Items.CYAN_DYE);
/*      */     
/*  356 */     r(var0, Blocks.GRAY_STAINED_GLASS, Items.GRAY_DYE);
/*  357 */     s(var0, Blocks.GRAY_STAINED_GLASS_PANE, Blocks.GRAY_STAINED_GLASS);
/*  358 */     t(var0, Blocks.GRAY_STAINED_GLASS_PANE, Items.GRAY_DYE);
/*      */     
/*  360 */     r(var0, Blocks.GREEN_STAINED_GLASS, Items.GREEN_DYE);
/*  361 */     s(var0, Blocks.GREEN_STAINED_GLASS_PANE, Blocks.GREEN_STAINED_GLASS);
/*  362 */     t(var0, Blocks.GREEN_STAINED_GLASS_PANE, Items.GREEN_DYE);
/*      */     
/*  364 */     r(var0, Blocks.LIGHT_BLUE_STAINED_GLASS, Items.LIGHT_BLUE_DYE);
/*  365 */     s(var0, Blocks.LIGHT_BLUE_STAINED_GLASS_PANE, Blocks.LIGHT_BLUE_STAINED_GLASS);
/*  366 */     t(var0, Blocks.LIGHT_BLUE_STAINED_GLASS_PANE, Items.LIGHT_BLUE_DYE);
/*      */     
/*  368 */     r(var0, Blocks.LIGHT_GRAY_STAINED_GLASS, Items.LIGHT_GRAY_DYE);
/*  369 */     s(var0, Blocks.LIGHT_GRAY_STAINED_GLASS_PANE, Blocks.LIGHT_GRAY_STAINED_GLASS);
/*  370 */     t(var0, Blocks.LIGHT_GRAY_STAINED_GLASS_PANE, Items.LIGHT_GRAY_DYE);
/*      */     
/*  372 */     r(var0, Blocks.LIME_STAINED_GLASS, Items.LIME_DYE);
/*  373 */     s(var0, Blocks.LIME_STAINED_GLASS_PANE, Blocks.LIME_STAINED_GLASS);
/*  374 */     t(var0, Blocks.LIME_STAINED_GLASS_PANE, Items.LIME_DYE);
/*      */     
/*  376 */     r(var0, Blocks.MAGENTA_STAINED_GLASS, Items.MAGENTA_DYE);
/*  377 */     s(var0, Blocks.MAGENTA_STAINED_GLASS_PANE, Blocks.MAGENTA_STAINED_GLASS);
/*  378 */     t(var0, Blocks.MAGENTA_STAINED_GLASS_PANE, Items.MAGENTA_DYE);
/*      */     
/*  380 */     r(var0, Blocks.ORANGE_STAINED_GLASS, Items.ORANGE_DYE);
/*  381 */     s(var0, Blocks.ORANGE_STAINED_GLASS_PANE, Blocks.ORANGE_STAINED_GLASS);
/*  382 */     t(var0, Blocks.ORANGE_STAINED_GLASS_PANE, Items.ORANGE_DYE);
/*      */     
/*  384 */     r(var0, Blocks.PINK_STAINED_GLASS, Items.PINK_DYE);
/*  385 */     s(var0, Blocks.PINK_STAINED_GLASS_PANE, Blocks.PINK_STAINED_GLASS);
/*  386 */     t(var0, Blocks.PINK_STAINED_GLASS_PANE, Items.PINK_DYE);
/*      */     
/*  388 */     r(var0, Blocks.PURPLE_STAINED_GLASS, Items.PURPLE_DYE);
/*  389 */     s(var0, Blocks.PURPLE_STAINED_GLASS_PANE, Blocks.PURPLE_STAINED_GLASS);
/*  390 */     t(var0, Blocks.PURPLE_STAINED_GLASS_PANE, Items.PURPLE_DYE);
/*      */     
/*  392 */     r(var0, Blocks.RED_STAINED_GLASS, Items.RED_DYE);
/*  393 */     s(var0, Blocks.RED_STAINED_GLASS_PANE, Blocks.RED_STAINED_GLASS);
/*  394 */     t(var0, Blocks.RED_STAINED_GLASS_PANE, Items.RED_DYE);
/*      */     
/*  396 */     r(var0, Blocks.WHITE_STAINED_GLASS, Items.WHITE_DYE);
/*  397 */     s(var0, Blocks.WHITE_STAINED_GLASS_PANE, Blocks.WHITE_STAINED_GLASS);
/*  398 */     t(var0, Blocks.WHITE_STAINED_GLASS_PANE, Items.WHITE_DYE);
/*      */     
/*  400 */     r(var0, Blocks.YELLOW_STAINED_GLASS, Items.YELLOW_DYE);
/*  401 */     s(var0, Blocks.YELLOW_STAINED_GLASS_PANE, Blocks.YELLOW_STAINED_GLASS);
/*  402 */     t(var0, Blocks.YELLOW_STAINED_GLASS_PANE, Items.YELLOW_DYE);
/*      */ 
/*      */     
/*  405 */     u(var0, Blocks.BLACK_TERRACOTTA, Items.BLACK_DYE);
/*  406 */     u(var0, Blocks.BLUE_TERRACOTTA, Items.BLUE_DYE);
/*  407 */     u(var0, Blocks.BROWN_TERRACOTTA, Items.BROWN_DYE);
/*  408 */     u(var0, Blocks.CYAN_TERRACOTTA, Items.CYAN_DYE);
/*  409 */     u(var0, Blocks.GRAY_TERRACOTTA, Items.GRAY_DYE);
/*  410 */     u(var0, Blocks.GREEN_TERRACOTTA, Items.GREEN_DYE);
/*  411 */     u(var0, Blocks.LIGHT_BLUE_TERRACOTTA, Items.LIGHT_BLUE_DYE);
/*  412 */     u(var0, Blocks.LIGHT_GRAY_TERRACOTTA, Items.LIGHT_GRAY_DYE);
/*  413 */     u(var0, Blocks.LIME_TERRACOTTA, Items.LIME_DYE);
/*  414 */     u(var0, Blocks.MAGENTA_TERRACOTTA, Items.MAGENTA_DYE);
/*  415 */     u(var0, Blocks.ORANGE_TERRACOTTA, Items.ORANGE_DYE);
/*  416 */     u(var0, Blocks.PINK_TERRACOTTA, Items.PINK_DYE);
/*  417 */     u(var0, Blocks.PURPLE_TERRACOTTA, Items.PURPLE_DYE);
/*  418 */     u(var0, Blocks.RED_TERRACOTTA, Items.RED_DYE);
/*  419 */     u(var0, Blocks.WHITE_TERRACOTTA, Items.WHITE_DYE);
/*  420 */     u(var0, Blocks.YELLOW_TERRACOTTA, Items.YELLOW_DYE);
/*      */     
/*  422 */     v(var0, Blocks.BLACK_CONCRETE_POWDER, Items.BLACK_DYE);
/*  423 */     v(var0, Blocks.BLUE_CONCRETE_POWDER, Items.BLUE_DYE);
/*  424 */     v(var0, Blocks.BROWN_CONCRETE_POWDER, Items.BROWN_DYE);
/*  425 */     v(var0, Blocks.CYAN_CONCRETE_POWDER, Items.CYAN_DYE);
/*  426 */     v(var0, Blocks.GRAY_CONCRETE_POWDER, Items.GRAY_DYE);
/*  427 */     v(var0, Blocks.GREEN_CONCRETE_POWDER, Items.GREEN_DYE);
/*  428 */     v(var0, Blocks.LIGHT_BLUE_CONCRETE_POWDER, Items.LIGHT_BLUE_DYE);
/*  429 */     v(var0, Blocks.LIGHT_GRAY_CONCRETE_POWDER, Items.LIGHT_GRAY_DYE);
/*  430 */     v(var0, Blocks.LIME_CONCRETE_POWDER, Items.LIME_DYE);
/*  431 */     v(var0, Blocks.MAGENTA_CONCRETE_POWDER, Items.MAGENTA_DYE);
/*  432 */     v(var0, Blocks.ORANGE_CONCRETE_POWDER, Items.ORANGE_DYE);
/*  433 */     v(var0, Blocks.PINK_CONCRETE_POWDER, Items.PINK_DYE);
/*  434 */     v(var0, Blocks.PURPLE_CONCRETE_POWDER, Items.PURPLE_DYE);
/*  435 */     v(var0, Blocks.RED_CONCRETE_POWDER, Items.RED_DYE);
/*  436 */     v(var0, Blocks.WHITE_CONCRETE_POWDER, Items.WHITE_DYE);
/*  437 */     v(var0, Blocks.YELLOW_CONCRETE_POWDER, Items.YELLOW_DYE);
/*      */ 
/*      */     
/*  440 */     jh.a(Blocks.ACTIVATOR_RAIL, 6)
/*  441 */       .a(Character.valueOf('#'), Blocks.REDSTONE_TORCH)
/*  442 */       .a(Character.valueOf('S'), Items.STICK)
/*  443 */       .a(Character.valueOf('X'), Items.IRON_INGOT)
/*  444 */       .a("XSX")
/*  445 */       .a("X#X")
/*  446 */       .a("XSX")
/*  447 */       .a("has_rail", a(Blocks.RAIL))
/*  448 */       .a(var0);
/*      */     
/*  450 */     ji.a(Blocks.ANDESITE, 2)
/*  451 */       .b(Blocks.DIORITE)
/*  452 */       .b(Blocks.COBBLESTONE)
/*  453 */       .a("has_stone", a(Blocks.DIORITE))
/*  454 */       .a(var0);
/*      */     
/*  456 */     jh.a(Blocks.ANVIL)
/*  457 */       .a(Character.valueOf('I'), Blocks.IRON_BLOCK)
/*  458 */       .a(Character.valueOf('i'), Items.IRON_INGOT)
/*  459 */       .a("III")
/*  460 */       .a(" i ")
/*  461 */       .a("iii")
/*  462 */       .a("has_iron_block", a(Blocks.IRON_BLOCK))
/*  463 */       .a(var0);
/*      */     
/*  465 */     jh.a(Items.ARMOR_STAND)
/*  466 */       .a(Character.valueOf('/'), Items.STICK)
/*  467 */       .a(Character.valueOf('_'), Blocks.SMOOTH_STONE_SLAB)
/*  468 */       .a("///")
/*  469 */       .a(" / ")
/*  470 */       .a("/_/")
/*  471 */       .a("has_stone_slab", a(Blocks.SMOOTH_STONE_SLAB))
/*  472 */       .a(var0);
/*      */     
/*  474 */     jh.a(Items.ARROW, 4)
/*  475 */       .a(Character.valueOf('#'), Items.STICK)
/*  476 */       .a(Character.valueOf('X'), Items.FLINT)
/*  477 */       .a(Character.valueOf('Y'), Items.FEATHER)
/*  478 */       .a("X")
/*  479 */       .a("#")
/*  480 */       .a("Y")
/*  481 */       .a("has_feather", a(Items.FEATHER))
/*  482 */       .a("has_flint", a(Items.FLINT))
/*  483 */       .a(var0);
/*      */     
/*  485 */     jh.a(Blocks.BARREL, 1)
/*  486 */       .a(Character.valueOf('P'), TagsItem.PLANKS)
/*  487 */       .a(Character.valueOf('S'), TagsItem.WOODEN_SLABS)
/*  488 */       .a("PSP")
/*  489 */       .a("P P")
/*  490 */       .a("PSP")
/*  491 */       .a("has_planks", a(TagsItem.PLANKS))
/*  492 */       .a("has_wood_slab", a(TagsItem.WOODEN_SLABS))
/*  493 */       .a(var0);
/*      */     
/*  495 */     jh.a(Blocks.BEACON)
/*  496 */       .a(Character.valueOf('S'), Items.NETHER_STAR)
/*  497 */       .a(Character.valueOf('G'), Blocks.GLASS)
/*  498 */       .a(Character.valueOf('O'), Blocks.OBSIDIAN)
/*  499 */       .a("GGG")
/*  500 */       .a("GSG")
/*  501 */       .a("OOO")
/*  502 */       .a("has_nether_star", a(Items.NETHER_STAR))
/*  503 */       .a(var0);
/*      */     
/*  505 */     jh.a(Blocks.BEEHIVE)
/*  506 */       .a(Character.valueOf('P'), TagsItem.PLANKS)
/*  507 */       .a(Character.valueOf('H'), Items.HONEYCOMB)
/*  508 */       .a("PPP")
/*  509 */       .a("HHH")
/*  510 */       .a("PPP")
/*  511 */       .a("has_honeycomb", a(Items.HONEYCOMB))
/*  512 */       .a(var0);
/*      */     
/*  514 */     ji.a(Items.BEETROOT_SOUP)
/*  515 */       .b(Items.BOWL)
/*  516 */       .b(Items.BEETROOT, 6)
/*  517 */       .a("has_beetroot", a(Items.BEETROOT))
/*  518 */       .a(var0);
/*      */     
/*  520 */     ji.a(Items.BLACK_DYE)
/*  521 */       .b(Items.INK_SAC)
/*  522 */       .a("black_dye")
/*  523 */       .a("has_ink_sac", a(Items.INK_SAC))
/*  524 */       .a(var0);
/*      */     
/*  526 */     ji.a(Items.BLACK_DYE)
/*  527 */       .b(Blocks.WITHER_ROSE)
/*  528 */       .a("black_dye")
/*  529 */       .a("has_black_flower", a(Blocks.WITHER_ROSE))
/*  530 */       .a(var0, "black_dye_from_wither_rose");
/*      */     
/*  532 */     ji.a(Items.BLAZE_POWDER, 2)
/*  533 */       .b(Items.BLAZE_ROD)
/*  534 */       .a("has_blaze_rod", a(Items.BLAZE_ROD))
/*  535 */       .a(var0);
/*      */     
/*  537 */     ji.a(Items.BLUE_DYE)
/*  538 */       .b(Items.LAPIS_LAZULI)
/*  539 */       .a("blue_dye")
/*  540 */       .a("has_lapis_lazuli", a(Items.LAPIS_LAZULI))
/*  541 */       .a(var0);
/*      */     
/*  543 */     ji.a(Items.BLUE_DYE)
/*  544 */       .b(Blocks.CORNFLOWER)
/*  545 */       .a("blue_dye")
/*  546 */       .a("has_blue_flower", a(Blocks.CORNFLOWER))
/*  547 */       .a(var0, "blue_dye_from_cornflower");
/*      */     
/*  549 */     jh.a(Blocks.BLUE_ICE)
/*  550 */       .a(Character.valueOf('#'), Blocks.PACKED_ICE)
/*  551 */       .a("###")
/*  552 */       .a("###")
/*  553 */       .a("###")
/*  554 */       .a("has_packed_ice", a(Blocks.PACKED_ICE))
/*  555 */       .a(var0);
/*      */     
/*  557 */     jh.a(Blocks.BONE_BLOCK)
/*  558 */       .a(Character.valueOf('X'), Items.BONE_MEAL)
/*  559 */       .a("XXX")
/*  560 */       .a("XXX")
/*  561 */       .a("XXX")
/*  562 */       .a("has_bonemeal", a(Items.BONE_MEAL))
/*  563 */       .a(var0);
/*      */     
/*  565 */     ji.a(Items.BONE_MEAL, 3)
/*  566 */       .b(Items.BONE)
/*  567 */       .a("bonemeal")
/*  568 */       .a("has_bone", a(Items.BONE))
/*  569 */       .a(var0);
/*      */     
/*  571 */     ji.a(Items.BONE_MEAL, 9)
/*  572 */       .b(Blocks.BONE_BLOCK)
/*  573 */       .a("bonemeal")
/*  574 */       .a("has_bone_block", a(Blocks.BONE_BLOCK))
/*  575 */       .a(var0, "bone_meal_from_bone_block");
/*      */     
/*  577 */     ji.a(Items.BOOK)
/*  578 */       .b(Items.PAPER, 3)
/*  579 */       .b(Items.LEATHER)
/*  580 */       .a("has_paper", a(Items.PAPER))
/*  581 */       .a(var0);
/*      */     
/*  583 */     jh.a(Blocks.BOOKSHELF)
/*  584 */       .a(Character.valueOf('#'), TagsItem.PLANKS)
/*  585 */       .a(Character.valueOf('X'), Items.BOOK)
/*  586 */       .a("###")
/*  587 */       .a("XXX")
/*  588 */       .a("###")
/*  589 */       .a("has_book", a(Items.BOOK))
/*  590 */       .a(var0);
/*      */     
/*  592 */     jh.a(Items.BOW)
/*  593 */       .a(Character.valueOf('#'), Items.STICK)
/*  594 */       .a(Character.valueOf('X'), Items.STRING)
/*  595 */       .a(" #X")
/*  596 */       .a("# X")
/*  597 */       .a(" #X")
/*  598 */       .a("has_string", a(Items.STRING))
/*  599 */       .a(var0);
/*      */     
/*  601 */     jh.a(Items.BOWL, 4)
/*  602 */       .a(Character.valueOf('#'), TagsItem.PLANKS)
/*  603 */       .a("# #")
/*  604 */       .a(" # ")
/*  605 */       .a("has_brown_mushroom", a(Blocks.BROWN_MUSHROOM))
/*  606 */       .a("has_red_mushroom", a(Blocks.RED_MUSHROOM))
/*  607 */       .a("has_mushroom_stew", a(Items.MUSHROOM_STEW))
/*  608 */       .a(var0);
/*      */     
/*  610 */     jh.a(Items.BREAD)
/*  611 */       .a(Character.valueOf('#'), Items.WHEAT)
/*  612 */       .a("###")
/*  613 */       .a("has_wheat", a(Items.WHEAT))
/*  614 */       .a(var0);
/*      */     
/*  616 */     jh.a(Blocks.BREWING_STAND)
/*  617 */       .a(Character.valueOf('B'), Items.BLAZE_ROD)
/*  618 */       .a(Character.valueOf('#'), TagsItem.STONE_CRAFTING_MATERIALS)
/*  619 */       .a(" B ")
/*  620 */       .a("###")
/*  621 */       .a("has_blaze_rod", a(Items.BLAZE_ROD))
/*  622 */       .a(var0);
/*      */     
/*  624 */     jh.a(Blocks.BRICKS)
/*  625 */       .a(Character.valueOf('#'), Items.BRICK)
/*  626 */       .a("##")
/*  627 */       .a("##")
/*  628 */       .a("has_brick", a(Items.BRICK))
/*  629 */       .a(var0);
/*      */     
/*  631 */     jh.a(Blocks.BRICK_SLAB, 6)
/*  632 */       .a(Character.valueOf('#'), Blocks.BRICKS)
/*  633 */       .a("###")
/*  634 */       .a("has_brick_block", a(Blocks.BRICKS))
/*  635 */       .a(var0);
/*      */     
/*  637 */     jh.a(Blocks.BRICK_STAIRS, 4)
/*  638 */       .a(Character.valueOf('#'), Blocks.BRICKS)
/*  639 */       .a("#  ")
/*  640 */       .a("## ")
/*  641 */       .a("###")
/*  642 */       .a("has_brick_block", a(Blocks.BRICKS))
/*  643 */       .a(var0);
/*      */     
/*  645 */     ji.a(Items.BROWN_DYE)
/*  646 */       .b(Items.COCOA_BEANS)
/*  647 */       .a("brown_dye")
/*  648 */       .a("has_cocoa_beans", a(Items.COCOA_BEANS))
/*  649 */       .a(var0);
/*      */     
/*  651 */     jh.a(Items.BUCKET)
/*  652 */       .a(Character.valueOf('#'), Items.IRON_INGOT)
/*  653 */       .a("# #")
/*  654 */       .a(" # ")
/*  655 */       .a("has_iron_ingot", a(Items.IRON_INGOT))
/*  656 */       .a(var0);
/*      */     
/*  658 */     jh.a(Blocks.CAKE)
/*  659 */       .a(Character.valueOf('A'), Items.MILK_BUCKET)
/*  660 */       .a(Character.valueOf('B'), Items.SUGAR)
/*  661 */       .a(Character.valueOf('C'), Items.WHEAT)
/*  662 */       .a(Character.valueOf('E'), Items.EGG)
/*  663 */       .a("AAA")
/*  664 */       .a("BEB")
/*  665 */       .a("CCC")
/*  666 */       .a("has_egg", a(Items.EGG))
/*  667 */       .a(var0);
/*      */     
/*  669 */     jh.a(Blocks.CAMPFIRE)
/*  670 */       .a(Character.valueOf('L'), TagsItem.LOGS)
/*  671 */       .a(Character.valueOf('S'), Items.STICK)
/*  672 */       .a(Character.valueOf('C'), TagsItem.COALS)
/*  673 */       .a(" S ")
/*  674 */       .a("SCS")
/*  675 */       .a("LLL")
/*  676 */       .a("has_stick", a(Items.STICK))
/*  677 */       .a("has_coal", a(TagsItem.COALS))
/*  678 */       .a(var0);
/*      */     
/*  680 */     jh.a(Items.CARROT_ON_A_STICK)
/*  681 */       .a(Character.valueOf('#'), Items.FISHING_ROD)
/*  682 */       .a(Character.valueOf('X'), Items.CARROT)
/*  683 */       .a("# ")
/*  684 */       .a(" X")
/*  685 */       .a("has_carrot", a(Items.CARROT))
/*  686 */       .a(var0);
/*      */     
/*  688 */     jh.a(Items.WARPED_FUNGUS_ON_A_STICK)
/*  689 */       .a(Character.valueOf('#'), Items.FISHING_ROD)
/*  690 */       .a(Character.valueOf('X'), Items.bx)
/*  691 */       .a("# ")
/*  692 */       .a(" X")
/*  693 */       .a("has_warped_fungus", a(Items.bx))
/*  694 */       .a(var0);
/*      */     
/*  696 */     jh.a(Blocks.CAULDRON)
/*  697 */       .a(Character.valueOf('#'), Items.IRON_INGOT)
/*  698 */       .a("# #")
/*  699 */       .a("# #")
/*  700 */       .a("###")
/*  701 */       .a("has_water_bucket", a(Items.WATER_BUCKET))
/*  702 */       .a(var0);
/*      */     
/*  704 */     jh.a(Blocks.COMPOSTER)
/*  705 */       .a(Character.valueOf('#'), TagsItem.WOODEN_SLABS)
/*  706 */       .a("# #")
/*  707 */       .a("# #")
/*  708 */       .a("###")
/*  709 */       .a("has_wood_slab", a(TagsItem.WOODEN_SLABS))
/*  710 */       .a(var0);
/*      */     
/*  712 */     jh.a(Blocks.CHEST)
/*  713 */       .a(Character.valueOf('#'), TagsItem.PLANKS)
/*  714 */       .a("###")
/*  715 */       .a("# #")
/*  716 */       .a("###")
/*  717 */       .a("has_lots_of_items", new CriterionTriggerInventoryChanged.a(CriterionConditionEntity.b.a, CriterionConditionValue.IntegerRange.b(10), CriterionConditionValue.IntegerRange.e, CriterionConditionValue.IntegerRange.e, new CriterionConditionItem[0]))
/*  718 */       .a(var0);
/*      */     
/*  720 */     jh.a(Items.CHEST_MINECART)
/*  721 */       .a(Character.valueOf('A'), Blocks.CHEST)
/*  722 */       .a(Character.valueOf('B'), Items.MINECART)
/*  723 */       .a("A")
/*  724 */       .a("B")
/*  725 */       .a("has_minecart", a(Items.MINECART))
/*  726 */       .a(var0);
/*      */     
/*  728 */     jh.a(Blocks.CHISELED_NETHER_BRICKS)
/*  729 */       .a(Character.valueOf('#'), Blocks.NETHER_BRICK_SLAB)
/*  730 */       .a("#")
/*  731 */       .a("#")
/*  732 */       .a("has_nether_bricks", a(Blocks.NETHER_BRICKS))
/*  733 */       .a(var0);
/*      */     
/*  735 */     jh.a(Blocks.CHISELED_QUARTZ_BLOCK)
/*  736 */       .a(Character.valueOf('#'), Blocks.QUARTZ_SLAB)
/*  737 */       .a("#")
/*  738 */       .a("#")
/*  739 */       .a("has_chiseled_quartz_block", a(Blocks.CHISELED_QUARTZ_BLOCK))
/*  740 */       .a("has_quartz_block", a(Blocks.QUARTZ_BLOCK))
/*  741 */       .a("has_quartz_pillar", a(Blocks.QUARTZ_PILLAR))
/*  742 */       .a(var0);
/*      */     
/*  744 */     jh.a(Blocks.CHISELED_STONE_BRICKS)
/*  745 */       .a(Character.valueOf('#'), Blocks.STONE_BRICK_SLAB)
/*  746 */       .a("#")
/*  747 */       .a("#")
/*  748 */       .a("has_stone_bricks", a(TagsItem.STONE_BRICKS))
/*  749 */       .a(var0);
/*      */     
/*  751 */     jh.a(Blocks.CLAY)
/*  752 */       .a(Character.valueOf('#'), Items.CLAY_BALL)
/*  753 */       .a("##")
/*  754 */       .a("##")
/*  755 */       .a("has_clay_ball", a(Items.CLAY_BALL))
/*  756 */       .a(var0);
/*      */     
/*  758 */     jh.a(Items.CLOCK)
/*  759 */       .a(Character.valueOf('#'), Items.GOLD_INGOT)
/*  760 */       .a(Character.valueOf('X'), Items.REDSTONE)
/*  761 */       .a(" # ")
/*  762 */       .a("#X#")
/*  763 */       .a(" # ")
/*  764 */       .a("has_redstone", a(Items.REDSTONE))
/*  765 */       .a(var0);
/*      */     
/*  767 */     ji.a(Items.COAL, 9)
/*  768 */       .b(Blocks.COAL_BLOCK)
/*  769 */       .a("has_coal_block", a(Blocks.COAL_BLOCK))
/*  770 */       .a(var0);
/*      */     
/*  772 */     jh.a(Blocks.COAL_BLOCK)
/*  773 */       .a(Character.valueOf('#'), Items.COAL)
/*  774 */       .a("###")
/*  775 */       .a("###")
/*  776 */       .a("###")
/*  777 */       .a("has_coal", a(Items.COAL))
/*  778 */       .a(var0);
/*      */     
/*  780 */     jh.a(Blocks.COARSE_DIRT, 4)
/*  781 */       .a(Character.valueOf('D'), Blocks.DIRT)
/*  782 */       .a(Character.valueOf('G'), Blocks.GRAVEL)
/*  783 */       .a("DG")
/*  784 */       .a("GD")
/*  785 */       .a("has_gravel", a(Blocks.GRAVEL))
/*  786 */       .a(var0);
/*      */     
/*  788 */     jh.a(Blocks.COBBLESTONE_SLAB, 6)
/*  789 */       .a(Character.valueOf('#'), Blocks.COBBLESTONE)
/*  790 */       .a("###")
/*  791 */       .a("has_cobblestone", a(Blocks.COBBLESTONE))
/*  792 */       .a(var0);
/*      */     
/*  794 */     jh.a(Blocks.COBBLESTONE_WALL, 6)
/*  795 */       .a(Character.valueOf('#'), Blocks.COBBLESTONE)
/*  796 */       .a("###")
/*  797 */       .a("###")
/*  798 */       .a("has_cobblestone", a(Blocks.COBBLESTONE))
/*  799 */       .a(var0);
/*      */     
/*  801 */     jh.a(Blocks.COMPARATOR)
/*  802 */       .a(Character.valueOf('#'), Blocks.REDSTONE_TORCH)
/*  803 */       .a(Character.valueOf('X'), Items.QUARTZ)
/*  804 */       .a(Character.valueOf('I'), Blocks.STONE)
/*  805 */       .a(" # ")
/*  806 */       .a("#X#")
/*  807 */       .a("III")
/*  808 */       .a("has_quartz", a(Items.QUARTZ))
/*  809 */       .a(var0);
/*      */     
/*  811 */     jh.a(Items.COMPASS)
/*  812 */       .a(Character.valueOf('#'), Items.IRON_INGOT)
/*  813 */       .a(Character.valueOf('X'), Items.REDSTONE)
/*  814 */       .a(" # ")
/*  815 */       .a("#X#")
/*  816 */       .a(" # ")
/*  817 */       .a("has_redstone", a(Items.REDSTONE))
/*  818 */       .a(var0);
/*      */     
/*  820 */     jh.a(Items.COOKIE, 8)
/*  821 */       .a(Character.valueOf('#'), Items.WHEAT)
/*  822 */       .a(Character.valueOf('X'), Items.COCOA_BEANS)
/*  823 */       .a("#X#")
/*  824 */       .a("has_cocoa", a(Items.COCOA_BEANS))
/*  825 */       .a(var0);
/*      */     
/*  827 */     jh.a(Blocks.CRAFTING_TABLE)
/*  828 */       .a(Character.valueOf('#'), TagsItem.PLANKS)
/*  829 */       .a("##")
/*  830 */       .a("##")
/*  831 */       .a("has_planks", a(TagsItem.PLANKS))
/*  832 */       .a(var0);
/*      */     
/*  834 */     jh.a(Items.CROSSBOW)
/*  835 */       .a(Character.valueOf('~'), Items.STRING)
/*  836 */       .a(Character.valueOf('#'), Items.STICK)
/*  837 */       .a(Character.valueOf('&'), Items.IRON_INGOT)
/*  838 */       .a(Character.valueOf('$'), Blocks.TRIPWIRE_HOOK)
/*  839 */       .a("#&#")
/*  840 */       .a("~$~")
/*  841 */       .a(" # ")
/*  842 */       .a("has_string", a(Items.STRING))
/*  843 */       .a("has_stick", a(Items.STICK))
/*  844 */       .a("has_iron_ingot", a(Items.IRON_INGOT))
/*  845 */       .a("has_tripwire_hook", a(Blocks.TRIPWIRE_HOOK))
/*  846 */       .a(var0);
/*      */     
/*  848 */     jh.a(Blocks.LOOM)
/*  849 */       .a(Character.valueOf('#'), TagsItem.PLANKS)
/*  850 */       .a(Character.valueOf('@'), Items.STRING)
/*  851 */       .a("@@")
/*  852 */       .a("##")
/*  853 */       .a("has_string", a(Items.STRING))
/*  854 */       .a(var0);
/*      */     
/*  856 */     jh.a(Blocks.CHISELED_RED_SANDSTONE)
/*  857 */       .a(Character.valueOf('#'), Blocks.RED_SANDSTONE_SLAB)
/*  858 */       .a("#")
/*  859 */       .a("#")
/*  860 */       .a("has_red_sandstone", a(Blocks.RED_SANDSTONE))
/*  861 */       .a("has_chiseled_red_sandstone", a(Blocks.CHISELED_RED_SANDSTONE))
/*  862 */       .a("has_cut_red_sandstone", a(Blocks.CUT_RED_SANDSTONE))
/*  863 */       .a(var0);
/*      */     
/*  865 */     jh.a(Blocks.CHISELED_SANDSTONE)
/*  866 */       .a(Character.valueOf('#'), Blocks.SANDSTONE_SLAB)
/*  867 */       .a("#")
/*  868 */       .a("#")
/*  869 */       .a("has_stone_slab", a(Blocks.SANDSTONE_SLAB))
/*  870 */       .a(var0);
/*      */     
/*  872 */     ji.a(Items.CYAN_DYE, 2)
/*  873 */       .b(Items.BLUE_DYE)
/*  874 */       .b(Items.GREEN_DYE)
/*  875 */       .a("has_green_dye", a(Items.GREEN_DYE))
/*  876 */       .a("has_blue_dye", a(Items.BLUE_DYE))
/*  877 */       .a(var0);
/*      */     
/*  879 */     jh.a(Blocks.DARK_PRISMARINE)
/*  880 */       .a(Character.valueOf('S'), Items.PRISMARINE_SHARD)
/*  881 */       .a(Character.valueOf('I'), Items.BLACK_DYE)
/*  882 */       .a("SSS")
/*  883 */       .a("SIS")
/*  884 */       .a("SSS")
/*  885 */       .a("has_prismarine_shard", a(Items.PRISMARINE_SHARD))
/*  886 */       .a(var0);
/*      */     
/*  888 */     jh.a(Blocks.PRISMARINE_STAIRS, 4)
/*  889 */       .a(Character.valueOf('#'), Blocks.PRISMARINE)
/*  890 */       .a("#  ")
/*  891 */       .a("## ")
/*  892 */       .a("###")
/*  893 */       .a("has_prismarine", a(Blocks.PRISMARINE))
/*  894 */       .a(var0);
/*      */     
/*  896 */     jh.a(Blocks.PRISMARINE_BRICK_STAIRS, 4)
/*  897 */       .a(Character.valueOf('#'), Blocks.PRISMARINE_BRICKS)
/*  898 */       .a("#  ")
/*  899 */       .a("## ")
/*  900 */       .a("###")
/*  901 */       .a("has_prismarine_bricks", a(Blocks.PRISMARINE_BRICKS))
/*  902 */       .a(var0);
/*      */     
/*  904 */     jh.a(Blocks.DARK_PRISMARINE_STAIRS, 4)
/*  905 */       .a(Character.valueOf('#'), Blocks.DARK_PRISMARINE)
/*  906 */       .a("#  ")
/*  907 */       .a("## ")
/*  908 */       .a("###")
/*  909 */       .a("has_dark_prismarine", a(Blocks.DARK_PRISMARINE))
/*  910 */       .a(var0);
/*      */     
/*  912 */     jh.a(Blocks.DAYLIGHT_DETECTOR)
/*  913 */       .a(Character.valueOf('Q'), Items.QUARTZ)
/*  914 */       .a(Character.valueOf('G'), Blocks.GLASS)
/*  915 */       .a(Character.valueOf('W'), RecipeItemStack.a(TagsItem.WOODEN_SLABS))
/*  916 */       .a("GGG")
/*  917 */       .a("QQQ")
/*  918 */       .a("WWW")
/*  919 */       .a("has_quartz", a(Items.QUARTZ))
/*  920 */       .a(var0);
/*      */     
/*  922 */     jh.a(Blocks.DETECTOR_RAIL, 6)
/*  923 */       .a(Character.valueOf('R'), Items.REDSTONE)
/*  924 */       .a(Character.valueOf('#'), Blocks.STONE_PRESSURE_PLATE)
/*  925 */       .a(Character.valueOf('X'), Items.IRON_INGOT)
/*  926 */       .a("X X")
/*  927 */       .a("X#X")
/*  928 */       .a("XRX")
/*  929 */       .a("has_rail", a(Blocks.RAIL))
/*  930 */       .a(var0);
/*      */     
/*  932 */     ji.a(Items.DIAMOND, 9)
/*  933 */       .b(Blocks.DIAMOND_BLOCK)
/*  934 */       .a("has_diamond_block", a(Blocks.DIAMOND_BLOCK))
/*  935 */       .a(var0);
/*      */     
/*  937 */     jh.a(Items.DIAMOND_AXE)
/*  938 */       .a(Character.valueOf('#'), Items.STICK)
/*  939 */       .a(Character.valueOf('X'), Items.DIAMOND)
/*  940 */       .a("XX")
/*  941 */       .a("X#")
/*  942 */       .a(" #")
/*  943 */       .a("has_diamond", a(Items.DIAMOND))
/*  944 */       .a(var0);
/*      */     
/*  946 */     jh.a(Blocks.DIAMOND_BLOCK)
/*  947 */       .a(Character.valueOf('#'), Items.DIAMOND)
/*  948 */       .a("###")
/*  949 */       .a("###")
/*  950 */       .a("###")
/*  951 */       .a("has_diamond", a(Items.DIAMOND))
/*  952 */       .a(var0);
/*      */     
/*  954 */     jh.a(Items.DIAMOND_BOOTS)
/*  955 */       .a(Character.valueOf('X'), Items.DIAMOND)
/*  956 */       .a("X X")
/*  957 */       .a("X X")
/*  958 */       .a("has_diamond", a(Items.DIAMOND))
/*  959 */       .a(var0);
/*      */     
/*  961 */     jh.a(Items.DIAMOND_CHESTPLATE)
/*  962 */       .a(Character.valueOf('X'), Items.DIAMOND)
/*  963 */       .a("X X")
/*  964 */       .a("XXX")
/*  965 */       .a("XXX")
/*  966 */       .a("has_diamond", a(Items.DIAMOND))
/*  967 */       .a(var0);
/*      */     
/*  969 */     jh.a(Items.DIAMOND_HELMET)
/*  970 */       .a(Character.valueOf('X'), Items.DIAMOND)
/*  971 */       .a("XXX")
/*  972 */       .a("X X")
/*  973 */       .a("has_diamond", a(Items.DIAMOND))
/*  974 */       .a(var0);
/*      */     
/*  976 */     jh.a(Items.DIAMOND_HOE)
/*  977 */       .a(Character.valueOf('#'), Items.STICK)
/*  978 */       .a(Character.valueOf('X'), Items.DIAMOND)
/*  979 */       .a("XX")
/*  980 */       .a(" #")
/*  981 */       .a(" #")
/*  982 */       .a("has_diamond", a(Items.DIAMOND))
/*  983 */       .a(var0);
/*      */     
/*  985 */     jh.a(Items.DIAMOND_LEGGINGS)
/*  986 */       .a(Character.valueOf('X'), Items.DIAMOND)
/*  987 */       .a("XXX")
/*  988 */       .a("X X")
/*  989 */       .a("X X")
/*  990 */       .a("has_diamond", a(Items.DIAMOND))
/*  991 */       .a(var0);
/*      */     
/*  993 */     jh.a(Items.DIAMOND_PICKAXE)
/*  994 */       .a(Character.valueOf('#'), Items.STICK)
/*  995 */       .a(Character.valueOf('X'), Items.DIAMOND)
/*  996 */       .a("XXX")
/*  997 */       .a(" # ")
/*  998 */       .a(" # ")
/*  999 */       .a("has_diamond", a(Items.DIAMOND))
/* 1000 */       .a(var0);
/*      */     
/* 1002 */     jh.a(Items.DIAMOND_SHOVEL)
/* 1003 */       .a(Character.valueOf('#'), Items.STICK)
/* 1004 */       .a(Character.valueOf('X'), Items.DIAMOND)
/* 1005 */       .a("X")
/* 1006 */       .a("#")
/* 1007 */       .a("#")
/* 1008 */       .a("has_diamond", a(Items.DIAMOND))
/* 1009 */       .a(var0);
/*      */     
/* 1011 */     jh.a(Items.DIAMOND_SWORD)
/* 1012 */       .a(Character.valueOf('#'), Items.STICK)
/* 1013 */       .a(Character.valueOf('X'), Items.DIAMOND)
/* 1014 */       .a("X")
/* 1015 */       .a("X")
/* 1016 */       .a("#")
/* 1017 */       .a("has_diamond", a(Items.DIAMOND))
/* 1018 */       .a(var0);
/*      */     
/* 1020 */     jh.a(Blocks.DIORITE, 2)
/* 1021 */       .a(Character.valueOf('Q'), Items.QUARTZ)
/* 1022 */       .a(Character.valueOf('C'), Blocks.COBBLESTONE)
/* 1023 */       .a("CQ")
/* 1024 */       .a("QC")
/* 1025 */       .a("has_quartz", a(Items.QUARTZ))
/* 1026 */       .a(var0);
/*      */     
/* 1028 */     jh.a(Blocks.DISPENSER)
/* 1029 */       .a(Character.valueOf('R'), Items.REDSTONE)
/* 1030 */       .a(Character.valueOf('#'), Blocks.COBBLESTONE)
/* 1031 */       .a(Character.valueOf('X'), Items.BOW)
/* 1032 */       .a("###")
/* 1033 */       .a("#X#")
/* 1034 */       .a("#R#")
/* 1035 */       .a("has_bow", a(Items.BOW))
/* 1036 */       .a(var0);
/*      */     
/* 1038 */     jh.a(Blocks.DROPPER)
/* 1039 */       .a(Character.valueOf('R'), Items.REDSTONE)
/* 1040 */       .a(Character.valueOf('#'), Blocks.COBBLESTONE)
/* 1041 */       .a("###")
/* 1042 */       .a("# #")
/* 1043 */       .a("#R#")
/* 1044 */       .a("has_redstone", a(Items.REDSTONE))
/* 1045 */       .a(var0);
/*      */     
/* 1047 */     ji.a(Items.EMERALD, 9)
/* 1048 */       .b(Blocks.EMERALD_BLOCK)
/* 1049 */       .a("has_emerald_block", a(Blocks.EMERALD_BLOCK))
/* 1050 */       .a(var0);
/*      */     
/* 1052 */     jh.a(Blocks.EMERALD_BLOCK)
/* 1053 */       .a(Character.valueOf('#'), Items.EMERALD)
/* 1054 */       .a("###")
/* 1055 */       .a("###")
/* 1056 */       .a("###")
/* 1057 */       .a("has_emerald", a(Items.EMERALD))
/* 1058 */       .a(var0);
/*      */     
/* 1060 */     jh.a(Blocks.ENCHANTING_TABLE)
/* 1061 */       .a(Character.valueOf('B'), Items.BOOK)
/* 1062 */       .a(Character.valueOf('#'), Blocks.OBSIDIAN)
/* 1063 */       .a(Character.valueOf('D'), Items.DIAMOND)
/* 1064 */       .a(" B ")
/* 1065 */       .a("D#D")
/* 1066 */       .a("###")
/* 1067 */       .a("has_obsidian", a(Blocks.OBSIDIAN))
/* 1068 */       .a(var0);
/*      */     
/* 1070 */     jh.a(Blocks.ENDER_CHEST)
/* 1071 */       .a(Character.valueOf('#'), Blocks.OBSIDIAN)
/* 1072 */       .a(Character.valueOf('E'), Items.ENDER_EYE)
/* 1073 */       .a("###")
/* 1074 */       .a("#E#")
/* 1075 */       .a("###")
/* 1076 */       .a("has_ender_eye", a(Items.ENDER_EYE))
/* 1077 */       .a(var0);
/*      */     
/* 1079 */     ji.a(Items.ENDER_EYE)
/* 1080 */       .b(Items.ENDER_PEARL)
/* 1081 */       .b(Items.BLAZE_POWDER)
/* 1082 */       .a("has_blaze_powder", a(Items.BLAZE_POWDER))
/* 1083 */       .a(var0);
/*      */     
/* 1085 */     jh.a(Blocks.END_STONE_BRICKS, 4)
/* 1086 */       .a(Character.valueOf('#'), Blocks.END_STONE)
/* 1087 */       .a("##")
/* 1088 */       .a("##")
/* 1089 */       .a("has_end_stone", a(Blocks.END_STONE))
/* 1090 */       .a(var0);
/*      */     
/* 1092 */     jh.a(Items.END_CRYSTAL)
/* 1093 */       .a(Character.valueOf('T'), Items.GHAST_TEAR)
/* 1094 */       .a(Character.valueOf('E'), Items.ENDER_EYE)
/* 1095 */       .a(Character.valueOf('G'), Blocks.GLASS)
/* 1096 */       .a("GGG")
/* 1097 */       .a("GEG")
/* 1098 */       .a("GTG")
/* 1099 */       .a("has_ender_eye", a(Items.ENDER_EYE))
/* 1100 */       .a(var0);
/*      */     
/* 1102 */     jh.a(Blocks.END_ROD, 4)
/* 1103 */       .a(Character.valueOf('#'), Items.POPPED_CHORUS_FRUIT)
/* 1104 */       .a(Character.valueOf('/'), Items.BLAZE_ROD)
/* 1105 */       .a("/")
/* 1106 */       .a("#")
/* 1107 */       .a("has_chorus_fruit_popped", a(Items.POPPED_CHORUS_FRUIT))
/* 1108 */       .a(var0);
/*      */     
/* 1110 */     ji.a(Items.FERMENTED_SPIDER_EYE)
/* 1111 */       .b(Items.SPIDER_EYE)
/* 1112 */       .b(Blocks.BROWN_MUSHROOM)
/* 1113 */       .b(Items.SUGAR)
/* 1114 */       .a("has_spider_eye", a(Items.SPIDER_EYE))
/* 1115 */       .a(var0);
/*      */     
/* 1117 */     ji.a(Items.FIRE_CHARGE, 3)
/* 1118 */       .b(Items.GUNPOWDER)
/* 1119 */       .b(Items.BLAZE_POWDER)
/* 1120 */       .a(RecipeItemStack.a(new IMaterial[] { Items.COAL, Items.CHARCOAL
/* 1121 */           })).a("has_blaze_powder", a(Items.BLAZE_POWDER))
/* 1122 */       .a(var0);
/*      */     
/* 1124 */     jh.a(Items.FISHING_ROD)
/* 1125 */       .a(Character.valueOf('#'), Items.STICK)
/* 1126 */       .a(Character.valueOf('X'), Items.STRING)
/* 1127 */       .a("  #")
/* 1128 */       .a(" #X")
/* 1129 */       .a("# X")
/* 1130 */       .a("has_string", a(Items.STRING))
/* 1131 */       .a(var0);
/*      */     
/* 1133 */     ji.a(Items.FLINT_AND_STEEL)
/* 1134 */       .b(Items.IRON_INGOT)
/* 1135 */       .b(Items.FLINT)
/* 1136 */       .a("has_flint", a(Items.FLINT))
/* 1137 */       .a("has_obsidian", a(Blocks.OBSIDIAN))
/* 1138 */       .a(var0);
/*      */     
/* 1140 */     jh.a(Blocks.FLOWER_POT)
/* 1141 */       .a(Character.valueOf('#'), Items.BRICK)
/* 1142 */       .a("# #")
/* 1143 */       .a(" # ")
/* 1144 */       .a("has_brick", a(Items.BRICK))
/* 1145 */       .a(var0);
/*      */     
/* 1147 */     jh.a(Blocks.FURNACE)
/* 1148 */       .a(Character.valueOf('#'), TagsItem.STONE_CRAFTING_MATERIALS)
/* 1149 */       .a("###")
/* 1150 */       .a("# #")
/* 1151 */       .a("###")
/* 1152 */       .a("has_cobblestone", a(TagsItem.STONE_CRAFTING_MATERIALS))
/* 1153 */       .a(var0);
/*      */     
/* 1155 */     jh.a(Items.FURNACE_MINECART)
/* 1156 */       .a(Character.valueOf('A'), Blocks.FURNACE)
/* 1157 */       .a(Character.valueOf('B'), Items.MINECART)
/* 1158 */       .a("A")
/* 1159 */       .a("B")
/* 1160 */       .a("has_minecart", a(Items.MINECART))
/* 1161 */       .a(var0);
/*      */     
/* 1163 */     jh.a(Items.GLASS_BOTTLE, 3)
/* 1164 */       .a(Character.valueOf('#'), Blocks.GLASS)
/* 1165 */       .a("# #")
/* 1166 */       .a(" # ")
/* 1167 */       .a("has_glass", a(Blocks.GLASS))
/* 1168 */       .a(var0);
/*      */     
/* 1170 */     jh.a(Blocks.GLASS_PANE, 16)
/* 1171 */       .a(Character.valueOf('#'), Blocks.GLASS)
/* 1172 */       .a("###")
/* 1173 */       .a("###")
/* 1174 */       .a("has_glass", a(Blocks.GLASS))
/* 1175 */       .a(var0);
/*      */     
/* 1177 */     jh.a(Blocks.GLOWSTONE)
/* 1178 */       .a(Character.valueOf('#'), Items.GLOWSTONE_DUST)
/* 1179 */       .a("##")
/* 1180 */       .a("##")
/* 1181 */       .a("has_glowstone_dust", a(Items.GLOWSTONE_DUST))
/* 1182 */       .a(var0);
/*      */     
/* 1184 */     jh.a(Items.GOLDEN_APPLE)
/* 1185 */       .a(Character.valueOf('#'), Items.GOLD_INGOT)
/* 1186 */       .a(Character.valueOf('X'), Items.APPLE)
/* 1187 */       .a("###")
/* 1188 */       .a("#X#")
/* 1189 */       .a("###")
/* 1190 */       .a("has_gold_ingot", a(Items.GOLD_INGOT))
/* 1191 */       .a(var0);
/*      */     
/* 1193 */     jh.a(Items.GOLDEN_AXE)
/* 1194 */       .a(Character.valueOf('#'), Items.STICK)
/* 1195 */       .a(Character.valueOf('X'), Items.GOLD_INGOT)
/* 1196 */       .a("XX")
/* 1197 */       .a("X#")
/* 1198 */       .a(" #")
/* 1199 */       .a("has_gold_ingot", a(Items.GOLD_INGOT))
/* 1200 */       .a(var0);
/*      */     
/* 1202 */     jh.a(Items.GOLDEN_BOOTS)
/* 1203 */       .a(Character.valueOf('X'), Items.GOLD_INGOT)
/* 1204 */       .a("X X")
/* 1205 */       .a("X X")
/* 1206 */       .a("has_gold_ingot", a(Items.GOLD_INGOT))
/* 1207 */       .a(var0);
/*      */     
/* 1209 */     jh.a(Items.GOLDEN_CARROT)
/* 1210 */       .a(Character.valueOf('#'), Items.GOLD_NUGGET)
/* 1211 */       .a(Character.valueOf('X'), Items.CARROT)
/* 1212 */       .a("###")
/* 1213 */       .a("#X#")
/* 1214 */       .a("###")
/* 1215 */       .a("has_gold_nugget", a(Items.GOLD_NUGGET))
/* 1216 */       .a(var0);
/*      */     
/* 1218 */     jh.a(Items.GOLDEN_CHESTPLATE)
/* 1219 */       .a(Character.valueOf('X'), Items.GOLD_INGOT)
/* 1220 */       .a("X X")
/* 1221 */       .a("XXX")
/* 1222 */       .a("XXX")
/* 1223 */       .a("has_gold_ingot", a(Items.GOLD_INGOT))
/* 1224 */       .a(var0);
/*      */     
/* 1226 */     jh.a(Items.GOLDEN_HELMET)
/* 1227 */       .a(Character.valueOf('X'), Items.GOLD_INGOT)
/* 1228 */       .a("XXX")
/* 1229 */       .a("X X")
/* 1230 */       .a("has_gold_ingot", a(Items.GOLD_INGOT))
/* 1231 */       .a(var0);
/*      */     
/* 1233 */     jh.a(Items.GOLDEN_HOE)
/* 1234 */       .a(Character.valueOf('#'), Items.STICK)
/* 1235 */       .a(Character.valueOf('X'), Items.GOLD_INGOT)
/* 1236 */       .a("XX")
/* 1237 */       .a(" #")
/* 1238 */       .a(" #")
/* 1239 */       .a("has_gold_ingot", a(Items.GOLD_INGOT))
/* 1240 */       .a(var0);
/*      */     
/* 1242 */     jh.a(Items.GOLDEN_LEGGINGS)
/* 1243 */       .a(Character.valueOf('X'), Items.GOLD_INGOT)
/* 1244 */       .a("XXX")
/* 1245 */       .a("X X")
/* 1246 */       .a("X X")
/* 1247 */       .a("has_gold_ingot", a(Items.GOLD_INGOT))
/* 1248 */       .a(var0);
/*      */     
/* 1250 */     jh.a(Items.GOLDEN_PICKAXE)
/* 1251 */       .a(Character.valueOf('#'), Items.STICK)
/* 1252 */       .a(Character.valueOf('X'), Items.GOLD_INGOT)
/* 1253 */       .a("XXX")
/* 1254 */       .a(" # ")
/* 1255 */       .a(" # ")
/* 1256 */       .a("has_gold_ingot", a(Items.GOLD_INGOT))
/* 1257 */       .a(var0);
/*      */     
/* 1259 */     jh.a(Blocks.POWERED_RAIL, 6)
/* 1260 */       .a(Character.valueOf('R'), Items.REDSTONE)
/* 1261 */       .a(Character.valueOf('#'), Items.STICK)
/* 1262 */       .a(Character.valueOf('X'), Items.GOLD_INGOT)
/* 1263 */       .a("X X")
/* 1264 */       .a("X#X")
/* 1265 */       .a("XRX")
/* 1266 */       .a("has_rail", a(Blocks.RAIL))
/* 1267 */       .a(var0);
/*      */     
/* 1269 */     jh.a(Items.GOLDEN_SHOVEL)
/* 1270 */       .a(Character.valueOf('#'), Items.STICK)
/* 1271 */       .a(Character.valueOf('X'), Items.GOLD_INGOT)
/* 1272 */       .a("X")
/* 1273 */       .a("#")
/* 1274 */       .a("#")
/* 1275 */       .a("has_gold_ingot", a(Items.GOLD_INGOT))
/* 1276 */       .a(var0);
/*      */     
/* 1278 */     jh.a(Items.GOLDEN_SWORD)
/* 1279 */       .a(Character.valueOf('#'), Items.STICK)
/* 1280 */       .a(Character.valueOf('X'), Items.GOLD_INGOT)
/* 1281 */       .a("X")
/* 1282 */       .a("X")
/* 1283 */       .a("#")
/* 1284 */       .a("has_gold_ingot", a(Items.GOLD_INGOT))
/* 1285 */       .a(var0);
/*      */     
/* 1287 */     jh.a(Blocks.GOLD_BLOCK)
/* 1288 */       .a(Character.valueOf('#'), Items.GOLD_INGOT)
/* 1289 */       .a("###")
/* 1290 */       .a("###")
/* 1291 */       .a("###")
/* 1292 */       .a("has_gold_ingot", a(Items.GOLD_INGOT))
/* 1293 */       .a(var0);
/*      */     
/* 1295 */     ji.a(Items.GOLD_INGOT, 9)
/* 1296 */       .b(Blocks.GOLD_BLOCK)
/* 1297 */       .a("gold_ingot")
/* 1298 */       .a("has_gold_block", a(Blocks.GOLD_BLOCK))
/* 1299 */       .a(var0, "gold_ingot_from_gold_block");
/*      */     
/* 1301 */     jh.a(Items.GOLD_INGOT)
/* 1302 */       .a(Character.valueOf('#'), Items.GOLD_NUGGET)
/* 1303 */       .a("###")
/* 1304 */       .a("###")
/* 1305 */       .a("###")
/* 1306 */       .b("gold_ingot")
/* 1307 */       .a("has_gold_nugget", a(Items.GOLD_NUGGET))
/* 1308 */       .a(var0, "gold_ingot_from_nuggets");
/*      */     
/* 1310 */     ji.a(Items.GOLD_NUGGET, 9)
/* 1311 */       .b(Items.GOLD_INGOT)
/* 1312 */       .a("has_gold_ingot", a(Items.GOLD_INGOT))
/* 1313 */       .a(var0);
/*      */     
/* 1315 */     ji.a(Blocks.GRANITE)
/* 1316 */       .b(Blocks.DIORITE)
/* 1317 */       .b(Items.QUARTZ)
/* 1318 */       .a("has_quartz", a(Items.QUARTZ))
/* 1319 */       .a(var0);
/*      */     
/* 1321 */     ji.a(Items.GRAY_DYE, 2)
/* 1322 */       .b(Items.BLACK_DYE)
/* 1323 */       .b(Items.WHITE_DYE)
/* 1324 */       .a("has_white_dye", a(Items.WHITE_DYE))
/* 1325 */       .a("has_black_dye", a(Items.BLACK_DYE))
/* 1326 */       .a(var0);
/*      */     
/* 1328 */     jh.a(Blocks.HAY_BLOCK)
/* 1329 */       .a(Character.valueOf('#'), Items.WHEAT)
/* 1330 */       .a("###")
/* 1331 */       .a("###")
/* 1332 */       .a("###")
/* 1333 */       .a("has_wheat", a(Items.WHEAT))
/* 1334 */       .a(var0);
/*      */     
/* 1336 */     jh.a(Blocks.HEAVY_WEIGHTED_PRESSURE_PLATE)
/* 1337 */       .a(Character.valueOf('#'), Items.IRON_INGOT)
/* 1338 */       .a("##")
/* 1339 */       .a("has_iron_ingot", a(Items.IRON_INGOT))
/* 1340 */       .a(var0);
/*      */     
/* 1342 */     ji.a(Items.HONEY_BOTTLE, 4)
/* 1343 */       .b(Items.ru)
/* 1344 */       .b(Items.GLASS_BOTTLE, 4)
/* 1345 */       .a("has_honey_block", a(Blocks.HONEY_BLOCK))
/* 1346 */       .a(var0);
/*      */     
/* 1348 */     jh.a(Blocks.HONEY_BLOCK, 1)
/* 1349 */       .a(Character.valueOf('S'), Items.HONEY_BOTTLE)
/* 1350 */       .a("SS")
/* 1351 */       .a("SS")
/* 1352 */       .a("has_honey_bottle", a(Items.HONEY_BOTTLE))
/* 1353 */       .a(var0);
/*      */     
/* 1355 */     jh.a(Blocks.HONEYCOMB_BLOCK)
/* 1356 */       .a(Character.valueOf('H'), Items.HONEYCOMB)
/* 1357 */       .a("HH")
/* 1358 */       .a("HH")
/* 1359 */       .a("has_honeycomb", a(Items.HONEYCOMB))
/* 1360 */       .a(var0);
/*      */     
/* 1362 */     jh.a(Blocks.HOPPER)
/* 1363 */       .a(Character.valueOf('C'), Blocks.CHEST)
/* 1364 */       .a(Character.valueOf('I'), Items.IRON_INGOT)
/* 1365 */       .a("I I")
/* 1366 */       .a("ICI")
/* 1367 */       .a(" I ")
/* 1368 */       .a("has_iron_ingot", a(Items.IRON_INGOT))
/* 1369 */       .a(var0);
/*      */     
/* 1371 */     jh.a(Items.HOPPER_MINECART)
/* 1372 */       .a(Character.valueOf('A'), Blocks.HOPPER)
/* 1373 */       .a(Character.valueOf('B'), Items.MINECART)
/* 1374 */       .a("A")
/* 1375 */       .a("B")
/* 1376 */       .a("has_minecart", a(Items.MINECART))
/* 1377 */       .a(var0);
/*      */     
/* 1379 */     jh.a(Items.IRON_AXE)
/* 1380 */       .a(Character.valueOf('#'), Items.STICK)
/* 1381 */       .a(Character.valueOf('X'), Items.IRON_INGOT)
/* 1382 */       .a("XX")
/* 1383 */       .a("X#")
/* 1384 */       .a(" #")
/* 1385 */       .a("has_iron_ingot", a(Items.IRON_INGOT))
/* 1386 */       .a(var0);
/*      */     
/* 1388 */     jh.a(Blocks.IRON_BARS, 16)
/* 1389 */       .a(Character.valueOf('#'), Items.IRON_INGOT)
/* 1390 */       .a("###")
/* 1391 */       .a("###")
/* 1392 */       .a("has_iron_ingot", a(Items.IRON_INGOT))
/* 1393 */       .a(var0);
/*      */     
/* 1395 */     jh.a(Blocks.IRON_BLOCK)
/* 1396 */       .a(Character.valueOf('#'), Items.IRON_INGOT)
/* 1397 */       .a("###")
/* 1398 */       .a("###")
/* 1399 */       .a("###")
/* 1400 */       .a("has_iron_ingot", a(Items.IRON_INGOT))
/* 1401 */       .a(var0);
/*      */     
/* 1403 */     jh.a(Items.IRON_BOOTS)
/* 1404 */       .a(Character.valueOf('X'), Items.IRON_INGOT)
/* 1405 */       .a("X X")
/* 1406 */       .a("X X")
/* 1407 */       .a("has_iron_ingot", a(Items.IRON_INGOT))
/* 1408 */       .a(var0);
/*      */     
/* 1410 */     jh.a(Items.IRON_CHESTPLATE)
/* 1411 */       .a(Character.valueOf('X'), Items.IRON_INGOT)
/* 1412 */       .a("X X")
/* 1413 */       .a("XXX")
/* 1414 */       .a("XXX")
/* 1415 */       .a("has_iron_ingot", a(Items.IRON_INGOT))
/* 1416 */       .a(var0);
/*      */     
/* 1418 */     jh.a(Blocks.IRON_DOOR, 3)
/* 1419 */       .a(Character.valueOf('#'), Items.IRON_INGOT)
/* 1420 */       .a("##")
/* 1421 */       .a("##")
/* 1422 */       .a("##")
/* 1423 */       .a("has_iron_ingot", a(Items.IRON_INGOT))
/* 1424 */       .a(var0);
/*      */     
/* 1426 */     jh.a(Items.IRON_HELMET)
/* 1427 */       .a(Character.valueOf('X'), Items.IRON_INGOT)
/* 1428 */       .a("XXX")
/* 1429 */       .a("X X")
/* 1430 */       .a("has_iron_ingot", a(Items.IRON_INGOT))
/* 1431 */       .a(var0);
/*      */     
/* 1433 */     jh.a(Items.IRON_HOE)
/* 1434 */       .a(Character.valueOf('#'), Items.STICK)
/* 1435 */       .a(Character.valueOf('X'), Items.IRON_INGOT)
/* 1436 */       .a("XX")
/* 1437 */       .a(" #")
/* 1438 */       .a(" #")
/* 1439 */       .a("has_iron_ingot", a(Items.IRON_INGOT))
/* 1440 */       .a(var0);
/*      */     
/* 1442 */     ji.a(Items.IRON_INGOT, 9)
/* 1443 */       .b(Blocks.IRON_BLOCK)
/* 1444 */       .a("iron_ingot")
/* 1445 */       .a("has_iron_block", a(Blocks.IRON_BLOCK))
/* 1446 */       .a(var0, "iron_ingot_from_iron_block");
/*      */     
/* 1448 */     jh.a(Items.IRON_INGOT)
/* 1449 */       .a(Character.valueOf('#'), Items.IRON_NUGGET)
/* 1450 */       .a("###")
/* 1451 */       .a("###")
/* 1452 */       .a("###")
/* 1453 */       .b("iron_ingot")
/* 1454 */       .a("has_iron_nugget", a(Items.IRON_NUGGET))
/* 1455 */       .a(var0, "iron_ingot_from_nuggets");
/*      */     
/* 1457 */     jh.a(Items.IRON_LEGGINGS)
/* 1458 */       .a(Character.valueOf('X'), Items.IRON_INGOT)
/* 1459 */       .a("XXX")
/* 1460 */       .a("X X")
/* 1461 */       .a("X X")
/* 1462 */       .a("has_iron_ingot", a(Items.IRON_INGOT))
/* 1463 */       .a(var0);
/*      */     
/* 1465 */     ji.a(Items.IRON_NUGGET, 9)
/* 1466 */       .b(Items.IRON_INGOT)
/* 1467 */       .a("has_iron_ingot", a(Items.IRON_INGOT))
/* 1468 */       .a(var0);
/*      */     
/* 1470 */     jh.a(Items.IRON_PICKAXE)
/* 1471 */       .a(Character.valueOf('#'), Items.STICK)
/* 1472 */       .a(Character.valueOf('X'), Items.IRON_INGOT)
/* 1473 */       .a("XXX")
/* 1474 */       .a(" # ")
/* 1475 */       .a(" # ")
/* 1476 */       .a("has_iron_ingot", a(Items.IRON_INGOT))
/* 1477 */       .a(var0);
/*      */     
/* 1479 */     jh.a(Items.IRON_SHOVEL)
/* 1480 */       .a(Character.valueOf('#'), Items.STICK)
/* 1481 */       .a(Character.valueOf('X'), Items.IRON_INGOT)
/* 1482 */       .a("X")
/* 1483 */       .a("#")
/* 1484 */       .a("#")
/* 1485 */       .a("has_iron_ingot", a(Items.IRON_INGOT))
/* 1486 */       .a(var0);
/*      */     
/* 1488 */     jh.a(Items.IRON_SWORD)
/* 1489 */       .a(Character.valueOf('#'), Items.STICK)
/* 1490 */       .a(Character.valueOf('X'), Items.IRON_INGOT)
/* 1491 */       .a("X")
/* 1492 */       .a("X")
/* 1493 */       .a("#")
/* 1494 */       .a("has_iron_ingot", a(Items.IRON_INGOT))
/* 1495 */       .a(var0);
/*      */     
/* 1497 */     jh.a(Blocks.IRON_TRAPDOOR)
/* 1498 */       .a(Character.valueOf('#'), Items.IRON_INGOT)
/* 1499 */       .a("##")
/* 1500 */       .a("##")
/* 1501 */       .a("has_iron_ingot", a(Items.IRON_INGOT))
/* 1502 */       .a(var0);
/*      */     
/* 1504 */     jh.a(Items.ITEM_FRAME)
/* 1505 */       .a(Character.valueOf('#'), Items.STICK)
/* 1506 */       .a(Character.valueOf('X'), Items.LEATHER)
/* 1507 */       .a("###")
/* 1508 */       .a("#X#")
/* 1509 */       .a("###")
/* 1510 */       .a("has_leather", a(Items.LEATHER))
/* 1511 */       .a(var0);
/*      */     
/* 1513 */     jh.a(Blocks.JUKEBOX)
/* 1514 */       .a(Character.valueOf('#'), TagsItem.PLANKS)
/* 1515 */       .a(Character.valueOf('X'), Items.DIAMOND)
/* 1516 */       .a("###")
/* 1517 */       .a("#X#")
/* 1518 */       .a("###")
/* 1519 */       .a("has_diamond", a(Items.DIAMOND))
/* 1520 */       .a(var0);
/*      */     
/* 1522 */     jh.a(Blocks.LADDER, 3)
/* 1523 */       .a(Character.valueOf('#'), Items.STICK)
/* 1524 */       .a("# #")
/* 1525 */       .a("###")
/* 1526 */       .a("# #")
/* 1527 */       .a("has_stick", a(Items.STICK))
/* 1528 */       .a(var0);
/*      */     
/* 1530 */     jh.a(Blocks.LAPIS_BLOCK)
/* 1531 */       .a(Character.valueOf('#'), Items.LAPIS_LAZULI)
/* 1532 */       .a("###")
/* 1533 */       .a("###")
/* 1534 */       .a("###")
/* 1535 */       .a("has_lapis", a(Items.LAPIS_LAZULI))
/* 1536 */       .a(var0);
/*      */     
/* 1538 */     ji.a(Items.LAPIS_LAZULI, 9)
/* 1539 */       .b(Blocks.LAPIS_BLOCK)
/* 1540 */       .a("has_lapis_block", a(Blocks.LAPIS_BLOCK))
/* 1541 */       .a(var0);
/*      */     
/* 1543 */     jh.a(Items.LEAD, 2)
/* 1544 */       .a(Character.valueOf('~'), Items.STRING)
/* 1545 */       .a(Character.valueOf('O'), Items.SLIME_BALL)
/* 1546 */       .a("~~ ")
/* 1547 */       .a("~O ")
/* 1548 */       .a("  ~")
/* 1549 */       .a("has_slime_ball", a(Items.SLIME_BALL))
/* 1550 */       .a(var0);
/*      */     
/* 1552 */     jh.a(Items.LEATHER)
/* 1553 */       .a(Character.valueOf('#'), Items.RABBIT_HIDE)
/* 1554 */       .a("##")
/* 1555 */       .a("##")
/* 1556 */       .a("has_rabbit_hide", a(Items.RABBIT_HIDE))
/* 1557 */       .a(var0);
/*      */     
/* 1559 */     jh.a(Items.LEATHER_BOOTS)
/* 1560 */       .a(Character.valueOf('X'), Items.LEATHER)
/* 1561 */       .a("X X")
/* 1562 */       .a("X X")
/* 1563 */       .a("has_leather", a(Items.LEATHER))
/* 1564 */       .a(var0);
/*      */     
/* 1566 */     jh.a(Items.LEATHER_CHESTPLATE)
/* 1567 */       .a(Character.valueOf('X'), Items.LEATHER)
/* 1568 */       .a("X X")
/* 1569 */       .a("XXX")
/* 1570 */       .a("XXX")
/* 1571 */       .a("has_leather", a(Items.LEATHER))
/* 1572 */       .a(var0);
/*      */     
/* 1574 */     jh.a(Items.LEATHER_HELMET)
/* 1575 */       .a(Character.valueOf('X'), Items.LEATHER)
/* 1576 */       .a("XXX")
/* 1577 */       .a("X X")
/* 1578 */       .a("has_leather", a(Items.LEATHER))
/* 1579 */       .a(var0);
/*      */     
/* 1581 */     jh.a(Items.LEATHER_LEGGINGS)
/* 1582 */       .a(Character.valueOf('X'), Items.LEATHER)
/* 1583 */       .a("XXX")
/* 1584 */       .a("X X")
/* 1585 */       .a("X X")
/* 1586 */       .a("has_leather", a(Items.LEATHER))
/* 1587 */       .a(var0);
/*      */     
/* 1589 */     jh.a(Items.LEATHER_HORSE_ARMOR)
/* 1590 */       .a(Character.valueOf('X'), Items.LEATHER)
/* 1591 */       .a("X X")
/* 1592 */       .a("XXX")
/* 1593 */       .a("X X")
/* 1594 */       .a("has_leather", a(Items.LEATHER))
/* 1595 */       .a(var0);
/*      */     
/* 1597 */     jh.a(Blocks.LECTERN)
/* 1598 */       .a(Character.valueOf('S'), TagsItem.WOODEN_SLABS)
/* 1599 */       .a(Character.valueOf('B'), Blocks.BOOKSHELF)
/* 1600 */       .a("SSS")
/* 1601 */       .a(" B ")
/* 1602 */       .a(" S ")
/* 1603 */       .a("has_book", a(Items.BOOK))
/* 1604 */       .a(var0);
/*      */     
/* 1606 */     jh.a(Blocks.LEVER)
/* 1607 */       .a(Character.valueOf('#'), Blocks.COBBLESTONE)
/* 1608 */       .a(Character.valueOf('X'), Items.STICK)
/* 1609 */       .a("X")
/* 1610 */       .a("#")
/* 1611 */       .a("has_cobblestone", a(Blocks.COBBLESTONE))
/* 1612 */       .a(var0);
/*      */     
/* 1614 */     ji.a(Items.LIGHT_BLUE_DYE)
/* 1615 */       .b(Blocks.BLUE_ORCHID)
/* 1616 */       .a("light_blue_dye")
/* 1617 */       .a("has_red_flower", a(Blocks.BLUE_ORCHID))
/* 1618 */       .a(var0, "light_blue_dye_from_blue_orchid");
/*      */     
/* 1620 */     ji.a(Items.LIGHT_BLUE_DYE, 2)
/* 1621 */       .b(Items.BLUE_DYE)
/* 1622 */       .b(Items.WHITE_DYE)
/* 1623 */       .a("light_blue_dye")
/* 1624 */       .a("has_blue_dye", a(Items.BLUE_DYE))
/* 1625 */       .a("has_white_dye", a(Items.WHITE_DYE))
/* 1626 */       .a(var0, "light_blue_dye_from_blue_white_dye");
/*      */     
/* 1628 */     ji.a(Items.LIGHT_GRAY_DYE)
/* 1629 */       .b(Blocks.AZURE_BLUET)
/* 1630 */       .a("light_gray_dye")
/* 1631 */       .a("has_red_flower", a(Blocks.AZURE_BLUET))
/* 1632 */       .a(var0, "light_gray_dye_from_azure_bluet");
/*      */     
/* 1634 */     ji.a(Items.LIGHT_GRAY_DYE, 2)
/* 1635 */       .b(Items.GRAY_DYE)
/* 1636 */       .b(Items.WHITE_DYE)
/* 1637 */       .a("light_gray_dye")
/* 1638 */       .a("has_gray_dye", a(Items.GRAY_DYE))
/* 1639 */       .a("has_white_dye", a(Items.WHITE_DYE))
/* 1640 */       .a(var0, "light_gray_dye_from_gray_white_dye");
/*      */     
/* 1642 */     ji.a(Items.LIGHT_GRAY_DYE, 3)
/* 1643 */       .b(Items.BLACK_DYE)
/* 1644 */       .b(Items.WHITE_DYE, 2)
/* 1645 */       .a("light_gray_dye")
/* 1646 */       .a("has_white_dye", a(Items.WHITE_DYE))
/* 1647 */       .a("has_black_dye", a(Items.BLACK_DYE))
/* 1648 */       .a(var0, "light_gray_dye_from_black_white_dye");
/*      */     
/* 1650 */     ji.a(Items.LIGHT_GRAY_DYE)
/* 1651 */       .b(Blocks.OXEYE_DAISY)
/* 1652 */       .a("light_gray_dye")
/* 1653 */       .a("has_red_flower", a(Blocks.OXEYE_DAISY))
/* 1654 */       .a(var0, "light_gray_dye_from_oxeye_daisy");
/*      */     
/* 1656 */     ji.a(Items.LIGHT_GRAY_DYE)
/* 1657 */       .b(Blocks.WHITE_TULIP)
/* 1658 */       .a("light_gray_dye")
/* 1659 */       .a("has_red_flower", a(Blocks.WHITE_TULIP))
/* 1660 */       .a(var0, "light_gray_dye_from_white_tulip");
/*      */     
/* 1662 */     jh.a(Blocks.LIGHT_WEIGHTED_PRESSURE_PLATE)
/* 1663 */       .a(Character.valueOf('#'), Items.GOLD_INGOT)
/* 1664 */       .a("##")
/* 1665 */       .a("has_gold_ingot", a(Items.GOLD_INGOT))
/* 1666 */       .a(var0);
/*      */     
/* 1668 */     ji.a(Items.LIME_DYE, 2)
/* 1669 */       .b(Items.GREEN_DYE)
/* 1670 */       .b(Items.WHITE_DYE)
/* 1671 */       .a("has_green_dye", a(Items.GREEN_DYE))
/* 1672 */       .a("has_white_dye", a(Items.WHITE_DYE))
/* 1673 */       .a(var0);
/*      */     
/* 1675 */     jh.a(Blocks.JACK_O_LANTERN)
/* 1676 */       .a(Character.valueOf('A'), Blocks.CARVED_PUMPKIN)
/* 1677 */       .a(Character.valueOf('B'), Blocks.TORCH)
/* 1678 */       .a("A")
/* 1679 */       .a("B")
/* 1680 */       .a("has_carved_pumpkin", a(Blocks.CARVED_PUMPKIN))
/* 1681 */       .a(var0);
/*      */     
/* 1683 */     ji.a(Items.MAGENTA_DYE)
/* 1684 */       .b(Blocks.ALLIUM)
/* 1685 */       .a("magenta_dye")
/* 1686 */       .a("has_red_flower", a(Blocks.ALLIUM))
/* 1687 */       .a(var0, "magenta_dye_from_allium");
/*      */     
/* 1689 */     ji.a(Items.MAGENTA_DYE, 4)
/* 1690 */       .b(Items.BLUE_DYE)
/* 1691 */       .b(Items.RED_DYE, 2)
/* 1692 */       .b(Items.WHITE_DYE)
/* 1693 */       .a("magenta_dye")
/* 1694 */       .a("has_blue_dye", a(Items.BLUE_DYE))
/* 1695 */       .a("has_rose_red", a(Items.RED_DYE))
/* 1696 */       .a("has_white_dye", a(Items.WHITE_DYE))
/* 1697 */       .a(var0, "magenta_dye_from_blue_red_white_dye");
/*      */     
/* 1699 */     ji.a(Items.MAGENTA_DYE, 3)
/* 1700 */       .b(Items.BLUE_DYE)
/* 1701 */       .b(Items.RED_DYE)
/* 1702 */       .b(Items.PINK_DYE)
/* 1703 */       .a("magenta_dye")
/* 1704 */       .a("has_pink_dye", a(Items.PINK_DYE))
/* 1705 */       .a("has_blue_dye", a(Items.BLUE_DYE))
/* 1706 */       .a("has_red_dye", a(Items.RED_DYE))
/* 1707 */       .a(var0, "magenta_dye_from_blue_red_pink");
/*      */     
/* 1709 */     ji.a(Items.MAGENTA_DYE, 2)
/* 1710 */       .b(Blocks.LILAC)
/* 1711 */       .a("magenta_dye")
/* 1712 */       .a("has_double_plant", a(Blocks.LILAC))
/* 1713 */       .a(var0, "magenta_dye_from_lilac");
/*      */     
/* 1715 */     ji.a(Items.MAGENTA_DYE, 2)
/* 1716 */       .b(Items.PURPLE_DYE)
/* 1717 */       .b(Items.PINK_DYE)
/* 1718 */       .a("magenta_dye")
/* 1719 */       .a("has_pink_dye", a(Items.PINK_DYE))
/* 1720 */       .a("has_purple_dye", a(Items.PURPLE_DYE))
/* 1721 */       .a(var0, "magenta_dye_from_purple_and_pink");
/*      */     
/* 1723 */     jh.a(Blocks.MAGMA_BLOCK)
/* 1724 */       .a(Character.valueOf('#'), Items.MAGMA_CREAM)
/* 1725 */       .a("##")
/* 1726 */       .a("##")
/* 1727 */       .a("has_magma_cream", a(Items.MAGMA_CREAM))
/* 1728 */       .a(var0);
/*      */     
/* 1730 */     ji.a(Items.MAGMA_CREAM)
/* 1731 */       .b(Items.BLAZE_POWDER)
/* 1732 */       .b(Items.SLIME_BALL)
/* 1733 */       .a("has_blaze_powder", a(Items.BLAZE_POWDER))
/* 1734 */       .a(var0);
/*      */     
/* 1736 */     jh.a(Items.MAP)
/* 1737 */       .a(Character.valueOf('#'), Items.PAPER)
/* 1738 */       .a(Character.valueOf('X'), Items.COMPASS)
/* 1739 */       .a("###")
/* 1740 */       .a("#X#")
/* 1741 */       .a("###")
/* 1742 */       .a("has_compass", a(Items.COMPASS))
/* 1743 */       .a(var0);
/*      */     
/* 1745 */     jh.a(Blocks.MELON)
/* 1746 */       .a(Character.valueOf('M'), Items.MELON_SLICE)
/* 1747 */       .a("MMM")
/* 1748 */       .a("MMM")
/* 1749 */       .a("MMM")
/* 1750 */       .a("has_melon", a(Items.MELON_SLICE))
/* 1751 */       .a(var0);
/*      */     
/* 1753 */     ji.a(Items.MELON_SEEDS)
/* 1754 */       .b(Items.MELON_SLICE)
/* 1755 */       .a("has_melon", a(Items.MELON_SLICE))
/* 1756 */       .a(var0);
/*      */     
/* 1758 */     jh.a(Items.MINECART)
/* 1759 */       .a(Character.valueOf('#'), Items.IRON_INGOT)
/* 1760 */       .a("# #")
/* 1761 */       .a("###")
/* 1762 */       .a("has_iron_ingot", a(Items.IRON_INGOT))
/* 1763 */       .a(var0);
/*      */     
/* 1765 */     ji.a(Blocks.MOSSY_COBBLESTONE)
/* 1766 */       .b(Blocks.COBBLESTONE)
/* 1767 */       .b(Blocks.VINE)
/* 1768 */       .a("has_vine", a(Blocks.VINE))
/* 1769 */       .a(var0);
/*      */     
/* 1771 */     jh.a(Blocks.MOSSY_COBBLESTONE_WALL, 6)
/* 1772 */       .a(Character.valueOf('#'), Blocks.MOSSY_COBBLESTONE)
/* 1773 */       .a("###")
/* 1774 */       .a("###")
/* 1775 */       .a("has_mossy_cobblestone", a(Blocks.MOSSY_COBBLESTONE))
/* 1776 */       .a(var0);
/*      */     
/* 1778 */     ji.a(Blocks.MOSSY_STONE_BRICKS)
/* 1779 */       .b(Blocks.STONE_BRICKS)
/* 1780 */       .b(Blocks.VINE)
/* 1781 */       .a("has_mossy_cobblestone", a(Blocks.MOSSY_COBBLESTONE))
/* 1782 */       .a(var0);
/*      */     
/* 1784 */     ji.a(Items.MUSHROOM_STEW)
/* 1785 */       .b(Blocks.BROWN_MUSHROOM)
/* 1786 */       .b(Blocks.RED_MUSHROOM)
/* 1787 */       .b(Items.BOWL)
/* 1788 */       .a("has_mushroom_stew", a(Items.MUSHROOM_STEW))
/* 1789 */       .a("has_bowl", a(Items.BOWL))
/* 1790 */       .a("has_brown_mushroom", a(Blocks.BROWN_MUSHROOM))
/* 1791 */       .a("has_red_mushroom", a(Blocks.RED_MUSHROOM))
/* 1792 */       .a(var0);
/*      */     
/* 1794 */     jh.a(Blocks.NETHER_BRICKS)
/* 1795 */       .a(Character.valueOf('N'), Items.NETHER_BRICK)
/* 1796 */       .a("NN")
/* 1797 */       .a("NN")
/* 1798 */       .a("has_netherbrick", a(Items.NETHER_BRICK))
/* 1799 */       .a(var0);
/*      */     
/* 1801 */     jh.a(Blocks.NETHER_BRICK_FENCE, 6)
/* 1802 */       .a(Character.valueOf('#'), Blocks.NETHER_BRICKS)
/* 1803 */       .a(Character.valueOf('-'), Items.NETHER_BRICK)
/* 1804 */       .a("#-#")
/* 1805 */       .a("#-#")
/* 1806 */       .a("has_nether_brick", a(Blocks.NETHER_BRICKS))
/* 1807 */       .a(var0);
/*      */     
/* 1809 */     jh.a(Blocks.NETHER_BRICK_SLAB, 6)
/* 1810 */       .a(Character.valueOf('#'), Blocks.NETHER_BRICKS)
/* 1811 */       .a("###")
/* 1812 */       .a("has_nether_brick", a(Blocks.NETHER_BRICKS))
/* 1813 */       .a(var0);
/*      */     
/* 1815 */     jh.a(Blocks.NETHER_BRICK_STAIRS, 4)
/* 1816 */       .a(Character.valueOf('#'), Blocks.NETHER_BRICKS)
/* 1817 */       .a("#  ")
/* 1818 */       .a("## ")
/* 1819 */       .a("###")
/* 1820 */       .a("has_nether_brick", a(Blocks.NETHER_BRICKS))
/* 1821 */       .a(var0);
/*      */     
/* 1823 */     jh.a(Blocks.NETHER_WART_BLOCK)
/* 1824 */       .a(Character.valueOf('#'), Items.NETHER_WART)
/* 1825 */       .a("###")
/* 1826 */       .a("###")
/* 1827 */       .a("###")
/* 1828 */       .a("has_nether_wart", a(Items.NETHER_WART))
/* 1829 */       .a(var0);
/*      */     
/* 1831 */     jh.a(Blocks.NOTE_BLOCK)
/* 1832 */       .a(Character.valueOf('#'), TagsItem.PLANKS)
/* 1833 */       .a(Character.valueOf('X'), Items.REDSTONE)
/* 1834 */       .a("###")
/* 1835 */       .a("#X#")
/* 1836 */       .a("###")
/* 1837 */       .a("has_redstone", a(Items.REDSTONE))
/* 1838 */       .a(var0);
/*      */     
/* 1840 */     jh.a(Blocks.OBSERVER)
/* 1841 */       .a(Character.valueOf('Q'), Items.QUARTZ)
/* 1842 */       .a(Character.valueOf('R'), Items.REDSTONE)
/* 1843 */       .a(Character.valueOf('#'), Blocks.COBBLESTONE)
/* 1844 */       .a("###")
/* 1845 */       .a("RRQ")
/* 1846 */       .a("###")
/* 1847 */       .a("has_quartz", a(Items.QUARTZ))
/* 1848 */       .a(var0);
/*      */     
/* 1850 */     ji.a(Items.ORANGE_DYE)
/* 1851 */       .b(Blocks.ORANGE_TULIP)
/* 1852 */       .a("orange_dye")
/* 1853 */       .a("has_red_flower", a(Blocks.ORANGE_TULIP))
/* 1854 */       .a(var0, "orange_dye_from_orange_tulip");
/*      */     
/* 1856 */     ji.a(Items.ORANGE_DYE, 2)
/* 1857 */       .b(Items.RED_DYE)
/* 1858 */       .b(Items.YELLOW_DYE)
/* 1859 */       .a("orange_dye")
/* 1860 */       .a("has_red_dye", a(Items.RED_DYE))
/* 1861 */       .a("has_yellow_dye", a(Items.YELLOW_DYE))
/* 1862 */       .a(var0, "orange_dye_from_red_yellow");
/*      */     
/* 1864 */     jh.a(Items.PAINTING)
/* 1865 */       .a(Character.valueOf('#'), Items.STICK)
/* 1866 */       .a(Character.valueOf('X'), RecipeItemStack.a(TagsItem.WOOL))
/* 1867 */       .a("###")
/* 1868 */       .a("#X#")
/* 1869 */       .a("###")
/* 1870 */       .a("has_wool", a(TagsItem.WOOL))
/* 1871 */       .a(var0);
/*      */     
/* 1873 */     jh.a(Items.PAPER, 3)
/* 1874 */       .a(Character.valueOf('#'), Blocks.SUGAR_CANE)
/* 1875 */       .a("###")
/* 1876 */       .a("has_reeds", a(Blocks.SUGAR_CANE))
/* 1877 */       .a(var0);
/*      */     
/* 1879 */     jh.a(Blocks.QUARTZ_PILLAR, 2)
/* 1880 */       .a(Character.valueOf('#'), Blocks.QUARTZ_BLOCK)
/* 1881 */       .a("#")
/* 1882 */       .a("#")
/* 1883 */       .a("has_chiseled_quartz_block", a(Blocks.CHISELED_QUARTZ_BLOCK))
/* 1884 */       .a("has_quartz_block", a(Blocks.QUARTZ_BLOCK))
/* 1885 */       .a("has_quartz_pillar", a(Blocks.QUARTZ_PILLAR))
/* 1886 */       .a(var0);
/*      */     
/* 1888 */     ji.a(Blocks.PACKED_ICE)
/* 1889 */       .b(Blocks.ICE, 9)
/* 1890 */       .a("has_ice", a(Blocks.ICE))
/* 1891 */       .a(var0);
/*      */     
/* 1893 */     ji.a(Items.PINK_DYE, 2)
/* 1894 */       .b(Blocks.PEONY)
/* 1895 */       .a("pink_dye")
/* 1896 */       .a("has_double_plant", a(Blocks.PEONY))
/* 1897 */       .a(var0, "pink_dye_from_peony");
/*      */     
/* 1899 */     ji.a(Items.PINK_DYE)
/* 1900 */       .b(Blocks.PINK_TULIP)
/* 1901 */       .a("pink_dye")
/* 1902 */       .a("has_red_flower", a(Blocks.PINK_TULIP))
/* 1903 */       .a(var0, "pink_dye_from_pink_tulip");
/*      */     
/* 1905 */     ji.a(Items.PINK_DYE, 2)
/* 1906 */       .b(Items.RED_DYE)
/* 1907 */       .b(Items.WHITE_DYE)
/* 1908 */       .a("pink_dye")
/* 1909 */       .a("has_white_dye", a(Items.WHITE_DYE))
/* 1910 */       .a("has_red_dye", a(Items.RED_DYE))
/* 1911 */       .a(var0, "pink_dye_from_red_white_dye");
/*      */     
/* 1913 */     jh.a(Blocks.PISTON)
/* 1914 */       .a(Character.valueOf('R'), Items.REDSTONE)
/* 1915 */       .a(Character.valueOf('#'), Blocks.COBBLESTONE)
/* 1916 */       .a(Character.valueOf('T'), TagsItem.PLANKS)
/* 1917 */       .a(Character.valueOf('X'), Items.IRON_INGOT)
/* 1918 */       .a("TTT")
/* 1919 */       .a("#X#")
/* 1920 */       .a("#R#")
/* 1921 */       .a("has_redstone", a(Items.REDSTONE))
/* 1922 */       .a(var0);
/*      */     
/* 1924 */     jh.a(Blocks.cP, 4)
/* 1925 */       .a(Character.valueOf('S'), Blocks.BASALT)
/* 1926 */       .a("SS")
/* 1927 */       .a("SS")
/* 1928 */       .a("has_basalt", a(Blocks.BASALT))
/* 1929 */       .a(var0);
/*      */ 
/*      */     
/* 1932 */     jh.a(Blocks.POLISHED_GRANITE, 4)
/* 1933 */       .a(Character.valueOf('S'), Blocks.GRANITE)
/* 1934 */       .a("SS")
/* 1935 */       .a("SS")
/* 1936 */       .a("has_stone", a(Blocks.GRANITE))
/* 1937 */       .a(var0);
/*      */     
/* 1939 */     jh.a(Blocks.POLISHED_DIORITE, 4)
/* 1940 */       .a(Character.valueOf('S'), Blocks.DIORITE)
/* 1941 */       .a("SS")
/* 1942 */       .a("SS")
/* 1943 */       .a("has_stone", a(Blocks.DIORITE))
/* 1944 */       .a(var0);
/*      */     
/* 1946 */     jh.a(Blocks.POLISHED_ANDESITE, 4)
/* 1947 */       .a(Character.valueOf('S'), Blocks.ANDESITE)
/* 1948 */       .a("SS")
/* 1949 */       .a("SS")
/* 1950 */       .a("has_stone", a(Blocks.ANDESITE))
/* 1951 */       .a(var0);
/*      */     
/* 1953 */     jh.a(Blocks.PRISMARINE)
/* 1954 */       .a(Character.valueOf('S'), Items.PRISMARINE_SHARD)
/* 1955 */       .a("SS")
/* 1956 */       .a("SS")
/* 1957 */       .a("has_prismarine_shard", a(Items.PRISMARINE_SHARD))
/* 1958 */       .a(var0);
/*      */     
/* 1960 */     jh.a(Blocks.PRISMARINE_BRICKS)
/* 1961 */       .a(Character.valueOf('S'), Items.PRISMARINE_SHARD)
/* 1962 */       .a("SSS")
/* 1963 */       .a("SSS")
/* 1964 */       .a("SSS")
/* 1965 */       .a("has_prismarine_shard", a(Items.PRISMARINE_SHARD))
/* 1966 */       .a(var0);
/*      */     
/* 1968 */     jh.a(Blocks.PRISMARINE_SLAB, 6)
/* 1969 */       .a(Character.valueOf('#'), Blocks.PRISMARINE)
/* 1970 */       .a("###")
/* 1971 */       .a("has_prismarine", a(Blocks.PRISMARINE))
/* 1972 */       .a(var0);
/*      */     
/* 1974 */     jh.a(Blocks.PRISMARINE_BRICK_SLAB, 6)
/* 1975 */       .a(Character.valueOf('#'), Blocks.PRISMARINE_BRICKS)
/* 1976 */       .a("###")
/* 1977 */       .a("has_prismarine_bricks", a(Blocks.PRISMARINE_BRICKS))
/* 1978 */       .a(var0);
/*      */     
/* 1980 */     jh.a(Blocks.DARK_PRISMARINE_SLAB, 6)
/* 1981 */       .a(Character.valueOf('#'), Blocks.DARK_PRISMARINE)
/* 1982 */       .a("###")
/* 1983 */       .a("has_dark_prismarine", a(Blocks.DARK_PRISMARINE))
/* 1984 */       .a(var0);
/*      */     
/* 1986 */     ji.a(Items.PUMPKIN_PIE)
/* 1987 */       .b(Blocks.PUMPKIN)
/* 1988 */       .b(Items.SUGAR)
/* 1989 */       .b(Items.EGG)
/* 1990 */       .a("has_carved_pumpkin", a(Blocks.CARVED_PUMPKIN))
/* 1991 */       .a("has_pumpkin", a(Blocks.PUMPKIN))
/* 1992 */       .a(var0);
/*      */     
/* 1994 */     ji.a(Items.PUMPKIN_SEEDS, 4)
/* 1995 */       .b(Blocks.PUMPKIN)
/* 1996 */       .a("has_pumpkin", a(Blocks.PUMPKIN))
/* 1997 */       .a(var0);
/*      */     
/* 1999 */     ji.a(Items.PURPLE_DYE, 2)
/* 2000 */       .b(Items.BLUE_DYE)
/* 2001 */       .b(Items.RED_DYE)
/* 2002 */       .a("has_blue_dye", a(Items.BLUE_DYE))
/* 2003 */       .a("has_red_dye", a(Items.RED_DYE))
/* 2004 */       .a(var0);
/*      */     
/* 2006 */     jh.a(Blocks.SHULKER_BOX)
/* 2007 */       .a(Character.valueOf('#'), Blocks.CHEST)
/* 2008 */       .a(Character.valueOf('-'), Items.SHULKER_SHELL)
/* 2009 */       .a("-")
/* 2010 */       .a("#")
/* 2011 */       .a("-")
/* 2012 */       .a("has_shulker_shell", a(Items.SHULKER_SHELL))
/* 2013 */       .a(var0);
/*      */     
/* 2015 */     jh.a(Blocks.PURPUR_BLOCK, 4)
/* 2016 */       .a(Character.valueOf('F'), Items.POPPED_CHORUS_FRUIT)
/* 2017 */       .a("FF")
/* 2018 */       .a("FF")
/* 2019 */       .a("has_chorus_fruit_popped", a(Items.POPPED_CHORUS_FRUIT))
/* 2020 */       .a(var0);
/*      */     
/* 2022 */     jh.a(Blocks.PURPUR_PILLAR)
/* 2023 */       .a(Character.valueOf('#'), Blocks.PURPUR_SLAB)
/* 2024 */       .a("#")
/* 2025 */       .a("#")
/* 2026 */       .a("has_purpur_block", a(Blocks.PURPUR_BLOCK))
/* 2027 */       .a(var0);
/*      */     
/* 2029 */     jh.a(Blocks.PURPUR_SLAB, 6)
/* 2030 */       .a(Character.valueOf('#'), RecipeItemStack.a(new IMaterial[] { Blocks.PURPUR_BLOCK, Blocks.PURPUR_PILLAR
/* 2031 */           })).a("###")
/* 2032 */       .a("has_purpur_block", a(Blocks.PURPUR_BLOCK))
/* 2033 */       .a(var0);
/*      */     
/* 2035 */     jh.a(Blocks.PURPUR_STAIRS, 4)
/* 2036 */       .a(Character.valueOf('#'), RecipeItemStack.a(new IMaterial[] { Blocks.PURPUR_BLOCK, Blocks.PURPUR_PILLAR
/* 2037 */           })).a("#  ")
/* 2038 */       .a("## ")
/* 2039 */       .a("###")
/* 2040 */       .a("has_purpur_block", a(Blocks.PURPUR_BLOCK))
/* 2041 */       .a(var0);
/*      */     
/* 2043 */     jh.a(Blocks.QUARTZ_BLOCK)
/* 2044 */       .a(Character.valueOf('#'), Items.QUARTZ)
/* 2045 */       .a("##")
/* 2046 */       .a("##")
/* 2047 */       .a("has_quartz", a(Items.QUARTZ))
/* 2048 */       .a(var0);
/*      */     
/* 2050 */     jh.a(Blocks.QUARTZ_BRICKS, 4)
/* 2051 */       .a(Character.valueOf('#'), Blocks.QUARTZ_BLOCK)
/* 2052 */       .a("##")
/* 2053 */       .a("##")
/* 2054 */       .a("has_quartz_block", a(Blocks.QUARTZ_BLOCK))
/* 2055 */       .a(var0);
/*      */     
/* 2057 */     jh.a(Blocks.QUARTZ_SLAB, 6)
/* 2058 */       .a(Character.valueOf('#'), RecipeItemStack.a(new IMaterial[] { Blocks.CHISELED_QUARTZ_BLOCK, Blocks.QUARTZ_BLOCK, Blocks.QUARTZ_PILLAR
/* 2059 */           })).a("###")
/* 2060 */       .a("has_chiseled_quartz_block", a(Blocks.CHISELED_QUARTZ_BLOCK))
/* 2061 */       .a("has_quartz_block", a(Blocks.QUARTZ_BLOCK))
/* 2062 */       .a("has_quartz_pillar", a(Blocks.QUARTZ_PILLAR))
/* 2063 */       .a(var0);
/*      */     
/* 2065 */     jh.a(Blocks.QUARTZ_STAIRS, 4)
/* 2066 */       .a(Character.valueOf('#'), RecipeItemStack.a(new IMaterial[] { Blocks.CHISELED_QUARTZ_BLOCK, Blocks.QUARTZ_BLOCK, Blocks.QUARTZ_PILLAR
/* 2067 */           })).a("#  ")
/* 2068 */       .a("## ")
/* 2069 */       .a("###")
/* 2070 */       .a("has_chiseled_quartz_block", a(Blocks.CHISELED_QUARTZ_BLOCK))
/* 2071 */       .a("has_quartz_block", a(Blocks.QUARTZ_BLOCK))
/* 2072 */       .a("has_quartz_pillar", a(Blocks.QUARTZ_PILLAR))
/* 2073 */       .a(var0);
/*      */     
/* 2075 */     ji.a(Items.RABBIT_STEW)
/* 2076 */       .b(Items.BAKED_POTATO)
/* 2077 */       .b(Items.COOKED_RABBIT)
/* 2078 */       .b(Items.BOWL)
/* 2079 */       .b(Items.CARROT)
/* 2080 */       .b(Blocks.BROWN_MUSHROOM)
/* 2081 */       .a("rabbit_stew")
/* 2082 */       .a("has_cooked_rabbit", a(Items.COOKED_RABBIT))
/* 2083 */       .a(var0, "rabbit_stew_from_brown_mushroom");
/*      */     
/* 2085 */     ji.a(Items.RABBIT_STEW)
/* 2086 */       .b(Items.BAKED_POTATO)
/* 2087 */       .b(Items.COOKED_RABBIT)
/* 2088 */       .b(Items.BOWL)
/* 2089 */       .b(Items.CARROT)
/* 2090 */       .b(Blocks.RED_MUSHROOM)
/* 2091 */       .a("rabbit_stew")
/* 2092 */       .a("has_cooked_rabbit", a(Items.COOKED_RABBIT))
/* 2093 */       .a(var0, "rabbit_stew_from_red_mushroom");
/*      */     
/* 2095 */     jh.a(Blocks.RAIL, 16)
/* 2096 */       .a(Character.valueOf('#'), Items.STICK)
/* 2097 */       .a(Character.valueOf('X'), Items.IRON_INGOT)
/* 2098 */       .a("X X")
/* 2099 */       .a("X#X")
/* 2100 */       .a("X X")
/* 2101 */       .a("has_minecart", a(Items.MINECART))
/* 2102 */       .a(var0);
/*      */     
/* 2104 */     ji.a(Items.REDSTONE, 9)
/* 2105 */       .b(Blocks.REDSTONE_BLOCK)
/* 2106 */       .a("has_redstone_block", a(Blocks.REDSTONE_BLOCK))
/* 2107 */       .a(var0);
/*      */     
/* 2109 */     jh.a(Blocks.REDSTONE_BLOCK)
/* 2110 */       .a(Character.valueOf('#'), Items.REDSTONE)
/* 2111 */       .a("###")
/* 2112 */       .a("###")
/* 2113 */       .a("###")
/* 2114 */       .a("has_redstone", a(Items.REDSTONE))
/* 2115 */       .a(var0);
/*      */     
/* 2117 */     jh.a(Blocks.REDSTONE_LAMP)
/* 2118 */       .a(Character.valueOf('R'), Items.REDSTONE)
/* 2119 */       .a(Character.valueOf('G'), Blocks.GLOWSTONE)
/* 2120 */       .a(" R ")
/* 2121 */       .a("RGR")
/* 2122 */       .a(" R ")
/* 2123 */       .a("has_glowstone", a(Blocks.GLOWSTONE))
/* 2124 */       .a(var0);
/*      */     
/* 2126 */     jh.a(Blocks.REDSTONE_TORCH)
/* 2127 */       .a(Character.valueOf('#'), Items.STICK)
/* 2128 */       .a(Character.valueOf('X'), Items.REDSTONE)
/* 2129 */       .a("X")
/* 2130 */       .a("#")
/* 2131 */       .a("has_redstone", a(Items.REDSTONE))
/* 2132 */       .a(var0);
/*      */     
/* 2134 */     ji.a(Items.RED_DYE)
/* 2135 */       .b(Items.BEETROOT)
/* 2136 */       .a("red_dye")
/* 2137 */       .a("has_beetroot", a(Items.BEETROOT))
/* 2138 */       .a(var0, "red_dye_from_beetroot");
/*      */     
/* 2140 */     ji.a(Items.RED_DYE)
/* 2141 */       .b(Blocks.POPPY)
/* 2142 */       .a("red_dye")
/* 2143 */       .a("has_red_flower", a(Blocks.POPPY))
/* 2144 */       .a(var0, "red_dye_from_poppy");
/*      */     
/* 2146 */     ji.a(Items.RED_DYE, 2)
/* 2147 */       .b(Blocks.ROSE_BUSH)
/* 2148 */       .a("red_dye")
/* 2149 */       .a("has_double_plant", a(Blocks.ROSE_BUSH))
/* 2150 */       .a(var0, "red_dye_from_rose_bush");
/*      */     
/* 2152 */     ji.a(Items.RED_DYE)
/* 2153 */       .b(Blocks.RED_TULIP)
/* 2154 */       .a("red_dye")
/* 2155 */       .a("has_red_flower", a(Blocks.RED_TULIP))
/* 2156 */       .a(var0, "red_dye_from_tulip");
/*      */     
/* 2158 */     jh.a(Blocks.RED_NETHER_BRICKS)
/* 2159 */       .a(Character.valueOf('W'), Items.NETHER_WART)
/* 2160 */       .a(Character.valueOf('N'), Items.NETHER_BRICK)
/* 2161 */       .a("NW")
/* 2162 */       .a("WN")
/* 2163 */       .a("has_nether_wart", a(Items.NETHER_WART))
/* 2164 */       .a(var0);
/*      */     
/* 2166 */     jh.a(Blocks.RED_SANDSTONE)
/* 2167 */       .a(Character.valueOf('#'), Blocks.RED_SAND)
/* 2168 */       .a("##")
/* 2169 */       .a("##")
/* 2170 */       .a("has_sand", a(Blocks.RED_SAND))
/* 2171 */       .a(var0);
/*      */     
/* 2173 */     jh.a(Blocks.RED_SANDSTONE_SLAB, 6)
/* 2174 */       .a(Character.valueOf('#'), RecipeItemStack.a(new IMaterial[] { Blocks.RED_SANDSTONE, Blocks.CHISELED_RED_SANDSTONE
/* 2175 */           })).a("###")
/* 2176 */       .a("has_red_sandstone", a(Blocks.RED_SANDSTONE))
/* 2177 */       .a("has_chiseled_red_sandstone", a(Blocks.CHISELED_RED_SANDSTONE))
/* 2178 */       .a(var0);
/*      */     
/* 2180 */     jh.a(Blocks.CUT_RED_SANDSTONE_SLAB, 6)
/* 2181 */       .a(Character.valueOf('#'), Blocks.CUT_RED_SANDSTONE)
/* 2182 */       .a("###")
/* 2183 */       .a("has_cut_red_sandstone", a(Blocks.CUT_RED_SANDSTONE))
/* 2184 */       .a(var0);
/*      */     
/* 2186 */     jh.a(Blocks.RED_SANDSTONE_STAIRS, 4)
/* 2187 */       .a(Character.valueOf('#'), RecipeItemStack.a(new IMaterial[] { Blocks.RED_SANDSTONE, Blocks.CHISELED_RED_SANDSTONE, Blocks.CUT_RED_SANDSTONE
/* 2188 */           })).a("#  ")
/* 2189 */       .a("## ")
/* 2190 */       .a("###")
/* 2191 */       .a("has_red_sandstone", a(Blocks.RED_SANDSTONE))
/* 2192 */       .a("has_chiseled_red_sandstone", a(Blocks.CHISELED_RED_SANDSTONE))
/* 2193 */       .a("has_cut_red_sandstone", a(Blocks.CUT_RED_SANDSTONE))
/* 2194 */       .a(var0);
/*      */     
/* 2196 */     jh.a(Blocks.REPEATER)
/* 2197 */       .a(Character.valueOf('#'), Blocks.REDSTONE_TORCH)
/* 2198 */       .a(Character.valueOf('X'), Items.REDSTONE)
/* 2199 */       .a(Character.valueOf('I'), Blocks.STONE)
/* 2200 */       .a("#X#")
/* 2201 */       .a("III")
/* 2202 */       .a("has_redstone_torch", a(Blocks.REDSTONE_TORCH))
/* 2203 */       .a(var0);
/*      */     
/* 2205 */     jh.a(Blocks.SANDSTONE)
/* 2206 */       .a(Character.valueOf('#'), Blocks.SAND)
/* 2207 */       .a("##")
/* 2208 */       .a("##")
/* 2209 */       .a("has_sand", a(Blocks.SAND))
/* 2210 */       .a(var0);
/*      */     
/* 2212 */     jh.a(Blocks.SANDSTONE_SLAB, 6)
/* 2213 */       .a(Character.valueOf('#'), RecipeItemStack.a(new IMaterial[] { Blocks.SANDSTONE, Blocks.CHISELED_SANDSTONE
/* 2214 */           })).a("###")
/* 2215 */       .a("has_sandstone", a(Blocks.SANDSTONE))
/* 2216 */       .a("has_chiseled_sandstone", a(Blocks.CHISELED_SANDSTONE))
/* 2217 */       .a(var0);
/*      */     
/* 2219 */     jh.a(Blocks.CUT_SANDSTONE_SLAB, 6)
/* 2220 */       .a(Character.valueOf('#'), Blocks.CUT_SANDSTONE)
/* 2221 */       .a("###")
/* 2222 */       .a("has_cut_sandstone", a(Blocks.CUT_SANDSTONE))
/* 2223 */       .a(var0);
/*      */     
/* 2225 */     jh.a(Blocks.SANDSTONE_STAIRS, 4)
/* 2226 */       .a(Character.valueOf('#'), RecipeItemStack.a(new IMaterial[] { Blocks.SANDSTONE, Blocks.CHISELED_SANDSTONE, Blocks.CUT_SANDSTONE
/* 2227 */           })).a("#  ")
/* 2228 */       .a("## ")
/* 2229 */       .a("###")
/* 2230 */       .a("has_sandstone", a(Blocks.SANDSTONE))
/* 2231 */       .a("has_chiseled_sandstone", a(Blocks.CHISELED_SANDSTONE))
/* 2232 */       .a("has_cut_sandstone", a(Blocks.CUT_SANDSTONE))
/* 2233 */       .a(var0);
/*      */     
/* 2235 */     jh.a(Blocks.SEA_LANTERN)
/* 2236 */       .a(Character.valueOf('S'), Items.PRISMARINE_SHARD)
/* 2237 */       .a(Character.valueOf('C'), Items.PRISMARINE_CRYSTALS)
/* 2238 */       .a("SCS")
/* 2239 */       .a("CCC")
/* 2240 */       .a("SCS")
/* 2241 */       .a("has_prismarine_crystals", a(Items.PRISMARINE_CRYSTALS))
/* 2242 */       .a(var0);
/*      */     
/* 2244 */     jh.a(Items.SHEARS)
/* 2245 */       .a(Character.valueOf('#'), Items.IRON_INGOT)
/* 2246 */       .a(" #")
/* 2247 */       .a("# ")
/* 2248 */       .a("has_iron_ingot", a(Items.IRON_INGOT))
/* 2249 */       .a(var0);
/*      */     
/* 2251 */     jh.a(Items.SHIELD)
/* 2252 */       .a(Character.valueOf('W'), TagsItem.PLANKS)
/* 2253 */       .a(Character.valueOf('o'), Items.IRON_INGOT)
/* 2254 */       .a("WoW")
/* 2255 */       .a("WWW")
/* 2256 */       .a(" W ")
/* 2257 */       .a("has_iron_ingot", a(Items.IRON_INGOT))
/* 2258 */       .a(var0);
/*      */     
/* 2260 */     jh.a(Blocks.SLIME_BLOCK)
/* 2261 */       .a(Character.valueOf('#'), Items.SLIME_BALL)
/* 2262 */       .a("###")
/* 2263 */       .a("###")
/* 2264 */       .a("###")
/* 2265 */       .a("has_slime_ball", a(Items.SLIME_BALL))
/* 2266 */       .a(var0);
/*      */     
/* 2268 */     ji.a(Items.SLIME_BALL, 9)
/* 2269 */       .b(Blocks.SLIME_BLOCK)
/* 2270 */       .a("has_slime", a(Blocks.SLIME_BLOCK))
/* 2271 */       .a(var0);
/*      */     
/* 2273 */     jh.a(Blocks.CUT_RED_SANDSTONE, 4)
/* 2274 */       .a(Character.valueOf('#'), Blocks.RED_SANDSTONE)
/* 2275 */       .a("##")
/* 2276 */       .a("##")
/* 2277 */       .a("has_red_sandstone", a(Blocks.RED_SANDSTONE))
/* 2278 */       .a(var0);
/*      */     
/* 2280 */     jh.a(Blocks.CUT_SANDSTONE, 4)
/* 2281 */       .a(Character.valueOf('#'), Blocks.SANDSTONE)
/* 2282 */       .a("##")
/* 2283 */       .a("##")
/* 2284 */       .a("has_sandstone", a(Blocks.SANDSTONE))
/* 2285 */       .a(var0);
/*      */     
/* 2287 */     jh.a(Blocks.SNOW_BLOCK)
/* 2288 */       .a(Character.valueOf('#'), Items.SNOWBALL)
/* 2289 */       .a("##")
/* 2290 */       .a("##")
/* 2291 */       .a("has_snowball", a(Items.SNOWBALL))
/* 2292 */       .a(var0);
/*      */     
/* 2294 */     jh.a(Blocks.SNOW, 6)
/* 2295 */       .a(Character.valueOf('#'), Blocks.SNOW_BLOCK)
/* 2296 */       .a("###")
/* 2297 */       .a("has_snowball", a(Items.SNOWBALL))
/* 2298 */       .a(var0);
/*      */     
/* 2300 */     jh.a(Blocks.SOUL_CAMPFIRE)
/* 2301 */       .a(Character.valueOf('L'), TagsItem.LOGS)
/* 2302 */       .a(Character.valueOf('S'), Items.STICK)
/* 2303 */       .a(Character.valueOf('#'), TagsItem.SOUL_FIRE_BASE_BLOCKS)
/* 2304 */       .a(" S ")
/* 2305 */       .a("S#S")
/* 2306 */       .a("LLL")
/* 2307 */       .a("has_stick", a(Items.STICK))
/* 2308 */       .a("has_soul_sand", a(TagsItem.SOUL_FIRE_BASE_BLOCKS))
/* 2309 */       .a(var0);
/*      */     
/* 2311 */     jh.a(Items.GLISTERING_MELON_SLICE)
/* 2312 */       .a(Character.valueOf('#'), Items.GOLD_NUGGET)
/* 2313 */       .a(Character.valueOf('X'), Items.MELON_SLICE)
/* 2314 */       .a("###")
/* 2315 */       .a("#X#")
/* 2316 */       .a("###")
/* 2317 */       .a("has_melon", a(Items.MELON_SLICE))
/* 2318 */       .a(var0);
/*      */     
/* 2320 */     jh.a(Items.SPECTRAL_ARROW, 2)
/* 2321 */       .a(Character.valueOf('#'), Items.GLOWSTONE_DUST)
/* 2322 */       .a(Character.valueOf('X'), Items.ARROW)
/* 2323 */       .a(" # ")
/* 2324 */       .a("#X#")
/* 2325 */       .a(" # ")
/* 2326 */       .a("has_glowstone_dust", a(Items.GLOWSTONE_DUST))
/* 2327 */       .a(var0);
/*      */     
/* 2329 */     jh.a(Items.STICK, 4)
/* 2330 */       .a(Character.valueOf('#'), TagsItem.PLANKS)
/* 2331 */       .a("#")
/* 2332 */       .a("#")
/* 2333 */       .b("sticks")
/* 2334 */       .a("has_planks", a(TagsItem.PLANKS))
/* 2335 */       .a(var0);
/*      */     
/* 2337 */     jh.a(Items.STICK, 1)
/* 2338 */       .a(Character.valueOf('#'), Blocks.BAMBOO)
/* 2339 */       .a("#")
/* 2340 */       .a("#")
/* 2341 */       .b("sticks")
/* 2342 */       .a("has_bamboo", a(Blocks.BAMBOO))
/* 2343 */       .a(var0, "stick_from_bamboo_item");
/*      */     
/* 2345 */     jh.a(Blocks.STICKY_PISTON)
/* 2346 */       .a(Character.valueOf('P'), Blocks.PISTON)
/* 2347 */       .a(Character.valueOf('S'), Items.SLIME_BALL)
/* 2348 */       .a("S")
/* 2349 */       .a("P")
/* 2350 */       .a("has_slime_ball", a(Items.SLIME_BALL))
/* 2351 */       .a(var0);
/*      */     
/* 2353 */     jh.a(Blocks.STONE_BRICKS, 4)
/* 2354 */       .a(Character.valueOf('#'), Blocks.STONE)
/* 2355 */       .a("##")
/* 2356 */       .a("##")
/* 2357 */       .a("has_stone", a(Blocks.STONE))
/* 2358 */       .a(var0);
/*      */     
/* 2360 */     jh.a(Items.STONE_AXE)
/* 2361 */       .a(Character.valueOf('#'), Items.STICK)
/* 2362 */       .a(Character.valueOf('X'), TagsItem.STONE_TOOL_MATERIALS)
/* 2363 */       .a("XX")
/* 2364 */       .a("X#")
/* 2365 */       .a(" #")
/* 2366 */       .a("has_cobblestone", a(TagsItem.STONE_TOOL_MATERIALS))
/* 2367 */       .a(var0);
/*      */     
/* 2369 */     jh.a(Blocks.STONE_BRICK_SLAB, 6)
/* 2370 */       .a(Character.valueOf('#'), Blocks.STONE_BRICKS)
/* 2371 */       .a("###")
/* 2372 */       .a("has_stone_bricks", a(TagsItem.STONE_BRICKS))
/* 2373 */       .a(var0);
/*      */     
/* 2375 */     jh.a(Blocks.STONE_BRICK_STAIRS, 4)
/* 2376 */       .a(Character.valueOf('#'), Blocks.STONE_BRICKS)
/* 2377 */       .a("#  ")
/* 2378 */       .a("## ")
/* 2379 */       .a("###")
/* 2380 */       .a("has_stone_bricks", a(TagsItem.STONE_BRICKS))
/* 2381 */       .a(var0);
/*      */     
/* 2383 */     ji.a(Blocks.STONE_BUTTON)
/* 2384 */       .b(Blocks.STONE)
/* 2385 */       .a("has_stone", a(Blocks.STONE))
/* 2386 */       .a(var0);
/*      */     
/* 2388 */     jh.a(Items.STONE_HOE)
/* 2389 */       .a(Character.valueOf('#'), Items.STICK)
/* 2390 */       .a(Character.valueOf('X'), TagsItem.STONE_TOOL_MATERIALS)
/* 2391 */       .a("XX")
/* 2392 */       .a(" #")
/* 2393 */       .a(" #")
/* 2394 */       .a("has_cobblestone", a(TagsItem.STONE_TOOL_MATERIALS))
/* 2395 */       .a(var0);
/*      */     
/* 2397 */     jh.a(Items.STONE_PICKAXE)
/* 2398 */       .a(Character.valueOf('#'), Items.STICK)
/* 2399 */       .a(Character.valueOf('X'), TagsItem.STONE_TOOL_MATERIALS)
/* 2400 */       .a("XXX")
/* 2401 */       .a(" # ")
/* 2402 */       .a(" # ")
/* 2403 */       .a("has_cobblestone", a(TagsItem.STONE_TOOL_MATERIALS))
/* 2404 */       .a(var0);
/*      */     
/* 2406 */     jh.a(Blocks.STONE_PRESSURE_PLATE)
/* 2407 */       .a(Character.valueOf('#'), Blocks.STONE)
/* 2408 */       .a("##")
/* 2409 */       .a("has_stone", a(Blocks.STONE))
/* 2410 */       .a(var0);
/*      */     
/* 2412 */     jh.a(Items.STONE_SHOVEL)
/* 2413 */       .a(Character.valueOf('#'), Items.STICK)
/* 2414 */       .a(Character.valueOf('X'), TagsItem.STONE_TOOL_MATERIALS)
/* 2415 */       .a("X")
/* 2416 */       .a("#")
/* 2417 */       .a("#")
/* 2418 */       .a("has_cobblestone", a(TagsItem.STONE_TOOL_MATERIALS))
/* 2419 */       .a(var0);
/*      */     
/* 2421 */     jh.a(Blocks.STONE_SLAB, 6)
/* 2422 */       .a(Character.valueOf('#'), Blocks.STONE)
/* 2423 */       .a("###")
/* 2424 */       .a("has_stone", a(Blocks.STONE))
/* 2425 */       .a(var0);
/*      */     
/* 2427 */     jh.a(Blocks.SMOOTH_STONE_SLAB, 6)
/* 2428 */       .a(Character.valueOf('#'), Blocks.SMOOTH_STONE)
/* 2429 */       .a("###")
/* 2430 */       .a("has_smooth_stone", a(Blocks.SMOOTH_STONE))
/* 2431 */       .a(var0);
/*      */     
/* 2433 */     jh.a(Blocks.COBBLESTONE_STAIRS, 4)
/* 2434 */       .a(Character.valueOf('#'), Blocks.COBBLESTONE)
/* 2435 */       .a("#  ")
/* 2436 */       .a("## ")
/* 2437 */       .a("###")
/* 2438 */       .a("has_cobblestone", a(Blocks.COBBLESTONE))
/* 2439 */       .a(var0);
/*      */     
/* 2441 */     jh.a(Items.STONE_SWORD)
/* 2442 */       .a(Character.valueOf('#'), Items.STICK)
/* 2443 */       .a(Character.valueOf('X'), TagsItem.STONE_TOOL_MATERIALS)
/* 2444 */       .a("X")
/* 2445 */       .a("X")
/* 2446 */       .a("#")
/* 2447 */       .a("has_cobblestone", a(TagsItem.STONE_TOOL_MATERIALS))
/* 2448 */       .a(var0);
/*      */     
/* 2450 */     jh.a(Blocks.WHITE_WOOL)
/* 2451 */       .a(Character.valueOf('#'), Items.STRING)
/* 2452 */       .a("##")
/* 2453 */       .a("##")
/* 2454 */       .a("has_string", a(Items.STRING))
/* 2455 */       .a(var0, "white_wool_from_string");
/*      */     
/* 2457 */     ji.a(Items.SUGAR)
/* 2458 */       .b(Blocks.SUGAR_CANE)
/* 2459 */       .a("sugar")
/* 2460 */       .a("has_reeds", a(Blocks.SUGAR_CANE))
/* 2461 */       .a(var0, "sugar_from_sugar_cane");
/*      */     
/* 2463 */     ji.a(Items.SUGAR, 3)
/* 2464 */       .b(Items.HONEY_BOTTLE)
/* 2465 */       .a("sugar")
/* 2466 */       .a("has_honey_bottle", a(Items.HONEY_BOTTLE))
/* 2467 */       .a(var0, "sugar_from_honey_bottle");
/*      */     
/* 2469 */     jh.a(Blocks.TARGET)
/* 2470 */       .a(Character.valueOf('H'), Items.fL)
/* 2471 */       .a(Character.valueOf('R'), Items.REDSTONE)
/* 2472 */       .a(" R ")
/* 2473 */       .a("RHR")
/* 2474 */       .a(" R ")
/* 2475 */       .a("has_redstone", a(Items.REDSTONE))
/* 2476 */       .a("has_hay_block", a(Blocks.HAY_BLOCK))
/* 2477 */       .a(var0);
/*      */     
/* 2479 */     jh.a(Blocks.TNT)
/* 2480 */       .a(Character.valueOf('#'), RecipeItemStack.a(new IMaterial[] { Blocks.SAND, Blocks.RED_SAND
/* 2481 */           })).a(Character.valueOf('X'), Items.GUNPOWDER)
/* 2482 */       .a("X#X")
/* 2483 */       .a("#X#")
/* 2484 */       .a("X#X")
/* 2485 */       .a("has_gunpowder", a(Items.GUNPOWDER))
/* 2486 */       .a(var0);
/*      */     
/* 2488 */     jh.a(Items.TNT_MINECART)
/* 2489 */       .a(Character.valueOf('A'), Blocks.TNT)
/* 2490 */       .a(Character.valueOf('B'), Items.MINECART)
/* 2491 */       .a("A")
/* 2492 */       .a("B")
/* 2493 */       .a("has_minecart", a(Items.MINECART))
/* 2494 */       .a(var0);
/*      */     
/* 2496 */     jh.a(Blocks.TORCH, 4)
/* 2497 */       .a(Character.valueOf('#'), Items.STICK)
/* 2498 */       .a(Character.valueOf('X'), RecipeItemStack.a(new IMaterial[] { Items.COAL, Items.CHARCOAL
/* 2499 */           })).a("X")
/* 2500 */       .a("#")
/* 2501 */       .a("has_stone_pickaxe", a(Items.STONE_PICKAXE))
/* 2502 */       .a(var0);
/*      */     
/* 2504 */     jh.a(Blocks.SOUL_TORCH, 4)
/* 2505 */       .a(Character.valueOf('X'), RecipeItemStack.a(new IMaterial[] { Items.COAL, Items.CHARCOAL
/* 2506 */           })).a(Character.valueOf('#'), Items.STICK)
/* 2507 */       .a(Character.valueOf('S'), TagsItem.SOUL_FIRE_BASE_BLOCKS)
/* 2508 */       .a("X")
/* 2509 */       .a("#")
/* 2510 */       .a("S")
/* 2511 */       .a("has_soul_sand", a(TagsItem.SOUL_FIRE_BASE_BLOCKS))
/* 2512 */       .a(var0);
/*      */     
/* 2514 */     jh.a(Blocks.LANTERN)
/* 2515 */       .a(Character.valueOf('#'), Items.cp)
/* 2516 */       .a(Character.valueOf('X'), Items.IRON_NUGGET)
/* 2517 */       .a("XXX")
/* 2518 */       .a("X#X")
/* 2519 */       .a("XXX")
/* 2520 */       .a("has_iron_nugget", a(Items.IRON_NUGGET))
/* 2521 */       .a("has_iron_ingot", a(Items.IRON_INGOT))
/* 2522 */       .a(var0);
/*      */     
/* 2524 */     jh.a(Blocks.SOUL_LANTERN)
/* 2525 */       .a(Character.valueOf('#'), Items.dp)
/* 2526 */       .a(Character.valueOf('X'), Items.IRON_NUGGET)
/* 2527 */       .a("XXX")
/* 2528 */       .a("X#X")
/* 2529 */       .a("XXX")
/* 2530 */       .a("has_soul_torch", a(Items.dp))
/* 2531 */       .a(var0);
/*      */     
/* 2533 */     ji.a(Blocks.TRAPPED_CHEST)
/* 2534 */       .b(Blocks.CHEST)
/* 2535 */       .b(Blocks.TRIPWIRE_HOOK)
/* 2536 */       .a("has_tripwire_hook", a(Blocks.TRIPWIRE_HOOK))
/* 2537 */       .a(var0);
/*      */     
/* 2539 */     jh.a(Blocks.TRIPWIRE_HOOK, 2)
/* 2540 */       .a(Character.valueOf('#'), TagsItem.PLANKS)
/* 2541 */       .a(Character.valueOf('S'), Items.STICK)
/* 2542 */       .a(Character.valueOf('I'), Items.IRON_INGOT)
/* 2543 */       .a("I")
/* 2544 */       .a("S")
/* 2545 */       .a("#")
/* 2546 */       .a("has_string", a(Items.STRING))
/* 2547 */       .a(var0);
/*      */     
/* 2549 */     jh.a(Items.TURTLE_HELMET)
/* 2550 */       .a(Character.valueOf('X'), Items.SCUTE)
/* 2551 */       .a("XXX")
/* 2552 */       .a("X X")
/* 2553 */       .a("has_scute", a(Items.SCUTE))
/* 2554 */       .a(var0);
/*      */     
/* 2556 */     ji.a(Items.WHEAT, 9)
/* 2557 */       .b(Blocks.HAY_BLOCK)
/* 2558 */       .a("has_hay_block", a(Blocks.HAY_BLOCK))
/* 2559 */       .a(var0);
/*      */     
/* 2561 */     ji.a(Items.WHITE_DYE)
/* 2562 */       .b(Items.BONE_MEAL)
/* 2563 */       .a("white_dye")
/* 2564 */       .a("has_bone_meal", a(Items.BONE_MEAL))
/* 2565 */       .a(var0);
/*      */     
/* 2567 */     ji.a(Items.WHITE_DYE)
/* 2568 */       .b(Blocks.LILY_OF_THE_VALLEY)
/* 2569 */       .a("white_dye")
/* 2570 */       .a("has_white_flower", a(Blocks.LILY_OF_THE_VALLEY))
/* 2571 */       .a(var0, "white_dye_from_lily_of_the_valley");
/*      */     
/* 2573 */     jh.a(Items.WOODEN_AXE)
/* 2574 */       .a(Character.valueOf('#'), Items.STICK)
/* 2575 */       .a(Character.valueOf('X'), TagsItem.PLANKS)
/* 2576 */       .a("XX")
/* 2577 */       .a("X#")
/* 2578 */       .a(" #")
/* 2579 */       .a("has_stick", a(Items.STICK))
/* 2580 */       .a(var0);
/*      */     
/* 2582 */     jh.a(Items.WOODEN_HOE)
/* 2583 */       .a(Character.valueOf('#'), Items.STICK)
/* 2584 */       .a(Character.valueOf('X'), TagsItem.PLANKS)
/* 2585 */       .a("XX")
/* 2586 */       .a(" #")
/* 2587 */       .a(" #")
/* 2588 */       .a("has_stick", a(Items.STICK))
/* 2589 */       .a(var0);
/*      */     
/* 2591 */     jh.a(Items.WOODEN_PICKAXE)
/* 2592 */       .a(Character.valueOf('#'), Items.STICK)
/* 2593 */       .a(Character.valueOf('X'), TagsItem.PLANKS)
/* 2594 */       .a("XXX")
/* 2595 */       .a(" # ")
/* 2596 */       .a(" # ")
/* 2597 */       .a("has_stick", a(Items.STICK))
/* 2598 */       .a(var0);
/*      */     
/* 2600 */     jh.a(Items.WOODEN_SHOVEL)
/* 2601 */       .a(Character.valueOf('#'), Items.STICK)
/* 2602 */       .a(Character.valueOf('X'), TagsItem.PLANKS)
/* 2603 */       .a("X")
/* 2604 */       .a("#")
/* 2605 */       .a("#")
/* 2606 */       .a("has_stick", a(Items.STICK))
/* 2607 */       .a(var0);
/*      */     
/* 2609 */     jh.a(Items.WOODEN_SWORD)
/* 2610 */       .a(Character.valueOf('#'), Items.STICK)
/* 2611 */       .a(Character.valueOf('X'), TagsItem.PLANKS)
/* 2612 */       .a("X")
/* 2613 */       .a("X")
/* 2614 */       .a("#")
/* 2615 */       .a("has_stick", a(Items.STICK))
/* 2616 */       .a(var0);
/*      */     
/* 2618 */     ji.a(Items.WRITABLE_BOOK)
/* 2619 */       .b(Items.BOOK)
/* 2620 */       .b(Items.INK_SAC)
/* 2621 */       .b(Items.FEATHER)
/* 2622 */       .a("has_book", a(Items.BOOK))
/* 2623 */       .a(var0);
/*      */     
/* 2625 */     ji.a(Items.YELLOW_DYE)
/* 2626 */       .b(Blocks.DANDELION)
/* 2627 */       .a("yellow_dye")
/* 2628 */       .a("has_yellow_flower", a(Blocks.DANDELION))
/* 2629 */       .a(var0, "yellow_dye_from_dandelion");
/*      */     
/* 2631 */     ji.a(Items.YELLOW_DYE, 2)
/* 2632 */       .b(Blocks.SUNFLOWER)
/* 2633 */       .a("yellow_dye")
/* 2634 */       .a("has_double_plant", a(Blocks.SUNFLOWER))
/* 2635 */       .a(var0, "yellow_dye_from_sunflower");
/*      */     
/* 2637 */     ji.a(Items.DRIED_KELP, 9)
/* 2638 */       .b(Blocks.DRIED_KELP_BLOCK)
/* 2639 */       .a("has_dried_kelp_block", a(Blocks.DRIED_KELP_BLOCK))
/* 2640 */       .a(var0);
/*      */     
/* 2642 */     ji.a(Blocks.DRIED_KELP_BLOCK)
/* 2643 */       .b(Items.DRIED_KELP, 9)
/* 2644 */       .a("has_dried_kelp", a(Items.DRIED_KELP))
/* 2645 */       .a(var0);
/*      */     
/* 2647 */     jh.a(Blocks.CONDUIT)
/* 2648 */       .a(Character.valueOf('#'), Items.NAUTILUS_SHELL)
/* 2649 */       .a(Character.valueOf('X'), Items.HEART_OF_THE_SEA)
/* 2650 */       .a("###")
/* 2651 */       .a("#X#")
/* 2652 */       .a("###")
/* 2653 */       .a("has_nautilus_core", a(Items.HEART_OF_THE_SEA))
/* 2654 */       .a("has_nautilus_shell", a(Items.NAUTILUS_SHELL))
/* 2655 */       .a(var0);
/*      */     
/* 2657 */     jh.a(Blocks.POLISHED_GRANITE_STAIRS, 4)
/* 2658 */       .a(Character.valueOf('#'), Blocks.POLISHED_GRANITE)
/* 2659 */       .a("#  ")
/* 2660 */       .a("## ")
/* 2661 */       .a("###")
/* 2662 */       .a("has_polished_granite", a(Blocks.POLISHED_GRANITE))
/* 2663 */       .a(var0);
/*      */     
/* 2665 */     jh.a(Blocks.SMOOTH_RED_SANDSTONE_STAIRS, 4)
/* 2666 */       .a(Character.valueOf('#'), Blocks.SMOOTH_RED_SANDSTONE)
/* 2667 */       .a("#  ")
/* 2668 */       .a("## ")
/* 2669 */       .a("###")
/* 2670 */       .a("has_smooth_red_sandstone", a(Blocks.SMOOTH_RED_SANDSTONE))
/* 2671 */       .a(var0);
/*      */     
/* 2673 */     jh.a(Blocks.MOSSY_STONE_BRICK_STAIRS, 4)
/* 2674 */       .a(Character.valueOf('#'), Blocks.MOSSY_STONE_BRICKS)
/* 2675 */       .a("#  ")
/* 2676 */       .a("## ")
/* 2677 */       .a("###")
/* 2678 */       .a("has_mossy_stone_bricks", a(Blocks.MOSSY_STONE_BRICKS))
/* 2679 */       .a(var0);
/*      */     
/* 2681 */     jh.a(Blocks.POLISHED_DIORITE_STAIRS, 4)
/* 2682 */       .a(Character.valueOf('#'), Blocks.POLISHED_DIORITE)
/* 2683 */       .a("#  ")
/* 2684 */       .a("## ")
/* 2685 */       .a("###")
/* 2686 */       .a("has_polished_diorite", a(Blocks.POLISHED_DIORITE))
/* 2687 */       .a(var0);
/*      */     
/* 2689 */     jh.a(Blocks.MOSSY_COBBLESTONE_STAIRS, 4)
/* 2690 */       .a(Character.valueOf('#'), Blocks.MOSSY_COBBLESTONE)
/* 2691 */       .a("#  ")
/* 2692 */       .a("## ")
/* 2693 */       .a("###")
/* 2694 */       .a("has_mossy_cobblestone", a(Blocks.MOSSY_COBBLESTONE))
/* 2695 */       .a(var0);
/*      */     
/* 2697 */     jh.a(Blocks.END_STONE_BRICK_STAIRS, 4)
/* 2698 */       .a(Character.valueOf('#'), Blocks.END_STONE_BRICKS)
/* 2699 */       .a("#  ")
/* 2700 */       .a("## ")
/* 2701 */       .a("###")
/* 2702 */       .a("has_end_stone_bricks", a(Blocks.END_STONE_BRICKS))
/* 2703 */       .a(var0);
/*      */     
/* 2705 */     jh.a(Blocks.STONE_STAIRS, 4)
/* 2706 */       .a(Character.valueOf('#'), Blocks.STONE)
/* 2707 */       .a("#  ")
/* 2708 */       .a("## ")
/* 2709 */       .a("###")
/* 2710 */       .a("has_stone", a(Blocks.STONE))
/* 2711 */       .a(var0);
/*      */     
/* 2713 */     jh.a(Blocks.SMOOTH_SANDSTONE_STAIRS, 4)
/* 2714 */       .a(Character.valueOf('#'), Blocks.SMOOTH_SANDSTONE)
/* 2715 */       .a("#  ")
/* 2716 */       .a("## ")
/* 2717 */       .a("###")
/* 2718 */       .a("has_smooth_sandstone", a(Blocks.SMOOTH_SANDSTONE))
/* 2719 */       .a(var0);
/*      */     
/* 2721 */     jh.a(Blocks.SMOOTH_QUARTZ_STAIRS, 4)
/* 2722 */       .a(Character.valueOf('#'), Blocks.SMOOTH_QUARTZ)
/* 2723 */       .a("#  ")
/* 2724 */       .a("## ")
/* 2725 */       .a("###")
/* 2726 */       .a("has_smooth_quartz", a(Blocks.SMOOTH_QUARTZ))
/* 2727 */       .a(var0);
/*      */     
/* 2729 */     jh.a(Blocks.GRANITE_STAIRS, 4)
/* 2730 */       .a(Character.valueOf('#'), Blocks.GRANITE)
/* 2731 */       .a("#  ")
/* 2732 */       .a("## ")
/* 2733 */       .a("###")
/* 2734 */       .a("has_granite", a(Blocks.GRANITE))
/* 2735 */       .a(var0);
/*      */     
/* 2737 */     jh.a(Blocks.ANDESITE_STAIRS, 4)
/* 2738 */       .a(Character.valueOf('#'), Blocks.ANDESITE)
/* 2739 */       .a("#  ")
/* 2740 */       .a("## ")
/* 2741 */       .a("###")
/* 2742 */       .a("has_andesite", a(Blocks.ANDESITE))
/* 2743 */       .a(var0);
/*      */     
/* 2745 */     jh.a(Blocks.RED_NETHER_BRICK_STAIRS, 4)
/* 2746 */       .a(Character.valueOf('#'), Blocks.RED_NETHER_BRICKS)
/* 2747 */       .a("#  ")
/* 2748 */       .a("## ")
/* 2749 */       .a("###")
/* 2750 */       .a("has_red_nether_bricks", a(Blocks.RED_NETHER_BRICKS))
/* 2751 */       .a(var0);
/*      */     
/* 2753 */     jh.a(Blocks.POLISHED_ANDESITE_STAIRS, 4)
/* 2754 */       .a(Character.valueOf('#'), Blocks.POLISHED_ANDESITE)
/* 2755 */       .a("#  ")
/* 2756 */       .a("## ")
/* 2757 */       .a("###")
/* 2758 */       .a("has_polished_andesite", a(Blocks.POLISHED_ANDESITE))
/* 2759 */       .a(var0);
/*      */     
/* 2761 */     jh.a(Blocks.DIORITE_STAIRS, 4)
/* 2762 */       .a(Character.valueOf('#'), Blocks.DIORITE)
/* 2763 */       .a("#  ")
/* 2764 */       .a("## ")
/* 2765 */       .a("###")
/* 2766 */       .a("has_diorite", a(Blocks.DIORITE))
/* 2767 */       .a(var0);
/*      */     
/* 2769 */     jh.a(Blocks.POLISHED_GRANITE_SLAB, 6)
/* 2770 */       .a(Character.valueOf('#'), Blocks.POLISHED_GRANITE)
/* 2771 */       .a("###")
/* 2772 */       .a("has_polished_granite", a(Blocks.POLISHED_GRANITE))
/* 2773 */       .a(var0);
/*      */     
/* 2775 */     jh.a(Blocks.SMOOTH_RED_SANDSTONE_SLAB, 6)
/* 2776 */       .a(Character.valueOf('#'), Blocks.SMOOTH_RED_SANDSTONE)
/* 2777 */       .a("###")
/* 2778 */       .a("has_smooth_red_sandstone", a(Blocks.SMOOTH_RED_SANDSTONE))
/* 2779 */       .a(var0);
/*      */     
/* 2781 */     jh.a(Blocks.MOSSY_STONE_BRICK_SLAB, 6)
/* 2782 */       .a(Character.valueOf('#'), Blocks.MOSSY_STONE_BRICKS)
/* 2783 */       .a("###")
/* 2784 */       .a("has_mossy_stone_bricks", a(Blocks.MOSSY_STONE_BRICKS))
/* 2785 */       .a(var0);
/*      */     
/* 2787 */     jh.a(Blocks.POLISHED_DIORITE_SLAB, 6)
/* 2788 */       .a(Character.valueOf('#'), Blocks.POLISHED_DIORITE)
/* 2789 */       .a("###")
/* 2790 */       .a("has_polished_diorite", a(Blocks.POLISHED_DIORITE))
/* 2791 */       .a(var0);
/*      */     
/* 2793 */     jh.a(Blocks.MOSSY_COBBLESTONE_SLAB, 6)
/* 2794 */       .a(Character.valueOf('#'), Blocks.MOSSY_COBBLESTONE)
/* 2795 */       .a("###")
/* 2796 */       .a("has_mossy_cobblestone", a(Blocks.MOSSY_COBBLESTONE))
/* 2797 */       .a(var0);
/*      */     
/* 2799 */     jh.a(Blocks.END_STONE_BRICK_SLAB, 6)
/* 2800 */       .a(Character.valueOf('#'), Blocks.END_STONE_BRICKS)
/* 2801 */       .a("###")
/* 2802 */       .a("has_end_stone_bricks", a(Blocks.END_STONE_BRICKS))
/* 2803 */       .a(var0);
/*      */     
/* 2805 */     jh.a(Blocks.SMOOTH_SANDSTONE_SLAB, 6)
/* 2806 */       .a(Character.valueOf('#'), Blocks.SMOOTH_SANDSTONE)
/* 2807 */       .a("###")
/* 2808 */       .a("has_smooth_sandstone", a(Blocks.SMOOTH_SANDSTONE))
/* 2809 */       .a(var0);
/*      */     
/* 2811 */     jh.a(Blocks.SMOOTH_QUARTZ_SLAB, 6)
/* 2812 */       .a(Character.valueOf('#'), Blocks.SMOOTH_QUARTZ)
/* 2813 */       .a("###")
/* 2814 */       .a("has_smooth_quartz", a(Blocks.SMOOTH_QUARTZ))
/* 2815 */       .a(var0);
/*      */     
/* 2817 */     jh.a(Blocks.GRANITE_SLAB, 6)
/* 2818 */       .a(Character.valueOf('#'), Blocks.GRANITE)
/* 2819 */       .a("###")
/* 2820 */       .a("has_granite", a(Blocks.GRANITE))
/* 2821 */       .a(var0);
/*      */     
/* 2823 */     jh.a(Blocks.ANDESITE_SLAB, 6)
/* 2824 */       .a(Character.valueOf('#'), Blocks.ANDESITE)
/* 2825 */       .a("###")
/* 2826 */       .a("has_andesite", a(Blocks.ANDESITE))
/* 2827 */       .a(var0);
/*      */     
/* 2829 */     jh.a(Blocks.RED_NETHER_BRICK_SLAB, 6)
/* 2830 */       .a(Character.valueOf('#'), Blocks.RED_NETHER_BRICKS)
/* 2831 */       .a("###")
/* 2832 */       .a("has_red_nether_bricks", a(Blocks.RED_NETHER_BRICKS))
/* 2833 */       .a(var0);
/*      */     
/* 2835 */     jh.a(Blocks.POLISHED_ANDESITE_SLAB, 6)
/* 2836 */       .a(Character.valueOf('#'), Blocks.POLISHED_ANDESITE)
/* 2837 */       .a("###")
/* 2838 */       .a("has_polished_andesite", a(Blocks.POLISHED_ANDESITE))
/* 2839 */       .a(var0);
/*      */     
/* 2841 */     jh.a(Blocks.DIORITE_SLAB, 6)
/* 2842 */       .a(Character.valueOf('#'), Blocks.DIORITE)
/* 2843 */       .a("###")
/* 2844 */       .a("has_diorite", a(Blocks.DIORITE))
/* 2845 */       .a(var0);
/*      */     
/* 2847 */     jh.a(Blocks.BRICK_WALL, 6)
/* 2848 */       .a(Character.valueOf('#'), Blocks.BRICKS)
/* 2849 */       .a("###")
/* 2850 */       .a("###")
/* 2851 */       .a("has_bricks", a(Blocks.BRICKS))
/* 2852 */       .a(var0);
/*      */     
/* 2854 */     jh.a(Blocks.PRISMARINE_WALL, 6)
/* 2855 */       .a(Character.valueOf('#'), Blocks.PRISMARINE)
/* 2856 */       .a("###")
/* 2857 */       .a("###")
/* 2858 */       .a("has_prismarine", a(Blocks.PRISMARINE))
/* 2859 */       .a(var0);
/*      */     
/* 2861 */     jh.a(Blocks.RED_SANDSTONE_WALL, 6)
/* 2862 */       .a(Character.valueOf('#'), Blocks.RED_SANDSTONE)
/* 2863 */       .a("###")
/* 2864 */       .a("###")
/* 2865 */       .a("has_red_sandstone", a(Blocks.RED_SANDSTONE))
/* 2866 */       .a(var0);
/*      */     
/* 2868 */     jh.a(Blocks.MOSSY_STONE_BRICK_WALL, 6)
/* 2869 */       .a(Character.valueOf('#'), Blocks.MOSSY_STONE_BRICKS)
/* 2870 */       .a("###")
/* 2871 */       .a("###")
/* 2872 */       .a("has_mossy_stone_bricks", a(Blocks.MOSSY_STONE_BRICKS))
/* 2873 */       .a(var0);
/*      */     
/* 2875 */     jh.a(Blocks.GRANITE_WALL, 6)
/* 2876 */       .a(Character.valueOf('#'), Blocks.GRANITE)
/* 2877 */       .a("###")
/* 2878 */       .a("###")
/* 2879 */       .a("has_granite", a(Blocks.GRANITE))
/* 2880 */       .a(var0);
/*      */     
/* 2882 */     jh.a(Blocks.STONE_BRICK_WALL, 6)
/* 2883 */       .a(Character.valueOf('#'), Blocks.STONE_BRICKS)
/* 2884 */       .a("###")
/* 2885 */       .a("###")
/* 2886 */       .a("has_stone_bricks", a(Blocks.STONE_BRICKS))
/* 2887 */       .a(var0);
/*      */     
/* 2889 */     jh.a(Blocks.NETHER_BRICK_WALL, 6)
/* 2890 */       .a(Character.valueOf('#'), Blocks.NETHER_BRICKS)
/* 2891 */       .a("###")
/* 2892 */       .a("###")
/* 2893 */       .a("has_nether_bricks", a(Blocks.NETHER_BRICKS))
/* 2894 */       .a(var0);
/*      */     
/* 2896 */     jh.a(Blocks.ANDESITE_WALL, 6)
/* 2897 */       .a(Character.valueOf('#'), Blocks.ANDESITE)
/* 2898 */       .a("###")
/* 2899 */       .a("###")
/* 2900 */       .a("has_andesite", a(Blocks.ANDESITE))
/* 2901 */       .a(var0);
/*      */     
/* 2903 */     jh.a(Blocks.RED_NETHER_BRICK_WALL, 6)
/* 2904 */       .a(Character.valueOf('#'), Blocks.RED_NETHER_BRICKS)
/* 2905 */       .a("###")
/* 2906 */       .a("###")
/* 2907 */       .a("has_red_nether_bricks", a(Blocks.RED_NETHER_BRICKS))
/* 2908 */       .a(var0);
/*      */     
/* 2910 */     jh.a(Blocks.SANDSTONE_WALL, 6)
/* 2911 */       .a(Character.valueOf('#'), Blocks.SANDSTONE)
/* 2912 */       .a("###")
/* 2913 */       .a("###")
/* 2914 */       .a("has_sandstone", a(Blocks.SANDSTONE))
/* 2915 */       .a(var0);
/*      */     
/* 2917 */     jh.a(Blocks.END_STONE_BRICK_WALL, 6)
/* 2918 */       .a(Character.valueOf('#'), Blocks.END_STONE_BRICKS)
/* 2919 */       .a("###")
/* 2920 */       .a("###")
/* 2921 */       .a("has_end_stone_bricks", a(Blocks.END_STONE_BRICKS))
/* 2922 */       .a(var0);
/*      */     
/* 2924 */     jh.a(Blocks.DIORITE_WALL, 6)
/* 2925 */       .a(Character.valueOf('#'), Blocks.DIORITE)
/* 2926 */       .a("###")
/* 2927 */       .a("###")
/* 2928 */       .a("has_diorite", a(Blocks.DIORITE))
/* 2929 */       .a(var0);
/*      */     
/* 2931 */     ji.a(Items.CREEPER_BANNER_PATTERN)
/* 2932 */       .b(Items.PAPER)
/* 2933 */       .b(Items.CREEPER_HEAD)
/* 2934 */       .a("has_creeper_head", a(Items.CREEPER_HEAD))
/* 2935 */       .a(var0);
/*      */     
/* 2937 */     ji.a(Items.SKULL_BANNER_PATTERN)
/* 2938 */       .b(Items.PAPER)
/* 2939 */       .b(Items.WITHER_SKELETON_SKULL)
/* 2940 */       .a("has_wither_skeleton_skull", a(Items.WITHER_SKELETON_SKULL))
/* 2941 */       .a(var0);
/*      */     
/* 2943 */     ji.a(Items.FLOWER_BANNER_PATTERN)
/* 2944 */       .b(Items.PAPER)
/* 2945 */       .b(Blocks.OXEYE_DAISY)
/* 2946 */       .a("has_oxeye_daisy", a(Blocks.OXEYE_DAISY))
/* 2947 */       .a(var0);
/*      */     
/* 2949 */     ji.a(Items.MOJANG_BANNER_PATTERN)
/* 2950 */       .b(Items.PAPER)
/* 2951 */       .b(Items.ENCHANTED_GOLDEN_APPLE)
/* 2952 */       .a("has_enchanted_golden_apple", a(Items.ENCHANTED_GOLDEN_APPLE))
/* 2953 */       .a(var0);
/*      */     
/* 2955 */     jh.a(Blocks.SCAFFOLDING, 6)
/* 2956 */       .a(Character.valueOf('~'), Items.STRING)
/* 2957 */       .a(Character.valueOf('I'), Blocks.BAMBOO)
/* 2958 */       .a("I~I")
/* 2959 */       .a("I I")
/* 2960 */       .a("I I")
/* 2961 */       .a("has_bamboo", a(Blocks.BAMBOO))
/* 2962 */       .a(var0);
/*      */     
/* 2964 */     jh.a(Blocks.GRINDSTONE)
/* 2965 */       .a(Character.valueOf('I'), Items.STICK)
/* 2966 */       .a(Character.valueOf('-'), Blocks.STONE_SLAB)
/* 2967 */       .a(Character.valueOf('#'), TagsItem.PLANKS)
/* 2968 */       .a("I-I")
/* 2969 */       .a("# #")
/* 2970 */       .a("has_stone_slab", a(Blocks.STONE_SLAB))
/* 2971 */       .a(var0);
/*      */     
/* 2973 */     jh.a(Blocks.BLAST_FURNACE)
/* 2974 */       .a(Character.valueOf('#'), Blocks.SMOOTH_STONE)
/* 2975 */       .a(Character.valueOf('X'), Blocks.FURNACE)
/* 2976 */       .a(Character.valueOf('I'), Items.IRON_INGOT)
/* 2977 */       .a("III")
/* 2978 */       .a("IXI")
/* 2979 */       .a("###")
/* 2980 */       .a("has_smooth_stone", a(Blocks.SMOOTH_STONE))
/* 2981 */       .a(var0);
/*      */     
/* 2983 */     jh.a(Blocks.SMOKER)
/* 2984 */       .a(Character.valueOf('#'), TagsItem.LOGS)
/* 2985 */       .a(Character.valueOf('X'), Blocks.FURNACE)
/* 2986 */       .a(" # ")
/* 2987 */       .a("#X#")
/* 2988 */       .a(" # ")
/* 2989 */       .a("has_furnace", a(Blocks.FURNACE))
/* 2990 */       .a(var0);
/*      */     
/* 2992 */     jh.a(Blocks.CARTOGRAPHY_TABLE)
/* 2993 */       .a(Character.valueOf('#'), TagsItem.PLANKS)
/* 2994 */       .a(Character.valueOf('@'), Items.PAPER)
/* 2995 */       .a("@@")
/* 2996 */       .a("##")
/* 2997 */       .a("##")
/* 2998 */       .a("has_paper", a(Items.PAPER))
/* 2999 */       .a(var0);
/*      */     
/* 3001 */     jh.a(Blocks.SMITHING_TABLE)
/* 3002 */       .a(Character.valueOf('#'), TagsItem.PLANKS)
/* 3003 */       .a(Character.valueOf('@'), Items.IRON_INGOT)
/* 3004 */       .a("@@")
/* 3005 */       .a("##")
/* 3006 */       .a("##")
/* 3007 */       .a("has_iron_ingot", a(Items.IRON_INGOT))
/* 3008 */       .a(var0);
/*      */     
/* 3010 */     jh.a(Blocks.FLETCHING_TABLE)
/* 3011 */       .a(Character.valueOf('#'), TagsItem.PLANKS)
/* 3012 */       .a(Character.valueOf('@'), Items.FLINT)
/* 3013 */       .a("@@")
/* 3014 */       .a("##")
/* 3015 */       .a("##")
/* 3016 */       .a("has_flint", a(Items.FLINT))
/* 3017 */       .a(var0);
/*      */     
/* 3019 */     jh.a(Blocks.STONECUTTER)
/* 3020 */       .a(Character.valueOf('I'), Items.IRON_INGOT)
/* 3021 */       .a(Character.valueOf('#'), Blocks.STONE)
/* 3022 */       .a(" I ")
/* 3023 */       .a("###")
/* 3024 */       .a("has_stone", a(Blocks.STONE))
/* 3025 */       .a(var0);
/*      */     
/* 3027 */     jh.a(Blocks.LODESTONE)
/* 3028 */       .a(Character.valueOf('S'), Items.dJ)
/* 3029 */       .a(Character.valueOf('#'), Items.NETHERITE_INGOT)
/* 3030 */       .a("SSS")
/* 3031 */       .a("S#S")
/* 3032 */       .a("SSS")
/* 3033 */       .a("has_netherite_ingot", a(Items.NETHERITE_INGOT))
/* 3034 */       .a(var0);
/*      */     
/* 3036 */     jh.a(Blocks.NETHERITE_BLOCK)
/* 3037 */       .a(Character.valueOf('#'), Items.NETHERITE_INGOT)
/* 3038 */       .a("###")
/* 3039 */       .a("###")
/* 3040 */       .a("###")
/* 3041 */       .a("has_netherite_ingot", a(Items.NETHERITE_INGOT))
/* 3042 */       .a(var0);
/*      */     
/* 3044 */     ji.a(Items.NETHERITE_INGOT, 9)
/* 3045 */       .b(Blocks.NETHERITE_BLOCK)
/* 3046 */       .a("netherite_ingot")
/* 3047 */       .a("has_netherite_block", a(Blocks.NETHERITE_BLOCK))
/* 3048 */       .a(var0, "netherite_ingot_from_netherite_block");
/*      */     
/* 3050 */     ji.a(Items.NETHERITE_INGOT)
/* 3051 */       .b(Items.NETHERITE_SCRAP, 4)
/* 3052 */       .b(Items.GOLD_INGOT, 4)
/* 3053 */       .a("netherite_ingot")
/* 3054 */       .a("has_netherite_scrap", a(Items.NETHERITE_SCRAP))
/* 3055 */       .a(var0);
/*      */     
/* 3057 */     jh.a(Blocks.RESPAWN_ANCHOR)
/* 3058 */       .a(Character.valueOf('O'), Blocks.CRYING_OBSIDIAN)
/* 3059 */       .a(Character.valueOf('G'), Blocks.GLOWSTONE)
/* 3060 */       .a("OOO")
/* 3061 */       .a("GGG")
/* 3062 */       .a("OOO")
/* 3063 */       .a("has_obsidian", a(Blocks.CRYING_OBSIDIAN))
/* 3064 */       .a(var0);
/*      */     
/* 3066 */     jh.a(Blocks.BLACKSTONE_STAIRS, 4)
/* 3067 */       .a(Character.valueOf('#'), Blocks.BLACKSTONE)
/* 3068 */       .a("#  ")
/* 3069 */       .a("## ")
/* 3070 */       .a("###")
/* 3071 */       .a("has_blackstone", a(Blocks.BLACKSTONE))
/* 3072 */       .a(var0);
/*      */     
/* 3074 */     jh.a(Blocks.POLISHED_BLACKSTONE_STAIRS, 4)
/* 3075 */       .a(Character.valueOf('#'), Blocks.POLISHED_BLACKSTONE)
/* 3076 */       .a("#  ")
/* 3077 */       .a("## ")
/* 3078 */       .a("###")
/* 3079 */       .a("has_polished_blackstone", a(Blocks.POLISHED_BLACKSTONE))
/* 3080 */       .a(var0);
/*      */     
/* 3082 */     jh.a(Blocks.POLISHED_BLACKSTONE_BRICK_STAIRS, 4)
/* 3083 */       .a(Character.valueOf('#'), Blocks.POLISHED_BLACKSTONE_BRICKS)
/* 3084 */       .a("#  ")
/* 3085 */       .a("## ")
/* 3086 */       .a("###")
/* 3087 */       .a("has_polished_blackstone_bricks", a(Blocks.POLISHED_BLACKSTONE_BRICKS))
/* 3088 */       .a(var0);
/*      */     
/* 3090 */     jh.a(Blocks.BLACKSTONE_SLAB, 6)
/* 3091 */       .a(Character.valueOf('#'), Blocks.BLACKSTONE)
/* 3092 */       .a("###")
/* 3093 */       .a("has_blackstone", a(Blocks.BLACKSTONE))
/* 3094 */       .a(var0);
/*      */     
/* 3096 */     jh.a(Blocks.POLISHED_BLACKSTONE_SLAB, 6)
/* 3097 */       .a(Character.valueOf('#'), Blocks.POLISHED_BLACKSTONE)
/* 3098 */       .a("###")
/* 3099 */       .a("has_polished_blackstone", a(Blocks.POLISHED_BLACKSTONE))
/* 3100 */       .a(var0);
/*      */     
/* 3102 */     jh.a(Blocks.POLISHED_BLACKSTONE_BRICK_SLAB, 6)
/* 3103 */       .a(Character.valueOf('#'), Blocks.POLISHED_BLACKSTONE_BRICKS)
/* 3104 */       .a("###")
/* 3105 */       .a("has_polished_blackstone_bricks", a(Blocks.POLISHED_BLACKSTONE_BRICKS))
/* 3106 */       .a(var0);
/*      */     
/* 3108 */     jh.a(Blocks.POLISHED_BLACKSTONE, 4)
/* 3109 */       .a(Character.valueOf('S'), Blocks.BLACKSTONE)
/* 3110 */       .a("SS")
/* 3111 */       .a("SS")
/* 3112 */       .a("has_blackstone", a(Blocks.BLACKSTONE))
/* 3113 */       .a(var0);
/*      */     
/* 3115 */     jh.a(Blocks.POLISHED_BLACKSTONE_BRICKS, 4)
/* 3116 */       .a(Character.valueOf('#'), Blocks.POLISHED_BLACKSTONE)
/* 3117 */       .a("##")
/* 3118 */       .a("##")
/* 3119 */       .a("has_polished_blackstone", a(Blocks.POLISHED_BLACKSTONE))
/* 3120 */       .a(var0);
/*      */     
/* 3122 */     jh.a(Blocks.CHISELED_POLISHED_BLACKSTONE)
/* 3123 */       .a(Character.valueOf('#'), Blocks.POLISHED_BLACKSTONE_SLAB)
/* 3124 */       .a("#")
/* 3125 */       .a("#")
/* 3126 */       .a("has_polished_blackstone", a(Blocks.POLISHED_BLACKSTONE))
/* 3127 */       .a(var0);
/*      */     
/* 3129 */     jh.a(Blocks.BLACKSTONE_WALL, 6)
/* 3130 */       .a(Character.valueOf('#'), Blocks.BLACKSTONE)
/* 3131 */       .a("###")
/* 3132 */       .a("###")
/* 3133 */       .a("has_blackstone", a(Blocks.BLACKSTONE))
/* 3134 */       .a(var0);
/*      */     
/* 3136 */     jh.a(Blocks.POLISHED_BLACKSTONE_WALL, 6)
/* 3137 */       .a(Character.valueOf('#'), Blocks.POLISHED_BLACKSTONE)
/* 3138 */       .a("###")
/* 3139 */       .a("###")
/* 3140 */       .a("has_polished_blackstone", a(Blocks.POLISHED_BLACKSTONE))
/* 3141 */       .a(var0);
/*      */     
/* 3143 */     jh.a(Blocks.POLISHED_BLACKSTONE_BRICK_WALL, 6)
/* 3144 */       .a(Character.valueOf('#'), Blocks.POLISHED_BLACKSTONE_BRICKS)
/* 3145 */       .a("###")
/* 3146 */       .a("###")
/* 3147 */       .a("has_polished_blackstone_bricks", a(Blocks.POLISHED_BLACKSTONE_BRICKS))
/* 3148 */       .a(var0);
/*      */     
/* 3150 */     ji.a(Blocks.POLISHED_BLACKSTONE_BUTTON)
/* 3151 */       .b(Blocks.POLISHED_BLACKSTONE)
/* 3152 */       .a("has_polished_blackstone", a(Blocks.POLISHED_BLACKSTONE))
/* 3153 */       .a(var0);
/*      */     
/* 3155 */     jh.a(Blocks.POLISHED_BLACKSTONE_PRESSURE_PLATE)
/* 3156 */       .a(Character.valueOf('#'), Blocks.POLISHED_BLACKSTONE)
/* 3157 */       .a("##")
/* 3158 */       .a("has_polished_blackstone", a(Blocks.POLISHED_BLACKSTONE))
/* 3159 */       .a(var0);
/*      */     
/* 3161 */     jh.a(Blocks.CHAIN)
/* 3162 */       .a(Character.valueOf('I'), Items.IRON_INGOT)
/* 3163 */       .a(Character.valueOf('N'), Items.IRON_NUGGET)
/* 3164 */       .a("N")
/* 3165 */       .a("I")
/* 3166 */       .a("N")
/* 3167 */       .a("has_iron_nugget", a(Items.IRON_NUGGET))
/* 3168 */       .a("has_iron_ingot", a(Items.IRON_INGOT))
/* 3169 */       .a(var0);
/*      */     
/* 3171 */     jl.a(RecipeSerializer.c)
/* 3172 */       .a(var0, "armor_dye");
/*      */     
/* 3174 */     jl.a(RecipeSerializer.k)
/* 3175 */       .a(var0, "banner_duplicate");
/*      */     
/* 3177 */     jl.a(RecipeSerializer.d)
/* 3178 */       .a(var0, "book_cloning");
/*      */     
/* 3180 */     jl.a(RecipeSerializer.g)
/* 3181 */       .a(var0, "firework_rocket");
/*      */     
/* 3183 */     jl.a(RecipeSerializer.h)
/* 3184 */       .a(var0, "firework_star");
/*      */     
/* 3186 */     jl.a(RecipeSerializer.i)
/* 3187 */       .a(var0, "firework_star_fade");
/*      */     
/* 3189 */     jl.a(RecipeSerializer.e)
/* 3190 */       .a(var0, "map_cloning");
/*      */     
/* 3192 */     jl.a(RecipeSerializer.f)
/* 3193 */       .a(var0, "map_extending");
/*      */     
/* 3195 */     jl.a(RecipeSerializer.o)
/* 3196 */       .a(var0, "repair_item");
/*      */     
/* 3198 */     jl.a(RecipeSerializer.l)
/* 3199 */       .a(var0, "shield_decoration");
/*      */     
/* 3201 */     jl.a(RecipeSerializer.m)
/* 3202 */       .a(var0, "shulker_box_coloring");
/*      */     
/* 3204 */     jl.a(RecipeSerializer.j)
/* 3205 */       .a(var0, "tipped_arrow");
/*      */     
/* 3207 */     jl.a(RecipeSerializer.n)
/* 3208 */       .a(var0, "suspicious_stew");
/*      */ 
/*      */     
/* 3211 */     jj.c(RecipeItemStack.a(new IMaterial[] { Items.POTATO }, ), Items.BAKED_POTATO, 0.35F, 200)
/* 3212 */       .a("has_potato", a(Items.POTATO))
/* 3213 */       .a(var0);
/*      */     
/* 3215 */     jj.c(RecipeItemStack.a(new IMaterial[] { Items.CLAY_BALL }, ), Items.BRICK, 0.3F, 200)
/* 3216 */       .a("has_clay_ball", a(Items.CLAY_BALL))
/* 3217 */       .a(var0);
/*      */     
/* 3219 */     jj.c(RecipeItemStack.a(TagsItem.LOGS_THAT_BURN), Items.CHARCOAL, 0.15F, 200)
/* 3220 */       .a("has_log", a(TagsItem.LOGS_THAT_BURN))
/* 3221 */       .a(var0);
/*      */     
/* 3223 */     jj.c(RecipeItemStack.a(new IMaterial[] { Items.CHORUS_FRUIT }, ), Items.POPPED_CHORUS_FRUIT, 0.1F, 200)
/* 3224 */       .a("has_chorus_fruit", a(Items.CHORUS_FRUIT))
/* 3225 */       .a(var0);
/*      */     
/* 3227 */     jj.c(RecipeItemStack.a(new IMaterial[] { Blocks.COAL_ORE.getItem() }, ), Items.COAL, 0.1F, 200)
/* 3228 */       .a("has_coal_ore", a(Blocks.COAL_ORE))
/* 3229 */       .a(var0, "coal_from_smelting");
/*      */     
/* 3231 */     jj.c(RecipeItemStack.a(new IMaterial[] { Items.BEEF }, ), Items.COOKED_BEEF, 0.35F, 200)
/* 3232 */       .a("has_beef", a(Items.BEEF))
/* 3233 */       .a(var0);
/*      */     
/* 3235 */     jj.c(RecipeItemStack.a(new IMaterial[] { Items.CHICKEN }, ), Items.COOKED_CHICKEN, 0.35F, 200)
/* 3236 */       .a("has_chicken", a(Items.CHICKEN))
/* 3237 */       .a(var0);
/*      */     
/* 3239 */     jj.c(RecipeItemStack.a(new IMaterial[] { Items.COD }, ), Items.COOKED_COD, 0.35F, 200)
/* 3240 */       .a("has_cod", a(Items.COD))
/* 3241 */       .a(var0);
/*      */     
/* 3243 */     jj.c(RecipeItemStack.a(new IMaterial[] { Blocks.KELP }, ), Items.DRIED_KELP, 0.1F, 200)
/* 3244 */       .a("has_kelp", a(Blocks.KELP))
/* 3245 */       .a(var0, "dried_kelp_from_smelting");
/*      */     
/* 3247 */     jj.c(RecipeItemStack.a(new IMaterial[] { Items.SALMON }, ), Items.COOKED_SALMON, 0.35F, 200)
/* 3248 */       .a("has_salmon", a(Items.SALMON))
/* 3249 */       .a(var0);
/*      */     
/* 3251 */     jj.c(RecipeItemStack.a(new IMaterial[] { Items.MUTTON }, ), Items.COOKED_MUTTON, 0.35F, 200)
/* 3252 */       .a("has_mutton", a(Items.MUTTON))
/* 3253 */       .a(var0);
/*      */     
/* 3255 */     jj.c(RecipeItemStack.a(new IMaterial[] { Items.PORKCHOP }, ), Items.COOKED_PORKCHOP, 0.35F, 200)
/* 3256 */       .a("has_porkchop", a(Items.PORKCHOP))
/* 3257 */       .a(var0);
/*      */     
/* 3259 */     jj.c(RecipeItemStack.a(new IMaterial[] { Items.RABBIT }, ), Items.COOKED_RABBIT, 0.35F, 200)
/* 3260 */       .a("has_rabbit", a(Items.RABBIT))
/* 3261 */       .a(var0);
/*      */     
/* 3263 */     jj.c(RecipeItemStack.a(new IMaterial[] { Blocks.DIAMOND_ORE.getItem() }, ), Items.DIAMOND, 1.0F, 200)
/* 3264 */       .a("has_diamond_ore", a(Blocks.DIAMOND_ORE))
/* 3265 */       .a(var0, "diamond_from_smelting");
/*      */     
/* 3267 */     jj.c(RecipeItemStack.a(new IMaterial[] { Blocks.LAPIS_ORE.getItem() }, ), Items.LAPIS_LAZULI, 0.2F, 200)
/* 3268 */       .a("has_lapis_ore", a(Blocks.LAPIS_ORE))
/* 3269 */       .a(var0, "lapis_from_smelting");
/*      */     
/* 3271 */     jj.c(RecipeItemStack.a(new IMaterial[] { Blocks.EMERALD_ORE.getItem() }, ), Items.EMERALD, 1.0F, 200)
/* 3272 */       .a("has_emerald_ore", a(Blocks.EMERALD_ORE))
/* 3273 */       .a(var0, "emerald_from_smelting");
/*      */     
/* 3275 */     jj.c(RecipeItemStack.a(TagsItem.SAND), Blocks.GLASS.getItem(), 0.1F, 200)
/* 3276 */       .a("has_sand", a(TagsItem.SAND))
/* 3277 */       .a(var0);
/*      */     
/* 3279 */     jj.c(RecipeItemStack.a(TagsItem.GOLD_ORES), Items.GOLD_INGOT, 1.0F, 200)
/* 3280 */       .a("has_gold_ore", a(TagsItem.GOLD_ORES))
/* 3281 */       .a(var0);
/*      */     
/* 3283 */     jj.c(RecipeItemStack.a(new IMaterial[] { Blocks.SEA_PICKLE.getItem() }, ), Items.LIME_DYE, 0.1F, 200)
/* 3284 */       .a("has_sea_pickle", a(Blocks.SEA_PICKLE))
/* 3285 */       .a(var0, "lime_dye_from_smelting");
/*      */     
/* 3287 */     jj.c(RecipeItemStack.a(new IMaterial[] { Blocks.CACTUS.getItem() }, ), Items.GREEN_DYE, 1.0F, 200)
/* 3288 */       .a("has_cactus", a(Blocks.CACTUS))
/* 3289 */       .a(var0);
/*      */     
/* 3291 */     jj.c(RecipeItemStack.a(new IMaterial[] { Items.GOLDEN_PICKAXE, Items.GOLDEN_SHOVEL, Items.GOLDEN_AXE, Items.GOLDEN_HOE, Items.GOLDEN_SWORD, Items.GOLDEN_HELMET, Items.GOLDEN_CHESTPLATE, Items.GOLDEN_LEGGINGS, Items.GOLDEN_BOOTS, Items.GOLDEN_HORSE_ARMOR }, ), Items.GOLD_NUGGET, 0.1F, 200)
/* 3292 */       .a("has_golden_pickaxe", a(Items.GOLDEN_PICKAXE))
/* 3293 */       .a("has_golden_shovel", a(Items.GOLDEN_SHOVEL))
/* 3294 */       .a("has_golden_axe", a(Items.GOLDEN_AXE))
/* 3295 */       .a("has_golden_hoe", a(Items.GOLDEN_HOE))
/* 3296 */       .a("has_golden_sword", a(Items.GOLDEN_SWORD))
/* 3297 */       .a("has_golden_helmet", a(Items.GOLDEN_HELMET))
/* 3298 */       .a("has_golden_chestplate", a(Items.GOLDEN_CHESTPLATE))
/* 3299 */       .a("has_golden_leggings", a(Items.GOLDEN_LEGGINGS))
/* 3300 */       .a("has_golden_boots", a(Items.GOLDEN_BOOTS))
/* 3301 */       .a("has_golden_horse_armor", a(Items.GOLDEN_HORSE_ARMOR))
/* 3302 */       .a(var0, "gold_nugget_from_smelting");
/*      */     
/* 3304 */     jj.c(RecipeItemStack.a(new IMaterial[] { Items.IRON_PICKAXE, Items.IRON_SHOVEL, Items.IRON_AXE, Items.IRON_HOE, Items.IRON_SWORD, Items.IRON_HELMET, Items.IRON_CHESTPLATE, Items.IRON_LEGGINGS, Items.IRON_BOOTS, Items.IRON_HORSE_ARMOR, Items.CHAINMAIL_HELMET, Items.CHAINMAIL_CHESTPLATE, Items.CHAINMAIL_LEGGINGS, Items.CHAINMAIL_BOOTS }, ), Items.IRON_NUGGET, 0.1F, 200)
/* 3305 */       .a("has_iron_pickaxe", a(Items.IRON_PICKAXE))
/* 3306 */       .a("has_iron_shovel", a(Items.IRON_SHOVEL))
/* 3307 */       .a("has_iron_axe", a(Items.IRON_AXE))
/* 3308 */       .a("has_iron_hoe", a(Items.IRON_HOE))
/* 3309 */       .a("has_iron_sword", a(Items.IRON_SWORD))
/* 3310 */       .a("has_iron_helmet", a(Items.IRON_HELMET))
/* 3311 */       .a("has_iron_chestplate", a(Items.IRON_CHESTPLATE))
/* 3312 */       .a("has_iron_leggings", a(Items.IRON_LEGGINGS))
/* 3313 */       .a("has_iron_boots", a(Items.IRON_BOOTS))
/* 3314 */       .a("has_iron_horse_armor", a(Items.IRON_HORSE_ARMOR))
/* 3315 */       .a("has_chainmail_helmet", a(Items.CHAINMAIL_HELMET))
/* 3316 */       .a("has_chainmail_chestplate", a(Items.CHAINMAIL_CHESTPLATE))
/* 3317 */       .a("has_chainmail_leggings", a(Items.CHAINMAIL_LEGGINGS))
/* 3318 */       .a("has_chainmail_boots", a(Items.CHAINMAIL_BOOTS))
/* 3319 */       .a(var0, "iron_nugget_from_smelting");
/*      */     
/* 3321 */     jj.c(RecipeItemStack.a(new IMaterial[] { Blocks.IRON_ORE.getItem() }, ), Items.IRON_INGOT, 0.7F, 200)
/* 3322 */       .a("has_iron_ore", a(Blocks.IRON_ORE.getItem()))
/* 3323 */       .a(var0);
/*      */     
/* 3325 */     jj.c(RecipeItemStack.a(new IMaterial[] { Blocks.CLAY }, ), Blocks.TERRACOTTA.getItem(), 0.35F, 200)
/* 3326 */       .a("has_clay_block", a(Blocks.CLAY))
/* 3327 */       .a(var0);
/*      */     
/* 3329 */     jj.c(RecipeItemStack.a(new IMaterial[] { Blocks.NETHERRACK }, ), Items.NETHER_BRICK, 0.1F, 200)
/* 3330 */       .a("has_netherrack", a(Blocks.NETHERRACK))
/* 3331 */       .a(var0);
/*      */     
/* 3333 */     jj.c(RecipeItemStack.a(new IMaterial[] { Blocks.NETHER_QUARTZ_ORE }, ), Items.QUARTZ, 0.2F, 200)
/* 3334 */       .a("has_nether_quartz_ore", a(Blocks.NETHER_QUARTZ_ORE))
/* 3335 */       .a(var0);
/*      */     
/* 3337 */     jj.c(RecipeItemStack.a(new IMaterial[] { Blocks.REDSTONE_ORE }, ), Items.REDSTONE, 0.7F, 200)
/* 3338 */       .a("has_redstone_ore", a(Blocks.REDSTONE_ORE))
/* 3339 */       .a(var0, "redstone_from_smelting");
/*      */     
/* 3341 */     jj.c(RecipeItemStack.a(new IMaterial[] { Blocks.WET_SPONGE }, ), Blocks.SPONGE.getItem(), 0.15F, 200)
/* 3342 */       .a("has_wet_sponge", a(Blocks.WET_SPONGE))
/* 3343 */       .a(var0);
/*      */     
/* 3345 */     jj.c(RecipeItemStack.a(new IMaterial[] { Blocks.COBBLESTONE }, ), Blocks.STONE.getItem(), 0.1F, 200)
/* 3346 */       .a("has_cobblestone", a(Blocks.COBBLESTONE))
/* 3347 */       .a(var0);
/*      */     
/* 3349 */     jj.c(RecipeItemStack.a(new IMaterial[] { Blocks.STONE }, ), Blocks.SMOOTH_STONE.getItem(), 0.1F, 200)
/* 3350 */       .a("has_stone", a(Blocks.STONE))
/* 3351 */       .a(var0);
/*      */     
/* 3353 */     jj.c(RecipeItemStack.a(new IMaterial[] { Blocks.SANDSTONE }, ), Blocks.SMOOTH_SANDSTONE.getItem(), 0.1F, 200)
/* 3354 */       .a("has_sandstone", a(Blocks.SANDSTONE))
/* 3355 */       .a(var0);
/*      */     
/* 3357 */     jj.c(RecipeItemStack.a(new IMaterial[] { Blocks.RED_SANDSTONE }, ), Blocks.SMOOTH_RED_SANDSTONE.getItem(), 0.1F, 200)
/* 3358 */       .a("has_red_sandstone", a(Blocks.RED_SANDSTONE))
/* 3359 */       .a(var0);
/*      */     
/* 3361 */     jj.c(RecipeItemStack.a(new IMaterial[] { Blocks.QUARTZ_BLOCK }, ), Blocks.SMOOTH_QUARTZ.getItem(), 0.1F, 200)
/* 3362 */       .a("has_quartz_block", a(Blocks.QUARTZ_BLOCK))
/* 3363 */       .a(var0);
/*      */     
/* 3365 */     jj.c(RecipeItemStack.a(new IMaterial[] { Blocks.STONE_BRICKS }, ), Blocks.CRACKED_STONE_BRICKS.getItem(), 0.1F, 200)
/* 3366 */       .a("has_stone_bricks", a(Blocks.STONE_BRICKS))
/* 3367 */       .a(var0);
/*      */     
/* 3369 */     jj.c(RecipeItemStack.a(new IMaterial[] { Blocks.BLACK_TERRACOTTA }, ), Blocks.BLACK_GLAZED_TERRACOTTA.getItem(), 0.1F, 200)
/* 3370 */       .a("has_black_terracotta", a(Blocks.BLACK_TERRACOTTA))
/* 3371 */       .a(var0);
/*      */     
/* 3373 */     jj.c(RecipeItemStack.a(new IMaterial[] { Blocks.BLUE_TERRACOTTA }, ), Blocks.BLUE_GLAZED_TERRACOTTA.getItem(), 0.1F, 200)
/* 3374 */       .a("has_blue_terracotta", a(Blocks.BLUE_TERRACOTTA))
/* 3375 */       .a(var0);
/*      */     
/* 3377 */     jj.c(RecipeItemStack.a(new IMaterial[] { Blocks.BROWN_TERRACOTTA }, ), Blocks.BROWN_GLAZED_TERRACOTTA.getItem(), 0.1F, 200)
/* 3378 */       .a("has_brown_terracotta", a(Blocks.BROWN_TERRACOTTA))
/* 3379 */       .a(var0);
/*      */     
/* 3381 */     jj.c(RecipeItemStack.a(new IMaterial[] { Blocks.CYAN_TERRACOTTA }, ), Blocks.CYAN_GLAZED_TERRACOTTA.getItem(), 0.1F, 200)
/* 3382 */       .a("has_cyan_terracotta", a(Blocks.CYAN_TERRACOTTA))
/* 3383 */       .a(var0);
/*      */     
/* 3385 */     jj.c(RecipeItemStack.a(new IMaterial[] { Blocks.GRAY_TERRACOTTA }, ), Blocks.GRAY_GLAZED_TERRACOTTA.getItem(), 0.1F, 200)
/* 3386 */       .a("has_gray_terracotta", a(Blocks.GRAY_TERRACOTTA))
/* 3387 */       .a(var0);
/*      */     
/* 3389 */     jj.c(RecipeItemStack.a(new IMaterial[] { Blocks.GREEN_TERRACOTTA }, ), Blocks.GREEN_GLAZED_TERRACOTTA.getItem(), 0.1F, 200)
/* 3390 */       .a("has_green_terracotta", a(Blocks.GREEN_TERRACOTTA))
/* 3391 */       .a(var0);
/*      */     
/* 3393 */     jj.c(RecipeItemStack.a(new IMaterial[] { Blocks.LIGHT_BLUE_TERRACOTTA }, ), Blocks.LIGHT_BLUE_GLAZED_TERRACOTTA.getItem(), 0.1F, 200)
/* 3394 */       .a("has_light_blue_terracotta", a(Blocks.LIGHT_BLUE_TERRACOTTA))
/* 3395 */       .a(var0);
/*      */     
/* 3397 */     jj.c(RecipeItemStack.a(new IMaterial[] { Blocks.LIGHT_GRAY_TERRACOTTA }, ), Blocks.LIGHT_GRAY_GLAZED_TERRACOTTA.getItem(), 0.1F, 200)
/* 3398 */       .a("has_light_gray_terracotta", a(Blocks.LIGHT_GRAY_TERRACOTTA))
/* 3399 */       .a(var0);
/*      */     
/* 3401 */     jj.c(RecipeItemStack.a(new IMaterial[] { Blocks.LIME_TERRACOTTA }, ), Blocks.LIME_GLAZED_TERRACOTTA.getItem(), 0.1F, 200)
/* 3402 */       .a("has_lime_terracotta", a(Blocks.LIME_TERRACOTTA))
/* 3403 */       .a(var0);
/*      */     
/* 3405 */     jj.c(RecipeItemStack.a(new IMaterial[] { Blocks.MAGENTA_TERRACOTTA }, ), Blocks.MAGENTA_GLAZED_TERRACOTTA.getItem(), 0.1F, 200)
/* 3406 */       .a("has_magenta_terracotta", a(Blocks.MAGENTA_TERRACOTTA))
/* 3407 */       .a(var0);
/*      */     
/* 3409 */     jj.c(RecipeItemStack.a(new IMaterial[] { Blocks.ORANGE_TERRACOTTA }, ), Blocks.ORANGE_GLAZED_TERRACOTTA.getItem(), 0.1F, 200)
/* 3410 */       .a("has_orange_terracotta", a(Blocks.ORANGE_TERRACOTTA))
/* 3411 */       .a(var0);
/*      */     
/* 3413 */     jj.c(RecipeItemStack.a(new IMaterial[] { Blocks.PINK_TERRACOTTA }, ), Blocks.PINK_GLAZED_TERRACOTTA.getItem(), 0.1F, 200)
/* 3414 */       .a("has_pink_terracotta", a(Blocks.PINK_TERRACOTTA))
/* 3415 */       .a(var0);
/*      */     
/* 3417 */     jj.c(RecipeItemStack.a(new IMaterial[] { Blocks.PURPLE_TERRACOTTA }, ), Blocks.PURPLE_GLAZED_TERRACOTTA.getItem(), 0.1F, 200)
/* 3418 */       .a("has_purple_terracotta", a(Blocks.PURPLE_TERRACOTTA))
/* 3419 */       .a(var0);
/*      */     
/* 3421 */     jj.c(RecipeItemStack.a(new IMaterial[] { Blocks.RED_TERRACOTTA }, ), Blocks.RED_GLAZED_TERRACOTTA.getItem(), 0.1F, 200)
/* 3422 */       .a("has_red_terracotta", a(Blocks.RED_TERRACOTTA))
/* 3423 */       .a(var0);
/*      */     
/* 3425 */     jj.c(RecipeItemStack.a(new IMaterial[] { Blocks.WHITE_TERRACOTTA }, ), Blocks.WHITE_GLAZED_TERRACOTTA.getItem(), 0.1F, 200)
/* 3426 */       .a("has_white_terracotta", a(Blocks.WHITE_TERRACOTTA))
/* 3427 */       .a(var0);
/*      */     
/* 3429 */     jj.c(RecipeItemStack.a(new IMaterial[] { Blocks.YELLOW_TERRACOTTA }, ), Blocks.YELLOW_GLAZED_TERRACOTTA.getItem(), 0.1F, 200)
/* 3430 */       .a("has_yellow_terracotta", a(Blocks.YELLOW_TERRACOTTA))
/* 3431 */       .a(var0);
/*      */     
/* 3433 */     jj.c(RecipeItemStack.a(new IMaterial[] { Blocks.ANCIENT_DEBRIS }, ), Items.NETHERITE_SCRAP, 2.0F, 200)
/* 3434 */       .a("has_ancient_debris", a(Blocks.ANCIENT_DEBRIS))
/* 3435 */       .a(var0);
/*      */     
/* 3437 */     jj.c(RecipeItemStack.a(new IMaterial[] { Blocks.POLISHED_BLACKSTONE_BRICKS }, ), Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS.getItem(), 0.1F, 200)
/* 3438 */       .a("has_blackstone_bricks", a(Blocks.POLISHED_BLACKSTONE_BRICKS))
/* 3439 */       .a(var0);
/*      */     
/* 3441 */     jj.c(RecipeItemStack.a(new IMaterial[] { Blocks.NETHER_BRICKS }, ), Blocks.CRACKED_NETHER_BRICKS.getItem(), 0.1F, 200)
/* 3442 */       .a("has_nether_bricks", a(Blocks.NETHER_BRICKS))
/* 3443 */       .a(var0);
/*      */ 
/*      */     
/* 3446 */     jj.b(RecipeItemStack.a(new IMaterial[] { Blocks.IRON_ORE.getItem() }, ), Items.IRON_INGOT, 0.7F, 100)
/* 3447 */       .a("has_iron_ore", a(Blocks.IRON_ORE.getItem()))
/* 3448 */       .a(var0, "iron_ingot_from_blasting");
/*      */     
/* 3450 */     jj.b(RecipeItemStack.a(TagsItem.GOLD_ORES), Items.GOLD_INGOT, 1.0F, 100)
/* 3451 */       .a("has_gold_ore", a(TagsItem.GOLD_ORES))
/* 3452 */       .a(var0, "gold_ingot_from_blasting");
/*      */     
/* 3454 */     jj.b(RecipeItemStack.a(new IMaterial[] { Blocks.DIAMOND_ORE.getItem() }, ), Items.DIAMOND, 1.0F, 100)
/* 3455 */       .a("has_diamond_ore", a(Blocks.DIAMOND_ORE))
/* 3456 */       .a(var0, "diamond_from_blasting");
/*      */     
/* 3458 */     jj.b(RecipeItemStack.a(new IMaterial[] { Blocks.LAPIS_ORE.getItem() }, ), Items.LAPIS_LAZULI, 0.2F, 100)
/* 3459 */       .a("has_lapis_ore", a(Blocks.LAPIS_ORE))
/* 3460 */       .a(var0, "lapis_from_blasting");
/*      */     
/* 3462 */     jj.b(RecipeItemStack.a(new IMaterial[] { Blocks.REDSTONE_ORE }, ), Items.REDSTONE, 0.7F, 100)
/* 3463 */       .a("has_redstone_ore", a(Blocks.REDSTONE_ORE))
/* 3464 */       .a(var0, "redstone_from_blasting");
/*      */     
/* 3466 */     jj.b(RecipeItemStack.a(new IMaterial[] { Blocks.COAL_ORE.getItem() }, ), Items.COAL, 0.1F, 100)
/* 3467 */       .a("has_coal_ore", a(Blocks.COAL_ORE))
/* 3468 */       .a(var0, "coal_from_blasting");
/*      */     
/* 3470 */     jj.b(RecipeItemStack.a(new IMaterial[] { Blocks.EMERALD_ORE.getItem() }, ), Items.EMERALD, 1.0F, 100)
/* 3471 */       .a("has_emerald_ore", a(Blocks.EMERALD_ORE))
/* 3472 */       .a(var0, "emerald_from_blasting");
/*      */     
/* 3474 */     jj.b(RecipeItemStack.a(new IMaterial[] { Blocks.NETHER_QUARTZ_ORE }, ), Items.QUARTZ, 0.2F, 100)
/* 3475 */       .a("has_nether_quartz_ore", a(Blocks.NETHER_QUARTZ_ORE))
/* 3476 */       .a(var0, "quartz_from_blasting");
/*      */     
/* 3478 */     jj.b(RecipeItemStack.a(new IMaterial[] { Items.GOLDEN_PICKAXE, Items.GOLDEN_SHOVEL, Items.GOLDEN_AXE, Items.GOLDEN_HOE, Items.GOLDEN_SWORD, Items.GOLDEN_HELMET, Items.GOLDEN_CHESTPLATE, Items.GOLDEN_LEGGINGS, Items.GOLDEN_BOOTS, Items.GOLDEN_HORSE_ARMOR }, ), Items.GOLD_NUGGET, 0.1F, 100)
/* 3479 */       .a("has_golden_pickaxe", a(Items.GOLDEN_PICKAXE))
/* 3480 */       .a("has_golden_shovel", a(Items.GOLDEN_SHOVEL))
/* 3481 */       .a("has_golden_axe", a(Items.GOLDEN_AXE))
/* 3482 */       .a("has_golden_hoe", a(Items.GOLDEN_HOE))
/* 3483 */       .a("has_golden_sword", a(Items.GOLDEN_SWORD))
/* 3484 */       .a("has_golden_helmet", a(Items.GOLDEN_HELMET))
/* 3485 */       .a("has_golden_chestplate", a(Items.GOLDEN_CHESTPLATE))
/* 3486 */       .a("has_golden_leggings", a(Items.GOLDEN_LEGGINGS))
/* 3487 */       .a("has_golden_boots", a(Items.GOLDEN_BOOTS))
/* 3488 */       .a("has_golden_horse_armor", a(Items.GOLDEN_HORSE_ARMOR))
/* 3489 */       .a(var0, "gold_nugget_from_blasting");
/*      */     
/* 3491 */     jj.b(RecipeItemStack.a(new IMaterial[] { Items.IRON_PICKAXE, Items.IRON_SHOVEL, Items.IRON_AXE, Items.IRON_HOE, Items.IRON_SWORD, Items.IRON_HELMET, Items.IRON_CHESTPLATE, Items.IRON_LEGGINGS, Items.IRON_BOOTS, Items.IRON_HORSE_ARMOR, Items.CHAINMAIL_HELMET, Items.CHAINMAIL_CHESTPLATE, Items.CHAINMAIL_LEGGINGS, Items.CHAINMAIL_BOOTS }, ), Items.IRON_NUGGET, 0.1F, 100)
/* 3492 */       .a("has_iron_pickaxe", a(Items.IRON_PICKAXE))
/* 3493 */       .a("has_iron_shovel", a(Items.IRON_SHOVEL))
/* 3494 */       .a("has_iron_axe", a(Items.IRON_AXE))
/* 3495 */       .a("has_iron_hoe", a(Items.IRON_HOE))
/* 3496 */       .a("has_iron_sword", a(Items.IRON_SWORD))
/* 3497 */       .a("has_iron_helmet", a(Items.IRON_HELMET))
/* 3498 */       .a("has_iron_chestplate", a(Items.IRON_CHESTPLATE))
/* 3499 */       .a("has_iron_leggings", a(Items.IRON_LEGGINGS))
/* 3500 */       .a("has_iron_boots", a(Items.IRON_BOOTS))
/* 3501 */       .a("has_iron_horse_armor", a(Items.IRON_HORSE_ARMOR))
/* 3502 */       .a("has_chainmail_helmet", a(Items.CHAINMAIL_HELMET))
/* 3503 */       .a("has_chainmail_chestplate", a(Items.CHAINMAIL_CHESTPLATE))
/* 3504 */       .a("has_chainmail_leggings", a(Items.CHAINMAIL_LEGGINGS))
/* 3505 */       .a("has_chainmail_boots", a(Items.CHAINMAIL_BOOTS))
/* 3506 */       .a(var0, "iron_nugget_from_blasting");
/*      */     
/* 3508 */     jj.b(RecipeItemStack.a(new IMaterial[] { Blocks.ANCIENT_DEBRIS }, ), Items.NETHERITE_SCRAP, 2.0F, 100)
/* 3509 */       .a("has_ancient_debris", a(Blocks.ANCIENT_DEBRIS))
/* 3510 */       .a(var0, "netherite_scrap_from_blasting");
/*      */     
/* 3512 */     a(var0, "smoking", RecipeSerializer.r, 100);
/* 3513 */     a(var0, "campfire_cooking", RecipeSerializer.s, 600);
/*      */ 
/*      */     
/* 3516 */     jk.a(RecipeItemStack.a(new IMaterial[] { Blocks.STONE }, ), Blocks.STONE_SLAB, 2)
/* 3517 */       .a("has_stone", a(Blocks.STONE))
/* 3518 */       .a(var0, "stone_slab_from_stone_stonecutting");
/*      */     
/* 3520 */     jk.a(RecipeItemStack.a(new IMaterial[] { Blocks.STONE }, ), Blocks.STONE_STAIRS)
/* 3521 */       .a("has_stone", a(Blocks.STONE))
/* 3522 */       .a(var0, "stone_stairs_from_stone_stonecutting");
/*      */     
/* 3524 */     jk.a(RecipeItemStack.a(new IMaterial[] { Blocks.STONE }, ), Blocks.STONE_BRICKS)
/* 3525 */       .a("has_stone", a(Blocks.STONE))
/* 3526 */       .a(var0, "stone_bricks_from_stone_stonecutting");
/*      */     
/* 3528 */     jk.a(RecipeItemStack.a(new IMaterial[] { Blocks.STONE }, ), Blocks.STONE_BRICK_SLAB, 2)
/* 3529 */       .a("has_stone", a(Blocks.STONE))
/* 3530 */       .a(var0, "stone_brick_slab_from_stone_stonecutting");
/*      */     
/* 3532 */     jk.a(RecipeItemStack.a(new IMaterial[] { Blocks.STONE }, ), Blocks.STONE_BRICK_STAIRS)
/* 3533 */       .a("has_stone", a(Blocks.STONE))
/* 3534 */       .a(var0, "stone_brick_stairs_from_stone_stonecutting");
/*      */     
/* 3536 */     jk.a(RecipeItemStack.a(new IMaterial[] { Blocks.STONE }, ), Blocks.CHISELED_STONE_BRICKS)
/* 3537 */       .a("has_stone", a(Blocks.STONE))
/* 3538 */       .a(var0, "chiseled_stone_bricks_stone_from_stonecutting");
/*      */     
/* 3540 */     jk.a(RecipeItemStack.a(new IMaterial[] { Blocks.STONE }, ), Blocks.STONE_BRICK_WALL)
/* 3541 */       .a("has_stone", a(Blocks.STONE))
/* 3542 */       .a(var0, "stone_brick_walls_from_stone_stonecutting");
/*      */     
/* 3544 */     jk.a(RecipeItemStack.a(new IMaterial[] { Blocks.SANDSTONE }, ), Blocks.CUT_SANDSTONE)
/* 3545 */       .a("has_sandstone", a(Blocks.SANDSTONE))
/* 3546 */       .a(var0, "cut_sandstone_from_sandstone_stonecutting");
/*      */     
/* 3548 */     jk.a(RecipeItemStack.a(new IMaterial[] { Blocks.SANDSTONE }, ), Blocks.SANDSTONE_SLAB, 2)
/* 3549 */       .a("has_sandstone", a(Blocks.SANDSTONE))
/* 3550 */       .a(var0, "sandstone_slab_from_sandstone_stonecutting");
/*      */     
/* 3552 */     jk.a(RecipeItemStack.a(new IMaterial[] { Blocks.SANDSTONE }, ), Blocks.CUT_SANDSTONE_SLAB, 2)
/* 3553 */       .a("has_sandstone", a(Blocks.SANDSTONE))
/* 3554 */       .a(var0, "cut_sandstone_slab_from_sandstone_stonecutting");
/*      */     
/* 3556 */     jk.a(RecipeItemStack.a(new IMaterial[] { Blocks.CUT_SANDSTONE }, ), Blocks.CUT_SANDSTONE_SLAB, 2)
/* 3557 */       .a("has_cut_sandstone", a(Blocks.SANDSTONE))
/* 3558 */       .a(var0, "cut_sandstone_slab_from_cut_sandstone_stonecutting");
/*      */     
/* 3560 */     jk.a(RecipeItemStack.a(new IMaterial[] { Blocks.SANDSTONE }, ), Blocks.SANDSTONE_STAIRS)
/* 3561 */       .a("has_sandstone", a(Blocks.SANDSTONE))
/* 3562 */       .a(var0, "sandstone_stairs_from_sandstone_stonecutting");
/*      */     
/* 3564 */     jk.a(RecipeItemStack.a(new IMaterial[] { Blocks.SANDSTONE }, ), Blocks.SANDSTONE_WALL)
/* 3565 */       .a("has_sandstone", a(Blocks.SANDSTONE))
/* 3566 */       .a(var0, "sandstone_wall_from_sandstone_stonecutting");
/*      */     
/* 3568 */     jk.a(RecipeItemStack.a(new IMaterial[] { Blocks.SANDSTONE }, ), Blocks.CHISELED_SANDSTONE)
/* 3569 */       .a("has_sandstone", a(Blocks.SANDSTONE))
/* 3570 */       .a(var0, "chiseled_sandstone_from_sandstone_stonecutting");
/*      */     
/* 3572 */     jk.a(RecipeItemStack.a(new IMaterial[] { Blocks.RED_SANDSTONE }, ), Blocks.CUT_RED_SANDSTONE)
/* 3573 */       .a("has_red_sandstone", a(Blocks.RED_SANDSTONE))
/* 3574 */       .a(var0, "cut_red_sandstone_from_red_sandstone_stonecutting");
/*      */     
/* 3576 */     jk.a(RecipeItemStack.a(new IMaterial[] { Blocks.RED_SANDSTONE }, ), Blocks.RED_SANDSTONE_SLAB, 2)
/* 3577 */       .a("has_red_sandstone", a(Blocks.RED_SANDSTONE))
/* 3578 */       .a(var0, "red_sandstone_slab_from_red_sandstone_stonecutting");
/*      */     
/* 3580 */     jk.a(RecipeItemStack.a(new IMaterial[] { Blocks.RED_SANDSTONE }, ), Blocks.CUT_RED_SANDSTONE_SLAB, 2)
/* 3581 */       .a("has_red_sandstone", a(Blocks.RED_SANDSTONE))
/* 3582 */       .a(var0, "cut_red_sandstone_slab_from_red_sandstone_stonecutting");
/*      */     
/* 3584 */     jk.a(RecipeItemStack.a(new IMaterial[] { Blocks.CUT_RED_SANDSTONE }, ), Blocks.CUT_RED_SANDSTONE_SLAB, 2)
/* 3585 */       .a("has_cut_red_sandstone", a(Blocks.RED_SANDSTONE))
/* 3586 */       .a(var0, "cut_red_sandstone_slab_from_cut_red_sandstone_stonecutting");
/*      */     
/* 3588 */     jk.a(RecipeItemStack.a(new IMaterial[] { Blocks.RED_SANDSTONE }, ), Blocks.RED_SANDSTONE_STAIRS)
/* 3589 */       .a("has_red_sandstone", a(Blocks.RED_SANDSTONE))
/* 3590 */       .a(var0, "red_sandstone_stairs_from_red_sandstone_stonecutting");
/*      */     
/* 3592 */     jk.a(RecipeItemStack.a(new IMaterial[] { Blocks.RED_SANDSTONE }, ), Blocks.RED_SANDSTONE_WALL)
/* 3593 */       .a("has_red_sandstone", a(Blocks.RED_SANDSTONE))
/* 3594 */       .a(var0, "red_sandstone_wall_from_red_sandstone_stonecutting");
/*      */     
/* 3596 */     jk.a(RecipeItemStack.a(new IMaterial[] { Blocks.RED_SANDSTONE }, ), Blocks.CHISELED_RED_SANDSTONE)
/* 3597 */       .a("has_red_sandstone", a(Blocks.RED_SANDSTONE))
/* 3598 */       .a(var0, "chiseled_red_sandstone_from_red_sandstone_stonecutting");
/*      */     
/* 3600 */     jk.a(RecipeItemStack.a(new IMaterial[] { Blocks.QUARTZ_BLOCK }, ), Blocks.QUARTZ_SLAB, 2)
/* 3601 */       .a("has_quartz_block", a(Blocks.QUARTZ_BLOCK))
/* 3602 */       .a(var0, "quartz_slab_from_stonecutting");
/*      */     
/* 3604 */     jk.a(RecipeItemStack.a(new IMaterial[] { Blocks.QUARTZ_BLOCK }, ), Blocks.QUARTZ_STAIRS)
/* 3605 */       .a("has_quartz_block", a(Blocks.QUARTZ_BLOCK))
/* 3606 */       .a(var0, "quartz_stairs_from_quartz_block_stonecutting");
/*      */     
/* 3608 */     jk.a(RecipeItemStack.a(new IMaterial[] { Blocks.QUARTZ_BLOCK }, ), Blocks.QUARTZ_PILLAR)
/* 3609 */       .a("has_quartz_block", a(Blocks.QUARTZ_BLOCK))
/* 3610 */       .a(var0, "quartz_pillar_from_quartz_block_stonecutting");
/*      */     
/* 3612 */     jk.a(RecipeItemStack.a(new IMaterial[] { Blocks.QUARTZ_BLOCK }, ), Blocks.CHISELED_QUARTZ_BLOCK)
/* 3613 */       .a("has_quartz_block", a(Blocks.QUARTZ_BLOCK))
/* 3614 */       .a(var0, "chiseled_quartz_block_from_quartz_block_stonecutting");
/*      */     
/* 3616 */     jk.a(RecipeItemStack.a(new IMaterial[] { Blocks.QUARTZ_BLOCK }, ), Blocks.QUARTZ_BRICKS)
/* 3617 */       .a("has_quartz_block", a(Blocks.QUARTZ_BLOCK))
/* 3618 */       .a(var0, "quartz_bricks_from_quartz_block_stonecutting");
/*      */     
/* 3620 */     jk.a(RecipeItemStack.a(new IMaterial[] { Blocks.COBBLESTONE }, ), Blocks.COBBLESTONE_STAIRS)
/* 3621 */       .a("has_cobblestone", a(Blocks.COBBLESTONE))
/* 3622 */       .a(var0, "cobblestone_stairs_from_cobblestone_stonecutting");
/*      */     
/* 3624 */     jk.a(RecipeItemStack.a(new IMaterial[] { Blocks.COBBLESTONE }, ), Blocks.COBBLESTONE_SLAB, 2)
/* 3625 */       .a("has_cobblestone", a(Blocks.COBBLESTONE))
/* 3626 */       .a(var0, "cobblestone_slab_from_cobblestone_stonecutting");
/*      */     
/* 3628 */     jk.a(RecipeItemStack.a(new IMaterial[] { Blocks.COBBLESTONE }, ), Blocks.COBBLESTONE_WALL)
/* 3629 */       .a("has_cobblestone", a(Blocks.COBBLESTONE))
/* 3630 */       .a(var0, "cobblestone_wall_from_cobblestone_stonecutting");
/*      */     
/* 3632 */     jk.a(RecipeItemStack.a(new IMaterial[] { Blocks.STONE_BRICKS }, ), Blocks.STONE_BRICK_SLAB, 2)
/* 3633 */       .a("has_stone_bricks", a(Blocks.STONE_BRICKS))
/* 3634 */       .a(var0, "stone_brick_slab_from_stone_bricks_stonecutting");
/*      */     
/* 3636 */     jk.a(RecipeItemStack.a(new IMaterial[] { Blocks.STONE_BRICKS }, ), Blocks.STONE_BRICK_STAIRS)
/* 3637 */       .a("has_stone_bricks", a(Blocks.STONE_BRICKS))
/* 3638 */       .a(var0, "stone_brick_stairs_from_stone_bricks_stonecutting");
/*      */     
/* 3640 */     jk.a(RecipeItemStack.a(new IMaterial[] { Blocks.STONE_BRICKS }, ), Blocks.STONE_BRICK_WALL)
/* 3641 */       .a("has_stone_bricks", a(Blocks.STONE_BRICKS))
/* 3642 */       .a(var0, "stone_brick_wall_from_stone_bricks_stonecutting");
/*      */     
/* 3644 */     jk.a(RecipeItemStack.a(new IMaterial[] { Blocks.STONE_BRICKS }, ), Blocks.CHISELED_STONE_BRICKS)
/* 3645 */       .a("has_stone_bricks", a(Blocks.STONE_BRICKS))
/* 3646 */       .a(var0, "chiseled_stone_bricks_from_stone_bricks_stonecutting");
/*      */     
/* 3648 */     jk.a(RecipeItemStack.a(new IMaterial[] { Blocks.BRICKS }, ), Blocks.BRICK_SLAB, 2)
/* 3649 */       .a("has_bricks", a(Blocks.BRICKS))
/* 3650 */       .a(var0, "brick_slab_from_bricks_stonecutting");
/*      */     
/* 3652 */     jk.a(RecipeItemStack.a(new IMaterial[] { Blocks.BRICKS }, ), Blocks.BRICK_STAIRS)
/* 3653 */       .a("has_bricks", a(Blocks.BRICKS))
/* 3654 */       .a(var0, "brick_stairs_from_bricks_stonecutting");
/*      */     
/* 3656 */     jk.a(RecipeItemStack.a(new IMaterial[] { Blocks.BRICKS }, ), Blocks.BRICK_WALL)
/* 3657 */       .a("has_bricks", a(Blocks.BRICKS))
/* 3658 */       .a(var0, "brick_wall_from_bricks_stonecutting");
/*      */     
/* 3660 */     jk.a(RecipeItemStack.a(new IMaterial[] { Blocks.NETHER_BRICKS }, ), Blocks.NETHER_BRICK_SLAB, 2)
/* 3661 */       .a("has_nether_bricks", a(Blocks.NETHER_BRICKS))
/* 3662 */       .a(var0, "nether_brick_slab_from_nether_bricks_stonecutting");
/*      */     
/* 3664 */     jk.a(RecipeItemStack.a(new IMaterial[] { Blocks.NETHER_BRICKS }, ), Blocks.NETHER_BRICK_STAIRS)
/* 3665 */       .a("has_nether_bricks", a(Blocks.NETHER_BRICKS))
/* 3666 */       .a(var0, "nether_brick_stairs_from_nether_bricks_stonecutting");
/*      */     
/* 3668 */     jk.a(RecipeItemStack.a(new IMaterial[] { Blocks.NETHER_BRICKS }, ), Blocks.NETHER_BRICK_WALL)
/* 3669 */       .a("has_nether_bricks", a(Blocks.NETHER_BRICKS))
/* 3670 */       .a(var0, "nether_brick_wall_from_nether_bricks_stonecutting");
/*      */     
/* 3672 */     jk.a(RecipeItemStack.a(new IMaterial[] { Blocks.NETHER_BRICKS }, ), Blocks.CHISELED_NETHER_BRICKS)
/* 3673 */       .a("has_nether_bricks", a(Blocks.NETHER_BRICKS))
/* 3674 */       .a(var0, "chiseled_nether_bricks_from_nether_bricks_stonecutting");
/*      */     
/* 3676 */     jk.a(RecipeItemStack.a(new IMaterial[] { Blocks.RED_NETHER_BRICKS }, ), Blocks.RED_NETHER_BRICK_SLAB, 2)
/* 3677 */       .a("has_nether_bricks", a(Blocks.RED_NETHER_BRICKS))
/* 3678 */       .a(var0, "red_nether_brick_slab_from_red_nether_bricks_stonecutting");
/*      */     
/* 3680 */     jk.a(RecipeItemStack.a(new IMaterial[] { Blocks.RED_NETHER_BRICKS }, ), Blocks.RED_NETHER_BRICK_STAIRS)
/* 3681 */       .a("has_nether_bricks", a(Blocks.RED_NETHER_BRICKS))
/* 3682 */       .a(var0, "red_nether_brick_stairs_from_red_nether_bricks_stonecutting");
/*      */     
/* 3684 */     jk.a(RecipeItemStack.a(new IMaterial[] { Blocks.RED_NETHER_BRICKS }, ), Blocks.RED_NETHER_BRICK_WALL)
/* 3685 */       .a("has_nether_bricks", a(Blocks.RED_NETHER_BRICKS))
/* 3686 */       .a(var0, "red_nether_brick_wall_from_red_nether_bricks_stonecutting");
/*      */     
/* 3688 */     jk.a(RecipeItemStack.a(new IMaterial[] { Blocks.PURPUR_BLOCK }, ), Blocks.PURPUR_SLAB, 2)
/* 3689 */       .a("has_purpur_block", a(Blocks.PURPUR_BLOCK))
/* 3690 */       .a(var0, "purpur_slab_from_purpur_block_stonecutting");
/*      */     
/* 3692 */     jk.a(RecipeItemStack.a(new IMaterial[] { Blocks.PURPUR_BLOCK }, ), Blocks.PURPUR_STAIRS)
/* 3693 */       .a("has_purpur_block", a(Blocks.PURPUR_BLOCK))
/* 3694 */       .a(var0, "purpur_stairs_from_purpur_block_stonecutting");
/*      */     
/* 3696 */     jk.a(RecipeItemStack.a(new IMaterial[] { Blocks.PURPUR_BLOCK }, ), Blocks.PURPUR_PILLAR)
/* 3697 */       .a("has_purpur_block", a(Blocks.PURPUR_BLOCK))
/* 3698 */       .a(var0, "purpur_pillar_from_purpur_block_stonecutting");
/*      */     
/* 3700 */     jk.a(RecipeItemStack.a(new IMaterial[] { Blocks.PRISMARINE }, ), Blocks.PRISMARINE_SLAB, 2)
/* 3701 */       .a("has_prismarine", a(Blocks.PRISMARINE))
/* 3702 */       .a(var0, "prismarine_slab_from_prismarine_stonecutting");
/*      */     
/* 3704 */     jk.a(RecipeItemStack.a(new IMaterial[] { Blocks.PRISMARINE }, ), Blocks.PRISMARINE_STAIRS)
/* 3705 */       .a("has_prismarine", a(Blocks.PRISMARINE))
/* 3706 */       .a(var0, "prismarine_stairs_from_prismarine_stonecutting");
/*      */     
/* 3708 */     jk.a(RecipeItemStack.a(new IMaterial[] { Blocks.PRISMARINE }, ), Blocks.PRISMARINE_WALL)
/* 3709 */       .a("has_prismarine", a(Blocks.PRISMARINE))
/* 3710 */       .a(var0, "prismarine_wall_from_prismarine_stonecutting");
/*      */     
/* 3712 */     jk.a(RecipeItemStack.a(new IMaterial[] { Blocks.PRISMARINE_BRICKS }, ), Blocks.PRISMARINE_BRICK_SLAB, 2)
/* 3713 */       .a("has_prismarine_brick", a(Blocks.PRISMARINE_BRICKS))
/* 3714 */       .a(var0, "prismarine_brick_slab_from_prismarine_stonecutting");
/*      */     
/* 3716 */     jk.a(RecipeItemStack.a(new IMaterial[] { Blocks.PRISMARINE_BRICKS }, ), Blocks.PRISMARINE_BRICK_STAIRS)
/* 3717 */       .a("has_prismarine_brick", a(Blocks.PRISMARINE_BRICKS))
/* 3718 */       .a(var0, "prismarine_brick_stairs_from_prismarine_stonecutting");
/*      */     
/* 3720 */     jk.a(RecipeItemStack.a(new IMaterial[] { Blocks.DARK_PRISMARINE }, ), Blocks.DARK_PRISMARINE_SLAB, 2)
/* 3721 */       .a("has_dark_prismarine", a(Blocks.DARK_PRISMARINE))
/* 3722 */       .a(var0, "dark_prismarine_slab_from_dark_prismarine_stonecutting");
/*      */     
/* 3724 */     jk.a(RecipeItemStack.a(new IMaterial[] { Blocks.DARK_PRISMARINE }, ), Blocks.DARK_PRISMARINE_STAIRS)
/* 3725 */       .a("has_dark_prismarine", a(Blocks.DARK_PRISMARINE))
/* 3726 */       .a(var0, "dark_prismarine_stairs_from_dark_prismarine_stonecutting");
/*      */     
/* 3728 */     jk.a(RecipeItemStack.a(new IMaterial[] { Blocks.ANDESITE }, ), Blocks.ANDESITE_SLAB, 2)
/* 3729 */       .a("has_andesite", a(Blocks.ANDESITE))
/* 3730 */       .a(var0, "andesite_slab_from_andesite_stonecutting");
/*      */     
/* 3732 */     jk.a(RecipeItemStack.a(new IMaterial[] { Blocks.ANDESITE }, ), Blocks.ANDESITE_STAIRS)
/* 3733 */       .a("has_andesite", a(Blocks.ANDESITE))
/* 3734 */       .a(var0, "andesite_stairs_from_andesite_stonecutting");
/*      */     
/* 3736 */     jk.a(RecipeItemStack.a(new IMaterial[] { Blocks.ANDESITE }, ), Blocks.ANDESITE_WALL)
/* 3737 */       .a("has_andesite", a(Blocks.ANDESITE))
/* 3738 */       .a(var0, "andesite_wall_from_andesite_stonecutting");
/*      */     
/* 3740 */     jk.a(RecipeItemStack.a(new IMaterial[] { Blocks.ANDESITE }, ), Blocks.POLISHED_ANDESITE)
/* 3741 */       .a("has_andesite", a(Blocks.ANDESITE))
/* 3742 */       .a(var0, "polished_andesite_from_andesite_stonecutting");
/*      */     
/* 3744 */     jk.a(RecipeItemStack.a(new IMaterial[] { Blocks.ANDESITE }, ), Blocks.POLISHED_ANDESITE_SLAB, 2)
/* 3745 */       .a("has_andesite", a(Blocks.ANDESITE))
/* 3746 */       .a(var0, "polished_andesite_slab_from_andesite_stonecutting");
/*      */     
/* 3748 */     jk.a(RecipeItemStack.a(new IMaterial[] { Blocks.ANDESITE }, ), Blocks.POLISHED_ANDESITE_STAIRS)
/* 3749 */       .a("has_andesite", a(Blocks.ANDESITE))
/* 3750 */       .a(var0, "polished_andesite_stairs_from_andesite_stonecutting");
/*      */     
/* 3752 */     jk.a(RecipeItemStack.a(new IMaterial[] { Blocks.POLISHED_ANDESITE }, ), Blocks.POLISHED_ANDESITE_SLAB, 2)
/* 3753 */       .a("has_polished_andesite", a(Blocks.POLISHED_ANDESITE))
/* 3754 */       .a(var0, "polished_andesite_slab_from_polished_andesite_stonecutting");
/*      */     
/* 3756 */     jk.a(RecipeItemStack.a(new IMaterial[] { Blocks.POLISHED_ANDESITE }, ), Blocks.POLISHED_ANDESITE_STAIRS)
/* 3757 */       .a("has_polished_andesite", a(Blocks.POLISHED_ANDESITE))
/* 3758 */       .a(var0, "polished_andesite_stairs_from_polished_andesite_stonecutting");
/*      */     
/* 3760 */     jk.a(RecipeItemStack.a(new IMaterial[] { Blocks.BASALT }, ), Blocks.cP)
/* 3761 */       .a("has_basalt", a(Blocks.BASALT))
/* 3762 */       .a(var0, "polished_basalt_from_basalt_stonecutting");
/*      */     
/* 3764 */     jk.a(RecipeItemStack.a(new IMaterial[] { Blocks.GRANITE }, ), Blocks.GRANITE_SLAB, 2)
/* 3765 */       .a("has_granite", a(Blocks.GRANITE))
/* 3766 */       .a(var0, "granite_slab_from_granite_stonecutting");
/*      */     
/* 3768 */     jk.a(RecipeItemStack.a(new IMaterial[] { Blocks.GRANITE }, ), Blocks.GRANITE_STAIRS)
/* 3769 */       .a("has_granite", a(Blocks.GRANITE))
/* 3770 */       .a(var0, "granite_stairs_from_granite_stonecutting");
/*      */     
/* 3772 */     jk.a(RecipeItemStack.a(new IMaterial[] { Blocks.GRANITE }, ), Blocks.GRANITE_WALL)
/* 3773 */       .a("has_granite", a(Blocks.GRANITE))
/* 3774 */       .a(var0, "granite_wall_from_granite_stonecutting");
/*      */     
/* 3776 */     jk.a(RecipeItemStack.a(new IMaterial[] { Blocks.GRANITE }, ), Blocks.POLISHED_GRANITE)
/* 3777 */       .a("has_granite", a(Blocks.GRANITE))
/* 3778 */       .a(var0, "polished_granite_from_granite_stonecutting");
/*      */     
/* 3780 */     jk.a(RecipeItemStack.a(new IMaterial[] { Blocks.GRANITE }, ), Blocks.POLISHED_GRANITE_SLAB, 2)
/* 3781 */       .a("has_granite", a(Blocks.GRANITE))
/* 3782 */       .a(var0, "polished_granite_slab_from_granite_stonecutting");
/*      */     
/* 3784 */     jk.a(RecipeItemStack.a(new IMaterial[] { Blocks.GRANITE }, ), Blocks.POLISHED_GRANITE_STAIRS)
/* 3785 */       .a("has_granite", a(Blocks.GRANITE))
/* 3786 */       .a(var0, "polished_granite_stairs_from_granite_stonecutting");
/*      */     
/* 3788 */     jk.a(RecipeItemStack.a(new IMaterial[] { Blocks.POLISHED_GRANITE }, ), Blocks.POLISHED_GRANITE_SLAB, 2)
/* 3789 */       .a("has_polished_granite", a(Blocks.POLISHED_GRANITE))
/* 3790 */       .a(var0, "polished_granite_slab_from_polished_granite_stonecutting");
/*      */     
/* 3792 */     jk.a(RecipeItemStack.a(new IMaterial[] { Blocks.POLISHED_GRANITE }, ), Blocks.POLISHED_GRANITE_STAIRS)
/* 3793 */       .a("has_polished_granite", a(Blocks.POLISHED_GRANITE))
/* 3794 */       .a(var0, "polished_granite_stairs_from_polished_granite_stonecutting");
/*      */     
/* 3796 */     jk.a(RecipeItemStack.a(new IMaterial[] { Blocks.DIORITE }, ), Blocks.DIORITE_SLAB, 2)
/* 3797 */       .a("has_diorite", a(Blocks.DIORITE))
/* 3798 */       .a(var0, "diorite_slab_from_diorite_stonecutting");
/*      */     
/* 3800 */     jk.a(RecipeItemStack.a(new IMaterial[] { Blocks.DIORITE }, ), Blocks.DIORITE_STAIRS)
/* 3801 */       .a("has_diorite", a(Blocks.DIORITE))
/* 3802 */       .a(var0, "diorite_stairs_from_diorite_stonecutting");
/*      */     
/* 3804 */     jk.a(RecipeItemStack.a(new IMaterial[] { Blocks.DIORITE }, ), Blocks.DIORITE_WALL)
/* 3805 */       .a("has_diorite", a(Blocks.DIORITE))
/* 3806 */       .a(var0, "diorite_wall_from_diorite_stonecutting");
/*      */     
/* 3808 */     jk.a(RecipeItemStack.a(new IMaterial[] { Blocks.DIORITE }, ), Blocks.POLISHED_DIORITE)
/* 3809 */       .a("has_diorite", a(Blocks.DIORITE))
/* 3810 */       .a(var0, "polished_diorite_from_diorite_stonecutting");
/*      */     
/* 3812 */     jk.a(RecipeItemStack.a(new IMaterial[] { Blocks.DIORITE }, ), Blocks.POLISHED_DIORITE_SLAB, 2)
/* 3813 */       .a("has_diorite", a(Blocks.POLISHED_DIORITE))
/* 3814 */       .a(var0, "polished_diorite_slab_from_diorite_stonecutting");
/*      */     
/* 3816 */     jk.a(RecipeItemStack.a(new IMaterial[] { Blocks.DIORITE }, ), Blocks.POLISHED_DIORITE_STAIRS)
/* 3817 */       .a("has_diorite", a(Blocks.POLISHED_DIORITE))
/* 3818 */       .a(var0, "polished_diorite_stairs_from_diorite_stonecutting");
/*      */     
/* 3820 */     jk.a(RecipeItemStack.a(new IMaterial[] { Blocks.POLISHED_DIORITE }, ), Blocks.POLISHED_DIORITE_SLAB, 2)
/* 3821 */       .a("has_polished_diorite", a(Blocks.POLISHED_DIORITE))
/* 3822 */       .a(var0, "polished_diorite_slab_from_polished_diorite_stonecutting");
/*      */     
/* 3824 */     jk.a(RecipeItemStack.a(new IMaterial[] { Blocks.POLISHED_DIORITE }, ), Blocks.POLISHED_DIORITE_STAIRS)
/* 3825 */       .a("has_polished_diorite", a(Blocks.POLISHED_DIORITE))
/* 3826 */       .a(var0, "polished_diorite_stairs_from_polished_diorite_stonecutting");
/*      */     
/* 3828 */     jk.a(RecipeItemStack.a(new IMaterial[] { Blocks.MOSSY_STONE_BRICKS }, ), Blocks.MOSSY_STONE_BRICK_SLAB, 2)
/* 3829 */       .a("has_mossy_stone_bricks", a(Blocks.MOSSY_STONE_BRICKS))
/* 3830 */       .a(var0, "mossy_stone_brick_slab_from_mossy_stone_brick_stonecutting");
/*      */     
/* 3832 */     jk.a(RecipeItemStack.a(new IMaterial[] { Blocks.MOSSY_STONE_BRICKS }, ), Blocks.MOSSY_STONE_BRICK_STAIRS)
/* 3833 */       .a("has_mossy_stone_bricks", a(Blocks.MOSSY_STONE_BRICKS))
/* 3834 */       .a(var0, "mossy_stone_brick_stairs_from_mossy_stone_brick_stonecutting");
/*      */     
/* 3836 */     jk.a(RecipeItemStack.a(new IMaterial[] { Blocks.MOSSY_STONE_BRICKS }, ), Blocks.MOSSY_STONE_BRICK_WALL)
/* 3837 */       .a("has_mossy_stone_bricks", a(Blocks.MOSSY_STONE_BRICKS))
/* 3838 */       .a(var0, "mossy_stone_brick_wall_from_mossy_stone_brick_stonecutting");
/*      */     
/* 3840 */     jk.a(RecipeItemStack.a(new IMaterial[] { Blocks.MOSSY_COBBLESTONE }, ), Blocks.MOSSY_COBBLESTONE_SLAB, 2)
/* 3841 */       .a("has_mossy_cobblestone", a(Blocks.MOSSY_COBBLESTONE))
/* 3842 */       .a(var0, "mossy_cobblestone_slab_from_mossy_cobblestone_stonecutting");
/*      */     
/* 3844 */     jk.a(RecipeItemStack.a(new IMaterial[] { Blocks.MOSSY_COBBLESTONE }, ), Blocks.MOSSY_COBBLESTONE_STAIRS)
/* 3845 */       .a("has_mossy_cobblestone", a(Blocks.MOSSY_COBBLESTONE))
/* 3846 */       .a(var0, "mossy_cobblestone_stairs_from_mossy_cobblestone_stonecutting");
/*      */     
/* 3848 */     jk.a(RecipeItemStack.a(new IMaterial[] { Blocks.MOSSY_COBBLESTONE }, ), Blocks.MOSSY_COBBLESTONE_WALL)
/* 3849 */       .a("has_mossy_cobblestone", a(Blocks.MOSSY_COBBLESTONE))
/* 3850 */       .a(var0, "mossy_cobblestone_wall_from_mossy_cobblestone_stonecutting");
/*      */     
/* 3852 */     jk.a(RecipeItemStack.a(new IMaterial[] { Blocks.SMOOTH_SANDSTONE }, ), Blocks.SMOOTH_SANDSTONE_SLAB, 2)
/* 3853 */       .a("has_smooth_sandstone", a(Blocks.SMOOTH_SANDSTONE))
/* 3854 */       .a(var0, "smooth_sandstone_slab_from_smooth_sandstone_stonecutting");
/*      */     
/* 3856 */     jk.a(RecipeItemStack.a(new IMaterial[] { Blocks.SMOOTH_SANDSTONE }, ), Blocks.SMOOTH_SANDSTONE_STAIRS)
/* 3857 */       .a("has_mossy_cobblestone", a(Blocks.SMOOTH_SANDSTONE))
/* 3858 */       .a(var0, "smooth_sandstone_stairs_from_smooth_sandstone_stonecutting");
/*      */     
/* 3860 */     jk.a(RecipeItemStack.a(new IMaterial[] { Blocks.SMOOTH_RED_SANDSTONE }, ), Blocks.SMOOTH_RED_SANDSTONE_SLAB, 2)
/* 3861 */       .a("has_smooth_red_sandstone", a(Blocks.SMOOTH_RED_SANDSTONE))
/* 3862 */       .a(var0, "smooth_red_sandstone_slab_from_smooth_red_sandstone_stonecutting");
/*      */     
/* 3864 */     jk.a(RecipeItemStack.a(new IMaterial[] { Blocks.SMOOTH_RED_SANDSTONE }, ), Blocks.SMOOTH_RED_SANDSTONE_STAIRS)
/* 3865 */       .a("has_smooth_red_sandstone", a(Blocks.SMOOTH_RED_SANDSTONE))
/* 3866 */       .a(var0, "smooth_red_sandstone_stairs_from_smooth_red_sandstone_stonecutting");
/*      */     
/* 3868 */     jk.a(RecipeItemStack.a(new IMaterial[] { Blocks.SMOOTH_QUARTZ }, ), Blocks.SMOOTH_QUARTZ_SLAB, 2)
/* 3869 */       .a("has_smooth_quartz", a(Blocks.SMOOTH_QUARTZ))
/* 3870 */       .a(var0, "smooth_quartz_slab_from_smooth_quartz_stonecutting");
/*      */     
/* 3872 */     jk.a(RecipeItemStack.a(new IMaterial[] { Blocks.SMOOTH_QUARTZ }, ), Blocks.SMOOTH_QUARTZ_STAIRS)
/* 3873 */       .a("has_smooth_quartz", a(Blocks.SMOOTH_QUARTZ))
/* 3874 */       .a(var0, "smooth_quartz_stairs_from_smooth_quartz_stonecutting");
/*      */     
/* 3876 */     jk.a(RecipeItemStack.a(new IMaterial[] { Blocks.END_STONE_BRICKS }, ), Blocks.END_STONE_BRICK_SLAB, 2)
/* 3877 */       .a("has_end_stone_brick", a(Blocks.END_STONE_BRICKS))
/* 3878 */       .a(var0, "end_stone_brick_slab_from_end_stone_brick_stonecutting");
/*      */     
/* 3880 */     jk.a(RecipeItemStack.a(new IMaterial[] { Blocks.END_STONE_BRICKS }, ), Blocks.END_STONE_BRICK_STAIRS)
/* 3881 */       .a("has_end_stone_brick", a(Blocks.END_STONE_BRICKS))
/* 3882 */       .a(var0, "end_stone_brick_stairs_from_end_stone_brick_stonecutting");
/*      */     
/* 3884 */     jk.a(RecipeItemStack.a(new IMaterial[] { Blocks.END_STONE_BRICKS }, ), Blocks.END_STONE_BRICK_WALL)
/* 3885 */       .a("has_end_stone_brick", a(Blocks.END_STONE_BRICKS))
/* 3886 */       .a(var0, "end_stone_brick_wall_from_end_stone_brick_stonecutting");
/*      */     
/* 3888 */     jk.a(RecipeItemStack.a(new IMaterial[] { Blocks.END_STONE }, ), Blocks.END_STONE_BRICKS)
/* 3889 */       .a("has_end_stone", a(Blocks.END_STONE))
/* 3890 */       .a(var0, "end_stone_bricks_from_end_stone_stonecutting");
/*      */     
/* 3892 */     jk.a(RecipeItemStack.a(new IMaterial[] { Blocks.END_STONE }, ), Blocks.END_STONE_BRICK_SLAB, 2)
/* 3893 */       .a("has_end_stone", a(Blocks.END_STONE))
/* 3894 */       .a(var0, "end_stone_brick_slab_from_end_stone_stonecutting");
/*      */     
/* 3896 */     jk.a(RecipeItemStack.a(new IMaterial[] { Blocks.END_STONE }, ), Blocks.END_STONE_BRICK_STAIRS)
/* 3897 */       .a("has_end_stone", a(Blocks.END_STONE))
/* 3898 */       .a(var0, "end_stone_brick_stairs_from_end_stone_stonecutting");
/*      */     
/* 3900 */     jk.a(RecipeItemStack.a(new IMaterial[] { Blocks.END_STONE }, ), Blocks.END_STONE_BRICK_WALL)
/* 3901 */       .a("has_end_stone", a(Blocks.END_STONE))
/* 3902 */       .a(var0, "end_stone_brick_wall_from_end_stone_stonecutting");
/*      */     
/* 3904 */     jk.a(RecipeItemStack.a(new IMaterial[] { Blocks.SMOOTH_STONE }, ), Blocks.SMOOTH_STONE_SLAB, 2)
/* 3905 */       .a("has_smooth_stone", a(Blocks.SMOOTH_STONE))
/* 3906 */       .a(var0, "smooth_stone_slab_from_smooth_stone_stonecutting");
/*      */     
/* 3908 */     jk.a(RecipeItemStack.a(new IMaterial[] { Blocks.BLACKSTONE }, ), Blocks.BLACKSTONE_SLAB, 2)
/* 3909 */       .a("has_blackstone", a(Blocks.BLACKSTONE))
/* 3910 */       .a(var0, "blackstone_slab_from_blackstone_stonecutting");
/*      */     
/* 3912 */     jk.a(RecipeItemStack.a(new IMaterial[] { Blocks.BLACKSTONE }, ), Blocks.BLACKSTONE_STAIRS)
/* 3913 */       .a("has_blackstone", a(Blocks.BLACKSTONE))
/* 3914 */       .a(var0, "blackstone_stairs_from_blackstone_stonecutting");
/*      */     
/* 3916 */     jk.a(RecipeItemStack.a(new IMaterial[] { Blocks.BLACKSTONE }, ), Blocks.BLACKSTONE_WALL)
/* 3917 */       .a("has_blackstone", a(Blocks.BLACKSTONE))
/* 3918 */       .a(var0, "blackstone_wall_from_blackstone_stonecutting");
/*      */     
/* 3920 */     jk.a(RecipeItemStack.a(new IMaterial[] { Blocks.BLACKSTONE }, ), Blocks.POLISHED_BLACKSTONE)
/* 3921 */       .a("has_blackstone", a(Blocks.BLACKSTONE))
/* 3922 */       .a(var0, "polished_blackstone_from_blackstone_stonecutting");
/*      */     
/* 3924 */     jk.a(RecipeItemStack.a(new IMaterial[] { Blocks.BLACKSTONE }, ), Blocks.POLISHED_BLACKSTONE_WALL)
/* 3925 */       .a("has_blackstone", a(Blocks.BLACKSTONE))
/* 3926 */       .a(var0, "polished_blackstone_wall_from_blackstone_stonecutting");
/*      */     
/* 3928 */     jk.a(RecipeItemStack.a(new IMaterial[] { Blocks.BLACKSTONE }, ), Blocks.POLISHED_BLACKSTONE_SLAB, 2)
/* 3929 */       .a("has_blackstone", a(Blocks.BLACKSTONE))
/* 3930 */       .a(var0, "polished_blackstone_slab_from_blackstone_stonecutting");
/*      */     
/* 3932 */     jk.a(RecipeItemStack.a(new IMaterial[] { Blocks.BLACKSTONE }, ), Blocks.POLISHED_BLACKSTONE_STAIRS)
/* 3933 */       .a("has_blackstone", a(Blocks.BLACKSTONE))
/* 3934 */       .a(var0, "polished_blackstone_stairs_from_blackstone_stonecutting");
/*      */     
/* 3936 */     jk.a(RecipeItemStack.a(new IMaterial[] { Blocks.BLACKSTONE }, ), Blocks.CHISELED_POLISHED_BLACKSTONE)
/* 3937 */       .a("has_blackstone", a(Blocks.BLACKSTONE))
/* 3938 */       .a(var0, "chiseled_polished_blackstone_from_blackstone_stonecutting");
/*      */     
/* 3940 */     jk.a(RecipeItemStack.a(new IMaterial[] { Blocks.BLACKSTONE }, ), Blocks.POLISHED_BLACKSTONE_BRICKS)
/* 3941 */       .a("has_blackstone", a(Blocks.BLACKSTONE))
/* 3942 */       .a(var0, "polished_blackstone_bricks_from_blackstone_stonecutting");
/*      */     
/* 3944 */     jk.a(RecipeItemStack.a(new IMaterial[] { Blocks.BLACKSTONE }, ), Blocks.POLISHED_BLACKSTONE_BRICK_SLAB, 2)
/* 3945 */       .a("has_blackstone", a(Blocks.BLACKSTONE))
/* 3946 */       .a(var0, "polished_blackstone_brick_slab_from_blackstone_stonecutting");
/*      */     
/* 3948 */     jk.a(RecipeItemStack.a(new IMaterial[] { Blocks.BLACKSTONE }, ), Blocks.POLISHED_BLACKSTONE_BRICK_STAIRS)
/* 3949 */       .a("has_blackstone", a(Blocks.BLACKSTONE))
/* 3950 */       .a(var0, "polished_blackstone_brick_stairs_from_blackstone_stonecutting");
/*      */     
/* 3952 */     jk.a(RecipeItemStack.a(new IMaterial[] { Blocks.BLACKSTONE }, ), Blocks.POLISHED_BLACKSTONE_BRICK_WALL)
/* 3953 */       .a("has_blackstone", a(Blocks.BLACKSTONE))
/* 3954 */       .a(var0, "polished_blackstone_brick_wall_from_blackstone_stonecutting");
/*      */     
/* 3956 */     jk.a(RecipeItemStack.a(new IMaterial[] { Blocks.POLISHED_BLACKSTONE }, ), Blocks.POLISHED_BLACKSTONE_SLAB, 2)
/* 3957 */       .a("has_polished_blackstone", a(Blocks.POLISHED_BLACKSTONE))
/* 3958 */       .a(var0, "polished_blackstone_slab_from_polished_blackstone_stonecutting");
/*      */     
/* 3960 */     jk.a(RecipeItemStack.a(new IMaterial[] { Blocks.POLISHED_BLACKSTONE }, ), Blocks.POLISHED_BLACKSTONE_STAIRS)
/* 3961 */       .a("has_polished_blackstone", a(Blocks.POLISHED_BLACKSTONE))
/* 3962 */       .a(var0, "polished_blackstone_stairs_from_polished_blackstone_stonecutting");
/*      */     
/* 3964 */     jk.a(RecipeItemStack.a(new IMaterial[] { Blocks.POLISHED_BLACKSTONE }, ), Blocks.POLISHED_BLACKSTONE_BRICKS)
/* 3965 */       .a("has_polished_blackstone", a(Blocks.POLISHED_BLACKSTONE))
/* 3966 */       .a(var0, "polished_blackstone_bricks_from_polished_blackstone_stonecutting");
/*      */     
/* 3968 */     jk.a(RecipeItemStack.a(new IMaterial[] { Blocks.POLISHED_BLACKSTONE }, ), Blocks.POLISHED_BLACKSTONE_WALL)
/* 3969 */       .a("has_polished_blackstone", a(Blocks.POLISHED_BLACKSTONE))
/* 3970 */       .a(var0, "polished_blackstone_wall_from_polished_blackstone_stonecutting");
/*      */     
/* 3972 */     jk.a(RecipeItemStack.a(new IMaterial[] { Blocks.POLISHED_BLACKSTONE }, ), Blocks.POLISHED_BLACKSTONE_BRICK_SLAB, 2)
/* 3973 */       .a("has_polished_blackstone", a(Blocks.POLISHED_BLACKSTONE))
/* 3974 */       .a(var0, "polished_blackstone_brick_slab_from_polished_blackstone_stonecutting");
/*      */     
/* 3976 */     jk.a(RecipeItemStack.a(new IMaterial[] { Blocks.POLISHED_BLACKSTONE }, ), Blocks.POLISHED_BLACKSTONE_BRICK_STAIRS)
/* 3977 */       .a("has_polished_blackstone", a(Blocks.POLISHED_BLACKSTONE))
/* 3978 */       .a(var0, "polished_blackstone_brick_stairs_from_polished_blackstone_stonecutting");
/*      */     
/* 3980 */     jk.a(RecipeItemStack.a(new IMaterial[] { Blocks.POLISHED_BLACKSTONE }, ), Blocks.POLISHED_BLACKSTONE_BRICK_WALL)
/* 3981 */       .a("has_polished_blackstone", a(Blocks.POLISHED_BLACKSTONE))
/* 3982 */       .a(var0, "polished_blackstone_brick_wall_from_polished_blackstone_stonecutting");
/*      */     
/* 3984 */     jk.a(RecipeItemStack.a(new IMaterial[] { Blocks.POLISHED_BLACKSTONE }, ), Blocks.CHISELED_POLISHED_BLACKSTONE)
/* 3985 */       .a("has_polished_blackstone", a(Blocks.POLISHED_BLACKSTONE))
/* 3986 */       .a(var0, "chiseled_polished_blackstone_from_polished_blackstone_stonecutting");
/*      */     
/* 3988 */     jk.a(RecipeItemStack.a(new IMaterial[] { Blocks.POLISHED_BLACKSTONE_BRICKS }, ), Blocks.POLISHED_BLACKSTONE_BRICK_SLAB, 2)
/* 3989 */       .a("has_polished_blackstone_bricks", a(Blocks.POLISHED_BLACKSTONE_BRICKS))
/* 3990 */       .a(var0, "polished_blackstone_brick_slab_from_polished_blackstone_bricks_stonecutting");
/*      */     
/* 3992 */     jk.a(RecipeItemStack.a(new IMaterial[] { Blocks.POLISHED_BLACKSTONE_BRICKS }, ), Blocks.POLISHED_BLACKSTONE_BRICK_STAIRS)
/* 3993 */       .a("has_polished_blackstone_bricks", a(Blocks.POLISHED_BLACKSTONE_BRICKS))
/* 3994 */       .a(var0, "polished_blackstone_brick_stairs_from_polished_blackstone_bricks_stonecutting");
/*      */     
/* 3996 */     jk.a(RecipeItemStack.a(new IMaterial[] { Blocks.POLISHED_BLACKSTONE_BRICKS }, ), Blocks.POLISHED_BLACKSTONE_BRICK_WALL)
/* 3997 */       .a("has_polished_blackstone_bricks", a(Blocks.POLISHED_BLACKSTONE_BRICKS))
/* 3998 */       .a(var0, "polished_blackstone_brick_wall_from_polished_blackstone_bricks_stonecutting");
/*      */ 
/*      */     
/* 4001 */     a(var0, Items.DIAMOND_CHESTPLATE, Items.NETHERITE_CHESTPLATE);
/* 4002 */     a(var0, Items.DIAMOND_LEGGINGS, Items.NETHERITE_LEGGINGS);
/* 4003 */     a(var0, Items.DIAMOND_HELMET, Items.NETHERITE_HELMET);
/* 4004 */     a(var0, Items.DIAMOND_BOOTS, Items.NETHERITE_BOOTS);
/* 4005 */     a(var0, Items.DIAMOND_SWORD, Items.NETHERITE_SWORD);
/* 4006 */     a(var0, Items.DIAMOND_AXE, Items.NETHERITE_AXE);
/* 4007 */     a(var0, Items.DIAMOND_PICKAXE, Items.NETHERITE_PICKAXE);
/* 4008 */     a(var0, Items.DIAMOND_HOE, Items.NETHERITE_HOE);
/* 4009 */     a(var0, Items.DIAMOND_SHOVEL, Items.NETHERITE_SHOVEL);
/*      */   }
/*      */   
/*      */   private static void a(Consumer<jf> var0, Item var1, Item var2) {
/* 4013 */     jm.a(RecipeItemStack.a(new IMaterial[] { var1 }, ), RecipeItemStack.a(new IMaterial[] { Items.NETHERITE_INGOT }, ), var2)
/* 4014 */       .a("has_netherite_ingot", a(Items.NETHERITE_INGOT))
/* 4015 */       .a(var0, IRegistry.ITEM.getKey(var2.getItem()).getKey() + "_smithing");
/*      */   }
/*      */ 
/*      */   
/*      */   private static void a(Consumer<jf> var0, IMaterial var1, Tag<Item> var2) {
/* 4020 */     ji.a(var1, 4)
/* 4021 */       .a(var2)
/* 4022 */       .a("planks")
/* 4023 */       .a("has_log", a(var2))
/* 4024 */       .a(var0);
/*      */   }
/*      */   
/*      */   private static void b(Consumer<jf> var0, IMaterial var1, Tag<Item> var2) {
/* 4028 */     ji.a(var1, 4)
/* 4029 */       .a(var2)
/* 4030 */       .a("planks")
/* 4031 */       .a("has_logs", a(var2))
/* 4032 */       .a(var0);
/*      */   }
/*      */   
/*      */   private static void a(Consumer<jf> var0, IMaterial var1, IMaterial var2) {
/* 4036 */     jh.a(var1, 3)
/* 4037 */       .a(Character.valueOf('#'), var2)
/* 4038 */       .a("##")
/* 4039 */       .a("##")
/* 4040 */       .b("bark")
/* 4041 */       .a("has_log", a(var2))
/* 4042 */       .a(var0);
/*      */   }
/*      */   
/*      */   private static void b(Consumer<jf> var0, IMaterial var1, IMaterial var2) {
/* 4046 */     jh.a(var1)
/* 4047 */       .a(Character.valueOf('#'), var2)
/* 4048 */       .a("# #")
/* 4049 */       .a("###")
/* 4050 */       .b("boat")
/* 4051 */       .a("in_water", a(Blocks.WATER))
/* 4052 */       .a(var0);
/*      */   }
/*      */   
/*      */   private static void c(Consumer<jf> var0, IMaterial var1, IMaterial var2) {
/* 4056 */     ji.a(var1)
/* 4057 */       .b(var2)
/* 4058 */       .a("wooden_button")
/* 4059 */       .a("has_planks", a(var2))
/* 4060 */       .a(var0);
/*      */   }
/*      */   
/*      */   private static void d(Consumer<jf> var0, IMaterial var1, IMaterial var2) {
/* 4064 */     jh.a(var1, 3)
/* 4065 */       .a(Character.valueOf('#'), var2)
/* 4066 */       .a("##")
/* 4067 */       .a("##")
/* 4068 */       .a("##")
/* 4069 */       .b("wooden_door")
/* 4070 */       .a("has_planks", a(var2))
/* 4071 */       .a(var0);
/*      */   }
/*      */   
/*      */   private static void e(Consumer<jf> var0, IMaterial var1, IMaterial var2) {
/* 4075 */     jh.a(var1, 3)
/* 4076 */       .a(Character.valueOf('#'), Items.STICK)
/* 4077 */       .a(Character.valueOf('W'), var2)
/* 4078 */       .a("W#W")
/* 4079 */       .a("W#W")
/* 4080 */       .b("wooden_fence")
/* 4081 */       .a("has_planks", a(var2))
/* 4082 */       .a(var0);
/*      */   }
/*      */   
/*      */   private static void f(Consumer<jf> var0, IMaterial var1, IMaterial var2) {
/* 4086 */     jh.a(var1)
/* 4087 */       .a(Character.valueOf('#'), Items.STICK)
/* 4088 */       .a(Character.valueOf('W'), var2)
/* 4089 */       .a("#W#")
/* 4090 */       .a("#W#")
/* 4091 */       .b("wooden_fence_gate")
/* 4092 */       .a("has_planks", a(var2))
/* 4093 */       .a(var0);
/*      */   }
/*      */   
/*      */   private static void g(Consumer<jf> var0, IMaterial var1, IMaterial var2) {
/* 4097 */     jh.a(var1)
/* 4098 */       .a(Character.valueOf('#'), var2)
/* 4099 */       .a("##")
/* 4100 */       .b("wooden_pressure_plate")
/* 4101 */       .a("has_planks", a(var2))
/* 4102 */       .a(var0);
/*      */   }
/*      */   
/*      */   private static void h(Consumer<jf> var0, IMaterial var1, IMaterial var2) {
/* 4106 */     jh.a(var1, 6)
/* 4107 */       .a(Character.valueOf('#'), var2)
/* 4108 */       .a("###")
/* 4109 */       .b("wooden_slab")
/* 4110 */       .a("has_planks", a(var2))
/* 4111 */       .a(var0);
/*      */   }
/*      */   
/*      */   private static void i(Consumer<jf> var0, IMaterial var1, IMaterial var2) {
/* 4115 */     jh.a(var1, 4)
/* 4116 */       .a(Character.valueOf('#'), var2)
/* 4117 */       .a("#  ")
/* 4118 */       .a("## ")
/* 4119 */       .a("###")
/* 4120 */       .b("wooden_stairs")
/* 4121 */       .a("has_planks", a(var2))
/* 4122 */       .a(var0);
/*      */   }
/*      */   
/*      */   private static void j(Consumer<jf> var0, IMaterial var1, IMaterial var2) {
/* 4126 */     jh.a(var1, 2)
/* 4127 */       .a(Character.valueOf('#'), var2)
/* 4128 */       .a("###")
/* 4129 */       .a("###")
/* 4130 */       .b("wooden_trapdoor")
/* 4131 */       .a("has_planks", a(var2))
/* 4132 */       .a(var0);
/*      */   }
/*      */   
/*      */   private static void k(Consumer<jf> var0, IMaterial var1, IMaterial var2) {
/* 4136 */     String var3 = IRegistry.ITEM.getKey(var2.getItem()).getKey();
/* 4137 */     jh.a(var1, 3)
/* 4138 */       .b("sign")
/* 4139 */       .a(Character.valueOf('#'), var2)
/* 4140 */       .a(Character.valueOf('X'), Items.STICK)
/* 4141 */       .a("###")
/* 4142 */       .a("###")
/* 4143 */       .a(" X ")
/* 4144 */       .a("has_" + var3, a(var2))
/* 4145 */       .a(var0);
/*      */   }
/*      */   
/*      */   private static void l(Consumer<jf> var0, IMaterial var1, IMaterial var2) {
/* 4149 */     ji.a(var1)
/* 4150 */       .b(var2)
/* 4151 */       .b(Blocks.WHITE_WOOL)
/* 4152 */       .a("wool")
/* 4153 */       .a("has_white_wool", a(Blocks.WHITE_WOOL))
/* 4154 */       .a(var0);
/*      */   }
/*      */   
/*      */   private static void m(Consumer<jf> var0, IMaterial var1, IMaterial var2) {
/* 4158 */     String var3 = IRegistry.ITEM.getKey(var2.getItem()).getKey();
/* 4159 */     jh.a(var1, 3)
/* 4160 */       .a(Character.valueOf('#'), var2)
/* 4161 */       .a("##")
/* 4162 */       .b("carpet")
/* 4163 */       .a("has_" + var3, a(var2))
/* 4164 */       .a(var0);
/*      */   }
/*      */   
/*      */   private static void n(Consumer<jf> var0, IMaterial var1, IMaterial var2) {
/* 4168 */     String var3 = IRegistry.ITEM.getKey(var1.getItem()).getKey();
/* 4169 */     String var4 = IRegistry.ITEM.getKey(var2.getItem()).getKey();
/* 4170 */     jh.a(var1, 8)
/* 4171 */       .a(Character.valueOf('#'), Blocks.WHITE_CARPET)
/* 4172 */       .a(Character.valueOf('$'), var2)
/* 4173 */       .a("###")
/* 4174 */       .a("#$#")
/* 4175 */       .a("###")
/* 4176 */       .b("carpet")
/* 4177 */       .a("has_white_carpet", a(Blocks.WHITE_CARPET))
/* 4178 */       .a("has_" + var4, a(var2))
/* 4179 */       .a(var0, var3 + "_from_white_carpet");
/*      */   }
/*      */   
/*      */   private static void o(Consumer<jf> var0, IMaterial var1, IMaterial var2) {
/* 4183 */     String var3 = IRegistry.ITEM.getKey(var2.getItem()).getKey();
/* 4184 */     jh.a(var1)
/* 4185 */       .a(Character.valueOf('#'), var2)
/* 4186 */       .a(Character.valueOf('X'), TagsItem.PLANKS)
/* 4187 */       .a("###")
/* 4188 */       .a("XXX")
/* 4189 */       .b("bed")
/* 4190 */       .a("has_" + var3, a(var2))
/* 4191 */       .a(var0);
/*      */   }
/*      */   
/*      */   private static void p(Consumer<jf> var0, IMaterial var1, IMaterial var2) {
/* 4195 */     String var3 = IRegistry.ITEM.getKey(var1.getItem()).getKey();
/* 4196 */     ji.a(var1)
/* 4197 */       .b(Items.WHITE_BED)
/* 4198 */       .b(var2)
/* 4199 */       .a("dyed_bed")
/* 4200 */       .a("has_bed", a(Items.WHITE_BED))
/* 4201 */       .a(var0, var3 + "_from_white_bed");
/*      */   }
/*      */   
/*      */   private static void q(Consumer<jf> var0, IMaterial var1, IMaterial var2) {
/* 4205 */     String var3 = IRegistry.ITEM.getKey(var2.getItem()).getKey();
/* 4206 */     jh.a(var1)
/* 4207 */       .a(Character.valueOf('#'), var2)
/* 4208 */       .a(Character.valueOf('|'), Items.STICK)
/* 4209 */       .a("###")
/* 4210 */       .a("###")
/* 4211 */       .a(" | ")
/* 4212 */       .b("banner")
/* 4213 */       .a("has_" + var3, a(var2))
/* 4214 */       .a(var0);
/*      */   }
/*      */   
/*      */   private static void r(Consumer<jf> var0, IMaterial var1, IMaterial var2) {
/* 4218 */     jh.a(var1, 8)
/* 4219 */       .a(Character.valueOf('#'), Blocks.GLASS)
/* 4220 */       .a(Character.valueOf('X'), var2)
/* 4221 */       .a("###")
/* 4222 */       .a("#X#")
/* 4223 */       .a("###")
/* 4224 */       .b("stained_glass")
/* 4225 */       .a("has_glass", a(Blocks.GLASS))
/* 4226 */       .a(var0);
/*      */   }
/*      */   
/*      */   private static void s(Consumer<jf> var0, IMaterial var1, IMaterial var2) {
/* 4230 */     jh.a(var1, 16)
/* 4231 */       .a(Character.valueOf('#'), var2)
/* 4232 */       .a("###")
/* 4233 */       .a("###")
/* 4234 */       .b("stained_glass_pane")
/* 4235 */       .a("has_glass", a(var2))
/* 4236 */       .a(var0);
/*      */   }
/*      */   
/*      */   private static void t(Consumer<jf> var0, IMaterial var1, IMaterial var2) {
/* 4240 */     String var3 = IRegistry.ITEM.getKey(var1.getItem()).getKey();
/* 4241 */     String var4 = IRegistry.ITEM.getKey(var2.getItem()).getKey();
/* 4242 */     jh.a(var1, 8)
/* 4243 */       .a(Character.valueOf('#'), Blocks.GLASS_PANE)
/* 4244 */       .a(Character.valueOf('$'), var2)
/* 4245 */       .a("###")
/* 4246 */       .a("#$#")
/* 4247 */       .a("###")
/* 4248 */       .b("stained_glass_pane")
/* 4249 */       .a("has_glass_pane", a(Blocks.GLASS_PANE))
/* 4250 */       .a("has_" + var4, a(var2))
/* 4251 */       .a(var0, var3 + "_from_glass_pane");
/*      */   }
/*      */   
/*      */   private static void u(Consumer<jf> var0, IMaterial var1, IMaterial var2) {
/* 4255 */     jh.a(var1, 8)
/* 4256 */       .a(Character.valueOf('#'), Blocks.TERRACOTTA)
/* 4257 */       .a(Character.valueOf('X'), var2)
/* 4258 */       .a("###")
/* 4259 */       .a("#X#")
/* 4260 */       .a("###")
/* 4261 */       .b("stained_terracotta")
/* 4262 */       .a("has_terracotta", a(Blocks.TERRACOTTA))
/* 4263 */       .a(var0);
/*      */   }
/*      */   
/*      */   private static void v(Consumer<jf> var0, IMaterial var1, IMaterial var2) {
/* 4267 */     ji.a(var1, 8)
/* 4268 */       .b(var2)
/* 4269 */       .b(Blocks.SAND, 4)
/* 4270 */       .b(Blocks.GRAVEL, 4)
/* 4271 */       .a("concrete_powder")
/* 4272 */       .a("has_sand", a(Blocks.SAND))
/* 4273 */       .a("has_gravel", a(Blocks.GRAVEL))
/* 4274 */       .a(var0);
/*      */   }
/*      */   
/*      */   private static void a(Consumer<jf> var0, String var1, RecipeSerializerCooking<?> var2, int var3) {
/* 4278 */     jj.a(RecipeItemStack.a(new IMaterial[] { Items.BEEF }, ), Items.COOKED_BEEF, 0.35F, var3, var2)
/* 4279 */       .a("has_beef", a(Items.BEEF))
/* 4280 */       .a(var0, "cooked_beef_from_" + var1);
/*      */     
/* 4282 */     jj.a(RecipeItemStack.a(new IMaterial[] { Items.CHICKEN }, ), Items.COOKED_CHICKEN, 0.35F, var3, var2)
/* 4283 */       .a("has_chicken", a(Items.CHICKEN))
/* 4284 */       .a(var0, "cooked_chicken_from_" + var1);
/*      */     
/* 4286 */     jj.a(RecipeItemStack.a(new IMaterial[] { Items.COD }, ), Items.COOKED_COD, 0.35F, var3, var2)
/* 4287 */       .a("has_cod", a(Items.COD))
/* 4288 */       .a(var0, "cooked_cod_from_" + var1);
/*      */     
/* 4290 */     jj.a(RecipeItemStack.a(new IMaterial[] { Blocks.KELP }, ), Items.DRIED_KELP, 0.1F, var3, var2)
/* 4291 */       .a("has_kelp", a(Blocks.KELP))
/* 4292 */       .a(var0, "dried_kelp_from_" + var1);
/*      */     
/* 4294 */     jj.a(RecipeItemStack.a(new IMaterial[] { Items.SALMON }, ), Items.COOKED_SALMON, 0.35F, var3, var2)
/* 4295 */       .a("has_salmon", a(Items.SALMON))
/* 4296 */       .a(var0, "cooked_salmon_from_" + var1);
/*      */     
/* 4298 */     jj.a(RecipeItemStack.a(new IMaterial[] { Items.MUTTON }, ), Items.COOKED_MUTTON, 0.35F, var3, var2)
/* 4299 */       .a("has_mutton", a(Items.MUTTON))
/* 4300 */       .a(var0, "cooked_mutton_from_" + var1);
/*      */     
/* 4302 */     jj.a(RecipeItemStack.a(new IMaterial[] { Items.PORKCHOP }, ), Items.COOKED_PORKCHOP, 0.35F, var3, var2)
/* 4303 */       .a("has_porkchop", a(Items.PORKCHOP))
/* 4304 */       .a(var0, "cooked_porkchop_from_" + var1);
/*      */     
/* 4306 */     jj.a(RecipeItemStack.a(new IMaterial[] { Items.POTATO }, ), Items.BAKED_POTATO, 0.35F, var3, var2)
/* 4307 */       .a("has_potato", a(Items.POTATO))
/* 4308 */       .a(var0, "baked_potato_from_" + var1);
/*      */     
/* 4310 */     jj.a(RecipeItemStack.a(new IMaterial[] { Items.RABBIT }, ), Items.COOKED_RABBIT, 0.35F, var3, var2)
/* 4311 */       .a("has_rabbit", a(Items.RABBIT))
/* 4312 */       .a(var0, "cooked_rabbit_from_" + var1);
/*      */   }
/*      */ 
/*      */   
/*      */   private static CriterionTriggerEnterBlock.a a(Block var0) {
/* 4317 */     return new CriterionTriggerEnterBlock.a(CriterionConditionEntity.b.a, var0, CriterionTriggerProperties.a);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static CriterionTriggerInventoryChanged.a a(IMaterial var0) {
/* 4325 */     return a(new CriterionConditionItem[] { CriterionConditionItem.a.a().a(var0).b() });
/*      */   }
/*      */   
/*      */   private static CriterionTriggerInventoryChanged.a a(Tag<Item> var0) {
/* 4329 */     return a(new CriterionConditionItem[] { CriterionConditionItem.a.a().a(var0).b() });
/*      */   }
/*      */   
/*      */   private static CriterionTriggerInventoryChanged.a a(CriterionConditionItem... var0) {
/* 4333 */     return new CriterionTriggerInventoryChanged.a(CriterionConditionEntity.b.a, CriterionConditionValue.IntegerRange.e, CriterionConditionValue.IntegerRange.e, CriterionConditionValue.IntegerRange.e, var0);
/*      */   }
/*      */ 
/*      */   
/*      */   public String a() {
/* 4338 */     return "Recipes";
/*      */   }
/*      */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\jg.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
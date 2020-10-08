/*      */ package net.minecraft.server.v1_16_R2;
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
/*      */ public class BiomesSettingsDefault
/*      */ {
/*      */   private static int a(float var0) {
/*   29 */     float var1 = var0;
/*   30 */     var1 /= 3.0F;
/*   31 */     var1 = MathHelper.a(var1, -1.0F, 1.0F);
/*   32 */     return MathHelper.f(0.62222224F - var1 * 0.05F, 0.5F + var1 * 0.1F, 1.0F);
/*      */   }
/*      */   
/*      */   public static BiomeBase a(float var0, float var1, float var2, boolean var3) {
/*   36 */     BiomeSettingsMobs.a var4 = new BiomeSettingsMobs.a();
/*   37 */     BiomeSettings.a(var4);
/*   38 */     var4.a(EnumCreatureType.CREATURE, new BiomeSettingsMobs.c(EntityTypes.WOLF, 8, 4, 4));
/*   39 */     var4.a(EnumCreatureType.CREATURE, new BiomeSettingsMobs.c(EntityTypes.RABBIT, 4, 2, 3));
/*   40 */     var4.a(EnumCreatureType.CREATURE, new BiomeSettingsMobs.c(EntityTypes.FOX, 8, 2, 4));
/*   41 */     if (var3) {
/*   42 */       BiomeSettings.c(var4);
/*      */     } else {
/*   44 */       BiomeSettings.b(var4);
/*   45 */       BiomeSettings.b(var4, 100, 25, 100);
/*      */     } 
/*      */ 
/*      */     
/*   49 */     BiomeSettingsGeneration.a var5 = (new BiomeSettingsGeneration.a()).a(WorldGenSurfaceComposites.i);
/*      */     
/*   51 */     BiomeSettings.b(var5);
/*   52 */     var5.a(StructureFeatures.y);
/*      */     
/*   54 */     BiomeSettings.d(var5);
/*      */     
/*   56 */     BiomeSettings.f(var5);
/*   57 */     BiomeSettings.h(var5);
/*   58 */     BiomeSettings.p(var5);
/*   59 */     BiomeSettings.q(var5);
/*   60 */     BiomeSettings.i(var5);
/*   61 */     BiomeSettings.j(var5);
/*   62 */     BiomeSettings.n(var5);
/*   63 */     var5.a(WorldGenStage.Decoration.VEGETAL_DECORATION, var3 ? BiomeDecoratorGroups.TREES_GIANT_SPRUCE : BiomeDecoratorGroups.TREES_GIANT);
/*   64 */     BiomeSettings.U(var5);
/*   65 */     BiomeSettings.T(var5);
/*   66 */     BiomeSettings.Z(var5);
/*   67 */     BiomeSettings.aa(var5);
/*   68 */     BiomeSettings.ak(var5);
/*   69 */     BiomeSettings.s(var5);
/*   70 */     BiomeSettings.an(var5);
/*      */     
/*   72 */     return (new BiomeBase.a())
/*   73 */       .a(BiomeBase.Precipitation.RAIN)
/*   74 */       .a(BiomeBase.Geography.TAIGA)
/*   75 */       .a(var0)
/*   76 */       .b(var1)
/*   77 */       .c(var2)
/*   78 */       .d(0.8F)
/*   79 */       .a((new BiomeFog.a())
/*   80 */         .b(4159204)
/*   81 */         .c(329011)
/*   82 */         .a(12638463)
/*   83 */         .d(a(var2))
/*   84 */         .a(CaveSoundSettings.b)
/*   85 */         .a())
/*      */       
/*   87 */       .a(var4.b())
/*   88 */       .a(var5.a())
/*   89 */       .a();
/*      */   }
/*      */   
/*      */   public static BiomeBase a(float var0, float var1, boolean var2) {
/*   93 */     BiomeSettingsMobs.a var3 = new BiomeSettingsMobs.a();
/*   94 */     BiomeSettings.a(var3);
/*   95 */     BiomeSettings.c(var3);
/*      */ 
/*      */     
/*   98 */     BiomeSettingsGeneration.a var4 = (new BiomeSettingsGeneration.a()).a(WorldGenSurfaceComposites.j);
/*   99 */     BiomeSettings.b(var4);
/*  100 */     var4.a(StructureFeatures.y);
/*      */     
/*  102 */     BiomeSettings.d(var4);
/*      */     
/*  104 */     BiomeSettings.f(var4);
/*  105 */     BiomeSettings.h(var4);
/*  106 */     BiomeSettings.N(var4);
/*  107 */     BiomeSettings.i(var4);
/*  108 */     BiomeSettings.j(var4);
/*  109 */     BiomeSettings.n(var4);
/*  110 */     if (var2) {
/*  111 */       BiomeSettings.z(var4);
/*      */     } else {
/*  113 */       BiomeSettings.x(var4);
/*      */     } 
/*  115 */     BiomeSettings.U(var4);
/*  116 */     BiomeSettings.O(var4);
/*  117 */     BiomeSettings.Z(var4);
/*  118 */     BiomeSettings.aa(var4);
/*  119 */     BiomeSettings.ak(var4);
/*  120 */     BiomeSettings.an(var4);
/*      */     
/*  122 */     return (new BiomeBase.a())
/*  123 */       .a(BiomeBase.Precipitation.RAIN)
/*  124 */       .a(BiomeBase.Geography.FOREST)
/*  125 */       .a(var0)
/*  126 */       .b(var1)
/*  127 */       .c(0.6F)
/*  128 */       .d(0.6F)
/*  129 */       .a((new BiomeFog.a())
/*  130 */         .b(4159204)
/*  131 */         .c(329011)
/*  132 */         .a(12638463)
/*  133 */         .d(a(0.6F))
/*  134 */         .a(CaveSoundSettings.b)
/*  135 */         .a())
/*      */       
/*  137 */       .a(var3.b())
/*  138 */       .a(var4.a())
/*  139 */       .a();
/*      */   }
/*      */   
/*      */   public static BiomeBase a() {
/*  143 */     return a(0.1F, 0.2F, 40, 2, 3);
/*      */   }
/*      */   
/*      */   public static BiomeBase b() {
/*  147 */     BiomeSettingsMobs.a var0 = new BiomeSettingsMobs.a();
/*  148 */     BiomeSettings.h(var0);
/*      */     
/*  150 */     return a(0.1F, 0.2F, 0.8F, false, true, false, var0);
/*      */   }
/*      */   
/*      */   public static BiomeBase c() {
/*  154 */     BiomeSettingsMobs.a var0 = new BiomeSettingsMobs.a();
/*  155 */     BiomeSettings.h(var0);
/*      */     
/*  157 */     return a(0.2F, 0.4F, 0.8F, false, true, true, var0);
/*      */   }
/*      */   
/*      */   public static BiomeBase d() {
/*  161 */     BiomeSettingsMobs.a var0 = new BiomeSettingsMobs.a();
/*  162 */     BiomeSettings.h(var0);
/*  163 */     var0.a(EnumCreatureType.CREATURE, new BiomeSettingsMobs.c(EntityTypes.PARROT, 10, 1, 1))
/*  164 */       .a(EnumCreatureType.MONSTER, new BiomeSettingsMobs.c(EntityTypes.OCELOT, 2, 1, 1));
/*  165 */     return a(0.2F, 0.4F, 0.9F, false, false, true, var0);
/*      */   }
/*      */   
/*      */   public static BiomeBase e() {
/*  169 */     return a(0.45F, 0.3F, 10, 1, 1);
/*      */   }
/*      */   
/*      */   public static BiomeBase f() {
/*  173 */     return a(0.1F, 0.2F, 40, 2);
/*      */   }
/*      */   
/*      */   public static BiomeBase g() {
/*  177 */     return a(0.45F, 0.3F, 10, 1);
/*      */   }
/*      */   
/*      */   private static BiomeBase a(float var0, float var1, int var2, int var3, int var4) {
/*  181 */     BiomeSettingsMobs.a var5 = new BiomeSettingsMobs.a();
/*  182 */     BiomeSettings.h(var5);
/*  183 */     var5.a(EnumCreatureType.CREATURE, new BiomeSettingsMobs.c(EntityTypes.PARROT, var2, 1, var3))
/*  184 */       .a(EnumCreatureType.MONSTER, new BiomeSettingsMobs.c(EntityTypes.OCELOT, 2, 1, var4))
/*  185 */       .a(EnumCreatureType.CREATURE, new BiomeSettingsMobs.c(EntityTypes.PANDA, 1, 1, 2));
/*      */     
/*  187 */     var5.a();
/*  188 */     return a(var0, var1, 0.9F, false, false, false, var5);
/*      */   }
/*      */   
/*      */   private static BiomeBase a(float var0, float var1, int var2, int var3) {
/*  192 */     BiomeSettingsMobs.a var4 = new BiomeSettingsMobs.a();
/*  193 */     BiomeSettings.h(var4);
/*  194 */     var4.a(EnumCreatureType.CREATURE, new BiomeSettingsMobs.c(EntityTypes.PARROT, var2, 1, var3))
/*  195 */       .a(EnumCreatureType.CREATURE, new BiomeSettingsMobs.c(EntityTypes.PANDA, 80, 1, 2))
/*  196 */       .a(EnumCreatureType.MONSTER, new BiomeSettingsMobs.c(EntityTypes.OCELOT, 2, 1, 1));
/*      */     
/*  198 */     return a(var0, var1, 0.9F, true, false, false, var4);
/*      */   }
/*      */ 
/*      */   
/*      */   private static BiomeBase a(float var0, float var1, float var2, boolean var3, boolean var4, boolean var5, BiomeSettingsMobs.a var6) {
/*  203 */     BiomeSettingsGeneration.a var7 = (new BiomeSettingsGeneration.a()).a(WorldGenSurfaceComposites.j);
/*  204 */     if (!var4 && !var5) {
/*  205 */       var7.a(StructureFeatures.e);
/*      */     }
/*  207 */     BiomeSettings.b(var7);
/*  208 */     var7.a(StructureFeatures.A);
/*      */     
/*  210 */     BiomeSettings.d(var7);
/*      */     
/*  212 */     BiomeSettings.f(var7);
/*  213 */     BiomeSettings.h(var7);
/*  214 */     BiomeSettings.i(var7);
/*  215 */     BiomeSettings.j(var7);
/*  216 */     BiomeSettings.n(var7);
/*  217 */     if (var3) {
/*  218 */       BiomeSettings.u(var7);
/*      */     } else {
/*  220 */       if (!var4 && !var5) {
/*  221 */         BiomeSettings.t(var7);
/*      */       }
/*  223 */       if (var4) {
/*  224 */         BiomeSettings.F(var7);
/*      */       } else {
/*  226 */         BiomeSettings.E(var7);
/*      */       } 
/*      */     } 
/*  229 */     BiomeSettings.V(var7);
/*  230 */     BiomeSettings.I(var7);
/*  231 */     BiomeSettings.Z(var7);
/*  232 */     BiomeSettings.aa(var7);
/*  233 */     BiomeSettings.ak(var7);
/*  234 */     BiomeSettings.ac(var7);
/*  235 */     BiomeSettings.an(var7);
/*  236 */     return (new BiomeBase.a())
/*  237 */       .a(BiomeBase.Precipitation.RAIN)
/*  238 */       .a(BiomeBase.Geography.JUNGLE)
/*  239 */       .a(var0)
/*  240 */       .b(var1)
/*  241 */       .c(0.95F)
/*  242 */       .d(var2)
/*  243 */       .a((new BiomeFog.a())
/*  244 */         .b(4159204)
/*  245 */         .c(329011)
/*  246 */         .a(12638463)
/*  247 */         .d(a(0.95F))
/*  248 */         .a(CaveSoundSettings.b)
/*  249 */         .a())
/*      */       
/*  251 */       .a(var6.b())
/*  252 */       .a(var7.a())
/*  253 */       .a();
/*      */   }
/*      */   
/*      */   public static BiomeBase a(float var0, float var1, WorldGenSurfaceComposite<WorldGenSurfaceConfigurationBase> var2, boolean var3) {
/*  257 */     BiomeSettingsMobs.a var4 = new BiomeSettingsMobs.a();
/*  258 */     BiomeSettings.a(var4);
/*  259 */     var4.a(EnumCreatureType.CREATURE, new BiomeSettingsMobs.c(EntityTypes.LLAMA, 5, 4, 6));
/*  260 */     BiomeSettings.c(var4);
/*      */ 
/*      */     
/*  263 */     BiomeSettingsGeneration.a var5 = (new BiomeSettingsGeneration.a()).a(var2);
/*  264 */     BiomeSettings.b(var5);
/*  265 */     var5.a(StructureFeatures.C);
/*      */     
/*  267 */     BiomeSettings.d(var5);
/*      */     
/*  269 */     BiomeSettings.f(var5);
/*  270 */     BiomeSettings.h(var5);
/*  271 */     BiomeSettings.i(var5);
/*  272 */     BiomeSettings.j(var5);
/*  273 */     BiomeSettings.n(var5);
/*  274 */     if (var3) {
/*  275 */       BiomeSettings.D(var5);
/*      */     } else {
/*  277 */       BiomeSettings.C(var5);
/*      */     } 
/*  279 */     BiomeSettings.U(var5);
/*  280 */     BiomeSettings.W(var5);
/*  281 */     BiomeSettings.Z(var5);
/*  282 */     BiomeSettings.aa(var5);
/*  283 */     BiomeSettings.ak(var5);
/*  284 */     BiomeSettings.l(var5);
/*  285 */     BiomeSettings.m(var5);
/*  286 */     BiomeSettings.an(var5);
/*      */     
/*  288 */     return (new BiomeBase.a())
/*  289 */       .a(BiomeBase.Precipitation.RAIN)
/*  290 */       .a(BiomeBase.Geography.EXTREME_HILLS)
/*  291 */       .a(var0)
/*  292 */       .b(var1)
/*  293 */       .c(0.2F)
/*  294 */       .d(0.3F)
/*  295 */       .a((new BiomeFog.a())
/*  296 */         .b(4159204)
/*  297 */         .c(329011)
/*  298 */         .a(12638463)
/*  299 */         .d(a(0.2F))
/*  300 */         .a(CaveSoundSettings.b)
/*  301 */         .a())
/*      */       
/*  303 */       .a(var4.b())
/*  304 */       .a(var5.a())
/*  305 */       .a();
/*      */   }
/*      */   
/*      */   public static BiomeBase a(float var0, float var1, boolean var2, boolean var3, boolean var4) {
/*  309 */     BiomeSettingsMobs.a var5 = new BiomeSettingsMobs.a();
/*  310 */     BiomeSettings.f(var5);
/*      */ 
/*      */     
/*  313 */     BiomeSettingsGeneration.a var6 = (new BiomeSettingsGeneration.a()).a(WorldGenSurfaceComposites.d);
/*  314 */     if (var2) {
/*  315 */       var6.a(StructureFeatures.u);
/*  316 */       var6.a(StructureFeatures.a);
/*      */     } 
/*  318 */     if (var3) {
/*  319 */       var6.a(StructureFeatures.f);
/*      */     }
/*  321 */     if (var4) {
/*  322 */       BiomeSettings.ag(var6);
/*      */     }
/*  324 */     BiomeSettings.b(var6);
/*  325 */     var6.a(StructureFeatures.z);
/*      */     
/*  327 */     BiomeSettings.d(var6);
/*  328 */     BiomeSettings.g(var6);
/*  329 */     BiomeSettings.h(var6);
/*  330 */     BiomeSettings.i(var6);
/*  331 */     BiomeSettings.j(var6);
/*  332 */     BiomeSettings.n(var6);
/*  333 */     BiomeSettings.U(var6);
/*  334 */     BiomeSettings.W(var6);
/*  335 */     BiomeSettings.S(var6);
/*  336 */     BiomeSettings.Z(var6);
/*  337 */     BiomeSettings.ad(var6);
/*  338 */     BiomeSettings.ak(var6);
/*  339 */     BiomeSettings.af(var6);
/*  340 */     BiomeSettings.an(var6);
/*      */     
/*  342 */     return (new BiomeBase.a())
/*  343 */       .a(BiomeBase.Precipitation.NONE)
/*  344 */       .a(BiomeBase.Geography.DESERT)
/*  345 */       .a(var0)
/*  346 */       .b(var1)
/*  347 */       .c(2.0F)
/*  348 */       .d(0.0F)
/*  349 */       .a((new BiomeFog.a())
/*  350 */         .b(4159204)
/*  351 */         .c(329011)
/*  352 */         .a(12638463)
/*  353 */         .d(a(2.0F))
/*  354 */         .a(CaveSoundSettings.b)
/*  355 */         .a())
/*      */       
/*  357 */       .a(var5.b())
/*  358 */       .a(var6.a())
/*  359 */       .a();
/*      */   }
/*      */   
/*      */   public static BiomeBase a(boolean var0) {
/*  363 */     BiomeSettingsMobs.a var1 = new BiomeSettingsMobs.a();
/*  364 */     BiomeSettings.d(var1);
/*  365 */     if (!var0) {
/*  366 */       var1.a();
/*      */     }
/*      */ 
/*      */     
/*  370 */     BiomeSettingsGeneration.a var2 = (new BiomeSettingsGeneration.a()).a(WorldGenSurfaceComposites.j);
/*  371 */     if (!var0) {
/*  372 */       var2.a(StructureFeatures.t)
/*  373 */         .a(StructureFeatures.a);
/*      */     }
/*  375 */     BiomeSettings.b(var2);
/*  376 */     var2.a(StructureFeatures.y);
/*      */     
/*  378 */     BiomeSettings.d(var2);
/*      */     
/*  380 */     BiomeSettings.f(var2);
/*  381 */     BiomeSettings.h(var2);
/*  382 */     BiomeSettings.Y(var2);
/*  383 */     if (var0) {
/*  384 */       var2.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeDecoratorGroups.PATCH_SUNFLOWER);
/*      */     }
/*  386 */     BiomeSettings.i(var2);
/*  387 */     BiomeSettings.j(var2);
/*  388 */     BiomeSettings.n(var2);
/*  389 */     BiomeSettings.R(var2);
/*  390 */     if (var0) {
/*  391 */       var2.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeDecoratorGroups.PATCH_SUGAR_CANE);
/*      */     }
/*  393 */     BiomeSettings.Z(var2);
/*  394 */     if (var0) {
/*  395 */       var2.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeDecoratorGroups.PATCH_PUMPKIN);
/*      */     } else {
/*  397 */       BiomeSettings.aa(var2);
/*      */     } 
/*  399 */     BiomeSettings.ak(var2);
/*  400 */     BiomeSettings.an(var2);
/*      */     
/*  402 */     return (new BiomeBase.a())
/*  403 */       .a(BiomeBase.Precipitation.RAIN)
/*  404 */       .a(BiomeBase.Geography.PLAINS)
/*  405 */       .a(0.125F)
/*  406 */       .b(0.05F)
/*  407 */       .c(0.8F)
/*  408 */       .d(0.4F)
/*  409 */       .a((new BiomeFog.a())
/*  410 */         .b(4159204)
/*  411 */         .c(329011)
/*  412 */         .a(12638463)
/*  413 */         .d(a(0.8F))
/*  414 */         .a(CaveSoundSettings.b)
/*  415 */         .a())
/*      */       
/*  417 */       .a(var1.b())
/*  418 */       .a(var2.a())
/*  419 */       .a();
/*      */   }
/*      */   
/*      */   private static BiomeBase a(BiomeSettingsGeneration.a var0) {
/*  423 */     BiomeSettingsMobs.a var1 = new BiomeSettingsMobs.a();
/*  424 */     BiomeSettings.i(var1);
/*      */     
/*  426 */     return (new BiomeBase.a())
/*  427 */       .a(BiomeBase.Precipitation.NONE)
/*  428 */       .a(BiomeBase.Geography.THEEND)
/*  429 */       .a(0.1F)
/*  430 */       .b(0.2F)
/*  431 */       .c(0.5F)
/*  432 */       .d(0.5F)
/*  433 */       .a((new BiomeFog.a())
/*  434 */         .b(4159204)
/*  435 */         .c(329011)
/*  436 */         .a(10518688)
/*  437 */         .d(0)
/*  438 */         .a(CaveSoundSettings.b)
/*  439 */         .a())
/*      */       
/*  441 */       .a(var1.b())
/*  442 */       .a(var0.a())
/*  443 */       .a();
/*      */   }
/*      */ 
/*      */   
/*      */   public static BiomeBase h() {
/*  448 */     BiomeSettingsGeneration.a var0 = (new BiomeSettingsGeneration.a()).a(WorldGenSurfaceComposites.e);
/*  449 */     return a(var0);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static BiomeBase i() {
/*  455 */     BiomeSettingsGeneration.a var0 = (new BiomeSettingsGeneration.a()).a(WorldGenSurfaceComposites.e).a(WorldGenStage.Decoration.SURFACE_STRUCTURES, BiomeDecoratorGroups.END_SPIKE);
/*  456 */     return a(var0);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static BiomeBase j() {
/*  462 */     BiomeSettingsGeneration.a var0 = (new BiomeSettingsGeneration.a()).a(WorldGenSurfaceComposites.e).a(StructureFeatures.q);
/*  463 */     return a(var0);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static BiomeBase k() {
/*  471 */     BiomeSettingsGeneration.a var0 = (new BiomeSettingsGeneration.a()).a(WorldGenSurfaceComposites.e).a(StructureFeatures.q).a(WorldGenStage.Decoration.SURFACE_STRUCTURES, BiomeDecoratorGroups.END_GATEWAY).a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeDecoratorGroups.CHORUS_PLANT);
/*  472 */     return a(var0);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static BiomeBase l() {
/*  478 */     BiomeSettingsGeneration.a var0 = (new BiomeSettingsGeneration.a()).a(WorldGenSurfaceComposites.e).a(WorldGenStage.Decoration.RAW_GENERATION, BiomeDecoratorGroups.END_ISLAND_DECORATED);
/*  479 */     return a(var0);
/*      */   }
/*      */   
/*      */   public static BiomeBase a(float var0, float var1) {
/*  483 */     BiomeSettingsMobs.a var2 = new BiomeSettingsMobs.a();
/*  484 */     BiomeSettings.g(var2);
/*      */ 
/*      */     
/*  487 */     BiomeSettingsGeneration.a var3 = (new BiomeSettingsGeneration.a()).a(WorldGenSurfaceComposites.n);
/*  488 */     BiomeSettings.b(var3);
/*  489 */     var3.a(StructureFeatures.y);
/*      */     
/*  491 */     BiomeSettings.d(var3);
/*      */     
/*  493 */     BiomeSettings.f(var3);
/*  494 */     BiomeSettings.h(var3);
/*  495 */     BiomeSettings.i(var3);
/*  496 */     BiomeSettings.j(var3);
/*  497 */     BiomeSettings.n(var3);
/*  498 */     BiomeSettings.Q(var3);
/*  499 */     BiomeSettings.Z(var3);
/*  500 */     BiomeSettings.aa(var3);
/*  501 */     BiomeSettings.ak(var3);
/*  502 */     BiomeSettings.an(var3);
/*      */     
/*  504 */     return (new BiomeBase.a())
/*  505 */       .a(BiomeBase.Precipitation.RAIN)
/*  506 */       .a(BiomeBase.Geography.MUSHROOM)
/*  507 */       .a(var0)
/*  508 */       .b(var1)
/*  509 */       .c(0.9F)
/*  510 */       .d(1.0F)
/*  511 */       .a((new BiomeFog.a())
/*  512 */         .b(4159204)
/*  513 */         .c(329011)
/*  514 */         .a(12638463)
/*  515 */         .d(a(0.9F))
/*  516 */         .a(CaveSoundSettings.b)
/*  517 */         .a())
/*      */       
/*  519 */       .a(var2.b())
/*  520 */       .a(var3.a())
/*  521 */       .a();
/*      */   }
/*      */ 
/*      */   
/*      */   private static BiomeBase a(float var0, float var1, float var2, boolean var3, boolean var4, BiomeSettingsMobs.a var5) {
/*  526 */     BiomeSettingsGeneration.a var6 = (new BiomeSettingsGeneration.a()).a(var4 ? WorldGenSurfaceComposites.r : WorldGenSurfaceComposites.j);
/*  527 */     if (!var3 && !var4) {
/*  528 */       var6.a(StructureFeatures.v)
/*  529 */         .a(StructureFeatures.a);
/*      */     }
/*  531 */     BiomeSettings.b(var6);
/*  532 */     var6.a(var3 ? StructureFeatures.C : StructureFeatures.y);
/*      */     
/*  534 */     BiomeSettings.d(var6);
/*      */     
/*  536 */     BiomeSettings.f(var6);
/*  537 */     BiomeSettings.h(var6);
/*  538 */     if (!var4) {
/*  539 */       BiomeSettings.J(var6);
/*      */     }
/*  541 */     BiomeSettings.i(var6);
/*  542 */     BiomeSettings.j(var6);
/*  543 */     BiomeSettings.n(var6);
/*  544 */     if (var4) {
/*  545 */       BiomeSettings.B(var6);
/*  546 */       BiomeSettings.U(var6);
/*  547 */       BiomeSettings.K(var6);
/*      */     } else {
/*  549 */       BiomeSettings.A(var6);
/*  550 */       BiomeSettings.V(var6);
/*  551 */       BiomeSettings.L(var6);
/*      */     } 
/*  553 */     BiomeSettings.Z(var6);
/*  554 */     BiomeSettings.aa(var6);
/*  555 */     BiomeSettings.ak(var6);
/*  556 */     BiomeSettings.an(var6);
/*      */     
/*  558 */     return (new BiomeBase.a())
/*  559 */       .a(BiomeBase.Precipitation.NONE)
/*  560 */       .a(BiomeBase.Geography.SAVANNA)
/*  561 */       .a(var0)
/*  562 */       .b(var1)
/*  563 */       .c(var2)
/*  564 */       .d(0.0F)
/*  565 */       .a((new BiomeFog.a())
/*  566 */         .b(4159204)
/*  567 */         .c(329011)
/*  568 */         .a(12638463)
/*  569 */         .d(a(var2))
/*  570 */         .a(CaveSoundSettings.b)
/*  571 */         .a())
/*      */       
/*  573 */       .a(var5.b())
/*  574 */       .a(var6.a())
/*  575 */       .a();
/*      */   }
/*      */   
/*      */   public static BiomeBase a(float var0, float var1, float var2, boolean var3, boolean var4) {
/*  579 */     BiomeSettingsMobs.a var5 = x();
/*  580 */     return a(var0, var1, var2, var3, var4, var5);
/*      */   }
/*      */   
/*      */   private static BiomeSettingsMobs.a x() {
/*  584 */     BiomeSettingsMobs.a var0 = new BiomeSettingsMobs.a();
/*  585 */     BiomeSettings.a(var0);
/*  586 */     var0.a(EnumCreatureType.CREATURE, new BiomeSettingsMobs.c(EntityTypes.HORSE, 1, 2, 6))
/*  587 */       .a(EnumCreatureType.CREATURE, new BiomeSettingsMobs.c(EntityTypes.DONKEY, 1, 1, 1));
/*  588 */     BiomeSettings.c(var0);
/*  589 */     return var0;
/*      */   }
/*      */   
/*      */   public static BiomeBase m() {
/*  593 */     BiomeSettingsMobs.a var0 = x();
/*  594 */     var0.a(EnumCreatureType.CREATURE, new BiomeSettingsMobs.c(EntityTypes.LLAMA, 8, 4, 4));
/*  595 */     return a(1.5F, 0.025F, 1.0F, true, false, var0);
/*      */   }
/*      */   
/*      */   private static BiomeBase a(WorldGenSurfaceComposite<WorldGenSurfaceConfigurationBase> var0, float var1, float var2, boolean var3, boolean var4) {
/*  599 */     BiomeSettingsMobs.a var5 = new BiomeSettingsMobs.a();
/*  600 */     BiomeSettings.c(var5);
/*      */ 
/*      */     
/*  603 */     BiomeSettingsGeneration.a var6 = (new BiomeSettingsGeneration.a()).a(var0);
/*  604 */     BiomeSettings.a(var6);
/*  605 */     var6.a(var3 ? StructureFeatures.C : StructureFeatures.y);
/*      */     
/*  607 */     BiomeSettings.d(var6);
/*      */     
/*  609 */     BiomeSettings.f(var6);
/*  610 */     BiomeSettings.h(var6);
/*  611 */     BiomeSettings.i(var6);
/*  612 */     BiomeSettings.j(var6);
/*  613 */     BiomeSettings.k(var6);
/*  614 */     BiomeSettings.n(var6);
/*  615 */     if (var4) {
/*  616 */       BiomeSettings.G(var6);
/*      */     }
/*  618 */     BiomeSettings.M(var6);
/*  619 */     BiomeSettings.Z(var6);
/*  620 */     BiomeSettings.ab(var6);
/*  621 */     BiomeSettings.ak(var6);
/*  622 */     BiomeSettings.an(var6);
/*  623 */     return (new BiomeBase.a())
/*  624 */       .a(BiomeBase.Precipitation.NONE)
/*  625 */       .a(BiomeBase.Geography.MESA)
/*  626 */       .a(var1)
/*  627 */       .b(var2)
/*  628 */       .c(2.0F)
/*  629 */       .d(0.0F)
/*  630 */       .a((new BiomeFog.a())
/*  631 */         .b(4159204)
/*  632 */         .c(329011)
/*  633 */         .a(12638463)
/*  634 */         .d(a(2.0F))
/*  635 */         .e(10387789)
/*  636 */         .f(9470285)
/*  637 */         .a(CaveSoundSettings.b)
/*  638 */         .a())
/*      */       
/*  640 */       .a(var5.b())
/*  641 */       .a(var6.a())
/*  642 */       .a();
/*      */   }
/*      */   
/*      */   public static BiomeBase b(float var0, float var1, boolean var2) {
/*  646 */     return a(WorldGenSurfaceComposites.a, var0, var1, var2, false);
/*      */   }
/*      */   
/*      */   public static BiomeBase b(float var0, float var1) {
/*  650 */     return a(WorldGenSurfaceComposites.w, var0, var1, true, true);
/*      */   }
/*      */   
/*      */   public static BiomeBase n() {
/*  654 */     return a(WorldGenSurfaceComposites.f, 0.1F, 0.2F, true, false);
/*      */   }
/*      */   
/*      */   private static BiomeBase a(BiomeSettingsMobs.a var0, int var1, int var2, boolean var3, BiomeSettingsGeneration.a var4) {
/*  658 */     return (new BiomeBase.a())
/*  659 */       .a(BiomeBase.Precipitation.RAIN)
/*  660 */       .a(BiomeBase.Geography.OCEAN)
/*  661 */       .a(var3 ? -1.8F : -1.0F)
/*  662 */       .b(0.1F)
/*  663 */       .c(0.5F)
/*  664 */       .d(0.5F)
/*  665 */       .a((new BiomeFog.a())
/*  666 */         .b(var1)
/*  667 */         .c(var2)
/*  668 */         .a(12638463)
/*  669 */         .d(a(0.5F))
/*  670 */         .a(CaveSoundSettings.b)
/*  671 */         .a())
/*      */       
/*  673 */       .a(var0.b())
/*  674 */       .a(var4.a())
/*  675 */       .a();
/*      */   }
/*      */ 
/*      */   
/*      */   private static BiomeSettingsGeneration.a a(WorldGenSurfaceComposite<WorldGenSurfaceConfigurationBase> var0, boolean var1, boolean var2, boolean var3) {
/*  680 */     BiomeSettingsGeneration.a var4 = (new BiomeSettingsGeneration.a()).a(var0);
/*  681 */     StructureFeature<?, ?> var5 = var2 ? StructureFeatures.n : StructureFeatures.m;
/*  682 */     if (var3) {
/*  683 */       if (var1) {
/*  684 */         var4.a(StructureFeatures.l);
/*      */       }
/*  686 */       BiomeSettings.c(var4);
/*  687 */       var4.a(var5);
/*      */     } else {
/*  689 */       var4.a(var5);
/*  690 */       if (var1) {
/*  691 */         var4.a(StructureFeatures.l);
/*      */       }
/*  693 */       BiomeSettings.c(var4);
/*      */     } 
/*  695 */     var4.a(StructureFeatures.D);
/*      */     
/*  697 */     BiomeSettings.e(var4);
/*      */     
/*  699 */     BiomeSettings.f(var4);
/*  700 */     BiomeSettings.h(var4);
/*  701 */     BiomeSettings.i(var4);
/*  702 */     BiomeSettings.j(var4);
/*  703 */     BiomeSettings.n(var4);
/*  704 */     BiomeSettings.w(var4);
/*  705 */     BiomeSettings.U(var4);
/*  706 */     BiomeSettings.W(var4);
/*  707 */     BiomeSettings.Z(var4);
/*  708 */     BiomeSettings.aa(var4);
/*  709 */     BiomeSettings.ak(var4);
/*  710 */     return var4;
/*      */   }
/*      */   
/*      */   public static BiomeBase b(boolean var0) {
/*  714 */     BiomeSettingsMobs.a var1 = new BiomeSettingsMobs.a();
/*  715 */     BiomeSettings.a(var1, 3, 4, 15);
/*  716 */     var1.a(EnumCreatureType.WATER_AMBIENT, new BiomeSettingsMobs.c(EntityTypes.SALMON, 15, 1, 5));
/*      */     
/*  718 */     boolean var2 = !var0;
/*  719 */     BiomeSettingsGeneration.a var3 = a(WorldGenSurfaceComposites.j, var0, false, var2);
/*  720 */     var3.a(WorldGenStage.Decoration.VEGETAL_DECORATION, var0 ? BiomeDecoratorGroups.SEAGRASS_DEEP_COLD : BiomeDecoratorGroups.SEAGRASS_COLD);
/*  721 */     BiomeSettings.ai(var3);
/*  722 */     BiomeSettings.ah(var3);
/*  723 */     BiomeSettings.an(var3);
/*      */     
/*  725 */     return a(var1, 4020182, 329011, var0, var3);
/*      */   }
/*      */   
/*      */   public static BiomeBase c(boolean var0) {
/*  729 */     BiomeSettingsMobs.a var1 = new BiomeSettingsMobs.a();
/*  730 */     BiomeSettings.a(var1, 1, 4, 10);
/*  731 */     var1.a(EnumCreatureType.WATER_CREATURE, new BiomeSettingsMobs.c(EntityTypes.DOLPHIN, 1, 1, 2));
/*      */     
/*  733 */     BiomeSettingsGeneration.a var2 = a(WorldGenSurfaceComposites.j, var0, false, true);
/*  734 */     var2.a(WorldGenStage.Decoration.VEGETAL_DECORATION, var0 ? BiomeDecoratorGroups.SEAGRASS_DEEP : BiomeDecoratorGroups.SEAGRASS_NORMAL);
/*  735 */     BiomeSettings.ai(var2);
/*  736 */     BiomeSettings.ah(var2);
/*  737 */     BiomeSettings.an(var2);
/*      */     
/*  739 */     return a(var1, 4159204, 329011, var0, var2);
/*      */   }
/*      */   
/*      */   public static BiomeBase d(boolean var0) {
/*  743 */     BiomeSettingsMobs.a var1 = new BiomeSettingsMobs.a();
/*  744 */     if (var0) {
/*  745 */       BiomeSettings.a(var1, 8, 4, 8);
/*      */     } else {
/*  747 */       BiomeSettings.a(var1, 10, 2, 15);
/*      */     } 
/*  749 */     var1.a(EnumCreatureType.WATER_AMBIENT, new BiomeSettingsMobs.c(EntityTypes.PUFFERFISH, 5, 1, 3))
/*  750 */       .a(EnumCreatureType.WATER_AMBIENT, new BiomeSettingsMobs.c(EntityTypes.TROPICAL_FISH, 25, 8, 8))
/*  751 */       .a(EnumCreatureType.WATER_CREATURE, new BiomeSettingsMobs.c(EntityTypes.DOLPHIN, 2, 1, 2));
/*      */     
/*  753 */     BiomeSettingsGeneration.a var2 = a(WorldGenSurfaceComposites.q, var0, true, false);
/*  754 */     var2.a(WorldGenStage.Decoration.VEGETAL_DECORATION, var0 ? BiomeDecoratorGroups.SEAGRASS_DEEP_WARM : BiomeDecoratorGroups.SEAGRASS_WARM);
/*  755 */     if (var0) {
/*  756 */       BiomeSettings.ai(var2);
/*      */     }
/*  758 */     BiomeSettings.aj(var2);
/*  759 */     BiomeSettings.an(var2);
/*      */     
/*  761 */     return a(var1, 4566514, 267827, var0, var2);
/*      */   }
/*      */ 
/*      */   
/*      */   public static BiomeBase o() {
/*  766 */     BiomeSettingsMobs.a var0 = (new BiomeSettingsMobs.a()).a(EnumCreatureType.WATER_AMBIENT, new BiomeSettingsMobs.c(EntityTypes.PUFFERFISH, 15, 1, 3));
/*  767 */     BiomeSettings.a(var0, 10, 4);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  772 */     BiomeSettingsGeneration.a var1 = a(WorldGenSurfaceComposites.h, false, true, false).a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeDecoratorGroups.WARM_OCEAN_VEGETATION).a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeDecoratorGroups.SEAGRASS_WARM).a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeDecoratorGroups.SEA_PICKLE);
/*  773 */     BiomeSettings.an(var1);
/*      */     
/*  775 */     return a(var0, 4445678, 270131, false, var1);
/*      */   }
/*      */   
/*      */   public static BiomeBase p() {
/*  779 */     BiomeSettingsMobs.a var0 = new BiomeSettingsMobs.a();
/*  780 */     BiomeSettings.a(var0, 5, 1);
/*  781 */     var0.a(EnumCreatureType.MONSTER, new BiomeSettingsMobs.c(EntityTypes.DROWNED, 5, 1, 1));
/*      */ 
/*      */     
/*  784 */     BiomeSettingsGeneration.a var1 = a(WorldGenSurfaceComposites.h, true, true, false).a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeDecoratorGroups.SEAGRASS_DEEP_WARM);
/*  785 */     BiomeSettings.ai(var1);
/*  786 */     BiomeSettings.an(var1);
/*      */     
/*  788 */     return a(var0, 4445678, 270131, true, var1);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static BiomeBase e(boolean var0) {
/*  795 */     BiomeSettingsMobs.a var1 = (new BiomeSettingsMobs.a()).a(EnumCreatureType.WATER_CREATURE, new BiomeSettingsMobs.c(EntityTypes.SQUID, 1, 1, 4)).a(EnumCreatureType.WATER_AMBIENT, new BiomeSettingsMobs.c(EntityTypes.SALMON, 15, 1, 5)).a(EnumCreatureType.CREATURE, new BiomeSettingsMobs.c(EntityTypes.POLAR_BEAR, 1, 1, 2));
/*  796 */     BiomeSettings.c(var1);
/*  797 */     var1.a(EnumCreatureType.MONSTER, new BiomeSettingsMobs.c(EntityTypes.DROWNED, 5, 1, 1));
/*      */     
/*  799 */     float var2 = var0 ? 0.5F : 0.0F;
/*      */     
/*  801 */     BiomeSettingsGeneration.a var3 = (new BiomeSettingsGeneration.a()).a(WorldGenSurfaceComposites.g);
/*  802 */     var3.a(StructureFeatures.m);
/*  803 */     if (var0) {
/*  804 */       var3.a(StructureFeatures.l);
/*      */     }
/*  806 */     BiomeSettings.c(var3);
/*  807 */     var3.a(StructureFeatures.D);
/*      */     
/*  809 */     BiomeSettings.e(var3);
/*      */     
/*  811 */     BiomeSettings.f(var3);
/*  812 */     BiomeSettings.al(var3);
/*  813 */     BiomeSettings.h(var3);
/*  814 */     BiomeSettings.am(var3);
/*  815 */     BiomeSettings.i(var3);
/*  816 */     BiomeSettings.j(var3);
/*  817 */     BiomeSettings.n(var3);
/*  818 */     BiomeSettings.w(var3);
/*  819 */     BiomeSettings.U(var3);
/*  820 */     BiomeSettings.W(var3);
/*  821 */     BiomeSettings.Z(var3);
/*  822 */     BiomeSettings.aa(var3);
/*  823 */     BiomeSettings.ak(var3);
/*  824 */     BiomeSettings.an(var3);
/*      */     
/*  826 */     return (new BiomeBase.a())
/*  827 */       .a(var0 ? BiomeBase.Precipitation.RAIN : BiomeBase.Precipitation.SNOW)
/*  828 */       .a(BiomeBase.Geography.OCEAN)
/*  829 */       .a(var0 ? -1.8F : -1.0F)
/*  830 */       .b(0.1F)
/*  831 */       .c(var2)
/*  832 */       .a(BiomeBase.TemperatureModifier.FROZEN)
/*  833 */       .d(0.5F)
/*  834 */       .a((new BiomeFog.a())
/*  835 */         .b(3750089)
/*  836 */         .c(329011)
/*  837 */         .a(12638463)
/*  838 */         .d(a(var2))
/*  839 */         .a(CaveSoundSettings.b)
/*  840 */         .a())
/*      */       
/*  842 */       .a(var1.b())
/*  843 */       .a(var3.a())
/*  844 */       .a();
/*      */   }
/*      */ 
/*      */   
/*      */   private static BiomeBase a(float var0, float var1, boolean var2, BiomeSettingsMobs.a var3) {
/*  849 */     BiomeSettingsGeneration.a var4 = (new BiomeSettingsGeneration.a()).a(WorldGenSurfaceComposites.j);
/*  850 */     BiomeSettings.b(var4);
/*  851 */     var4.a(StructureFeatures.y);
/*      */     
/*  853 */     BiomeSettings.d(var4);
/*      */     
/*  855 */     BiomeSettings.f(var4);
/*  856 */     BiomeSettings.h(var4);
/*  857 */     if (var2) {
/*  858 */       var4.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeDecoratorGroups.FOREST_FLOWER_VEGETATION_COMMON);
/*      */     } else {
/*  860 */       BiomeSettings.N(var4);
/*      */     } 
/*  862 */     BiomeSettings.i(var4);
/*  863 */     BiomeSettings.j(var4);
/*  864 */     BiomeSettings.n(var4);
/*  865 */     if (var2) {
/*  866 */       var4.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeDecoratorGroups.FOREST_FLOWER_TREES);
/*  867 */       var4.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeDecoratorGroups.FLOWER_FOREST);
/*  868 */       BiomeSettings.W(var4);
/*      */     } else {
/*  870 */       BiomeSettings.y(var4);
/*  871 */       BiomeSettings.U(var4);
/*  872 */       BiomeSettings.O(var4);
/*      */     } 
/*  874 */     BiomeSettings.Z(var4);
/*  875 */     BiomeSettings.aa(var4);
/*  876 */     BiomeSettings.ak(var4);
/*  877 */     BiomeSettings.an(var4);
/*      */     
/*  879 */     return (new BiomeBase.a())
/*  880 */       .a(BiomeBase.Precipitation.RAIN)
/*  881 */       .a(BiomeBase.Geography.FOREST)
/*  882 */       .a(var0)
/*  883 */       .b(var1)
/*  884 */       .c(0.7F)
/*  885 */       .d(0.8F)
/*  886 */       .a((new BiomeFog.a())
/*  887 */         .b(4159204)
/*  888 */         .c(329011)
/*  889 */         .a(12638463)
/*  890 */         .d(a(0.7F))
/*  891 */         .a(CaveSoundSettings.b)
/*  892 */         .a())
/*      */       
/*  894 */       .a(var3.b())
/*  895 */       .a(var4.a())
/*  896 */       .a();
/*      */   }
/*      */   
/*      */   private static BiomeSettingsMobs.a y() {
/*  900 */     BiomeSettingsMobs.a var0 = new BiomeSettingsMobs.a();
/*  901 */     BiomeSettings.a(var0);
/*  902 */     BiomeSettings.c(var0);
/*  903 */     return var0;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static BiomeBase c(float var0, float var1) {
/*  909 */     BiomeSettingsMobs.a var2 = y().a(EnumCreatureType.CREATURE, new BiomeSettingsMobs.c(EntityTypes.WOLF, 5, 4, 4)).a();
/*  910 */     return a(var0, var1, false, var2);
/*      */   }
/*      */ 
/*      */   
/*      */   public static BiomeBase q() {
/*  915 */     BiomeSettingsMobs.a var0 = y().a(EnumCreatureType.CREATURE, new BiomeSettingsMobs.c(EntityTypes.RABBIT, 4, 2, 3));
/*  916 */     return a(0.1F, 0.4F, true, var0);
/*      */   }
/*      */   
/*      */   public static BiomeBase a(float var0, float var1, boolean var2, boolean var3, boolean var4, boolean var5) {
/*  920 */     BiomeSettingsMobs.a var6 = new BiomeSettingsMobs.a();
/*  921 */     BiomeSettings.a(var6);
/*  922 */     var6.a(EnumCreatureType.CREATURE, new BiomeSettingsMobs.c(EntityTypes.WOLF, 8, 4, 4))
/*  923 */       .a(EnumCreatureType.CREATURE, new BiomeSettingsMobs.c(EntityTypes.RABBIT, 4, 2, 3))
/*  924 */       .a(EnumCreatureType.CREATURE, new BiomeSettingsMobs.c(EntityTypes.FOX, 8, 2, 4));
/*      */     
/*  926 */     if (!var2 && !var3) {
/*  927 */       var6.a();
/*      */     }
/*  929 */     BiomeSettings.c(var6);
/*      */     
/*  931 */     float var7 = var2 ? -0.5F : 0.25F;
/*      */ 
/*      */     
/*  934 */     BiomeSettingsGeneration.a var8 = (new BiomeSettingsGeneration.a()).a(WorldGenSurfaceComposites.j);
/*  935 */     if (var4) {
/*  936 */       var8.a(StructureFeatures.x);
/*  937 */       var8.a(StructureFeatures.a);
/*      */     } 
/*  939 */     if (var5) {
/*  940 */       var8.a(StructureFeatures.g);
/*      */     }
/*  942 */     BiomeSettings.b(var8);
/*  943 */     var8.a(var3 ? StructureFeatures.C : StructureFeatures.y);
/*      */     
/*  945 */     BiomeSettings.d(var8);
/*      */     
/*  947 */     BiomeSettings.f(var8);
/*  948 */     BiomeSettings.h(var8);
/*  949 */     BiomeSettings.q(var8);
/*  950 */     BiomeSettings.i(var8);
/*  951 */     BiomeSettings.j(var8);
/*  952 */     BiomeSettings.n(var8);
/*  953 */     BiomeSettings.v(var8);
/*  954 */     BiomeSettings.U(var8);
/*  955 */     BiomeSettings.X(var8);
/*  956 */     BiomeSettings.Z(var8);
/*  957 */     BiomeSettings.aa(var8);
/*  958 */     BiomeSettings.ak(var8);
/*  959 */     if (var2) {
/*  960 */       BiomeSettings.r(var8);
/*      */     } else {
/*  962 */       BiomeSettings.s(var8);
/*      */     } 
/*  964 */     BiomeSettings.an(var8);
/*      */     
/*  966 */     return (new BiomeBase.a())
/*  967 */       .a(var2 ? BiomeBase.Precipitation.SNOW : BiomeBase.Precipitation.RAIN)
/*  968 */       .a(BiomeBase.Geography.TAIGA)
/*  969 */       .a(var0)
/*  970 */       .b(var1)
/*  971 */       .c(var7)
/*  972 */       .d(var2 ? 0.4F : 0.8F)
/*  973 */       .a((new BiomeFog.a())
/*  974 */         .b(var2 ? 4020182 : 4159204)
/*  975 */         .c(329011)
/*  976 */         .a(12638463)
/*  977 */         .d(a(var7))
/*  978 */         .a(CaveSoundSettings.b)
/*  979 */         .a())
/*      */       
/*  981 */       .a(var6.b())
/*  982 */       .a(var8.a())
/*  983 */       .a();
/*      */   }
/*      */   
/*      */   public static BiomeBase c(float var0, float var1, boolean var2) {
/*  987 */     BiomeSettingsMobs.a var3 = new BiomeSettingsMobs.a();
/*  988 */     BiomeSettings.a(var3);
/*  989 */     BiomeSettings.c(var3);
/*      */ 
/*      */     
/*  992 */     BiomeSettingsGeneration.a var4 = (new BiomeSettingsGeneration.a()).a(WorldGenSurfaceComposites.j);
/*  993 */     var4.a(StructureFeatures.d);
/*  994 */     BiomeSettings.b(var4);
/*  995 */     var4.a(StructureFeatures.y);
/*      */     
/*  997 */     BiomeSettings.d(var4);
/*      */     
/*  999 */     BiomeSettings.f(var4);
/* 1000 */     BiomeSettings.h(var4);
/* 1001 */     var4.a(WorldGenStage.Decoration.VEGETAL_DECORATION, var2 ? BiomeDecoratorGroups.DARK_FOREST_VEGETATION_RED : BiomeDecoratorGroups.DARK_FOREST_VEGETATION_BROWN);
/* 1002 */     BiomeSettings.N(var4);
/* 1003 */     BiomeSettings.i(var4);
/* 1004 */     BiomeSettings.j(var4);
/* 1005 */     BiomeSettings.n(var4);
/* 1006 */     BiomeSettings.U(var4);
/* 1007 */     BiomeSettings.O(var4);
/* 1008 */     BiomeSettings.Z(var4);
/* 1009 */     BiomeSettings.aa(var4);
/* 1010 */     BiomeSettings.ak(var4);
/* 1011 */     BiomeSettings.an(var4);
/*      */     
/* 1013 */     return (new BiomeBase.a())
/* 1014 */       .a(BiomeBase.Precipitation.RAIN)
/* 1015 */       .a(BiomeBase.Geography.FOREST)
/* 1016 */       .a(var0)
/* 1017 */       .b(var1)
/* 1018 */       .c(0.7F)
/* 1019 */       .d(0.8F)
/* 1020 */       .a((new BiomeFog.a())
/* 1021 */         .b(4159204)
/* 1022 */         .c(329011)
/* 1023 */         .a(12638463)
/* 1024 */         .d(a(0.7F))
/* 1025 */         .a(BiomeFog.GrassColor.DARK_FOREST)
/* 1026 */         .a(CaveSoundSettings.b)
/* 1027 */         .a())
/*      */       
/* 1029 */       .a(var3.b())
/* 1030 */       .a(var4.a())
/* 1031 */       .a();
/*      */   }
/*      */   
/*      */   public static BiomeBase d(float var0, float var1, boolean var2) {
/* 1035 */     BiomeSettingsMobs.a var3 = new BiomeSettingsMobs.a();
/* 1036 */     BiomeSettings.a(var3);
/* 1037 */     BiomeSettings.c(var3);
/* 1038 */     var3.a(EnumCreatureType.MONSTER, new BiomeSettingsMobs.c(EntityTypes.SLIME, 1, 1, 1));
/*      */ 
/*      */     
/* 1041 */     BiomeSettingsGeneration.a var4 = (new BiomeSettingsGeneration.a()).a(WorldGenSurfaceComposites.u);
/*      */     
/* 1043 */     if (!var2) {
/* 1044 */       var4.a(StructureFeatures.j);
/*      */     }
/* 1046 */     var4.a(StructureFeatures.b);
/* 1047 */     var4.a(StructureFeatures.B);
/*      */     
/* 1049 */     BiomeSettings.d(var4);
/* 1050 */     if (!var2) {
/* 1051 */       BiomeSettings.ag(var4);
/*      */     }
/* 1053 */     BiomeSettings.f(var4);
/* 1054 */     BiomeSettings.h(var4);
/* 1055 */     BiomeSettings.i(var4);
/* 1056 */     BiomeSettings.j(var4);
/* 1057 */     BiomeSettings.o(var4);
/* 1058 */     BiomeSettings.P(var4);
/* 1059 */     BiomeSettings.Z(var4);
/* 1060 */     BiomeSettings.ae(var4);
/* 1061 */     BiomeSettings.ak(var4);
/* 1062 */     if (var2) {
/* 1063 */       BiomeSettings.ag(var4);
/*      */     } else {
/* 1065 */       var4.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeDecoratorGroups.SEAGRASS_SWAMP);
/*      */     } 
/* 1067 */     BiomeSettings.an(var4);
/*      */     
/* 1069 */     return (new BiomeBase.a())
/* 1070 */       .a(BiomeBase.Precipitation.RAIN)
/* 1071 */       .a(BiomeBase.Geography.SWAMP)
/* 1072 */       .a(var0)
/* 1073 */       .b(var1)
/* 1074 */       .c(0.8F)
/* 1075 */       .d(0.9F)
/* 1076 */       .a((new BiomeFog.a())
/* 1077 */         .b(6388580)
/* 1078 */         .c(2302743)
/* 1079 */         .a(12638463)
/* 1080 */         .d(a(0.8F))
/* 1081 */         .e(6975545)
/* 1082 */         .a(BiomeFog.GrassColor.SWAMP)
/* 1083 */         .a(CaveSoundSettings.b)
/* 1084 */         .a())
/*      */       
/* 1086 */       .a(var3.b())
/* 1087 */       .a(var4.a())
/* 1088 */       .a();
/*      */   }
/*      */ 
/*      */   
/*      */   public static BiomeBase a(float var0, float var1, boolean var2, boolean var3) {
/* 1093 */     BiomeSettingsMobs.a var4 = (new BiomeSettingsMobs.a()).a(0.07F);
/* 1094 */     BiomeSettings.e(var4);
/*      */ 
/*      */     
/* 1097 */     BiomeSettingsGeneration.a var5 = (new BiomeSettingsGeneration.a()).a(var2 ? WorldGenSurfaceComposites.l : WorldGenSurfaceComposites.j);
/*      */     
/* 1099 */     if (!var2 && !var3) {
/* 1100 */       var5.a(StructureFeatures.w)
/* 1101 */         .a(StructureFeatures.g);
/*      */     }
/* 1103 */     BiomeSettings.b(var5);
/* 1104 */     if (!var2 && !var3) {
/* 1105 */       var5.a(StructureFeatures.a);
/*      */     }
/* 1107 */     var5.a(var3 ? StructureFeatures.C : StructureFeatures.y);
/*      */     
/* 1109 */     BiomeSettings.d(var5);
/*      */     
/* 1111 */     BiomeSettings.f(var5);
/* 1112 */     BiomeSettings.h(var5);
/* 1113 */     if (var2) {
/* 1114 */       var5.a(WorldGenStage.Decoration.SURFACE_STRUCTURES, BiomeDecoratorGroups.ICE_SPIKE);
/* 1115 */       var5.a(WorldGenStage.Decoration.SURFACE_STRUCTURES, BiomeDecoratorGroups.ICE_PATCH);
/*      */     } 
/*      */     
/* 1118 */     BiomeSettings.i(var5);
/* 1119 */     BiomeSettings.j(var5);
/* 1120 */     BiomeSettings.n(var5);
/* 1121 */     BiomeSettings.H(var5);
/* 1122 */     BiomeSettings.U(var5);
/* 1123 */     BiomeSettings.W(var5);
/* 1124 */     BiomeSettings.Z(var5);
/* 1125 */     BiomeSettings.aa(var5);
/* 1126 */     BiomeSettings.ak(var5);
/* 1127 */     BiomeSettings.an(var5);
/*      */     
/* 1129 */     return (new BiomeBase.a())
/* 1130 */       .a(BiomeBase.Precipitation.SNOW)
/* 1131 */       .a(BiomeBase.Geography.ICY)
/* 1132 */       .a(var0)
/* 1133 */       .b(var1)
/* 1134 */       .c(0.0F)
/* 1135 */       .d(0.5F)
/* 1136 */       .a((new BiomeFog.a())
/* 1137 */         .b(4159204)
/* 1138 */         .c(329011)
/* 1139 */         .a(12638463)
/* 1140 */         .d(a(0.0F))
/* 1141 */         .a(CaveSoundSettings.b)
/* 1142 */         .a())
/*      */       
/* 1144 */       .a(var4.b())
/* 1145 */       .a(var5.a())
/* 1146 */       .a();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static BiomeBase a(float var0, float var1, float var2, int var3, boolean var4) {
/* 1152 */     BiomeSettingsMobs.a var5 = (new BiomeSettingsMobs.a()).a(EnumCreatureType.WATER_CREATURE, new BiomeSettingsMobs.c(EntityTypes.SQUID, 2, 1, 4)).a(EnumCreatureType.WATER_AMBIENT, new BiomeSettingsMobs.c(EntityTypes.SALMON, 5, 1, 5));
/* 1153 */     BiomeSettings.c(var5);
/* 1154 */     var5.a(EnumCreatureType.MONSTER, new BiomeSettingsMobs.c(EntityTypes.DROWNED, var4 ? 1 : 100, 1, 1));
/*      */ 
/*      */     
/* 1157 */     BiomeSettingsGeneration.a var6 = (new BiomeSettingsGeneration.a()).a(WorldGenSurfaceComposites.j);
/* 1158 */     var6.a(StructureFeatures.b);
/* 1159 */     var6.a(StructureFeatures.y);
/*      */     
/* 1161 */     BiomeSettings.d(var6);
/*      */     
/* 1163 */     BiomeSettings.f(var6);
/* 1164 */     BiomeSettings.h(var6);
/* 1165 */     BiomeSettings.i(var6);
/* 1166 */     BiomeSettings.j(var6);
/* 1167 */     BiomeSettings.n(var6);
/* 1168 */     BiomeSettings.w(var6);
/* 1169 */     BiomeSettings.U(var6);
/* 1170 */     BiomeSettings.W(var6);
/* 1171 */     BiomeSettings.Z(var6);
/* 1172 */     BiomeSettings.aa(var6);
/* 1173 */     BiomeSettings.ak(var6);
/* 1174 */     if (!var4) {
/* 1175 */       var6.a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeDecoratorGroups.SEAGRASS_RIVER);
/*      */     }
/* 1177 */     BiomeSettings.an(var6);
/*      */     
/* 1179 */     return (new BiomeBase.a())
/* 1180 */       .a(var4 ? BiomeBase.Precipitation.SNOW : BiomeBase.Precipitation.RAIN)
/* 1181 */       .a(BiomeBase.Geography.RIVER)
/* 1182 */       .a(var0)
/* 1183 */       .b(var1)
/* 1184 */       .c(var2)
/* 1185 */       .d(0.5F)
/* 1186 */       .a((new BiomeFog.a())
/* 1187 */         .b(var3)
/* 1188 */         .c(329011)
/* 1189 */         .a(12638463)
/* 1190 */         .d(a(var2))
/* 1191 */         .a(CaveSoundSettings.b)
/* 1192 */         .a())
/*      */       
/* 1194 */       .a(var5.b())
/* 1195 */       .a(var6.a())
/* 1196 */       .a();
/*      */   }
/*      */   
/*      */   public static BiomeBase a(float var0, float var1, float var2, float var3, int var4, boolean var5, boolean var6) {
/* 1200 */     BiomeSettingsMobs.a var7 = new BiomeSettingsMobs.a();
/* 1201 */     if (!var6 && !var5) {
/* 1202 */       var7.a(EnumCreatureType.CREATURE, new BiomeSettingsMobs.c(EntityTypes.TURTLE, 5, 2, 5));
/*      */     }
/* 1204 */     BiomeSettings.c(var7);
/*      */ 
/*      */     
/* 1207 */     BiomeSettingsGeneration.a var8 = (new BiomeSettingsGeneration.a()).a(var6 ? WorldGenSurfaceComposites.t : WorldGenSurfaceComposites.d);
/* 1208 */     if (var6) {
/* 1209 */       BiomeSettings.b(var8);
/*      */     } else {
/* 1211 */       var8.a(StructureFeatures.b);
/* 1212 */       var8.a(StructureFeatures.r);
/* 1213 */       var8.a(StructureFeatures.i);
/*      */     } 
/* 1215 */     var8.a(var6 ? StructureFeatures.C : StructureFeatures.y);
/*      */     
/* 1217 */     BiomeSettings.d(var8);
/*      */     
/* 1219 */     BiomeSettings.f(var8);
/* 1220 */     BiomeSettings.h(var8);
/* 1221 */     BiomeSettings.i(var8);
/* 1222 */     BiomeSettings.j(var8);
/* 1223 */     BiomeSettings.n(var8);
/* 1224 */     BiomeSettings.U(var8);
/* 1225 */     BiomeSettings.W(var8);
/* 1226 */     BiomeSettings.Z(var8);
/* 1227 */     BiomeSettings.aa(var8);
/* 1228 */     BiomeSettings.ak(var8);
/* 1229 */     BiomeSettings.an(var8);
/*      */     
/* 1231 */     return (new BiomeBase.a())
/* 1232 */       .a(var5 ? BiomeBase.Precipitation.SNOW : BiomeBase.Precipitation.RAIN)
/* 1233 */       .a(var6 ? BiomeBase.Geography.NONE : BiomeBase.Geography.BEACH)
/* 1234 */       .a(var0)
/* 1235 */       .b(var1)
/* 1236 */       .c(var2)
/* 1237 */       .d(var3)
/* 1238 */       .a((new BiomeFog.a())
/* 1239 */         .b(var4)
/* 1240 */         .c(329011)
/* 1241 */         .a(12638463)
/* 1242 */         .d(a(var2))
/* 1243 */         .a(CaveSoundSettings.b)
/* 1244 */         .a())
/*      */       
/* 1246 */       .a(var7.b())
/* 1247 */       .a(var8.a())
/* 1248 */       .a();
/*      */   }
/*      */ 
/*      */   
/*      */   public static BiomeBase r() {
/* 1253 */     BiomeSettingsGeneration.a var0 = (new BiomeSettingsGeneration.a()).a(WorldGenSurfaceComposites.p);
/* 1254 */     var0.a(WorldGenStage.Decoration.TOP_LAYER_MODIFICATION, BiomeDecoratorGroups.VOID_START_PLATFORM);
/*      */     
/* 1256 */     return (new BiomeBase.a())
/* 1257 */       .a(BiomeBase.Precipitation.NONE)
/* 1258 */       .a(BiomeBase.Geography.NONE)
/* 1259 */       .a(0.1F)
/* 1260 */       .b(0.2F)
/* 1261 */       .c(0.5F)
/* 1262 */       .d(0.5F)
/* 1263 */       .a((new BiomeFog.a())
/* 1264 */         .b(4159204)
/* 1265 */         .c(329011)
/* 1266 */         .a(12638463)
/* 1267 */         .d(a(0.5F))
/* 1268 */         .a(CaveSoundSettings.b)
/* 1269 */         .a())
/*      */       
/* 1271 */       .a(BiomeSettingsMobs.b)
/* 1272 */       .a(var0.a())
/* 1273 */       .a();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static BiomeBase s() {
/* 1284 */     BiomeSettingsMobs var0 = (new BiomeSettingsMobs.a()).a(EnumCreatureType.MONSTER, new BiomeSettingsMobs.c(EntityTypes.GHAST, 50, 4, 4)).a(EnumCreatureType.MONSTER, new BiomeSettingsMobs.c(EntityTypes.ZOMBIFIED_PIGLIN, 100, 4, 4)).a(EnumCreatureType.MONSTER, new BiomeSettingsMobs.c(EntityTypes.MAGMA_CUBE, 2, 4, 4)).a(EnumCreatureType.MONSTER, new BiomeSettingsMobs.c(EntityTypes.ENDERMAN, 1, 4, 4)).a(EnumCreatureType.MONSTER, new BiomeSettingsMobs.c(EntityTypes.PIGLIN, 15, 4, 4)).a(EnumCreatureType.CREATURE, new BiomeSettingsMobs.c(EntityTypes.STRIDER, 60, 1, 2)).b();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1292 */     BiomeSettingsGeneration.a var1 = (new BiomeSettingsGeneration.a()).a(WorldGenSurfaceComposites.o).a(StructureFeatures.E).a(StructureFeatures.o).a(StructureFeatures.s).<WorldGenFeatureConfigurationChance>a(WorldGenStage.Features.AIR, WorldGenCarvers.f).a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeDecoratorGroups.SPRING_LAVA);
/*      */     
/* 1294 */     BiomeSettings.Z(var1);
/* 1295 */     var1.a(WorldGenStage.Decoration.UNDERGROUND_DECORATION, BiomeDecoratorGroups.SPRING_OPEN)
/* 1296 */       .a(WorldGenStage.Decoration.UNDERGROUND_DECORATION, BiomeDecoratorGroups.PATCH_FIRE)
/* 1297 */       .a(WorldGenStage.Decoration.UNDERGROUND_DECORATION, BiomeDecoratorGroups.PATCH_SOUL_FIRE)
/* 1298 */       .a(WorldGenStage.Decoration.UNDERGROUND_DECORATION, BiomeDecoratorGroups.GLOWSTONE_EXTRA)
/* 1299 */       .a(WorldGenStage.Decoration.UNDERGROUND_DECORATION, BiomeDecoratorGroups.GLOWSTONE)
/* 1300 */       .a(WorldGenStage.Decoration.UNDERGROUND_DECORATION, BiomeDecoratorGroups.BROWN_MUSHROOM_NETHER)
/* 1301 */       .a(WorldGenStage.Decoration.UNDERGROUND_DECORATION, BiomeDecoratorGroups.RED_MUSHROOM_NETHER)
/* 1302 */       .a(WorldGenStage.Decoration.UNDERGROUND_DECORATION, BiomeDecoratorGroups.ORE_MAGMA)
/* 1303 */       .a(WorldGenStage.Decoration.UNDERGROUND_DECORATION, BiomeDecoratorGroups.SPRING_CLOSED);
/*      */     
/* 1305 */     BiomeSettings.ao(var1);
/*      */     
/* 1307 */     return (new BiomeBase.a())
/* 1308 */       .a(BiomeBase.Precipitation.NONE)
/* 1309 */       .a(BiomeBase.Geography.NETHER)
/* 1310 */       .a(0.1F)
/* 1311 */       .b(0.2F)
/* 1312 */       .c(2.0F)
/* 1313 */       .d(0.0F)
/* 1314 */       .a((new BiomeFog.a())
/* 1315 */         .b(4159204)
/* 1316 */         .c(329011)
/* 1317 */         .a(3344392)
/* 1318 */         .d(a(2.0F))
/* 1319 */         .a(SoundEffects.AMBIENT_NETHER_WASTES_LOOP)
/* 1320 */         .a(new CaveSoundSettings(SoundEffects.AMBIENT_NETHER_WASTES_MOOD, 6000, 8, 2.0D))
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1326 */         .a(new CaveSound(SoundEffects.AMBIENT_NETHER_WASTES_ADDITIONS, 0.0111D))
/*      */ 
/*      */ 
/*      */         
/* 1330 */         .a(Musics.a(SoundEffects.MUSIC_NETHER_NETHER_WASTES))
/* 1331 */         .a())
/*      */       
/* 1333 */       .a(var0)
/* 1334 */       .a(var1.a())
/* 1335 */       .a();
/*      */   }
/*      */   
/*      */   public static BiomeBase t() {
/* 1339 */     double var0 = 0.7D;
/* 1340 */     double var2 = 0.15D;
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
/* 1351 */     BiomeSettingsMobs var4 = (new BiomeSettingsMobs.a()).a(EnumCreatureType.MONSTER, new BiomeSettingsMobs.c(EntityTypes.SKELETON, 20, 5, 5)).a(EnumCreatureType.MONSTER, new BiomeSettingsMobs.c(EntityTypes.GHAST, 50, 4, 4)).a(EnumCreatureType.MONSTER, new BiomeSettingsMobs.c(EntityTypes.ENDERMAN, 1, 4, 4)).a(EnumCreatureType.CREATURE, new BiomeSettingsMobs.c(EntityTypes.STRIDER, 60, 1, 2)).a(EntityTypes.SKELETON, 0.7D, 0.15D).a(EntityTypes.GHAST, 0.7D, 0.15D).a(EntityTypes.ENDERMAN, 0.7D, 0.15D).a(EntityTypes.STRIDER, 0.7D, 0.15D).b();
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
/* 1371 */     BiomeSettingsGeneration.a var5 = (new BiomeSettingsGeneration.a()).a(WorldGenSurfaceComposites.s).a(StructureFeatures.o).a(StructureFeatures.p).a(StructureFeatures.E).a(StructureFeatures.s).<WorldGenFeatureConfigurationChance>a(WorldGenStage.Features.AIR, WorldGenCarvers.f).a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeDecoratorGroups.SPRING_LAVA).a(WorldGenStage.Decoration.LOCAL_MODIFICATIONS, BiomeDecoratorGroups.BASALT_PILLAR).a(WorldGenStage.Decoration.UNDERGROUND_DECORATION, BiomeDecoratorGroups.SPRING_OPEN).a(WorldGenStage.Decoration.UNDERGROUND_DECORATION, BiomeDecoratorGroups.GLOWSTONE_EXTRA).a(WorldGenStage.Decoration.UNDERGROUND_DECORATION, BiomeDecoratorGroups.GLOWSTONE).a(WorldGenStage.Decoration.UNDERGROUND_DECORATION, BiomeDecoratorGroups.PATCH_CRIMSON_ROOTS).a(WorldGenStage.Decoration.UNDERGROUND_DECORATION, BiomeDecoratorGroups.PATCH_FIRE).a(WorldGenStage.Decoration.UNDERGROUND_DECORATION, BiomeDecoratorGroups.PATCH_SOUL_FIRE).a(WorldGenStage.Decoration.UNDERGROUND_DECORATION, BiomeDecoratorGroups.ORE_MAGMA).a(WorldGenStage.Decoration.UNDERGROUND_DECORATION, BiomeDecoratorGroups.SPRING_CLOSED).a(WorldGenStage.Decoration.UNDERGROUND_DECORATION, BiomeDecoratorGroups.ORE_SOUL_SAND);
/*      */     
/* 1373 */     BiomeSettings.ao(var5);
/*      */     
/* 1375 */     return (new BiomeBase.a())
/* 1376 */       .a(BiomeBase.Precipitation.NONE)
/* 1377 */       .a(BiomeBase.Geography.NETHER)
/* 1378 */       .a(0.1F)
/* 1379 */       .b(0.2F)
/* 1380 */       .c(2.0F)
/* 1381 */       .d(0.0F)
/* 1382 */       .a((new BiomeFog.a())
/* 1383 */         .b(4159204)
/* 1384 */         .c(329011)
/* 1385 */         .a(1787717)
/* 1386 */         .d(a(2.0F))
/* 1387 */         .a(new BiomeParticles(Particles.ASH, 0.00625F))
/* 1388 */         .a(SoundEffects.AMBIENT_SOUL_SAND_VALLEY_LOOP)
/* 1389 */         .a(new CaveSoundSettings(SoundEffects.AMBIENT_SOUL_SAND_VALLEY_MOOD, 6000, 8, 2.0D))
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1395 */         .a(new CaveSound(SoundEffects.AMBIENT_SOUL_SAND_VALLEY_ADDITIONS, 0.0111D))
/*      */ 
/*      */ 
/*      */         
/* 1399 */         .a(Musics.a(SoundEffects.MUSIC_NETHER_SOUL_SAND_VALLEY))
/* 1400 */         .a())
/*      */       
/* 1402 */       .a(var4)
/* 1403 */       .a(var5.a())
/* 1404 */       .a();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static BiomeBase u() {
/* 1412 */     BiomeSettingsMobs var0 = (new BiomeSettingsMobs.a()).a(EnumCreatureType.MONSTER, new BiomeSettingsMobs.c(EntityTypes.GHAST, 40, 1, 1)).a(EnumCreatureType.MONSTER, new BiomeSettingsMobs.c(EntityTypes.MAGMA_CUBE, 100, 2, 5)).a(EnumCreatureType.CREATURE, new BiomeSettingsMobs.c(EntityTypes.STRIDER, 60, 1, 2)).b();
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
/* 1435 */     BiomeSettingsGeneration.a var1 = (new BiomeSettingsGeneration.a()).a(WorldGenSurfaceComposites.b).a(StructureFeatures.E).<WorldGenFeatureConfigurationChance>a(WorldGenStage.Features.AIR, WorldGenCarvers.f).a(StructureFeatures.o).a(WorldGenStage.Decoration.SURFACE_STRUCTURES, BiomeDecoratorGroups.DELTA).a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeDecoratorGroups.SPRING_LAVA_DOUBLE).a(WorldGenStage.Decoration.SURFACE_STRUCTURES, BiomeDecoratorGroups.SMALL_BASALT_COLUMNS).a(WorldGenStage.Decoration.SURFACE_STRUCTURES, BiomeDecoratorGroups.LARGE_BASALT_COLUMNS).a(WorldGenStage.Decoration.UNDERGROUND_DECORATION, BiomeDecoratorGroups.BASALT_BLOBS).a(WorldGenStage.Decoration.UNDERGROUND_DECORATION, BiomeDecoratorGroups.BLACKSTONE_BLOBS).a(WorldGenStage.Decoration.UNDERGROUND_DECORATION, BiomeDecoratorGroups.SPRING_DELTA).a(WorldGenStage.Decoration.UNDERGROUND_DECORATION, BiomeDecoratorGroups.PATCH_FIRE).a(WorldGenStage.Decoration.UNDERGROUND_DECORATION, BiomeDecoratorGroups.PATCH_SOUL_FIRE).a(WorldGenStage.Decoration.UNDERGROUND_DECORATION, BiomeDecoratorGroups.GLOWSTONE_EXTRA).a(WorldGenStage.Decoration.UNDERGROUND_DECORATION, BiomeDecoratorGroups.GLOWSTONE).a(WorldGenStage.Decoration.UNDERGROUND_DECORATION, BiomeDecoratorGroups.BROWN_MUSHROOM_NETHER).a(WorldGenStage.Decoration.UNDERGROUND_DECORATION, BiomeDecoratorGroups.RED_MUSHROOM_NETHER).a(WorldGenStage.Decoration.UNDERGROUND_DECORATION, BiomeDecoratorGroups.ORE_MAGMA).a(WorldGenStage.Decoration.UNDERGROUND_DECORATION, BiomeDecoratorGroups.SPRING_CLOSED_DOUBLE).a(WorldGenStage.Decoration.UNDERGROUND_DECORATION, BiomeDecoratorGroups.ORE_GOLD_DELTAS).a(WorldGenStage.Decoration.UNDERGROUND_DECORATION, BiomeDecoratorGroups.ORE_QUARTZ_DELTAS);
/* 1436 */     BiomeSettings.ap(var1);
/*      */     
/* 1438 */     return (new BiomeBase.a())
/* 1439 */       .a(BiomeBase.Precipitation.NONE)
/* 1440 */       .a(BiomeBase.Geography.NETHER)
/* 1441 */       .a(0.1F)
/* 1442 */       .b(0.2F)
/* 1443 */       .c(2.0F)
/* 1444 */       .d(0.0F)
/* 1445 */       .a((new BiomeFog.a())
/* 1446 */         .b(4159204)
/* 1447 */         .c(4341314)
/* 1448 */         .a(6840176)
/* 1449 */         .d(a(2.0F))
/* 1450 */         .a(new BiomeParticles(Particles.WHITE_ASH, 0.118093334F))
/* 1451 */         .a(SoundEffects.AMBIENT_BASALT_DELTAS_LOOP)
/* 1452 */         .a(new CaveSoundSettings(SoundEffects.AMBIENT_BASALT_DELTAS_MOOD, 6000, 8, 2.0D))
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1458 */         .a(new CaveSound(SoundEffects.AMBIENT_BASALT_DELTAS_ADDITIONS, 0.0111D))
/*      */ 
/*      */ 
/*      */         
/* 1462 */         .a(Musics.a(SoundEffects.MUSIC_NETHER_BASALT_DELTAS))
/* 1463 */         .a())
/*      */       
/* 1465 */       .a(var0)
/* 1466 */       .a(var1.a())
/* 1467 */       .a();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static BiomeBase v() {
/* 1476 */     BiomeSettingsMobs var0 = (new BiomeSettingsMobs.a()).a(EnumCreatureType.MONSTER, new BiomeSettingsMobs.c(EntityTypes.ZOMBIFIED_PIGLIN, 1, 2, 4)).a(EnumCreatureType.MONSTER, new BiomeSettingsMobs.c(EntityTypes.HOGLIN, 9, 3, 4)).a(EnumCreatureType.MONSTER, new BiomeSettingsMobs.c(EntityTypes.PIGLIN, 5, 3, 4)).a(EnumCreatureType.CREATURE, new BiomeSettingsMobs.c(EntityTypes.STRIDER, 60, 1, 2)).b();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1484 */     BiomeSettingsGeneration.a var1 = (new BiomeSettingsGeneration.a()).a(WorldGenSurfaceComposites.c).a(StructureFeatures.E).<WorldGenFeatureConfigurationChance>a(WorldGenStage.Features.AIR, WorldGenCarvers.f).a(StructureFeatures.o).a(StructureFeatures.s).a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeDecoratorGroups.SPRING_LAVA);
/*      */     
/* 1486 */     BiomeSettings.Z(var1);
/* 1487 */     var1.a(WorldGenStage.Decoration.UNDERGROUND_DECORATION, BiomeDecoratorGroups.SPRING_OPEN)
/* 1488 */       .a(WorldGenStage.Decoration.UNDERGROUND_DECORATION, BiomeDecoratorGroups.PATCH_FIRE)
/* 1489 */       .a(WorldGenStage.Decoration.UNDERGROUND_DECORATION, BiomeDecoratorGroups.GLOWSTONE_EXTRA)
/* 1490 */       .a(WorldGenStage.Decoration.UNDERGROUND_DECORATION, BiomeDecoratorGroups.GLOWSTONE)
/* 1491 */       .a(WorldGenStage.Decoration.UNDERGROUND_DECORATION, BiomeDecoratorGroups.ORE_MAGMA)
/* 1492 */       .a(WorldGenStage.Decoration.UNDERGROUND_DECORATION, BiomeDecoratorGroups.SPRING_CLOSED)
/* 1493 */       .a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeDecoratorGroups.WEEPING_VINES)
/* 1494 */       .a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeDecoratorGroups.CRIMSON_FUNGI)
/* 1495 */       .a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeDecoratorGroups.CRIMSON_FOREST_VEGETATION);
/*      */     
/* 1497 */     BiomeSettings.ao(var1);
/*      */     
/* 1499 */     return (new BiomeBase.a())
/* 1500 */       .a(BiomeBase.Precipitation.NONE)
/* 1501 */       .a(BiomeBase.Geography.NETHER)
/* 1502 */       .a(0.1F)
/* 1503 */       .b(0.2F)
/* 1504 */       .c(2.0F)
/* 1505 */       .d(0.0F)
/* 1506 */       .a((new BiomeFog.a())
/* 1507 */         .b(4159204)
/* 1508 */         .c(329011)
/* 1509 */         .a(3343107)
/* 1510 */         .d(a(2.0F))
/* 1511 */         .a(new BiomeParticles(Particles.CRIMSON_SPORE, 0.025F))
/* 1512 */         .a(SoundEffects.AMBIENT_CRIMSON_FOREST_LOOP)
/* 1513 */         .a(new CaveSoundSettings(SoundEffects.AMBIENT_CRIMSON_FOREST_MOOD, 6000, 8, 2.0D))
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1519 */         .a(new CaveSound(SoundEffects.AMBIENT_CRIMSON_FOREST_ADDITIONS, 0.0111D))
/*      */ 
/*      */ 
/*      */         
/* 1523 */         .a(Musics.a(SoundEffects.MUSIC_NETHER_CRIMSON_FOREST))
/* 1524 */         .a())
/*      */       
/* 1526 */       .a(var0)
/* 1527 */       .a(var1.a())
/* 1528 */       .a();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static BiomeBase w() {
/* 1537 */     BiomeSettingsMobs var0 = (new BiomeSettingsMobs.a()).a(EnumCreatureType.MONSTER, new BiomeSettingsMobs.c(EntityTypes.ENDERMAN, 1, 4, 4)).a(EnumCreatureType.CREATURE, new BiomeSettingsMobs.c(EntityTypes.STRIDER, 60, 1, 2)).a(EntityTypes.ENDERMAN, 1.0D, 0.12D).b();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1545 */     BiomeSettingsGeneration.a var1 = (new BiomeSettingsGeneration.a()).a(WorldGenSurfaceComposites.v).a(StructureFeatures.o).a(StructureFeatures.s).a(StructureFeatures.E).<WorldGenFeatureConfigurationChance>a(WorldGenStage.Features.AIR, WorldGenCarvers.f).a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeDecoratorGroups.SPRING_LAVA);
/*      */     
/* 1547 */     BiomeSettings.Z(var1);
/* 1548 */     var1.a(WorldGenStage.Decoration.UNDERGROUND_DECORATION, BiomeDecoratorGroups.SPRING_OPEN)
/* 1549 */       .a(WorldGenStage.Decoration.UNDERGROUND_DECORATION, BiomeDecoratorGroups.PATCH_FIRE)
/* 1550 */       .a(WorldGenStage.Decoration.UNDERGROUND_DECORATION, BiomeDecoratorGroups.PATCH_SOUL_FIRE)
/* 1551 */       .a(WorldGenStage.Decoration.UNDERGROUND_DECORATION, BiomeDecoratorGroups.GLOWSTONE_EXTRA)
/* 1552 */       .a(WorldGenStage.Decoration.UNDERGROUND_DECORATION, BiomeDecoratorGroups.GLOWSTONE)
/* 1553 */       .a(WorldGenStage.Decoration.UNDERGROUND_DECORATION, BiomeDecoratorGroups.ORE_MAGMA)
/* 1554 */       .a(WorldGenStage.Decoration.UNDERGROUND_DECORATION, BiomeDecoratorGroups.SPRING_CLOSED)
/* 1555 */       .a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeDecoratorGroups.WARPED_FUNGI)
/* 1556 */       .a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeDecoratorGroups.WARPED_FOREST_VEGETATION)
/* 1557 */       .a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeDecoratorGroups.NETHER_SPROUTS)
/* 1558 */       .a(WorldGenStage.Decoration.VEGETAL_DECORATION, BiomeDecoratorGroups.TWISTING_VINES);
/*      */     
/* 1560 */     BiomeSettings.ao(var1);
/*      */     
/* 1562 */     return (new BiomeBase.a())
/* 1563 */       .a(BiomeBase.Precipitation.NONE)
/* 1564 */       .a(BiomeBase.Geography.NETHER)
/* 1565 */       .a(0.1F)
/* 1566 */       .b(0.2F)
/* 1567 */       .c(2.0F)
/* 1568 */       .d(0.0F)
/* 1569 */       .a((new BiomeFog.a())
/* 1570 */         .b(4159204)
/* 1571 */         .c(329011)
/* 1572 */         .a(1705242)
/* 1573 */         .d(a(2.0F))
/* 1574 */         .a(new BiomeParticles(Particles.WARPED_SPORE, 0.01428F))
/* 1575 */         .a(SoundEffects.AMBIENT_WARPED_FOREST_LOOP)
/* 1576 */         .a(new CaveSoundSettings(SoundEffects.AMBIENT_WARPED_FOREST_MOOD, 6000, 8, 2.0D))
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1582 */         .a(new CaveSound(SoundEffects.AMBIENT_WARPED_FOREST_ADDITIONS, 0.0111D))
/*      */ 
/*      */ 
/*      */         
/* 1586 */         .a(Musics.a(SoundEffects.MUSIC_NETHER_WARPED_FOREST))
/* 1587 */         .a())
/*      */       
/* 1589 */       .a(var0)
/* 1590 */       .a(var1.a())
/* 1591 */       .a();
/*      */   }
/*      */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BiomesSettingsDefault.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
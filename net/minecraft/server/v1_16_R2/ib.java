/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.ImmutableSet;
/*     */ import com.google.common.collect.Maps;
/*     */ import com.google.common.collect.Sets;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.function.BiConsumer;
/*     */ import java.util.function.Consumer;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ib
/*     */   implements Consumer<BiConsumer<MinecraftKey, LootTable.a>>
/*     */ {
/*  49 */   private static final CriterionConditionEntity.a a = CriterionConditionEntity.a.a().a(CriterionConditionEntityFlags.a.a().a(Boolean.valueOf(true)).b());
/*  50 */   private static final Set<EntityTypes<?>> b = (Set<EntityTypes<?>>)ImmutableSet.of(EntityTypes.PLAYER, EntityTypes.ARMOR_STAND, EntityTypes.IRON_GOLEM, EntityTypes.SNOW_GOLEM, EntityTypes.VILLAGER);
/*     */ 
/*     */ 
/*     */   
/*     */   private static LootTable.a a(IMaterial var0) {
/*  55 */     return LootTable.b()
/*  56 */       .a(LootSelector.a()
/*  57 */         .a(LootValueConstant.a(1))
/*  58 */         .a(LootItem.a(var0)))
/*     */       
/*  60 */       .a(LootSelector.a()
/*  61 */         .a(LootValueConstant.a(1))
/*  62 */         .a(LootSelectorLootTable.a(EntityTypes.SHEEP.i())));
/*     */   }
/*     */ 
/*     */   
/*  66 */   private final Map<MinecraftKey, LootTable.a> c = Maps.newHashMap();
/*     */ 
/*     */   
/*     */   public void accept(BiConsumer<MinecraftKey, LootTable.a> var0) {
/*  70 */     a(EntityTypes.ARMOR_STAND, 
/*  71 */         LootTable.b());
/*     */ 
/*     */     
/*  74 */     a(EntityTypes.BAT, 
/*  75 */         LootTable.b());
/*     */ 
/*     */     
/*  78 */     a(EntityTypes.BEE, 
/*  79 */         LootTable.b());
/*     */ 
/*     */     
/*  82 */     a(EntityTypes.BLAZE, 
/*  83 */         LootTable.b()
/*  84 */         .a(LootSelector.a()
/*  85 */           .a(LootValueConstant.a(1))
/*  86 */           .a(LootItem.a(Items.BLAZE_ROD).b(LootItemFunctionSetCount.a(LootValueBounds.a(0.0F, 1.0F))).b(LootEnchantFunction.a(LootValueBounds.a(0.0F, 1.0F))))
/*  87 */           .b(LootItemConditionKilledByPlayer.c())));
/*     */ 
/*     */     
/*  90 */     a(EntityTypes.CAT, 
/*  91 */         LootTable.b()
/*  92 */         .a(LootSelector.a()
/*  93 */           .a(LootValueConstant.a(1))
/*  94 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.STRING).b(LootItemFunctionSetCount.a(LootValueBounds.a(0.0F, 2.0F))))));
/*     */ 
/*     */ 
/*     */     
/*  98 */     a(EntityTypes.CAVE_SPIDER, 
/*  99 */         LootTable.b()
/* 100 */         .a(LootSelector.a()
/* 101 */           .a(LootValueConstant.a(1))
/* 102 */           .a(LootItem.a(Items.STRING).b(LootItemFunctionSetCount.a(LootValueBounds.a(0.0F, 2.0F))).b(LootEnchantFunction.a(LootValueBounds.a(0.0F, 1.0F)))))
/*     */         
/* 104 */         .a(LootSelector.a()
/* 105 */           .a(LootValueConstant.a(1))
/* 106 */           .a(LootItem.a(Items.SPIDER_EYE).b(LootItemFunctionSetCount.a(LootValueBounds.a(-1.0F, 1.0F))).b(LootEnchantFunction.a(LootValueBounds.a(0.0F, 1.0F))))
/* 107 */           .b(LootItemConditionKilledByPlayer.c())));
/*     */ 
/*     */     
/* 110 */     a(EntityTypes.CHICKEN, 
/* 111 */         LootTable.b()
/* 112 */         .a(LootSelector.a()
/* 113 */           .a(LootValueConstant.a(1))
/* 114 */           .a(LootItem.a(Items.FEATHER).b(LootItemFunctionSetCount.a(LootValueBounds.a(0.0F, 2.0F))).b(LootEnchantFunction.a(LootValueBounds.a(0.0F, 1.0F)))))
/*     */         
/* 116 */         .a(LootSelector.a()
/* 117 */           .a(LootValueConstant.a(1))
/* 118 */           .a(LootItem.a(Items.CHICKEN).b((LootItemFunction.a)LootItemFunctionSmelt.c().b(LootItemConditionEntityProperty.a(LootTableInfo.EntityTarget.THIS, a))).b(LootEnchantFunction.a(LootValueBounds.a(0.0F, 1.0F))))));
/*     */ 
/*     */ 
/*     */     
/* 122 */     a(EntityTypes.COD, 
/* 123 */         LootTable.b()
/* 124 */         .a(LootSelector.a()
/* 125 */           .a(LootValueConstant.a(1))
/* 126 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.COD).b((LootItemFunction.a)LootItemFunctionSmelt.c().b(LootItemConditionEntityProperty.a(LootTableInfo.EntityTarget.THIS, a)))))
/*     */         
/* 128 */         .a(LootSelector.a()
/* 129 */           .a(LootValueConstant.a(1))
/* 130 */           .a(LootItem.a(Items.BONE_MEAL))
/* 131 */           .b(LootItemConditionRandomChance.a(0.05F))));
/*     */ 
/*     */     
/* 134 */     a(EntityTypes.COW, 
/* 135 */         LootTable.b()
/* 136 */         .a(LootSelector.a()
/* 137 */           .a(LootValueConstant.a(1))
/* 138 */           .a(LootItem.a(Items.LEATHER).b(LootItemFunctionSetCount.a(LootValueBounds.a(0.0F, 2.0F))).b(LootEnchantFunction.a(LootValueBounds.a(0.0F, 1.0F)))))
/*     */         
/* 140 */         .a(LootSelector.a()
/* 141 */           .a(LootValueConstant.a(1))
/* 142 */           .a(LootItem.a(Items.BEEF).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 3.0F))).b((LootItemFunction.a)LootItemFunctionSmelt.c().b(LootItemConditionEntityProperty.a(LootTableInfo.EntityTarget.THIS, a))).b(LootEnchantFunction.a(LootValueBounds.a(0.0F, 1.0F))))));
/*     */ 
/*     */ 
/*     */     
/* 146 */     a(EntityTypes.CREEPER, 
/* 147 */         LootTable.b()
/* 148 */         .a(LootSelector.a()
/* 149 */           .a(LootValueConstant.a(1))
/* 150 */           .a(LootItem.a(Items.GUNPOWDER).b(LootItemFunctionSetCount.a(LootValueBounds.a(0.0F, 2.0F))).b(LootEnchantFunction.a(LootValueBounds.a(0.0F, 1.0F)))))
/*     */         
/* 152 */         .a(LootSelector.a()
/* 153 */           .a(LootSelectorTag.b(TagsItem.CREEPER_DROP_MUSIC_DISCS))
/* 154 */           .b(LootItemConditionEntityProperty.a(LootTableInfo.EntityTarget.KILLER, CriterionConditionEntity.a.a().a(TagsEntity.SKELETONS)))));
/*     */ 
/*     */     
/* 157 */     a(EntityTypes.DOLPHIN, 
/* 158 */         LootTable.b()
/* 159 */         .a(LootSelector.a()
/* 160 */           .a(LootValueConstant.a(1))
/* 161 */           .a(LootItem.a(Items.COD).b(LootItemFunctionSetCount.a(LootValueBounds.a(0.0F, 1.0F))).b(LootEnchantFunction.a(LootValueBounds.a(0.0F, 1.0F))).b((LootItemFunction.a)LootItemFunctionSmelt.c().b(LootItemConditionEntityProperty.a(LootTableInfo.EntityTarget.THIS, a))))));
/*     */ 
/*     */ 
/*     */     
/* 165 */     a(EntityTypes.DONKEY, 
/* 166 */         LootTable.b()
/* 167 */         .a(LootSelector.a()
/* 168 */           .a(LootValueConstant.a(1))
/* 169 */           .a(LootItem.a(Items.LEATHER).b(LootItemFunctionSetCount.a(LootValueBounds.a(0.0F, 2.0F))).b(LootEnchantFunction.a(LootValueBounds.a(0.0F, 1.0F))))));
/*     */ 
/*     */ 
/*     */     
/* 173 */     a(EntityTypes.DROWNED, 
/* 174 */         LootTable.b()
/* 175 */         .a(LootSelector.a()
/* 176 */           .a(LootValueConstant.a(1))
/* 177 */           .a(LootItem.a(Items.ROTTEN_FLESH).b(LootItemFunctionSetCount.a(LootValueBounds.a(0.0F, 2.0F))).b(LootEnchantFunction.a(LootValueBounds.a(0.0F, 1.0F)))))
/*     */         
/* 179 */         .a(LootSelector.a()
/* 180 */           .a(LootValueConstant.a(1))
/* 181 */           .a(LootItem.a(Items.GOLD_INGOT))
/* 182 */           .b(LootItemConditionKilledByPlayer.c())
/* 183 */           .b(LootItemConditionRandomChanceWithLooting.a(0.05F, 0.01F))));
/*     */ 
/*     */     
/* 186 */     a(EntityTypes.ELDER_GUARDIAN, 
/* 187 */         LootTable.b()
/* 188 */         .a(LootSelector.a()
/* 189 */           .a(LootValueConstant.a(1))
/* 190 */           .a(LootItem.a(Items.PRISMARINE_SHARD).b(LootItemFunctionSetCount.a(LootValueBounds.a(0.0F, 2.0F))).b(LootEnchantFunction.a(LootValueBounds.a(0.0F, 1.0F)))))
/*     */         
/* 192 */         .a(LootSelector.a()
/* 193 */           .a(LootValueConstant.a(1))
/* 194 */           .a(LootItem.a(Items.COD).a(3).b(LootEnchantFunction.a(LootValueBounds.a(0.0F, 1.0F))).b((LootItemFunction.a)LootItemFunctionSmelt.c().b(LootItemConditionEntityProperty.a(LootTableInfo.EntityTarget.THIS, a))))
/* 195 */           .a(LootItem.a(Items.PRISMARINE_CRYSTALS).a(2).b(LootEnchantFunction.a(LootValueBounds.a(0.0F, 1.0F))))
/* 196 */           .a(LootSelectorEmpty.b()))
/*     */         
/* 198 */         .a(LootSelector.a()
/* 199 */           .a(LootValueConstant.a(1))
/* 200 */           .a(LootItem.a(Blocks.WET_SPONGE))
/* 201 */           .b(LootItemConditionKilledByPlayer.c()))
/* 202 */         .a(LootSelector.a()
/* 203 */           .a(LootValueConstant.a(1))
/* 204 */           .a(LootSelectorLootTable.a(LootTables.aj))
/* 205 */           .b(LootItemConditionKilledByPlayer.c())
/* 206 */           .b(LootItemConditionRandomChanceWithLooting.a(0.025F, 0.01F))));
/*     */ 
/*     */     
/* 209 */     a(EntityTypes.ENDER_DRAGON, 
/* 210 */         LootTable.b());
/*     */ 
/*     */     
/* 213 */     a(EntityTypes.ENDERMAN, 
/* 214 */         LootTable.b()
/* 215 */         .a(LootSelector.a()
/* 216 */           .a(LootValueConstant.a(1))
/* 217 */           .a(LootItem.a(Items.ENDER_PEARL).b(LootItemFunctionSetCount.a(LootValueBounds.a(0.0F, 1.0F))).b(LootEnchantFunction.a(LootValueBounds.a(0.0F, 1.0F))))));
/*     */ 
/*     */ 
/*     */     
/* 221 */     a(EntityTypes.ENDERMITE, 
/* 222 */         LootTable.b());
/*     */ 
/*     */     
/* 225 */     a(EntityTypes.EVOKER, 
/* 226 */         LootTable.b()
/* 227 */         .a(LootSelector.a()
/* 228 */           .a(LootValueConstant.a(1))
/* 229 */           .a(LootItem.a(Items.TOTEM_OF_UNDYING)))
/*     */         
/* 231 */         .a(LootSelector.a()
/* 232 */           .a(LootValueConstant.a(1))
/* 233 */           .a(LootItem.a(Items.EMERALD).b(LootItemFunctionSetCount.a(LootValueBounds.a(0.0F, 1.0F))).b(LootEnchantFunction.a(LootValueBounds.a(0.0F, 1.0F))))
/* 234 */           .b(LootItemConditionKilledByPlayer.c())));
/*     */ 
/*     */     
/* 237 */     a(EntityTypes.FOX, 
/* 238 */         LootTable.b());
/*     */ 
/*     */     
/* 241 */     a(EntityTypes.GHAST, 
/* 242 */         LootTable.b()
/* 243 */         .a(LootSelector.a()
/* 244 */           .a(LootValueConstant.a(1))
/* 245 */           .a(LootItem.a(Items.GHAST_TEAR).b(LootItemFunctionSetCount.a(LootValueBounds.a(0.0F, 1.0F))).b(LootEnchantFunction.a(LootValueBounds.a(0.0F, 1.0F)))))
/*     */         
/* 247 */         .a(LootSelector.a()
/* 248 */           .a(LootValueConstant.a(1))
/* 249 */           .a(LootItem.a(Items.GUNPOWDER).b(LootItemFunctionSetCount.a(LootValueBounds.a(0.0F, 2.0F))).b(LootEnchantFunction.a(LootValueBounds.a(0.0F, 1.0F))))));
/*     */ 
/*     */ 
/*     */     
/* 253 */     a(EntityTypes.GIANT, 
/* 254 */         LootTable.b());
/*     */ 
/*     */     
/* 257 */     a(EntityTypes.GUARDIAN, 
/* 258 */         LootTable.b()
/* 259 */         .a(LootSelector.a()
/* 260 */           .a(LootValueConstant.a(1))
/* 261 */           .a(LootItem.a(Items.PRISMARINE_SHARD).b(LootItemFunctionSetCount.a(LootValueBounds.a(0.0F, 2.0F))).b(LootEnchantFunction.a(LootValueBounds.a(0.0F, 1.0F)))))
/*     */         
/* 263 */         .a(LootSelector.a()
/* 264 */           .a(LootValueConstant.a(1))
/* 265 */           .a(LootItem.a(Items.COD).a(2).b(LootEnchantFunction.a(LootValueBounds.a(0.0F, 1.0F))).b((LootItemFunction.a)LootItemFunctionSmelt.c().b(LootItemConditionEntityProperty.a(LootTableInfo.EntityTarget.THIS, a))))
/* 266 */           .a(LootItem.a(Items.PRISMARINE_CRYSTALS).a(2).b(LootEnchantFunction.a(LootValueBounds.a(0.0F, 1.0F))))
/* 267 */           .a(LootSelectorEmpty.b()))
/*     */         
/* 269 */         .a(LootSelector.a()
/* 270 */           .a(LootValueConstant.a(1))
/* 271 */           .a(LootSelectorLootTable.a(LootTables.aj))
/* 272 */           .b(LootItemConditionKilledByPlayer.c())
/* 273 */           .b(LootItemConditionRandomChanceWithLooting.a(0.025F, 0.01F))));
/*     */ 
/*     */     
/* 276 */     a(EntityTypes.HORSE, 
/* 277 */         LootTable.b()
/* 278 */         .a(LootSelector.a()
/* 279 */           .a(LootValueConstant.a(1))
/* 280 */           .a(LootItem.a(Items.LEATHER).b(LootItemFunctionSetCount.a(LootValueBounds.a(0.0F, 2.0F))).b(LootEnchantFunction.a(LootValueBounds.a(0.0F, 1.0F))))));
/*     */ 
/*     */ 
/*     */     
/* 284 */     a(EntityTypes.HUSK, 
/* 285 */         LootTable.b()
/* 286 */         .a(LootSelector.a()
/* 287 */           .a(LootValueConstant.a(1))
/* 288 */           .a(LootItem.a(Items.ROTTEN_FLESH).b(LootItemFunctionSetCount.a(LootValueBounds.a(0.0F, 2.0F))).b(LootEnchantFunction.a(LootValueBounds.a(0.0F, 1.0F)))))
/*     */         
/* 290 */         .a(LootSelector.a()
/* 291 */           .a(LootValueConstant.a(1))
/* 292 */           .a(LootItem.a(Items.IRON_INGOT))
/* 293 */           .a(LootItem.a(Items.CARROT))
/* 294 */           .a(LootItem.a(Items.POTATO))
/* 295 */           .b(LootItemConditionKilledByPlayer.c())
/* 296 */           .b(LootItemConditionRandomChanceWithLooting.a(0.025F, 0.01F))));
/*     */ 
/*     */     
/* 299 */     a(EntityTypes.RAVAGER, 
/* 300 */         LootTable.b()
/* 301 */         .a(LootSelector.a()
/* 302 */           .a(LootValueConstant.a(1))
/* 303 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.SADDLE).b(LootItemFunctionSetCount.a(LootValueConstant.a(1))))));
/*     */ 
/*     */ 
/*     */     
/* 307 */     a(EntityTypes.ILLUSIONER, 
/* 308 */         LootTable.b());
/*     */ 
/*     */     
/* 311 */     a(EntityTypes.IRON_GOLEM, 
/* 312 */         LootTable.b()
/* 313 */         .a(LootSelector.a()
/* 314 */           .a(LootValueConstant.a(1))
/* 315 */           .a((LootEntryAbstract.a<?>)LootItem.a(Blocks.POPPY).b(LootItemFunctionSetCount.a(LootValueBounds.a(0.0F, 2.0F)))))
/*     */         
/* 317 */         .a(LootSelector.a()
/* 318 */           .a(LootValueConstant.a(1))
/* 319 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.IRON_INGOT).b(LootItemFunctionSetCount.a(LootValueBounds.a(3.0F, 5.0F))))));
/*     */ 
/*     */ 
/*     */     
/* 323 */     a(EntityTypes.LLAMA, 
/* 324 */         LootTable.b()
/* 325 */         .a(LootSelector.a()
/* 326 */           .a(LootValueConstant.a(1))
/* 327 */           .a(LootItem.a(Items.LEATHER).b(LootItemFunctionSetCount.a(LootValueBounds.a(0.0F, 2.0F))).b(LootEnchantFunction.a(LootValueBounds.a(0.0F, 1.0F))))));
/*     */ 
/*     */ 
/*     */     
/* 331 */     a(EntityTypes.MAGMA_CUBE, 
/* 332 */         LootTable.b()
/* 333 */         .a(LootSelector.a()
/* 334 */           .a(LootValueConstant.a(1))
/* 335 */           .a(LootItem.a(Items.MAGMA_CREAM).b(LootItemFunctionSetCount.a(LootValueBounds.a(-2.0F, 1.0F))).b(LootEnchantFunction.a(LootValueBounds.a(0.0F, 1.0F))))));
/*     */ 
/*     */ 
/*     */     
/* 339 */     a(EntityTypes.MULE, 
/* 340 */         LootTable.b()
/* 341 */         .a(LootSelector.a()
/* 342 */           .a(LootValueConstant.a(1))
/* 343 */           .a(LootItem.a(Items.LEATHER).b(LootItemFunctionSetCount.a(LootValueBounds.a(0.0F, 2.0F))).b(LootEnchantFunction.a(LootValueBounds.a(0.0F, 1.0F))))));
/*     */ 
/*     */ 
/*     */     
/* 347 */     a(EntityTypes.MOOSHROOM, 
/* 348 */         LootTable.b()
/* 349 */         .a(LootSelector.a()
/* 350 */           .a(LootValueConstant.a(1))
/* 351 */           .a(LootItem.a(Items.LEATHER).b(LootItemFunctionSetCount.a(LootValueBounds.a(0.0F, 2.0F))).b(LootEnchantFunction.a(LootValueBounds.a(0.0F, 1.0F)))))
/*     */         
/* 353 */         .a(LootSelector.a()
/* 354 */           .a(LootValueConstant.a(1))
/* 355 */           .a(LootItem.a(Items.BEEF).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 3.0F))).b((LootItemFunction.a)LootItemFunctionSmelt.c().b(LootItemConditionEntityProperty.a(LootTableInfo.EntityTarget.THIS, a))).b(LootEnchantFunction.a(LootValueBounds.a(0.0F, 1.0F))))));
/*     */ 
/*     */ 
/*     */     
/* 359 */     a(EntityTypes.OCELOT, 
/* 360 */         LootTable.b());
/*     */ 
/*     */     
/* 363 */     a(EntityTypes.PANDA, 
/* 364 */         LootTable.b()
/* 365 */         .a(LootSelector.a()
/* 366 */           .a(LootValueConstant.a(1))
/* 367 */           .a((LootEntryAbstract.a<?>)LootItem.a(Blocks.BAMBOO).b(LootItemFunctionSetCount.a(LootValueConstant.a(1))))));
/*     */ 
/*     */ 
/*     */     
/* 371 */     a(EntityTypes.PARROT, 
/* 372 */         LootTable.b()
/* 373 */         .a(LootSelector.a()
/* 374 */           .a(LootValueConstant.a(1))
/* 375 */           .a(LootItem.a(Items.FEATHER).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 2.0F))).b(LootEnchantFunction.a(LootValueBounds.a(0.0F, 1.0F))))));
/*     */ 
/*     */ 
/*     */     
/* 379 */     a(EntityTypes.PHANTOM, 
/* 380 */         LootTable.b()
/* 381 */         .a(LootSelector.a()
/* 382 */           .a(LootValueConstant.a(1))
/* 383 */           .a(LootItem.a(Items.PHANTOM_MEMBRANE).b(LootItemFunctionSetCount.a(LootValueBounds.a(0.0F, 1.0F))).b(LootEnchantFunction.a(LootValueBounds.a(0.0F, 1.0F))))
/* 384 */           .b(LootItemConditionKilledByPlayer.c())));
/*     */ 
/*     */     
/* 387 */     a(EntityTypes.PIG, 
/* 388 */         LootTable.b()
/* 389 */         .a(LootSelector.a()
/* 390 */           .a(LootValueConstant.a(1))
/* 391 */           .a(LootItem.a(Items.PORKCHOP).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 3.0F))).b((LootItemFunction.a)LootItemFunctionSmelt.c().b(LootItemConditionEntityProperty.a(LootTableInfo.EntityTarget.THIS, a))).b(LootEnchantFunction.a(LootValueBounds.a(0.0F, 1.0F))))));
/*     */ 
/*     */ 
/*     */     
/* 395 */     a(EntityTypes.PILLAGER, 
/* 396 */         LootTable.b());
/*     */ 
/*     */     
/* 399 */     a(EntityTypes.PLAYER, 
/* 400 */         LootTable.b());
/*     */ 
/*     */     
/* 403 */     a(EntityTypes.POLAR_BEAR, 
/* 404 */         LootTable.b()
/* 405 */         .a(LootSelector.a()
/* 406 */           .a(LootValueConstant.a(1))
/* 407 */           .a(LootItem.a(Items.COD).a(3).b(LootItemFunctionSetCount.a(LootValueBounds.a(0.0F, 2.0F))).b(LootEnchantFunction.a(LootValueBounds.a(0.0F, 1.0F))))
/* 408 */           .a(LootItem.a(Items.SALMON).b(LootItemFunctionSetCount.a(LootValueBounds.a(0.0F, 2.0F))).b(LootEnchantFunction.a(LootValueBounds.a(0.0F, 1.0F))))));
/*     */ 
/*     */ 
/*     */     
/* 412 */     a(EntityTypes.PUFFERFISH, 
/* 413 */         LootTable.b()
/* 414 */         .a(LootSelector.a()
/* 415 */           .a(LootValueConstant.a(1))
/* 416 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.PUFFERFISH).b(LootItemFunctionSetCount.a(LootValueConstant.a(1)))))
/*     */         
/* 418 */         .a(LootSelector.a()
/* 419 */           .a(LootValueConstant.a(1))
/* 420 */           .a(LootItem.a(Items.BONE_MEAL))
/* 421 */           .b(LootItemConditionRandomChance.a(0.05F))));
/*     */ 
/*     */     
/* 424 */     a(EntityTypes.RABBIT, 
/* 425 */         LootTable.b()
/* 426 */         .a(LootSelector.a()
/* 427 */           .a(LootValueConstant.a(1))
/* 428 */           .a(LootItem.a(Items.RABBIT_HIDE).b(LootItemFunctionSetCount.a(LootValueBounds.a(0.0F, 1.0F))).b(LootEnchantFunction.a(LootValueBounds.a(0.0F, 1.0F)))))
/*     */         
/* 430 */         .a(LootSelector.a()
/* 431 */           .a(LootValueConstant.a(1))
/* 432 */           .a(LootItem.a(Items.RABBIT).b(LootItemFunctionSetCount.a(LootValueBounds.a(0.0F, 1.0F))).b((LootItemFunction.a)LootItemFunctionSmelt.c().b(LootItemConditionEntityProperty.a(LootTableInfo.EntityTarget.THIS, a))).b(LootEnchantFunction.a(LootValueBounds.a(0.0F, 1.0F)))))
/*     */         
/* 434 */         .a(LootSelector.a()
/* 435 */           .a(LootValueConstant.a(1))
/* 436 */           .a(LootItem.a(Items.RABBIT_FOOT))
/* 437 */           .b(LootItemConditionKilledByPlayer.c())
/* 438 */           .b(LootItemConditionRandomChanceWithLooting.a(0.1F, 0.03F))));
/*     */ 
/*     */     
/* 441 */     a(EntityTypes.SALMON, 
/* 442 */         LootTable.b()
/* 443 */         .a(LootSelector.a()
/* 444 */           .a(LootValueConstant.a(1))
/* 445 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.SALMON).b((LootItemFunction.a)LootItemFunctionSmelt.c().b(LootItemConditionEntityProperty.a(LootTableInfo.EntityTarget.THIS, a)))))
/*     */         
/* 447 */         .a(LootSelector.a()
/* 448 */           .a(LootValueConstant.a(1))
/* 449 */           .a(LootItem.a(Items.BONE_MEAL))
/* 450 */           .b(LootItemConditionRandomChance.a(0.05F))));
/*     */ 
/*     */     
/* 453 */     a(EntityTypes.SHEEP, 
/* 454 */         LootTable.b()
/* 455 */         .a(LootSelector.a()
/* 456 */           .a(LootValueConstant.a(1))
/* 457 */           .a(LootItem.a(Items.MUTTON).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 2.0F))).b((LootItemFunction.a)LootItemFunctionSmelt.c().b(LootItemConditionEntityProperty.a(LootTableInfo.EntityTarget.THIS, a))).b(LootEnchantFunction.a(LootValueBounds.a(0.0F, 1.0F))))));
/*     */ 
/*     */ 
/*     */     
/* 461 */     a(LootTables.af, a(Blocks.BLACK_WOOL));
/* 462 */     a(LootTables.ab, a(Blocks.BLUE_WOOL));
/* 463 */     a(LootTables.ac, a(Blocks.BROWN_WOOL));
/* 464 */     a(LootTables.Z, a(Blocks.CYAN_WOOL));
/* 465 */     a(LootTables.X, a(Blocks.GRAY_WOOL));
/* 466 */     a(LootTables.ad, a(Blocks.GREEN_WOOL));
/* 467 */     a(LootTables.T, a(Blocks.LIGHT_BLUE_WOOL));
/* 468 */     a(LootTables.Y, a(Blocks.LIGHT_GRAY_WOOL));
/* 469 */     a(LootTables.V, a(Blocks.LIME_WOOL));
/* 470 */     a(LootTables.S, a(Blocks.MAGENTA_WOOL));
/* 471 */     a(LootTables.R, a(Blocks.ORANGE_WOOL));
/* 472 */     a(LootTables.W, a(Blocks.PINK_WOOL));
/* 473 */     a(LootTables.aa, a(Blocks.PURPLE_WOOL));
/* 474 */     a(LootTables.ae, a(Blocks.RED_WOOL));
/* 475 */     a(LootTables.Q, a(Blocks.WHITE_WOOL));
/* 476 */     a(LootTables.U, a(Blocks.YELLOW_WOOL));
/*     */     
/* 478 */     a(EntityTypes.SHULKER, 
/* 479 */         LootTable.b()
/* 480 */         .a(LootSelector.a()
/* 481 */           .a(LootValueConstant.a(1))
/* 482 */           .a(LootItem.a(Items.SHULKER_SHELL))
/* 483 */           .b(LootItemConditionRandomChanceWithLooting.a(0.5F, 0.0625F))));
/*     */ 
/*     */     
/* 486 */     a(EntityTypes.SILVERFISH, 
/* 487 */         LootTable.b());
/*     */ 
/*     */     
/* 490 */     a(EntityTypes.SKELETON, 
/* 491 */         LootTable.b()
/* 492 */         .a(LootSelector.a()
/* 493 */           .a(LootValueConstant.a(1))
/* 494 */           .a(LootItem.a(Items.ARROW).b(LootItemFunctionSetCount.a(LootValueBounds.a(0.0F, 2.0F))).b(LootEnchantFunction.a(LootValueBounds.a(0.0F, 1.0F)))))
/*     */         
/* 496 */         .a(LootSelector.a()
/* 497 */           .a(LootValueConstant.a(1))
/* 498 */           .a(LootItem.a(Items.BONE).b(LootItemFunctionSetCount.a(LootValueBounds.a(0.0F, 2.0F))).b(LootEnchantFunction.a(LootValueBounds.a(0.0F, 1.0F))))));
/*     */ 
/*     */ 
/*     */     
/* 502 */     a(EntityTypes.SKELETON_HORSE, 
/* 503 */         LootTable.b()
/* 504 */         .a(LootSelector.a()
/* 505 */           .a(LootValueConstant.a(1))
/* 506 */           .a(LootItem.a(Items.BONE).b(LootItemFunctionSetCount.a(LootValueBounds.a(0.0F, 2.0F))).b(LootEnchantFunction.a(LootValueBounds.a(0.0F, 1.0F))))));
/*     */ 
/*     */ 
/*     */     
/* 510 */     a(EntityTypes.SLIME, 
/* 511 */         LootTable.b()
/* 512 */         .a(LootSelector.a()
/* 513 */           .a(LootValueConstant.a(1))
/* 514 */           .a(LootItem.a(Items.SLIME_BALL).b(LootItemFunctionSetCount.a(LootValueBounds.a(0.0F, 2.0F))).b(LootEnchantFunction.a(LootValueBounds.a(0.0F, 1.0F))))));
/*     */ 
/*     */ 
/*     */     
/* 518 */     a(EntityTypes.SNOW_GOLEM, 
/* 519 */         LootTable.b()
/* 520 */         .a(LootSelector.a()
/* 521 */           .a(LootValueConstant.a(1))
/* 522 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.SNOWBALL).b(LootItemFunctionSetCount.a(LootValueBounds.a(0.0F, 15.0F))))));
/*     */ 
/*     */ 
/*     */     
/* 526 */     a(EntityTypes.SPIDER, 
/* 527 */         LootTable.b()
/* 528 */         .a(LootSelector.a()
/* 529 */           .a(LootValueConstant.a(1))
/* 530 */           .a(LootItem.a(Items.STRING).b(LootItemFunctionSetCount.a(LootValueBounds.a(0.0F, 2.0F))).b(LootEnchantFunction.a(LootValueBounds.a(0.0F, 1.0F)))))
/*     */         
/* 532 */         .a(LootSelector.a()
/* 533 */           .a(LootValueConstant.a(1))
/* 534 */           .a(LootItem.a(Items.SPIDER_EYE).b(LootItemFunctionSetCount.a(LootValueBounds.a(-1.0F, 1.0F))).b(LootEnchantFunction.a(LootValueBounds.a(0.0F, 1.0F))))
/* 535 */           .b(LootItemConditionKilledByPlayer.c())));
/*     */ 
/*     */     
/* 538 */     a(EntityTypes.SQUID, 
/* 539 */         LootTable.b()
/* 540 */         .a(LootSelector.a()
/* 541 */           .a(LootValueConstant.a(1))
/* 542 */           .a(LootItem.a(Items.INK_SAC).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 3.0F))).b(LootEnchantFunction.a(LootValueBounds.a(0.0F, 1.0F))))));
/*     */ 
/*     */ 
/*     */     
/* 546 */     a(EntityTypes.STRAY, 
/* 547 */         LootTable.b()
/* 548 */         .a(LootSelector.a()
/* 549 */           .a(LootValueConstant.a(1))
/* 550 */           .a(LootItem.a(Items.ARROW).b(LootItemFunctionSetCount.a(LootValueBounds.a(0.0F, 2.0F))).b(LootEnchantFunction.a(LootValueBounds.a(0.0F, 1.0F)))))
/*     */         
/* 552 */         .a(LootSelector.a()
/* 553 */           .a(LootValueConstant.a(1))
/* 554 */           .a(LootItem.a(Items.BONE).b(LootItemFunctionSetCount.a(LootValueBounds.a(0.0F, 2.0F))).b(LootEnchantFunction.a(LootValueBounds.a(0.0F, 1.0F)))))
/*     */         
/* 556 */         .a(LootSelector.a()
/* 557 */           .a(LootValueConstant.a(1))
/* 558 */           .a(LootItem.a(Items.TIPPED_ARROW).b(LootItemFunctionSetCount.a(LootValueBounds.a(0.0F, 1.0F))).b(LootEnchantFunction.a(LootValueBounds.a(0.0F, 1.0F)).a(1)).b(LootItemFunctionSetTag.a(SystemUtils.<NBTTagCompound>a(new NBTTagCompound(), var0 -> var0.setString("Potion", "minecraft:slowness")))))
/* 559 */           .b(LootItemConditionKilledByPlayer.c())));
/*     */ 
/*     */     
/* 562 */     a(EntityTypes.STRIDER, 
/* 563 */         LootTable.b()
/* 564 */         .a(LootSelector.a()
/* 565 */           .a(LootValueConstant.a(1))
/* 566 */           .a(LootItem.a(Items.STRING).b(LootItemFunctionSetCount.a(LootValueBounds.a(2.0F, 5.0F))).b(LootEnchantFunction.a(LootValueBounds.a(0.0F, 1.0F))))));
/*     */ 
/*     */ 
/*     */     
/* 570 */     a(EntityTypes.TRADER_LLAMA, 
/* 571 */         LootTable.b()
/* 572 */         .a(LootSelector.a()
/* 573 */           .a(LootValueConstant.a(1))
/* 574 */           .a(LootItem.a(Items.LEATHER).b(LootItemFunctionSetCount.a(LootValueBounds.a(0.0F, 2.0F))).b(LootEnchantFunction.a(LootValueBounds.a(0.0F, 1.0F))))));
/*     */ 
/*     */ 
/*     */     
/* 578 */     a(EntityTypes.TROPICAL_FISH, 
/* 579 */         LootTable.b()
/* 580 */         .a(LootSelector.a()
/* 581 */           .a(LootValueConstant.a(1))
/* 582 */           .a((LootEntryAbstract.a<?>)LootItem.a(Items.TROPICAL_FISH).b(LootItemFunctionSetCount.a(LootValueConstant.a(1)))))
/*     */         
/* 584 */         .a(LootSelector.a()
/* 585 */           .a(LootValueConstant.a(1))
/* 586 */           .a(LootItem.a(Items.BONE_MEAL))
/* 587 */           .b(LootItemConditionRandomChance.a(0.05F))));
/*     */ 
/*     */     
/* 590 */     a(EntityTypes.TURTLE, 
/* 591 */         LootTable.b()
/* 592 */         .a(LootSelector.a()
/* 593 */           .a(LootValueConstant.a(1))
/* 594 */           .a(LootItem.a(Blocks.SEAGRASS).a(3).b(LootItemFunctionSetCount.a(LootValueBounds.a(0.0F, 2.0F))).b(LootEnchantFunction.a(LootValueBounds.a(0.0F, 1.0F)))))
/*     */         
/* 596 */         .a(LootSelector.a()
/* 597 */           .a(LootValueConstant.a(1))
/* 598 */           .a(LootItem.a(Items.BOWL))
/* 599 */           .b(LootItemConditionDamageSourceProperties.a(CriterionConditionDamageSource.a.a().h(Boolean.valueOf(true))))));
/*     */ 
/*     */ 
/*     */     
/* 603 */     a(EntityTypes.VEX, 
/* 604 */         LootTable.b());
/*     */ 
/*     */     
/* 607 */     a(EntityTypes.VILLAGER, 
/* 608 */         LootTable.b());
/*     */ 
/*     */     
/* 611 */     a(EntityTypes.WANDERING_TRADER, 
/* 612 */         LootTable.b());
/*     */ 
/*     */     
/* 615 */     a(EntityTypes.VINDICATOR, 
/* 616 */         LootTable.b()
/* 617 */         .a(LootSelector.a()
/* 618 */           .a(LootValueConstant.a(1))
/* 619 */           .a(LootItem.a(Items.EMERALD).b(LootItemFunctionSetCount.a(LootValueBounds.a(0.0F, 1.0F))).b(LootEnchantFunction.a(LootValueBounds.a(0.0F, 1.0F))))
/* 620 */           .b(LootItemConditionKilledByPlayer.c())));
/*     */ 
/*     */     
/* 623 */     a(EntityTypes.WITCH, 
/* 624 */         LootTable.b()
/* 625 */         .a(LootSelector.a()
/* 626 */           .a(LootValueBounds.a(1.0F, 3.0F))
/* 627 */           .a(LootItem.a(Items.GLOWSTONE_DUST).b(LootItemFunctionSetCount.a(LootValueBounds.a(0.0F, 2.0F))).b(LootEnchantFunction.a(LootValueBounds.a(0.0F, 1.0F))))
/* 628 */           .a(LootItem.a(Items.SUGAR).b(LootItemFunctionSetCount.a(LootValueBounds.a(0.0F, 2.0F))).b(LootEnchantFunction.a(LootValueBounds.a(0.0F, 1.0F))))
/* 629 */           .a(LootItem.a(Items.REDSTONE).b(LootItemFunctionSetCount.a(LootValueBounds.a(0.0F, 2.0F))).b(LootEnchantFunction.a(LootValueBounds.a(0.0F, 1.0F))))
/* 630 */           .a(LootItem.a(Items.SPIDER_EYE).b(LootItemFunctionSetCount.a(LootValueBounds.a(0.0F, 2.0F))).b(LootEnchantFunction.a(LootValueBounds.a(0.0F, 1.0F))))
/* 631 */           .a(LootItem.a(Items.GLASS_BOTTLE).b(LootItemFunctionSetCount.a(LootValueBounds.a(0.0F, 2.0F))).b(LootEnchantFunction.a(LootValueBounds.a(0.0F, 1.0F))))
/* 632 */           .a(LootItem.a(Items.GUNPOWDER).b(LootItemFunctionSetCount.a(LootValueBounds.a(0.0F, 2.0F))).b(LootEnchantFunction.a(LootValueBounds.a(0.0F, 1.0F))))
/* 633 */           .a(LootItem.a(Items.STICK).a(2).b(LootItemFunctionSetCount.a(LootValueBounds.a(0.0F, 2.0F))).b(LootEnchantFunction.a(LootValueBounds.a(0.0F, 1.0F))))));
/*     */ 
/*     */ 
/*     */     
/* 637 */     a(EntityTypes.WITHER, 
/* 638 */         LootTable.b());
/*     */ 
/*     */     
/* 641 */     a(EntityTypes.WITHER_SKELETON, 
/* 642 */         LootTable.b()
/* 643 */         .a(LootSelector.a()
/* 644 */           .a(LootValueConstant.a(1))
/* 645 */           .a(LootItem.a(Items.COAL).b(LootItemFunctionSetCount.a(LootValueBounds.a(-1.0F, 1.0F))).b(LootEnchantFunction.a(LootValueBounds.a(0.0F, 1.0F)))))
/*     */         
/* 647 */         .a(LootSelector.a()
/* 648 */           .a(LootValueConstant.a(1))
/* 649 */           .a(LootItem.a(Items.BONE).b(LootItemFunctionSetCount.a(LootValueBounds.a(0.0F, 2.0F))).b(LootEnchantFunction.a(LootValueBounds.a(0.0F, 1.0F)))))
/*     */         
/* 651 */         .a(LootSelector.a()
/* 652 */           .a(LootValueConstant.a(1))
/* 653 */           .a(LootItem.a(Blocks.WITHER_SKELETON_SKULL))
/* 654 */           .b(LootItemConditionKilledByPlayer.c())
/* 655 */           .b(LootItemConditionRandomChanceWithLooting.a(0.025F, 0.01F))));
/*     */ 
/*     */     
/* 658 */     a(EntityTypes.WOLF, 
/* 659 */         LootTable.b());
/*     */ 
/*     */     
/* 662 */     a(EntityTypes.ZOGLIN, 
/* 663 */         LootTable.b()
/* 664 */         .a(LootSelector.a()
/* 665 */           .a(LootValueConstant.a(1))
/* 666 */           .a(LootItem.a(Items.ROTTEN_FLESH).b(LootItemFunctionSetCount.a(LootValueBounds.a(1.0F, 3.0F))).b(LootEnchantFunction.a(LootValueBounds.a(0.0F, 1.0F))))));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 671 */     a(EntityTypes.ZOMBIE, 
/* 672 */         LootTable.b()
/* 673 */         .a(LootSelector.a()
/* 674 */           .a(LootValueConstant.a(1))
/* 675 */           .a(LootItem.a(Items.ROTTEN_FLESH).b(LootItemFunctionSetCount.a(LootValueBounds.a(0.0F, 2.0F))).b(LootEnchantFunction.a(LootValueBounds.a(0.0F, 1.0F)))))
/*     */         
/* 677 */         .a(LootSelector.a()
/* 678 */           .a(LootValueConstant.a(1))
/* 679 */           .a(LootItem.a(Items.IRON_INGOT))
/* 680 */           .a(LootItem.a(Items.CARROT))
/* 681 */           .a(LootItem.a(Items.POTATO))
/* 682 */           .b(LootItemConditionKilledByPlayer.c())
/* 683 */           .b(LootItemConditionRandomChanceWithLooting.a(0.025F, 0.01F))));
/*     */ 
/*     */     
/* 686 */     a(EntityTypes.ZOMBIE_HORSE, 
/* 687 */         LootTable.b()
/* 688 */         .a(LootSelector.a()
/* 689 */           .a(LootValueConstant.a(1))
/* 690 */           .a(LootItem.a(Items.ROTTEN_FLESH).b(LootItemFunctionSetCount.a(LootValueBounds.a(0.0F, 2.0F))).b(LootEnchantFunction.a(LootValueBounds.a(0.0F, 1.0F))))));
/*     */ 
/*     */ 
/*     */     
/* 694 */     a(EntityTypes.ZOMBIFIED_PIGLIN, 
/* 695 */         LootTable.b()
/* 696 */         .a(LootSelector.a()
/* 697 */           .a(LootValueConstant.a(1))
/* 698 */           .a(LootItem.a(Items.ROTTEN_FLESH).b(LootItemFunctionSetCount.a(LootValueBounds.a(0.0F, 1.0F))).b(LootEnchantFunction.a(LootValueBounds.a(0.0F, 1.0F)))))
/*     */         
/* 700 */         .a(LootSelector.a()
/* 701 */           .a(LootValueConstant.a(1))
/* 702 */           .a(LootItem.a(Items.GOLD_NUGGET).b(LootItemFunctionSetCount.a(LootValueBounds.a(0.0F, 1.0F))).b(LootEnchantFunction.a(LootValueBounds.a(0.0F, 1.0F)))))
/*     */         
/* 704 */         .a(LootSelector.a()
/* 705 */           .a(LootValueConstant.a(1))
/* 706 */           .a(LootItem.a(Items.GOLD_INGOT))
/* 707 */           .b(LootItemConditionKilledByPlayer.c())
/* 708 */           .b(LootItemConditionRandomChanceWithLooting.a(0.025F, 0.01F))));
/*     */ 
/*     */     
/* 711 */     a(EntityTypes.HOGLIN, 
/* 712 */         LootTable.b()
/* 713 */         .a(LootSelector.a()
/* 714 */           .a(LootValueConstant.a(1))
/* 715 */           .a(LootItem.a(Items.PORKCHOP).b(LootItemFunctionSetCount.a(LootValueBounds.a(2.0F, 4.0F))).b((LootItemFunction.a)LootItemFunctionSmelt.c().b(LootItemConditionEntityProperty.a(LootTableInfo.EntityTarget.THIS, a))).b(LootEnchantFunction.a(LootValueBounds.a(0.0F, 1.0F)))))
/*     */         
/* 717 */         .a(LootSelector.a()
/* 718 */           .a(LootValueConstant.a(1))
/* 719 */           .a(LootItem.a(Items.LEATHER).b(LootItemFunctionSetCount.a(LootValueBounds.a(0.0F, 1.0F))).b(LootEnchantFunction.a(LootValueBounds.a(0.0F, 1.0F))))));
/*     */ 
/*     */ 
/*     */     
/* 723 */     a(EntityTypes.PIGLIN, 
/* 724 */         LootTable.b());
/*     */ 
/*     */     
/* 727 */     a(EntityTypes.PIGLIN_BRUTE, 
/* 728 */         LootTable.b());
/*     */ 
/*     */     
/* 731 */     a(EntityTypes.ZOMBIE_VILLAGER, 
/* 732 */         LootTable.b()
/* 733 */         .a(LootSelector.a()
/* 734 */           .a(LootValueConstant.a(1))
/* 735 */           .a(LootItem.a(Items.ROTTEN_FLESH).b(LootItemFunctionSetCount.a(LootValueBounds.a(0.0F, 2.0F))).b(LootEnchantFunction.a(LootValueBounds.a(0.0F, 1.0F)))))
/*     */         
/* 737 */         .a(LootSelector.a()
/* 738 */           .a(LootValueConstant.a(1))
/* 739 */           .a(LootItem.a(Items.IRON_INGOT))
/* 740 */           .a(LootItem.a(Items.CARROT))
/* 741 */           .a(LootItem.a(Items.POTATO))
/* 742 */           .b(LootItemConditionKilledByPlayer.c())
/* 743 */           .b(LootItemConditionRandomChanceWithLooting.a(0.025F, 0.01F))));
/*     */ 
/*     */     
/* 746 */     Set<MinecraftKey> var1 = Sets.newHashSet();
/* 747 */     for (EntityTypes<?> var3 : IRegistry.ENTITY_TYPE) {
/* 748 */       MinecraftKey var4 = var3.i();
/* 749 */       if (b.contains(var3) || var3.e() != EnumCreatureType.MISC) {
/* 750 */         if (var4 != LootTables.a && var1.add(var4)) {
/* 751 */           LootTable.a var5 = this.c.remove(var4);
/* 752 */           if (var5 == null) {
/* 753 */             throw new IllegalStateException(String.format("Missing loottable '%s' for '%s'", new Object[] { var4, IRegistry.ENTITY_TYPE.getKey(var3) }));
/*     */           }
/* 755 */           var0.accept(var4, var5);
/*     */         }  continue;
/*     */       } 
/* 758 */       if (var4 != LootTables.a && this.c.remove(var4) != null) {
/* 759 */         throw new IllegalStateException(String.format("Weird loottable '%s' for '%s', not a LivingEntity so should not have loot", new Object[] { var4, IRegistry.ENTITY_TYPE.getKey(var3) }));
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 764 */     this.c.forEach(var0::accept);
/*     */   }
/*     */   
/*     */   private void a(EntityTypes<?> var0, LootTable.a var1) {
/* 768 */     a(var0.i(), var1);
/*     */   }
/*     */   
/*     */   private void a(MinecraftKey var0, LootTable.a var1) {
/* 772 */     this.c.put(var0, var1);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ib.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
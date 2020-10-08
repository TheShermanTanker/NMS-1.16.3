/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ import java.util.Set;
/*     */ import java.util.TreeSet;
/*     */ import java.util.function.Function;
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
/*     */ public class DispenserRegistry
/*     */ {
/*  22 */   public static final PrintStream a = System.out;
/*     */   private static boolean b;
/*  24 */   private static final Logger LOGGER = LogManager.getLogger();
/*     */   
/*     */   public static void init() {
/*  27 */     if (!b) {
/*  28 */       b = true;
/*  29 */       if (IRegistry.f.keySet().isEmpty()) {
/*  30 */         throw new IllegalStateException("Unable to load registries");
/*     */       }
/*  32 */       BlockFire.c();
/*  33 */       BlockComposter.c();
/*  34 */       if (EntityTypes.getName(EntityTypes.PLAYER) == null) {
/*  35 */         throw new IllegalStateException("Failed loading EntityTypes");
/*     */       }
/*  37 */       PotionBrewer.a();
/*  38 */       PlayerSelector.a();
/*  39 */       IDispenseBehavior.c();
/*  40 */       ArgumentRegistry.a();
/*  41 */       TagStatic.b();
/*  42 */       d();
/*     */ 
/*     */       
/*  45 */       DataConverterFlattenData.map(1008, "{Name:'minecraft:oak_sign',Properties:{rotation:'0'}}", new String[] { "{Name:'minecraft:standing_sign',Properties:{rotation:'0'}}" });
/*  46 */       DataConverterFlattenData.map(1009, "{Name:'minecraft:oak_sign',Properties:{rotation:'1'}}", new String[] { "{Name:'minecraft:standing_sign',Properties:{rotation:'1'}}" });
/*  47 */       DataConverterFlattenData.map(1010, "{Name:'minecraft:oak_sign',Properties:{rotation:'2'}}", new String[] { "{Name:'minecraft:standing_sign',Properties:{rotation:'2'}}" });
/*  48 */       DataConverterFlattenData.map(1011, "{Name:'minecraft:oak_sign',Properties:{rotation:'3'}}", new String[] { "{Name:'minecraft:standing_sign',Properties:{rotation:'3'}}" });
/*  49 */       DataConverterFlattenData.map(1012, "{Name:'minecraft:oak_sign',Properties:{rotation:'4'}}", new String[] { "{Name:'minecraft:standing_sign',Properties:{rotation:'4'}}" });
/*  50 */       DataConverterFlattenData.map(1013, "{Name:'minecraft:oak_sign',Properties:{rotation:'5'}}", new String[] { "{Name:'minecraft:standing_sign',Properties:{rotation:'5'}}" });
/*  51 */       DataConverterFlattenData.map(1014, "{Name:'minecraft:oak_sign',Properties:{rotation:'6'}}", new String[] { "{Name:'minecraft:standing_sign',Properties:{rotation:'6'}}" });
/*  52 */       DataConverterFlattenData.map(1015, "{Name:'minecraft:oak_sign',Properties:{rotation:'7'}}", new String[] { "{Name:'minecraft:standing_sign',Properties:{rotation:'7'}}" });
/*  53 */       DataConverterFlattenData.map(1016, "{Name:'minecraft:oak_sign',Properties:{rotation:'8'}}", new String[] { "{Name:'minecraft:standing_sign',Properties:{rotation:'8'}}" });
/*  54 */       DataConverterFlattenData.map(1017, "{Name:'minecraft:oak_sign',Properties:{rotation:'9'}}", new String[] { "{Name:'minecraft:standing_sign',Properties:{rotation:'9'}}" });
/*  55 */       DataConverterFlattenData.map(1018, "{Name:'minecraft:oak_sign',Properties:{rotation:'10'}}", new String[] { "{Name:'minecraft:standing_sign',Properties:{rotation:'10'}}" });
/*  56 */       DataConverterFlattenData.map(1019, "{Name:'minecraft:oak_sign',Properties:{rotation:'11'}}", new String[] { "{Name:'minecraft:standing_sign',Properties:{rotation:'11'}}" });
/*  57 */       DataConverterFlattenData.map(1020, "{Name:'minecraft:oak_sign',Properties:{rotation:'12'}}", new String[] { "{Name:'minecraft:standing_sign',Properties:{rotation:'12'}}" });
/*  58 */       DataConverterFlattenData.map(1021, "{Name:'minecraft:oak_sign',Properties:{rotation:'13'}}", new String[] { "{Name:'minecraft:standing_sign',Properties:{rotation:'13'}}" });
/*  59 */       DataConverterFlattenData.map(1022, "{Name:'minecraft:oak_sign',Properties:{rotation:'14'}}", new String[] { "{Name:'minecraft:standing_sign',Properties:{rotation:'14'}}" });
/*  60 */       DataConverterFlattenData.map(1023, "{Name:'minecraft:oak_sign',Properties:{rotation:'15'}}", new String[] { "{Name:'minecraft:standing_sign',Properties:{rotation:'15'}}" });
/*  61 */       DataConverterMaterialId.ID_MAPPING.put(323, "minecraft:oak_sign");
/*     */       
/*  63 */       DataConverterFlattenData.map(1440, "{Name:'minecraft:portal',Properties:{axis:'x'}}", new String[] { "{Name:'minecraft:portal',Properties:{axis:'x'}}" });
/*     */       
/*  65 */       DataConverterMaterialId.ID_MAPPING.put(409, "minecraft:prismarine_shard");
/*  66 */       DataConverterMaterialId.ID_MAPPING.put(410, "minecraft:prismarine_crystals");
/*  67 */       DataConverterMaterialId.ID_MAPPING.put(411, "minecraft:rabbit");
/*  68 */       DataConverterMaterialId.ID_MAPPING.put(412, "minecraft:cooked_rabbit");
/*  69 */       DataConverterMaterialId.ID_MAPPING.put(413, "minecraft:rabbit_stew");
/*  70 */       DataConverterMaterialId.ID_MAPPING.put(414, "minecraft:rabbit_foot");
/*  71 */       DataConverterMaterialId.ID_MAPPING.put(415, "minecraft:rabbit_hide");
/*  72 */       DataConverterMaterialId.ID_MAPPING.put(416, "minecraft:armor_stand");
/*     */       
/*  74 */       DataConverterMaterialId.ID_MAPPING.put(423, "minecraft:mutton");
/*  75 */       DataConverterMaterialId.ID_MAPPING.put(424, "minecraft:cooked_mutton");
/*  76 */       DataConverterMaterialId.ID_MAPPING.put(425, "minecraft:banner");
/*  77 */       DataConverterMaterialId.ID_MAPPING.put(426, "minecraft:end_crystal");
/*  78 */       DataConverterMaterialId.ID_MAPPING.put(427, "minecraft:spruce_door");
/*  79 */       DataConverterMaterialId.ID_MAPPING.put(428, "minecraft:birch_door");
/*  80 */       DataConverterMaterialId.ID_MAPPING.put(429, "minecraft:jungle_door");
/*  81 */       DataConverterMaterialId.ID_MAPPING.put(430, "minecraft:acacia_door");
/*  82 */       DataConverterMaterialId.ID_MAPPING.put(431, "minecraft:dark_oak_door");
/*  83 */       DataConverterMaterialId.ID_MAPPING.put(432, "minecraft:chorus_fruit");
/*  84 */       DataConverterMaterialId.ID_MAPPING.put(433, "minecraft:chorus_fruit_popped");
/*  85 */       DataConverterMaterialId.ID_MAPPING.put(434, "minecraft:beetroot");
/*  86 */       DataConverterMaterialId.ID_MAPPING.put(435, "minecraft:beetroot_seeds");
/*  87 */       DataConverterMaterialId.ID_MAPPING.put(436, "minecraft:beetroot_soup");
/*  88 */       DataConverterMaterialId.ID_MAPPING.put(437, "minecraft:dragon_breath");
/*  89 */       DataConverterMaterialId.ID_MAPPING.put(438, "minecraft:splash_potion");
/*  90 */       DataConverterMaterialId.ID_MAPPING.put(439, "minecraft:spectral_arrow");
/*  91 */       DataConverterMaterialId.ID_MAPPING.put(440, "minecraft:tipped_arrow");
/*  92 */       DataConverterMaterialId.ID_MAPPING.put(441, "minecraft:lingering_potion");
/*  93 */       DataConverterMaterialId.ID_MAPPING.put(442, "minecraft:shield");
/*  94 */       DataConverterMaterialId.ID_MAPPING.put(443, "minecraft:elytra");
/*  95 */       DataConverterMaterialId.ID_MAPPING.put(444, "minecraft:spruce_boat");
/*  96 */       DataConverterMaterialId.ID_MAPPING.put(445, "minecraft:birch_boat");
/*  97 */       DataConverterMaterialId.ID_MAPPING.put(446, "minecraft:jungle_boat");
/*  98 */       DataConverterMaterialId.ID_MAPPING.put(447, "minecraft:acacia_boat");
/*  99 */       DataConverterMaterialId.ID_MAPPING.put(448, "minecraft:dark_oak_boat");
/* 100 */       DataConverterMaterialId.ID_MAPPING.put(449, "minecraft:totem_of_undying");
/* 101 */       DataConverterMaterialId.ID_MAPPING.put(450, "minecraft:shulker_shell");
/* 102 */       DataConverterMaterialId.ID_MAPPING.put(452, "minecraft:iron_nugget");
/* 103 */       DataConverterMaterialId.ID_MAPPING.put(453, "minecraft:knowledge_book");
/*     */       
/* 105 */       DataConverterSpawnEgg.ID_MAPPING[23] = "Arrow";
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static <T> void a(Iterable<T> iterable, Function<T, String> function, Set<String> set) {
/* 112 */     LocaleLanguage localelanguage = LocaleLanguage.a();
/*     */     
/* 114 */     iterable.forEach(object -> {
/*     */           String s = function.apply(object);
/*     */           if (!localelanguage.b(s)) {
/*     */             set.add(s);
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static void a(final Set<String> set) {
/* 125 */     final LocaleLanguage localelanguage = LocaleLanguage.a();
/*     */     
/* 127 */     GameRules.a(new GameRules.GameRuleVisitor()
/*     */         {
/*     */           public <T extends GameRules.GameRuleValue<T>> void a(GameRules.GameRuleKey<T> gamerules_gamerulekey, GameRules.GameRuleDefinition<T> gamerules_gameruledefinition) {
/* 130 */             if (!localelanguage.b(gamerules_gamerulekey.b())) {
/* 131 */               set.add(gamerules_gamerulekey.a());
/*     */             }
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   public static Set<String> b() {
/* 139 */     Set<String> set = new TreeSet<>();
/*     */     
/* 141 */     a(IRegistry.ATTRIBUTE, AttributeBase::getName, set);
/* 142 */     a(IRegistry.ENTITY_TYPE, EntityTypes::f, set);
/* 143 */     a(IRegistry.MOB_EFFECT, MobEffectList::c, set);
/* 144 */     a(IRegistry.ITEM, Item::getName, set);
/* 145 */     a(IRegistry.ENCHANTMENT, Enchantment::g, set);
/* 146 */     a(IRegistry.BLOCK, Block::i, set);
/* 147 */     a(IRegistry.CUSTOM_STAT, minecraftkey -> "stat." + minecraftkey.toString().replace(':', '.'), set);
/*     */ 
/*     */     
/* 150 */     a(set);
/* 151 */     return set;
/*     */   }
/*     */   
/*     */   public static void c() {
/* 155 */     if (!b) {
/* 156 */       throw new IllegalArgumentException("Not bootstrapped");
/*     */     }
/* 158 */     if (SharedConstants.d) {
/* 159 */       b().forEach(s -> LOGGER.error("Missing translations: " + s));
/*     */ 
/*     */       
/* 162 */       CommandDispatcher.b();
/*     */     } 
/*     */     
/* 165 */     AttributeDefaults.a();
/*     */   }
/*     */ 
/*     */   
/*     */   private static void d() {
/* 170 */     if (LOGGER.isDebugEnabled()) {
/* 171 */       System.setErr(new DebugOutputStream("STDERR", System.err));
/* 172 */       System.setOut(new DebugOutputStream("STDOUT", a));
/*     */     } else {
/* 174 */       System.setErr(new RedirectStream("STDERR", System.err));
/* 175 */       System.setOut(new RedirectStream("STDOUT", a));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void a(String s) {
/* 181 */     a.println(s);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DispenserRegistry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
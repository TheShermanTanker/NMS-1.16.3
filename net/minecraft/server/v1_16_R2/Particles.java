/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.mojang.serialization.Codec;
/*     */ import java.util.function.Function;
/*     */ 
/*     */ 
/*     */ public class Particles
/*     */ {
/*   9 */   public static final ParticleType AMBIENT_ENTITY_EFFECT = a("ambient_entity_effect", false);
/*  10 */   public static final ParticleType ANGRY_VILLAGER = a("angry_villager", false);
/*  11 */   public static final ParticleType BARRIER = a("barrier", false);
/*  12 */   public static final Particle<ParticleParamBlock> BLOCK = a("block", ParticleParamBlock.a, ParticleParamBlock::a);
/*  13 */   public static final ParticleType BUBBLE = a("bubble", false);
/*  14 */   public static final ParticleType CLOUD = a("cloud", false);
/*  15 */   public static final ParticleType CRIT = a("crit", false);
/*  16 */   public static final ParticleType DAMAGE_INDICATOR = a("damage_indicator", true);
/*  17 */   public static final ParticleType DRAGON_BREATH = a("dragon_breath", false);
/*  18 */   public static final ParticleType DRIPPING_LAVA = a("dripping_lava", false);
/*  19 */   public static final ParticleType FALLING_LAVA = a("falling_lava", false);
/*  20 */   public static final ParticleType LANDING_LAVA = a("landing_lava", false);
/*  21 */   public static final ParticleType DRIPPING_WATER = a("dripping_water", false);
/*  22 */   public static final ParticleType FALLING_WATER = a("falling_water", false);
/*  23 */   public static final Particle<ParticleParamRedstone> DUST = a("dust", ParticleParamRedstone.c, var0 -> ParticleParamRedstone.b);
/*  24 */   public static final ParticleType EFFECT = a("effect", false);
/*  25 */   public static final ParticleType ELDER_GUARDIAN = a("elder_guardian", true);
/*  26 */   public static final ParticleType ENCHANTED_HIT = a("enchanted_hit", false);
/*  27 */   public static final ParticleType ENCHANT = a("enchant", false);
/*  28 */   public static final ParticleType END_ROD = a("end_rod", false);
/*  29 */   public static final ParticleType ENTITY_EFFECT = a("entity_effect", false);
/*  30 */   public static final ParticleType EXPLOSION_EMITTER = a("explosion_emitter", true);
/*  31 */   public static final ParticleType EXPLOSION = a("explosion", true);
/*  32 */   public static final Particle<ParticleParamBlock> FALLING_DUST = a("falling_dust", ParticleParamBlock.a, ParticleParamBlock::a);
/*  33 */   public static final ParticleType FIREWORK = a("firework", false);
/*  34 */   public static final ParticleType FISHING = a("fishing", false);
/*  35 */   public static final ParticleType FLAME = a("flame", false);
/*  36 */   public static final ParticleType SOUL_FIRE_FLAME = a("soul_fire_flame", false);
/*  37 */   public static final ParticleType SOUL = a("soul", false);
/*  38 */   public static final ParticleType FLASH = a("flash", false);
/*  39 */   public static final ParticleType HAPPY_VILLAGER = a("happy_villager", false);
/*  40 */   public static final ParticleType COMPOSTER = a("composter", false);
/*  41 */   public static final ParticleType HEART = a("heart", false);
/*  42 */   public static final ParticleType INSTANT_EFFECT = a("instant_effect", false);
/*  43 */   public static final Particle<ParticleParamItem> ITEM = a("item", ParticleParamItem.a, ParticleParamItem::a);
/*     */   
/*  45 */   public static final ParticleType ITEM_SLIME = a("item_slime", false);
/*  46 */   public static final ParticleType ITEM_SNOWBALL = a("item_snowball", false);
/*  47 */   public static final ParticleType LARGE_SMOKE = a("large_smoke", false);
/*  48 */   public static final ParticleType LAVA = a("lava", false);
/*  49 */   public static final ParticleType MYCELIUM = a("mycelium", false);
/*  50 */   public static final ParticleType NOTE = a("note", false);
/*  51 */   public static final ParticleType POOF = a("poof", true);
/*  52 */   public static final ParticleType PORTAL = a("portal", false);
/*  53 */   public static final ParticleType RAIN = a("rain", false);
/*  54 */   public static final ParticleType SMOKE = a("smoke", false);
/*  55 */   public static final ParticleType SNEEZE = a("sneeze", false);
/*  56 */   public static final ParticleType SPIT = a("spit", true);
/*  57 */   public static final ParticleType SQUID_INK = a("squid_ink", true);
/*  58 */   public static final ParticleType SWEEP_ATTACK = a("sweep_attack", true);
/*  59 */   public static final ParticleType TOTEM_OF_UNDYING = a("totem_of_undying", false);
/*     */   
/*  61 */   public static final ParticleType UNDERWATER = a("underwater", false);
/*  62 */   public static final ParticleType SPLASH = a("splash", false);
/*  63 */   public static final ParticleType WITCH = a("witch", false);
/*  64 */   public static final ParticleType BUBBLE_POP = a("bubble_pop", false);
/*  65 */   public static final ParticleType CURRENT_DOWN = a("current_down", false);
/*  66 */   public static final ParticleType BUBBLE_COLUMN_UP = a("bubble_column_up", false);
/*  67 */   public static final ParticleType NAUTILUS = a("nautilus", false);
/*  68 */   public static final ParticleType DOLPHIN = a("dolphin", false);
/*     */   
/*  70 */   public static final ParticleType CAMPFIRE_COSY_SMOKE = a("campfire_cosy_smoke", true);
/*  71 */   public static final ParticleType CAMPFIRE_SIGNAL_SMOKE = a("campfire_signal_smoke", true);
/*     */   
/*  73 */   public static final ParticleType DRIPPING_HONEY = a("dripping_honey", false);
/*  74 */   public static final ParticleType FALLING_HONEY = a("falling_honey", false);
/*  75 */   public static final ParticleType LANDING_HONEY = a("landing_honey", false);
/*  76 */   public static final ParticleType FALLING_NECTAR = a("falling_nectar", false);
/*     */   
/*  78 */   public static final ParticleType ASH = a("ash", false);
/*  79 */   public static final ParticleType CRIMSON_SPORE = a("crimson_spore", false);
/*  80 */   public static final ParticleType WARPED_SPORE = a("warped_spore", false);
/*  81 */   public static final ParticleType DRIPPING_OBSIDIAN_TEAR = a("dripping_obsidian_tear", false);
/*  82 */   public static final ParticleType FALLING_OBSIDIAN_TEAR = a("falling_obsidian_tear", false);
/*  83 */   public static final ParticleType LANDING_OBSIDIAN_TEAR = a("landing_obsidian_tear", false);
/*     */   
/*  85 */   public static final ParticleType REVERSE_PORTAL = a("reverse_portal", false);
/*     */   
/*  87 */   public static final ParticleType WHITE_ASH = a("white_ash", false);
/*     */   
/*     */   private static ParticleType a(String var0, boolean var1) {
/*  90 */     return (ParticleType)IRegistry.<Particle<?>>a(IRegistry.PARTICLE_TYPE, var0, new ParticleType(var1));
/*     */   }
/*     */   
/*     */   private static <T extends ParticleParam> Particle<T> a(String var0, ParticleParam.a<T> var1, Function<Particle<T>, Codec<T>> var2) {
/*  94 */     return (Particle<T>)IRegistry.<Particle<?>>a(IRegistry.PARTICLE_TYPE, var0, new Particle<T>(false, var1, var2)
/*     */         {
/*     */           public Codec<T> e() {
/*  97 */             return this.a.apply(this);
/*     */           }
/*     */         });
/*     */   }
/*     */   
/* 102 */   public static final Codec<ParticleParam> au = IRegistry.PARTICLE_TYPE.dispatch("type", ParticleParam::getParticle, Particle::e);
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\Particles.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
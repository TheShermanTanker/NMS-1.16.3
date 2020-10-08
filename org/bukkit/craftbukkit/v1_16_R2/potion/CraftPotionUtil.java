/*     */ package org.bukkit.craftbukkit.v1_16_R2.potion;
/*     */ 
/*     */ import com.google.common.base.Preconditions;
/*     */ import com.google.common.collect.BiMap;
/*     */ import com.google.common.collect.ImmutableBiMap;
/*     */ import net.minecraft.server.v1_16_R2.MobEffect;
/*     */ import net.minecraft.server.v1_16_R2.MobEffectList;
/*     */ import org.bukkit.potion.PotionData;
/*     */ import org.bukkit.potion.PotionEffect;
/*     */ import org.bukkit.potion.PotionEffectType;
/*     */ import org.bukkit.potion.PotionType;
/*     */ 
/*     */ public class CraftPotionUtil
/*     */ {
/*  15 */   private static final BiMap<PotionType, String> regular = (BiMap<PotionType, String>)ImmutableBiMap.builder()
/*  16 */     .put(PotionType.UNCRAFTABLE, "empty")
/*  17 */     .put(PotionType.WATER, "water")
/*  18 */     .put(PotionType.MUNDANE, "mundane")
/*  19 */     .put(PotionType.THICK, "thick")
/*  20 */     .put(PotionType.AWKWARD, "awkward")
/*  21 */     .put(PotionType.NIGHT_VISION, "night_vision")
/*  22 */     .put(PotionType.INVISIBILITY, "invisibility")
/*  23 */     .put(PotionType.JUMP, "leaping")
/*  24 */     .put(PotionType.FIRE_RESISTANCE, "fire_resistance")
/*  25 */     .put(PotionType.SPEED, "swiftness")
/*  26 */     .put(PotionType.SLOWNESS, "slowness")
/*  27 */     .put(PotionType.WATER_BREATHING, "water_breathing")
/*  28 */     .put(PotionType.INSTANT_HEAL, "healing")
/*  29 */     .put(PotionType.INSTANT_DAMAGE, "harming")
/*  30 */     .put(PotionType.POISON, "poison")
/*  31 */     .put(PotionType.REGEN, "regeneration")
/*  32 */     .put(PotionType.STRENGTH, "strength")
/*  33 */     .put(PotionType.WEAKNESS, "weakness")
/*  34 */     .put(PotionType.LUCK, "luck")
/*  35 */     .put(PotionType.TURTLE_MASTER, "turtle_master")
/*  36 */     .put(PotionType.SLOW_FALLING, "slow_falling")
/*  37 */     .build();
/*  38 */   private static final BiMap<PotionType, String> upgradeable = (BiMap<PotionType, String>)ImmutableBiMap.builder()
/*  39 */     .put(PotionType.JUMP, "strong_leaping")
/*  40 */     .put(PotionType.SPEED, "strong_swiftness")
/*  41 */     .put(PotionType.INSTANT_HEAL, "strong_healing")
/*  42 */     .put(PotionType.INSTANT_DAMAGE, "strong_harming")
/*  43 */     .put(PotionType.POISON, "strong_poison")
/*  44 */     .put(PotionType.REGEN, "strong_regeneration")
/*  45 */     .put(PotionType.STRENGTH, "strong_strength")
/*  46 */     .put(PotionType.SLOWNESS, "strong_slowness")
/*  47 */     .put(PotionType.TURTLE_MASTER, "strong_turtle_master")
/*  48 */     .build();
/*  49 */   private static final BiMap<PotionType, String> extendable = (BiMap<PotionType, String>)ImmutableBiMap.builder()
/*  50 */     .put(PotionType.NIGHT_VISION, "long_night_vision")
/*  51 */     .put(PotionType.INVISIBILITY, "long_invisibility")
/*  52 */     .put(PotionType.JUMP, "long_leaping")
/*  53 */     .put(PotionType.FIRE_RESISTANCE, "long_fire_resistance")
/*  54 */     .put(PotionType.SPEED, "long_swiftness")
/*  55 */     .put(PotionType.SLOWNESS, "long_slowness")
/*  56 */     .put(PotionType.WATER_BREATHING, "long_water_breathing")
/*  57 */     .put(PotionType.POISON, "long_poison")
/*  58 */     .put(PotionType.REGEN, "long_regeneration")
/*  59 */     .put(PotionType.STRENGTH, "long_strength")
/*  60 */     .put(PotionType.WEAKNESS, "long_weakness")
/*  61 */     .put(PotionType.TURTLE_MASTER, "long_turtle_master")
/*  62 */     .put(PotionType.SLOW_FALLING, "long_slow_falling")
/*  63 */     .build();
/*     */   
/*     */   public static String fromBukkit(PotionData data) {
/*     */     String type;
/*  67 */     if (data.isUpgraded()) {
/*  68 */       type = (String)upgradeable.get(data.getType());
/*  69 */     } else if (data.isExtended()) {
/*  70 */       type = (String)extendable.get(data.getType());
/*     */     } else {
/*  72 */       type = (String)regular.get(data.getType());
/*     */     } 
/*  74 */     Preconditions.checkNotNull(type, "Unknown potion type from data " + data);
/*     */     
/*  76 */     return "minecraft:" + type;
/*     */   }
/*     */   
/*     */   public static PotionData toBukkit(String type) {
/*  80 */     if (type == null) {
/*  81 */       return new PotionData(PotionType.UNCRAFTABLE, false, false);
/*     */     }
/*  83 */     if (type.startsWith("minecraft:")) {
/*  84 */       type = type.substring(10);
/*     */     }
/*  86 */     PotionType potionType = null;
/*  87 */     potionType = (PotionType)extendable.inverse().get(type);
/*  88 */     if (potionType != null) {
/*  89 */       return new PotionData(potionType, true, false);
/*     */     }
/*  91 */     potionType = (PotionType)upgradeable.inverse().get(type);
/*  92 */     if (potionType != null) {
/*  93 */       return new PotionData(potionType, false, true);
/*     */     }
/*  95 */     potionType = (PotionType)regular.inverse().get(type);
/*  96 */     if (potionType != null) {
/*  97 */       return new PotionData(potionType, false, false);
/*     */     }
/*  99 */     return new PotionData(PotionType.UNCRAFTABLE, false, false);
/*     */   }
/*     */   
/*     */   public static MobEffect fromBukkit(PotionEffect effect) {
/* 103 */     MobEffectList type = MobEffectList.fromId(effect.getType().getId());
/* 104 */     return new MobEffect(type, effect.getDuration(), effect.getAmplifier(), effect.isAmbient(), effect.hasParticles());
/*     */   }
/*     */   
/*     */   public static PotionEffect toBukkit(MobEffect effect) {
/* 108 */     PotionEffectType type = PotionEffectType.getById(MobEffectList.getId(effect.getMobEffect()));
/* 109 */     int amp = effect.getAmplifier();
/* 110 */     int duration = effect.getDuration();
/* 111 */     boolean ambient = effect.isAmbient();
/* 112 */     boolean particles = effect.isShowParticles();
/* 113 */     return new PotionEffect(type, duration, amp, ambient, particles);
/*     */   }
/*     */   
/*     */   public static boolean equals(MobEffectList mobEffect, PotionEffectType type) {
/* 117 */     PotionEffectType typeV = PotionEffectType.getById(MobEffectList.getId(mobEffect));
/* 118 */     return typeV.equals(type);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\potion\CraftPotionUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
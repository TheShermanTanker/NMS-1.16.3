/*     */ package org.bukkit.craftbukkit.v1_16_R2;
/*     */ 
/*     */ import com.google.common.base.Preconditions;
/*     */ import com.google.common.collect.BiMap;
/*     */ import com.google.common.collect.HashBiMap;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import net.minecraft.server.v1_16_R2.IRegistry;
/*     */ import net.minecraft.server.v1_16_R2.MinecraftKey;
/*     */ import net.minecraft.server.v1_16_R2.Particle;
/*     */ import net.minecraft.server.v1_16_R2.ParticleParam;
/*     */ import net.minecraft.server.v1_16_R2.ParticleParamBlock;
/*     */ import net.minecraft.server.v1_16_R2.ParticleParamItem;
/*     */ import net.minecraft.server.v1_16_R2.ParticleParamRedstone;
/*     */ import org.bukkit.Color;
/*     */ import org.bukkit.Particle;
/*     */ import org.bukkit.block.data.BlockData;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.block.data.CraftBlockData;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftItemStack;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.util.CraftMagicNumbers;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.material.MaterialData;
/*     */ 
/*     */ public enum CraftParticle
/*     */ {
/*  26 */   EXPLOSION_NORMAL("poof"),
/*  27 */   EXPLOSION_LARGE("explosion"),
/*  28 */   EXPLOSION_HUGE("explosion_emitter"),
/*  29 */   FIREWORKS_SPARK("firework"),
/*  30 */   WATER_BUBBLE("bubble"),
/*  31 */   WATER_SPLASH("splash"),
/*  32 */   WATER_WAKE("fishing"),
/*  33 */   SUSPENDED("underwater"),
/*  34 */   SUSPENDED_DEPTH("underwater"),
/*  35 */   CRIT("crit"),
/*  36 */   CRIT_MAGIC("enchanted_hit"),
/*  37 */   SMOKE_NORMAL("smoke"),
/*  38 */   SMOKE_LARGE("large_smoke"),
/*  39 */   SPELL("effect"),
/*  40 */   SPELL_INSTANT("instant_effect"),
/*  41 */   SPELL_MOB("entity_effect"),
/*  42 */   SPELL_MOB_AMBIENT("ambient_entity_effect"),
/*  43 */   SPELL_WITCH("witch"),
/*  44 */   DRIP_WATER("dripping_water"),
/*  45 */   DRIP_LAVA("dripping_lava"),
/*  46 */   VILLAGER_ANGRY("angry_villager"),
/*  47 */   VILLAGER_HAPPY("happy_villager"),
/*  48 */   TOWN_AURA("mycelium"),
/*  49 */   NOTE("note"),
/*  50 */   PORTAL("portal"),
/*  51 */   ENCHANTMENT_TABLE("enchant"),
/*  52 */   FLAME("flame"),
/*  53 */   LAVA("lava"),
/*  54 */   CLOUD("cloud"),
/*  55 */   REDSTONE("dust"),
/*  56 */   SNOWBALL("item_snowball"),
/*  57 */   SNOW_SHOVEL("item_snowball"),
/*  58 */   SLIME("item_slime"),
/*  59 */   HEART("heart"),
/*  60 */   BARRIER("barrier"),
/*  61 */   ITEM_CRACK("item"),
/*  62 */   BLOCK_CRACK("block"),
/*  63 */   BLOCK_DUST("block"),
/*  64 */   WATER_DROP("rain"),
/*  65 */   MOB_APPEARANCE("elder_guardian"),
/*  66 */   DRAGON_BREATH("dragon_breath"),
/*  67 */   END_ROD("end_rod"),
/*  68 */   DAMAGE_INDICATOR("damage_indicator"),
/*  69 */   SWEEP_ATTACK("sweep_attack"),
/*  70 */   FALLING_DUST("falling_dust"),
/*  71 */   TOTEM("totem_of_undying"),
/*  72 */   SPIT("spit"),
/*  73 */   SQUID_INK("squid_ink"),
/*  74 */   BUBBLE_POP("bubble_pop"),
/*  75 */   CURRENT_DOWN("current_down"),
/*  76 */   BUBBLE_COLUMN_UP("bubble_column_up"),
/*  77 */   NAUTILUS("nautilus"),
/*  78 */   DOLPHIN("dolphin"),
/*  79 */   SNEEZE("sneeze"),
/*  80 */   CAMPFIRE_COSY_SMOKE("campfire_cosy_smoke"),
/*  81 */   CAMPFIRE_SIGNAL_SMOKE("campfire_signal_smoke"),
/*  82 */   COMPOSTER("composter"),
/*  83 */   FLASH("flash"),
/*  84 */   FALLING_LAVA("falling_lava"),
/*  85 */   LANDING_LAVA("landing_lava"),
/*  86 */   FALLING_WATER("falling_water"),
/*  87 */   DRIPPING_HONEY("dripping_honey"),
/*  88 */   FALLING_HONEY("falling_honey"),
/*  89 */   LANDING_HONEY("landing_honey"),
/*  90 */   FALLING_NECTAR("falling_nectar"),
/*  91 */   SOUL_FIRE_FLAME("soul_fire_flame"),
/*  92 */   ASH("ash"),
/*  93 */   CRIMSON_SPORE("crimson_spore"),
/*  94 */   WARPED_SPORE("warped_spore"),
/*  95 */   SOUL("soul"),
/*  96 */   DRIPPING_OBSIDIAN_TEAR("dripping_obsidian_tear"),
/*  97 */   FALLING_OBSIDIAN_TEAR("falling_obsidian_tear"),
/*  98 */   LANDING_OBSIDIAN_TEAR("landing_obsidian_tear"),
/*  99 */   REVERSE_PORTAL("reverse_portal"),
/* 100 */   WHITE_ASH("white_ash"),
/*     */   
/* 102 */   LEGACY_BLOCK_CRACK("block"),
/* 103 */   LEGACY_BLOCK_DUST("block"),
/* 104 */   LEGACY_FALLING_DUST("falling_dust");
/*     */   private final MinecraftKey minecraftKey;
/*     */   private final Particle bukkit;
/*     */   private static final BiMap<Particle, MinecraftKey> particles;
/*     */   private static final Map<Particle, Particle> aliases;
/*     */   
/*     */   static {
/* 111 */     particles = (BiMap<Particle, MinecraftKey>)HashBiMap.create();
/* 112 */     aliases = new HashMap<>();
/*     */     
/* 114 */     for (CraftParticle particle : values()) {
/* 115 */       if (particles.containsValue(particle.minecraftKey)) {
/* 116 */         aliases.put(particle.bukkit, (Particle)particles.inverse().get(particle.minecraftKey));
/*     */       } else {
/* 118 */         particles.put(particle.bukkit, particle.minecraftKey);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   CraftParticle(String minecraftKey) {
/* 124 */     this.minecraftKey = new MinecraftKey(minecraftKey);
/*     */     
/* 126 */     this.bukkit = Particle.valueOf(name());
/* 127 */     Preconditions.checkState((this.bukkit != null), "Bukkit particle %s does not exist", name());
/*     */   }
/*     */   
/*     */   public static ParticleParam toNMS(Particle bukkit) {
/* 131 */     return toNMS(bukkit, (Object)null);
/*     */   }
/*     */   
/*     */   public static <T> ParticleParam toNMS(Particle particle, T obj) {
/* 135 */     Particle canonical = particle;
/* 136 */     if (aliases.containsKey(particle)) {
/* 137 */       canonical = aliases.get(particle);
/*     */     }
/*     */     
/* 140 */     Particle nms = (Particle)IRegistry.PARTICLE_TYPE.get((MinecraftKey)particles.get(canonical));
/* 141 */     Preconditions.checkArgument((nms != null), "No NMS particle %s", particle);
/*     */     
/* 143 */     if (particle.getDataType().equals(Void.class)) {
/* 144 */       return (ParticleParam)nms;
/*     */     }
/* 146 */     Preconditions.checkArgument((obj != null), "Particle %s requires data, null provided", particle);
/* 147 */     if (particle.getDataType().equals(ItemStack.class)) {
/* 148 */       ItemStack itemStack = (ItemStack)obj;
/* 149 */       return (ParticleParam)new ParticleParamItem(nms, CraftItemStack.asNMSCopy(itemStack));
/*     */     } 
/* 151 */     if (particle.getDataType() == MaterialData.class) {
/* 152 */       MaterialData data = (MaterialData)obj;
/* 153 */       return (ParticleParam)new ParticleParamBlock(nms, CraftMagicNumbers.getBlock(data));
/*     */     } 
/* 155 */     if (particle.getDataType() == BlockData.class) {
/* 156 */       BlockData data = (BlockData)obj;
/* 157 */       return (ParticleParam)new ParticleParamBlock(nms, ((CraftBlockData)data).getState());
/*     */     } 
/* 159 */     if (particle.getDataType() == Particle.DustOptions.class) {
/* 160 */       Particle.DustOptions data = (Particle.DustOptions)obj;
/* 161 */       Color color = data.getColor();
/* 162 */       return (ParticleParam)new ParticleParamRedstone(color.getRed() / 255.0F, color.getGreen() / 255.0F, color.getBlue() / 255.0F, data.getSize());
/*     */     } 
/* 164 */     throw new IllegalArgumentException(particle.getDataType().toString());
/*     */   }
/*     */   
/*     */   public static Particle toBukkit(ParticleParam nms) {
/* 168 */     return toBukkit(nms.getParticle());
/*     */   }
/*     */   
/*     */   public static Particle toBukkit(Particle nms) {
/* 172 */     return (Particle)particles.inverse().get(IRegistry.PARTICLE_TYPE.getKey(nms));
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\CraftParticle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
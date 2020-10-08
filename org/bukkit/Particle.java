/*     */ package org.bukkit;
/*     */ import com.destroystokyo.paper.ParticleBuilder;
/*     */ import com.google.common.base.Preconditions;
/*     */ import org.bukkit.block.data.BlockData;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.material.MaterialData;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ public enum Particle {
/*  10 */   EXPLOSION_NORMAL,
/*  11 */   EXPLOSION_LARGE,
/*  12 */   EXPLOSION_HUGE,
/*  13 */   FIREWORKS_SPARK,
/*  14 */   WATER_BUBBLE,
/*  15 */   WATER_SPLASH,
/*  16 */   WATER_WAKE,
/*  17 */   SUSPENDED,
/*  18 */   SUSPENDED_DEPTH,
/*  19 */   CRIT,
/*  20 */   CRIT_MAGIC,
/*  21 */   SMOKE_NORMAL,
/*  22 */   SMOKE_LARGE,
/*  23 */   SPELL,
/*  24 */   SPELL_INSTANT,
/*  25 */   SPELL_MOB,
/*  26 */   SPELL_MOB_AMBIENT,
/*  27 */   SPELL_WITCH,
/*  28 */   DRIP_WATER,
/*  29 */   DRIP_LAVA,
/*  30 */   VILLAGER_ANGRY,
/*  31 */   VILLAGER_HAPPY,
/*  32 */   TOWN_AURA,
/*  33 */   NOTE,
/*  34 */   PORTAL,
/*  35 */   ENCHANTMENT_TABLE,
/*  36 */   FLAME,
/*  37 */   LAVA,
/*  38 */   CLOUD,
/*  39 */   REDSTONE(DustOptions.class),
/*  40 */   SNOWBALL,
/*  41 */   SNOW_SHOVEL,
/*  42 */   SLIME,
/*  43 */   HEART,
/*  44 */   BARRIER,
/*  45 */   ITEM_CRACK(ItemStack.class),
/*  46 */   BLOCK_CRACK(BlockData.class),
/*  47 */   BLOCK_DUST(BlockData.class),
/*  48 */   WATER_DROP,
/*  49 */   MOB_APPEARANCE,
/*  50 */   DRAGON_BREATH,
/*  51 */   END_ROD,
/*  52 */   DAMAGE_INDICATOR,
/*  53 */   SWEEP_ATTACK,
/*  54 */   FALLING_DUST(BlockData.class),
/*  55 */   TOTEM,
/*  56 */   SPIT,
/*  57 */   SQUID_INK,
/*  58 */   BUBBLE_POP,
/*  59 */   CURRENT_DOWN,
/*  60 */   BUBBLE_COLUMN_UP,
/*  61 */   NAUTILUS,
/*  62 */   DOLPHIN,
/*  63 */   SNEEZE,
/*  64 */   CAMPFIRE_COSY_SMOKE,
/*  65 */   CAMPFIRE_SIGNAL_SMOKE,
/*  66 */   COMPOSTER,
/*  67 */   FLASH,
/*  68 */   FALLING_LAVA,
/*  69 */   LANDING_LAVA,
/*  70 */   FALLING_WATER,
/*  71 */   DRIPPING_HONEY,
/*  72 */   FALLING_HONEY,
/*  73 */   LANDING_HONEY,
/*  74 */   FALLING_NECTAR,
/*  75 */   SOUL_FIRE_FLAME,
/*  76 */   ASH,
/*  77 */   CRIMSON_SPORE,
/*  78 */   WARPED_SPORE,
/*  79 */   SOUL,
/*  80 */   DRIPPING_OBSIDIAN_TEAR,
/*  81 */   FALLING_OBSIDIAN_TEAR,
/*  82 */   LANDING_OBSIDIAN_TEAR,
/*  83 */   REVERSE_PORTAL,
/*  84 */   WHITE_ASH,
/*     */   
/*  86 */   LEGACY_BLOCK_CRACK(MaterialData.class),
/*  87 */   LEGACY_BLOCK_DUST(MaterialData.class),
/*  88 */   LEGACY_FALLING_DUST(MaterialData.class);
/*     */   
/*     */   private final Class<?> dataType;
/*     */   
/*     */   Particle() {
/*  93 */     this.dataType = Void.class;
/*     */   }
/*     */   
/*     */   Particle(Class<?> data) {
/*  97 */     this.dataType = data;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Class<?> getDataType() {
/* 106 */     return this.dataType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public ParticleBuilder builder() {
/* 117 */     return new ParticleBuilder(this);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static class DustOptions
/*     */   {
/*     */     private final Color color;
/*     */     
/*     */     private final float size;
/*     */ 
/*     */     
/*     */     public DustOptions(@NotNull Color color, float size) {
/* 130 */       Preconditions.checkArgument((color != null), "color");
/* 131 */       this.color = color;
/* 132 */       this.size = size;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     @NotNull
/*     */     public Color getColor() {
/* 142 */       return this.color;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public float getSize() {
/* 151 */       return this.size;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\Particle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
/*     */ package org.bukkit.craftbukkit.v1_16_R2.potion;
/*     */ 
/*     */ import net.minecraft.server.v1_16_R2.MobEffectList;
/*     */ import org.bukkit.Color;
/*     */ import org.bukkit.potion.PotionEffectType;
/*     */ 
/*     */ public class CraftPotionEffectType extends PotionEffectType {
/*     */   private final MobEffectList handle;
/*     */   
/*     */   public CraftPotionEffectType(MobEffectList handle) {
/*  11 */     super(MobEffectList.getId(handle));
/*  12 */     this.handle = handle;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getDurationModifier() {
/*  17 */     return 1.0D;
/*     */   }
/*     */   
/*     */   public MobEffectList getHandle() {
/*  21 */     return this.handle;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getName() {
/*  26 */     switch (getId()) {
/*     */       case 1:
/*  28 */         return "SPEED";
/*     */       case 2:
/*  30 */         return "SLOW";
/*     */       case 3:
/*  32 */         return "FAST_DIGGING";
/*     */       case 4:
/*  34 */         return "SLOW_DIGGING";
/*     */       case 5:
/*  36 */         return "INCREASE_DAMAGE";
/*     */       case 6:
/*  38 */         return "HEAL";
/*     */       case 7:
/*  40 */         return "HARM";
/*     */       case 8:
/*  42 */         return "JUMP";
/*     */       case 9:
/*  44 */         return "CONFUSION";
/*     */       case 10:
/*  46 */         return "REGENERATION";
/*     */       case 11:
/*  48 */         return "DAMAGE_RESISTANCE";
/*     */       case 12:
/*  50 */         return "FIRE_RESISTANCE";
/*     */       case 13:
/*  52 */         return "WATER_BREATHING";
/*     */       case 14:
/*  54 */         return "INVISIBILITY";
/*     */       case 15:
/*  56 */         return "BLINDNESS";
/*     */       case 16:
/*  58 */         return "NIGHT_VISION";
/*     */       case 17:
/*  60 */         return "HUNGER";
/*     */       case 18:
/*  62 */         return "WEAKNESS";
/*     */       case 19:
/*  64 */         return "POISON";
/*     */       case 20:
/*  66 */         return "WITHER";
/*     */       case 21:
/*  68 */         return "HEALTH_BOOST";
/*     */       case 22:
/*  70 */         return "ABSORPTION";
/*     */       case 23:
/*  72 */         return "SATURATION";
/*     */       case 24:
/*  74 */         return "GLOWING";
/*     */       case 25:
/*  76 */         return "LEVITATION";
/*     */       case 26:
/*  78 */         return "LUCK";
/*     */       case 27:
/*  80 */         return "UNLUCK";
/*     */       case 28:
/*  82 */         return "SLOW_FALLING";
/*     */       case 29:
/*  84 */         return "CONDUIT_POWER";
/*     */       case 30:
/*  86 */         return "DOLPHINS_GRACE";
/*     */       case 31:
/*  88 */         return "BAD_OMEN";
/*     */       case 32:
/*  90 */         return "HERO_OF_THE_VILLAGE";
/*     */     } 
/*  92 */     return "UNKNOWN_EFFECT_TYPE_" + getId();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isInstant() {
/*  98 */     return this.handle.isInstant();
/*     */   }
/*     */ 
/*     */   
/*     */   public Color getColor() {
/* 103 */     return Color.fromRGB(this.handle.getColor());
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\potion\CraftPotionEffectType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
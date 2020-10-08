/*     */ package org.bukkit.potion;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import org.apache.commons.lang.Validate;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.entity.LivingEntity;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.meta.ItemMeta;
/*     */ import org.bukkit.inventory.meta.PotionMeta;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ 
/*     */ 
/*     */ @Deprecated
/*     */ public class Potion
/*     */ {
/*     */   private boolean extended = false;
/*     */   private boolean splash = false;
/*  19 */   private int level = 1;
/*     */   
/*     */   private PotionType type;
/*     */   
/*     */   private static PotionBrewer brewer;
/*     */   private static final int EXTENDED_BIT = 64;
/*     */   private static final int POTION_BIT = 15;
/*     */   private static final int SPLASH_BIT = 16384;
/*     */   private static final int TIER_BIT = 32;
/*     */   private static final int TIER_SHIFT = 5;
/*     */   
/*     */   public Potion(@NotNull PotionType type) {
/*  31 */     Validate.notNull(type, "Null PotionType");
/*  32 */     this.type = type;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Potion(@NotNull PotionType type, int level) {
/*  42 */     this(type);
/*  43 */     Validate.notNull(type, "Type cannot be null");
/*  44 */     Validate.isTrue((level > 0 && level < 3), "Level must be 1 or 2");
/*  45 */     this.level = level;
/*     */   }
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
/*     */   @Deprecated
/*     */   public Potion(@NotNull PotionType type, int level, boolean splash) {
/*  59 */     this(type, level);
/*  60 */     this.splash = splash;
/*     */   }
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
/*     */   @Deprecated
/*     */   public Potion(@NotNull PotionType type, int level, boolean splash, boolean extended) {
/*  75 */     this(type, level, splash);
/*  76 */     this.extended = extended;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Potion splash() {
/*  86 */     setSplash(true);
/*  87 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Potion extend() {
/*  97 */     setHasExtendedDuration(true);
/*  98 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void apply(@NotNull ItemStack to) {
/* 108 */     Validate.notNull(to, "itemstack cannot be null");
/* 109 */     Validate.isTrue(to.hasItemMeta(), "given itemstack is not a potion");
/* 110 */     Validate.isTrue(to.getItemMeta() instanceof PotionMeta, "given itemstack is not a potion");
/* 111 */     PotionMeta meta = (PotionMeta)to.getItemMeta();
/* 112 */     meta.setBasePotionData(new PotionData(this.type, this.extended, (this.level == 2)));
/* 113 */     to.setItemMeta((ItemMeta)meta);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void apply(@NotNull LivingEntity to) {
/* 124 */     Validate.notNull(to, "entity cannot be null");
/* 125 */     to.addPotionEffects(getEffects());
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/* 130 */     if (this == obj) {
/* 131 */       return true;
/*     */     }
/* 133 */     if (obj == null || getClass() != obj.getClass()) {
/* 134 */       return false;
/*     */     }
/* 136 */     Potion other = (Potion)obj;
/* 137 */     return (this.extended == other.extended && this.splash == other.splash && this.level == other.level && this.type == other.type);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Collection<PotionEffect> getEffects() {
/* 150 */     return getBrewer().getEffects(this.type, (this.level == 2), this.extended);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getLevel() {
/* 159 */     return this.level;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public PotionType getType() {
/* 169 */     return this.type;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasExtendedDuration() {
/* 178 */     return this.extended;
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 183 */     int prime = 31;
/* 184 */     int result = 31 + this.level;
/* 185 */     result = 31 * result + (this.extended ? 1231 : 1237);
/* 186 */     result = 31 * result + (this.splash ? 1231 : 1237);
/* 187 */     result = 31 * result + ((this.type == null) ? 0 : this.type.hashCode());
/* 188 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isSplash() {
/* 197 */     return this.splash;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setHasExtendedDuration(boolean isExtended) {
/* 207 */     Validate.isTrue((this.type == null || !this.type.isInstant()), "Instant potions cannot be extended");
/* 208 */     this.extended = isExtended;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSplash(boolean isSplash) {
/* 218 */     this.splash = isSplash;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setType(@NotNull PotionType type) {
/* 227 */     this.type = type;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLevel(int level) {
/* 236 */     Validate.notNull(this.type, "No-effect potions don't have a level.");
/* 237 */     Validate.isTrue((level > 0 && level <= 2), "Level must be between 1 and 2 for this potion");
/* 238 */     this.level = level;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public short toDamageValue() {
/* 250 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public ItemStack toItemStack(int amount) {
/*     */     Material material;
/* 263 */     if (isSplash()) {
/* 264 */       material = Material.SPLASH_POTION;
/*     */     } else {
/* 266 */       material = Material.POTION;
/*     */     } 
/* 268 */     ItemStack itemStack = new ItemStack(material, amount);
/* 269 */     PotionMeta meta = (PotionMeta)itemStack.getItemMeta();
/* 270 */     meta.setBasePotionData(new PotionData(this.type, this.extended, (this.level == 2)));
/* 271 */     itemStack.setItemMeta((ItemMeta)meta);
/* 272 */     return itemStack;
/*     */   }
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
/*     */   @NotNull
/*     */   public static Potion fromDamage(int damage) {
/*     */     PotionType type;
/*     */     Potion potion;
/* 292 */     switch (damage & 0xF) {
/*     */       case 0:
/* 294 */         type = PotionType.WATER;
/*     */         break;
/*     */       case 1:
/* 297 */         type = PotionType.REGEN;
/*     */         break;
/*     */       case 2:
/* 300 */         type = PotionType.SPEED;
/*     */         break;
/*     */       case 3:
/* 303 */         type = PotionType.FIRE_RESISTANCE;
/*     */         break;
/*     */       case 4:
/* 306 */         type = PotionType.POISON;
/*     */         break;
/*     */       case 5:
/* 309 */         type = PotionType.INSTANT_HEAL;
/*     */         break;
/*     */       case 6:
/* 312 */         type = PotionType.NIGHT_VISION;
/*     */         break;
/*     */       case 8:
/* 315 */         type = PotionType.WEAKNESS;
/*     */         break;
/*     */       case 9:
/* 318 */         type = PotionType.STRENGTH;
/*     */         break;
/*     */       case 10:
/* 321 */         type = PotionType.SLOWNESS;
/*     */         break;
/*     */       case 11:
/* 324 */         type = PotionType.JUMP;
/*     */         break;
/*     */       case 12:
/* 327 */         type = PotionType.INSTANT_DAMAGE;
/*     */         break;
/*     */       case 13:
/* 330 */         type = PotionType.WATER_BREATHING;
/*     */         break;
/*     */       case 14:
/* 333 */         type = PotionType.INVISIBILITY;
/*     */         break;
/*     */       default:
/* 336 */         type = PotionType.WATER;
/*     */         break;
/*     */     } 
/* 339 */     if (type == null || type == PotionType.WATER) {
/* 340 */       potion = new Potion(PotionType.WATER);
/*     */     } else {
/* 342 */       int level = (damage & 0x20) >> 5;
/* 343 */       level++;
/* 344 */       potion = new Potion(type, level);
/*     */     } 
/* 346 */     if ((damage & 0x4000) != 0) {
/* 347 */       potion = potion.splash();
/*     */     }
/* 349 */     if ((damage & 0x40) != 0) {
/* 350 */       potion = potion.extend();
/*     */     }
/* 352 */     return potion;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public static Potion fromItemStack(@NotNull ItemStack item) {
/* 357 */     Validate.notNull(item, "item cannot be null");
/* 358 */     if (item.getType() != Material.POTION)
/* 359 */       throw new IllegalArgumentException("item is not a potion"); 
/* 360 */     return fromDamage(item.getDurability());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public static PotionBrewer getBrewer() {
/* 370 */     return brewer;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void setPotionBrewer(@NotNull PotionBrewer other) {
/* 380 */     if (brewer != null)
/* 381 */       throw new IllegalArgumentException("brewer can only be set internally"); 
/* 382 */     brewer = other;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public int getNameId() {
/* 393 */     return 0;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\potion\Potion.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
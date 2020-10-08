/*     */ package org.bukkit.craftbukkit.v1_16_R2.inventory;
/*     */ 
/*     */ import com.google.common.collect.ImmutableList;
/*     */ import com.google.common.collect.ImmutableMap;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import net.minecraft.server.v1_16_R2.NBTBase;
/*     */ import net.minecraft.server.v1_16_R2.NBTTagCompound;
/*     */ import net.minecraft.server.v1_16_R2.NBTTagList;
/*     */ import org.apache.commons.lang.Validate;
/*     */ import org.bukkit.Color;
/*     */ import org.bukkit.FireworkEffect;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.configuration.serialization.DelegateDeserialization;
/*     */ import org.bukkit.inventory.meta.Damageable;
/*     */ import org.bukkit.inventory.meta.FireworkMeta;
/*     */ import org.bukkit.inventory.meta.ItemMeta;
/*     */ import org.bukkit.inventory.meta.Repairable;
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
/*     */ @DelegateDeserialization(CraftMetaItem.SerializableMeta.class)
/*     */ class CraftMetaFirework
/*     */   extends CraftMetaItem
/*     */   implements FireworkMeta
/*     */ {
/*  41 */   static final CraftMetaItem.ItemMetaKey FIREWORKS = new CraftMetaItem.ItemMetaKey("Fireworks");
/*  42 */   static final CraftMetaItem.ItemMetaKey FLIGHT = new CraftMetaItem.ItemMetaKey("Flight", "power");
/*  43 */   static final CraftMetaItem.ItemMetaKey EXPLOSIONS = new CraftMetaItem.ItemMetaKey("Explosions", "firework-effects");
/*     */   
/*  45 */   static final CraftMetaItem.ItemMetaKey EXPLOSION_COLORS = new CraftMetaItem.ItemMetaKey("Colors");
/*     */   
/*  47 */   static final CraftMetaItem.ItemMetaKey EXPLOSION_TYPE = new CraftMetaItem.ItemMetaKey("Type");
/*     */   
/*  49 */   static final CraftMetaItem.ItemMetaKey EXPLOSION_TRAIL = new CraftMetaItem.ItemMetaKey("Trail");
/*     */   
/*  51 */   static final CraftMetaItem.ItemMetaKey EXPLOSION_FLICKER = new CraftMetaItem.ItemMetaKey("Flicker");
/*     */   
/*  53 */   static final CraftMetaItem.ItemMetaKey EXPLOSION_FADE = new CraftMetaItem.ItemMetaKey("FadeColors");
/*     */   
/*     */   private List<FireworkEffect> effects;
/*     */   private int power;
/*     */   
/*     */   CraftMetaFirework(CraftMetaItem meta) {
/*  59 */     super(meta);
/*     */     
/*  61 */     if (!(meta instanceof CraftMetaFirework)) {
/*     */       return;
/*     */     }
/*     */     
/*  65 */     CraftMetaFirework that = (CraftMetaFirework)meta;
/*     */     
/*  67 */     this.power = that.power;
/*     */     
/*  69 */     if (that.hasEffects()) {
/*  70 */       this.effects = new ArrayList<>(that.effects);
/*     */     }
/*     */   }
/*     */   
/*     */   CraftMetaFirework(NBTTagCompound tag) {
/*  75 */     super(tag);
/*     */     
/*  77 */     if (!tag.hasKey(FIREWORKS.NBT)) {
/*     */       return;
/*     */     }
/*     */     
/*  81 */     NBTTagCompound fireworks = tag.getCompound(FIREWORKS.NBT);
/*     */     
/*  83 */     this.power = 0xFF & fireworks.getByte(FLIGHT.NBT);
/*     */     
/*  85 */     if (!fireworks.hasKey(EXPLOSIONS.NBT)) {
/*     */       return;
/*     */     }
/*     */     
/*  89 */     NBTTagList fireworkEffects = fireworks.getList(EXPLOSIONS.NBT, 10);
/*  90 */     List<FireworkEffect> effects = this.effects = new ArrayList<>(fireworkEffects.size());
/*     */     
/*  92 */     for (int i = 0; i < fireworkEffects.size(); i++) {
/*     */       try {
/*  94 */         effects.add(getEffect((NBTTagCompound)fireworkEffects.get(i)));
/*  95 */       } catch (IllegalArgumentException illegalArgumentException) {}
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static FireworkEffect getEffect(NBTTagCompound explosion) {
/* 105 */     FireworkEffect.Builder effect = FireworkEffect.builder().flicker(explosion.getBoolean(EXPLOSION_FLICKER.NBT)).trail(explosion.getBoolean(EXPLOSION_TRAIL.NBT)).with(getEffectType(0xFF & explosion.getByte(EXPLOSION_TYPE.NBT)));
/*     */     
/* 107 */     int[] colors = explosion.getIntArray(EXPLOSION_COLORS.NBT);
/*     */ 
/*     */ 
/*     */     
/* 111 */     if (colors.length == 0) {
/* 112 */       effect.withColor(Color.WHITE);
/*     */     }
/*     */     
/* 115 */     for (int color : colors) {
/* 116 */       effect.withColor(Color.fromRGB(color));
/*     */     }
/*     */     
/* 119 */     for (int color : explosion.getIntArray(EXPLOSION_FADE.NBT)) {
/* 120 */       effect.withFade(Color.fromRGB(color));
/*     */     }
/*     */     
/* 123 */     return effect.build();
/*     */   }
/*     */   
/*     */   static NBTTagCompound getExplosion(FireworkEffect effect) {
/* 127 */     NBTTagCompound explosion = new NBTTagCompound();
/*     */     
/* 129 */     if (effect.hasFlicker()) {
/* 130 */       explosion.setBoolean(EXPLOSION_FLICKER.NBT, true);
/*     */     }
/*     */     
/* 133 */     if (effect.hasTrail()) {
/* 134 */       explosion.setBoolean(EXPLOSION_TRAIL.NBT, true);
/*     */     }
/*     */     
/* 137 */     addColors(explosion, EXPLOSION_COLORS, effect.getColors());
/* 138 */     addColors(explosion, EXPLOSION_FADE, effect.getFadeColors());
/*     */     
/* 140 */     explosion.setByte(EXPLOSION_TYPE.NBT, (byte)getNBT(effect.getType()));
/*     */     
/* 142 */     return explosion;
/*     */   }
/*     */   
/*     */   static int getNBT(FireworkEffect.Type type) {
/* 146 */     switch (type) {
/*     */       case FIREWORK_ROCKET:
/* 148 */         return 0;
/*     */       case null:
/* 150 */         return 1;
/*     */       case null:
/* 152 */         return 2;
/*     */       case null:
/* 154 */         return 3;
/*     */       case null:
/* 156 */         return 4;
/*     */     } 
/* 158 */     throw new IllegalArgumentException("Unknown effect type " + type);
/*     */   }
/*     */ 
/*     */   
/*     */   static FireworkEffect.Type getEffectType(int nbt) {
/* 163 */     switch (nbt) {
/*     */       case 0:
/* 165 */         return FireworkEffect.Type.BALL;
/*     */       case 1:
/* 167 */         return FireworkEffect.Type.BALL_LARGE;
/*     */       case 2:
/* 169 */         return FireworkEffect.Type.STAR;
/*     */       case 3:
/* 171 */         return FireworkEffect.Type.CREEPER;
/*     */       case 4:
/* 173 */         return FireworkEffect.Type.BURST;
/*     */     } 
/* 175 */     throw new IllegalArgumentException("Unknown effect type " + nbt);
/*     */   }
/*     */ 
/*     */   
/*     */   CraftMetaFirework(Map<String, Object> map) {
/* 180 */     super(map);
/*     */     
/* 182 */     Integer power = CraftMetaItem.SerializableMeta.<Integer>getObject(Integer.class, map, FLIGHT.BUKKIT, true);
/* 183 */     if (power != null) {
/* 184 */       setPower(power.intValue());
/*     */     }
/*     */     
/* 187 */     Iterable<?> effects = CraftMetaItem.SerializableMeta.<Iterable>getObject(Iterable.class, map, EXPLOSIONS.BUKKIT, true);
/* 188 */     safelyAddEffects(effects);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasEffects() {
/* 193 */     return (this.effects != null && !this.effects.isEmpty());
/*     */   }
/*     */   
/*     */   void safelyAddEffects(Iterable<?> collection) {
/* 197 */     if (collection == null || (collection instanceof Collection && ((Collection)collection).isEmpty())) {
/*     */       return;
/*     */     }
/*     */     
/* 201 */     List<FireworkEffect> effects = this.effects;
/* 202 */     if (effects == null) {
/* 203 */       effects = this.effects = new ArrayList<>();
/*     */     }
/*     */     
/* 206 */     for (Object obj : collection) {
/* 207 */       if (obj instanceof FireworkEffect) {
/* 208 */         effects.add((FireworkEffect)obj); continue;
/*     */       } 
/* 210 */       throw new IllegalArgumentException(obj + " in " + collection + " is not a FireworkEffect");
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   void applyToItem(NBTTagCompound itemTag) {
/* 217 */     super.applyToItem(itemTag);
/* 218 */     if (isFireworkEmpty()) {
/*     */       return;
/*     */     }
/*     */     
/* 222 */     NBTTagCompound fireworks = itemTag.getCompound(FIREWORKS.NBT);
/* 223 */     itemTag.set(FIREWORKS.NBT, (NBTBase)fireworks);
/*     */     
/* 225 */     if (hasEffects()) {
/* 226 */       NBTTagList effects = new NBTTagList();
/* 227 */       for (FireworkEffect effect : this.effects) {
/* 228 */         effects.add(getExplosion(effect));
/*     */       }
/*     */       
/* 231 */       if (effects.size() > 0) {
/* 232 */         fireworks.set(EXPLOSIONS.NBT, (NBTBase)effects);
/*     */       }
/*     */     } 
/*     */     
/* 236 */     if (hasPower()) {
/* 237 */       fireworks.setByte(FLIGHT.NBT, (byte)this.power);
/*     */     }
/*     */   }
/*     */   
/*     */   static void addColors(NBTTagCompound compound, CraftMetaItem.ItemMetaKey key, List<Color> colors) {
/* 242 */     if (colors.isEmpty()) {
/*     */       return;
/*     */     }
/*     */     
/* 246 */     int[] colorArray = new int[colors.size()];
/* 247 */     int i = 0;
/* 248 */     for (Color color : colors) {
/* 249 */       colorArray[i++] = color.asRGB();
/*     */     }
/*     */     
/* 252 */     compound.setIntArray(key.NBT, colorArray);
/*     */   }
/*     */ 
/*     */   
/*     */   boolean applicableTo(Material type) {
/* 257 */     switch (type) {
/*     */       case FIREWORK_ROCKET:
/* 259 */         return true;
/*     */     } 
/* 261 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   boolean isEmpty() {
/* 267 */     return (super.isEmpty() && isFireworkEmpty());
/*     */   }
/*     */   
/*     */   boolean isFireworkEmpty() {
/* 271 */     return (!hasEffects() && !hasPower());
/*     */   }
/*     */   
/*     */   boolean hasPower() {
/* 275 */     return (this.power != 0);
/*     */   }
/*     */ 
/*     */   
/*     */   boolean equalsCommon(CraftMetaItem meta) {
/* 280 */     if (!super.equalsCommon(meta)) {
/* 281 */       return false;
/*     */     }
/*     */     
/* 284 */     if (meta instanceof CraftMetaFirework) {
/* 285 */       CraftMetaFirework that = (CraftMetaFirework)meta;
/*     */       
/* 287 */       if (hasPower() ? (that.hasPower() && this.power == that.power) : !that.hasPower())
/* 288 */         if (hasEffects() ? (that.hasEffects() && this.effects.equals(that.effects)) : !that.hasEffects()); 
/*     */       return false;
/*     */     } 
/* 291 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   boolean notUncommon(CraftMetaItem meta) {
/* 296 */     return (super.notUncommon(meta) && (meta instanceof CraftMetaFirework || isFireworkEmpty()));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   int applyHash() {
/* 302 */     int original = super.applyHash(), hash = original;
/* 303 */     if (hasPower()) {
/* 304 */       hash = 61 * hash + this.power;
/*     */     }
/* 306 */     if (hasEffects()) {
/* 307 */       hash = 61 * hash + 13 * this.effects.hashCode();
/*     */     }
/* 309 */     return (hash != original) ? (CraftMetaFirework.class.hashCode() ^ hash) : hash;
/*     */   }
/*     */ 
/*     */   
/*     */   ImmutableMap.Builder<String, Object> serialize(ImmutableMap.Builder<String, Object> builder) {
/* 314 */     super.serialize(builder);
/*     */     
/* 316 */     if (hasEffects()) {
/* 317 */       builder.put(EXPLOSIONS.BUKKIT, ImmutableList.copyOf(this.effects));
/*     */     }
/*     */     
/* 320 */     if (hasPower()) {
/* 321 */       builder.put(FLIGHT.BUKKIT, Integer.valueOf(this.power));
/*     */     }
/*     */     
/* 324 */     return builder;
/*     */   }
/*     */ 
/*     */   
/*     */   public CraftMetaFirework clone() {
/* 329 */     CraftMetaFirework meta = (CraftMetaFirework)super.clone();
/*     */     
/* 331 */     if (this.effects != null) {
/* 332 */       meta.effects = new ArrayList<>(this.effects);
/*     */     }
/*     */     
/* 335 */     return meta;
/*     */   }
/*     */ 
/*     */   
/*     */   public void addEffect(FireworkEffect effect) {
/* 340 */     Validate.notNull(effect, "Effect cannot be null");
/* 341 */     if (this.effects == null) {
/* 342 */       this.effects = new ArrayList<>();
/*     */     }
/* 344 */     this.effects.add(effect);
/*     */   }
/*     */ 
/*     */   
/*     */   public void addEffects(FireworkEffect... effects) {
/* 349 */     Validate.notNull(effects, "Effects cannot be null");
/* 350 */     if (effects.length == 0) {
/*     */       return;
/*     */     }
/*     */     
/* 354 */     List<FireworkEffect> list = this.effects;
/* 355 */     if (list == null) {
/* 356 */       list = this.effects = new ArrayList<>();
/*     */     }
/*     */     
/* 359 */     for (FireworkEffect effect : effects) {
/* 360 */       Validate.notNull(effect, "Effect cannot be null");
/* 361 */       list.add(effect);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void addEffects(Iterable<FireworkEffect> effects) {
/* 367 */     Validate.notNull(effects, "Effects cannot be null");
/* 368 */     safelyAddEffects(effects);
/*     */   }
/*     */ 
/*     */   
/*     */   public List<FireworkEffect> getEffects() {
/* 373 */     return (this.effects == null) ? (List<FireworkEffect>)ImmutableList.of() : (List<FireworkEffect>)ImmutableList.copyOf(this.effects);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getEffectsSize() {
/* 378 */     return (this.effects == null) ? 0 : this.effects.size();
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeEffect(int index) {
/* 383 */     if (this.effects == null) {
/* 384 */       throw new IndexOutOfBoundsException("Index: " + index + ", Size: 0");
/*     */     }
/* 386 */     this.effects.remove(index);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void clearEffects() {
/* 392 */     this.effects = null;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getPower() {
/* 397 */     return this.power;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPower(int power) {
/* 402 */     Validate.isTrue((power >= 0), "Power cannot be less than zero: ", power);
/* 403 */     Validate.isTrue((power < 128), "Power cannot be more than 127: ", power);
/* 404 */     this.power = power;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\inventory\CraftMetaFirework.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
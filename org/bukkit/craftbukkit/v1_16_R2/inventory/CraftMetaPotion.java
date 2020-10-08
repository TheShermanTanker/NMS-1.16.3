/*     */ package org.bukkit.craftbukkit.v1_16_R2.inventory;
/*     */ import com.google.common.collect.ImmutableList;
/*     */ import com.google.common.collect.ImmutableMap;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import net.minecraft.server.v1_16_R2.NBTTagCompound;
/*     */ import net.minecraft.server.v1_16_R2.NBTTagList;
/*     */ import org.apache.commons.lang.Validate;
/*     */ import org.bukkit.Color;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.configuration.serialization.DelegateDeserialization;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.potion.CraftPotionUtil;
/*     */ import org.bukkit.inventory.meta.Damageable;
/*     */ import org.bukkit.inventory.meta.ItemMeta;
/*     */ import org.bukkit.inventory.meta.PotionMeta;
/*     */ import org.bukkit.inventory.meta.Repairable;
/*     */ import org.bukkit.potion.PotionData;
/*     */ import org.bukkit.potion.PotionEffect;
/*     */ import org.bukkit.potion.PotionEffectType;
/*     */ import org.bukkit.potion.PotionType;
/*     */ 
/*     */ @DelegateDeserialization(CraftMetaItem.SerializableMeta.class)
/*     */ class CraftMetaPotion extends CraftMetaItem implements PotionMeta {
/*  26 */   static final CraftMetaItem.ItemMetaKey AMPLIFIER = new CraftMetaItem.ItemMetaKey("Amplifier", "amplifier");
/*  27 */   static final CraftMetaItem.ItemMetaKey AMBIENT = new CraftMetaItem.ItemMetaKey("Ambient", "ambient");
/*  28 */   static final CraftMetaItem.ItemMetaKey DURATION = new CraftMetaItem.ItemMetaKey("Duration", "duration");
/*  29 */   static final CraftMetaItem.ItemMetaKey SHOW_PARTICLES = new CraftMetaItem.ItemMetaKey("ShowParticles", "has-particles");
/*  30 */   static final CraftMetaItem.ItemMetaKey SHOW_ICON = new CraftMetaItem.ItemMetaKey("ShowIcon", "has-icon");
/*  31 */   static final CraftMetaItem.ItemMetaKey POTION_EFFECTS = new CraftMetaItem.ItemMetaKey("CustomPotionEffects", "custom-effects");
/*  32 */   static final CraftMetaItem.ItemMetaKey POTION_COLOR = new CraftMetaItem.ItemMetaKey("CustomPotionColor", "custom-color");
/*  33 */   static final CraftMetaItem.ItemMetaKey ID = new CraftMetaItem.ItemMetaKey("Id", "potion-id");
/*  34 */   static final CraftMetaItem.ItemMetaKey DEFAULT_POTION = new CraftMetaItem.ItemMetaKey("Potion", "potion-type");
/*     */ 
/*     */ 
/*     */   
/*  38 */   private PotionData type = new PotionData(PotionType.UNCRAFTABLE, false, false);
/*     */   private List<PotionEffect> customEffects;
/*     */   private Color color;
/*     */   
/*     */   CraftMetaPotion(CraftMetaItem meta) {
/*  43 */     super(meta);
/*  44 */     if (!(meta instanceof CraftMetaPotion)) {
/*     */       return;
/*     */     }
/*  47 */     CraftMetaPotion potionMeta = (CraftMetaPotion)meta;
/*  48 */     this.type = potionMeta.type;
/*  49 */     this.color = potionMeta.color;
/*  50 */     if (potionMeta.hasCustomEffects()) {
/*  51 */       this.customEffects = new ArrayList<>(potionMeta.customEffects);
/*     */     }
/*     */   }
/*     */   
/*     */   CraftMetaPotion(NBTTagCompound tag) {
/*  56 */     super(tag);
/*  57 */     if (tag.hasKey(DEFAULT_POTION.NBT)) {
/*  58 */       this.type = CraftPotionUtil.toBukkit(tag.getString(DEFAULT_POTION.NBT));
/*     */     }
/*  60 */     if (tag.hasKey(POTION_COLOR.NBT)) {
/*     */       try {
/*  62 */         this.color = Color.fromRGB(tag.getInt(POTION_COLOR.NBT));
/*  63 */       } catch (IllegalArgumentException illegalArgumentException) {}
/*     */     }
/*     */ 
/*     */     
/*  67 */     if (tag.hasKey(POTION_EFFECTS.NBT)) {
/*  68 */       NBTTagList list = tag.getList(POTION_EFFECTS.NBT, 10);
/*  69 */       int length = list.size();
/*  70 */       this.customEffects = new ArrayList<>(length);
/*     */       
/*  72 */       for (int i = 0; i < length; i++) {
/*  73 */         NBTTagCompound effect = list.getCompound(i);
/*  74 */         PotionEffectType type = PotionEffectType.getById(effect.getByte(ID.NBT));
/*     */         
/*  76 */         if (type != null) {
/*     */ 
/*     */ 
/*     */           
/*  80 */           int amp = effect.getByte(AMPLIFIER.NBT);
/*  81 */           int duration = effect.getInt(DURATION.NBT);
/*  82 */           boolean ambient = effect.getBoolean(AMBIENT.NBT);
/*  83 */           boolean particles = tag.hasKeyOfType(SHOW_PARTICLES.NBT, 1) ? effect.getBoolean(SHOW_PARTICLES.NBT) : true;
/*  84 */           boolean icon = tag.hasKeyOfType(SHOW_ICON.NBT, 1) ? effect.getBoolean(SHOW_ICON.NBT) : particles;
/*  85 */           this.customEffects.add(new PotionEffect(type, duration, amp, ambient, particles, icon));
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   CraftMetaPotion(Map<String, Object> map) {
/*  91 */     super(map);
/*  92 */     this.type = CraftPotionUtil.toBukkit(CraftMetaItem.SerializableMeta.getString(map, DEFAULT_POTION.BUKKIT, true));
/*     */     
/*  94 */     Color color = CraftMetaItem.SerializableMeta.<Color>getObject(Color.class, map, POTION_COLOR.BUKKIT, true);
/*  95 */     if (color != null) {
/*  96 */       setColor(color);
/*     */     }
/*     */     
/*  99 */     Iterable<?> rawEffectList = CraftMetaItem.SerializableMeta.<Iterable>getObject(Iterable.class, map, POTION_EFFECTS.BUKKIT, true);
/* 100 */     if (rawEffectList == null) {
/*     */       return;
/*     */     }
/*     */     
/* 104 */     for (Object obj : rawEffectList) {
/* 105 */       if (!(obj instanceof PotionEffect)) {
/* 106 */         throw new IllegalArgumentException("Object in effect list is not valid. " + obj.getClass());
/*     */       }
/* 108 */       addCustomEffect((PotionEffect)obj, true);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   void applyToItem(NBTTagCompound tag) {
/* 114 */     super.applyToItem(tag);
/*     */     
/* 116 */     tag.setString(DEFAULT_POTION.NBT, CraftPotionUtil.fromBukkit(this.type));
/*     */     
/* 118 */     if (hasColor()) {
/* 119 */       tag.setInt(POTION_COLOR.NBT, this.color.asRGB());
/*     */     }
/*     */     
/* 122 */     if (this.customEffects != null) {
/* 123 */       NBTTagList effectList = new NBTTagList();
/* 124 */       tag.set(POTION_EFFECTS.NBT, (NBTBase)effectList);
/*     */       
/* 126 */       for (PotionEffect effect : this.customEffects) {
/* 127 */         NBTTagCompound effectData = new NBTTagCompound();
/* 128 */         effectData.setByte(ID.NBT, (byte)effect.getType().getId());
/* 129 */         effectData.setByte(AMPLIFIER.NBT, (byte)effect.getAmplifier());
/* 130 */         effectData.setInt(DURATION.NBT, effect.getDuration());
/* 131 */         effectData.setBoolean(AMBIENT.NBT, effect.isAmbient());
/* 132 */         effectData.setBoolean(SHOW_PARTICLES.NBT, effect.hasParticles());
/* 133 */         effectData.setBoolean(SHOW_ICON.NBT, effect.hasIcon());
/* 134 */         effectList.add(effectData);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   boolean isEmpty() {
/* 141 */     return (super.isEmpty() && isPotionEmpty());
/*     */   }
/*     */   
/*     */   boolean isPotionEmpty() {
/* 145 */     return (this.type.getType() == PotionType.UNCRAFTABLE && !hasCustomEffects() && !hasColor());
/*     */   }
/*     */ 
/*     */   
/*     */   boolean applicableTo(Material type) {
/* 150 */     switch (type) {
/*     */       case POTION:
/*     */       case SPLASH_POTION:
/*     */       case LINGERING_POTION:
/*     */       case TIPPED_ARROW:
/* 155 */         return true;
/*     */     } 
/* 157 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public CraftMetaPotion clone() {
/* 163 */     CraftMetaPotion clone = (CraftMetaPotion)super.clone();
/* 164 */     clone.type = this.type;
/* 165 */     if (this.customEffects != null) {
/* 166 */       clone.customEffects = new ArrayList<>(this.customEffects);
/*     */     }
/* 168 */     return clone;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBasePotionData(PotionData data) {
/* 173 */     Validate.notNull(data, "PotionData cannot be null");
/* 174 */     this.type = data;
/*     */   }
/*     */ 
/*     */   
/*     */   public PotionData getBasePotionData() {
/* 179 */     return this.type;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasCustomEffects() {
/* 184 */     return (this.customEffects != null);
/*     */   }
/*     */ 
/*     */   
/*     */   public List<PotionEffect> getCustomEffects() {
/* 189 */     if (hasCustomEffects()) {
/* 190 */       return (List<PotionEffect>)ImmutableList.copyOf(this.customEffects);
/*     */     }
/* 192 */     return (List<PotionEffect>)ImmutableList.of();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean addCustomEffect(PotionEffect effect, boolean overwrite) {
/* 197 */     Validate.notNull(effect, "Potion effect must not be null");
/*     */     
/* 199 */     int index = indexOfEffect(effect.getType());
/* 200 */     if (index != -1) {
/* 201 */       if (overwrite) {
/* 202 */         PotionEffect old = this.customEffects.get(index);
/* 203 */         if (old.getAmplifier() == effect.getAmplifier() && old.getDuration() == effect.getDuration() && old.isAmbient() == effect.isAmbient()) {
/* 204 */           return false;
/*     */         }
/* 206 */         this.customEffects.set(index, effect);
/* 207 */         return true;
/*     */       } 
/* 209 */       return false;
/*     */     } 
/*     */     
/* 212 */     if (this.customEffects == null) {
/* 213 */       this.customEffects = new ArrayList<>();
/*     */     }
/* 215 */     this.customEffects.add(effect);
/* 216 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean removeCustomEffect(PotionEffectType type) {
/* 222 */     Validate.notNull(type, "Potion effect type must not be null");
/*     */     
/* 224 */     if (!hasCustomEffects()) {
/* 225 */       return false;
/*     */     }
/*     */     
/* 228 */     boolean changed = false;
/* 229 */     Iterator<PotionEffect> iterator = this.customEffects.iterator();
/* 230 */     while (iterator.hasNext()) {
/* 231 */       PotionEffect effect = iterator.next();
/* 232 */       if (type.equals(effect.getType())) {
/* 233 */         iterator.remove();
/* 234 */         changed = true;
/*     */       } 
/*     */     } 
/* 237 */     if (this.customEffects.isEmpty()) {
/* 238 */       this.customEffects = null;
/*     */     }
/* 240 */     return changed;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasCustomEffect(PotionEffectType type) {
/* 245 */     Validate.notNull(type, "Potion effect type must not be null");
/* 246 */     return (indexOfEffect(type) != -1);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean setMainEffect(PotionEffectType type) {
/* 251 */     Validate.notNull(type, "Potion effect type must not be null");
/* 252 */     int index = indexOfEffect(type);
/* 253 */     if (index == -1 || index == 0) {
/* 254 */       return false;
/*     */     }
/*     */     
/* 257 */     PotionEffect old = this.customEffects.get(0);
/* 258 */     this.customEffects.set(0, this.customEffects.get(index));
/* 259 */     this.customEffects.set(index, old);
/* 260 */     return true;
/*     */   }
/*     */   
/*     */   private int indexOfEffect(PotionEffectType type) {
/* 264 */     if (!hasCustomEffects()) {
/* 265 */       return -1;
/*     */     }
/*     */     
/* 268 */     for (int i = 0; i < this.customEffects.size(); i++) {
/* 269 */       if (((PotionEffect)this.customEffects.get(i)).getType().equals(type)) {
/* 270 */         return i;
/*     */       }
/*     */     } 
/* 273 */     return -1;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean clearCustomEffects() {
/* 278 */     boolean changed = hasCustomEffects();
/* 279 */     this.customEffects = null;
/* 280 */     return changed;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasColor() {
/* 285 */     return (this.color != null);
/*     */   }
/*     */ 
/*     */   
/*     */   public Color getColor() {
/* 290 */     return this.color;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setColor(Color color) {
/* 295 */     this.color = color;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   int applyHash() {
/* 301 */     int original = super.applyHash(), hash = original;
/* 302 */     if (this.type.getType() != PotionType.UNCRAFTABLE) {
/* 303 */       hash = 73 * hash + this.type.hashCode();
/*     */     }
/* 305 */     if (hasColor()) {
/* 306 */       hash = 73 * hash + this.color.hashCode();
/*     */     }
/* 308 */     if (hasCustomEffects()) {
/* 309 */       hash = 73 * hash + this.customEffects.hashCode();
/*     */     }
/* 311 */     return (original != hash) ? (CraftMetaPotion.class.hashCode() ^ hash) : hash;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equalsCommon(CraftMetaItem meta) {
/* 316 */     if (!super.equalsCommon(meta)) {
/* 317 */       return false;
/*     */     }
/* 319 */     if (meta instanceof CraftMetaPotion) {
/* 320 */       CraftMetaPotion that = (CraftMetaPotion)meta;
/*     */       
/* 322 */       return (this.type.equals(that.type) && (
/* 323 */         hasCustomEffects() ? (that.hasCustomEffects() && this.customEffects.equals(that.customEffects)) : !that.hasCustomEffects()) && (
/* 324 */         hasColor() ? (that.hasColor() && this.color.equals(that.color)) : !that.hasColor()));
/*     */     } 
/* 326 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   boolean notUncommon(CraftMetaItem meta) {
/* 331 */     return (super.notUncommon(meta) && (meta instanceof CraftMetaPotion || isPotionEmpty()));
/*     */   }
/*     */ 
/*     */   
/*     */   ImmutableMap.Builder<String, Object> serialize(ImmutableMap.Builder<String, Object> builder) {
/* 336 */     super.serialize(builder);
/* 337 */     if (this.type.getType() != PotionType.UNCRAFTABLE) {
/* 338 */       builder.put(DEFAULT_POTION.BUKKIT, CraftPotionUtil.fromBukkit(this.type));
/*     */     }
/*     */     
/* 341 */     if (hasColor()) {
/* 342 */       builder.put(POTION_COLOR.BUKKIT, getColor());
/*     */     }
/*     */     
/* 345 */     if (hasCustomEffects()) {
/* 346 */       builder.put(POTION_EFFECTS.BUKKIT, ImmutableList.copyOf(this.customEffects));
/*     */     }
/*     */     
/* 349 */     return builder;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\inventory\CraftMetaPotion.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
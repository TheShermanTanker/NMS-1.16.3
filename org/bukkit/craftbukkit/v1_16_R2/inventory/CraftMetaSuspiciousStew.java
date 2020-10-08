/*     */ package org.bukkit.craftbukkit.v1_16_R2.inventory;
/*     */ import com.google.common.collect.ImmutableMap;
/*     */ import com.google.common.collect.Multimap;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import javax.annotation.Nonnull;
/*     */ import net.md_5.bungee.api.chat.BaseComponent;
/*     */ import net.minecraft.server.v1_16_R2.NBTTagCompound;
/*     */ import net.minecraft.server.v1_16_R2.NBTTagList;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.attribute.Attribute;
/*     */ import org.bukkit.attribute.AttributeModifier;
/*     */ import org.bukkit.enchantments.Enchantment;
/*     */ import org.bukkit.inventory.EquipmentSlot;
/*     */ import org.bukkit.inventory.ItemFlag;
/*     */ import org.bukkit.potion.PotionEffect;
/*     */ import org.bukkit.potion.PotionEffectType;
/*     */ 
/*     */ @DelegateDeserialization(CraftMetaItem.SerializableMeta.class)
/*     */ public class CraftMetaSuspiciousStew extends CraftMetaItem implements SuspiciousStewMeta {
/*  22 */   static final CraftMetaItem.ItemMetaKey DURATION = new CraftMetaItem.ItemMetaKey("EffectDuration", "duration");
/*  23 */   static final CraftMetaItem.ItemMetaKey EFFECTS = new CraftMetaItem.ItemMetaKey("Effects", "effects");
/*  24 */   static final CraftMetaItem.ItemMetaKey ID = new CraftMetaItem.ItemMetaKey("EffectId", "id");
/*     */   
/*     */   private List<PotionEffect> customEffects;
/*     */   
/*     */   CraftMetaSuspiciousStew(CraftMetaItem meta) {
/*  29 */     super(meta);
/*  30 */     if (!(meta instanceof CraftMetaSuspiciousStew)) {
/*     */       return;
/*     */     }
/*  33 */     CraftMetaSuspiciousStew stewMeta = (CraftMetaSuspiciousStew)meta;
/*  34 */     if (stewMeta.hasCustomEffects()) {
/*  35 */       this.customEffects = new ArrayList<>(stewMeta.customEffects);
/*     */     }
/*     */   }
/*     */   
/*     */   CraftMetaSuspiciousStew(NBTTagCompound tag) {
/*  40 */     super(tag);
/*  41 */     if (tag.hasKey(EFFECTS.NBT)) {
/*  42 */       NBTTagList list = tag.getList(EFFECTS.NBT, 10);
/*  43 */       int length = list.size();
/*  44 */       this.customEffects = new ArrayList<>(length);
/*     */       
/*  46 */       for (int i = 0; i < length; i++) {
/*  47 */         NBTTagCompound effect = list.getCompound(i);
/*  48 */         PotionEffectType type = PotionEffectType.getById(effect.getByte(ID.NBT));
/*  49 */         if (type != null) {
/*     */ 
/*     */           
/*  52 */           int duration = effect.getInt(DURATION.NBT);
/*  53 */           this.customEffects.add(new PotionEffect(type, duration, 0));
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   CraftMetaSuspiciousStew(Map<String, Object> map) {
/*  59 */     super(map);
/*     */     
/*  61 */     Iterable<?> rawEffectList = CraftMetaItem.SerializableMeta.<Iterable>getObject(Iterable.class, map, EFFECTS.BUKKIT, true);
/*  62 */     if (rawEffectList == null) {
/*     */       return;
/*     */     }
/*     */     
/*  66 */     for (Object obj : rawEffectList) {
/*  67 */       if (!(obj instanceof PotionEffect)) {
/*  68 */         throw new IllegalArgumentException("Object in effect list is not valid. " + obj.getClass());
/*     */       }
/*  70 */       addCustomEffect((PotionEffect)obj, true);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   void applyToItem(NBTTagCompound tag) {
/*  76 */     super.applyToItem(tag);
/*     */     
/*  78 */     if (this.customEffects != null) {
/*  79 */       NBTTagList effectList = new NBTTagList();
/*  80 */       tag.set(EFFECTS.NBT, (NBTBase)effectList);
/*     */       
/*  82 */       for (PotionEffect effect : this.customEffects) {
/*  83 */         NBTTagCompound effectData = new NBTTagCompound();
/*  84 */         effectData.setByte(ID.NBT, (byte)effect.getType().getId());
/*  85 */         effectData.setInt(DURATION.NBT, effect.getDuration());
/*  86 */         effectList.add(effectData);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   boolean isEmpty() {
/*  93 */     return (super.isEmpty() && isStewEmpty());
/*     */   }
/*     */   
/*     */   boolean isStewEmpty() {
/*  97 */     return !hasCustomEffects();
/*     */   }
/*     */ 
/*     */   
/*     */   boolean applicableTo(Material type) {
/* 102 */     return (type == Material.SUSPICIOUS_STEW);
/*     */   }
/*     */ 
/*     */   
/*     */   public CraftMetaSuspiciousStew clone() {
/* 107 */     CraftMetaSuspiciousStew clone = (CraftMetaSuspiciousStew)super.clone();
/* 108 */     if (this.customEffects != null) {
/* 109 */       clone.customEffects = new ArrayList<>(this.customEffects);
/*     */     }
/* 111 */     return clone;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasCustomEffects() {
/* 116 */     return (this.customEffects != null);
/*     */   }
/*     */ 
/*     */   
/*     */   public List<PotionEffect> getCustomEffects() {
/* 121 */     if (hasCustomEffects()) {
/* 122 */       return (List<PotionEffect>)ImmutableList.copyOf(this.customEffects);
/*     */     }
/* 124 */     return (List<PotionEffect>)ImmutableList.of();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean addCustomEffect(PotionEffect effect, boolean overwrite) {
/* 129 */     Validate.notNull(effect, "Potion effect must not be null");
/*     */     
/* 131 */     int index = indexOfEffect(effect.getType());
/* 132 */     if (index != -1) {
/* 133 */       if (overwrite) {
/* 134 */         PotionEffect old = this.customEffects.get(index);
/* 135 */         if (old.getDuration() == effect.getDuration()) {
/* 136 */           return false;
/*     */         }
/* 138 */         this.customEffects.set(index, effect);
/* 139 */         return true;
/*     */       } 
/* 141 */       return false;
/*     */     } 
/*     */     
/* 144 */     if (this.customEffects == null) {
/* 145 */       this.customEffects = new ArrayList<>();
/*     */     }
/* 147 */     this.customEffects.add(effect);
/* 148 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean removeCustomEffect(PotionEffectType type) {
/* 154 */     Validate.notNull(type, "Potion effect type must not be null");
/*     */     
/* 156 */     if (!hasCustomEffects()) {
/* 157 */       return false;
/*     */     }
/*     */     
/* 160 */     boolean changed = false;
/* 161 */     Iterator<PotionEffect> iterator = this.customEffects.iterator();
/* 162 */     while (iterator.hasNext()) {
/* 163 */       PotionEffect effect = iterator.next();
/* 164 */       if (type.equals(effect.getType())) {
/* 165 */         iterator.remove();
/* 166 */         changed = true;
/*     */       } 
/*     */     } 
/* 169 */     if (this.customEffects.isEmpty()) {
/* 170 */       this.customEffects = null;
/*     */     }
/* 172 */     return changed;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasCustomEffect(PotionEffectType type) {
/* 177 */     Validate.notNull(type, "Potion effect type must not be null");
/* 178 */     return (indexOfEffect(type) != -1);
/*     */   }
/*     */   
/*     */   private int indexOfEffect(PotionEffectType type) {
/* 182 */     if (!hasCustomEffects()) {
/* 183 */       return -1;
/*     */     }
/*     */     
/* 186 */     for (int i = 0; i < this.customEffects.size(); i++) {
/* 187 */       if (((PotionEffect)this.customEffects.get(i)).getType().equals(type)) {
/* 188 */         return i;
/*     */       }
/*     */     } 
/* 191 */     return -1;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean clearCustomEffects() {
/* 196 */     boolean changed = hasCustomEffects();
/* 197 */     this.customEffects = null;
/* 198 */     return changed;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   int applyHash() {
/* 204 */     int original = super.applyHash(), hash = original;
/* 205 */     if (hasCustomEffects()) {
/* 206 */       hash = 73 * hash + this.customEffects.hashCode();
/*     */     }
/* 208 */     return (original != hash) ? (CraftMetaSuspiciousStew.class.hashCode() ^ hash) : hash;
/*     */   }
/*     */ 
/*     */   
/*     */   boolean equalsCommon(CraftMetaItem meta) {
/* 213 */     if (!super.equalsCommon(meta)) {
/* 214 */       return false;
/*     */     }
/* 216 */     if (meta instanceof CraftMetaSuspiciousStew) {
/* 217 */       CraftMetaSuspiciousStew that = (CraftMetaSuspiciousStew)meta;
/*     */       
/* 219 */       return hasCustomEffects() ? ((that.hasCustomEffects() && this.customEffects.equals(that.customEffects))) : (!that.hasCustomEffects());
/*     */     } 
/* 221 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   boolean notUncommon(CraftMetaItem meta) {
/* 226 */     return (super.notUncommon(meta) && (meta instanceof CraftMetaSuspiciousStew || isStewEmpty()));
/*     */   }
/*     */ 
/*     */   
/*     */   ImmutableMap.Builder<String, Object> serialize(ImmutableMap.Builder<String, Object> builder) {
/* 231 */     super.serialize(builder);
/*     */     
/* 233 */     if (hasCustomEffects()) {
/* 234 */       builder.put(EFFECTS.BUKKIT, ImmutableList.copyOf(this.customEffects));
/*     */     }
/*     */     
/* 237 */     return builder;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\inventory\CraftMetaSuspiciousStew.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
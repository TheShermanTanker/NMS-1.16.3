/*     */ package org.bukkit.craftbukkit.v1_16_R2.inventory;
/*     */ import com.google.common.collect.ImmutableMap;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import net.minecraft.server.v1_16_R2.NBTTagCompound;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.attribute.Attribute;
/*     */ import org.bukkit.attribute.AttributeModifier;
/*     */ import org.bukkit.enchantments.Enchantment;
/*     */ 
/*     */ @DelegateDeserialization(CraftMetaItem.SerializableMeta.class)
/*     */ public class CraftMetaArmorStand extends CraftMetaItem implements ArmorStandMeta {
/*  13 */   static final CraftMetaItem.ItemMetaKey ENTITY_TAG = new CraftMetaItem.ItemMetaKey("EntityTag", "entity-tag");
/*     */   
/*  15 */   static final CraftMetaItem.ItemMetaKey INVISIBLE = new CraftMetaItem.ItemMetaKey("Invisible", "invisible");
/*  16 */   static final CraftMetaItem.ItemMetaKey NO_BASE_PLATE = new CraftMetaItem.ItemMetaKey("NoBasePlate", "no-base-plate");
/*  17 */   static final CraftMetaItem.ItemMetaKey SHOW_ARMS = new CraftMetaItem.ItemMetaKey("ShowArms", "show-arms");
/*  18 */   static final CraftMetaItem.ItemMetaKey SMALL = new CraftMetaItem.ItemMetaKey("Small", "small");
/*  19 */   static final CraftMetaItem.ItemMetaKey MARKER = new CraftMetaItem.ItemMetaKey("Marker", "marker");
/*     */   
/*     */   private boolean invisible;
/*     */   
/*     */   private boolean noBasePlate;
/*     */   private boolean showArms;
/*     */   private boolean small;
/*     */   private boolean marker;
/*     */   NBTTagCompound entityTag;
/*     */   
/*     */   CraftMetaArmorStand(CraftMetaItem meta) {
/*  30 */     super(meta);
/*     */     
/*  32 */     if (!(meta instanceof CraftMetaArmorStand)) {
/*     */       return;
/*     */     }
/*     */     
/*  36 */     CraftMetaArmorStand armorStand = (CraftMetaArmorStand)meta;
/*     */     
/*  38 */     this.invisible = armorStand.invisible;
/*  39 */     this.noBasePlate = armorStand.noBasePlate;
/*  40 */     this.showArms = armorStand.showArms;
/*  41 */     this.small = armorStand.small;
/*  42 */     this.marker = armorStand.marker;
/*     */     
/*  44 */     this.entityTag = armorStand.entityTag;
/*     */   }
/*     */   
/*     */   CraftMetaArmorStand(NBTTagCompound tag) {
/*  48 */     super(tag);
/*     */     
/*  50 */     if (tag.hasKey(ENTITY_TAG.NBT)) {
/*  51 */       this.entityTag = tag.getCompound(ENTITY_TAG.NBT);
/*     */ 
/*     */       
/*  54 */       if (this.entityTag.hasKey(INVISIBLE.NBT)) {
/*  55 */         this.invisible = this.entityTag.getBoolean(INVISIBLE.NBT);
/*     */       }
/*     */       
/*  58 */       if (this.entityTag.hasKey(NO_BASE_PLATE.NBT)) {
/*  59 */         this.noBasePlate = this.entityTag.getBoolean(NO_BASE_PLATE.NBT);
/*     */       }
/*     */       
/*  62 */       if (this.entityTag.hasKey(SHOW_ARMS.NBT)) {
/*  63 */         this.showArms = this.entityTag.getBoolean(SHOW_ARMS.NBT);
/*     */       }
/*     */       
/*  66 */       if (this.entityTag.hasKey(SMALL.NBT)) {
/*  67 */         this.small = this.entityTag.getBoolean(SMALL.NBT);
/*     */       }
/*     */       
/*  70 */       if (this.entityTag.hasKey(MARKER.NBT)) {
/*  71 */         this.marker = this.entityTag.getBoolean(MARKER.NBT);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   CraftMetaArmorStand(Map<String, Object> map) {
/*  78 */     super(map);
/*     */ 
/*     */     
/*  81 */     boolean invis = CraftMetaItem.SerializableMeta.getBoolean(map, INVISIBLE.BUKKIT);
/*  82 */     boolean noBase = CraftMetaItem.SerializableMeta.getBoolean(map, NO_BASE_PLATE.BUKKIT);
/*  83 */     boolean showArms = CraftMetaItem.SerializableMeta.getBoolean(map, SHOW_ARMS.BUKKIT);
/*  84 */     boolean small = CraftMetaItem.SerializableMeta.getBoolean(map, SMALL.BUKKIT);
/*  85 */     boolean marker = CraftMetaItem.SerializableMeta.getBoolean(map, MARKER.BUKKIT);
/*     */     
/*  87 */     this.invisible = invis;
/*  88 */     this.noBasePlate = noBase;
/*  89 */     this.showArms = showArms;
/*  90 */     this.small = small;
/*  91 */     this.marker = marker;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   void deserializeInternal(NBTTagCompound tag, Object context) {
/*  97 */     super.deserializeInternal(tag, context);
/*     */     
/*  99 */     if (tag.hasKey(ENTITY_TAG.NBT)) {
/* 100 */       this.entityTag = tag.getCompound(ENTITY_TAG.NBT);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   void serializeInternal(Map<String, NBTBase> internalTags) {
/* 106 */     if (this.entityTag != null && !this.entityTag.isEmpty()) {
/* 107 */       internalTags.put(ENTITY_TAG.NBT, this.entityTag);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   void applyToItem(NBTTagCompound tag) {
/* 113 */     super.applyToItem(tag);
/*     */ 
/*     */     
/* 116 */     if (!isArmorStandEmpty() && this.entityTag == null) {
/* 117 */       this.entityTag = new NBTTagCompound();
/*     */     }
/*     */     
/* 120 */     if (isInvisible()) {
/* 121 */       this.entityTag.setBoolean(INVISIBLE.NBT, this.invisible);
/*     */     }
/*     */     
/* 124 */     if (hasNoBasePlate()) {
/* 125 */       this.entityTag.setBoolean(NO_BASE_PLATE.NBT, this.noBasePlate);
/*     */     }
/*     */     
/* 128 */     if (shouldShowArms()) {
/* 129 */       this.entityTag.setBoolean(SHOW_ARMS.NBT, this.showArms);
/*     */     }
/*     */     
/* 132 */     if (isSmall()) {
/* 133 */       this.entityTag.setBoolean(SMALL.NBT, this.small);
/*     */     }
/*     */     
/* 136 */     if (isMarker()) {
/* 137 */       this.entityTag.setBoolean(MARKER.NBT, this.marker);
/*     */     }
/*     */ 
/*     */     
/* 141 */     if (this.entityTag != null) {
/* 142 */       tag.set(ENTITY_TAG.NBT, (NBTBase)this.entityTag);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   boolean applicableTo(Material type) {
/* 148 */     switch (type) {
/*     */       case ARMOR_STAND:
/* 150 */         return true;
/*     */     } 
/* 152 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   boolean isEmpty() {
/* 158 */     return (super.isEmpty() && isArmorStandEmpty());
/*     */   }
/*     */   
/*     */   boolean isArmorStandEmpty() {
/* 162 */     return (!isInvisible() && !hasNoBasePlate() && !shouldShowArms() && !isSmall() && !isMarker() && this.entityTag == null);
/*     */   }
/*     */ 
/*     */   
/*     */   boolean equalsCommon(CraftMetaItem meta) {
/* 167 */     if (!super.equalsCommon(meta)) {
/* 168 */       return false;
/*     */     }
/* 170 */     if (meta instanceof CraftMetaArmorStand) {
/* 171 */       CraftMetaArmorStand that = (CraftMetaArmorStand)meta;
/*     */ 
/*     */       
/* 174 */       return (this.invisible == that.invisible && this.noBasePlate == that.noBasePlate && this.showArms == that.showArms && this.small == that.small && this.marker == that.marker);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 181 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   boolean notUncommon(CraftMetaItem meta) {
/* 186 */     return (super.notUncommon(meta) && (meta instanceof CraftMetaArmorStand || isArmorStandEmpty()));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   int applyHash() {
/* 192 */     int original = super.applyHash(), hash = original;
/*     */ 
/*     */     
/* 195 */     hash += (this.entityTag != null) ? (73 * hash + this.entityTag.hashCode()) : 0;
/* 196 */     hash += isInvisible() ? (61 * hash + 1231) : 0;
/* 197 */     hash += hasNoBasePlate() ? (61 * hash + 1231) : 0;
/* 198 */     hash += shouldShowArms() ? (61 * hash + 1231) : 0;
/* 199 */     hash += isSmall() ? (61 * hash + 1231) : 0;
/* 200 */     hash += isMarker() ? (61 * hash + 1231) : 0;
/*     */ 
/*     */     
/* 203 */     return (original != hash) ? (CraftMetaArmorStand.class.hashCode() ^ hash) : hash;
/*     */   }
/*     */ 
/*     */   
/*     */   ImmutableMap.Builder<String, Object> serialize(ImmutableMap.Builder<String, Object> builder) {
/* 208 */     super.serialize(builder);
/*     */ 
/*     */     
/* 211 */     if (isInvisible()) {
/* 212 */       builder.put(INVISIBLE.BUKKIT, Boolean.valueOf(this.invisible));
/*     */     }
/*     */     
/* 215 */     if (hasNoBasePlate()) {
/* 216 */       builder.put(NO_BASE_PLATE.BUKKIT, Boolean.valueOf(this.noBasePlate));
/*     */     }
/*     */     
/* 219 */     if (shouldShowArms()) {
/* 220 */       builder.put(SHOW_ARMS.BUKKIT, Boolean.valueOf(this.showArms));
/*     */     }
/*     */     
/* 223 */     if (isSmall()) {
/* 224 */       builder.put(SMALL.BUKKIT, Boolean.valueOf(this.small));
/*     */     }
/*     */     
/* 227 */     if (isMarker()) {
/* 228 */       builder.put(MARKER.BUKKIT, Boolean.valueOf(this.marker));
/*     */     }
/*     */ 
/*     */     
/* 232 */     return builder;
/*     */   }
/*     */ 
/*     */   
/*     */   public CraftMetaArmorStand clone() {
/* 237 */     CraftMetaArmorStand clone = (CraftMetaArmorStand)super.clone();
/*     */     
/* 239 */     if (this.entityTag != null) {
/* 240 */       clone.entityTag = this.entityTag.clone();
/*     */     }
/*     */     
/* 243 */     return clone;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isInvisible() {
/* 249 */     return this.invisible;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasNoBasePlate() {
/* 254 */     return this.noBasePlate;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean shouldShowArms() {
/* 259 */     return this.showArms;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isSmall() {
/* 264 */     return this.small;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isMarker() {
/* 269 */     return this.marker;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setInvisible(boolean invisible) {
/* 274 */     this.invisible = invisible;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setNoBasePlate(boolean noBasePlate) {
/* 279 */     this.noBasePlate = noBasePlate;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setShowArms(boolean showArms) {
/* 284 */     this.showArms = showArms;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSmall(boolean small) {
/* 289 */     this.small = small;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMarker(boolean marker) {
/* 294 */     this.marker = marker;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\inventory\CraftMetaArmorStand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
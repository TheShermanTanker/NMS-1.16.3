/*     */ package org.bukkit.craftbukkit.v1_16_R2.inventory;
/*     */ import com.google.common.collect.ImmutableMap;
/*     */ import java.util.Map;
/*     */ import net.minecraft.server.v1_16_R2.NBTBase;
/*     */ import net.minecraft.server.v1_16_R2.NBTTagCompound;
/*     */ import org.bukkit.DyeColor;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.configuration.serialization.DelegateDeserialization;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.entity.CraftTropicalFish;
/*     */ import org.bukkit.entity.TropicalFish;
/*     */ import org.bukkit.inventory.meta.Damageable;
/*     */ import org.bukkit.inventory.meta.ItemMeta;
/*     */ import org.bukkit.inventory.meta.Repairable;
/*     */ import org.bukkit.inventory.meta.TropicalFishBucketMeta;
/*     */ 
/*     */ @DelegateDeserialization(CraftMetaItem.SerializableMeta.class)
/*     */ class CraftMetaTropicalFishBucket extends CraftMetaItem implements TropicalFishBucketMeta {
/*  18 */   static final CraftMetaItem.ItemMetaKey VARIANT = new CraftMetaItem.ItemMetaKey("BucketVariantTag", "fish-variant");
/*  19 */   static final CraftMetaItem.ItemMetaKey ENTITY_TAG = new CraftMetaItem.ItemMetaKey("EntityTag", "entity-tag");
/*     */   
/*     */   private Integer variant;
/*     */   private NBTTagCompound entityTag;
/*     */   
/*     */   CraftMetaTropicalFishBucket(CraftMetaItem meta) {
/*  25 */     super(meta);
/*     */     
/*  27 */     if (!(meta instanceof CraftMetaTropicalFishBucket)) {
/*     */       return;
/*     */     }
/*     */     
/*  31 */     CraftMetaTropicalFishBucket bucket = (CraftMetaTropicalFishBucket)meta;
/*  32 */     this.variant = bucket.variant;
/*     */   }
/*     */   
/*     */   CraftMetaTropicalFishBucket(NBTTagCompound tag) {
/*  36 */     super(tag);
/*     */     
/*  38 */     if (tag.hasKeyOfType(VARIANT.NBT, 3)) {
/*  39 */       this.variant = Integer.valueOf(tag.getInt(VARIANT.NBT));
/*     */     }
/*     */     
/*  42 */     if (tag.hasKey(ENTITY_TAG.NBT)) {
/*  43 */       this.entityTag = tag.getCompound(ENTITY_TAG.NBT);
/*     */     }
/*     */   }
/*     */   
/*     */   CraftMetaTropicalFishBucket(Map<String, Object> map) {
/*  48 */     super(map);
/*     */     
/*  50 */     Integer variant = CraftMetaItem.SerializableMeta.<Integer>getObject(Integer.class, map, VARIANT.BUKKIT, true);
/*  51 */     if (variant != null) {
/*  52 */       this.variant = variant;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   void deserializeInternal(NBTTagCompound tag, Object context) {
/*  58 */     super.deserializeInternal(tag, context);
/*     */     
/*  60 */     if (tag.hasKey(ENTITY_TAG.NBT)) {
/*  61 */       this.entityTag = tag.getCompound(ENTITY_TAG.NBT);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   void serializeInternal(Map<String, NBTBase> internalTags) {
/*  67 */     if (this.entityTag != null && !this.entityTag.isEmpty()) {
/*  68 */       internalTags.put(ENTITY_TAG.NBT, this.entityTag);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   void applyToItem(NBTTagCompound tag) {
/*  74 */     super.applyToItem(tag);
/*     */     
/*  76 */     if (hasVariant()) {
/*  77 */       tag.setInt(VARIANT.NBT, this.variant.intValue());
/*     */     }
/*     */     
/*  80 */     if (this.entityTag != null) {
/*  81 */       tag.set(ENTITY_TAG.NBT, (NBTBase)this.entityTag);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   boolean applicableTo(Material type) {
/*  87 */     switch (type) {
/*     */       case TROPICAL_FISH_BUCKET:
/*  89 */         return true;
/*     */     } 
/*  91 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   boolean isEmpty() {
/*  97 */     return (super.isEmpty() && isBucketEmpty());
/*     */   }
/*     */   
/*     */   boolean isBucketEmpty() {
/* 101 */     return (!hasVariant() && this.entityTag == null);
/*     */   }
/*     */ 
/*     */   
/*     */   public DyeColor getPatternColor() {
/* 106 */     return CraftTropicalFish.getPatternColor(this.variant.intValue());
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPatternColor(DyeColor color) {
/* 111 */     if (this.variant == null) {
/* 112 */       this.variant = Integer.valueOf(0);
/*     */     }
/* 114 */     this.variant = Integer.valueOf(CraftTropicalFish.getData(color, getPatternColor(), getPattern()));
/*     */   }
/*     */ 
/*     */   
/*     */   public DyeColor getBodyColor() {
/* 119 */     return CraftTropicalFish.getBodyColor(this.variant.intValue());
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBodyColor(DyeColor color) {
/* 124 */     if (this.variant == null) {
/* 125 */       this.variant = Integer.valueOf(0);
/*     */     }
/* 127 */     this.variant = Integer.valueOf(CraftTropicalFish.getData(getPatternColor(), color, getPattern()));
/*     */   }
/*     */ 
/*     */   
/*     */   public TropicalFish.Pattern getPattern() {
/* 132 */     return CraftTropicalFish.getPattern(this.variant.intValue());
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPattern(TropicalFish.Pattern pattern) {
/* 137 */     if (this.variant == null) {
/* 138 */       this.variant = Integer.valueOf(0);
/*     */     }
/* 140 */     this.variant = Integer.valueOf(CraftTropicalFish.getData(getPatternColor(), getBodyColor(), pattern));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasVariant() {
/* 145 */     return (this.variant != null);
/*     */   }
/*     */ 
/*     */   
/*     */   boolean equalsCommon(CraftMetaItem meta) {
/* 150 */     if (!super.equalsCommon(meta)) {
/* 151 */       return false;
/*     */     }
/* 153 */     if (meta instanceof CraftMetaTropicalFishBucket) {
/* 154 */       CraftMetaTropicalFishBucket that = (CraftMetaTropicalFishBucket)meta;
/*     */       
/* 156 */       if (hasVariant() ? (that.hasVariant() && this.variant.equals(that.variant)) : !that.hasVariant()) if (this.entityTag != null) return 
/* 157 */             (that.entityTag != null && this.entityTag.equals(that.entityTag));   return (this.entityTag == null);
/*     */     } 
/* 159 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   boolean notUncommon(CraftMetaItem meta) {
/* 164 */     return (super.notUncommon(meta) && (meta instanceof CraftMetaTropicalFishBucket || isBucketEmpty()));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   int applyHash() {
/* 170 */     int original = super.applyHash(), hash = original;
/*     */     
/* 172 */     if (hasVariant()) {
/* 173 */       hash = 61 * hash + this.variant.intValue();
/*     */     }
/* 175 */     if (this.entityTag != null) {
/* 176 */       hash = 61 * hash + this.entityTag.hashCode();
/*     */     }
/*     */     
/* 179 */     return (original != hash) ? (CraftMetaTropicalFishBucket.class.hashCode() ^ hash) : hash;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public CraftMetaTropicalFishBucket clone() {
/* 185 */     CraftMetaTropicalFishBucket clone = (CraftMetaTropicalFishBucket)super.clone();
/*     */     
/* 187 */     if (this.entityTag != null) {
/* 188 */       clone.entityTag = this.entityTag.clone();
/*     */     }
/*     */     
/* 191 */     return clone;
/*     */   }
/*     */ 
/*     */   
/*     */   ImmutableMap.Builder<String, Object> serialize(ImmutableMap.Builder<String, Object> builder) {
/* 196 */     super.serialize(builder);
/*     */     
/* 198 */     if (hasVariant()) {
/* 199 */       builder.put(VARIANT.BUKKIT, this.variant);
/*     */     }
/*     */     
/* 202 */     return builder;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\inventory\CraftMetaTropicalFishBucket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
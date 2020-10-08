/*     */ package org.bukkit.craftbukkit.v1_16_R2.inventory;
/*     */ import java.util.Set;
/*     */ import javax.annotation.Nonnull;
/*     */ import net.minecraft.server.v1_16_R2.NBTTagCompound;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.attribute.Attribute;
/*     */ import org.bukkit.attribute.AttributeModifier;
/*     */ import org.bukkit.enchantments.Enchantment;
/*     */ import org.bukkit.inventory.ItemFlag;
/*     */ 
/*     */ @DelegateDeserialization(CraftMetaItem.SerializableMeta.class)
/*     */ public class CraftMetaEntityTag extends CraftMetaItem {
/*  13 */   static final CraftMetaItem.ItemMetaKey ENTITY_TAG = new CraftMetaItem.ItemMetaKey("EntityTag", "entity-tag");
/*     */   NBTTagCompound entityTag;
/*     */   
/*     */   CraftMetaEntityTag(CraftMetaItem meta) {
/*  17 */     super(meta);
/*     */     
/*  19 */     if (!(meta instanceof CraftMetaEntityTag)) {
/*     */       return;
/*     */     }
/*     */     
/*  23 */     CraftMetaEntityTag entity = (CraftMetaEntityTag)meta;
/*  24 */     this.entityTag = entity.entityTag;
/*     */   }
/*     */   
/*     */   CraftMetaEntityTag(NBTTagCompound tag) {
/*  28 */     super(tag);
/*     */     
/*  30 */     if (tag.hasKey(ENTITY_TAG.NBT)) {
/*  31 */       this.entityTag = tag.getCompound(ENTITY_TAG.NBT);
/*     */     }
/*     */   }
/*     */   
/*     */   CraftMetaEntityTag(Map<String, Object> map) {
/*  36 */     super(map);
/*     */   }
/*     */ 
/*     */   
/*     */   void deserializeInternal(NBTTagCompound tag, Object context) {
/*  41 */     super.deserializeInternal(tag, context);
/*     */     
/*  43 */     if (tag.hasKey(ENTITY_TAG.NBT)) {
/*  44 */       this.entityTag = tag.getCompound(ENTITY_TAG.NBT);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   void serializeInternal(Map<String, NBTBase> internalTags) {
/*  50 */     if (this.entityTag != null && !this.entityTag.isEmpty()) {
/*  51 */       internalTags.put(ENTITY_TAG.NBT, this.entityTag);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   void applyToItem(NBTTagCompound tag) {
/*  57 */     super.applyToItem(tag);
/*     */     
/*  59 */     if (this.entityTag != null) {
/*  60 */       tag.set(ENTITY_TAG.NBT, (NBTBase)this.entityTag);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   boolean applicableTo(Material type) {
/*  66 */     switch (type) {
/*     */       case COD_BUCKET:
/*     */       case PUFFERFISH_BUCKET:
/*     */       case SALMON_BUCKET:
/*     */       case ITEM_FRAME:
/*     */       case PAINTING:
/*  72 */         return true;
/*     */     } 
/*  74 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   boolean isEmpty() {
/*  80 */     return (super.isEmpty() && isEntityTagEmpty());
/*     */   }
/*     */   
/*     */   boolean isEntityTagEmpty() {
/*  84 */     return (this.entityTag == null);
/*     */   }
/*     */ 
/*     */   
/*     */   boolean equalsCommon(CraftMetaItem meta) {
/*  89 */     if (!super.equalsCommon(meta)) {
/*  90 */       return false;
/*     */     }
/*  92 */     if (meta instanceof CraftMetaEntityTag) {
/*  93 */       CraftMetaEntityTag that = (CraftMetaEntityTag)meta;
/*     */       
/*  95 */       return (this.entityTag != null) ? ((that.entityTag != null && this.entityTag.equals(that.entityTag))) : ((this.entityTag == null));
/*     */     } 
/*  97 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   boolean notUncommon(CraftMetaItem meta) {
/* 102 */     return (super.notUncommon(meta) && (meta instanceof CraftMetaEntityTag || isEntityTagEmpty()));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   int applyHash() {
/* 108 */     int original = super.applyHash(), hash = original;
/*     */     
/* 110 */     if (this.entityTag != null) {
/* 111 */       hash = 73 * hash + this.entityTag.hashCode();
/*     */     }
/*     */     
/* 114 */     return (original != hash) ? (CraftMetaEntityTag.class.hashCode() ^ hash) : hash;
/*     */   }
/*     */ 
/*     */   
/*     */   ImmutableMap.Builder<String, Object> serialize(ImmutableMap.Builder<String, Object> builder) {
/* 119 */     super.serialize(builder);
/*     */     
/* 121 */     return builder;
/*     */   }
/*     */ 
/*     */   
/*     */   public CraftMetaEntityTag clone() {
/* 126 */     CraftMetaEntityTag clone = (CraftMetaEntityTag)super.clone();
/*     */     
/* 128 */     if (this.entityTag != null) {
/* 129 */       clone.entityTag = this.entityTag.clone();
/*     */     }
/*     */     
/* 132 */     return clone;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\inventory\CraftMetaEntityTag.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
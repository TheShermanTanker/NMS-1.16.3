/*     */ package org.bukkit.craftbukkit.v1_16_R2.inventory;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import javax.annotation.Nonnull;
/*     */ import net.md_5.bungee.api.chat.BaseComponent;
/*     */ import net.minecraft.server.v1_16_R2.NBTTagCompound;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.attribute.Attribute;
/*     */ import org.bukkit.attribute.AttributeModifier;
/*     */ import org.bukkit.enchantments.Enchantment;
/*     */ import org.bukkit.entity.EntityType;
/*     */ import org.bukkit.inventory.ItemFlag;
/*     */ 
/*     */ @DelegateDeserialization(CraftMetaItem.SerializableMeta.class)
/*     */ public class CraftMetaSpawnEgg extends CraftMetaItem implements SpawnEggMeta {
/*  18 */   static final CraftMetaItem.ItemMetaKey ENTITY_TAG = new CraftMetaItem.ItemMetaKey("EntityTag", "entity-tag");
/*     */   
/*  20 */   static final CraftMetaItem.ItemMetaKey ENTITY_ID = new CraftMetaItem.ItemMetaKey("id");
/*     */   
/*     */   private EntityType spawnedType;
/*     */   private NBTTagCompound entityTag;
/*     */   
/*     */   CraftMetaSpawnEgg(CraftMetaItem meta) {
/*  26 */     super(meta);
/*     */     
/*  28 */     if (!(meta instanceof CraftMetaSpawnEgg)) {
/*     */       return;
/*     */     }
/*     */     
/*  32 */     CraftMetaSpawnEgg egg = (CraftMetaSpawnEgg)meta;
/*  33 */     this.spawnedType = egg.spawnedType;
/*     */     
/*  35 */     updateMaterial(null);
/*     */   }
/*     */   
/*     */   CraftMetaSpawnEgg(NBTTagCompound tag) {
/*  39 */     super(tag);
/*     */     
/*  41 */     if (tag.hasKey(ENTITY_TAG.NBT)) {
/*  42 */       this.entityTag = tag.getCompound(ENTITY_TAG.NBT);
/*     */     }
/*     */   }
/*     */   
/*     */   CraftMetaSpawnEgg(Map<String, Object> map) {
/*  47 */     super(map);
/*     */     
/*  49 */     String entityType = CraftMetaItem.SerializableMeta.getString(map, ENTITY_ID.BUKKIT, true);
/*  50 */     if (entityType != null) {
/*  51 */       this.spawnedType = EntityType.fromName(entityType);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   void deserializeInternal(NBTTagCompound tag, Object context) {
/*  57 */     super.deserializeInternal(tag, context);
/*     */     
/*  59 */     if (tag.hasKey(ENTITY_TAG.NBT)) {
/*  60 */       this.entityTag = tag.getCompound(ENTITY_TAG.NBT);
/*     */       
/*  62 */       if (context instanceof Map) {
/*  63 */         Map<String, Object> map = (Map<String, Object>)context;
/*     */ 
/*     */         
/*  66 */         String entityType = CraftMetaItem.SerializableMeta.getString(map, ENTITY_ID.BUKKIT, true);
/*  67 */         if (entityType != null) {
/*  68 */           this.spawnedType = EntityType.fromName(entityType);
/*     */         }
/*     */       } 
/*     */       
/*  72 */       if (this.spawnedType != null)
/*     */       {
/*  74 */         this.entityTag.remove(ENTITY_ID.NBT);
/*     */       }
/*     */ 
/*     */       
/*  78 */       if (!this.entityTag.isEmpty());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  84 */       if (this.entityTag.hasKey(ENTITY_ID.NBT)) {
/*  85 */         this.spawnedType = EntityType.fromName((new MinecraftKey(this.entityTag.getString(ENTITY_ID.NBT))).getKey());
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   void serializeInternal(Map<String, NBTBase> internalTags) {
/*  92 */     if (this.entityTag != null && !this.entityTag.isEmpty()) {
/*  93 */       internalTags.put(ENTITY_TAG.NBT, this.entityTag);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   void applyToItem(NBTTagCompound tag) {
/*  99 */     super.applyToItem(tag);
/*     */     
/* 101 */     if (!isSpawnEggEmpty() && this.entityTag == null) {
/* 102 */       this.entityTag = new NBTTagCompound();
/*     */     }
/*     */     
/* 105 */     if (this.entityTag != null) {
/* 106 */       tag.set(ENTITY_TAG.NBT, (NBTBase)this.entityTag);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   boolean applicableTo(Material type) {
/* 112 */     switch (type) {
/*     */       case BAT_SPAWN_EGG:
/*     */       case BEE_SPAWN_EGG:
/*     */       case BLAZE_SPAWN_EGG:
/*     */       case CAT_SPAWN_EGG:
/*     */       case CAVE_SPIDER_SPAWN_EGG:
/*     */       case CHICKEN_SPAWN_EGG:
/*     */       case COD_SPAWN_EGG:
/*     */       case COW_SPAWN_EGG:
/*     */       case CREEPER_SPAWN_EGG:
/*     */       case DOLPHIN_SPAWN_EGG:
/*     */       case DONKEY_SPAWN_EGG:
/*     */       case DROWNED_SPAWN_EGG:
/*     */       case ELDER_GUARDIAN_SPAWN_EGG:
/*     */       case ENDERMAN_SPAWN_EGG:
/*     */       case ENDERMITE_SPAWN_EGG:
/*     */       case EVOKER_SPAWN_EGG:
/*     */       case FOX_SPAWN_EGG:
/*     */       case GHAST_SPAWN_EGG:
/*     */       case GUARDIAN_SPAWN_EGG:
/*     */       case HOGLIN_SPAWN_EGG:
/*     */       case HORSE_SPAWN_EGG:
/*     */       case HUSK_SPAWN_EGG:
/*     */       case LLAMA_SPAWN_EGG:
/*     */       case MAGMA_CUBE_SPAWN_EGG:
/*     */       case MOOSHROOM_SPAWN_EGG:
/*     */       case MULE_SPAWN_EGG:
/*     */       case OCELOT_SPAWN_EGG:
/*     */       case PANDA_SPAWN_EGG:
/*     */       case PARROT_SPAWN_EGG:
/*     */       case PHANTOM_SPAWN_EGG:
/*     */       case PIGLIN_SPAWN_EGG:
/*     */       case PIG_SPAWN_EGG:
/*     */       case PILLAGER_SPAWN_EGG:
/*     */       case POLAR_BEAR_SPAWN_EGG:
/*     */       case PUFFERFISH_SPAWN_EGG:
/*     */       case RABBIT_SPAWN_EGG:
/*     */       case RAVAGER_SPAWN_EGG:
/*     */       case SALMON_SPAWN_EGG:
/*     */       case SHEEP_SPAWN_EGG:
/*     */       case SHULKER_SPAWN_EGG:
/*     */       case SILVERFISH_SPAWN_EGG:
/*     */       case SKELETON_HORSE_SPAWN_EGG:
/*     */       case SKELETON_SPAWN_EGG:
/*     */       case SLIME_SPAWN_EGG:
/*     */       case SPIDER_SPAWN_EGG:
/*     */       case SQUID_SPAWN_EGG:
/*     */       case STRAY_SPAWN_EGG:
/*     */       case STRIDER_SPAWN_EGG:
/*     */       case TRADER_LLAMA_SPAWN_EGG:
/*     */       case TROPICAL_FISH_SPAWN_EGG:
/*     */       case TURTLE_SPAWN_EGG:
/*     */       case VEX_SPAWN_EGG:
/*     */       case VILLAGER_SPAWN_EGG:
/*     */       case VINDICATOR_SPAWN_EGG:
/*     */       case WANDERING_TRADER_SPAWN_EGG:
/*     */       case WITCH_SPAWN_EGG:
/*     */       case WITHER_SKELETON_SPAWN_EGG:
/*     */       case WOLF_SPAWN_EGG:
/*     */       case ZOGLIN_SPAWN_EGG:
/*     */       case ZOMBIE_HORSE_SPAWN_EGG:
/*     */       case ZOMBIE_SPAWN_EGG:
/*     */       case ZOMBIE_VILLAGER_SPAWN_EGG:
/*     */       case ZOMBIFIED_PIGLIN_SPAWN_EGG:
/* 176 */         return true;
/*     */     } 
/* 178 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   boolean isEmpty() {
/* 184 */     return (super.isEmpty() && isSpawnEggEmpty());
/*     */   }
/*     */   
/*     */   boolean isSpawnEggEmpty() {
/* 188 */     return (!hasSpawnedType() && this.entityTag == null);
/*     */   }
/*     */   
/*     */   boolean hasSpawnedType() {
/* 192 */     return (this.spawnedType != null);
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityType getSpawnedType() {
/* 197 */     throw new UnsupportedOperationException("Must check item type to get spawned type");
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSpawnedType(EntityType type) {
/* 202 */     throw new UnsupportedOperationException("Must change item type to set spawned type");
/*     */   }
/*     */ 
/*     */   
/*     */   boolean equalsCommon(CraftMetaItem meta) {
/* 207 */     if (!super.equalsCommon(meta)) {
/* 208 */       return false;
/*     */     }
/* 210 */     if (meta instanceof CraftMetaSpawnEgg) {
/* 211 */       CraftMetaSpawnEgg that = (CraftMetaSpawnEgg)meta;
/*     */       
/* 213 */       return hasSpawnedType() ? ((that.hasSpawnedType() && this.spawnedType.equals(that.spawnedType))) : (
/* 214 */         (!that.hasSpawnedType() && this.entityTag != null) ? ((that.entityTag != null && this.entityTag.equals(that.entityTag))) : ((this.entityTag == null)));
/*     */     } 
/* 216 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   boolean notUncommon(CraftMetaItem meta) {
/* 221 */     return (super.notUncommon(meta) && (meta instanceof CraftMetaSpawnEgg || isSpawnEggEmpty()));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   int applyHash() {
/* 227 */     int original = super.applyHash(), hash = original;
/*     */     
/* 229 */     if (hasSpawnedType()) {
/* 230 */       hash = 73 * hash + this.spawnedType.hashCode();
/*     */     }
/* 232 */     if (this.entityTag != null) {
/* 233 */       hash = 73 * hash + this.entityTag.hashCode();
/*     */     }
/*     */     
/* 236 */     return (original != hash) ? (CraftMetaSpawnEgg.class.hashCode() ^ hash) : hash;
/*     */   }
/*     */ 
/*     */   
/*     */   ImmutableMap.Builder<String, Object> serialize(ImmutableMap.Builder<String, Object> builder) {
/* 241 */     super.serialize(builder);
/*     */     
/* 243 */     return builder;
/*     */   }
/*     */ 
/*     */   
/*     */   public CraftMetaSpawnEgg clone() {
/* 248 */     CraftMetaSpawnEgg clone = (CraftMetaSpawnEgg)super.clone();
/*     */     
/* 250 */     clone.spawnedType = this.spawnedType;
/* 251 */     if (this.entityTag != null) {
/* 252 */       clone.entityTag = this.entityTag.clone();
/*     */     }
/*     */     
/* 255 */     return clone;
/*     */   }
/*     */ 
/*     */   
/*     */   final Material updateMaterial(Material material) {
/* 260 */     if (this.spawnedType == null) {
/* 261 */       this.spawnedType = EntityType.fromId(getDamage());
/* 262 */       setDamage(0);
/*     */     } 
/*     */     
/* 265 */     if (this.spawnedType != null) {
/* 266 */       if (this.entityTag != null)
/*     */       {
/* 268 */         this.entityTag.remove(ENTITY_ID.NBT);
/*     */       }
/*     */       
/* 271 */       return CraftLegacy.fromLegacy(new MaterialData(Material.LEGACY_MONSTER_EGG, (byte)this.spawnedType.getTypeId()));
/*     */     } 
/*     */     
/* 274 */     return super.updateMaterial(material);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\inventory\CraftMetaSpawnEgg.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
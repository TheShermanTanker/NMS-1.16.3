/*      */ package org.bukkit.craftbukkit.v1_16_R2.inventory;
/*      */ 
/*      */ import com.destroystokyo.paper.Namespaced;
/*      */ import com.destroystokyo.paper.NamespacedTag;
/*      */ import com.google.common.base.Preconditions;
/*      */ import com.google.common.base.Strings;
/*      */ import com.google.common.collect.ImmutableList;
/*      */ import com.google.common.collect.ImmutableMap;
/*      */ import com.google.common.collect.ImmutableMultimap;
/*      */ import com.google.common.collect.ImmutableSortedMap;
/*      */ import com.google.common.collect.LinkedHashMultimap;
/*      */ import com.google.common.collect.Lists;
/*      */ import com.google.common.collect.Multimap;
/*      */ import com.google.common.collect.Sets;
/*      */ import com.google.common.collect.UnmodifiableIterator;
/*      */ import com.google.gson.JsonParseException;
/*      */ import com.mojang.brigadier.StringReader;
/*      */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*      */ import java.io.ByteArrayInputStream;
/*      */ import java.io.ByteArrayOutputStream;
/*      */ import java.io.IOException;
/*      */ import java.lang.annotation.ElementType;
/*      */ import java.lang.annotation.Retention;
/*      */ import java.lang.annotation.RetentionPolicy;
/*      */ import java.lang.annotation.Target;
/*      */ import java.lang.reflect.Constructor;
/*      */ import java.lang.reflect.InvocationTargetException;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Arrays;
/*      */ import java.util.Collection;
/*      */ import java.util.Collections;
/*      */ import java.util.Comparator;
/*      */ import java.util.EnumSet;
/*      */ import java.util.HashMap;
/*      */ import java.util.HashSet;
/*      */ import java.util.Iterator;
/*      */ import java.util.LinkedHashMap;
/*      */ import java.util.List;
/*      */ import java.util.Locale;
/*      */ import java.util.Map;
/*      */ import java.util.NoSuchElementException;
/*      */ import java.util.Set;
/*      */ import java.util.TreeMap;
/*      */ import java.util.logging.Level;
/*      */ import java.util.logging.Logger;
/*      */ import java.util.stream.Collectors;
/*      */ import javax.annotation.Nonnull;
/*      */ import javax.annotation.Nullable;
/*      */ import net.md_5.bungee.api.chat.BaseComponent;
/*      */ import net.md_5.bungee.chat.ComponentSerializer;
/*      */ import net.minecraft.server.v1_16_R2.ArgumentBlock;
/*      */ import net.minecraft.server.v1_16_R2.AttributeModifier;
/*      */ import net.minecraft.server.v1_16_R2.ChatComponentText;
/*      */ import net.minecraft.server.v1_16_R2.EnumItemSlot;
/*      */ import net.minecraft.server.v1_16_R2.IChatBaseComponent;
/*      */ import net.minecraft.server.v1_16_R2.ItemBlock;
/*      */ import net.minecraft.server.v1_16_R2.MinecraftKey;
/*      */ import net.minecraft.server.v1_16_R2.NBTBase;
/*      */ import net.minecraft.server.v1_16_R2.NBTCompressedStreamTools;
/*      */ import net.minecraft.server.v1_16_R2.NBTTagCompound;
/*      */ import net.minecraft.server.v1_16_R2.NBTTagList;
/*      */ import net.minecraft.server.v1_16_R2.NBTTagString;
/*      */ import org.apache.commons.lang.Validate;
/*      */ import org.bukkit.Bukkit;
/*      */ import org.bukkit.Material;
/*      */ import org.bukkit.NamespacedKey;
/*      */ import org.bukkit.attribute.Attribute;
/*      */ import org.bukkit.attribute.AttributeModifier;
/*      */ import org.bukkit.block.data.BlockData;
/*      */ import org.bukkit.configuration.serialization.ConfigurationSerializable;
/*      */ import org.bukkit.configuration.serialization.DelegateDeserialization;
/*      */ import org.bukkit.configuration.serialization.SerializableAs;
/*      */ import org.bukkit.craftbukkit.libs.org.apache.commons.codec.binary.Base64;
/*      */ import org.bukkit.craftbukkit.libs.org.apache.commons.lang3.EnumUtils;
/*      */ import org.bukkit.craftbukkit.v1_16_R2.CraftEquipmentSlot;
/*      */ import org.bukkit.craftbukkit.v1_16_R2.Overridden;
/*      */ import org.bukkit.craftbukkit.v1_16_R2.attribute.CraftAttributeInstance;
/*      */ import org.bukkit.craftbukkit.v1_16_R2.attribute.CraftAttributeMap;
/*      */ import org.bukkit.craftbukkit.v1_16_R2.block.data.CraftBlockData;
/*      */ import org.bukkit.craftbukkit.v1_16_R2.inventory.tags.DeprecatedCustomTagContainer;
/*      */ import org.bukkit.craftbukkit.v1_16_R2.persistence.CraftPersistentDataContainer;
/*      */ import org.bukkit.craftbukkit.v1_16_R2.persistence.CraftPersistentDataTypeRegistry;
/*      */ import org.bukkit.craftbukkit.v1_16_R2.util.CraftChatMessage;
/*      */ import org.bukkit.craftbukkit.v1_16_R2.util.CraftMagicNumbers;
/*      */ import org.bukkit.craftbukkit.v1_16_R2.util.CraftNBTTagConfigSerializer;
/*      */ import org.bukkit.craftbukkit.v1_16_R2.util.CraftNamespacedKey;
/*      */ import org.bukkit.enchantments.Enchantment;
/*      */ import org.bukkit.inventory.EquipmentSlot;
/*      */ import org.bukkit.inventory.ItemFlag;
/*      */ import org.bukkit.inventory.meta.BlockDataMeta;
/*      */ import org.bukkit.inventory.meta.Damageable;
/*      */ import org.bukkit.inventory.meta.ItemMeta;
/*      */ import org.bukkit.inventory.meta.Repairable;
/*      */ import org.bukkit.inventory.meta.tags.CustomItemTagContainer;
/*      */ import org.bukkit.persistence.PersistentDataContainer;
/*      */ import org.spigotmc.ValidateUtils;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ @DelegateDeserialization(CraftMetaItem.SerializableMeta.class)
/*      */ class CraftMetaItem
/*      */   implements ItemMeta, Damageable, Repairable, BlockDataMeta
/*      */ {
/*      */   static class ItemMetaKey
/*      */   {
/*      */     final String BUKKIT;
/*      */     final String NBT;
/*      */     
/*      */     @Retention(RetentionPolicy.SOURCE)
/*      */     @Target({ElementType.FIELD})
/*      */     static @interface Specific
/*      */     {
/*      */       To value();
/*      */       
/*      */       public enum To
/*      */       {
/*  125 */         BUKKIT,
/*  126 */         NBT;
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     ItemMetaKey(String both) {
/*  136 */       this(both, both);
/*      */     }
/*      */     
/*      */     ItemMetaKey(String nbt, String bukkit) {
/*  140 */       this.NBT = nbt;
/*  141 */       this.BUKKIT = bukkit;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   @SerializableAs("ItemMeta")
/*      */   public static final class SerializableMeta
/*      */     implements ConfigurationSerializable
/*      */   {
/*      */     static final String TYPE_FIELD = "meta-type";
/*      */     
/*  153 */     static final ImmutableMap<Class<? extends CraftMetaItem>, String> classMap = ImmutableMap.builder()
/*  154 */       .put(CraftMetaArmorStand.class, "ARMOR_STAND")
/*  155 */       .put(CraftMetaBanner.class, "BANNER")
/*  156 */       .put(CraftMetaBlockState.class, "TILE_ENTITY")
/*  157 */       .put(CraftMetaBook.class, "BOOK")
/*  158 */       .put(CraftMetaBookSigned.class, "BOOK_SIGNED")
/*  159 */       .put(CraftMetaSkull.class, "SKULL")
/*  160 */       .put(CraftMetaLeatherArmor.class, "LEATHER_ARMOR")
/*  161 */       .put(CraftMetaMap.class, "MAP")
/*  162 */       .put(CraftMetaPotion.class, "POTION")
/*  163 */       .put(CraftMetaSpawnEgg.class, "SPAWN_EGG")
/*  164 */       .put(CraftMetaEnchantedBook.class, "ENCHANTED")
/*  165 */       .put(CraftMetaFirework.class, "FIREWORK")
/*  166 */       .put(CraftMetaCharge.class, "FIREWORK_EFFECT")
/*  167 */       .put(CraftMetaKnowledgeBook.class, "KNOWLEDGE_BOOK")
/*  168 */       .put(CraftMetaTropicalFishBucket.class, "TROPICAL_FISH_BUCKET")
/*  169 */       .put(CraftMetaCrossbow.class, "CROSSBOW")
/*  170 */       .put(CraftMetaSuspiciousStew.class, "SUSPICIOUS_STEW")
/*  171 */       .put(CraftMetaEntityTag.class, "ENTITY_TAG")
/*  172 */       .put(CraftMetaCompass.class, "COMPASS")
/*  173 */       .put(CraftMetaItem.class, "UNSPECIFIC")
/*  174 */       .build(); static final ImmutableMap<String, Constructor<? extends CraftMetaItem>> constructorMap;
/*      */     static {
/*  176 */       ImmutableMap.Builder<String, Constructor<? extends CraftMetaItem>> classConstructorBuilder = ImmutableMap.builder();
/*  177 */       for (UnmodifiableIterator<Map.Entry<Class<? extends CraftMetaItem>, String>> unmodifiableIterator = classMap.entrySet().iterator(); unmodifiableIterator.hasNext(); ) { Map.Entry<Class<? extends CraftMetaItem>, String> mapping = unmodifiableIterator.next();
/*      */         try {
/*  179 */           classConstructorBuilder.put(mapping.getValue(), ((Class)mapping.getKey()).getDeclaredConstructor(new Class[] { Map.class }));
/*  180 */         } catch (NoSuchMethodException e) {
/*  181 */           throw new AssertionError(e);
/*      */         }  }
/*      */       
/*  184 */       constructorMap = classConstructorBuilder.build();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public static ItemMeta deserialize(Map<String, Object> map) throws Throwable {
/*  191 */       Validate.notNull(map, "Cannot deserialize null map");
/*      */       
/*  193 */       String type = getString(map, "meta-type", false);
/*  194 */       Constructor<? extends CraftMetaItem> constructor = (Constructor<? extends CraftMetaItem>)constructorMap.get(type);
/*      */       
/*  196 */       if (constructor == null) {
/*  197 */         throw new IllegalArgumentException(type + " is not a valid " + "meta-type");
/*      */       }
/*      */       
/*      */       try {
/*  201 */         return constructor.newInstance(new Object[] { map });
/*  202 */       } catch (InstantiationException e) {
/*  203 */         throw new AssertionError(e);
/*  204 */       } catch (IllegalAccessException e) {
/*  205 */         throw new AssertionError(e);
/*  206 */       } catch (InvocationTargetException e) {
/*  207 */         throw e.getCause();
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*      */     public Map<String, Object> serialize() {
/*  213 */       throw new AssertionError();
/*      */     }
/*      */     
/*      */     static String getString(Map<?, ?> map, Object field, boolean nullable) {
/*  217 */       return getObject(String.class, map, field, nullable);
/*      */     }
/*      */     
/*      */     static boolean getBoolean(Map<?, ?> map, Object field) {
/*  221 */       Boolean value = getObject(Boolean.class, map, field, true);
/*  222 */       return (value != null && value.booleanValue());
/*      */     }
/*      */     
/*      */     static <T> T getObject(Class<T> clazz, Map<?, ?> map, Object field, boolean nullable) {
/*  226 */       Object object = map.get(field);
/*      */       
/*  228 */       if (clazz.isInstance(object)) {
/*  229 */         return clazz.cast(object);
/*      */       }
/*  231 */       if (object == null) {
/*  232 */         if (!nullable) {
/*  233 */           throw new NoSuchElementException(map + " does not contain " + field);
/*      */         }
/*  235 */         return null;
/*      */       } 
/*  237 */       throw new IllegalArgumentException(field + "(" + object + ") is not a valid " + clazz);
/*      */     }
/*      */   }
/*      */   
/*  241 */   static final ItemMetaKey NAME = new ItemMetaKey("Name", "display-name");
/*  242 */   static final ItemMetaKey LOCNAME = new ItemMetaKey("LocName", "loc-name");
/*      */   
/*  244 */   static final ItemMetaKey DISPLAY = new ItemMetaKey("display");
/*  245 */   static final ItemMetaKey LORE = new ItemMetaKey("Lore", "lore");
/*  246 */   static final ItemMetaKey CUSTOM_MODEL_DATA = new ItemMetaKey("CustomModelData", "custom-model-data");
/*  247 */   static final ItemMetaKey ENCHANTMENTS = new ItemMetaKey("Enchantments", "enchants");
/*      */   
/*  249 */   static final ItemMetaKey ENCHANTMENTS_ID = new ItemMetaKey("id");
/*      */   
/*  251 */   static final ItemMetaKey ENCHANTMENTS_LVL = new ItemMetaKey("lvl");
/*  252 */   static final ItemMetaKey REPAIR = new ItemMetaKey("RepairCost", "repair-cost");
/*  253 */   static final ItemMetaKey ATTRIBUTES = new ItemMetaKey("AttributeModifiers", "attribute-modifiers");
/*      */   
/*  255 */   static final ItemMetaKey ATTRIBUTES_IDENTIFIER = new ItemMetaKey("AttributeName");
/*      */   
/*  257 */   static final ItemMetaKey ATTRIBUTES_NAME = new ItemMetaKey("Name");
/*      */   
/*  259 */   static final ItemMetaKey ATTRIBUTES_VALUE = new ItemMetaKey("Amount");
/*      */   
/*  261 */   static final ItemMetaKey ATTRIBUTES_TYPE = new ItemMetaKey("Operation");
/*      */   
/*  263 */   static final ItemMetaKey ATTRIBUTES_UUID_HIGH = new ItemMetaKey("UUIDMost");
/*      */   
/*  265 */   static final ItemMetaKey ATTRIBUTES_UUID_LOW = new ItemMetaKey("UUIDLeast");
/*      */   
/*  267 */   static final ItemMetaKey ATTRIBUTES_SLOT = new ItemMetaKey("Slot");
/*      */   
/*  269 */   static final ItemMetaKey HIDEFLAGS = new ItemMetaKey("HideFlags", "ItemFlags");
/*      */   
/*  271 */   static final ItemMetaKey UNBREAKABLE = new ItemMetaKey("Unbreakable");
/*      */   
/*  273 */   static final ItemMetaKey DAMAGE = new ItemMetaKey("Damage");
/*      */   
/*  275 */   static final ItemMetaKey BLOCK_DATA = new ItemMetaKey("BlockStateTag");
/*  276 */   static final ItemMetaKey BUKKIT_CUSTOM_TAG = new ItemMetaKey("PublicBukkitValues");
/*      */   
/*  278 */   static final ItemMetaKey CAN_DESTROY = new ItemMetaKey("CanDestroy");
/*  279 */   static final ItemMetaKey CAN_PLACE_ON = new ItemMetaKey("CanPlaceOn");
/*      */   
/*      */   private IChatBaseComponent displayName;
/*      */   
/*      */   private IChatBaseComponent locName;
/*      */   
/*      */   private List<IChatBaseComponent> lore;
/*      */   private Integer customModelData;
/*      */   private NBTTagCompound blockData;
/*      */   private EnchantmentMap enchantments;
/*      */   private Multimap<Attribute, AttributeModifier> attributeModifiers;
/*      */   private int repairCost;
/*      */   private int hideFlag;
/*      */   private boolean unbreakable;
/*      */   private int damage;
/*  294 */   private Set<Namespaced> placeableKeys = Sets.newHashSet();
/*  295 */   private Set<Namespaced> destroyableKeys = Sets.newHashSet();
/*      */ 
/*      */   
/*  298 */   private static final Set<String> HANDLED_TAGS = Sets.newHashSet();
/*  299 */   private static final CraftPersistentDataTypeRegistry DATA_TYPE_REGISTRY = new CraftPersistentDataTypeRegistry();
/*      */   
/*      */   private NBTTagCompound internalTag;
/*  302 */   private final Map<String, NBTBase> unhandledTags = new TreeMap<>();
/*  303 */   private CraftPersistentDataContainer persistentDataContainer = new CraftPersistentDataContainer(DATA_TYPE_REGISTRY);
/*      */   
/*  305 */   private int version = CraftMagicNumbers.INSTANCE.getDataVersion();
/*      */   
/*      */   CraftMetaItem(CraftMetaItem meta) {
/*  308 */     if (meta == null) {
/*      */       return;
/*      */     }
/*      */     
/*  312 */     this.displayName = meta.displayName;
/*  313 */     this.locName = meta.locName;
/*      */     
/*  315 */     if (meta.hasLore()) {
/*  316 */       this.lore = new ArrayList<>(meta.lore);
/*      */     }
/*      */     
/*  319 */     this.customModelData = meta.customModelData;
/*  320 */     this.blockData = meta.blockData;
/*      */     
/*  322 */     if (meta.enchantments != null) {
/*  323 */       this.enchantments = new EnchantmentMap(meta.enchantments);
/*      */     }
/*      */     
/*  326 */     if (meta.hasAttributeModifiers()) {
/*  327 */       this.attributeModifiers = (Multimap<Attribute, AttributeModifier>)LinkedHashMultimap.create(meta.attributeModifiers);
/*      */     }
/*      */     
/*  330 */     this.repairCost = meta.repairCost;
/*  331 */     this.hideFlag = meta.hideFlag;
/*  332 */     this.unbreakable = meta.unbreakable;
/*  333 */     this.damage = meta.damage;
/*      */     
/*  335 */     if (meta.hasPlaceableKeys()) {
/*  336 */       this.placeableKeys = new HashSet<>(meta.placeableKeys);
/*      */     }
/*      */     
/*  339 */     if (meta.hasDestroyableKeys()) {
/*  340 */       this.destroyableKeys = new HashSet<>(meta.destroyableKeys);
/*      */     }
/*      */     
/*  343 */     this.unhandledTags.putAll(meta.unhandledTags);
/*  344 */     this.persistentDataContainer.putAll(meta.persistentDataContainer.getRaw());
/*      */     
/*  346 */     this.internalTag = meta.internalTag;
/*  347 */     if (this.internalTag != null) {
/*  348 */       deserializeInternal(this.internalTag, meta);
/*      */     }
/*      */     
/*  351 */     this.version = meta.version;
/*      */   }
/*      */   
/*      */   CraftMetaItem(NBTTagCompound tag) {
/*  355 */     if (tag.hasKey(DISPLAY.NBT)) {
/*  356 */       NBTTagCompound display = tag.getCompound(DISPLAY.NBT);
/*      */       
/*  358 */       if (display.hasKey(NAME.NBT)) {
/*      */         try {
/*  360 */           this.displayName = (IChatBaseComponent)IChatBaseComponent.ChatSerializer.a(ValidateUtils.limit(display.getString(NAME.NBT), 8192));
/*  361 */         } catch (JsonParseException jsonParseException) {}
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*  366 */       if (display.hasKey(LOCNAME.NBT)) {
/*      */         try {
/*  368 */           this.locName = (IChatBaseComponent)IChatBaseComponent.ChatSerializer.a(ValidateUtils.limit(display.getString(LOCNAME.NBT), 8192));
/*  369 */         } catch (JsonParseException jsonParseException) {}
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*  374 */       if (display.hasKey(LORE.NBT)) {
/*  375 */         NBTTagList list = display.getList(LORE.NBT, 8);
/*  376 */         this.lore = new ArrayList<>(list.size());
/*      */         
/*  378 */         for (int index = 0; index < list.size(); index++) {
/*  379 */           String line = ValidateUtils.limit(list.getString(index), 8192);
/*      */           try {
/*  381 */             this.lore.add(IChatBaseComponent.ChatSerializer.a(line));
/*  382 */           } catch (JsonParseException jsonParseException) {}
/*      */         } 
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  389 */     if (tag.hasKeyOfType(CUSTOM_MODEL_DATA.NBT, 3)) {
/*  390 */       this.customModelData = Integer.valueOf(tag.getInt(CUSTOM_MODEL_DATA.NBT));
/*      */     }
/*  392 */     if (tag.hasKeyOfType(BLOCK_DATA.NBT, 10)) {
/*  393 */       this.blockData = tag.getCompound(BLOCK_DATA.NBT);
/*      */     }
/*      */     
/*  396 */     this.enchantments = buildEnchantments(tag, ENCHANTMENTS);
/*  397 */     this.attributeModifiers = buildModifiers(tag, ATTRIBUTES);
/*      */     
/*  399 */     if (tag.hasKey(REPAIR.NBT)) {
/*  400 */       this.repairCost = tag.getInt(REPAIR.NBT);
/*      */     }
/*      */     
/*  403 */     if (tag.hasKey(HIDEFLAGS.NBT)) {
/*  404 */       this.hideFlag = tag.getInt(HIDEFLAGS.NBT);
/*      */     }
/*  406 */     if (tag.hasKey(UNBREAKABLE.NBT)) {
/*  407 */       this.unbreakable = tag.getBoolean(UNBREAKABLE.NBT);
/*      */     }
/*  409 */     if (tag.hasKey(DAMAGE.NBT)) {
/*  410 */       this.damage = tag.getInt(DAMAGE.NBT);
/*      */     }
/*  412 */     if (tag.hasKey(BUKKIT_CUSTOM_TAG.NBT)) {
/*  413 */       NBTTagCompound compound = tag.getCompound(BUKKIT_CUSTOM_TAG.NBT);
/*  414 */       Set<String> set = compound.getKeys();
/*  415 */       for (String key : set) {
/*  416 */         this.persistentDataContainer.put(key, compound.get(key));
/*      */       }
/*      */     } 
/*      */     
/*  420 */     if (tag.hasKey(CAN_DESTROY.NBT)) {
/*  421 */       NBTTagList list = tag.getList(CAN_DESTROY.NBT, 8);
/*  422 */       for (int i = 0; i < list.size(); i++) {
/*  423 */         Namespaced namespaced = deserializeNamespaced(list.getString(i));
/*  424 */         if (namespaced != null)
/*      */         {
/*      */ 
/*      */           
/*  428 */           this.destroyableKeys.add(namespaced);
/*      */         }
/*      */       } 
/*      */     } 
/*  432 */     if (tag.hasKey(CAN_PLACE_ON.NBT)) {
/*  433 */       NBTTagList list = tag.getList(CAN_PLACE_ON.NBT, 8);
/*  434 */       for (int i = 0; i < list.size(); i++) {
/*  435 */         Namespaced namespaced = deserializeNamespaced(list.getString(i));
/*  436 */         if (namespaced != null)
/*      */         {
/*      */ 
/*      */           
/*  440 */           this.placeableKeys.add(namespaced);
/*      */         }
/*      */       } 
/*      */     } 
/*      */     
/*  445 */     Set<String> keys = tag.getKeys();
/*  446 */     for (String key : keys) {
/*  447 */       if (!getHandledTags().contains(key)) {
/*  448 */         this.unhandledTags.put(key, tag.get(key));
/*      */       }
/*      */     } 
/*      */   }
/*      */   
/*      */   static EnchantmentMap buildEnchantments(NBTTagCompound tag, ItemMetaKey key) {
/*  454 */     if (!tag.hasKey(key.NBT)) {
/*  455 */       return null;
/*      */     }
/*      */     
/*  458 */     NBTTagList ench = tag.getList(key.NBT, 10);
/*  459 */     EnchantmentMap enchantments = new EnchantmentMap();
/*      */     
/*  461 */     for (int i = 0; i < ench.size(); i++) {
/*  462 */       String id = ((NBTTagCompound)ench.get(i)).getString(ENCHANTMENTS_ID.NBT);
/*  463 */       int level = 0xFFFF & ((NBTTagCompound)ench.get(i)).getShort(ENCHANTMENTS_LVL.NBT);
/*      */       
/*  465 */       Enchantment enchant = Enchantment.getByKey(CraftNamespacedKey.fromStringOrNull(id));
/*  466 */       if (enchant != null) {
/*  467 */         enchantments.put(enchant, Integer.valueOf(level));
/*      */       }
/*      */     } 
/*      */     
/*  471 */     return enchantments;
/*      */   }
/*      */   
/*      */   static Multimap<Attribute, AttributeModifier> buildModifiers(NBTTagCompound tag, ItemMetaKey key) {
/*  475 */     LinkedHashMultimap linkedHashMultimap = LinkedHashMultimap.create();
/*  476 */     if (!tag.hasKeyOfType(key.NBT, 9)) {
/*  477 */       return (Multimap<Attribute, AttributeModifier>)linkedHashMultimap;
/*      */     }
/*  479 */     NBTTagList mods = tag.getList(key.NBT, 10);
/*  480 */     int size = mods.size();
/*      */     
/*  482 */     int i = 0; while (true) { AttributeModifier attribMod; Attribute attribute; if (i < size)
/*  483 */       { NBTTagCompound entry = mods.getCompound(i);
/*  484 */         if (entry.isEmpty()) {
/*      */           continue;
/*      */         }
/*      */         
/*  488 */         AttributeModifier nmsModifier = AttributeModifier.a(entry);
/*  489 */         if (nmsModifier == null) {
/*      */           continue;
/*      */         }
/*      */         
/*  493 */         attribMod = CraftAttributeInstance.convert(nmsModifier);
/*      */         
/*  495 */         String attributeName = CraftAttributeMap.convertIfNeeded(entry.getString(ATTRIBUTES_IDENTIFIER.NBT));
/*  496 */         if (attributeName == null || attributeName.isEmpty()) {
/*      */           continue;
/*      */         }
/*      */         
/*  500 */         attribute = CraftAttributeMap.fromMinecraft(attributeName);
/*  501 */         if (attribute == null) {
/*      */           continue;
/*      */         }
/*      */         
/*  505 */         if (entry.hasKeyOfType(ATTRIBUTES_SLOT.NBT, 8))
/*  506 */         { String slotName = entry.getString(ATTRIBUTES_SLOT.NBT);
/*  507 */           if (slotName == null || slotName.isEmpty())
/*  508 */           { linkedHashMultimap.put(attribute, attribMod); }
/*      */           
/*      */           else
/*      */           
/*  512 */           { EquipmentSlot slot = null;
/*      */             try {
/*  514 */               slot = CraftEquipmentSlot.getSlot(EnumItemSlot.fromName(slotName.toLowerCase(Locale.ROOT)));
/*  515 */             } catch (IllegalArgumentException illegalArgumentException) {}
/*      */ 
/*      */ 
/*      */             
/*  519 */             if (slot == null)
/*  520 */             { linkedHashMultimap.put(attribute, attribMod); }
/*      */             
/*      */             else
/*      */             
/*  524 */             { attribMod = new AttributeModifier(attribMod.getUniqueId(), attribMod.getName(), attribMod.getAmount(), attribMod.getOperation(), slot);
/*      */               
/*  526 */               linkedHashMultimap.put(attribute, attribMod); }  }  continue; }  } else { break; }  linkedHashMultimap.put(attribute, attribMod); i++; }
/*      */     
/*  528 */     return (Multimap<Attribute, AttributeModifier>)linkedHashMultimap;
/*      */   }
/*      */   
/*      */   CraftMetaItem(Map<String, Object> map) {
/*  532 */     setDisplayName(SerializableMeta.getString(map, NAME.BUKKIT, true));
/*  533 */     setLocalizedName(SerializableMeta.getString(map, LOCNAME.BUKKIT, true));
/*      */     
/*  535 */     Iterable<?> lore = SerializableMeta.<Iterable>getObject(Iterable.class, map, LORE.BUKKIT, true);
/*  536 */     if (lore != null) {
/*  537 */       safelyAdd(lore, this.lore = new ArrayList<>(), 2147483647);
/*      */     }
/*      */     
/*  540 */     Integer customModelData = SerializableMeta.<Integer>getObject(Integer.class, map, CUSTOM_MODEL_DATA.BUKKIT, true);
/*  541 */     if (customModelData != null) {
/*  542 */       setCustomModelData(customModelData);
/*      */     }
/*      */     
/*  545 */     Map blockData = SerializableMeta.<Map>getObject(Map.class, map, BLOCK_DATA.BUKKIT, true);
/*  546 */     if (blockData != null) {
/*  547 */       this.blockData = (NBTTagCompound)CraftNBTTagConfigSerializer.deserialize(blockData);
/*      */     }
/*      */     
/*  550 */     this.enchantments = buildEnchantments(map, ENCHANTMENTS);
/*  551 */     this.attributeModifiers = buildModifiers(map, ATTRIBUTES);
/*      */     
/*  553 */     Integer repairCost = SerializableMeta.<Integer>getObject(Integer.class, map, REPAIR.BUKKIT, true);
/*  554 */     if (repairCost != null) {
/*  555 */       setRepairCost(repairCost.intValue());
/*      */     }
/*      */     
/*  558 */     Iterable<?> hideFlags = SerializableMeta.<Iterable>getObject(Iterable.class, map, HIDEFLAGS.BUKKIT, true);
/*  559 */     if (hideFlags != null) {
/*  560 */       for (Object hideFlagObject : hideFlags) {
/*  561 */         String hideFlagString = (String)hideFlagObject;
/*      */         try {
/*  563 */           ItemFlag hideFlatEnum = ItemFlag.valueOf(hideFlagString);
/*  564 */           addItemFlags(new ItemFlag[] { hideFlatEnum });
/*  565 */         } catch (IllegalArgumentException illegalArgumentException) {}
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  571 */     Boolean unbreakable = SerializableMeta.<Boolean>getObject(Boolean.class, map, UNBREAKABLE.BUKKIT, true);
/*  572 */     if (unbreakable != null) {
/*  573 */       setUnbreakable(unbreakable.booleanValue());
/*      */     }
/*      */     
/*  576 */     Integer damage = SerializableMeta.<Integer>getObject(Integer.class, map, DAMAGE.BUKKIT, true);
/*  577 */     if (damage != null) {
/*  578 */       setDamage(damage.intValue());
/*      */     }
/*      */ 
/*      */     
/*  582 */     Iterable<?> canPlaceOnSerialized = SerializableMeta.<Iterable>getObject(Iterable.class, map, CAN_PLACE_ON.BUKKIT, true);
/*  583 */     if (canPlaceOnSerialized != null) {
/*  584 */       for (Object canPlaceOnElement : canPlaceOnSerialized) {
/*  585 */         String canPlaceOnRaw = (String)canPlaceOnElement;
/*  586 */         Namespaced value = deserializeNamespaced(canPlaceOnRaw);
/*  587 */         if (value == null) {
/*      */           continue;
/*      */         }
/*      */         
/*  591 */         this.placeableKeys.add(value);
/*      */       } 
/*      */     }
/*      */     
/*  595 */     Iterable<?> canDestroySerialized = SerializableMeta.<Iterable>getObject(Iterable.class, map, CAN_DESTROY.BUKKIT, true);
/*  596 */     if (canDestroySerialized != null) {
/*  597 */       for (Object canDestroyElement : canDestroySerialized) {
/*  598 */         String canDestroyRaw = (String)canDestroyElement;
/*  599 */         Namespaced value = deserializeNamespaced(canDestroyRaw);
/*  600 */         if (value == null) {
/*      */           continue;
/*      */         }
/*      */         
/*  604 */         this.destroyableKeys.add(value);
/*      */       } 
/*      */     }
/*      */ 
/*      */     
/*  609 */     String internal = SerializableMeta.getString(map, "internal", true);
/*  610 */     if (internal != null) {
/*  611 */       ByteArrayInputStream buf = new ByteArrayInputStream(Base64.decodeBase64(internal));
/*      */       try {
/*  613 */         this.internalTag = NBTCompressedStreamTools.a(buf);
/*  614 */         deserializeInternal(this.internalTag, map);
/*  615 */         Set<String> keys = this.internalTag.getKeys();
/*  616 */         for (String key : keys) {
/*  617 */           if (!getHandledTags().contains(key)) {
/*  618 */             this.unhandledTags.put(key, this.internalTag.get(key));
/*      */           }
/*      */         } 
/*  621 */       } catch (IOException ex) {
/*  622 */         Logger.getLogger(CraftMetaItem.class.getName()).log(Level.SEVERE, (String)null, ex);
/*      */       } 
/*      */     } 
/*      */     
/*  626 */     Map nbtMap = SerializableMeta.<Map>getObject(Map.class, map, BUKKIT_CUSTOM_TAG.BUKKIT, true);
/*  627 */     if (nbtMap != null) {
/*  628 */       this.persistentDataContainer.putAll((NBTTagCompound)CraftNBTTagConfigSerializer.deserialize(nbtMap));
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   void deserializeInternal(NBTTagCompound tag, Object context) {
/*  634 */     if (tag.hasKeyOfType(ATTRIBUTES.NBT, 9)) {
/*  635 */       this.attributeModifiers = buildModifiers(tag, ATTRIBUTES);
/*      */     }
/*      */   }
/*      */   
/*      */   static EnchantmentMap buildEnchantments(Map<String, Object> map, ItemMetaKey key) {
/*  640 */     Map<?, ?> ench = SerializableMeta.<Map<?, ?>>getObject((Class)Map.class, map, key.BUKKIT, true);
/*  641 */     if (ench == null) {
/*  642 */       return null;
/*      */     }
/*      */     
/*  645 */     EnchantmentMap enchantments = new EnchantmentMap();
/*  646 */     for (Map.Entry<?, ?> entry : ench.entrySet()) {
/*      */       
/*  648 */       String enchantKey = entry.getKey().toString();
/*  649 */       if (enchantKey.equals("SWEEPING")) {
/*  650 */         enchantKey = "SWEEPING_EDGE";
/*      */       }
/*      */       
/*  653 */       Enchantment enchantment = Enchantment.getByName(enchantKey);
/*  654 */       if (enchantment != null && entry.getValue() instanceof Integer) {
/*  655 */         enchantments.put(enchantment, (Integer)entry.getValue());
/*      */       }
/*      */     } 
/*      */     
/*  659 */     return enchantments;
/*      */   }
/*      */   
/*      */   static Multimap<Attribute, AttributeModifier> buildModifiers(Map<String, Object> map, ItemMetaKey key) {
/*  663 */     Map<?, ?> mods = SerializableMeta.<Map<?, ?>>getObject((Class)Map.class, map, key.BUKKIT, true);
/*  664 */     LinkedHashMultimap linkedHashMultimap = LinkedHashMultimap.create();
/*  665 */     if (mods == null) {
/*  666 */       return (Multimap<Attribute, AttributeModifier>)linkedHashMultimap;
/*      */     }
/*      */     
/*  669 */     for (Object obj : mods.keySet()) {
/*  670 */       if (!(obj instanceof String)) {
/*      */         continue;
/*      */       }
/*  673 */       String attributeName = (String)obj;
/*  674 */       if (Strings.isNullOrEmpty(attributeName)) {
/*      */         continue;
/*      */       }
/*  677 */       List<?> list = SerializableMeta.<List>getObject(List.class, mods, attributeName, true);
/*  678 */       if (list == null || list.isEmpty()) {
/*  679 */         return (Multimap<Attribute, AttributeModifier>)linkedHashMultimap;
/*      */       }
/*      */       
/*  682 */       for (Object o : list) {
/*  683 */         if (!(o instanceof AttributeModifier)) {
/*      */           continue;
/*      */         }
/*  686 */         AttributeModifier modifier = (AttributeModifier)o;
/*  687 */         Attribute attribute = (Attribute)EnumUtils.getEnum(Attribute.class, attributeName.toUpperCase(Locale.ROOT));
/*  688 */         if (attribute == null) {
/*      */           continue;
/*      */         }
/*      */         
/*  692 */         linkedHashMultimap.put(attribute, modifier);
/*      */       } 
/*      */     } 
/*  695 */     return (Multimap<Attribute, AttributeModifier>)linkedHashMultimap;
/*      */   }
/*      */   
/*      */   @Overridden
/*      */   void applyToItem(NBTTagCompound itemTag) {
/*  700 */     if (hasDisplayName()) {
/*  701 */       setDisplayTag(itemTag, NAME.NBT, (NBTBase)NBTTagString.a(CraftChatMessage.toJSON(this.displayName)));
/*      */     }
/*  703 */     if (hasLocalizedName()) {
/*  704 */       setDisplayTag(itemTag, LOCNAME.NBT, (NBTBase)NBTTagString.a(CraftChatMessage.toJSON(this.locName)));
/*      */     }
/*      */     
/*  707 */     if (hasLore()) {
/*  708 */       setDisplayTag(itemTag, LORE.NBT, (NBTBase)createStringList(this.lore));
/*      */     }
/*      */     
/*  711 */     if (hasCustomModelData()) {
/*  712 */       itemTag.setInt(CUSTOM_MODEL_DATA.NBT, this.customModelData.intValue());
/*      */     }
/*      */     
/*  715 */     if (hasBlockData()) {
/*  716 */       itemTag.set(BLOCK_DATA.NBT, (NBTBase)this.blockData);
/*      */     }
/*      */     
/*  719 */     if (this.hideFlag != 0) {
/*  720 */       itemTag.setInt(HIDEFLAGS.NBT, this.hideFlag);
/*      */     }
/*      */     
/*  723 */     applyEnchantments(this.enchantments, itemTag, ENCHANTMENTS);
/*  724 */     applyModifiers(this.attributeModifiers, itemTag, ATTRIBUTES);
/*      */     
/*  726 */     if (hasRepairCost()) {
/*  727 */       itemTag.setInt(REPAIR.NBT, this.repairCost);
/*      */     }
/*      */     
/*  730 */     if (isUnbreakable()) {
/*  731 */       itemTag.setBoolean(UNBREAKABLE.NBT, this.unbreakable);
/*      */     }
/*      */     
/*  734 */     if (hasDamage()) {
/*  735 */       itemTag.setInt(DAMAGE.NBT, this.damage);
/*      */     }
/*      */     
/*  738 */     if (hasPlaceableKeys()) {
/*      */ 
/*      */       
/*  741 */       List<String> items = (List<String>)this.placeableKeys.stream().map(this::serializeNamespaced).collect(Collectors.toList());
/*      */       
/*  743 */       itemTag.set(CAN_PLACE_ON.NBT, (NBTBase)createNonComponentStringList(items));
/*      */     } 
/*      */     
/*  746 */     if (hasDestroyableKeys()) {
/*      */ 
/*      */       
/*  749 */       List<String> items = (List<String>)this.destroyableKeys.stream().map(this::serializeNamespaced).collect(Collectors.toList());
/*      */       
/*  751 */       itemTag.set(CAN_DESTROY.NBT, (NBTBase)createNonComponentStringList(items));
/*      */     } 
/*      */ 
/*      */     
/*  755 */     for (Map.Entry<String, NBTBase> e : this.unhandledTags.entrySet()) {
/*  756 */       itemTag.set(e.getKey(), e.getValue());
/*      */     }
/*      */     
/*  759 */     if (!this.persistentDataContainer.isEmpty()) {
/*  760 */       NBTTagCompound bukkitCustomCompound = new NBTTagCompound();
/*  761 */       Map<String, NBTBase> rawPublicMap = this.persistentDataContainer.getRaw();
/*      */       
/*  763 */       for (Map.Entry<String, NBTBase> nbtBaseEntry : rawPublicMap.entrySet()) {
/*  764 */         bukkitCustomCompound.set(nbtBaseEntry.getKey(), nbtBaseEntry.getValue());
/*      */       }
/*  766 */       itemTag.set(BUKKIT_CUSTOM_TAG.NBT, (NBTBase)bukkitCustomCompound);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   static NBTTagList createNonComponentStringList(List<String> list) {
/*  772 */     if (list == null || list.isEmpty()) {
/*  773 */       return null;
/*      */     }
/*      */     
/*  776 */     NBTTagList tagList = new NBTTagList();
/*  777 */     for (String value : list) {
/*  778 */       tagList.add(NBTTagString.a(value));
/*      */     }
/*      */     
/*  781 */     return tagList;
/*      */   }
/*      */ 
/*      */   
/*      */   NBTTagList createStringList(List<IChatBaseComponent> list) {
/*  786 */     if (list == null || list.isEmpty()) {
/*  787 */       return null;
/*      */     }
/*      */     
/*  790 */     NBTTagList tagList = new NBTTagList();
/*  791 */     for (IChatBaseComponent value : list)
/*      */     {
/*  793 */       tagList.add(NBTTagString.a((this.version <= 0 || this.version >= 1803) ? CraftChatMessage.toJSON(value) : CraftChatMessage.fromComponent(value)));
/*      */     }
/*      */     
/*  796 */     return tagList;
/*      */   }
/*      */   
/*      */   static void applyEnchantments(Map<Enchantment, Integer> enchantments, NBTTagCompound tag, ItemMetaKey key) {
/*  800 */     if (enchantments == null) {
/*      */       return;
/*      */     }
/*      */     
/*  804 */     NBTTagList list = new NBTTagList();
/*      */     
/*  806 */     for (Map.Entry<Enchantment, Integer> entry : enchantments.entrySet()) {
/*  807 */       NBTTagCompound subtag = new NBTTagCompound();
/*      */       
/*  809 */       subtag.setString(ENCHANTMENTS_ID.NBT, ((Enchantment)entry.getKey()).getKey().toString());
/*  810 */       subtag.setShort(ENCHANTMENTS_LVL.NBT, ((Integer)entry.getValue()).shortValue());
/*      */       
/*  812 */       list.add(subtag);
/*      */     } 
/*      */     
/*  815 */     tag.set(key.NBT, (NBTBase)list);
/*      */   }
/*      */   
/*      */   static void applyModifiers(Multimap<Attribute, AttributeModifier> modifiers, NBTTagCompound tag, ItemMetaKey key) {
/*  819 */     if (modifiers == null || modifiers.isEmpty()) {
/*      */       return;
/*      */     }
/*      */     
/*  823 */     NBTTagList list = new NBTTagList();
/*  824 */     for (Map.Entry<Attribute, AttributeModifier> entry : (Iterable<Map.Entry<Attribute, AttributeModifier>>)modifiers.entries()) {
/*  825 */       if (entry.getKey() == null || entry.getValue() == null) {
/*      */         continue;
/*      */       }
/*  828 */       AttributeModifier nmsModifier = CraftAttributeInstance.convert(entry.getValue());
/*  829 */       NBTTagCompound sub = nmsModifier.save();
/*  830 */       if (sub.isEmpty()) {
/*      */         continue;
/*      */       }
/*      */       
/*  834 */       String name = ((Attribute)entry.getKey()).getKey().toString();
/*  835 */       if (name == null || name.isEmpty()) {
/*      */         continue;
/*      */       }
/*      */       
/*  839 */       sub.setString(ATTRIBUTES_IDENTIFIER.NBT, name);
/*  840 */       if (((AttributeModifier)entry.getValue()).getSlot() != null) {
/*  841 */         EnumItemSlot slot = CraftEquipmentSlot.getNMS(((AttributeModifier)entry.getValue()).getSlot());
/*  842 */         if (slot != null) {
/*  843 */           sub.setString(ATTRIBUTES_SLOT.NBT, slot.getSlotName());
/*      */         }
/*      */       } 
/*  846 */       list.add(sub);
/*      */     } 
/*  848 */     tag.set(key.NBT, (NBTBase)list);
/*      */   }
/*      */   
/*      */   void setDisplayTag(NBTTagCompound tag, String key, NBTBase value) {
/*  852 */     NBTTagCompound display = tag.getCompound(DISPLAY.NBT);
/*      */     
/*  854 */     if (!tag.hasKey(DISPLAY.NBT)) {
/*  855 */       tag.set(DISPLAY.NBT, (NBTBase)display);
/*      */     }
/*      */     
/*  858 */     display.set(key, value);
/*      */   }
/*      */   
/*      */   @Overridden
/*      */   boolean applicableTo(Material type) {
/*  863 */     return (type != Material.AIR);
/*      */   }
/*      */   
/*      */   @Overridden
/*      */   boolean isEmpty() {
/*  868 */     return (!hasDisplayName() && !hasLocalizedName() && !hasEnchants() && !hasLore() && !hasCustomModelData() && !hasBlockData() && !hasRepairCost() && this.unhandledTags.isEmpty() && this.persistentDataContainer.isEmpty() && this.hideFlag == 0 && !isUnbreakable() && !hasDamage() && !hasAttributeModifiers() && !hasPlaceableKeys() && !hasDestroyableKeys());
/*      */   }
/*      */ 
/*      */   
/*      */   public String getDisplayName() {
/*  873 */     return CraftChatMessage.fromComponent(this.displayName);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public BaseComponent[] getDisplayNameComponent() {
/*  879 */     return ComponentSerializer.parse(IChatBaseComponent.ChatSerializer.componentToJson(this.displayName));
/*      */   }
/*      */ 
/*      */   
/*      */   public final void setDisplayName(String name) {
/*  884 */     this.displayName = CraftChatMessage.fromStringOrNull(name);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setDisplayNameComponent(BaseComponent[] component) {
/*  890 */     this.displayName = IChatBaseComponent.ChatSerializer.jsonToComponent(ComponentSerializer.toString(component));
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean hasDisplayName() {
/*  895 */     return (this.displayName != null);
/*      */   }
/*      */ 
/*      */   
/*      */   public String getLocalizedName() {
/*  900 */     return CraftChatMessage.fromComponent(this.locName);
/*      */   }
/*      */ 
/*      */   
/*      */   public void setLocalizedName(String name) {
/*  905 */     this.locName = CraftChatMessage.fromStringOrNull(name);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean hasLocalizedName() {
/*  910 */     return (this.locName != null);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean hasLore() {
/*  915 */     return (this.lore != null && !this.lore.isEmpty());
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean hasRepairCost() {
/*  920 */     return (this.repairCost > 0);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean hasEnchant(Enchantment ench) {
/*  925 */     Validate.notNull(ench, "Enchantment cannot be null");
/*  926 */     return (hasEnchants() && this.enchantments.containsKey(ench));
/*      */   }
/*      */ 
/*      */   
/*      */   public int getEnchantLevel(Enchantment ench) {
/*  931 */     Validate.notNull(ench, "Enchantment cannot be null");
/*  932 */     Integer level = hasEnchants() ? this.enchantments.get(ench) : null;
/*  933 */     if (level == null) {
/*  934 */       return 0;
/*      */     }
/*  936 */     return level.intValue();
/*      */   }
/*      */ 
/*      */   
/*      */   public Map<Enchantment, Integer> getEnchants() {
/*  941 */     return hasEnchants() ? (Map<Enchantment, Integer>)ImmutableSortedMap.copyOfSorted(this.enchantments) : (Map<Enchantment, Integer>)ImmutableMap.of();
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean addEnchant(Enchantment ench, int level, boolean ignoreRestrictions) {
/*  946 */     Validate.notNull(ench, "Enchantment cannot be null");
/*  947 */     if (this.enchantments == null) {
/*  948 */       this.enchantments = new EnchantmentMap();
/*      */     }
/*      */     
/*  951 */     if (ignoreRestrictions || (level >= ench.getStartLevel() && level <= ench.getMaxLevel())) {
/*  952 */       Integer old = this.enchantments.put(ench, Integer.valueOf(level));
/*  953 */       return (old == null || old.intValue() != level);
/*      */     } 
/*  955 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean removeEnchant(Enchantment ench) {
/*  960 */     Validate.notNull(ench, "Enchantment cannot be null");
/*      */     
/*  962 */     boolean b = (hasEnchants() && this.enchantments.remove(ench) != null);
/*  963 */     if (this.enchantments != null && this.enchantments.isEmpty())
/*      */     {
/*  965 */       this.enchantments = null;
/*      */     }
/*  967 */     return b;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean hasEnchants() {
/*  973 */     return (this.enchantments != null && !this.enchantments.isEmpty());
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean hasConflictingEnchant(Enchantment ench) {
/*  978 */     return checkConflictingEnchants(this.enchantments, ench);
/*      */   }
/*      */ 
/*      */   
/*      */   public void addItemFlags(ItemFlag... hideFlags) {
/*  983 */     for (ItemFlag f : hideFlags) {
/*  984 */       this.hideFlag |= getBitModifier(f);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void removeItemFlags(ItemFlag... hideFlags) {
/*  990 */     for (ItemFlag f : hideFlags) {
/*  991 */       this.hideFlag &= getBitModifier(f) ^ 0xFFFFFFFF;
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public Set<ItemFlag> getItemFlags() {
/*  997 */     Set<ItemFlag> currentFlags = EnumSet.noneOf(ItemFlag.class);
/*      */     
/*  999 */     for (ItemFlag f : ItemFlag.values()) {
/* 1000 */       if (hasItemFlag(f)) {
/* 1001 */         currentFlags.add(f);
/*      */       }
/*      */     } 
/*      */     
/* 1005 */     return currentFlags;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean hasItemFlag(ItemFlag flag) {
/* 1010 */     int bitModifier = getBitModifier(flag);
/* 1011 */     return ((this.hideFlag & bitModifier) == bitModifier);
/*      */   }
/*      */   
/*      */   private byte getBitModifier(ItemFlag hideFlag) {
/* 1015 */     return (byte)(1 << hideFlag.ordinal());
/*      */   }
/*      */ 
/*      */   
/*      */   public List<String> getLore() {
/* 1020 */     return (this.lore == null) ? null : new ArrayList<>(Lists.transform(this.lore, CraftChatMessage::fromComponent));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public List<BaseComponent[]> getLoreComponents() {
/* 1026 */     return (this.lore == null) ? null : (List)new ArrayList<>((Collection<? extends BaseComponent>)this.lore.stream().map(entry -> ComponentSerializer.parse(IChatBaseComponent.ChatSerializer.componentToJson(entry)))
/*      */         
/* 1028 */         .collect(Collectors.toList()));
/*      */   }
/*      */ 
/*      */   
/*      */   public void setLore(List<String> lore) {
/* 1033 */     if (lore == null) {
/* 1034 */       this.lore = null;
/*      */     }
/* 1036 */     else if (this.lore == null) {
/* 1037 */       safelyAdd(lore, this.lore = new ArrayList<>(lore.size()), 2147483647);
/*      */     } else {
/* 1039 */       this.lore.clear();
/* 1040 */       safelyAdd(lore, this.lore, 2147483647);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setLoreComponents(List<BaseComponent[]> lore) {
/* 1048 */     if (lore == null) {
/* 1049 */       this.lore = null;
/*      */     }
/* 1051 */     else if (this.lore == null) {
/* 1052 */       safelyAdd(lore, this.lore = new ArrayList<>(lore.size()), 2147483647);
/*      */     } else {
/* 1054 */       this.lore.clear();
/* 1055 */       safelyAdd(lore, this.lore, 2147483647);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean hasCustomModelData() {
/* 1062 */     return (this.customModelData != null);
/*      */   }
/*      */ 
/*      */   
/*      */   public int getCustomModelData() {
/* 1067 */     Preconditions.checkState(hasCustomModelData(), "We don't have CustomModelData! Check hasCustomModelData first!");
/* 1068 */     return this.customModelData.intValue();
/*      */   }
/*      */ 
/*      */   
/*      */   public void setCustomModelData(Integer data) {
/* 1073 */     this.customModelData = data;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean hasBlockData() {
/* 1078 */     return (this.blockData != null);
/*      */   }
/*      */ 
/*      */   
/*      */   public BlockData getBlockData(Material material) {
/* 1083 */     return (BlockData)CraftBlockData.fromData(ItemBlock.getBlockState(CraftMagicNumbers.getBlock(material).getBlockData(), this.blockData));
/*      */   }
/*      */ 
/*      */   
/*      */   public void setBlockData(BlockData blockData) {
/* 1088 */     this.blockData = (blockData == null) ? null : ((CraftBlockData)blockData).toStates();
/*      */   }
/*      */ 
/*      */   
/*      */   public int getRepairCost() {
/* 1093 */     return this.repairCost;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setRepairCost(int cost) {
/* 1098 */     this.repairCost = cost;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isUnbreakable() {
/* 1103 */     return this.unbreakable;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setUnbreakable(boolean unbreakable) {
/* 1108 */     this.unbreakable = unbreakable;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean hasAttributeModifiers() {
/* 1113 */     return (this.attributeModifiers != null && !this.attributeModifiers.isEmpty());
/*      */   }
/*      */ 
/*      */   
/*      */   public Multimap<Attribute, AttributeModifier> getAttributeModifiers() {
/* 1118 */     return hasAttributeModifiers() ? (Multimap<Attribute, AttributeModifier>)ImmutableMultimap.copyOf(this.attributeModifiers) : null;
/*      */   }
/*      */   
/*      */   private void checkAttributeList() {
/* 1122 */     if (this.attributeModifiers == null) {
/* 1123 */       this.attributeModifiers = (Multimap<Attribute, AttributeModifier>)LinkedHashMultimap.create();
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public Multimap<Attribute, AttributeModifier> getAttributeModifiers(@Nullable EquipmentSlot slot) {
/* 1129 */     checkAttributeList();
/* 1130 */     LinkedHashMultimap linkedHashMultimap = LinkedHashMultimap.create();
/* 1131 */     for (Map.Entry<Attribute, AttributeModifier> entry : (Iterable<Map.Entry<Attribute, AttributeModifier>>)this.attributeModifiers.entries()) {
/* 1132 */       if (((AttributeModifier)entry.getValue()).getSlot() == null || ((AttributeModifier)entry.getValue()).getSlot() == slot) {
/* 1133 */         linkedHashMultimap.put(entry.getKey(), entry.getValue());
/*      */       }
/*      */     } 
/* 1136 */     return (Multimap<Attribute, AttributeModifier>)linkedHashMultimap;
/*      */   }
/*      */ 
/*      */   
/*      */   public Collection<AttributeModifier> getAttributeModifiers(@Nonnull Attribute attribute) {
/* 1141 */     Preconditions.checkNotNull(attribute, "Attribute cannot be null");
/* 1142 */     return this.attributeModifiers.containsKey(attribute) ? (Collection<AttributeModifier>)ImmutableList.copyOf(this.attributeModifiers.get(attribute)) : null;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean addAttributeModifier(@Nonnull Attribute attribute, @Nonnull AttributeModifier modifier) {
/* 1147 */     Preconditions.checkNotNull(attribute, "Attribute cannot be null");
/* 1148 */     Preconditions.checkNotNull(modifier, "AttributeModifier cannot be null");
/* 1149 */     checkAttributeList();
/* 1150 */     for (Map.Entry<Attribute, AttributeModifier> entry : (Iterable<Map.Entry<Attribute, AttributeModifier>>)this.attributeModifiers.entries()) {
/* 1151 */       Preconditions.checkArgument(!((AttributeModifier)entry.getValue()).getUniqueId().equals(modifier.getUniqueId()), "Cannot register AttributeModifier. Modifier is already applied! %s", modifier);
/*      */     }
/* 1153 */     return this.attributeModifiers.put(attribute, modifier);
/*      */   }
/*      */ 
/*      */   
/*      */   public void setAttributeModifiers(@Nullable Multimap<Attribute, AttributeModifier> attributeModifiers) {
/* 1158 */     if (attributeModifiers == null || attributeModifiers.isEmpty()) {
/* 1159 */       this.attributeModifiers = (Multimap<Attribute, AttributeModifier>)LinkedHashMultimap.create();
/*      */       
/*      */       return;
/*      */     } 
/* 1163 */     checkAttributeList();
/* 1164 */     this.attributeModifiers.clear();
/*      */     
/* 1166 */     Iterator<Map.Entry<Attribute, AttributeModifier>> iterator = attributeModifiers.entries().iterator();
/* 1167 */     while (iterator.hasNext()) {
/* 1168 */       Map.Entry<Attribute, AttributeModifier> next = iterator.next();
/*      */       
/* 1170 */       if (next.getKey() == null || next.getValue() == null) {
/* 1171 */         iterator.remove();
/*      */         continue;
/*      */       } 
/* 1174 */       this.attributeModifiers.put(next.getKey(), next.getValue());
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean removeAttributeModifier(@Nonnull Attribute attribute) {
/* 1180 */     Preconditions.checkNotNull(attribute, "Attribute cannot be null");
/* 1181 */     checkAttributeList();
/* 1182 */     return !this.attributeModifiers.removeAll(attribute).isEmpty();
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean removeAttributeModifier(@Nullable EquipmentSlot slot) {
/* 1187 */     checkAttributeList();
/* 1188 */     int removed = 0;
/* 1189 */     Iterator<Map.Entry<Attribute, AttributeModifier>> iter = this.attributeModifiers.entries().iterator();
/*      */     
/* 1191 */     while (iter.hasNext()) {
/* 1192 */       Map.Entry<Attribute, AttributeModifier> entry = iter.next();
/*      */ 
/*      */       
/* 1195 */       if (((AttributeModifier)entry.getValue()).getSlot() == null || ((AttributeModifier)entry.getValue()).getSlot() == slot) {
/* 1196 */         iter.remove();
/* 1197 */         removed++;
/*      */       } 
/*      */     } 
/* 1200 */     return (removed > 0);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean removeAttributeModifier(@Nonnull Attribute attribute, @Nonnull AttributeModifier modifier) {
/* 1205 */     Preconditions.checkNotNull(attribute, "Attribute cannot be null");
/* 1206 */     Preconditions.checkNotNull(modifier, "AttributeModifier cannot be null");
/* 1207 */     checkAttributeList();
/* 1208 */     int removed = 0;
/* 1209 */     Iterator<Map.Entry<Attribute, AttributeModifier>> iter = this.attributeModifiers.entries().iterator();
/*      */     
/* 1211 */     while (iter.hasNext()) {
/* 1212 */       Map.Entry<Attribute, AttributeModifier> entry = iter.next();
/* 1213 */       if (entry.getKey() == null || entry.getValue() == null) {
/* 1214 */         iter.remove();
/* 1215 */         removed++;
/*      */         
/*      */         continue;
/*      */       } 
/* 1219 */       if (entry.getKey() == attribute && ((AttributeModifier)entry.getValue()).getUniqueId().equals(modifier.getUniqueId())) {
/* 1220 */         iter.remove();
/* 1221 */         removed++;
/*      */       } 
/*      */     } 
/* 1224 */     return (removed > 0);
/*      */   }
/*      */ 
/*      */   
/*      */   public CustomItemTagContainer getCustomTagContainer() {
/* 1229 */     return (CustomItemTagContainer)new DeprecatedCustomTagContainer(getPersistentDataContainer());
/*      */   }
/*      */ 
/*      */   
/*      */   public PersistentDataContainer getPersistentDataContainer() {
/* 1234 */     return (PersistentDataContainer)this.persistentDataContainer;
/*      */   }
/*      */   
/*      */   private static boolean compareModifiers(Multimap<Attribute, AttributeModifier> first, Multimap<Attribute, AttributeModifier> second) {
/* 1238 */     if (first == null || second == null) {
/* 1239 */       return false;
/*      */     }
/* 1241 */     for (Map.Entry<Attribute, AttributeModifier> entry : (Iterable<Map.Entry<Attribute, AttributeModifier>>)first.entries()) {
/* 1242 */       if (!second.containsEntry(entry.getKey(), entry.getValue())) {
/* 1243 */         return false;
/*      */       }
/*      */     } 
/* 1246 */     for (Map.Entry<Attribute, AttributeModifier> entry : (Iterable<Map.Entry<Attribute, AttributeModifier>>)second.entries()) {
/* 1247 */       if (!first.containsEntry(entry.getKey(), entry.getValue())) {
/* 1248 */         return false;
/*      */       }
/*      */     } 
/* 1251 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean hasDamage() {
/* 1256 */     return (this.damage > 0);
/*      */   }
/*      */ 
/*      */   
/*      */   public int getDamage() {
/* 1261 */     return this.damage;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setDamage(int damage) {
/* 1266 */     this.damage = damage;
/*      */   }
/*      */ 
/*      */   
/*      */   public final boolean equals(Object object) {
/* 1271 */     if (object == null) {
/* 1272 */       return false;
/*      */     }
/* 1274 */     if (object == this) {
/* 1275 */       return true;
/*      */     }
/* 1277 */     if (!(object instanceof CraftMetaItem)) {
/* 1278 */       return false;
/*      */     }
/* 1280 */     return CraftItemFactory.instance().equals(this, (ItemMeta)object);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Overridden
/*      */   boolean equalsCommon(CraftMetaItem that) {
/*      */     // Byte code:
/*      */     //   0: aload_0
/*      */     //   1: invokevirtual hasDisplayName : ()Z
/*      */     //   4: ifeq -> 31
/*      */     //   7: aload_1
/*      */     //   8: invokevirtual hasDisplayName : ()Z
/*      */     //   11: ifeq -> 485
/*      */     //   14: aload_0
/*      */     //   15: getfield displayName : Lnet/minecraft/server/v1_16_R2/IChatBaseComponent;
/*      */     //   18: aload_1
/*      */     //   19: getfield displayName : Lnet/minecraft/server/v1_16_R2/IChatBaseComponent;
/*      */     //   22: invokevirtual equals : (Ljava/lang/Object;)Z
/*      */     //   25: ifeq -> 485
/*      */     //   28: goto -> 38
/*      */     //   31: aload_1
/*      */     //   32: invokevirtual hasDisplayName : ()Z
/*      */     //   35: ifne -> 485
/*      */     //   38: aload_0
/*      */     //   39: invokevirtual hasLocalizedName : ()Z
/*      */     //   42: ifeq -> 69
/*      */     //   45: aload_1
/*      */     //   46: invokevirtual hasLocalizedName : ()Z
/*      */     //   49: ifeq -> 485
/*      */     //   52: aload_0
/*      */     //   53: getfield locName : Lnet/minecraft/server/v1_16_R2/IChatBaseComponent;
/*      */     //   56: aload_1
/*      */     //   57: getfield locName : Lnet/minecraft/server/v1_16_R2/IChatBaseComponent;
/*      */     //   60: invokevirtual equals : (Ljava/lang/Object;)Z
/*      */     //   63: ifeq -> 485
/*      */     //   66: goto -> 76
/*      */     //   69: aload_1
/*      */     //   70: invokevirtual hasLocalizedName : ()Z
/*      */     //   73: ifne -> 485
/*      */     //   76: aload_0
/*      */     //   77: invokevirtual hasEnchants : ()Z
/*      */     //   80: ifeq -> 107
/*      */     //   83: aload_1
/*      */     //   84: invokevirtual hasEnchants : ()Z
/*      */     //   87: ifeq -> 485
/*      */     //   90: aload_0
/*      */     //   91: getfield enchantments : Lorg/bukkit/craftbukkit/v1_16_R2/inventory/CraftMetaItem$EnchantmentMap;
/*      */     //   94: aload_1
/*      */     //   95: getfield enchantments : Lorg/bukkit/craftbukkit/v1_16_R2/inventory/CraftMetaItem$EnchantmentMap;
/*      */     //   98: invokevirtual equals : (Ljava/lang/Object;)Z
/*      */     //   101: ifeq -> 485
/*      */     //   104: goto -> 114
/*      */     //   107: aload_1
/*      */     //   108: invokevirtual hasEnchants : ()Z
/*      */     //   111: ifne -> 485
/*      */     //   114: aload_0
/*      */     //   115: invokevirtual hasLore : ()Z
/*      */     //   118: ifeq -> 147
/*      */     //   121: aload_1
/*      */     //   122: invokevirtual hasLore : ()Z
/*      */     //   125: ifeq -> 485
/*      */     //   128: aload_0
/*      */     //   129: getfield lore : Ljava/util/List;
/*      */     //   132: aload_1
/*      */     //   133: getfield lore : Ljava/util/List;
/*      */     //   136: invokeinterface equals : (Ljava/lang/Object;)Z
/*      */     //   141: ifeq -> 485
/*      */     //   144: goto -> 154
/*      */     //   147: aload_1
/*      */     //   148: invokevirtual hasLore : ()Z
/*      */     //   151: ifne -> 485
/*      */     //   154: aload_0
/*      */     //   155: invokevirtual hasCustomModelData : ()Z
/*      */     //   158: ifeq -> 185
/*      */     //   161: aload_1
/*      */     //   162: invokevirtual hasCustomModelData : ()Z
/*      */     //   165: ifeq -> 485
/*      */     //   168: aload_0
/*      */     //   169: getfield customModelData : Ljava/lang/Integer;
/*      */     //   172: aload_1
/*      */     //   173: getfield customModelData : Ljava/lang/Integer;
/*      */     //   176: invokevirtual equals : (Ljava/lang/Object;)Z
/*      */     //   179: ifeq -> 485
/*      */     //   182: goto -> 192
/*      */     //   185: aload_1
/*      */     //   186: invokevirtual hasCustomModelData : ()Z
/*      */     //   189: ifne -> 485
/*      */     //   192: aload_0
/*      */     //   193: invokevirtual hasBlockData : ()Z
/*      */     //   196: ifeq -> 223
/*      */     //   199: aload_1
/*      */     //   200: invokevirtual hasBlockData : ()Z
/*      */     //   203: ifeq -> 485
/*      */     //   206: aload_0
/*      */     //   207: getfield blockData : Lnet/minecraft/server/v1_16_R2/NBTTagCompound;
/*      */     //   210: aload_1
/*      */     //   211: getfield blockData : Lnet/minecraft/server/v1_16_R2/NBTTagCompound;
/*      */     //   214: invokevirtual equals : (Ljava/lang/Object;)Z
/*      */     //   217: ifeq -> 485
/*      */     //   220: goto -> 230
/*      */     //   223: aload_1
/*      */     //   224: invokevirtual hasBlockData : ()Z
/*      */     //   227: ifne -> 485
/*      */     //   230: aload_0
/*      */     //   231: invokevirtual hasRepairCost : ()Z
/*      */     //   234: ifeq -> 258
/*      */     //   237: aload_1
/*      */     //   238: invokevirtual hasRepairCost : ()Z
/*      */     //   241: ifeq -> 485
/*      */     //   244: aload_0
/*      */     //   245: getfield repairCost : I
/*      */     //   248: aload_1
/*      */     //   249: getfield repairCost : I
/*      */     //   252: if_icmpne -> 485
/*      */     //   255: goto -> 265
/*      */     //   258: aload_1
/*      */     //   259: invokevirtual hasRepairCost : ()Z
/*      */     //   262: ifne -> 485
/*      */     //   265: aload_0
/*      */     //   266: invokevirtual hasAttributeModifiers : ()Z
/*      */     //   269: ifeq -> 296
/*      */     //   272: aload_1
/*      */     //   273: invokevirtual hasAttributeModifiers : ()Z
/*      */     //   276: ifeq -> 485
/*      */     //   279: aload_0
/*      */     //   280: getfield attributeModifiers : Lcom/google/common/collect/Multimap;
/*      */     //   283: aload_1
/*      */     //   284: getfield attributeModifiers : Lcom/google/common/collect/Multimap;
/*      */     //   287: invokestatic compareModifiers : (Lcom/google/common/collect/Multimap;Lcom/google/common/collect/Multimap;)Z
/*      */     //   290: ifeq -> 485
/*      */     //   293: goto -> 303
/*      */     //   296: aload_1
/*      */     //   297: invokevirtual hasAttributeModifiers : ()Z
/*      */     //   300: ifne -> 485
/*      */     //   303: aload_0
/*      */     //   304: getfield unhandledTags : Ljava/util/Map;
/*      */     //   307: aload_1
/*      */     //   308: getfield unhandledTags : Ljava/util/Map;
/*      */     //   311: invokeinterface equals : (Ljava/lang/Object;)Z
/*      */     //   316: ifeq -> 485
/*      */     //   319: aload_0
/*      */     //   320: getfield persistentDataContainer : Lorg/bukkit/craftbukkit/v1_16_R2/persistence/CraftPersistentDataContainer;
/*      */     //   323: aload_1
/*      */     //   324: getfield persistentDataContainer : Lorg/bukkit/craftbukkit/v1_16_R2/persistence/CraftPersistentDataContainer;
/*      */     //   327: invokevirtual equals : (Ljava/lang/Object;)Z
/*      */     //   330: ifeq -> 485
/*      */     //   333: aload_0
/*      */     //   334: getfield hideFlag : I
/*      */     //   337: aload_1
/*      */     //   338: getfield hideFlag : I
/*      */     //   341: if_icmpne -> 485
/*      */     //   344: aload_0
/*      */     //   345: invokevirtual isUnbreakable : ()Z
/*      */     //   348: aload_1
/*      */     //   349: invokevirtual isUnbreakable : ()Z
/*      */     //   352: if_icmpne -> 485
/*      */     //   355: aload_0
/*      */     //   356: invokevirtual hasDamage : ()Z
/*      */     //   359: ifeq -> 383
/*      */     //   362: aload_1
/*      */     //   363: invokevirtual hasDamage : ()Z
/*      */     //   366: ifeq -> 485
/*      */     //   369: aload_0
/*      */     //   370: getfield damage : I
/*      */     //   373: aload_1
/*      */     //   374: getfield damage : I
/*      */     //   377: if_icmpne -> 485
/*      */     //   380: goto -> 390
/*      */     //   383: aload_1
/*      */     //   384: invokevirtual hasDamage : ()Z
/*      */     //   387: ifne -> 485
/*      */     //   390: aload_0
/*      */     //   391: getfield version : I
/*      */     //   394: aload_1
/*      */     //   395: getfield version : I
/*      */     //   398: if_icmpne -> 485
/*      */     //   401: aload_0
/*      */     //   402: invokevirtual hasPlaceableKeys : ()Z
/*      */     //   405: ifeq -> 434
/*      */     //   408: aload_1
/*      */     //   409: invokevirtual hasPlaceableKeys : ()Z
/*      */     //   412: ifeq -> 485
/*      */     //   415: aload_0
/*      */     //   416: getfield placeableKeys : Ljava/util/Set;
/*      */     //   419: aload_1
/*      */     //   420: getfield placeableKeys : Ljava/util/Set;
/*      */     //   423: invokeinterface equals : (Ljava/lang/Object;)Z
/*      */     //   428: ifeq -> 485
/*      */     //   431: goto -> 441
/*      */     //   434: aload_1
/*      */     //   435: invokevirtual hasPlaceableKeys : ()Z
/*      */     //   438: ifne -> 485
/*      */     //   441: aload_0
/*      */     //   442: invokevirtual hasDestroyableKeys : ()Z
/*      */     //   445: ifeq -> 474
/*      */     //   448: aload_1
/*      */     //   449: invokevirtual hasDestroyableKeys : ()Z
/*      */     //   452: ifeq -> 485
/*      */     //   455: aload_0
/*      */     //   456: getfield destroyableKeys : Ljava/util/Set;
/*      */     //   459: aload_1
/*      */     //   460: getfield destroyableKeys : Ljava/util/Set;
/*      */     //   463: invokeinterface equals : (Ljava/lang/Object;)Z
/*      */     //   468: ifeq -> 485
/*      */     //   471: goto -> 481
/*      */     //   474: aload_1
/*      */     //   475: invokevirtual hasDestroyableKeys : ()Z
/*      */     //   478: ifne -> 485
/*      */     //   481: iconst_1
/*      */     //   482: goto -> 486
/*      */     //   485: iconst_0
/*      */     //   486: ireturn
/*      */     // Line number table:
/*      */     //   Java source line number -> byte code offset
/*      */     //   #1290	-> 0
/*      */     //   #1291	-> 39
/*      */     //   #1292	-> 77
/*      */     //   #1293	-> 115
/*      */     //   #1294	-> 155
/*      */     //   #1295	-> 193
/*      */     //   #1296	-> 231
/*      */     //   #1297	-> 266
/*      */     //   #1298	-> 311
/*      */     //   #1299	-> 327
/*      */     //   #1301	-> 345
/*      */     //   #1302	-> 356
/*      */     //   #1305	-> 402
/*      */     //   #1306	-> 442
/*      */     //   #1290	-> 486
/*      */     // Local variable table:
/*      */     //   start	length	slot	name	descriptor
/*      */     //   0	487	0	this	Lorg/bukkit/craftbukkit/v1_16_R2/inventory/CraftMetaItem;
/*      */     //   0	487	1	that	Lorg/bukkit/craftbukkit/v1_16_R2/inventory/CraftMetaItem;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Overridden
/*      */   boolean notUncommon(CraftMetaItem meta) {
/* 1317 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   public final int hashCode() {
/* 1322 */     return applyHash();
/*      */   }
/*      */   
/*      */   @Overridden
/*      */   int applyHash() {
/* 1327 */     int hash = 3;
/* 1328 */     hash = 61 * hash + (hasDisplayName() ? this.displayName.hashCode() : 0);
/* 1329 */     hash = 61 * hash + (hasLocalizedName() ? this.locName.hashCode() : 0);
/* 1330 */     hash = 61 * hash + (hasLore() ? this.lore.hashCode() : 0);
/* 1331 */     hash = 61 * hash + (hasCustomModelData() ? this.customModelData.hashCode() : 0);
/* 1332 */     hash = 61 * hash + (hasBlockData() ? this.blockData.hashCode() : 0);
/* 1333 */     hash = 61 * hash + (hasEnchants() ? this.enchantments.hashCode() : 0);
/* 1334 */     hash = 61 * hash + (hasRepairCost() ? this.repairCost : 0);
/* 1335 */     hash = 61 * hash + this.unhandledTags.hashCode();
/* 1336 */     hash = 61 * hash + (!this.persistentDataContainer.isEmpty() ? this.persistentDataContainer.hashCode() : 0);
/* 1337 */     hash = 61 * hash + this.hideFlag;
/* 1338 */     hash = 61 * hash + (isUnbreakable() ? 1231 : 1237);
/* 1339 */     hash = 61 * hash + (hasDamage() ? this.damage : 0);
/* 1340 */     hash = 61 * hash + (hasAttributeModifiers() ? this.attributeModifiers.hashCode() : 0);
/* 1341 */     hash = 61 * hash + this.version;
/*      */     
/* 1343 */     hash = 61 * hash + (hasPlaceableKeys() ? this.placeableKeys.hashCode() : 0);
/* 1344 */     hash = 61 * hash + (hasDestroyableKeys() ? this.destroyableKeys.hashCode() : 0);
/*      */     
/* 1346 */     return hash;
/*      */   }
/*      */ 
/*      */   
/*      */   @Overridden
/*      */   public CraftMetaItem clone() {
/*      */     try {
/* 1353 */       CraftMetaItem clone = (CraftMetaItem)super.clone();
/* 1354 */       if (this.lore != null) {
/* 1355 */         clone.lore = new ArrayList<>(this.lore);
/*      */       }
/* 1357 */       clone.customModelData = this.customModelData;
/* 1358 */       clone.blockData = this.blockData;
/* 1359 */       if (this.enchantments != null) {
/* 1360 */         clone.enchantments = new EnchantmentMap(this.enchantments);
/*      */       }
/* 1362 */       if (hasAttributeModifiers()) {
/* 1363 */         clone.attributeModifiers = (Multimap<Attribute, AttributeModifier>)LinkedHashMultimap.create(this.attributeModifiers);
/*      */       }
/* 1365 */       clone.persistentDataContainer = new CraftPersistentDataContainer(this.persistentDataContainer.getRaw(), DATA_TYPE_REGISTRY);
/* 1366 */       clone.hideFlag = this.hideFlag;
/* 1367 */       clone.unbreakable = this.unbreakable;
/* 1368 */       clone.damage = this.damage;
/* 1369 */       clone.version = this.version;
/*      */       
/* 1371 */       if (this.placeableKeys != null) {
/* 1372 */         clone.placeableKeys = Sets.newHashSet(this.placeableKeys);
/*      */       }
/* 1374 */       if (this.destroyableKeys != null) {
/* 1375 */         clone.destroyableKeys = Sets.newHashSet(this.destroyableKeys);
/*      */       }
/*      */       
/* 1378 */       return clone;
/* 1379 */     } catch (CloneNotSupportedException e) {
/* 1380 */       throw new Error(e);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public final Map<String, Object> serialize() {
/* 1386 */     ImmutableMap.Builder<String, Object> map = ImmutableMap.builder();
/* 1387 */     map.put("meta-type", SerializableMeta.classMap.get(getClass()));
/* 1388 */     serialize(map);
/* 1389 */     return (Map<String, Object>)map.build();
/*      */   }
/*      */   
/*      */   @Overridden
/*      */   ImmutableMap.Builder<String, Object> serialize(ImmutableMap.Builder<String, Object> builder) {
/* 1394 */     if (hasDisplayName()) {
/* 1395 */       builder.put(NAME.BUKKIT, CraftChatMessage.fromComponent(this.displayName));
/*      */     }
/* 1397 */     if (hasLocalizedName()) {
/* 1398 */       builder.put(LOCNAME.BUKKIT, CraftChatMessage.fromComponent(this.locName));
/*      */     }
/*      */     
/* 1401 */     if (hasLore()) {
/* 1402 */       builder.put(LORE.BUKKIT, ImmutableList.copyOf(Lists.transform(this.lore, CraftChatMessage::fromComponent)));
/*      */     }
/*      */     
/* 1405 */     if (hasCustomModelData()) {
/* 1406 */       builder.put(CUSTOM_MODEL_DATA.BUKKIT, this.customModelData);
/*      */     }
/* 1408 */     if (hasBlockData()) {
/* 1409 */       builder.put(BLOCK_DATA.BUKKIT, CraftNBTTagConfigSerializer.serialize((NBTBase)this.blockData));
/*      */     }
/*      */     
/* 1412 */     serializeEnchantments(this.enchantments, builder, ENCHANTMENTS);
/* 1413 */     serializeModifiers(this.attributeModifiers, builder, ATTRIBUTES);
/*      */     
/* 1415 */     if (hasRepairCost()) {
/* 1416 */       builder.put(REPAIR.BUKKIT, Integer.valueOf(this.repairCost));
/*      */     }
/*      */     
/* 1419 */     List<String> hideFlags = new ArrayList<>();
/* 1420 */     for (ItemFlag hideFlagEnum : getItemFlags()) {
/* 1421 */       hideFlags.add(hideFlagEnum.name());
/*      */     }
/* 1423 */     if (!hideFlags.isEmpty()) {
/* 1424 */       builder.put(HIDEFLAGS.BUKKIT, hideFlags);
/*      */     }
/*      */     
/* 1427 */     if (isUnbreakable()) {
/* 1428 */       builder.put(UNBREAKABLE.BUKKIT, Boolean.valueOf(this.unbreakable));
/*      */     }
/*      */     
/* 1431 */     if (hasDamage()) {
/* 1432 */       builder.put(DAMAGE.BUKKIT, Integer.valueOf(this.damage));
/*      */     }
/*      */ 
/*      */     
/* 1436 */     if (hasPlaceableKeys()) {
/*      */ 
/*      */       
/* 1439 */       List<String> cerealPlaceable = (List<String>)this.placeableKeys.stream().map(this::serializeNamespaced).collect(Collectors.toList());
/*      */       
/* 1441 */       builder.put(CAN_PLACE_ON.BUKKIT, cerealPlaceable);
/*      */     } 
/*      */     
/* 1444 */     if (hasDestroyableKeys()) {
/*      */ 
/*      */       
/* 1447 */       List<String> cerealDestroyable = (List<String>)this.destroyableKeys.stream().map(this::serializeNamespaced).collect(Collectors.toList());
/*      */       
/* 1449 */       builder.put(CAN_DESTROY.BUKKIT, cerealDestroyable);
/*      */     } 
/*      */ 
/*      */     
/* 1453 */     Map<String, NBTBase> internalTags = new HashMap<>(this.unhandledTags);
/* 1454 */     serializeInternal(internalTags);
/* 1455 */     if (!internalTags.isEmpty()) {
/* 1456 */       NBTTagCompound internal = new NBTTagCompound();
/* 1457 */       for (Map.Entry<String, NBTBase> e : internalTags.entrySet()) {
/* 1458 */         internal.set(e.getKey(), e.getValue());
/*      */       }
/*      */       try {
/* 1461 */         ByteArrayOutputStream buf = new ByteArrayOutputStream();
/* 1462 */         NBTCompressedStreamTools.a(internal, buf);
/* 1463 */         builder.put("internal", Base64.encodeBase64String(buf.toByteArray()));
/* 1464 */       } catch (IOException ex) {
/* 1465 */         Logger.getLogger(CraftMetaItem.class.getName()).log(Level.SEVERE, (String)null, ex);
/*      */       } 
/*      */     } 
/*      */     
/* 1469 */     if (!this.persistentDataContainer.isEmpty()) {
/* 1470 */       builder.put(BUKKIT_CUSTOM_TAG.BUKKIT, this.persistentDataContainer.serialize());
/*      */     }
/*      */     
/* 1473 */     return builder;
/*      */   }
/*      */ 
/*      */   
/*      */   void serializeInternal(Map<String, NBTBase> unhandledTags) {}
/*      */   
/*      */   Material updateMaterial(Material material) {
/* 1480 */     return material;
/*      */   }
/*      */   
/*      */   static void serializeEnchantments(Map<Enchantment, Integer> enchantments, ImmutableMap.Builder<String, Object> builder, ItemMetaKey key) {
/* 1484 */     if (enchantments == null || enchantments.isEmpty()) {
/*      */       return;
/*      */     }
/*      */     
/* 1488 */     ImmutableMap.Builder<String, Integer> enchants = ImmutableMap.builder();
/* 1489 */     for (Map.Entry<? extends Enchantment, Integer> enchant : enchantments.entrySet()) {
/* 1490 */       enchants.put(((Enchantment)enchant.getKey()).getName(), enchant.getValue());
/*      */     }
/*      */     
/* 1493 */     builder.put(key.BUKKIT, enchants.build());
/*      */   }
/*      */   
/*      */   static void serializeModifiers(Multimap<Attribute, AttributeModifier> modifiers, ImmutableMap.Builder<String, Object> builder, ItemMetaKey key) {
/* 1497 */     if (modifiers == null || modifiers.isEmpty()) {
/*      */       return;
/*      */     }
/*      */     
/* 1501 */     Map<String, List<Object>> mods = new LinkedHashMap<>();
/* 1502 */     for (Map.Entry<Attribute, AttributeModifier> entry : (Iterable<Map.Entry<Attribute, AttributeModifier>>)modifiers.entries()) {
/* 1503 */       if (entry.getKey() == null) {
/*      */         continue;
/*      */       }
/* 1506 */       Collection<AttributeModifier> modCollection = modifiers.get(entry.getKey());
/* 1507 */       if (modCollection == null || modCollection.isEmpty()) {
/*      */         continue;
/*      */       }
/* 1510 */       mods.put(((Attribute)entry.getKey()).name(), new ArrayList(modCollection));
/*      */     } 
/* 1512 */     builder.put(key.BUKKIT, mods);
/*      */   }
/*      */   
/*      */   static void safelyAdd(Iterable<?> addFrom, Collection<IChatBaseComponent> addTo, int maxItemLength) {
/* 1516 */     if (addFrom == null) {
/*      */       return;
/*      */     }
/*      */     
/* 1520 */     for (Object object : addFrom) {
/*      */       
/* 1522 */       if (object instanceof BaseComponent[]) {
/* 1523 */         addTo.add(IChatBaseComponent.ChatSerializer.jsonToComponent(ComponentSerializer.toString((BaseComponent[])object)));
/*      */         continue;
/*      */       } 
/* 1526 */       if (!(object instanceof String)) {
/* 1527 */         if (object != null) {
/* 1528 */           throw new IllegalArgumentException(addFrom + " cannot contain non-string " + object.getClass().getName());
/*      */         }
/*      */         
/* 1531 */         addTo.add(new ChatComponentText("")); continue;
/*      */       } 
/* 1533 */       String page = object.toString();
/*      */       
/* 1535 */       if (page.length() > maxItemLength) {
/* 1536 */         page = page.substring(0, maxItemLength);
/*      */       }
/*      */       
/* 1539 */       addTo.add(CraftChatMessage.fromString(page)[0]);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   static boolean checkConflictingEnchants(Map<Enchantment, Integer> enchantments, Enchantment ench) {
/* 1545 */     if (enchantments == null || enchantments.isEmpty()) {
/* 1546 */       return false;
/*      */     }
/*      */     
/* 1549 */     for (Enchantment enchant : enchantments.keySet()) {
/* 1550 */       if (enchant.conflictsWith(ench)) {
/* 1551 */         return true;
/*      */       }
/*      */     } 
/*      */     
/* 1555 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public final String toString() {
/* 1560 */     return (String)SerializableMeta.classMap.get(getClass()) + "_META:" + serialize();
/*      */   }
/*      */   
/*      */   public int getVersion() {
/* 1564 */     return this.version;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setVersion(int version) {
/* 1569 */     this.version = version;
/*      */   }
/*      */   
/*      */   public static Set<String> getHandledTags() {
/* 1573 */     synchronized (HANDLED_TAGS) {
/* 1574 */       if (HANDLED_TAGS.isEmpty()) {
/* 1575 */         HANDLED_TAGS.addAll(Arrays.asList(new String[] { DISPLAY.NBT, CUSTOM_MODEL_DATA.NBT, BLOCK_DATA.NBT, REPAIR.NBT, ENCHANTMENTS.NBT, HIDEFLAGS.NBT, UNBREAKABLE.NBT, DAMAGE.NBT, BUKKIT_CUSTOM_TAG.NBT, ATTRIBUTES.NBT, ATTRIBUTES_IDENTIFIER.NBT, ATTRIBUTES_NAME.NBT, ATTRIBUTES_VALUE.NBT, ATTRIBUTES_UUID_HIGH.NBT, ATTRIBUTES_UUID_LOW.NBT, ATTRIBUTES_SLOT.NBT, CraftMetaMap.MAP_SCALING.NBT, CraftMetaMap.MAP_ID.NBT, CraftMetaPotion.POTION_EFFECTS.NBT, CraftMetaPotion.DEFAULT_POTION.NBT, CraftMetaPotion.POTION_COLOR.NBT, CraftMetaSkull.SKULL_OWNER.NBT, CraftMetaSkull.SKULL_PROFILE.NBT, CraftMetaSpawnEgg.ENTITY_TAG.NBT, CraftMetaBlockState.BLOCK_ENTITY_TAG.NBT, CraftMetaBook.BOOK_TITLE.NBT, CraftMetaBook.BOOK_AUTHOR.NBT, CraftMetaBook.BOOK_PAGES.NBT, CraftMetaBook.RESOLVED.NBT, CraftMetaBook.GENERATION.NBT, CraftMetaFirework.FIREWORKS.NBT, CraftMetaEnchantedBook.STORED_ENCHANTMENTS.NBT, CraftMetaCharge.EXPLOSION.NBT, CraftMetaBlockState.BLOCK_ENTITY_TAG.NBT, CraftMetaKnowledgeBook.BOOK_RECIPES.NBT, CraftMetaTropicalFishBucket.VARIANT.NBT, CraftMetaCrossbow.CHARGED.NBT, CraftMetaCrossbow.CHARGED_PROJECTILES.NBT, CraftMetaSuspiciousStew.EFFECTS.NBT, CraftMetaArmorStand.ENTITY_TAG.NBT, CraftMetaArmorStand.INVISIBLE.NBT, CraftMetaArmorStand.NO_BASE_PLATE.NBT, CraftMetaArmorStand.SHOW_ARMS.NBT, CraftMetaArmorStand.SMALL.NBT, CraftMetaArmorStand.MARKER.NBT, CAN_DESTROY.NBT, CAN_PLACE_ON.NBT, CraftMetaCompass.LODESTONE_DIMENSION.NBT, CraftMetaCompass.LODESTONE_POS.NBT, CraftMetaCompass.LODESTONE_TRACKED.NBT }));
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1630 */       return HANDLED_TAGS;
/*      */     } 
/*      */   }
/*      */   
/*      */   private static class EnchantmentMap
/*      */     extends TreeMap<Enchantment, Integer> {
/*      */     private EnchantmentMap(Map<Enchantment, Integer> enchantments) {
/* 1637 */       this();
/* 1638 */       putAll(enchantments);
/*      */     }
/*      */     
/*      */     private EnchantmentMap() {
/* 1642 */       super(Comparator.comparing(o -> o.getKey().toString()));
/*      */     }
/*      */     
/*      */     public EnchantmentMap clone() {
/* 1646 */       return (EnchantmentMap)super.clone();
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Set<Material> getCanDestroy() {
/* 1655 */     return !hasDestroyableKeys() ? Collections.<Material>emptySet() : legacyGetMatsFromKeys(this.destroyableKeys);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setCanDestroy(Set<Material> canDestroy) {
/* 1661 */     Validate.notNull(canDestroy, "Cannot replace with null set!");
/* 1662 */     legacyClearAndReplaceKeys(this.destroyableKeys, canDestroy);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public Set<Material> getCanPlaceOn() {
/* 1668 */     return !hasPlaceableKeys() ? Collections.<Material>emptySet() : legacyGetMatsFromKeys(this.placeableKeys);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setCanPlaceOn(Set<Material> canPlaceOn) {
/* 1674 */     Validate.notNull(canPlaceOn, "Cannot replace with null set!");
/* 1675 */     legacyClearAndReplaceKeys(this.placeableKeys, canPlaceOn);
/*      */   }
/*      */ 
/*      */   
/*      */   public Set<Namespaced> getDestroyableKeys() {
/* 1680 */     return !hasDestroyableKeys() ? Collections.<Namespaced>emptySet() : Sets.newHashSet(this.destroyableKeys);
/*      */   }
/*      */ 
/*      */   
/*      */   public void setDestroyableKeys(Collection<Namespaced> canDestroy) {
/* 1685 */     Validate.notNull(canDestroy, "Cannot replace with null collection!");
/* 1686 */     Validate.isTrue(ofAcceptableType(canDestroy), "Can only use NamespacedKey or NamespacedTag objects!");
/* 1687 */     this.destroyableKeys.clear();
/* 1688 */     this.destroyableKeys.addAll(canDestroy);
/*      */   }
/*      */ 
/*      */   
/*      */   public Set<Namespaced> getPlaceableKeys() {
/* 1693 */     return !hasPlaceableKeys() ? Collections.<Namespaced>emptySet() : Sets.newHashSet(this.placeableKeys);
/*      */   }
/*      */ 
/*      */   
/*      */   public void setPlaceableKeys(Collection<Namespaced> canPlaceOn) {
/* 1698 */     Validate.notNull(canPlaceOn, "Cannot replace with null collection!");
/* 1699 */     Validate.isTrue(ofAcceptableType(canPlaceOn), "Can only use NamespacedKey or NamespacedTag objects!");
/* 1700 */     this.placeableKeys.clear();
/* 1701 */     this.placeableKeys.addAll(canPlaceOn);
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean hasPlaceableKeys() {
/* 1706 */     return (this.placeableKeys != null && !this.placeableKeys.isEmpty());
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean hasDestroyableKeys() {
/* 1711 */     return (this.destroyableKeys != null && !this.destroyableKeys.isEmpty());
/*      */   }
/*      */   
/*      */   @Deprecated
/*      */   private void legacyClearAndReplaceKeys(Collection<Namespaced> toUpdate, Collection<Material> beingSet) {
/* 1716 */     if (beingSet.stream().anyMatch(Material::isLegacy)) {
/* 1717 */       throw new IllegalArgumentException("Set must not contain any legacy materials!");
/*      */     }
/*      */     
/* 1720 */     toUpdate.clear();
/* 1721 */     toUpdate.addAll((Collection<? extends Namespaced>)beingSet.stream().map(Material::getKey).collect(Collectors.toSet()));
/*      */   }
/*      */   
/*      */   @Deprecated
/*      */   private Set<Material> legacyGetMatsFromKeys(Collection<Namespaced> names) {
/* 1726 */     Set<Material> mats = Sets.newHashSet();
/* 1727 */     for (Namespaced key : names) {
/* 1728 */       if (!(key instanceof NamespacedKey)) {
/*      */         continue;
/*      */       }
/*      */       
/* 1732 */       Material material = Material.matchMaterial(key.toString(), false);
/* 1733 */       if (material != null) {
/* 1734 */         mats.add(material);
/*      */       }
/*      */     } 
/*      */     
/* 1738 */     return mats; } @Nullable
/*      */   private Namespaced deserializeNamespaced(String raw) {
/*      */     MinecraftKey key;
/*      */     NamespacedKey namespacedKey;
/* 1742 */     boolean isTag = (raw.length() > 0 && raw.codePointAt(0) == 35);
/* 1743 */     ArgumentBlock blockParser = new ArgumentBlock(new StringReader(raw), true);
/*      */     try {
/* 1745 */       blockParser = blockParser.parse(false);
/* 1746 */     } catch (CommandSyntaxException e) {
/* 1747 */       e.printStackTrace();
/* 1748 */       return null;
/*      */     } 
/*      */ 
/*      */     
/* 1752 */     if (isTag) {
/* 1753 */       key = blockParser.getTagKey();
/*      */     } else {
/* 1755 */       key = blockParser.getBlockKey();
/*      */     } 
/*      */     
/* 1758 */     if (key == null) {
/* 1759 */       return null;
/*      */     }
/*      */ 
/*      */     
/* 1763 */     Namespaced resource = null;
/*      */     try {
/* 1765 */       if (isTag) {
/* 1766 */         NamespacedTag namespacedTag = new NamespacedTag(key.getNamespace(), key.getKey());
/*      */       } else {
/* 1768 */         namespacedKey = CraftNamespacedKey.fromMinecraft(key);
/*      */       } 
/* 1770 */     } catch (IllegalArgumentException ex) {
/* 1771 */       Bukkit.getLogger().warning("Namespaced resource does not validate: " + key.toString());
/* 1772 */       ex.printStackTrace();
/*      */     } 
/*      */     
/* 1775 */     return (Namespaced)namespacedKey;
/*      */   }
/*      */   @Nonnull
/*      */   private String serializeNamespaced(Namespaced resource) {
/* 1779 */     return resource.toString();
/*      */   }
/*      */ 
/*      */   
/*      */   private boolean ofAcceptableType(Collection<Namespaced> namespacedResources) {
/* 1784 */     boolean valid = true;
/* 1785 */     for (Namespaced resource : namespacedResources) {
/* 1786 */       if (valid && !(resource instanceof NamespacedKey) && !(resource instanceof NamespacedTag)) {
/* 1787 */         valid = false;
/*      */       }
/*      */     } 
/*      */     
/* 1791 */     return valid;
/*      */   }
/*      */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\inventory\CraftMetaItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
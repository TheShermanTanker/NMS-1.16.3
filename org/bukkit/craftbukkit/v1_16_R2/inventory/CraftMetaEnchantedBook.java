/*     */ package org.bukkit.craftbukkit.v1_16_R2.inventory;
/*     */ import com.google.common.collect.ImmutableMap;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import net.minecraft.server.v1_16_R2.NBTTagCompound;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.configuration.serialization.DelegateDeserialization;
/*     */ import org.bukkit.enchantments.Enchantment;
/*     */ import org.bukkit.inventory.meta.Damageable;
/*     */ import org.bukkit.inventory.meta.EnchantmentStorageMeta;
/*     */ import org.bukkit.inventory.meta.ItemMeta;
/*     */ import org.bukkit.inventory.meta.Repairable;
/*     */ 
/*     */ @DelegateDeserialization(CraftMetaItem.SerializableMeta.class)
/*     */ class CraftMetaEnchantedBook extends CraftMetaItem implements EnchantmentStorageMeta {
/*  16 */   static final CraftMetaItem.ItemMetaKey STORED_ENCHANTMENTS = new CraftMetaItem.ItemMetaKey("StoredEnchantments", "stored-enchants");
/*     */   
/*     */   private Map<Enchantment, Integer> enchantments;
/*     */   
/*     */   CraftMetaEnchantedBook(CraftMetaItem meta) {
/*  21 */     super(meta);
/*     */     
/*  23 */     if (!(meta instanceof CraftMetaEnchantedBook)) {
/*     */       return;
/*     */     }
/*     */     
/*  27 */     CraftMetaEnchantedBook that = (CraftMetaEnchantedBook)meta;
/*     */     
/*  29 */     if (that.hasEnchants()) {
/*  30 */       this.enchantments = new HashMap<>(that.enchantments);
/*     */     }
/*     */   }
/*     */   
/*     */   CraftMetaEnchantedBook(NBTTagCompound tag) {
/*  35 */     super(tag);
/*     */     
/*  37 */     if (!tag.hasKey(STORED_ENCHANTMENTS.NBT)) {
/*     */       return;
/*     */     }
/*     */     
/*  41 */     this.enchantments = buildEnchantments(tag, STORED_ENCHANTMENTS);
/*     */   }
/*     */   
/*     */   CraftMetaEnchantedBook(Map<String, Object> map) {
/*  45 */     super(map);
/*     */     
/*  47 */     this.enchantments = buildEnchantments(map, STORED_ENCHANTMENTS);
/*     */   }
/*     */ 
/*     */   
/*     */   void applyToItem(NBTTagCompound itemTag) {
/*  52 */     super.applyToItem(itemTag);
/*     */     
/*  54 */     applyEnchantments(this.enchantments, itemTag, STORED_ENCHANTMENTS);
/*     */   }
/*     */ 
/*     */   
/*     */   boolean applicableTo(Material type) {
/*  59 */     switch (type) {
/*     */       case ENCHANTED_BOOK:
/*  61 */         return true;
/*     */     } 
/*  63 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   boolean isEmpty() {
/*  69 */     return (super.isEmpty() && isEnchantedEmpty());
/*     */   }
/*     */ 
/*     */   
/*     */   boolean equalsCommon(CraftMetaItem meta) {
/*  74 */     if (!super.equalsCommon(meta)) {
/*  75 */       return false;
/*     */     }
/*  77 */     if (meta instanceof CraftMetaEnchantedBook) {
/*  78 */       CraftMetaEnchantedBook that = (CraftMetaEnchantedBook)meta;
/*     */       
/*  80 */       return hasStoredEnchants() ? ((that.hasStoredEnchants() && this.enchantments.equals(that.enchantments))) : (!that.hasStoredEnchants());
/*     */     } 
/*  82 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   boolean notUncommon(CraftMetaItem meta) {
/*  87 */     return (super.notUncommon(meta) && (meta instanceof CraftMetaEnchantedBook || isEnchantedEmpty()));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   int applyHash() {
/*  93 */     int original = super.applyHash(), hash = original;
/*     */     
/*  95 */     if (hasStoredEnchants()) {
/*  96 */       hash = 61 * hash + this.enchantments.hashCode();
/*     */     }
/*     */     
/*  99 */     return (original != hash) ? (CraftMetaEnchantedBook.class.hashCode() ^ hash) : hash;
/*     */   }
/*     */ 
/*     */   
/*     */   public CraftMetaEnchantedBook clone() {
/* 104 */     CraftMetaEnchantedBook meta = (CraftMetaEnchantedBook)super.clone();
/*     */     
/* 106 */     if (this.enchantments != null) {
/* 107 */       meta.enchantments = new HashMap<>(this.enchantments);
/*     */     }
/*     */     
/* 110 */     return meta;
/*     */   }
/*     */ 
/*     */   
/*     */   ImmutableMap.Builder<String, Object> serialize(ImmutableMap.Builder<String, Object> builder) {
/* 115 */     super.serialize(builder);
/*     */     
/* 117 */     serializeEnchantments(this.enchantments, builder, STORED_ENCHANTMENTS);
/*     */     
/* 119 */     return builder;
/*     */   }
/*     */   
/*     */   boolean isEnchantedEmpty() {
/* 123 */     return !hasStoredEnchants();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasStoredEnchant(Enchantment ench) {
/* 128 */     return (hasStoredEnchants() && this.enchantments.containsKey(ench));
/*     */   }
/*     */ 
/*     */   
/*     */   public int getStoredEnchantLevel(Enchantment ench) {
/* 133 */     Integer level = hasStoredEnchants() ? this.enchantments.get(ench) : null;
/* 134 */     if (level == null) {
/* 135 */       return 0;
/*     */     }
/* 137 */     return level.intValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public Map<Enchantment, Integer> getStoredEnchants() {
/* 142 */     return hasStoredEnchants() ? (Map<Enchantment, Integer>)ImmutableMap.copyOf(this.enchantments) : (Map<Enchantment, Integer>)ImmutableMap.of();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean addStoredEnchant(Enchantment ench, int level, boolean ignoreRestrictions) {
/* 147 */     if (this.enchantments == null) {
/* 148 */       this.enchantments = new HashMap<>(4);
/*     */     }
/*     */     
/* 151 */     if (ignoreRestrictions || (level >= ench.getStartLevel() && level <= ench.getMaxLevel())) {
/* 152 */       Integer old = this.enchantments.put(ench, Integer.valueOf(level));
/* 153 */       return (old == null || old.intValue() != level);
/*     */     } 
/* 155 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean removeStoredEnchant(Enchantment ench) {
/* 160 */     return (hasStoredEnchants() && this.enchantments.remove(ench) != null);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasStoredEnchants() {
/* 165 */     return (this.enchantments != null && !this.enchantments.isEmpty());
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasConflictingStoredEnchant(Enchantment ench) {
/* 170 */     return checkConflictingEnchants(this.enchantments, ench);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\inventory\CraftMetaEnchantedBook.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
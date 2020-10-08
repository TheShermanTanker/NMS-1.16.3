/*     */ package org.bukkit.inventory;
/*     */ 
/*     */ import com.google.common.collect.ImmutableMap;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.lang.Validate;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.UndefinedNullability;
/*     */ import org.bukkit.configuration.serialization.ConfigurationSerializable;
/*     */ import org.bukkit.enchantments.Enchantment;
/*     */ import org.bukkit.inventory.meta.Damageable;
/*     */ import org.bukkit.inventory.meta.ItemMeta;
/*     */ import org.bukkit.material.MaterialData;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ItemStack
/*     */   implements Cloneable, ConfigurationSerializable
/*     */ {
/*  29 */   private Material type = Material.AIR;
/*  30 */   private int amount = 0;
/*  31 */   private MaterialData data = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private ItemMeta meta;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack(@NotNull Material type) {
/*  47 */     this(type, 1);
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
/*     */   public ItemStack(@NotNull Material type, int amount) {
/*  61 */     this(type, amount, (short)0);
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
/*     */   public ItemStack(@NotNull Material type, int amount, short damage) {
/*  73 */     this(type, amount, damage, null);
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
/*     */   public ItemStack(@NotNull Material type, int amount, short damage, @Nullable Byte data) {
/*  85 */     Validate.notNull(type, "Material cannot be null");
/*  86 */     this.type = type;
/*  87 */     this.amount = amount;
/*  88 */     if (damage != 0) {
/*  89 */       setDurability(damage);
/*     */     }
/*  91 */     if (data != null) {
/*  92 */       createData(data.byteValue());
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack(@NotNull ItemStack stack) throws IllegalArgumentException {
/* 104 */     Validate.notNull(stack, "Cannot copy null stack");
/* 105 */     this.type = stack.getType();
/* 106 */     this.amount = stack.getAmount();
/* 107 */     if (this.type.isLegacy()) {
/* 108 */       this.data = stack.getData();
/*     */     }
/* 110 */     if (stack.hasItemMeta()) {
/* 111 */       setItemMeta0(stack.getItemMeta(), this.type);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Material getType() {
/* 123 */     return this.type;
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
/*     */   public void setType(@NotNull Material type) {
/* 139 */     Validate.notNull(type, "Material cannot be null");
/* 140 */     this.type = type;
/* 141 */     if (this.meta != null) {
/* 142 */       this.meta = Bukkit.getItemFactory().asMetaFor(this.meta, type);
/*     */     }
/* 144 */     if (type.isLegacy()) {
/* 145 */       createData((byte)0);
/*     */     } else {
/* 147 */       this.data = null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getAmount() {
/* 157 */     return this.amount;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAmount(int amount) {
/* 166 */     this.amount = amount;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public MaterialData getData() {
/* 176 */     Material mat = Bukkit.getUnsafe().toLegacy(getType());
/* 177 */     if (this.data == null && mat != null && mat.getData() != null) {
/* 178 */       this.data = mat.getNewData((byte)getDurability());
/*     */     }
/*     */     
/* 181 */     return this.data;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setData(@Nullable MaterialData data) {
/* 190 */     if (data == null) {
/* 191 */       this.data = data;
/*     */     } else {
/* 193 */       Material mat = Bukkit.getUnsafe().toLegacy(getType());
/*     */       
/* 195 */       if (data.getClass() == mat.getData() || data.getClass() == MaterialData.class) {
/* 196 */         this.data = data;
/*     */       } else {
/* 198 */         throw new IllegalArgumentException("Provided data is not of type " + mat.getData().getName() + ", found " + data.getClass().getName());
/*     */       } 
/*     */     } 
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
/*     */   public void setDurability(short durability) {
/* 215 */     ItemMeta meta = getItemMeta();
/* 216 */     if (meta != null) {
/* 217 */       ((Damageable)meta).setDamage(durability);
/* 218 */       setItemMeta(meta);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public short getDurability() {
/* 230 */     ItemMeta meta = getItemMeta();
/* 231 */     return (meta == null) ? 0 : (short)((Damageable)meta).getDamage();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMaxStackSize() {
/* 242 */     Material material = getType();
/* 243 */     if (material != null) {
/* 244 */       return material.getMaxStackSize();
/*     */     }
/* 246 */     return -1;
/*     */   }
/*     */   
/*     */   private void createData(byte data) {
/* 250 */     this.data = this.type.getNewData(data);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 256 */     StringBuilder toString = (new StringBuilder("ItemStack{")).append(getType().name()).append(" x ").append(getAmount());
/* 257 */     if (hasItemMeta()) {
/* 258 */       toString.append(", ").append(getItemMeta());
/*     */     }
/* 260 */     return toString.append('}').toString();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/* 266 */     if (this == obj) {
/* 267 */       return true;
/*     */     }
/* 269 */     if (!(obj instanceof ItemStack)) {
/* 270 */       return false;
/*     */     }
/*     */     
/* 273 */     ItemStack stack = (ItemStack)obj;
/* 274 */     return (getAmount() == stack.getAmount() && isSimilar(stack));
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
/*     */   public boolean isSimilar(@Nullable ItemStack stack) {
/* 286 */     if (stack == null) {
/* 287 */       return false;
/*     */     }
/* 289 */     if (stack == this) {
/* 290 */       return true;
/*     */     }
/* 292 */     Material comparisonType = this.type.isLegacy() ? Bukkit.getUnsafe().fromLegacy(getData(), true) : this.type;
/* 293 */     return (comparisonType == stack.getType() && getDurability() == stack.getDurability() && hasItemMeta() == stack.hasItemMeta() && (!hasItemMeta() || Bukkit.getItemFactory().equals(getItemMeta(), stack.getItemMeta())));
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public ItemStack clone() {
/*     */     try {
/* 300 */       ItemStack itemStack = (ItemStack)super.clone();
/*     */       
/* 302 */       if (this.meta != null) {
/* 303 */         itemStack.meta = this.meta.clone();
/*     */       }
/*     */       
/* 306 */       if (this.data != null) {
/* 307 */         itemStack.data = this.data.clone();
/*     */       }
/*     */       
/* 310 */       return itemStack;
/* 311 */     } catch (CloneNotSupportedException e) {
/* 312 */       throw new Error(e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 319 */     int hash = 1;
/*     */     
/* 321 */     hash = hash * 31 + getType().hashCode();
/* 322 */     hash = hash * 31 + getAmount();
/* 323 */     hash = hash * 31 + (getDurability() & 0xFFFF);
/* 324 */     hash = hash * 31 + (hasItemMeta() ? ((this.meta == null) ? getItemMeta().hashCode() : this.meta.hashCode()) : 0);
/*     */     
/* 326 */     return hash;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean containsEnchantment(@NotNull Enchantment ench) {
/* 336 */     return (this.meta == null) ? false : this.meta.hasEnchant(ench);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getEnchantmentLevel(@NotNull Enchantment ench) {
/* 346 */     return (this.meta == null) ? 0 : this.meta.getEnchantLevel(ench);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Map<Enchantment, Integer> getEnchantments() {
/* 356 */     return (this.meta == null) ? (Map<Enchantment, Integer>)ImmutableMap.of() : this.meta.getEnchants();
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
/*     */   
/*     */   public void addEnchantments(@NotNull Map<Enchantment, Integer> enchantments) {
/* 374 */     Validate.notNull(enchantments, "Enchantments cannot be null");
/* 375 */     for (Map.Entry<Enchantment, Integer> entry : enchantments.entrySet()) {
/* 376 */       addEnchantment(entry.getKey(), ((Integer)entry.getValue()).intValue());
/*     */     }
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
/*     */   public void addEnchantment(@NotNull Enchantment ench, int level) {
/* 393 */     Validate.notNull(ench, "Enchantment cannot be null");
/* 394 */     if (level < ench.getStartLevel() || level > ench.getMaxLevel())
/* 395 */       throw new IllegalArgumentException("Enchantment level is either too low or too high (given " + level + ", bounds are " + ench.getStartLevel() + " to " + ench.getMaxLevel() + ")"); 
/* 396 */     if (!ench.canEnchantItem(this)) {
/* 397 */       throw new IllegalArgumentException("Specified enchantment cannot be applied to this itemstack");
/*     */     }
/*     */     
/* 400 */     addUnsafeEnchantment(ench, level);
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
/*     */   public void addUnsafeEnchantments(@NotNull Map<Enchantment, Integer> enchantments) {
/* 414 */     for (Map.Entry<Enchantment, Integer> entry : enchantments.entrySet()) {
/* 415 */       addUnsafeEnchantment(entry.getKey(), ((Integer)entry.getValue()).intValue());
/*     */     }
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
/*     */   public void addUnsafeEnchantment(@NotNull Enchantment ench, int level) {
/* 432 */     ItemMeta itemMeta = (this.meta == null) ? (this.meta = Bukkit.getItemFactory().getItemMeta(this.type)) : this.meta;
/* 433 */     if (itemMeta != null) {
/* 434 */       itemMeta.addEnchant(ench, level, true);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int removeEnchantment(@NotNull Enchantment ench) {
/* 446 */     int level = getEnchantmentLevel(ench);
/* 447 */     if (level == 0 || this.meta == null) {
/* 448 */       return level;
/*     */     }
/* 450 */     this.meta.removeEnchant(ench);
/* 451 */     return level;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Map<String, Object> serialize() {
/* 458 */     Map<String, Object> result = new LinkedHashMap<>();
/*     */     
/* 460 */     result.put("v", Integer.valueOf(Bukkit.getUnsafe().getDataVersion()));
/* 461 */     result.put("type", getType().name());
/*     */     
/* 463 */     if (getAmount() != 1) {
/* 464 */       result.put("amount", Integer.valueOf(getAmount()));
/*     */     }
/*     */     
/* 467 */     ItemMeta meta = getItemMeta();
/* 468 */     if (!Bukkit.getItemFactory().equals(meta, null)) {
/* 469 */       result.put("meta", meta);
/*     */     }
/*     */     
/* 472 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public static ItemStack deserialize(@NotNull Map<String, Object> args) {
/*     */     Material type;
/* 484 */     int version = args.containsKey("v") ? ((Number)args.get("v")).intValue() : -1;
/* 485 */     short damage = 0;
/* 486 */     int amount = 1;
/*     */     
/* 488 */     if (args.containsKey("damage")) {
/* 489 */       damage = ((Number)args.get("damage")).shortValue();
/*     */     }
/*     */ 
/*     */     
/* 493 */     if (version < 0) {
/* 494 */       type = Material.getMaterial("LEGACY_" + (String)args.get("type"));
/*     */       
/* 496 */       byte dataVal = (type != null && type.getMaxDurability() == 0) ? (byte)damage : 0;
/* 497 */       type = Bukkit.getUnsafe().fromLegacy(new MaterialData(type, dataVal), true);
/*     */ 
/*     */       
/* 500 */       if (dataVal != 0) {
/* 501 */         damage = 0;
/*     */       }
/*     */     } else {
/* 504 */       type = Bukkit.getUnsafe().getMaterial((String)args.get("type"), version);
/*     */     } 
/*     */     
/* 507 */     if (args.containsKey("amount")) {
/* 508 */       amount = ((Number)args.get("amount")).intValue();
/*     */     }
/*     */     
/* 511 */     ItemStack result = new ItemStack(type, amount, damage);
/*     */     
/* 513 */     if (args.containsKey("enchantments")) {
/* 514 */       Object raw = args.get("enchantments");
/*     */       
/* 516 */       if (raw instanceof Map) {
/* 517 */         Map<?, ?> map = (Map<?, ?>)raw;
/*     */         
/* 519 */         for (Map.Entry<?, ?> entry : map.entrySet()) {
/* 520 */           Enchantment enchantment = Enchantment.getByName(entry.getKey().toString());
/*     */           
/* 522 */           if (enchantment != null && entry.getValue() instanceof Integer) {
/* 523 */             result.addUnsafeEnchantment(enchantment, ((Integer)entry.getValue()).intValue());
/*     */           }
/*     */         } 
/*     */       } 
/* 527 */     } else if (args.containsKey("meta")) {
/* 528 */       Object raw = args.get("meta");
/* 529 */       if (raw instanceof ItemMeta) {
/* 530 */         ((ItemMeta)raw).setVersion(version);
/* 531 */         result.setItemMeta((ItemMeta)raw);
/*     */       } 
/*     */     } 
/*     */     
/* 535 */     if (version < 0)
/*     */     {
/* 537 */       if (args.containsKey("damage")) {
/* 538 */         result.setDurability(damage);
/*     */       }
/*     */     }
/*     */     
/* 542 */     return result.ensureServerConversions();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @UndefinedNullability
/*     */   public ItemMeta getItemMeta() {
/* 552 */     return (this.meta == null) ? Bukkit.getItemFactory().getItemMeta(this.type) : this.meta.clone();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasItemMeta() {
/* 561 */     return !Bukkit.getItemFactory().equals(this.meta, null);
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
/*     */   public boolean setItemMeta(@Nullable ItemMeta itemMeta) {
/* 574 */     return setItemMeta0(itemMeta, this.type);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean setItemMeta0(@Nullable ItemMeta itemMeta, @NotNull Material material) {
/* 581 */     if (itemMeta == null) {
/* 582 */       this.meta = null;
/* 583 */       return true;
/*     */     } 
/* 585 */     if (!Bukkit.getItemFactory().isApplicable(itemMeta, material)) {
/* 586 */       return false;
/*     */     }
/* 588 */     this.meta = Bukkit.getItemFactory().asMetaFor(itemMeta, material);
/*     */     
/* 590 */     Material newType = Bukkit.getItemFactory().updateMaterial(this.meta, material);
/* 591 */     if (this.type != newType) {
/* 592 */       this.type = newType;
/*     */     }
/*     */     
/* 595 */     if (this.meta == itemMeta) {
/* 596 */       this.meta = itemMeta.clone();
/*     */     }
/*     */     
/* 599 */     return true;
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
/*     */   @NotNull
/*     */   public ItemStack ensureServerConversions() {
/* 613 */     return Bukkit.getServer().getItemFactory().ensureServerConversions(this);
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
/*     */   public static ItemStack deserializeBytes(@NotNull byte[] bytes) {
/* 626 */     return Bukkit.getUnsafe().deserializeItem(bytes);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public byte[] serializeAsBytes() {
/* 636 */     return Bukkit.getUnsafe().serializeItem(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public String getI18NDisplayName() {
/* 648 */     return Bukkit.getServer().getItemFactory().getI18NDisplayName(this);
/*     */   }
/*     */   
/*     */   public int getMaxItemUseDuration() {
/* 652 */     if (this.type == null || this.type == Material.AIR || !this.type.isItem()) {
/* 653 */       return 0;
/*     */     }
/*     */     
/* 656 */     return ensureServerConversions().getMaxItemUseDuration();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public ItemStack asOne() {
/* 665 */     return asQuantity(1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public ItemStack asQuantity(int qty) {
/* 675 */     ItemStack clone = clone();
/* 676 */     clone.setAmount(qty);
/* 677 */     return clone;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public ItemStack add() {
/* 686 */     return add(1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public ItemStack add(int qty) {
/* 697 */     setAmount(Math.min(getMaxStackSize(), getAmount() + qty));
/* 698 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public ItemStack subtract() {
/* 707 */     return subtract(1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public ItemStack subtract(int qty) {
/* 718 */     setAmount(Math.max(0, getAmount() - qty));
/* 719 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public List<String> getLore() {
/* 728 */     if (!hasItemMeta()) {
/* 729 */       return null;
/*     */     }
/* 731 */     ItemMeta itemMeta = getItemMeta();
/* 732 */     if (!itemMeta.hasLore()) {
/* 733 */       return null;
/*     */     }
/* 735 */     return itemMeta.getLore();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLore(@Nullable List<String> lore) {
/* 745 */     ItemMeta itemMeta = getItemMeta();
/* 746 */     itemMeta.setLore(lore);
/* 747 */     setItemMeta(itemMeta);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addItemFlags(@NotNull ItemFlag... itemFlags) {
/* 756 */     ItemMeta itemMeta = getItemMeta();
/* 757 */     itemMeta.addItemFlags(itemFlags);
/* 758 */     setItemMeta(itemMeta);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeItemFlags(@NotNull ItemFlag... itemFlags) {
/* 767 */     ItemMeta itemMeta = getItemMeta();
/* 768 */     itemMeta.removeItemFlags(itemFlags);
/* 769 */     setItemMeta(itemMeta);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Set<ItemFlag> getItemFlags() {
/* 779 */     ItemMeta itemMeta = getItemMeta();
/* 780 */     return itemMeta.getItemFlags();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasItemFlag(@NotNull ItemFlag flag) {
/* 790 */     ItemMeta itemMeta = getItemMeta();
/* 791 */     return itemMeta.hasItemFlag(flag);
/*     */   }
/*     */   
/*     */   protected ItemStack() {}
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\inventory\ItemStack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
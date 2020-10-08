/*     */ package com.destroystokyo.paper;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Sets;
/*     */ import java.util.Collection;
/*     */ import java.util.Set;
/*     */ import java.util.function.Predicate;
/*     */ import java.util.stream.Collectors;
/*     */ import java.util.stream.Stream;
/*     */ import org.bukkit.Keyed;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.NamespacedKey;
/*     */ import org.bukkit.Tag;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.block.BlockState;
/*     */ import org.bukkit.block.data.BlockData;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MaterialSetTag
/*     */   implements Tag<Material>
/*     */ {
/*     */   private final NamespacedKey key;
/*     */   private final Set<Material> materials;
/*     */   
/*     */   @Deprecated
/*     */   public MaterialSetTag(@NotNull Predicate<Material> filter) {
/*  35 */     this((NamespacedKey)null, (Collection<Material>)Stream.<Material>of(Material.values()).filter(filter).collect(Collectors.toList()));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public MaterialSetTag(@NotNull Collection<Material> materials) {
/*  43 */     this((NamespacedKey)null, materials);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public MaterialSetTag(@NotNull Material... materials) {
/*  51 */     this((NamespacedKey)null, materials);
/*     */   }
/*     */   
/*     */   public MaterialSetTag(@Nullable NamespacedKey key, @NotNull Predicate<Material> filter) {
/*  55 */     this(key, (Collection<Material>)Stream.<Material>of(Material.values()).filter(filter).collect(Collectors.toList()));
/*     */   }
/*     */   
/*     */   public MaterialSetTag(@Nullable NamespacedKey key, @NotNull Material... materials) {
/*  59 */     this(key, Lists.newArrayList((Object[])materials));
/*     */   }
/*     */   
/*     */   public MaterialSetTag(@Nullable NamespacedKey key, @NotNull Collection<Material> materials) {
/*  63 */     this.key = (key != null) ? key : NamespacedKey.randomKey();
/*  64 */     this.materials = Sets.newEnumSet(materials, Material.class);
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public NamespacedKey getKey() {
/*  70 */     return this.key;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public MaterialSetTag add(@NotNull Tag<Material>... tags) {
/*  75 */     for (Tag<Material> tag : tags) {
/*  76 */       add(tag.getValues());
/*     */     }
/*  78 */     return this;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public MaterialSetTag add(@NotNull MaterialSetTag... tags) {
/*  83 */     for (Tag<Material> tag : tags) {
/*  84 */       add(tag.getValues());
/*     */     }
/*  86 */     return this;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public MaterialSetTag add(@NotNull Material... material) {
/*  91 */     this.materials.addAll(Lists.newArrayList((Object[])material));
/*  92 */     return this;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public MaterialSetTag add(@NotNull Collection<Material> materials) {
/*  97 */     this.materials.addAll(materials);
/*  98 */     return this;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public MaterialSetTag contains(@NotNull String with) {
/* 103 */     return add(mat -> mat.name().contains(with));
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public MaterialSetTag endsWith(@NotNull String with) {
/* 108 */     return add(mat -> mat.name().endsWith(with));
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public MaterialSetTag startsWith(@NotNull String with) {
/* 114 */     return add(mat -> mat.name().startsWith(with));
/*     */   }
/*     */   @NotNull
/*     */   public MaterialSetTag add(@NotNull Predicate<Material> filter) {
/* 118 */     add((Collection<Material>)Stream.<Material>of(Material.values()).filter(Material::isLegacy.negate()).filter(filter).collect(Collectors.toList()));
/* 119 */     return this;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public MaterialSetTag not(@NotNull MaterialSetTag tags) {
/* 124 */     not(tags.getValues());
/* 125 */     return this;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public MaterialSetTag not(@NotNull Material... material) {
/* 130 */     this.materials.removeAll(Lists.newArrayList((Object[])material));
/* 131 */     return this;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public MaterialSetTag not(@NotNull Collection<Material> materials) {
/* 136 */     this.materials.removeAll(materials);
/* 137 */     return this;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public MaterialSetTag not(@NotNull Predicate<Material> filter) {
/* 142 */     not((Collection<Material>)Stream.<Material>of(Material.values()).filter(Material::isLegacy.negate()).filter(filter).collect(Collectors.toList()));
/* 143 */     return this;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public MaterialSetTag notEndsWith(@NotNull String with) {
/* 148 */     return not(mat -> mat.name().endsWith(with));
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public MaterialSetTag notStartsWith(@NotNull String with) {
/* 154 */     return not(mat -> mat.name().startsWith(with));
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public Set<Material> getValues() {
/* 159 */     return this.materials;
/*     */   }
/*     */   
/*     */   public boolean isTagged(@NotNull BlockData block) {
/* 163 */     return isTagged(block.getMaterial());
/*     */   }
/*     */   
/*     */   public boolean isTagged(@NotNull BlockState block) {
/* 167 */     return isTagged(block.getType());
/*     */   }
/*     */   
/*     */   public boolean isTagged(@NotNull Block block) {
/* 171 */     return isTagged(block.getType());
/*     */   }
/*     */   
/*     */   public boolean isTagged(@NotNull ItemStack item) {
/* 175 */     return isTagged(item.getType());
/*     */   }
/*     */   
/*     */   public boolean isTagged(@NotNull Material material) {
/* 179 */     return this.materials.contains(material);
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public MaterialSetTag ensureSize(@NotNull String label, int size) {
/* 184 */     long actual = this.materials.stream().filter(Material::isLegacy.negate()).count();
/* 185 */     if (size != actual) {
/* 186 */       throw new IllegalStateException(label + " - Expected " + size + " materials, got " + actual);
/*     */     }
/* 188 */     return this;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\MaterialSetTag.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
/*     */ package org.bukkit.craftbukkit.v1_16_R2.inventory;
/*     */ import com.google.common.collect.ImmutableMap;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import java.util.Map;
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
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ 
/*     */ @DelegateDeserialization(CraftMetaItem.SerializableMeta.class)
/*     */ public class CraftMetaCrossbow extends CraftMetaItem implements CrossbowMeta {
/*  21 */   static final CraftMetaItem.ItemMetaKey CHARGED = new CraftMetaItem.ItemMetaKey("Charged", "charged");
/*  22 */   static final CraftMetaItem.ItemMetaKey CHARGED_PROJECTILES = new CraftMetaItem.ItemMetaKey("ChargedProjectiles", "charged-projectiles");
/*     */   
/*     */   private boolean charged;
/*     */   private List<ItemStack> chargedProjectiles;
/*     */   
/*     */   CraftMetaCrossbow(CraftMetaItem meta) {
/*  28 */     super(meta);
/*     */     
/*  30 */     if (!(meta instanceof CraftMetaCrossbow)) {
/*     */       return;
/*     */     }
/*     */     
/*  34 */     CraftMetaCrossbow crossbow = (CraftMetaCrossbow)meta;
/*  35 */     this.charged = crossbow.charged;
/*     */     
/*  37 */     if (crossbow.hasChargedProjectiles()) {
/*  38 */       this.chargedProjectiles = new ArrayList<>(crossbow.chargedProjectiles);
/*     */     }
/*     */   }
/*     */   
/*     */   CraftMetaCrossbow(NBTTagCompound tag) {
/*  43 */     super(tag);
/*     */     
/*  45 */     this.charged = tag.getBoolean(CHARGED.NBT);
/*     */     
/*  47 */     if (tag.hasKeyOfType(CHARGED_PROJECTILES.NBT, 9)) {
/*  48 */       NBTTagList list = tag.getList(CHARGED_PROJECTILES.NBT, 10);
/*     */       
/*  50 */       if (list != null && !list.isEmpty()) {
/*  51 */         this.chargedProjectiles = new ArrayList<>();
/*     */         
/*  53 */         for (int i = 0; i < list.size(); i++) {
/*  54 */           NBTTagCompound nbttagcompound1 = list.getCompound(i);
/*     */           
/*  56 */           this.chargedProjectiles.add(CraftItemStack.asCraftMirror(ItemStack.a(nbttagcompound1)));
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   CraftMetaCrossbow(Map<String, Object> map) {
/*  63 */     super(map);
/*     */     
/*  65 */     Boolean charged = CraftMetaItem.SerializableMeta.<Boolean>getObject(Boolean.class, map, CHARGED.BUKKIT, true);
/*  66 */     if (charged != null) {
/*  67 */       this.charged = charged.booleanValue();
/*     */     }
/*     */     
/*  70 */     Iterable<?> projectiles = CraftMetaItem.SerializableMeta.<Iterable>getObject(Iterable.class, map, CHARGED_PROJECTILES.BUKKIT, true);
/*  71 */     if (projectiles != null) {
/*  72 */       for (Object stack : projectiles) {
/*  73 */         if (stack instanceof ItemStack) {
/*  74 */           addChargedProjectile((ItemStack)stack);
/*     */         }
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   void applyToItem(NBTTagCompound tag) {
/*  82 */     super.applyToItem(tag);
/*     */     
/*  84 */     tag.setBoolean(CHARGED.NBT, this.charged);
/*  85 */     if (hasChargedProjectiles()) {
/*  86 */       NBTTagList list = new NBTTagList();
/*     */       
/*  88 */       for (ItemStack item : this.chargedProjectiles) {
/*  89 */         NBTTagCompound saved = new NBTTagCompound();
/*  90 */         CraftItemStack.asNMSCopy(item).save(saved);
/*  91 */         list.add(saved);
/*     */       } 
/*     */       
/*  94 */       tag.set(CHARGED_PROJECTILES.NBT, (NBTBase)list);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   boolean applicableTo(Material type) {
/* 100 */     switch (type) {
/*     */       case CROSSBOW:
/* 102 */         return true;
/*     */     } 
/* 104 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   boolean isEmpty() {
/* 110 */     return (super.isEmpty() && isCrossbowEmpty());
/*     */   }
/*     */   
/*     */   boolean isCrossbowEmpty() {
/* 114 */     return !hasChargedProjectiles();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasChargedProjectiles() {
/* 119 */     return (this.chargedProjectiles != null);
/*     */   }
/*     */ 
/*     */   
/*     */   public List<ItemStack> getChargedProjectiles() {
/* 124 */     return (this.chargedProjectiles == null) ? (List<ItemStack>)ImmutableList.of() : (List<ItemStack>)ImmutableList.copyOf(this.chargedProjectiles);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setChargedProjectiles(List<ItemStack> projectiles) {
/* 129 */     this.chargedProjectiles = null;
/* 130 */     this.charged = false;
/*     */     
/* 132 */     if (projectiles == null) {
/*     */       return;
/*     */     }
/*     */     
/* 136 */     for (ItemStack i : projectiles) {
/* 137 */       addChargedProjectile(i);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void addChargedProjectile(ItemStack item) {
/* 143 */     Preconditions.checkArgument((item != null), "item");
/* 144 */     Preconditions.checkArgument((item.getType() == Material.FIREWORK_ROCKET || CraftMagicNumbers.getItem(item.getType()) instanceof net.minecraft.server.v1_16_R2.ItemArrow), "Item %s is not an arrow or firework rocket", item);
/*     */     
/* 146 */     if (this.chargedProjectiles == null) {
/* 147 */       this.chargedProjectiles = new ArrayList<>();
/*     */     }
/*     */     
/* 150 */     this.charged = true;
/* 151 */     this.chargedProjectiles.add(item);
/*     */   }
/*     */ 
/*     */   
/*     */   boolean equalsCommon(CraftMetaItem meta) {
/* 156 */     if (!super.equalsCommon(meta)) {
/* 157 */       return false;
/*     */     }
/* 159 */     if (meta instanceof CraftMetaCrossbow) {
/* 160 */       CraftMetaCrossbow that = (CraftMetaCrossbow)meta;
/*     */       
/* 162 */       return (this.charged == that.charged && (
/* 163 */         hasChargedProjectiles() ? (that.hasChargedProjectiles() && this.chargedProjectiles.equals(that.chargedProjectiles)) : !that.hasChargedProjectiles()));
/*     */     } 
/* 165 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   boolean notUncommon(CraftMetaItem meta) {
/* 170 */     return (super.notUncommon(meta) && (meta instanceof CraftMetaCrossbow || isCrossbowEmpty()));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   int applyHash() {
/* 176 */     int original = super.applyHash(), hash = original;
/*     */     
/* 178 */     if (hasChargedProjectiles()) {
/* 179 */       hash = 61 * hash + (this.charged ? 1 : 0);
/* 180 */       hash = 61 * hash + this.chargedProjectiles.hashCode();
/*     */     } 
/*     */     
/* 183 */     return (original != hash) ? (CraftMetaCrossbow.class.hashCode() ^ hash) : hash;
/*     */   }
/*     */ 
/*     */   
/*     */   public CraftMetaCrossbow clone() {
/* 188 */     return (CraftMetaCrossbow)super.clone();
/*     */   }
/*     */ 
/*     */   
/*     */   ImmutableMap.Builder<String, Object> serialize(ImmutableMap.Builder<String, Object> builder) {
/* 193 */     super.serialize(builder);
/*     */     
/* 195 */     builder.put(CHARGED.BUKKIT, Boolean.valueOf(this.charged));
/* 196 */     if (hasChargedProjectiles()) {
/* 197 */       builder.put(CHARGED_PROJECTILES.BUKKIT, this.chargedProjectiles);
/*     */     }
/*     */     
/* 200 */     return builder;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\inventory\CraftMetaCrossbow.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
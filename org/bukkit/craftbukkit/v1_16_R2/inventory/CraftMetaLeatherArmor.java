/*     */ package org.bukkit.craftbukkit.v1_16_R2.inventory;
/*     */ import com.google.common.collect.ImmutableMap;
/*     */ import java.util.Map;
/*     */ import net.minecraft.server.v1_16_R2.NBTTagCompound;
/*     */ import net.minecraft.server.v1_16_R2.NBTTagInt;
/*     */ import org.bukkit.Color;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.configuration.serialization.DelegateDeserialization;
/*     */ import org.bukkit.inventory.meta.Damageable;
/*     */ import org.bukkit.inventory.meta.ItemMeta;
/*     */ import org.bukkit.inventory.meta.LeatherArmorMeta;
/*     */ import org.bukkit.inventory.meta.Repairable;
/*     */ 
/*     */ @DelegateDeserialization(CraftMetaItem.SerializableMeta.class)
/*     */ class CraftMetaLeatherArmor extends CraftMetaItem implements LeatherArmorMeta {
/*  16 */   static final CraftMetaItem.ItemMetaKey COLOR = new CraftMetaItem.ItemMetaKey("color");
/*     */   
/*  18 */   private Color color = CraftItemFactory.DEFAULT_LEATHER_COLOR;
/*     */   
/*     */   CraftMetaLeatherArmor(CraftMetaItem meta) {
/*  21 */     super(meta);
/*  22 */     if (!(meta instanceof CraftMetaLeatherArmor)) {
/*     */       return;
/*     */     }
/*     */     
/*  26 */     CraftMetaLeatherArmor armorMeta = (CraftMetaLeatherArmor)meta;
/*  27 */     this.color = armorMeta.color;
/*     */   }
/*     */   
/*     */   CraftMetaLeatherArmor(NBTTagCompound tag) {
/*  31 */     super(tag);
/*  32 */     if (tag.hasKey(DISPLAY.NBT)) {
/*  33 */       NBTTagCompound display = tag.getCompound(DISPLAY.NBT);
/*  34 */       if (display.hasKey(COLOR.NBT)) {
/*     */         try {
/*  36 */           this.color = Color.fromRGB(display.getInt(COLOR.NBT));
/*  37 */         } catch (IllegalArgumentException illegalArgumentException) {}
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   CraftMetaLeatherArmor(Map<String, Object> map) {
/*  45 */     super(map);
/*  46 */     setColor(CraftMetaItem.SerializableMeta.<Color>getObject(Color.class, map, COLOR.BUKKIT, true));
/*     */   }
/*     */ 
/*     */   
/*     */   void applyToItem(NBTTagCompound itemTag) {
/*  51 */     super.applyToItem(itemTag);
/*     */     
/*  53 */     if (hasColor()) {
/*  54 */       setDisplayTag(itemTag, COLOR.NBT, (NBTBase)NBTTagInt.a(this.color.asRGB()));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   boolean isEmpty() {
/*  60 */     return (super.isEmpty() && isLeatherArmorEmpty());
/*     */   }
/*     */   
/*     */   boolean isLeatherArmorEmpty() {
/*  64 */     return !hasColor();
/*     */   }
/*     */ 
/*     */   
/*     */   boolean applicableTo(Material type) {
/*  69 */     switch (type) {
/*     */       case LEATHER_HELMET:
/*     */       case LEATHER_HORSE_ARMOR:
/*     */       case LEATHER_CHESTPLATE:
/*     */       case LEATHER_LEGGINGS:
/*     */       case LEATHER_BOOTS:
/*  75 */         return true;
/*     */     } 
/*  77 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public CraftMetaLeatherArmor clone() {
/*  83 */     return (CraftMetaLeatherArmor)super.clone();
/*     */   }
/*     */ 
/*     */   
/*     */   public Color getColor() {
/*  88 */     return this.color;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setColor(Color color) {
/*  93 */     this.color = (color == null) ? CraftItemFactory.DEFAULT_LEATHER_COLOR : color;
/*     */   }
/*     */   
/*     */   boolean hasColor() {
/*  97 */     return !CraftItemFactory.DEFAULT_LEATHER_COLOR.equals(this.color);
/*     */   }
/*     */ 
/*     */   
/*     */   ImmutableMap.Builder<String, Object> serialize(ImmutableMap.Builder<String, Object> builder) {
/* 102 */     super.serialize(builder);
/*     */     
/* 104 */     if (hasColor()) {
/* 105 */       builder.put(COLOR.BUKKIT, this.color);
/*     */     }
/*     */     
/* 108 */     return builder;
/*     */   }
/*     */ 
/*     */   
/*     */   boolean equalsCommon(CraftMetaItem meta) {
/* 113 */     if (!super.equalsCommon(meta)) {
/* 114 */       return false;
/*     */     }
/* 116 */     if (meta instanceof CraftMetaLeatherArmor) {
/* 117 */       CraftMetaLeatherArmor that = (CraftMetaLeatherArmor)meta;
/*     */       
/* 119 */       return this.color.equals(that.color);
/*     */     } 
/* 121 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   boolean notUncommon(CraftMetaItem meta) {
/* 126 */     return (super.notUncommon(meta) && (meta instanceof CraftMetaLeatherArmor || isLeatherArmorEmpty()));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   int applyHash() {
/* 132 */     int original = super.applyHash(), hash = original;
/* 133 */     if (hasColor()) {
/* 134 */       hash ^= this.color.hashCode();
/*     */     }
/* 136 */     return (original != hash) ? (CraftMetaSkull.class.hashCode() ^ hash) : hash;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\inventory\CraftMetaLeatherArmor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
/*     */ package org.bukkit.craftbukkit.v1_16_R2.inventory;
/*     */ import com.google.common.collect.ImmutableMap;
/*     */ import java.util.Map;
/*     */ import net.minecraft.server.v1_16_R2.NBTTagCompound;
/*     */ import org.bukkit.FireworkEffect;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.inventory.meta.Damageable;
/*     */ import org.bukkit.inventory.meta.FireworkEffectMeta;
/*     */ import org.bukkit.inventory.meta.ItemMeta;
/*     */ import org.bukkit.inventory.meta.Repairable;
/*     */ 
/*     */ @DelegateDeserialization(CraftMetaItem.SerializableMeta.class)
/*     */ class CraftMetaCharge extends CraftMetaItem implements FireworkEffectMeta {
/*  14 */   static final CraftMetaItem.ItemMetaKey EXPLOSION = new CraftMetaItem.ItemMetaKey("Explosion", "firework-effect");
/*     */   
/*     */   private FireworkEffect effect;
/*     */   
/*     */   CraftMetaCharge(CraftMetaItem meta) {
/*  19 */     super(meta);
/*     */     
/*  21 */     if (meta instanceof CraftMetaCharge) {
/*  22 */       this.effect = ((CraftMetaCharge)meta).effect;
/*     */     }
/*     */   }
/*     */   
/*     */   CraftMetaCharge(Map<String, Object> map) {
/*  27 */     super(map);
/*     */     
/*  29 */     setEffect(CraftMetaItem.SerializableMeta.<FireworkEffect>getObject(FireworkEffect.class, map, EXPLOSION.BUKKIT, true));
/*     */   }
/*     */   
/*     */   CraftMetaCharge(NBTTagCompound tag) {
/*  33 */     super(tag);
/*     */     
/*  35 */     if (tag.hasKey(EXPLOSION.NBT)) {
/*     */       try {
/*  37 */         this.effect = CraftMetaFirework.getEffect(tag.getCompound(EXPLOSION.NBT));
/*  38 */       } catch (IllegalArgumentException illegalArgumentException) {}
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setEffect(FireworkEffect effect) {
/*  46 */     this.effect = effect;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasEffect() {
/*  51 */     return (this.effect != null);
/*     */   }
/*     */ 
/*     */   
/*     */   public FireworkEffect getEffect() {
/*  56 */     return this.effect;
/*     */   }
/*     */ 
/*     */   
/*     */   void applyToItem(NBTTagCompound itemTag) {
/*  61 */     super.applyToItem(itemTag);
/*     */     
/*  63 */     if (hasEffect()) {
/*  64 */       itemTag.set(EXPLOSION.NBT, (NBTBase)CraftMetaFirework.getExplosion(this.effect));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   boolean applicableTo(Material type) {
/*  70 */     switch (type) {
/*     */       case FIREWORK_STAR:
/*  72 */         return true;
/*     */     } 
/*  74 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   boolean isEmpty() {
/*  80 */     return (super.isEmpty() && !hasChargeMeta());
/*     */   }
/*     */   
/*     */   boolean hasChargeMeta() {
/*  84 */     return hasEffect();
/*     */   }
/*     */ 
/*     */   
/*     */   boolean equalsCommon(CraftMetaItem meta) {
/*  89 */     if (!super.equalsCommon(meta)) {
/*  90 */       return false;
/*     */     }
/*  92 */     if (meta instanceof CraftMetaCharge) {
/*  93 */       CraftMetaCharge that = (CraftMetaCharge)meta;
/*     */       
/*  95 */       return hasEffect() ? ((that.hasEffect() && this.effect.equals(that.effect))) : (!that.hasEffect());
/*     */     } 
/*  97 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   boolean notUncommon(CraftMetaItem meta) {
/* 102 */     return (super.notUncommon(meta) && (meta instanceof CraftMetaCharge || !hasChargeMeta()));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   int applyHash() {
/* 108 */     int original = super.applyHash(), hash = original;
/*     */     
/* 110 */     if (hasEffect()) {
/* 111 */       hash = 61 * hash + this.effect.hashCode();
/*     */     }
/*     */     
/* 114 */     return (hash != original) ? (CraftMetaCharge.class.hashCode() ^ hash) : hash;
/*     */   }
/*     */ 
/*     */   
/*     */   public CraftMetaCharge clone() {
/* 119 */     return (CraftMetaCharge)super.clone();
/*     */   }
/*     */ 
/*     */   
/*     */   ImmutableMap.Builder<String, Object> serialize(ImmutableMap.Builder<String, Object> builder) {
/* 124 */     super.serialize(builder);
/*     */     
/* 126 */     if (hasEffect()) {
/* 127 */       builder.put(EXPLOSION.BUKKIT, this.effect);
/*     */     }
/*     */     
/* 130 */     return builder;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\inventory\CraftMetaCharge.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
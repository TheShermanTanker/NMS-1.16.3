/*     */ package org.bukkit.craftbukkit.v1_16_R2.entity;
/*     */ import com.google.common.collect.ImmutableList;
/*     */ import java.util.List;
/*     */ import net.minecraft.server.v1_16_R2.Entity;
/*     */ import net.minecraft.server.v1_16_R2.EntityArrow;
/*     */ import net.minecraft.server.v1_16_R2.EntityTippedArrow;
/*     */ import net.minecraft.server.v1_16_R2.MobEffect;
/*     */ import net.minecraft.server.v1_16_R2.MobEffectList;
/*     */ import org.apache.commons.lang.Validate;
/*     */ import org.bukkit.Color;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.CraftServer;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.potion.CraftPotionUtil;
/*     */ import org.bukkit.entity.Arrow;
/*     */ import org.bukkit.entity.EntityType;
/*     */ import org.bukkit.potion.PotionData;
/*     */ import org.bukkit.potion.PotionEffect;
/*     */ import org.bukkit.potion.PotionEffectType;
/*     */ 
/*     */ public class CraftTippedArrow extends CraftArrow implements Arrow {
/*     */   public CraftTippedArrow(CraftServer server, EntityTippedArrow entity) {
/*  21 */     super(server, (EntityArrow)entity);
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityTippedArrow getHandle() {
/*  26 */     return (EntityTippedArrow)this.entity;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/*  31 */     return "CraftTippedArrow";
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityType getType() {
/*  36 */     return EntityType.ARROW;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean addCustomEffect(PotionEffect effect, boolean override) {
/*  41 */     int effectId = effect.getType().getId();
/*  42 */     MobEffect existing = null;
/*  43 */     for (MobEffect mobEffect : (getHandle()).effects) {
/*  44 */       if (MobEffectList.getId(mobEffect.getMobEffect()) == effectId) {
/*  45 */         existing = mobEffect;
/*     */       }
/*     */     } 
/*  48 */     if (existing != null) {
/*  49 */       if (!override) {
/*  50 */         return false;
/*     */       }
/*  52 */       (getHandle()).effects.remove(existing);
/*     */     } 
/*  54 */     getHandle().addEffect(CraftPotionUtil.fromBukkit(effect));
/*  55 */     getHandle().refreshEffects();
/*  56 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void clearCustomEffects() {
/*  61 */     (getHandle()).effects.clear();
/*  62 */     getHandle().refreshEffects();
/*     */   }
/*     */ 
/*     */   
/*     */   public List<PotionEffect> getCustomEffects() {
/*  67 */     ImmutableList.Builder<PotionEffect> builder = ImmutableList.builder();
/*  68 */     for (MobEffect effect : (getHandle()).effects) {
/*  69 */       builder.add(CraftPotionUtil.toBukkit(effect));
/*     */     }
/*  71 */     return (List<PotionEffect>)builder.build();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasCustomEffect(PotionEffectType type) {
/*  76 */     for (MobEffect effect : (getHandle()).effects) {
/*  77 */       if (CraftPotionUtil.equals(effect.getMobEffect(), type)) {
/*  78 */         return true;
/*     */       }
/*     */     } 
/*  81 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasCustomEffects() {
/*  86 */     return !(getHandle()).effects.isEmpty();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean removeCustomEffect(PotionEffectType effect) {
/*  91 */     int effectId = effect.getId();
/*  92 */     MobEffect existing = null;
/*  93 */     for (MobEffect mobEffect : (getHandle()).effects) {
/*  94 */       if (MobEffectList.getId(mobEffect.getMobEffect()) == effectId) {
/*  95 */         existing = mobEffect;
/*     */       }
/*     */     } 
/*  98 */     if (existing == null) {
/*  99 */       return false;
/*     */     }
/* 101 */     (getHandle()).effects.remove(existing);
/* 102 */     getHandle().refreshEffects();
/* 103 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBasePotionData(PotionData data) {
/* 108 */     Validate.notNull(data, "PotionData cannot be null");
/* 109 */     getHandle().setType(CraftPotionUtil.fromBukkit(data));
/*     */   }
/*     */ 
/*     */   
/*     */   public PotionData getBasePotionData() {
/* 114 */     return CraftPotionUtil.toBukkit(getHandle().getType());
/*     */   }
/*     */ 
/*     */   
/*     */   public void setColor(Color color) {
/* 119 */     getHandle().setColor(color.asRGB());
/*     */   }
/*     */ 
/*     */   
/*     */   public Color getColor() {
/* 124 */     return Color.fromRGB(getHandle().getColor());
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\entity\CraftTippedArrow.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
/*     */ package org.bukkit.craftbukkit.v1_16_R2.entity;
/*     */ 
/*     */ import com.google.common.collect.ImmutableList;
/*     */ import java.util.List;
/*     */ import net.minecraft.server.v1_16_R2.Entity;
/*     */ import net.minecraft.server.v1_16_R2.EntityAreaEffectCloud;
/*     */ import net.minecraft.server.v1_16_R2.EntityLiving;
/*     */ import net.minecraft.server.v1_16_R2.MobEffect;
/*     */ import net.minecraft.server.v1_16_R2.MobEffectList;
/*     */ import org.apache.commons.lang.Validate;
/*     */ import org.bukkit.Color;
/*     */ import org.bukkit.Particle;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.CraftParticle;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.CraftServer;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.potion.CraftPotionUtil;
/*     */ import org.bukkit.entity.AreaEffectCloud;
/*     */ import org.bukkit.entity.EntityType;
/*     */ import org.bukkit.potion.PotionData;
/*     */ import org.bukkit.potion.PotionEffect;
/*     */ import org.bukkit.potion.PotionEffectType;
/*     */ import org.bukkit.projectiles.ProjectileSource;
/*     */ 
/*     */ public class CraftAreaEffectCloud
/*     */   extends CraftEntity implements AreaEffectCloud {
/*     */   public CraftAreaEffectCloud(CraftServer server, EntityAreaEffectCloud entity) {
/*  26 */     super(server, (Entity)entity);
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityAreaEffectCloud getHandle() {
/*  31 */     return (EntityAreaEffectCloud)super.getHandle();
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/*  36 */     return "CraftAreaEffectCloud";
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityType getType() {
/*  41 */     return EntityType.AREA_EFFECT_CLOUD;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getDuration() {
/*  46 */     return getHandle().getDuration();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDuration(int duration) {
/*  51 */     getHandle().setDuration(duration);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getWaitTime() {
/*  56 */     return (getHandle()).waitTime;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setWaitTime(int waitTime) {
/*  61 */     getHandle().setWaitTime(waitTime);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getReapplicationDelay() {
/*  66 */     return (getHandle()).reapplicationDelay;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setReapplicationDelay(int delay) {
/*  71 */     (getHandle()).reapplicationDelay = delay;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getDurationOnUse() {
/*  76 */     return (getHandle()).durationOnUse;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDurationOnUse(int duration) {
/*  81 */     (getHandle()).durationOnUse = duration;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getRadius() {
/*  86 */     return getHandle().getRadius();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRadius(float radius) {
/*  91 */     getHandle().setRadius(radius);
/*     */   }
/*     */ 
/*     */   
/*     */   public float getRadiusOnUse() {
/*  96 */     return (getHandle()).radiusOnUse;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRadiusOnUse(float radius) {
/* 101 */     getHandle().setRadiusOnUse(radius);
/*     */   }
/*     */ 
/*     */   
/*     */   public float getRadiusPerTick() {
/* 106 */     return (getHandle()).radiusPerTick;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRadiusPerTick(float radius) {
/* 111 */     getHandle().setRadiusPerTick(radius);
/*     */   }
/*     */ 
/*     */   
/*     */   public Particle getParticle() {
/* 116 */     return CraftParticle.toBukkit(getHandle().getParticle());
/*     */   }
/*     */ 
/*     */   
/*     */   public void setParticle(Particle particle) {
/* 121 */     setParticle(particle, null);
/*     */   }
/*     */ 
/*     */   
/*     */   public <T> void setParticle(Particle particle, T data) {
/* 126 */     getHandle().setParticle(CraftParticle.toNMS(particle, data));
/*     */   }
/*     */ 
/*     */   
/*     */   public Color getColor() {
/* 131 */     return Color.fromRGB(getHandle().getColor());
/*     */   }
/*     */ 
/*     */   
/*     */   public void setColor(Color color) {
/* 136 */     getHandle().setColor(color.asRGB());
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean addCustomEffect(PotionEffect effect, boolean override) {
/* 141 */     int effectId = effect.getType().getId();
/* 142 */     MobEffect existing = null;
/* 143 */     for (MobEffect mobEffect : (getHandle()).effects) {
/* 144 */       if (MobEffectList.getId(mobEffect.getMobEffect()) == effectId) {
/* 145 */         existing = mobEffect;
/*     */       }
/*     */     } 
/* 148 */     if (existing != null) {
/* 149 */       if (!override) {
/* 150 */         return false;
/*     */       }
/* 152 */       (getHandle()).effects.remove(existing);
/*     */     } 
/* 154 */     getHandle().addEffect(CraftPotionUtil.fromBukkit(effect));
/* 155 */     getHandle().refreshEffects();
/* 156 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void clearCustomEffects() {
/* 161 */     (getHandle()).effects.clear();
/* 162 */     getHandle().refreshEffects();
/*     */   }
/*     */ 
/*     */   
/*     */   public List<PotionEffect> getCustomEffects() {
/* 167 */     ImmutableList.Builder<PotionEffect> builder = ImmutableList.builder();
/* 168 */     for (MobEffect effect : (getHandle()).effects) {
/* 169 */       builder.add(CraftPotionUtil.toBukkit(effect));
/*     */     }
/* 171 */     return (List<PotionEffect>)builder.build();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasCustomEffect(PotionEffectType type) {
/* 176 */     for (MobEffect effect : (getHandle()).effects) {
/* 177 */       if (CraftPotionUtil.equals(effect.getMobEffect(), type)) {
/* 178 */         return true;
/*     */       }
/*     */     } 
/* 181 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasCustomEffects() {
/* 186 */     return !(getHandle()).effects.isEmpty();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean removeCustomEffect(PotionEffectType effect) {
/* 191 */     int effectId = effect.getId();
/* 192 */     MobEffect existing = null;
/* 193 */     for (MobEffect mobEffect : (getHandle()).effects) {
/* 194 */       if (MobEffectList.getId(mobEffect.getMobEffect()) == effectId) {
/* 195 */         existing = mobEffect;
/*     */       }
/*     */     } 
/* 198 */     if (existing == null) {
/* 199 */       return false;
/*     */     }
/* 201 */     (getHandle()).effects.remove(existing);
/* 202 */     getHandle().refreshEffects();
/* 203 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBasePotionData(PotionData data) {
/* 208 */     Validate.notNull(data, "PotionData cannot be null");
/* 209 */     getHandle().setType(CraftPotionUtil.fromBukkit(data));
/*     */   }
/*     */ 
/*     */   
/*     */   public PotionData getBasePotionData() {
/* 214 */     return CraftPotionUtil.toBukkit(getHandle().getType());
/*     */   }
/*     */ 
/*     */   
/*     */   public ProjectileSource getSource() {
/* 219 */     EntityLiving source = getHandle().getSource();
/* 220 */     return (source == null) ? null : (ProjectileSource)source.getBukkitEntity();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSource(ProjectileSource shooter) {
/* 225 */     if (shooter instanceof CraftLivingEntity) {
/* 226 */       getHandle().setSource(((CraftLivingEntity)shooter).getHandle());
/*     */     } else {
/* 228 */       getHandle().setSource((EntityLiving)null);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\entity\CraftAreaEffectCloud.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
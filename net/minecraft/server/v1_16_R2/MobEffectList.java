/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.Maps;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.UUID;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.event.CraftEventFactory;
/*     */ import org.bukkit.event.entity.EntityRegainHealthEvent;
/*     */ import org.bukkit.event.entity.FoodLevelChangeEvent;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MobEffectList
/*     */ {
/*  17 */   private final Map<AttributeBase, AttributeModifier> a = Maps.newHashMap();
/*     */   private final MobEffectInfo b;
/*     */   private final int c;
/*     */   @Nullable
/*     */   private String d;
/*     */   
/*     */   @Nullable
/*     */   public static MobEffectList fromId(int i) {
/*  25 */     return IRegistry.MOB_EFFECT.fromId(i);
/*     */   }
/*     */   
/*     */   public static int getId(MobEffectList mobeffectlist) {
/*  29 */     return IRegistry.MOB_EFFECT.a(mobeffectlist);
/*     */   }
/*     */   
/*     */   protected MobEffectList(MobEffectInfo mobeffectinfo, int i) {
/*  33 */     this.b = mobeffectinfo;
/*  34 */     this.c = i;
/*     */   }
/*     */   
/*     */   public void tick(EntityLiving entityliving, int i) {
/*  38 */     if (this == MobEffects.REGENERATION) {
/*  39 */       if (entityliving.getHealth() < entityliving.getMaxHealth()) {
/*  40 */         entityliving.heal(1.0F, EntityRegainHealthEvent.RegainReason.MAGIC_REGEN);
/*     */       }
/*  42 */     } else if (this == MobEffects.POISON) {
/*  43 */       if (entityliving.getHealth() > 1.0F) {
/*  44 */         entityliving.damageEntity(CraftEventFactory.POISON, 1.0F);
/*     */       }
/*  46 */     } else if (this == MobEffects.WITHER) {
/*  47 */       entityliving.damageEntity(DamageSource.WITHER, 1.0F);
/*  48 */     } else if (this == MobEffects.HUNGER && entityliving instanceof EntityHuman) {
/*  49 */       ((EntityHuman)entityliving).applyExhaustion(0.005F * (i + 1));
/*  50 */     } else if (this == MobEffects.SATURATION && entityliving instanceof EntityHuman) {
/*  51 */       if (!entityliving.world.isClientSide)
/*     */       {
/*  53 */         EntityHuman entityhuman = (EntityHuman)entityliving;
/*  54 */         int oldFoodLevel = (entityhuman.getFoodData()).foodLevel;
/*     */         
/*  56 */         FoodLevelChangeEvent event = CraftEventFactory.callFoodLevelChangeEvent(entityhuman, i + 1 + oldFoodLevel);
/*     */         
/*  58 */         if (!event.isCancelled()) {
/*  59 */           entityhuman.getFoodData().eat(event.getFoodLevel() - oldFoodLevel, 1.0F);
/*     */         }
/*     */         
/*  62 */         ((EntityPlayer)entityhuman).playerConnection.sendPacket(new PacketPlayOutUpdateHealth(((EntityPlayer)entityhuman).getBukkitEntity().getScaledHealth(), (entityhuman.getFoodData()).foodLevel, (entityhuman.getFoodData()).saturationLevel));
/*     */       }
/*     */     
/*  65 */     } else if ((this != MobEffects.HEAL || entityliving.di()) && (this != MobEffects.HARM || !entityliving.di())) {
/*  66 */       if ((this == MobEffects.HARM && !entityliving.di()) || (this == MobEffects.HEAL && entityliving.di())) {
/*  67 */         entityliving.damageEntity(DamageSource.MAGIC, (6 << i));
/*     */       }
/*     */     } else {
/*  70 */       entityliving.heal(Math.max(4 << i, 0), EntityRegainHealthEvent.RegainReason.MAGIC);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void applyInstantEffect(@Nullable Entity entity, @Nullable Entity entity1, EntityLiving entityliving, int i, double d0) {
/*  78 */     if ((this != MobEffects.HEAL || entityliving.di()) && (this != MobEffects.HARM || !entityliving.di())) {
/*  79 */       if ((this != MobEffects.HARM || entityliving.di()) && (this != MobEffects.HEAL || !entityliving.di())) {
/*  80 */         tick(entityliving, i);
/*     */       } else {
/*  82 */         int j = (int)(d0 * (6 << i) + 0.5D);
/*  83 */         if (entity == null) {
/*  84 */           entityliving.damageEntity(DamageSource.MAGIC, j);
/*     */         } else {
/*  86 */           entityliving.damageEntity(DamageSource.c(entity, entity1), j);
/*     */         } 
/*     */       } 
/*     */     } else {
/*  90 */       int j = (int)(d0 * (4 << i) + 0.5D);
/*  91 */       entityliving.heal(j, EntityRegainHealthEvent.RegainReason.MAGIC);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean a(int i, int j) {
/*  99 */     if (this == MobEffects.REGENERATION) {
/* 100 */       int k = 50 >> j;
/* 101 */       return (k > 0) ? ((i % k == 0)) : true;
/* 102 */     }  if (this == MobEffects.POISON) {
/* 103 */       int k = 25 >> j;
/* 104 */       return (k > 0) ? ((i % k == 0)) : true;
/* 105 */     }  if (this == MobEffects.WITHER) {
/* 106 */       int k = 40 >> j;
/* 107 */       return (k > 0) ? ((i % k == 0)) : true;
/*     */     } 
/* 109 */     return (this == MobEffects.HUNGER);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isInstant() {
/* 114 */     return false;
/*     */   }
/*     */   
/*     */   protected String b() {
/* 118 */     if (this.d == null) {
/* 119 */       this.d = SystemUtils.a("effect", IRegistry.MOB_EFFECT.getKey(this));
/*     */     }
/*     */     
/* 122 */     return this.d;
/*     */   }
/*     */   
/*     */   public String c() {
/* 126 */     return b();
/*     */   }
/*     */   
/*     */   public IChatBaseComponent d() {
/* 130 */     return new ChatMessage(c());
/*     */   }
/*     */   
/*     */   public int getColor() {
/* 134 */     return this.c;
/*     */   }
/*     */   
/*     */   public MobEffectList a(AttributeBase attributebase, String s, double d0, AttributeModifier.Operation attributemodifier_operation) {
/* 138 */     AttributeModifier attributemodifier = new AttributeModifier(UUID.fromString(s), this::c, d0, attributemodifier_operation);
/*     */     
/* 140 */     this.a.put(attributebase, attributemodifier);
/* 141 */     return this;
/*     */   }
/*     */   
/*     */   public void a(EntityLiving entityliving, AttributeMapBase attributemapbase, int i) {
/* 145 */     Iterator<Map.Entry<AttributeBase, AttributeModifier>> iterator = this.a.entrySet().iterator();
/*     */     
/* 147 */     while (iterator.hasNext()) {
/* 148 */       Map.Entry<AttributeBase, AttributeModifier> entry = iterator.next();
/* 149 */       AttributeModifiable attributemodifiable = attributemapbase.a(entry.getKey());
/*     */       
/* 151 */       if (attributemodifiable != null) {
/* 152 */         attributemodifiable.removeModifier(entry.getValue());
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void b(EntityLiving entityliving, AttributeMapBase attributemapbase, int i) {
/* 159 */     Iterator<Map.Entry<AttributeBase, AttributeModifier>> iterator = this.a.entrySet().iterator();
/*     */     
/* 161 */     while (iterator.hasNext()) {
/* 162 */       Map.Entry<AttributeBase, AttributeModifier> entry = iterator.next();
/* 163 */       AttributeModifiable attributemodifiable = attributemapbase.a(entry.getKey());
/*     */       
/* 165 */       if (attributemodifiable != null) {
/* 166 */         AttributeModifier attributemodifier = entry.getValue();
/*     */         
/* 168 */         attributemodifiable.removeModifier(attributemodifier);
/* 169 */         attributemodifiable.addModifier(new AttributeModifier(attributemodifier.getUniqueId(), c() + " " + i, a(i, attributemodifier), attributemodifier.getOperation()));
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public double a(int i, AttributeModifier attributemodifier) {
/* 176 */     return attributemodifier.getAmount() * (i + 1);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\MobEffectList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
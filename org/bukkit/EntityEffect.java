/*     */ package org.bukkit;
/*     */ 
/*     */ import org.bukkit.entity.Ageable;
/*     */ import org.bukkit.entity.ArmorStand;
/*     */ import org.bukkit.entity.Cat;
/*     */ import org.bukkit.entity.Dolphin;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.entity.Firework;
/*     */ import org.bukkit.entity.Fox;
/*     */ import org.bukkit.entity.Guardian;
/*     */ import org.bukkit.entity.IronGolem;
/*     */ import org.bukkit.entity.LivingEntity;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.entity.Rabbit;
/*     */ import org.bukkit.entity.Ravager;
/*     */ import org.bukkit.entity.Squid;
/*     */ import org.bukkit.entity.Tameable;
/*     */ import org.bukkit.entity.TippedArrow;
/*     */ import org.bukkit.entity.Villager;
/*     */ import org.bukkit.entity.Witch;
/*     */ import org.bukkit.entity.Wolf;
/*     */ import org.bukkit.entity.ZombieVillager;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public enum EntityEffect
/*     */ {
/*  33 */   ARROW_PARTICLES(0, (Class)TippedArrow.class),
/*     */ 
/*     */ 
/*     */   
/*  37 */   RABBIT_JUMP(1, (Class)Rabbit.class),
/*     */ 
/*     */ 
/*     */   
/*  41 */   HURT(2, (Class)LivingEntity.class),
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  50 */   DEATH(3, Entity.class),
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  58 */   WOLF_SMOKE(6, (Class)Tameable.class),
/*     */ 
/*     */ 
/*     */   
/*  62 */   WOLF_HEARTS(7, (Class)Wolf.class),
/*     */ 
/*     */ 
/*     */   
/*  66 */   WOLF_SHAKE(8, (Class)Wolf.class),
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  74 */   SHEEP_EAT(10, Entity.class),
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  79 */   IRON_GOLEM_ROSE(11, (Class)IronGolem.class),
/*     */ 
/*     */ 
/*     */   
/*  83 */   VILLAGER_HEART(12, (Class)Villager.class),
/*     */ 
/*     */ 
/*     */   
/*  87 */   VILLAGER_ANGRY(13, (Class)Villager.class),
/*     */ 
/*     */ 
/*     */   
/*  91 */   VILLAGER_HAPPY(14, (Class)Villager.class),
/*     */ 
/*     */ 
/*     */   
/*  95 */   WITCH_MAGIC(15, (Class)Witch.class),
/*     */ 
/*     */ 
/*     */   
/*  99 */   ZOMBIE_TRANSFORM(16, (Class)ZombieVillager.class),
/*     */ 
/*     */ 
/*     */   
/* 103 */   FIREWORK_EXPLODE(17, (Class)Firework.class),
/*     */ 
/*     */ 
/*     */   
/* 107 */   LOVE_HEARTS(18, (Class)Ageable.class),
/*     */ 
/*     */ 
/*     */   
/* 111 */   SQUID_ROTATE(19, (Class)Squid.class),
/*     */ 
/*     */ 
/*     */   
/* 115 */   ENTITY_POOF(20, (Class)LivingEntity.class),
/*     */ 
/*     */ 
/*     */   
/* 119 */   GUARDIAN_TARGET(21, (Class)Guardian.class),
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 124 */   SHIELD_BLOCK(29, (Class)LivingEntity.class),
/*     */ 
/*     */ 
/*     */   
/* 128 */   SHIELD_BREAK(30, (Class)LivingEntity.class),
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 133 */   ARMOR_STAND_HIT(32, (Class)ArmorStand.class),
/*     */ 
/*     */ 
/*     */   
/* 137 */   THORNS_HURT(33, (Class)LivingEntity.class),
/*     */ 
/*     */ 
/*     */   
/* 141 */   IRON_GOLEM_SHEATH(34, (Class)IronGolem.class),
/*     */ 
/*     */ 
/*     */   
/* 145 */   TOTEM_RESURRECT(35, (Class)LivingEntity.class),
/*     */ 
/*     */ 
/*     */   
/* 149 */   HURT_DROWN(36, (Class)LivingEntity.class),
/*     */ 
/*     */ 
/*     */   
/* 153 */   HURT_EXPLOSION(37, (Class)LivingEntity.class),
/*     */ 
/*     */ 
/*     */   
/* 157 */   DOLPHIN_FED(38, (Class)Dolphin.class),
/*     */ 
/*     */ 
/*     */   
/* 161 */   RAVAGER_STUNNED(39, (Class)Ravager.class),
/*     */ 
/*     */ 
/*     */   
/* 165 */   CAT_TAME_FAIL(40, (Class)Cat.class),
/*     */ 
/*     */ 
/*     */   
/* 169 */   CAT_TAME_SUCCESS(41, (Class)Cat.class),
/*     */ 
/*     */ 
/*     */   
/* 173 */   VILLAGER_SPLASH(42, (Class)Villager.class),
/*     */ 
/*     */ 
/*     */   
/* 177 */   PLAYER_BAD_OMEN_RAID(43, (Class)Player.class),
/*     */ 
/*     */ 
/*     */   
/* 181 */   HURT_BERRY_BUSH(44, (Class)LivingEntity.class),
/*     */ 
/*     */ 
/*     */   
/* 185 */   FOX_CHEW(45, (Class)Fox.class),
/*     */ 
/*     */ 
/*     */   
/* 189 */   TELEPORT_ENDER(46, (Class)LivingEntity.class),
/*     */ 
/*     */ 
/*     */   
/* 193 */   BREAK_EQUIPMENT_MAIN_HAND(47, (Class)LivingEntity.class),
/*     */ 
/*     */ 
/*     */   
/* 197 */   BREAK_EQUIPMENT_OFF_HAND(48, (Class)LivingEntity.class),
/*     */ 
/*     */ 
/*     */   
/* 201 */   BREAK_EQUIPMENT_HELMET(49, (Class)LivingEntity.class),
/*     */ 
/*     */ 
/*     */   
/* 205 */   BREAK_EQUIPMENT_CHESTPLATE(50, (Class)LivingEntity.class),
/*     */ 
/*     */ 
/*     */   
/* 209 */   BREAK_EQUIPMENT_LEGGINGS(51, (Class)LivingEntity.class),
/*     */ 
/*     */ 
/*     */   
/* 213 */   BREAK_EQUIPMENT_BOOTS(52, (Class)LivingEntity.class);
/*     */   
/*     */   private final byte data;
/*     */   private final Class<? extends Entity> applicable;
/*     */   
/*     */   EntityEffect(int data, Class<? extends Entity> clazz) {
/* 219 */     this.data = (byte)data;
/* 220 */     this.applicable = clazz;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public byte getData() {
/* 231 */     return this.data;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Class<? extends Entity> getApplicable() {
/* 241 */     return this.applicable;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\EntityEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
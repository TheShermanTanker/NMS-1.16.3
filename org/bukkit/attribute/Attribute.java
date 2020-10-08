/*    */ package org.bukkit.attribute;
/*    */ 
/*    */ import org.bukkit.Keyed;
/*    */ import org.bukkit.NamespacedKey;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public enum Attribute
/*    */   implements Keyed
/*    */ {
/* 15 */   GENERIC_MAX_HEALTH("generic.max_health"),
/*    */ 
/*    */ 
/*    */   
/* 19 */   GENERIC_FOLLOW_RANGE("generic.follow_range"),
/*    */ 
/*    */ 
/*    */   
/* 23 */   GENERIC_KNOCKBACK_RESISTANCE("generic.knockback_resistance"),
/*    */ 
/*    */ 
/*    */   
/* 27 */   GENERIC_MOVEMENT_SPEED("generic.movement_speed"),
/*    */ 
/*    */ 
/*    */   
/* 31 */   GENERIC_FLYING_SPEED("generic.flying_speed"),
/*    */ 
/*    */ 
/*    */   
/* 35 */   GENERIC_ATTACK_DAMAGE("generic.attack_damage"),
/*    */ 
/*    */ 
/*    */   
/* 39 */   GENERIC_ATTACK_KNOCKBACK("generic.attack_knockback"),
/*    */ 
/*    */ 
/*    */   
/* 43 */   GENERIC_ATTACK_SPEED("generic.attack_speed"),
/*    */ 
/*    */ 
/*    */   
/* 47 */   GENERIC_ARMOR("generic.armor"),
/*    */ 
/*    */ 
/*    */   
/* 51 */   GENERIC_ARMOR_TOUGHNESS("generic.armor_toughness"),
/*    */ 
/*    */ 
/*    */   
/* 55 */   GENERIC_LUCK("generic.luck"),
/*    */ 
/*    */ 
/*    */   
/* 59 */   HORSE_JUMP_STRENGTH("horse.jump_strength"),
/*    */ 
/*    */ 
/*    */   
/* 63 */   ZOMBIE_SPAWN_REINFORCEMENTS("zombie.spawn_reinforcements");
/*    */   
/*    */   private final NamespacedKey key;
/*    */   
/*    */   Attribute(String key) {
/* 68 */     this.key = NamespacedKey.minecraft(key);
/*    */   }
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public NamespacedKey getKey() {
/* 74 */     return this.key;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\attribute\Attribute.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
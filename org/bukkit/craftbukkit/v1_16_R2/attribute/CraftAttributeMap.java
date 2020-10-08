/*    */ package org.bukkit.craftbukkit.v1_16_R2.attribute;
/*    */ 
/*    */ import com.google.common.base.Preconditions;
/*    */ import com.google.common.collect.ImmutableMap;
/*    */ import net.minecraft.server.v1_16_R2.AttributeBase;
/*    */ import net.minecraft.server.v1_16_R2.AttributeMapBase;
/*    */ import net.minecraft.server.v1_16_R2.AttributeModifiable;
/*    */ import net.minecraft.server.v1_16_R2.IRegistry;
/*    */ import org.bukkit.Registry;
/*    */ import org.bukkit.attribute.Attributable;
/*    */ import org.bukkit.attribute.Attribute;
/*    */ import org.bukkit.attribute.AttributeInstance;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.util.CraftNamespacedKey;
/*    */ 
/*    */ public class CraftAttributeMap implements Attributable {
/*    */   private final AttributeMapBase handle;
/* 17 */   private static final ImmutableMap<String, String> legacyNMS = ImmutableMap.builder().put("generic.maxHealth", "generic.max_health").put("Max Health", "generic.max_health").put("zombie.spawnReinforcements", "zombie.spawn_reinforcements").put("Spawn Reinforcements Chance", "zombie.spawn_reinforcements").put("horse.jumpStrength", "horse.jump_strength").put("Jump Strength", "horse.jump_strength").put("generic.followRange", "generic.follow_range").put("Follow Range", "generic.follow_range").put("generic.knockbackResistance", "generic.knockback_resistance").put("Knockback Resistance", "generic.knockback_resistance").put("generic.movementSpeed", "generic.movement_speed").put("Movement Speed", "generic.movement_speed").put("generic.flyingSpeed", "generic.flying_speed").put("Flying Speed", "generic.flying_speed").put("generic.attackDamage", "generic.attack_damage").put("generic.attackKnockback", "generic.attack_knockback").put("generic.attackSpeed", "generic.attack_speed").put("generic.armorToughness", "generic.armor_toughness").build();
/*    */   
/*    */   public static String convertIfNeeded(String nms) {
/* 20 */     if (nms == null) {
/* 21 */       return null;
/*    */     }
/* 23 */     nms = (String)legacyNMS.getOrDefault(nms, nms);
/* 24 */     if (!nms.toLowerCase().equals(nms) || nms.indexOf(' ') != -1) {
/* 25 */       return null;
/*    */     }
/* 27 */     return nms;
/*    */   }
/*    */ 
/*    */   
/*    */   public CraftAttributeMap(AttributeMapBase handle) {
/* 32 */     this.handle = handle;
/*    */   }
/*    */ 
/*    */   
/*    */   public AttributeInstance getAttribute(Attribute attribute) {
/* 37 */     Preconditions.checkArgument((attribute != null), "attribute");
/* 38 */     AttributeModifiable nms = this.handle.a(toMinecraft(attribute));
/*    */     
/* 40 */     return (nms == null) ? null : new CraftAttributeInstance(nms, attribute);
/*    */   }
/*    */   
/*    */   public static AttributeBase toMinecraft(Attribute attribute) {
/* 44 */     return (AttributeBase)IRegistry.ATTRIBUTE.get(CraftNamespacedKey.toMinecraft(attribute.getKey()));
/*    */   }
/*    */   
/*    */   public static Attribute fromMinecraft(String nms) {
/* 48 */     return (Attribute)Registry.ATTRIBUTE.get(CraftNamespacedKey.fromString(nms));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\attribute\CraftAttributeMap.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
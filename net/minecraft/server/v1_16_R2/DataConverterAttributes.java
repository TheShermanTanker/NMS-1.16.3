/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.ImmutableMap;
/*    */ import com.mojang.datafixers.DSL;
/*    */ import com.mojang.datafixers.DataFix;
/*    */ import com.mojang.datafixers.DataFixUtils;
/*    */ import com.mojang.datafixers.OpticFinder;
/*    */ import com.mojang.datafixers.TypeRewriteRule;
/*    */ import com.mojang.datafixers.Typed;
/*    */ import com.mojang.datafixers.schemas.Schema;
/*    */ import com.mojang.datafixers.types.Type;
/*    */ import com.mojang.serialization.Dynamic;
/*    */ import java.util.Map;
/*    */ import java.util.stream.Stream;
/*    */ 
/*    */ public class DataConverterAttributes extends DataFix {
/* 17 */   private static final Map<String, String> a = (Map<String, String>)ImmutableMap.builder()
/* 18 */     .put("generic.maxHealth", "generic.max_health")
/* 19 */     .put("Max Health", "generic.max_health")
/*    */     
/* 21 */     .put("zombie.spawnReinforcements", "zombie.spawn_reinforcements")
/* 22 */     .put("Spawn Reinforcements Chance", "zombie.spawn_reinforcements")
/*    */     
/* 24 */     .put("horse.jumpStrength", "horse.jump_strength")
/* 25 */     .put("Jump Strength", "horse.jump_strength")
/*    */     
/* 27 */     .put("generic.followRange", "generic.follow_range")
/* 28 */     .put("Follow Range", "generic.follow_range")
/*    */     
/* 30 */     .put("generic.knockbackResistance", "generic.knockback_resistance")
/* 31 */     .put("Knockback Resistance", "generic.knockback_resistance")
/*    */     
/* 33 */     .put("generic.movementSpeed", "generic.movement_speed")
/* 34 */     .put("Movement Speed", "generic.movement_speed")
/*    */     
/* 36 */     .put("generic.flyingSpeed", "generic.flying_speed")
/* 37 */     .put("Flying Speed", "generic.flying_speed")
/*    */     
/* 39 */     .put("generic.attackDamage", "generic.attack_damage")
/* 40 */     .put("generic.attackKnockback", "generic.attack_knockback")
/* 41 */     .put("generic.attackSpeed", "generic.attack_speed")
/* 42 */     .put("generic.armorToughness", "generic.armor_toughness")
/* 43 */     .build();
/*    */   
/*    */   public DataConverterAttributes(Schema var0) {
/* 46 */     super(var0, false);
/*    */   }
/*    */ 
/*    */   
/*    */   protected TypeRewriteRule makeRule() {
/* 51 */     Type<?> var0 = getInputSchema().getType(DataConverterTypes.ITEM_STACK);
/* 52 */     OpticFinder<?> var1 = var0.findField("tag");
/* 53 */     return TypeRewriteRule.seq(
/* 54 */         fixTypeEverywhereTyped("Rename ItemStack Attributes", var0, var1 -> var1.updateTyped(var0, DataConverterAttributes::a)), new TypeRewriteRule[] {
/*    */ 
/*    */           
/* 57 */           fixTypeEverywhereTyped("Rename Entity Attributes", getInputSchema().getType(DataConverterTypes.ENTITY), DataConverterAttributes::b), 
/* 58 */           fixTypeEverywhereTyped("Rename Player Attributes", getInputSchema().getType(DataConverterTypes.PLAYER), DataConverterAttributes::b)
/*    */         });
/*    */   }
/*    */   
/*    */   private static Dynamic<?> a(Dynamic<?> var0) {
/* 63 */     return (Dynamic)DataFixUtils.orElse(var0.asString().result().map(var0 -> (String)a.getOrDefault(var0, var0)).map(var0::createString), var0);
/*    */   }
/*    */   
/*    */   private static Typed<?> a(Typed<?> var0) {
/* 67 */     return var0.update(DSL.remainderFinder(), var0 -> var0.update("AttributeModifiers", ()));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private static Typed<?> b(Typed<?> var0) {
/* 75 */     return var0.update(DSL.remainderFinder(), var0 -> var0.update("Attributes", ()));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataConverterAttributes.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
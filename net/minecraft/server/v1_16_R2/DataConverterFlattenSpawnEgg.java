/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.Maps;
/*    */ import com.mojang.datafixers.DSL;
/*    */ import com.mojang.datafixers.DataFix;
/*    */ import com.mojang.datafixers.DataFixUtils;
/*    */ import com.mojang.datafixers.OpticFinder;
/*    */ import com.mojang.datafixers.TypeRewriteRule;
/*    */ import com.mojang.datafixers.Typed;
/*    */ import com.mojang.datafixers.schemas.Schema;
/*    */ import com.mojang.datafixers.types.Type;
/*    */ import com.mojang.datafixers.util.Pair;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import java.util.Objects;
/*    */ import java.util.Optional;
/*    */ 
/*    */ public class DataConverterFlattenSpawnEgg extends DataFix {
/*    */   private static final Map<String, String> a;
/*    */   
/*    */   public DataConverterFlattenSpawnEgg(Schema var0, boolean var1) {
/* 22 */     super(var0, var1);
/*    */   }
/*    */   static {
/* 25 */     a = (Map<String, String>)DataFixUtils.make(Maps.newHashMap(), var0 -> {
/*    */           var0.put("minecraft:bat", "minecraft:bat_spawn_egg");
/*    */           var0.put("minecraft:blaze", "minecraft:blaze_spawn_egg");
/*    */           var0.put("minecraft:cave_spider", "minecraft:cave_spider_spawn_egg");
/*    */           var0.put("minecraft:chicken", "minecraft:chicken_spawn_egg");
/*    */           var0.put("minecraft:cow", "minecraft:cow_spawn_egg");
/*    */           var0.put("minecraft:creeper", "minecraft:creeper_spawn_egg");
/*    */           var0.put("minecraft:donkey", "minecraft:donkey_spawn_egg");
/*    */           var0.put("minecraft:elder_guardian", "minecraft:elder_guardian_spawn_egg");
/*    */           var0.put("minecraft:enderman", "minecraft:enderman_spawn_egg");
/*    */           var0.put("minecraft:endermite", "minecraft:endermite_spawn_egg");
/*    */           var0.put("minecraft:evocation_illager", "minecraft:evocation_illager_spawn_egg");
/*    */           var0.put("minecraft:ghast", "minecraft:ghast_spawn_egg");
/*    */           var0.put("minecraft:guardian", "minecraft:guardian_spawn_egg");
/*    */           var0.put("minecraft:horse", "minecraft:horse_spawn_egg");
/*    */           var0.put("minecraft:husk", "minecraft:husk_spawn_egg");
/*    */           var0.put("minecraft:llama", "minecraft:llama_spawn_egg");
/*    */           var0.put("minecraft:magma_cube", "minecraft:magma_cube_spawn_egg");
/*    */           var0.put("minecraft:mooshroom", "minecraft:mooshroom_spawn_egg");
/*    */           var0.put("minecraft:mule", "minecraft:mule_spawn_egg");
/*    */           var0.put("minecraft:ocelot", "minecraft:ocelot_spawn_egg");
/*    */           var0.put("minecraft:pufferfish", "minecraft:pufferfish_spawn_egg");
/*    */           var0.put("minecraft:parrot", "minecraft:parrot_spawn_egg");
/*    */           var0.put("minecraft:pig", "minecraft:pig_spawn_egg");
/*    */           var0.put("minecraft:polar_bear", "minecraft:polar_bear_spawn_egg");
/*    */           var0.put("minecraft:rabbit", "minecraft:rabbit_spawn_egg");
/*    */           var0.put("minecraft:sheep", "minecraft:sheep_spawn_egg");
/*    */           var0.put("minecraft:shulker", "minecraft:shulker_spawn_egg");
/*    */           var0.put("minecraft:silverfish", "minecraft:silverfish_spawn_egg");
/*    */           var0.put("minecraft:skeleton", "minecraft:skeleton_spawn_egg");
/*    */           var0.put("minecraft:skeleton_horse", "minecraft:skeleton_horse_spawn_egg");
/*    */           var0.put("minecraft:slime", "minecraft:slime_spawn_egg");
/*    */           var0.put("minecraft:spider", "minecraft:spider_spawn_egg");
/*    */           var0.put("minecraft:squid", "minecraft:squid_spawn_egg");
/*    */           var0.put("minecraft:stray", "minecraft:stray_spawn_egg");
/*    */           var0.put("minecraft:turtle", "minecraft:turtle_spawn_egg");
/*    */           var0.put("minecraft:vex", "minecraft:vex_spawn_egg");
/*    */           var0.put("minecraft:villager", "minecraft:villager_spawn_egg");
/*    */           var0.put("minecraft:vindication_illager", "minecraft:vindication_illager_spawn_egg");
/*    */           var0.put("minecraft:witch", "minecraft:witch_spawn_egg");
/*    */           var0.put("minecraft:wither_skeleton", "minecraft:wither_skeleton_spawn_egg");
/*    */           var0.put("minecraft:wolf", "minecraft:wolf_spawn_egg");
/*    */           var0.put("minecraft:zombie", "minecraft:zombie_spawn_egg");
/*    */           var0.put("minecraft:zombie_horse", "minecraft:zombie_horse_spawn_egg");
/*    */           var0.put("minecraft:zombie_pigman", "minecraft:zombie_pigman_spawn_egg");
/*    */           var0.put("minecraft:zombie_villager", "minecraft:zombie_villager_spawn_egg");
/*    */         });
/*    */   }
/*    */   
/*    */   public TypeRewriteRule makeRule() {
/* 75 */     Type<?> var0 = getInputSchema().getType(DataConverterTypes.ITEM_STACK);
/*    */     
/* 77 */     OpticFinder<Pair<String, String>> var1 = DSL.fieldFinder("id", DSL.named(DataConverterTypes.ITEM_NAME.typeName(), DataConverterSchemaNamed.a()));
/* 78 */     OpticFinder<String> var2 = DSL.fieldFinder("id", DataConverterSchemaNamed.a());
/* 79 */     OpticFinder<?> var3 = var0.findField("tag");
/* 80 */     OpticFinder<?> var4 = var3.type().findField("EntityTag");
/*    */     
/* 82 */     return fixTypeEverywhereTyped("ItemInstanceSpawnEggFix", var0, var4 -> {
/*    */           Optional<Pair<String, String>> var5 = var4.getOptional(var0);
/*    */           if (var5.isPresent() && Objects.equals(((Pair)var5.get()).getSecond(), "minecraft:spawn_egg")) {
/*    */             Typed<?> var6 = var4.getOrCreateTyped(var1);
/*    */             Typed<?> var7 = var6.getOrCreateTyped(var2);
/*    */             Optional<String> var8 = var7.getOptional(var3);
/*    */             if (var8.isPresent())
/*    */               return var4.set(var0, Pair.of(DataConverterTypes.ITEM_NAME.typeName(), a.getOrDefault(var8.get(), "minecraft:pig_spawn_egg"))); 
/*    */           } 
/*    */           return var4;
/*    */         });
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataConverterFlattenSpawnEgg.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
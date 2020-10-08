/*    */ package net.minecraft.server.v1_16_R2;
/*    */ import com.google.common.collect.Maps;
/*    */ import com.mojang.datafixers.DataFixUtils;
/*    */ import com.mojang.datafixers.TypeRewriteRule;
/*    */ import com.mojang.datafixers.schemas.Schema;
/*    */ import com.mojang.datafixers.types.Type;
/*    */ import com.mojang.datafixers.types.templates.TaggedChoice;
/*    */ import com.mojang.datafixers.util.Pair;
/*    */ import com.mojang.serialization.DynamicOps;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import java.util.function.Function;
/*    */ 
/*    */ public class DataConverterTileEntity extends DataFix {
/*    */   public DataConverterTileEntity(Schema var0, boolean var1) {
/* 16 */     super(var0, var1);
/*    */   } private static final Map<String, String> a;
/*    */   static {
/* 19 */     a = (Map<String, String>)DataFixUtils.make(Maps.newHashMap(), var0 -> {
/*    */           var0.put("Airportal", "minecraft:end_portal");
/*    */           var0.put("Banner", "minecraft:banner");
/*    */           var0.put("Beacon", "minecraft:beacon");
/*    */           var0.put("Cauldron", "minecraft:brewing_stand");
/*    */           var0.put("Chest", "minecraft:chest");
/*    */           var0.put("Comparator", "minecraft:comparator");
/*    */           var0.put("Control", "minecraft:command_block");
/*    */           var0.put("DLDetector", "minecraft:daylight_detector");
/*    */           var0.put("Dropper", "minecraft:dropper");
/*    */           var0.put("EnchantTable", "minecraft:enchanting_table");
/*    */           var0.put("EndGateway", "minecraft:end_gateway");
/*    */           var0.put("EnderChest", "minecraft:ender_chest");
/*    */           var0.put("FlowerPot", "minecraft:flower_pot");
/*    */           var0.put("Furnace", "minecraft:furnace");
/*    */           var0.put("Hopper", "minecraft:hopper");
/*    */           var0.put("MobSpawner", "minecraft:mob_spawner");
/*    */           var0.put("Music", "minecraft:noteblock");
/*    */           var0.put("Piston", "minecraft:piston");
/*    */           var0.put("RecordPlayer", "minecraft:jukebox");
/*    */           var0.put("Sign", "minecraft:sign");
/*    */           var0.put("Skull", "minecraft:skull");
/*    */           var0.put("Structure", "minecraft:structure_block");
/*    */           var0.put("Trap", "minecraft:dispenser");
/*    */         });
/*    */   }
/*    */   
/*    */   public TypeRewriteRule makeRule() {
/* 47 */     Type<?> var0 = getInputSchema().getType(DataConverterTypes.ITEM_STACK);
/* 48 */     Type<?> var1 = getOutputSchema().getType(DataConverterTypes.ITEM_STACK);
/*    */     
/* 50 */     TaggedChoice.TaggedChoiceType<String> var2 = getInputSchema().findChoiceType(DataConverterTypes.BLOCK_ENTITY);
/* 51 */     TaggedChoice.TaggedChoiceType<String> var3 = getOutputSchema().findChoiceType(DataConverterTypes.BLOCK_ENTITY);
/*    */     
/* 53 */     return TypeRewriteRule.seq(
/* 54 */         convertUnchecked("item stack block entity name hook converter", var0, var1), 
/* 55 */         fixTypeEverywhere("BlockEntityIdFix", (Type)var2, (Type)var3, var0 -> ()));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataConverterTileEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
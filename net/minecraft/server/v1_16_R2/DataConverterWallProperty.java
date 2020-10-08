/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.ImmutableSet;
/*    */ import com.mojang.datafixers.DSL;
/*    */ import com.mojang.datafixers.DataFix;
/*    */ import com.mojang.datafixers.DataFixUtils;
/*    */ import com.mojang.datafixers.TypeRewriteRule;
/*    */ import com.mojang.datafixers.Typed;
/*    */ import com.mojang.datafixers.schemas.Schema;
/*    */ import com.mojang.serialization.Dynamic;
/*    */ import java.util.Set;
/*    */ 
/*    */ public class DataConverterWallProperty extends DataFix {
/* 14 */   private static final Set<String> a = (Set<String>)ImmutableSet.of("minecraft:andesite_wall", "minecraft:brick_wall", "minecraft:cobblestone_wall", "minecraft:diorite_wall", "minecraft:end_stone_brick_wall", "minecraft:granite_wall", (Object[])new String[] { "minecraft:mossy_cobblestone_wall", "minecraft:mossy_stone_brick_wall", "minecraft:nether_brick_wall", "minecraft:prismarine_wall", "minecraft:red_nether_brick_wall", "minecraft:red_sandstone_wall", "minecraft:sandstone_wall", "minecraft:stone_brick_wall" });
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public DataConverterWallProperty(Schema var0, boolean var1) {
/* 33 */     super(var0, var1);
/*    */   }
/*    */ 
/*    */   
/*    */   public TypeRewriteRule makeRule() {
/* 38 */     return fixTypeEverywhereTyped("WallPropertyFix", getInputSchema().getType(DataConverterTypes.BLOCK_STATE), var0 -> var0.update(DSL.remainderFinder(), DataConverterWallProperty::a));
/*    */   }
/*    */   
/*    */   private static String a(String var0) {
/* 42 */     return "true".equals(var0) ? "low" : "none";
/*    */   }
/*    */   
/*    */   private static <T> Dynamic<T> a(Dynamic<T> var0, String var1) {
/* 46 */     return var0.update(var1, var0 -> (Dynamic)DataFixUtils.orElse(var0.asString().result().map(DataConverterWallProperty::a).map(var0::createString), var0));
/*    */   }
/*    */   
/*    */   private static <T> Dynamic<T> a(Dynamic<T> var0) {
/* 50 */     boolean var1 = var0.get("Name").asString().result().filter(a::contains).isPresent();
/* 51 */     if (!var1) {
/* 52 */       return var0;
/*    */     }
/*    */     
/* 55 */     return var0.update("Properties", var0 -> {
/*    */           Dynamic<?> var1 = a(var0, "east");
/*    */           var1 = a(var1, "west");
/*    */           var1 = a(var1, "north");
/*    */           return a(var1, "south");
/*    */         });
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataConverterWallProperty.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
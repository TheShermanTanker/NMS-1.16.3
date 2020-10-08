/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.ImmutableMap;
/*    */ import com.mojang.datafixers.DSL;
/*    */ import com.mojang.datafixers.DataFix;
/*    */ import com.mojang.datafixers.TypeRewriteRule;
/*    */ import com.mojang.datafixers.Typed;
/*    */ import com.mojang.datafixers.schemas.Schema;
/*    */ import com.mojang.serialization.Dynamic;
/*    */ import java.util.Map;
/*    */ import java.util.Optional;
/*    */ 
/*    */ public class DataConverterJigsawRotation extends DataFix {
/* 14 */   private static final Map<String, String> a = (Map<String, String>)ImmutableMap.builder()
/* 15 */     .put("down", "down_south")
/* 16 */     .put("up", "up_north")
/* 17 */     .put("north", "north_up")
/* 18 */     .put("south", "south_up")
/* 19 */     .put("west", "west_up")
/* 20 */     .put("east", "east_up")
/* 21 */     .build();
/*    */   
/*    */   public DataConverterJigsawRotation(Schema var0, boolean var1) {
/* 24 */     super(var0, var1);
/*    */   }
/*    */   
/*    */   private static Dynamic<?> a(Dynamic<?> var0) {
/* 28 */     Optional<String> var1 = var0.get("Name").asString().result();
/* 29 */     if (var1.equals(Optional.of("minecraft:jigsaw"))) {
/* 30 */       return var0.update("Properties", var0 -> {
/*    */             String var1 = var0.get("facing").asString("north");
/*    */             
/*    */             return var0.remove("facing").set("orientation", var0.createString(a.getOrDefault(var1, var1)));
/*    */           });
/*    */     }
/*    */     
/* 37 */     return var0;
/*    */   }
/*    */ 
/*    */   
/*    */   protected TypeRewriteRule makeRule() {
/* 42 */     return fixTypeEverywhereTyped("jigsaw_rotation_fix", getInputSchema().getType(DataConverterTypes.BLOCK_STATE), var0 -> var0.update(DSL.remainderFinder(), DataConverterJigsawRotation::a));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataConverterJigsawRotation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
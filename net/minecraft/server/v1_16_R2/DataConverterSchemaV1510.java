/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.datafixers.schemas.Schema;
/*    */ import com.mojang.datafixers.types.templates.TypeTemplate;
/*    */ import java.util.Map;
/*    */ import java.util.function.Supplier;
/*    */ 
/*    */ public class DataConverterSchemaV1510
/*    */   extends DataConverterSchemaNamed {
/*    */   public DataConverterSchemaV1510(int var0, Schema var1) {
/* 11 */     super(var0, var1);
/*    */   }
/*    */ 
/*    */   
/*    */   public Map<String, Supplier<TypeTemplate>> registerEntities(Schema var0) {
/* 16 */     Map<String, Supplier<TypeTemplate>> var1 = super.registerEntities(var0);
/*    */     
/* 18 */     var1.put("minecraft:command_block_minecart", var1.remove("minecraft:commandblock_minecart"));
/* 19 */     var1.put("minecraft:end_crystal", var1.remove("minecraft:ender_crystal"));
/* 20 */     var1.put("minecraft:snow_golem", var1.remove("minecraft:snowman"));
/* 21 */     var1.put("minecraft:evoker", var1.remove("minecraft:evocation_illager"));
/* 22 */     var1.put("minecraft:evoker_fangs", var1.remove("minecraft:evocation_fangs"));
/* 23 */     var1.put("minecraft:illusioner", var1.remove("minecraft:illusion_illager"));
/* 24 */     var1.put("minecraft:vindicator", var1.remove("minecraft:vindication_illager"));
/* 25 */     var1.put("minecraft:iron_golem", var1.remove("minecraft:villager_golem"));
/* 26 */     var1.put("minecraft:experience_orb", var1.remove("minecraft:xp_orb"));
/* 27 */     var1.put("minecraft:experience_bottle", var1.remove("minecraft:xp_bottle"));
/* 28 */     var1.put("minecraft:eye_of_ender", var1.remove("minecraft:eye_of_ender_signal"));
/* 29 */     var1.put("minecraft:firework_rocket", var1.remove("minecraft:fireworks_rocket"));
/*    */     
/* 31 */     return var1;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataConverterSchemaV1510.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.ImmutableMap;
/*    */ import com.mojang.datafixers.schemas.Schema;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class DataConverterRecipeRename
/*    */   extends DataConverterRecipeBase {
/*  9 */   private static final Map<String, String> a = (Map<String, String>)ImmutableMap.builder()
/* 10 */     .put("minecraft:acacia_bark", "minecraft:acacia_wood")
/* 11 */     .put("minecraft:birch_bark", "minecraft:birch_wood")
/* 12 */     .put("minecraft:dark_oak_bark", "minecraft:dark_oak_wood")
/* 13 */     .put("minecraft:jungle_bark", "minecraft:jungle_wood")
/* 14 */     .put("minecraft:oak_bark", "minecraft:oak_wood")
/* 15 */     .put("minecraft:spruce_bark", "minecraft:spruce_wood")
/* 16 */     .build();
/*    */   
/*    */   public DataConverterRecipeRename(Schema var0, boolean var1) {
/* 19 */     super(var0, var1, "Recipes renamening fix", var0 -> (String)a.getOrDefault(var0, var0));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataConverterRecipeRename.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
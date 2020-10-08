/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.ImmutableMap;
/*    */ import com.mojang.datafixers.schemas.Schema;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class DataConverterEntityCodSalmon
/*    */   extends DataConverterEntityRenameAbstract {
/*  9 */   public static final Map<String, String> a = (Map<String, String>)ImmutableMap.builder()
/* 10 */     .put("minecraft:salmon_mob", "minecraft:salmon")
/* 11 */     .put("minecraft:cod_mob", "minecraft:cod")
/* 12 */     .build();
/*    */   
/* 14 */   public static final Map<String, String> b = (Map<String, String>)ImmutableMap.builder()
/* 15 */     .put("minecraft:salmon_mob_spawn_egg", "minecraft:salmon_spawn_egg")
/* 16 */     .put("minecraft:cod_mob_spawn_egg", "minecraft:cod_spawn_egg")
/* 17 */     .build();
/*    */   
/*    */   public DataConverterEntityCodSalmon(Schema var0, boolean var1) {
/* 20 */     super("EntityCodSalmonFix", var0, var1);
/*    */   }
/*    */ 
/*    */   
/*    */   protected String a(String var0) {
/* 25 */     return a.getOrDefault(var0, var0);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataConverterEntityCodSalmon.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
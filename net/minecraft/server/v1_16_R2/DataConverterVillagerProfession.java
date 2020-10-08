/*    */ package net.minecraft.server.v1_16_R2;
/*    */ import com.google.common.collect.ImmutableMap;
/*    */ import com.mojang.datafixers.DSL;
/*    */ import com.mojang.datafixers.DataFixUtils;
/*    */ import com.mojang.datafixers.Typed;
/*    */ import com.mojang.datafixers.schemas.Schema;
/*    */ import com.mojang.serialization.Dynamic;
/*    */ import java.util.Map;
/*    */ 
/*    */ public class DataConverterVillagerProfession extends DataConverterNamedEntity {
/*    */   public DataConverterVillagerProfession(Schema var0, String var1) {
/* 12 */     super(var0, false, "Villager profession data fix (" + var1 + ")", DataConverterTypes.ENTITY, var1);
/*    */   }
/*    */ 
/*    */   
/*    */   protected Typed<?> a(Typed<?> var0) {
/* 17 */     Dynamic<?> var1 = (Dynamic)var0.get(DSL.remainderFinder());
/*    */     
/* 19 */     return var0.set(DSL.remainderFinder(), var1
/* 20 */         .remove("Profession")
/* 21 */         .remove("Career")
/* 22 */         .remove("CareerLevel")
/* 23 */         .set("VillagerData", var1
/* 24 */           .createMap((Map)ImmutableMap.of(var1
/* 25 */               .createString("type"), var1.createString("minecraft:plains"), var1
/* 26 */               .createString("profession"), var1.createString(a(var1.get("Profession").asInt(0), var1.get("Career").asInt(0))), var1
/* 27 */               .createString("level"), DataFixUtils.orElse(var1.get("CareerLevel").result(), var1.createInt(1))))));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private static String a(int var0, int var1) {
/* 34 */     if (var0 == 0) {
/* 35 */       if (var1 == 2)
/* 36 */         return "minecraft:fisherman"; 
/* 37 */       if (var1 == 3)
/* 38 */         return "minecraft:shepherd"; 
/* 39 */       if (var1 == 4) {
/* 40 */         return "minecraft:fletcher";
/*    */       }
/* 42 */       return "minecraft:farmer";
/*    */     } 
/* 44 */     if (var0 == 1) {
/* 45 */       if (var1 == 2) {
/* 46 */         return "minecraft:cartographer";
/*    */       }
/* 48 */       return "minecraft:librarian";
/*    */     } 
/* 50 */     if (var0 == 2)
/* 51 */       return "minecraft:cleric"; 
/* 52 */     if (var0 == 3) {
/* 53 */       if (var1 == 2)
/* 54 */         return "minecraft:weaponsmith"; 
/* 55 */       if (var1 == 3) {
/* 56 */         return "minecraft:toolsmith";
/*    */       }
/* 58 */       return "minecraft:armorer";
/*    */     } 
/* 60 */     if (var0 == 4) {
/* 61 */       if (var1 == 2) {
/* 62 */         return "minecraft:leatherworker";
/*    */       }
/* 64 */       return "minecraft:butcher";
/*    */     } 
/* 66 */     if (var0 == 5) {
/* 67 */       return "minecraft:nitwit";
/*    */     }
/* 69 */     return "minecraft:none";
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataConverterVillagerProfession.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
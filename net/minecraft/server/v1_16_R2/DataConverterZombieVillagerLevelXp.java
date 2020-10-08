/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.datafixers.DSL;
/*    */ import com.mojang.datafixers.Typed;
/*    */ import com.mojang.datafixers.schemas.Schema;
/*    */ import com.mojang.serialization.Dynamic;
/*    */ import java.util.Optional;
/*    */ 
/*    */ public class DataConverterZombieVillagerLevelXp extends DataConverterNamedEntity {
/*    */   public DataConverterZombieVillagerLevelXp(Schema var0, boolean var1) {
/* 11 */     super(var0, var1, "Zombie Villager XP rebuild", DataConverterTypes.ENTITY, "minecraft:zombie_villager");
/*    */   }
/*    */ 
/*    */   
/*    */   protected Typed<?> a(Typed<?> var0) {
/* 16 */     return var0.update(DSL.remainderFinder(), var0 -> {
/*    */           Optional<Number> var1 = var0.get("Xp").asNumber().result();
/*    */           if (!var1.isPresent()) {
/*    */             int var2 = var0.get("VillagerData").get("level").asInt(1);
/*    */             return var0.set("Xp", var0.createInt(DataConverterVillagerLevelXp.a(var2)));
/*    */           } 
/*    */           return var0;
/*    */         });
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataConverterZombieVillagerLevelXp.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
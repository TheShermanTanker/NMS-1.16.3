/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.Sets;
/*    */ import com.mojang.datafixers.DSL;
/*    */ import com.mojang.datafixers.DataFix;
/*    */ import com.mojang.datafixers.TypeRewriteRule;
/*    */ import com.mojang.datafixers.Typed;
/*    */ import com.mojang.datafixers.schemas.Schema;
/*    */ import com.mojang.serialization.Dynamic;
/*    */ import java.util.Optional;
/*    */ import java.util.Set;
/*    */ 
/*    */ public class DataConverterHealth extends DataFix {
/*    */   public DataConverterHealth(Schema var0, boolean var1) {
/* 15 */     super(var0, var1);
/*    */   }
/*    */   
/* 18 */   private static final Set<String> a = Sets.newHashSet((Object[])new String[] { "ArmorStand", "Bat", "Blaze", "CaveSpider", "Chicken", "Cow", "Creeper", "EnderDragon", "Enderman", "Endermite", "EntityHorse", "Ghast", "Giant", "Guardian", "LavaSlime", "MushroomCow", "Ozelot", "Pig", "PigZombie", "Rabbit", "Sheep", "Shulker", "Silverfish", "Skeleton", "Slime", "SnowMan", "Spider", "Squid", "Villager", "VillagerGolem", "Witch", "WitherBoss", "Wolf", "Zombie" });
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
/*    */ 
/*    */ 
/*    */   
/*    */   public Dynamic<?> a(Dynamic<?> var0) {
/*    */     float var1;
/* 58 */     Optional<Number> var2 = var0.get("HealF").asNumber().result();
/* 59 */     Optional<Number> var3 = var0.get("Health").asNumber().result();
/* 60 */     if (var2.isPresent()) {
/* 61 */       var1 = ((Number)var2.get()).floatValue();
/* 62 */       var0 = var0.remove("HealF");
/* 63 */     } else if (var3.isPresent()) {
/* 64 */       var1 = ((Number)var3.get()).floatValue();
/*    */     } else {
/* 66 */       return var0;
/*    */     } 
/* 68 */     return var0.set("Health", var0.createFloat(var1));
/*    */   }
/*    */ 
/*    */   
/*    */   public TypeRewriteRule makeRule() {
/* 73 */     return fixTypeEverywhereTyped("EntityHealthFix", getInputSchema().getType(DataConverterTypes.ENTITY), var0 -> var0.update(DSL.remainderFinder(), this::a));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataConverterHealth.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
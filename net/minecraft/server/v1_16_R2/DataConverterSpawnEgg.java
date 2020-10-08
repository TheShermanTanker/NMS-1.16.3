/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.mojang.datafixers.DSL;
/*     */ import com.mojang.datafixers.DataFix;
/*     */ import com.mojang.datafixers.DataFixUtils;
/*     */ import com.mojang.datafixers.OpticFinder;
/*     */ import com.mojang.datafixers.TypeRewriteRule;
/*     */ import com.mojang.datafixers.Typed;
/*     */ import com.mojang.datafixers.schemas.Schema;
/*     */ import com.mojang.datafixers.types.Type;
/*     */ import com.mojang.datafixers.util.Pair;
/*     */ import com.mojang.serialization.DataResult;
/*     */ import com.mojang.serialization.Dynamic;
/*     */ import java.util.Objects;
/*     */ import java.util.Optional;
/*     */ 
/*     */ public class DataConverterSpawnEgg extends DataFix {
/*     */   public static final String[] ID_MAPPING;
/*     */   
/*     */   public DataConverterSpawnEgg(Schema var0, boolean var1) {
/*  21 */     super(var0, var1);
/*     */   }
/*     */   static {
/*  24 */     ID_MAPPING = (String[])DataFixUtils.make(new String[256], var0 -> {
/*     */           var0[1] = "Item";
/*     */           var0[2] = "XPOrb";
/*     */           var0[7] = "ThrownEgg";
/*     */           var0[8] = "LeashKnot";
/*     */           var0[9] = "Painting";
/*     */           var0[10] = "Arrow";
/*     */           var0[11] = "Snowball";
/*     */           var0[12] = "Fireball";
/*     */           var0[13] = "SmallFireball";
/*     */           var0[14] = "ThrownEnderpearl";
/*     */           var0[15] = "EyeOfEnderSignal";
/*     */           var0[16] = "ThrownPotion";
/*     */           var0[17] = "ThrownExpBottle";
/*     */           var0[18] = "ItemFrame";
/*     */           var0[19] = "WitherSkull";
/*     */           var0[20] = "PrimedTnt";
/*     */           var0[21] = "FallingSand";
/*     */           var0[22] = "FireworksRocketEntity";
/*     */           var0[23] = "TippedArrow";
/*     */           var0[24] = "SpectralArrow";
/*     */           var0[25] = "ShulkerBullet";
/*     */           var0[26] = "DragonFireball";
/*     */           var0[30] = "ArmorStand";
/*     */           var0[41] = "Boat";
/*     */           var0[42] = "MinecartRideable";
/*     */           var0[43] = "MinecartChest";
/*     */           var0[44] = "MinecartFurnace";
/*     */           var0[45] = "MinecartTNT";
/*     */           var0[46] = "MinecartHopper";
/*     */           var0[47] = "MinecartSpawner";
/*     */           var0[40] = "MinecartCommandBlock";
/*     */           var0[48] = "Mob";
/*     */           var0[49] = "Monster";
/*     */           var0[50] = "Creeper";
/*     */           var0[51] = "Skeleton";
/*     */           var0[52] = "Spider";
/*     */           var0[53] = "Giant";
/*     */           var0[54] = "Zombie";
/*     */           var0[55] = "Slime";
/*     */           var0[56] = "Ghast";
/*     */           var0[57] = "PigZombie";
/*     */           var0[58] = "Enderman";
/*     */           var0[59] = "CaveSpider";
/*     */           var0[60] = "Silverfish";
/*     */           var0[61] = "Blaze";
/*     */           var0[62] = "LavaSlime";
/*     */           var0[63] = "EnderDragon";
/*     */           var0[64] = "WitherBoss";
/*     */           var0[65] = "Bat";
/*     */           var0[66] = "Witch";
/*     */           var0[67] = "Endermite";
/*     */           var0[68] = "Guardian";
/*     */           var0[69] = "Shulker";
/*     */           var0[90] = "Pig";
/*     */           var0[91] = "Sheep";
/*     */           var0[92] = "Cow";
/*     */           var0[93] = "Chicken";
/*     */           var0[94] = "Squid";
/*     */           var0[95] = "Wolf";
/*     */           var0[96] = "MushroomCow";
/*     */           var0[97] = "SnowMan";
/*     */           var0[98] = "Ozelot";
/*     */           var0[99] = "VillagerGolem";
/*     */           var0[100] = "EntityHorse";
/*     */           var0[101] = "Rabbit";
/*     */           var0[120] = "Villager";
/*     */           var0[200] = "EnderCrystal";
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TypeRewriteRule makeRule() {
/* 107 */     Schema var0 = getInputSchema();
/* 108 */     Type<?> var1 = var0.getType(DataConverterTypes.ITEM_STACK);
/*     */     
/* 110 */     OpticFinder<Pair<String, String>> var2 = DSL.fieldFinder("id", DSL.named(DataConverterTypes.ITEM_NAME.typeName(), DataConverterSchemaNamed.a()));
/* 111 */     OpticFinder<String> var3 = DSL.fieldFinder("id", DSL.string());
/* 112 */     OpticFinder<?> var4 = var1.findField("tag");
/* 113 */     OpticFinder<?> var5 = var4.type().findField("EntityTag");
/* 114 */     OpticFinder<?> var6 = DSL.typeFinder(var0.getTypeRaw(DataConverterTypes.ENTITY));
/*     */     
/* 116 */     Type<?> var7 = getOutputSchema().getTypeRaw(DataConverterTypes.ENTITY);
/*     */     
/* 118 */     return fixTypeEverywhereTyped("ItemSpawnEggFix", var1, var6 -> {
/*     */           Optional<Pair<String, String>> var7 = var6.getOptional(var0);
/*     */           if (var7.isPresent() && Objects.equals(((Pair)var7.get()).getSecond(), "minecraft:spawn_egg")) {
/*     */             Dynamic<?> var8 = (Dynamic)var6.get(DSL.remainderFinder());
/*     */             short var9 = var8.get("Damage").asShort((short)0);
/*     */             Optional<? extends Typed<?>> var10 = var6.getOptionalTyped(var1);
/*     */             Optional<? extends Typed<?>> var11 = var10.flatMap(());
/*     */             Optional<? extends Typed<?>> var12 = var11.flatMap(());
/*     */             Optional<String> var13 = var12.flatMap(());
/*     */             Typed<?> var14 = var6;
/*     */             String var15 = ID_MAPPING[var9 & 0xFF];
/*     */             if (var15 != null && (!var13.isPresent() || !Objects.equals(var13.get(), var15))) {
/*     */               Typed<?> var16 = var6.getOrCreateTyped(var1);
/*     */               Typed<?> var17 = var16.getOrCreateTyped(var2);
/*     */               Typed<?> var18 = var17.getOrCreateTyped(var3);
/*     */               Dynamic<?> var19 = var8;
/*     */               Typed<?> var20 = (Typed)((Pair)var18.write().flatMap(()).result().orElseThrow(())).getFirst();
/*     */               var14 = var14.set(var1, var16.set(var2, var17.set(var3, var20)));
/*     */             } 
/*     */             if (var9 != 0) {
/*     */               var8 = var8.set("Damage", var8.createShort((short)0));
/*     */               var14 = var14.set(DSL.remainderFinder(), var8);
/*     */             } 
/*     */             return var14;
/*     */           } 
/*     */           return var6;
/*     */         });
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataConverterSpawnEgg.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
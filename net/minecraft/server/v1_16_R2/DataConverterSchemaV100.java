/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.mojang.datafixers.DSL;
/*     */ import com.mojang.datafixers.schemas.Schema;
/*     */ import com.mojang.datafixers.types.templates.TypeTemplate;
/*     */ import java.util.Map;
/*     */ import java.util.function.Supplier;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DataConverterSchemaV100
/*     */   extends Schema
/*     */ {
/*     */   public DataConverterSchemaV100(int var0, Schema var1) {
/*  21 */     super(var0, var1);
/*     */   }
/*     */   
/*     */   protected static TypeTemplate a(Schema var0) {
/*  25 */     return DSL.optionalFields("ArmorItems", 
/*  26 */         DSL.list(DataConverterTypes.ITEM_STACK.in(var0)), "HandItems", 
/*  27 */         DSL.list(DataConverterTypes.ITEM_STACK.in(var0)));
/*     */   }
/*     */ 
/*     */   
/*     */   protected static void a(Schema var0, Map<String, Supplier<TypeTemplate>> var1, String var2) {
/*  32 */     var0.register(var1, var2, () -> a(var0));
/*     */   }
/*     */ 
/*     */   
/*     */   public Map<String, Supplier<TypeTemplate>> registerEntities(Schema var0) {
/*  37 */     Map<String, Supplier<TypeTemplate>> var1 = super.registerEntities(var0);
/*     */ 
/*     */     
/*  40 */     a(var0, var1, "ArmorStand");
/*  41 */     a(var0, var1, "Creeper");
/*  42 */     a(var0, var1, "Skeleton");
/*  43 */     a(var0, var1, "Spider");
/*  44 */     a(var0, var1, "Giant");
/*  45 */     a(var0, var1, "Zombie");
/*  46 */     a(var0, var1, "Slime");
/*  47 */     a(var0, var1, "Ghast");
/*  48 */     a(var0, var1, "PigZombie");
/*  49 */     var0.register(var1, "Enderman", var1 -> DSL.optionalFields("carried", DataConverterTypes.BLOCK_NAME.in(var0), a(var0)));
/*     */ 
/*     */ 
/*     */     
/*  53 */     a(var0, var1, "CaveSpider");
/*  54 */     a(var0, var1, "Silverfish");
/*  55 */     a(var0, var1, "Blaze");
/*  56 */     a(var0, var1, "LavaSlime");
/*  57 */     a(var0, var1, "EnderDragon");
/*  58 */     a(var0, var1, "WitherBoss");
/*  59 */     a(var0, var1, "Bat");
/*  60 */     a(var0, var1, "Witch");
/*  61 */     a(var0, var1, "Endermite");
/*  62 */     a(var0, var1, "Guardian");
/*  63 */     a(var0, var1, "Pig");
/*  64 */     a(var0, var1, "Sheep");
/*  65 */     a(var0, var1, "Cow");
/*  66 */     a(var0, var1, "Chicken");
/*  67 */     a(var0, var1, "Squid");
/*  68 */     a(var0, var1, "Wolf");
/*  69 */     a(var0, var1, "MushroomCow");
/*  70 */     a(var0, var1, "SnowMan");
/*  71 */     a(var0, var1, "Ozelot");
/*  72 */     a(var0, var1, "VillagerGolem");
/*  73 */     var0.register(var1, "EntityHorse", var1 -> DSL.optionalFields("Items", DSL.list(DataConverterTypes.ITEM_STACK.in(var0)), "ArmorItem", DataConverterTypes.ITEM_STACK.in(var0), "SaddleItem", DataConverterTypes.ITEM_STACK.in(var0), a(var0)));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  79 */     a(var0, var1, "Rabbit");
/*  80 */     var0.register(var1, "Villager", var1 -> DSL.optionalFields("Inventory", DSL.list(DataConverterTypes.ITEM_STACK.in(var0)), "Offers", DSL.optionalFields("Recipes", DSL.list(DSL.optionalFields("buy", DataConverterTypes.ITEM_STACK.in(var0), "buyB", DataConverterTypes.ITEM_STACK.in(var0), "sell", DataConverterTypes.ITEM_STACK.in(var0)))), a(var0)));
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
/*  93 */     a(var0, var1, "Shulker");
/*     */ 
/*     */     
/*  96 */     var0.registerSimple(var1, "AreaEffectCloud");
/*  97 */     var0.registerSimple(var1, "ShulkerBullet");
/*     */     
/*  99 */     return var1;
/*     */   }
/*     */ 
/*     */   
/*     */   public void registerTypes(Schema var0, Map<String, Supplier<TypeTemplate>> var1, Map<String, Supplier<TypeTemplate>> var2) {
/* 104 */     super.registerTypes(var0, var1, var2);
/*     */     
/* 106 */     var0.registerType(false, DataConverterTypes.STRUCTURE, () -> DSL.optionalFields("entities", DSL.list(DSL.optionalFields("nbt", DataConverterTypes.ENTITY_TREE.in(var0))), "blocks", DSL.list(DSL.optionalFields("nbt", DataConverterTypes.BLOCK_ENTITY.in(var0))), "palette", DSL.list(DataConverterTypes.BLOCK_STATE.in(var0))));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 111 */     var0.registerType(false, DataConverterTypes.BLOCK_STATE, DSL::remainder);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataConverterSchemaV100.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
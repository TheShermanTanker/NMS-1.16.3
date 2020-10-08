/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.Sets;
/*     */ import com.mojang.datafixers.DSL;
/*     */ import com.mojang.datafixers.DataFixUtils;
/*     */ import com.mojang.datafixers.TypeRewriteRule;
/*     */ import com.mojang.datafixers.Typed;
/*     */ import com.mojang.datafixers.schemas.Schema;
/*     */ import com.mojang.serialization.Dynamic;
/*     */ import java.util.Optional;
/*     */ import java.util.Set;
/*     */ 
/*     */ public class DataConverterEntityUUID extends DataConverterUUIDBase {
/*  14 */   private static final Set<String> c = Sets.newHashSet();
/*  15 */   private static final Set<String> d = Sets.newHashSet();
/*  16 */   private static final Set<String> e = Sets.newHashSet();
/*  17 */   private static final Set<String> f = Sets.newHashSet();
/*  18 */   private static final Set<String> g = Sets.newHashSet();
/*  19 */   private static final Set<String> h = Sets.newHashSet();
/*     */   
/*     */   static {
/*  22 */     c.add("minecraft:donkey");
/*  23 */     c.add("minecraft:horse");
/*  24 */     c.add("minecraft:llama");
/*  25 */     c.add("minecraft:mule");
/*  26 */     c.add("minecraft:skeleton_horse");
/*  27 */     c.add("minecraft:trader_llama");
/*  28 */     c.add("minecraft:zombie_horse");
/*  29 */     d.add("minecraft:cat");
/*  30 */     d.add("minecraft:parrot");
/*  31 */     d.add("minecraft:wolf");
/*  32 */     e.add("minecraft:bee");
/*  33 */     e.add("minecraft:chicken");
/*  34 */     e.add("minecraft:cow");
/*  35 */     e.add("minecraft:fox");
/*  36 */     e.add("minecraft:mooshroom");
/*  37 */     e.add("minecraft:ocelot");
/*  38 */     e.add("minecraft:panda");
/*  39 */     e.add("minecraft:pig");
/*  40 */     e.add("minecraft:polar_bear");
/*  41 */     e.add("minecraft:rabbit");
/*  42 */     e.add("minecraft:sheep");
/*  43 */     e.add("minecraft:turtle");
/*  44 */     e.add("minecraft:hoglin");
/*  45 */     f.add("minecraft:bat");
/*  46 */     f.add("minecraft:blaze");
/*  47 */     f.add("minecraft:cave_spider");
/*  48 */     f.add("minecraft:cod");
/*  49 */     f.add("minecraft:creeper");
/*  50 */     f.add("minecraft:dolphin");
/*  51 */     f.add("minecraft:drowned");
/*  52 */     f.add("minecraft:elder_guardian");
/*  53 */     f.add("minecraft:ender_dragon");
/*  54 */     f.add("minecraft:enderman");
/*  55 */     f.add("minecraft:endermite");
/*  56 */     f.add("minecraft:evoker");
/*  57 */     f.add("minecraft:ghast");
/*  58 */     f.add("minecraft:giant");
/*  59 */     f.add("minecraft:guardian");
/*  60 */     f.add("minecraft:husk");
/*  61 */     f.add("minecraft:illusioner");
/*  62 */     f.add("minecraft:magma_cube");
/*  63 */     f.add("minecraft:pufferfish");
/*  64 */     f.add("minecraft:zombified_piglin");
/*  65 */     f.add("minecraft:salmon");
/*  66 */     f.add("minecraft:shulker");
/*  67 */     f.add("minecraft:silverfish");
/*  68 */     f.add("minecraft:skeleton");
/*  69 */     f.add("minecraft:slime");
/*  70 */     f.add("minecraft:snow_golem");
/*  71 */     f.add("minecraft:spider");
/*  72 */     f.add("minecraft:squid");
/*  73 */     f.add("minecraft:stray");
/*  74 */     f.add("minecraft:tropical_fish");
/*  75 */     f.add("minecraft:vex");
/*  76 */     f.add("minecraft:villager");
/*  77 */     f.add("minecraft:iron_golem");
/*  78 */     f.add("minecraft:vindicator");
/*  79 */     f.add("minecraft:pillager");
/*  80 */     f.add("minecraft:wandering_trader");
/*  81 */     f.add("minecraft:witch");
/*  82 */     f.add("minecraft:wither");
/*  83 */     f.add("minecraft:wither_skeleton");
/*  84 */     f.add("minecraft:zombie");
/*  85 */     f.add("minecraft:zombie_villager");
/*  86 */     f.add("minecraft:phantom");
/*  87 */     f.add("minecraft:ravager");
/*  88 */     f.add("minecraft:piglin");
/*  89 */     g.add("minecraft:armor_stand");
/*  90 */     h.add("minecraft:arrow");
/*  91 */     h.add("minecraft:dragon_fireball");
/*  92 */     h.add("minecraft:firework_rocket");
/*  93 */     h.add("minecraft:fireball");
/*  94 */     h.add("minecraft:llama_spit");
/*  95 */     h.add("minecraft:small_fireball");
/*  96 */     h.add("minecraft:snowball");
/*  97 */     h.add("minecraft:spectral_arrow");
/*  98 */     h.add("minecraft:egg");
/*  99 */     h.add("minecraft:ender_pearl");
/* 100 */     h.add("minecraft:experience_bottle");
/* 101 */     h.add("minecraft:potion");
/* 102 */     h.add("minecraft:trident");
/* 103 */     h.add("minecraft:wither_skull");
/*     */   }
/*     */ 
/*     */   
/*     */   public DataConverterEntityUUID(Schema var0) {
/* 108 */     super(var0, DataConverterTypes.ENTITY);
/*     */   }
/*     */ 
/*     */   
/*     */   protected TypeRewriteRule makeRule() {
/* 113 */     return fixTypeEverywhereTyped("EntityUUIDFixes", getInputSchema().getType(this.b), var0 -> {
/*     */           var0 = var0.update(DSL.remainderFinder(), DataConverterEntityUUID::c);
/*     */           for (String var2 : c) {
/*     */             var0 = a(var0, var2, DataConverterEntityUUID::l);
/*     */           }
/*     */           for (String var2 : d) {
/*     */             var0 = a(var0, var2, DataConverterEntityUUID::l);
/*     */           }
/*     */           for (String var2 : e) {
/*     */             var0 = a(var0, var2, DataConverterEntityUUID::m);
/*     */           }
/*     */           for (String var2 : f) {
/*     */             var0 = a(var0, var2, DataConverterEntityUUID::n);
/*     */           }
/*     */           for (String var2 : g) {
/*     */             var0 = a(var0, var2, DataConverterEntityUUID::b);
/*     */           }
/*     */           for (String var2 : h) {
/*     */             var0 = a(var0, var2, DataConverterEntityUUID::o);
/*     */           }
/*     */           var0 = a(var0, "minecraft:bee", DataConverterEntityUUID::k);
/*     */           var0 = a(var0, "minecraft:zombified_piglin", DataConverterEntityUUID::k);
/*     */           var0 = a(var0, "minecraft:fox", DataConverterEntityUUID::j);
/*     */           var0 = a(var0, "minecraft:item", DataConverterEntityUUID::i);
/*     */           var0 = a(var0, "minecraft:shulker_bullet", DataConverterEntityUUID::h);
/*     */           var0 = a(var0, "minecraft:area_effect_cloud", DataConverterEntityUUID::g);
/*     */           var0 = a(var0, "minecraft:zombie_villager", DataConverterEntityUUID::f);
/*     */           var0 = a(var0, "minecraft:evoker_fangs", DataConverterEntityUUID::e);
/*     */           return a(var0, "minecraft:piglin", DataConverterEntityUUID::d);
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   private static Dynamic<?> d(Dynamic<?> var0) {
/* 147 */     return var0.update("Brain", var0 -> var0.update("memories", ()));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static Dynamic<?> e(Dynamic<?> var0) {
/* 158 */     return c(var0, "OwnerUUID", "Owner").orElse(var0);
/*     */   }
/*     */   
/*     */   private static Dynamic<?> f(Dynamic<?> var0) {
/* 162 */     return c(var0, "ConversionPlayer", "ConversionPlayer").orElse(var0);
/*     */   }
/*     */   
/*     */   private static Dynamic<?> g(Dynamic<?> var0) {
/* 166 */     return c(var0, "OwnerUUID", "Owner").orElse(var0);
/*     */   }
/*     */   
/*     */   private static Dynamic<?> h(Dynamic<?> var0) {
/* 170 */     var0 = b(var0, "Owner", "Owner").orElse(var0);
/* 171 */     return b(var0, "Target", "Target").orElse(var0);
/*     */   }
/*     */   
/*     */   private static Dynamic<?> i(Dynamic<?> var0) {
/* 175 */     var0 = b(var0, "Owner", "Owner").orElse(var0);
/* 176 */     return b(var0, "Thrower", "Thrower").orElse(var0);
/*     */   }
/*     */   
/*     */   private static Dynamic<?> j(Dynamic<?> var0) {
/* 180 */     Optional<Dynamic<?>> var1 = var0.get("TrustedUUIDs").result().map(var1 -> var0.createList(var1.asStream().map(())));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 188 */     return (Dynamic)DataFixUtils.orElse(var1.map(var1 -> var0.remove("TrustedUUIDs").set("Trusted", var1)), var0);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static Dynamic<?> k(Dynamic<?> var0) {
/* 194 */     return a(var0, "HurtBy", "HurtBy").orElse(var0);
/*     */   }
/*     */   
/*     */   private static Dynamic<?> l(Dynamic<?> var0) {
/* 198 */     Dynamic<?> var1 = m(var0);
/* 199 */     return a(var1, "OwnerUUID", "Owner").orElse(var1);
/*     */   }
/*     */   
/*     */   private static Dynamic<?> m(Dynamic<?> var0) {
/* 203 */     Dynamic<?> var1 = n(var0);
/* 204 */     return c(var1, "LoveCause", "LoveCause").orElse(var1);
/*     */   }
/*     */   
/*     */   private static Dynamic<?> n(Dynamic<?> var0) {
/* 208 */     return b(var0).update("Leash", var0 -> (Dynamic)c(var0, "UUID", "UUID").orElse(var0));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static Dynamic<?> b(Dynamic<?> var0) {
/* 214 */     return var0.update("Attributes", var1 -> var0.createList(var1.asStream().map(())));
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
/*     */   private static Dynamic<?> o(Dynamic<?> var0) {
/* 226 */     return (Dynamic)DataFixUtils.orElse(var0.get("OwnerUUID").result().map(var1 -> var0.remove("OwnerUUID").set("Owner", var1)), var0);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static Dynamic<?> c(Dynamic<?> var0) {
/* 232 */     return c(var0, "UUID", "UUID").orElse(var0);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataConverterEntityUUID.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
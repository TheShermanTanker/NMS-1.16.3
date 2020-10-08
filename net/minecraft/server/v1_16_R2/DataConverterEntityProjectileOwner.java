/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.mojang.datafixers.DSL;
/*     */ import com.mojang.datafixers.DataFix;
/*     */ import com.mojang.datafixers.TypeRewriteRule;
/*     */ import com.mojang.datafixers.Typed;
/*     */ import com.mojang.datafixers.schemas.Schema;
/*     */ import com.mojang.datafixers.types.Type;
/*     */ import com.mojang.serialization.Dynamic;
/*     */ import com.mojang.serialization.OptionalDynamic;
/*     */ import java.util.Arrays;
/*     */ import java.util.function.Function;
/*     */ 
/*     */ 
/*     */ public class DataConverterEntityProjectileOwner
/*     */   extends DataFix
/*     */ {
/*     */   public DataConverterEntityProjectileOwner(Schema var0) {
/*  19 */     super(var0, false);
/*     */   }
/*     */ 
/*     */   
/*     */   protected TypeRewriteRule makeRule() {
/*  24 */     Schema var0 = getInputSchema();
/*  25 */     return fixTypeEverywhereTyped("EntityProjectileOwner", var0.getType(DataConverterTypes.ENTITY), this::a);
/*     */   }
/*     */   
/*     */   private Typed<?> a(Typed<?> var0) {
/*  29 */     var0 = a(var0, "minecraft:egg", this::d);
/*  30 */     var0 = a(var0, "minecraft:ender_pearl", this::d);
/*  31 */     var0 = a(var0, "minecraft:experience_bottle", this::d);
/*  32 */     var0 = a(var0, "minecraft:snowball", this::d);
/*  33 */     var0 = a(var0, "minecraft:potion", this::d);
/*  34 */     var0 = a(var0, "minecraft:potion", this::c);
/*  35 */     var0 = a(var0, "minecraft:llama_spit", this::b);
/*  36 */     var0 = a(var0, "minecraft:arrow", this::a);
/*  37 */     var0 = a(var0, "minecraft:spectral_arrow", this::a);
/*  38 */     var0 = a(var0, "minecraft:trident", this::a);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  48 */     return var0;
/*     */   }
/*     */   
/*     */   private Dynamic<?> a(Dynamic<?> var0) {
/*  52 */     long var1 = var0.get("OwnerUUIDMost").asLong(0L);
/*  53 */     long var3 = var0.get("OwnerUUIDLeast").asLong(0L);
/*     */     
/*  55 */     return a(var0, var1, var3).remove("OwnerUUIDMost").remove("OwnerUUIDLeast");
/*     */   }
/*     */   
/*     */   private Dynamic<?> b(Dynamic<?> var0) {
/*  59 */     OptionalDynamic<?> var1 = var0.get("Owner");
/*  60 */     long var2 = var1.get("OwnerUUIDMost").asLong(0L);
/*  61 */     long var4 = var1.get("OwnerUUIDLeast").asLong(0L);
/*     */     
/*  63 */     return a(var0, var2, var4).remove("Owner");
/*     */   }
/*     */   
/*     */   private Dynamic<?> c(Dynamic<?> var0) {
/*  67 */     OptionalDynamic<?> var1 = var0.get("Potion");
/*  68 */     return var0.set("Item", var1.orElseEmptyMap()).remove("Potion");
/*     */   }
/*     */   
/*     */   private Dynamic<?> d(Dynamic<?> var0) {
/*  72 */     String var1 = "owner";
/*  73 */     OptionalDynamic<?> var2 = var0.get("owner");
/*  74 */     long var3 = var2.get("M").asLong(0L);
/*  75 */     long var5 = var2.get("L").asLong(0L);
/*     */     
/*  77 */     return a(var0, var3, var5).remove("owner");
/*     */   }
/*     */   
/*     */   private Dynamic<?> a(Dynamic<?> var0, long var1, long var3) {
/*  81 */     String var5 = "OwnerUUID";
/*  82 */     if (var1 != 0L && var3 != 0L) {
/*  83 */       return var0.set("OwnerUUID", var0.createIntList(Arrays.stream(a(var1, var3))));
/*     */     }
/*  85 */     return var0;
/*     */   }
/*     */   
/*     */   private static int[] a(long var0, long var2) {
/*  89 */     return new int[] { (int)(var0 >> 32L), (int)var0, (int)(var2 >> 32L), (int)var2 };
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Typed<?> a(Typed<?> var0, String var1, Function<Dynamic<?>, Dynamic<?>> var2) {
/*  98 */     Type<?> var3 = getInputSchema().getChoiceType(DataConverterTypes.ENTITY, var1);
/*  99 */     Type<?> var4 = getOutputSchema().getChoiceType(DataConverterTypes.ENTITY, var1);
/* 100 */     return var0.updateTyped(DSL.namedChoice(var1, var3), var4, var1 -> var1.update(DSL.remainderFinder(), var0));
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataConverterEntityProjectileOwner.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
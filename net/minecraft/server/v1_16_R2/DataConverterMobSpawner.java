/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.datafixers.DSL;
/*    */ import com.mojang.datafixers.DataFix;
/*    */ import com.mojang.datafixers.DataFixUtils;
/*    */ import com.mojang.datafixers.TypeRewriteRule;
/*    */ import com.mojang.datafixers.Typed;
/*    */ import com.mojang.datafixers.schemas.Schema;
/*    */ import com.mojang.datafixers.types.Type;
/*    */ import com.mojang.datafixers.util.Pair;
/*    */ import com.mojang.serialization.DataResult;
/*    */ import com.mojang.serialization.Dynamic;
/*    */ import java.util.Optional;
/*    */ import java.util.stream.Stream;
/*    */ 
/*    */ public class DataConverterMobSpawner
/*    */   extends DataFix {
/*    */   public DataConverterMobSpawner(Schema var0, boolean var1) {
/* 19 */     super(var0, var1);
/*    */   }
/*    */   
/*    */   private Dynamic<?> a(Dynamic<?> var0) {
/* 23 */     if (!"MobSpawner".equals(var0.get("id").asString(""))) {
/* 24 */       return var0;
/*    */     }
/*    */     
/* 27 */     Optional<String> var1 = var0.get("EntityId").asString().result();
/* 28 */     if (var1.isPresent()) {
/* 29 */       Dynamic<?> dynamic = (Dynamic)DataFixUtils.orElse(var0.get("SpawnData").result(), var0.emptyMap());
/* 30 */       dynamic = dynamic.set("id", dynamic.createString(((String)var1.get()).isEmpty() ? "Pig" : var1.get()));
/* 31 */       var0 = var0.set("SpawnData", dynamic);
/*    */       
/* 33 */       var0 = var0.remove("EntityId");
/*    */     } 
/*    */     
/* 36 */     Optional<? extends Stream<? extends Dynamic<?>>> var2 = var0.get("SpawnPotentials").asStreamOpt().result();
/* 37 */     if (var2.isPresent()) {
/* 38 */       var0 = var0.set("SpawnPotentials", var0.createList(((Stream)var2.get()).map(var0 -> {
/*    */                 Optional<String> var1 = var0.get("Type").asString().result();
/*    */                 
/*    */                 if (var1.isPresent()) {
/*    */                   Dynamic<?> var2 = ((Dynamic)DataFixUtils.orElse(var0.get("Properties").result(), var0.emptyMap())).set("id", var0.createString(var1.get()));
/*    */                   
/*    */                   return var0.set("Entity", var2).remove("Type").remove("Properties");
/*    */                 } 
/*    */                 
/*    */                 return var0;
/*    */               })));
/*    */     }
/*    */     
/* 51 */     return var0;
/*    */   }
/*    */ 
/*    */   
/*    */   public TypeRewriteRule makeRule() {
/* 56 */     Type<?> var0 = getOutputSchema().getType(DataConverterTypes.UNTAGGED_SPAWNER);
/* 57 */     return fixTypeEverywhereTyped("MobSpawnerEntityIdentifiersFix", getInputSchema().getType(DataConverterTypes.UNTAGGED_SPAWNER), var0, var1 -> {
/*    */           Dynamic<?> var2 = (Dynamic)var1.get(DSL.remainderFinder());
/*    */           var2 = var2.set("id", var2.createString("MobSpawner"));
/*    */           DataResult<? extends Pair<? extends Typed<?>, ?>> var3 = var0.readTyped(a(var2));
/*    */           return !var3.result().isPresent() ? var1 : (Typed)((Pair)var3.result().get()).getFirst();
/*    */         });
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataConverterMobSpawner.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
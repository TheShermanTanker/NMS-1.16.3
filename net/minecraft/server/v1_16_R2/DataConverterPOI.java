/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.ImmutableMap;
/*    */ import com.google.common.collect.Maps;
/*    */ import com.mojang.datafixers.DSL;
/*    */ import com.mojang.datafixers.DataFix;
/*    */ import com.mojang.datafixers.TypeRewriteRule;
/*    */ import com.mojang.datafixers.schemas.Schema;
/*    */ import com.mojang.datafixers.types.Type;
/*    */ import com.mojang.datafixers.util.Pair;
/*    */ import com.mojang.serialization.Dynamic;
/*    */ import com.mojang.serialization.DynamicOps;
/*    */ import java.util.Map;
/*    */ import java.util.Objects;
/*    */ import java.util.Optional;
/*    */ import java.util.function.Function;
/*    */ 
/*    */ public class DataConverterPOI
/*    */   extends DataFix {
/*    */   public DataConverterPOI(Schema var0, boolean var1) {
/* 21 */     super(var0, var1);
/*    */   }
/*    */ 
/*    */   
/*    */   protected TypeRewriteRule makeRule() {
/* 26 */     Type<Pair<String, Dynamic<?>>> var0 = DSL.named(DataConverterTypes.POI_CHUNK.typeName(), DSL.remainderType());
/*    */     
/* 28 */     if (!Objects.equals(var0, getInputSchema().getType(DataConverterTypes.POI_CHUNK))) {
/* 29 */       throw new IllegalStateException("Poi type is not what was expected.");
/*    */     }
/* 31 */     return fixTypeEverywhere("POI reorganization", var0, var0 -> ());
/*    */   }
/*    */   
/*    */   private static <T> Dynamic<T> a(Dynamic<T> var0) {
/* 35 */     Map<Dynamic<T>, Dynamic<T>> var1 = Maps.newHashMap();
/* 36 */     for (int var2 = 0; var2 < 16; var2++) {
/* 37 */       String var3 = String.valueOf(var2);
/* 38 */       Optional<Dynamic<T>> var4 = var0.get(var3).result();
/* 39 */       if (var4.isPresent()) {
/* 40 */         Dynamic<T> var5 = var4.get();
/* 41 */         Dynamic<T> var6 = var0.createMap((Map)ImmutableMap.of(var0.createString("Records"), var5));
/* 42 */         var1.put(var0.createInt(var2), var6);
/* 43 */         var0 = var0.remove(var3);
/*    */       } 
/*    */     } 
/*    */     
/* 47 */     return var0.set("Sections", var0.createMap(var1));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataConverterPOI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
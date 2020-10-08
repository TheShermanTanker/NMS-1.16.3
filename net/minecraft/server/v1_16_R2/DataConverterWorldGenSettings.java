/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.annotations.VisibleForTesting;
/*    */ import com.google.common.base.Splitter;
/*    */ import com.mojang.datafixers.DSL;
/*    */ import com.mojang.datafixers.DataFix;
/*    */ import com.mojang.datafixers.DataFixUtils;
/*    */ import com.mojang.datafixers.TypeRewriteRule;
/*    */ import com.mojang.datafixers.Typed;
/*    */ import com.mojang.datafixers.schemas.Schema;
/*    */ import com.mojang.serialization.Dynamic;
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import java.util.stream.Collectors;
/*    */ import java.util.stream.StreamSupport;
/*    */ import org.bukkit.craftbukkit.libs.org.apache.commons.lang3.math.NumberUtils;
/*    */ 
/*    */ public class DataConverterWorldGenSettings extends DataFix {
/*    */   public DataConverterWorldGenSettings(Schema var0, boolean var1) {
/* 20 */     super(var0, var1);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 27 */   private static final Splitter a = Splitter.on(';').limit(5);
/* 28 */   private static final Splitter b = Splitter.on(',');
/* 29 */   private static final Splitter c = Splitter.on('x').limit(2);
/* 30 */   private static final Splitter d = Splitter.on('*').limit(2);
/* 31 */   private static final Splitter e = Splitter.on(':').limit(3);
/*    */ 
/*    */   
/*    */   public TypeRewriteRule makeRule() {
/* 35 */     return fixTypeEverywhereTyped("LevelFlatGeneratorInfoFix", getInputSchema().getType(DataConverterTypes.LEVEL), var0 -> var0.update(DSL.remainderFinder(), this::a));
/*    */   }
/*    */   
/*    */   private Dynamic<?> a(Dynamic<?> var0) {
/* 39 */     if (var0.get("generatorName").asString("").equalsIgnoreCase("flat")) {
/* 40 */       return var0.update("generatorOptions", var0 -> (Dynamic)DataFixUtils.orElse(var0.asString().map(this::a).map(var0::createString).result(), var0));
/*    */     }
/* 42 */     return var0;
/*    */   } @VisibleForTesting
/*    */   String a(String var0) {
/*    */     int var3;
/*    */     String var4;
/* 47 */     if (var0.isEmpty()) {
/* 48 */       return "minecraft:bedrock,2*minecraft:dirt,minecraft:grass_block;1;village";
/*    */     }
/*    */     
/* 51 */     Iterator<String> var1 = a.split(var0).iterator();
/*    */     
/* 53 */     String var2 = var1.next();
/*    */ 
/*    */     
/* 56 */     if (var1.hasNext()) {
/* 57 */       var3 = NumberUtils.toInt(var2, 0);
/* 58 */       var4 = var1.next();
/*    */     } else {
/* 60 */       var3 = 0;
/* 61 */       var4 = var2;
/*    */     } 
/*    */     
/* 64 */     if (var3 < 0 || var3 > 3) {
/* 65 */       return "minecraft:bedrock,2*minecraft:dirt,minecraft:grass_block;1;village";
/*    */     }
/*    */     
/* 68 */     StringBuilder var5 = new StringBuilder();
/*    */     
/* 70 */     Splitter var6 = (var3 < 3) ? c : d;
/*    */     
/* 72 */     var5.append(StreamSupport.stream(b.split(var4).spliterator(), false).map(var2 -> {
/*    */             int var3;
/*    */             
/*    */             String var4;
/*    */             
/*    */             List<String> var5 = var0.splitToList(var2);
/*    */             
/*    */             if (var5.size() == 2) {
/*    */               var3 = NumberUtils.toInt(var5.get(0));
/*    */               var4 = var5.get(1);
/*    */             } else {
/*    */               var3 = 1;
/*    */               var4 = var5.get(0);
/*    */             } 
/*    */             List<String> var6 = e.splitToList(var4);
/*    */             int var7 = ((String)var6.get(0)).equals("minecraft") ? 1 : 0;
/*    */             String var8 = var6.get(var7);
/*    */             int var9 = (var1 == 3) ? DataConverterEntityBlockState.a("minecraft:" + var8) : NumberUtils.toInt(var8, 0);
/*    */             int var10 = var7 + 1;
/*    */             int var11 = (var6.size() > var10) ? NumberUtils.toInt(var6.get(var10), 0) : 0;
/*    */             return ((var3 == 1) ? "" : (var3 + "*")) + DataConverterFlattenData.b(var9 << 4 | var11).get("Name").asString("");
/* 93 */           }).collect(Collectors.joining(",")));
/*    */     
/* 95 */     while (var1.hasNext()) {
/* 96 */       var5.append(';').append(var1.next());
/*    */     }
/*    */     
/* 99 */     return var5.toString();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataConverterWorldGenSettings.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
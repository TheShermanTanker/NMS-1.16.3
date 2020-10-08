/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.datafixers.DSL;
/*    */ import com.mojang.datafixers.Typed;
/*    */ import com.mojang.datafixers.schemas.Schema;
/*    */ import com.mojang.datafixers.types.Type;
/*    */ import com.mojang.datafixers.util.Pair;
/*    */ import com.mojang.serialization.Dynamic;
/*    */ import java.util.Objects;
/*    */ 
/*    */ public class DataConverterHorse
/*    */   extends DataConverterEntityName {
/*    */   public DataConverterHorse(Schema var0, boolean var1) {
/* 14 */     super("EntityHorseSplitFix", var0, var1);
/*    */   }
/*    */ 
/*    */   
/*    */   protected Pair<String, Typed<?>> a(String var0, Typed<?> var1) {
/* 19 */     Dynamic<?> var2 = (Dynamic)var1.get(DSL.remainderFinder());
/* 20 */     if (Objects.equals("EntityHorse", var0)) {
/*    */       
/* 22 */       int var4 = var2.get("Type").asInt(0);
/* 23 */       switch (var4)
/*    */       
/*    */       { default:
/* 26 */           var3 = "Horse";
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
/* 41 */           var2.remove("Type");
/*    */           
/* 43 */           var5 = (Type)getOutputSchema().findChoiceType(DataConverterTypes.ENTITY).types().get(var3);
/* 44 */           return Pair.of(var3, ((Pair)var1.write().flatMap(var5::readTyped).result().orElseThrow(() -> new IllegalStateException("Could not parse the new horse"))).getFirst());case 1: var3 = "Donkey"; var2.remove("Type"); var5 = (Type)getOutputSchema().findChoiceType(DataConverterTypes.ENTITY).types().get(var3); return Pair.of(var3, ((Pair)var1.write().flatMap(var5::readTyped).result().orElseThrow(() -> new IllegalStateException("Could not parse the new horse"))).getFirst());case 2: var3 = "Mule"; var2.remove("Type"); var5 = (Type)getOutputSchema().findChoiceType(DataConverterTypes.ENTITY).types().get(var3); return Pair.of(var3, ((Pair)var1.write().flatMap(var5::readTyped).result().orElseThrow(() -> new IllegalStateException("Could not parse the new horse"))).getFirst());case 3: var3 = "ZombieHorse"; var2.remove("Type"); var5 = (Type)getOutputSchema().findChoiceType(DataConverterTypes.ENTITY).types().get(var3); return Pair.of(var3, ((Pair)var1.write().flatMap(var5::readTyped).result().orElseThrow(() -> new IllegalStateException("Could not parse the new horse"))).getFirst());case 4: break; }  String var3 = "SkeletonHorse"; var2.remove("Type"); Type<?> var5 = (Type)getOutputSchema().findChoiceType(DataConverterTypes.ENTITY).types().get(var3); return Pair.of(var3, ((Pair)var1.write().flatMap(var5::readTyped).result().orElseThrow(() -> new IllegalStateException("Could not parse the new horse"))).getFirst());
/*    */     } 
/* 46 */     return Pair.of(var0, var1);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataConverterHorse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
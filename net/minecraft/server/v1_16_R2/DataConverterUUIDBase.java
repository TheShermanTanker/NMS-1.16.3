/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.datafixers.DSL;
/*    */ import com.mojang.datafixers.DataFix;
/*    */ import com.mojang.datafixers.Typed;
/*    */ import com.mojang.datafixers.schemas.Schema;
/*    */ import com.mojang.datafixers.types.Type;
/*    */ import com.mojang.serialization.Dynamic;
/*    */ import java.util.Arrays;
/*    */ import java.util.Optional;
/*    */ import java.util.UUID;
/*    */ import java.util.function.Function;
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ 
/*    */ 
/*    */ public abstract class DataConverterUUIDBase
/*    */   extends DataFix
/*    */ {
/* 20 */   protected static final Logger LOGGER = LogManager.getLogger();
/*    */   protected DSL.TypeReference b;
/*    */   
/*    */   public DataConverterUUIDBase(Schema var0, DSL.TypeReference var1) {
/* 24 */     super(var0, false);
/* 25 */     this.b = var1;
/*    */   }
/*    */   
/*    */   protected Typed<?> a(Typed<?> var0, String var1, Function<Dynamic<?>, Dynamic<?>> var2) {
/* 29 */     Type<?> var3 = getInputSchema().getChoiceType(this.b, var1);
/* 30 */     Type<?> var4 = getOutputSchema().getChoiceType(this.b, var1);
/* 31 */     return var0.updateTyped(DSL.namedChoice(var1, var3), var4, var1 -> var1.update(DSL.remainderFinder(), var0));
/*    */   }
/*    */   
/*    */   protected static Optional<Dynamic<?>> a(Dynamic<?> var0, String var1, String var2) {
/* 35 */     return a(var0, var1).map(var3 -> var0.remove(var1).set(var2, var3));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected static Optional<Dynamic<?>> b(Dynamic<?> var0, String var1, String var2) {
/* 41 */     return var0.get(var1).result().flatMap(DataConverterUUIDBase::a).map(var3 -> var0.remove(var1).set(var2, var3));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected static Optional<Dynamic<?>> c(Dynamic<?> var0, String var1, String var2) {
/* 47 */     String var3 = var1 + "Most";
/* 48 */     String var4 = var1 + "Least";
/* 49 */     return d(var0, var3, var4).map(var4 -> var0.remove(var1).remove(var2).set(var3, var4));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected static Optional<Dynamic<?>> a(Dynamic<?> var0, String var1) {
/* 55 */     return var0.get(var1).result().flatMap(var1 -> {
/*    */           String var2 = var1.asString(null);
/*    */           if (var2 != null) {
/*    */             try {
/*    */               UUID var3 = UUID.fromString(var2);
/*    */               return a(var0, var3.getMostSignificantBits(), var3.getLeastSignificantBits());
/* 61 */             } catch (IllegalArgumentException illegalArgumentException) {}
/*    */           }
/*    */           return Optional.empty();
/*    */         });
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected static Optional<Dynamic<?>> a(Dynamic<?> var0) {
/* 70 */     return d(var0, "M", "L");
/*    */   }
/*    */   
/*    */   protected static Optional<Dynamic<?>> d(Dynamic<?> var0, String var1, String var2) {
/* 74 */     long var3 = var0.get(var1).asLong(0L);
/* 75 */     long var5 = var0.get(var2).asLong(0L);
/* 76 */     if (var3 == 0L || var5 == 0L) {
/* 77 */       return Optional.empty();
/*    */     }
/* 79 */     return a(var0, var3, var5);
/*    */   }
/*    */   
/*    */   protected static Optional<Dynamic<?>> a(Dynamic<?> var0, long var1, long var3) {
/* 83 */     return Optional.of(var0.createIntList(Arrays.stream(new int[] { (int)(var1 >> 32L), (int)var1, (int)(var3 >> 32L), (int)var3 })));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataConverterUUIDBase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
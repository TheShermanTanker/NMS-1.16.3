/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.datafixers.DSL;
/*    */ import com.mojang.datafixers.Typed;
/*    */ import com.mojang.datafixers.schemas.Schema;
/*    */ import com.mojang.datafixers.util.Pair;
/*    */ import com.mojang.serialization.Dynamic;
/*    */ 
/*    */ public abstract class DataConverterEntityNameAbstract extends DataConverterEntityName {
/*    */   public DataConverterEntityNameAbstract(String var0, Schema var1, boolean var2) {
/* 11 */     super(var0, var1, var2);
/*    */   }
/*    */ 
/*    */   
/*    */   protected Pair<String, Typed<?>> a(String var0, Typed<?> var1) {
/* 16 */     Pair<String, Dynamic<?>> var2 = a(var0, (Dynamic)var1.getOrCreate(DSL.remainderFinder()));
/* 17 */     return Pair.of(var2.getFirst(), var1.set(DSL.remainderFinder(), var2.getSecond()));
/*    */   }
/*    */   
/*    */   protected abstract Pair<String, Dynamic<?>> a(String paramString, Dynamic<?> paramDynamic);
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataConverterEntityNameAbstract.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
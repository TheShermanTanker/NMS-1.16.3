/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.ImmutableMap;
/*    */ import com.mojang.datafixers.DSL;
/*    */ import com.mojang.datafixers.Typed;
/*    */ import com.mojang.datafixers.schemas.Schema;
/*    */ import com.mojang.datafixers.util.Pair;
/*    */ import com.mojang.serialization.Dynamic;
/*    */ import java.util.Map;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DataConverterMemoryExpiry
/*    */   extends DataConverterNamedEntity
/*    */ {
/*    */   public DataConverterMemoryExpiry(Schema var0, String var1) {
/* 31 */     super(var0, false, "Memory expiry data fix (" + var1 + ")", DataConverterTypes.ENTITY, var1);
/*    */   }
/*    */ 
/*    */   
/*    */   protected Typed<?> a(Typed<?> var0) {
/* 36 */     return var0.update(DSL.remainderFinder(), this::a);
/*    */   }
/*    */   
/*    */   public Dynamic<?> a(Dynamic<?> var0) {
/* 40 */     return var0.update("Brain", this::b);
/*    */   }
/*    */   
/*    */   private Dynamic<?> b(Dynamic<?> var0) {
/* 44 */     return var0.update("memories", this::c);
/*    */   }
/*    */   
/*    */   private Dynamic<?> c(Dynamic<?> var0) {
/* 48 */     return var0.updateMapValues(this::a);
/*    */   }
/*    */   
/*    */   private Pair<Dynamic<?>, Dynamic<?>> a(Pair<Dynamic<?>, Dynamic<?>> var0) {
/* 52 */     return var0.mapSecond(this::d);
/*    */   }
/*    */   
/*    */   private Dynamic<?> d(Dynamic<?> var0) {
/* 56 */     return var0.createMap((Map)ImmutableMap.of(var0
/* 57 */           .createString("value"), var0));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataConverterMemoryExpiry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
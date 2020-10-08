/*    */ package net.minecraft.server.v1_16_R2;
/*    */ import com.mojang.datafixers.DataFixUtils;
/*    */ import com.mojang.datafixers.Typed;
/*    */ import com.mojang.datafixers.schemas.Schema;
/*    */ import com.mojang.serialization.Dynamic;
/*    */ import java.util.stream.Stream;
/*    */ 
/*    */ public class DataConverterGossip extends DataConverterNamedEntity {
/*    */   public DataConverterGossip(Schema var0, String var1) {
/* 10 */     super(var0, false, "Gossip for for " + var1, DataConverterTypes.ENTITY, var1);
/*    */   }
/*    */ 
/*    */   
/*    */   protected Typed<?> a(Typed<?> var0) {
/* 15 */     return var0.update(DSL.remainderFinder(), var0 -> var0.update("Gossips", ()));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataConverterGossip.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
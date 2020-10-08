/*    */ package net.minecraft.server.v1_16_R2;
/*    */ import com.mojang.datafixers.DSL;
/*    */ import com.mojang.datafixers.DataFix;
/*    */ import com.mojang.datafixers.TypeRewriteRule;
/*    */ import com.mojang.datafixers.Typed;
/*    */ import com.mojang.datafixers.schemas.Schema;
/*    */ import com.mojang.serialization.Dynamic;
/*    */ 
/*    */ public class DataConverterRedstoneConnections extends DataFix {
/*    */   public DataConverterRedstoneConnections(Schema var0) {
/* 11 */     super(var0, false);
/*    */   }
/*    */ 
/*    */   
/*    */   protected TypeRewriteRule makeRule() {
/* 16 */     Schema var0 = getInputSchema();
/* 17 */     return fixTypeEverywhereTyped("RedstoneConnectionsFix", var0.getType(DataConverterTypes.BLOCK_STATE), var0 -> var0.update(DSL.remainderFinder(), this::a));
/*    */   }
/*    */   
/*    */   private <T> Dynamic<T> a(Dynamic<T> var0) {
/* 21 */     boolean var1 = var0.get("Name").asString().result().filter("minecraft:redstone_wire"::equals).isPresent();
/* 22 */     if (!var1) {
/* 23 */       return var0;
/*    */     }
/*    */     
/* 26 */     return var0.update("Properties", var0 -> {
/*    */           String var1 = var0.get("east").asString("none");
/*    */           String var2 = var0.get("west").asString("none");
/*    */           String var3 = var0.get("north").asString("none");
/*    */           String var4 = var0.get("south").asString("none");
/* 31 */           boolean var5 = (a(var1) || a(var2));
/* 32 */           boolean var6 = (a(var3) || a(var4));
/*    */           
/* 34 */           String var7 = (!a(var1) && !var6) ? "side" : var1;
/* 35 */           String var8 = (!a(var2) && !var6) ? "side" : var2;
/* 36 */           String var9 = (!a(var3) && !var5) ? "side" : var3;
/* 37 */           String var10 = (!a(var4) && !var5) ? "side" : var4;
/*    */           return var0.update("east", ()).update("west", ()).update("north", ()).update("south", ());
/*    */         });
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private static boolean a(String var0) {
/* 48 */     return !"none".equals(var0);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataConverterRedstoneConnections.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
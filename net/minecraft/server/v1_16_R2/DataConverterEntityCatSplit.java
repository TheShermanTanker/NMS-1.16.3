/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.datafixers.schemas.Schema;
/*    */ import com.mojang.datafixers.util.Pair;
/*    */ import com.mojang.serialization.Dynamic;
/*    */ import java.util.Objects;
/*    */ 
/*    */ public class DataConverterEntityCatSplit
/*    */   extends DataConverterEntityNameAbstract {
/*    */   public DataConverterEntityCatSplit(Schema var0, boolean var1) {
/* 11 */     super("EntityCatSplitFix", var0, var1);
/*    */   }
/*    */ 
/*    */   
/*    */   protected Pair<String, Dynamic<?>> a(String var0, Dynamic<?> var1) {
/* 16 */     if (Objects.equals("minecraft:ocelot", var0)) {
/* 17 */       int var2 = var1.get("CatType").asInt(0);
/* 18 */       if (var2 == 0) {
/* 19 */         String var3 = var1.get("Owner").asString("");
/* 20 */         String var4 = var1.get("OwnerUUID").asString("");
/* 21 */         if (var3.length() > 0 || var4.length() > 0) {
/* 22 */           var1.set("Trusting", var1.createBoolean(true));
/*    */         }
/* 24 */       } else if (var2 > 0 && var2 < 4) {
/* 25 */         var1 = var1.set("CatType", var1.createInt(var2));
/* 26 */         var1 = var1.set("OwnerUUID", var1.createString(var1.get("OwnerUUID").asString("")));
/* 27 */         return Pair.of("minecraft:cat", var1);
/*    */       } 
/*    */     } 
/*    */     
/* 31 */     return Pair.of(var0, var1);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataConverterEntityCatSplit.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
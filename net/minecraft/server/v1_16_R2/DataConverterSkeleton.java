/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.datafixers.schemas.Schema;
/*    */ import com.mojang.datafixers.util.Pair;
/*    */ import com.mojang.serialization.Dynamic;
/*    */ import java.util.Objects;
/*    */ 
/*    */ public class DataConverterSkeleton
/*    */   extends DataConverterEntityNameAbstract {
/*    */   public DataConverterSkeleton(Schema var0, boolean var1) {
/* 11 */     super("EntitySkeletonSplitFix", var0, var1);
/*    */   }
/*    */ 
/*    */   
/*    */   protected Pair<String, Dynamic<?>> a(String var0, Dynamic<?> var1) {
/* 16 */     if (Objects.equals(var0, "Skeleton")) {
/* 17 */       int var2 = var1.get("SkeletonType").asInt(0);
/* 18 */       if (var2 == 1) {
/* 19 */         var0 = "WitherSkeleton";
/* 20 */       } else if (var2 == 2) {
/* 21 */         var0 = "Stray";
/*    */       } 
/*    */     } 
/* 24 */     return Pair.of(var0, var1);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataConverterSkeleton.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
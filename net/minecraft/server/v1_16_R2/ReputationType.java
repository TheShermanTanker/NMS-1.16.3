/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.ImmutableMap;
/*    */ import java.util.Map;
/*    */ import java.util.function.Function;
/*    */ import java.util.stream.Stream;
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ public enum ReputationType
/*    */ {
/* 11 */   MAJOR_NEGATIVE("major_negative", -5, 100, 10, 10), MINOR_NEGATIVE("minor_negative", -1, 200, 20, 20), MINOR_POSITIVE("minor_positive", 1, 200, 1, 5), MAJOR_POSITIVE("major_positive", 5, 100, 0, 100), TRADING("trading", 1, 25, 2, 20); private static final Map<String, ReputationType> k; public final int j; public final int i;
/*    */   
/*    */   public int getWeight() {
/* 14 */     return this.g;
/*    */   }
/*    */   public final int h; public final int g; public final String f;
/*    */   static {
/* 18 */     k = (Map<String, ReputationType>)Stream.<ReputationType>of(values()).collect(ImmutableMap.toImmutableMap(reputationtype -> reputationtype.f, 
/*    */           
/* 20 */           Function.identity()));
/*    */   }
/*    */   ReputationType(String s, int i, int j, int k, int l) {
/* 23 */     this.f = s;
/* 24 */     this.g = i;
/* 25 */     this.h = j;
/* 26 */     this.i = k;
/* 27 */     this.j = l;
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public static ReputationType a(String s) {
/* 32 */     return k.get(s);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ReputationType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ public interface OperatorBoolean {
/*    */   static {
/*  5 */     NOT_OR = ((var0, var1) -> (!var0 && !var1));
/*  6 */     ONLY_SECOND = ((var0, var1) -> (var1 && !var0));
/*  7 */     NOT_FIRST = ((var0, var1) -> !var0);
/*  8 */     ONLY_FIRST = ((var0, var1) -> (var0 && !var1));
/*  9 */     NOT_SECOND = ((var0, var1) -> !var1);
/* 10 */     NOT_SAME = ((var0, var1) -> (var0 != var1));
/* 11 */     NOT_AND = ((var0, var1) -> (!var0 || !var1));
/* 12 */     AND = ((var0, var1) -> (var0 && var1));
/* 13 */     SAME = ((var0, var1) -> (var0 == var1));
/* 14 */     SECOND = ((var0, var1) -> var1);
/* 15 */     CAUSES = ((var0, var1) -> (!var0 || var1));
/* 16 */     FIRST = ((var0, var1) -> var0);
/* 17 */     CAUSED_BY = ((var0, var1) -> (var0 || !var1));
/* 18 */     OR = ((var0, var1) -> (var0 || var1));
/*    */   }
/*    */   
/*    */   public static final OperatorBoolean FALSE = (var0, var1) -> false;
/*    */   public static final OperatorBoolean NOT_OR;
/*    */   public static final OperatorBoolean ONLY_SECOND;
/*    */   public static final OperatorBoolean NOT_FIRST;
/*    */   public static final OperatorBoolean ONLY_FIRST;
/*    */   public static final OperatorBoolean NOT_SECOND;
/*    */   public static final OperatorBoolean NOT_SAME;
/*    */   public static final OperatorBoolean NOT_AND;
/*    */   public static final OperatorBoolean AND;
/*    */   public static final OperatorBoolean SAME;
/*    */   public static final OperatorBoolean SECOND;
/*    */   public static final OperatorBoolean CAUSES;
/*    */   public static final OperatorBoolean FIRST;
/*    */   public static final OperatorBoolean CAUSED_BY;
/*    */   public static final OperatorBoolean OR;
/*    */   public static final OperatorBoolean TRUE = (var0, var1) -> true;
/*    */   
/*    */   boolean apply(boolean paramBoolean1, boolean paramBoolean2);
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\OperatorBoolean.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
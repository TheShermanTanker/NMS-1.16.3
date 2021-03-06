/*    */ package com.mojang.datafixers.util;
/*    */ 
/*    */ import java.util.function.BiFunction;
/*    */ import java.util.function.Function;
/*    */ 
/*    */ 
/*    */ public interface Function15<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, R>
/*    */ {
/*    */   default Function<T1, Function14<T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, R>> curry() {
/* 10 */     return t1 -> ();
/*    */   }
/*    */   
/*    */   default BiFunction<T1, T2, Function13<T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, R>> curry2() {
/* 14 */     return (t1, t2) -> ();
/*    */   }
/*    */   
/*    */   default Function3<T1, T2, T3, Function12<T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, R>> curry3() {
/* 18 */     return (t1, t2, t3) -> ();
/*    */   }
/*    */   
/*    */   default Function4<T1, T2, T3, T4, Function11<T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, R>> curry4() {
/* 22 */     return (t1, t2, t3, t4) -> ();
/*    */   }
/*    */   
/*    */   default Function5<T1, T2, T3, T4, T5, Function10<T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, R>> curry5() {
/* 26 */     return (t1, t2, t3, t4, t5) -> ();
/*    */   }
/*    */   
/*    */   default Function6<T1, T2, T3, T4, T5, T6, Function9<T7, T8, T9, T10, T11, T12, T13, T14, T15, R>> curry6() {
/* 30 */     return (t1, t2, t3, t4, t5, t6) -> ();
/*    */   }
/*    */   
/*    */   default Function7<T1, T2, T3, T4, T5, T6, T7, Function8<T8, T9, T10, T11, T12, T13, T14, T15, R>> curry7() {
/* 34 */     return (t1, t2, t3, t4, t5, t6, t7) -> ();
/*    */   }
/*    */   
/*    */   default Function8<T1, T2, T3, T4, T5, T6, T7, T8, Function7<T9, T10, T11, T12, T13, T14, T15, R>> curry8() {
/* 38 */     return (t1, t2, t3, t4, t5, t6, t7, t8) -> ();
/*    */   }
/*    */   
/*    */   default Function9<T1, T2, T3, T4, T5, T6, T7, T8, T9, Function6<T10, T11, T12, T13, T14, T15, R>> curry9() {
/* 42 */     return (t1, t2, t3, t4, t5, t6, t7, t8, t9) -> ();
/*    */   }
/*    */   
/*    */   default Function10<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, Function5<T11, T12, T13, T14, T15, R>> curry10() {
/* 46 */     return (t1, t2, t3, t4, t5, t6, t7, t8, t9, t10) -> ();
/*    */   }
/*    */   
/*    */   default Function11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, Function4<T12, T13, T14, T15, R>> curry11() {
/* 50 */     return (t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11) -> ();
/*    */   }
/*    */   
/*    */   default Function12<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, Function3<T13, T14, T15, R>> curry12() {
/* 54 */     return (t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12) -> ();
/*    */   }
/*    */   
/*    */   default Function13<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, BiFunction<T14, T15, R>> curry13() {
/* 58 */     return (t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13) -> ();
/*    */   }
/*    */   
/*    */   default Function14<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, Function<T15, R>> curry14() {
/* 62 */     return (t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14) -> ();
/*    */   }
/*    */   
/*    */   R apply(T1 paramT1, T2 paramT2, T3 paramT3, T4 paramT4, T5 paramT5, T6 paramT6, T7 paramT7, T8 paramT8, T9 paramT9, T10 paramT10, T11 paramT11, T12 paramT12, T13 paramT13, T14 paramT14, T15 paramT15);
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\mojang\datafixer\\util\Function15.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */